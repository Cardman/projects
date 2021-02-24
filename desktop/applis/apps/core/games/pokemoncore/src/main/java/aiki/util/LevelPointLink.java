package aiki.util;

import aiki.map.levels.Link;

public final class LevelPointLink {
    private final LevelPoint levelPoint;
    private final Link link;

    public LevelPointLink(LevelPoint _levelPoint, Link _link) {
        this.levelPoint = _levelPoint;
        this.link = _link;
    }

    public LevelPoint getLevelPoint() {
        return levelPoint;
    }

    public Link getLink() {
        return link;
    }
}
