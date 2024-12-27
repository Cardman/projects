package aiki.gui.components.editor;

import code.gui.events.*;

public final class BuildingKindChoiceEvent implements AbsActionListener {
    private final ContentComponentModelCity form;

    public BuildingKindChoiceEvent(ContentComponentModelCity _f) {
        this.form = _f;
    }
    @Override
    public void action() {
        form.choose(true);
        form.getLevel().getFrame().pack();
    }
}
