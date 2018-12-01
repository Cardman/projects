package code.expressionlanguage.opers.util.annotation;

import code.util.CustList;

abstract class ParentAnnotPart extends InfoAnnotPart {

    static final String PART_SEP = ",";
    private InfoAnnotPart first;

    @Override
    public InfoAnnotPart getFirst() {
        return first;
    }

    public void setFirst(InfoAnnotPart _first) {
        first = _first;
    }
    public void append(InfoAnnotPart _first) {
        if (first == null) {
            first = _first;
            return;
        }
        InfoAnnotPart f_ = first;
        int index_ = f_.getIndex();
        while (f_.getNext() != null) {
            f_ = f_.getNext();
            index_++;
        }
        index_++;
        _first.setIndex(index_);
        f_.setNext(_first);
    }

    int count() {
        int c_ = 0;
        InfoAnnotPart f_ = first;
        while (f_ != null) {
            f_ = f_.getNext();
            c_++;
        }
        return c_;
    }
    abstract CustList<StackObject> getStack();

    abstract String getBegin();
    abstract String getEnd();
}
