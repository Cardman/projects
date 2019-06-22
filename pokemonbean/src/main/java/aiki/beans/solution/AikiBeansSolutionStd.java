package aiki.beans.solution;
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
import code.formathtml.util.RealInstanceStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.StdStruct;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class AikiBeansSolutionStd {
    public static final String TYPE_SOLUTION_BEAN = "aiki.beans.solution.SolutionBean";

    private static final String GET_PLACE = "getPlace";
    private static final String STEPS = "steps";

    public static void build(BeanLgNames _std) {
        buildSolutionBean(_std);
    }
    private static void buildSolutionBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_SOLUTION_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.put(STEPS,new StandardField(STEPS,_std.getCustList(),false,false,type_));
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(GET_PLACE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_SOLUTION_BEAN, type_);
    }
    public static ResultErrorStd getResultSolutionBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        SolutionBean instance_ = (SolutionBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,STEPS)) {
            res_.setResult(new StdStruct(instance_.getSteps(),std_.getCustList()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodSolutionBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        SolutionBean instance_ = (SolutionBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,GET_PLACE)) {
            res_.setResult(new StringStruct(instance_.getPlace((Long)_args[0],(Long)_args[1])));
            return res_;
        }
        return res_;
    }
}
