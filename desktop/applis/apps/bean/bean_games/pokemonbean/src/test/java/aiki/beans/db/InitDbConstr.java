package aiki.beans.db;

import aiki.beans.*;
import aiki.beans.effects.*;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Ball;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.ExpType;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.fight.status.Status;
import aiki.fight.status.StatusSimple;
import aiki.fight.status.StatusType;
import aiki.fight.status.effects.EffectPartnerStatus;
import aiki.fight.util.LevelMove;
import aiki.fight.util.StatBaseEv;
import aiki.instances.Instances;
import aiki.map.characters.Ally;
import aiki.map.characters.Person;
import aiki.map.levels.AbsAreaApparition;
import aiki.map.places.Place;
import aiki.map.pokemon.*;
import aiki.util.Coords;
import code.bean.nat.*;
import code.bean.nat.analyze.NatConfigurationCore;
//import code.formathtml.Configuration;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.scripts.confs.*;
import code.scripts.pages.aiki.*;
import code.sml.util.*;
import code.util.*;

public abstract class InitDbConstr extends InitDbBean {

    public static final String B_NO = "B_NO";
    public static final String B_YES = "B_YES";
    public static final String TIME = "time";
    protected static final String TAB = "\t";
    protected static final String CST_ABILITIES = "abilities";
    protected static final String CST_ABILITIES_SET = "abilities_set";
    protected static final String CST_ABILITY = "ability";
    protected static final String CST_ALLY = "ally";
    protected static final String CST_AREA = "area";
    protected static final String CST_PERSON = "person";
    protected static final String CST_BALL = "ball";
    protected static final String CST_BERRY = "berry";
    protected static final String CST_BOOST = "boost";
    protected static final String CST_COMBO = "combo";
    protected static final String CST_SIMULATION = "simulation";
    protected static final String CST_SIMULATION_STATE = "simulation_state";
    //    protected static final String CST_CURRENT_TILE = "current_tile";
    protected static final String CST_DEALER = "dealer";
    protected static final String CST_DUAL = "dual";
    protected static final String CST_EVO_ITEM = "evo_item";
    protected static final String CST_EVO_STONE = "evo_stone";
    protected static final String CST_EVOLVINGITEM = "evolvingitem";
    protected static final String CST_EVOLVINGSTONE = "evolvingstone";
    protected static final String CST_FOSSIL = "fossil";
    protected static final String CST_FROM_LIST = "from_list";
    protected static final String CST_HEALINGHP = "healinghp";
    protected static final String CST_HEALINGHPSTATUS = "healinghpstatus";
    protected static final String CST_HEALINGITEM = "healingitem";
    protected static final String CST_HEALINGPP = "healingpp";
    protected static final String CST_HEALINGSTATUS = "healingstatus";
    //    protected static final String CST_INSIDE = "inside";
    protected static final String CST_ITEM = "item";
    protected static final String CST_ITEMFORBATTLE = "itemforbattle";
    protected static final String CST_ITEMS = "items";
    protected static final String CST_ITEMS_SET = "items_set";
    protected static final String CST_LEARNT = "learnt";
    protected static final String CST_LEARNT_MOVES = "learnt_moves";
    protected static final String CST_LEG_PK = "leg_pk";
    protected static final String CST_LEVEL = "level";
    //    protected static final String CST_LEVEL_MAP = "level_map";
    protected static final String CST_LEVEL_MAP_INDEX = "level_map_index";
    protected static final String CST_MOVE = "move";
    protected static final String CST_MOVES = "moves";
    protected static final String CST_MOVES_SET = "moves_set";
    protected static final String CST_OTHER_ITEM = "other_item";
    protected static final String CST_OTHER_WEATHER = "other_weather";
    protected static final String CST_PK = "pk";
    protected static final String CST_PLACE_MAP_INDEX = "place_map_index";
    protected static final String CST_POKEMON = "pokemon";
    protected static final String CST_POKEMON_SET = "pokemon_set";
    protected static final String CST_PROPONE_LINK = "propone_link";
    protected static final String CST_COORDS = "coords";
    protected static final String CST_NO_FIGHT = "no_fight";
    protected static final String CST_POKEMON_SET_SIMU = "pokemon_set_simu";
    protected static final String CST_POKEMON_ADDED = "pokemon_added";
    protected static final String CST_POKEMON_EDIT = "pokemon_edit";
    protected static final String CST_POKEMON_INDEX_EDIT = "pokemon_index_edit";
    protected static final String CST_POKEMON_NAME_EDIT = "pokemon_name_edit";
    protected static final String CST_POKEMON_LEVEL_EDIT = "pokemon_level_edit";
    protected static final String CST_POKEMON_EXPERIENCE = "pokemon_experience";
    protected static final String CST_POKEMON_HAPPINESS = "pokemon_happiness";
    protected static final String CST_POKEMON_HP = "pokemon_hp";
    protected static final String CST_POKEMON_EV_VAR = "pokemon_ev_";
    protected static final String CST_HEAL_EDIT_PK = "heal_edit_pk";
    protected static final String CST_CATCHING_BALL = "catching_ball";
    protected static final String CST_ITEM_EDIT = "item_edit";
    protected static final String CST_POKEMON_MOVES_EDIT = "pokemon_moves_edit";
    protected static final String CST_POKEMON_ABILITY_EDIT = "pokemon_ability_edit";
    protected static final String CST_POKEMON_GENDER_EDIT = "pokemon_gender_edit";
    protected static final String CST_POKEMON_FOE = "pokemon_foe";
    protected static final String CST_ADDING_TRAINER_PK = "adding_pk";
    protected static final String CST_POKEMON_ITEM_EDIT = "pokemon_item_edit";
    protected static final String CST_ITEMS_SET_EDIT = "items_set_edit";
    protected static final String CST_VALIDATE_TRAINER_PK = "validate_trainer_pk";
    protected static final String CST_ADD_POKEMON_PLAYER = "add_pokemon_player";
    protected static final String CST_EDIT_POKEMON_PLAYER = "edit_pokemon_player";
    protected static final String CST_IS_POKEMON_PLAYER_MOVES = "is_pokemon_player_moves";
    protected static final String CST_PK_NAME = "pk_name";
    /**exception naming*/
    protected static final String CST_PROPONE_LINK_VAR = "propone_link_";
    protected static final String CST_PROPONE_TILE = "propone_tile";
    protected static final String CST_REPEL = "repel";
    protected static final String CST_SEE_AREA = "see_area";
    protected static final String CST_SELLER = "seller";
    protected static final String CST_SELLINGITEM = "sellingitem";
    protected static final String CST_STATUS = "status";
    protected static final String CST_STATUS_SET = "status_set";
    protected static final String CST_TRAINER = "trainer";
    protected static final String CST_TRAINER_MULTI_FIGHT = "trainer_multi_fight";
    protected static final String CST_TRAINER_ONE_FIGHT = "trainer_one_fight";

    protected static final String A_ABILITY="A_ABILITY";
    protected static final String P_POKEMON="P_POKEMON";
    protected static final String P_PIKA="P_PIKA";
    protected static final String M_DAM="M_DAM";
    protected static final String M_DAM_VAR="M_DAM_VAR";
    protected static final String M_DAM_BAD="M_DAM_BAD";
    protected static final String M_DAM_VERY_BAD="M_DAM_VERY_BAD";
    protected static final String M_DAM_POW="M_DAM_POW";
    protected static final String M_STA="M_STA";
    protected static final String M_WEA="M_WEA";
    protected static final String T_TYPE1 ="T_TYPE1";
    protected static final String T_TYPE2 ="T_TYPE2";
    protected static final String I_ITEM="I_ITEM";
    protected static final String C_CAT="C_CAT";
    protected static final String S_STA_SIM="S_STA_SIM";
    protected static final String S_STA_REL="S_STA_REL";

    protected static final String A_ABILITY_TR="A_ABILITY_TR";
    protected static final String P_POKEMON_TR="P_POKEMON_TR";
    protected static final String P_PIKA_TR="P_PIKA_TR";
    protected static final String M_DAM_TR="M_DAM_TR";
    protected static final String M_DAM_POW_TR="M_DAM_POW_TR";
    protected static final String M_DAM_VAR_TR="M_DAM_VAR_TR";
    protected static final String M_DAM_BAD_TR="M_DAM_BAD_TR";
    protected static final String M_DAM_VERY_BAD_TR="M_DAM_VERY_BAD_TR";
    protected static final String M_STA_TR="M_STA_TR";
    protected static final String M_WEA_TR="M_WEA_TR";
    protected static final String T_TYPE1_TR ="T_TYPE1_TR";
    protected static final String T_TYPE2_TR ="T_TYPE2_TR";
    protected static final String I_ITEM_TR="I_ITEM_TR";
    protected static final String C_CAT1_TR="C_CAT1_TR";
    protected static final String C_CAT2_TR="C_CAT2_TR";
    protected static final String S_STA_SIM_TR="S_STA_SIM_TR";
    protected static final String S_STA_REL_TR="S_STA_REL_TR";
    protected static final String ST_ATT_TR="ST_ATT_TR";
    protected static final String ST_DEF_TR="ST_DEF_TR";
    protected static final String ST_ATT_SPE_TR="ST_ATT_SPE_TR";
    protected static final String ST_DEF_SPE_TR="ST_DEF_SPE_TR";
    protected static final String ST_SPEED_TR="ST_SPEED_TR";
    protected static final String ST_HP_TR="ST_HP_TR";
//    public static String navigateData(NatCaller _caller, NaSt _str, long... _args) {
//        PkScriptPagesInit.initConfData(new NatConfigurationCore());
//        return navigate(_caller, "", _str, _args);
//    }
    //    public static String navigateData(IntBeanAction _caller, NaSt _str) {
//        PkScriptPagesInit.initConfData(new NatConfigurationCore());
//        return _caller.actionBean();
//    }
    public static String navigateData(IntBeanAction _caller, NaSt _str) {
        String url_ = _caller.actionBean();
//        _caller.getBean();
//        _caller.getBean().getBuilder().build(url_,_caller.getBean().getForms());
        return url_;
    }
//    public static NaSt callEffectWhileSendingBeanEffectSet(NaSt _str, NaSt _args) {
//        return BeanPokemonCommonTs.callStruct(new EffectWhileSendingBeanEffectSet(),_str,_args);
//    }

    protected static FacadeGame facade() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        data_.initValue(DataBase.DEF_CAT,AUTRE);
        FacadeGame fac_ = new FacadeGame();
        fac_.setLanguages(indexes());
        StringMap<String> displayLanguages_ = new StringMap<String>();
        displayLanguages_.addEntry(EN,EN);
        fac_.setDisplayLanguages(displayLanguages_);
        fac_.setData(data_);
        data_.setMessages(fac_.getData());
        fac_.setLoadedData(true);
        fac_.setZipName("");
        fac_.setData(data_);
        fac_.setLanguage(EN);
        fac_.updateTrs();
        fac_.getData().setCombos(Instances.newCombos());
        return fac_;
    }
    protected static Status simple(StatusType _v) {
        StatusSimple s_ = Instances.newStatusSimple();
        s_.setStatusType(_v);
        return s_;
    }
    protected static DamagingMoveData moveDam(TargetChoice _t) {
        return moveDam(_t,"1", SwitchType.NOTHING, 0);
    }
    protected static DamagingMoveData moveDam(TargetChoice _t, String _acc, SwitchType _n, int _rk) {
        DamagingMoveData mv_ = Instances.newDamagingMoveData();
        feed(mv_, _t, _acc, _n, _rk, true, true, true, true, true, true, true, true, true, M_STA, M_WEA, 1, 1);
        feed(mv_, true, true, true);
        return mv_;
    }

    protected static void feed(DamagingMoveData _mv, boolean _s, boolean _c, boolean _d) {
        _mv.setCategory(C_CAT);
        _mv.setStoppableMoveKoSingle(_s);
        _mv.setCannotKo(_c);
        _mv.setDirect(_d);
    }

    protected static StatusMoveData moveSta(TargetChoice _t) {
        return moveSta(_t,"1", SwitchType.NOTHING, 0);
    }
    protected static StatusMoveData moveSta(TargetChoice _t, String _acc, SwitchType _n, int _rk) {
        StatusMoveData mv_ = Instances.newStatusMoveData();
        feed(mv_, _t, _acc, _n, _rk, true, true, true, true, true, true, true, true, true, M_STA, M_WEA, 1, 1);
        feed(mv_, true, true);
        return mv_;
    }

    protected static void feed(StatusMoveData _mv, boolean _t, boolean _c) {
        _mv.setThievableMove(_t);
        _mv.setCounterableMove(_c);
    }

    protected static void feed(MoveData _mv, TargetChoice _t, String _acc, SwitchType _n, int _rk, boolean _c, boolean _d, boolean _an, boolean _ep, boolean _rech, boolean _sec, boolean _multi, boolean _prio, boolean _solo, String _achieve, String _wea, int _prepa, int _prioNb) {
        _mv.setTargetChoice(_t);
        _mv.setAccuracy(_acc);
        _mv.setNbPrepaRound( _prepa);
        _mv.setPp( 1);
        _mv.setPriority(_prioNb);
        _mv.setSwitchType(_n);
        _mv.setRankIncrementNbRound( _rk);
        _mv.setBoostedTypes(new StringList(T_TYPE1));
        _mv.setTypes(new StringList(T_TYPE1));
        _mv.setAchieveDisappearedPkUsingMove(new StringList(_achieve));
        StringMap<String> t_ = new StringMap<String>();
        t_.addEntry(I_ITEM, T_TYPE1);
        _mv.setTypesByOwnedItem(t_);
        StringMap<String> w_ = new StringMap<String>();
        w_.addEntry(_wea, T_TYPE1);
        _mv.setTypesByWeather(w_);
        _mv.setDeletedStatus(new StringList(S_STA_SIM));
        _mv.setRequiredStatus(new StringList(S_STA_REL));
        StringMap<Ints> sec_ = new StringMap<Ints>();
        sec_.addEntry(I_ITEM,Ints.newList(0));
        _mv.setSecEffectsByItem(sec_);
        MonteCarloNumber r_ = new MonteCarloNumber();
        r_.addQuickEvent(Rate.one(), LgInt.one());
        _mv.setRepeatRoundLaw(r_);
        _mv.setConstUserChoice(_c);
        _mv.setDisappearBeforeUse(_d);
        _mv.setIgnVarAccurUserNeg(_an);
        _mv.setIgnVarEvasTargetPos(_ep);
        _mv.setRechargeRound(_rech);
        _mv.setSecEffectIfNoDamage(_sec);
        _mv.setStoppableMoveMulti(_multi);
        _mv.setStoppableMovePrio(_prio);
        _mv.setStoppableMoveSolo(_solo);
    }
    protected static Ball ball() {
        return ball("1");
    }
    protected static Ball ball(String _r) {
        Ball b_ = Instances.newBall();
        b_.setCatchingRate(_r);
        b_.setPrice(1);
        return b_;
    }
    protected static Status staSimple(String _f) {
        return staSimple(_f,1,true,true);
    }
    protected static Status staRel(String _f) {
        return staRel(_f,1,true,true);
    }
    protected static Status staSimple(String _f, int _i, boolean _inc, boolean _d) {
        StatusSimple st_ = Instances.newStatusSimple();
        feed(_f, _i, _inc, _d, st_, StatusType.INDIVIDUEL);
        return st_;
    }
    protected static Status staRel(String _f, int _i, boolean _inc, boolean _d) {
        StatusSimple st_ = Instances.newStatusSimple();
        feed(_f, _i, _inc, _d, st_, StatusType.RELATION_UNIQUE);
        return st_;
    }

    private static void feed(String _f, int _i, boolean _inc, boolean _d, Status _st, StatusType _stType) {
        _st.setStatusType(_stType);
        _st.setCatchingRate(Rate.one());
        _st.setFail(_f);
        _st.setIncrementEndRound(_i);
        IdMap<Statistic, Rate> ev_ = new IdMap<Statistic, Rate>();
        ev_.addEntry(Statistic.EVASINESS,Rate.one());
        _st.setMultStat(ev_);
        _st.setIncrementingEndRound(_inc);
        _st.setDisabledEffIfSwitch(_d);
    }

    protected static PokemonData pk(StringList _g, GenderRepartition _rep) {
        PokemonData pk_ = Instances.newPokemonData();
        pk_.setBaseEvo(P_POKEMON);
        pk_.setTypes(new StringList(T_TYPE1));
        pk_.setAbilities(new StringList(A_ABILITY));
        pk_.setHeight(Rate.one());
        pk_.setWeight(Rate.one());
        pk_.setHappiness( 1);
        pk_.setHappinessHatch( 1);
        pk_.setExpRate(1);
        pk_.setCatchingRate( 1);
        pk_.setExpEvo(ExpType.E);
        pk_.setGenderRep(_rep);
        pk_.setHatchingSteps(LgInt.one());
        pk_.setHiddenMoves(Ints.newList(1));
        pk_.setTechnicalMoves(Ints.newList(1));
        pk_.setEggGroups(_g);
        pk_.setMoveTutors(new StringList(M_DAM));
        CustList<LevelMove> lv_ = new CustList<LevelMove>();
        lv_.add(new LevelMove( 1,M_DAM));
        lv_.add(new LevelMove( 3,M_STA));
        pk_.setLevMoves(lv_);
        statAdv(pk_);
        return pk_;
    }

    protected static void feedTm(IntMap< String> _tm, IntMap< LgInt> _tmPrice) {
        feedTm(_tm, _tmPrice, 1,M_DAM);
    }
    protected static void feedHm(IntMap< String> _hm) {
        feedHm(_hm, 1,M_STA);
    }
    protected static void feedTm(IntMap< String> _tm, IntMap< LgInt> _tmPrice, int _nb, String _m) {
        _tm.addEntry(_nb,_m);
        _tmPrice.addEntry(_nb,LgInt.one());
    }
    protected static void feedHm(IntMap< String> _hm, int _nb, String _m) {
        _hm.addEntry(_nb,_m);
    }

    protected static void statAdv(PokemonData _pk) {
        _pk.getStatistics().addEntry(Statistic.ATTACK,new StatBaseEv(1,1));
        _pk.getStatistics().addEntry(Statistic.DEFENSE,new StatBaseEv(1,1));
        _pk.getStatistics().addEntry(Statistic.SPECIAL_ATTACK,new StatBaseEv(1,1));
        _pk.getStatistics().addEntry(Statistic.SPECIAL_DEFENSE,new StatBaseEv(1,1));
        _pk.getStatistics().addEntry(Statistic.SPEED,new StatBaseEv(1,1));
        _pk.getStatistics().addEntry(Statistic.HP,new StatBaseEv(1,1));
    }

    public static PkData pkDataByFacade(FacadeGame _dataBase) {
        PkData pk_ = new PkData();
        pk_.setDataBase(_dataBase);
        _dataBase.getData().getTranslatedStatistics().tryAdd(EN,new IdMap<Statistic, String>());
        _dataBase.getData().getLitterals().tryAdd(EN,new StringMap<String>());
        _dataBase.updateTrs();
//        pk_.setBaseEncode(BASE);
        return pk_;
    }

    public static NaSt callEffectWhileSendingBeanCancelChgtStatGet(NaSt _str, int... _args) {
        return PokemonStandards.getValues(((EffectWhileSendingBean) ((PokemonBeanStruct) _str).getInstance()).getEffectStatisticCommon().getCancelChgtStat());
    }

    public static NaSt callEffectWhileSendingBeanCancelLowStatGet(NaSt _str, int... _args) {
        return PokemonStandards.getValues(((EffectWhileSendingBean) ((PokemonBeanStruct) _str).getInstance()).getEffectStatisticCommon().getCancelLowStat());
    }

    public static String callEffectWhileSendingBeanClickWeather(NaSt _str, int... _args) {
        return ( (EffectWhileSendingBean) ((PokemonBeanStruct)_str).getInstance()).clickWeather();
    }

    public static String callEffectWhileSendingBeanClickWeatherId(NaSt _str, int... _args) {
        callEffectWhileSendingBeanClickWeather(_str, _args);
        return getValMoveId(_str);
    }

    public static NaSt callEffectWhileSendingBeanCopyBoostGet(NaSt _str, int... _args) {
        return PokemonStandards.getValues(((EffectWhileSendingBean) ((PokemonBeanStruct) _str).getInstance()).getEffectStatisticCommon().getCopyBoost());
    }

    public static boolean callEffectWhileSendingBeanCopyingAbilityGet(NaSt _str, int... _args) {
        return ( (EffectWhileSendingBean) ((PokemonBeanStruct)_str).getInstance()).getCopyingAbility();
    }

    public static long callEffectWhileSendingBeanDefaultBoostGet(NaSt _str, int... _args) {
        return ((EffectWhileSendingBean) ((PokemonBeanStruct) _str).getInstance()).getEffectStatisticCommon().getDefaultBoost();
    }

    public static boolean callEffectWhileSendingBeanDisableWeatherGet(NaSt _str, int... _args) {
        return ( (EffectWhileSendingBean) ((PokemonBeanStruct)_str).getInstance()).getDisableWeather();
    }

    public static String callEffectWhileSendingBeanEnabledWeatherGet(NaSt _str, int... _args) {
        return ( (EffectWhileSendingBean) ((PokemonBeanStruct)_str).getInstance()).getEnabledWeather().getKey();
    }

    public static Rate callEffectWhileSendingBeanEvtRateGet(NaSt _str, int... _args) {
        return ((EffectWhileSendingBean) ((PokemonBeanStruct) _str).getInstance()).getEffectStatisticCommon().getEvtRate();
    }

    public static String callEffectWhileSendingBeanEvtRatePerCentGet(NaSt _str, int... _args) {
        return ((EffectWhileSendingBean) ((PokemonBeanStruct) _str).getInstance()).getEffectStatisticCommon().getEvtRatePerCent();
    }

    public static String callEffectWhileSendingBeanGetFail(NaSt _str, int... _args) {
        return ((EffectWhileSendingBean) ((PokemonBeanStruct) _str).getInstance()).getEffectStatisticCommon().getFail(_args[0]);
    }

    public static Rate callEffectWhileSendingBeanGetRate(NaSt _str, int... _args) {
        return ((EffectWhileSendingBean) ((PokemonBeanStruct) _str).getInstance()).getEffectStatisticCommon().getRate(_args[0]);
    }

    public static String callEffectWhileSendingBeanGetSwapFail(NaSt _str, int... _args) {
        return ((EffectWhileSendingBean) ((PokemonBeanStruct) _str).getInstance()).getEffectStatisticCommon().getSwapFail(_args[0]);
    }

    public static String callEffectWhileSendingBeanGetTrWeather(NaSt _str, int... _args) {
        return ( (EffectWhileSendingBean) ((PokemonBeanStruct)_str).getInstance()).getTrWeather();
    }

    public static boolean callEffectWhileSendingBeanIsAlwaysEnabled(NaSt _str, int... _args) {
        return ((EffectWhileSendingBean) ((PokemonBeanStruct) _str).getInstance()).getEffectStatisticCommon().isAlwaysEnabled();
    }

    public static NaSt callEffectWhileSendingBeanMapVarsFailGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrStr(( (EffectWhileSendingBean) ((PokemonBeanStruct)_str).getInstance()).getMapVarsFail());
    }

    public static NaSt callEffectWhileSendingBeanMapVarsStatisticsGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrStr(((EffectWhileSendingBean) ((PokemonBeanStruct) _str).getInstance()).getEffectStatisticCommon().getMapVarsStatistics());
    }

    public static Rate callEffectWhileSendingBeanMultWeightGet(NaSt _str, int... _args) {
        return ( (EffectWhileSendingBean) ((PokemonBeanStruct)_str).getInstance()).getMultWeight();
    }

    public static boolean callEffectWhileSendingBeanNotEmptyVarBoost(NaSt _str, int... _args) {
        return ((EffectWhileSendingBean) ((PokemonBeanStruct) _str).getInstance()).getEffectStatisticCommon().notEmptyVarBoost();
    }

//    public static Struct callEffectWhileSendingBeanPlateGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new EffectWhileSendingBeanPlateGet(),_str,_args);
//    }

    public static boolean callEffectWhileSendingBeanRandomStatis(NaSt _str, int... _args) {
        return ((EffectWhileSendingBean) ((PokemonBeanStruct) _str).getInstance()).getEffectStatisticCommon().randomStatis();
    }

    public static NaSt callEffectWhileSendingBeanReasonsGet(NaSt _str, int... _args) {
        return BeanNatCommonLgNames.getStringArray(( (EffectWhileSendingBean) ((PokemonBeanStruct)_str).getInstance()).getReasons());
    }

    public static NaSt callEffectWhileSendingBeanStatisVarRankGet(NaSt _str, int... _args) {
        return PokemonStandards.getStrLongStat(((EffectWhileSendingBean) ((PokemonBeanStruct) _str).getInstance()).getEffectStatisticCommon().getStatisVarRank());
    }

    public static boolean callEffectWhileSendingBeanStatisticGet(NaSt _str, int... _args) {
        return ( (EffectWhileSendingBean) ((PokemonBeanStruct)_str).getInstance()).getStatistic();
    }

    public static NaSt callEffectWhileSendingBeanSwapBoostStatisGet(NaSt _str, int... _args) {
        return PokemonStandards.getValues(((EffectWhileSendingBean) ((PokemonBeanStruct) _str).getInstance()).getEffectStatisticCommon().getSwapBoostStatis().getKeys());
    }
//
//    public static NaSt callSimulationBeanSelectedTeamNumberGet(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new SimulationBeanSelectedTeamNumberGet(),_str,_args);
//    }

    public static long callBoostHpRateGetBoost(NaSt _str, int... _args) {
        return ( ((BoostHpRateStruct) _str).getBoostHpRate()).getBoost();
    }

    public static Rate callBoostHpRateGetHpRate(NaSt _str, int... _args) {
        return ( ((BoostHpRateStruct) _str).getBoostHpRate()).getHpRate();
    }

    public static String callCategoryMultGetCategory(NaSt _str, int... _args) {
        return ( ((CategoryMultStruct) _str).getCategoryMult()).getCategory();
    }

    public static long callCategoryMultGetMult(NaSt _str, int... _args) {
        return ( ((CategoryMultStruct) _str).getCategoryMult()).getMult();
    }

    public static Rate callEfficiencyRateGetEff(NaSt _str, int... _args) {
        return ( ((EfficiencyRateStruct) _str).getEfficiencyRate()).getEff();
    }

    public static Rate callEfficiencyRateGetHpRate(NaSt _str, int... _args) {
        return ( ((EfficiencyRateStruct) _str).getEfficiencyRate()).getHpRate();
    }
    public static Rate callEffectPartnerStatusGetMultDamageAgainstFoe(EffectPartnerStatus _str, int... _args) {
        return _str.getMultDamageAgainstFoe();
    }

    public static Rate callEffectPartnerStatusGetRestoredHpRateLovedAlly(EffectPartnerStatus _str, int... _args) {
        return _str.getRestoredHpRateLovedAlly();
    }

    public static boolean callEffectPartnerStatusGetWeddingAlly(EffectPartnerStatus _str, int... _args) {
        return _str.getWeddingAlly();
    }

    public static String callPkTrainerGetItem(NaSt _str, int... _args) {
        return ( (PkTrainer) ((PkStruct)_str).getWildPk()).getItem();
    }

    public static long callPkTrainerGetLevel(NaSt _str, int... _args) {
        return ( (PkTrainer) ((PkStruct)_str).getWildPk()).getLevel();
    }

    public static NaSt callPkTrainerGetMoves(NaSt _str, int... _args) {
        return BeanNatCommonLgNames.getStringArray(( (PkTrainer) ((PkStruct)_str).getWildPk()).getMoves());
    }
    public static String callPlaceGetName(Place _str, int... _args) {
        return _str.getName();
    }

    public static long callPokemonPlayerGetHappiness(NaSt _str, int... _args) {
        return ( (PokemonPlayer) ((PkStruct)_str).getWildPk()).getHappiness();
    }

    public static String callPokemonPlayerGetItem(NaSt _str, int... _args) {
        return ( (PokemonPlayer) ((PkStruct)_str).getWildPk()).getItem();
    }

    public static Rate callPokemonPlayerGetWonExpSinceLastLevel(NaSt _str, int... _args) {
        return ( (PokemonPlayer) ((PkStruct)_str).getWildPk()).getWonExpSinceLastLevel();
    }


    public static Rate callTypeDamageBoostGetBoost(NaSt _str, int... _args) {
        return ( ((TypeDamageBoostStruct) _str).getTypeDamageBoost()).getBoost();
    }

    public static String callTypesDuoGetDamageType(NaSt _str, int... _args) {
        return ( ((TypesDuoStruct) _str).getTypesDuo()).getDamageType();
    }

    public static String callTypesDuoGetPokemonType(NaSt _str, int... _args) {
        return ( ((TypesDuoStruct) _str).getTypesDuo()).getPokemonType();
    }

    public static String callWildPkGetItem(Pokemon _str, int... _args) {
        return _str.getItem();
    }

    public static long callWildPkGetLevel(Pokemon _str, int... _args) {
        return _str.getLevel();
    }

    protected static String getValItemId(NaSt _bean) {
        return forms(_bean).getValStr(CST_ITEM);
    }

    protected static String getValMoveId(NaSt _bean) {
        return forms(_bean).getValStr(CST_MOVE);
    }

    protected static String getValPkId(NaSt _bean) {
        return forms(_bean).getValStr(CST_PK);
    }

    protected static String getValStatusId(NaSt _bean) {
        return forms(_bean).getValStr(CST_STATUS);
    }

    protected static String getValAbilityId(NaSt _bean) {
        return forms(_bean).getValStr(CST_ABILITY);
    }

    protected static boolean containsPlaceLevelId(NaSt _bean) {
        return forms(_bean).contains(CST_COORDS);
    }

    protected static Coords getValPlaceLevelId(NaSt _bean) {
        return forms(_bean).getValCoords(CST_COORDS);
    }

    protected static String getValItemId(CommonBean _bean) {
        return _bean.getForms().getValStr(CST_ITEM);
    }

    protected static String getValMoveId(CommonBean _bean) {
        return _bean.getForms().getValStr(CST_MOVE);
    }

    protected static String getValPkId(CommonBean _bean) {
        return _bean.getForms().getValStr(CST_PK);
    }

    protected static String getValStatusId(CommonBean _bean) {
        return _bean.getForms().getValStr(CST_STATUS);
    }

    protected static String getValAbilityId(CommonBean _bean) {
        return _bean.getForms().getValStr(CST_ABILITY);
    }

    protected static boolean containsPlaceLevelId(CommonBean _bean) {
        return _bean.getForms().contains(CST_COORDS);
    }

    protected static Coords getValPlaceLevelId(CommonBean _bean) {
        return _bean.getForms().getValCoords(CST_COORDS);
    }

    protected static String getValItemId(StringMapObject _bean) {
        return _bean.getValStr(CST_ITEM);
    }

    protected static String getValMoveId(StringMapObject _bean) {
        return _bean.getValStr(CST_MOVE);
    }

    protected static String getValPkId(StringMapObject _bean) {
        return _bean.getValStr(CST_PK);
    }

    protected static String getValStatusId(StringMapObject _bean) {
        return _bean.getValStr(CST_STATUS);
    }

    protected static String getValAbilityId(StringMapObject _bean) {
        return _bean.getValStr(CST_ABILITY);
    }

    protected static boolean containsPlaceLevelId(StringMapObject _bean) {
        return _bean.contains(CST_COORDS);
    }

    protected static Coords getValPlaceLevelId(StringMapObject _bean) {
        return _bean.getValCoords(CST_COORDS);
    }
//
//    protected static Point getValPointInside(Struct _bean) {
//        return forms(_bean).getValPt(CST_INSIDE);
//    }
//
//    protected static Point getValCurrentTile(Struct _bean) {
//        return forms(_bean).getValPt(CST_CURRENT_TILE);
//    }

//    protected static AbsAreaApparition getValArea(NaSt _bean) {
//        return forms(_bean).getValArea(CST_AREA);
//    }
//    protected static Person getValPers(NaSt _key){
//        return forms(_key).getValPers(CST_PERSON);
//    }
//    protected static Ally getValAlly(NaSt _key){
//        return forms(_key).getValAlly(CST_ALLY);
//    }
//    protected static WildPk getValPk(NaSt _key){
//        return forms(_key).getValPk(CST_LEG_PK);
//    }

    public static Place callPlaceIndexGetPlace(NaSt _str, int... _args) {
        return ( ((PlaceIndexStruct) _str).getPlaceIndex()).getPlace();
    }

    public static long callPlaceIndexIndexGet(NaSt _str, int... _args) {
        return ( ((PlaceIndexStruct) _str).getPlaceIndex()).getIndex();
    }
    protected static WelcomeBean beanWelcomeBean(PkData _pk,String _lg) {
        WelcomeBean b_ = new WelcomeBean();
        _pk.bean(b_, _lg);
        MockBeanBuilderHelper bu_ = builder(_pk.getDataBase());
        bu_.getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_INDEX_HTML,b_);
        b_.setBuilder(bu_);
        return b_;
    }

    protected static MockBeanBuilderHelper builder(FacadeGame _facade) {
        MockBeanBuilderHelper bu_ = new MockBeanBuilderHelper();
        Translations tr_ = new Translations();
        TranslationsLg en_ = new TranslationsLg();
        en_.getMapping().addEntry(MessagesPkBean.APP_BEAN_DATA, new TranslationsAppli());
        en_.getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.INDEX,new TranslationsFile());
        tr_.getMapping().addEntry(EN, en_);
        TranslationsLg fr_ = new TranslationsLg();
        fr_.getMapping().addEntry(MessagesPkBean.APP_BEAN_DATA, new TranslationsAppli());
        fr_.getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.INDEX,new TranslationsFile());
        tr_.getMapping().addEntry(FR, fr_);
        bu_.setTranslations(tr_);
        bu_.setFacade(_facade);
        return bu_;
    }
}
