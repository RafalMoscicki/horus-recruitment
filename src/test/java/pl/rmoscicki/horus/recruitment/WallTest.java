package pl.rmoscicki.horus.recruitment;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WallTest {

    @Test
    void shouldCountForEmptyWall() {
        //Given
        Structure wall = createEmptyWall();

        //When
        int count = wall.count();

        //Then
        assertEquals(0, count);
    }

    @Test
    void shouldCountForOneSimpleBlock() {
        //Given
        Structure wall = createWallWithOneSimpleBlock();

        //When
        int count = wall.count();

        //Then
        assertEquals(1, count);
    }

    @Test
    void shouldCountForCompositeBlock() {
        //Given
        Structure wall = createWallWithOneCompositeBlock();

        //When
        int count = wall.count();

        //Then
        assertEquals(3, count);
    }

    @Test
    void shouldCountForCompositeNestedBlock() {
        //Given
        Structure wall = createWallWihOneCompositeNestedBlock();

        //When
        int count = wall.count();

        //Then
        assertEquals(5, count);
    }

    @Test
    void shouldNotFindByColorForEmptyWall() {
        //Given
        Structure wall = createEmptyWall();

        //When
        Optional<Block> result = wall.findBlockByColor("red");

        //Then
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldFindByColorForOneSimpleBlock() {
        //Given
        Structure wall = createWallWithOneSimpleBlock();

        //When
        Optional<Block> result = wall.findBlockByColor("red");

        //Then
        assertTrue(result.isPresent());
    }

    @Test
    void shouldNotFindByColorForOneSimpleBlock() {
        //Given
        Structure wall = createWallWithOneSimpleBlock();

        //When
        Optional<Block> result = wall.findBlockByColor("pink");

        //Then
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldFindByColorForCompositeBlock() {
        //Given
        Structure wall = createWallWithOneCompositeBlock();

        //When
        Optional<Block> result = wall.findBlockByColor("red");

        //Then
        assertTrue(result.isPresent());
    }

    @Test
    void shouldNotFindByColorForCompositeBlock() {
        //Given
        Structure wall = createWallWithOneCompositeBlock();

        //When
        Optional<Block> result = wall.findBlockByColor("pink");

        //Then
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldFindByColorForCompositeNestedBlock() {
        //Given
        Structure wall = createWallWihOneCompositeNestedBlock();

        //When
        Optional<Block> result = wall.findBlockByColor("red");

        //Then
        assertTrue(result.isPresent());
    }

    @Test
    void shouldNotFindByColorForCompositeNestedBlock() {
        //Given
        Structure wall = createWallWihOneCompositeNestedBlock();

        //When
        Optional<Block> result = wall.findBlockByColor("pink");

        //Then
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldNotFindByMaterialForEmptyWall() {
        //Given
        Structure wall = createEmptyWall();

        //When
        List<Block> result = wall.findBlocksByMaterial("airbrick");

        //Then
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldFindByMaterialForOneSimpleBlock() {
        //Given
        Structure wall = createWallWithOneSimpleBlock();

        //When
        List<Block> result = wall.findBlocksByMaterial("brick");

        //Then
        assertEquals(1, result.size());
    }

    @Test
    void shouldNotFindByMaterialForOneSimpleBlock() {
        //Given
        Structure wall = createWallWithOneSimpleBlock();

        //When
        List<Block> result = wall.findBlocksByMaterial("airbrick");

        //Then
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldFindByMaterialForCompositeBlock() {
        //Given
        Structure wall = createWallWithOneCompositeBlock();

        //When
        List<Block> result = wall.findBlocksByMaterial("brick");

        //Then
        assertEquals(3, result.size());
    }

    @Test
    void shouldNotFindByMaterialForCompositeBlock() {
        //Given
        Structure wall = createWallWithOneCompositeBlock();

        //When
        List<Block> result = wall.findBlocksByMaterial("airbrick");

        //Then
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldFindByMaterialForCompositeNestedBlock() {
        //Given
        Structure wall = createWallWihOneCompositeNestedBlock();

        //When
        List<Block> result = wall.findBlocksByMaterial("brick");

        //Then
        assertEquals(4, result.size());
    }

    @Test
    void shouldNotFindByMaterialForCompositeNestedBlock() {
        //Given
        Structure wall = createWallWihOneCompositeNestedBlock();

        //When
        List<Block> result = wall.findBlocksByMaterial("airbrick");

        //Then
        assertTrue(result.isEmpty());
    }

    private Structure createEmptyWall() {
        List<Block> blocks = Collections.emptyList();
        return new Wall(blocks);
    }

    private Structure createWallWithOneSimpleBlock() {
        Block simpleBlock = mock(Block.class);
        when(simpleBlock.getColor()).thenReturn("red");
        when(simpleBlock.getMaterial()).thenReturn("brick");
        List<Block> blocks = Arrays.asList(simpleBlock);
        return new Wall(blocks);
    }

    private Structure createWallWithOneCompositeBlock() {
        CompositeBlock compositeBlock = mock(CompositeBlock.class);
        when(compositeBlock.getColor()).thenReturn("red");
        when(compositeBlock.getMaterial()).thenReturn("brick");
        Block block1 = mock(Block.class);
        Block block2 = mock(Block.class);
        when(block1.getColor()).thenReturn("red");
        when(block1.getMaterial()).thenReturn("brick");
        when(block2.getColor()).thenReturn("white");
        when(block2.getMaterial()).thenReturn("brick");
        when(compositeBlock.getBlocks()).thenReturn(Arrays.asList(block1, block2));
        List<Block> blocks = Arrays.asList(compositeBlock);
        return new Wall(blocks);
    }

    private Structure createWallWihOneCompositeNestedBlock() {
        Block block1 = mock(Block.class);
        Block block2 = mock(Block.class);
        Block block3 = mock(Block.class);
        when(block1.getColor()).thenReturn("red");
        when(block1.getMaterial()).thenReturn("brick");
        when(block2.getColor()).thenReturn("white");
        when(block2.getMaterial()).thenReturn("brick");
        when(block3.getColor()).thenReturn("white");
        when(block3.getMaterial()).thenReturn("brick");
        CompositeBlock compositeBlock = mock(CompositeBlock.class);
        CompositeBlock nestedCompositeBlock = mock(CompositeBlock.class);
        when(compositeBlock.getColor()).thenReturn("red");
        when(compositeBlock.getMaterial()).thenReturn("brick");
        when(nestedCompositeBlock.getColor()).thenReturn(null);
        when(nestedCompositeBlock.getMaterial()).thenReturn(null);
        when(nestedCompositeBlock.getBlocks()).thenReturn(Arrays.asList(block1, block2));
        when(compositeBlock.getBlocks()).thenReturn(Arrays.asList(block3, nestedCompositeBlock));
        List<Block> blocks = Arrays.asList(compositeBlock);
        return new Wall(blocks);
    }

}