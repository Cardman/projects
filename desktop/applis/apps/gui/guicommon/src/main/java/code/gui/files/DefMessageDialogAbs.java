package code.gui.files;

import code.gui.AbsCommonFrame;
import code.gui.AbsCustComponent;
import code.gui.AbsDialog;
import code.gui.MessageDialogAbs;

public final class DefMessageDialogAbs implements MessageDialogAbs {

    private final ConfirmDialog confirmDialog;

    public DefMessageDialogAbs(ConfirmDialog _conf) {
        confirmDialog = _conf;
    }

    @Override
    public int input(AbsDialog _frame, String _message, String _title, int _option) {
        ConfirmDialog.showMessage(_frame,_message,_title, _option, confirmDialog);
        return 0;
    }

    @Override
    public int input(AbsCommonFrame _frame, String _message, String _title, int _option) {
        ConfirmDialog.showMessage(_message, _title, _option, confirmDialog, _frame);
        return 0;
    }

    @Override
    public int input(AbsCommonFrame _frame, AbsCustComponent _message, String _title, int _option) {
        ConfirmDialog.showComponent(_message,
                _title,
                _option, confirmDialog, _frame);
        return 0;
    }
}
