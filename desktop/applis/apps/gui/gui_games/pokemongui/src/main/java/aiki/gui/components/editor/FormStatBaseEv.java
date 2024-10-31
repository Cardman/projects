package aiki.gui.components.editor;

import code.gui.*;
import code.gui.initialize.*;

public final class FormStatBaseEv {
    private final AbstractProgramInfos api;
    private final AbsSpinner ev;
    private final AbsSpinner base;
    public FormStatBaseEv(AbstractProgramInfos _a) {
        api = _a;
        AbsCompoFactory c_ = api.getCompoFactory();
        ev = c_.newSpinner(0,0,Integer.MAX_VALUE,1);
        base = c_.newSpinner(1,1,Integer.MAX_VALUE,1);
    }

    public AbsCustComponent row(String _title) {
        AbsCompoFactory c_ = api.getCompoFactory();
        AbsPanel l_ = c_.newLineBox();
        l_.setTitledBorder(_title);
        l_.add(base);
        l_.add(ev);
        return l_;
    }
    public AbsSpinner getBase() {
        return base;
    }

    public AbsSpinner getEv() {
        return ev;
    }
}
