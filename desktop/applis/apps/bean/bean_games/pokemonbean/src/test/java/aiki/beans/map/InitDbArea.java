package aiki.beans.map;

import aiki.beans.*;
import aiki.beans.map.elements.*;
import code.bean.nat.*;
import code.util.StringMap;

public abstract class InitDbArea extends InitDbLevelMap{

    public static NaSt callAreaApparitionGetAvgNbSteps(int _area) {
        return BeanPokemonCommonTs.callLongs(new AreaApparitionGetAvgNbSteps(),callAreaBeanAreaGet(_area));
    }

    public static NaSt callAreaApparitionGetWildPokemon(int _area) {
        return BeanPokemonCommonTs.callLongs(new AreaApparitionGetWildPokemon(),callAreaBeanAreaGet(_area));
    }

    public static NaSt callAreaApparitionGetWildPokemonFishing(int _area) {
        return BeanPokemonCommonTs.callLongs(new AreaApparitionGetWildPokemonFishing(),callAreaBeanAreaGet(_area));
    }

    public static NaSt callAreaBeanAreaGet(int _area) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanAreaGet(),displayArea(_area));
    }

    public static String callAreaBeanClickAbilityId(int _area, int _pk) {
        NaSt bean_ = displayArea(_area);
        BeanPokemonCommonTs.callLongs(new AreaBeanClickAbility(),bean_,_pk);
        return getValAbilityId(bean_);
    }

    public static String callAreaBeanClickAbilityFishingId(int _area, int _pk) {
        NaSt bean_ = displayArea(_area);
        BeanPokemonCommonTs.callLongs(new AreaBeanClickAbilityFishing(),bean_,_pk);
        return getValAbilityId(bean_);
    }

    public static String callAreaBeanClickItem(int _area, int _pk) {
        return callAreaBeanClickItem(displayArea(_area),_pk);
    }

    public static String callAreaBeanClickItemFishing(int _area, int _pk) {
        return callAreaBeanClickItemFishing(displayArea(_area),_pk);
    }

    public static String callAreaBeanClickItem(NaSt _str, int _pk) {
        return navigateData(new AreaBeanClickItem(),_str,_pk);
    }

    public static String callAreaBeanClickItemFishing(NaSt _str, int _pk) {
        return navigateData(new AreaBeanClickItemFishing(),_str,_pk);
    }

    public static String callAreaBeanClickItemId(int _area, int _pk) {
        NaSt bean_ = displayArea(_area);
        callAreaBeanClickItem(bean_,_pk);
        return getValItemId(bean_);
    }

    public static String callAreaBeanClickItemFishingId(int _area, int _pk) {
        NaSt bean_ = displayArea(_area);
        callAreaBeanClickItemFishing(bean_,_pk);
        return getValItemId(bean_);
    }

    public static String callAreaBeanClickMoveId(int _area, int _pk, int _move) {
        NaSt bean_ = displayArea(_area);
        BeanPokemonCommonTs.callLongs(new AreaBeanClickMove(),bean_,_pk,_move);
        return getValMoveId(bean_);
    }

    public static String callAreaBeanClickMoveFishingId(int _area, int _pk, int _move) {
        NaSt bean_ = displayArea(_area);
        BeanPokemonCommonTs.callLongs(new AreaBeanClickMoveFishing(),bean_,_pk,_move);
        return getValMoveId(bean_);
    }

    public static String callAreaBeanClickNameId(int _area, int _pk) {
        NaSt bean_ = displayArea(_area);
        BeanPokemonCommonTs.callLongs(new AreaBeanClickName(),bean_,_pk);
        return getValPkId(bean_);
    }

    public static String callAreaBeanClickNameFishingId(int _area, int _pk) {
        NaSt bean_ = displayArea(_area);
        BeanPokemonCommonTs.callLongs(new AreaBeanClickNameFishing(),bean_,_pk);
        return getValPkId(bean_);
    }

    public static NaSt callAreaBeanGetAbility(int _area, int _pk) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetAbility(),displayArea(_area),_pk);
    }

    public static NaSt callAreaBeanGetAbilityFishing(int _area, int _pk) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetAbilityFishing(),displayArea(_area),_pk);
    }

    public static NaSt callAreaBeanGetGender(int _area, int _pk) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetGender(),displayArea(_area),_pk);
    }

    public static NaSt callAreaBeanGetGenderFishing(int _area, int _pk) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetGenderFishing(),displayArea(_area),_pk);
    }

    public static NaSt callAreaBeanGetImage(int _area, int _pk) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetImage(),displayArea(_area),_pk);
    }

    public static NaSt callAreaBeanGetImageFishing(int _area, int _pk) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetImageFishing(),displayArea(_area),_pk);
    }

    public static NaSt callAreaBeanGetItem(int _area, int _pk) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetItem(),displayArea(_area),_pk);
    }

    public static NaSt callAreaBeanGetItemFishing(int _area, int _pk) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetItemFishing(),displayArea(_area),_pk);
    }

    public static NaSt callAreaBeanGetMove(int _area, int _pk, int _move) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetMove(),displayArea(_area),_pk,_move);
    }

    public static NaSt callAreaBeanGetMoveFishing(int _area, int _pk, int _move) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetMoveFishing(),displayArea(_area),_pk,_move);
    }

    public static NaSt callAreaBeanGetMovesAtLevel(int _area, int _pk) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetMovesAtLevel(),displayArea(_area),_pk);
    }

    public static NaSt callAreaBeanGetMovesAtLevelFishing(int _area, int _pk) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetMovesAtLevelFishing(),displayArea(_area),_pk);
    }

    public static NaSt callAreaBeanGetName(int _area, int _pk) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetName(),displayArea(_area),_pk);
    }

    public static NaSt callAreaBeanGetNameFishing(int _area, int _pk) {
        return BeanPokemonCommonTs.callLongs(new AreaBeanGetNameFishing(),displayArea(_area),_pk);
    }
    public static NaSt displayArea(int _area){
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToMap(pk_);
        return transitArea(_area,pk_,all_);
    }

    private static NaSt transitArea(int _no,PkData _pk, StringMap<NaSt> _all) {
        NaSt bean_ = transitLevelZero(2, _pk, _all);
        NaSt area_ = _all.getVal(AikiBeansMapStd.BEAN_AREA);
        transit(_pk,new MapLevelBeanClickArea(),bean_,area_,_no);
        return area_;
    }
}
