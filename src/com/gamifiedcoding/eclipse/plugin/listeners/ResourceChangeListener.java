package com.gamifiedcoding.eclipse.plugin.listeners;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResource;
import com.gamifiedcoding.eclipse.plugin.NotificationManager;
import com.gamifiedcoding.eclipse.plugin.ScoreManager;
public class ResourceChangeListener implements IResourceChangeListener {
    @Override
    public void resourceChanged(IResourceChangeEvent event) {
        if (event.getType() == IResourceChangeEvent.POST_CHANGE) {
            IResourceDelta delta = event.getDelta();
            if (delta != null) {
                processDelta(delta);
            }
        }
    }  
    private void processDelta(IResourceDelta delta) {
        IResource resource = delta.getResource();
        if (delta.getKind() == IResourceDelta.CHANGED) {
            if ((delta.getFlags() & IResourceDelta.CONTENT) != 0) {
                handleFileSaved(resource);
            }
        }
        for (IResourceDelta child : delta.getAffectedChildren()) {
            processDelta(child);
        }
    }   
    private void handleFileSaved(IResource resource) {
        String fileName = resource.getName();
        if (fileName.endsWith(".java")) {
            System.out.println("📝 Java file saved: " + fileName);
            ScoreManager.getInstance().addXP(10, "File Saved");
            NotificationManager.showXPNotification(10, "File Saved: " + fileName);
            checkFirstSaveAchievement();
        }
    } 
    private void checkFirstSaveAchievement() {
        ScoreManager scoreManager = ScoreManager.getInstance();
        if (scoreManager.getTotalXP() == 10 && scoreManager.getAchievements().isEmpty()) {
            scoreManager.addAchievement("🏆 First Steps - Saved your first file!");
            NotificationManager.showAchievementNotification("First Steps", "You saved your first file!");          
            System.out.println("🎉 Achievement Unlocked: First Steps!");
        }
    }
}