package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class ValidateNavLine implements AbsActionListener {
    private final OutputDialogNavLine output;
    private final WindowCdmEditor editor;

    public ValidateNavLine(OutputDialogNavLine _w, WindowCdmEditor _ed) {
        this.output = _w;
        editor = _ed;
    }

    @Override
    public void action() {
        editor.getDialogNavigLine().setVisible(false);
        int index_ = output.getIndex();
        output.getTab().afterValidate(index_);
    }
}
