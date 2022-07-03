package code.mock;

import code.gui.AbsDialog;
import code.gui.AbsGroupFrame;
import code.gui.ConfirmDialogAnsAbs;

public final class MockConfirmDialogAnsAbs implements ConfirmDialogAnsAbs {

    private final MockTyping<Integer> answers;

    public MockConfirmDialogAnsAbs(int[] _s) {
        this.answers = MockTypingUtil.wrap(_s);
    }

    @Override
    public int input(AbsGroupFrame _frame, String _message, String _title, String _language, int _option) {
        return answers.incr(0);
    }

    @Override
    public int input(AbsDialog _dialog, AbsGroupFrame _frame, String _message, String _title, String _language, int _option) {
        return answers.incr(0);
    }

}
