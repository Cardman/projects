package code.gui;

public interface MessageDialogAbs {
    int input(AbsDialog _frame, String _message, String _title, int _option);
    int input(AbsCommonFrame _frame, String _message, String _title, int _option);
    int input(AbsCommonFrame _frame, AbsCustComponent _message, String _title, int _option);
}
