package aiki.beans.effects;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.comparators.ComparatorTrStringStatistic;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectCombo;
import aiki.fight.moves.effects.EffectEndRound;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.EnumMap;
import code.util.NatCmpTreeMap;
import code.util.NatStringTreeMap;
import code.util.StringList;
import code.util.TreeMap;

public class EffectComboBean extends CommonBean {
    private ComboDto combos;
    private long index;
    private StringList moves;

    private EffectCombo effect;
    private Rate multEvtRateSecEff;
    private NatCmpTreeMap<LgInt,Rate> repeatedRoundsLaw;
    private short rankIncrementNbRound;
    private boolean endRound;
    private int endRoundRank;
    private StringList reasonsEndRound;
    private NatStringTreeMap<String> mapVarsFailEndRound;
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
            mapVarsFailEndRound = new NatStringTreeMap<String>();
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
    public String getTrStatistic(Long _index) {
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_;
        translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        Statistic stat_ = multStatisticFoe.getKey(_index.intValue());
        return translatedStatistics_.getVal(stat_);
    }
    public String clickMove(Long _indexOne, Long _indexTwo) {
        StringList moves_ = combos.getKey(_indexOne.intValue());
        getForms().put(MOVE, moves_.get(_indexTwo.intValue()));
        return MOVE;
    }

    public StringList getMoves() {
        return moves;
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(long _index) {
        index = _index;
    }

    public boolean getEndRound() {
        return endRound;
    }

    public int getEndRoundRank() {
        return endRoundRank;
    }

    public StringList getReasonsEndRound() {
        return reasonsEndRound;
    }

    public NatStringTreeMap<String> getMapVarsFailEndRound() {
        return mapVarsFailEndRound;
    }

    public Rate getMultEvtRateSecEff() {
        return multEvtRateSecEff;
    }

    public TreeMap<Statistic,Rate> getMultStatisticFoe() {
        return multStatisticFoe;
    }

    public short getRankIncrementNbRound() {
        return rankIncrementNbRound;
    }

    public NatCmpTreeMap<LgInt,Rate> getRepeatedRoundsLaw() {
        return repeatedRoundsLaw;
    }

    public void setCombos(ComboDto _combos) {
        combos = _combos;
    }
}