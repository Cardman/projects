package cards.gui.dialogs;

import code.gui.AbsCommonFrame;

public interface DialogRules {

    void saveRules();
    void validateRules();
    AbsCommonFrame getAbsDialog();
    void closeWindow();
}
