package code.gui.events;

import code.gui.AbsTextField;
import code.util.core.NumberUtil;

public final class ChgLgInput implements AbsActionListener{
    private final int dir;
    private final AbsTextField textField;

    public ChgLgInput(int _d, AbsTextField _t) {
        this.dir = _d;
        this.textField = _t;
    }

    @Override
    public void action() {
        long lg_ = NumberUtil.parseLongZero(textField.getText());
        textField.setText(Long.toString(lg_+dir));
    }
}
