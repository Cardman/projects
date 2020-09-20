package code.expressionlanguage.exec.annotation;

import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class ExportAnnotationUtil {

    private ExportAnnotationUtil(){
    }
    public static String exportAnnotation(String _infinity, String _nan,String _exp,Struct _str) {
        InfoAnnotPart root_ = InfoAnnotPart.create(_str, null);
        if (!(root_ instanceof ParentAnnotPart)) {
            return "";
        }
        StringBuilder out_ = new StringBuilder();
        CustList<CustList<StackObject>> elts_ = new CustList<CustList<StackObject>>();
        InfoAnnotPart current_ = root_;
        StackObject curStack_ = null;
        while (current_ != null) {
            if (curStack_ != null) {
                out_.append(curStack_.getPrefix());
            }
            if (current_ instanceof LeafAnnotPart) {
                out_.append(((LeafAnnotPart)current_).export(_infinity,_nan,_exp));
            } else {
                ParentAnnotPart par_ = (ParentAnnotPart)current_;
                out_.append(par_.getBegin());
                CustList<StackObject> eltCur_ = par_.getStack();
                if (!eltCur_.isEmpty()) {
                    elts_.add(eltCur_);
                    StackObject f_ = eltCur_.first();
                    InfoAnnotPart next_ = InfoAnnotPart.create(f_.getValue(), par_);
                    par_.append(next_);
                    current_ = next_;
                    curStack_ = f_;
                    continue;
                }
                out_.append(par_.getEnd());
            }
            while (current_ != null) {
                ParentAnnotPart par_ = current_.getParent();
                if (!elts_.isEmpty()&&current_.getIndex() + 1 < elts_.last().size()) {
                    out_.append(ParentAnnotPart.PART_SEP);
                    StackObject value_ = elts_.last().get(current_.getIndex() + 1);
                    InfoAnnotPart next_ = InfoAnnotPart.create(value_.getValue(), par_);
                    par_.append(next_);
                    current_ = next_;
                    curStack_ = value_;
                    break;
                }
                elts_.removeLast();
                current_ = par_;
                if (par_ != null) {
                    out_.append(par_.getEnd());
                }
            }
        }
        return out_.toString();
    }
}
