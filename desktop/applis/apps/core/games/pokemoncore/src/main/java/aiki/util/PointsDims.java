package aiki.util;

import aiki.map.tree.util.Dims;

public final class PointsDims extends Points<Dims> {

    @Override
    protected Dims def() {
        return new Dims();
    }
}
