package code.gui.document;

import aiki.beans.*;
import aiki.game.fight.*;
import code.gui.*;

public final class DefBeanChgActivityOfMove extends BeanChgActivityOfMove {
    private final AbsCustCheckBox check;
    private final GeneComponentModelLong spinner;
    public DefBeanChgActivityOfMove(AbsCustCheckBox _c, GeneComponentModelLong _d, boolean _incrCount) {
        super(_incrCount);
        check = _c;
        spinner = _d;
    }

    @Override
    public ActivityOfMove valueActivity() {
        ActivityOfMove ac_ = new ActivityOfMove();
        ac_.setEnabled(check.isSelected());
        ac_.setNbTurn(spinner.valueLong());
        return ac_;
    }

    @Override
    public void valueActivity(ActivityOfMove _v) {
        check.setSelected(_v.isEnabled());
        spinner.valueLong(_v.getNbTurn());
    }

}
