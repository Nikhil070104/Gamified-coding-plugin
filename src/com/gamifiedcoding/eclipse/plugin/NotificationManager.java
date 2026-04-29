package com.gamifiedcoding.eclipse.plugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
public class NotificationManager {    
    public static void showXPNotification(int xp, String action) {
        Display.getDefault().asyncExec(() -> {
            Shell shell = new Shell(Display.getDefault(), SWT.NO_TRIM | SWT.ON_TOP);
            shell.setLayout(new GridLayout());
            shell.setBackground(new Color(Display.getDefault(), 50, 205, 50));            
            Label label = new Label(shell, SWT.CENTER);
            label.setText("+" + xp + " XP\n" + action);
            label.setFont(org.eclipse.jface.resource.JFaceResources.getFontRegistry().getBold(org.eclipse.jface.resource.JFaceResources.DEFAULT_FONT));
            label.setForeground(new Color(Display.getDefault(), 255, 255, 255));
            label.setBackground(new Color(Display.getDefault(), 50, 205, 50));
            label.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            shell.pack();
            shell.setLocation(Display.getDefault().getPrimaryMonitor().getBounds().width - shell.getSize().x - 20, 50);
            shell.open(); 
            Display.getDefault().timerExec(2000, () -> {
                if (!shell.isDisposed()) {
                    shell.dispose();
                }
            });
        });
    }
    
    public static void showLevelUpNotification(int level) {
        Display.getDefault().asyncExec(() -> {
            Shell shell = new Shell(Display.getDefault(), SWT.NO_TRIM | SWT.ON_TOP);
            shell.setLayout(new GridLayout());
            shell.setBackground(new Color(Display.getDefault(), 255, 215, 0));
            Label label = new Label(shell, SWT.CENTER);
            label.setText("🎉 LEVEL UP! 🎉\nLevel " + level);
            label.setFont(org.eclipse.jface.resource.JFaceResources.getFontRegistry().getBold(org.eclipse.jface.resource.JFaceResources.DEFAULT_FONT));
            label.setForeground(new Color(Display.getDefault(), 0, 0, 0));
            label.setBackground(new Color(Display.getDefault(), 255, 215, 0));
            label.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            shell.pack();
            shell.setLocation( Display.getDefault().getPrimaryMonitor().getBounds().width / 2 - shell.getSize().x / 2, 100);           
            shell.open();
            Display.getDefault().timerExec(3000, () -> {
                if (!shell.isDisposed()) {
                    shell.dispose();                                 
                }
            });
        });
    }
    
    public static void showAchievementNotification(String achievementName, String description) {
        Display.getDefault().asyncExec(() -> {
            Shell shell = new Shell(Display.getDefault(), SWT.NO_TRIM | SWT.ON_TOP);
            shell.setLayout(new GridLayout());
            Color bgColor = new Color(Display.getDefault(), 255, 215, 0);
            shell.setBackground(bgColor); 
            Label label = new Label(shell, SWT.CENTER);
            label.setText("🎉 ACHIEVEMENT UNLOCKED! 🎉\n\n" + achievementName + "\n" + description);
            label.setFont(org.eclipse.jface.resource.JFaceResources.getFontRegistry().getBold(org.eclipse.jface.resource.JFaceResources.DEFAULT_FONT));
            label.setForeground(new Color(Display.getDefault(), 0, 0, 0));
            label.setBackground(bgColor);
            label.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            shell.pack();
            shell.setLocation(
                Display.getDefault().getPrimaryMonitor().getBounds().width / 2 - shell.getSize().x / 2,
                Display.getDefault().getPrimaryMonitor().getBounds().height / 2 - shell.getSize().y / 2
            );
            shell.open();
            Display.getDefault().timerExec(5000, () -> {
                if (!shell.isDisposed()) {
                    shell.dispose();
                }
            });
        });
    }
}