package aiki.beans.facade.dto;
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
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.bean.nat.BeanNatLgNames;
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
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_ITEM_LINE, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        fields_.add(new StandardField(DISPLAY_NAME,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(PRICE,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(DESCRIPTION_CLASS,_std.getAliasString(),false,false,type_));
        _std.getStandards().addEntry(TYPE_ITEM_LINE, type_);
    }
    private static void buildItemTypeLine(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_ITEM_TYPE_LINE, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().addEntry(TYPE_ITEM_TYPE_LINE, type_);
    }
    private static void buildKeptMovesAfterFight(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_KEPT_MOVES_AFTER_FIGHT, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().addEntry(TYPE_KEPT_MOVES_AFTER_FIGHT, type_);
    }
    private static void buildMoveLine(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_MOVE_LINE, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        fields_.add(new StandardField(DISPLAY_NAME,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(PP,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(CATEGORY,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(PRIORITY,_std.getAliasPrimInteger(),false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(IS_DAMAGE_MOVE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(IS_DIRECT,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_TYPES,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStandards().addEntry(TYPE_MOVE_LINE, type_);
    }
    private static void buildPokemonLine(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_POKEMON_LINE, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        fields_.add(new StandardField(DISPLAY_NAME,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(TYPES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(EVOLUTIONS,_std.getAliasPrimInteger(),false,false,type_));
        _std.getStandards().addEntry(TYPE_POKEMON_LINE, type_);
    }
    private static void buildWeatherTypeLine(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_WEATHER_TYPE_LINE, fields_, constructors_, methods_, _std.getAliasObject(), MethodModifier.NORMAL);
        _std.getStandards().addEntry(TYPE_WEATHER_TYPE_LINE, type_);
    }
    public static ResultErrorStd getResultItemLine(ContextEl _cont, ClassField _classField, ItemLine _inst) {
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
    public static ResultErrorStd getResultMoveLine(ContextEl _cont, ClassField _classField, MoveLine _inst) {
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
    public static ResultErrorStd getResultPokemonLine(ContextEl _cont, ClassField _classField, PokemonLine _inst) {
        BeanNatLgNames std_ = (BeanNatLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,DISPLAY_NAME)) {
            res_.setResult(new StringStruct(_inst.getDisplayName()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,TYPES)) {
            res_.setResult(std_.getStringArray(_inst.getTypes()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,EVOLUTIONS)) {
            res_.setResult(new IntStruct(_inst.getEvolutions()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodMoveLine(ContextEl _cont, ClassMethodId _method, MoveLine _inst, Struct... _args) {
        BeanNatLgNames std_ = (BeanNatLgNames) _cont.getStandards();
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
            res_.setResult(std_.getStringArray(_inst.getTypes()));
            return res_;
        }
        return res_;
    }
}
