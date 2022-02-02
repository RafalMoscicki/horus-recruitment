package pl.rmoscicki.horus.recruitment;

import java.util.List;
import java.util.Optional;

public class Wall implements Structure {

    private List blocks;

    @Override
    public Optional findBlockByColor(String color) {
        return Optional.empty();
    }

    @Override
    public List findBlocksByMaterial(String material) {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }
}
