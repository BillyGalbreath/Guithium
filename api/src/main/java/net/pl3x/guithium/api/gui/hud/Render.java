package net.pl3x.guithium.api.gui.hud;

/**
 * The type of vanilla hud renders.
 */
public enum Render {
    // hides mask from:
    // carved pumpkin on head
    // vignette in fancy graphics mode
    // spyglass scope
    // frozen outline
    // portal effect
    // confusion/nausea effect
    CAMERA_OVERLAYS,

    // hides chat hud (not the chat screen)
    CHAT,

    // hides first person crosshair (includes spectator and debug screen)
    CROSSHAIR,

    // hides active effects icons from hud (not from inventory screen)
    EFFECTS,

    // hides experience bar above hotbar (not the level)
    EXPERIENCE_BAR,

    // hides experience level above hotbar (not the bar)
    EXPERIENCE_LEVEL,

    // hides a bunch of hotbar elements:
    //   player health bar
    //   vehicle health bar
    //   hunger bar
    //   jump meter
    //   experience bar
    //   item hotbar (including spectator hotbar)
    //   selected item name
    // does NOT hide:
    //   experience level
    //   action bar text
    HOTBAR_AND_DECORATIONS,

    // hides the item hotbar (not spectator)
    ITEM_HOTBAR,

    // hides jump meter
    JUMP_METER,

    // hides actionbar text (not item name text)
    OVERLAY_MESSAGE,

    // hides player health and hunger bar
    // (not vehicle health)
    PLAYER_HEALTH,

    // hides scoreboard on sidebar
    SCOREBOARD_SIDEBAR,

    // hides selected item name text (not action bar text)
    SELECTED_ITEM_NAME,

    // hides the darkness fade out when sleeping
    SLEEP_OVERLAY,

    // hides the player tab list
    TAB_LIST,

    // hides title and subtitle texts
    TITLE,

    // hides vehicle health bar
    // (if false, will show player hunger if possible)
    VEHICLE_HEALTH,

    // not implemented yet
    SPECTATOR_HOTBAR,  // spectatorGuiRenderHotbar,

    // not implemented yet
    SPECTATOR_TOOLTIP, // spectatorGuiRenderTooltip,

    // not implemented yet
    SUBTITLE_OVERLAY,  // subtitleOverlay,

    // not implemented yet
    BOSS_OVERLAY,      // bossOverlay,
    ;
}
