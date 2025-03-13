package aiki.beans.map;

import aiki.beans.*;
import aiki.beans.map.elements.*;
import aiki.map.levels.AbsAreaApparition;
import aiki.map.pokemon.WildPk;
import code.bean.nat.*;
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
        return ( (AreaBean) ((PokemonBeanStruct)displayArea(_area)).getInstance()).getArea();
    }

    public static String callAreaBeanClickAbilityId(int _area, int _pk) {
        NaSt bean_ = displayArea(_area);
        ((AreaBean) ((PokemonBeanStruct)bean_).getInstance()).clickAbility(_pk);
        return getValAbilityId(bean_);
    }

    public static String callAreaBeanClickAbilityFishingId(int _area, int _pk) {
        NaSt bean_ = displayArea(_area);
        ((AreaBean) ((PokemonBeanStruct)bean_).getInstance()).clickAbilityFishing(_pk);
        return getValAbilityId(bean_);
    }

    public static String callAreaBeanClickItem(int _area, int _pk) {
        return callAreaBeanClickItem(displayArea(_area),_pk);
    }

    public static String callAreaBeanClickItemFishing(int _area, int _pk) {
        return callAreaBeanClickItemFishing(displayArea(_area),_pk);
    }

    public static String callAreaBeanClickItem(NaSt _str, int _pk) {
        return ( (AreaBean) ((PokemonBeanStruct)_str).getInstance()).clickItem(_pk);
    }

    public static String callAreaBeanClickItemFishing(NaSt _str, int _pk) {
        return ( (AreaBean) ((PokemonBeanStruct)_str).getInstance()).clickItemFishing(_pk);
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
        ((AreaBean) ((PokemonBeanStruct)bean_).getInstance()).clickMove(_pk,_move);
        return getValMoveId(bean_);
    }

    public static String callAreaBeanClickMoveFishingId(int _area, int _pk, int _move) {
        NaSt bean_ = displayArea(_area);
        ((AreaBean) ((PokemonBeanStruct)bean_).getInstance()).clickMoveFishing(_pk,_move);
        return getValMoveId(bean_);
    }

    public static String callAreaBeanClickNameId(int _area, int _pk) {
        NaSt bean_ = displayArea(_area);
        ((AreaBean) ((PokemonBeanStruct)bean_).getInstance()).clickName(_pk);
        return getValPkId(bean_);
    }

    public static String callAreaBeanClickNameFishingId(int _area, int _pk) {
        NaSt bean_ = displayArea(_area);
        ((AreaBean) ((PokemonBeanStruct)bean_).getInstance()).clickNameFishing(_pk);
        return getValPkId(bean_);
    }

    public static String callAreaBeanGetAbility(int _area, int _pk) {
        return ( (AreaBean) ((PokemonBeanStruct)displayArea(_area)).getInstance()).getAbility(_pk);
    }

    public static String callAreaBeanGetAbilityFishing(int _area, int _pk) {
        return ( (AreaBean) ((PokemonBeanStruct)displayArea(_area)).getInstance()).getAbilityFishing(_pk);
    }

    public static String callAreaBeanGetGender(int _area, int _pk) {
        return ( (AreaBean) ((PokemonBeanStruct)displayArea(_area)).getInstance()).getGender(_pk);
    }

    public static String callAreaBeanGetGenderFishing(int _area, int _pk) {
        return ( (AreaBean) ((PokemonBeanStruct)displayArea(_area)).getInstance()).getGenderFishing(_pk);
    }

    public static int[][] callAreaBeanGetImage(int _area, int _pk) {
        return ( (AreaBean) ((PokemonBeanStruct)displayArea(_area)).getInstance()).getImage(_pk);
    }

    public static int[][] callAreaBeanGetImageFishing(int _area, int _pk) {
        return ( (AreaBean) ((PokemonBeanStruct)displayArea(_area)).getInstance()).getImageFishing(_pk);
    }

    public static String callAreaBeanGetItem(int _area, int _pk) {
        return ( (AreaBean) ((PokemonBeanStruct)displayArea(_area)).getInstance()).getItem(_pk);
    }

    public static String callAreaBeanGetItemFishing(int _area, int _pk) {
        return ( (AreaBean) ((PokemonBeanStruct)displayArea(_area)).getInstance()).getItemFishing(_pk);
    }

    public static String callAreaBeanGetMove(int _area, int _pk, int _move) {
        return ( (AreaBean) ((PokemonBeanStruct)displayArea(_area)).getInstance()).getMove(_pk,_move);
    }

    public static String callAreaBeanGetMoveFishing(int _area, int _pk, int _move) {
        return ( (AreaBean) ((PokemonBeanStruct)displayArea(_area)).getInstance()).getMoveFishing(_pk,_move);
    }

    public static CustList<TranslatedKey> callAreaBeanGetMovesAtLevel(int _area, int _pk) {
        return (( (AreaBean) ((PokemonBeanStruct)displayArea(_area)).getInstance()).getMovesAtLevel(_pk));
    }

    public static CustList<TranslatedKey> callAreaBeanGetMovesAtLevelFishing(int _area, int _pk) {
        return (( (AreaBean) ((PokemonBeanStruct)displayArea(_area)).getInstance()).getMovesAtLevelFishing(_pk));
    }

    public static String callAreaBeanGetName(int _area, int _pk) {
        return ( (AreaBean) ((PokemonBeanStruct)displayArea(_area)).getInstance()).getName(_pk);
    }

    public static String callAreaBeanGetNameFishing(int _area, int _pk) {
        return ( (AreaBean) ((PokemonBeanStruct)displayArea(_area)).getInstance()).getNameFishing(_pk);
    }
    public static NaSt displayArea(int _area){
        PkData pk_ = pkDataByFacade(db());
        StringMap<NaSt> all_ = beanToMap(pk_);
        return transitArea(_area,pk_,all_);
    }

    private static NaSt transitArea(int _no,PkData _pk, StringMap<NaSt> _all) {
        NaSt bean_ = transitLevelZero(2, _pk, _all);
        NaSt area_ = _all.getVal(InitDbMap.BEAN_AREA);
        transit(_pk,new MapLevelBeanClickArea(((AbsLevelBean) ((PokemonBeanStruct)bean_).getBean()),_no),bean_,area_);
        return area_;
    }
}
