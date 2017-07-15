package aiki.beans.endround;
import code.bean.Accessible;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.NatCmpTreeMap;
import code.util.NatTreeMap;
import aiki.fight.moves.effects.EffectEndRoundSingleRelation;

public class EffectEndRoundSingleRelationBean extends EffectEndRoundBean {

    @Accessible
    private NatTreeMap<Long, Rate> rateDamageFunctionOfNbRounds;

    @Accessible
    private NatCmpTreeMap<LgInt, Rate> lawForEnablingEffect;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectEndRoundSingleRelation effect_ = (EffectEndRoundSingleRelation) getEffect();
        NatTreeMap<Long, Rate> rateDamageFunctionOfNbRounds_;
        rateDamageFunctionOfNbRounds_ = new NatTreeMap<Long, Rate>();
        for (Long k: effect_.getRateDamageFunctionOfNbRounds().getKeys()) {
            rateDamageFunctionOfNbRounds_.put(k, effect_.getRateDamageFunctionOfNbRounds().getVal(k));
        }
        rateDamageFunctionOfNbRounds = rateDamageFunctionOfNbRounds_;
        LgInt sum_ = effect_.getLawForEnablingEffect().sum();
        NatCmpTreeMap<LgInt, Rate> lawForEnablingEffect_;
        lawForEnablingEffect_ = new NatCmpTreeMap<LgInt, Rate>();
        for (Rate k: effect_.getLawForEnablingEffect().events()) {
            lawForEnablingEffect_.put(k.intPart(), new Rate(effect_.getLawForEnablingEffect().rate(k), sum_));
        }
        lawForEnablingEffect = lawForEnablingEffect_;
    }
}
