
package user.biscut710.corrupted.world.biome;

import user.biscut710.corrupted.world.features.treedecorators.GlowingObsidianFieldLandsTrunkDecorator;
import user.biscut710.corrupted.world.features.treedecorators.GlowingObsidianFieldLandsLeaveDecorator;
import user.biscut710.corrupted.world.features.treedecorators.GlowingObsidianFieldLandsFruitDecorator;
import user.biscut710.corrupted.init.CorruptedMcModBlocks;

import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class GlowingObsidianFieldLandsBiome {
	public static Biome createBiome() {
		BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder().fogColor(-65536).waterColor(-65536).waterFogColor(-65536).skyColor(-65536)
				.foliageColorOverride(-65536).grassColorOverride(-65536).build();
		BiomeGenerationSettings.Builder biomeGenerationSettings = new BiomeGenerationSettings.Builder();
		biomeGenerationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PlacementUtils.register(
				"corrupted_mc:tree_glowing_obsidian_field_lands",
				FeatureUtils.register("corrupted_mc:tree_glowing_obsidian_field_lands", Feature.TREE,
						new TreeConfiguration.TreeConfigurationBuilder(
								BlockStateProvider.simple(CorruptedMcModBlocks.GLOWING_OBSIDIAN.get().defaultBlockState()),
								new StraightTrunkPlacer(7, 2, 0),
								BlockStateProvider.simple(CorruptedMcModBlocks.GLOWING_OBSIDIAN.get().defaultBlockState()),
								new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1))
								.decorators(ImmutableList.of(GlowingObsidianFieldLandsLeaveDecorator.INSTANCE,
										GlowingObsidianFieldLandsTrunkDecorator.INSTANCE, GlowingObsidianFieldLandsFruitDecorator.INSTANCE))
								.build()),
				List.of(CountPlacement.of(3), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0),
						PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING), BiomeFilter.biome())));
		BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeGenerationSettings);
		BiomeDefaultFeatures.addDefaultOres(biomeGenerationSettings);
		BiomeDefaultFeatures.addSurfaceFreezing(biomeGenerationSettings);
		MobSpawnSettings.Builder mobSpawnInfo = new MobSpawnSettings.Builder();
		return new Biome.BiomeBuilder().precipitation(Biome.Precipitation.RAIN).biomeCategory(Biome.BiomeCategory.NONE).temperature(0.5f)
				.downfall(0.5f).specialEffects(effects).mobSpawnSettings(mobSpawnInfo.build()).generationSettings(biomeGenerationSettings.build())
				.build();
	}
}
