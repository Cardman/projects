package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.opers.NamedArgumentOperation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.util.CustList;

public final class NameParametersFilter {
    private int index = -1;
    private boolean ok;
    private final CustList<NamedArgumentOperation> parameterFilter = new CustList<NamedArgumentOperation>();
    private final CustList<NamedArgumentOperation> parameterFilterErr = new CustList<NamedArgumentOperation>();
    private final CustList<OperationNode> positional = new CustList<OperationNode>();

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
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

    public CustList<NamedArgumentOperation> getParameterFilter() {
        return parameterFilter;
    }

    public CustList<NamedArgumentOperation> getParameterFilterErr() {
        return parameterFilterErr;
    }

    public CustList<OperationNode> getPositional() {
        return positional;
    }
}
