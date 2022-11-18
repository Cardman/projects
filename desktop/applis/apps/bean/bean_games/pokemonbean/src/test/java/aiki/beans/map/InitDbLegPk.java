package aiki.beans.map;

import aiki.beans.*;
import aiki.beans.map.elements.*;
import code.expressionlanguage.structs.Struct;
import code.util.*;

public abstract class InitDbLegPk extends InitDbLevelMap{

    public static String callLegendaryPokemonBeanClickAbilityId(int _pk) {
        Struct bean_ = displayPk(_pk);
        BeanPokemonCommonTs.callLongs(new LegendaryPokemonBeanClickAbility(),bean_);
        return getValAbilityId(bean_);
    }

    public static String callLegendaryPokemonBeanClickItem(int _pk) {
        return callLegendaryPokemonBeanClickItem(displayPk(_pk));
    }

    public static String callLegendaryPokemonBeanClickItem(Struct _str) {
        return navigateData(new LegendaryPokemonBeanClickItem(),_str);
    }

    public static String callLegendaryPokemonBeanClickItemId(int _pk) {
        Struct bean_ = displayPk(_pk);
        callLegendaryPokemonBeanClickItem(bean_);
        return getValItemId(bean_);
    }

    public static String callLegendaryPokemonBeanClickMoveId(int _pk, int _move) {
        Struct bean_ = displayPk(_pk);
        BeanPokemonCommonTs.callLongs(new LegendaryPokemonBeanClickMove(),bean_,_move);
        return getValMoveId(bean_);
    }

    public static String callLegendaryPokemonBeanClickNameId(int _pk) {
        Struct bean_ = displayPk(_pk);
        BeanPokemonCommonTs.callLongs(new LegendaryPokemonBeanClickName(),bean_);
        return getValPkId(bean_);
    }

    public static Struct callLegendaryPokemonBeanGetAbility(int _pk) {
        return BeanPokemonCommonTs.callLongs(new LegendaryPokemonBeanGetAbility(),displayPk(_pk));
    }

    public static Struct callLegendaryPokemonBeanGetGender(int _pk) {
        return BeanPokemonCommonTs.callLongs(new LegendaryPokemonBeanGetGender(),displayPk(_pk));
    }

    public static Struct callLegendaryPokemonBeanGetImage(int _pk) {
        return BeanPokemonCommonTs.callLongs(new LegendaryPokemonBeanGetImage(),displayPk(_pk));
    }

    public static Struct callLegendaryPokemonBeanGetItem(int _pk) {
        return BeanPokemonCommonTs.callLongs(new LegendaryPokemonBeanGetItem(),displayPk(_pk));
    }

    public static Struct callLegendaryPokemonBeanGetLevel(int _pk) {
        return BeanPokemonCommonTs.callLongs(new LegendaryPokemonBeanGetLevel(),displayPk(_pk));
    }

    public static Struct callLegendaryPokemonBeanGetMove(int _pk, int _move) {
        return BeanPokemonCommonTs.callLongs(new LegendaryPokemonBeanGetMove(),displayPk(_pk),_move);
    }

    public static Struct callLegendaryPokemonBeanGetMovesAtLevel(int _pk) {
        return BeanPokemonCommonTs.callLongs(new LegendaryPokemonBeanGetMovesAtLevel(),displayPk(_pk));
    }

    public static Struct callLegendaryPokemonBeanGetName(int _pk) {
        return BeanPokemonCommonTs.callLongs(new LegendaryPokemonBeanGetName(),displayPk(_pk));
    }

    public static Struct callLegendaryPokemonBeanPokemonGet(int _pk) {
        return BeanPokemonCommonTs.callLongs(new LegendaryPokemonBeanPokemonGet(),displayPk(_pk));
    }
    public static Struct displayPk(int _pk){
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToMap(pk_);
        return transitPk(mapInts().get(_pk),pk_,all_);
    }

    private static Struct transitPk(int _tile,PkData _pk, StringMap<Struct> _all) {
        Struct bean_ = transitLevel(3,0, _pk, _all);
        Struct area_ = _all.getVal(AikiBeansMapStd.BEAN_LEG_PK);
        transit(_pk,new MapLevelBeanClickTileOnMap(),bean_,area_,_tile);
        return area_;
    }
    private static Ints mapInts() {
        return Ints.newList(18,12);
    }
}
