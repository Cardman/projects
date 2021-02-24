package aiki.util;

import aiki.map.levels.Link;
import code.util.CollCapacity;

public final class PointsLink extends Points<Link> {
    public PointsLink() {
    }
    public PointsLink(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected Link def() {
        return new Link("");
    }
}
