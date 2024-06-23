package org.itiszakk.bettertools.actions.impl;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.itiszakk.bettertools.actions.AbstractAction;
import org.itiszakk.bettertools.config.ChoppingActionConfig;
import org.itiszakk.bettertools.search.BlockSearch;
import org.itiszakk.bettertools.search.impl.ChoppingNeighbourValidator;
import org.itiszakk.bettertools.search.impl.ChoppingNeighboursProvider;

import java.util.Set;

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
        ChoppingNeighboursProvider provider = new ChoppingNeighboursProvider(world);
        ChoppingNeighbourValidator validator = new ChoppingNeighbourValidator(world, state.getBlock());

        Set<BlockPos> treeBlocks = BlockSearch.bfs(pos, provider, validator);

        int amount = destroy(treeBlocks);
        player.getMainHandStack().damage(amount, player, EquipmentSlot.MAINHAND);
    }

    private int destroy(Set<BlockPos> treeBlocks) {
        int amount = 0;

        for (BlockPos treeBlockPos : treeBlocks) {
            BlockState treeBlockState = world.getBlockState(treeBlockPos);

            if (treeBlockState.isIn(BlockTags.LOGS)) {
                amount += world.breakBlock(treeBlockPos, true) ? 1 : 0;
            }

            if (treeBlockState.isIn(BlockTags.LEAVES)) {
                world.breakBlock(treeBlockPos, true);
            }
        }

        return amount;
    }

    private boolean useInCreative(PlayerEntity player) {
        return !player.isInCreativeMode() || ChoppingActionConfig.USE_IN_CREATIVE.getValue();
    }

    private boolean useWhileSneaking(PlayerEntity player) {
        return !player.isSneaking() || ChoppingActionConfig.USE_WHILE_SNEAKING.getValue();
    }
}
