package code.mock;

import code.gui.AbsTextPane;
import code.gui.events.AbsEnabledAction;
import code.gui.images.MetaFont;
import code.util.CustList;
import code.util.StringMap;

public final class MockTextPane extends MockCustComponent implements AbsTextPane {
    private final StringMap<AbsEnabledAction> actions = new StringMap<AbsEnabledAction>();

    @Override
    public void registerKeyboardAction(AbsEnabledAction _action, int _a, int _b) {
        actions.put(_a+","+_b,_action);
    }

    @Override
    public CustList<String> getKeysAction() {
        return actions.getKeys();
    }

    @Override
    public AbsEnabledAction getAction(int _a, int _b) {
        return actions.getVal(_a+","+_b);
    }

    @Override
    public CustList<AbsEnabledAction> getActions() {
        return actions.values();
    }

    @Override
    public void setFontSize(int _size) {
        MetaFont m_ = getMetaFont();
        setFont(new MetaFont(m_.getFontFamily(),m_.getFont(),_size));
    }
}
