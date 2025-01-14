package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;
import code.gui.events.AbsAutoCompleteListener;

public final class DirectValidateKeyValueEvent implements AbsAutoCompleteListener {
    private final AbsActionListener matching;

    public DirectValidateKeyValueEvent(AbsActionListener _m) {
        this.matching = _m;
    }

    @Override
    public void insertUpdate(int _off, int _len) {
        matching.action();
    }

    @Override
    public void removeUpdate(int _off, int _len) {
        matching.action();
    }

    @Override
    public void changedUpdate(int _off, int _len) {
        matching.action();
    }
}
