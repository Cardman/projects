package cards.gui.dialogs;

import cards.gui.WindowCardsInt;
import code.gui.AbsCommonFrame;

public interface DialogDisplaying {

    void validateDisplaying();
    AbsCommonFrame getAbsDialog();

    DialogDisplayingContent getDialogDisplayingContent();
    void savePrefs(WindowCardsInt _w);
}
