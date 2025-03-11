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
import code.scripts.pages.aiki.*;
import code.sml.util.*;
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

    public static String callGeneralHelpBeanBeginGet() {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getBegin();
    }

    public static String callGeneralHelpBeanClickAbility() {
        return callGeneralHelpBeanClickAbility(with());
    }

    public static String callGeneralHelpBeanClickAbility(NaSt _str) {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickAbility();
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
        return ( (GeneralHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickHm(0);
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
        return ( (GeneralHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickItem();
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
        return ( (GeneralHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickMoveFirstPk(0);
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
        return ( (GeneralHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickName();
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
        return ( (GeneralHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickPokemon(0);
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
        return ( (GeneralHelpBean) ((PokemonBeanStruct)_str).getInstance()).clickTm(_tm);
    }

    public static String callGeneralHelpBeanClickTmId(int _tm) {
        NaSt bean_ = with();
        callGeneralHelpBeanClickTm(bean_,_tm);
        return getValMoveId(bean_);
    }
    public static Rate callGeneralHelpBeanDefaultMoneyGet() {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getDefaultMoney();
    }

    public static boolean callGeneralHelpBeanFirstPokemonHasItem() {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).firstPokemonHasItem();
    }

    public static boolean callGeneralHelpBeanFirstPokemonHasNoItem() {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)without()).getInstance()).firstPokemonHasItem();
    }

    public static String callGeneralHelpBeanGetAbility() {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getAbility();
    }

    public static int[][] callGeneralHelpBeanGetColorType() {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getColorType(0);
    }

    public static String callGeneralHelpBeanGetGender() {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getGender();
    }

    public static int[][] callGeneralHelpBeanGetImage() {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getImage();
    }

    public static int[][] callGeneralHelpBeanGetImageType() {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getImageType(0);
    }

    public static String callGeneralHelpBeanGetItem() {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getItem();
    }

    public static long callGeneralHelpBeanGetLevel() {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getLevel();
    }

    public static long callGeneralHelpBeanGetMapWidthLight() {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)withLight()).getInstance()).getMapWidth();
    }

    public static long callGeneralHelpBeanGetMapWidth() {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getMapWidth();
    }

    public static int[][] callGeneralHelpBeanGetMiniMapImage(int _index) {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getMiniMapImage(_index);
    }

    public static String callGeneralHelpBeanGetMove() {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getMoveFirstPk(0);
    }

    public static NaSt callGeneralHelpBeanGetMovesAtLevel() {
        return PokemonStandards.getKeys(( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getMovesAtLevelFirstPk());
    }

    public static String callGeneralHelpBeanGetName() {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getName();
    }

    public static String callGeneralHelpBeanGetPlaceName(int _index) {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getPlaceName(_index);
    }

    public static String callGeneralHelpBeanGetTmPrice(int _move) {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getTmPrice(_move);
    }

    public static String callGeneralHelpBeanGetTrHm() {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getTrHm(0);
    }

    public static String callGeneralHelpBeanGetTrPokemon() {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getTrPokemon(0);
    }

    public static String callGeneralHelpBeanGetTrTm(int _tm) {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getTrTm(_tm);
    }

    public static String callGeneralHelpBeanGetTrType() {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getTrType(0);
    }

    public static long callGeneralHelpBeanHappinessMaxGet() {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getHappinessMax();
    }

    public static NaSt callGeneralHelpBeanHmGet() {
        return PokemonStandards.getKeys(( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getHm());
    }

//    public static Struct callGeneralHelpBeanIsFirstRow(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanIsFirstRow(),_str,_args);
//    }

    public static long callGeneralHelpBeanMaxEvGet() {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getMaxEv();
    }

    public static long callGeneralHelpBeanMaxIvGet() {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getMaxIv();
    }

    public static long callGeneralHelpBeanMaxLevelGet() {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getMaxLevel();
    }

    public static long callGeneralHelpBeanMaxPpGet() {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getMaxPp();
    }

    public static long callGeneralHelpBeanMinLevelGet() {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getMinLevel();
    }

    public static NaSt callGeneralHelpBeanMiniMapGet() {
        return PokemonStandards.getWcStr(( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getMiniMap());
    }

    public static long callGeneralHelpBeanNbMaxMovesGet() {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getNbMaxMoves();
    }

    public static long callGeneralHelpBeanNbMaxStepsGet() {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getNbMaxSteps();
    }

    public static long callGeneralHelpBeanNbMaxStepsSameEvoBaseGet() {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getNbMaxStepsSameEvoBase();
    }

    public static long callGeneralHelpBeanNbMaxTeamGet() {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getNbMaxTeam();
    }

    public static long callGeneralHelpBeanNbNecStepsIncrHappinessGet() {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getNbNecStepsIncrHappiness();
    }

    public static NaSt callGeneralHelpBeanPokemonDefaultEggGroupGet() {
        return PokemonStandards.getKeys(( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getPokemonDefaultEggGroup());
    }

    public static NaSt callGeneralHelpBeanTmGet() {
        return PokemonStandards.getStrStrOnly(( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getTm());
    }

    public static NaSt callGeneralHelpBeanTypesGet() {
        return PokemonStandards.getKeys(( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getTypes());
    }

    public static int[][] callGeneralHelpBeanUnlockedCityGet() {
        return ( (GeneralHelpBean) ((PokemonBeanStruct)with()).getInstance()).getUnlockedCity();
    }
    protected static NaSt with() {
        return str(I_BALL);
    }
    protected static NaSt withLight() {
        return strLight(I_BALL);
    }
    protected static NaSt without() {
        return str(NULL_REF);
    }
    protected static NaSt str(String _it) {
        return bean(db(_it));
    }
    protected static NaSt strLight(String _it) {
        return bean(dbLight(_it));
    }

    private static PokemonBeanStruct bean(FacadeGame _db) {
        PkData pk_ = pkDataByFacade(_db);
        GeneralHelpBean g_ = new GeneralHelpBean();
        g_.setBuilder(builder(_db));
        g_.getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.GENERAL,new TranslationsFile());
        g_.getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.GENERAL,new TranslationsFile());
        g_.getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.NPC,new TranslationsFile());
        g_.getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.NPC,new TranslationsFile());
        pk_.bean(g_, EN);
        beforeDisplaying(g_);
        return new PokemonBeanStruct(g_);
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
    protected static FacadeGame dbLight(String _it) {
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
        facade_.getData().setMap(dmLight(_it));
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
    protected static DataMap dmLight(String _it) {
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
