package pl.rmoscicki.horus.recruitment;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Wall implements Structure {

    private List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        if (color == null) {
            return Optional.empty();
        }
        return blocks.stream()
                .flatMap(block -> getAllBlocks(block).stream())
                .filter(block -> color.equals(block.getColor()))
                .findAny();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        if (material == null) {
            return Collections.emptyList();
        }
        return blocks.stream()
                .flatMap(block -> getAllBlocks(block).stream())
                .filter(block -> material.equals(block.getMaterial()))
                .collect(Collectors.toList());
    }

    @Override
    public int count() {
        return blocks.stream()
                .mapToInt(block -> getAllBlocks(block).size())
                .sum();
    }

    private List<Block> getAllBlocks(Block block) {
        List<Block> result = new LinkedList<>();
        result.add(block);
        if (block instanceof CompositeBlock) {
            List<Block> children = ((CompositeBlock) block).getBlocks()
                    .stream()
                    .flatMap(b -> getAllBlocks(b).stream())
                    .collect(Collectors.toList());
            result.addAll(children);
        }
        return result;
    }
}
