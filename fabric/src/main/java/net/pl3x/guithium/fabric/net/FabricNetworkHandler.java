package net.pl3x.guithium.fabric.net;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.resources.ResourceLocation;
import net.pl3x.guithium.api.network.Connection;
import net.pl3x.guithium.api.network.NetworkHandler;
import net.pl3x.guithium.api.network.packet.HelloPacket;
import net.pl3x.guithium.api.network.packet.Packet;
import net.pl3x.guithium.fabric.Guithium;
import org.jetbrains.annotations.NotNull;

public class FabricNetworkHandler extends NetworkHandler {
    public static final ResourceLocation RESOURCE_LOCATION = new ResourceLocation(CHANNEL.toString());

    private final Guithium mod;
    private final Connection connection;

    public FabricNetworkHandler(@NotNull Guithium mod) {
        this.mod = mod;
        this.connection = new FabricConnection();
    }

    @NotNull
    public Connection getConnection() {
        return this.connection;
    }

    @Override
    public void register() {
        ClientPlayNetworking.registerGlobalReceiver(RESOURCE_LOCATION,
            (client, handler, buf, sender) -> {
                // receive data from server
                receive(
                    this.connection.getPacketListener(),
                    Packet.in(buf.accessByteBufWithCorrectSize())
                );
            }
        );

        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            // ensure we are not connecting to a single player game
            if (client.isLocalServer()) {
                return;
            }

            // send hello on first client tick to ensure everything is ready to receive a reply
            this.mod.getScheduler().addTask(0, () -> this.connection.send(new HelloPacket()));
        });

        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
            this.mod.getScheduler().cancelAll();
            this.mod.getTextureManager().clear();
            this.mod.getHudManager().clear();
        });
    }
}
