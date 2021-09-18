package aiki.beans.solution;

import aiki.beans.AikiBeansStd;
import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import code.bean.nat.BeanNatLgNames;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AikiBeansSolutionStd {
    public static final String TYPE_SOLUTION_BEAN = "aiki.beans.solution.SolutionBean";

    private static final String GET_PLACE = "getPlace";
    private static final String STEPS = "steps";

    public static void build(PokemonStandards _std) {
        buildSolutionBean(_std);
    }
    private static void buildSolutionBean(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_SOLUTION_BEAN, fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        fields_.add(new StandardField(STEPS, BeanNatLgNames.TYPE_LIST,false,false));
        params_ = new StringList(_std.getAliasPrimInteger(),_std.getAliasPrimInteger());
        method_ = new SpecNatMethod(GET_PLACE,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add(method_);
        _std.getStds().addEntry(TYPE_SOLUTION_BEAN, type_);
    }
    public static ResultErrorStd getResultSolutionBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanNatLgNames std_ = (BeanNatLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        SolutionBean instance_ = (SolutionBean) ((PokemonBeanStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,STEPS)) {
            res_.setResult(PokemonStandards.getSteDto(instance_.getSteps()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodSolutionBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args) {
        SolutionBean instance_ = (SolutionBean) ((PokemonBeanStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_PLACE)) {
            res_.setResult(new StringStruct(instance_.getPlace(NumParsers.convertToNumber(_args[0]).intStruct(),NumParsers.convertToNumber(_args[1]).intStruct())));
            return res_;
        }
        return res_;
    }
}
