package code.gui;

public final class DefConfirmDialogTextAbs implements ConfirmDialogTextAbs{

    private final ConfirmDialog confirmDialog;

    public DefConfirmDialogTextAbs(ConfirmDialog _conf) {
        confirmDialog = _conf;
    }
    @Override
    public TextAnswerValue input(AbsCommonFrame _frame, String _value, String _message, String _title, String _language) {
        ConfirmDialog.showTextField(_value, _message, _title, _language, confirmDialog, _frame);
        return confirmDialog.textValue();
    }
}
