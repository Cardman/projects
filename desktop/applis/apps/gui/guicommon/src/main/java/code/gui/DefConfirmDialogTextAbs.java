package code.gui;

public final class DefConfirmDialogTextAbs implements ConfirmDialogTextAbs{
    @Override
    public TextAnswerValue input(GroupFrame _frame, String _value, String _message, String _title, String _language) {
        ConfirmDialog.showTextField(_frame, _value, _message, _title, _language);
        return _frame.getConfirmDialog().textValue();
    }
}
