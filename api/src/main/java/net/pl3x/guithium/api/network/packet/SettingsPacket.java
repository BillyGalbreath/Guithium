package net.pl3x.guithium.api.network.packet;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import net.pl3x.guithium.api.gui.hud.Render;
import net.pl3x.guithium.api.gui.hud.Settings;
import net.pl3x.guithium.api.key.Key;
import net.pl3x.guithium.api.network.PacketListener;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a packet that contains HUD settings.
 */
public class SettingsPacket extends Packet {
    /**
     * Unique identifying key
     */
    public static final Key KEY = Key.of("packet:hud_settings");

    private final boolean isGlobal;
    private final Settings<Render> settings;

    /**
     * Create a new settings packet.
     *
     * @param settings The settings
     */
    public SettingsPacket(@NotNull Settings<Render> settings) {
        this(false, settings);
    }

    /**
     * Create a new settings packet.
     *
     * @param isGlobal Whether these settings are global
     * @param settings The settings
     */
    public SettingsPacket(boolean isGlobal, @NotNull Settings<Render> settings) {
        super(KEY);
        this.isGlobal = isGlobal;
        this.settings = settings;
    }

    /**
     * Create a new update element packet.
     *
     * @param in Input byte array
     */
    public SettingsPacket(@NotNull ByteArrayDataInput in) {
        this(in.readBoolean(), Settings.create(in, Render.values()));
    }

    /**
     * Whether the contained settings are global or not.
     *
     * @return {@code true} if global, otherwise {@code false}
     */
    public boolean isGlobal() {
        return this.isGlobal;
    }

    /**
     * Get the settings contained in this packet.
     *
     * @return The settings
     */
    @NotNull
    public Settings<Render> getSettings() {
        return settings;
    }

    @Override
    public <T extends PacketListener> void handle(@NotNull T listener) {
        listener.handleSettings(this);
    }

    @Override
    @NotNull
    public ByteArrayDataOutput write() {
        ByteArrayDataOutput out = out(this);
        out.writeBoolean(isGlobal());
        for (Render render : Render.values()) {
            out.writeBoolean(getSettings().get(render));
        }
        return out;
    }
}
