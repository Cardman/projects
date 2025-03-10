package aiki.beans.fight;

import aiki.beans.*;

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
    public int display(CommonBean _rend, ActivityOfMoveStill _info, int _index) {
        _rend.displayActivityOfMoveEnabled(_info.getActivity(), valueTrue,valueFalse);
        _rend.displayActivityOfMoveNbRound(_info.getActivity(), other);
        return 2;
    }
}
