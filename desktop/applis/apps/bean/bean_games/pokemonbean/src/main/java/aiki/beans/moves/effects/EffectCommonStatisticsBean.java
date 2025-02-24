package aiki.beans.moves.effects;

import aiki.beans.BeanDisplayMap;
import aiki.beans.BeanDisplayString;
import aiki.beans.BeanDisplayTranslatedKey;
import aiki.beans.TranslatedKey;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectCommonStatistics;
import code.scripts.pages.aiki.MessagesDataEffcommonstatistics;
import code.scripts.pages.aiki.MessagesPkBean;
import code.util.NatStringTreeMap;

public class EffectCommonStatisticsBean extends EffectBean {
    private DictionaryComparator<TranslatedKey, String> commonValue;
    private NatStringTreeMap< String> mapVarsCommonStatistics;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectCommonStatistics effect_ = (EffectCommonStatistics) getEffect();
        DataBase data_ = getDataBase();
        NatStringTreeMap< String> mapVarsCommonStatistics_;
        mapVarsCommonStatistics_ = new NatStringTreeMap< String>();
        DictionaryComparator<TranslatedKey, String> commonValue_;
        commonValue_ = DictionaryComparatorUtil.buildStatisString();
//        Map<String,String> loc_ = new Map<>();
//        loc_.put(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        loc_.put(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        for (Statistic s: effect_.getCommonValue().getKeys()) {
            String str_ = effect_.getCommonValue().getVal(s);
            String formula_ = data_.getFormula(str_, getLanguage());
//            formula_ = StringList.replace(formula_, loc_);
//            formula_ = formula_.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//            formula_ = formula_.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
            commonValue_.put(buildSi(getFacade(),s), formula_);
            NatStringTreeMap<String> mapVars_ = data_.getDescriptions(str_,getLanguage());
            mapVarsCommonStatistics_.putAllMap(mapVars_);
        }
        commonValue = commonValue_;
        mapVarsCommonStatistics = mapVarsCommonStatistics_;
    }

    @Override
    public void buildSubEff() {
        new BeanDisplayMap<TranslatedKey,String>(new BeanDisplayTranslatedKey(),new BeanDisplayString()).displayGrid(this,getCommonValue(), MessagesPkBean.EFF_COMMONSTATISTICS, MessagesDataEffcommonstatistics.M_P_41_COMMON,MessagesDataEffcommonstatistics.M_P_41_COMMON_STAT,MessagesDataEffcommonstatistics.M_P_41_COMMON_VALUE);
        mapVarsInit(getMapVarsCommonStatistics());
    }

    public String getTrStatistic(int _index) {
        return commonValue.getKey(_index).getTranslation();
//        Statistic st_ = commonValue.getKey(_index);
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        return translatedStatistics_.getVal(st_);
    }

    public DictionaryComparator<TranslatedKey,String> getCommonValue() {
        return commonValue;
    }

    public NatStringTreeMap<String> getMapVarsCommonStatistics() {
        return mapVarsCommonStatistics;
    }
}