package aiki.beans.map;

import aiki.beans.AikiBeansStd;
import aiki.beans.PokemonStandards;
import aiki.beans.map.elements.*;
import code.bean.nat.*;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.CustList;
public final class AikiBeansMapStd{
    public static final String TYPE_MAP_BEAN = "aiki.beans.map.MapBean";
    public static final String TYPE_MAP_LEVEL_BEAN = "aiki.beans.map.MapLevelBean";
    public static final String WEB_HTML_MAP_LEVEL_HTML="web/html/map/level.html";
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
    private static final String AREAS = "areas";
    private static final String IS_DOWN = "isDown";
    private static final String IS_LEFT = "isLeft";
    private static final String IS_RIGHT = "isRight";
    private static final String IS_MULTI_LAYER = "isMultiLayer";
    private static final String LAYERS = "layers";
    private static final String CLICK_LEVEL = "clickLevel";
    private static final String CLICK_LEVEL_ZERO = "clickLevelZero";
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
        SpecialNatClass type_ = new SpecialNatClass(TYPE_MAP_LEVEL_BEAN, fields_, methods_, AikiBeansStd.TYPE_ABS_LEVEL_BEAN);
        fields_.add(new StandardField(PROPONE_TILE,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new MapLevelBeanProponeTileGet(),null));
        fields_.add(new StandardField(PROPONE_LINK,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new MapLevelBeanProponeLinkGet(),null));
        fields_.add(new StandardField(SEE_AREA,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new MapLevelBeanSeeAreaGet(),null));
        fields_.add(new StandardField(DIRS, BeanNatCommonLgNames.TYPE_MAP,false,false,new MapLevelBeanDirsGet(),null));
        fields_.add(new StandardField(AREAS,BeanNatCommonLgNames.TYPE_LIST, false, false,new MapLevelBeanAreas(),null));
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
        methods_.add( new SpecNatMethod(AikiBeansMapElementsStd.GM, BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new CstNatCaller(AikiBeansMapElementsStd.WEB_HTML_MAP_MAP_HTML)));
        _std.getStds().addEntry(TYPE_MAP_LEVEL_BEAN, type_);
    }
}
