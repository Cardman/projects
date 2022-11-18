package aiki.beans.map;

import aiki.beans.*;
import aiki.beans.map.elements.*;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;

public abstract class InitDbArea extends InitDbLevelMap{

    public static Struct callAreaApparitionGetAvgNbSteps(int _area) {
        return BeanPokemonCommonTs.callLongs(new AreaApparitionGetAvgNbSteps(),callAreaBeanAreaGet(_area));
    }

    public static Struct callAreaApparitionGetWildPokemon(int _area) {
        return BeanPokemonCommonTs.callLongs(new AreaApparitionGetWildPokemon(),callAreaBeanAreaGet(_area));
    }

    public static Struct callAreaApparitionGetWildPokemonFishing(int _area) {
        return BeanPokemonCommonTs.callLongs(new AreaApparitionGetWildPokemonFishing(),callAreaBeanAreaGet(_area));
    }

    public static Struct callAreaBeanAreaGet(int _area) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanAreaGet(),displayArea(_area));
    }

    public static String callAreaBeanClickAbilityId(int _area, int _pk) {
        Struct bean_ = displayArea(_area);
        BeanPokemonCommonTs.callLongs(new AreaBeanClickAbility(),bean_,_pk);
        return getValAbilityId(bean_);
    }

    public static String callAreaBeanClickAbilityFishingId(int _area, int _pk) {
        Struct bean_ = displayArea(_area);
        BeanPokemonCommonTs.callLongs(new AreaBeanClickAbilityFishing(),bean_,_pk);
        return getValAbilityId(bean_);
    }

    public static String callAreaBeanClickItem(int _area, int _pk) {
        return callAreaBeanClickItem(displayArea(_area),_pk);
    }

    public static String callAreaBeanClickItemFishing(int _area, int _pk) {
        return callAreaBeanClickItemFishing(displayArea(_area),_pk);
    }

    public static String callAreaBeanClickItem(Struct _str, int _pk) {
        return navigateData(new AreaBeanClickItem(),_str,_pk);
    }

    public static String callAreaBeanClickItemFishing(Struct _str, int _pk) {
        return navigateData(new AreaBeanClickItemFishing(),_str,_pk);
    }

    public static String callAreaBeanClickItemId(int _area, int _pk) {
        Struct bean_ = displayArea(_area);
        callAreaBeanClickItem(bean_,_pk);
        return getValItemId(bean_);
    }

    public static String callAreaBeanClickItemFishingId(int _area, int _pk) {
        Struct bean_ = displayArea(_area);
        callAreaBeanClickItemFishing(bean_,_pk);
        return getValItemId(bean_);
    }

    public static String callAreaBeanClickMoveId(int _area, int _pk, int _move) {
        Struct bean_ = displayArea(_area);
        BeanPokemonCommonTs.callLongs(new AreaBeanClickMove(),bean_,_pk,_move);
        return getValMoveId(bean_);
    }

    public static String callAreaBeanClickMoveFishingId(int _area, int _pk, int _move) {
        Struct bean_ = displayArea(_area);
        BeanPokemonCommonTs.callLongs(new AreaBeanClickMoveFishing(),bean_,_pk,_move);
        return getValMoveId(bean_);
    }

    public static String callAreaBeanClickNameId(int _area, int _pk) {
        Struct bean_ = displayArea(_area);
        BeanPokemonCommonTs.callLongs(new AreaBeanClickName(),bean_,_pk);
        return getValPkId(bean_);
    }

    public static String callAreaBeanClickNameFishingId(int _area, int _pk) {
        Struct bean_ = displayArea(_area);
        BeanPokemonCommonTs.callLongs(new AreaBeanClickNameFishing(),bean_,_pk);
        return getValPkId(bean_);
    }

    public static Struct callAreaBeanGetAbility(int _area, int _pk) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetAbility(),displayArea(_area),_pk);
    }

    public static Struct callAreaBeanGetAbilityFishing(int _area, int _pk) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetAbilityFishing(),displayArea(_area),_pk);
    }

    public static Struct callAreaBeanGetGender(int _area, int _pk) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetGender(),displayArea(_area),_pk);
    }

    public static Struct callAreaBeanGetGenderFishing(int _area, int _pk) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetGenderFishing(),displayArea(_area),_pk);
    }

    public static Struct callAreaBeanGetImage(int _area, int _pk) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetImage(),displayArea(_area),_pk);
    }

    public static Struct callAreaBeanGetImageFishing(int _area, int _pk) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetImageFishing(),displayArea(_area),_pk);
    }

    public static Struct callAreaBeanGetItem(int _area, int _pk) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetItem(),displayArea(_area),_pk);
    }

    public static Struct callAreaBeanGetItemFishing(int _area, int _pk) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetItemFishing(),displayArea(_area),_pk);
    }

    public static Struct callAreaBeanGetMove(int _area, int _pk, int _move) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetMove(),displayArea(_area),_pk,_move);
    }

    public static Struct callAreaBeanGetMoveFishing(int _area, int _pk, int _move) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetMoveFishing(),displayArea(_area),_pk,_move);
    }

    public static Struct callAreaBeanGetMovesAtLevel(int _area, int _pk) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetMovesAtLevel(),displayArea(_area),_pk);
    }

    public static Struct callAreaBeanGetMovesAtLevelFishing(int _area, int _pk) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetMovesAtLevelFishing(),displayArea(_area),_pk);
    }

    public static Struct callAreaBeanGetName(int _area, int _pk) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetName(),displayArea(_area),_pk);
    }

    public static Struct callAreaBeanGetNameFishing(int _area, int _pk) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetNameFishing(),displayArea(_area),_pk);
    }
    public static Struct displayArea(int _area){
        PkData pk_ = pkDataByFacade(db());
        StringMap<Struct> all_ = beanToMap(pk_);
        return transitArea(_area,pk_,all_);
    }

    private static Struct transitArea(int _no,PkData _pk, StringMap<Struct> _all) {
        Struct bean_ = transitLevelZero(2, _pk, _all);
        Struct area_ = _all.getVal(AikiBeansMapStd.BEAN_AREA);
        transit(_pk,new MapLevelBeanClickArea(),bean_,area_,_no);
        return area_;
    }
}
