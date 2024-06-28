package code.expressionlanguage.adv;

import code.gui.AbsCustCheckBox;
import code.gui.events.AbsActionListener;

public final class AddRemoveBpcFirstEvent implements AbsActionListener {
    private final AbsCustCheckBox check;
    private final DependantPointsForm form;
    private final int bpc;

    public AddRemoveBpcFirstEvent(AbsCustCheckBox _c, DependantPointsForm _f, int _b) {
        this.check = _c;
        this.form = _f;
        this.bpc = _b;
    }

    @Override
    public void action() {
        if (check.isSelected()) {
            form.getSelectedCurrent().add(bpc);
        } else {
            form.getSelectedCurrent().removeObj(bpc);
        }
    }
}
