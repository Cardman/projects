package aiki.beans.db;

import aiki.beans.BeanPokemonCommonTs;
import aiki.beans.InitDbBean;
import aiki.beans.PkData;
import aiki.beans.effects.EffectWhileSendingBeanEffectSet;
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
import aiki.fight.util.LevelMove;
import aiki.fight.util.StatBaseEv;
import aiki.instances.Instances;
import code.bean.nat.NatCaller;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.scripts.confs.PkScriptPagesInit;
import code.util.*;

public abstract class InitDbConstr extends InitDbBean {
    protected static final String CST_ABILITIES = "abilities";
    protected static final String CST_ABILITIES_SET = "abilities_set";
    protected static final String CST_ABILITY = "ability";
    protected static final String CST_ALLY = "ally";
    protected static final String CST_AREA = "area";
    protected static final String CST_BALL = "ball";
    protected static final String CST_BERRY = "berry";
    protected static final String CST_BOOST = "boost";
    protected static final String CST_COMBO = "combo";
    protected static final String CST_SIMULATION = "simulation";
    protected static final String CST_SIMULATION_STATE = "simulation_state";
    protected static final String CST_CURRENT_TILE = "current_tile";
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
    protected static final String CST_INSIDE = "inside";
    protected static final String CST_ITEM = "item";
    protected static final String CST_ITEMFORBATTLE = "itemforbattle";
    protected static final String CST_ITEMS = "items";
    protected static final String CST_ITEMS_SET = "items_set";
    protected static final String CST_LEARNT = "learnt";
    protected static final String CST_LEARNT_MOVES = "learnt_moves";
    protected static final String CST_LEG_PK = "leg_pk";
    protected static final String CST_LEVEL = "level";
    protected static final String CST_LEVEL_MAP = "level_map";
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
    protected static final String M_DAM="M_DAM";
    protected static final String M_STA="M_STA";
    protected static final String M_WEA="M_WEA";
    protected static final String T_TYPE="T_TYPE";
    protected static final String I_ITEM="I_ITEM";
    protected static final String C_CAT="C_CAT";
    protected static final String S_STA_SIM="S_STA_SIM";
    protected static final String S_STA_REL="S_STA_REL";
    protected static final String A_ABILITY_TR="A_ABILITY_TR";
    protected static final String P_POKEMON_TR="P_POKEMON_TR";
    protected static final String M_DAM_TR="M_DAM_TR";
    protected static final String M_STA_TR="M_STA_TR";
    protected static final String M_WEA_TR="M_WEA_TR";
    protected static final String T_TYPE_TR="T_TYPE_TR";
    protected static final String I_ITEM_TR="I_ITEM_TR";
    protected static final String C_CAT_TR="C_CAT_TR";
    protected static final String S_STA_SIM_TR="S_STA_SIM_TR";
    protected static final String S_STA_REL_TR="S_STA_REL_TR";
    protected static final String ST_ATT_TR="ST_ATT_TR";
    protected static final String ST_DEF_TR="ST_DEF_TR";
    protected static final String ST_ATT_SPE_TR="ST_ATT_SPE_TR";
    protected static final String ST_DEF_SPE_TR="ST_DEF_SPE_TR";
    protected static final String ST_SPEED_TR="ST_SPEED_TR";
    protected static final String ST_HP_TR="ST_HP_TR";
    public static String navigateData(NatCaller _caller, String _url, String _concat, Struct _str, long... _args) {
        return navigate(_caller,_url, PkScriptPagesInit.initConfData(new Configuration()),_concat,_str,_args);
    }
    public static Struct callEffectWhileSendingBeanEffectSet(Struct _str, Struct _args) {
        return BeanPokemonCommonTs.callStruct(new EffectWhileSendingBeanEffectSet(),_str,_args);
    }

    protected static FacadeGame facade() {
        DataBase data_ = newData();
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        FacadeGame fac_ = new FacadeGame();
        fac_.setLanguages(new StringList(EN));
        StringMap<String> displayLanguages_ = new StringMap<String>();
        displayLanguages_.addEntry(EN,EN);
        fac_.setDisplayLanguages(displayLanguages_);
        fac_.setData(data_);
        data_.setMessages(fac_.getData());
        fac_.setLoadedData(true);
        fac_.setZipName("");
        fac_.setData(data_);
        fac_.setLanguage(EN);
        return fac_;
    }
    protected static MoveData moveDam(TargetChoice _t) {
        return moveDam(_t,"1", SwitchType.NOTHING, 0);
    }
    protected static MoveData moveDam(TargetChoice _t, String _acc, SwitchType _n, int _rk) {
        DamagingMoveData mv_ = Instances.newDamagingMoveData();
        feed(mv_, _t, _acc, _n, _rk, true, true, true, true, true, true, true, true, true);
        feed(mv_, true, true, true);
        return mv_;
    }

    protected static void feed(DamagingMoveData _mv, boolean _s, boolean _c, boolean _d) {
        _mv.setCategory(C_CAT);
        _mv.setStoppableMoveKoSingle(_s);
        _mv.setCannotKo(_c);
        _mv.setDirect(_d);
    }

    protected static MoveData moveSta(TargetChoice _t) {
        return moveSta(_t,"1", SwitchType.NOTHING, 0);
    }
    protected static MoveData moveSta(TargetChoice _t, String _acc, SwitchType _n, int _rk) {
        StatusMoveData mv_ = Instances.newStatusMoveData();
        feed(mv_, _t, _acc, _n, _rk, true, true, true, true, true, true, true, true, true);
        feed(mv_, true, true);
        return mv_;
    }

    private static void feed(StatusMoveData _mv, boolean _t, boolean _c) {
        _mv.setThievableMove(_t);
        _mv.setCounterableMove(_c);
    }

    protected static void feed(MoveData _mv, TargetChoice _t, String _acc, SwitchType _n, int _rk, boolean _c, boolean _d, boolean _an, boolean _ep, boolean _rech, boolean _sec, boolean _multi, boolean _prio, boolean _solo) {
        _mv.setTargetChoice(_t);
        _mv.setAccuracy(_acc);
        _mv.setNbPrepaRound((short) 1);
        _mv.setPp((short) 1);
        _mv.setPriority((byte) 1);
        _mv.setSwitchType(_n);
        _mv.setRankIncrementNbRound((short) _rk);
        _mv.setBoostedTypes(new StringList(T_TYPE));
        _mv.setTypes(new StringList(T_TYPE));
        _mv.setAchieveDisappearedPkUsingMove(new StringList(M_STA));
        StringMap<String> t_ = new StringMap<String>();
        t_.addEntry(I_ITEM,T_TYPE);
        _mv.setTypesByOwnedItem(t_);
        StringMap<String> w_ = new StringMap<String>();
        w_.addEntry(M_WEA,T_TYPE);
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
        pk_.setTypes(new StringList(T_TYPE));
        pk_.setAbilities(new StringList(A_ABILITY));
        pk_.setHeight(Rate.one());
        pk_.setWeight(Rate.one());
        pk_.setHappiness((short) 1);
        pk_.setHappinessHatch((short) 1);
        pk_.setExpRate(1);
        pk_.setCatchingRate((short) 1);
        pk_.setExpEvo(ExpType.E);
        pk_.setGenderRep(_rep);
        pk_.setHatchingSteps(LgInt.one());
        pk_.setHiddenMoves(Shorts.newList((short) 1));
        pk_.setTechnicalMoves(Shorts.newList((short) 1));
        pk_.setEggGroups(_g);
        pk_.setMoveTutors(new StringList(M_DAM));
        CustList<LevelMove> lv_ = new CustList<LevelMove>();
        lv_.add(new LevelMove((short) 1,M_DAM));
        lv_.add(new LevelMove((short) 3,M_STA));
        pk_.setLevMoves(lv_);
        statAdv(pk_);
        return pk_;
    }

    protected static void feedTm(ShortMap< String> _tm, ShortMap< LgInt> _tmPrice) {
        feedTm(_tm, _tmPrice, 1,M_DAM);
    }
    protected static void feedHm(ShortMap< String> _hm) {
        feedHm(_hm, 1,M_STA);
    }
    protected static void feedTm(ShortMap< String> _tm, ShortMap< LgInt> _tmPrice, int _nb, String _m) {
        _tm.addEntry((short)_nb,_m);
        _tmPrice.addEntry((short)_nb,LgInt.one());
    }
    protected static void feedHm(ShortMap< String> _hm, int _nb, String _m) {
        _hm.addEntry((short)_nb,_m);
    }

    protected static void statAdv(PokemonData _pk) {
        _pk.getStatistics().addEntry(Statistic.ATTACK,new StatBaseEv((short)1,(short)1));
        _pk.getStatistics().addEntry(Statistic.DEFENSE,new StatBaseEv((short)1,(short)1));
        _pk.getStatistics().addEntry(Statistic.SPECIAL_ATTACK,new StatBaseEv((short)1,(short)1));
        _pk.getStatistics().addEntry(Statistic.SPECIAL_DEFENSE,new StatBaseEv((short)1,(short)1));
        _pk.getStatistics().addEntry(Statistic.SPEED,new StatBaseEv((short)1,(short)1));
        _pk.getStatistics().addEntry(Statistic.HP,new StatBaseEv((short)1,(short)1));
    }

    public static PkData pkDataByFacade(FacadeGame _dataBase) {
        PkData pk_ = new PkData();
        pk_.setDataBase(_dataBase);
        return pk_;
    }

}