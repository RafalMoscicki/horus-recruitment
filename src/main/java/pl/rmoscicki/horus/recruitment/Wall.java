package pl.rmoscicki.horus.recruitment;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Wall implements Structure {

    private List<Block> blocks;

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return blocks.stream()
                .filter(block -> block.getColor().equals(color))
                .findAny();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return blocks.stream()
                .filter(block -> block.getMaterial().equals(material))
                .collect(Collectors.toList());
    }

    @Override
    public int count() {
        return blocks.stream()
                .map(this::count)
                .mapToInt(x -> x)
                .sum();
    }

    private int count(Block block) {
        if (block instanceof CompositeBlock) {
            return ((CompositeBlock) block).getBlocks()
                    .stream()
                    .map(this::count)
                    .mapToInt(x -> x)
                    .sum();
        }
        return 1;
    }
}
