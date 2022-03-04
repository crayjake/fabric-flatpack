package net.jake.flatpack;

// serialization
import com.mojang.serialization.Codec;

// blocks
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;

// util
import net.minecraft.util.math.BlockPos;

// world
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.chunk.Chunk;

// world gen
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;


public class SuperflatFeature extends Feature<SuperflatFeatureConfig> {
    public SuperflatFeature(Codec<SuperflatFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<SuperflatFeatureConfig> context) {
        final int blockStateFlag = 2;

        // get config and world
        SuperflatFeatureConfig config = context.getConfig();
        StructureWorldAccess world = context.getWorld();

        // find current chunk
        BlockPos pos = context.getOrigin();
        Chunk chunk = context.getWorld().getChunk(pos);

        chunk.setLightOn(false);

        int x = chunk.getPos().getStartX();
        int z = chunk.getPos().getStartZ();

        for (int c_x = 0; c_x <= 15; c_x++) {
            for (int c_z = 0; c_z <= 15; c_z++) {


                int top = world.getTopY(Heightmap.Type.WORLD_SURFACE, x + c_x, z + c_z);
                int top_wg = world.getTopY(Heightmap.Type.WORLD_SURFACE_WG, x + c_x, z + c_z);

                BlockPos position = new BlockPos(x + c_x, top_wg, z + c_z);

                // get desiredHeight from config
                int desiredHeight = config.worldHeight().getValue();
                // find height difference
                int diff = top_wg - desiredHeight;
                if (diff == 0) {
                    continue;
                }

                // if surface > desiredHeight   ->    move everything down (start at bottom)
                if (diff > 0) {
                    for (int i = diff; i <= 279 - diff; i++) { // was 319 - diff (old: top + diff)
                        BlockState state = Blocks.AIR.getDefaultState();
                        if (i + diff <= 319) {
                            state = world.getBlockState(position.withY(i + diff));
                        }

                        if (Math.abs(top - top_wg) > Math.abs(diff) && i + diff > top_wg) {
                            // do nothing? i.e leave trees but still move villages
                        } else {
                            ReplaceBlock(chunk, world, state, position, i, diff, blockStateFlag);
                        }
                    }
                }
                // otherwise desiredHeight > surface    ->    move everything up (start at top)
                else {
                    for (int i = 279 + diff; i > -64; i--) { // was 319 + diff (old: top - diff)
                        BlockState state = Blocks.AIR.getDefaultState();
                        if (i + diff > -64) {
                            state = world.getBlockState(position.withY(i + diff));
                        }

                        if (Math.abs(top - top_wg) > Math.abs(diff) && i + diff > top_wg) {
                            // do nothing? i.e leave trees but still move villages
                        } else {
                            ReplaceBlock(chunk, world, state, position, i, diff, blockStateFlag);
                        }
                    }
                }
            }
        }

        chunk.setLightOn(true);

        return true;
    }

    public void ReplaceBlock(Chunk chunk, StructureWorldAccess world, BlockState state, BlockPos position, int i, int diff, int blockStateFlag) {
        // check if we are copying a block entity
        if (state.hasBlockEntity()) {
            // get block entity
            BlockEntity entity = world.getBlockEntity(position.withY(i + diff));

            // set new block state (from the entities cached state)
            world.setBlockState(position.withY(i), entity.getCachedState(), blockStateFlag);

            // get the newly spawned block entity
            BlockEntity blockEntity = world.getBlockEntity(position.withY(i));
            if (blockEntity == null) { return; }

            // copy over the nbt
            blockEntity.readNbt(entity.createNbt());
            // remove old entity
            entity.markRemoved();
        } else {
            chunk.setBlockState(position.withY(i), state, false);
        }
    }
}