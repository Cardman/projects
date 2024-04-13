package aiki.gui.components.walk.events;

import aiki.facade.FacadeGame;
import aiki.gui.components.listeners.AbsNicknameAutoCompleteListener;
import code.gui.AbsTextField;

public final class WalkNicknameAutoCompleteListener extends AbsNicknameAutoCompleteListener {
    public WalkNicknameAutoCompleteListener(AbsTextField _n, FacadeGame _facade) {
        super(_n, _facade);
    }

    @Override
    public void update() {
        getFacadeGame().validateNickname(getNickname().getText());
    }
}
