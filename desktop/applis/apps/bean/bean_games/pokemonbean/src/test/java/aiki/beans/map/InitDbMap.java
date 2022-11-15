package aiki.beans.map;

import aiki.beans.*;
import aiki.beans.db.*;
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
import code.expressionlanguage.structs.Struct;
import code.images.*;
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
    public static final String ROAD = "R 1";
    public static final String CITY = "CI 1";
    public static final String CAVE = "CA 1";

    protected static final String I_BALL_TR = "I_BALL_TR";
    protected static final String I_BERRY_TR = "I_BERRY_TR";
    protected static final String I_BOOST_TR = "I_BOOST_TR";
    protected static final String I_EVO_ITEM_TR = "I_EVO_ITEM_TR";
    protected static final String I_EVO_STONE_TR = "I_EVO_STONE_TR";
    protected static final String I_FOSSIL_TR = "I_FOSSIL_TR";
    protected static final String I_HEAL_TR = "I_HEAL_TR";
    protected static final String I_HEAL_HP_STATUS_TR = "I_HEAL_HP_STATUS_TR";
    protected static final String I_HEAL_HP_TR = "I_HEAL_HP_TR";
    protected static final String I_HEAL_PP_TR = "I_HEAL_PP_TR";
    protected static final String I_HEAL_STATUS_TR = "I_HEAL_STATUS_TR";
    protected static final String I_ITEMBATTLE_TR = "I_ITEMBATTLE_TR";
    protected static final String I_REPEL_TR = "I_REPEL_TR";
    protected static final String I_SELLING_TR = "I_SELLING_TR";
    protected static final String I_BALL = "I_BALL";
    protected static final String I_BERRY = "I_BERRY";
    protected static final String I_BOOST = "I_BOOST";
    protected static final String I_EVO_ITEM = "I_EVO_ITEM";
    protected static final String I_EVO_STONE = "I_EVO_STONE";
    protected static final String I_FOSSIL = "I_FOSSIL";
    protected static final String I_HEAL = "I_HEAL";
    protected static final String I_HEAL_HP_STATUS = "I_HEAL_HP_STATUS";
    protected static final String I_HEAL_HP = "I_HEAL_HP";
    protected static final String I_HEAL_PP = "I_HEAL_PP";
    protected static final String I_HEAL_STATUS = "I_HEAL_STATUS";
    protected static final String I_ITEMBATTLE = "I_ITEMBATTLE";
    protected static final String I_REPEL = "I_REPEL";
    protected static final String I_SELLING = "I_SELLING";

    protected static final String A_ABILITY2="B_ABILITY";
    protected static final String A_ABILITY2_TR="B_ABILITY_TR";
    public static final String DUAL = "dual";
    public static final String SINGLE = "single";
    public static final String DUAL_1 = "dual_1";
    public static final String DUAL_2 = "dual_2";
    public static final String SI = "si";

    public static Struct callMapBeanClickLevel(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapBeanClickLevel(),_str,_args);
    }

    public static Struct callMapBeanClickLevelZero(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapBeanClickLevelZero(),_str,_args);
    }

    public static Struct callMapBeanIsMultiLayer(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapBeanIsMultiLayer(),_str,_args);
    }

    public static Struct callMapBeanLayers(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapBeanLayers(),_str,_args);
    }

    public static Struct callMapBeanPlacesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapBeanPlacesGet(),_str,_args);
    }

    protected static PokemonData pkOne() {
        return pk(M_POK_00,M_POK_01);
    }

    protected static PokemonData pkTwo() {
        return pk(M_POK_02,M_POK_03);
    }

    protected static PokemonData pk(String _one, String _second) {
        PokemonData pk_ = Instances.newPokemonData();
        pk_.getLevMoves().add(new LevelMove((short) 1,_one));
        pk_.getLevMoves().add(new LevelMove((short) 3,_second));
        return pk_;
    }
    protected static DualFight dual() {
        DualFight d_ = Instances.newDualFight();
        d_.getFoeTrainer().setImageMaxiFileName(DUAL);
        d_.getFoeTrainer().setImageMiniFileName(DUAL_1);
        d_.getFoeTrainer().setImageMiniSecondTrainerFileName(DUAL_2);
        d_.setPt(newPoint(0,1));
        d_.getAlly().getTeam().add(wpOne(P_POK_16,A_ABILITY,I_HEAL_HP_STATUS,18));
        d_.getAlly().getTeam().add(wpTwo(P_POK_17,A_ABILITY2,I_HEAL_PP,19));
        d_.getFoeTrainer().getTeam().add(wpOne(P_POK_14,A_ABILITY2,I_HEAL,18));
        d_.getFoeTrainer().getTeam().add(wpTwo(P_POK_15,A_ABILITY,I_HEAL_HP,19));
        return d_;
    }
    protected static TrainerLeague trLeagueOne() {
        TrainerLeague tmf_ = Instances.newTrainerLeague();
        tmf_.setImageMaxiFileName(SINGLE);
        tmf_.setImageMiniFileName(SI);
        mult((byte) 1, tmf_);
        tmf_.getTeam().add(wpOne(P_POK_12,A_ABILITY,I_BOOST,20));
        tmf_.getTeam().add(wpTwo(P_POK_13,A_ABILITY2,I_ITEMBATTLE,25));
        return tmf_;
    }
    protected static TrainerLeague trLeagueTwo() {
        TrainerLeague tmf_ = Instances.newTrainerLeague();
        tmf_.setImageMaxiFileName(SINGLE);
        tmf_.setImageMiniFileName(SI);
        mult((byte) 1, tmf_);
        tmf_.getTeam().add(wpOne(P_POK_20,A_ABILITY,I_BOOST,20));
        tmf_.getTeam().add(wpTwo(P_POK_21,A_ABILITY2,I_ITEMBATTLE,25));
        return tmf_;
    }
    protected static GymLeader trGymLeader() {
        GymLeader tmf_ = Instances.newGymLeader();
        tmf_.setImageMaxiFileName(SINGLE);
        tmf_.setImageMiniFileName(SI);
        mult((byte) 1, tmf_);
        tmf_.getTeam().add(wpOne(P_POK_14,A_ABILITY2,I_HEAL,18));
        tmf_.getTeam().add(wpTwo(P_POK_15,A_ABILITY,I_HEAL_HP,19));
        return tmf_;
    }
    protected static GymTrainer trGymTrainer() {
        GymTrainer tmf_ = Instances.newGymTrainer();
        tmf_.setImageMaxiFileName(SINGLE);
        tmf_.setImageMiniFileName(SI);
        mult((byte) 1, tmf_);
        tmf_.getTeam().add(wpOne(P_POK_16,A_ABILITY,I_HEAL_HP_STATUS,18));
        tmf_.getTeam().add(wpTwo(P_POK_17,A_ABILITY2,I_HEAL_PP,19));
        return tmf_;
    }
    protected static TrainerMultiFights trMult() {
        TrainerMultiFights tmf_ = Instances.newTrainerMultiFights();
        mult((byte) 2, tmf_);
        tmf_.getTeamsRewards().add(teamOne());
        tmf_.getTeamsRewards().add(teamTwo());
        return tmf_;
    }

    private static void mult(byte _m, Trainer _t) {
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
        t_.setReward((short) _v);
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
        pk_.setLevel((short) _level);
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
        _facade.getData().getMaxiPkFront().addEntry(P_POK_00,BaseSixtyFourUtil.getImageByString("AAABAAAA"));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_01,BaseSixtyFourUtil.getImageByString("AAABAAAB"));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_02,BaseSixtyFourUtil.getImageByString("AAABAAAC"));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_03,BaseSixtyFourUtil.getImageByString("AAABAAAD"));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_04,BaseSixtyFourUtil.getImageByString("AAABAAAE"));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_05,BaseSixtyFourUtil.getImageByString("AAABAAAF"));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_06,BaseSixtyFourUtil.getImageByString("AAABAAAG"));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_07,BaseSixtyFourUtil.getImageByString("AAABAAAH"));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_08,BaseSixtyFourUtil.getImageByString("AAABAAAI"));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_09,BaseSixtyFourUtil.getImageByString("AAABAAAJ"));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_10,BaseSixtyFourUtil.getImageByString("AAABAAAK"));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_11,BaseSixtyFourUtil.getImageByString("AAABAAAL"));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_12,BaseSixtyFourUtil.getImageByString("AAABAAAM"));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_13,BaseSixtyFourUtil.getImageByString("AAABAAAN"));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_14,BaseSixtyFourUtil.getImageByString("AAABAAAO"));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_15,BaseSixtyFourUtil.getImageByString("AAABAAAP"));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_16,BaseSixtyFourUtil.getImageByString("AAABAAAQ"));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_17,BaseSixtyFourUtil.getImageByString("AAABAAAR"));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_18,BaseSixtyFourUtil.getImageByString("AAABAAAS"));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_19,BaseSixtyFourUtil.getImageByString("AAABAAAT"));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_20,BaseSixtyFourUtil.getImageByString("AAABAAAU"));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_21,BaseSixtyFourUtil.getImageByString("AAABAAAV"));

        _facade.getData().getTrainers().addEntry(DUAL,BaseSixtyFourUtil.getImageByString("AAACAAAWAAAX"));
        _facade.getData().getTrainers().addEntry(SINGLE,BaseSixtyFourUtil.getImageByString("AAABAAAW"));
        _facade.getData().getPeople().addEntry(DUAL_1,BaseSixtyFourUtil.getImageByString("AAABAAAX"));
        _facade.getData().getPeople().addEntry(DUAL_2,BaseSixtyFourUtil.getImageByString("AAABAAAY"));
        _facade.getData().getPeople().addEntry(SI,BaseSixtyFourUtil.getImageByString("AAABAAAZ"));
        _facade.getData().getPeople().addEntry(NULL_REF,BaseSixtyFourUtil.getImageByString("AAAB////"));
        _facade.getData().getImages().addEntry(NULL_REF,BaseSixtyFourUtil.getImageByString("AAAB////"));
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
        d_.getPlaces().add(lrOne(newCoords(3,0,0,1),newCoords(1,0,1,2),newCoords(0,0,1,0)));
        d_.getPlaces().add(lcOne(d_.getPlaces().size(),newCoords(2,0,1,0)));
        d_.getPlaces().add(city(Direction.LEFT,newCoords(5,0,0,0)));
        d_.getPlaces().add(city(Direction.RIGHT,newCoords(4,0,0,0)));
        d_.getPlaces().add(road(Direction.LEFT,newCoords(7,0,0,0)));
        d_.getPlaces().add(road(Direction.RIGHT,newCoords(6,0,0,0)));
        d_.getPlaces().add(league(newCoords(3,0,0,2)));
        d_.getAccessCondition().addEntry(newCoords(3,0,0,2),new Condition());
        d_.join((short)0,(short)2,newPoint(0,1),newPoint(0,1),Direction.UP);
        d_.join((short)2,(short)1,newPoint(0,0),newPoint(0,0),Direction.UP);
        d_.join((short)4,(short)5,newPoint(0,0),newPoint(0,0),Direction.LEFT);
        d_.join((short)6,(short)7,newPoint(0,0),newPoint(0,0),Direction.LEFT);
        return d_;
    }
    protected static League league(Coords _c) {
        League l_ = Instances.newLeague();
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
    protected static City city(Direction _dir,Coords _other) {
        City c_ = Instances.newCity();
        c_.setName(CITY);
        sqThree(c_.getLevel());
        c_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(0,0),_dir),_other);
        return c_;
    }
    protected static Road road(Direction _dir,Coords _other) {
        Road c_ = Instances.newRoad();
        c_.setName(ROAD);
        Block one_ = Instances.newBlock();
        one_.setHeight((short) 1);
        one_.setWidth((short) 1);
        one_.setTileFileName("");
        one_.setIndexApparition((short)0);
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
        c_.setName(CITY);
        sqThree(c_.getLevel());
        c_.getBuildings().addEntry(newPoint(1,1),gym());
        c_.getPointsWithCitiesAndOtherRoads().addEntry(new PlaceInterConnect(newPoint(1,0),Direction.UP),_other);
        return c_;
    }
    protected static City cityTwo(Coords _other) {
        City c_ = Instances.newCity();
        c_.setName(CITY);
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
        bl_.setHeight((short) 1);
        bl_.setWidth((short) 1);
        bl_.setTileFileName("");
        g_.getIndoor().getBlocks().addEntry(newPoint(1,0),bl_);
        g_.getIndoor().getBlocks().addEntry(newPoint(1,1),bl_);
        g_.getIndoor().getBlocks().addEntry(newPoint(1,2),bl_);
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
        s3_.getTm().add((short)3);
        l_.getGerants().addEntry(newPoint(2,2), s3_);
        pc_.setLevel(l_);
        return pc_;
    }

    private static void sqThree(Level _l) {
        Block bl_ = Instances.newBlock();
        bl_.setHeight((short) 1);
        bl_.setWidth((short) 1);
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

    protected static Road lrOne(Coords _cave, Coords _up, Coords _down) {
        Road r_ = Instances.newRoad();
        r_.setName(ROAD);
        LevelRoad lr_ = Instances.newLevelRoad();
        Block one_ = Instances.newBlock();
        one_.setHeight((short) 1);
        one_.setWidth((short) 1);
        one_.setTileFileName("");
        one_.setIndexApparition((short)0);
        Block two_ = Instances.newBlock();
        two_.setHeight((short) 1);
        two_.setWidth((short) 1);
        two_.setTileFileName("");
        two_.setIndexApparition((short)1);
        Block three_ = Instances.newBlock();
        three_.setHeight((short) 1);
        three_.setWidth((short) 1);
        three_.setTileFileName("");
        three_.setIndexApparition((short)-1);
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
        c_.setName(CAVE);
        LevelCave first_ = Instances.newLevelCave();
        sqThree(first_);
        first_.getLegendaryPks().addEntry(newPoint(2,2),wp(P_POK_18,A_ABILITY2,I_REPEL));
        first_.getLegendaryPks().addEntry(newPoint(1,1),wp(P_POK_19,A_ABILITY,I_SELLING));
        c_.getLevels().add(first_);
        LevelCave sec_ = Instances.newLevelCave();
        sqThree(sec_);
        sec_.getDualFights().addEntry(newPoint(0,2),dual());
        sec_.getCharacters().addEntry(newPoint(1,0),trMult());
        DealerItem dOne_ = Instances.newDealerItem();
        dOne_.getItems().add(I_BALL);
        sec_.getCharacters().addEntry(newPoint(1,1), dOne_);
        DealerItem dTwo_ = Instances.newDealerItem();
        dTwo_.getTechnicalMoves().add((short) 2);
        sec_.getCharacters().addEntry(newPoint(1,2), dTwo_);
        sec_.getItems().addEntry(newPoint(2,0),I_BOOST);
        sec_.getHm().addEntry(newPoint(2,1),(short)1);
        sec_.getTm().addEntry(newPoint(2,2),(short)1);
        c_.getLevels().add(sec_);
        first_.getLinksOtherLevels().addEntry(newPoint(0,0),new Link("",newCoords(_pl,1,0,0)));
        sec_.getLinksOtherLevels().addEntry(newPoint(0,0),new Link("",newCoords(_pl,0,0,0)));
        c_.getLinksWithOtherPlaces().addEntry(newCoords(0,0,0,1).getLevel(),new Link("",_join));
        return c_;
    }
    protected static AreaApparition areaOne() {
        AreaApparition a_ = Instances.newAreaApparition();
        a_.setMultFight((byte)1);
        a_.getWildPokemon().add(wp(P_POK_00,A_ABILITY,I_FOSSIL));
        a_.getWildPokemon().add(wp(P_POK_01,A_ABILITY2,I_HEAL_STATUS));
        a_.getWildPokemonFishing().add(wp(P_POK_02,A_ABILITY,I_EVO_ITEM));
        a_.getWildPokemonFishing().add(wp(P_POK_03,A_ABILITY2,I_EVO_STONE));
        return a_;
    }
    protected static AreaApparition areaTwo() {
        AreaApparition a_ = Instances.newAreaApparition();
        a_.setMultFight((byte)1);
        a_.getWildPokemon().add(wp(P_POK_04,A_ABILITY,I_FOSSIL));
        a_.getWildPokemon().add(wp(P_POK_05,A_ABILITY2,I_HEAL_STATUS));
        a_.getWildPokemonFishing().add(wp(P_POK_06,A_ABILITY,I_EVO_ITEM));
        a_.getWildPokemonFishing().add(wp(P_POK_07,A_ABILITY2,I_EVO_STONE));
        return a_;
    }
    protected static AreaApparition areaThree() {
        AreaApparition a_ = Instances.newAreaApparition();
        a_.setMultFight((byte)1);
        a_.getWildPokemon().add(wp(P_POK_08,A_ABILITY,I_HEAL_STATUS));
        a_.getWildPokemon().add(wp(P_POK_09,A_ABILITY2,I_HEAL));
        a_.getWildPokemonFishing().add(wp(P_POK_10,A_ABILITY,I_HEAL_PP));
        a_.getWildPokemonFishing().add(wp(P_POK_11,A_ABILITY2,I_HEAL_HP));
        return a_;
    }
    protected static WildPk wp(String _name, String _ab, String _it) {
        WildPk pk_ = Instances.newWildPk();
        pk_.setName(_name);
        pk_.setLevel((short) 4);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(_ab);
        pk_.setItem(_it);
        return pk_;
    }
}
