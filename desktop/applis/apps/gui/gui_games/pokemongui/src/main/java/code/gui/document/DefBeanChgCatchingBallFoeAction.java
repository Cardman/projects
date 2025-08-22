package code.gui.document;

import aiki.beans.*;
import aiki.game.fight.*;
import aiki.gui.components.editor.*;
import code.gui.*;

public final class DefBeanChgCatchingBallFoeAction extends BeanChgCatchingBallFoeAction {

    private final GeneComponentModelElt<String> catchingBall;
    private final GeneComponentModelElt<Integer> player;
    private final GeneComponentModelText nickname;
    private final AbsCustCheckBox caught;
    private final AbsCustCheckBox team;

    public DefBeanChgCatchingBallFoeAction(GeneComponentModelElt<String> _c, GeneComponentModelText _n, GeneComponentModelElt<Integer> _p, AbsCustCheckBox _g, AbsCustCheckBox _t) {
        this.catchingBall = _c;
        this.player = _p;
        this.nickname = _n;
        this.caught = _g;
        this.team = _t;
    }

    @Override
    public CatchingBallFoeAction valCatch() {
        CatchingBallFoeAction act_ = new CatchingBallFoeAction();
        act_.setCatchingBall(catchingBall.tryRet());
        act_.setPlayer(player.tryRet());
        act_.setNickname(nickname.valueString());
        act_.setCaught(caught.isSelected());
        act_.setTeam(team.isSelected());
        return act_;
    }

    @Override
    public void valCatch(CatchingBallFoeAction _v) {
        catchingBall.setupValue(_v.getCatchingBall());
        player.setupValue(_v.getPlayer());
        nickname.valueString(_v.getNickname());
        caught.setSelected(_v.isCaught());
        team.setSelected(_v.isTeam());
    }

}
