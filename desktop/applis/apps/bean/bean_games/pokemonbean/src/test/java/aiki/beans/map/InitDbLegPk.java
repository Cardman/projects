package aiki.beans.map;

import aiki.beans.*;
import aiki.beans.map.elements.*;
import aiki.facade.FacadeGame;
import aiki.map.pokemon.Pokemon;
import code.util.*;

public abstract class InitDbLegPk extends InitDbLevelMap{

    public static String callLegendaryPokemonBeanClickAbilityId(int _pk) {
        LegendaryPokemonBean bean_ = displayPk(_pk);
        bean_.clickAbility();
        return getValAbilityId(bean_);
    }

    public static String callLegendaryPokemonBeanClickItem(int _pk) {
        return callLegendaryPokemonBeanClickItem(displayPk(_pk));
    }

    public static String callLegendaryPokemonBeanClickItem(LegendaryPokemonBean _str) {
        return _str.clickItem();
    }

    public static String callLegendaryPokemonBeanClickItemId(int _pk) {
        LegendaryPokemonBean bean_ = displayPk(_pk);
        callLegendaryPokemonBeanClickItem(bean_);
        return getValItemId(bean_);
    }

    public static String callLegendaryPokemonBeanClickMoveId(int _pk, int _move) {
        LegendaryPokemonBean bean_ = displayPk(_pk);
        bean_.clickMove(_move);
        return getValMoveId(bean_);
    }

    public static String callLegendaryPokemonBeanClickNameId(int _pk) {
        LegendaryPokemonBean bean_ = displayPk(_pk);
        bean_.clickName();
        return getValPkId(bean_);
    }

    public static String callLegendaryPokemonBeanGetAbility(int _pk) {
        return displayPk(_pk).getAbility();
    }

    public static String callLegendaryPokemonBeanGetGender(int _pk) {
        return displayPk(_pk).getGender();
    }

    public static int[][] callLegendaryPokemonBeanGetImage(int _pk) {
        return displayPk(_pk).getImage();
    }

    public static String callLegendaryPokemonBeanGetItem(int _pk) {
        return displayPk(_pk).getItem();
    }

    public static long callLegendaryPokemonBeanGetLevel(int _pk) {
        return displayPk(_pk).getLevel();
    }

    public static String callLegendaryPokemonBeanGetMove(int _pk, int _move) {
        return displayPk(_pk).getMove(_move);
    }

    public static CustList<TranslatedKey> callLegendaryPokemonBeanGetMovesAtLevel(int _pk) {
        return displayPk(_pk).getMovesAtLevel();
    }

    public static String callLegendaryPokemonBeanGetName(int _pk) {
        return displayPk(_pk).getName();
    }

    public static Pokemon callLegendaryPokemonBeanPokemonGet(int _pk) {
        return displayPk(_pk).getPokemon();
    }
    public static LegendaryPokemonBean displayPk(int _pk){
        FacadeGame pk_ = pkDataByFacade(db());
        StringMap<BeanRenderWithAppName> all_ = beanToMap(pk_);
        return transitPk(mapInts().get(_pk),pk_,all_);
    }

    private static LegendaryPokemonBean transitPk(int _tile,FacadeGame _pk, StringMap<BeanRenderWithAppName> _all) {
        MapLevelBean bean_ = transitLevel(3,0, _pk, _all);
        LegendaryPokemonBean area_ = (LegendaryPokemonBean) _all.getVal(InitDbMap.BEAN_LEG_PK);
        transit(_pk,new MapLevelBeanClickTileOnMap(bean_,_tile),bean_,area_);
        return area_;
    }
    private static Ints mapInts() {
        return Ints.newList(18,12);
    }
}
