package aiki.beans.map;

import aiki.beans.*;
import aiki.beans.PokemonBeanStruct;
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
        NaSt bean_ = tileOnMap(_place, _tile);
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
        return navigateData(new MapLevelBeanClickTileOnMap((MapLevelBean)((PokemonBeanStruct)_str).getBean(),_tile),_str);
    }

//    public static NaSt callMapLevelBeanClickTileOnMapStruct(NaSt _str, int _tile) {
//        return callLongs(new MapLevelBeanClickTileOnMap(),_str,_tile);
//    }
//    public static String callMapLevelBeanClickForeGround(Struct _str, int _tile) {
//        return navigateData(new MapLevelBeanClickTileOnMap(),_str,_tile);
//    }

//    public static Struct callMapLevelBeanDirsGet(int _place, int _tile) {
//        Struct bean_ = dispMapLevelZero(_place);
//        callMapLevelBeanClickTileOnMap(bean_,_tile);
//        beforeDisplaying(bean_);
//        return BeanPokemonCommonTs.callLongs(new MapLevelBeanDirsGet(),bean_);
//    }

    public static long callMapLevelBeanGetMapWidth(int _place) {
        return ( (AbsLevelBean) ((PokemonBeanStruct)dispMapLevelZero(_place)).getInstance()).getMapWidth();
    }

    public static boolean callMapLevelBeanGymGet(int _place, int _tile) {
        return ( (AbsLevelBean) ((PokemonBeanStruct)tileOnMap(_place, _tile)).getInstance()).getGym();
    }

    public static boolean callMapLevelBeanIsAccessibleByBeatingSomeTrainers(int _place, int _level, int _tile) {
        return ( (MapLevelBean) ((PokemonBeanStruct)dispMapLevel(_place, _level)).getInstance()).isAccessibleByBeatingSomeTrainers(_tile);
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

    public static boolean callMapLevelBeanIsFossile(int _place, int _tile, int _cell) {
        return ( (MapLevelBean) ((PokemonBeanStruct)tileOnMap(_place, _tile)).getInstance()).isFossile(_cell);
    }

    public static boolean callMapLevelBeanIsHealer(int _place, int _tile, int _cell) {
        return ( (MapLevelBean) ((PokemonBeanStruct)tileOnMap(_place, _tile)).getInstance()).isHealer(_cell);
    }

    public static boolean callMapLevelBeanIsHost(int _place, int _tile, int _cell) {
        return ( (MapLevelBean) ((PokemonBeanStruct)tileOnMap(_place, _tile)).getInstance()).isHost(_cell);
    }

//    public static Struct callMapLevelBeanIsLeft(int _place, int _tile, int _second) {
//        Struct bean_ = dispMapLevelZero(_place);
//        callMapLevelBeanClickTileOnMap(bean_,_tile);
//        beforeDisplaying(bean_);
//        return BeanPokemonCommonTs.callLongs(new MapLevelBeanIsLeft(),bean_,_second);
//    }

    public static boolean callMapLevelBeanIsMoveTutors(int _place, int _tile, int _cell) {
        return ( (MapLevelBean) ((PokemonBeanStruct)tileOnMap(_place, _tile)).getInstance()).isMoveTutors(_cell);
    }

//    public static Struct callMapLevelBeanIsRight(int _place, int _tile, int _second) {
//        Struct bean_ = dispMapLevelZero(_place);
//        callMapLevelBeanClickTileOnMap(bean_,_tile);
//        beforeDisplaying(bean_);
//        return BeanPokemonCommonTs.callLongs(new MapLevelBeanIsRight(),bean_,_second);
//    }

    public static boolean callMapLevelBeanIsStorage(int _place, int _tile, int _cell) {
        return ( (MapLevelBean) ((PokemonBeanStruct)tileOnMap(_place, _tile)).getInstance()).isStorage(_cell);
    }

    private static NaSt tileOnMap(int _place, int _tile) {
        NaSt bean_ = dispMapLevelZero(_place);
        callMapLevelBeanClickTileOnMap(bean_, _tile);
        beforeDisplaying(bean_);
        return bean_;
    }

    public static boolean callMapLevelBeanIsStorage(int _place, int _cell) {
        return ( (MapLevelBean) ((PokemonBeanStruct)dispMapLevelZero(_place)).getInstance()).isStorage(_cell);
    }

//    public static Struct callMapLevelBeanIsUp(int _place, int _tile, int _second) {
//        Struct bean_ = dispMapLevelZero(_place);
//        callMapLevelBeanClickTileOnMap(bean_,_tile);
//        beforeDisplaying(bean_);
//        return BeanPokemonCommonTs.callLongs(new MapLevelBeanIsUp(),bean_,_second);
//    }

    public static long callMapLevelBeanLevelIndexGet(int _place, int _level) {
        return ( (AbsLevelBean) ((PokemonBeanStruct)dispMapLevel(_place, _level)).getInstance()).getLevelIndex();
    }

    public static boolean callMapLevelBeanOutsideGet(int _place) {
        return ( (AbsLevelBean) ((PokemonBeanStruct)dispMapLevelZero(_place)).getInstance()).getOutside();
    }

//    public static NaSt callMapLevelBeanClickArea(NaSt _str, int _index) {
//        return BeanPokemonCommonTs.callLongs(new MapLevelBeanClickArea(),_str,_index);
//    }

    public static String callMapLevelBeanClickArea(int _place, int _index) {
        NaSt str_ = dispMapLevelZero(_place);
        return navigateData(new MapLevelBeanClickArea((AbsLevelBean) ((PokemonBeanStruct)str_).getBean(),_index),str_);
    }

    public static String callMapLevelBeanClickAreaOnMap(int _place, int _tile) {
        NaSt level_ = dispMapLevelZero(_place);
        return navigateData(new MapLevelBeanClickAreaOnMap((AbsLevelBean)((PokemonBeanStruct)level_).getBean(),_tile), level_);
    }

    public static NaSt callMapLevelBeanWhiteTilesGet(int _place) {
        return PokemonStandards.getPtStr(( (AbsLevelBean) ((PokemonBeanStruct)dispMapLevelZero(_place)).getInstance()).getWhiteTiles());
    }

    public static NaSt callMapLevelBeanNeighboursGet(int _place) {
        return PokemonStandards.getIntStr(( (AbsLevelBean) ((PokemonBeanStruct)dispMapLevelZero(_place)).getInstance()).getNeighbours());
    }

    public static String callMapLevelBeanClickNeighbour(int _place, int _index) {
        NaSt lev_ = dispMapLevelZero(_place);
        return navigateData(new MapLevelBeanClickNeighbour((AbsLevelBean) ((PokemonBeanStruct)lev_).getBean(),_index), lev_);
    }
    public static int callMapLevelBeanAreas(int _place) {
        return ( (AbsLevelBean) ((PokemonBeanStruct)dispMapLevelZero(_place)).getInstance()).getWildPokemonAreas().size();
    }
    public static boolean callMapLevelBeanOutsideGet(int _place, int _tile) {
        return ( (MapLevelBean) ((PokemonBeanStruct)tileOnMap(_place, _tile)).getInstance()).getOutside();
    }

    public static String callMapLevelBeanPlaceNameGet(int _place, int _level) {
        return ( (AbsLevelBean) ((PokemonBeanStruct)dispMapLevel(_place, _level)).getInstance()).getPlaceName();
    }

    public static boolean callMapLevelBeanPokemonCenterGet(int _place, int _tile) {
        return ( (MapLevelBean) ((PokemonBeanStruct)tileOnMap(_place, _tile)).getInstance()).getPokemonCenter();
    }

    public static boolean callMapLevelBeanPossibleMultiLayerGet(int _place) {
        return ( (AbsLevelBean) ((PokemonBeanStruct)dispMapLevelZero(_place)).getInstance()).getPossibleMultiLayer();
    }

    public static boolean callMapLevelBeanPossibleMultiLayerGet(int _place, int _level) {
        return ( (AbsLevelBean) ((PokemonBeanStruct)dispMapLevel(_place, _level)).getInstance()).getPossibleMultiLayer();
    }

//    public static Struct callMapLevelBeanProponeLinkGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new MapLevelBeanProponeLinkGet(),_str,_args);
//    }
//
//    public static Struct callMapLevelBeanProponeTileGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new MapLevelBeanProponeTileGet(),_str,_args);
//    }

    public static boolean callMapLevelBeanRoadGet(int _place, int _level) {
        return ( (AbsLevelBean) ((PokemonBeanStruct)dispMapLevel(_place, _level)).getInstance()).getRoad();
    }

    public static boolean callMapLevelBeanRoadGet(int _place) {
        return ( (AbsLevelBean) ((PokemonBeanStruct)dispMapLevelZero(_place)).getInstance()).getRoad();
    }

//    public static Struct callMapLevelBeanSeeArea(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new MapLevelBeanSeeArea(),_str,_args);
//    }
//
//    public static Struct callMapLevelBeanSeeAreaGet(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new MapLevelBeanSeeAreaGet(),_str,_args);
//    }

    public static NaSt callMapLevelBeanTilesGet(int _place, int _level) {
        return PokemonStandards.getPtStr(( (AbsLevelBean) ((PokemonBeanStruct)dispMapLevel(_place, _level)).getInstance()).getTiles());
    }

    public static NaSt callMapLevelBeanTilesGet(int _place) {
        return PokemonStandards.getPtStr(( (AbsLevelBean) ((PokemonBeanStruct)dispMapLevelZero(_place)).getInstance()).getTiles());
    }

    public static boolean callMapLevelBeanWithoutTitle(int _place, int _tile, int _cell) {
        return ( (MapLevelBean) ((PokemonBeanStruct)tileOnMap(_place, _tile)).getInstance()).withoutTitle(_cell);
    }

}
