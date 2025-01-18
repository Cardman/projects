package aiki.beans.map;

import aiki.beans.*;
import aiki.beans.db.*;
import aiki.db.DataBase;
import aiki.facade.*;
import aiki.fight.pokemon.*;
import aiki.fight.util.*;
import aiki.instances.*;
import aiki.map.*;
import aiki.map.buildings.*;
import aiki.map.characters.*;
import aiki.map.characters.enums.*;
import aiki.map.enums.*;
import aiki.map.levels.*;
import aiki.map.places.*;
import aiki.map.pokemon.*;
import aiki.map.pokemon.enums.*;
import aiki.map.util.*;
import aiki.util.Coords;
import aiki.util.Point;
import code.bean.nat.*;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import code.util.*;

public abstract class InitDbMap extends InitDbConstr {

    public static final String M_POK_00 = "M_POK00";
    public static final String M_POK_01 = "M_POK01";
    public static final String M_POK_02 = "M_POK02";
    public static final String M_POK_03 = "M_POK03";
    public static final String M_POK_04 = "M_POK04";
    public static final String M_POK_05 = "M_POK05";
    public static final String M_POK_06 = "M_POK06";
    public static final String M_POK_07 = "M_POK07";
    public static final String P_POK_00 = "P_POK00";
    public static final String P_POK_01 = "P_POK01";
    public static final String P_POK_02 = "P_POK02";
    public static final String P_POK_03 = "P_POK03";
    public static final String P_POK_04 = "P_POK04";
    public static final String P_POK_05 = "P_POK05";
    public static final String P_POK_06 = "P_POK06";
    public static final String P_POK_07 = "P_POK07";
    public static final String P_POK_08 = "P_POK08";
    public static final String P_POK_09 = "P_POK09";
    public static final String P_POK_10 = "P_POK10";
    public static final String P_POK_11 = "P_POK11";
    public static final String P_POK_12 = "P_POK12";
    public static final String P_POK_13 = "P_POK13";
    public static final String P_POK_14 = "P_POK14";
    public static final String P_POK_15 = "P_POK15";
    public static final String P_POK_16 = "P_POK16";
    public static final String P_POK_17 = "P_POK17";
    public static final String P_POK_18 = "P_POK18";
    public static final String P_POK_19 = "P_POK19";
    public static final String P_POK_20 = "P_POK20";
    public static final String P_POK_21 = "P_POK21";

    public static final String M_POK_00_TR = "M_POK00_TR";
    public static final String M_POK_01_TR = "M_POK01_TR";
    public static final String M_POK_02_TR = "M_POK02_TR";
    public static final String M_POK_03_TR = "M_POK03_TR";
    public static final String M_POK_04_TR = "M_POK04_TR";
    public static final String M_POK_05_TR = "M_POK05_TR";
    public static final String M_POK_06_TR = "M_POK06_TR";
    public static final String M_POK_07_TR = "M_POK07_TR";
    public static final String P_POK_00_TR = "P_POK00_TR";
    public static final String P_POK_01_TR = "P_POK01_TR";
    public static final String P_POK_02_TR = "P_POK02_TR";
    public static final String P_POK_03_TR = "P_POK03_TR";
    public static final String P_POK_04_TR = "P_POK04_TR";
    public static final String P_POK_05_TR = "P_POK05_TR";
    public static final String P_POK_06_TR = "P_POK06_TR";
    public static final String P_POK_07_TR = "P_POK07_TR";
    public static final String P_POK_08_TR = "P_POK08_TR";
    public static final String P_POK_09_TR = "P_POK09_TR";
    public static final String P_POK_10_TR = "P_POK10_TR";
    public static final String P_POK_11_TR = "P_POK11_TR";
    public static final String P_POK_12_TR = "P_POK12_TR";
    public static final String P_POK_13_TR = "P_POK13_TR";
    public static final String P_POK_14_TR = "P_POK14_TR";
    public static final String P_POK_15_TR = "P_POK15_TR";
    public static final String P_POK_16_TR = "P_POK16_TR";
    public static final String P_POK_17_TR = "P_POK17_TR";
    public static final String P_POK_18_TR = "P_POK18_TR";
    public static final String P_POK_19_TR = "P_POK19_TR";
    public static final String P_POK_20_TR = "P_POK20_TR";
    public static final String P_POK_21_TR = "P_POK21_TR";

    public static final String I_BALL_TR = "I_BALL_TR";
    public static final String I_BERRY_TR = "I_BERRY_TR";
    public static final String I_BOOST_TR = "I_BOOST_TR";
    public static final String I_EVO_ITEM_TR = "I_EVO_ITEM_TR";
    public static final String I_EVO_STONE_TR = "I_EVO_STONE_TR";
    public static final String I_FOSSIL_TR = "I_FOSSIL_TR";
    public static final String I_HEAL_TR = "I_HEAL_TR";
    public static final String I_HEAL_HP_STATUS_TR = "I_HEAL_HP_STATUS_TR";
    public static final String I_HEAL_HP_TR = "I_HEAL_HP_TR";
    public static final String I_HEAL_PP_TR = "I_HEAL_PP_TR";
    public static final String I_HEAL_STATUS_TR = "I_HEAL_STATUS_TR";
    public static final String I_ITEMBATTLE_TR = "I_ITEMBATTLE_TR";
    public static final String I_REPEL_TR = "I_REPEL_TR";
    public static final String I_SELLING_TR = "I_SELLING_TR";
    public static final String I_BALL = "I_BALL";
    public static final String I_BERRY = "I_BERRY";
    public static final String I_BOOST = "I_BOOST";
    public static final String I_EVO_ITEM = "I_EVO_ITEM";
    public static final String I_EVO_STONE = "I_EVO_STONE";
    public static final String I_FOSSIL = "I_FOSSIL";
    public static final String I_HEAL = "I_HEAL";
    public static final String I_HEAL_HP_STATUS = "I_HEAL_HP_STATUS";
    public static final String I_HEAL_HP = "I_HEAL_HP";
    public static final String I_HEAL_PP = "I_HEAL_PP";
    public static final String I_HEAL_STATUS = "I_HEAL_STATUS";
    public static final String I_ITEMBATTLE = "I_ITEMBATTLE";
    public static final String I_REPEL = "I_REPEL";
    public static final String I_SELLING = "I_SELLING";

    public static final String A_ABILITY2="B_ABILITY";
    public static final String A_ABILITY2_TR="B_ABILITY_TR";
    public static final String DUAL = "dual";
    public static final String SINGLE = "single";
    public static final String DUAL_1 = "dual_1";
    public static final String DUAL_2 = "dual_2";
    public static final String SI = "si";
    public static final String T_L_1 = "T L 1";
    public static final String T_L_2 = "T L 2";
    public static final String G_L_1 = "G L 1";
    public static final String D_T_1 = "D T 1";
    public static final String D_T_2 = "D T 2";
    public static final String PL_1 = "PL 1";
    public static final String PL_2 = "PL 2";
    public static final String PL_3 = "PL 3";
    public static final String PL_4 = "PL 4";
    public static final String PL_5 = "PL 5";
    public static final String PL_6 = "PL 6";
    public static final String PL_7 = "PL 7";
    public static final String PL_8 = "PL 8";
    public static final String PL_9 = "PL 9";
    public static final int IMG_00 = 0;
    public static final int IMG_01 = IMG_00 + 1;
    public static final int IMG_02 = IMG_01 + 1;
    public static final int IMG_03 = IMG_02 + 1;
    public static final int IMG_04 = IMG_03 + 1;
    public static final int IMG_05 = IMG_04 + 1;
    public static final int IMG_06 = IMG_05 + 1;
    public static final int IMG_07 = IMG_06 + 1;
    public static final int IMG_08 = IMG_07 + 1;
    public static final int IMG_09 = IMG_08 + 1;
    public static final int IMG_10 = IMG_09 + 1;
    public static final int IMG_11 = IMG_10 + 1;
    public static final int IMG_12 = IMG_11 + 1;
    public static final int IMG_13 = IMG_12 + 1;
    public static final int IMG_14 = IMG_13 + 1;
    public static final int IMG_15 = IMG_14 + 1;
    public static final int IMG_16 = IMG_15 + 1;
    public static final int IMG_17 = IMG_16 + 1;
    public static final int IMG_18 = IMG_17 + 1;
    public static final int IMG_19 = IMG_18 + 1;
    public static final int IMG_20 = IMG_19 + 1;
    public static final int IMG_21 = IMG_20 + 1;
//    public static final String IMG_DUAL = "AAACAAAWAAAX";
    public static final int IMG_SINGLE = 22;
    public static final int IMG_DUAL1 = IMG_SINGLE + 1;
    public static final int IMG_DUAL2 = IMG_DUAL1 + 1;
    public static final int IMG_SI = IMG_DUAL2 + 1;
    public static final int IMG_ITEM = 16777215;

    public static String callMapBeanClickLevel(int _place, int _level) {
        return callMapBeanClickLevel(dispMap(),_place,_level);
    }

    public static String callMapBeanClickLevel(NaSt _str, int _place, int _level) {
        return navigateData(new MapBeanClickLevel(),_str,_place,_level);
    }

    public static Coords callMapBeanClickLevelId(int _place, int _level) {
        NaSt bean_ = dispMap();
        callMapBeanClickLevel(bean_,_place,_level);
        return getValPlaceLevelId(bean_);
    }
    public static String callMapBeanClickLevelZero(int _place) {
        return callMapBeanClickLevelZero(dispMap(),_place);
    }

    public static String callMapBeanClickLevelZero(NaSt _str, int  _place) {
        return navigateData(new MapBeanClickLevelZero(),_str,_place);
    }

    public static Coords callMapBeanClickLevelZeroId(int _place) {
        NaSt bean_ = dispMap();
        callMapBeanClickLevelZero(bean_,_place);
        return getValPlaceLevelId(bean_);
    }
    public static NaSt callMapBeanIsMultiLayer(int _place) {
        return BeanPokemonCommonTs.callLongs(new MapBeanIsMultiLayer(),dispMap(),_place);
    }

    public static NaSt callMapBeanLayers(int _place) {
        return BeanPokemonCommonTs.callLongs(new MapBeanLayers(),dispMap(),_place);
    }

    public static NaSt callMapBeanPlacesGet() {
        return BeanPokemonCommonTs.callLongs(new MapBeanPlacesGet(),dispMap());
    }

    protected static NaSt dispMap() {
        PkData pk_ = pkDataByFacade(db());
        return dispMap(pk_);
    }

    private static NaSt dispMap(PkData _pk) {
        StringMap<NaSt> all_ = beanToMap(_pk);
        NaSt welcome_ = all_.getVal(AikiBeansMapStd.BEAN_GAME_MAP);
        beforeDisplaying(welcome_);
        return welcome_;
    }

    protected static NaSt dispMapLevel(int _place, int _level) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToMap(pk_);
        return transitLevel(_place, _level, pk_, all_);
    }

    protected static NaSt transitLevel(int _place, int _level, PkData _pk, StringMap<NaSt> _all) {
        NaSt welcome_ = _all.getVal(AikiBeansMapStd.BEAN_GAME_MAP);
        beforeDisplaying(welcome_);
        NaSt moves_ = _all.getVal(AikiBeansMapStd.BEAN_LEVEL_MAP);
        transit(_pk,new MapBeanClickLevel(),welcome_,moves_, _place, _level);
        return moves_;
    }

    protected static NaSt dispMapLevelZero(int _place) {
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToMap(pk_);
        return transitLevelZero(_place, pk_, all_);
    }

    protected static NaSt transitLevelZero(int _place, PkData _pk, StringMap<NaSt> _all) {
        NaSt welcome_ = _all.getVal(AikiBeansMapStd.BEAN_GAME_MAP);
        beforeDisplaying(welcome_);
        NaSt moves_ = _all.getVal(AikiBeansMapStd.BEAN_LEVEL_MAP);
        transit(_pk,new MapBeanClickLevelZero(),welcome_,moves_, _place);
        return moves_;
    }

    /*protected static Struct transitToAllPks(PkData _pk, StringMap<Struct> _all,int _index) {
        Struct welcome_ = _all.getVal(AikiBeansStd.BEAN_WELCOME);
        beforeDisplaying(welcome_);
        Struct pks_ = _all.getVal(AikiBeansPokemonStd.BEAN_POKEDEX);
        Struct pk_ = _all.getVal(AikiBeansPokemonStd.BEAN_PK);
        transit(_pk,new WelcomeBeanClickPokedex(),welcome_,pks_);
        transit(_pk,new PokedexBeanSearch(),pks_,pks_);
        transit(_pk,new PokedexBeanClickLink(),pks_,pk_,_index);
        return pk_;
    }*/
    public static StringMap<NaSt> beanToMap(PkData _pk) {
        StringMap<NaSt> map_ = new StringMap<NaSt>();
        map_.addEntry(AikiBeansMapStd.BEAN_GAME_MAP,_pk.beanMapBean(EN));
        map_.addEntry(AikiBeansMapStd.BEAN_LEVEL_MAP,_pk.beanMapLevelBean(EN));
        map_.addEntry(AikiBeansMapStd.BEAN_PK_TEAM,_pk.beanPokemonTeamBean(EN));
        map_.addEntry(AikiBeansMapStd.BEAN_TRAINER_FIGHT,_pk.beanTrainerBean(EN));
        map_.addEntry(AikiBeansMapStd.BEAN_ALLY,_pk.beanAllyBean(EN));
        map_.addEntry(AikiBeansMapStd.BEAN_DUAL,_pk.beanDualFightBean(EN));
        map_.addEntry(AikiBeansMapStd.BEAN_AREA,_pk.beanAreaBean(EN));
        map_.addEntry(AikiBeansMapStd.BEAN_LEG_PK,_pk.beanLegendaryPokemonBean(EN));
        map_.addEntry(AikiBeansMapStd.BEAN_DEALER,_pk.beanDealerBean(EN));
        map_.addEntry(AikiBeansMapStd.BEAN_SELLER,_pk.beanSellerBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToMap() {
        StringMap<String> map_ = new StringMap<String>();
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MAP_MAP_HTML,AikiBeansMapStd.BEAN_GAME_MAP);
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MAP_LEVEL_HTML,AikiBeansMapStd.BEAN_LEVEL_MAP);
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_POKEMON_TEAM_HTML,AikiBeansMapStd.BEAN_PK_TEAM);
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_TRAINER_MULTI_FIGHT_HTML,AikiBeansMapStd.BEAN_TRAINER_FIGHT);
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_TRAINER_ONE_FIGHT_HTML,AikiBeansMapStd.BEAN_TRAINER_FIGHT);
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_ALLY_HTML,AikiBeansMapStd.BEAN_ALLY);
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_DUAL_FIGHT_HTML,AikiBeansMapStd.BEAN_DUAL);
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_AREA_HTML,AikiBeansMapStd.BEAN_AREA);
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_LEG_PK_HTML,AikiBeansMapStd.BEAN_LEG_PK);
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_DEALER_HTML,AikiBeansMapStd.BEAN_DEALER);
        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_MAP_ELEMENTS_SELLER_HTML,AikiBeansMapStd.BEAN_SELLER);
        return map_;
    }
    protected static PokemonData pkOne() {
        return pk(M_POK_00,M_POK_01);
    }

    protected static PokemonData pkTwo() {
        return pk(M_POK_02,M_POK_03);
    }

    protected static PokemonData pk(String _one, String _second) {
        PokemonData pk_ = Instances.newPokemonData();
        pk_.getLevMoves().add(new LevelMove( 1,_one));
        pk_.getLevMoves().add(new LevelMove( 3,_second));
        return pk_;
    }
    protected static DualFight dual() {
        DualFight d_ = Instances.newDualFight();
        d_.setNames(new StringList(D_T_1, D_T_2));
        d_.getFoeTrainer().setImageMaxiFileName(DUAL);
        d_.getFoeTrainer().setImageMiniFileName(DUAL_1);
        d_.getFoeTrainer().setImageMiniSecondTrainerFileName(DUAL_2);
        d_.setPt(newPoint(1,0));
        d_.getAlly().getTeam().add(wpOne(P_POK_16,A_ABILITY,I_HEAL_HP_STATUS,18));
        d_.getAlly().getTeam().add(wpTwo(P_POK_17,A_ABILITY2,I_HEAL_PP,19));
        d_.getFoeTrainer().getTeam().add(wpOne(P_POK_14,A_ABILITY2,I_HEAL,18));
        d_.getFoeTrainer().getTeam().add(wpTwo(P_POK_15,A_ABILITY,I_HEAL_HP,19));
        d_.getFoeTrainer().setReward( 25);
        return d_;
    }
    protected static TrainerLeague trLeagueOne() {
        TrainerLeague tmf_ = Instances.newTrainerLeague();
        tmf_.setName(T_L_1);
        tmf_.setImageMaxiFileName(SINGLE);
        tmf_.setImageMiniFileName(SI);
        mult(1, tmf_);
        tmf_.getTeam().add(wpOne(P_POK_12,A_ABILITY,I_BOOST,20));
        tmf_.getTeam().add(wpTwo(P_POK_13,A_ABILITY2,I_ITEMBATTLE,25));
        return tmf_;
    }
    protected static TrainerLeague trLeagueTwo() {
        TrainerLeague tmf_ = Instances.newTrainerLeague();
        tmf_.setName(T_L_2);
        tmf_.setImageMaxiFileName(SINGLE);
        tmf_.setImageMiniFileName(SI);
        mult(1, tmf_);
        tmf_.getTeam().add(wpOne(P_POK_20,A_ABILITY,I_BOOST,20));
        tmf_.getTeam().add(wpTwo(P_POK_21,A_ABILITY2,I_ITEMBATTLE,25));
        return tmf_;
    }
    protected static GymLeader trGymLeader() {
        GymLeader tmf_ = Instances.newGymLeader();
        tmf_.setName(G_L_1);
        tmf_.setImageMaxiFileName(SINGLE);
        tmf_.setImageMiniFileName(SI);
        mult(1, tmf_);
        tmf_.getTeam().add(wpOne(P_POK_14,A_ABILITY2,I_HEAL,18));
        tmf_.getTeam().add(wpTwo(P_POK_15,A_ABILITY,I_HEAL_HP,19));
        tmf_.setTm(2);
        return tmf_;
    }
    protected static GymTrainer trGymTrainer() {
        GymTrainer tmf_ = Instances.newGymTrainer();
        tmf_.setImageMaxiFileName(SINGLE);
        tmf_.setImageMiniFileName(SI);
        mult(1, tmf_);
        tmf_.getTeam().add(wpOne(P_POK_16,A_ABILITY,I_HEAL_HP_STATUS,18));
        tmf_.getTeam().add(wpTwo(P_POK_17,A_ABILITY2,I_HEAL_PP,19));
        return tmf_;
    }
    protected static TrainerMultiFights trMult() {
        TrainerMultiFights tmf_ = Instances.newTrainerMultiFights();
        tmf_.setImageMaxiFileName(SINGLE);
        tmf_.setImageMiniFileName(SI);
        mult(2, tmf_);
        tmf_.getTeamsRewards().add(teamOne());
        tmf_.getTeamsRewards().add(teamTwo());
        return tmf_;
    }

    private static void mult(int _m, Trainer _t) {
        _t.setMultiplicityFight(_m);
    }

    protected static PokemonTeam teamOne() {
        PokemonTeam t_ = teamBase(20);
        t_.getTeam().add(wpOne(P_POK_08,A_ABILITY,I_BALL,7));
        t_.getTeam().add(wpTwo(P_POK_09,A_ABILITY2,I_BERRY,9));
        return t_;
    }
    protected static PokemonTeam teamTwo() {
        PokemonTeam t_ = teamBase(15);
        t_.getTeam().add(wpOne(P_POK_10,A_ABILITY2,"",17));
        t_.getTeam().add(wpTwo(P_POK_11,A_ABILITY,"",19));
        return t_;
    }

    private static PokemonTeam teamBase(int _v) {
        PokemonTeam t_ = Instances.newPokemonTeam();
        t_.setReward( _v);
        return t_;
    }

    protected static PkTrainer wpOne(String _name, String _ab, String _it, int _level) {
        return trp(_name, _ab, _it, _level, M_POK_04, M_POK_05);
    }

    protected static PkTrainer wpTwo(String _name, String _ab, String _it, int _level) {
        return trp(_name, _ab, _it, _level, M_POK_06, M_POK_07);
    }
    protected static PkTrainer trp(String _name, String _ab, String _it, int _level, String _one, String _second) {
        PkTrainer pk_ = Instances.newPkTrainer();
        pk_.setName(_name);
        pk_.setLevel( _level);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(_ab);
        pk_.setItem(_it);
        pk_.getMoves().add(_one);
        pk_.getMoves().add(_second);
        return pk_;
    }
    protected static FacadeGame db() {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers(P_POK_00,pkOne());
        facade_.getData().completeMembers(P_POK_01,pkTwo());
        facade_.getData().completeMembers(P_POK_02,pkOne());
        facade_.getData().completeMembers(P_POK_03,pkTwo());
        facade_.getData().completeMembers(P_POK_04,pkOne());
        facade_.getData().completeMembers(P_POK_05,pkTwo());
        facade_.getData().completeMembers(P_POK_06,pkOne());
        facade_.getData().completeMembers(P_POK_07,pkTwo());
        facade_.getData().completeMembers(P_POK_08,pkOne());
        facade_.getData().completeMembers(P_POK_09,pkTwo());
        facade_.getData().completeMembers(P_POK_10,pkOne());
        facade_.getData().completeMembers(P_POK_11,pkTwo());
        facade_.getData().completeMembers(P_POK_12,pkOne());
        facade_.getData().completeMembers(P_POK_13,pkTwo());
        facade_.getData().completeMembers(P_POK_14,pkOne());
        facade_.getData().completeMembers(P_POK_15,pkTwo());
        facade_.getData().completeMembers(P_POK_16,pkOne());
        facade_.getData().completeMembers(P_POK_17,pkTwo());
        facade_.getData().completeMembers(P_POK_18,pkOne());
        facade_.getData().completeMembers(P_POK_19,pkTwo());
        facade_.getData().completeMembers(P_POK_20,pkOne());
        facade_.getData().completeMembers(P_POK_21,pkTwo());
        facade_.getData().completeMembers(M_POK_00,Instances.newDamagingMoveData());
        facade_.getData().completeMembers(M_POK_01,Instances.newDamagingMoveData());
        facade_.getData().completeMembers(M_POK_02,Instances.newDamagingMoveData());
        facade_.getData().completeMembers(M_POK_03,Instances.newDamagingMoveData());
        facade_.getData().completeMembers(M_POK_04,Instances.newDamagingMoveData());
        facade_.getData().completeMembers(M_POK_05,Instances.newDamagingMoveData());
        facade_.getData().completeMembers(M_POK_06,Instances.newDamagingMoveData());
        facade_.getData().completeMembers(M_POK_07,Instances.newDamagingMoveData());
        facade_.getData().completeMembers(I_BALL,Instances.newBall());
        facade_.getData().completeMembers(I_BERRY,Instances.newBerry());
        facade_.getData().completeMembers(I_BOOST,Instances.newBoost());
        facade_.getData().completeMembers(I_EVO_ITEM,Instances.newEvolvingItem());
        facade_.getData().completeMembers(I_EVO_STONE,Instances.newEvolvingStone());
        facade_.getData().completeMembers(I_FOSSIL,Instances.newFossil());
        facade_.getData().completeMembers(I_HEAL,Instances.newHealingSimpleItem());
        facade_.getData().completeMembers(I_HEAL_HP,Instances.newHealingHp());
        facade_.getData().completeMembers(I_HEAL_HP_STATUS,Instances.newHealingHpStatus());
        facade_.getData().completeMembers(I_HEAL_PP,Instances.newHealingPp());
        facade_.getData().completeMembers(I_HEAL_STATUS,Instances.newHealingSimpleStatus());
        facade_.getData().completeMembers(I_ITEMBATTLE,Instances.newItemForBattle());
        facade_.getData().completeMembers(I_REPEL,Instances.newRepel());
        facade_.getData().completeMembers(I_SELLING,Instances.newSellingItem());
        facade_.getData().completeMembers(A_ABILITY,Instances.newAbilityData());
        facade_.getData().completeMembers(A_ABILITY2,Instances.newAbilityData());
        facade_.getData().setMap(dm());
        facade_.getData().getHm().addEntry(1,M_POK_00);
        facade_.getData().getTm().addEntry(1,M_POK_01);
        facade_.getData().getTm().addEntry(2,M_POK_02);
        facade_.getData().getTm().addEntry(3,M_POK_03);
        trsCore(facade_);
        return facade_;
    }
    protected static void trsCore(FacadeGame _facade) {
        _facade.getData().getTranslatedAbilities().addEntry(EN,new StringMap<String>());
        _facade.getData().getTranslatedAbilities().getVal(EN).addEntry(A_ABILITY,A_ABILITY_TR);
        _facade.getData().getTranslatedAbilities().getVal(EN).addEntry(A_ABILITY2,A_ABILITY2_TR);
//        _facade.getData().getTranslatedTypes().addEntry(EN,new StringMap<String>());
//        _facade.getData().getTranslatedTypes().getVal(EN).addEntry(T_TYPE1, T_TYPE1_TR);
//        _facade.getData().getTranslatedCategories().addEntry(EN,new StringMap<String>());
//        _facade.getData().getTranslatedCategories().getVal(EN).addEntry(C_CAT, C_CAT1_TR);
//        _facade.getData().getTranslatedCategories().getVal(EN).addEntry(AUTRE,C_CAT2_TR);
        _facade.getData().getTranslatedItems().addEntry(EN,new StringMap<String>());
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_BALL,I_BALL_TR);
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_BERRY,I_BERRY_TR);
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_BOOST,I_BOOST_TR);
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_ITEMBATTLE,I_ITEMBATTLE_TR);
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_EVO_ITEM,I_EVO_ITEM_TR);
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_EVO_STONE,I_EVO_STONE_TR);
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_FOSSIL,I_FOSSIL_TR);
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_HEAL,I_HEAL_TR);
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_HEAL_HP,I_HEAL_HP_TR);
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_HEAL_PP,I_HEAL_PP_TR);
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_HEAL_HP_STATUS,I_HEAL_HP_STATUS_TR);
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_HEAL_STATUS,I_HEAL_STATUS_TR);
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_REPEL,I_REPEL_TR);
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_SELLING,I_SELLING_TR);
        _facade.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_POK_00,M_POK_00_TR);
        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_POK_01,M_POK_01_TR);
        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_POK_02,M_POK_02_TR);
        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_POK_03,M_POK_03_TR);
        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_POK_04,M_POK_04_TR);
        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_POK_05,M_POK_05_TR);
        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_POK_06,M_POK_06_TR);
        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_POK_07,M_POK_07_TR);
        _facade.getData().getTranslatedPokemon().addEntry(EN,new StringMap<String>());
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_00, P_POK_00_TR);
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_01, P_POK_01_TR);
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_02, P_POK_02_TR);
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_03, P_POK_03_TR);
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_04, P_POK_04_TR);
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_05, P_POK_05_TR);
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_06, P_POK_06_TR);
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_07, P_POK_07_TR);
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_08, P_POK_08_TR);
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_09, P_POK_09_TR);
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_10, P_POK_10_TR);
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_11, P_POK_11_TR);
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_12, P_POK_12_TR);
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_13, P_POK_13_TR);
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_14, P_POK_14_TR);
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_15, P_POK_15_TR);
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_16, P_POK_16_TR);
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_17, P_POK_17_TR);
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_18, P_POK_18_TR);
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_19, P_POK_19_TR);
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_20, P_POK_20_TR);
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_21, P_POK_21_TR);
        _facade.getData().getTranslatedGenders().addEntry(EN,new IdMap<Gender,String>());
        _facade.getData().getTranslatedGenders().getVal(EN).addEntry(Gender.NO_GENDER,NO_G);
//        _facade.getData().getMiniPk().addEntry(P_POK_00,BaseSixtyFourUtil.getImageByString("AAACAAAABAAA////////"));
//        _facade.getData().getMiniPk().addEntry(P_POK_01,BaseSixtyFourUtil.getImageByString("AAACAAABBAAA////////"));
//        _facade.getData().getMiniPk().addEntry(P_POK_02,BaseSixtyFourUtil.getImageByString("AAACAAACBAAA////////"));
//        _facade.getData().getMiniPk().addEntry(P_POK_03,BaseSixtyFourUtil.getImageByString("AAACAAADBAAA////////"));
//        _facade.getData().getMiniPk().addEntry(P_POK_04,BaseSixtyFourUtil.getImageByString("AAACAAAEBAAA////////"));
//        _facade.getData().getMiniPk().addEntry(P_POK_05,BaseSixtyFourUtil.getImageByString("AAACAAAFBAAA////////"));
//        _facade.getData().getMiniPk().addEntry(P_POK_06,BaseSixtyFourUtil.getImageByString("AAACAAAGBAAA////////"));
//        _facade.getData().getMiniPk().addEntry(P_POK_07,BaseSixtyFourUtil.getImageByString("AAACAAAHBAAA////////"));
//        _facade.getData().getMiniPk().addEntry(P_POK_08,BaseSixtyFourUtil.getImageByString("AAACAAAIBAAA////////"));
//        _facade.getData().getMiniPk().addEntry(P_POK_09,BaseSixtyFourUtil.getImageByString("AAACAAAJBAAA////////"));
//        _facade.getData().getMiniPk().addEntry(P_POK_10,BaseSixtyFourUtil.getImageByString("AAACAAAKBAAA////////"));
//        _facade.getData().getMiniPk().addEntry(P_POK_11,BaseSixtyFourUtil.getImageByString("AAACAAALBAAA////////"));
//        _facade.getData().getMiniPk().addEntry(P_POK_12,BaseSixtyFourUtil.getImageByString("AAACAAAMBAAA////////"));
//        _facade.getData().getMiniPk().addEntry(P_POK_13,BaseSixtyFourUtil.getImageByString("AAACAAANBAAA////////"));
//        _facade.getData().getMiniPk().addEntry(P_POK_14,BaseSixtyFourUtil.getImageByString("AAACAAAOBAAA////////"));
//        _facade.getData().getMiniPk().addEntry(P_POK_15,BaseSixtyFourUtil.getImageByString("AAACAAAPBAAA////////"));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_00,instance(IMG_00));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_01,instance(IMG_01));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_02,instance(IMG_02));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_03,instance(IMG_03));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_04,instance(IMG_04));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_05,instance(IMG_05));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_06,instance(IMG_06));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_07,instance(IMG_07));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_08,instance(IMG_08));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_09,instance(IMG_09));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_10,instance(IMG_10));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_11,instance(IMG_11));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_12,instance(IMG_12));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_13,instance(IMG_13));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_14,instance(IMG_14));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_15,instance(IMG_15));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_16,instance(IMG_16));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_17,instance(IMG_17));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_18,instance(IMG_18));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_19,instance(IMG_19));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_20,instance(IMG_20));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_21,instance(IMG_21));
        _facade.getData().getMiniPk().addEntry(P_POK_00,instance(IMG_00));
        _facade.getData().getMiniPk().addEntry(P_POK_01,instance(IMG_01));
        _facade.getData().getMiniPk().addEntry(P_POK_02,instance(IMG_02));
        _facade.getData().getMiniPk().addEntry(P_POK_03,instance(IMG_03));
        _facade.getData().getMiniPk().addEntry(P_POK_04,instance(IMG_04));
        _facade.getData().getMiniPk().addEntry(P_POK_05,instance(IMG_05));
        _facade.getData().getMiniPk().addEntry(P_POK_06,instance(IMG_06));
        _facade.getData().getMiniPk().addEntry(P_POK_07,instance(IMG_07));
        _facade.getData().getMiniPk().addEntry(P_POK_08,instance(IMG_08));
        _facade.getData().getMiniPk().addEntry(P_POK_09,instance(IMG_09));
        _facade.getData().getMiniPk().addEntry(P_POK_10,instance(IMG_10));
        _facade.getData().getMiniPk().addEntry(P_POK_11,instance(IMG_11));
        _facade.getData().getMiniPk().addEntry(P_POK_12,instance(IMG_12));
        _facade.getData().getMiniPk().addEntry(P_POK_13,instance(IMG_13));
        _facade.getData().getMiniPk().addEntry(P_POK_14,instance(IMG_14));
        _facade.getData().getMiniPk().addEntry(P_POK_15,instance(IMG_15));
        _facade.getData().getMiniPk().addEntry(P_POK_16,instance(IMG_16));
        _facade.getData().getMiniPk().addEntry(P_POK_17,instance(IMG_17));
        _facade.getData().getMiniPk().addEntry(P_POK_18,instance(IMG_18));
        _facade.getData().getMiniPk().addEntry(P_POK_19,instance(IMG_19));
        _facade.getData().getMiniPk().addEntry(P_POK_20,instance(IMG_20));
        _facade.getData().getMiniPk().addEntry(P_POK_21,instance(IMG_21));
        _facade.getData().getTrainers().addEntry(DUAL,instance(line(IMG_SINGLE,IMG_DUAL1)));
        _facade.getData().getTrainers().addEntry(SINGLE,instance(IMG_SINGLE));
        _facade.getData().getPeople().addEntry(DUAL_1,instance(IMG_DUAL1));
        _facade.getData().getPeople().addEntry(DUAL_2,instance(IMG_DUAL2));
        _facade.getData().getPeople().addEntry(SI,instance(IMG_SI));
        _facade.getData().getPeople().addEntry(NULL_REF,instance(IMG_ITEM));
        _facade.getData().getImages().addEntry(NULL_REF,instance(IMG_ITEM));
        _facade.getData().getLinks().addEntry(NULL_REF,instance(IMG_ITEM));
        _facade.getData().getMiniItems().addEntry(I_BALL,instance(IMG_ITEM));
        _facade.getData().getMiniItems().addEntry(I_BERRY,instance(IMG_ITEM));
        _facade.getData().getMiniItems().addEntry(I_BOOST,instance(IMG_ITEM));
        _facade.getData().getMiniItems().addEntry(I_ITEMBATTLE,instance(IMG_ITEM));
        _facade.getData().getMiniItems().addEntry(I_EVO_ITEM,instance(IMG_ITEM));
        _facade.getData().getMiniItems().addEntry(I_EVO_STONE,instance(IMG_ITEM));
        _facade.getData().getMiniItems().addEntry(I_FOSSIL,instance(IMG_ITEM));
        _facade.getData().getMiniItems().addEntry(I_HEAL,instance(IMG_ITEM));
        _facade.getData().getMiniItems().addEntry(I_HEAL_HP,instance(IMG_ITEM));
        _facade.getData().getMiniItems().addEntry(I_HEAL_PP,instance(IMG_ITEM));
        _facade.getData().getMiniItems().addEntry(I_HEAL_HP_STATUS,instance(IMG_ITEM));
        _facade.getData().getMiniItems().addEntry(I_HEAL_STATUS,instance(IMG_ITEM));
        _facade.getData().getMiniItems().addEntry(I_REPEL,instance(IMG_ITEM));
        _facade.getData().getMiniItems().addEntry(I_SELLING,instance(IMG_ITEM));
        _facade.getData().setImageTmHm(instance(IMG_ITEM));
        _facade.getData().addConstNumTest(DataBase.DEF_MAX_ATT,new Rate(4));
//        _facade.getData().getTranslatedStatus().addEntry(EN,new StringMap<String>());
//        _facade.getData().getTranslatedStatus().getVal(EN).addEntry(S_STA_REL,S_STA_REL_TR);
//        _facade.getData().getTranslatedStatus().getVal(EN).addEntry(S_STA_SIM,S_STA_SIM_TR);
//        _facade.getData().getTranslatedStatistics().addEntry(EN,new IdMap<Statistic, String>());
//        _facade.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.ATTACK,ST_ATT_TR);
//        _facade.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.DEFENSE,ST_DEF_TR);
//        _facade.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.SPECIAL_ATTACK,ST_ATT_SPE_TR);
//        _facade.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.SPECIAL_DEFENSE,ST_DEF_SPE_TR);
//        _facade.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.SPEED,ST_SPEED_TR);
//        _facade.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.HP,ST_HP_TR);
//        _facade.getData().getTranslatedBooleans().addEntry(EN,new IdMap<SelectedBoolean, String>());
//        _facade.getData().getTranslatedBooleans().getVal(EN).addEntry(SelectedBoolean.NO, B_NO);
//        _facade.getData().getTranslatedBooleans().getVal(EN).addEntry(SelectedBoolean.YES, B_YES);
//        _facade.getData().getTranslatedBooleans().getVal(EN).addEntry(SelectedBoolean.YES_AND_NO," ");
    }
    protected static DataMap dm() {
        DataMap d_ = Instances.newDataMap();
        d_.getPlaces().add(cityOne(newCoords(2,0,0,1)));
        d_.getPlaces().add(cityTwo(newCoords(2,0,0,0)));
        d_.getPlaces().add(lrOne(newCoords(3,0,0,1),newCoords(1,0,1,2),newCoords(0,0,1,0), PL_3));
        d_.getPlaces().add(lcOne(d_.getPlaces().size(),newCoords(2,0,1,0)));
        d_.getPlaces().add(city(Direction.LEFT,newCoords(5,0,0,0), PL_5, newPoint(0, 0)));
        d_.getPlaces().add(city(Direction.RIGHT,newCoords(4,0,0,0), PL_6, newPoint(0, 2)));
        d_.getPlaces().add(road(Direction.LEFT,newCoords(7,0,0,0), PL_7));
        d_.getPlaces().add(road(Direction.RIGHT,newCoords(6,0,0,0), PL_8));
        d_.getPlaces().add(league(newCoords(3,0,0,2)));
        d_.getPlaces().add(lrOne(newCoords(3,0,0,1),newCoords(1,0,1,2),newCoords(0,0,1,0), "PL__"));
        d_.getAccessCondition().addEntry(newCoords(3,0,0,2),new Condition());
        d_.join(0,2,newPoint(1,0),newPoint(0,1),Direction.DOWN);
        d_.join(2,1,newPoint(0,0),newPoint(1,2),Direction.DOWN);
        d_.join(4,5,newPoint(0,0),newPoint(0,0),Direction.LEFT);
        d_.join(6,7,newPoint(0,0),newPoint(0,0),Direction.LEFT);
        return d_;
    }
    protected static League league(Coords _c) {
        League l_ = Instances.newLeague();
        l_.setName(PL_9);
        LevelLeague one_ = Instances.newLevelLeague();
        one_.setTrainer(trLeagueOne());
        sqThree(one_);
        one_.setTrainerCoords(newPoint(1,1));
        one_.setAccessPoint(newPoint(1,2));
        one_.setNextLevelTarget(newPoint(1,0));
        l_.getRooms().add(one_);
        LevelLeague two_ = Instances.newLevelLeague();
        two_.setTrainer(trLeagueTwo());
        sqThree(two_);
        two_.setAccessPoint(newPoint(1,2));
        two_.setTrainerCoords(newPoint(1,1));
        l_.getRooms().add(two_);
        l_.setAccessCoords(_c);
        return l_;
    }
    protected static City city(Direction _dir, Coords _other, String _n, Point _source) {
        City c_ = Instances.newCity();
        c_.setName(_n);
        sqThree(c_.getLevel());
        c_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(_source,_dir),_other);
        return c_;
    }
    protected static Road road(Direction _dir, Coords _other, String _n) {
        Road c_ = Instances.newRoad();
        c_.setName(_n);
        Block one_ = Instances.newBlock();
        one_.setHeight(1);
        one_.setWidth(1);
        one_.setTileFileName("");
        one_.setIndexApparition(0);
        c_.getLevelRoad().getBlocks().addEntry(newPoint(0,0),one_);
        c_.getLevelRoad().getBlocks().addEntry(newPoint(0,1),one_);
        c_.getLevelRoad().getBlocks().addEntry(newPoint(1,0),one_);
        c_.getLevelRoad().getBlocks().addEntry(newPoint(1,1),one_);
        c_.getLevelRoad().getWildPokemonAreas().add(areaThree());
        c_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(0,0),_dir),_other);
        return c_;
    }
    protected static City cityOne(Coords _other) {
        City c_ = Instances.newCity();
        c_.setName(PL_1);
        sqThree(c_.getLevel());
        c_.getBuildings().addEntry(newPoint(1,1),gym());
        c_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(1,0),Direction.UP),_other);
        return c_;
    }
    protected static City cityTwo(Coords _other) {
        City c_ = Instances.newCity();
        c_.setName(PL_2);
        sqThree(c_.getLevel());
        c_.getBuildings().addEntry(newPoint(1,1),pkCenter());
        c_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(1,2),Direction.DOWN),_other);
        return c_;
    }
    protected static Gym gym() {
        Gym g_ = Instances.newGym();
        g_.setExitCity(newPoint(1,2));
        g_.getIndoor().setGymLeaderCoords(newPoint(1,0));
        g_.getIndoor().getGymTrainers().addEntry(newPoint(1,1),trGymTrainer());
        g_.getIndoor().setGymLeader(trGymLeader());
        Block bl_ = Instances.newBlock();
        bl_.setHeight(1);
        bl_.setWidth(1);
        bl_.setTileFileName("");
        g_.getIndoor().getBlocks().addEntry(newPoint(1,0),bl_);
        g_.getIndoor().getBlocks().addEntry(newPoint(1,1),bl_);
        g_.getIndoor().getBlocks().addEntry(newPoint(1,2),bl_);
        g_.getIndoor().getBlocks().addEntry(newPoint(1,3),bl_);
        return g_;
    }
    protected static PokemonCenter pkCenter() {
        PokemonCenter pc_ = Instances.newPokemonCenter();
        pc_.setExitCity(newPoint(1,2));
        LevelIndoorPokemonCenter l_ = Instances.newLevelIndoorPokemonCenter();
        sqThree(l_);
        l_.setStorageCoords(newPoint(1,0));
        GerantPokemon g1_ = Instances.newGerantPokemon();
        g1_.setGerance(GeranceType.HEAL);
        l_.getGerants().addEntry(newPoint(0,0), g1_);
        GerantPokemon g2_ = Instances.newGerantPokemon();
        g2_.setGerance(GeranceType.HOST);
        l_.getGerants().addEntry(newPoint(0,1), g2_);
        GerantPokemon g3_ = Instances.newGerantPokemon();
        g3_.setGerance(GeranceType.FOSSILE);
        l_.getGerants().addEntry(newPoint(0,2), g3_);
        Seller s1_ = Instances.newSeller();
        s1_.setSell(SellType.MOVE);
        l_.getGerants().addEntry(newPoint(2,0), s1_);
        Seller s2_ = Instances.newSeller();
        s2_.setSell(SellType.ITEM);
        s2_.getItems().add(I_ITEMBATTLE);
        l_.getGerants().addEntry(newPoint(2,1), s2_);
        Seller s3_ = Instances.newSeller();
        s3_.setSell(SellType.TM);
        s3_.getTm().add(3);
        l_.getGerants().addEntry(newPoint(2,2), s3_);
        pc_.setLevel(l_);
        return pc_;
    }

    private static void sqThree(Level _l) {
        Block bl_ = Instances.newBlock();
        bl_.setHeight(1);
        bl_.setWidth(1);
        bl_.setTileFileName("");
        _l.getBlocks().addEntry(newPoint(0,0),bl_);
        _l.getBlocks().addEntry(newPoint(0,1),bl_);
        _l.getBlocks().addEntry(newPoint(0,2),bl_);
        _l.getBlocks().addEntry(newPoint(1,0),bl_);
        _l.getBlocks().addEntry(newPoint(1,1),bl_);
        _l.getBlocks().addEntry(newPoint(1,2),bl_);
        _l.getBlocks().addEntry(newPoint(2,0),bl_);
        _l.getBlocks().addEntry(newPoint(2,1),bl_);
        _l.getBlocks().addEntry(newPoint(2,2),bl_);
    }

    protected static Road lrOne(Coords _cave, Coords _up, Coords _down, String _n) {
        Road r_ = Instances.newRoad();
        r_.setName(_n);
        LevelRoad lr_ = Instances.newLevelRoad();
        Block one_ = Instances.newBlock();
        one_.setHeight(1);
        one_.setWidth(1);
        one_.setTileFileName("");
        one_.setIndexApparition(0);
        Block two_ = Instances.newBlock();
        two_.setHeight(1);
        two_.setWidth(1);
        two_.setTileFileName("");
        two_.setIndexApparition(1);
        Block three_ = Instances.newBlock();
        three_.setHeight(1);
        three_.setWidth(1);
        three_.setTileFileName("");
        three_.setIndexApparition(-1);
        lr_.getWildPokemonAreas().add(areaOne());
        lr_.getWildPokemonAreas().add(areaTwo());
        lr_.getBlocks().addEntry(newPoint(0,0),one_);
        lr_.getBlocks().addEntry(newPoint(0,1),two_);
        lr_.getBlocks().addEntry(newPoint(1,0),three_);
        lr_.getBlocks().addEntry(newPoint(1,1),three_);
        r_.setLevel(lr_);
        r_.getLinksWithCaves().addEntry(newPoint(1,0),new Link("",_cave));
        r_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(0,1),Direction.DOWN),_down);
        r_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.UP),_up);
        return r_;
    }
    protected static Cave lcOne(int _pl, Coords _join) {
        Cave c_ = Instances.newCave();
        c_.setName(PL_4);
        LevelCave first_ = Instances.newLevelCave();
        sqThree(first_);
        first_.getLegendaryPks().addEntry(newPoint(2,2),wp(P_POK_18,A_ABILITY2,I_REPEL));
        first_.getLegendaryPks().addEntry(newPoint(1,1),wp(P_POK_19,A_ABILITY,I_SELLING));
        first_.getDualFights().addEntry(newPoint(2,0),dual());
        c_.getLevels().add(first_);
        LevelCave sec_ = Instances.newLevelCave();
        sqThree(sec_);
        sec_.getCharacters().addEntry(newPoint(1,0),trMult());
        DealerItem dOne_ = Instances.newDealerItem();
        dOne_.getItems().add(I_BALL);
        sec_.getCharacters().addEntry(newPoint(1,1), dOne_);
        DealerItem dTwo_ = Instances.newDealerItem();
        dTwo_.getTechnicalMoves().add( 2);
        sec_.getCharacters().addEntry(newPoint(1,2), dTwo_);
        sec_.getItems().addEntry(newPoint(2,0),I_BOOST);
        sec_.getHm().addEntry(newPoint(2,1),1);
        sec_.getTm().addEntry(newPoint(2,2),1);
        c_.getLevels().add(sec_);
        first_.getLinksOtherLevels().addEntry(newPoint(0,0),new Link("",newCoords(_pl,1,0,0)));
        sec_.getLinksOtherLevels().addEntry(newPoint(0,0),new Link("",newCoords(_pl,0,0,0)));
        c_.getLinksWithOtherPlaces().addEntry(newCoords(0,0,0,1).getLevel(),new Link("",_join));
        return c_;
    }
    protected static AreaApparition areaOne() {
        AreaApparition a_ = Instances.newAreaApparition();
        a_.setMultFight(1);
        a_.setAvgNbSteps( 5);
        a_.getWildPokemon().add(wp(P_POK_00,A_ABILITY,I_FOSSIL));
        a_.getWildPokemon().add(wp(P_POK_01,A_ABILITY2,I_HEAL_STATUS));
        a_.getWildPokemonFishing().add(wp(P_POK_02,A_ABILITY,I_EVO_ITEM));
        a_.getWildPokemonFishing().add(wp(P_POK_03,A_ABILITY2,I_EVO_STONE));
        return a_;
    }
    protected static AreaApparition areaTwo() {
        AreaApparition a_ = Instances.newAreaApparition();
        a_.setMultFight(1);
        a_.setAvgNbSteps( 10);
        a_.getWildPokemon().add(wp(P_POK_04,A_ABILITY,I_FOSSIL));
        a_.getWildPokemon().add(wp(P_POK_05,A_ABILITY2,I_HEAL_STATUS));
        a_.getWildPokemonFishing().add(wp(P_POK_06,A_ABILITY,I_EVO_ITEM));
        a_.getWildPokemonFishing().add(wp(P_POK_07,A_ABILITY2,I_EVO_STONE));
        return a_;
    }
    protected static AreaApparition areaThree() {
        AreaApparition a_ = Instances.newAreaApparition();
        a_.setMultFight(1);
        a_.getWildPokemon().add(wp(P_POK_08,A_ABILITY,I_HEAL_STATUS));
        a_.getWildPokemon().add(wp(P_POK_09,A_ABILITY2,I_HEAL));
        a_.getWildPokemonFishing().add(wp(P_POK_10,A_ABILITY,I_HEAL_PP));
        a_.getWildPokemonFishing().add(wp(P_POK_11,A_ABILITY2,I_HEAL_HP));
        return a_;
    }
    protected static WildPk wp(String _name, String _ab, String _it) {
        WildPk pk_ = Instances.newWildPk();
        pk_.setName(_name);
        pk_.setLevel( 4);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(_ab);
        pk_.setItem(_it);
        return pk_;
    }
    public static int[][] line(int _c, int _d) {
        return new int[][]{new int[]{_c, _d}};
    }
}
