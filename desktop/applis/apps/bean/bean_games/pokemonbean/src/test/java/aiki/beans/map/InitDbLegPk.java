package aiki.beans.map;

import aiki.beans.*;
import aiki.beans.map.elements.*;
import code.bean.nat.*;
import code.util.*;

public abstract class InitDbLegPk extends InitDbLevelMap{

    public static String callLegendaryPokemonBeanClickAbilityId(int _pk) {
        NaSt bean_ = displayPk(_pk);
        ( (LegendaryPokemonBean) ((PokemonBeanStruct)bean_).getInstance()).clickAbility();
        return getValAbilityId(bean_);
    }

    public static String callLegendaryPokemonBeanClickItem(int _pk) {
        return callLegendaryPokemonBeanClickItem(displayPk(_pk));
    }

    public static String callLegendaryPokemonBeanClickItem(NaSt _str) {
        return new NaStSt(( (LegendaryPokemonBean) ((PokemonBeanStruct)_str).getInstance()).clickItem()).getInstance();
    }

    public static String callLegendaryPokemonBeanClickItemId(int _pk) {
        NaSt bean_ = displayPk(_pk);
        callLegendaryPokemonBeanClickItem(bean_);
        return getValItemId(bean_);
    }

    public static String callLegendaryPokemonBeanClickMoveId(int _pk, int _move) {
        NaSt bean_ = displayPk(_pk);
        ( (LegendaryPokemonBean) ((PokemonBeanStruct)bean_).getInstance()).clickMove(_move);
        return getValMoveId(bean_);
    }

    public static String callLegendaryPokemonBeanClickNameId(int _pk) {
        NaSt bean_ = displayPk(_pk);
        ( (LegendaryPokemonBean) ((PokemonBeanStruct)bean_).getInstance()).clickName();
        return getValPkId(bean_);
    }

    public static NaSt callLegendaryPokemonBeanGetAbility(int _pk) {
        return new NaStSt(( (LegendaryPokemonBean) ((PokemonBeanStruct)displayPk(_pk)).getInstance()).getAbility());
    }

    public static NaSt callLegendaryPokemonBeanGetGender(int _pk) {
        return new NaStSt(( (LegendaryPokemonBean) ((PokemonBeanStruct)displayPk(_pk)).getInstance()).getGender());
    }

    public static NaSt callLegendaryPokemonBeanGetImage(int _pk) {
        return new NaImgSt(( (LegendaryPokemonBean) ((PokemonBeanStruct)displayPk(_pk)).getInstance()).getImage());
    }

    public static NaSt callLegendaryPokemonBeanGetItem(int _pk) {
        return new NaStSt(( (LegendaryPokemonBean) ((PokemonBeanStruct)displayPk(_pk)).getInstance()).getItem());
    }

    public static NaSt callLegendaryPokemonBeanGetLevel(int _pk) {
        return new NaNbSt(( (LegendaryPokemonBean) ((PokemonBeanStruct)displayPk(_pk)).getInstance()).getLevel());
    }

    public static NaSt callLegendaryPokemonBeanGetMove(int _pk, int _move) {
        return new NaStSt(( (LegendaryPokemonBean) ((PokemonBeanStruct)displayPk(_pk)).getInstance()).getMove(_move));
    }

    public static NaSt callLegendaryPokemonBeanGetMovesAtLevel(int _pk) {
        return PokemonStandards.getKeys(( (LegendaryPokemonBean) ((PokemonBeanStruct)displayPk(_pk)).getInstance()).getMovesAtLevel());
    }

    public static NaSt callLegendaryPokemonBeanGetName(int _pk) {
        return new NaStSt(( (LegendaryPokemonBean) ((PokemonBeanStruct)displayPk(_pk)).getInstance()).getName());
    }

    public static NaSt callLegendaryPokemonBeanPokemonGet(int _pk) {
        return new PkStruct(( (LegendaryPokemonBean) ((PokemonBeanStruct)displayPk(_pk)).getInstance()).getPokemon());
    }
    public static NaSt displayPk(int _pk){
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToMap(pk_);
        return transitPk(mapInts().get(_pk),pk_,all_);
    }

    private static NaSt transitPk(int _tile,PkData _pk, StringMap<NaSt> _all) {
        NaSt bean_ = transitLevel(3,0, _pk, _all);
        NaSt area_ = _all.getVal(InitDbMap.BEAN_LEG_PK);
        transit(_pk,new MapLevelBeanClickTileOnMap((MapLevelBean)((PokemonBeanStruct)bean_).getBean(),_tile),bean_,area_);
        return area_;
    }
    private static Ints mapInts() {
        return Ints.newList(18,12);
    }
}
