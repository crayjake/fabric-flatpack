package net.jake.flatpack;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;

public record SuperflatFeatureConfig(ConstantIntProvider worldHeight) implements FeatureConfig {
    public static final Codec<SuperflatFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ConstantIntProvider.CODEC.fieldOf("worldHeight").forGetter(SuperflatFeatureConfig::worldHeight)
    ).apply(instance, instance.stable(SuperflatFeatureConfig::new)));
}