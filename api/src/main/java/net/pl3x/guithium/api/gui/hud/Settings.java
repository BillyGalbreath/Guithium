package net.pl3x.guithium.api.gui.hud;

import com.google.common.io.ByteArrayDataInput;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a collection of settings.
 *
 * @param <T> Type of settings
 */
public class Settings<T extends Enum<T>> {
    private final Map<T, Boolean> settings = new HashMap<>();

    /**
     * Create a new settings object with all settings toggled off by default.
     */
    public Settings() {
    }

    /**
     * Create a new settings object with all settings toggled on by default.
     *
     * @param values Values array from the enum
     */
    public Settings(@NotNull T[] values) {
        for (T value : values) {
            this.settings.put(value, true);
        }
    }

    /**
     * Get the value for the specified setting.
     *
     * @param setting Setting to check
     * @return Value of setting
     */
    public boolean get(@NotNull T setting) {
        return Boolean.TRUE.equals(this.settings.get(setting));
    }

    /**
     * Set a value for specified setting.
     *
     * @param setting Setting to set
     * @param value   Value to set
     * @return this settings instance
     */
    @NotNull
    public Settings<T> set(@NotNull T setting, boolean value) {
        this.settings.put(setting, value);
        return this;
    }

    /**
     * Create a new instance of settings from incoming data stream
     *
     * @param in  Incoming data
     * @param arr Array of settings
     * @param <T> Type of settings
     * @return New settings instance
     */
    @NotNull
    public static <T extends Enum<T>> Settings<T> create(@NotNull ByteArrayDataInput in, @NotNull T[] arr) {
        Settings<T> settings = new Settings<>();
        for (T setting : arr) {
            settings.set(setting, in.readBoolean());
        }
        return settings;
    }
}
