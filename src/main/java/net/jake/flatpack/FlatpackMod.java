package net.jake.flatpack;

// client
import net.minecraft.client.world.GeneratorType;

// util
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

// world gen
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.GeneratorOptions;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.chunk.*;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.*;

// fabric
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;

// flatpack
import net.jake.flatpack.mixin.GeneratorTypeAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlatpackMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("flatpack");
	public static boolean isFLATPACK = false;
	public static int height = 100;

	private static final Feature<SuperflatFeatureConfig> SUPERFLAT = new SuperflatFeature(SuperflatFeatureConfig.CODEC);

	private static ConfiguredFeature<?, ?> SUPERFLAT_CONFIGURED = SUPERFLAT
			.configure(new SuperflatFeatureConfig(
					ConstantIntProvider.create(height)));

	public static PlacedFeature SUPERFLAT_PLACED = SUPERFLAT_CONFIGURED.withPlacement(
			CountPlacementModifier.of(1), // number of veins per chunk
			SquarePlacementModifier.of(), // spreading horizontally
			HeightRangePlacementModifier.uniform(YOffset.fixed(height), YOffset.fixed(height))); // height

	@Override
	public void onInitialize() {
		// feature
		RegistryKey<Feature<?>> REGISTRY_FEATURE = RegistryKey.of(Registry.FEATURE_KEY,
				new Identifier("flatpack", "superflat_feature"));

		Registry.register(Registry.FEATURE,
				REGISTRY_FEATURE.getValue(), SUPERFLAT);

		// configured feature
		RegistryKey<ConfiguredFeature<?,?>> REGISTRY_CONFIGURED = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
				new Identifier("flatpack", "superflat_feature"));

		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
				REGISTRY_CONFIGURED.getValue(), SUPERFLAT_CONFIGURED);

		// placed feature
		RegistryKey<PlacedFeature> REGISTRY_PLACED = RegistryKey.of(Registry.PLACED_FEATURE_KEY,
				new Identifier("flatpack", "superflat_feature"));

		Registry.register(BuiltinRegistries.PLACED_FEATURE,
				REGISTRY_PLACED.getValue(), SUPERFLAT_PLACED);


		// create and register flatpack generator type
		final GeneratorType FLATPACK = new GeneratorType("flatpack"){

			protected ChunkGenerator getChunkGenerator(DynamicRegistryManager registryManager, long seed) {
				if(!FlatpackMod.isFLATPACK) { FlatpackMod.isFLATPACK = true; }
				// only add the superflat structure if we are retrieving flatpack generator
				BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.SURFACE_STRUCTURES, REGISTRY_PLACED); // consider looking at gen step

				return GeneratorOptions.createOverworldGenerator(registryManager, seed);
			}
		};
		GeneratorTypeAccessor.getValues().add(0, FLATPACK);}
}



