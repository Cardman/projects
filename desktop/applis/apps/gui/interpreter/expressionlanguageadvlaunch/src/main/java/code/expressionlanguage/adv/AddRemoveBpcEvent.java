package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.BreakPointCondition;
import code.gui.AbsCustCheckBox;
import code.gui.events.AbsActionListener;

public final class AddRemoveBpcEvent implements AbsActionListener {
    private final AbsCustCheckBox check;
    private final DependantPointsForm form;
    private final BreakPointCondition bpc;

    public AddRemoveBpcEvent(AbsCustCheckBox _c, DependantPointsForm _f, BreakPointCondition _b) {
        this.check = _c;
        this.form = _f;
        this.bpc = _b;
    }

    @Override
    public void action() {
        if (check.isSelected()) {
            form.getSelected().add(bpc);
        } else {
            form.getSelected().removeObj(bpc);
        }
    }
}
