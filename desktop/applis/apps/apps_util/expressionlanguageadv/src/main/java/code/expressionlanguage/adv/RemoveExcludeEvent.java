package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.AbsCallContraints;
import code.gui.AbsCommonFrame;
import code.gui.events.AbsActionListener;

public final class RemoveExcludeEvent implements AbsActionListener {
    private final StackConstraintsForm form;
    private final AbsCallContraints elt;
    private final AbsCommonFrame commonFrame;

    public RemoveExcludeEvent(StackConstraintsForm _f, AbsCallContraints _l, AbsCommonFrame _com) {
        this.form = _f;
        this.commonFrame = _com;
        this.elt = _l;
    }

    @Override
    public void action() {
        StackConstraintsForm.remove(form.getMustNotBe(), elt);
        form.actualiseLists(commonFrame);
    }
}
