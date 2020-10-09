package aiki.beans.map.elements;
import aiki.beans.AikiBeansStd;
import aiki.beans.DefaultStruct;
import aiki.beans.PokemonStandards;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.bean.RealInstanceStruct;
import code.expressionlanguage.structs.ShortStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.BeanLgNames;
import code.bean.nat.BeanNatLgNames;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AikiBeansMapElementsStd {
    public static final String TYPE_AREA_BEAN = "aiki.beans.map.elements.AreaBean";
    public static final String TYPE_LEGENDARY_POKEMON_BEAN = "aiki.beans.map.elements.LegendaryPokemonBean";

    private static final String GET_IMAGE = "getImage";
    private static final String CLICK_NAME = "clickName";
    private static final String GET_NAME = "getName";
    private static final String GET_GENDER = "getGender";
    private static final String CLICK_ABILITY = "clickAbility";
    private static final String GET_ABILITY = "getAbility";
    private static final String CLICK_ITEM = "clickItem";
    private static final String GET_ITEM = "getItem";
    private static final String GET_MOVES_AT_LEVEL = "getMovesAtLevel";
    private static final String CLICK_MOVE = "clickMove";
    private static final String GET_MOVE = "getMove";
    private static final String GET_IMAGE_FISHING = "getImageFishing";
    private static final String CLICK_NAME_FISHING = "clickNameFishing";
    private static final String GET_NAME_FISHING = "getNameFishing";
    private static final String CLICK_ABILITY_FISHING = "clickAbilityFishing";
    private static final String GET_ABILITY_FISHING = "getAbilityFishing";
    private static final String CLICK_ITEM_FISHING = "clickItemFishing";
    private static final String GET_ITEM_FISHING = "getItemFishing";
    private static final String GET_MOVES_AT_LEVEL_FISHING = "getMovesAtLevelFishing";
    private static final String CLICK_MOVE_FISHING = "clickMoveFishing";
    private static final String GET_MOVE_FISHING = "getMoveFishing";
    private static final String GET_LEVEL = "getLevel";
    private static final String AREA = "area";
    private static final String POKEMON = "pokemon";

    public static void build(PokemonStandards _std) {
        buildAreaBean(_std);
        buildLegendaryPokemonBean(_std);
    }
    private static void buildAreaBean(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_AREA_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.add(new StandardField(AREA,PokemonStandards.TYPE_AREA_APPARITION,false,false,type_));
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_IMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_NAME,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_NAME,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_GENDER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_MOVES_AT_LEVEL,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(GET_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_IMAGE_FISHING,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_NAME_FISHING,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_NAME_FISHING,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ABILITY_FISHING,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_ABILITY_FISHING,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ITEM_FISHING,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_ITEM_FISHING,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_MOVES_AT_LEVEL_FISHING,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVE_FISHING,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(GET_MOVE_FISHING,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStandards().addEntry(TYPE_AREA_BEAN, type_);
    }
    private static void buildLegendaryPokemonBean(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_LEGENDARY_POKEMON_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.add(new StandardField(POKEMON,PokemonStandards.TYPE_POKEMON,false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(GET_NAME,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_IMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(CLICK_NAME,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_GENDER,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_LEVEL,params_,_std.getAliasPrimShort(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(CLICK_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(CLICK_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_MOVES_AT_LEVEL,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStandards().addEntry(TYPE_LEGENDARY_POKEMON_BEAN, type_);
    }
    public static ResultErrorStd getResultAreaBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        AreaBean instance_ = (AreaBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,AREA)) {
            res_.setResult(DefaultStruct.newInstance(instance_.getArea(),PokemonStandards.TYPE_AREA_APPARITION));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultLegendaryPokemonBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        LegendaryPokemonBean instance_ = (LegendaryPokemonBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,POKEMON)) {
            res_.setResult(DefaultStruct.newInstance(instance_.getPokemon(),PokemonStandards.TYPE_POKEMON));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodAreaBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        AreaBean instance_ = (AreaBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_IMAGE)) {
            res_.setResult(new StringStruct(instance_.getImage((Long)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_NAME)) {
            res_.setResult(new StringStruct(instance_.clickName((Long)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_NAME)) {
            res_.setResult(new StringStruct(instance_.getName((Long)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_GENDER)) {
            res_.setResult(new StringStruct(instance_.getGender((Long)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITY)) {
            res_.setResult(new StringStruct(instance_.clickAbility((Long)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ABILITY)) {
            res_.setResult(new StringStruct(instance_.getAbility((Long)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ITEM)) {
            res_.setResult(new StringStruct(instance_.clickItem((Long)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ITEM)) {
            res_.setResult(new StringStruct(instance_.getItem((Long)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_MOVES_AT_LEVEL)) {
            res_.setResult(new DefaultStruct(instance_.getMovesAtLevel((Long)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickMove((Long)_args[0],(Long)_args[1])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_MOVE)) {
            res_.setResult(new StringStruct(instance_.getMove((Long)_args[0],(Long)_args[1])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_IMAGE_FISHING)) {
            res_.setResult(new StringStruct(instance_.getImageFishing((Long)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_NAME_FISHING)) {
            res_.setResult(new StringStruct(instance_.clickNameFishing((Long)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_NAME_FISHING)) {
            res_.setResult(new StringStruct(instance_.getNameFishing((Long)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITY_FISHING)) {
            res_.setResult(new StringStruct(instance_.clickAbilityFishing((Long)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ABILITY_FISHING)) {
            res_.setResult(new StringStruct(instance_.getAbilityFishing((Long)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ITEM_FISHING)) {
            res_.setResult(new StringStruct(instance_.clickItemFishing((Long)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ITEM_FISHING)) {
            res_.setResult(new StringStruct(instance_.getItemFishing((Long)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_MOVES_AT_LEVEL_FISHING)) {
            res_.setResult(new DefaultStruct(instance_.getMovesAtLevelFishing((Long)_args[0]), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVE_FISHING)) {
            res_.setResult(new StringStruct(instance_.clickMoveFishing((Long)_args[0],(Long)_args[1])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_MOVE_FISHING)) {
            res_.setResult(new StringStruct(instance_.getMoveFishing((Long)_args[0],(Long)_args[1])));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodLegendaryPokemonBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        LegendaryPokemonBean instance_ = (LegendaryPokemonBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_NAME)) {
            res_.setResult(new StringStruct(instance_.getName()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_IMAGE)) {
            res_.setResult(new StringStruct(instance_.getImage()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_NAME)) {
            res_.setResult(new StringStruct(instance_.clickName()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_GENDER)) {
            res_.setResult(new StringStruct(instance_.getGender()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_LEVEL)) {
            res_.setResult(new ShortStruct(instance_.getLevel()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITY)) {
            res_.setResult(new StringStruct(instance_.clickAbility()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ABILITY)) {
            res_.setResult(new StringStruct(instance_.getAbility()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ITEM)) {
            res_.setResult(new StringStruct(instance_.clickItem()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ITEM)) {
            res_.setResult(new StringStruct(instance_.getItem()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_MOVES_AT_LEVEL)) {
            res_.setResult(new DefaultStruct(instance_.getMovesAtLevel(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickMove((Long)_args[0])));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_MOVE)) {
            res_.setResult(new StringStruct(instance_.getMove((Long)_args[0])));
            return res_;
        }
        return res_;
    }
}
