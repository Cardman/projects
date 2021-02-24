package aiki.map.util;

public final class MiniMapCoordsTile {
    private final MiniMapCoords miniMapCoords;
    private final TileMiniMap tileMap;

    public MiniMapCoordsTile(MiniMapCoords _miniMapCoords, TileMiniMap _tileMap) {
        this.miniMapCoords = _miniMapCoords;
        this.tileMap = _tileMap;
    }

    public MiniMapCoords getMiniMapCoords() {
        return miniMapCoords;
    }

    public TileMiniMap getTileMap() {
        return tileMap;
    }
}
