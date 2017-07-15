package aiki.gui.dialogs;
import code.gui.ProgressingDialog;
import aiki.DataBase;

public final class ProgressingDialogPokemon extends ProgressingDialog {

    @Override
    public void closeWindow() {
        super.closeWindow();
        DataBase.setLoading(false);
    }
}
