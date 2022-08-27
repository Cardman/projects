package aiki.beans.moves.effects;
import aiki.beans.facade.comparators.ComparatorTrStringStatistic;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectCommonStatistics;
import code.util.AbsMap;
import code.util.EnumMap;
import code.util.NatStringTreeMap;
import code.util.TreeMap;

public class EffectCommonStatisticsBean extends EffectBean {
    private TreeMap<Statistic, String> commonValue;
    private NatStringTreeMap< String> mapVarsCommonStatistics;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectCommonStatistics effect_ = (EffectCommonStatistics) getEffect();
        DataBase data_ = (DataBase) getDataBase();
        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        NatStringTreeMap< String> mapVarsCommonStatistics_;
        mapVarsCommonStatistics_ = new NatStringTreeMap< String>();
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
            NatStringTreeMap<String> mapVars_ = data_.getDescriptions(str_,getLanguage());
            mapVarsCommonStatistics_.putAllMap(mapVars_);
        }
        commonValue = commonValue_;
        mapVarsCommonStatistics = mapVarsCommonStatistics_;
    }
    public String getTrStatistic(int _index) {
        Statistic st_ = commonValue.getKey(_index);
        DataBase data_ = (DataBase) getDataBase();
        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(st_);
    }

    public TreeMap<Statistic,String> getCommonValue() {
        return commonValue;
    }

    public NatStringTreeMap<String> getMapVarsCommonStatistics() {
        return mapVarsCommonStatistics;
    }
}