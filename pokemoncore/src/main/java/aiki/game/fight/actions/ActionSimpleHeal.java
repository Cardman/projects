package aiki.game.fight.actions;
import code.util.annot.RwXml;

@RwXml
public final class ActionSimpleHeal extends ActionHeal {
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
