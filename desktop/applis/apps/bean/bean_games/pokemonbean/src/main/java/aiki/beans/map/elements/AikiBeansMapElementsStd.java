package aiki.beans.map.elements;

import aiki.beans.*;
import code.bean.nat.*;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
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
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_AREA_BEAN, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        fields_.add(new StandardField(AREA,PokemonStandards.TYPE_AREA_APPARITION,false,false));
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(GET_IMAGE,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(CLICK_NAME,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(GET_NAME,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(GET_GENDER,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(CLICK_ABILITY,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(GET_ABILITY,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(CLICK_ITEM,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(GET_ITEM,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(GET_MOVES_AT_LEVEL,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER, BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(CLICK_MOVE,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER, BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(GET_MOVE,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(GET_IMAGE_FISHING,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(CLICK_NAME_FISHING,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(GET_NAME_FISHING,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(CLICK_ABILITY_FISHING,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(GET_ABILITY_FISHING,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(CLICK_ITEM_FISHING,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(GET_ITEM_FISHING,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(GET_MOVES_AT_LEVEL_FISHING,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER, BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(CLICK_MOVE_FISHING,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER, BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(GET_MOVE_FISHING,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_AREA_BEAN, type_);
    }
    private static void buildLegendaryPokemonBean(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_LEGENDARY_POKEMON_BEAN, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        fields_.add(new StandardField(POKEMON,PokemonStandards.TYPE_WILD_PK,false,false));
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_NAME,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_IMAGE,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(CLICK_NAME,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_GENDER,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_LEVEL,params_, BeanNatCommonLgNames.PRIM_INTEGER, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(CLICK_ABILITY,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_ABILITY,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(CLICK_ITEM,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_ITEM,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_MOVES_AT_LEVEL,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(CLICK_MOVE,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(BeanNatCommonLgNames.PRIM_INTEGER);
        method_ = new SpecNatMethod(GET_MOVE,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_LEGENDARY_POKEMON_BEAN, type_);
    }
    public static ResultErrorStd getResultAreaBean(ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        AreaBean instance_ = (AreaBean) ((PokemonBeanStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,AREA)) {
            res_.setResult(new AreaApparitionStruct(instance_.getArea(),PokemonStandards.TYPE_AREA_APPARITION));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultLegendaryPokemonBean(ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        LegendaryPokemonBean instance_ = (LegendaryPokemonBean) ((PokemonBeanStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,POKEMON)) {
            res_.setResult(new PkStruct(instance_.getPokemon(),PokemonStandards.TYPE_WILD_PK));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodAreaBean(Struct _instance, ClassMethodId _method, Struct... _args) {
        AreaBean instance_ = (AreaBean) ((PokemonBeanStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_IMAGE)) {
            res_.setResult(new StringStruct(instance_.getImage(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_NAME)) {
            res_.setResult(new StringStruct(instance_.clickName(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_NAME)) {
            res_.setResult(new StringStruct(instance_.getName(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_GENDER)) {
            res_.setResult(new StringStruct(instance_.getGender(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITY)) {
            res_.setResult(new StringStruct(instance_.clickAbility(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ABILITY)) {
            res_.setResult(new StringStruct(instance_.getAbility(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ITEM)) {
            res_.setResult(new StringStruct(instance_.clickItem(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ITEM)) {
            res_.setResult(new StringStruct(instance_.getItem(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_MOVES_AT_LEVEL)) {
            res_.setResult(BeanNatCommonLgNames.getStringArray(instance_.getMovesAtLevel(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickMove(NumParsers.convertToNumber(_args[0]).intStruct(),NumParsers.convertToNumber(_args[1]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_MOVE)) {
            res_.setResult(new StringStruct(instance_.getMove(NumParsers.convertToNumber(_args[0]).intStruct(),NumParsers.convertToNumber(_args[1]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_IMAGE_FISHING)) {
            res_.setResult(new StringStruct(instance_.getImageFishing(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_NAME_FISHING)) {
            res_.setResult(new StringStruct(instance_.clickNameFishing(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_NAME_FISHING)) {
            res_.setResult(new StringStruct(instance_.getNameFishing(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITY_FISHING)) {
            res_.setResult(new StringStruct(instance_.clickAbilityFishing(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ABILITY_FISHING)) {
            res_.setResult(new StringStruct(instance_.getAbilityFishing(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ITEM_FISHING)) {
            res_.setResult(new StringStruct(instance_.clickItemFishing(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ITEM_FISHING)) {
            res_.setResult(new StringStruct(instance_.getItemFishing(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_MOVES_AT_LEVEL_FISHING)) {
            res_.setResult(BeanNatCommonLgNames.getStringArray(instance_.getMovesAtLevelFishing(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVE_FISHING)) {
            res_.setResult(new StringStruct(instance_.clickMoveFishing(NumParsers.convertToNumber(_args[0]).intStruct(),NumParsers.convertToNumber(_args[1]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_MOVE_FISHING)) {
            res_.setResult(new StringStruct(instance_.getMoveFishing(NumParsers.convertToNumber(_args[0]).intStruct(),NumParsers.convertToNumber(_args[1]).intStruct())));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodLegendaryPokemonBean(Struct _instance, ClassMethodId _method, Struct... _args) {
        LegendaryPokemonBean instance_ = (LegendaryPokemonBean) ((PokemonBeanStruct)_instance).getInstance();
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
            res_.setResult(new IntStruct(instance_.getLevel()));
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
            res_.setResult(BeanNatCommonLgNames.getStringArray(instance_.getMovesAtLevel()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickMove(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_MOVE)) {
            res_.setResult(new StringStruct(instance_.getMove(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        return res_;
    }
}
