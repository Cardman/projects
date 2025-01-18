package aiki.beans.solution;

import aiki.beans.*;
import aiki.beans.db.InitDbConstr;
import aiki.beans.facade.solution.dto.*;
import aiki.db.*;
import aiki.facade.*;
import aiki.fight.pokemon.*;
import aiki.fight.pokemon.enums.*;
import aiki.fight.pokemon.evolution.*;
import aiki.instances.Instances;
import aiki.map.*;
import aiki.map.buildings.*;
import aiki.map.characters.*;
import aiki.map.enums.*;
import aiki.map.levels.*;
import aiki.map.levels.enums.*;
import aiki.map.places.*;
import aiki.map.pokemon.*;
import aiki.map.pokemon.enums.*;
import aiki.map.util.*;
import aiki.util.Coords;
import code.bean.nat.*;
import code.maths.*;
import code.util.*;

public abstract class InitDbSolution extends InitDbConstr {
    public static final String P_POK_00 = "P_POK00";
    public static final String P_POK_01 = "P_POK01";
    public static final String P_POK_02 = "P_POK02";
    public static final String P_POK_03 = "P_POK03";
    public static final String P_POK_04 = "P_POK04";
    public static final String P_POK_05 = "P_POK05";

    public static final String P_POK_00_TR = "P_POK00_TR";
    public static final String P_POK_01_TR = "P_POK01_TR";
    public static final String P_POK_02_TR = "P_POK02_TR";
    public static final String P_POK_03_TR = "P_POK03_TR";
    public static final String P_POK_04_TR = "P_POK04_TR";
    public static final String P_POK_05_TR = "P_POK05_TR";

    public static final String ROAD1 = "R 1";
    public static final String ROAD2 = "R 2";
    public static final String CITY1 = "CI 1";
    public static final String CITY2 = "CI 2";
    public static final String CAVE = "CA 1";
    public static final String LEAGUE = "L 1";

    public static final String T_L = "T L";
    public static final String G_L = "G L";
    public static final String D_T_1 = "D T 1";
    public static final String D_T_2 = "D T 2";
    public static final int IMG_00 = 0;
    public static final int IMG_01 = IMG_00 + 1;
    public static final int IMG_02 = IMG_01 + 1;
    public static final int IMG_03 = IMG_02 + 1;
    public static final int IMG_04 = IMG_03 + 1;
    public static final int IMG_05 = IMG_04 + 1;
    public static final int IMG_OTHER = 16777215;

    public static NaSt callSolutionBeanGetPlace(int _step, int _place) {
        return BeanPokemonCommonTs.callLongs(new SolutionBeanGetPlace(),dispSol(),_step,_place);
    }

    public static NaSt callSolutionBeanStepsGet() {
        return BeanPokemonCommonTs.callLongs(new SolutionBeanStepsGet(),dispSol());
    }

    public static NaSt callSolutionBeanStepsTwiceGet() {
        return BeanPokemonCommonTs.callLongs(new SolutionBeanStepsGet(),dispSolTwice());
    }

    public static NaSt callPlaceTrainerDtoPlaceGet(NaSt _str) {
        return BeanPokemonCommonTs.callLongs(new PlaceTrainerDtoPlaceGet(),_str);
    }

    public static NaSt callPlaceTrainerDtoTrainerGet(NaSt _str) {
        return BeanPokemonCommonTs.callLongs(new PlaceTrainerDtoTrainerGet(),_str);
    }

    public static NaSt callStepDtoGetNames(NaSt _str) {
        return BeanPokemonCommonTs.callLongs(new StepDtoGetNames(),_str);
    }

    public static NaSt callStepDtoGetPokemon(NaSt _str) {
        return BeanPokemonCommonTs.callLongs(new StepDtoGetPokemon(),_str);
    }

    public static NaSt callWildPokemonDtoGenderGet(NaSt _str) {
        return BeanPokemonCommonTs.callLongs(new WildPokemonDtoGenderGet(),_str);
    }

    public static NaSt callWildPokemonDtoImageGet(NaSt _str) {
        return BeanPokemonCommonTs.callLongs(new WildPokemonDtoImageGet(),_str);
    }

    public static NaSt callWildPokemonDtoNameGet(NaSt _str) {
        return BeanPokemonCommonTs.callLongs(new WildPokemonDtoNameGet(),_str);
    }
    protected static NaSt dispSol() {
        PkData pk_ = pkDataByFacade(db());
        return dispSol(pk_);
    }
    protected static NaSt dispSolTwice() {
        PkData pk_ = pkDataByFacade(db());
        NaSt bean_ = dispSol(pk_);
        beforeDisplaying(bean_);
        return bean_;
    }

    private static NaSt dispSol(PkData _pk) {
        NaSt welcome_ = _pk.beanSolutionBean(EN);
        beforeDisplaying(welcome_);
        return welcome_;
    }

    private static FacadeGame db() {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers(P_POK_00,specPk(P_POK_00,P_POK_01));
        facade_.getData().completeMembers(P_POK_01,specPkEvo(P_POK_00));
        facade_.getData().completeMembers(P_POK_02,specPk(P_POK_02,P_POK_03));
        facade_.getData().completeMembers(P_POK_03,specPkEvo(P_POK_02));
        facade_.getData().completeMembers(P_POK_04,specLeg(P_POK_04));
        facade_.getData().completeMembers(P_POK_05,specLeg(P_POK_05));

        trsCore(facade_);
        DataBase data_ = facade_.getData();
        data_.setMap(dm());
        DataMap map_ = data_.getMap();
        map_.initializeLinks();
        map_.initInteractiveElements();
        map_.initializeTree();
        map_.initializeAccessibility();
        return facade_;
    }

    protected static DataMap dm() {
        DataMap d_ = Instances.newDataMap();
        d_.setBegin(newCoords(0,0,0,0));
        d_.getPlaces().add(cityOne(newCoords(1,0,0,0)));
        d_.getPlaces().add(roadOne(newCoords(0,0,2,0),newCoords(2,0,0,0)));
        d_.getPlaces().add(cityTwo(newCoords(1,0,1,0),newCoords(3,0,0,0)));
        d_.getPlaces().add(roadTwo(newCoords(2,0,2,0),newCoords(5,0,0,1)));
        d_.getPlaces().add(league());
        d_.getPlaces().add(cave(newCoords(3,0,3,0)));
        d_.getAccessCondition().addEntry(newCoords(3,0,0,0),Condition.newList(newCoords(2,0,1,0,1,0)));
        d_.getAccessCondition().addEntry(newCoords(3,0,0,1),Condition.newList(newCoords(2,0,1,0,1,0)));
        d_.getAccessCondition().addEntry(newCoords(3,0,0,2),Condition.newList(newCoords(2,0,1,0,1,0)));
//        d_.getAccessCondition().addEntry(newCoords(3,0,1,2),Condition.newList(newCoords(3,0,2,1)));
        d_.getAccessCondition().addEntry(newCoords(3,0,2,2),Condition.newList(newCoords(3,0,2,1)));
        d_.getAccessCondition().addEntry(newCoords(3,0,3,2),Condition.newList(newCoords(3,0,2,1)));
        d_.getAccessCondition().addEntry(newCoords(3,0,3,0),Condition.newList(newCoords(4,0,1,0)));
//        d_.join(0,1,newPoint(2,0),newPoint(0,0),Direction.RIGHT);
//        d_.join(1,2,newPoint(1,0),newPoint(0,0),Direction.RIGHT);
//        d_.join(2,3,newPoint(2,0),newPoint(0,0),Direction.RIGHT);
        return d_;
    }

    //CI 1 - R 1 - CI 2 - R 2 - C
    //                     | L
    protected static City cityOne(Coords _other) {
        City c_ = Instances.newCity();
        c_.setName(CITY1);
        sqThree(c_.getLevel());
        c_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(2,0), Direction.RIGHT),_other);
        return c_;
    }
    protected static Road roadOne(Coords _left, Coords _right) {
        Road c_ = Instances.newRoad();
        c_.setName(ROAD1);
        Block one_ = Instances.newBlock();
        one_.setType(EnvironmentType.ROAD);
        one_.setHeight(1);
        one_.setWidth(1);
        one_.setTileFileName("");
        one_.setIndexApparition(0);
        c_.getLevelRoad().getBlocks().addEntry(newPoint(0,0),one_);
        c_.getLevelRoad().getBlocks().addEntry(newPoint(0,1),one_);
        c_.getLevelRoad().getBlocks().addEntry(newPoint(1,0),one_);
        c_.getLevelRoad().getBlocks().addEntry(newPoint(1,1),one_);
        c_.getLevelRoad().getWildPokemonAreas().add(area(P_POK_00));
        c_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),_left);
        c_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(1,0),Direction.RIGHT),_right);
        return c_;
    }

    protected static City cityTwo(Coords _left, Coords _right) {
        City c_ = Instances.newCity();
        c_.setName(CITY2);
        sqThree(c_.getLevel());
        c_.getLevel().getBlocks().getVal(newPoint(1,0)).setType(EnvironmentType.NOTHING);
        c_.getBuildings().addEntry(newPoint(1,0),gym());
        c_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),_left);
        c_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(2,0),Direction.RIGHT),_right);
        return c_;
    }

    protected static Gym gym() {
        Gym g_ = Instances.newGym();
        g_.setExitCity(newPoint(1,2));
        g_.getIndoor().setGymLeaderCoords(newPoint(1,0));
//        g_.getIndoor().getGymTrainers().addEntry(newPoint(1,1),trGymTrainer());
        g_.getIndoor().setGymLeader(trGymLeader());
        Block bl_ = Instances.newBlock();
        bl_.setType(EnvironmentType.BUILDING);
        bl_.setHeight(1);
        bl_.setWidth(1);
        bl_.setTileFileName("");
        g_.getIndoor().getBlocks().addEntry(newPoint(1,0),bl_);
        g_.getIndoor().getBlocks().addEntry(newPoint(1,1),bl_);
        g_.getIndoor().getBlocks().addEntry(newPoint(1,2),bl_);
        g_.getIndoor().getBlocks().addEntry(newPoint(1,3),bl_);
        return g_;
    }

    protected static GymLeader trGymLeader() {
        GymLeader tmf_ = Instances.newGymLeader();
        tmf_.setName(G_L);
//        tmf_.setImageMaxiFileName(SINGLE);
//        tmf_.setImageMiniFileName(SI);
//        mult(1, tmf_);
//        tmf_.getTeam().add(wpOne(P_POK_14,A_ABILITY2,I_HEAL,18));
//        tmf_.getTeam().add(wpTwo(P_POK_15,A_ABILITY,I_HEAL_HP,19));
//        tmf_.setTm(2);
        return tmf_;
    }
    protected static Road roadTwo(Coords _left, Coords _right) {
        Road c_ = Instances.newRoad();
        c_.setName(ROAD2);
        Block one_ = Instances.newBlock();
        one_.setType(EnvironmentType.ROAD);
        one_.setHeight(1);
        one_.setWidth(1);
        one_.setTileFileName("");
        one_.setIndexApparition(0);
        Block two_ = Instances.newBlock();
        two_.setType(EnvironmentType.ROAD);
        two_.setHeight(1);
        two_.setWidth(1);
        two_.setTileFileName("");
        two_.setIndexApparition(-1);
        c_.getLevelRoad().getBlocks().addEntry(newPoint(0,0),two_);
        c_.getLevelRoad().getBlocks().addEntry(newPoint(0,1),two_);
        c_.getLevelRoad().getBlocks().addEntry(newPoint(0,2),two_);
        c_.getLevelRoad().getBlocks().addEntry(newPoint(1,0),one_);
        c_.getLevelRoad().getBlocks().addEntry(newPoint(1,1),one_);
        c_.getLevelRoad().getBlocks().addEntry(newPoint(1,2),two_);
        c_.getLevelRoad().getBlocks().addEntry(newPoint(2,0),one_);
        c_.getLevelRoad().getBlocks().addEntry(newPoint(2,1),one_);
        c_.getLevelRoad().getBlocks().addEntry(newPoint(2,2),two_);
        c_.getLevelRoad().getBlocks().addEntry(newPoint(3,0),two_);
        c_.getLevelRoad().getBlocks().addEntry(newPoint(3,1),two_);
        c_.getLevelRoad().getBlocks().addEntry(newPoint(3,2),two_);
        c_.getLevelRoad().getDualFights().addEntry(newPoint(2,1),dual());
        c_.getLevelRoad().getWildPokemonAreas().add(area(P_POK_02));
        c_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),_left);
        c_.getLinksWithCaves().addEntry(newPoint(3,0),new Link(NULL_REF,_right));
        return c_;
    }

    protected static DualFight dual() {
        DualFight d_ = Instances.newDualFight();
        d_.setNames(new StringList(D_T_1, D_T_2));
//        d_.getFoeTrainer().setImageMaxiFileName(DUAL);
//        d_.getFoeTrainer().setImageMiniFileName(DUAL_1);
//        d_.getFoeTrainer().setImageMiniSecondTrainerFileName(DUAL_2);
        d_.setPt(newPoint(2,0));
//        d_.getAlly().getTeam().add(wpOne(P_POK_16,A_ABILITY,I_HEAL_HP_STATUS,18));
//        d_.getAlly().getTeam().add(wpTwo(P_POK_17,A_ABILITY2,I_HEAL_PP,19));
//        d_.getFoeTrainer().getTeam().add(wpOne(P_POK_14,A_ABILITY2,I_HEAL,18));
//        d_.getFoeTrainer().getTeam().add(wpTwo(P_POK_15,A_ABILITY,I_HEAL_HP,19));
//        d_.getFoeTrainer().setReward( 25);
        return d_;
    }
    protected static AreaApparition area(String _pk) {
        AreaApparition a_ = Instances.newAreaApparition();
        a_.setMultFight(1);
        a_.getWildPokemon().add(wp(_pk));
        return a_;
    }
    protected static League league() {
        League l_ = Instances.newLeague();
        l_.setName(LEAGUE);
        l_.setBegin(newPoint(1,0));
        LevelLeague one_ = Instances.newLevelLeague();
        one_.setTrainer(trLeague());
        sqThree(one_);
        one_.setTrainerCoords(newPoint(1,1));
        one_.setAccessPoint(newPoint(1,2));
        l_.getRooms().add(one_);
        l_.setAccessCoords(newCoords(3,0,2,2));
        return l_;
    }
    protected static Cave cave(Coords _join) {
        Cave c_ = Instances.newCave();
        c_.setName(CAVE);
        LevelCave first_ = Instances.newLevelCave();
        sqThree(first_);
        first_.getLegendaryPks().addEntry(newPoint(2,2),wp(P_POK_04));
        c_.getLevels().add(first_);
        LevelCave sec_ = Instances.newLevelCave();
        sqThree(sec_);
        sec_.getWildPokemonAreas().add(area(P_POK_05));
        sec_.getBlocks().getVal(newPoint(1,0)).setIndexApparition( 0);
        c_.getLevels().add(sec_);
        first_.getLinksOtherLevels().addEntry(newPoint(0,0),new Link("",newCoords(5,1,0,0)));
        sec_.getLinksOtherLevels().addEntry(newPoint(0,0),new Link("",newCoords(5,0,0,0)));
        c_.getLinksWithOtherPlaces().addEntry(newCoords(0,0,0,1).getLevel(),new Link("",_join));
        return c_;
    }
    protected static TrainerLeague trLeague() {
        TrainerLeague tmf_ = Instances.newTrainerLeague();
        tmf_.setName(T_L);
//        tmf_.setImageMaxiFileName(SINGLE);
//        tmf_.setImageMiniFileName(SI);
//        mult(1, tmf_);
//        tmf_.getTeam().add(wpOne(P_POK_12,A_ABILITY,I_BOOST,20));
//        tmf_.getTeam().add(wpTwo(P_POK_13,A_ABILITY2,I_ITEMBATTLE,25));
        return tmf_;
    }
    protected static void trsCore(FacadeGame _facade) {
        _facade.getData().getTranslatedAbilities().addEntry(EN,new StringMap<String>());
//        _facade.getData().getTranslatedTypes().addEntry(EN,new StringMap<String>());
//        _facade.getData().getTranslatedTypes().getVal(EN).addEntry(T_TYPE1, T_TYPE1_TR);
//        _facade.getData().getTranslatedCategories().addEntry(EN,new StringMap<String>());
//        _facade.getData().getTranslatedCategories().getVal(EN).addEntry(C_CAT, C_CAT1_TR);
//        _facade.getData().getTranslatedCategories().getVal(EN).addEntry(AUTRE,C_CAT2_TR);
        _facade.getData().getTranslatedItems().addEntry(EN,new StringMap<String>());
        _facade.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
//        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_POK_00,M_POK_00_TR);
//        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_POK_01,M_POK_01_TR);
//        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_POK_02,M_POK_02_TR);
//        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_POK_03,M_POK_03_TR);
//        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_POK_04,M_POK_04_TR);
//        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_POK_05,M_POK_05_TR);
//        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_POK_06,M_POK_06_TR);
//        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_POK_07,M_POK_07_TR);
        _facade.getData().getTranslatedPokemon().addEntry(EN,new StringMap<String>());
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_00, P_POK_00_TR);
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_01, P_POK_01_TR);
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_02, P_POK_02_TR);
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_03, P_POK_03_TR);
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_04, P_POK_04_TR);
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_05, P_POK_05_TR);
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
        _facade.getData().getMaxiPkFront().addEntry(P_POK_00, instance(IMG_00));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_01,instance(IMG_01));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_02,instance(IMG_02));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_03,instance(IMG_03));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_04,instance(IMG_04));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_05,instance(IMG_05));
        _facade.getData().getMiniPk().addEntry(P_POK_00,instance(IMG_00));
        _facade.getData().getMiniPk().addEntry(P_POK_01,instance(IMG_01));
        _facade.getData().getMiniPk().addEntry(P_POK_02,instance(IMG_02));
        _facade.getData().getMiniPk().addEntry(P_POK_03,instance(IMG_03));
        _facade.getData().getMiniPk().addEntry(P_POK_04,instance(IMG_04));
        _facade.getData().getMiniPk().addEntry(P_POK_05,instance(IMG_05));
//        _facade.getData().getTrainers().addEntry(DUAL,BaseSixtyFourUtil.getImageByString("AAACAAAWAAAX"));
//        _facade.getData().getTrainers().addEntry(SINGLE,BaseSixtyFourUtil.getImageByString("AAABAAAW"));
//        _facade.getData().getPeople().addEntry(DUAL_1,BaseSixtyFourUtil.getImageByString("AAABAAAX"));
//        _facade.getData().getPeople().addEntry(DUAL_2,BaseSixtyFourUtil.getImageByString("AAABAAAY"));
//        _facade.getData().getPeople().addEntry(SI,BaseSixtyFourUtil.getImageByString("AAABAAAZ"));
//        _facade.getData().getPeople().addEntry(NULL_REF,BaseSixtyFourUtil.getImageByString("AAAB////"));
        _facade.getData().getImages().addEntry(NULL_REF,instance(IMG_OTHER));
        _facade.getData().getLinks().addEntry(NULL_REF,instance(IMG_OTHER));
        _facade.getData().setImageTmHm(instance(IMG_OTHER));
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

    private static void sqThree(Level _l) {
        Block bl_ = Instances.newBlock();
        bl_.setHeight(1);
        bl_.setWidth(1);
        bl_.setTileFileName("");
        bl_.setType(EnvironmentType.ROAD);
        Block not_ = Instances.newBlock();
        not_.setHeight(1);
        not_.setWidth(1);
        not_.setTileFileName("");
        not_.setType(EnvironmentType.ROAD);
        _l.getBlocks().addEntry(newPoint(0,0),bl_);
        _l.getBlocks().addEntry(newPoint(0,1),bl_);
        _l.getBlocks().addEntry(newPoint(0,2),bl_);
        _l.getBlocks().addEntry(newPoint(1,0),not_);
        _l.getBlocks().addEntry(newPoint(1,1),bl_);
        _l.getBlocks().addEntry(newPoint(1,2),bl_);
        _l.getBlocks().addEntry(newPoint(2,0),bl_);
        _l.getBlocks().addEntry(newPoint(2,1),bl_);
        _l.getBlocks().addEntry(newPoint(2,2),bl_);
    }

    private static PokemonData specPk(String _base, String _evo) {
        PokemonData pk_ = pk(new StringList("__"), GenderRepartition.NO_GENDER);
        pk_.setBaseEvo(_base);
        EvolutionLevelSimple e_ = Instances.newEvolutionLevelSimple();
        e_.setLevel( 16);
        pk_.getEvolutions().addEntry(_evo,e_);
        return pk_;
    }

    private static PokemonData specPkEvo(String _base) {
        PokemonData pk_ = pk(new StringList("__"), GenderRepartition.NO_GENDER);
        pk_.setBaseEvo(_base);
        return pk_;
    }

    private static PokemonData specLeg(String _base) {
        PokemonData pk_ = pk(new StringList("__"), GenderRepartition.LEGENDARY);
        pk_.setBaseEvo(_base);
        return pk_;
    }
    protected static WildPk wp(String _name) {
        WildPk pk_ = Instances.newWildPk();
        pk_.setName(_name);
        pk_.setLevel( 4);
        pk_.setGender(Gender.NO_GENDER);
//        pk_.setAbility(_ab);
//        pk_.setItem(_it);
        return pk_;
    }
}
