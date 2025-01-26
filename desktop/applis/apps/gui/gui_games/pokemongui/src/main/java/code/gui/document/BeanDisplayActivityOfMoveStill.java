package code.gui.document;

import aiki.beans.fight.*;
import code.gui.*;
import code.gui.initialize.*;

public final class BeanDisplayActivityOfMoveStill implements BeanDisplay<ActivityOfMoveStill> {

    private final String valueTrue;
    private final String valueFalse;
    private final String other;

    public BeanDisplayActivityOfMoveStill(String _t, String _f, String _o) {
        this.valueTrue = _t;
        this.valueFalse = _f;
        this.other = _o;
    }

    @Override
    public int display(AbsBeanRender _rend, AbstractProgramInfos _api, AbsPanel _form, ActivityOfMoveStill _info, int _index, int _count) {
        _rend.displayActivityOfMoveEnabled(_api,_form,AbsBeanRender.remainder(_api,_index,_count),MessagesFightFighter.FIGHTER,_info.getActivity(),valueTrue,valueFalse);
        _rend.displayActivityOfMoveNbRound(_api,_form,AbsBeanRender.remainder(_api,_index+1,_count),MessagesFightFighter.FIGHTER,_info.getActivity(),other);
        return 2;
    }
}
