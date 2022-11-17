package net.pl3x.servergui.api.net;

import net.pl3x.servergui.api.net.packet.Packet;
import org.jetbrains.annotations.NotNull;

public interface Connection {
    /**
     * Get the packet listener.
     *
     * @return Packet listener
     */
    PacketListener getPacketListener();

    /**
     * Send packet.
     *
     * @param packet Packet to send
     */
    void send(@NotNull Packet packet);
}