package net.pl3x.guithium.fabric.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PlayerRideableJumping;
import net.pl3x.guithium.api.Guithium;
import net.pl3x.guithium.api.gui.hud.Render;
import net.pl3x.guithium.fabric.GuithiumMod;
import net.pl3x.guithium.fabric.gui.FabricHudManager;
import net.pl3x.guithium.fabric.gui.Gfx;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Gui.class)
public class GuiMixin {
    @Unique
    private static FabricHudManager getHudManager() {
        return ((GuithiumMod) Guithium.api()).getHudManager();
    }

    @WrapMethod(method = "render")
    private void preRender(@NotNull GuiGraphics gfx, @NotNull DeltaTracker deltaTracker, @NotNull Operation<Void> original) {
        FabricHudManager hud = getHudManager();
        float delta = deltaTracker.getGameTimeDeltaTicks();

        Gfx.wrap(gfx, () -> hud.preRender(gfx, delta));
        original.call(gfx, deltaTracker);
        Gfx.wrap(gfx, () -> hud.postRender(gfx, delta));
    }

    @WrapMethod(method = "renderCameraOverlays")
    private void renderCameraOverlays(GuiGraphics gfx, DeltaTracker deltaTracker, Operation<Void> original) {
        if (getHudManager().check(Render.CAMERA_OVERLAYS)) {
            original.call(gfx, deltaTracker);
        }
    }

    @WrapMethod(method = "renderChat") // only hides hud, can still open chat screen
    private void renderChat(GuiGraphics gfx, DeltaTracker deltaTracker, Operation<Void> original) {
        if (getHudManager().check(Render.CHAT)) {
            original.call(gfx, deltaTracker);
        }
    }

    @WrapMethod(method = "renderCrosshair")
    private void renderCrosshair(GuiGraphics gfx, DeltaTracker deltaTracker, Operation<Void> original) {
        if (getHudManager().check(Render.CROSSHAIR)) {
            original.call(gfx, deltaTracker);
        }
    }

    @WrapMethod(method = "renderEffects")
    private void renderEffects(GuiGraphics gfx, DeltaTracker deltaTracker, Operation<Void> original) {
        if (getHudManager().check(Render.EFFECTS)) {
            original.call(gfx, deltaTracker);
        }
    }

    @WrapMethod(method = "renderExperienceBar")
    private void renderExperienceBar(GuiGraphics gfx, int i, Operation<Void> original) {
        if (getHudManager().check(Render.EXPERIENCE_BAR)) {
            original.call(gfx, i);
        }
    }

    @WrapMethod(method = "renderExperienceLevel")
    private void renderExperienceLevel(GuiGraphics gfx, DeltaTracker deltaTracker, Operation<Void> original) {
        if (getHudManager().check(Render.EXPERIENCE_LEVEL)) {
            original.call(gfx, deltaTracker);
        }
    }

    @WrapMethod(method = "renderHotbarAndDecorations")
    private void renderHotbarAndDecorations(GuiGraphics gfx, DeltaTracker deltaTracker, Operation<Void> original) {
        if (getHudManager().check(Render.HOTBAR_AND_DECORATIONS)) {
            original.call(gfx, deltaTracker);
        }
    }

    @WrapMethod(method = "renderItemHotbar")
    private void renderItemHotbar(GuiGraphics gfx, DeltaTracker deltaTracker, Operation<Void> original) {
        if (getHudManager().check(Render.ITEM_HOTBAR)) {
            original.call(gfx, deltaTracker);
        }
    }

    @WrapMethod(method = "renderJumpMeter")
    private void renderJumpMeter(PlayerRideableJumping playerRideableJumping, GuiGraphics gfx, int i, Operation<Void> original) {
        if (getHudManager().check(Render.JUMP_METER)) {
            original.call(playerRideableJumping, gfx, i);
        }
    }

    @WrapMethod(method = "renderOverlayMessage")
    private void renderOverlayMessage(GuiGraphics gfx, DeltaTracker deltaTracker, Operation<Void> original) {
        if (getHudManager().check(Render.OVERLAY_MESSAGE)) {
            original.call(gfx, deltaTracker);
        }
    }

    @WrapMethod(method = "renderPlayerHealth")
    private void renderPlayerHealth(GuiGraphics gfx, Operation<Void> original) {
        if (getHudManager().check(Render.PLAYER_HEALTH)) {
            original.call(gfx);
        }
    }

    @WrapMethod(method = "renderScoreboardSidebar")
    private void renderScoreboardSidebar(GuiGraphics gfx, DeltaTracker deltaTracker, Operation<Void> original) {
        if (getHudManager().check(Render.SCOREBOARD_SIDEBAR)) {
            original.call(gfx, deltaTracker);
        }
    }

    @WrapMethod(method = "renderSelectedItemName")
    private void renderSelectedItemName(GuiGraphics gfx, Operation<Void> original) {
        if (getHudManager().check(Render.SELECTED_ITEM_NAME)) {
            original.call(gfx);
        }
    }

    @WrapMethod(method = "renderSleepOverlay")
    private void renderSleepOverlay(GuiGraphics gfx, DeltaTracker deltaTracker, Operation<Void> original) {
        if (getHudManager().check(Render.SLEEP_OVERLAY)) {
            original.call(gfx, deltaTracker);
        }
    }

    @WrapMethod(method = "renderTabList")
    private void renderTabList(GuiGraphics gfx, DeltaTracker deltaTracker, Operation<Void> original) {
        if (getHudManager().check(Render.TAB_LIST)) {
            original.call(gfx, deltaTracker);
        }
    }

    @WrapMethod(method = "renderTitle")
    private void renderTitle(GuiGraphics gfx, DeltaTracker deltaTracker, Operation<Void> original) {
        if (getHudManager().check(Render.TITLE)) {
            original.call(gfx, deltaTracker);
        }
    }

    @WrapMethod(method = "renderVehicleHealth")
    private void renderVehicleHealth(GuiGraphics gfx, Operation<Void> original) {
        if (getHudManager().check(Render.VEHICLE_HEALTH)) {
            original.call(gfx);
        }
    }

    @WrapMethod(method = "getVehicleMaxHearts")
    private int getVehicleMaxHearts(LivingEntity livingEntity, Operation<Integer> original) {
        if (getHudManager().check(Render.VEHICLE_HEALTH)) {
            return original.call(livingEntity);
        } else {
            // returning 0 here will show the player's
            // hunger instead of vehicle health
            return 0;
        }
    }

    //@WrapMethod(method = "spectatorGui.renderHotbar")
    //private void spectatorGui.renderHotbar (){}

    //@WrapMethod(method = "spectatorGui.renderTooltip")
    //private void spectatorGui.renderTooltip (){}

    //@WrapMethod(method = "subtitleOverlay")
    //private void subtitleOverlay (){}

    //@WrapMethod(method = "bossOverlay")
    //private void bossOverlay (){}
}
