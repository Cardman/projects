package aiki.beans;

import aiki.game.fight.*;

public class BeanChgActivityOfMove implements IntBeanChgActivityOfMove {

    private boolean enabled;
    private long nbTurn;
    private final boolean incrementCount;
    public BeanChgActivityOfMove(boolean _incrCount) {
        incrementCount = _incrCount;
    }
    @Override
    public ActivityOfMove genericValue() {
        return valueActivity();
    }

    @Override
    public ActivityOfMove valueActivity() {
        ActivityOfMove ac_ = new ActivityOfMove(incrementCount);
        ac_.setEnabled(enabled);
        ac_.setNbTurn(nbTurn);
        return ac_;
    }

    @Override
    public void valueActivity(ActivityOfMove _v) {
        enabled = _v.isEnabled();
        nbTurn = _v.getNbTurn();
    }
}
