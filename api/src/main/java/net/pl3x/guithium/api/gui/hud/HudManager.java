package net.pl3x.guithium.api.gui.hud;

import org.jetbrains.annotations.NotNull;

/**
 * Manages HUD elements on the client's screen.
 */
public class HudManager {
    private final Settings<Render> globalSettings = new Settings<>(Render.values());

    /**
     * Create a new hud manager.
     */
    public HudManager() {
        // Empty constructor to pacify javadoc lint
    }

    /**
     * Get the map of global settings for rendering vanilla hud elements.
     *
     * @return Global settings
     */
    @NotNull
    public Settings<Render> getGlobalSettings() {
        return this.globalSettings;
    }
}
