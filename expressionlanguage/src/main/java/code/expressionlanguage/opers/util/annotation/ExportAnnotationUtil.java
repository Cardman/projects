package code.expressionlanguage.opers.util.annotation;

import code.expressionlanguage.structs.FieldableStruct;
import code.util.CustList;

public final class ExportAnnotationUtil {

    public static String exportAnnotation(FieldableStruct _str) {
        StringBuilder out_ = new StringBuilder();
        CustList<CustList<StackObject>> elts_;
        elts_ = new CustList<CustList<StackObject>>();
        InfoAnnotPart root_ = InfoAnnotPart.create(_str, null);
        CustList<StackObject> elt_ =((ParentAnnotPart)root_).getStack();
        InfoAnnotPart current_ = root_;
        if (elt_.isEmpty()) {
            out_.append(((ParentAnnotPart)root_).getBegin());
            out_.append(((ParentAnnotPart)root_).getEnd());
            return out_.toString();
        }
        CustList<InfoAnnotPart> vis_ = new CustList<InfoAnnotPart>();
        CustList<StackObject> visSt_ = new CustList<StackObject>();
        StackObject curStack_ = elt_.first();
        vis_.add(root_);
        while (true) {
            if (current_ != root_) {
                out_.append(curStack_.getPrefix());
            }
            if (current_ instanceof LeafAnnotPart) {
                out_.append(((LeafAnnotPart)current_).export());
            } else {
                ParentAnnotPart par_ = (ParentAnnotPart)current_;
                out_.append(par_.getBegin());
                CustList<StackObject> eltCur_ = par_.getStack();
                if (!eltCur_.isEmpty()) {
                    elts_.add(eltCur_);
                    StackObject f_ = eltCur_.first();
                    visSt_.add(f_);
                    InfoAnnotPart next_ = InfoAnnotPart.create(f_.getValue(), par_);
                    par_.append(next_);
                    current_ = next_;
                    curStack_ = f_;
                    continue;
                }
                out_.append(par_.getEnd());
            }
            while (true) {
                ParentAnnotPart par_ = current_.getParent();
                if (current_.getIndex() + 1 < elts_.last().size()) {
                    out_.append(ParentAnnotPart.PART_SEP);
                    StackObject value_ = elts_.last().get(current_.getIndex() + 1);
                    InfoAnnotPart next_ = InfoAnnotPart.create(value_.getValue(), par_);
                    par_.append(next_);
                    current_ = next_;
                    curStack_ = value_;
                    break;
                }
                out_.append(par_.getEnd());
                current_ = current_.getParent();
                curStack_ = visSt_.last();
                elts_.removeLast();
                visSt_.removeLast();
                if (elts_.isEmpty()) {
                    return out_.toString();
                }
            }
        }
    }
}
