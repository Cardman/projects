package aiki.beans.endround;
import code.bean.Accessible;
import code.maths.Rate;
import aiki.fight.moves.effects.EffectEndRoundStatusRelation;

public class EffectEndRoundStatusRelationBean extends EffectEndRoundStatusBean {

    @Accessible
    private Rate thievedHpRateTargetToUser;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectEndRoundStatusRelation effect_ = (EffectEndRoundStatusRelation) getEffect();
        thievedHpRateTargetToUser = effect_.getThievedHpRateTargetToUser();
    }
}
