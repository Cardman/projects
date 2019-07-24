package aiki.beans.map;
import aiki.beans.AikiBeansStd;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.IntStruct;
import code.formathtml.structs.RealInstanceStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.BeanLgNames;
import code.formathtml.structs.StdStruct;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class AikiBeansMapStd {
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

    public static void build(BeanLgNames _std) {
        buildMapBean(_std);
        buildMapLevelBean(_std);
    }
    private static void buildMapBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_MAP_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.put(PLACES,new StandardField(PLACES,_std.getCustList(),false,false,type_));
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_MULTI_LAYER,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(LAYERS,params_,_std.getCustList(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(CLICK_LEVEL,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_MAP_BEAN, type_);
    }
    private static void buildMapLevelBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_MAP_LEVEL_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.put(POSSIBLE_MULTI_LAYER,new StandardField(POSSIBLE_MULTI_LAYER,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(PLACE_NAME,new StandardField(PLACE_NAME,_std.getAliasString(),false,false,type_));
        fields_.put(LEVEL_INDEX,new StandardField(LEVEL_INDEX,_std.getAliasPrimInteger(),false,false,type_));
        fields_.put(OUTSIDE,new StandardField(OUTSIDE,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(ROAD,new StandardField(ROAD,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(GYM,new StandardField(GYM,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(POKEMON_CENTER,new StandardField(POKEMON_CENTER,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(TILES,new StandardField(TILES,_std.getCustMap(),false,false,type_));
        fields_.put(PROPONE_TILE,new StandardField(PROPONE_TILE,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(PROPONE_LINK,new StandardField(PROPONE_LINK,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(SEE_AREA,new StandardField(SEE_AREA,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(DIRS,new StandardField(DIRS,_std.getCustMap(),false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(GET_MAP_WIDTH,params_,_std.getAliasPrimInteger(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_FIRST_ROW,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(WITHOUT_TITLE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_ACCESSIBLE_BY_BEATING_SOME_TRAINERS,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_TILE_ON_MAP,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_STORAGE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_HEALER,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_HOST,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_FOSSILE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_MOVE_TUTORS,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(CLICK_TILE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(CLICK_LINK,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(SEE_AREA,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_UP,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_DIRECTED_LINK,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_DOWN,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_LEFT,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_RIGHT,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_MAP_LEVEL_BEAN, type_);
    }
    public static ResultErrorStd getResultMapBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        MapBean instance_ = (MapBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,PLACES)) {
            res_.setResult(new StdStruct(instance_.getPlaces(),std_.getCustList()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultMapLevelBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        MapLevelBean instance_ = (MapLevelBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,POSSIBLE_MULTI_LAYER)) {
            res_.setResult(new BooleanStruct(instance_.getPossibleMultiLayer()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PLACE_NAME)) {
            res_.setResult(new StringStruct(instance_.getPlaceName()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,LEVEL_INDEX)) {
            res_.setResult(new IntStruct(instance_.getLevelIndex()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,OUTSIDE)) {
            res_.setResult(new BooleanStruct(instance_.getOutside()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ROAD)) {
            res_.setResult(new BooleanStruct(instance_.getRoad()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,GYM)) {
            res_.setResult(new BooleanStruct(instance_.getGym()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,POKEMON_CENTER)) {
            res_.setResult(new BooleanStruct(instance_.getPokemonCenter()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,TILES)) {
            res_.setResult(new StdStruct(instance_.getTiles(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PROPONE_TILE)) {
            res_.setResult(new BooleanStruct(instance_.getProponeTile()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PROPONE_LINK)) {
            res_.setResult(new BooleanStruct(instance_.getProponeLink()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,SEE_AREA)) {
            res_.setResult(new BooleanStruct(instance_.getSeeArea()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DIRS)) {
            res_.setResult(new StdStruct(instance_.getDirs(),std_.getCustMap()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodMapBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        MapBean instance_ = (MapBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,IS_MULTI_LAYER)) {
            res_.setResult(new BooleanStruct(instance_.isMultiLayer((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,LAYERS)) {
            res_.setResult(new StdStruct(instance_.layers((Long)_args[0]),std_.getCustList()));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_LEVEL)) {
            res_.setResult(new StringStruct(instance_.clickLevel((Long)_args[0],(Long)_args[1])));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodMapLevelBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        MapLevelBean instance_ = (MapLevelBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,GET_MAP_WIDTH)) {
            res_.setResult(new IntStruct(instance_.getMapWidth()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_FIRST_ROW)) {
            res_.setResult(new BooleanStruct(instance_.isFirstRow((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,WITHOUT_TITLE)) {
            res_.setResult(new BooleanStruct(instance_.withoutTitle((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_ACCESSIBLE_BY_BEATING_SOME_TRAINERS)) {
            res_.setResult(new BooleanStruct(instance_.isAccessibleByBeatingSomeTrainers((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_TILE_ON_MAP)) {
            res_.setResult(new StringStruct(instance_.clickTileOnMap((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_STORAGE)) {
            res_.setResult(new BooleanStruct(instance_.isStorage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_HEALER)) {
            res_.setResult(new BooleanStruct(instance_.isHealer((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_HOST)) {
            res_.setResult(new BooleanStruct(instance_.isHost((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_FOSSILE)) {
            res_.setResult(new BooleanStruct(instance_.isFossile((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_MOVE_TUTORS)) {
            res_.setResult(new BooleanStruct(instance_.isMoveTutors((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_TILE)) {
            res_.setResult(new StringStruct(instance_.clickTile()));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_LINK)) {
            res_.setResult(new StringStruct(instance_.clickLink()));
            return res_;
        }
        if (StringList.quickEq(methodName_,SEE_AREA)) {
            res_.setResult(new StringStruct(instance_.seeArea()));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_UP)) {
            res_.setResult(new BooleanStruct(instance_.isUp((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_DIRECTED_LINK)) {
            res_.setResult(new StringStruct(instance_.clickDirectedLink((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_DOWN)) {
            res_.setResult(new BooleanStruct(instance_.isDown((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_LEFT)) {
            res_.setResult(new BooleanStruct(instance_.isLeft((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,IS_RIGHT)) {
            res_.setResult(new BooleanStruct(instance_.isRight((Long)_args[0])));
            return res_;
        }
        return res_;
    }
}
