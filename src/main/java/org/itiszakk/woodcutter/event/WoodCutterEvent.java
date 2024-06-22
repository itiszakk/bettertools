package org.itiszakk.woodcutter.event;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents.After;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.itiszakk.woodcutter.config.WoodCutterConfiguration;
import org.itiszakk.woodcutter.util.Tree;
import org.jetbrains.annotations.Nullable;

public class WoodCutterEvent implements After {

    @Override
    public void afterBlockBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity) {
        if (!validate(player, state)) {
            return;
        }

        Tree tree = new Tree(world, pos, state.getBlock());
        int amount = tree.destroy();

        player.getMainHandStack().damage(amount, player, EquipmentSlot.MAINHAND);
    }

    private boolean validate(PlayerEntity player, BlockState state) {
        return WoodCutterConfiguration.ENABLE.getValue()
                && state.isIn(BlockTags.LOGS)
                && player.getMainHandStack().isIn(ItemTags.AXES)
                && checkCreativeMod(player)
                && checkSneaking(player);
    }

    private boolean checkCreativeMod(PlayerEntity player) {
        return !player.isInCreativeMode() || WoodCutterConfiguration.USE_IN_CREATIVE.getValue();
    }

    private boolean checkSneaking(PlayerEntity player) {
        return !player.isSneaking() || WoodCutterConfiguration.USE_WHILE_SNEAKING.getValue();
    }
}