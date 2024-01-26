package code.mock;

import code.gui.*;

public final class MockMessageDialogAbs implements MessageDialogAbs {
    @Override
    public int input(AbsDialog _frame, String _message, String _title, int _option) {
        return 0;
    }

    @Override
    public int input(AbsCommonFrame _frame, String _message, String _title, int _option) {
        return 0;
    }

    @Override
    public int input(AbsCommonFrame _frame, AbsCustComponent _message, String _title, int _option) {
        return 0;
    }
}
