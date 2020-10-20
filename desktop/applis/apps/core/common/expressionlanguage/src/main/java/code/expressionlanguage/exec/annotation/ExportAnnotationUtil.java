package code.expressionlanguage.exec.annotation;

import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.structs.AnnotationStruct;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class ExportAnnotationUtil {

    private ExportAnnotationUtil(){
    }
    public static String exportAnnotation(String _infinity, String _nan,String _exp,Struct _str) {
        InfoAnnotPart root_ = InfoAnnotPart.create(_str, null, null);
        StringBuilder out_ = new StringBuilder();
        CustList<CustList<StackObject>> elts_ = new CustList<CustList<StackObject>>();
        InfoAnnotPart current_ = root_;
        StringMap<Integer> counts_ = new StringMap<Integer>();
        IdMap<Struct, String> cls_ = new IdMap<Struct, String>();
        while (current_ != null) {
            appendPrefix(out_, current_.getStackObject());
            InfoAnnotPart res_ = updateFirst(_infinity, _nan, _exp, root_, current_, elts_, counts_, cls_, out_);
            if (res_ != null) {
                current_ = res_;
                continue;
            }
            while (current_ != null) {
                InfoAnnotPart n_ = updateNext(current_, elts_, out_);
                if (n_ != null) {
                    current_ = n_;
                    break;
                }
                ParentAnnotPart par_ = current_.getParent();
                elts_.removeLast();
                current_ = par_;
                appendEnd(out_, par_);
            }
        }
        return out_.toString();
    }

    private static InfoAnnotPart updateFirst(String _infinity, String _nan,String _exp,
                                             InfoAnnotPart _root, InfoAnnotPart _current,
                                             CustList<CustList<StackObject>> _elts,
                                             StringMap<Integer> _counts, IdMap<Struct, String> _cls,
                                             StringBuilder _out) {
        if (!(_root instanceof ParentAnnotPart)) {
            return null;
        }
        if (_current instanceof LeafAnnotPart) {
            _out.append(((LeafAnnotPart)_current).export(_infinity,_nan,_exp));
            return null;
        }
        ParentAnnotPart par_ = (ParentAnnotPart)_current;
        _out.append(par_.getBegin());
        CustList<StackObject> eltCur_ = par_.getStack();
        if (!eltCur_.isEmpty()) {
            Struct cont_ = getStruct(par_);
            String clName_ = _cls.getVal(cont_);
            if (clName_ == null) {
                String clGeneName_ = getStringClass(cont_);
                int count_ = incrCount(_counts, clGeneName_);
                _cls.put(cont_, StringUtil.concatNbs(StringUtil.concat(clGeneName_,"/"), count_));
                _elts.add(eltCur_);
                StackObject f_ = eltCur_.first();
                Struct str_ = f_.getValue();
                InfoAnnotPart next_ = InfoAnnotPart.create(str_, f_, par_);
                par_.append(next_);
                return next_;
            }
            _out.append(clName_);
        }
        _out.append(par_.getEnd());
        return null;
    }
    private static InfoAnnotPart updateNext(InfoAnnotPart _current,
                                            CustList<CustList<StackObject>> _elts,
                                            StringBuilder _out) {
        ParentAnnotPart par_ = _current.getParent();
        if (!ExecTemplates.hasNext(par_, _elts.size())) {
            return null;
        }
        CustList<StackObject> lastStack_ = _elts.last();
        int nextIndex_ = _current.getIndex() + 1;
        if (nextIndex_ < lastStack_.size()) {
            _out.append(ParentAnnotPart.PART_SEP);
            StackObject value_ = lastStack_.get(nextIndex_);
            Struct str_ = value_.getValue();
            InfoAnnotPart next_ = InfoAnnotPart.create(str_, value_, par_);
            par_.append(next_);
            return next_;
        }
        return null;
    }
    private static void appendEnd(StringBuilder _out, ParentAnnotPart _par) {
        if (_par != null) {
            _out.append(_par.getEnd());
        }
    }

    private static void appendPrefix(StringBuilder _out, StackObject _curStack) {
        if (_curStack != null) {
            _out.append(_curStack.getPrefix());
        }
    }

    private static Struct getStruct(ParentAnnotPart _par) {
        Struct str_ = null;
        if (_par instanceof CompleteAnnotPart) {
            str_ = ((CompleteAnnotPart)_par).getStruct();
        }
        if (_par instanceof ArrayAnnotPart) {
            str_ = ((ArrayAnnotPart)_par).getArray();
        }
        return str_;
    }

    private static String getStringClass(Struct _annot) {
        String cl_ = "";
        if (_annot instanceof AnnotationStruct) {
            cl_ = ((AnnotationStruct)_annot).getClassName();
        }
        if (_annot instanceof ArrayStruct) {
            cl_ = ((ArrayStruct)_annot).getClassName();
        }
        return cl_;
    }

    private static int incrCount(StringMap<Integer> _counts, String _clName) {
        Integer count_ = _counts.getVal(_clName);
        if (count_ == null) {
            _counts.put(_clName,1);
            return 1;
        }
        int nextValue_ = count_ + 1;
        _counts.put(_clName, nextValue_);
        return nextValue_;
    }
}
