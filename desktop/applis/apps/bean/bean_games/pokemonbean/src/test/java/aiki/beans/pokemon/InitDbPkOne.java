package aiki.beans.pokemon;

import aiki.beans.*;
import aiki.beans.pokemon.evolutions.*;
import aiki.util.Coords;
import code.bean.nat.*;
import code.scripts.pages.aiki.MessagesPkBean;
import code.sml.util.TranslationsFile;
import code.util.StringMap;

public abstract class InitDbPkOne extends InitDbPk {

    public static NaSt callLevelMoveGetLevel(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new LevelMoveGetLevel(),_str,_args);
    }

    public static NaSt callLevelMoveGetMove(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new LevelMoveGetMove(),_str,_args);
    }

    public static NaSt callPokemonBeanAbilitiesGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanAbilitiesGet(),dispPkOne(0));
    }

    public static NaSt callPokemonBeanBackImageGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanBackImageGet(),dispPkOne(0));
    }

    public static NaSt callPokemonBeanCatchingRateGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanCatchingRateGet(),dispPkOne(0));
    }

    public static String callPokemonBeanClickAbility() {
        return callPokemonBeanClickAbility(dispPkOne(0));
    }

    public static String callPokemonBeanClickAbility(NaSt _str) {
        return navigateData(new PokemonBeanClickAbility(),_str,0);
    }

    public static String callPokemonBeanClickAbilityId() {
        NaSt bean_ = dispPkOne(0);
        callPokemonBeanClickAbility(bean_);
        return getValAbilityId(bean_);
    }

    public static String callPokemonBeanClickBase(NaSt _str) {
        return navigateData(new PokemonBeanClickBase(),_str);
    }

    public static String callPokemonBeanClickBase() {
        return callPokemonBeanClickBase(dispPkOne(1));
    }

    public static String callPokemonBeanClickBaseId() {
        NaSt bean_ = dispPkOne(1);
        callPokemonBeanClickBase(bean_);
        return getValPkId(bean_);
    }

    public static String callPokemonBeanClickEggPk(int _row,int _args) {
        return callPokemonBeanClickEggPk(dispPkOne(_row),_args);
    }

    public static String callPokemonBeanClickEggPk(NaSt _str, int _ind) {
        return navigateData(new PokemonBeanClickEggPk(),_str,_ind);
    }

    public static String callPokemonBeanClickEggPkId(int _row,int _args) {
        NaSt bean_ = dispPkOne(_row);
        callPokemonBeanClickEggPk(bean_,_args);
        return getValPkId(bean_);
    }

    public static String callPokemonBeanClickHiddenMove() {
        return callPokemonBeanClickHiddenMove(dispPkOne(0));
    }

    public static String callPokemonBeanClickHiddenMove(NaSt _str) {
        return navigateData(new PokemonBeanClickHiddenMove(),_str,0);
    }

    public static String callPokemonBeanClickHiddenMoveId() {
        NaSt bean_ = dispPkOne(0);
        callPokemonBeanClickHiddenMove(bean_);
        return getValMoveId(bean_);
    }

    public static String callPokemonBeanClickLevel() {
        return callPokemonBeanClickLevel(dispPkOne(10));
    }

    public static String callPokemonBeanClickLevel(NaSt _str) {
        PokemonBean pk_ = new PokemonBean();
        pk_.setBuilder(((PokemonBeanStruct)_str).getInstance().getBuilder());
        navigateData(new PokemonBeanClickLevel(),new PokemonBeanStruct(pk_),2,1);
        return navigateData(new MapBeanClickLevelBeanAction((CommonBean) ((PokemonBeanStruct)_str).getBean(),2,1),_str);
    }

    public static Coords callPokemonBeanClickLevelId() {
        NaSt bean_ = dispPkOne(10);
        callPokemonBeanClickLevel(bean_);
        assertTrue(containsPlaceLevelId(bean_));
        return getValPlaceLevelId(bean_);
    }

    public static String callPokemonBeanClickLevelZero() {
        return callPokemonBeanClickLevelZero(dispPkOne(1));
    }

    public static String callPokemonBeanClickLevelZero(NaSt _str) {
        return navigateData(new PokemonBeanClickLevelZero(),_str,0);
    }

    public static Coords callPokemonBeanClickLevelZeroId() {
        NaSt bean_ = dispPkOne(1);
        callPokemonBeanClickLevelZero(bean_);
        assertTrue(containsPlaceLevelId(bean_));
        return getValPlaceLevelId(bean_);
    }
    public static String callPokemonBeanClickMove(int _args) {
        return callPokemonBeanClickMove(dispPkOne(0),_args);
    }

    public static String callPokemonBeanClickMove(NaSt _str, int _args) {
        return navigateData(new PokemonBeanClickMove(),_str,_args);
    }

    public static String callPokemonBeanClickMoveId(int _args) {
        NaSt bean_ = dispPkOne(0);
        callPokemonBeanClickMove(bean_,_args);
        return getValMoveId(bean_);
    }

    public static String callPokemonBeanClickMoveTutors() {
        return callPokemonBeanClickMoveTutors(dispPkOne(0));
    }

    public static String callPokemonBeanClickMoveTutors(NaSt _str) {
        return navigateData(new PokemonBeanClickMoveTutors(),_str,0);
    }

    public static String callPokemonBeanClickMoveTutorsId() {
        NaSt bean_ = dispPkOne(0);
        callPokemonBeanClickMoveTutors(bean_);
        return getValMoveId(bean_);
    }

    public static String callPokemonBeanClickPokedex() {
        NaSt bean_ = dispPkOne(0);
        String value_ = navigateData(new PokemonBeanClickPokedex(((PokemonBean) ((PokemonBeanStruct)bean_).getBean())),bean_);
        assertFalse(containsPlaceLevelId(bean_));
        return value_;
    }

    public static String callPokemonBeanClickTechnicalMove() {
        return callPokemonBeanClickTechnicalMove(dispPkOne(0));
    }

    public static String callPokemonBeanClickTechnicalMove(NaSt _str) {
        return navigateData(new PokemonBeanClickTechnicalMove(),_str,0);
    }

    public static String callPokemonBeanClickTechnicalMoveId() {
        NaSt bean_ = dispPkOne(0);
        callPokemonBeanClickTechnicalMove(bean_);
        return getValMoveId(bean_);
    }

    public static NaSt callPokemonBeanDisplayNameGet(int _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanDisplayNameGet(),dispPkOne(_args));
    }

    public static NaSt callPokemonBeanEggGroupsPkGet(int _line) {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanEggGroupsPkGet(),dispPkOne(_line));
    }

    public static NaSt callPokemonBeanEvoBaseGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanEvoBaseGet(),dispPkOne(1));
    }

    public static NaSt callPokemonBeanEvolutionsGet() {
        return callPokemonBeanEvolutionsGet(dispPkOne(0));
    }

    public static NaSt callPokemonBeanEvolutionsGet(NaSt _str) {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanEvolutionsGet(),_str);
    }

    public static NaSt callPokemonBeanExpEvoGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanExpEvoGet(),dispPkOne(0));
    }

    public static NaSt callPokemonBeanExpRateGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanExpRateGet(),dispPkOne(0));
    }

    public static NaSt callPokemonBeanFrontImageGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanFrontImageGet(),dispPkOne(0));
    }

    public static NaSt callPokemonBeanGetBase() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanGetBase(),dispPkOne(0),0);
    }

    public static NaSt callPokemonBeanGetEggPk(int _line, int _ind) {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanGetEggPk(),dispPkOne(_line),_ind);
    }

    public static NaSt callPokemonBeanGetEv() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanGetEv(),dispPkOne(0),0);
    }

    public static NaSt callPokemonBeanGetMapWidth() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanGetMapWidth(),dispPkOne(0));
    }

    public static NaSt callPokemonBeanGetMiniMapImage(int _tile) {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanGetMiniMapImage(),dispPkOne(0),_tile);
    }

    public static NaSt callPokemonBeanGetMoveTutor() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanGetMoveTutor(),dispPkOne(0),0);
    }

//    public static NaSt callPokemonBeanGetPage(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new PokemonBeanGetPage(),_str,_args);
//    }

    public static NaSt callPokemonBeanGetPlaceName(int _tile) {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanGetPlaceName(),dispPkOne(0),_tile);
    }

    public static NaSt callPokemonBeanGetTrAbility() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanGetTrAbility(),dispPkOne(0),0);
    }

    public static NaSt callPokemonBeanHatchingStepsGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanHatchingStepsGet(),dispPkOne(0));
    }

    public static NaSt callPokemonBeanHeightGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanHeightGet(),dispPkOne(0));
    }

    public static NaSt callPokemonBeanHiddenMovesGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanHiddenMovesGet(),dispPkOne(0));
    }

    public static NaSt callPokemonBeanImagesGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanImagesGet(),dispPkOne(0));
    }

    public static NaSt callPokemonBeanIsAppearing(int _pk,int _pl, int _lev) {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanIsAppearing(),dispPkOne(_pk),_pl,_lev);
    }

    public static NaSt callPokemonBeanIsAppearingAnyWhere(int _pk) {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanIsAppearingAnyWhere(),dispPkOne(_pk));
    }

    public static NaSt callPokemonBeanIsAppearingPlace(int _pk,int _pl) {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanIsAppearingPlace(),dispPkOne(_pk),_pl);
    }

    public static NaSt callPokemonBeanIsAppearingZero(int _pk,int _pl) {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanIsAppearingZero(),dispPkOne(_pk),_pl);
    }
//
//    public static Struct callPokemonBeanIsFirstRow(int _row) {
//        return BeanPokemonCommonTs.callLongs(new PokemonBeanIsFirstRow(),dispPkOne(0),_row);
//    }

    public static NaSt callPokemonBeanIsMultiLayer(int _pl) {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanIsMultiLayer(),dispPkOne(0),_pl);
    }

    public static NaSt callPokemonBeanLayers(int _pl) {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanLayers(),dispPkOne(0),_pl);
    }

    public static NaSt callPokemonBeanLevMovesGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanLevMovesGet(),dispPkOne(0));
    }

    public static NaSt callPokemonBeanMapVarsGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanMapVarsGet(),dispPkOne(0));
    }

    public static NaSt callPokemonBeanMoveTutorsGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanMoveTutorsGet(),dispPkOne(0));
    }

//    public static NaSt callPokemonBeanNameGet(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new PokemonBeanNameGet(),_str,_args);
//    }

    public static NaSt callPokemonBeanPlacesGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanPlacesGet(),dispPkOne(0));
    }

    public static NaSt callPokemonBeanPossibleGendersGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanPossibleGendersGet(),dispPkOne(0));
    }

    public static NaSt callPokemonBeanRoundHeight() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanRoundHeight(),dispPkOne(0));
    }

    public static NaSt callPokemonBeanRoundWeight() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanRoundWeight(),dispPkOne(0));
    }

    public static NaSt callPokemonBeanStatisticsGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanStatisticsGet(),dispPkOne(0));
    }

    public static NaSt callPokemonBeanTechnicalMovesGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanTechnicalMovesGet(),dispPkOne(0));
    }

    public static NaSt callPokemonBeanTypesGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanTypesGet(),dispPkOne(0));
    }

    public static NaSt callPokemonBeanWeightGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanWeightGet(),dispPkOne(0));
    }


    public static NaSt callEvolutionHappinessBeanMinGet() {
        return BeanPokemonCommonTs.callLongs(new EvolutionHappinessBeanMinGet(),dispPkOne(12,0));
    }

    public static String callEvolutionItemBeanClickItem() {
        return callEvolutionItemBeanClickItem(dispPkOne(9,0));
    }

    public static String callEvolutionItemBeanClickItem(NaSt _str) {
        return navigateData(new EvolutionItemBeanClickItem(),_str);
    }

    public static String callEvolutionItemBeanClickItemId() {
        NaSt bean_ = dispPkOne(9, 0);
        callEvolutionItemBeanClickItem(bean_);
        return getValItemId(bean_);
    }

    public static NaSt callEvolutionItemBeanItemGet() {
        return BeanPokemonCommonTs.callLongs(new EvolutionItemBeanItemGet(),dispPkOne(9,0));
    }

    public static NaSt callEvolutionLevelBeanLevelGet() {
        return BeanPokemonCommonTs.callLongs(new EvolutionLevelBeanLevelGet(),dispPkOne(0,0));
    }

    public static NaSt callEvolutionLevelGenderBeanGenderGet() {
        return BeanPokemonCommonTs.callLongs(new EvolutionLevelGenderBeanGenderGet(),dispPkOne(1,0));
    }

    public static String callEvolutionMoveBeanClickMove() {
        return callEvolutionMoveBeanClickMove(dispPkOne(5,0));
    }

    public static String callEvolutionMoveBeanClickMove(NaSt _str) {
        return navigateData(new EvolutionMoveBeanClickMove(),_str);
    }

    public static String callEvolutionMoveBeanClickMoveId() {
        NaSt bean_ = dispPkOne(5, 0);
        callEvolutionMoveBeanClickMove(bean_);
        return getValMoveId(bean_);
    }

    public static NaSt callEvolutionMoveBeanMoveGet() {
        return BeanPokemonCommonTs.callLongs(new EvolutionMoveBeanMoveGet(),dispPkOne(5,0));
    }

    public static NaSt callEvolutionMoveTypeBeanTypeGet() {
        return BeanPokemonCommonTs.callLongs(new EvolutionMoveTypeBeanTypeGet(),dispPkOne(5,1));
    }

    public static String callEvolutionStoneBeanClickStone() {
        return callEvolutionStoneBeanClickStone(dispPkOne(3,0));
    }

    public static String callEvolutionStoneBeanClickStone(NaSt _str) {
        return navigateData(new EvolutionStoneBeanClickStone(),_str);
    }

    public static String callEvolutionStoneBeanClickStoneId() {
        NaSt bean_ = dispPkOne(3, 0);
        callEvolutionStoneBeanClickStone(bean_);
        return getValItemId(bean_);
    }

    public static NaSt callEvolutionStoneBeanStoneGet() {
        return BeanPokemonCommonTs.callLongs(new EvolutionStoneBeanStoneGet(),dispPkOne(3,0));
    }

    public static NaSt callEvolutionStoneGenderBeanGenderGet() {
        return BeanPokemonCommonTs.callLongs(new EvolutionStoneGenderBeanGenderGet(),dispPkOne(4,0));
    }

    public static String callEvolutionTeamBeanClickTeam() {
        return callEvolutionTeamBeanClickTeam(dispPkOne(8,0));
    }

    public static String callEvolutionTeamBeanClickTeam(NaSt _str) {
        return navigateData(new EvolutionTeamBeanClickTeam(),_str);
    }

    public static String callEvolutionTeamBeanClickTeamId() {
        NaSt bean_ = dispPkOne(8, 0);
        callEvolutionTeamBeanClickTeam(bean_);
        return getValPkId(bean_);
    }

    public static NaSt callEvolutionTeamBeanOtherGet() {
        return BeanPokemonCommonTs.callLongs(new EvolutionTeamBeanOtherGet(),dispPkOne(8,0));
    }

    public static String callEvolutionBeanClickEvo() {
        return callEvolutionBeanClickEvo(dispPkOne(12,0));
    }

    public static String callEvolutionBeanClickEvo(NaSt _str) {
        return navigateData(new EvolutionBeanClickEvo(),_str);
    }

    public static String callEvolutionBeanClickEvoId() {
        NaSt bean_ = dispPkOne(12, 0);
        callEvolutionBeanClickEvo(bean_);
        return getValPkId(bean_);
    }

    public static NaSt callEvolutionBeanDisplayBaseGet() {
        return BeanPokemonCommonTs.callLongs(new EvolutionBeanDisplayBaseGet(),dispPkOne(1,0));
    }

    public static NaSt callEvolutionBeanDisplayNameGet() {
        return BeanPokemonCommonTs.callLongs(new EvolutionBeanDisplayNameGet(),dispPkOne(12,0));
    }

//    public static NaSt callEvolutionBeanIndexGet(NaSt _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new EvolutionBeanIndexGet(),_str,_args);
//    }
//    public static NaSt callEvolutionBeanBaseSet(NaSt _str, String _args) {
//        return BeanPokemonCommonTs.callString(new EvolutionBeanBaseSet(),_str,_args);
//    }
//
//    public static NaSt callEvolutionBeanNameSet(NaSt _str, String _args) {
//        return BeanPokemonCommonTs.callString(new EvolutionBeanNameSet(),_str,_args);
//    }
//
//    public static NaSt callEvolutionBeanIndexSet(NaSt _str, int _args) {
//        return BeanPokemonCommonTs.callInt(new EvolutionBeanIndexSet(),_str,_args);
//    }

    public static NaSt eltLvMv(NaSt _arr, int _index) {
        return ((NatArrayStruct)_arr).get(_index);
    }

    public static NaSt eltImg(NaSt _arr, int _index) {
        return ((NatArrayStruct)_arr).get(_index);
    }
    public static NaSt secondImg(NaSt _arr) {
        return ((PairStruct)_arr).getSecond();
    }
    protected static NaSt dispPkOne(int _index) {
        PkData pk_ = pkDataByFacade(feedDb());
        StringMap<NaSt> all_ = beanToPkOne(pk_);
        return transitToAllPks(pk_, all_, _index);
    }
    protected static NaSt dispPkOne(int _index, int _evo) {
        PkData pk_ = pkDataByFacade(feedDb());
        StringMap<NaSt> all_ = beanToPkOne(pk_);
//        StringMap<String> mapping_ = mappingToPkOne();
        NaSt pkbean_ = transitToAllPks(pk_, all_, _index);
//        NaSt evobean_ = byStr(all_, mapping_, callPokemonBeanGetPage(pkbean_, _evo));
//        callEvolutionBeanBaseSet(evobean_,toStr(callPokemonBeanNameGet(pkbean_)));
//        callEvolutionBeanIndexSet(evobean_,_evo);
//        callEvolutionBeanNameSet(evobean_,toStr(elt(callPokemonBeanEvolutionsGet(pkbean_),_evo)));
//        setFormsBy(pk_,evobean_,pkbean_);
//        beforeDisplaying(evobean_);
        return new PokemonBeanStruct(((PokemonBean)((PokemonBeanStruct)pkbean_).getBean()).getBeans().get(_evo));
    }
    public static StringMap<NaSt> beanToPkOne(PkData _pk) {
        StringMap<NaSt> map_ = beanToPk(_pk);
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.PK_DATA,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.PK_DATA,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_HAPPINESS,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_ITEM,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_LEVEL,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_LEVEL_GENDER,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_MOVE,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_STONE,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_STONE_GENDER,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_TEAM,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_TYPE,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_HAPPINESS,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_ITEM,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_LEVEL,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_LEVEL_GENDER,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_MOVE,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_STONE,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_STONE_GENDER,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_TEAM,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.EVO_TYPE,new TranslationsFile());
        PokemonBean pkBean_ = new PokemonBean();
        pkBean_.setBuilder(((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_POKEMON_DATA_HTML,pkBean_);
        map_.addEntry(AikiBeansPokemonStd.BEAN_PK, _pk.bean(pkBean_, EN));
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
