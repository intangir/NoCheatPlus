package fr.neatmonster.nocheatplus.checks.fight;

import org.bukkit.entity.Player;

import fr.neatmonster.nocheatplus.checks.Check;
import fr.neatmonster.nocheatplus.checks.CheckType;

/*
 * M"""""""`YM          MP""""""`MM            oo                   
 * M  mmmm.  M          M  mmmmm..M                                 
 * M  MMMMM  M .d8888b. M.      `YM dP  dP  dP dP 88d888b. .d8888b. 
 * M  MMMMM  M 88'  `88 MMMMMMM.  M 88  88  88 88 88'  `88 88'  `88 
 * M  MMMMM  M 88.  .88 M. .MMM'  M 88.88b.88' 88 88    88 88.  .88 
 *  * M  MMMMM  M `88888P' Mb.     .dM 8888P Y8P  dP dP    dP `8888P88 
 * MMMMMMMMMMM          MMMMMMMMMMM                             .88 
 *                                                          d8888P  
 */
/**
 * We require that the player moves his arm between attacks, this is basically what gets checked here.
 */
public class NoSwing extends Check {

    /**
     * Instantiates a new no swing check.
     */
    public NoSwing() {
        super(CheckType.FIGHT_NOSWING);
    }

    /**
     * Checks a player.
     * 
     * @param player
     *            the player
     * @return true, if successful
     */
    public boolean check(final Player player) {
        final FightData data = FightData.getData(player);

        boolean cancel = false;

        // Did he swing his arm before?
        if (data.noSwingArmSwung) {
            // Yes, reward him with reduction of his violation level.
            data.noSwingArmSwung = false;
            data.noSwingVL *= 0.9D;
        } else {
            // No, increase his violation level.
            data.noSwingVL += 1D;

            // Execute whatever actions are associated with this check and the violation level and find out if we should
            // cancel the event.
            cancel = executeActions(player, data.noSwingVL, 1D, FightConfig.getConfig(player).noSwingActions);
        }

        return cancel;
    }
}
