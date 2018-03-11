package aiki.game.fight.actions;


public final class Action extends AbstractAction {

    @Override
    public boolean isEmpty() {
        return true;
    }
    @Override
    public boolean isActionMove() {
        return false;
    }
    @Override
    public boolean isActionHealMove() {
        return false;
    }
    @Override
    public boolean isActionSwitch() {
        return false;
    }
}
