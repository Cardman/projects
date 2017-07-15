package aiki.game.fight.actions;
import code.datacheck.CheckedData;
import code.util.annot.RwXml;

@CheckedData
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
