package cards.gui.dialogs;

import code.gui.AbsDialog;

public interface DialogRules {

    void saveRules();
    void validateRules();
    AbsDialog getCardDialog();
    void closeWindow();
}
