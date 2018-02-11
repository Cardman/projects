package aiki.beans.moves.effects;
import aiki.fight.moves.effects.EffectSwitchPointView;
import aiki.fight.moves.effects.enums.PointViewChangementType;

public class EffectSwitchPointViewBean extends EffectBean {

    private PointViewChangementType pointViewChangement;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectSwitchPointView effect_ = (EffectSwitchPointView) getEffect();
        pointViewChangement = effect_.getPointViewChangement();
    }
    public boolean thieveBonus() {
        return pointViewChangement == PointViewChangementType.THIEF_BONUSES;
    }
    public boolean attractDamageMoves() {
        return pointViewChangement == PointViewChangementType.ATTRACT_DAMAGES_MOVES;
    }
    public boolean mirrorAgainstUser() {
        return pointViewChangement == PointViewChangementType.MIRROR_AGAINST_THROWER;
    }
}