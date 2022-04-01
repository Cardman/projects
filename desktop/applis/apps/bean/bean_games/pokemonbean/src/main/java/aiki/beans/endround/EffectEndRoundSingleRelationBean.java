package aiki.beans.endround;

import aiki.fight.moves.effects.EffectEndRoundSingleRelation;
import code.maths.ComparatorLgInt;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.LongTreeMap;
import code.util.TreeMap;

public class EffectEndRoundSingleRelationBean extends EffectEndRoundBean {
    private LongTreeMap< Rate> rateDamageFunctionOfNbRounds;
    private TreeMap<LgInt, Rate> lawForEnablingEffect;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectEndRoundSingleRelation effect_ = (EffectEndRoundSingleRelation) getEffect();
        LongTreeMap< Rate> rateDamageFunctionOfNbRounds_;
        rateDamageFunctionOfNbRounds_ = new LongTreeMap< Rate>();
        for (Long k: effect_.getRateDamageFunctionOfNbRounds().getKeys()) {
            rateDamageFunctionOfNbRounds_.put(k, effect_.getRateDamageFunctionOfNbRounds().getVal(k));
        }
        rateDamageFunctionOfNbRounds = rateDamageFunctionOfNbRounds_;
        TreeMap<LgInt, Rate> lawForEnablingEffect_;
        lawForEnablingEffect_ = new TreeMap<LgInt, Rate>(new ComparatorLgInt());
        for (Rate k: effect_.getLawForEnablingEffect().events()) {
            lawForEnablingEffect_.put(k.intPart(), effect_.getLawForEnablingEffect().normalizedRate(k));
        }
        lawForEnablingEffect = lawForEnablingEffect_;
    }

    public LongTreeMap<Rate> getRateDamageFunctionOfNbRounds() {
        return rateDamageFunctionOfNbRounds;
    }

    public TreeMap<LgInt,Rate> getLawForEnablingEffect() {
        return lawForEnablingEffect;
    }
}