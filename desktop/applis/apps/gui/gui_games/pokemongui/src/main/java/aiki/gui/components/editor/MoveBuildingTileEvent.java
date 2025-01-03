package aiki.gui.components.editor;

public final class MoveBuildingTileEvent extends AbsMoveForeTileEvent {
    private final ContentComponentModelCity form;
    public MoveBuildingTileEvent(ContentComponentModelCity _g) {
        super(_g.getContentLevelOutdoor());
        form = _g;
    }

    @Override
    protected void move() {
        form.moveTile();
    }
}
