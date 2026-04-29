package com.gamifiedcoding.eclipse.plugin;
import java.util.ArrayList;
import java.util.List;
public class ScoreManager {  
    private static ScoreManager instance;
    private int totalXP;
    private int level;
    private List<String> achievements;
    private List<ScoreListener> listeners;    
    private ScoreManager() {
        this.totalXP = 0;
        this.level = 1;
        this.achievements = new ArrayList<>();
        this.listeners = new ArrayList<>();
    }    
    public static ScoreManager getInstance() {
        if (instance == null) {
            instance = new ScoreManager();
        }
        return instance;
    }  
    public void addXP(int xp, String action) {
        totalXP += xp;
        checkLevelUp();
        notifyListeners(xp, action);
    }    
    private void checkLevelUp() {
        int newLevel = (totalXP / 100) + 1; 
        if (newLevel > level) {
            level = newLevel;
            notifyLevelUp();
        }
    }    
    public void addAchievement(String achievement) {
        if (!achievements.contains(achievement)) {
            achievements.add(achievement);
            notifyAchievement(achievement);
        }
    }    
    public void addListener(ScoreListener listener) {
        listeners.add(listener);
    }    
    public void removeListener(ScoreListener listener) {
        listeners.remove(listener);
    }   
    private void notifyListeners(int xp, String action) {
        for (ScoreListener listener : listeners) {
            listener.onXPGained(xp, action);
        }
    }    
    private void notifyLevelUp() {
        for (ScoreListener listener : listeners) {
            listener.onLevelUp(level);
        }
    }   
    private void notifyAchievement(String achievement) {
        for (ScoreListener listener : listeners) {
            listener.onAchievementUnlocked(achievement);
        }
    }   
    public int getTotalXP() { return totalXP; }
    public int getLevel() { return level; }
    public List<String> getAchievements() { return achievements; }   
    public interface ScoreListener {
        void onXPGained(int xp, String action);
        void onLevelUp(int newLevel);
        void onAchievementUnlocked(String achievement);
    }
}