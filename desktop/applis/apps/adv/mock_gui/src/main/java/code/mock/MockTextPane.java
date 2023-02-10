package code.mock;

import code.gui.AbsTextPane;
import code.gui.events.AbsEnabledAction;
import code.gui.images.MetaFont;
import code.util.StringMap;

public final class MockTextPane extends MockTxtComponent implements AbsTextPane {
    private final StringMap<AbsEnabledAction> actions = new StringMap<AbsEnabledAction>();


    @Override
    public void registerKeyboardAction(AbsEnabledAction _action, int _a, int _b) {
        actions.put(_a+","+_b,_action);
    }

    @Override
    public StringMap<AbsEnabledAction> getActionsMap() {
        return actions;
    }

    @Override
    public void setFontSize(int _size) {
        MetaFont m_ = getMetaFont();
        setFont(new MetaFont(m_.getFontFamily(),m_.getFont(),_size));
    }
}
