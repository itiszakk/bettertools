package org.itiszakk.woodcutter.util;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;


public class ContinuousBlocks implements Iterable<BlockPos> {

    private final World world;
    private final BlockPos startPosition;
    private final Direction direction;
    private final Predicate<BlockState> predicate;

    public ContinuousBlocks(World world, BlockPos startPosition, Direction direction, Predicate<BlockState> predicate) {
        this.world = world;
        this.startPosition = startPosition;
        this.direction = direction;
        this.predicate = predicate;
    }

    @Override
    @NotNull
    public Iterator<BlockPos> iterator() {
        return new ContinuousBlocksIterator();
    }

    private class ContinuousBlocksIterator implements Iterator<BlockPos> {

        private BlockPos currentPosition;

        public ContinuousBlocksIterator() {
            this.currentPosition = startPosition;
        }

        @Override
        public BlockPos next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            BlockPos nextPosition = advance();
            currentPosition = nextPosition;

            return nextPosition;
        }

        @Override
        public boolean hasNext() {
            return predicate.test(world.getBlockState(advance()));
        }

        private BlockPos advance() {
            return currentPosition.offset(direction);
        }
    }
}
