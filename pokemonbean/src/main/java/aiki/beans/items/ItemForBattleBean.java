package aiki.beans.items;
import aiki.beans.facade.comparators.ComparatorStatisticPokemon;
import aiki.comparators.ComparatorTrStringStatistic;
import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import aiki.fight.effects.EffectWhileSending;
import aiki.fight.enums.Statistic;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectEndRound;
import aiki.fight.moves.effects.EffectEndRoundSingleRelation;
import aiki.fight.util.StatisticPokemon;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloBoolean;
import code.util.EnumMap;
import code.util.NatStringTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;

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
    private TreeMap<String, Short> increasingMaxNbRoundTrap;
    private boolean attacksSoon;
    private boolean repellingWildPk;
    private StringList synchroStatus;
    private TreeMap<String, String> failStatus;
    private Rate protectAgainstKo;
    private Rate protectAgainstKoIfFullHp;
    private Rate drainedHpByDamageRate;
    private TreeMap<Statistic, Short> winEvFight;

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
    private TreeMap<Statistic, Byte> multStatRank;
    private TreeMap<StatisticPokemon, Byte> multStatPokemonRank;
    private TreeMap<Statistic,String> multStat;
    private TreeMap<String, Short> increasingMaxNbRoundGlobalMove;
    private TreeMap<String, Short> increasingMaxNbRoundTeamMove;
    private StringList immuMoves;
    private StringList hatching;
    private StringList immuTypes;
    private StringList immuWeather;
    private TreeMap<Statistic, Byte> boostStatisSuperEff;
    private TreeMap<String, TreeMap<Statistic, Byte>> boostStatisTypes;
    private boolean endRound;
    private int endRoundRank;
    private StringList reasonsEndRound;
    private NatStringTreeMap<String> mapVarsFailEndRound;
    private boolean sending;
    private NatStringTreeMap<String> mapVars;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        DataBase data_ = (DataBase) getDataBase();
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
        NatStringTreeMap<String> mapVars_;
        mapVars_ = new NatStringTreeMap<String>();
        TreeMap<Statistic,String> multStat_;
        multStat_ = new TreeMap<Statistic, String>(new ComparatorTrStringStatistic(translatedStatistics_));
        for (Statistic s: item_.getMultStat().getKeys()) {
            String formula_ = data_.getFormula(item_.getMultStat().getVal(s), getLanguage());
//            formula_ = formula_.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//            formula_ = formula_.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
//            formula_ = StringList.replace(formula_, loc_);
            mapVars_.putAllMap(data_.getDescriptions(item_.getMultStat().getVal(s), getLanguage()));
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
            mapVars_.putAllMap(data_.getDescriptions(item_.getFailStatus().getVal(s), getLanguage()));
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
        mapVars_.putAllMap(data_.getDescriptions(item_.getMultPower(), getLanguage()));
        mapVars_.putAllMap(data_.getDescriptions(item_.getMultDamage(), getLanguage()));
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
    public String getTrTypesPk(Long _index) {
        String type_ = typesPk.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translatedTypes_.getVal(type_);
    }
    public String getTrTypesPkAbility(Long _index) {
        String type_ = typesPkAbilities.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        return translatedAbilities_.getVal(type_);
    }
    public String clickTypesPkAbility(Long _index) {
        String type_ = typesPkAbilities.get(_index.intValue());
        getForms().put(ABILITY, type_);
        return ABILITY;
    }
    public String getTrImmuTypes(Long _index) {
        String type_ = immuTypes.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translatedTypes_.getVal(type_);
    }
    public String getTrImmuStatus(Long _index) {
        String type_ = immuStatus.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(type_);
    }
    public String clickImmuStatus(Long _index) {
        String type_ = immuStatus.get(_index.intValue());
        getForms().put(STATUS, type_);
        return STATUS;
    }
    public String getTrSynchroStatus(Long _index) {
        String type_ = synchroStatus.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(type_);
    }
    public String clickSynchroStatus(Long _index) {
        String type_ = synchroStatus.get(_index.intValue());
        getForms().put(STATUS, type_);
        return STATUS;
    }
    public String getTrFailStatus(Long _index) {
        String type_ = failStatus.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
        return translatedStatus_.getVal(type_);
    }
    public String clickFailStatus(Long _index) {
        String type_ = failStatus.getKey(_index.intValue());
        getForms().put(STATUS, type_);
        return STATUS;
    }
    public String getTrWinEvFight(Long _index) {
        Statistic type_ = winEvFight.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(type_);
    }
    public String getTrMultStat(Long _index) {
        Statistic type_ = multStat.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(type_);
    }
    public String getTrMultStatRank(Long _index) {
        Statistic type_ = multStatRank.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(type_);
    }
    public String getTrMultStatPkRank(Long _index) {
        Statistic type_ = multStatPokemonRank.getKey(_index.intValue()).getStatistic();
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(type_);
    }
    public String getTrMultStatPk(Long _index) {
        String type_ = multStatPokemonRank.getKey(_index.intValue()).getPokemon();
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        return translatedPokemon_.getVal(type_);
    }
    public String clickMultStatPk(Long _index) {
        String type_ = multStatPokemonRank.getKey(_index.intValue()).getPokemon();
        getForms().put(PK, type_);
        return POKEMON;
    }
    public String getTrMultStatisTypes(Long _index) {
        String type_ = boostStatisTypes.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedStatistics_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translatedStatistics_.getVal(type_);
    }
    public String getTrMultStatisTypesStat(Long _indexOne, Long _indexTwo) {
        Statistic type_ = boostStatisTypes.getValue(_indexOne.intValue()).getKey(_indexTwo.intValue());
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(type_);
    }
    public TreeMap<Statistic, Byte> getMapMultStatisTypesStat(Long _index) {
        return boostStatisTypes.getValue(_index.intValue());
    }
    public String getTrMultStatisSuperEff(Long _index) {
        Statistic type_ = boostStatisSuperEff.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        EnumMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(type_);
    }
    public String getTrTrapMove(Long _index) {
        String type_ = increasingMaxNbRoundTrap.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(type_);
    }
    public String clickTrapMove(Long _index) {
        String type_ = increasingMaxNbRoundTrap.getKey(_index.intValue());
        getForms().put(MOVE, type_);
        return MOVE;
    }
    public String getTrGlobalMove(Long _index) {
        String type_ = increasingMaxNbRoundGlobalMove.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(type_);
    }
    public String clickGlobalMove(Long _index) {
        String type_ = increasingMaxNbRoundGlobalMove.getKey(_index.intValue());
        getForms().put(MOVE, type_);
        return MOVE;
    }
    public String getTrTeamMove(Long _index) {
        String type_ = increasingMaxNbRoundTeamMove.getKey(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(type_);
    }
    public String clickTeamMove(Long _index) {
        String type_ = increasingMaxNbRoundTeamMove.getKey(_index.intValue());
        getForms().put(MOVE, type_);
        return MOVE;
    }
    public String getTrImmuMove(Long _index) {
        String type_ = immuMoves.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(type_);
    }
    public String clickImmuMove(Long _index) {
        String type_ = immuMoves.get(_index.intValue());
        getForms().put(MOVE, type_);
        return MOVE;
    }
    public String getTrWeather(Long _index) {
        String type_ = immuWeather.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(type_);
    }
    public String clickWeather(Long _index) {
        String type_ = immuWeather.get(_index.intValue());
        getForms().put(MOVE, type_);
        return MOVE;
    }
    public String getTrHatching(Long _index) {
        String type_ = hatching.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        return translatedPokemon_.getVal(type_);
    }
    public String clickHatching(Long _index) {
        String type_ = hatching.get(_index.intValue());
        getForms().put(PK, type_);
        return POKEMON;
    }
    public Rate rateForAttackFirst() {
        if (lawForAttackFirst.isZero()) {
            return Rate.zero();
        }
        if (!lawForAttackFirst.containsEvent(true)) {
            return Rate.zero();
        }
        return lawForAttackFirst.normalizedRate(true);
    }
    public boolean determinated() {
        if (lawForAttackFirst.events().isEmpty()) {
            return false;
        }
        if (!lawForAttackFirst.containsEvent(true)) {
            return false;
        }
        return lawForAttackFirst.events().size() == DataBase.ONE_POSSIBLE_CHOICE;
    }

    public EffectWhileSending getEffectSending() {
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

    public TreeMap<Statistic,Byte> getMultStatRank() {
        return multStatRank;
    }

    public TreeMap<StatisticPokemon,Byte> getMultStatPokemonRank() {
        return multStatPokemonRank;
    }

    public TreeMap<Statistic,Byte> getBoostStatisSuperEff() {
        return boostStatisSuperEff;
    }

    public TreeMap<String,TreeMap<Statistic,Byte>> getBoostStatisTypes() {
        return boostStatisTypes;
    }

    public TreeMap<Statistic,String> getMultStat() {
        return multStat;
    }

    public TreeMap<String,String> getFailStatus() {
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

    public TreeMap<Statistic,Short> getWinEvFight() {
        return winEvFight;
    }

    public TreeMap<String,Short> getIncreasingMaxNbRoundTrap() {
        return increasingMaxNbRoundTrap;
    }

    public TreeMap<String,Short> getIncreasingMaxNbRoundGlobalMove() {
        return increasingMaxNbRoundGlobalMove;
    }

    public TreeMap<String,Short> getIncreasingMaxNbRoundTeamMove() {
        return increasingMaxNbRoundTeamMove;
    }

    public StringList getImmuMoves() {
        return immuMoves;
    }

    public StringList getImmuWeather() {
        return immuWeather;
    }
}