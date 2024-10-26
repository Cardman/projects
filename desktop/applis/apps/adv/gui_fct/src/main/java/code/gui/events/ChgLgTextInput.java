package code.gui.events;

import code.gui.*;
import code.maths.*;

public final class ChgLgTextInput implements AbsActionListener{
    private final int dir;
    private final AbsTextField textField;

    public ChgLgTextInput(int _d, AbsTextField _t) {
        this.dir = _d;
        this.textField = _t;
    }

    @Override
    public void action() {
        LgInt lg_ = LgInt.newLgInt(textField.getText());
        lg_.addNb(new LgInt(dir));
        textField.setText(lg_.toNumberString());
    }
}
