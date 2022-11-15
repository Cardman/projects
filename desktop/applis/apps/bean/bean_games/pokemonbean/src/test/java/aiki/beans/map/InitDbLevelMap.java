package aiki.beans.map;

import aiki.beans.BeanPokemonCommonTs;
import aiki.util.Coords;
import code.expressionlanguage.structs.Struct;

public abstract class InitDbLevelMap extends InitDbMap {

    public static Struct callMapLevelBeanClickDirectedLink(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanClickDirectedLink(),_str,_args);
    }

    public static Struct callMapLevelBeanClickLink(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanClickLink(),_str,_args);
    }

    public static Struct callMapLevelBeanClickTile(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanClickTile(),_str,_args);
    }

    public static Struct callMapLevelBeanClickTileOnMap(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanClickTileOnMap(),_str,_args);
    }

    public static String callMapLevelBeanClickForeGroundTwice(int _place, int _tile, int _second) {
        Struct bean_ = dispMapLevelZero(_place);
        callMapLevelBeanClickForeGround(bean_,_tile);
        beforeDisplaying(bean_);
        return callMapLevelBeanClickForeGround(bean_,_second);
    }
    public static String callMapLevelBeanClickForeGround(int _place, int _tile) {
        return callMapLevelBeanClickForeGround(dispMapLevelZero(_place),_tile);
    }

    public static Coords callMapLevelBeanClickForeGroundId(int _place, int _tile) {
        Struct bean_ = dispMapLevelZero(_place);
        callMapLevelBeanClickForeGround(bean_,_tile);
        return getValPlaceLevelId(bean_);
    }

    public static String callMapLevelBeanClickForeGround(int _place, int _level, int _tile) {
        return callMapLevelBeanClickForeGround(dispMapLevel(_place, _level),_tile);
    }

    public static Coords callMapLevelBeanClickForeGroundId(int _place, int _level, int _tile) {
        Struct bean_ = dispMapLevel(_place,_level);
        callMapLevelBeanClickForeGround(bean_,_tile);
        return getValPlaceLevelId(bean_);
    }

    public static String callMapLevelBeanClickForeGround(Struct _str, int _tile) {
        return navigateData(new MapLevelBeanClickForeGround(),_str,_tile);
    }

    public static Struct callMapLevelBeanDirsGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanDirsGet(),_str,_args);
    }

    public static Struct callMapLevelBeanGetMapWidth(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanGetMapWidth(),_str,_args);
    }

    public static Struct callMapLevelBeanGymGet(int _place, int _tile) {
        Struct bean_ = dispMapLevelZero(_place);
        callMapLevelBeanClickForeGround(bean_,_tile);
        beforeDisplaying(bean_);
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanGymGet(),bean_);
    }

    public static Struct callMapLevelBeanIsAccessibleByBeatingSomeTrainers(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanIsAccessibleByBeatingSomeTrainers(),_str,_args);
    }

    public static Struct callMapLevelBeanIsDown(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanIsDown(),_str,_args);
    }

    public static Struct callMapLevelBeanIsFirstRow(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanIsFirstRow(),_str,_args);
    }

    public static Struct callMapLevelBeanIsFossile(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanIsFossile(),_str,_args);
    }

    public static Struct callMapLevelBeanIsHealer(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanIsHealer(),_str,_args);
    }

    public static Struct callMapLevelBeanIsHost(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanIsHost(),_str,_args);
    }

    public static Struct callMapLevelBeanIsLeft(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanIsLeft(),_str,_args);
    }

    public static Struct callMapLevelBeanIsMoveTutors(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanIsMoveTutors(),_str,_args);
    }

    public static Struct callMapLevelBeanIsRight(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanIsRight(),_str,_args);
    }

    public static Struct callMapLevelBeanIsStorage(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanIsStorage(),_str,_args);
    }

    public static Struct callMapLevelBeanIsUp(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanIsUp(),_str,_args);
    }

    public static Struct callMapLevelBeanLevelIndexGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanLevelIndexGet(),_str,_args);
    }

    public static Struct callMapLevelBeanOutsideGet(int _place) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanOutsideGet(),dispMapLevelZero(_place));
    }

    public static Struct callMapLevelBeanOutsideGet(int _place, int _tile) {
        Struct bean_ = dispMapLevelZero(_place);
        callMapLevelBeanClickForeGround(bean_,_tile);
        beforeDisplaying(bean_);
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanOutsideGet(),bean_);
    }

    public static Struct callMapLevelBeanPlaceNameGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanPlaceNameGet(),_str,_args);
    }

    public static Struct callMapLevelBeanPokemonCenterGet(int _place, int _tile) {
        Struct bean_ = dispMapLevelZero(_place);
        callMapLevelBeanClickForeGround(bean_,_tile);
        beforeDisplaying(bean_);
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanPokemonCenterGet(),bean_);
    }

    public static Struct callMapLevelBeanPossibleMultiLayerGet(int _place) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanPossibleMultiLayerGet(),dispMapLevelZero(_place));
    }

    public static Struct callMapLevelBeanPossibleMultiLayerGet(int _place, int _level) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanPossibleMultiLayerGet(),dispMapLevel(_place, _level));
    }

    public static Struct callMapLevelBeanProponeLinkGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanProponeLinkGet(),_str,_args);
    }

    public static Struct callMapLevelBeanProponeTileGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanProponeTileGet(),_str,_args);
    }

    public static Struct callMapLevelBeanRoadGet(int _place, int _level) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanRoadGet(),dispMapLevel(_place, _level));
    }

    public static Struct callMapLevelBeanRoadGet(int _place) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanRoadGet(),dispMapLevelZero(_place));
    }

    public static Struct callMapLevelBeanSeeArea(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanSeeArea(),_str,_args);
    }

    public static Struct callMapLevelBeanSeeAreaGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanSeeAreaGet(),_str,_args);
    }

    public static Struct callMapLevelBeanTilesGet(int _place, int _level) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanTilesGet(),dispMapLevel(_place, _level));
    }

    public static Struct callMapLevelBeanTilesGet(int _place) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanTilesGet(),dispMapLevelZero(_place));
    }

    public static Struct callMapLevelBeanWithoutTitle(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanWithoutTitle(),_str,_args);
    }

}
