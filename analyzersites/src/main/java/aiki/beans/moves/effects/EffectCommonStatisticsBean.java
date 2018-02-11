package aiki.beans.moves.effects;
import code.bean.Accessible;
import code.util.EnumMap;
import code.util.NatTreeMap;
import code.util.TreeMap;
import aiki.DataBase;
import aiki.comparators.ComparatorTrStringStatistic;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectCommonStatistics;

public class EffectCommonStatisticsBean extends EffectBean {

    @Accessible
    private TreeMap<Statistic, String> commonValue;

    @Accessible
    private NatTreeMap<String, String> mapVarsCommonStatistics;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectCommonStatistics effect_ = (EffectCommonStatistics) getEffect();
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        NatTreeMap<String, String> mapVarsCommonStatistics_;
        mapVarsCommonStatistics_ = new NatTreeMap<String, String>();
        TreeMap<Statistic, String> commonValue_;
        commonValue_ = new TreeMap<Statistic, String>(new ComparatorTrStringStatistic(translatedStatistics_));
//        Map<String,String> loc_ = new Map<>();
//        loc_.put(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        loc_.put(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        for (Statistic s: effect_.getCommonValue().getKeys()) {
            String str_ = effect_.getCommonValue().getVal(s);
            String formula_ = data_.getFormula(str_, getLanguage());
//            formula_ = StringList.replace(formula_, loc_);
//            formula_ = formula_.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//            formula_ = formula_.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
            commonValue_.put(s, formula_);
            NatTreeMap<String,String> mapVars_ = data_.getDescriptions(str_,getLanguage());
            mapVarsCommonStatistics_.putAllTreeMap(mapVars_);
        }
        commonValue = commonValue_;
        mapVarsCommonStatistics = mapVarsCommonStatistics_;
    }

    @Accessible
    private String getTrStatistic(Long _index) {
        Statistic st_ = commonValue.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(st_);
    }
}
