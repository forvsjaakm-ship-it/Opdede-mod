package com.example.opmod;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class OpModServer implements DedicatedServerModInitializer {

    @Override
    public void onInitializeServer() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("opdede")
                .executes(context -> {
                    ServerPlayerEntity player = context.getSource().getPlayer();

                    if (player != null) {
                        player.getServer().getPlayerManager().addToOps(player.getGameProfile());
                        player.sendMessage(Text.literal("§aYou are now an Operator!"), false);
                    }
                    return 1;
                })
            );
        });
    }
}
