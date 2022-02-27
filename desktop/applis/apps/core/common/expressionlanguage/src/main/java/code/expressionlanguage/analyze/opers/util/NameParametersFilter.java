package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.opers.NamedArgumentOperation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.StaticCallAccessOperation;
import code.util.CustList;
import code.util.core.StringUtil;

public final class NameParametersFilter {
//    private int index = -1;
//    private boolean ok;
    private final CustList<NamedArgumentOperation> parameterFilter = new CustList<NamedArgumentOperation>();
    private final CustList<NamedArgumentOperation> parameterFilterErr = new CustList<NamedArgumentOperation>();
    private final CustList<OperationNode> positional = new CustList<OperationNode>();
    private final CustList<OperationNode> allOps = new CustList<OperationNode>();
    private StaticCallAccessOperation staticCallOp;
    private FormattedFilter formattedFilter = new FormattedFilter();

//    public int getIndex() {
//        return index;
//    }
//
//    public void setIndex(int _index) {
//        this.index = _index;
//    }

//    public boolean isOk() {
//        return ok;
//    }

//    public void setOk(boolean _ok) {
//        this.ok = _ok;
//    }
    public boolean isEmptyArg() {
        return positional.size() + parameterFilter.size() == 0;
    }

    public CustList<OperationNode> getAllOps() {
        return allOps;
    }

    public CustList<NamedArgumentOperation> getParameterFilter() {
        return parameterFilter;
    }

    public CustList<NamedArgumentOperation> getParameterFilterErr() {
        return parameterFilterErr;
    }

    public void addNamed(NamedArgumentOperation _op) {
        parameterFilter.add(_op);
        allOps.add(_op);
    }

    public void addPos(OperationNode _op) {
        positional.add(_op);
        allOps.add(_op);
    }
    public int posCount() {
        return positional.size();
    }
    public CustList<OperationNode> getPositional() {
        return positional;
    }

    public StaticCallAccessOperation getStaticCallOp() {
        return staticCallOp;
    }

    public void setStaticCallOp(StaticCallAccessOperation _staticCallOp) {
        this.staticCallOp = _staticCallOp;
    }

    public FormattedFilter getFormattedFilter() {
        return formattedFilter;
    }

    public void setFormattedFilter(FormattedFilter _formattedFilter) {
        this.formattedFilter = _formattedFilter;
    }

    public String getStaticCall() {
        return formattedFilter.getStCall();
    }

    public String getStaticCall(String _def) {
        if (StringUtil.quickEq("<>",formattedFilter.getStCall())) {
            return _def;
        }
        return formattedFilter.getStCall();
    }

    public String getReturnType() {
        return formattedFilter.getReturnType();
    }

}

