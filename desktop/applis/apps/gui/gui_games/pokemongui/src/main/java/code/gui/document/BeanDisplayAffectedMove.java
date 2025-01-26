package code.gui.document;

import aiki.game.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;

public final class BeanDisplayAffectedMove implements BeanDisplay<AffectedMove> {

    private final String valueTrue;
    private final String valueFalse;
    private final String other;

    public BeanDisplayAffectedMove(String _t, String _f, String _o) {
        this.valueTrue = _t;
        this.valueFalse = _f;
        this.other = _o;
    }

    @Override
    public int display(AbsBeanRender _rend, AbstractProgramInfos _api, AbsPanel _form, AffectedMove _info, int _index, int _count) {
        _rend.formatMessageDir(_api,_form,AbsBeanRender.remainder(_api,_index,_count),_info.getMove());
        _rend.displayActivityOfMoveEnabled(_api,_form,AbsBeanRender.remainder(_api,_index+1,_count),MessagesFightFighter.FIGHTER,_info.getActivity(),valueTrue,valueFalse);
        _rend.displayActivityOfMoveNbRound(_api,_form,AbsBeanRender.remainder(_api,_index+2,_count),MessagesFightFighter.FIGHTER,_info.getActivity(),other);
        return 3;
    }
}
