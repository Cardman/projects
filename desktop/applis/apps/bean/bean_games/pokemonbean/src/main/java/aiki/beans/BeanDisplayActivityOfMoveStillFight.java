package aiki.beans;

import aiki.beans.fight.*;

public final class BeanDisplayActivityOfMoveStillFight implements BeanDisplay<ActivityOfMoveStill> {

    private final String valueTrue;
    private final String valueFalse;
    private final String other;

    public BeanDisplayActivityOfMoveStillFight(String _t, String _f, String _o) {
        this.valueTrue = _t;
        this.valueFalse = _f;
        this.other = _o;
    }
    @Override
    public int display(CommonBean _rend, ActivityOfMoveStill _info, int _index) {
        if (_info.isStill()) {
            _rend.displayActivityOfMoveEnabled(_info.getActivity(), valueTrue,valueFalse);
        } else {
            _rend.formatMessageDirCts(other);
        }
        _rend.displayActivityOfMoveEnabled(_info.getActivity(), valueTrue,valueFalse);
        _rend.displayActivityOfMoveNbRound(_info.getActivity(), other);
        return 3;
    }

}
