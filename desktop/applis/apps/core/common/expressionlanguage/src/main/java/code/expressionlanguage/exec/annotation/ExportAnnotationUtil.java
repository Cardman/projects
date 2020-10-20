package code.expressionlanguage.exec.annotation;

import code.expressionlanguage.structs.AnnotationStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class ExportAnnotationUtil {

    private ExportAnnotationUtil(){
    }
    public static String exportAnnotation(String _infinity, String _nan,String _exp,Struct _str) {
        InfoAnnotPart root_ = InfoAnnotPart.create(_str, null);
        StringBuilder out_ = new StringBuilder();
        CustList<CustList<StackObject>> elts_ = new CustList<CustList<StackObject>>();
        InfoAnnotPart current_ = root_;
        StackObject curStack_ = null;
        StringMap<Integer> counts_ = new StringMap<Integer>();
        IdMap<Struct, String> cls_ = new IdMap<Struct, String>();
        while (current_ != null) {
            if (curStack_ != null) {
                out_.append(curStack_.getPrefix());
            }
            if (root_ instanceof ParentAnnotPart) {
                if (current_ instanceof LeafAnnotPart) {
                    out_.append(((LeafAnnotPart)current_).export(_infinity,_nan,_exp));
                } else {
                    ParentAnnotPart par_ = (ParentAnnotPart)current_;
                    out_.append(par_.getBegin());
                    CustList<StackObject> eltCur_ = par_.getStack();
                    if (!eltCur_.isEmpty()) {
                        Struct annot_ = null;
                        if (par_ instanceof CompleteAnnotPart) {
                            annot_ = ((CompleteAnnotPart)par_).getStruct();
                        }
                        String clName_ = getSeenStruct(cls_, annot_);
                        if (clName_ == null) {
                            String clGeneName_ = getStringClass(annot_);
                            int count_ = incrCount(counts_, clGeneName_);
                            if (annot_ instanceof AnnotationStruct) {
                                cls_.put(annot_, StringUtil.concatNbs(clGeneName_, count_));
                            }
                            elts_.add(eltCur_);
                            StackObject f_ = eltCur_.first();
                            Struct str_ = f_.getValue();
                            InfoAnnotPart next_ = InfoAnnotPart.create(str_, par_);
                            par_.append(next_);
                            current_ = next_;
                            curStack_ = f_;
                            continue;
                        }
                        out_.append(clName_);
                    }
                    out_.append(par_.getEnd());
                }
            }
            while (current_ != null) {
                ParentAnnotPart par_ = current_.getParent();
                if (hasNext(par_, elts_.size())) {
                    CustList<StackObject> lastStack_ = elts_.last();
                    int nextIndex_ = current_.getIndex() + 1;
                    if (nextIndex_ < lastStack_.size()) {
                        out_.append(ParentAnnotPart.PART_SEP);
                        StackObject value_ = lastStack_.get(nextIndex_);
                        Struct str_ = value_.getValue();
                        InfoAnnotPart next_ = InfoAnnotPart.create(str_, par_);
                        par_.append(next_);
                        current_ = next_;
                        curStack_ = value_;
                        break;
                    }
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

    public static boolean hasNext(Object _par, int _size) {
        return _par != null && _size != 0;
    }

    private static String getSeenStruct(IdMap<Struct, String> _cls, Struct _annot) {
        String clName_ = null;
        if (_annot instanceof AnnotationStruct) {
            clName_ = _cls.getVal(_annot);
        }
        return clName_;
    }

    private static String getStringClass(Struct _annot) {
        if (_annot instanceof AnnotationStruct) {
            return ((AnnotationStruct)_annot).getClassName();
        }
        return "";
    }

    private static int incrCount(StringMap<Integer> _counts, String _clName) {
        Integer count_ = _counts.getVal(_clName);
        if (count_ == null) {
            _counts.put(_clName,1);
            return 1;
        }
        _counts.put(_clName,count_+1);
        return count_+1;
    }
}
