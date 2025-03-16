package aiki.beans.pokemon;

import aiki.beans.*;
import aiki.beans.pokemon.evolutions.*;
import aiki.fight.util.LevelMove;
import aiki.util.Coords;
import code.scripts.pages.aiki.MessagesPkBean;
import code.sml.util.TranslationsFile;
import code.util.CustList;
import code.util.StringMap;
import aiki.beans.facade.map.dto.*;
import aiki.map.util.*;
import code.maths.*;
import code.util.*;

public abstract class InitDbPkOne extends InitDbPk {

    public static long callLevelMoveGetLevel(LevelMove _str, int... _args) {
        return _str.getLevel();
    }

    public static String callLevelMoveGetMove(LevelMove _str, int... _args) {
        return _str.getMove();
    }

    public static CustList<TranslatedKey> callPokemonBeanAbilitiesGet() {
        return dispPkOne(0).getAbilities();
    }

    public static int[][] callPokemonBeanBackImageGet() {
        return dispPkOne(0).getBackImage();
    }

    public static long callPokemonBeanCatchingRateGet() {
        return dispPkOne(0).getCatchingRate();
    }

    public static String callPokemonBeanClickAbility() {
        return callPokemonBeanClickAbility(dispPkOne(0));
    }

    public static String callPokemonBeanClickAbility(PokemonBean _str) {
        return _str.clickAbility(0);
    }

    public static String callPokemonBeanClickAbilityId() {
        PokemonBean bean_ = dispPkOne(0);
        callPokemonBeanClickAbility(bean_);
        return getValAbilityId(bean_);
    }

    public static String callPokemonBeanClickBase(PokemonBean _str) {
        return _str.clickBase();
    }

    public static String callPokemonBeanClickBase() {
        return callPokemonBeanClickBase(dispPkOne(1));
    }

    public static String callPokemonBeanClickBaseId() {
        PokemonBean bean_ = dispPkOne(1);
        callPokemonBeanClickBase(bean_);
        return getValPkId(bean_);
    }

    public static String callPokemonBeanClickEggPk(int _row,int _args) {
        return callPokemonBeanClickEggPk(dispPkOne(_row),_args);
    }

    public static String callPokemonBeanClickEggPk(PokemonBean _str, int _ind) {
        return _str.clickEggPk(_ind);
    }

    public static String callPokemonBeanClickEggPkId(int _row,int _args) {
        PokemonBean bean_ = dispPkOne(_row);
        callPokemonBeanClickEggPk(bean_,_args);
        return getValPkId(bean_);
    }

    public static String callPokemonBeanClickHiddenMove() {
        return callPokemonBeanClickHiddenMove(dispPkOne(0));
    }

    public static String callPokemonBeanClickHiddenMove(PokemonBean _str) {
        return _str.clickHiddenMove(0);
    }

    public static String callPokemonBeanClickHiddenMoveId() {
        PokemonBean bean_ = dispPkOne(0);
        callPokemonBeanClickHiddenMove(bean_);
        return getValMoveId(bean_);
    }

    public static String callPokemonBeanClickLevel() {
        return callPokemonBeanClickLevel(dispPkOne(10));
    }

    public static String callPokemonBeanClickLevel(PokemonBean _str) {
        PokemonBean pk_ = new PokemonBean();
        pk_.setBuilder(_str.getBuilder());
//        navigateData(new PokemonBeanClickLevel(),new PokemonBeanStruct(pk_),2,1);
        return navigateData(new MapBeanClickLevelBeanAction(_str,2,1),_str);
    }

    public static Coords callPokemonBeanClickLevelId() {
        PokemonBean bean_ = dispPkOne(10);
        callPokemonBeanClickLevel(bean_);
        assertTrue(containsPlaceLevelId(bean_));
        return getValPlaceLevelId(bean_);
    }

    public static String callPokemonBeanClickLevelZero() {
        return callPokemonBeanClickLevelZero(dispPkOne(1));
    }

    public static String callPokemonBeanClickLevelZero(PokemonBean _str) {
        return _str.clickLevel(0,0);
    }

    public static Coords callPokemonBeanClickLevelZeroId() {
        PokemonBean bean_ = dispPkOne(1);
        callPokemonBeanClickLevelZero(bean_);
        assertTrue(containsPlaceLevelId(bean_));
        return getValPlaceLevelId(bean_);
    }
    public static String callPokemonBeanClickMove(int _args) {
        return callPokemonBeanClickMove(dispPkOne(0),_args);
    }

    public static String callPokemonBeanClickMove(PokemonBean _str, int _args) {
        return _str.clickMove(_args);
    }

    public static String callPokemonBeanClickMoveId(int _args) {
        PokemonBean bean_ = dispPkOne(0);
        callPokemonBeanClickMove(bean_,_args);
        return getValMoveId(bean_);
    }

    public static String callPokemonBeanClickMoveTutors() {
        return callPokemonBeanClickMoveTutors(dispPkOne(0));
    }

    public static String callPokemonBeanClickMoveTutors(PokemonBean _str) {
        return _str.clickMoveTutors(0);
    }

    public static String callPokemonBeanClickMoveTutorsId() {
        PokemonBean bean_ = dispPkOne(0);
        callPokemonBeanClickMoveTutors(bean_);
        return getValMoveId(bean_);
    }

    public static String callPokemonBeanClickPokedex() {
        PokemonBean bean_ = dispPkOne(0);
        String value_ = navigateData(new PokemonBeanClickPokedex(bean_),bean_);
        assertFalse(containsPlaceLevelId(bean_));
        return value_;
    }

    public static String callPokemonBeanClickTechnicalMove() {
        return callPokemonBeanClickTechnicalMove(dispPkOne(0));
    }

    public static String callPokemonBeanClickTechnicalMove(PokemonBean _str) {
        return _str.clickTechnicalMove(0);
    }

    public static String callPokemonBeanClickTechnicalMoveId() {
        PokemonBean bean_ = dispPkOne(0);
        callPokemonBeanClickTechnicalMove(bean_);
        return getValMoveId(bean_);
    }

    public static String callPokemonBeanDisplayNameGet(int _args) {
        return dispPkOne(_args).getDisplayName();
    }

    public static CustList<TranslatedKey> callPokemonBeanEggGroupsPkGet(int _line) {
        return dispPkOne(_line).getEggGroupsPk();
    }

    public static String callPokemonBeanEvoBaseGet() {
        return dispPkOne(1).getEvoBase();
    }

    public static CustList<TranslatedKey> callPokemonBeanEvolutionsGet() {
        return dispPkOne(0).getEvolutions();
    }

    public static String callPokemonBeanExpEvoGet() {
        return dispPkOne(0).getExpEvo();
    }

    public static long callPokemonBeanExpRateGet() {
        return dispPkOne(0).getExpRate();
    }

    public static int[][] callPokemonBeanFrontImageGet() {
        return dispPkOne(0).getFrontImage();
    }

    public static long callPokemonBeanGetBase() {
        return dispPkOne(0).getBase(0);
    }

    public static String callPokemonBeanGetEggPk(int _line, int _ind) {
        return dispPkOne(_line).getEggPk(_ind);
    }

    public static long callPokemonBeanGetEv() {
        return dispPkOne(0).getEv(0);
    }

    public static long callPokemonBeanGetMapWidth() {
        return dispPkOne(0).getMapWidth();
    }

    public static int[][] callPokemonBeanGetMiniMapImage(int _tile) {
        return dispPkOne(0).getMiniMapImage(_tile);
    }

    public static String callPokemonBeanGetMoveTutor() {
        return dispPkOne(0).getMoveTutor(0);
    }

//    public static NaSt callPokemonBeanGetPage(PokemonBean _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new PokemonBeanGetPage(),_str,_args);
//    }

    public static String callPokemonBeanGetPlaceName(int _tile) {
        return dispPkOne(0).getPlaceName(_tile);
    }

    public static String callPokemonBeanGetTrAbility() {
        return dispPkOne(0).getTrAbility(0);
    }

    public static LgInt callPokemonBeanHatchingStepsGet() {
        return dispPkOne(0).getHatchingSteps();
    }

    public static Rate callPokemonBeanHeightGet() {
        return dispPkOne(0).getHeight();
    }

    public static AbsMap<Integer,TranslatedKey> callPokemonBeanHiddenMovesGet() {
        return dispPkOne(0).getHiddenMoves();
    }

    public static AbsMap<MiniMapCoords,int[][]> callPokemonBeanImagesGet() {
        return dispPkOne(0).getImages();
    }

    public static boolean callPokemonBeanIsAppearing(int _pk,int _pl, int _lev) {
        return dispPkOne(_pk).isAppearing(_pl,_lev);
    }

    public static boolean callPokemonBeanIsAppearingAnyWhere(int _pk) {
        return dispPkOne(_pk).isAppearingAnyWhere();
    }

    public static boolean callPokemonBeanIsAppearingPlace(int _pk,int _pl) {
        return dispPkOne(_pk).isAppearingPlace(_pl);
    }

    public static boolean callPokemonBeanIsAppearingZero(int _pk,int _pl) {
        return dispPkOne(_pk).isAppearing(_pl,0);
    }
//
//    public static Struct callPokemonBeanIsFirstRow(int _row) {
//        return BeanPokemonCommonTs.callLongs(new PokemonBeanIsFirstRow(),dispPkOne(0),_row);
//    }

    public static boolean callPokemonBeanIsMultiLayer(int _pl) {
        return dispPkOne(0).isMultiLayer(_pl);
    }

    public static int callPokemonBeanLayers(int _pl) {
        return dispPkOne(0).layers(_pl).size();
    }

    public static CustList<LevelMoveTranslatedKey> callPokemonBeanLevMovesGet() {
        return dispPkOne(0).getLevMoves();
    }

    public static AbsMap<String,String> callPokemonBeanMapVarsGet() {
        return dispPkOne(0).getMapVars();
    }

    public static CustList<TranslatedKey> callPokemonBeanMoveTutorsGet() {
        return dispPkOne(0).getMoveTutors();
    }

//    public static NaSt callPokemonBeanNameGet(PokemonBean _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new PokemonBeanNameGet(),_str,_args);
//    }

    public static CustList<PlaceIndex> callPokemonBeanPlacesGet() {
        return dispPkOne(0).getPlaces();
    }

    public static CustList<TranslatedKey> callPokemonBeanPossibleGendersGet() {
        return dispPkOne(0).getPossibleGenders();
    }

    public static String callPokemonBeanRoundHeight() {
        return dispPkOne(0).roundHeight();
    }

    public static String callPokemonBeanRoundWeight() {
        return dispPkOne(0).roundWeight();
    }

    public static CustList<StringStatBaseEv> callPokemonBeanStatisticsGet() {
        return dispPkOne(0).getStatistics();
    }

    public static AbsMap<Integer,TranslatedKey> callPokemonBeanTechnicalMovesGet() {
        return dispPkOne(0).getTechnicalMoves();
    }

    public static CustList<TranslatedKey> callPokemonBeanTypesGet() {
        return dispPkOne(0).getTypes();
    }

    public static Rate callPokemonBeanWeightGet() {
        return dispPkOne(0).getWeight();
    }


    public static long callEvolutionHappinessBeanMinGet() {
        return ((EvolutionHappinessBean) dispPkOne(12,0)).getMin();
    }

    public static String callEvolutionItemBeanClickItem() {
        return callEvolutionItemBeanClickItem(dispPkOne(9,0));
    }

    public static String callEvolutionItemBeanClickItem(EvolutionBean _str) {
        return ((EvolutionItemBean) _str).clickItem();
    }

    public static String callEvolutionItemBeanClickItemId() {
        EvolutionBean bean_ = dispPkOne(9, 0);
        callEvolutionItemBeanClickItem(bean_);
        return getValItemId(bean_);
    }

    public static String callEvolutionItemBeanItemGet() {
        return ((EvolutionItemBean) dispPkOne(9,0)).getItem().getTranslation();
    }

    public static long callEvolutionLevelBeanLevelGet() {
        return ((EvolutionLevelBean) dispPkOne(0,0)).getLevel();
    }

    public static String callEvolutionLevelGenderBeanGenderGet() {
        return ((EvolutionLevelGenderBean) dispPkOne(1,0)).getGender();
    }

    public static String callEvolutionMoveBeanClickMove() {
        return callEvolutionMoveBeanClickMove(dispPkOne(5,0));
    }

    public static String callEvolutionMoveBeanClickMove(EvolutionBean _str) {
        return ((EvolutionMoveBean) _str).clickMove();
    }

    public static String callEvolutionMoveBeanClickMoveId() {
        EvolutionBean bean_ = dispPkOne(5, 0);
        callEvolutionMoveBeanClickMove(bean_);
        return getValMoveId(bean_);
    }

    public static String callEvolutionMoveBeanMoveGet() {
        return ((EvolutionMoveBean) dispPkOne(5,0)).getMove().getTranslation();
    }

    public static String callEvolutionMoveTypeBeanTypeGet() {
        return ((EvolutionMoveTypeBean) dispPkOne(5,1)).getType();
    }

    public static String callEvolutionStoneBeanClickStone() {
        return callEvolutionStoneBeanClickStone(dispPkOne(3,0));
    }

    public static String callEvolutionStoneBeanClickStone(EvolutionBean _str) {
        return ((EvolutionStoneBean) _str).clickStone();
    }

    public static String callEvolutionStoneBeanClickStoneId() {
        EvolutionBean bean_ = dispPkOne(3, 0);
        callEvolutionStoneBeanClickStone(bean_);
        return getValItemId(bean_);
    }

    public static String callEvolutionStoneBeanStoneGet() {
        return ((EvolutionStoneBean) dispPkOne(3,0)).getStone().getTranslation();
    }

    public static String callEvolutionStoneGenderBeanGenderGet() {
        return ((EvolutionStoneGenderBean) dispPkOne(4,0)).getGender();
    }

    public static String callEvolutionTeamBeanClickTeam() {
        return callEvolutionTeamBeanClickTeam(dispPkOne(8,0));
    }

    public static String callEvolutionTeamBeanClickTeam(EvolutionBean _str) {
        return ((EvolutionTeamBean) _str).clickTeam();
    }

    public static String callEvolutionTeamBeanClickTeamId() {
        EvolutionBean bean_ = dispPkOne(8, 0);
        callEvolutionTeamBeanClickTeam(bean_);
        return getValPkId(bean_);
    }

    public static String callEvolutionTeamBeanOtherGet() {
        return ((EvolutionTeamBean) dispPkOne(8,0)).getOther().getTranslation();
    }

    public static String callEvolutionBeanClickEvo() {
        return callEvolutionBeanClickEvo(dispPkOne(12,0));
    }

    public static String callEvolutionBeanClickEvo(EvolutionBean _str) {
        return _str.clickEvo();
    }

    public static String callEvolutionBeanClickEvoId() {
        EvolutionBean bean_ = dispPkOne(12, 0);
        callEvolutionBeanClickEvo(bean_);
        return getValPkId(bean_);
    }

    public static String callEvolutionBeanDisplayBaseGet() {
        return dispPkOne(1,0).getDisplayBase();
    }

    public static String callEvolutionBeanDisplayNameGet() {
        return dispPkOne(12,0).getDisplayName();
    }

//    public static NaSt callEvolutionBeanIndexGet(PokemonBean _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new EvolutionBeanIndexGet(),_str,_args);
//    }
//    public static NaSt callEvolutionBeanBaseSet(PokemonBean _str, String _args) {
//        return BeanPokemonCommonTs.callString(new EvolutionBeanBaseSet(),_str,_args);
//    }
//
//    public static NaSt callEvolutionBeanNameSet(PokemonBean _str, String _args) {
//        return BeanPokemonCommonTs.callString(new EvolutionBeanNameSet(),_str,_args);
//    }
//
//    public static NaSt callEvolutionBeanIndexSet(PokemonBean _str, int _args) {
//        return BeanPokemonCommonTs.callInt(new EvolutionBeanIndexSet(),_str,_args);
//    }

    public static LevelMove eltLvMv(CustList<LevelMoveTranslatedKey> _arr, int _index) {
        return _arr.get(_index).lm();
    }
    public static String eltStringStatBaseEv(CustList<StringStatBaseEv> _ls, int _i){
        return _ls.get(_i).getName().getTranslation();
    }
//
//    public static NaSt eltImg(NaSt _arr, int _index) {
//        return ((NatArrayStruct)_arr).get(_index);
//    }
//    public static NaSt secondImg(NaSt _arr) {
//        return ((PairStruct)_arr).getSecond();
//    }
    protected static PokemonBean dispPkOne(int _index) {
        PkData pk_ = pkDataByFacade(feedDb());
        StringMap<BeanRenderWithAppName> all_ = beanToPkOne(pk_);
        return transitToAllPks(pk_, all_, _index);
    }
    protected static EvolutionBean dispPkOne(int _index, int _evo) {
        PkData pk_ = pkDataByFacade(feedDb());
        StringMap<BeanRenderWithAppName> all_ = beanToPkOne(pk_);
//        StringMap<String> mapping_ = mappingToPkOne();
        PokemonBean pkbean_ = transitToAllPks(pk_, all_, _index);
//        NaSt evobean_ = byStr(all_, mapping_, callPokemonBeanGetPage(pkbean_, _evo));
//        callEvolutionBeanBaseSet(evobean_,toStr(callPokemonBeanNameGet(pkbean_)));
//        callEvolutionBeanIndexSet(evobean_,_evo);
//        callEvolutionBeanNameSet(evobean_,toStr(elt(callPokemonBeanEvolutionsGet(pkbean_),_evo)));
//        setFormsBy(pk_,evobean_,pkbean_);
//        beforeDisplaying(evobean_);
        return pkbean_.getBeans().get(_evo);
    }
    public static StringMap<BeanRenderWithAppName> beanToPkOne(PkData _pk) {
        StringMap<BeanRenderWithAppName> map_ = beanToPk(_pk);
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.PK_DATA,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.PK_DATA,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_HAPPINESS,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_ITEM,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_LEVEL,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_LEVEL_GENDER,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_MOVE,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_STONE,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_STONE_GENDER,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_TEAM,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_TYPE,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_HAPPINESS,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_ITEM,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_LEVEL,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_LEVEL_GENDER,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_MOVE,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_STONE,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_STONE_GENDER,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_TEAM,new TranslationsFile());
        map_.getValue(0).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_TYPE,new TranslationsFile());
        PokemonBean pkBean_ = new PokemonBean();
        pkBean_.setBuilder(map_.getValue(0).getBuilder());
        map_.getValue(0).getBuilder().getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_POKEMON_DATA_HTML,pkBean_);
        initBean(pkBean_,EN,_pk.getDataBase());
        map_.addEntry(BEAN_PK, pkBean_);
//        map_.addEntry(AikiBeansPokemonStd.BEAN_EVO_HAPPY,_pk.beanEvolutionHappinessBean(EN));
//        map_.addEntry(AikiBeansPokemonStd.BEAN_EVO_ITEM,_pk.beanEvolutionItemBean(EN));
//        map_.addEntry(AikiBeansPokemonStd.BEAN_EVO_LEVEL,_pk.beanEvolutionLevelBean(EN));
//        map_.addEntry(AikiBeansPokemonStd.BEAN_EVO_LEVELGENDER,_pk.beanEvolutionLevelGenderBean(EN));
//        map_.addEntry(AikiBeansPokemonStd.BEAN_EVO_MOVE,_pk.beanEvolutionMoveBean(EN));
//        map_.addEntry(AikiBeansPokemonStd.BEAN_EVO_STONE,_pk.beanEvolutionStoneBean(EN));
//        map_.addEntry(AikiBeansPokemonStd.BEAN_EVO_STONEGENDER,_pk.beanEvolutionStoneGenderBean(EN));
//        map_.addEntry(AikiBeansPokemonStd.BEAN_EVO_TEAM,_pk.beanEvolutionTeamBean(EN));
//        map_.addEntry(AikiBeansPokemonStd.BEAN_EVO_TYPE,_pk.beanEvolutionMoveTypeBean(EN));
        return map_;
    }
//    public static StringMap<String> mappingToPkOne() {
//        StringMap<String> map_ = mappingToPk();
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_DATA_HTML,AikiBeansPokemonStd.BEAN_PK);
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOHAPPY_HTML,AikiBeansPokemonStd.BEAN_EVO_HAPPY);
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOITEM_HTML,AikiBeansPokemonStd.BEAN_EVO_ITEM);
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOLEVEL_HTML,AikiBeansPokemonStd.BEAN_EVO_LEVEL);
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOLEVELGENDER_HTML,AikiBeansPokemonStd.BEAN_EVO_LEVELGENDER);
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOMOVE_HTML,AikiBeansPokemonStd.BEAN_EVO_MOVE);
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOSTONE_HTML,AikiBeansPokemonStd.BEAN_EVO_STONE);
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOSTONEGENDER_HTML,AikiBeansPokemonStd.BEAN_EVO_STONEGENDER);
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOTEAM_HTML,AikiBeansPokemonStd.BEAN_EVO_TEAM);
//        map_.addEntry(PkScriptPages.REN_ADD_WEB_HTML_POKEMON_EVOLUTIONS_EVOTYPE_HTML,AikiBeansPokemonStd.BEAN_EVO_TYPE);
//        return map_;
//    }
}
