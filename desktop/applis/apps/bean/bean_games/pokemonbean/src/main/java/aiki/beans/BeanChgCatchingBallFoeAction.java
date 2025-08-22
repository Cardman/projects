package aiki.beans;

import aiki.db.*;
import aiki.game.fight.*;

public class BeanChgCatchingBallFoeAction implements IntBeanChgCatchingBallFoeAction {
    private CatchingBallFoeAction activity = new CatchingBallFoeAction();

    public BeanChgCatchingBallFoeAction() {
        activity.setCatchingBall(DataBase.EMPTY_STRING);
        activity.setNickname(DataBase.EMPTY_STRING);
    }

    @Override
    public CatchingBallFoeAction valCatch() {
        return activity;
    }

    @Override
    public void valCatch(CatchingBallFoeAction _v) {
        activity = _v;
    }

}
