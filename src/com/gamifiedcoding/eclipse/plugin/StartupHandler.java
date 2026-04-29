package com.gamifiedcoding.eclipse.plugin;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.ui.IStartup;
import com.gamifiedcoding.eclipse.plugin.listeners.ResourceChangeListener;
public class StartupHandler implements IStartup {
    private ResourceChangeListener resourceListener;
    @Override
    public void earlyStartup() {
        System.out.println("🎮 Gamified Coding Plugin Started!");
        registerListeners();
    }
    
    private void registerListeners() {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        resourceListener = new ResourceChangeListener();
        workspace.addResourceChangeListener(resourceListener);
        System.out.println("✅ Resource change listener registered!");
    }
}