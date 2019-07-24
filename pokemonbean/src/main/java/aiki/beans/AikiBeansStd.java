package aiki.beans;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.formathtml.structs.RealInstanceStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

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

    public static void build(BeanLgNames _std) {
        buildCommonBean(_std);
        buildWelcomeBean(_std);
    }
    private static void buildCommonBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_COMMON_BEAN, fields_, constructors_, methods_, _std.getBean(), MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_COMMON_BEAN, type_);
    }
    private static void buildWelcomeBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_WELCOME_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        params_ = new StringList();
        method_ = new StandardMethod(CLICK_POKEDEX,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(CLICK_ITEMS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(SEE_ALL_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(SEE_LEARNT_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(SEE_NOT_LEARNT_MOVES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(CLICK_ABILITIES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(CLICK_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(CLICK_SIMULATION,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_WELCOME_BEAN, type_);
    }
    public static ResultErrorStd invokeMethodWelcomeBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        WelcomeBean instance_ = (WelcomeBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,CLICK_POKEDEX)) {
            res_.setResult(new StringStruct(instance_.clickPokedex()));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ITEMS)) {
            res_.setResult(new StringStruct(instance_.clickItems()));
            return res_;
        }
        if (StringList.quickEq(methodName_,SEE_ALL_MOVES)) {
            res_.setResult(new StringStruct(instance_.seeAllMoves()));
            return res_;
        }
        if (StringList.quickEq(methodName_,SEE_LEARNT_MOVES)) {
            res_.setResult(new StringStruct(instance_.seeLearntMoves()));
            return res_;
        }
        if (StringList.quickEq(methodName_,SEE_NOT_LEARNT_MOVES)) {
            res_.setResult(new StringStruct(instance_.seeNotLearntMoves()));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_ABILITIES)) {
            res_.setResult(new StringStruct(instance_.clickAbilities()));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickStatus()));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_SIMULATION)) {
            res_.setResult(new StringStruct(instance_.clickSimulation()));
            return res_;
        }
        return res_;
    }
}
