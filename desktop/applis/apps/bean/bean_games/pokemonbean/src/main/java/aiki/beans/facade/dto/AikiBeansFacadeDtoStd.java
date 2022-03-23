package aiki.beans.facade.dto;

import aiki.beans.PokemonStandards;
import code.bean.nat.*;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AikiBeansFacadeDtoStd {
    public static final String TYPE_ITEM_LINE = "aiki.beans.facade.dto.ItemLine";
    public static final String TYPE_ITEM_TYPE_LINE = "aiki.beans.facade.dto.ItemTypeLine";
    public static final String TYPE_KEPT_MOVES_AFTER_FIGHT = "aiki.beans.facade.dto.KeptMovesAfterFight";
    public static final String TYPE_MOVE_LINE = "aiki.beans.facade.dto.MoveLine";
    public static final String TYPE_POKEMON_LINE = "aiki.beans.facade.dto.PokemonLine";
    public static final String TYPE_WEATHER_TYPE_LINE = "aiki.beans.facade.dto.WeatherTypeLine";

    private static final String IS_DAMAGE_MOVE = "isDamageMove";
    private static final String IS_DIRECT = "isDirect";
    private static final String GET_TYPES = "getTypes";
    private static final String DISPLAY_NAME = "displayName";
    private static final String PRICE = "price";
    private static final String DESCRIPTION_CLASS = "descriptionClass";
    private static final String TYPES = "types";
    private static final String EVOLUTIONS = "evolutions";
    private static final String PP = "pp";
    private static final String CATEGORY = "category";
    private static final String PRIORITY = "priority";

    public static void build(PokemonStandards _std) {
        buildItemLine(_std);
        buildItemTypeLine(_std);
        buildKeptMovesAfterFight(_std);
        buildMoveLine(_std);
        buildPokemonLine(_std);
        buildWeatherTypeLine(_std);
    }
    private static void buildItemLine(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_ITEM_LINE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        fields_.add(new StandardField(DISPLAY_NAME,BeanNatCommonLgNames.STRING,false,false));
        fields_.add(new StandardField(PRICE, BeanNatCommonLgNames.PRIM_INTEGER,false,false));
        fields_.add(new StandardField(DESCRIPTION_CLASS,BeanNatCommonLgNames.STRING,false,false));
        _std.getStds().addEntry(TYPE_ITEM_LINE, type_);
    }
    private static void buildItemTypeLine(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_ITEM_TYPE_LINE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        _std.getStds().addEntry(TYPE_ITEM_TYPE_LINE, type_);
    }
    private static void buildKeptMovesAfterFight(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_KEPT_MOVES_AFTER_FIGHT, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        _std.getStds().addEntry(TYPE_KEPT_MOVES_AFTER_FIGHT, type_);
    }
    private static void buildMoveLine(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_MOVE_LINE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        fields_.add(new StandardField(DISPLAY_NAME,BeanNatCommonLgNames.STRING,false,false));
        fields_.add(new StandardField(PP, BeanNatCommonLgNames.PRIM_INTEGER,false,false));
        fields_.add(new StandardField(CATEGORY,BeanNatCommonLgNames.STRING,false,false));
        fields_.add(new StandardField(PRIORITY, BeanNatCommonLgNames.PRIM_INTEGER,false,false));
        params_ = new StringList();
        method_ = new SpecNatMethod(IS_DAMAGE_MOVE,params_,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(IS_DIRECT,params_,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_TYPES,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_MOVE_LINE, type_);
    }
    private static void buildPokemonLine(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_POKEMON_LINE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        fields_.add(new StandardField(DISPLAY_NAME,BeanNatCommonLgNames.STRING,false,false));
        fields_.add(new StandardField(TYPES, BeanNatLgNames.TYPE_LIST,false,false));
        fields_.add(new StandardField(EVOLUTIONS, BeanNatCommonLgNames.PRIM_INTEGER,false,false));
        _std.getStds().addEntry(TYPE_POKEMON_LINE, type_);
    }
    private static void buildWeatherTypeLine(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_WEATHER_TYPE_LINE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        _std.getStds().addEntry(TYPE_WEATHER_TYPE_LINE, type_);
    }
    public static ResultErrorStd getResultItemLine(ClassField _classField, ItemLine _inst) {
        ResultErrorStd res_ = new ResultErrorStd();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,DISPLAY_NAME)) {
            res_.setResult(new StringStruct(_inst.getDisplayName()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,PRICE)) {
            res_.setResult(new IntStruct(_inst.getPrice()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,DESCRIPTION_CLASS)) {
            res_.setResult(new StringStruct(_inst.getDescriptionClass()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultMoveLine(ClassField _classField, MoveLine _inst) {
        ResultErrorStd res_ = new ResultErrorStd();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,DISPLAY_NAME)) {
            res_.setResult(new StringStruct(_inst.getDisplayName()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,PP)) {
            res_.setResult(new IntStruct(_inst.getPp()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,CATEGORY)) {
            res_.setResult(new StringStruct(_inst.getCategory()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,PRIORITY)) {
            res_.setResult(new IntStruct(_inst.getPriority()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultPokemonLine(ClassField _classField, PokemonLine _inst) {
        ResultErrorStd res_ = new ResultErrorStd();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,DISPLAY_NAME)) {
            res_.setResult(new StringStruct(_inst.getDisplayName()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,TYPES)) {
            res_.setResult(BeanNatCommonLgNames.getStringArray(_inst.getTypes()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,EVOLUTIONS)) {
            res_.setResult(new IntStruct(_inst.getEvolutions()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodMoveLine(ClassMethodId _method, MoveLine _inst, Struct... _args) {
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,IS_DAMAGE_MOVE)) {
            res_.setResult(BooleanStruct.of(_inst.isDamageMove()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_DIRECT)) {
            res_.setResult(BooleanStruct.of(_inst.isDirect()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TYPES)) {
            res_.setResult(BeanNatCommonLgNames.getStringArray(_inst.getTypes()));
            return res_;
        }
        return res_;
    }
}
