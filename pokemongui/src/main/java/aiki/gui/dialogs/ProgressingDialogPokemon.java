package aiki.gui.dialogs;
import aiki.DataBase;
import code.gui.ProgressingDialog;

public final class ProgressingDialogPokemon extends ProgressingDialog {

    @Override
    public void closeWindow() {
        super.closeWindow();
        DataBase.setLoading(false);
    }
}
