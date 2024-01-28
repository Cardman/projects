package cards.gui.dialogs;

import cards.gui.WindowCardsInt;
import code.gui.AbsDialog;

public interface DialogDisplaying {

    void validateDisplaying();
    AbsDialog getCardDialog();

    DialogDisplayingContent getDialogDisplayingContent();
    void savePrefs(WindowCardsInt _w);
}
