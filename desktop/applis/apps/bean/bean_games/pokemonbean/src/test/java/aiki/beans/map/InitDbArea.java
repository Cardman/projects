package aiki.beans.map;

import aiki.beans.*;
import aiki.beans.map.elements.*;
import aiki.map.levels.AbsAreaApparition;
import aiki.map.pokemon.WildPk;
import code.util.CustList;
import code.util.StringMap;

public abstract class InitDbArea extends InitDbLevelMap{

    public static long callAreaApparitionGetAvgNbSteps(int _area) {
        return callAreaBeanAreaGet(_area).getAvgNbSteps();
    }

    public static CustList<WildPk> callAreaApparitionGetWildPokemon(int _area) {
        return callAreaBeanAreaGet(_area).getWildPokemon();
    }

    public static CustList<WildPk> callAreaApparitionGetWildPokemonFishing(int _area) {
        return callAreaBeanAreaGet(_area).getWildPokemonFishing();
    }

    public static WildPk eltWild(CustList<WildPk> _a, int _i) {
        return _a.get(_i);
    }
    public static AbsAreaApparition callAreaBeanAreaGet(int _area) {
        return displayArea(_area).getArea();
    }

    public static String callAreaBeanClickAbilityId(int _area, int _pk) {
        AreaBean bean_ = displayArea(_area);
        bean_.clickAbility(_pk);
        return getValAbilityId(bean_);
    }

    public static String callAreaBeanClickAbilityFishingId(int _area, int _pk) {
        AreaBean bean_ = displayArea(_area);
        bean_.clickAbilityFishing(_pk);
        return getValAbilityId(bean_);
    }

    public static String callAreaBeanClickItem(int _area, int _pk) {
        return callAreaBeanClickItem(displayArea(_area),_pk);
    }

    public static String callAreaBeanClickItemFishing(int _area, int _pk) {
        return callAreaBeanClickItemFishing(displayArea(_area),_pk);
    }

    public static String callAreaBeanClickItem(AreaBean _str, int _pk) {
        return _str.clickItem(_pk);
    }

    public static String callAreaBeanClickItemFishing(AreaBean _str, int _pk) {
        return _str.clickItemFishing(_pk);
    }

    public static String callAreaBeanClickItemId(int _area, int _pk) {
        AreaBean bean_ = displayArea(_area);
        callAreaBeanClickItem(bean_,_pk);
        return getValItemId(bean_);
    }

    public static String callAreaBeanClickItemFishingId(int _area, int _pk) {
        AreaBean bean_ = displayArea(_area);
        callAreaBeanClickItemFishing(bean_,_pk);
        return getValItemId(bean_);
    }

    public static String callAreaBeanClickMoveId(int _area, int _pk, int _move) {
        AreaBean bean_ = displayArea(_area);
        bean_.clickMove(_pk,_move);
        return getValMoveId(bean_);
    }

    public static String callAreaBeanClickMoveFishingId(int _area, int _pk, int _move) {
        AreaBean bean_ = displayArea(_area);
        bean_.clickMoveFishing(_pk,_move);
        return getValMoveId(bean_);
    }

    public static String callAreaBeanClickNameId(int _area, int _pk) {
        AreaBean bean_ = displayArea(_area);
        bean_.clickName(_pk);
        return getValPkId(bean_);
    }

    public static String callAreaBeanClickNameFishingId(int _area, int _pk) {
        AreaBean bean_ = displayArea(_area);
        bean_.clickNameFishing(_pk);
        return getValPkId(bean_);
    }

    public static String callAreaBeanGetAbility(int _area, int _pk) {
        return displayArea(_area).getAbility(_pk);
    }

    public static String callAreaBeanGetAbilityFishing(int _area, int _pk) {
        return displayArea(_area).getAbilityFishing(_pk);
    }

    public static String callAreaBeanGetGender(int _area, int _pk) {
        return displayArea(_area).getGender(_pk);
    }

    public static String callAreaBeanGetGenderFishing(int _area, int _pk) {
        return displayArea(_area).getGenderFishing(_pk);
    }

    public static int[][] callAreaBeanGetImage(int _area, int _pk) {
        return displayArea(_area).getImage(_pk);
    }

    public static int[][] callAreaBeanGetImageFishing(int _area, int _pk) {
        return displayArea(_area).getImageFishing(_pk);
    }

    public static String callAreaBeanGetItem(int _area, int _pk) {
        return displayArea(_area).getItem(_pk);
    }

    public static String callAreaBeanGetItemFishing(int _area, int _pk) {
        return displayArea(_area).getItemFishing(_pk);
    }

    public static String callAreaBeanGetMove(int _area, int _pk, int _move) {
        return displayArea(_area).getMove(_pk,_move);
    }

    public static String callAreaBeanGetMoveFishing(int _area, int _pk, int _move) {
        return displayArea(_area).getMoveFishing(_pk,_move);
    }

    public static CustList<TranslatedKey> callAreaBeanGetMovesAtLevel(int _area, int _pk) {
        return displayArea(_area).getMovesAtLevel(_pk);
    }

    public static CustList<TranslatedKey> callAreaBeanGetMovesAtLevelFishing(int _area, int _pk) {
        return displayArea(_area).getMovesAtLevelFishing(_pk);
    }

    public static String callAreaBeanGetName(int _area, int _pk) {
        return displayArea(_area).getName(_pk);
    }

    public static String callAreaBeanGetNameFishing(int _area, int _pk) {
        return displayArea(_area).getNameFishing(_pk);
    }
    public static AreaBean displayArea(int _area){
        PkData pk_ = pkDataByFacade(db());
        StringMap<BeanRenderWithAppName> all_ = beanToMap(pk_);
        return transitArea(_area,pk_,all_);
    }

    private static AreaBean transitArea(int _no,PkData _pk, StringMap<BeanRenderWithAppName> _all) {
        MapLevelBean bean_ = transitLevelZero(2, _pk, _all);
        AreaBean area_ = (AreaBean) _all.getVal(InitDbMap.BEAN_AREA);
        transit(_pk,new MapLevelBeanClickArea(bean_,_no),bean_,area_);
        return area_;
    }
}
