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
import code.expressionlanguage.structs.Struct;
import code.images.*;
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
    public static Struct callGeneralHelpBeanBeginGet() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanBeginGet(),with());
    }

    public static Struct callGeneralHelpBeanClickAbility(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanClickAbility(),_str,_args);
    }

    public static Struct callGeneralHelpBeanClickHm(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanClickHm(),_str,_args);
    }

    public static Struct callGeneralHelpBeanClickItem(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanClickItem(),_str,_args);
    }

    public static Struct callGeneralHelpBeanClickMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanClickMove(),_str,_args);
    }

    public static Struct callGeneralHelpBeanClickName(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanClickName(),_str,_args);
    }

    public static Struct callGeneralHelpBeanClickPokemon(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanClickPokemon(),_str,_args);
    }

    public static Struct callGeneralHelpBeanClickTm(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanClickTm(),_str,_args);
    }

    public static Struct callGeneralHelpBeanDefaultMoneyGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanDefaultMoneyGet(),_str,_args);
    }

    public static Struct callGeneralHelpBeanFirstPokemonHasItem() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanFirstPokemonHasItem(),with());
    }

    public static Struct callGeneralHelpBeanFirstPokemonHasNoItem() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanFirstPokemonHasItem(),without());
    }

    public static Struct callGeneralHelpBeanGetAbility(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetAbility(),_str,_args);
    }

    public static Struct callGeneralHelpBeanGetColorType(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetColorType(),_str,_args);
    }

    public static Struct callGeneralHelpBeanGetGender(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetGender(),_str,_args);
    }

    public static Struct callGeneralHelpBeanGetImage(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetImage(),_str,_args);
    }

    public static Struct callGeneralHelpBeanGetImageType(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetImageType(),_str,_args);
    }

    public static Struct callGeneralHelpBeanGetItem(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetItem(),_str,_args);
    }

    public static Struct callGeneralHelpBeanGetLevel(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetLevel(),_str,_args);
    }

    public static Struct callGeneralHelpBeanGetMapWidth() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetMapWidth(),with());
    }

    public static Struct callGeneralHelpBeanGetMiniMapImage(int _index) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetMiniMapImage(),with(),_index);
    }

    public static Struct callGeneralHelpBeanGetMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetMove(),_str,_args);
    }

    public static Struct callGeneralHelpBeanGetMovesAtLevel(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetMovesAtLevel(),_str,_args);
    }

    public static Struct callGeneralHelpBeanGetName(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetName(),_str,_args);
    }

    public static Struct callGeneralHelpBeanGetPlaceName(int _index) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetPlaceName(),with(),_index);
    }

    public static Struct callGeneralHelpBeanGetTmPrice(int _move) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetTmPrice(),with(),_move);
    }

    public static Struct callGeneralHelpBeanGetTrHm(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetTrHm(),_str,_args);
    }

    public static Struct callGeneralHelpBeanGetTrPokemon(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetTrPokemon(),_str,_args);
    }

    public static Struct callGeneralHelpBeanGetTrTm(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetTrTm(),_str,_args);
    }

    public static Struct callGeneralHelpBeanGetTrType(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanGetTrType(),_str,_args);
    }

    public static Struct callGeneralHelpBeanHappinessMaxGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanHappinessMaxGet(),_str,_args);
    }

    public static Struct callGeneralHelpBeanHmGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanHmGet(),_str,_args);
    }

//    public static Struct callGeneralHelpBeanIsFirstRow(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanIsFirstRow(),_str,_args);
//    }

    public static Struct callGeneralHelpBeanMaxEvGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanMaxEvGet(),_str,_args);
    }

    public static Struct callGeneralHelpBeanMaxIvGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanMaxIvGet(),_str,_args);
    }

    public static Struct callGeneralHelpBeanMaxLevelGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanMaxLevelGet(),_str,_args);
    }

    public static Struct callGeneralHelpBeanMaxPpGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanMaxPpGet(),_str,_args);
    }

    public static Struct callGeneralHelpBeanMinLevelGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanMinLevelGet(),_str,_args);
    }

    public static Struct callGeneralHelpBeanMiniMapGet() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanMiniMapGet(),with());
    }

    public static Struct callGeneralHelpBeanNbMaxMovesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanNbMaxMovesGet(),_str,_args);
    }

    public static Struct callGeneralHelpBeanNbMaxStepsGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanNbMaxStepsGet(),_str,_args);
    }

    public static Struct callGeneralHelpBeanNbMaxStepsSameEvoBaseGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanNbMaxStepsSameEvoBaseGet(),_str,_args);
    }

    public static Struct callGeneralHelpBeanNbMaxTeamGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanNbMaxTeamGet(),_str,_args);
    }

    public static Struct callGeneralHelpBeanNbNecStepsIncrHappinessGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanNbNecStepsIncrHappinessGet(),_str,_args);
    }

    public static Struct callGeneralHelpBeanPokemonDefaultEggGroupGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanPokemonDefaultEggGroupGet(),_str,_args);
    }

    public static Struct callGeneralHelpBeanTmGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanTmGet(),_str,_args);
    }

    public static Struct callGeneralHelpBeanTypesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanTypesGet(),_str,_args);
    }

    public static Struct callGeneralHelpBeanUnlockedCityGet() {
        return BeanPokemonCommonTs.callLongs(new GeneralHelpBeanUnlockedCityGet(),with());
    }
    protected static Struct with() {
        return str(I_BALL);
    }
    protected static Struct without() {
        return str(NULL_REF);
    }
    protected static Struct str(String _it) {
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
        facade_.getData().getHm().addEntry((short)1,M_POK_00);
        facade_.getData().getTm().addEntry((short)2,M_POK_02);
        facade_.getData().getTm().addEntry((short)3,M_POK_03);
        facade_.getData().getTmPrice().addEntry((short)2,LgInt.one());
        facade_.getData().getTypes().add(T_TYPE1);
        trsCore(facade_);
        facade_.getData().setMap(dm(_it));
        facade_.getData().getMiniMap().addEntry("0",BaseSixtyFourUtil.getImageByString("AAACXXXXCAAADAAA////"));
        facade_.getData().getMiniMap().addEntry("1",BaseSixtyFourUtil.getImageByString("AAACXXXXCAAAEAAA////"));
        facade_.getData().getMiniMap().addEntry("2",BaseSixtyFourUtil.getImageByString("AAACXXXXCAAAFAAA////"));
        facade_.getData().getMiniMap().addEntry("3",BaseSixtyFourUtil.getImageByString("AAACXXXXCAAAGAAA////"));
        facade_.getData().getMiniMap().addEntry("4",BaseSixtyFourUtil.getImageByString("AAACXXXXCAAAHAAA////"));
        facade_.getData().getTypesImages().addEntry(T_TYPE1,BaseSixtyFourUtil.getImageByString("AAACXXXXCAAAIAAA////"));
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
        _facade.getData().getMaxiPkFront().addEntry(P_POK_00, BaseSixtyFourUtil.getImageByString("AAABAAAA"));
        _facade.getData().getMaxiPkFront().addEntry(P_POK_01,BaseSixtyFourUtil.getImageByString("AAABAAAB"));
        _facade.getData().getMiniPk().addEntry(P_POK_00,BaseSixtyFourUtil.getImageByString("AAABAAAA"));
        _facade.getData().getMiniPk().addEntry(P_POK_01,BaseSixtyFourUtil.getImageByString("AAABAAAB"));
//        _facade.getData().getMiniItems().addEntry(I_BALL,BaseSixtyFourUtil.getImageByString("AAAB////"));
//        _facade.getData().setImageTmHm(BaseSixtyFourUtil.getImageByString("AAAB////"));
        _facade.getData().addConstNumTest(DataBase.DEF_MAX_ATT,new Rate(4));
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
        pk_.getLevMoves().add(new LevelMove((short) 1,_one));
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
        map_.getMiniMap().addEntry(new MiniMapCoords((short)0,(short)0),tm("0", -1));
        map_.getMiniMap().addEntry(new MiniMapCoords((short)0,(short)1),tm("1", 0));
        map_.getMiniMap().addEntry(new MiniMapCoords((short)1,(short)0),tm("2", 1));
        map_.getMiniMap().addEntry(new MiniMapCoords((short)1,(short)1),tm("3", 2));
        map_.setUnlockedCity("4");
        map_.setFirstPokemon(wp(_it));
        map_.setBegin(newCoords(0,0,0,0));
        return map_;
    }

    private static TileMiniMap tm(String _file, int _pl) {
        TileMiniMap t_ = Instances.newTileMiniMap();
        t_.setFile(_file);
        t_.setPlace((short) _pl);
        return t_;
    }
    protected static WildPk wp(String _it) {
        WildPk pk_ = Instances.newWildPk();
        pk_.setName(P_POK_00);
        pk_.setLevel((short) 4);
        pk_.setGender(Gender.NO_GENDER);
        pk_.setAbility(A_ABILITY);
        pk_.setItem(_it);
        return pk_;
    }
}
