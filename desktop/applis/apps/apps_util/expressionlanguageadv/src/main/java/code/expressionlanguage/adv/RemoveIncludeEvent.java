package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.AbsCallContraints;
import code.gui.AbsCommonFrame;
import code.gui.events.AbsActionListener;

public final class RemoveIncludeEvent implements AbsActionListener {
    private final GuiStackForm form;
    private final AbsCallContraints elt;
    private final AbsCommonFrame commonFrame;

    public RemoveIncludeEvent(GuiStackForm _f, AbsCallContraints _l, AbsCommonFrame _com) {
        this.form = _f;
        this.commonFrame = _com;
        this.elt = _l;
    }

    @Override
    public void action() {
        GuiStackForm.remove(form.getMustBe(), elt);
        form.actualiseLists(commonFrame);
    }
}
