package code.gui;

public final class DefMessageDialogAbs implements MessageDialogAbs {

    private final ConfirmDialog confirmDialog;

    public DefMessageDialogAbs(ConfirmDialog _conf) {
        confirmDialog = _conf;
    }

    @Override
    public int input(AbsDialog _frame, String _message, String _title, String _language, int _option) {
        ConfirmDialog.showMessage(_frame,_message,_title,_language,_option, confirmDialog);
        return 0;
    }

    @Override
    public int input(AbsCommonFrame _frame, String _message, String _title, String _language, int _option) {
        ConfirmDialog.showMessage(_message, _title, _language, _option, confirmDialog, _frame);
        return 0;
    }

    @Override
    public int input(AbsCommonFrame _frame, AbsCustComponent _message, String _title, String _language, int _option) {
        ConfirmDialog.showComponent(_message,
                _title,
                _language, _option, confirmDialog, _frame);
        return 0;
    }
}
