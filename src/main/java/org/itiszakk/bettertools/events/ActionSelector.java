package org.itiszakk.bettertools.events;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import org.itiszakk.bettertools.actions.Action;
import org.itiszakk.bettertools.actions.ActionFactory;

public class ActionSelector {

    public static void register() {
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            Action action = ActionFactory.getAction(world, player, pos, state);
            action.execute();
        });
    }
}