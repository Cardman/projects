package aiki.beans.items;

import aiki.beans.*;
import aiki.beans.abilities.*;
import aiki.comparators.*;
import aiki.db.*;
import aiki.facade.FacadeGame;
import aiki.fight.abilities.*;
import aiki.fight.effects.*;
import aiki.fight.enums.*;
import aiki.fight.items.*;
import aiki.fight.moves.effects.*;
import aiki.fight.util.*;
import code.maths.*;
import code.maths.montecarlo.*;
import code.scripts.confs.*;
import code.util.*;

public class ItemForBattleBean extends ItemBean {
    static final String EFFECT_SEND_BEAN=PkScriptPages.REN_ADD_WEB_HTML_SENDING_EFFSENDING_HTML;
    private StringList typesPk;
    private CustList<TranslatedKey> typesPkAbilities;
    private boolean cancelImmuType;
    private boolean againstEvo;
    private boolean attackLast;
    private boolean boostExp;
    private CustList<TranslatedKey> immuStatus;
    private boolean immuLowStatis;
    private DictionaryComparator<TranslatedKey, Long> increasingMaxNbRoundTrap;
    private boolean attacksSoon;
    private CustList<TranslatedKey> synchroStatus;
    private DictionaryComparator<TranslatedKey, String> failStatus;
    private Rate protectAgainstKo;
    private Rate protectAgainstKoIfFullHp;
    private Rate drainedHpByDamageRate;
    private DictionaryComparator<Statistic, Long> winEvFight;

    private MonteCarloBoolean lawForAttackFirst;
    private Rate multTrappingDamage;
    private Rate multWinningHappiness;
    private Rate multWinningEv;
    private Rate multWinningExp;
    private String multPower;
    private String multDamage;
    private Rate multDrainedHp;
    private Rate damageRecoil;
    private DictionaryComparator<Statistic, Long> multStatRank;
    private DictionaryComparator<TranslatedKeyPair, Long> multStatPokemonRank;
    private DictionaryComparator<Statistic,String> multStat;
    private DictionaryComparator<TranslatedKey, Long> increasingMaxNbRoundGlobalMove;
    private DictionaryComparator<TranslatedKey, Long> increasingMaxNbRoundTeamMove;
    private CustList<TranslatedKey> immuMoves;
//    private StringList hatching;
    private StringList immuTypes;
    private CustList<TranslatedKey> immuWeather;
    private DictionaryComparator<Statistic, Long> boostStatisSuperEff;
    private DictionaryComparator<String, DictionaryComparator<Statistic, Long>> boostStatisTypes;
//    private boolean endRound;
//    private int endRoundRank;
//    private StringList reasonsEndRound;
//    private NatStringTreeMap<String> mapVarsFailEndRound;
    private boolean sending;
    private NatStringTreeMap<String> mapVars;
    private final EndRoundCommon endRoundCommon = new EndRoundCommon();

    @Override
    public void beforeDisplaying() {
        beforeDisplayingItem();
        DataBase data_ = getDataBase();
        ItemForBattle item_ = (ItemForBattle)getItem();
        endRound(item_);
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
        typesPkAbilities = typesPkAbilities();
        StringList immuTypes_;
        immuTypes_ = new StringList();
        for (String type_: item_.getImmuTypes()) {
            immuTypes_.add(type_);
        }
        immuTypes_.sortElts(DictionaryComparatorUtil.cmpTypes(data_,getLanguage()));
        immuTypes = immuTypes_;
        immuStatus = listTrStringsSt(item_.getImmuStatus(),getFacade());
        synchroStatus = listTrStringsSt(item_.getSynchroStatus(),getFacade());
        DictionaryComparator<Statistic, Long> winEvFight_;
        winEvFight_ = DictionaryComparatorUtil.buildStatisShort(data_,getLanguage());
        for (Statistic s: item_.getWinEvFight().getKeys()) {
            winEvFight_.put(s, item_.getWinEvFight().getVal(s));
        }
        winEvFight = winEvFight_;
        DictionaryComparator<Statistic, Long> multStatRank_;
        multStatRank_ = DictionaryComparatorUtil.buildStatisByte(data_,getLanguage());
        for (Statistic s: item_.getMultStatRank().getKeys()) {
            multStatRank_.put(s, item_.getMultStatRank().getVal(s));
        }
        multStatRank = multStatRank_;
        DictionaryComparator<TranslatedKeyPair, Long> multStatPokemonRank_;
        multStatPokemonRank_ = DictionaryComparatorUtil.buildStatPk(data_, getLanguage());
        for (StatisticPokemon s: item_.getMultStatPokemonRank().getKeys()) {
            multStatPokemonRank_.put(buildPair(getFacade(),s), item_.getMultStatPokemonRank().getVal(s));
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
        DictionaryComparator<Statistic, Long> boostStatisSuperEff_;
        boostStatisSuperEff_ = DictionaryComparatorUtil.buildStatisByte(data_,getLanguage());
        for (Statistic s: item_.getBoostStatisSuperEff().getKeys()) {
            boostStatisSuperEff_.put(s, item_.getBoostStatisSuperEff().getVal(s));
        }
        boostStatisSuperEff = boostStatisSuperEff_;
//        Map<String,String> locHtml_ = new Map<>();
//        locHtml_.put(EAMP, E_AMP);
//        locHtml_.put(EGT, E_GT);
//        locHtml_.put(ELT, E_LT);
        DictionaryComparator<TranslatedKey, String> failStatus_;
        failStatus_ = DictionaryComparatorUtil.buildStatusStrOnly();
        for (String s: item_.getFailStatus().getKeys()) {
            String formula_ = data_.getFormula(item_.getFailStatus().getVal(s), getLanguage());
//            formula_ = StringList.replace(formula_, locHtml_);
//            formula_ = formula_.replace(EAMP, E_AMP);
//            formula_ = formula_.replace(ELT, E_LT);
//            formula_ = formula_.replace(EGT, E_GT);
            mapVars_.putAllMap(data_.getDescriptions(item_.getFailStatus().getVal(s), getLanguage()));
            failStatus_.put(buildSt(getFacade(),s), formula_);
        }
        failStatus = failStatus_;
        increasingMaxNbRoundTrap = increasingMaxNbRoundTrap(item_.getIncreasingMaxNbRoundTrap());
        increasingMaxNbRoundGlobalMove = increasingMaxNbRoundTrapRepeat(item_.getIncreasingMaxNbRoundGlobalMove());
        increasingMaxNbRoundTeamMove = increasingMaxNbRoundTrapRepeat(item_.getIncreasingMaxNbRoundTeamMove());
        immuMoves = listTrStringsMv(item_.getImmuMoves(),getFacade());
        immuWeather = listTrStringsMv(item_.getImmuWeather(),getFacade());
        mapVars_.putAllMap(data_.getDescriptions(item_.getMultPower(), getLanguage()));
        mapVars_.putAllMap(data_.getDescriptions(item_.getMultDamage(), getLanguage()));
        mapVars = mapVars_;
//        StringList hatching_;
//        hatching_ = new StringList(item_.getHatching());
//        hatching_.sortElts(DictionaryComparatorUtil.cmpPokemon(data_,getLanguage()));
//        hatching = hatching_;
        boostStatisTypes = boostStatisTypes(item_);
        lawForAttackFirst = item_.getLawForAttackFirst();
    }

    public static TranslatedKeyPair buildPair(FacadeGame _data, StatisticPokemon _w) {
        return new TranslatedKeyPair(buildSi(_data, _w.getStatistic()), buildPk(_data, _w.getPokemon()));
    }
    private DictionaryComparator<TranslatedKey, Long> increasingMaxNbRoundTrapRepeat(StringMap<Long> _map) {
        DataBase data_ = getDataBase();
        DictionaryComparator<TranslatedKey, Long> out_;
        out_ = DictionaryComparatorUtil.buildMovesShort();
        for (String m: _map.getKeys()) {
            long nb_ = _map.getVal(m);
            nb_ += data_.getMove(m).getRepeatRoundLaw().maximum().ll();
            out_.put(buildMv(getFacade(),m), nb_);
        }
        return out_;
    }

    private DictionaryComparator<String, DictionaryComparator<Statistic, Long>> boostStatisTypes(ItemForBattle _item) {
        DataBase data_ = getDataBase();
        DictionaryComparator<String, DictionaryComparator<Statistic, Long>> boostStatisTypes_;
        boostStatisTypes_ = DictionaryComparatorUtil.buildTypesTypeDic(data_,getLanguage());
        for (EntryCust<String, IdMap<Statistic,Long>> t: _item.getBoostStatisTypes().entryList()) {
            DictionaryComparator<Statistic, Long> boost_;
            boost_ = DictionaryComparatorUtil.buildStatisByte(data_,getLanguage());
            for (EntryCust<Statistic, Long> s: t.getValue().entryList()) {
                boost_.put(s.getKey(), s.getValue());
            }
            boostStatisTypes_.put(t.getKey(), boost_);
        }
        return boostStatisTypes_;
    }

    private DictionaryComparator<TranslatedKey, Long> increasingMaxNbRoundTrap(StringMap<Long> _map) {
        DataBase data_ = getDataBase();
        DictionaryComparator<TranslatedKey, Long> out_;
        out_ = DictionaryComparatorUtil.buildMovesShort();
        for (String m: _map.getKeys()) {
            long nb_ = _map.getVal(m);
            nb_+=bonusEffect(data_, m);
            out_.put(buildMv(getFacade(),m), nb_);
        }
        return out_;
    }

    public static long bonusEffect(DataBase _data, String _move) {
        long add_ = 0;
        for (Effect e: _data.getMove(_move).getEffects()) {
            if (!(e instanceof EffectEndRoundSingleRelation)) {
                continue;
            }
            EffectEndRoundSingleRelation eff_ = (EffectEndRoundSingleRelation) e;
            add_ += eff_.getLawForEnablingEffect().maximum().ll();
        }
        return add_;
    }

    private CustList<TranslatedKey> typesPkAbilities() {
        CustList<TranslatedKey> typesPkAbilities_ = initTypesPkAbilities(getFacade());
        typesPkAbilities_.sortElts(DictionaryComparatorUtil.cmpAbilities());
        return typesPkAbilities_;
    }

    public static CustList<TranslatedKey> initTypesPkAbilities(FacadeGame _data) {
        CustList<TranslatedKey> typesPkAbilities_;
        typesPkAbilities_ = new CustList<TranslatedKey>();
        for (EntryCust<String, AbilityData> ability_: _data.getData().getAbilities().entryList()) {
            if (ability_.getValue().isPlate()) {
                typesPkAbilities_.add(buildAb(_data,ability_.getKey()));
            }
        }
        return typesPkAbilities_;
    }

    private void endRound(ItemForBattle _item) {
        DataBase data_ = getDataBase();
        EffectEndRound effect_;
        if (!_item.getEffectEndRound().isEmpty()) {
//            endRound = true;
            effect_ = _item.getEffectEndRound().first();
//            endRoundRank = effect_.getEndRoundRank();
//            reasonsEndRound = getFormattedReasons(data_, getReasons(effect_.getFailEndRound()), getLanguage());
//            mapVarsFailEndRound = getMapVarsFail(data_, effect_.getFailEndRound(), getLanguage());
        } else {
//            endRound = false;
            effect_ = null;
//            endRoundRank = 0;
//            reasonsEndRound = new StringList();
//            mapVarsFailEndRound = new NatStringTreeMap<String>();
        }
        endRoundCommon.endRondElts(data_,effect_,getLanguage());
    }

    public EndRoundCommon getEndRoundCommon() {
        return endRoundCommon;
    }
    public String getTrTypesPk(int _index) {
        String type_ = typesPk.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translatedTypes_.getVal(type_);
    }
    public String getTrTypesPkAbility(int _index) {
        return typesPkAbilities.get(_index).getTranslation();
//        String type_ = typesPkAbilities.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
//        return translatedAbilities_.getVal(type_);
    }
    public String clickTypesPkAbility(int _index) {
        return tryRedirect(typesPkAbilities.get(_index));
//        String type_ = typesPkAbilities.get(_index).getKey();
//        return tryRedirectAb(type_);
    }
    public String getTrImmuTypes(int _index) {
        String type_ = immuTypes.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        return translatedTypes_.getVal(type_);
    }
    public String getTrImmuStatus(int _index) {
        return immuStatus.get(_index).getTranslation();
//        String type_ = immuStatus.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
//        return translatedStatus_.getVal(type_);
    }
    public String clickImmuStatus(int _index) {
        return tryRedirect(immuStatus.get(_index));
    }
    public String getTrSynchroStatus(int _index) {
        return synchroStatus.get(_index).getTranslation();
//        String type_ = synchroStatus.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
//        return translatedStatus_.getVal(type_);
    }
    public String clickSynchroStatus(int _index) {
        return tryRedirect(synchroStatus.get(_index));
    }
    public String getTrFailStatus(int _index) {
        return failStatus.getKey(_index).getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedStatus_ = data_.getTranslatedStatus().getVal(getLanguage());
//        return translatedStatus_.getVal(type_);
    }
    public String clickFailStatus(int _index) {
        return tryRedirect(failStatus.getKey(_index));
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
        return multStatPokemonRank.getKey(_index).getFirst().getTranslation();
//        Statistic type_ = multStatPokemonRank.getKey(_index).getStatistic();
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        return translatedStatistics_.getVal(type_);
    }
    public String getTrMultStatPk(int _index) {
        return multStatPokemonRank.getKey(_index).getSecond().getTranslation();
//        String type_ = multStatPokemonRank.getKey(_index).getPokemon();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
//        return translatedPokemon_.getVal(type_);
    }
    public String clickMultStatPk(int _index) {
        return tryRedirect(multStatPokemonRank.getKey(_index).getSecond());
//        String type_ = multStatPokemonRank.getKey(_index).getPokemon();
//        return tryRedirectPk(type_);
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
//    public DictionaryComparator<Statistic, Integer> getMapMultStatisTypesStat(int _index) {
//        return boostStatisTypes.getValue(_index);
//    }
    public String getTrMultStatisSuperEff(int _index) {
        Statistic type_ = boostStatisSuperEff.getKey(_index);
        DataBase data_ = getDataBase();
        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
        return translatedStatistics_.getVal(type_);
    }
    public String getTrTrapMove(int _index) {
        return increasingMaxNbRoundTrap.getKey(_index).getTranslation();
//        String type_ = increasingMaxNbRoundTrap.getKey(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        return translatedMoves_.getVal(type_);
    }
    public String clickTrapMove(int _index) {
        return tryRedirect(increasingMaxNbRoundTrap.getKey(_index));
    }
    public String getTrGlobalMove(int _index) {
        return increasingMaxNbRoundGlobalMove.getKey(_index).getTranslation();
//        String type_ = increasingMaxNbRoundGlobalMove.getKey(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        return translatedMoves_.getVal(type_);
    }
    public String clickGlobalMove(int _index) {
        return tryRedirect(increasingMaxNbRoundGlobalMove.getKey(_index));
    }
    public String getTrTeamMove(int _index) {
        return increasingMaxNbRoundTeamMove.getKey(_index).getTranslation();
//        String type_ = increasingMaxNbRoundTeamMove.getKey(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        return translatedMoves_.getVal(type_);
    }
    public String clickTeamMove(int _index) {
        return tryRedirect(increasingMaxNbRoundTeamMove.getKey(_index));
    }
    public String getTrImmuMove(int _index) {
        return immuMoves.get(_index).getTranslation();
//        String type_ = immuMoves.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        return translatedMoves_.getVal(type_);
    }
    public String clickImmuMove(int _index) {
        return tryRedirect(immuMoves.get(_index));
    }
    public String getTrWeather(int _index) {
        return immuWeather.get(_index).getTranslation();
//        String type_ = immuWeather.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        return translatedMoves_.getVal(type_);
    }
    public String clickWeather(int _index) {
        return tryRedirect(immuWeather.get(_index));
    }
//    public String getTrHatching(int _index) {
//        String type_ = hatching.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
//        return translatedPokemon_.getVal(type_);
//    }
//    public String clickHatching(int _index) {
//        String type_ = hatching.get(_index);
//        getForms().put(CST_PK, type_);
//        return CST_POKEMON;
//    }
    public Rate rateForAttackFirst() {
        return rateTrue(lawForAttackFirst);
    }

    public boolean determinated() {
        return rateForAttackFirst().greaterOrEqualsOne();
    }

    public EffectWhileSendingWithStatistic getEffectSending() {
        return ((ItemForBattle)getItem()).getEffectSending().first();
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

    public DictionaryComparator<Statistic,Long> getMultStatRank() {
        return multStatRank;
    }

    public DictionaryComparator<TranslatedKeyPair,Long> getMultStatPokemonRank() {
        return multStatPokemonRank;
    }

    public DictionaryComparator<Statistic,Long> getBoostStatisSuperEff() {
        return boostStatisSuperEff;
    }

    public DictionaryComparator<String,DictionaryComparator<Statistic,Long>> getBoostStatisTypes() {
        return boostStatisTypes;
    }

    public DictionaryComparator<Statistic,String> getMultStat() {
        return multStat;
    }

    public DictionaryComparator<TranslatedKey,String> getFailStatus() {
        return failStatus;
    }

    public NatStringTreeMap<String> getMapVars() {
        return mapVars;
    }

    public StringList getTypesPk() {
        return typesPk;
    }

    public CustList<TranslatedKey> getTypesPkAbilities() {
        return typesPkAbilities;
    }

    public CustList<TranslatedKey> getImmuStatus() {
        return immuStatus;
    }

    public StringList getImmuTypes() {
        return immuTypes;
    }

    public CustList<TranslatedKey> getSynchroStatus() {
        return synchroStatus;
    }

    public DictionaryComparator<Statistic,Long> getWinEvFight() {
        return winEvFight;
    }

    public DictionaryComparator<TranslatedKey,Long> getIncreasingMaxNbRoundTrap() {
        return increasingMaxNbRoundTrap;
    }

    public DictionaryComparator<TranslatedKey,Long> getIncreasingMaxNbRoundGlobalMove() {
        return increasingMaxNbRoundGlobalMove;
    }

    public DictionaryComparator<TranslatedKey,Long> getIncreasingMaxNbRoundTeamMove() {
        return increasingMaxNbRoundTeamMove;
    }

    public CustList<TranslatedKey> getImmuMoves() {
        return immuMoves;
    }

    public CustList<TranslatedKey> getImmuWeather() {
        return immuWeather;
    }
}