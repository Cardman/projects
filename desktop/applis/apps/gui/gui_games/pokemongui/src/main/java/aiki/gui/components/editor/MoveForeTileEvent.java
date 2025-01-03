package aiki.gui.components.editor;

public final class MoveForeTileEvent extends AbsMoveForeTileEvent {
    private final ContentComponentModelLevelWithWild form;
    public MoveForeTileEvent(ContentComponentModelLevelWithWild _g) {
        super(_g.getContentLevel());
        form = _g;
    }

    @Override
    protected void move() {
        form.moveTile();
    }
}
