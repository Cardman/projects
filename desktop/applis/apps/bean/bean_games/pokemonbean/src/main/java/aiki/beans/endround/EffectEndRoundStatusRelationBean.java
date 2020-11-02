package aiki.beans.endround;
import aiki.fight.moves.effects.EffectEndRoundStatusRelation;
import code.maths.Rate;

public class EffectEndRoundStatusRelationBean extends EffectEndRoundStatusBean {
    private Rate thievedHpRateTargetToUser;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        beforeDisplayingEffectEndRoundStatus();
        EffectEndRoundStatusRelation effect_ = (EffectEndRoundStatusRelation) getEffect();
        thievedHpRateTargetToUser = effect_.getThievedHpRateTargetToUser();
    }

    public Rate getThievedHpRateTargetToUser() {
        return thievedHpRateTargetToUser;
    }
}