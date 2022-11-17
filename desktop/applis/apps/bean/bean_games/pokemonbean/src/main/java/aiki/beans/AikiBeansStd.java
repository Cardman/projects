package aiki.beans;

import aiki.beans.endround.AikiBeansEndroundStd;
import aiki.beans.map.*;
import aiki.beans.map.elements.*;
import code.bean.nat.*;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.CustList;
public final class AikiBeansStd{
    public static final String WEB_HTML_INDEX_HTML="web/html/index.html";
    public static final String BEAN_WELCOME = "welcome";
    public static final String TYPE_COMMON_BEAN = "aiki.beans.CommonBean";
    public static final String TYPE_WELCOME_BEAN = "aiki.beans.WelcomeBean";
    public static final String TYPE_ABS_LEVEL_BEAN = "aiki.beans.AbsLevelBean";
    private static final String CLICK_POKEDEX = "clickPokedex";
    private static final String CLICK_ITEMS = "clickItems";
    private static final String SEE_ALL_MOVES = "seeAllMoves";
    private static final String SEE_LEARNT_MOVES = "seeLearntMoves";
    private static final String SEE_NOT_LEARNT_MOVES = "seeNotLearntMoves";
    private static final String CLICK_ABILITIES = "clickAbilities";
    private static final String CLICK_STATUS = "clickStatus";
    private static final String CLICK_SIMULATION = "clickSimulation";
    private static final String GET_MAP_WIDTH = "getMapWidth";
    private static final String IS_FIRST_ROW = "isFirstRow";
    private static final String CLICK_AREA = "clickArea";
    private static final String CLICK_AREA_ON_MAP = "clickAreaOnMap";
    private static final String CLICK_NEIGHBOUR = "clickNeighbour";
    private static final String POSSIBLE_MULTI_LAYER = "possibleMultiLayer";
    private static final String PLACE_NAME = "placeName";
    private static final String LEVEL_INDEX = "levelIndex";
    private static final String OUTSIDE = "outside";
    private static final String ROAD = "road";
    private static final String GYM = "gym";
    private static final String POKEMON_CENTER = "pokemonCenter";
    private static final String TILES = "tiles";
    private static final String WHITE_TILES = "whiteTiles";
    private static final String NEIGHBOURS = "neighbours";
    private static final String GO_TO_INDEX = "gi";
    private static final String GO_TO_ENDROUND = "ge";
    private static final String GO_TO_GENERAL = "g";
    private static final String GO_TO_HELPROUND = "h";
    private static final String GO_TO_COMBOS = "c";
    private static final String GO_TO_SOLUTION = "s";
    private static final String GO_TO_LANGS = "l";

    private AikiBeansStd(){}
    public static void build(PokemonStandards _std) {
        buildCommonBean(_std);
        buildWelcomeBean(_std);
        buildAbsBean(_std);
    }
    private static void buildCommonBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_COMMON_BEAN, fields_, methods_, BeanNatCommonLgNames.TYPE_BEAN);
        methods_.add( new SpecNatMethod(GO_TO_INDEX, BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new CstNatCaller(WEB_HTML_INDEX_HTML)));
        methods_.add( new SpecNatMethod(GO_TO_ENDROUND, BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new CstNatCaller(AikiBeansEndroundStd.PAGE_END_ROUND)));
        _std.getStds().addEntry(TYPE_COMMON_BEAN, type_);
    }
    private static void buildWelcomeBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_WELCOME_BEAN, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        methods_.add( new SpecNatMethod(CLICK_POKEDEX, BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new WelcomeBeanClickPokedex()));
        methods_.add( new SpecNatMethod(CLICK_ITEMS,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new WelcomeBeanClickItems()));
        methods_.add( new SpecNatMethod(SEE_ALL_MOVES,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new WelcomeBeanSeeAllMoves()));
        methods_.add( new SpecNatMethod(SEE_LEARNT_MOVES,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new WelcomeBeanSeeLearntMoves()));
        methods_.add( new SpecNatMethod(SEE_NOT_LEARNT_MOVES,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new WelcomeBeanSeeNotLearntMoves()));
        methods_.add( new SpecNatMethod(CLICK_ABILITIES,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new WelcomeBeanClickAbilities()));
        methods_.add( new SpecNatMethod(CLICK_STATUS,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new WelcomeBeanClickStatus()));
        methods_.add( new SpecNatMethod(CLICK_SIMULATION,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new WelcomeBeanClickSimulation()));
        methods_.add( new SpecNatMethod(AikiBeansMapElementsStd.GM, BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new CstNatCaller(AikiBeansMapElementsStd.WEB_HTML_MAP_MAP_HTML)));
        methods_.add( new SpecNatMethod(GO_TO_GENERAL, BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new CstNatCaller("web/html/general/general.html")));
        methods_.add( new SpecNatMethod(GO_TO_HELPROUND, BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new CstNatCaller("web/html/round/helpround.html")));
        methods_.add( new SpecNatMethod(GO_TO_COMBOS, BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new CstNatCaller("web/html/combo/combos.html")));
        methods_.add( new SpecNatMethod(GO_TO_SOLUTION, BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new CstNatCaller("web/html/solution/solution.html")));
        methods_.add( new SpecNatMethod(GO_TO_LANGS, BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new CstNatCaller("web/html/langs/langs.html")));
        _std.getStds().addEntry(TYPE_WELCOME_BEAN, type_);
    }
    private static void buildAbsBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_ABS_LEVEL_BEAN, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        fields_.add(new StandardField(POSSIBLE_MULTI_LAYER,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new MapLevelBeanPossibleMultiLayerGet(),null));
        fields_.add(new StandardField(PLACE_NAME,BeanNatCommonLgNames.STRING,false,false,new MapLevelBeanPlaceNameGet(),null));
        fields_.add(new StandardField(LEVEL_INDEX, BeanNatCommonLgNames.PRIM_INTEGER,false,false,new MapLevelBeanLevelIndexGet(),null));
        fields_.add(new StandardField(OUTSIDE,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new MapLevelBeanOutsideGet(),null));
        fields_.add(new StandardField(ROAD,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new MapLevelBeanRoadGet(),null));
        fields_.add(new StandardField(GYM,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new MapLevelBeanGymGet(),null));
        fields_.add(new StandardField(POKEMON_CENTER,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new MapLevelBeanPokemonCenterGet(),null));
        fields_.add(new StandardField(TILES, BeanNatCommonLgNames.TYPE_MAP,false,false,new MapLevelBeanTilesGet(),null));
        fields_.add(new StandardField(WHITE_TILES, BeanNatCommonLgNames.TYPE_MAP,false,false,new MapLevelBeanWhiteTilesGet(),null));
        fields_.add(new StandardField(NEIGHBOURS, BeanNatCommonLgNames.TYPE_MAP,false,false,new MapLevelBeanNeighboursGet(),null));
        methods_.add( new SpecNatMethod(GET_MAP_WIDTH, BeanNatCommonLgNames.PRIM_INTEGER, false, MethodModifier.NORMAL,new MapLevelBeanGetMapWidth()));
        methods_.add( new SpecNatMethod(IS_FIRST_ROW,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new MapLevelBeanIsFirstRow()));
        methods_.add( new SpecNatMethod(CLICK_AREA,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MapLevelBeanClickArea()));
        methods_.add( new SpecNatMethod(CLICK_AREA_ON_MAP,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MapLevelBeanClickAreaOnMap()));
        methods_.add( new SpecNatMethod(CLICK_NEIGHBOUR,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new MapLevelBeanClickNeighbour()));
        _std.getStds().addEntry(TYPE_ABS_LEVEL_BEAN, type_);
    }
}
