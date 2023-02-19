package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class ValidateNavLine implements AbsActionListener {
    private final OutputDialogNavLine output;

    public ValidateNavLine(OutputDialogNavLine _w) {
        this.output = _w;
    }

    @Override
    public void action() {
        output.getWindowCdmEditor().getDialogNavigLine().setVisible(false);
        int index_ = output.getIndex();
        output.getTab().afterValidate(index_);
    }
}
