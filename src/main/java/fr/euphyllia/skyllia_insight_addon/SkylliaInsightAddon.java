package fr.euphyllia.skyllia_insight_addon;

import dev.frankheijden.insights.api.addons.InsightsAddon;
import dev.frankheijden.insights.api.addons.Region;
import dev.frankheijden.insights.api.objects.chunk.ChunkLocation;
import dev.frankheijden.insights.api.objects.chunk.ChunkPart;
import fr.euphyllia.skyllia.api.SkylliaAPI;
import fr.euphyllia.skyllia.api.skyblock.Island;
import fr.euphyllia.skyllia.api.skyblock.model.Position;
import fr.euphyllia.skyllia.api.utils.helper.RegionHelper;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SkylliaInsightAddon implements InsightsAddon {

    @Override
    public String getPluginName() {
        return "Skyllia";
    }

    @Override
    public String getAreaName() {
        return "island";
    }

    @Override
    public String getVersion() {
        return "{version}";
    }

    @Override
    public Optional<Region> getRegion(org.bukkit.Location location) {
        if (!SkylliaAPI.isWorldSkyblock(location.getWorld())) return Optional.empty();
        Island island = SkylliaAPI.getIslandByChunk(location.getChunk());
        if (island == null) return Optional.empty();
        return Optional.of(new SkylliaRegion(island, location.getWorld()));
    }

    public class SkylliaRegion implements Region {

        private Island island;
        private World world;
        public SkylliaRegion(Island island, World world) {
            this.island = island;
            this.world = world;
        }

        @Override
        public String getAddon() {
            return getPluginName();
        }

        @Override
        public String getKey() {
            return "SKYLLIA_%s".formatted(this.island.getId());
        }

        @Override
        public List<ChunkPart> toChunkParts() {
            List<Position> islandPositionWithRadius = RegionHelper.getRegionsInRadius(island.getPosition(), (int) Math.round(island.getSize())+1);

            List<ChunkPart> parts = new ArrayList<>(islandPositionWithRadius.size());
            for (Position chunk : islandPositionWithRadius) {
                parts.add(new ChunkPart(new ChunkLocation(world, chunk.x(), chunk.z())));
            }
            return parts;
        }
    }
}