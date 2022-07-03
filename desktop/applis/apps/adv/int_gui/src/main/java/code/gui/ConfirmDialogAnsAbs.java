package code.gui;

public interface ConfirmDialogAnsAbs {
    int input(AbsGroupFrame _frame, String _message, String _title, String _language, int _option);
    int input(AbsDialog _dialog, AbsGroupFrame _frame, String _message, String _title, String _language, int _option);
}
