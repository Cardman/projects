package aiki.game.fight.actions;
import code.datacheck.CheckedData;


@CheckedData
public abstract class AbstractAction {

    public abstract boolean isEmpty();
    public abstract boolean isActionMove();
    public abstract boolean isActionHealMove();
    public abstract boolean isActionSwitch();
}
