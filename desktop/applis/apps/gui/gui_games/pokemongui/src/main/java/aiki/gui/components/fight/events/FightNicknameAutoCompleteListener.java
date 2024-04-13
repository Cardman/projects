package aiki.gui.components.fight.events;

import aiki.facade.FacadeGame;
import aiki.game.fight.CatchingBallFoeAction;
import aiki.game.fight.FighterPosition;
import aiki.gui.components.fight.FighterPanel;
import aiki.gui.components.listeners.AbsNicknameAutoCompleteListener;
import code.gui.AbsTextField;

public final class FightNicknameAutoCompleteListener extends AbsNicknameAutoCompleteListener {
    private final FighterPanel fighterCaughtPanel;

    public FightNicknameAutoCompleteListener(AbsTextField _n, FighterPanel _f, FacadeGame _facade) {
        super(_n, _facade);
        this.fighterCaughtPanel = _f;
    }

    @Override
    public void update() {
        int sel_ = fighterCaughtPanel.getSelectedIndex();
        if (sel_ < 0) {
            return;
        }
        FighterPosition fp_ = getFacadeGame().getSingleFoeToBeCaught(sel_);
        CatchingBallFoeAction cat_ = getFacadeGame().getFight().getCatchingBalls().get(fp_.getFirstPosit());
        cat_.setNickname(getNickname().getText());
    }
}
