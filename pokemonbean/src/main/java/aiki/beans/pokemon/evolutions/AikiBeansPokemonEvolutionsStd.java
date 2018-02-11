package aiki.beans.pokemon.evolutions;
import aiki.beans.AikiBeansStd;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.LongStruct;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.ShortStruct;
import code.expressionlanguage.opers.util.StringStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class AikiBeansPokemonEvolutionsStd {
    public static final String TYPE_EVOLUTION_BEAN = "aiki.beans.pokemon.evolutions.EvolutionBean";
    public static final String TYPE_EVOLUTION_HAPPINESS_BEAN = "aiki.beans.pokemon.evolutions.EvolutionHappinessBean";
    public static final String TYPE_EVOLUTION_ITEM_BEAN = "aiki.beans.pokemon.evolutions.EvolutionItemBean";
    public static final String TYPE_EVOLUTION_LEVEL_BEAN = "aiki.beans.pokemon.evolutions.EvolutionLevelBean";
    public static final String TYPE_EVOLUTION_LEVEL_GENDER_BEAN = "aiki.beans.pokemon.evolutions.EvolutionLevelGenderBean";
    public static final String TYPE_EVOLUTION_MOVE_BEAN = "aiki.beans.pokemon.evolutions.EvolutionMoveBean";
    public static final String TYPE_EVOLUTION_MOVE_TYPE_BEAN = "aiki.beans.pokemon.evolutions.EvolutionMoveTypeBean";
    public static final String TYPE_EVOLUTION_STONE_BEAN = "aiki.beans.pokemon.evolutions.EvolutionStoneBean";
    public static final String TYPE_EVOLUTION_STONE_GENDER_BEAN = "aiki.beans.pokemon.evolutions.EvolutionStoneGenderBean";
    public static final String TYPE_EVOLUTION_TEAM_BEAN = "aiki.beans.pokemon.evolutions.EvolutionTeamBean";

    private static final String CLICK_EVO = "clickEvo";
    private static final String CLICK_ITEM = "clickItem";
    private static final String CLICK_MOVE = "clickMove";
    private static final String CLICK_STONE = "clickStone";
    private static final String CLICK_TEAM = "clickTeam";
    private static final String INDEX = "index";
    private static final String NAME = "name";
    private static final String BASE = "base";
    private static final String DISPLAY_NAME = "displayName";
    private static final String DISPLAY_BASE = "displayBase";
    private static final String MIN = "min";
    private static final String ITEM = "item";
    private static final String LEVEL = "level";
    private static final String GENDER = "gender";
    private static final String MOVE = "move";
    private static final String STONE = "stone";
    private static final String OTHER = "other";
    private static final String TYPE = "type";

    public static void build(BeanLgNames _std) {
        buildEvolutionBean(_std);
        buildEvolutionHappinessBean(_std);
        buildEvolutionItemBean(_std);
        buildEvolutionLevelBean(_std);
        buildEvolutionLevelGenderBean(_std);
        buildEvolutionMoveBean(_std);
        buildEvolutionMoveTypeBean(_std);
        buildEvolutionStoneBean(_std);
        buildEvolutionStoneGenderBean(_std);
        buildEvolutionTeamBean(_std);
    }
    private static void buildEvolutionBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EVOLUTION_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.put(INDEX,new StandardField(INDEX,_std.getAliasPrimLong(),false,false,type_));
        fields_.put(NAME,new StandardField(NAME,_std.getAliasString(),false,false,type_));
        fields_.put(BASE,new StandardField(BASE,_std.getAliasString(),false,false,type_));
        fields_.put(DISPLAY_NAME,new StandardField(DISPLAY_NAME,_std.getAliasString(),false,false,type_));
        fields_.put(DISPLAY_BASE,new StandardField(DISPLAY_BASE,_std.getAliasString(),false,false,type_));
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_EVO,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EVOLUTION_BEAN, type_);
    }
    private static void buildEvolutionHappinessBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EVOLUTION_HAPPINESS_BEAN, fields_, constructors_, methods_, AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_BEAN, MethodModifier.NORMAL);
        fields_.put(MIN,new StandardField(MIN,_std.getAliasPrimLong(),false,false,type_));
        _std.getStandards().put(TYPE_EVOLUTION_HAPPINESS_BEAN, type_);
    }
    private static void buildEvolutionItemBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EVOLUTION_ITEM_BEAN, fields_, constructors_, methods_, AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_BEAN, MethodModifier.NORMAL);
        fields_.put(ITEM,new StandardField(ITEM,_std.getAliasString(),false,false,type_));
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_ITEM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EVOLUTION_ITEM_BEAN, type_);
    }
    private static void buildEvolutionLevelBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EVOLUTION_LEVEL_BEAN, fields_, constructors_, methods_, AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_BEAN, MethodModifier.NORMAL);
        fields_.put(LEVEL,new StandardField(LEVEL,_std.getAliasPrimShort(),false,false,type_));
        _std.getStandards().put(TYPE_EVOLUTION_LEVEL_BEAN, type_);
    }
    private static void buildEvolutionLevelGenderBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EVOLUTION_LEVEL_GENDER_BEAN, fields_, constructors_, methods_, AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_LEVEL_BEAN, MethodModifier.NORMAL);
        fields_.put(GENDER,new StandardField(GENDER,_std.getAliasString(),false,false,type_));
        _std.getStandards().put(TYPE_EVOLUTION_LEVEL_GENDER_BEAN, type_);
    }
    private static void buildEvolutionMoveBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EVOLUTION_MOVE_BEAN, fields_, constructors_, methods_, AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_BEAN, MethodModifier.NORMAL);
        fields_.put(MOVE,new StandardField(MOVE,_std.getAliasString(),false,false,type_));
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EVOLUTION_MOVE_BEAN, type_);
    }
    private static void buildEvolutionMoveTypeBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EVOLUTION_MOVE_TYPE_BEAN, fields_, constructors_, methods_, AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_BEAN, MethodModifier.NORMAL);
        fields_.put(TYPE,new StandardField(TYPE,_std.getAliasString(),false,false,type_));
        _std.getStandards().put(TYPE_EVOLUTION_MOVE_TYPE_BEAN, type_);
    }
    private static void buildEvolutionStoneBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EVOLUTION_STONE_BEAN, fields_, constructors_, methods_, AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_BEAN, MethodModifier.NORMAL);
        fields_.put(STONE,new StandardField(STONE,_std.getAliasString(),false,false,type_));
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_STONE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EVOLUTION_STONE_BEAN, type_);
    }
    private static void buildEvolutionStoneGenderBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EVOLUTION_STONE_GENDER_BEAN, fields_, constructors_, methods_, AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_STONE_BEAN, MethodModifier.NORMAL);
        fields_.put(GENDER,new StandardField(GENDER,_std.getAliasString(),false,false,type_));
        _std.getStandards().put(TYPE_EVOLUTION_STONE_GENDER_BEAN, type_);
    }
    private static void buildEvolutionTeamBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EVOLUTION_TEAM_BEAN, fields_, constructors_, methods_, AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_BEAN, MethodModifier.NORMAL);
        fields_.put(OTHER,new StandardField(OTHER,_std.getAliasString(),false,false,type_));
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_TEAM,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EVOLUTION_TEAM_BEAN, type_);
    }
    public static ResultErrorStd getResultEvolutionBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        EvolutionBean instance_ = (EvolutionBean) _instance.getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,INDEX)) {
            res_.setResult(new LongStruct(instance_.getIndex()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DISPLAY_NAME)) {
            res_.setResult(new StringStruct(instance_.getDisplayName()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DISPLAY_BASE)) {
            res_.setResult(new StringStruct(instance_.getDisplayBase()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultEvolutionHappinessBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        EvolutionHappinessBean instance_ = (EvolutionHappinessBean) _instance.getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,MIN)) {
            res_.setResult(new LongStruct(instance_.getMin()));
            return res_;
        }
        return AikiBeansPokemonEvolutionsStd.getResultEvolutionBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEvolutionItemBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        EvolutionItemBean instance_ = (EvolutionItemBean) _instance.getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,ITEM)) {
            res_.setResult(new StringStruct(instance_.getItem()));
            return res_;
        }
        return AikiBeansPokemonEvolutionsStd.getResultEvolutionBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEvolutionLevelBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        EvolutionLevelBean instance_ = (EvolutionLevelBean) _instance.getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,LEVEL)) {
            res_.setResult(new ShortStruct(instance_.getLevel()));
            return res_;
        }
        return AikiBeansPokemonEvolutionsStd.getResultEvolutionBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEvolutionLevelGenderBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        EvolutionLevelGenderBean instance_ = (EvolutionLevelGenderBean) _instance.getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,GENDER)) {
            res_.setResult(new StringStruct(instance_.getGender()));
            return res_;
        }
        return AikiBeansPokemonEvolutionsStd.getResultEvolutionLevelBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEvolutionMoveBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        EvolutionMoveBean instance_ = (EvolutionMoveBean) _instance.getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,MOVE)) {
            res_.setResult(new StringStruct(instance_.getMove()));
            return res_;
        }
        return AikiBeansPokemonEvolutionsStd.getResultEvolutionBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEvolutionMoveTypeBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        EvolutionMoveTypeBean instance_ = (EvolutionMoveTypeBean) _instance.getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,TYPE)) {
            res_.setResult(new StringStruct(instance_.getType()));
            return res_;
        }
        return AikiBeansPokemonEvolutionsStd.getResultEvolutionBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEvolutionStoneBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        EvolutionStoneBean instance_ = (EvolutionStoneBean) _instance.getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,STONE)) {
            res_.setResult(new StringStruct(instance_.getStone()));
            return res_;
        }
        return AikiBeansPokemonEvolutionsStd.getResultEvolutionBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEvolutionStoneGenderBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        EvolutionStoneGenderBean instance_ = (EvolutionStoneGenderBean) _instance.getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,GENDER)) {
            res_.setResult(new StringStruct(instance_.getGender()));
            return res_;
        }
        return AikiBeansPokemonEvolutionsStd.getResultEvolutionStoneBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEvolutionTeamBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        EvolutionTeamBean instance_ = (EvolutionTeamBean) _instance.getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,OTHER)) {
            res_.setResult(new StringStruct(instance_.getOther()));
            return res_;
        }
        return AikiBeansPokemonEvolutionsStd.getResultEvolutionBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd setResultEvolutionBean(ContextEl _cont, ClassField _classField, Struct _instance, Struct _value) {
        ResultErrorStd res_ = new ResultErrorStd();
        EvolutionBean instance_ = (EvolutionBean) _instance.getInstance();
        Object value_ = _value.getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,INDEX)) {
            instance_.setIndex((Long) value_);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,NAME)) {
            instance_.setName((String) value_);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,BASE)) {
            instance_.setBase((String) value_);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodEvolutionBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EvolutionBean instance_ = (EvolutionBean) _instance.getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,CLICK_EVO)) {
            res_.setResult(new StringStruct(instance_.clickEvo((Long)_args[0])));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodEvolutionItemBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EvolutionItemBean instance_ = (EvolutionItemBean) _instance.getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,CLICK_ITEM)) {
            res_.setResult(new StringStruct(instance_.clickItem((Long)_args[0])));
            return res_;
        }
        return AikiBeansPokemonEvolutionsStd.invokeMethodEvolutionBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodEvolutionMoveBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EvolutionMoveBean instance_ = (EvolutionMoveBean) _instance.getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,CLICK_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickMove((Long)_args[0])));
            return res_;
        }
        return AikiBeansPokemonEvolutionsStd.invokeMethodEvolutionBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodEvolutionStoneBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EvolutionStoneBean instance_ = (EvolutionStoneBean) _instance.getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,CLICK_STONE)) {
            res_.setResult(new StringStruct(instance_.clickStone((Long)_args[0])));
            return res_;
        }
        return AikiBeansPokemonEvolutionsStd.invokeMethodEvolutionBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodEvolutionTeamBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EvolutionTeamBean instance_ = (EvolutionTeamBean) _instance.getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,CLICK_TEAM)) {
            res_.setResult(new StringStruct(instance_.clickTeam((Long)_args[0])));
            return res_;
        }
        return AikiBeansPokemonEvolutionsStd.invokeMethodEvolutionBean(_cont, _instance, _method, _args);
    }
}
