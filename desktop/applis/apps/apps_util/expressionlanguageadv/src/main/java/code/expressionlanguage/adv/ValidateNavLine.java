package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class ValidateNavLine implements AbsActionListener {
    private final OutputDialogNavLine output;

    public ValidateNavLine(OutputDialogNavLine _w) {
        this.output = _w;
    }

    @Override
    public void action() {
        output.getResult().setCol(output.getCol().getValue());
        output.getResult().setRow(output.getRow().getValue());
        output.getResult().setIndex(output.getTab().index(output.getResult().getRow(), output.getResult().getCol()));
        if (output.getResult().getIndex() < 0) {
            return;
        }
        output.getResult().getValid().set(true);
        output.getWindowCdmEditor().getDialogNavigLine().setVisible(false);
    }
}
