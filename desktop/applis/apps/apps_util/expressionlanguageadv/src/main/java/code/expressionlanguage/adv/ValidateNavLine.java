package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class ValidateNavLine implements AbsActionListener {
    private final OutputDialogNavLine output;

    public ValidateNavLine(OutputDialogNavLine _w) {
        this.output = _w;
    }

    @Override
    public void action() {
        int index_ = output.getIndex();
        output.getTab().setNavigateIndex(index_);
        output.getValid().set(true);
        output.getWindowCdmEditor().getDialogNavigLine().setVisible(false);
    }
}
