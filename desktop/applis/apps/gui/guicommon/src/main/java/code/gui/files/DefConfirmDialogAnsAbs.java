package code.gui.files;

import code.gui.AbsCommonFrame;
import code.gui.AbsDialog;
import code.gui.ConfirmDialogAnsAbs;

public final class DefConfirmDialogAnsAbs implements ConfirmDialogAnsAbs {

    private final ConfirmDialog confirmDialog;

    public DefConfirmDialogAnsAbs(ConfirmDialog _conf) {
        confirmDialog = _conf;
    }

    @Override
    public int input(AbsCommonFrame _frame, String _message, String _title, int _option) {
        return ConfirmDialog.getAnswer(_message,_title, _option, confirmDialog, _frame);
    }

    @Override
    public int input(AbsDialog _dialog, AbsCommonFrame _frame, String _message, String _title, int _option) {
        return ConfirmDialog.showMiniDialog(_dialog,_message,_title, _option,confirmDialog).getAnswer();
    }
}
