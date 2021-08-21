package aiki.gui.dialogs;
import aiki.gui.WindowAiki;
import code.gui.ProgressingDialog;

public final class ProgressingDialogPokemon extends ProgressingDialog {

    private WindowAiki mainWindow;

    public ProgressingDialogPokemon(WindowAiki _mainWindow) {
        super(_mainWindow.getFrames().getFrameFactory());
        mainWindow = _mainWindow;
    }

    @Override
    public void closeWindow() {
        super.closeWindow();
        mainWindow.getLoadFlag().set(false);
    }
}
