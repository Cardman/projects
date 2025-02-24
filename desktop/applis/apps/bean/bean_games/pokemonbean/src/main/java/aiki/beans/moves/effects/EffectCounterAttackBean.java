package aiki.beans.moves.effects;

import aiki.beans.*;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectCounterAttack;
import code.maths.Rate;
import code.scripts.pages.aiki.MessagesDataEffcounterattack;
import code.scripts.pages.aiki.MessagesPkBean;
import code.util.*;

public class EffectCounterAttackBean extends EffectBean {
    private DictionaryComparator<TranslatedKey,Rate> sufferingDamageTypes;
    private DictionaryComparator<TranslatedKey, Long> droppedStatDirectMove;
    private Rate sufferingDamageDirectMove;
    private StringList reasonsProtect;
    private StringList reasonsCounter;

    private NatStringTreeMap<String> mapVarsFailCounter;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectCounterAttack effect_ = (EffectCounterAttack) getEffect();
        DataBase data_ = getDataBase();
        DictionaryComparator<TranslatedKey,Rate> sufferingDamageTypes_;
        sufferingDamageTypes_ = DictionaryComparatorUtil.buildTypesRate();
        for (String type_: effect_.getSufferingDamageTypes().getKeys()) {
            sufferingDamageTypes_.put(buildTy(getFacade(),type_), effect_.getSufferingDamageTypes().getVal(type_));
        }
        sufferingDamageTypes = sufferingDamageTypes_;
        DictionaryComparator<TranslatedKey, Long> droppedStatDirectMove_;
        droppedStatDirectMove_ = DictionaryComparatorUtil.buildStatisByte();
        for (Statistic st_: effect_.getDroppedStatDirectMove().getKeys()) {
            droppedStatDirectMove_.put(buildSi(getFacade(),st_), effect_.getDroppedStatDirectMove().getVal(st_));
        }
        droppedStatDirectMove = droppedStatDirectMove_;
        sufferingDamageDirectMove = effect_.getSufferingDamageDirectMove();
//        Map<String,String> locHtml_ = new Map<>();
//        locHtml_.put(EAMP, E_AMP);
//        locHtml_.put(EGT, E_GT);
//        locHtml_.put(ELT, E_LT);
//        locHtml_.put(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        locHtml_.put(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        //        reasonsProtect_ = new StringList();
//        for (String f: getFailProtectReasons()) {
//            String formula_ = data_.getFormula(f, getLanguage());
////            formula_ = StringList.replace(formula_, locHtml_);
////            formula_ = formula_.replace(EAMP, E_AMP);
////            formula_ = formula_.replace(EGT, E_GT);
////            formula_ = formula_.replace(ELT, E_LT);
////            formula_ = formula_.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
////            formula_ = formula_.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
//            reasonsProtect_.add(formula_);
//        }
        reasonsProtect = CommonBean.getFormattedReasons(data_, effect_.getProtectFail(), getLanguage());
        NatStringTreeMap<String> mapVars_ = data_.getDescriptions(effect_.getProtectFail(),getLanguage());
        NatStringTreeMap<String> mapVarsFail_ = new NatStringTreeMap< String>();
        StringList desc_ = new StringList(mapVars_.getKeys());
        desc_.sort();
        for (String k: desc_) {
            mapVarsFail_.put(k, mapVars_.getVal(k));
        }
        //        reasonsCounter_ = new StringList();
//        for (String f: getFailCounterReasons()) {
//            String formula_ = data_.getFormula(f, getLanguage());
////            formula_ = StringList.replace(formula_, locHtml_);
////            formula_ = formula_.replace(EAMP, E_AMP);
////            formula_ = formula_.replace(EGT, E_GT);
////            formula_ = formula_.replace(ELT, E_LT);
////            formula_ = formula_.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
////            formula_ = formula_.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
//            reasonsCounter_.add(formula_);
//        }
        reasonsCounter = CommonBean.getFormattedReasons(data_, effect_.getCounterFail(), getLanguage());
        mapVars_ = data_.getDescriptions(effect_.getCounterFail(),getLanguage());
        desc_ = new StringList(mapVars_.getKeys());
        desc_.sort();
        for (String k: desc_) {
            mapVarsFail_.put(k, mapVars_.getVal(k));
        }
        mapVarsFailCounter = mapVarsFail_;
    }

    @Override
    public void buildSubEffPre() {
        formatMessage(MessagesPkBean.EFF_COUNTERATTACK,MessagesDataEffcounterattack.M_P_44_EFFECT);
    }

    @Override
    public void buildSubEff() {
        new BeanDisplayMap<TranslatedKey,Rate>(new BeanDisplayTranslatedKey(),new BeanDisplayRate()).displayGridParam(this, getSufferingDamageTypes(),new String[]{getMove()}, MessagesPkBean.EFF_COUNTERATTACK, MessagesDataEffcounterattack.M_P_44_SUFFERING_TYPES,MessagesDataEffcounterattack.M_P_44_SUFFERING_TYPES_T,MessagesDataEffcounterattack.M_P_44_SUFFERING_TYPES_HP);
        new BeanDisplayMap<TranslatedKey,Long>(new BeanDisplayTranslatedKey(),new BeanDisplayLong()).displayGridParam(this, getDroppedStatDirectMove(),new String[]{getMove()},MessagesPkBean.EFF_COUNTERATTACK,MessagesDataEffcounterattack.M_P_44_DROPPED_STAT,MessagesDataEffcounterattack.M_P_44_DROPPED_STAT_S,MessagesDataEffcounterattack.M_P_44_DROPPED_STAT_V);
//        if (!_sub.getSufferingDamageDirectMove().isZero()) {
//            formatMessage(MessagesPkBean.EFF_COUNTERATTACK,MessagesDataEffcounterattack.M_P_44_SUFFERING_DIRECT, _sub.getMove(), _sub.getSufferingDamageDirectMove().toNumberString());
//        }
        displayIntDef(getSufferingDamageDirectMove(), MessagesPkBean.EFF_COUNTERATTACK, MessagesDataEffcounterattack.M_P_44_SUFFERING_DIRECT);
        displayStringList(getReasonsProtect(), MessagesPkBean.EFF_COUNTERATTACK, MessagesDataEffcounterattack.M_P_44_FAIL_PROTECT, getMove());
        displayStringList(getReasonsCounter(), MessagesPkBean.EFF_COUNTERATTACK, MessagesDataEffcounterattack.M_P_44_COUNTER_PROTECT, getMove());
        mapVarsInit(getMapVarsFailCounter());
    }

    public NatStringTreeMap< String> getMapVarsFailCounter() {
        return mapVarsFailCounter;
    }
    public String getTrSufferingDamageTypes(int _index) {
        return sufferingDamageTypes.getKey(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
//        String st_ = sufferingDamageTypes.getKey(_index);
//        return translatedTypes_.getVal(st_);
    }
    public String getTrDroppedStatDirectMove(int _index) {
        return droppedStatDirectMove.getKey(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        Statistic st_ = droppedStatDirectMove.getKey(_index);
//        return translatedStatistics_.getVal(st_);
    }

    public DictionaryComparator<TranslatedKey,Rate> getSufferingDamageTypes() {
        return sufferingDamageTypes;
    }

    public DictionaryComparator<TranslatedKey,Long> getDroppedStatDirectMove() {
        return droppedStatDirectMove;
    }

    public Rate getSufferingDamageDirectMove() {
        return sufferingDamageDirectMove;
    }

    public StringList getReasonsProtect() {
        return reasonsProtect;
    }

    public StringList getReasonsCounter() {
        return reasonsCounter;
    }
}