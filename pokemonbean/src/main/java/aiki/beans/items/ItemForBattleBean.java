package aiki.beans.items;
import code.bean.Accessible;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloBoolean;
import code.util.EnumMap;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import aiki.DataBase;
import aiki.beans.facade.comparators.ComparatorStatisticPokemon;
import aiki.comparators.ComparatorTrStringStatistic;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Item;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectEndRound;
import aiki.fight.moves.effects.EffectEndRoundSingleRelation;
import aiki.fight.util.StatisticPokemon;

public class ItemForBattleBean extends ItemBean {

    @Accessible
    private final String effectSendBean="web/html/sending/effsending.html";

    @Accessible
    private StringList typesPk;

    @Accessible
    private StringList typesPkAbilities;

    @Accessible
    private boolean cancelImmuType;

    @Accessible
    private boolean againstEvo;

    @Accessible
    private boolean attackLast;

    @Accessible
    private boolean boostExp;

    @Accessible
    private StringList immuStatus;

    @Accessible
    private boolean immuLowStatis;

    @Accessible
    private TreeMap<String, Short> increasingMaxNbRoundTrap;

    @Accessible
    private boolean attacksSoon;

    @Accessible
    private boolean repellingWildPk;

    @Accessible
    private StringList synchroStatus;

    @Accessible
    private TreeMap<String, String> failStatus;

    @Accessible
    private Rate protectAgainstKo;

    @Accessible
    private Rate protectAgainstKoIfFullHp;

    @Accessible
    private Rate drainedHpByDamageRate;

    @Accessible
    private TreeMap<Statistic, Short> winEvFight;

    private MonteCarloBoolean lawForAttackFirst;

    @Accessible
    private Rate multTrappingDamage;

    @Accessible
    private Rate multWinningMoney;

    @Accessible
    private Rate multWinningHappiness;

    @Accessible
    private Rate multWinningEv;

    @Accessible
    private Rate multWinningExp;

    @Accessible
    private String multPower;

    @Accessible
    private String multDamage;

    @Accessible
    private Rate multDrainedHp;

    @Accessible
    private Rate damageRecoil;

    @Accessible
    private TreeMap<Statistic, Byte> multStatRank;

    @Accessible
    private TreeMap<StatisticPokemon, Byte> multStatPokemonRank;

    @Accessible
    private TreeMap<Statistic,String> multStat;

    @Accessible
    private TreeMap<String, Short> increasingMaxNbRoundGlobalMove;

    @Accessible
    private TreeMap<String, Short> increasingMaxNbRoundTeamMove;

    @Accessible
    private StringList immuMoves;

    @Accessible
    private StringList hatching;

    @Accessible
    private StringList immuTypes;

    @Accessible
    private StringList immuWeather;

    @Accessible
    private TreeMap<Statistic, Byte> boostStatisSuperEff;

    @Accessible
    private TreeMap<String, TreeMap<Statistic, Byte>> boostStatisTypes;

//    @Accessible
//    private StringList reasons;
//
//    @Accessible
//    private TreeMap<String,String> mapVarsFail;

    @Accessible
    private boolean endRound;

    @Accessible
    private int endRoundRank;

    @Accessible
    private StringList reasonsEndRound;

    @Accessible
    private NatTreeMap<String,String> mapVarsFailEndRound;

    @Accessible
    private boolean sending;

    @Accessible
    private NatTreeMap<String,String> mapVars;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        DataBase data_ = (DataBase) getDataBase();
        ItemForBattle item_ = (ItemForBattle) getItem();
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
            mapVarsFailEndRound = new NatTreeMap<String,String>();
        }
        if (!item_.getEffectSending().isEmpty()) {
            sending = true;
        } else {
            sending = false;
        }
        cancelImmuType = item_.getCancelImmuType();
        againstEvo = item_.getAgainstEvo();
        attackLast = item_.getAttackLast();
        boostExp = item_.getBoostExp();
        immuLowStatis = item_.getImmuLowStatis();
        attacksSoon = item_.getAttacksSoon();
        repellingWildPk = item_.getRepellingWildPk();
        protectAgainstKo = item_.getProtectAgainstKo();
        protectAgainstKoIfFullHp = item_.getProtectAgainstKoIfFullHp();
        drainedHpByDamageRate = item_.getDrainedHpByDamageRate();
        multTrappingDamage = item_.getMultTrappingDamage();
        multWinningMoney = item_.getMultWinningMoney();
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
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        typesPk_.sortElts(new ComparatorTrStrings(translatedTypes_));
        typesPk = typesPk_;
        StringList typesPkAbilities_;
        typesPkAbilities_ = new StringList();
        for (String ability_: data_.getAbilities().getKeys()) {
            if (data_.getAbility(ability_).isPlate()) {
                typesPkAbilities_.add(ability_);
            }
        }
        typesPkAbilities_.sortElts(new ComparatorTrStrings(translatedAbilities_));
        typesPkAbilities = typesPkAbilities_;
        StringList immuTypes_;
        immuTypes_ = new StringList();
        for (String type_: item_.getImmuTypes()) {
            immuTypes_.add(type_);
        }
        immuTypes_.sortElts(new ComparatorTrStrings(translatedTypes_));
        immuTypes = immuTypes_;
        StringList immuStatus_;
        immuStatus_ = new StringList();
        for (String type_: item_.getImmuStatus()) {
            immuStatus_.add(type_);
        }
        immuStatus_.sortElts(new ComparatorTrStrings(translatedStatus_));
        immuStatus = immuStatus_;
        StringList synchroStatus_;
        synchroStatus_ = new StringList();
        for (String type_: item_.getSynchroStatus()) {
            synchroStatus_.add(type_);
        }
        synchroStatus_.sortElts(new ComparatorTrStrings(translatedStatus_));
        synchroStatus = synchroStatus_;
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        TreeMap<Statistic, Short> winEvFight_;
        winEvFight_ = new TreeMap<Statistic, Short>(new ComparatorTrStringStatistic(translatedStatistics_));
        for (Statistic s: item_.getWinEvFight().getKeys()) {
            winEvFight_.put(s, item_.getWinEvFight().getVal(s));
        }
        winEvFight = winEvFight_;
        TreeMap<Statistic, Byte> multStatRank_;
        multStatRank_ = new TreeMap<Statistic, Byte>(new ComparatorTrStringStatistic(translatedStatistics_));
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
        NatTreeMap<String,String> mapVars_;
        mapVars_ = new NatTreeMap<String,String>();
        TreeMap<Statistic,String> multStat_;
        multStat_ = new TreeMap<Statistic, String>(new ComparatorTrStringStatistic(translatedStatistics_));
        for (Statistic s: item_.getMultStat().getKeys()) {
            String formula_ = data_.getFormula(item_.getMultStat().getVal(s), getLanguage());
//            formula_ = formula_.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//            formula_ = formula_.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
//            formula_ = StringList.replace(formula_, loc_);
            mapVars_.putAllTreeMap(data_.getDescriptions(item_.getMultStat().getVal(s), getLanguage()));
            multStat_.put(s, formula_);
        }
        multStat = multStat_;
        TreeMap<Statistic, Byte> boostStatisSuperEff_;
        boostStatisSuperEff_ = new TreeMap<Statistic, Byte>(new ComparatorTrStringStatistic(translatedStatistics_));
        for (Statistic s: item_.getBoostStatisSuperEff().getKeys()) {
            boostStatisSuperEff_.put(s, item_.getBoostStatisSuperEff().getVal(s));
        }
        boostStatisSuperEff = boostStatisSuperEff_;
//        Map<String,String> locHtml_ = new Map<>();
//        locHtml_.put(EAMP, E_AMP);
//        locHtml_.put(EGT, E_GT);
//        locHtml_.put(ELT, E_LT);
        TreeMap<String, String> failStatus_;
        failStatus_ = new TreeMap<String, String>(new ComparatorTrStrings(translatedStatus_));
        for (String s: item_.getFailStatus().getKeys()) {
            String formula_ = data_.getFormula(item_.getFailStatus().getVal(s), getLanguage());
//            formula_ = StringList.replace(formula_, locHtml_);
//            formula_ = formula_.replace(EAMP, E_AMP);
//            formula_ = formula_.replace(ELT, E_LT);
//            formula_ = formula_.replace(EGT, E_GT);
            mapVars_.putAllTreeMap(data_.getDescriptions(item_.getFailStatus().getVal(s), getLanguage()));
            failStatus_.put(s, formula_);
        }
        failStatus = failStatus_;
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        TreeMap<String, Short> increasingMaxNbRoundTrap_;
        increasingMaxNbRoundTrap_ = new TreeMap<String, Short>(new ComparatorTrStrings(translatedMoves_));
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
        TreeMap<String, Short> increasingMaxNbRoundGlobalMove_;
        increasingMaxNbRoundGlobalMove_ = new TreeMap<String, Short>(new ComparatorTrStrings(translatedMoves_));
        for (String m: item_.getIncreasingMaxNbRoundGlobalMove().getKeys()) {
            short nb_ = item_.getIncreasingMaxNbRoundGlobalMove().getVal(m);
            nb_ += data_.getMove(m).getRepeatRoundLaw().maximum().ll();
            increasingMaxNbRoundGlobalMove_.put(m, nb_);
        }
        increasingMaxNbRoundGlobalMove = increasingMaxNbRoundGlobalMove_;
        TreeMap<String, Short> increasingMaxNbRoundTeamMove_;
        increasingMaxNbRoundTeamMove_ = new TreeMap<String, Short>(new ComparatorTrStrings(translatedMoves_));
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
        immuMoves_.sortElts(new ComparatorTrStrings(translatedMoves_));
        immuMoves = immuMoves_;
        StringList immuWeather_;
        immuWeather_ = new StringList();
        for (String m: item_.getImmuWeather()) {
            immuWeather_.add(m);
        }
        immuWeather_.sortElts(new ComparatorTrStrings(translatedMoves_));
        immuWeather = immuWeather_;
        mapVars_.putAllTreeMap(data_.getDescriptions(item_.getMultPower(), getLanguage()));
        mapVars_.putAllTreeMap(data_.getDescriptions(item_.getMultDamage(), getLanguage()));
        mapVars = mapVars_;
        StringList hatching_;
        hatching_ = new StringList(item_.getHatching());
        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        hatching_.sortElts(new ComparatorTrStrings(translatedPokemon_));
        hatching = hatching_;
        TreeMap<String, TreeMap<Statistic, Byte>> boostStatisTypes_;
        boostStatisTypes_ = new TreeMap<String, TreeMap<Statistic, Byte>>(new ComparatorTrStrings(translatedTypes_));
        for (String t: item_.getBoostStatisTypes().getKeys()) {
            TreeMap<Statistic, Byte> boost_;
            boost_ = new TreeMap<Statistic, Byte>(new ComparatorTrStringStatistic(translatedStatistics_));
            for (Statistic s: item_.getBoostStatisTypes().getVal(t).getKeys()) {
                boost_.put(s, item_.getBoostStatisTypes().getVal(t).getVal(s));
            }
            boostStatisTypes_.put(t, boost_);
        }
        boostStatisTypes = boostStatisTypes_;
        lawForAttackFirst = item_.getLawForAttackFirst();
    }

    @Accessible
    private String getTrTypesPk(Long _index) {
        String type_ = typesPk.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translatedTypes_.getVal(type_);
    }

    @Accessible
    private String getTrTypesPkAbility(Long _index) {
        String type_ = typesPkAbilities.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(type_);
    }

    @Accessible
    private String clickTypesPkAbility(Long _index) {
        String type_ = typesPkAbilities.get(_index.intValue());
        getForms().put(ABILITY, type_);
        return ABILITY;
    }

    @Accessible
    private String getTrImmuTypes(Long _index) {
        String type_ = immuTypes.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translatedTypes_.getVal(type_);
    }

    @Accessible
    private String getTrImmuStatus(Long _index) {
        String type_ = immuStatus.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(type_);
    }

    @Accessible
    private String clickImmuStatus(Long _index) {
        String type_ = immuStatus.get(_index.intValue());
        getForms().put(STATUS, type_);
        return STATUS;
    }

    @Accessible
    private String getTrSynchroStatus(Long _index) {
        String type_ = synchroStatus.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(type_);
    }

    @Accessible
    private String clickSynchroStatus(Long _index) {
        String type_ = synchroStatus.get(_index.intValue());
        getForms().put(STATUS, type_);
        return STATUS;
    }

    @Accessible
    private String getTrFailStatus(Long _index) {
        String type_ = failStatus.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(type_);
    }

    @Accessible
    private String clickFailStatus(Long _index) {
        String type_ = failStatus.getKey(_index.intValue());
        getForms().put(STATUS, type_);
        return STATUS;
    }

    @Accessible
    private String getTrWinEvFight(Long _index) {
        Statistic type_ = winEvFight.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(type_);
    }

    @Accessible
    private String getTrMultStat(Long _index) {
        Statistic type_ = multStat.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(type_);
    }

    @Accessible
    private String getTrMultStatRank(Long _index) {
        Statistic type_ = multStatRank.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(type_);
    }

    @Accessible
    private String getTrMultStatPkRank(Long _index) {
        Statistic type_ = multStatPokemonRank.getKey(_index.intValue()).getStatistic();
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(type_);
    }

    @Accessible
    private String getTrMultStatPk(Long _index) {
        String type_ = multStatPokemonRank.getKey(_index.intValue()).getPokemon();
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        return translatedPokemon_.getVal(type_);
    }

    @Accessible
    private String clickMultStatPk(Long _index) {
        String type_ = multStatPokemonRank.getKey(_index.intValue()).getPokemon();
        getForms().put(PK, type_);
        return POKEMON;
    }

    @Accessible
    private String getTrMultStatisTypes(Long _index) {
        String type_ = boostStatisTypes.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatistics_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translatedStatistics_.getVal(type_);
    }

    @Accessible
    private String getTrMultStatisTypesStat(Long _indexOne, Long _indexTwo) {
        Statistic type_ = boostStatisTypes.getValue(_indexOne.intValue()).getKey(_indexTwo.intValue());
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(type_);
    }

    @Accessible
    private TreeMap<Statistic, Byte> getMapMultStatisTypesStat(Long _index) {
        return boostStatisTypes.getValue(_index.intValue());
    }

    @Accessible
    private String getTrMultStatisSuperEff(Long _index) {
        Statistic type_ = boostStatisSuperEff.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(type_);
    }

    @Accessible
    private String getTrTrapMove(Long _index) {
        String type_ = increasingMaxNbRoundTrap.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(type_);
    }

    @Accessible
    private String clickTrapMove(Long _index) {
        String type_ = increasingMaxNbRoundTrap.getKey(_index.intValue());
        getForms().put(MOVE, type_);
        return MOVE;
    }

    @Accessible
    private String getTrGlobalMove(Long _index) {
        String type_ = increasingMaxNbRoundGlobalMove.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(type_);
    }

    @Accessible
    private String clickGlobalMove(Long _index) {
        String type_ = increasingMaxNbRoundGlobalMove.getKey(_index.intValue());
        getForms().put(MOVE, type_);
        return MOVE;
    }

    @Accessible
    private String getTrTeamMove(Long _index) {
        String type_ = increasingMaxNbRoundTeamMove.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(type_);
    }

    @Accessible
    private String clickTeamMove(Long _index) {
        String type_ = increasingMaxNbRoundTeamMove.getKey(_index.intValue());
        getForms().put(MOVE, type_);
        return MOVE;
    }

    @Accessible
    private String getTrImmuMove(Long _index) {
        String type_ = immuMoves.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(type_);
    }

    @Accessible
    private String clickImmuMove(Long _index) {
        String type_ = immuMoves.get(_index.intValue());
        getForms().put(MOVE, type_);
        return MOVE;
    }

    @Accessible
    private String getTrWeather(Long _index) {
        String type_ = immuWeather.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(type_);
    }

    @Accessible
    private String clickWeather(Long _index) {
        String type_ = immuWeather.get(_index.intValue());
        getForms().put(MOVE, type_);
        return MOVE;
    }

    @Accessible
    private String getTrHatching(Long _index) {
        String type_ = hatching.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        return translatedPokemon_.getVal(type_);
    }

    @Accessible
    private String clickHatching(Long _index) {
        String type_ = hatching.get(_index.intValue());
        getForms().put(PK, type_);
        return POKEMON;
    }

    @Accessible
    private Rate rateForAttackFirst() {
        if (lawForAttackFirst.events().isEmpty()) {
            return Rate.zero();
        }
        if (!lawForAttackFirst.containsEvent(true)) {
            return Rate.zero();
        }
        return new Rate(lawForAttackFirst.rate(true), lawForAttackFirst.sum());
    }

    @Accessible
    private boolean determinated() {
        if (lawForAttackFirst.events().isEmpty()) {
            return false;
        }
        if (!lawForAttackFirst.containsEvent(true)) {
            return false;
        }
        return lawForAttackFirst.events().size() == DataBase.ONE_POSSIBLE_CHOICE;
    }

    @Override
    public Item getItem() {
        return super.getItem();
    }
}
