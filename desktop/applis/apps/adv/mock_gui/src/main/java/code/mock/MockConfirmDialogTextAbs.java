package code.mock;

import code.gui.AbsGroupFrame;
import code.gui.ConfirmDialogTextAbs;
import code.gui.TextAnswerValue;

public final class MockConfirmDialogTextAbs implements ConfirmDialogTextAbs {

    private final MockTyping<TextAnswerValue> answers;

    public MockConfirmDialogTextAbs(TextAnswerValue[] _s) {
        this.answers = MockTypingUtil.wrap(_s);
    }

    @Override
    public TextAnswerValue input(AbsGroupFrame _frame, String _value, String _message, String _title, String _language) {
        return answers.incr(new TextAnswerValue(0,""));
    }
}
