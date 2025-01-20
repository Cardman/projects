package aiki.beans.effects;

import aiki.beans.CommonBean;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectCombo;
import aiki.fight.moves.effects.EffectEndRound;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.AbsMap;
import code.util.NatStringTreeMap;
import code.util.StringList;

public class EffectComboBean extends CommonBean {
    private ComboDto combos;
    private int index;
    private StringList moves;

    private Rate multEvtRateSecEff;
    private DictionaryComparator<LgInt,Rate> repeatedRoundsLaw;
    private long rankIncrementNbRound;
    private boolean endRound;
    private long endRoundRank;
    private StringList reasonsEndRound;
    private NatStringTreeMap<String> mapVarsFailEndRound;
    private DictionaryComparator<Statistic, Rate> multStatisticFoe;

    @Override
    public void beforeDisplaying() {
        DataBase data_ = getDataBase();
        moves = new StringList();
        for (String m: combos.getKey(index)) {
            moves.add(data_.translateMove(m));
        }
        EffectCombo e_ = combos.getValue(index);
        if (!e_.getEffectEndRound().isEmpty()) {
            endRound = true;
            EffectEndRound effect_ = e_.getEffectEndRound().first();
            endRoundRank = effect_.getEndRoundRank();
            reasonsEndRound = getFormattedReasons(data_, getReasons(effect_.getFailEndRound()), getLanguage());
            mapVarsFailEndRound = getMapVarsFail(data_, effect_.getFailEndRound(), getLanguage());
        } else {
            endRound = false;
            endRoundRank = 0;
            reasonsEndRound = new StringList();
            mapVarsFailEndRound = new NatStringTreeMap<String>();
        }
        if (!e_.getTeamMove().isEmpty()) {
            DictionaryComparator<Statistic, Rate> multStatisticFoe_;
            multStatisticFoe_ = DictionaryComparatorUtil.buildStatisRate(data_,getLanguage());
            multStatisticFoe_.putAllMap(e_.getTeamMove().first().getMultStatisticFoe());
            multStatisticFoe = multStatisticFoe_;
        } else {
            multStatisticFoe = DictionaryComparatorUtil.buildStatisRate(data_,getLanguage());
        }
        multEvtRateSecEff = e_.getMultEvtRateSecEff();
        rankIncrementNbRound = e_.getRankIncrementNbRound();
        DictionaryComparator<LgInt,Rate> repeatedRoundsLaw_;
        repeatedRoundsLaw_ = DictionaryComparatorUtil.buildIntRate();
        for (Rate e: e_.getRepeatedRoundsLaw().eventsDiff()) {
            repeatedRoundsLaw_.put(e.intPart(), e_.getRepeatedRoundsLaw().normalizedRate(e));
        }
        repeatedRoundsLaw = repeatedRoundsLaw_;
    }
    public String getTrStatistic(int _index) {
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translatedStatistics_;
        translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        Statistic stat_ = multStatisticFoe.getKey(_index);
        return translatedStatistics_.getVal(stat_);
    }
    public String clickMove(int _indexOne, int _indexTwo) {
        StringList moves_ = combos.getKey(_indexOne);
        return tryRedirectMv(moves_.get(_indexTwo));
    }

    public StringList getMoves() {
        return moves;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int _index) {
        index = _index;
    }

    public boolean getEndRound() {
        return endRound;
    }

    public long getEndRoundRank() {
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

    public DictionaryComparator<Statistic,Rate> getMultStatisticFoe() {
        return multStatisticFoe;
    }

    public long getRankIncrementNbRound() {
        return rankIncrementNbRound;
    }

    public DictionaryComparator<LgInt,Rate> getRepeatedRoundsLaw() {
        return repeatedRoundsLaw;
    }

    public void setCombos(ComboDto _combos) {
        combos = _combos;
    }
}