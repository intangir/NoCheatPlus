package fr.neatmonster.nocheatplus.checks;

import org.bukkit.entity.Player;

import fr.neatmonster.nocheatplus.checks.blockbreak.BlockBreakConfig;
import fr.neatmonster.nocheatplus.checks.blockbreak.BlockBreakData;
import fr.neatmonster.nocheatplus.checks.blockinteract.BlockInteractConfig;
import fr.neatmonster.nocheatplus.checks.blockinteract.BlockInteractData;
import fr.neatmonster.nocheatplus.checks.blockplace.BlockPlaceConfig;
import fr.neatmonster.nocheatplus.checks.blockplace.BlockPlaceData;
import fr.neatmonster.nocheatplus.checks.chat.ChatConfig;
import fr.neatmonster.nocheatplus.checks.chat.ChatData;
import fr.neatmonster.nocheatplus.checks.fight.FightConfig;
import fr.neatmonster.nocheatplus.checks.fight.FightData;
import fr.neatmonster.nocheatplus.checks.inventory.InventoryConfig;
import fr.neatmonster.nocheatplus.checks.inventory.InventoryData;
import fr.neatmonster.nocheatplus.checks.moving.MovingConfig;
import fr.neatmonster.nocheatplus.checks.moving.MovingData;
import fr.neatmonster.nocheatplus.players.Permissions;

/*
 * MM'""""'YMM dP                         dP       M""""""""M                            
 * M' .mmm. `M 88                         88       Mmmm  mmmM                            
 * M  MMMMMooM 88d888b. .d8888b. .d8888b. 88  .dP  MMMM  MMMM dP    dP 88d888b. .d8888b. 
 * M  MMMMMMMM 88'  `88 88ooood8 88'  `"" 88888"   MMMM  MMMM 88    88 88'  `88 88ooood8 
 * M. `MMM' .M 88    88 88.  ... 88.  ... 88  `8b. MMMM  MMMM 88.  .88 88.  .88 88.  ... 
 * MM.     .dM dP    dP `88888P' `88888P' dP   `YP MMMM  MMMM `8888P88 88Y888P' `88888P' 
 * MMMMMMMMMMM                                     MMMMMMMMMM      .88 88                
 *                                                             d8888P  dP                
 */
/**
 * Type of checks (containing configuration and dataFactory classes, name and permission).
 */
public enum CheckType {
    ALL,

    BLOCKBREAK(BlockBreakConfig.factory, BlockBreakData.factory),
    BLOCKBREAK_DIRECTION(BLOCKBREAK, Permissions.BLOCKBREAK_DIRECTION),
    BLOCKBREAK_FASTBREAK(BLOCKBREAK, Permissions.BLOCKBREAK_FASTBREAK),
    BLOCKBREAK_NOSWING(BLOCKBREAK, Permissions.BLOCKBREAK_NOSWING),
    BLOCKBREAK_REACH(BLOCKBREAK, Permissions.BLOCKBREAK_REACH),

    BLOCKINTERACT(BlockInteractConfig.factory, BlockInteractData.factory),
    BLOCKINTERACT_DIRECTION(BLOCKINTERACT, Permissions.BLOCKINTERACT_DIRECTION),
    BLOCKINTERACT_REACH(BLOCKINTERACT, Permissions.BLOCKINTERACT_REACH),

    BLOCKPLACE(BlockPlaceConfig.factory, BlockPlaceData.factory),
    BLOCKPLACE_DIRECTION(BLOCKPLACE, Permissions.BLOCKPLACE_DIRECTION),
    BLOCKPLACE_FASTPLACE(BLOCKPLACE, Permissions.BLOCKPLACE_FASTPLACE),
    BLOCKPLACE_NOSWING(BLOCKPLACE, Permissions.BLOCKPLACE_NOSWING),
    BLOCKPLACE_REACH(BLOCKPLACE, Permissions.BLOCKBREAK_REACH),
    BLOCKPLACE_SPEED(BLOCKPLACE, Permissions.BLOCKPLACE_SPEED),

    CHAT(ChatConfig.factory, ChatData.factory),
    CHAT_COLOR(CHAT, Permissions.CHAT_COLOR),
    CHAT_NOPWNAGE(CHAT, Permissions.CHAT_NOPWNAGE),

    FIGHT(FightConfig.factory, FightData.factory),
    FIGHT_ANGLE(FIGHT, Permissions.FIGHT_ANGLE),
    FIGHT_CRITICAL(FIGHT, Permissions.FIGHT_CRITICAL),
    FIGHT_DIRECTION(FIGHT, Permissions.FIGHT_DIRECTION),
    FIGHT_GODMODE(FIGHT, Permissions.FIGHT_GODMODE),
    FIGHT_INSTANTHEAL(FIGHT, Permissions.FIGHT_INSTANTHEAL),
    FIGHT_KNOCKBACK(FIGHT, Permissions.FIGHT_KNOCKBACK),
    FIGHT_NOSWING(FIGHT, Permissions.FIGHT_NOSWING),
    FIGHT_REACH(FIGHT, Permissions.FIGHT_REACH),
    FIGHT_SPEED(FIGHT, Permissions.FIGHT_SPEED),

    INVENTORY(InventoryConfig.factory, InventoryData.factory),
    INVENTORY_DROP(INVENTORY, Permissions.INVENTORY_DROP),
    INVENTORY_INSTANTBOW(INVENTORY, Permissions.INVENTORY_INSTANTBOW),
    INVENTORY_INSTANTEAT(INVENTORY, Permissions.INVENTORY_INSTANTEAT),

    MOVING(MovingConfig.factory, MovingData.factory),
    MOVING_CREATIVEFLY(MOVING, Permissions.MOVING_CREATIVEFLY),
    MOVING_MOREPACKETS(MOVING, Permissions.MOVING_MOREPACKETS),
    MOVING_MOREPACKETSVEHICLE(MOVING, Permissions.MOVING_MOREPACKETSVEHICLE),
    MOVING_NOFALL(MOVING, Permissions.MOVING_NOFALL),
    MOVING_SURVIVALFLY(MOVING, Permissions.MOVING_SURVIVALFLY),

    UNKNOWN;

    /** The group. */
    private CheckType          parent        = null;

    /** The configFactory. */
    private CheckConfigFactory configFactory = null;

    /** The dataFactory. */
    private CheckDataFactory   dataFactory   = null;

    /** The permission. */
    private String             permission    = null;

    /**
     * Instantiates a new check type.
     */
    private CheckType() {}

    /**
     * Instantiates a new check type.
     * 
     * @param configFactory
     *            the configFactory
     * @param dataFactory
     *            the dataFactory
     */
    private CheckType(final CheckConfigFactory configFactory, final CheckDataFactory dataFactory) {
        this.configFactory = configFactory;
        this.dataFactory = dataFactory;
    }

    /**
     * Instantiates a new check type.
     * 
     * @param parent
     *            the parent
     * @param permission
     *            the permission
     */
    private CheckType(final CheckType parent, final String permission) {
        this.parent = parent;
        configFactory = parent.getConfigFactory();
        dataFactory = parent.getDataFactory();
        this.permission = permission;
    }

    /**
     * Gets the configFactory.
     * 
     * @return the configFactory
     */
    public CheckConfigFactory getConfigFactory() {
        return configFactory;
    }

    /**
     * Gets the dataFactory.
     * 
     * @return the dataFactory
     */
    public CheckDataFactory getDataFactory() {
        return dataFactory;
    }

    /**
     * Gets the name.
     * 
     * @return the name
     */
    public String getName() {
        return toString().toLowerCase().replace("_", ".");
    }

    /**
     * Gets the parent.
     * 
     * @return the parent
     */
    public CheckType getParent() {
        return parent;
    }

    /**
     * Gets the permission.
     * 
     * @return the permission
     */
    public String getPermission() {
        return permission;
    }

    /**
     * Check if the check is enabled by configuration (no permission check).
     * 
     * @param player
     *            the player
     * @return true, if the check is enabled
     */
    public final boolean isEnabled(final Player player) {
        return configFactory.getConfig(player).isEnabled(this);
    }
}