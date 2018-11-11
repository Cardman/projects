package aiki.gui.dialogs;
import aiki.facade.FacadeGame;
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
        FacadeGame fg_ = mainWindow.getFacade();
        fg_.setLoading(false);
    }
}
