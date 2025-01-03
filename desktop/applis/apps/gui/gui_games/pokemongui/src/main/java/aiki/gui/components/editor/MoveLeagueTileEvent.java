package aiki.gui.components.editor;

public final class MoveLeagueTileEvent extends AbsMoveForeTileEvent {
    private final ContentComponentModelLevelLeague form;
    public MoveLeagueTileEvent(ContentComponentModelLevelLeague _g) {
        super(_g.getContentLevel());
        form = _g;
    }

    @Override
    protected void move() {
        form.moveTile();
    }
}
