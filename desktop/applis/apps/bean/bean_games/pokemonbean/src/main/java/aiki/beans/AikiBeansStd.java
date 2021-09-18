package aiki.beans;

import code.bean.nat.BeanNatLgNames;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AikiBeansStd {
    public static final String TYPE_COMMON_BEAN = "aiki.beans.CommonBean";
    public static final String TYPE_WELCOME_BEAN = "aiki.beans.WelcomeBean";

    private static final String CLICK_POKEDEX = "clickPokedex";
    private static final String CLICK_ITEMS = "clickItems";
    private static final String SEE_ALL_MOVES = "seeAllMoves";
    private static final String SEE_LEARNT_MOVES = "seeLearntMoves";
    private static final String SEE_NOT_LEARNT_MOVES = "seeNotLearntMoves";
    private static final String CLICK_ABILITIES = "clickAbilities";
    private static final String CLICK_STATUS = "clickStatus";
    private static final String CLICK_SIMULATION = "clickSimulation";

    public static void build(PokemonStandards _std) {
        buildCommonBean(_std);
        buildWelcomeBean(_std);
    }
    private static void buildCommonBean(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COMMON_BEAN, fields_, methods_, BeanNatLgNames.TYPE_BEAN);
        _std.getStds().addEntry(TYPE_COMMON_BEAN, type_);
    }
    private static void buildWelcomeBean(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_WELCOME_BEAN, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        params_ = new StringList();
        method_ = new SpecNatMethod(CLICK_POKEDEX,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(CLICK_ITEMS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(SEE_ALL_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(SEE_LEARNT_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(SEE_NOT_LEARNT_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(CLICK_ABILITIES,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(CLICK_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(CLICK_SIMULATION,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_WELCOME_BEAN, type_);
    }
    public static ResultErrorStd invokeMethodWelcomeBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args) {
        WelcomeBean instance_ = (WelcomeBean) ((PokemonBeanStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,CLICK_POKEDEX)) {
            res_.setResult(new StringStruct(instance_.clickPokedex()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ITEMS)) {
            res_.setResult(new StringStruct(instance_.clickItems()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,SEE_ALL_MOVES)) {
            res_.setResult(new StringStruct(instance_.seeAllMoves()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,SEE_LEARNT_MOVES)) {
            res_.setResult(new StringStruct(instance_.seeLearntMoves()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,SEE_NOT_LEARNT_MOVES)) {
            res_.setResult(new StringStruct(instance_.seeNotLearntMoves()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_ABILITIES)) {
            res_.setResult(new StringStruct(instance_.clickAbilities()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickStatus()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,CLICK_SIMULATION)) {
            res_.setResult(new StringStruct(instance_.clickSimulation()));
            return res_;
        }
        return res_;
    }
}
