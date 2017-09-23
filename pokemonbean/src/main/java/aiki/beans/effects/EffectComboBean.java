package aiki.beans.effects;
import code.bean.Accessible;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.EnumMap;
import code.util.NatCmpTreeMap;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.TreeMap;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.comparators.ComparatorTrStringStatistic;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectCombo;
import aiki.fight.moves.effects.EffectEndRound;

public class EffectComboBean extends CommonBean {

    @Accessible
    private TreeMap<StringList, EffectCombo> combos;

    @Accessible
    private long index;

    @Accessible
    private StringList moves;

    private EffectCombo effect;

    @Accessible
    private Rate multEvtRateSecEff;

    @Accessible
    private NatCmpTreeMap<LgInt,Rate> repeatedRoundsLaw;

    @Accessible
    private short rankIncrementNbRound;

    @Accessible
    private boolean endRound;

    @Accessible
    private int endRoundRank;

    @Accessible
    private StringList reasonsEndRound;

    @Accessible
    private NatTreeMap<String,String> mapVarsFailEndRound;

    @Accessible
    private TreeMap<Statistic, Rate> multStatisticFoe;

    @Override
    public void beforeDisplaying() {
        DataBase data_ = (DataBase) getDataBase();
        moves = new StringList();
        for (String m: combos.getKey((int) index)) {
            moves.add(data_.translateMove(m));
        }
        effect = combos.getValue((int) index);
        if (!effect.getEffectEndRound().isEmpty()) {
            endRound = true;
            EffectEndRound effect_ = effect.getEffectEndRound().first();
            endRoundRank = effect_.getEndRoundRank();
            reasonsEndRound = getFormattedReasons(data_, getReasons(effect_.getFailEndRound()), getLanguage());
            mapVarsFailEndRound = getMapVarsFail(data_, effect_.getFailEndRound(), getLanguage());
        } else {
            endRound = false;
            endRoundRank = 0;
            reasonsEndRound = new StringList();
            mapVarsFailEndRound = new NatTreeMap<String,String>();
        }
        EnumMap<Statistic,String> translatedStatistics_;
        translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        if (!effect.getTeamMove().isEmpty()) {
            TreeMap<Statistic, Rate> multStatisticFoe_;
            multStatisticFoe_ = new TreeMap<Statistic, Rate>(new ComparatorTrStringStatistic(translatedStatistics_));
            multStatisticFoe_.putAllMap(effect.getTeamMove().first().getMultStatisticFoe());
            multStatisticFoe = multStatisticFoe_;
        } else {
            multStatisticFoe = new TreeMap<Statistic, Rate>(new ComparatorTrStringStatistic(translatedStatistics_));
        }
        multEvtRateSecEff = effect.getMultEvtRateSecEff();
        rankIncrementNbRound = effect.getRankIncrementNbRound();
        NatCmpTreeMap<LgInt,Rate> repeatedRoundsLaw_;
        repeatedRoundsLaw_ = new NatCmpTreeMap<LgInt, Rate>();
        for (Rate e: effect.getRepeatedRoundsLaw().events()) {
            repeatedRoundsLaw_.put(e.intPart(), effect.getRepeatedRoundsLaw().normalizedRate(e));
        }
        repeatedRoundsLaw = repeatedRoundsLaw_;
    }

    @Accessible
    private String getTrStatistic(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_;
        translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        Statistic stat_ = multStatisticFoe.getKey(_index.intValue());
        return translatedStatistics_.getVal(stat_);
    }

    @Accessible
    private String clickMove(Long _indexOne, Long _indexTwo) {
        StringList moves_ = combos.getKey(_indexOne.intValue());
        getForms().put(MOVE, moves_.get(_indexTwo.intValue()));
        return MOVE;
    }
}
