package aiki.beans.pokemon;

import aiki.beans.*;
import aiki.beans.pokemon.evolutions.*;
import aiki.util.Coords;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;

public abstract class InitDbPkOne extends InitDbPk {

    public static Struct callLevelMoveGetLevel(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new LevelMoveGetLevel(),_str,_args);
    }

    public static Struct callLevelMoveGetMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new LevelMoveGetMove(),_str,_args);
    }

    public static Struct callPokemonBeanAbilitiesGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanAbilitiesGet(),dispPkOne(0));
    }

    public static Struct callPokemonBeanBackImageGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanBackImageGet(),dispPkOne(0));
    }

    public static Struct callPokemonBeanCatchingRateGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanCatchingRateGet(),dispPkOne(0));
    }

    public static String callPokemonBeanClickAbility() {
        return callPokemonBeanClickAbility(dispPkOne(0));
    }

    public static String callPokemonBeanClickAbility(Struct _str) {
        return navigateData(new PokemonBeanClickAbility(),_str,0);
    }

    public static String callPokemonBeanClickAbilityId() {
        Struct bean_ = dispPkOne(0);
        callPokemonBeanClickAbility(bean_);
        return getValAbilityId(bean_);
    }

    public static String callPokemonBeanClickBase(Struct _str) {
        return navigateData(new PokemonBeanClickBase(),_str);
    }

    public static String callPokemonBeanClickBase() {
        return callPokemonBeanClickBase(dispPkOne(1));
    }

    public static String callPokemonBeanClickBaseId() {
        Struct bean_ = dispPkOne(1);
        callPokemonBeanClickBase(bean_);
        return getValPkId(bean_);
    }

    public static String callPokemonBeanClickEggPk(int _row,int _args) {
        return callPokemonBeanClickEggPk(dispPkOne(_row),_args);
    }

    public static String callPokemonBeanClickEggPk(Struct _str, int _ind) {
        return navigateData(new PokemonBeanClickEggPk(),_str,_ind);
    }

    public static String callPokemonBeanClickEggPkId(int _row,int _args) {
        Struct bean_ = dispPkOne(_row);
        callPokemonBeanClickEggPk(bean_,_args);
        return getValPkId(bean_);
    }

    public static String callPokemonBeanClickHiddenMove() {
        return callPokemonBeanClickHiddenMove(dispPkOne(0));
    }

    public static String callPokemonBeanClickHiddenMove(Struct _str) {
        return navigateData(new PokemonBeanClickHiddenMove(),_str,0);
    }

    public static String callPokemonBeanClickHiddenMoveId() {
        Struct bean_ = dispPkOne(0);
        callPokemonBeanClickHiddenMove(bean_);
        return getValMoveId(bean_);
    }

    public static String callPokemonBeanClickLevel() {
        return callPokemonBeanClickLevel(dispPkOne(10));
    }

    public static String callPokemonBeanClickLevel(Struct _str) {
        return navigateData(new PokemonBeanClickLevel(),_str,2,1);
    }

    public static Coords callPokemonBeanClickLevelId() {
        Struct bean_ = dispPkOne(10);
        callPokemonBeanClickLevel(bean_);
        assertTrue(containsPlaceLevelId(bean_));
        return getValPlaceLevelId(bean_);
    }

    public static String callPokemonBeanClickLevelZero() {
        return callPokemonBeanClickLevelZero(dispPkOne(1));
    }

    public static String callPokemonBeanClickLevelZero(Struct _str) {
        return navigateData(new PokemonBeanClickLevelZero(),_str,0);
    }

    public static Coords callPokemonBeanClickLevelZeroId() {
        Struct bean_ = dispPkOne(1);
        callPokemonBeanClickLevelZero(bean_);
        assertTrue(containsPlaceLevelId(bean_));
        return getValPlaceLevelId(bean_);
    }
    public static String callPokemonBeanClickMove(int _args) {
        return callPokemonBeanClickMove(dispPkOne(0),_args);
    }

    public static String callPokemonBeanClickMove(Struct _str, int _args) {
        return navigateData(new PokemonBeanClickMove(),_str,_args);
    }

    public static String callPokemonBeanClickMoveId(int _args) {
        Struct bean_ = dispPkOne(0);
        callPokemonBeanClickMove(bean_,_args);
        return getValMoveId(bean_);
    }

    public static String callPokemonBeanClickMoveTutors() {
        return callPokemonBeanClickMoveTutors(dispPkOne(0));
    }

    public static String callPokemonBeanClickMoveTutors(Struct _str) {
        return navigateData(new PokemonBeanClickMoveTutors(),_str,0);
    }

    public static String callPokemonBeanClickMoveTutorsId() {
        Struct bean_ = dispPkOne(0);
        callPokemonBeanClickMoveTutors(bean_);
        return getValMoveId(bean_);
    }

    public static String callPokemonBeanClickPokedex() {
        Struct bean_ = dispPkOne(0);
        String value_ = navigateData(new PokemonBeanClickPokedex(), bean_);
        assertFalse(containsPlaceLevelId(bean_));
        return value_;
    }

    public static String callPokemonBeanClickTechnicalMove() {
        return callPokemonBeanClickTechnicalMove(dispPkOne(0));
    }

    public static String callPokemonBeanClickTechnicalMove(Struct _str) {
        return navigateData(new PokemonBeanClickTechnicalMove(),_str,0);
    }

    public static String callPokemonBeanClickTechnicalMoveId() {
        Struct bean_ = dispPkOne(0);
        callPokemonBeanClickTechnicalMove(bean_);
        return getValMoveId(bean_);
    }

    public static Struct callPokemonBeanDisplayNameGet(int _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanDisplayNameGet(),dispPkOne(_args));
    }

    public static Struct callPokemonBeanEggGroupsPkGet(int _line) {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanEggGroupsPkGet(),dispPkOne(_line));
    }

    public static Struct callPokemonBeanEvoBaseGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanEvoBaseGet(),dispPkOne(1));
    }

    public static Struct callPokemonBeanEvolutionsGet() {
        return callPokemonBeanEvolutionsGet(dispPkOne(0));
    }

    public static Struct callPokemonBeanEvolutionsGet(Struct _str) {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanEvolutionsGet(),_str);
    }

    public static Struct callPokemonBeanExpEvoGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanExpEvoGet(),dispPkOne(0));
    }

    public static Struct callPokemonBeanExpRateGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanExpRateGet(),dispPkOne(0));
    }

    public static Struct callPokemonBeanFrontImageGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanFrontImageGet(),dispPkOne(0));
    }

    public static Struct callPokemonBeanGetBase() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanGetBase(),dispPkOne(0),0);
    }

    public static Struct callPokemonBeanGetEggPk(int _line, int _ind) {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanGetEggPk(),dispPkOne(_line),_ind);
    }

    public static Struct callPokemonBeanGetEv() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanGetEv(),dispPkOne(0),0);
    }

    public static Struct callPokemonBeanGetMapWidth() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanGetMapWidth(),dispPkOne(0));
    }

    public static Struct callPokemonBeanGetMiniMapImage(int _tile) {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanGetMiniMapImage(),dispPkOne(0),_tile);
    }

    public static Struct callPokemonBeanGetMoveTutor() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanGetMoveTutor(),dispPkOne(0),0);
    }

    public static Struct callPokemonBeanGetPage(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanGetPage(),_str,_args);
    }

    public static Struct callPokemonBeanGetPlaceName(int _tile) {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanGetPlaceName(),dispPkOne(0),_tile);
    }

    public static Struct callPokemonBeanGetTrAbility() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanGetTrAbility(),dispPkOne(0),0);
    }

    public static Struct callPokemonBeanHatchingStepsGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanHatchingStepsGet(),dispPkOne(0));
    }

    public static Struct callPokemonBeanHeightGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanHeightGet(),dispPkOne(0));
    }

    public static Struct callPokemonBeanHiddenMovesGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanHiddenMovesGet(),dispPkOne(0));
    }

    public static Struct callPokemonBeanImagesGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanImagesGet(),dispPkOne(0));
    }

    public static Struct callPokemonBeanIsAppearing(int _pk,int _pl, int _lev) {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanIsAppearing(),dispPkOne(_pk),_pl,_lev);
    }

    public static Struct callPokemonBeanIsAppearingAnyWhere(int _pk) {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanIsAppearingAnyWhere(),dispPkOne(_pk));
    }

    public static Struct callPokemonBeanIsAppearingPlace(int _pk,int _pl) {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanIsAppearingPlace(),dispPkOne(_pk),_pl);
    }

    public static Struct callPokemonBeanIsAppearingZero(int _pk,int _pl) {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanIsAppearingZero(),dispPkOne(_pk),_pl);
    }
//
//    public static Struct callPokemonBeanIsFirstRow(int _row) {
//        return BeanPokemonCommonTs.callLongs(new PokemonBeanIsFirstRow(),dispPkOne(0),_row);
//    }

    public static Struct callPokemonBeanIsMultiLayer(int _pl) {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanIsMultiLayer(),dispPkOne(0),_pl);
    }

    public static Struct callPokemonBeanLayers(int _pl) {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanLayers(),dispPkOne(0),_pl);
    }

    public static Struct callPokemonBeanLevMovesGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanLevMovesGet(),dispPkOne(0));
    }

    public static Struct callPokemonBeanMapVarsGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanMapVarsGet(),dispPkOne(0));
    }

    public static Struct callPokemonBeanMoveTutorsGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanMoveTutorsGet(),dispPkOne(0));
    }

    public static Struct callPokemonBeanNameGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanNameGet(),_str,_args);
    }

    public static Struct callPokemonBeanPlacesGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanPlacesGet(),dispPkOne(0));
    }

    public static Struct callPokemonBeanPossibleGendersGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanPossibleGendersGet(),dispPkOne(0));
    }

    public static Struct callPokemonBeanRoundHeight() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanRoundHeight(),dispPkOne(0));
    }

    public static Struct callPokemonBeanRoundWeight() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanRoundWeight(),dispPkOne(0));
    }

    public static Struct callPokemonBeanStatisticsGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanStatisticsGet(),dispPkOne(0));
    }

    public static Struct callPokemonBeanTechnicalMovesGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanTechnicalMovesGet(),dispPkOne(0));
    }

    public static Struct callPokemonBeanTypesGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanTypesGet(),dispPkOne(0));
    }

    public static Struct callPokemonBeanWeightGet() {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanWeightGet(),dispPkOne(0));
    }


    public static Struct callEvolutionHappinessBeanMinGet() {
        return BeanPokemonCommonTs.callLongs(new EvolutionHappinessBeanMinGet(),dispPkOne(12,0));
    }

    public static String callEvolutionItemBeanClickItem() {
        return callEvolutionItemBeanClickItem(dispPkOne(9,0));
    }

    public static String callEvolutionItemBeanClickItem(Struct _str) {
        return navigateData(new EvolutionItemBeanClickItem(),_str,toInt(callEvolutionBeanIndexGet(_str)));
    }

    public static String callEvolutionItemBeanClickItemId() {
        Struct bean_ = dispPkOne(9, 0);
        callEvolutionItemBeanClickItem(bean_);
        return getValItemId(bean_);
    }

    public static Struct callEvolutionItemBeanItemGet() {
        return BeanPokemonCommonTs.callLongs(new EvolutionItemBeanItemGet(),dispPkOne(9,0));
    }

    public static Struct callEvolutionLevelBeanLevelGet() {
        return BeanPokemonCommonTs.callLongs(new EvolutionLevelBeanLevelGet(),dispPkOne(0,0));
    }

    public static Struct callEvolutionLevelGenderBeanGenderGet() {
        return BeanPokemonCommonTs.callLongs(new EvolutionLevelGenderBeanGenderGet(),dispPkOne(1,0));
    }

    public static String callEvolutionMoveBeanClickMove() {
        return callEvolutionMoveBeanClickMove(dispPkOne(5,0));
    }

    public static String callEvolutionMoveBeanClickMove(Struct _str) {
        return navigateData(new EvolutionMoveBeanClickMove(),_str,toInt(callEvolutionBeanIndexGet(_str)));
    }

    public static String callEvolutionMoveBeanClickMoveId() {
        Struct bean_ = dispPkOne(5, 0);
        callEvolutionMoveBeanClickMove(bean_);
        return getValMoveId(bean_);
    }

    public static Struct callEvolutionMoveBeanMoveGet() {
        return BeanPokemonCommonTs.callLongs(new EvolutionMoveBeanMoveGet(),dispPkOne(5,0));
    }

    public static Struct callEvolutionMoveTypeBeanTypeGet() {
        return BeanPokemonCommonTs.callLongs(new EvolutionMoveTypeBeanTypeGet(),dispPkOne(5,1));
    }

    public static String callEvolutionStoneBeanClickStone() {
        return callEvolutionStoneBeanClickStone(dispPkOne(3,0));
    }

    public static String callEvolutionStoneBeanClickStone(Struct _str) {
        return navigateData(new EvolutionStoneBeanClickStone(),_str,toInt(callEvolutionBeanIndexGet(_str)));
    }

    public static String callEvolutionStoneBeanClickStoneId() {
        Struct bean_ = dispPkOne(3, 0);
        callEvolutionStoneBeanClickStone(bean_);
        return getValItemId(bean_);
    }

    public static Struct callEvolutionStoneBeanStoneGet() {
        return BeanPokemonCommonTs.callLongs(new EvolutionStoneBeanStoneGet(),dispPkOne(3,0));
    }

    public static Struct callEvolutionStoneGenderBeanGenderGet() {
        return BeanPokemonCommonTs.callLongs(new EvolutionStoneGenderBeanGenderGet(),dispPkOne(4,0));
    }

    public static String callEvolutionTeamBeanClickTeam() {
        return callEvolutionTeamBeanClickTeam(dispPkOne(8,0));
    }

    public static String callEvolutionTeamBeanClickTeam(Struct _str) {
        return navigateData(new EvolutionTeamBeanClickTeam(),_str,toInt(callEvolutionBeanIndexGet(_str)));
    }

    public static String callEvolutionTeamBeanClickTeamId() {
        Struct bean_ = dispPkOne(8, 0);
        callEvolutionTeamBeanClickTeam(bean_);
        return getValPkId(bean_);
    }

    public static Struct callEvolutionTeamBeanOtherGet() {
        return BeanPokemonCommonTs.callLongs(new EvolutionTeamBeanOtherGet(),dispPkOne(8,0));
    }

    public static String callEvolutionBeanClickEvo() {
        return callEvolutionBeanClickEvo(dispPkOne(12,0));
    }

    public static String callEvolutionBeanClickEvo(Struct _str) {
        return navigateData(new EvolutionBeanClickEvo(),_str,toInt(callEvolutionBeanIndexGet(_str)));
    }

    public static String callEvolutionBeanClickEvoId() {
        Struct bean_ = dispPkOne(12, 0);
        callEvolutionBeanClickEvo(bean_);
        return getValPkId(bean_);
    }

    public static Struct callEvolutionBeanDisplayBaseGet() {
        return BeanPokemonCommonTs.callLongs(new EvolutionBeanDisplayBaseGet(),dispPkOne(1,0));
    }

    public static Struct callEvolutionBeanDisplayNameGet() {
        return BeanPokemonCommonTs.callLongs(new EvolutionBeanDisplayNameGet(),dispPkOne(12,0));
    }

    public static Struct callEvolutionBeanIndexGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EvolutionBeanIndexGet(),_str,_args);
    }
    public static Struct callEvolutionBeanBaseSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new EvolutionBeanBaseSet(),_str,_args);
    }

    public static Struct callEvolutionBeanNameSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new EvolutionBeanNameSet(),_str,_args);
    }

    public static Struct callEvolutionBeanIndexSet(Struct _str, int _args) {
        return BeanPokemonCommonTs.callInt(new EvolutionBeanIndexSet(),_str,_args);
    }
    protected static Struct dispPkOne(int _index) {
        PkData pk_ = pkDataByFacade(feedDb());
        StringMap<Struct> all_ = beanToPkOne(pk_);
        return transitToAllPks(pk_, all_, _index);
    }
    protected static Struct dispPkOne(int _index, int _evo) {
        PkData pk_ = pkDataByFacade(feedDb());
        StringMap<Struct> all_ = beanToPkOne(pk_);
        StringMap<String> mapping_ = mappingToPkOne();
        Struct pkbean_ = transitToAllPks(pk_, all_, _index);
        Struct evobean_ = byStr(all_, mapping_, callPokemonBeanGetPage(pkbean_, _evo));
        callEvolutionBeanBaseSet(evobean_,toStr(callPokemonBeanNameGet(pkbean_)));
        callEvolutionBeanIndexSet(evobean_,_evo);
        callEvolutionBeanNameSet(evobean_,toStr(elt(callPokemonBeanEvolutionsGet(pkbean_),_evo)));
        beforeDisplaying(evobean_);
        return evobean_;
    }
    public static StringMap<Struct> beanToPkOne(PkData _pk) {
        StringMap<Struct> map_ = beanToPk(_pk);
        map_.addEntry(AikiBeansPokemonStd.BEAN_PK,_pk.beanPokemonBean(EN));
        map_.addEntry(AikiBeansPokemonStd.BEAN_EVO_HAPPY,_pk.beanEvolutionHappinessBean(EN));
        map_.addEntry(AikiBeansPokemonStd.BEAN_EVO_ITEM,_pk.beanEvolutionItemBean(EN));
        map_.addEntry(AikiBeansPokemonStd.BEAN_EVO_LEVEL,_pk.beanEvolutionLevelBean(EN));
        map_.addEntry(AikiBeansPokemonStd.BEAN_EVO_LEVELGENDER,_pk.beanEvolutionLevelGenderBean(EN));
        map_.addEntry(AikiBeansPokemonStd.BEAN_EVO_MOVE,_pk.beanEvolutionMoveBean(EN));
        map_.addEntry(AikiBeansPokemonStd.BEAN_EVO_STONE,_pk.beanEvolutionStoneBean(EN));
        map_.addEntry(AikiBeansPokemonStd.BEAN_EVO_STONEGENDER,_pk.beanEvolutionStoneGenderBean(EN));
        map_.addEntry(AikiBeansPokemonStd.BEAN_EVO_TEAM,_pk.beanEvolutionTeamBean(EN));
        map_.addEntry(AikiBeansPokemonStd.BEAN_EVO_TYPE,_pk.beanEvolutionMoveTypeBean(EN));
        return map_;
    }
    public static StringMap<String> mappingToPkOne() {
        StringMap<String> map_ = mappingToPk();
        map_.addEntry(AikiBeansPokemonStd.WEB_HTML_POKEMON_DATA_HTML,AikiBeansPokemonStd.BEAN_PK);
        map_.addEntry(AikiBeansPokemonStd.WEB_HTML_POKEMON_EVOS_HAPPY,AikiBeansPokemonStd.BEAN_EVO_HAPPY);
        map_.addEntry(AikiBeansPokemonStd.WEB_HTML_POKEMON_EVOS_ITEM,AikiBeansPokemonStd.BEAN_EVO_ITEM);
        map_.addEntry(AikiBeansPokemonStd.WEB_HTML_POKEMON_EVOS_LEVEL,AikiBeansPokemonStd.BEAN_EVO_LEVEL);
        map_.addEntry(AikiBeansPokemonStd.WEB_HTML_POKEMON_EVOS_LEVELGENDER,AikiBeansPokemonStd.BEAN_EVO_LEVELGENDER);
        map_.addEntry(AikiBeansPokemonStd.WEB_HTML_POKEMON_EVOS_MOVE,AikiBeansPokemonStd.BEAN_EVO_MOVE);
        map_.addEntry(AikiBeansPokemonStd.WEB_HTML_POKEMON_EVOS_STONE,AikiBeansPokemonStd.BEAN_EVO_STONE);
        map_.addEntry(AikiBeansPokemonStd.WEB_HTML_POKEMON_EVOS_STONEGENDER,AikiBeansPokemonStd.BEAN_EVO_STONEGENDER);
        map_.addEntry(AikiBeansPokemonStd.WEB_HTML_POKEMON_EVOS_TEAM,AikiBeansPokemonStd.BEAN_EVO_TEAM);
        map_.addEntry(AikiBeansPokemonStd.WEB_HTML_POKEMON_EVOS_TYPE,AikiBeansPokemonStd.BEAN_EVO_TYPE);
        return map_;
    }
}
