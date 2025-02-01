package code.gui.document;

import aiki.game.fight.util.*;

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
    public int display(AbsBeanRender _rend, AffectedMove _info, int _index) {
        _rend.formatMessageDirCts(_info.getMove());
        _rend.displayActivityOfMoveEnabled(MessagesPkBean.FIGHTER,_info.getActivity(),valueTrue,valueFalse);
        _rend.displayActivityOfMoveNbRound(MessagesPkBean.FIGHTER,_info.getActivity(),other);
        return 3;
    }
}
