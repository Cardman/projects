package aiki.gui.components.editor;

import code.gui.*;
import code.gui.initialize.*;
import code.scripts.pages.aiki.*;

public final class FormStatBaseEv {
    private final AbstractProgramInfos api;
    private final AbsSpinner ev;
    private final AbsSpinner base;
    private AbsPanel group;
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
        l_.add(SubscribedTranslationList.line(api,MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_STATISTICS_VALUE,base));
        l_.add(SubscribedTranslationList.line(api,MessagesPkBean.PK_DATA,MessagesDataPokemonData.M_P_72_STATISTICS_EV,ev));
        group = l_;
        return l_;
    }

    public AbsPanel getGroup() {
        return group;
    }

    public AbsSpinner getBase() {
        return base;
    }

    public AbsSpinner getEv() {
        return ev;
    }
}
