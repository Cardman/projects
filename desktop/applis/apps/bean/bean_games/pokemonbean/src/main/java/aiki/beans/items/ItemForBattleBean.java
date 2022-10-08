package aiki.beans.items;

import aiki.beans.facade.comparators.ComparatorStatisticPokemon;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.effects.EffectWhileSendingWithStatistic;
import aiki.fight.enums.Statistic;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectEndRound;
import aiki.fight.moves.effects.EffectEndRoundSingleRelation;
import aiki.fight.util.StatisticPokemon;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloBoolean;
import code.util.*;

public class ItemForBattleBean extends ItemBean {
    static final String EFFECT_SEND_BEAN="web/html/sending/effsending.html";
    private StringList typesPk;
    private StringList typesPkAbilities;
    private boolean cancelImmuType;
    private boolean againstEvo;
    private boolean attackLast;
    private boolean boostExp;
    private StringList immuStatus;
    private boolean immuLowStatis;
    private DictionaryComparator<String, Short> increasingMaxNbRoundTrap;
    private boolean attacksSoon;
    private boolean repellingWildPk;
    private StringList synchroStatus;
    private DictionaryComparator<String, String> failStatus;
    private Rate protectAgainstKo;
    private Rate protectAgainstKoIfFullHp;
    private Rate drainedHpByDamageRate;
    private DictionaryComparator<Statistic, Short> winEvFight;

    private MonteCarloBoolean lawForAttackFirst;
    private Rate multTrappingDamage;
    private Rate multWinningMoney;
    private Rate multWinningHappiness;
    private Rate multWinningEv;
    private Rate multWinningExp;
    private String multPower;
    private String multDamage;
    private Rate multDrainedHp;
    private Rate damageRecoil;
    private DictionaryComparator<Statistic, Byte> multStatRank;
    private TreeMap<StatisticPokemon, Byte> multStatPokemonRank;
    private DictionaryComparator<Statistic,String> multStat;
    private DictionaryComparator<String, Short> increasingMaxNbRoundGlobalMove;
    private DictionaryComparator<String, Short> increasingMaxNbRoundTeamMove;
    private StringList immuMoves;
    private StringList hatching;
    private StringList immuTypes;
    private StringList immuWeather;
    private DictionaryComparator<Statistic, Byte> boostStatisSuperEff;
    private DictionaryComparator<String, DictionaryComparator<Statistic, Byte>> boostStatisTypes;
    private boolean endRound;
    private int endRoundRank;
    private StringList reasonsEndRound;
    private NatStringTreeMap<String> mapVarsFailEndRound;
    private boolean sending;
    private NatStringTreeMap<String> mapVars;

    @Override
    public void beforeDisplaying() {
        beforeDisplayingItem();
        DataBase data_ = getDataBase();
        ItemForBattle item_ = (ItemForBattle)getItem();
        if (!item_.getEffectEndRound().isEmpty()) {
            endRound = true;
            EffectEndRound effect_ = item_.getEffectEndRound().first();
            endRoundRank = effect_.getEndRoundRank();
            reasonsEndRound = getFormattedReasons(data_, getReasons(effect_.getFailEndRound()), getLanguage());
            mapVarsFailEndRound = getMapVarsFail(data_, effect_.getFailEndRound(), getLanguage());
        } else {
            endRound = false;
            endRoundRank = 0;
            reasonsEndRound = new StringList();
            mapVarsFailEndRound = new NatStringTreeMap<String>();
        }
        sending = !item_.getEffectSending().isEmpty();
        cancelImmuType = item_.getCancelImmuType();
        againstEvo = item_.getAgainstEvo();
        attackLast = item_.getAttackLast();
        boostExp = item_.getBoostExp();
        immuLowStatis = item_.getImmuLowStatis();
        attacksSoon = item_.getAttacksSoon();
        protectAgainstKo = item_.getProtectAgainstKo();
        protectAgainstKoIfFullHp = item_.getProtectAgainstKoIfFullHp();
        drainedHpByDamageRate = item_.getDrainedHpByDamageRate();
        multTrappingDamage = item_.getMultTrappingDamage();
        multWinningMoney = Rate.zero();
        multWinningHappiness = item_.getMultWinningHappiness();
        multWinningEv = item_.getMultWinningEv();
        multWinningExp = item_.getMultWinningExp();
        multDrainedHp = item_.getMultDrainedHp();
        damageRecoil = item_.getDamageRecoil();
//        Map<String,String> loc_ = new Map<>();
//        loc_.put(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        loc_.put(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        multPower = data_.getFormula(item_.getMultPower(), getLanguage());
//        multPower = StringList.replace(multPower, loc_);
//        multPower = multPower.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        multPower = multPower.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        multDamage = data_.getFormula(item_.getMultDamage(), getLanguage());
//        multDamage = StringList.replace(multDamage, loc_);
//        multDamage = multDamage.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//        multDamage = multDamage.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
        StringList typesPk_;
        typesPk_ = new StringList();
        for (String type_: item_.getTypesPk()) {
            typesPk_.add(type_);
        }
        typesPk_.sortElts(DictionaryComparatorUtil.cmpTypes(data_,getLanguage()));
        typesPk = typesPk_;
        StringList typesPkAbilities_;
        typesPkAbilities_ = new StringList();
        for (String ability_: data_.getAbilities().getKeys()) {
            if (data_.getAbility(ability_).isPlate()) {
                typesPkAbilities_.add(ability_);
            }
        }
        typesPkAbilities_.sortElts(DictionaryComparatorUtil.cmpAbilities(data_,getLanguage()));
        typesPkAbilities = typesPkAbilities_;
        StringList immuTypes_;
        immuTypes_ = new StringList();
        for (String type_: item_.getImmuTypes()) {
            immuTypes_.add(type_);
        }
        immuTypes_.sortElts(DictionaryComparatorUtil.cmpTypes(data_,getLanguage()));
        immuTypes = immuTypes_;
        StringList immuStatus_;
        immuStatus_ = new StringList();
        for (String type_: item_.getImmuStatus()) {
            immuStatus_.add(type_);
        }
        immuStatus_.sortElts(DictionaryComparatorUtil.cmpStatus(data_,getLanguage()));
        immuStatus = immuStatus_;
        StringList synchroStatus_;
        synchroStatus_ = new StringList();
        for (String type_: item_.getSynchroStatus()) {
            synchroStatus_.add(type_);
        }
        synchroStatus_.sortElts(DictionaryComparatorUtil.cmpStatus(data_,getLanguage()));
        synchroStatus = synchroStatus_;
        DictionaryComparator<Statistic, Short> winEvFight_;
        winEvFight_ = DictionaryComparatorUtil.buildStatisShort(data_,getLanguage());
        for (Statistic s: item_.getWinEvFight().getKeys()) {
            winEvFight_.put(s, item_.getWinEvFight().getVal(s));
        }
        winEvFight = winEvFight_;
        DictionaryComparator<Statistic, Byte> multStatRank_;
        multStatRank_ = DictionaryComparatorUtil.buildStatisByte(data_,getLanguage());
        for (Statistic s: item_.getMultStatRank().getKeys()) {
            multStatRank_.put(s, item_.getMultStatRank().getVal(s));
        }
        multStatRank = multStatRank_;
        TreeMap<StatisticPokemon, Byte> multStatPokemonRank_;
        multStatPokemonRank_ = new TreeMap<StatisticPokemon, Byte>(new ComparatorStatisticPokemon(data_, getLanguage()));
        for (StatisticPokemon s: item_.getMultStatPokemonRank().getKeys()) {
            multStatPokemonRank_.put(s, item_.getMultStatPokemonRank().getVal(s));
        }
        multStatPokemonRank = multStatPokemonRank_;
        NatStringTreeMap<String> mapVars_;
        mapVars_ = new NatStringTreeMap<String>();
        DictionaryComparator<Statistic,String> multStat_;
        multStat_ = DictionaryComparatorUtil.buildStatisString(data_,getLanguage());
        for (Statistic s: item_.getMultStat().getKeys()) {
            String formula_ = data_.getFormula(item_.getMultStat().getVal(s), getLanguage());
//            formula_ = formula_.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//            formula_ = formula_.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
//            formula_ = StringList.replace(formula_, loc_);
            mapVars_.putAllMap(data_.getDescriptions(item_.getMultStat().getVal(s), getLanguage()));
            multStat_.put(s, formula_);
        }
        multStat = multStat_;
        DictionaryComparator<Statistic, Byte> boostStatisSuperEff_;
        boostStatisSuperEff_ = DictionaryComparatorUtil.buildStatisByte(data_,getLanguage());
        for (Statistic s: item_.getBoostStatisSuperEff().getKeys()) {
            boostStatisSuperEff_.put(s, item_.getBoostStatisSuperEff().getVal(s));
        }
        boostStatisSuperEff = boostStatisSuperEff_;
//        Map<String,String> locHtml_ = new Map<>();
//        locHtml_.put(EAMP, E_AMP);
//        locHtml_.put(EGT, E_GT);
//        locHtml_.put(ELT, E_LT);
        DictionaryComparator<String, String> failStatus_;
        failStatus_ = DictionaryComparatorUtil.buildStatusStr(data_,getLanguage());
        for (String s: item_.getFailStatus().getKeys()) {
            String formula_ = data_.getFormula(item_.getFailStatus().getVal(s), getLanguage());
//            formula_ = StringList.replace(formula_, locHtml_);
//            formula_ = formula_.replace(EAMP, E_AMP);
//            formula_ = formula_.replace(ELT, E_LT);
//            formula_ = formula_.replace(EGT, E_GT);
            mapVars_.putAllMap(data_.getDescriptions(item_.getFailStatus().getVal(s), getLanguage()));
            failStatus_.put(s, formula_);
        }
        failStatus = failStatus_;
        DictionaryComparator<String, Short> increasingMaxNbRoundTrap_;
        increasingMaxNbRoundTrap_ = DictionaryComparatorUtil.buildMovesShort(data_,getLanguage());
        for (String m: item_.getIncreasingMaxNbRoundTrap().getKeys()) {
            short nb_ = item_.getIncreasingMaxNbRoundTrap().getVal(m);
            for (Effect e: data_.getMove(m).getEffects()) {
                if (!(e instanceof EffectEndRoundSingleRelation)) {
                    continue;
                }
                EffectEndRoundSingleRelation eff_ = (EffectEndRoundSingleRelation) e;
                nb_ += eff_.getLawForEnablingEffect().maximum().ll();
            }
            increasingMaxNbRoundTrap_.put(m, nb_);
        }
        increasingMaxNbRoundTrap = increasingMaxNbRoundTrap_;
        DictionaryComparator<String, Short> increasingMaxNbRoundGlobalMove_;
        increasingMaxNbRoundGlobalMove_ = DictionaryComparatorUtil.buildMovesShort(data_,getLanguage());
        for (String m: item_.getIncreasingMaxNbRoundGlobalMove().getKeys()) {
            short nb_ = item_.getIncreasingMaxNbRoundGlobalMove().getVal(m);
            nb_ += data_.getMove(m).getRepeatRoundLaw().maximum().ll();
            increasingMaxNbRoundGlobalMove_.put(m, nb_);
        }
        increasingMaxNbRoundGlobalMove = increasingMaxNbRoundGlobalMove_;
        DictionaryComparator<String, Short> increasingMaxNbRoundTeamMove_;
        increasingMaxNbRoundTeamMove_ = DictionaryComparatorUtil.buildMovesShort(data_,getLanguage());
        for (String m: item_.getIncreasingMaxNbRoundTeamMove().getKeys()) {
            short nb_ = item_.getIncreasingMaxNbRoundTeamMove().getVal(m);
            nb_ += data_.getMove(m).getRepeatRoundLaw().maximum().ll();
            increasingMaxNbRoundTeamMove_.put(m, nb_);
        }
        increasingMaxNbRoundTeamMove = increasingMaxNbRoundTeamMove_;
        StringList immuMoves_;
        immuMoves_ = new StringList();
        for (String m: item_.getImmuMoves()) {
            immuMoves_.add(m);
        }
        immuMoves_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        immuMoves = immuMoves_;
        StringList immuWeather_;
        immuWeather_ = new StringList();
        for (String m: item_.getImmuWeather()) {
            immuWeather_.add(m);
        }
        immuWeather_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        immuWeather = immuWeather_;
        mapVars_.putAllMap(data_.getDescriptions(item_.getMultPower(), getLanguage()));
        mapVars_.putAllMap(data_.getDescriptions(item_.getMultDamage(), getLanguage()));
        mapVars = mapVars_;
        StringList hatching_;
        hatching_ = new StringList(item_.getHatching());
        hatching_.sortElts(DictionaryComparatorUtil.cmpPokemon(data_,getLanguage()));
        hatching = hatching_;
        DictionaryComparator<String, DictionaryComparator<Statistic, Byte>> boostStatisTypes_;
        boostStatisTypes_ = DictionaryComparatorUtil.buildTypesTypeDic(data_,getLanguage());
        for (String t: item_.getBoostStatisTypes().getKeys()) {
            DictionaryComparator<Statistic, Byte> boost_;
            boost_ = DictionaryComparatorUtil.buildStatisByte(data_,getLanguage());
            for (Statistic s: item_.getBoostStatisTypes().getVal(t).getKeys()) {
                boost_.put(s, item_.getBoostStatisTypes().getVal(t).getVal(s));
            }
            boostStatisTypes_.put(t, boost_);
        }
        boostStatisTypes = boostStatisTypes_;
        lawForAttackFirst = item_.getLawForAttackFirst();
    }
    public String getTrTypesPk(int _index) {
        String type_ = typesPk.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translatedTypes_.getVal(type_);
    }
    public String getTrTypesPkAbility(int _index) {
        String type_ = typesPkAbilities.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(type_);
    }
    public String clickTypesPkAbility(int _index) {
        String type_ = typesPkAbilities.get(_index);
        getForms().put(CST_ABILITY, type_);
        return CST_ABILITY;
    }
    public String getTrImmuTypes(int _index) {
        String type_ = immuTypes.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translatedTypes_.getVal(type_);
    }
    public String getTrImmuStatus(int _index) {
        String type_ = immuStatus.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(type_);
    }
    public String clickImmuStatus(int _index) {
        String type_ = immuStatus.get(_index);
        getForms().put(CST_STATUS, type_);
        return CST_STATUS;
    }
    public String getTrSynchroStatus(int _index) {
        String type_ = synchroStatus.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(type_);
    }
    public String clickSynchroStatus(int _index) {
        String type_ = synchroStatus.get(_index);
        getForms().put(CST_STATUS, type_);
        return CST_STATUS;
    }
    public String getTrFailStatus(int _index) {
        String type_ = failStatus.getKey(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(type_);
    }
    public String clickFailStatus(int _index) {
        String type_ = failStatus.getKey(_index);
        getForms().put(CST_STATUS, type_);
        return CST_STATUS;
    }
    public String getTrWinEvFight(int _index) {
        Statistic type_ = winEvFight.getKey(_index);
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(type_);
    }
    public String getTrMultStat(int _index) {
        Statistic type_ = multStat.getKey(_index);
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(type_);
    }
    public String getTrMultStatRank(int _index) {
        Statistic type_ = multStatRank.getKey(_index);
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(type_);
    }
    public String getTrMultStatPkRank(int _index) {
        Statistic type_ = multStatPokemonRank.getKey(_index).getStatistic();
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(type_);
    }
    public String getTrMultStatPk(int _index) {
        String type_ = multStatPokemonRank.getKey(_index).getPokemon();
        DataBase data_ = getDataBase();
        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        return translatedPokemon_.getVal(type_);
    }
    public String clickMultStatPk(int _index) {
        String type_ = multStatPokemonRank.getKey(_index).getPokemon();
        getForms().put(CST_PK, type_);
        return CST_POKEMON;
    }
    public String getTrMultStatisTypes(int _index) {
        String type_ = boostStatisTypes.getKey(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedStatistics_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translatedStatistics_.getVal(type_);
    }
    public String getTrMultStatisTypesStat(int _indexOne, int _indexTwo) {
        Statistic type_ = boostStatisTypes.getValue(_indexOne).getKey(_indexTwo);
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(type_);
    }
    public DictionaryComparator<Statistic, Byte> getMapMultStatisTypesStat(int _index) {
        return boostStatisTypes.getValue(_index);
    }
    public String getTrMultStatisSuperEff(int _index) {
        Statistic type_ = boostStatisSuperEff.getKey(_index);
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(type_);
    }
    public String getTrTrapMove(int _index) {
        String type_ = increasingMaxNbRoundTrap.getKey(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(type_);
    }
    public String clickTrapMove(int _index) {
        String type_ = increasingMaxNbRoundTrap.getKey(_index);
        getForms().put(CST_MOVE, type_);
        return CST_MOVE;
    }
    public String getTrGlobalMove(int _index) {
        String type_ = increasingMaxNbRoundGlobalMove.getKey(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(type_);
    }
    public String clickGlobalMove(int _index) {
        String type_ = increasingMaxNbRoundGlobalMove.getKey(_index);
        getForms().put(CST_MOVE, type_);
        return CST_MOVE;
    }
    public String getTrTeamMove(int _index) {
        String type_ = increasingMaxNbRoundTeamMove.getKey(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(type_);
    }
    public String clickTeamMove(int _index) {
        String type_ = increasingMaxNbRoundTeamMove.getKey(_index);
        getForms().put(CST_MOVE, type_);
        return CST_MOVE;
    }
    public String getTrImmuMove(int _index) {
        String type_ = immuMoves.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(type_);
    }
    public String clickImmuMove(int _index) {
        String type_ = immuMoves.get(_index);
        getForms().put(CST_MOVE, type_);
        return CST_MOVE;
    }
    public String getTrWeather(int _index) {
        String type_ = immuWeather.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(type_);
    }
    public String clickWeather(int _index) {
        String type_ = immuWeather.get(_index);
        getForms().put(CST_MOVE, type_);
        return CST_MOVE;
    }
    public String getTrHatching(int _index) {
        String type_ = hatching.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        return translatedPokemon_.getVal(type_);
    }
    public String clickHatching(int _index) {
        String type_ = hatching.get(_index);
        getForms().put(CST_PK, type_);
        return CST_POKEMON;
    }
    public Rate rateForAttackFirst() {
        return rateTrue(lawForAttackFirst);
    }

    public boolean determinated() {
        return rateForAttackFirst().greaterOrEqualsOne();
    }

    public EffectWhileSendingWithStatistic getEffectSending() {
        return ((ItemForBattle)getItem()).getEffectSending().first();
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

    public boolean getSending() {
        return sending;
    }

    public boolean getCancelImmuType() {
        return cancelImmuType;
    }

    public boolean getAgainstEvo() {
        return againstEvo;
    }

    public boolean getAttackLast() {
        return attackLast;
    }

    public boolean getBoostExp() {
        return boostExp;
    }

    public boolean getRepellingWildPk() {
        return repellingWildPk;
    }

    public boolean getImmuLowStatis() {
        return immuLowStatis;
    }

    public boolean getAttacksSoon() {
        return attacksSoon;
    }

    public Rate getProtectAgainstKo() {
        return protectAgainstKo;
    }

    public Rate getProtectAgainstKoIfFullHp() {
        return protectAgainstKoIfFullHp;
    }

    public Rate getDrainedHpByDamageRate() {
        return drainedHpByDamageRate;
    }

    public Rate getMultTrappingDamage() {
        return multTrappingDamage;
    }

    public Rate getMultWinningMoney() {
        return multWinningMoney;
    }

    public Rate getMultWinningHappiness() {
        return multWinningHappiness;
    }

    public Rate getMultWinningEv() {
        return multWinningEv;
    }

    public Rate getMultWinningExp() {
        return multWinningExp;
    }

    public Rate getMultDrainedHp() {
        return multDrainedHp;
    }

    public Rate getDamageRecoil() {
        return damageRecoil;
    }

    public String getMultPower() {
        return multPower;
    }

    public String getMultDamage() {
        return multDamage;
    }

    public DictionaryComparator<Statistic,Byte> getMultStatRank() {
        return multStatRank;
    }

    public TreeMap<StatisticPokemon,Byte> getMultStatPokemonRank() {
        return multStatPokemonRank;
    }

    public DictionaryComparator<Statistic,Byte> getBoostStatisSuperEff() {
        return boostStatisSuperEff;
    }

    public DictionaryComparator<String,DictionaryComparator<Statistic,Byte>> getBoostStatisTypes() {
        return boostStatisTypes;
    }

    public DictionaryComparator<Statistic,String> getMultStat() {
        return multStat;
    }

    public DictionaryComparator<String,String> getFailStatus() {
        return failStatus;
    }

    public NatStringTreeMap<String> getMapVars() {
        return mapVars;
    }

    public StringList getTypesPk() {
        return typesPk;
    }

    public StringList getTypesPkAbilities() {
        return typesPkAbilities;
    }

    public StringList getImmuStatus() {
        return immuStatus;
    }

    public StringList getImmuTypes() {
        return immuTypes;
    }

    public StringList getSynchroStatus() {
        return synchroStatus;
    }

    public DictionaryComparator<Statistic,Short> getWinEvFight() {
        return winEvFight;
    }

    public DictionaryComparator<String,Short> getIncreasingMaxNbRoundTrap() {
        return increasingMaxNbRoundTrap;
    }

    public DictionaryComparator<String,Short> getIncreasingMaxNbRoundGlobalMove() {
        return increasingMaxNbRoundGlobalMove;
    }

    public DictionaryComparator<String,Short> getIncreasingMaxNbRoundTeamMove() {
        return increasingMaxNbRoundTeamMove;
    }

    public StringList getImmuMoves() {
        return immuMoves;
    }

    public StringList getImmuWeather() {
        return immuWeather;
    }
}