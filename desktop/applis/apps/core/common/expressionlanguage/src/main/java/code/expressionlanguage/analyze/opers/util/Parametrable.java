package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.Identifiable;
import code.util.CustList;

public interface Parametrable {

    ParametersGroup getParameters();

    String getReturnType();

    String getClassName();

    boolean isVararg();


    boolean isVarArgWrap();
    void setVarArgWrap(boolean _v);
    boolean sameParamsVararg(Parametrable _id);

    Identifiable getGeneFormatted();
    Identifiable getPartialId();

    void setInvocation(InvocationMethod _inv);
    InvocationMethod getInvocation();
    CustList<CustList<ClassMethodId>> getImplicits();
}
