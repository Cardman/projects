package code.gui;

public final class DefConfirmDialogAnsAbs implements ConfirmDialogAnsAbs {

    private final ConfirmDialog confirmDialog;

    public DefConfirmDialogAnsAbs(ConfirmDialog _conf) {
        confirmDialog = _conf;
    }

    @Override
    public int input(AbsCommonFrame _frame, String _message, String _title, String _language, int _option) {
        return ConfirmDialog.getAnswer(_message,_title, _language,_option, confirmDialog, _frame);
    }

    @Override
    public int input(AbsDialog _dialog, AbsCommonFrame _frame, String _message, String _title, String _language, int _option) {
        return ConfirmDialog.showMiniDialog(_dialog,_message,_title,_language,_option,confirmDialog).getAnswer();
    }
}
