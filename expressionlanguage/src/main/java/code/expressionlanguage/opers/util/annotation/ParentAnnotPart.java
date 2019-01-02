package code.expressionlanguage.opers.util.annotation;

import code.util.CustList;

abstract class ParentAnnotPart extends InfoAnnotPart {

    static final String PART_SEP = ",";
    private InfoAnnotPart first;

    void append(InfoAnnotPart _first) {
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

    abstract CustList<StackObject> getStack();

    abstract String getBegin();
    abstract String getEnd();
}
