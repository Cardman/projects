package aiki.beans.effects;

import aiki.beans.*;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectCombo;
import aiki.fight.moves.effects.EffectEndRound;
import code.maths.LgInt;
import code.maths.Rate;
import code.scripts.pages.aiki.*;
import code.util.*;
import code.util.core.StringUtil;

public final class EffectComboBean extends CommonBean {
    private ComboDto combos;
    private int index;
    private CustList<TranslatedKey> moves;

    private Rate multEvtRateSecEff;
    private DictionaryComparator<LgInt,Rate> repeatedRoundsLaw;
    private long rankIncrementNbRound;
    private boolean endRound;
    private long endRoundRank;
    private StringList reasonsEndRound;
    private NatStringTreeMap<String> mapVarsFailEndRound;
    private DictionaryComparator<TranslatedKey, Rate> multStatisticFoe;

    @Override
    public void beforeDisplaying() {
        DataBase data_ = getDataBase();
        moves = combos.getKey(index);
        EffectCombo e_ = combos.getValue(getIndex());
        if (!e_.getEffectEndRound().isEmpty()) {
            endRound = true;
            EffectEndRound effect_ = e_.getEffectEndRound().first();
            endRoundRank = effect_.getEndRoundRank();
            reasonsEndRound = getFormattedReasons(data_, effect_.getFailEndRound(), getLanguage());
            mapVarsFailEndRound = getMapVarsFail(data_, effect_.getFailEndRound(), getLanguage());
        } else {
            endRound = false;
            endRoundRank = 0;
            reasonsEndRound = new StringList();
            mapVarsFailEndRound = new NatStringTreeMap<String>();
        }
        if (!e_.getTeamMove().isEmpty()) {
            DictionaryComparator<TranslatedKey, Rate> multStatisticFoe_;
            multStatisticFoe_ = DictionaryComparatorUtil.buildStatisRate();
            for (EntryCust<Statistic, Rate> e: e_.getTeamMove().first().getMultStatisticFoe().entryList()) {
                multStatisticFoe_.put(buildSi(getFacade(),e.getKey()),e.getValue());
            }
//            multStatisticFoe_.putAllMap(e_.getTeamMove().first().getMultStatisticFoe());
            multStatisticFoe = multStatisticFoe_;
        } else {
            multStatisticFoe = DictionaryComparatorUtil.buildStatisRate();
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
    public void buildSub() {
        getBuilder().setRefLk(StringUtil.join(WithFilterBean.keys(moves),DataBase.SEPARATOR_MOVES));
        formatMessage(MessagesPkBean.COMBO,MessagesDataCombo.M_P_2_EFFECT);
        getBuilder().setRefLk(DataBase.EMPTY_STRING);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,moves);
        if (endRound) {
            formatMessage(MessagesPkBean.EFF_ENDROUND,MessagesDataEffendround.M_P_47_RANK,Long.toString(endRoundRank));
            formatMessageAnc(new BeanAnchorCstEvent(CommonBean.REN_ADD_WEB_HTML_ENDROUND_ENDROUND_HTML),MessagesPkBean.COMBO,MessagesDataCombo.M_P_2_ENDROUND);
            displayStringList(reasonsEndRound,MessagesPkBean.EFF_ENDROUND,MessagesDataEffendround.M_P_47_REASONS);
            mapVarsInit(mapVarsFailEndRound);
        }
        displayIntDef(multEvtRateSecEff,MessagesPkBean.COMBO,MessagesDataCombo.M_P_2_RATE_SEC_EFF);
        new BeanDisplayMap<TranslatedKey,Rate>(new BeanDisplayTranslatedKey(),new BeanDisplayRate()).displayGrid(this,multStatisticFoe,MessagesPkBean.COMBO,MessagesDataCombo.M_P_2_MULT_STAT_FOE,MessagesDataCombo.M_P_2_STATISTIC,MessagesDataCombo.M_P_2_RATE);
        formatMessage(MessagesPkBean.COMBO,MessagesDataCombo.M_P_2_RANK_INCREMENT_NB_ROUND,Long.toString(rankIncrementNbRound));
        new BeanDisplayMap<LgInt,Rate>(new BeanDisplayLgInt(), new BeanDisplayRate()).displayGrid(this,repeatedRoundsLaw,MessagesPkBean.COMBO,MessagesDataCombo.M_P_2_LAW_REPEAT,MessagesDataCombo.M_P_2_LAW_REPEAT_KEY,MessagesDataCombo.M_P_2_LAW_REPEAT_VALUE);
    }
    public String getTrStatistic(int _index) {
        return multStatisticFoe.getKey(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translatedStatistics_;
//        translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        Statistic stat_ = multStatisticFoe.getKey(_index);
//        return translatedStatistics_.getVal(stat_);
    }
    public String clickMove(int _indexTwo) {
        return tryRedirect(moves.get(_indexTwo));
    }

    public CustList<TranslatedKey> getMoves() {
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

    public DictionaryComparator<TranslatedKey,Rate> getMultStatisticFoe() {
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