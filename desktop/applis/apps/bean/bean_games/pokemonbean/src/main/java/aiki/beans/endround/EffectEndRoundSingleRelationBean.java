package aiki.beans.endround;

import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.fight.moves.effects.EffectEndRoundSingleRelation;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.LongTreeMap;

public class EffectEndRoundSingleRelationBean extends EffectEndRoundBean {
    private LongTreeMap< Rate> rateDamageFunctionOfNbRounds;
    private DictionaryComparator<LgInt, Rate> lawForEnablingEffect;

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
        DictionaryComparator<LgInt, Rate> lawForEnablingEffect_;
        lawForEnablingEffect_ = DictionaryComparatorUtil.buildIntRate();
        for (Rate k: effect_.getLawForEnablingEffect().eventsDiff()) {
            lawForEnablingEffect_.put(k.intPart(), effect_.getLawForEnablingEffect().normalizedRate(k));
        }
        lawForEnablingEffect = lawForEnablingEffect_;
    }

    public LongTreeMap<Rate> getRateDamageFunctionOfNbRounds() {
        return rateDamageFunctionOfNbRounds;
    }

    public DictionaryComparator<LgInt,Rate> getLawForEnablingEffect() {
        return lawForEnablingEffect;
    }
}