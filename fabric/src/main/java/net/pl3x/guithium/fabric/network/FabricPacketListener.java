package net.pl3x.guithium.fabric.network;

import net.minecraft.client.Minecraft;
import net.pl3x.guithium.api.Guithium;
import net.pl3x.guithium.api.gui.element.ClickableElement;
import net.pl3x.guithium.api.gui.element.Element;
import net.pl3x.guithium.api.gui.element.ValueElement;
import net.pl3x.guithium.api.network.PacketListener;
import net.pl3x.guithium.api.network.packet.CloseScreenPacket;
import net.pl3x.guithium.api.network.packet.ElementChangedValuePacket;
import net.pl3x.guithium.api.network.packet.ElementClickedPacket;
import net.pl3x.guithium.api.network.packet.ElementPacket;
import net.pl3x.guithium.api.network.packet.HelloPacket;
import net.pl3x.guithium.api.network.packet.OpenScreenPacket;
import net.pl3x.guithium.api.network.packet.TexturesPacket;
import net.pl3x.guithium.fabric.GuithiumMod;
import net.pl3x.guithium.fabric.gui.screen.AbstractScreen;
import net.pl3x.guithium.fabric.gui.screen.RenderableScreen;
import org.jetbrains.annotations.NotNull;

public class FabricPacketListener implements PacketListener {
    private final GuithiumMod mod;

    public FabricPacketListener(@NotNull GuithiumMod mod) {
        this.mod = mod;
    }

    @Override
    public <T extends ClickableElement<T>> void handleElementClick(@NotNull ElementClickedPacket<T> packet) {
        // server does not send this packet to the client
        throw new UnsupportedOperationException("Not supported on client.");
    }

    @Override
    public <T extends ValueElement<T, V>, V> void handleElementChangedValue(@NotNull ElementChangedValuePacket<V> packet) {
        // server does not send this packet to the client
        throw new UnsupportedOperationException("Not supported on client.");
    }

    @Override
    public void handleElement(@NotNull ElementPacket packet) {
        // element to update
        Element element = packet.getElement();

        // check if element is on current screen
        if (Minecraft.getInstance().screen instanceof AbstractScreen screen) {
            if (screen.updateElement(element)) {
                return;
            }
        }

        // check hud screens for the element
        for (AbstractScreen screen : this.mod.getHudManager().getAllScreens().values()) {
            if (screen.updateElement(element)) {
                return;
            }
        }
    }

    @Override
    public void handleOpenScreen(@NotNull OpenScreenPacket packet) {
        AbstractScreen renderableScreen = new RenderableScreen(this.mod, packet.getScreen());
        if (renderableScreen.getScreen().isHud()) {
            this.mod.getHudManager().add(renderableScreen);
        } else {
            renderableScreen.open();
        }
    }

    @Override
    public void handleCloseScreen(@NotNull CloseScreenPacket packet) {
        // try to remove from HUD
        if (this.mod.getHudManager().remove(packet.getScreenKey()) != null) {
            return; // removed from hud
        }
        // not on HUD, so lets get current open screen
        if (!(Minecraft.getInstance().screen instanceof AbstractScreen screen)) {
            return; // nothing to remove
        }
        // check if current screen keys match
        if (!screen.getKey().equals(packet.getScreenKey())) {
            return;
        }
        // close the screen
        screen.close();
    }

    @Override
    public void handleTextures(@NotNull TexturesPacket packet) {
        // add texture to manager
        this.mod.getTextureManager().add(packet.getTextures());
    }

    @Override
    public void handleHello(@NotNull HelloPacket packet) {
        int protocol = packet.getProtocol();

        if (protocol != Guithium.PROTOCOL) {
            Guithium.logger.info("Server responded with a different protocol ({})", protocol);
            /*Minecraft.getInstance().getToasts().addToast(
                new SystemToast(
                    SystemToast.SystemToastIds.TUTORIAL_HINT,
                    Component.literal("Guithium Error"),
                    Component.literal("Protocol mismatch. Disabling mod.")
                )
            );*/
            return;
        }

        Guithium.logger.info("Server responded with correct protocol ({})", protocol);
    }
}
