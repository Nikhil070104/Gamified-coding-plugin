An Eclipse plugin that transforms coding into an engaging, game-like experience by rewarding developers with XP, levels, and achievements as they write code.

FEATURES:-
1.Real-time XP tracking: Earn +10 XP every time you save a Java file
2.Level progression: Level up every 100 XP
3.Visual notifications: Green popup notifications for XP gains, gold notifications for level-ups
4.Stats dashboard: Real-time view showing current level, total XP, and progress to next level
5.Achievement system: Unlock achievements like "First Steps" for saving your first file
6.Automatic detection: Plugin automatically detects file saves and awards XP

WHAT WAS COMPLETED:-
1.Implemented Features
2.Plugin lifecycle management (Activator)
3.Automatic startup (StartupHandler)
4.File save detection (ResourceChangeListener)
5.XP and level tracking (ScoreManager using Singleton pattern)
6.Real-time UI updates (Observer pattern)
7.Popup notifications (NotificationManager)
8.Stats dashboard view (StatsView)

Deferred Features
1.XP resets when Eclipse closes (not saved between sessions)
2.Only Java files supported (could extend to Python, C++, etc.)
3.Framework ready, but limited achievements implemented
4.No cooldown timer on saves

TECH STACK
1.Language: Java 11+
2.Framework: Eclipse Plugin Development Environment (PDE)
3.Architecture: OSGi bundles
4.UI Framework: SWT (Standard Widget Toolkit)
5.APIs Used: Eclipse Core Resources API (workspace/file management) ,Eclipse UI API (views and extensions) ,OSGi Bundle 6.Framework (plugin lifecycle)

BUILD INSTRUCTIONS:-
Prerequisites:- 1. Eclipse IDE, 2.Java JDK 11 or higher
	@@ -60,11 +60,11 @@ KNOWN ISSUES:-
AI ASSISTANCE:-
AI Tool Used: Chatgpt
Usage:
1.Architecture design guidance
2.Debugging assistance 
3.Design pattern recommendations

REFERENCES:-
1.Eclipse Plugin Development Guide: https://www.eclipse.org/articles/
2.Eclipse Platform API: https://help.eclipse.org/latest/
3.SWT Documentation: https://www.eclipse.org/swt/
