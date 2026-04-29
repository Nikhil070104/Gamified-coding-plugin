package com.gamifiedcoding.eclipse.plugin.views;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.ui.part.ViewPart;
import com.gamifiedcoding.eclipse.plugin.NotificationManager;
import com.gamifiedcoding.eclipse.plugin.ScoreManager;
public class StatsView extends ViewPart implements ScoreManager.ScoreListener {
    public static final String ID = "com.gamifiedcoding.eclipse.plugin.views.StatsView";    
    private Label levelLabel;
    private Label xpLabel;
    private ProgressBar progressBar;
    private Label progressLabel;
    private Label achievementsLabel;   
    @Override
    public void createPartControl(Composite parent) {
        parent.setLayout(new GridLayout(2, false));
        Label titleLabel = new Label(parent, SWT.BOLD);
        titleLabel.setText("🎮 Coding Stats");
        titleLabel.setFont(org.eclipse.jface.resource.JFaceResources.getFontRegistry().getBold(org.eclipse.jface.resource.JFaceResources.DEFAULT_FONT));
        GridData titleData = new GridData(GridData.FILL_HORIZONTAL);
        titleData.horizontalSpan = 2;
        titleLabel.setLayoutData(titleData);
        Label separator1 = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
        GridData sepData1 = new GridData(GridData.FILL_HORIZONTAL);
        sepData1.horizontalSpan = 2;
        separator1.setLayoutData(sepData1);
        Label levelTitleLabel = new Label(parent, SWT.NONE);
        levelTitleLabel.setText("Level:");
        levelLabel = new Label(parent, SWT.BOLD);
        levelLabel.setText(String.valueOf(ScoreManager.getInstance().getLevel()));
        levelLabel.setFont(org.eclipse.jface.resource.JFaceResources.getFontRegistry().getBold(org.eclipse.jface.resource.JFaceResources.DEFAULT_FONT));
        Label xpTitleLabel = new Label(parent, SWT.NONE);
        xpTitleLabel.setText("Total XP:");
        xpLabel = new Label(parent, SWT.BOLD);
        xpLabel.setText(String.valueOf(ScoreManager.getInstance().getTotalXP()));
        xpLabel.setFont(org.eclipse.jface.resource.JFaceResources.getFontRegistry().getBold(org.eclipse.jface.resource.JFaceResources.DEFAULT_FONT));
        Label separator2 = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
        GridData sepData2 = new GridData(GridData.FILL_HORIZONTAL);
        sepData2.horizontalSpan = 2;
        separator2.setLayoutData(sepData2);
        progressLabel = new Label(parent, SWT.NONE);
        progressLabel.setText("Progress to next level:");
        GridData progressLabelData = new GridData(GridData.FILL_HORIZONTAL);
        progressLabelData.horizontalSpan = 2;
        progressLabel.setLayoutData(progressLabelData);
        progressBar = new ProgressBar(parent, SWT.SMOOTH);
        GridData progressData = new GridData(GridData.FILL_HORIZONTAL);
        progressData.horizontalSpan = 2;
        progressBar.setLayoutData(progressData);
        progressBar.setMaximum(100);
        updateProgressBar();
        Label separator3 = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
        GridData sepData3 = new GridData(GridData.FILL_HORIZONTAL);
        sepData3.horizontalSpan = 2;
        separator3.setLayoutData(sepData3);
        Label achievementsTitle = new Label(parent, SWT.BOLD);
        achievementsTitle.setText("🏆 Achievements:");
        achievementsTitle.setFont(org.eclipse.jface.resource.JFaceResources.getFontRegistry().getBold(org.eclipse.jface.resource.JFaceResources.DEFAULT_FONT));
        GridData achievementsTitleData = new GridData(GridData.FILL_HORIZONTAL);
        achievementsTitleData.horizontalSpan = 2;
        achievementsTitle.setLayoutData(achievementsTitleData);
        achievementsLabel = new Label(parent, SWT.WRAP);
        achievementsLabel.setText(getAchievementsText());
        GridData achievementsData = new GridData(GridData.FILL_BOTH);
        achievementsData.horizontalSpan = 2;
        achievementsLabel.setLayoutData(achievementsData);
        ScoreManager.getInstance().addListener(this);
    } 
    private void updateProgressBar() {
        int currentXP = ScoreManager.getInstance().getTotalXP();
        int xpInLevel = currentXP % 100;  
        progressBar.setSelection(xpInLevel);
        progressLabel.setText("Progress to next level: " + xpInLevel + "/100 XP");
    }
    private String getAchievementsText() {
        if (ScoreManager.getInstance().getAchievements().isEmpty()) {
            return "No achievements yet!\nStart coding to unlock achievements!";
        }
        return String.join("\n", ScoreManager.getInstance().getAchievements());
    }
    @Override
    public void setFocus() {
        xpLabel.setFocus();
    }
    @Override
    public void dispose() {
        ScoreManager.getInstance().removeListener(this);
        super.dispose();
    }
    @Override
    public void onXPGained(int xp, String action) {
        getSite().getShell().getDisplay().asyncExec(() -> {
            if (!xpLabel.isDisposed()) {
                xpLabel.setText(String.valueOf(ScoreManager.getInstance().getTotalXP()));
                updateProgressBar();
            }
        });
    }
    @Override
    public void onLevelUp(int newLevel) {
        getSite().getShell().getDisplay().asyncExec(() -> {
            if (!levelLabel.isDisposed()) {
                levelLabel.setText(String.valueOf(newLevel));
                updateProgressBar();
                NotificationManager.showLevelUpNotification(newLevel);
            }
        });
    } 
    @Override
    public void onAchievementUnlocked(String achievement) {
        getSite().getShell().getDisplay().asyncExec(() -> {
            if (!achievementsLabel.isDisposed()) {
                achievementsLabel.setText(getAchievementsText());
            }
        });
    }
}