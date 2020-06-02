package aiki.gui.dialogs;
import aiki.gui.MainWindow;
import code.gui.ProgressingDialog;

public final class ProgressingDialogPokemon extends ProgressingDialog {

    private MainWindow mainWindow;

    public ProgressingDialogPokemon(MainWindow _mainWindow) {
        mainWindow = _mainWindow;
    }

    @Override
    public void closeWindow() {
        super.closeWindow();
        mainWindow.getLoadFlag().set(false);
    }
}
