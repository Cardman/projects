package aiki.beans.moves.effects;

import aiki.beans.CommonBean;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectCounterAttack;
import code.maths.Rate;
import code.util.AbsMap;
import code.util.NatStringTreeMap;
import code.util.StringList;
import code.util.StringMap;

public class EffectCounterAttackBean extends EffectBean {
    private DictionaryComparator<String,Rate> sufferingDamageTypes;
    private DictionaryComparator<Statistic, Long> droppedStatDirectMove;
    private Rate sufferingDamageDirectMove;
    private StringList reasonsProtect;
    private StringList reasonsCounter;

    private NatStringTreeMap<String> mapVarsFailCounter;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectCounterAttack effect_ = (EffectCounterAttack) getEffect();
        DataBase data_ = getDataBase();
        DictionaryComparator<String,Rate> sufferingDamageTypes_;
        sufferingDamageTypes_ = DictionaryComparatorUtil.buildTypesRate(data_,getLanguage());
        for (String type_: effect_.getSufferingDamageTypes().getKeys()) {
            sufferingDamageTypes_.put(type_, effect_.getSufferingDamageTypes().getVal(type_));
        }
        sufferingDamageTypes = sufferingDamageTypes_;
        DictionaryComparator<Statistic, Long> droppedStatDirectMove_;
        droppedStatDirectMove_ = DictionaryComparatorUtil.buildStatisByte(data_,getLanguage());
        for (Statistic st_: effect_.getDroppedStatDirectMove().getKeys()) {
            droppedStatDirectMove_.put(st_, effect_.getDroppedStatDirectMove().getVal(st_));
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
        reasonsProtect = CommonBean.getFormattedReasons(data_, getFailProtectReasons(), getLanguage());
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
        reasonsCounter = CommonBean.getFormattedReasons(data_, getFailCounterReasons(), getLanguage());
        mapVars_ = data_.getDescriptions(effect_.getCounterFail(),getLanguage());
        desc_ = new StringList(mapVars_.getKeys());
        desc_.sort();
        for (String k: desc_) {
            mapVarsFail_.put(k, mapVars_.getVal(k));
        }
        mapVarsFailCounter = mapVarsFail_;
    }

    public NatStringTreeMap< String> getMapVarsFailCounter() {
        return mapVarsFailCounter;
    }
    public String getTrSufferingDamageTypes(int _index) {
        DataBase data_ = getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        String st_ = sufferingDamageTypes.getKey(_index);
        return translatedTypes_.getVal(st_);
    }
    public String getTrDroppedStatDirectMove(int _index) {
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        Statistic st_ = droppedStatDirectMove.getKey(_index);
        return translatedStatistics_.getVal(st_);
    }

    private StringList getFailProtectReasons() {
        EffectCounterAttack effect_ = (EffectCounterAttack) getEffect();
        return getReasons(effect_.getProtectFail());
    }

    private StringList getFailCounterReasons() {
        EffectCounterAttack effect_ = (EffectCounterAttack) getEffect();
        return getReasons(effect_.getCounterFail());
    }

    public DictionaryComparator<String,Rate> getSufferingDamageTypes() {
        return sufferingDamageTypes;
    }

    public DictionaryComparator<Statistic,Long> getDroppedStatDirectMove() {
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