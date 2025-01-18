package aiki.beans.help;

import aiki.beans.*;
import aiki.beans.db.*;
import aiki.db.*;
import aiki.facade.*;
import aiki.fight.pokemon.*;
import aiki.fight.util.*;
import aiki.instances.*;
import aiki.map.*;
import aiki.map.places.*;
import aiki.map.pokemon.*;
import aiki.map.pokemon.enums.*;
import aiki.map.util.*;
import code.bean.nat.*;
import code.maths.*;
import code.util.*;

public abstract class InitDbGeneralHelp extends InitDbConstr {
    public static final String M_POK_00 = "M_POK00";
    public static final String M_POK_01 = "M_POK01";
    public static final String M_POK_02 = "M_POK02";
    public static final String M_POK_03 = "M_POK03";
    public static final String P_POK_00 = "P_POK00";
    public static final String P_POK_01 = "P_POK01";
    public static final String I_BALL = "I_BALL";

    public static final String M_POK_00_TR = "M_POK00_TR";
    public static final String M_POK_01_TR = "M_POK01_TR";
    public static final String M_POK_02_TR = "M_POK02_TR";
    public static final String M_POK_03_TR = "M_POK03_TR";
    public static final String P_POK_00_TR = "P_POK00_TR";
    public static final String P_POK_01_TR = "P_POK01_TR";
    public static final String I_BALL_TR = "I_BALL_TR";

    public static final String ROAD = "R 1";
    public static final String CITY = "CI 1";
    public static final String CAVE = "CA 1";
    public static final int IMG_MINI0 = 786432;
    public static final int IMG_MINI1 = IMG_MINI0 + 262144;
    public static final int IMG_MINI2 = IMG_MINI1 + 262144;
    public static final int IMG_MINI3 = IMG_MINI2 + 262144;
    public static final int IMG_MINI4 = IMG_MINI3 + 262144;
    public static final int IMG_MINI5 = IMG_MINI4 + 262144;
    public static final int IMG_0 = 0;
    public static final int IMG_1 = 1;

    public static NaSt callGeneralHelpBeanBeginGet() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanBeginGet(),with());
    }

    public static String callGeneralHelpBeanClickAbility() {
        return callGeneralHelpBeanClickAbility(with());
    }

    public static String callGeneralHelpBeanClickAbility(NaSt _str) {
        return navigateData(new GeneralHelpBeanClickAbility(),_str);
    }

    public static String callGeneralHelpBeanClickAbilityId() {
        NaSt bean_ = with();
        callGeneralHelpBeanClickAbility(bean_);
        return getValAbilityId(bean_);
    }


    public static String callGeneralHelpBeanClickHm() {
        return callGeneralHelpBeanClickHm(with());
    }
    public static String callGeneralHelpBeanClickHm(NaSt _str) {
        return navigateData(new GeneralHelpBeanClickHm(),_str,0);
    }

    public static String callGeneralHelpBeanClickHmId() {
        NaSt bean_ = with();
        callGeneralHelpBeanClickHm(bean_);
        return getValMoveId(bean_);
    }

    public static String callGeneralHelpBeanClickItem() {
        return callGeneralHelpBeanClickItem(with());
    }

    public static String callGeneralHelpBeanClickItem(NaSt _str) {
        return navigateData(new GeneralHelpBeanClickItem(),_str);
    }

    public static String callGeneralHelpBeanClickItemId() {
        NaSt bean_ = with();
        callGeneralHelpBeanClickItem(bean_);
        return getValItemId(bean_);
    }

    public static String callGeneralHelpBeanClickMove() {
        return callGeneralHelpBeanClickMove(with());
    }

    public static String callGeneralHelpBeanClickMove(NaSt _str) {
        return navigateData(new GeneralHelpBeanClickMove(),_str,0);
    }

    public static String callGeneralHelpBeanClickMoveId() {
        NaSt bean_ = with();
        callGeneralHelpBeanClickMove(bean_);
        return getValMoveId(bean_);
    }

    public static String callGeneralHelpBeanClickName() {
        return callGeneralHelpBeanClickName(with());
    }

    public static String callGeneralHelpBeanClickName(NaSt _str) {
        return navigateData(new GeneralHelpBeanClickName(),_str);
    }

    public static String callGeneralHelpBeanClickNameId() {
        NaSt bean_ = with();
        callGeneralHelpBeanClickName(bean_);
        return getValPkId(bean_);
    }

    public static String callGeneralHelpBeanClickPokemon() {
        return callGeneralHelpBeanClickPokemon(with());
    }

    public static String callGeneralHelpBeanClickPokemon(NaSt _str) {
        return navigateData(new GeneralHelpBeanClickPokemon(),_str,0);
    }

    public static String callGeneralHelpBeanClickPokemonId() {
        NaSt bean_ = with();
        callGeneralHelpBeanClickPokemon(bean_);
        return getValPkId(bean_);
    }

    public static String callGeneralHelpBeanClickTm(int _tm) {
        return callGeneralHelpBeanClickTm(with(),_tm);
    }

    public static String callGeneralHelpBeanClickTm(NaSt _str, int _tm) {
        return navigateData(new GeneralHelpBeanClickTm(),_str,_tm);
    }

    public static String callGeneralHelpBeanClickTmId(int _tm) {
        NaSt bean_ = with();
        callGeneralHelpBeanClickTm(bean_,_tm);
        return getValMoveId(bean_);
    }
    public static NaSt callGeneralHelpBeanDefaultMoneyGet() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanDefaultMoneyGet(),with());
    }

    public static NaSt callGeneralHelpBeanFirstPokemonHasItem() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanFirstPokemonHasItem(),with());
    }

    public static NaSt callGeneralHelpBeanFirstPokemonHasNoItem() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanFirstPokemonHasItem(),without());
    }

    public static NaSt callGeneralHelpBeanGetAbility() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetAbility(),with());
    }

    public static NaSt callGeneralHelpBeanGetColorType() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetColorType(),with(),0);
    }

    public static NaSt callGeneralHelpBeanGetGender() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetGender(),with());
    }

    public static NaSt callGeneralHelpBeanGetImage() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetImage(),with());
    }

    public static NaSt callGeneralHelpBeanGetImageType() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetImageType(),with(),0);
    }

    public static NaSt callGeneralHelpBeanGetItem() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetItem(),with());
    }

    public static NaSt callGeneralHelpBeanGetLevel() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetLevel(),with());
    }

    public static NaSt callGeneralHelpBeanGetMapWidth() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetMapWidth(),with());
    }

    public static NaSt callGeneralHelpBeanGetMiniMapImage(int _index) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetMiniMapImage(),with(),_index);
    }

    public static NaSt callGeneralHelpBeanGetMove() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetMove(),with(),0);
    }

    public static NaSt callGeneralHelpBeanGetMovesAtLevel() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetMovesAtLevel(),with());
    }

    public static NaSt callGeneralHelpBeanGetName() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetName(),with());
    }

    public static NaSt callGeneralHelpBeanGetPlaceName(int _index) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetPlaceName(),with(),_index);
    }

    public static NaSt callGeneralHelpBeanGetTmPrice(int _move) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetTmPrice(),with(),_move);
    }

    public static NaSt callGeneralHelpBeanGetTrHm() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetTrHm(),with(),0);
    }

    public static NaSt callGeneralHelpBeanGetTrPokemon() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetTrPokemon(),with(),0);
    }

    public static NaSt callGeneralHelpBeanGetTrTm(int _tm) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetTrTm(),with(),_tm);
    }

    public static NaSt callGeneralHelpBeanGetTrType() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetTrType(),with(),0);
    }

    public static NaSt callGeneralHelpBeanHappinessMaxGet() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanHappinessMaxGet(),with());
    }

    public static NaSt callGeneralHelpBeanHmGet() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanHmGet(),with());
    }

//    public static Struct callGeneralHelpBeanIsFirstRow(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanIsFirstRow(),_str,_args);
//    }

    public static NaSt callGeneralHelpBeanMaxEvGet() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanMaxEvGet(),with());
    }

    public static NaSt callGeneralHelpBeanMaxIvGet() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanMaxIvGet(),with());
    }

    public static NaSt callGeneralHelpBeanMaxLevelGet() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanMaxLevelGet(),with());
    }

    public static NaSt callGeneralHelpBeanMaxPpGet() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanMaxPpGet(),with());
    }

    public static NaSt callGeneralHelpBeanMinLevelGet() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanMinLevelGet(),with());
    }

    public static NaSt callGeneralHelpBeanMiniMapGet() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanMiniMapGet(),with());
    }

    public static NaSt callGeneralHelpBeanNbMaxMovesGet() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanNbMaxMovesGet(),with());
    }

    public static NaSt callGeneralHelpBeanNbMaxStepsGet() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanNbMaxStepsGet(),with());
    }

    public static NaSt callGeneralHelpBeanNbMaxStepsSameEvoBaseGet() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanNbMaxStepsSameEvoBaseGet(),with());
    }

    public static NaSt callGeneralHelpBeanNbMaxTeamGet() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanNbMaxTeamGet(),with());
    }

    public static NaSt callGeneralHelpBeanNbNecStepsIncrHappinessGet() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanNbNecStepsIncrHappinessGet(),with());
    }

    public static NaSt callGeneralHelpBeanPokemonDefaultEggGroupGet() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanPokemonDefaultEggGroupGet(),with());
    }

    public static NaSt callGeneralHelpBeanTmGet() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanTmGet(),with());
    }

    public static NaSt callGeneralHelpBeanTypesGet() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanTypesGet(),with());
    }

    public static NaSt callGeneralHelpBeanUnlockedCityGet() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanUnlockedCityGet(),with());
    }
    protected static NaSt with() {
        return str(I_BALL);
    }
    protected static NaSt without() {
        return str(NULL_REF);
    }
    protected static NaSt str(String _it) {
        PkData pk_ = pkDataByFacade(db(_it));
        PokemonBeanStruct bean_ = pk_.beanGeneralHelpBean(EN);
        beforeDisplaying(bean_);
        return bean_;
    }
    protected static FacadeGame db(String _it) {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers(P_POK_00,pkOne());
        facade_.getData().completeMembers(P_POK_01,pkTwo());
        facade_.getData().completeMembers(M_POK_00,Instances.newDamagingMoveData());
        facade_.getData().completeMembers(M_POK_01,Instances.newDamagingMoveData());
        facade_.getData().completeMembers(M_POK_02,Instances.newDamagingMoveData());
        facade_.getData().completeMembers(M_POK_03,Instances.newDamagingMoveData());
        facade_.getData().completeMembers(I_BALL,Instances.newBall());
        facade_.getData().completeMembers(A_ABILITY,Instances.newAbilityData());
        facade_.getData().getHm().addEntry(1,M_POK_00);
        facade_.getData().getTm().addEntry(2,M_POK_02);
        facade_.getData().getTm().addEntry(3,M_POK_03);
        facade_.getData().getTmPrice().addEntry(2,LgInt.one());
        facade_.getData().getTypes().add(T_TYPE1);
        trsCore(facade_);
        facade_.getData().setMap(dm(_it));
        facade_.getData().getMiniMap().addEntry("0",instance(two(IMG_MINI0)));
        facade_.getData().getMiniMap().addEntry("1",instance(two(IMG_MINI1)));
        facade_.getData().getMiniMap().addEntry("2",instance(two(IMG_MINI2)));
        facade_.getData().getMiniMap().addEntry("3",instance(two(IMG_MINI3)));
        facade_.getData().getMiniMap().addEntry("4",instance(two(IMG_MINI4)));
        facade_.getData().getTypesImages().addEntry(T_TYPE1,instance(two(IMG_MINI5)));
        facade_.getData().getTypesColors().addEntry(T_TYPE1,"5;6;7");
        return facade_;
    }
    protected static void trsCore(FacadeGame _facade) {
        _facade.getData().getTranslatedAbilities().addEntry(EN,new StringMap<String>());
        _facade.getData().getTranslatedAbilities().getVal(EN).addEntry(A_ABILITY,A_ABILITY_TR);
//        _facade.getData().getTranslatedTypes().addEntry(EN,new StringMap<String>());
//        _facade.getData().getTranslatedTypes().getVal(EN).addEntry(T_TYPE1, T_TYPE1_TR);
//        _facade.getData().getTranslatedCategories().addEntry(EN,new StringMap<String>());
//        _facade.getData().getTranslatedCategories().getVal(EN).addEntry(C_CAT, C_CAT1_TR);
//        _facade.getData().getTranslatedCategories().getVal(EN).addEntry(AUTRE,C_CAT2_TR);
        _facade.getData().getTranslatedItems().addEntry(EN,new StringMap<String>());
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_BALL,I_BALL_TR);
        _facade.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_POK_00,M_POK_00_TR);
        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_POK_01,M_POK_01_TR);
        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_POK_02,M_POK_02_TR);
        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_POK_03,M_POK_03_TR);
        _facade.getData().getTranslatedPokemon().addEntry(EN,new StringMap<String>());
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_00, P_POK_00_TR);
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POK_01, P_POK_01_TR);
        _facade.getData().getTranslatedTypes().addEntry(EN,new StringMap<String>());
        _facade.getData().getTranslatedTypes().getVal(EN).addEntry(T_TYPE1,T_TYPE1_TR);
        _facade.getData().getTranslatedGenders().addEntry(EN,new IdMap<Gender,String>());
        _facade.getData().getTranslatedGenders().getVal(EN).addEntry(Gender.NO_GENDER,NO_G);
//        _facade.getData().getMiniPk().addEntry(P_POK_00,getImageByString("AAACAAAABAAA////////"));
//        _facade.getData().getMiniPk().addEntry(P_POK_01,getImageByString("AAACAAABBAAA////////"));
//        _facade.getData().getMiniPk().addEntry(P_POK_02,getImageByString("AAACAAACBAAA////////"));
//        _facade.getData().getMiniPk().addEntry(P_POK_03,getImageByString("AAACAAADBAAA////////"));
//        _facade.getData().getMiniPk().addEntry(P_POK_04,getImageByString("AAACAAAEBAAA////////"));
//        _facade.getData().getMiniPk().addEntry(P_POK_05,getImageByString("AAACAAAFBAAA////////"));
//        _facade.getData().getMiniPk().addEntry(P_POK_06,getImageByString("AAACAAAGBAAA////////"));
//        _facade.getData().getMiniPk().addEntry(P_POK_07,getImageByString("AAACAAAHBAAA////////"));
//        _facade.getData().getMiniPk().addEntry(P_POK_08,getImageByString("AAACAAAIBAAA////////"));
//        _facade.getData().getMiniPk().addEntry(P_POK_09,getImageByString("AAACAAAJBAAA////////"));
//        _facade.getData().getMiniPk().addEntry(P_POK_10,getImageByString("AAACAAAKBAAA////////"));
//        _facade.getData().getMiniPk().addEntry(P_POK_11,getImageByString("AAACAAALBAAA////////"));
//        _facade.getData().getMiniPk().addEntry(P_POK_12,getImageByString("AAACAAAMBAAA////////"));
//        _facade.getData().getMiniPk().addEntry(P_POK_13,getImageByString("AAACAAANBAAA////////"));
//        _facade.getData().getMiniPk().addEntry(P_POK_14,getImageByString("AAACAAAOBAAA////////"));
//        _facade.getData().getMiniPk().addEntry(P_POK_15,getImageByString("AAACAAAPBAAA////////"));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_00, instance(IMG_0));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_01,instance(IMG_1));
        _facade.getData().getMiniPk().addEntry(P_POK_00,instance(IMG_0));
        _facade.getData().getMiniPk().addEntry(P_POK_01,instance(IMG_1));
//        _facade.getData().getMiniItems().addEntry(I_BALL,getImageByString("AAAB////"));
//        _facade.getData().setImageTmHm(getImageByString("AAAB////"));
        _facade.getData().addConstNumTest(DataBase.DEF_MAX_ATT,new Rate(4));
        _facade.getData().addConstNumTest(DataBase.MAX_IV,new Rate(16));
        _facade.getData().addConstNumTest(DataBase.MAX_EV,new Rate(15));
        _facade.getData().addConstNumTest(DataBase.NIVEAU_PK_MAX,new Rate(64));
        _facade.getData().addConstNumTest(DataBase.NIVEAU_PK_ECLOSION,new Rate(1));
        _facade.getData().addConstNumTest(DataBase.MAX_BONHEUR,new Rate(128));
        _facade.getData().addConstNumTest(DataBase.DEF_PKEQ,new Rate(8));
        _facade.getData().addConstNumTest(DataBase.PP_MAX,new Rate(48));
        _facade.getData().addConstNumTest(DataBase.PAS_NECES_INCREMENT_BONHEUR,new Rate(2));
        _facade.getData().addConstNumTest(DataBase.MAX_STEPS_SAME_EVO_BASE,new Rate(3));
        _facade.getData().addConstNumTest(DataBase.MAX_STEPS,new Rate(6));
        _facade.getData().addConstNumTest(DataBase.ARGENT,new Rate(1024));
        _facade.getData().initValue(DataBase.DEFAULT_EGG_GROUP,"DEF_EGG");
    }
    protected static PokemonData pkOne() {
        return pk(M_POK_00, "");
    }

    protected static PokemonData pkTwo() {
        return pk(M_POK_01, "DEF_EGG");
    }

    protected static PokemonData pk(String _one, String _egg) {
        PokemonData pk_ = Instances.newPokemonData();
        pk_.getEggGroups().add(_egg);
        pk_.getLevMoves().add(new LevelMove( 1,_one));
        return pk_;
    }
    protected static DataMap dm(String _it) {
        DataMap map_ = Instances.newDataMap();
        Road r_ = Instances.newRoad();
        r_.setName(ROAD);
        map_.getPlaces().add(r_);
        City ci_ = Instances.newCity();
        ci_.setName(CITY);
        map_.getPlaces().add(ci_);
        Cave ca_ = Instances.newCave();
        ca_.setName(CAVE);
        map_.getPlaces().add(ca_);
        map_.getMiniMap().addEntry(new MiniMapCoords(0,0),tm("0", -1));
        map_.getMiniMap().addEntry(new MiniMapCoords(0,1),tm("1", 0));
        map_.getMiniMap().addEntry(new MiniMapCoords(1,0),tm("2", 1));
        map_.getMiniMap().addEntry(new MiniMapCoords(1,1),tm("3", 2));
        map_.setUnlockedCity("4");
        map_.setFirstPokemon(wp(_it));
        map_.setBegin(newCoords(0,0,0,0));
        map_.setSideLength(1);
        return map_;
    }

    private static TileMiniMap tm(String _file, int _pl) {
        TileMiniMap t_ = Instances.newTileMiniMap();
        t_.setFile(_file);
        t_.setPlace(_pl);
        return t_;
    }
    protected static WildPk wp(String _it) {
        WildPk pk_ = Instances.newWildPk();
        pk_.setName(P_POK_00);
        pk_.setLevel( 4);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(A_ABILITY);
        pk_.setItem(_it);
        return pk_;
    }
    protected static int[][] two(int _i) {
        return new int[][]{new int[]{6125015, 524288},new int[]{_i, 16777215}};
    }
}
