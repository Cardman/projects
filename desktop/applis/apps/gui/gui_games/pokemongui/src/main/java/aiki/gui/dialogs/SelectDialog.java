package aiki.gui.dialogs;
import code.gui.AbsCloseableDialog;
import code.gui.AbsDialog;
import code.gui.initialize.AbsFrameFactory;

public abstract class SelectDialog implements AbsCloseableDialog {

    private final AbsDialog selectDial;
    private boolean ok;

    protected SelectDialog(AbsFrameFactory _fact) {
        selectDial = _fact.newDialog(this);
    }

    public AbsDialog getSelectDial() {
        return selectDial;
    }

    public void validateChoice() {
        ok = true;
        closeWindow();
    }

    protected boolean isSelected() {
        return ok;
    }

    protected void initOk() {
        ok = false;
    }
}
