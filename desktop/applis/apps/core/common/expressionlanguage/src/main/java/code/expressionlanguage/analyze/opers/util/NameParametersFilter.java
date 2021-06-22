package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.opers.NamedArgumentOperation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.StaticCallAccessOperation;
import code.util.CustList;

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
    public CustList<OperationNode> getAll() {
        CustList<OperationNode> out_ = new CustList<OperationNode>();
        CustList<NamedArgumentOperation> named_ = new CustList<NamedArgumentOperation>();
        for (OperationNode c: positional) {
            out_.add(c);
        }
        for (NamedArgumentOperation c: parameterFilter) {
            named_.add(c);
        }
        while (!named_.isEmpty()) {
            NamedArgumentOperation min_ = named_.first();
            int minIndex_ = min_.getIndex();
            int size_ = named_.size();
            int i_ = 0;
            for (int i = 1; i < size_; i++) {
                NamedArgumentOperation elt_ = named_.get(i);
                int index_ = elt_.getIndex();
                if (index_ < minIndex_) {
                    min_ = elt_;
                    minIndex_ = index_;
                    i_ = i;
                }
            }
            out_.add(min_);
            named_.remove(i_);
        }
        return out_;
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

    public String getReturnType() {
        return formattedFilter.getReturnType();
    }

}

