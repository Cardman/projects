package aiki.gui.components.editor;

public final class MoveBuildingEltTileEvent extends AbsMoveForeTileEvent {
    private final ContentComponentModelCity form;
    public MoveBuildingEltTileEvent(ContentComponentModelCity _g) {
        super(_g.getContentLevelBuilding());
        form = _g;
    }

    @Override
    protected void move() {
        form.moveBuildingTile();
    }
}
