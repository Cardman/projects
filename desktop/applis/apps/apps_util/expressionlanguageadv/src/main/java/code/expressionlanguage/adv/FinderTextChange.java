package code.expressionlanguage.adv;

import code.gui.AbsCommonFrame;
import code.gui.events.AbsAutoCompleteListener;

public final class FinderTextChange implements AbsAutoCompleteListener {
    private final WindowCdmEditor current;

    public FinderTextChange(WindowCdmEditor _editor) {
        current = _editor;
    }
    @Override
    public void insertUpdate() {
        update();
    }

    @Override
    public void removeUpdate() {
        update();
    }

    @Override
    public void changedUpdate() {
        update();
    }

    private void update() {
        AbsCommonFrame frame_ = current.getCommonFrame();
        FindAction.updateEditor(current.getTabEditor());
        frame_.pack();
    }
}
