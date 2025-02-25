package aiki.beans;

import aiki.game.fight.*;

public final class BeanDisplayActivityOfMove implements BeanDisplay<ActivityOfMove> {

    private final String valueTrue;
    private final String valueFalse;
    private final String other;

    public BeanDisplayActivityOfMove(String _t, String _f, String _o) {
        this.valueTrue = _t;
        this.valueFalse = _f;
        this.other = _o;
    }
    @Override
    public int display(CommonBean _rend, ActivityOfMove _info, int _index) {
        _rend.displayActivityOfMoveEnabled(_info, valueTrue,valueFalse);
        _rend.displayActivityOfMoveNbRound(_info, other);
        return 2;
    }

}
