package aiki.beans.moves.effects;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.comparators.ComparatorTrStringStatistic;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectCounterAttack;
import code.maths.Rate;
import code.util.EnumMap;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;

public class EffectCounterAttackBean extends EffectBean {
    private TreeMap<String,Rate> sufferingDamageTypes;
    private TreeMap<Statistic, Byte> droppedStatDirectMove;
    private Rate sufferingDamageDirectMove;
    private StringList reasonsProtect;
    private StringList reasonsCounter;

    private NatTreeMap<String,String> mapVarsFailCounter;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectCounterAttack effect_ = (EffectCounterAttack) getEffect();
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        TreeMap<String,Rate> sufferingDamageTypes_;
        sufferingDamageTypes_ = new TreeMap<String, Rate>(new ComparatorTrStrings(translatedTypes_));
        for (String type_: effect_.getSufferingDamageTypes().getKeys()) {
            sufferingDamageTypes_.put(type_, effect_.getSufferingDamageTypes().getVal(type_));
        }
        sufferingDamageTypes = sufferingDamageTypes_;
        TreeMap<Statistic, Byte> droppedStatDirectMove_;
        droppedStatDirectMove_ = new TreeMap<Statistic, Byte>(new ComparatorTrStringStatistic(translatedStatistics_));
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
        StringList reasonsProtect_ = CommonBean.getFormattedReasons(data_, getFailProtectReasons(), getLanguage());
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
        reasonsProtect = reasonsProtect_;
        NatTreeMap<String,String> mapVars_ = data_.getDescriptions(effect_.getProtectFail(),getLanguage());
        NatTreeMap<String,String> mapVarsFail_ = new NatTreeMap<String, String>();
        StringList desc_ = new StringList(mapVars_.getKeys());
        desc_.sort();
        for (String k: desc_) {
            mapVarsFail_.put(k, mapVars_.getVal(k));
        }
        StringList reasonsCounter_ = CommonBean.getFormattedReasons(data_, getFailCounterReasons(), getLanguage());
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
        reasonsCounter = reasonsCounter_;
        mapVars_ = data_.getDescriptions(effect_.getCounterFail(),getLanguage());
        desc_ = new StringList(mapVars_.getKeys());
        desc_.sort();
        for (String k: desc_) {
            mapVarsFail_.put(k, mapVars_.getVal(k));
        }
        mapVarsFailCounter = mapVarsFail_;
    }

    public NatTreeMap<String, String> getMapVarsFailCounter() {
        return mapVarsFailCounter;
    }
    public String getTrSufferingDamageTypes(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        String st_ = sufferingDamageTypes.getKey(_index.intValue());
        return translatedTypes_.getVal(st_);
    }
    public String getTrDroppedStatDirectMove(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        Statistic st_ = droppedStatDirectMove.getKey(_index.intValue());
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

    public TreeMap<String,Rate> getSufferingDamageTypes() {
        return sufferingDamageTypes;
    }

    public TreeMap<Statistic,Byte> getDroppedStatDirectMove() {
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