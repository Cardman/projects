package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.functionid.Identifiable;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public interface Parametrable {

    ParametersGroup getParameters();

    String getReturnType();

    String getClassName();

    boolean isVararg();


    boolean isVarArgWrap();
    void setVarArgWrap(boolean _v);
    boolean sameParamsVararg(Parametrable _id);

    Identifiable getGeneFormatted();
    StringList getFormattedParams();
    StringList getParametersNames();
    Ints getNameParametersFilterIndexes();
    Identifiable getPartialId();

    void setInvocation(InvocationMethod _inv);
    InvocationMethod getInvocation();
    CustList<CustList<ImplicitInfos>> getImplicits();
}
