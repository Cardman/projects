package aiki.gui.components.editor;

import code.gui.events.AbsActionListener;

public final class ApplyModifDataMapDimsEvent implements AbsActionListener {
    private final FormDataMap formDataMap;

    public ApplyModifDataMapDimsEvent(FormDataMap _f) {
        this.formDataMap = _f;
    }

    @Override
    public void action() {
        formDataMap.update();
    }
}
