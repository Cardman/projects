package aiki.beans.endround;

import aiki.beans.*;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.fight.moves.effects.EffectEndRoundSingleRelation;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.LongTreeMap;
import code.scripts.pages.aiki.*;

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

    @Override
    public void buildSub() {
        super.buildSub();
        formatMessage(MessagesPkBean.ENDROUND_SINGLERELATION,MessagesDataEndroundSinglerelation.M_P_10_EFFECT);
        new BeanDisplayMap<Long,Rate>(new BeanDisplayLong(),new BeanDisplayRate()).displayGrid(this,rateDamageFunctionOfNbRounds,MessagesPkBean.ENDROUND_SINGLERELATION,MessagesDataEndroundSinglerelation.M_P_10_SUFFERED,MessagesDataEndroundSinglerelation.M_P_10_SUFFERED_RD,MessagesDataEndroundSinglerelation.M_P_10_SUFFERED_RATE);
        new BeanDisplayMap<LgInt,Rate>(new BeanDisplayLgInt(),new BeanDisplayRate()).displayGrid(this,lawForEnablingEffect,MessagesPkBean.ENDROUND_SINGLERELATION,MessagesDataEndroundSinglerelation.M_P_10_LAW,MessagesDataEndroundSinglerelation.M_P_10_LAW_RD,MessagesDataEndroundSinglerelation.M_P_10_LAW_VALUE);
    }

    public LongTreeMap<Rate> getRateDamageFunctionOfNbRounds() {
        return rateDamageFunctionOfNbRounds;
    }

    public DictionaryComparator<LgInt,Rate> getLawForEnablingEffect() {
        return lawForEnablingEffect;
    }
}