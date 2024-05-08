package aiki.gui.components.listeners;

import aiki.facade.FacadeGame;
import code.gui.AbsTextField;
import code.gui.events.AbsAutoCompleteListener;

public abstract class AbsNicknameAutoCompleteListener implements AbsAutoCompleteListener {
    private final AbsTextField nickname;
    private final FacadeGame facadeGame;

    protected AbsNicknameAutoCompleteListener(AbsTextField _n, FacadeGame _facade) {
        this.nickname = _n;
        this.facadeGame = _facade;
    }

    @Override
    public void insertUpdate() {
        update();
    }

    @Override
    public void removeUpdate() {
        update();
    }

    @Override
    public void changedUpdate() {
        getNickname().getText();
    }

    public abstract void update();

    public AbsTextField getNickname() {
        return nickname;
    }

    public FacadeGame getFacadeGame() {
        return facadeGame;
    }
}
