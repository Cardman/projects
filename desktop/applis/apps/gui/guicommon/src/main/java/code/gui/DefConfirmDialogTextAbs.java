package code.gui;

public final class DefConfirmDialogTextAbs implements ConfirmDialogTextAbs{
    @Override
    public TextAnswerValue input(AbsGroupFrame _frame, String _value, String _message, String _title, String _language) {
        ConfirmDialog.showTextField((GroupFrame) _frame, _value, _message, _title, _language);
        return ((GroupFrame) _frame).getConfirmDialog().textValue();
    }
}
