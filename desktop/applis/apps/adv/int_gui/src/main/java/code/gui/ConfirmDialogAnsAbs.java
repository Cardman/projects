package code.gui;

public interface ConfirmDialogAnsAbs {
    int input(AbsCommonFrame _frame, String _message, String _title, int _option);
    int input(AbsDialog _dialog, AbsCommonFrame _frame, String _message, String _title, int _option);
}
