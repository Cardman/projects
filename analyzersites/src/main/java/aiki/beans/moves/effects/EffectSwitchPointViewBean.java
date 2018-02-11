package aiki.beans.moves.effects;
import code.bean.Accessible;
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

    @Accessible
    private boolean thieveBonus() {
        return pointViewChangement == PointViewChangementType.THIEF_BONUSES;
    }

    @Accessible
    private boolean attractDamageMoves() {
        return pointViewChangement == PointViewChangementType.ATTRACT_DAMAGES_MOVES;
    }

    @Accessible
    private boolean mirrorAgainstUser() {
        return pointViewChangement == PointViewChangementType.MIRROR_AGAINST_THROWER;
    }
}
