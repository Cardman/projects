package aiki.beans.pokemon;

import aiki.beans.*;
import aiki.beans.moves.AikiBeansMovesStd;
import aiki.beans.pokemon.evolutions.*;
import aiki.facade.FacadeGame;
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

    public static Struct callPokemonBeanClickLevel(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanClickLevel(),_str,_args);
    }

    public static Struct callPokemonBeanClickLevelZero(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanClickLevelZero(),_str,_args);
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

    public static Struct callPokemonBeanClickPokedex(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanClickPokedex(),_str,_args);
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

    public static Struct callPokemonBeanIsFirstRow(int _row) {
        return BeanPokemonCommonTs.callLongs(new PokemonBeanIsFirstRow(),dispPkOne(0),_row);
    }

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


    public static Struct callEvolutionHappinessBeanMinGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EvolutionHappinessBeanMinGet(),_str,_args);
    }

    public static Struct callEvolutionItemBeanClickItem(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EvolutionItemBeanClickItem(),_str,_args);
    }

    public static Struct callEvolutionItemBeanItemGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EvolutionItemBeanItemGet(),_str,_args);
    }

    public static Struct callEvolutionLevelBeanLevelGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EvolutionLevelBeanLevelGet(),_str,_args);
    }

    public static Struct callEvolutionLevelGenderBeanGenderGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EvolutionLevelGenderBeanGenderGet(),_str,_args);
    }

    public static Struct callEvolutionMoveBeanClickMove(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EvolutionMoveBeanClickMove(),_str,_args);
    }

    public static Struct callEvolutionMoveBeanMoveGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EvolutionMoveBeanMoveGet(),_str,_args);
    }

    public static Struct callEvolutionMoveTypeBeanTypeGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EvolutionMoveTypeBeanTypeGet(),_str,_args);
    }

    public static Struct callEvolutionStoneBeanClickStone(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EvolutionStoneBeanClickStone(),_str,_args);
    }

    public static Struct callEvolutionStoneBeanStoneGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EvolutionStoneBeanStoneGet(),_str,_args);
    }

    public static Struct callEvolutionStoneGenderBeanGenderGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EvolutionStoneGenderBeanGenderGet(),_str,_args);
    }

    public static Struct callEvolutionTeamBeanClickTeam(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EvolutionTeamBeanClickTeam(),_str,_args);
    }

    public static Struct callEvolutionTeamBeanOtherGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EvolutionTeamBeanOtherGet(),_str,_args);
    }

    public static Struct callEvolutionBeanClickEvo(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EvolutionBeanClickEvo(),_str,_args);
    }

    public static Struct callEvolutionBeanDisplayBaseGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EvolutionBeanDisplayBaseGet(),_str,_args);
    }

    public static Struct callEvolutionBeanDisplayNameGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new EvolutionBeanDisplayNameGet(),_str,_args);
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
        callEvolutionBeanIndexSet(evobean_,_index);
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
