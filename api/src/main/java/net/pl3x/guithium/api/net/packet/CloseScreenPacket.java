package net.pl3x.guithium.api.net.packet;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import net.pl3x.guithium.api.gui.Screen;
import net.pl3x.guithium.api.Key;
import net.pl3x.guithium.api.json.Gson;
import net.pl3x.guithium.api.net.PacketListener;
import org.jetbrains.annotations.NotNull;

public class CloseScreenPacket extends Packet {
    public static final Key KEY = Key.of("packet:close_screen");

    private final Screen screen;

    public CloseScreenPacket(Screen screen) {
        this.screen = screen;
    }

    public CloseScreenPacket(@NotNull ByteArrayDataInput in) {
        this.screen = Gson.fromJson(in.readUTF(), Screen.class);
    }

    @Override
    public Key getKey() {
        return KEY;
    }

    public Screen getScreen() {
        return this.screen;
    }

    @Override
    public void handle(@NotNull PacketListener listener) {
        listener.handleCloseScreen(this);
    }

    @Override
    @NotNull
    public ByteArrayDataOutput write() {
        ByteArrayDataOutput out = out(this);
        out.writeUTF(Gson.toJson(getScreen()));
        return out;
    }
}