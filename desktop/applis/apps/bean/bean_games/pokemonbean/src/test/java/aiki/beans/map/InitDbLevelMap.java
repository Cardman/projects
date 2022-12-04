package aiki.beans.map;

import aiki.beans.BeanPokemonCommonTs;
import aiki.util.Coords;
import code.bean.nat.*;

public abstract class InitDbLevelMap extends InitDbMap {

//    public static Struct callMapLevelBeanClickDirectedLink(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new MapLevelBeanClickDirectedLink(),_str,_args);
//    }

//    public static Struct callMapLevelBeanClickLink(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new MapLevelBeanClickLink(),_str,_args);
//    }

//    public static Struct callMapLevelBeanClickTile(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new MapLevelBeanClickTile(),_str,_args);
//    }
    public static String callMapLevelBeanClickTileOnMap(int _place, int _tile) {
        return callMapLevelBeanClickTileOnMap(dispMapLevelZero(_place),_tile);
    }

//    public static String callMapLevelBeanClickTileOnMap(Struct _str, long... _args) {
//        return navigateData(new MapLevelBeanClickTileOnMap(),_str,_args);
//    }

    public static String callMapLevelBeanClickTileOnMap(int _place, int _level, int _tile) {
        return callMapLevelBeanClickTileOnMap(dispMapLevel(_place, _level),_tile);
    }

    public static String callMapLevelBeanClickTileOnMapMv(int _place, int _level, int _tile) {
        NaSt bean_ = dispMapLevel(_place, _level);
        callMapLevelBeanClickTileOnMap(bean_,_tile);
        return getValMoveId(bean_);
    }

    public static String callMapLevelBeanClickTileOnMapIt(int _place, int _level, int _tile) {
        NaSt bean_ = dispMapLevel(_place, _level);
        callMapLevelBeanClickTileOnMap(bean_,_tile);
        return getValItemId(bean_);
    }
//    public static String callMapLevelBeanClickForeGroundTwice(int _place, int _tile, int _second) {
//        Struct bean_ = dispMapLevelZero(_place);
//        callMapLevelBeanClickForeGround(bean_,_tile);
//        beforeDisplaying(bean_);
//        return callMapLevelBeanClickForeGround(bean_,_second);
//    }
//    public static String callMapLevelBeanClickForeGround(int _place, int _tile) {
//        return callMapLevelBeanClickForeGround(dispMapLevelZero(_place),_tile);
//    }
//
//    public static Coords callMapLevelBeanClickForeGroundId(int _place, int _tile) {
//        Struct bean_ = dispMapLevelZero(_place);
//        callMapLevelBeanClickForeGround(bean_,_tile);
//        return getValPlaceLevelId(bean_);
//    }
//
//    public static String callMapLevelBeanClickForeGround(int _place, int _level, int _tile) {
//        return callMapLevelBeanClickForeGround(dispMapLevel(_place, _level),_tile);
//    }
//
//    public static Coords callMapLevelBeanClickForeGroundId(int _place, int _level, int _tile) {
//        Struct bean_ = dispMapLevel(_place,_level);
//        callMapLevelBeanClickForeGround(bean_,_tile);
//        return getValPlaceLevelId(bean_);
//    }
    public static String callMapLevelBeanClickTileOnMapTwice(int _place, int _tile, int _second) {
        NaSt bean_ = dispMapLevelZero(_place);
        callMapLevelBeanClickTileOnMap(bean_,_tile);
        beforeDisplaying(bean_);
        return callMapLevelBeanClickTileOnMap(bean_,_second);
    }

    public static Coords callMapLevelBeanClickTileOnMapId(int _place, int _tile) {
        NaSt bean_ = dispMapLevelZero(_place);
        callMapLevelBeanClickTileOnMap(bean_,_tile);
        return getValPlaceLevelId(bean_);
    }

    public static Coords callMapLevelBeanClickTileOnMapId(int _place, int _level, int _tile) {
        NaSt bean_ = dispMapLevel(_place,_level);
        callMapLevelBeanClickTileOnMap(bean_,_tile);
        return getValPlaceLevelId(bean_);
    }
    public static String callMapLevelBeanClickTileOnMap(NaSt _str, int _tile) {
        return navigateData(new MapLevelBeanClickTileOnMap(),_str,_tile);
    }

    public static NaSt callMapLevelBeanClickTileOnMapStruct(NaSt _str, int _tile) {
        return callLongs(new MapLevelBeanClickTileOnMap(),_str,_tile);
    }
//    public static String callMapLevelBeanClickForeGround(Struct _str, int _tile) {
//        return navigateData(new MapLevelBeanClickTileOnMap(),_str,_tile);
//    }

//    public static Struct callMapLevelBeanDirsGet(int _place, int _tile) {
//        Struct bean_ = dispMapLevelZero(_place);
//        callMapLevelBeanClickTileOnMap(bean_,_tile);
//        beforeDisplaying(bean_);
//        return BeanPokemonCommonTs.callLongs(new MapLevelBeanDirsGet(),bean_);
//    }

    public static NaSt callMapLevelBeanGetMapWidth(int _place) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanGetMapWidth(),dispMapLevelZero(_place));
    }

    public static NaSt callMapLevelBeanGymGet(int _place, int _tile) {
        NaSt bean_ = dispMapLevelZero(_place);
        callMapLevelBeanClickTileOnMap(bean_,_tile);
        beforeDisplaying(bean_);
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanGymGet(),bean_);
    }

    public static NaSt callMapLevelBeanIsAccessibleByBeatingSomeTrainers(int _place, int _level, int _tile) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanIsAccessibleByBeatingSomeTrainers(),dispMapLevel(_place, _level),_tile);
    }

//    public static Struct callMapLevelBeanIsDown(int _place, int _tile, int _second) {
//        Struct bean_ = dispMapLevelZero(_place);
//        callMapLevelBeanClickTileOnMap(bean_,_tile);
//        beforeDisplaying(bean_);
//        return BeanPokemonCommonTs.callLongs(new MapLevelBeanIsDown(),bean_,_second);
//    }

//    public static Struct callMapLevelBeanIsFirstRow(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new MapLevelBeanIsFirstRow(),_str,_args);
//    }

    public static NaSt callMapLevelBeanIsFossile(int _place, int _tile, int _cell) {
        NaSt bean_ = dispMapLevelZero(_place);
        callMapLevelBeanClickTileOnMap(bean_,_tile);
        beforeDisplaying(bean_);
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanIsFossile(),bean_,_cell);
    }

    public static NaSt callMapLevelBeanIsHealer(int _place, int _tile, int _cell) {
        NaSt bean_ = dispMapLevelZero(_place);
        callMapLevelBeanClickTileOnMap(bean_,_tile);
        beforeDisplaying(bean_);
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanIsHealer(),bean_,_cell);
    }

    public static NaSt callMapLevelBeanIsHost(int _place, int _tile, int _cell) {
        NaSt bean_ = dispMapLevelZero(_place);
        callMapLevelBeanClickTileOnMap(bean_,_tile);
        beforeDisplaying(bean_);
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanIsHost(),bean_,_cell);
    }

//    public static Struct callMapLevelBeanIsLeft(int _place, int _tile, int _second) {
//        Struct bean_ = dispMapLevelZero(_place);
//        callMapLevelBeanClickTileOnMap(bean_,_tile);
//        beforeDisplaying(bean_);
//        return BeanPokemonCommonTs.callLongs(new MapLevelBeanIsLeft(),bean_,_second);
//    }

    public static NaSt callMapLevelBeanIsMoveTutors(int _place, int _tile, int _cell) {
        NaSt bean_ = dispMapLevelZero(_place);
        callMapLevelBeanClickTileOnMap(bean_,_tile);
        beforeDisplaying(bean_);
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanIsMoveTutors(),bean_,_cell);
    }

//    public static Struct callMapLevelBeanIsRight(int _place, int _tile, int _second) {
//        Struct bean_ = dispMapLevelZero(_place);
//        callMapLevelBeanClickTileOnMap(bean_,_tile);
//        beforeDisplaying(bean_);
//        return BeanPokemonCommonTs.callLongs(new MapLevelBeanIsRight(),bean_,_second);
//    }

    public static NaSt callMapLevelBeanIsStorage(int _place, int _tile, int _cell) {
        NaSt bean_ = dispMapLevelZero(_place);
        callMapLevelBeanClickTileOnMap(bean_,_tile);
        beforeDisplaying(bean_);
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanIsStorage(),bean_,_cell);
    }

    public static NaSt callMapLevelBeanIsStorage(int _place, int _cell) {
        NaSt bean_ = dispMapLevelZero(_place);
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanIsStorage(),bean_,_cell);
    }

//    public static Struct callMapLevelBeanIsUp(int _place, int _tile, int _second) {
//        Struct bean_ = dispMapLevelZero(_place);
//        callMapLevelBeanClickTileOnMap(bean_,_tile);
//        beforeDisplaying(bean_);
//        return BeanPokemonCommonTs.callLongs(new MapLevelBeanIsUp(),bean_,_second);
//    }

    public static NaSt callMapLevelBeanLevelIndexGet(int _place, int _level) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanLevelIndexGet(),dispMapLevel(_place, _level));
    }

    public static NaSt callMapLevelBeanOutsideGet(int _place) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanOutsideGet(),dispMapLevelZero(_place));
    }

    public static NaSt callMapLevelBeanClickArea(NaSt _str, int _index) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanClickArea(),_str,_index);
    }

    public static NaSt callMapLevelBeanClickArea(int _place, int _index) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanClickArea(),dispMapLevelZero(_place),_index);
    }

    public static String callMapLevelBeanClickAreaOnMap(int _place, int _tile) {
        return navigateData(new MapLevelBeanClickAreaOnMap(),dispMapLevelZero(_place),_tile);
    }

    public static NaSt callMapLevelBeanWhiteTilesGet(int _place) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanWhiteTilesGet(),dispMapLevelZero(_place));
    }

    public static NaSt callMapLevelBeanNeighboursGet(int _place) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanNeighboursGet(),dispMapLevelZero(_place));
    }

    public static String callMapLevelBeanClickNeighbour(int _place, int _index) {
        return navigateData(new MapLevelBeanClickNeighbour(),dispMapLevelZero(_place),_index);
    }
    public static NaSt callMapLevelBeanAreas(int _place) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanAreas(),dispMapLevelZero(_place));
    }
    public static NaSt callMapLevelBeanOutsideGet(int _place, int _tile) {
        NaSt bean_ = dispMapLevelZero(_place);
        callMapLevelBeanClickTileOnMap(bean_,_tile);
        beforeDisplaying(bean_);
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanOutsideGet(),bean_);
    }

    public static NaSt callMapLevelBeanPlaceNameGet(int _place, int _level) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanPlaceNameGet(),dispMapLevel(_place, _level));
    }

    public static NaSt callMapLevelBeanPokemonCenterGet(int _place, int _tile) {
        NaSt bean_ = dispMapLevelZero(_place);
        callMapLevelBeanClickTileOnMap(bean_,_tile);
        beforeDisplaying(bean_);
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanPokemonCenterGet(),bean_);
    }

    public static NaSt callMapLevelBeanPossibleMultiLayerGet(int _place) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanPossibleMultiLayerGet(),dispMapLevelZero(_place));
    }

    public static NaSt callMapLevelBeanPossibleMultiLayerGet(int _place, int _level) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanPossibleMultiLayerGet(),dispMapLevel(_place, _level));
    }

//    public static Struct callMapLevelBeanProponeLinkGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new MapLevelBeanProponeLinkGet(),_str,_args);
//    }
//
//    public static Struct callMapLevelBeanProponeTileGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new MapLevelBeanProponeTileGet(),_str,_args);
//    }

    public static NaSt callMapLevelBeanRoadGet(int _place, int _level) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanRoadGet(),dispMapLevel(_place, _level));
    }

    public static NaSt callMapLevelBeanRoadGet(int _place) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanRoadGet(),dispMapLevelZero(_place));
    }

//    public static Struct callMapLevelBeanSeeArea(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new MapLevelBeanSeeArea(),_str,_args);
//    }
//
//    public static Struct callMapLevelBeanSeeAreaGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new MapLevelBeanSeeAreaGet(),_str,_args);
//    }

    public static NaSt callMapLevelBeanTilesGet(int _place, int _level) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanTilesGet(),dispMapLevel(_place, _level));
    }

    public static NaSt callMapLevelBeanTilesGet(int _place) {
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanTilesGet(),dispMapLevelZero(_place));
    }

    public static NaSt callMapLevelBeanWithoutTitle(int _place, int _tile, int _cell) {
        NaSt bean_ = dispMapLevelZero(_place);
        callMapLevelBeanClickTileOnMap(bean_,_tile);
        beforeDisplaying(bean_);
        return BeanPokemonCommonTs.callLongs(new MapLevelBeanWithoutTitle(),bean_,_cell);
    }

}
