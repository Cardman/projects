package aiki.gui.dialogs;
import code.gui.Dialog;

public abstract class SelectDialog extends Dialog {

    private boolean ok;

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
