package aiki.gui.components.editor;

import code.gui.events.*;

public final class PkCompleteTextEvent implements AbsAutoCompleteListener {
    private final GeneComponentModelText input;

    public PkCompleteTextEvent(GeneComponentModelText _i) {
        this.input = _i;
    }

    @Override
    public void insertUpdate(int _off, int _len) {
        PkCompleteEvent.computeWords(input, _len);
    }

    @Override
    public void removeUpdate(int _off, int _len) {
        PkCompleteEvent.computeWords(input, -_len);
    }

    @Override
    public void changedUpdate() {
        input.valueString();
    }
}
