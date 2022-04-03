package aiki.beans.map;

import aiki.beans.AikiBeansStd;
import aiki.beans.PokemonStandards;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.CustList;
public final class AikiBeansMapStd{
    public static final String TYPE_MAP_BEAN = "aiki.beans.map.MapBean";
    public static final String TYPE_MAP_LEVEL_BEAN = "aiki.beans.map.MapLevelBean";
    private static final String GET_MAP_WIDTH = "getMapWidth";
    private static final String IS_FIRST_ROW = "isFirstRow";
    private static final String WITHOUT_TITLE = "withoutTitle";
    private static final String IS_ACCESSIBLE_BY_BEATING_SOME_TRAINERS = "isAccessibleByBeatingSomeTrainers";
    private static final String CLICK_TILE_ON_MAP = "clickTileOnMap";
    private static final String IS_STORAGE = "isStorage";
    private static final String IS_HEALER = "isHealer";
    private static final String IS_HOST = "isHost";
    private static final String IS_FOSSILE = "isFossile";
    private static final String IS_MOVE_TUTORS = "isMoveTutors";
    private static final String CLICK_TILE = "clickTile";
    private static final String CLICK_LINK = "clickLink";
    private static final String SEE_AREA = "seeArea";
    private static final String IS_UP = "isUp";
    private static final String CLICK_DIRECTED_LINK = "clickDirectedLink";
    private static final String IS_DOWN = "isDown";
    private static final String IS_LEFT = "isLeft";
    private static final String IS_RIGHT = "isRight";
    private static final String IS_MULTI_LAYER = "isMultiLayer";
    private static final String LAYERS = "layers";
    private static final String CLICK_LEVEL = "clickLevel";
    private static final String CLICK_LEVEL_ZERO = "clickLevelZero";
    private static final String POSSIBLE_MULTI_LAYER = "possibleMultiLayer";
    private static final String PLACE_NAME = "placeName";
    private static final String LEVEL_INDEX = "levelIndex";
    private static final String OUTSIDE = "outside";
    private static final String ROAD = "road";
    private static final String GYM = "gym";
    private static final String POKEMON_CENTER = "pokemonCenter";
    private static final String TILES = "tiles";
    private static final String PROPONE_TILE = "proponeTile";
    private static final String PROPONE_LINK = "proponeLink";
    private static final String DIRS = "dirs";
    private static final String PLACES = "places";
    private AikiBeansMapStd(){}
    public static void build(PokemonStandards _std) {
        buildMapBean(_std);
        buildMapLevelBean(_std);
    }
    private static void buildMapBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_MAP_BEAN, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        fields_.add(new StandardField(PLACES, BeanNatCommonLgNames.TYPE_LIST,false,false,new MapBeanPlacesGet(),null));
        methods_.add( new SpecNatMethod(IS_MULTI_LAYER,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MapBeanIsMultiLayer()));
        methods_.add( new SpecNatMethod(LAYERS, BeanNatCommonLgNames.TYPE_LIST, false, MethodModifier.NORMAL,new MapBeanLayers()));
        methods_.add( new SpecNatMethod(CLICK_LEVEL,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MapBeanClickLevel()));
        methods_.add( new SpecNatMethod(CLICK_LEVEL_ZERO,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MapBeanClickLevelZero()));
        _std.getStds().addEntry(TYPE_MAP_BEAN, type_);
    }
    private static void buildMapLevelBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_MAP_LEVEL_BEAN, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        fields_.add(new StandardField(POSSIBLE_MULTI_LAYER,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new MapLevelBeanPossibleMultiLayerGet(),null));
        fields_.add(new StandardField(PLACE_NAME,BeanNatCommonLgNames.STRING,false,false,new MapLevelBeanPlaceNameGet(),null));
        fields_.add(new StandardField(LEVEL_INDEX, BeanNatCommonLgNames.PRIM_INTEGER,false,false,new MapLevelBeanLevelIndexGet(),null));
        fields_.add(new StandardField(OUTSIDE,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new MapLevelBeanOutsideGet(),null));
        fields_.add(new StandardField(ROAD,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new MapLevelBeanRoadGet(),null));
        fields_.add(new StandardField(GYM,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new MapLevelBeanGymGet(),null));
        fields_.add(new StandardField(POKEMON_CENTER,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new MapLevelBeanPokemonCenterGet(),null));
        fields_.add(new StandardField(TILES, BeanNatCommonLgNames.TYPE_MAP,false,false,new MapLevelBeanTilesGet(),null));
        fields_.add(new StandardField(PROPONE_TILE,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new MapLevelBeanProponeTileGet(),null));
        fields_.add(new StandardField(PROPONE_LINK,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new MapLevelBeanProponeLinkGet(),null));
        fields_.add(new StandardField(SEE_AREA,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new MapLevelBeanSeeAreaGet(),null));
        fields_.add(new StandardField(DIRS, BeanNatCommonLgNames.TYPE_MAP,false,false,new MapLevelBeanDirsGet(),null));
        methods_.add( new SpecNatMethod(GET_MAP_WIDTH, BeanNatCommonLgNames.PRIM_INTEGER, false, MethodModifier.NORMAL,new MapLevelBeanGetMapWidth()));
        methods_.add( new SpecNatMethod(IS_FIRST_ROW,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MapLevelBeanIsFirstRow()));
        methods_.add( new SpecNatMethod(WITHOUT_TITLE,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MapLevelBeanWithoutTitle()));
        methods_.add( new SpecNatMethod(IS_ACCESSIBLE_BY_BEATING_SOME_TRAINERS,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MapLevelBeanIsAccessibleByBeatingSomeTrainers()));
        methods_.add( new SpecNatMethod(CLICK_TILE_ON_MAP,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MapLevelBeanClickTileOnMap()));
        methods_.add( new SpecNatMethod(IS_STORAGE,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MapLevelBeanIsStorage()));
        methods_.add( new SpecNatMethod(IS_HEALER,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MapLevelBeanIsHealer()));
        methods_.add( new SpecNatMethod(IS_HOST,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MapLevelBeanIsHost()));
        methods_.add( new SpecNatMethod(IS_FOSSILE,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MapLevelBeanIsFossile()));
        methods_.add( new SpecNatMethod(IS_MOVE_TUTORS,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MapLevelBeanIsMoveTutors()));
        methods_.add( new SpecNatMethod(CLICK_TILE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MapLevelBeanClickTile()));
        methods_.add( new SpecNatMethod(CLICK_LINK,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MapLevelBeanClickLink()));
        methods_.add( new SpecNatMethod(SEE_AREA,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MapLevelBeanSeeArea()));
        methods_.add( new SpecNatMethod(IS_UP,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MapLevelBeanIsUp()));
        methods_.add( new SpecNatMethod(CLICK_DIRECTED_LINK,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MapLevelBeanClickDirectedLink()));
        methods_.add( new SpecNatMethod(IS_DOWN,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MapLevelBeanIsDown()));
        methods_.add( new SpecNatMethod(IS_LEFT,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MapLevelBeanIsLeft()));
        methods_.add( new SpecNatMethod(IS_RIGHT,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MapLevelBeanIsRight()));
        _std.getStds().addEntry(TYPE_MAP_LEVEL_BEAN, type_);
    }
}
