package org.itiszakk.bettertools.actions.chopping;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.itiszakk.bettertools.actions.AbstractAction;

public class ChoppingAction extends AbstractAction {

    public ChoppingAction(World world, PlayerEntity player, BlockPos pos, BlockState state) {
        super(world, player, pos, state);
    }

    @Override
    protected boolean skip() {
        return !useInCreative(player) || !useWhileSneaking(player);
    }

    @Override
    protected void action() {
        Tree tree = new Tree(world, pos, state.getBlock());
        int amount = tree.destroy();

        player.getMainHandStack().damage(amount, player, EquipmentSlot.MAINHAND);
    }

    private boolean useInCreative(PlayerEntity player) {
        return !player.isInCreativeMode() || ChoppingActionConfig.USE_IN_CREATIVE.getValue();
    }

    private boolean useWhileSneaking(PlayerEntity player) {
        return !player.isSneaking() || ChoppingActionConfig.USE_WHILE_SNEAKING.getValue();
    }
}
