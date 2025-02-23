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
import code.scripts.pages.aiki.*;
import code.util.*;
import code.util.core.NumberUtil;

public final class ItemForBattleBean extends ItemBean {
    static final String EFFECT_SEND_BEAN=PkScriptPages.REN_ADD_WEB_HTML_SENDING_EFFSENDING_HTML;
    private CustList<TranslatedKey> typesPk;
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
    private DictionaryComparator<TranslatedKey, Long> winEvFight;

    private MonteCarloBoolean lawForAttackFirst;
    private Rate multTrappingDamage;
    private Rate multWinningHappiness;
    private Rate multWinningEv;
    private Rate multWinningExp;
    private String multPower;
    private String multDamage;
    private Rate multDrainedHp;
    private Rate damageRecoil;
    private DictionaryComparator<TranslatedKey, Long> multStatRank;
    private DictionaryComparator<TranslatedKeyPair, Long> multStatPokemonRank;
    private DictionaryComparator<TranslatedKey,String> multStat;
    private DictionaryComparator<TranslatedKey, Long> increasingMaxNbRoundGlobalMove;
    private DictionaryComparator<TranslatedKey, Long> increasingMaxNbRoundTeamMove;
    private CustList<TranslatedKey> immuMoves;
//    private StringList hatching;
    private CustList<TranslatedKey> immuTypes;
    private CustList<TranslatedKey> immuWeather;
    private DictionaryComparator<TranslatedKey, Long> boostStatisSuperEff;
    private DictionaryComparator<TranslatedKey, DictionaryComparator<TranslatedKey, Long>> boostStatisTypes;
//    private boolean endRound;
//    private int endRoundRank;
//    private StringList reasonsEndRound;
//    private NatStringTreeMap<String> mapVarsFailEndRound;
    private boolean sending;
    private NatStringTreeMap<String> mapVars;
    private final EndRoundCommon endRoundCommon = new EndRoundCommon();

    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(_facade, _form);
        buildHeader();
        if (getEndRoundCommon().getEndRound()) {
            formatMessage(MessagesPkBean.EFF_ENDROUND,MessagesDataEffendround.M_P_47_RANK,Long.toString(getEndRoundCommon().getEndRoundRank()));
            formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_ENDROUND_ENDROUND_HTML,this),MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_ENDROUND);
            displayStringList(getEndRoundCommon().getReasonsEndRound(),MessagesPkBean.EFF_ENDROUND,MessagesDataEffendround.M_P_47_REASONS);
            mapVarsInit(getEndRoundCommon().getMapVarsFailEndRound());
        }
        if (sending) {
            displaySend(getEffectSending());
        }
        displayBoolTrue(toInt(cancelImmuType), MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_CANCEL_IMMU_TYPE);
        displayBoolTrue(toInt(againstEvo),MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_AGAINST_EVO);
        displayBoolTrue(toInt(attackLast),MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_ATTACK_LAST);
        displayBoolTrue(toInt(boostExp),MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_BOOST_EXP);
        displayBoolTrue(toInt(immuLowStatis),MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_IMMU_LOW_STATIS);
        displayBoolTrue(toInt(attacksSoon),MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_ATTACK_SOON);
        displayIntDef(protectAgainstKo,MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_PROTECT_KO);
        displayIntDef(protectAgainstKoIfFullHp,MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_PROTECT_KO_ALL);
        displayIntDef(drainedHpByDamageRate,MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_DRAINED_HP);
        displayIntDef(multTrappingDamage,MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_MULT_TRAPPING);
        displayIntDef(multWinningHappiness,MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_MULT_WIN_HAPPINESS);
        displayIntDef(multWinningEv,MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_MULT_WIN_EV);
        displayIntDef(multWinningExp,MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_MULT_WIN_EXP);
        displayIntDef(multDrainedHp,MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_MULT_DRAINED_HP);
        displayIntDef(damageRecoil,MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_DAMAGE_RECOIL);
        displayNotEmpty(multPower,MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_MULT_POWER);
        displayNotEmpty(multDamage,MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_MULT_DAMAGE);
        new BeanDisplayMap<TranslatedKey,Long>(new BeanDisplayTranslatedKey(),new BeanDisplayLong()).displayGrid(this,multStatRank,MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_MULT_STAT_RANK,MessagesDataItemsItemforbattle.M_P_28_MULT_STAT_RANK_KEY,MessagesDataItemsItemforbattle.M_P_28_MULT_STAT_RANK_BOOST);
        new BeanDisplayMap<TranslatedKeyPair,Long>(new BeanDisplayTranslatedKeyPair(),new BeanDisplayLong()).displayGrid(this,multStatPokemonRank,MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_MULT_STAT_POKEMON_RANK,MessagesDataItemsItemforbattle.M_P_28_MULT_STAT_POKEMON_RANK_STAT,MessagesDataItemsItemforbattle.M_P_28_MULT_STAT_POKEMON_RANK_NAME,MessagesDataItemsItemforbattle.M_P_28_MULT_STAT_POKEMON_RANK_VAR);
        new BeanDisplayMap<TranslatedKey,Long>(new BeanDisplayTranslatedKey(),new BeanDisplayLong()).displayGrid(this,boostStatisSuperEff,MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_BOOST_SUPER_EFF,MessagesDataItemsItemforbattle.M_P_28_BOOST_SUPER_EFF_STAT,MessagesDataItemsItemforbattle.M_P_28_BOOST_SUPER_EFF_BOOST);
        display(boostStatisTypes,MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_BOOST_TYPES);
        for (EntryCust<TranslatedKey,DictionaryComparator<TranslatedKey, Long>> e:boostStatisTypes.entryList()) {
            formatMessageDir(e.getKey());
            new BeanDisplayMap<TranslatedKey,Long>(new BeanDisplayTranslatedKey(),new BeanDisplayLong()).displayGrid(this,e.getValue(),MessagesPkBean.IT_ITEMFORBATTLE,"",MessagesDataItemsItemforbattle.M_P_28_BOOST_TYPES_STAT,MessagesDataItemsItemforbattle.M_P_28_BOOST_TYPES_BOOST);
        }
        new BeanDisplayMap<TranslatedKey,String>(new BeanDisplayTranslatedKey(),new BeanDisplayString()).displayGrid(this,multStat,MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_MULT_STAT,MessagesDataItemsItemforbattle.M_P_28_MULT_STAT_KEY,MessagesDataItemsItemforbattle.M_P_28_MULT_STAT_RATE);
        new BeanDisplayMap<TranslatedKey,String>(new BeanDisplayTranslatedKey(),new BeanDisplayString()).displayGrid(this,failStatus,MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_FAIL,MessagesDataItemsItemforbattle.M_P_28_FAIL_STATUS,MessagesDataItemsItemforbattle.M_P_28_FAIL_REASON);
        mapVarsInit(mapVars);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,typesPkAbilities, NumberUtil.signum(typesPk.size()),MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_TYPES_PK);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,typesPk,NumberUtil.signum(typesPkAbilities.size()),MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_TYPES_PK_2);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,immuStatus,MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_IMMU_STATUS);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,immuTypes,MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_IMMU_TYPES);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,synchroStatus,MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_SYNCHRO_STATUS);
        new BeanDisplayMap<TranslatedKey,Long>(new BeanDisplayTranslatedKey(),new BeanDisplayLong()).displayGrid(this,winEvFight,MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_WIN_EV,MessagesDataItemsItemforbattle.M_P_28_WIN_EV_STAT,MessagesDataItemsItemforbattle.M_P_28_WIN_EV_VALUE);
        new BeanDisplayMap<TranslatedKey,Long>(new BeanDisplayTranslatedKey(),new BeanDisplayLong()).displayGrid(this,increasingMaxNbRoundTrap,MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_INCREASING_TRAP,MessagesDataItemsItemforbattle.M_P_28_INCREASING_TRAP_MOVE,MessagesDataItemsItemforbattle.M_P_28_INCREASING_TRAP_NB);
        new BeanDisplayMap<TranslatedKey,Long>(new BeanDisplayTranslatedKey(),new BeanDisplayLong()).displayGrid(this,increasingMaxNbRoundGlobalMove,MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_INCREASING_GLOBAL,MessagesDataItemsItemforbattle.M_P_28_INCREASING_GLOBAL_MOVE,MessagesDataItemsItemforbattle.M_P_28_INCREASING_GLOBAL_NB);
        new BeanDisplayMap<TranslatedKey,Long>(new BeanDisplayTranslatedKey(),new BeanDisplayLong()).displayGrid(this,increasingMaxNbRoundTeamMove,MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_INCREASING_TEAM,MessagesDataItemsItemforbattle.M_P_28_INCREASING_TEAM_MOVE,MessagesDataItemsItemforbattle.M_P_28_INCREASING_TEAM_NB);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,immuMoves,MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_IMMU_MOVES);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,immuWeather,MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_IMMU_WEATHERS);
        if (determinated()) {
            formatMessage(MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_ATTACK_FIRST);
        } else {
            displayIntDef(rateForAttackFirst(),MessagesPkBean.IT_ITEMFORBATTLE,MessagesDataItemsItemforbattle.M_P_28_ATTACK_FIRST);
        }
    }

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
//        StringList typesPk_;
//        typesPk_ = new StringList();
//        for (String type_: item_.getTypesPk()) {
//            typesPk_.add(type_);
//        }
//        typesPk_.sortElts(DictionaryComparatorUtil.cmpTypes(data_,getLanguage()));
        typesPk = listTrStringsTy(item_.getTypesPk(),getFacade());
        typesPkAbilities = typesPkAbilities();
//        StringList immuTypes_;
//        immuTypes_ = new StringList();
//        for (String type_: item_.getImmuTypes()) {
//            immuTypes_.add(type_);
//        }
//        immuTypes_.sortElts(DictionaryComparatorUtil.cmpTypes(data_,getLanguage()));
        immuTypes = listTrStringsTy(item_.getImmuTypes(),getFacade());
        immuStatus = listTrStringsSt(item_.getImmuStatus(),getFacade());
        synchroStatus = listTrStringsSt(item_.getSynchroStatus(),getFacade());
        DictionaryComparator<TranslatedKey, Long> winEvFight_;
        winEvFight_ = DictionaryComparatorUtil.buildStatisShort();
        for (Statistic s: item_.getWinEvFight().getKeys()) {
            winEvFight_.put(buildSi(getFacade(),s), item_.getWinEvFight().getVal(s));
        }
        winEvFight = winEvFight_;
        DictionaryComparator<TranslatedKey, Long> multStatRank_;
        multStatRank_ = DictionaryComparatorUtil.buildStatisByte();
        for (Statistic s: item_.getMultStatRank().getKeys()) {
            multStatRank_.put(buildSi(getFacade(),s), item_.getMultStatRank().getVal(s));
        }
        multStatRank = multStatRank_;
        DictionaryComparator<TranslatedKeyPair, Long> multStatPokemonRank_;
        multStatPokemonRank_ = DictionaryComparatorUtil.buildStatPk();
        for (StatisticPokemon s: item_.getMultStatPokemonRank().getKeys()) {
            multStatPokemonRank_.put(buildPair(getFacade(),s), item_.getMultStatPokemonRank().getVal(s));
        }
        multStatPokemonRank = multStatPokemonRank_;
        NatStringTreeMap<String> mapVars_;
        mapVars_ = new NatStringTreeMap<String>();
        DictionaryComparator<TranslatedKey,String> multStat_;
        multStat_ = DictionaryComparatorUtil.buildStatisString();
        for (Statistic s: item_.getMultStat().getKeys()) {
            String formula_ = data_.getFormula(item_.getMultStat().getVal(s), getLanguage());
//            formula_ = formula_.replace(LEFT_BRACE, QUOTED_LEFT_BRACE);
//            formula_ = formula_.replace(RIGHT_BRACE, QUOTED_RIGHT_BRACE);
//            formula_ = StringList.replace(formula_, loc_);
            mapVars_.putAllMap(data_.getDescriptions(item_.getMultStat().getVal(s), getLanguage()));
            multStat_.put(buildSi(getFacade(),s), formula_);
        }
        multStat = multStat_;
        DictionaryComparator<TranslatedKey, Long> boostStatisSuperEff_;
        boostStatisSuperEff_ = DictionaryComparatorUtil.buildStatisByte();
        for (Statistic s: item_.getBoostStatisSuperEff().getKeys()) {
            boostStatisSuperEff_.put(buildSi(getFacade(),s), item_.getBoostStatisSuperEff().getVal(s));
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

    private DictionaryComparator<TranslatedKey, DictionaryComparator<TranslatedKey, Long>> boostStatisTypes(ItemForBattle _item) {
        DictionaryComparator<TranslatedKey, DictionaryComparator<TranslatedKey, Long>> boostStatisTypes_;
        boostStatisTypes_ = DictionaryComparatorUtil.buildTypesTypeDic();
        for (EntryCust<String, IdMap<Statistic,Long>> t: _item.getBoostStatisTypes().entryList()) {
            DictionaryComparator<TranslatedKey, Long> boost_;
            boost_ = DictionaryComparatorUtil.buildStatisByte();
            for (EntryCust<Statistic, Long> s: t.getValue().entryList()) {
                boost_.put(buildSi(getFacade(),s.getKey()), s.getValue());
            }
            boostStatisTypes_.put(buildTy(getFacade(),t.getKey()), boost_);
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
        return typesPk.get(_index).getTranslation();
//        String type_ = typesPk.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
//        return translatedTypes_.getVal(type_);
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
        return immuTypes.get(_index).getTranslation();
//        String type_ = immuTypes.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
//        return translatedTypes_.getVal(type_);
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
        return winEvFight.getKey(_index).getTranslation();
//        Statistic type_ = winEvFight.getKey(_index);
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        return translatedStatistics_.getVal(type_);
    }
    public String getTrMultStat(int _index) {
        return multStat.getKey(_index).getTranslation();
//        Statistic type_ = multStat.getKey(_index);
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        return translatedStatistics_.getVal(type_);
    }
    public String getTrMultStatRank(int _index) {
        return multStatRank.getKey(_index).getTranslation();
//        Statistic type_ = multStatRank.getKey(_index);
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        return translatedStatistics_.getVal(type_);
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
        return boostStatisTypes.getKey(_index).getTranslation();
//        String type_ = boostStatisTypes.getKey(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedStatistics_ = data_.getTranslatedTypes().getVal(getLanguage());
//        return translatedStatistics_.getVal(type_);
    }
    public String getTrMultStatisTypesStat(int _indexOne, int _indexTwo) {
        return boostStatisTypes.getValue(_indexOne).getKey(_indexTwo).getTranslation();
//        Statistic type_ = boostStatisTypes.getValue(_indexOne).getKey(_indexTwo);
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        return translatedStatistics_.getVal(type_);
    }
//    public DictionaryComparator<Statistic, Integer> getMapMultStatisTypesStat(int _index) {
//        return boostStatisTypes.getValue(_index);
//    }
    public String getTrMultStatisSuperEff(int _index) {
        return boostStatisSuperEff.getKey(_index).getTranslation();
//        Statistic type_ = boostStatisSuperEff.getKey(_index);
//        DataBase data_ = getDataBase();
//        AbsMap<Statistic,String> translatedStatistics_ = data_.getTranslatedStatistics().getVal(getLanguage());
//        return translatedStatistics_.getVal(type_);
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

    public DictionaryComparator<TranslatedKey,Long> getMultStatRank() {
        return multStatRank;
    }

    public DictionaryComparator<TranslatedKeyPair,Long> getMultStatPokemonRank() {
        return multStatPokemonRank;
    }

    public DictionaryComparator<TranslatedKey,Long> getBoostStatisSuperEff() {
        return boostStatisSuperEff;
    }

    public DictionaryComparator<TranslatedKey,DictionaryComparator<TranslatedKey,Long>> getBoostStatisTypes() {
        return boostStatisTypes;
    }

    public DictionaryComparator<TranslatedKey,String> getMultStat() {
        return multStat;
    }

    public DictionaryComparator<TranslatedKey,String> getFailStatus() {
        return failStatus;
    }

    public NatStringTreeMap<String> getMapVars() {
        return mapVars;
    }

    public CustList<TranslatedKey> getTypesPk() {
        return typesPk;
    }

    public CustList<TranslatedKey> getTypesPkAbilities() {
        return typesPkAbilities;
    }

    public CustList<TranslatedKey> getImmuStatus() {
        return immuStatus;
    }

    public CustList<TranslatedKey> getImmuTypes() {
        return immuTypes;
    }

    public CustList<TranslatedKey> getSynchroStatus() {
        return synchroStatus;
    }

    public DictionaryComparator<TranslatedKey,Long> getWinEvFight() {
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