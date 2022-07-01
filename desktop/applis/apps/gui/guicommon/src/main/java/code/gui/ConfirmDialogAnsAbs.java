package code.gui;

public interface ConfirmDialogAnsAbs {
    int input(GroupFrame _frame, String _message, String _title, String _language, int _option);
    int input(AbsDialog _dialog, GroupFrame _frame, String _message, String _title, String _language, int _option);
}
