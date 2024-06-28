package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;
import code.gui.events.AbsAutoCompleteListener;

public final class DirectValidateKeyValueEvent implements AbsAutoCompleteListener {
    private final AbsActionListener matching;

    public DirectValidateKeyValueEvent(AbsActionListener _m) {
        this.matching = _m;
    }

    @Override
    public void insertUpdate() {
        matching.action();
    }

    @Override
    public void removeUpdate() {
        matching.action();
    }

    @Override
    public void changedUpdate() {
        matching.action();
    }
}
