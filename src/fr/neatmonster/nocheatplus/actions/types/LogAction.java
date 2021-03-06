package fr.neatmonster.nocheatplus.actions.types;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.neatmonster.nocheatplus.checks.ViolationData;
import fr.neatmonster.nocheatplus.config.ConfPaths;
import fr.neatmonster.nocheatplus.config.ConfigFile;
import fr.neatmonster.nocheatplus.config.ConfigManager;
import fr.neatmonster.nocheatplus.players.Permissions;
import fr.neatmonster.nocheatplus.utilities.CheckUtils;

/*
 * M""MMMMMMMM                   MMP"""""""MM            dP   oo                   
 * M  MMMMMMMM                   M' .mmmm  MM            88                        
 * M  MMMMMMMM .d8888b. .d8888b. M         `M .d8888b. d8888P dP .d8888b. 88d888b. 
 * M  MMMMMMMM 88'  `88 88'  `88 M  MMMMM  MM 88'  `""   88   88 88'  `88 88'  `88 
 * M  MMMMMMMM 88.  .88 88.  .88 M  MMMMM  MM 88.  ...   88   88 88.  .88 88    88 
 * M         M `88888P' `8888P88 M  MMMMM  MM `88888P'   dP   dP `88888P' dP    dP 
 * MMMMMMMMMMM               .88 MMMMMMMMMMMM                                      
 *                       d8888P                                                    
 */
/**
 * Print a log message to various locations.
 */
public class LogAction extends ActionWithParameters {
    // Some flags to decide where the log message should show up, based on the configuration file.
    /** Log to chat? */
    private final boolean toChat;

    /** Log to console? */
    private final boolean toConsole;

    /** Log to file? */
    private final boolean toFile;

    /**
     * Instantiates a new log action.
     * 
     * @param name
     *            the name
     * @param delay
     *            the delay
     * @param repeat
     *            the repeat
     * @param toChat
     *            the to chat
     * @param toConsole
     *            the to console
     * @param toFile
     *            the to file
     * @param message
     *            the message
     */
    public LogAction(final String name, final int delay, final int repeat, final boolean toChat,
            final boolean toConsole, final boolean toFile, final String message) {
        super(name, delay, repeat, message);
        this.toChat = toChat;
        this.toConsole = toConsole;
        this.toFile = toFile;
    }

    /* (non-Javadoc)
     * @see fr.neatmonster.nocheatplus.actions.Action#execute(fr.neatmonster.nocheatplus.checks.ViolationData)
     */
    @Override
    public boolean execute(final ViolationData violationData) {
        final ConfigFile configurationFile = ConfigManager.getConfigFile();
        if (configurationFile.getBoolean(ConfPaths.LOGGING_ACTIVE)
                && !violationData.player.hasPermission(violationData.actions.permissionSilent)) {
            final String message = super.getMessage(violationData);
            if (toChat && configurationFile.getBoolean(ConfPaths.LOGGING_INGAMECHAT))
                for (final Player otherPlayer : Bukkit.getServer().getOnlinePlayers())
                    if (otherPlayer.hasPermission(Permissions.ADMINISTRATION_NOTIFY))
                        otherPlayer.sendMessage(ChatColor.RED + "NCP: " + ChatColor.WHITE
                                + CheckUtils.replaceColors(message));
            if (toConsole && configurationFile.getBoolean(ConfPaths.LOGGING_CONSOLE))
                System.out.println("[NoCheatPlus] " + CheckUtils.removeColors(message));
            if (toFile && configurationFile.getBoolean(ConfPaths.LOGGING_FILE))
                CheckUtils.fileLogger.info(CheckUtils.removeColors(message));
        }
        return false;
    }

    /**
     * Create the string that's used to define the action in the logfile.
     * 
     * @return the string
     */
    @Override
    public String toString() {
        return "log:" + name + ":" + delay + ":" + repeat + ":" + (toConsole ? "c" : "") + (toChat ? "i" : "")
                + (toFile ? "f" : "");
    }
}
