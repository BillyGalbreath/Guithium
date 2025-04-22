package net.pl3x.guithium.api.json.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A custom JSON (de)serializer for the GSON library specifically for adventure components.
 */
public class ComponentAdapter implements JsonSerializer<Component>, JsonDeserializer<Component> {
    /**
     * Create a new component adapter for gson.
     */
    public ComponentAdapter() {
        // Empty constructor to pacify javadoc lint
    }

    @Override
    @NotNull
    public JsonElement serialize(@NotNull Component component, @NotNull Type type, @NotNull JsonSerializationContext context) {
        return new JsonPrimitive(GsonComponentSerializer.gson().serialize(component));
    }

    @Override
    @Nullable
    public Component deserialize(@NotNull JsonElement json, @NotNull Type type, @NotNull JsonDeserializationContext context) throws JsonParseException {
        return GsonComponentSerializer.gson().deserialize(json.getAsString());
    }
}
