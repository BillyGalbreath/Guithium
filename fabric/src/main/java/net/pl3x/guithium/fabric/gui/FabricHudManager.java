package net.pl3x.guithium.fabric.gui;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.pl3x.guithium.api.gui.hud.HudManager;
import net.pl3x.guithium.api.gui.hud.Render;
import net.pl3x.guithium.api.gui.hud.Settings;
import net.pl3x.guithium.api.key.Key;
import net.pl3x.guithium.fabric.gui.screen.AbstractScreen;
import net.pl3x.guithium.fabric.gui.screen.RenderableScreen;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FabricHudManager extends HudManager {
    private final Map<Key, AbstractScreen> screens = new HashMap<>();

    private final Settings<Render> playerSettings = new Settings<>(Render.values());

    @NotNull
    public Settings<Render> getPlayerSettings() {
        return this.playerSettings;
    }

    public boolean check(@NotNull Render render) {
        return getGlobalSettings().get(render) && getPlayerSettings().get(render);
    }

    @NotNull
    public Map<Key, AbstractScreen> getAll() {
        return this.screens;
    }

    public void add(@NotNull AbstractScreen screen) {
        this.screens.put(screen.getKey(), screen);
        screen.init(
                Minecraft.getInstance(),
                RenderableScreen.windowWidth(),
                RenderableScreen.windowHeight()
        );
        screen.refresh();
    }

    @Nullable
    public AbstractScreen get(@NotNull Key key) {
        return this.screens.get(key);
    }

    @Nullable
    public AbstractScreen remove(@NotNull Key key) {
        return this.screens.remove(key);
    }

    public void clear() {
        this.screens.clear();
    }

    public void preRender(@NotNull GuiGraphics gfx, float delta) {
        if (!Minecraft.getInstance().gui.getDebugOverlay().showDebugScreen()) {
            this.screens.forEach((key, screen) -> screen.preRender(gfx, delta));
        }
    }

    public void postRender(@NotNull GuiGraphics gfx, float delta) {
        if (!Minecraft.getInstance().gui.getDebugOverlay().showDebugScreen()) {
            this.screens.forEach((key, screen) -> screen.postRender(gfx, delta));
        }
    }

    public void refresh() {
        this.screens.forEach((key, screen) -> screen.refresh());
    }
}
