package org.itiszakk.bettertools.search.impl;

import net.minecraft.block.BlockState;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import org.itiszakk.bettertools.configuration.ChoppingConfiguration;
import org.itiszakk.bettertools.configuration.ChoppingConfiguration.CutMode;
import org.itiszakk.bettertools.search.NeighboursProvider;
import org.itiszakk.bettertools.search.utils.NeighbourOffsets;
import org.itiszakk.bettertools.search.utils.Range;

import java.util.ArrayList;
import java.util.List;

public class ChoppingNeighboursProvider implements NeighboursProvider {

    private static final NeighbourOffsets COMPLETE_OFFSETS = NeighbourOffsets.of(
            new Range(-1, 1),
            new Range(-1, 1),
            new Range(-1, 1)
    );

    private static final NeighbourOffsets REALISTIC_OFFSETS = NeighbourOffsets.of(
            new Range(-1, 1),
            new Range(0, 1),
            new Range(-1, 1)
    );

    private final World world;

    public ChoppingNeighboursProvider(World world) {
        this.world = world;
    }

    @Override
    public List<BlockPos> neighbours(BlockPos pos) {
        return switch (ChoppingConfiguration.MODE.getValue()) {
            case CutMode.COMPLETE -> neighbours(pos, COMPLETE_OFFSETS);
            case CutMode.REALISTIC -> neighbours(pos, REALISTIC_OFFSETS);
        };
    }

    private List<BlockPos> neighbours(BlockPos current, NeighbourOffsets offsets) {
        List<BlockPos> neighbours = new ArrayList<>();

        for (int dx : offsets.getX()) {
            for (int dy : offsets.getY()) {
                for (int dz : offsets.getZ()) {

                    if (dx == 0 && dy == 0 && dz == 0) {
                        continue;
                    }

                    BlockPos next = current.add(new Vec3i(dx, dy, dz));
                    BlockState state = world.getBlockState(next);

                    if (state.isIn(BlockTags.LOGS) || state.isIn(BlockTags.LEAVES)) {
                        neighbours.add(next);
                    }
                }
            }
        }

        return neighbours;
    }
}
