package org.itiszakk.bettertools.actions.impl;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.itiszakk.bettertools.actions.AbstractAction;
import org.itiszakk.bettertools.configuration.ChoppingConfiguration;
import org.itiszakk.bettertools.search.BlockSearch;
import org.itiszakk.bettertools.search.impl.ChoppingNeighbourValidator;
import org.itiszakk.bettertools.search.impl.ChoppingNeighboursProvider;

import java.util.Set;

public class ChoppingAction extends AbstractAction {

    private final ChoppingNeighboursProvider provider;
    private final ChoppingNeighbourValidator validator;

    public ChoppingAction(World world, PlayerEntity player, BlockPos pos, BlockState state) {
        super(world, player, pos, state);

        provider = new ChoppingNeighboursProvider(world);
        validator = new ChoppingNeighbourValidator(world, state.getBlock());
    }

    @Override
    protected boolean check() {
        return ChoppingConfiguration.ENABLE.getValue() && checkCreative(player) && checkSneaking(player);
    }

    @Override
    protected void action() {
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

    private boolean checkCreative(PlayerEntity player) {
        return !player.isInCreativeMode() || ChoppingConfiguration.USE_IN_CREATIVE.getValue();
    }

    private boolean checkSneaking(PlayerEntity player) {
        return !player.isSneaking() || ChoppingConfiguration.USE_WHILE_SNEAKING.getValue();
    }
}
