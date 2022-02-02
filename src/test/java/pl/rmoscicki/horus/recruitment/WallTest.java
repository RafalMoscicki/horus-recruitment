package pl.rmoscicki.horus.recruitment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WallTest {

    @Test
    void shouldCountForEmptyWall() {
        //Given
        List<Block> blocks = Collections.emptyList();
        Structure wall = new Wall(blocks);

        //When
        int count = wall.count();

        //Then
        assertEquals(0, count);
    }

    @Test
    void shouldCountForOneSimpleBlock() {
        //Given
        Block simpleBlock = mock(Block.class);
        List<Block> blocks = Arrays.asList(simpleBlock);
        Structure wall = new Wall(blocks);

        //When
        int count = wall.count();

        //Then
        assertEquals(1, count);
    }

    @Test
    void shouldCountForCompositeBlock() {
        //Given
        CompositeBlock compositeBlock = mock(CompositeBlock.class);
        when(compositeBlock.getBlocks()).thenReturn(Arrays.asList(mock(Block.class), mock(Block.class)));
        List<Block> blocks = Arrays.asList(compositeBlock);
        Structure wall = new Wall(blocks);

        //When
        int count = wall.count();

        //Then
        assertEquals(2, count);
    }

    @Test
    void shouldCountForNestedCompositeBlock() {
        //Given
        CompositeBlock compositeBlock = mock(CompositeBlock.class);
        CompositeBlock nestedCompositeBlock = mock(CompositeBlock.class);
        when(nestedCompositeBlock.getBlocks()).thenReturn(Arrays.asList(mock(Block.class), mock(Block.class)));
        when(compositeBlock.getBlocks()).thenReturn(Arrays.asList(mock(Block.class), nestedCompositeBlock));
        List<Block> blocks = Arrays.asList(compositeBlock);
        Structure wall = new Wall(blocks);

        //When
        int count = wall.count();

        //Then
        assertEquals(3, count);
    }

}