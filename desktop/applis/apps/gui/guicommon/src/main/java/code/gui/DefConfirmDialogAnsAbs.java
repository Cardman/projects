package code.gui;

public final class DefConfirmDialogAnsAbs implements ConfirmDialogAnsAbs {
    @Override
    public int input(AbsGroupFrame _frame, String _message, String _title, String _language, int _option) {
        return ConfirmDialog.getAnswer((GroupFrame) _frame,_message,_title, _language,_option);
    }

    @Override
    public int input(AbsDialog _dialog, AbsGroupFrame _frame, String _message, String _title, String _language, int _option) {
        return ConfirmDialog.showMiniDialog(_dialog,_message,_title,_language,_option,((GroupFrame)_frame).getConfirmDialog()).getAnswer();
    }
}
