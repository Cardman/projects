package code.expressionlanguage.exec.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.types.AnalyzingType;
import code.expressionlanguage.types.ParserType;
import code.expressionlanguage.types.PartTypeUtil;
import code.util.*;

public final class ExecPartTypeUtil {
    private ExecPartTypeUtil(){
    }
    public static boolean processAnalyzeConstraintsExec(String _className, ContextEl _context) {
        boolean res_ = PartTypeUtil.checkParametersCount(_className, new StringMap<StringList>(), _context);
        if (!res_) {
            return false;
        }
        Ints indexes_ = ParserType.getQuickIndexes(_className);
        AnalyzingType loc_ = ParserType.analyzeQuickLocal(0, _className, indexes_);
        CustList<IntTreeMap< String>> dels_;
        dels_ = new CustList<IntTreeMap< String>>();
        ExecPartType root_ = ExecPartType.createQuickPartType(null, 0, loc_, loc_.getValues());
        addValues(root_, dels_, loc_);
        ExecPartType current_ = root_;
        while (true) {
            ExecPartType child_ = createQuickFirstChild(current_, loc_, dels_);
            if (child_ != null) {
                ((ExecParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                current_.analyzeTemplateExec(_context, dels_);
                if (current_.getAnalyzedType().isEmpty()) {
                    return false;
                }
                ExecPartType next_ = createQuickNextSibling(current_, loc_, dels_);
                ExecParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    par_.analyzeTemplateExec(_context, dels_);
                    stop_ = true;
                    break;
                }
                if (par_ == null) {
                    stop_ = true;
                    break;
                }
                dels_.removeLast();
                current_ = par_;
            }
            if (stop_) {
                break;
            }
        }
        return !root_.getAnalyzedType().isEmpty();
    }

    public static String processPrettyType(String _input) {
        StringBuilder out_ = new StringBuilder();
        Ints indexes_ = ParserType.getIndexesExec(_input);
        AnalyzingType loc_ = ParserType.analyzeLocalExec(0, _input, indexes_);
        CustList<IntTreeMap< String>> dels_;
        dels_ = new CustList<IntTreeMap< String>>();
        ExecPartType root_ = ExecPartType.createPartTypeExec(null, 0, loc_, loc_.getValues());
        addValues(root_, dels_, loc_);
        ExecPartType current_ = root_;
        while (true) {
            if (current_ instanceof ExecLeafPartType) {
                String t_ = ((ExecLeafPartType)current_).getTypeName();
                out_.append(t_);
            }
            ExecParentChildType parChild_ = createFirstChildExec(current_, loc_, dels_);
            ExecPartType child_ = parChild_.getChild();
            if (child_ != null) {
                out_.append(parChild_.getParentPartType().getPrettyBegin());
                parChild_.getParentPartType().appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                ExecPartType next_ = createNextSiblingExec(current_, loc_, dels_);
                ExecParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    out_.append(((ExecBinaryType) par_).getSeparator(current_.getIndex()));
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    out_.append(par_.getPrettyEnd());
                    stop_ = true;
                    break;
                }
                if (par_ == null) {
                    stop_ = true;
                    break;
                }
                out_.append(par_.getPrettyEnd());
                dels_.removeLast();
                current_ = par_;
            }
            if (stop_) {
                break;
            }
        }
        return out_.toString();
    }
    public static String processPrettySingleType(String _input) {
        StringBuilder out_ = new StringBuilder();
        Ints indexes_ = ParserType.getIndexesExec(_input);
        AnalyzingType loc_ = ParserType.analyzeLocalExec(0, _input, indexes_);
        CustList<IntTreeMap< String>> dels_;
        dels_ = new CustList<IntTreeMap< String>>();
        ExecPartType root_ = ExecPartType.createPartTypeExec(null, 0, loc_, loc_.getValues());
        addValues(root_, dels_, loc_);
        ExecPartType current_ = root_;
        while (true) {
            if (current_ instanceof ExecLeafPartType) {
                String t_ = ((ExecLeafPartType)current_).getTypeName();
                out_.append(t_);
            }
            ExecParentChildType parChild_ = createFirstChildExec(current_, loc_, dels_);
            ExecPartType child_ = parChild_.getChild();
            if (child_ != null) {
                out_.append(parChild_.getParentPartType().getPrettyBegin());
                parChild_.getParentPartType().appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                ExecPartType next_ = createNextSiblingExec(current_, loc_, dels_);
                ExecParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    out_.append(((ExecBinaryType) par_).getSingleSeparator(current_.getIndex()));
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    out_.append(par_.getPrettyEnd());
                    stop_ = true;
                    break;
                }
                if (par_ == null) {
                    stop_ = true;
                    break;
                }
                out_.append(par_.getPrettyEnd());
                dels_.removeLast();
                current_ = par_;
            }
            if (stop_) {
                break;
            }
        }
        return out_.toString();
    }
    public static String processExec(String _input,ContextEl _an) {
        StringBuilder out_ = new StringBuilder();
        Ints indexes_ = ParserType.getIndexesExec(_input);
        if (indexes_ == null) {
            return "";
        }
        AnalyzingType loc_ = ParserType.analyzeLocalExec(0, _input, indexes_);
        CustList<IntTreeMap< String>> dels_;
        dels_ = new CustList<IntTreeMap< String>>();
        ExecPartType root_ = ExecPartType.createPartTypeExec(null, 0, loc_, loc_.getValues());
        addValues(root_, dels_, loc_);
        ExecPartType current_ = root_;
        while (true) {
            if (current_ instanceof ExecLeafPartType) {
                ((ExecLeafPartType)current_).checkDynExistence(_an, dels_);
                String t_ = ((ExecLeafPartType)current_).exportHeader();
                if (t_.trim().isEmpty()) {
                    return "";
                }
                out_.append(t_);
            }
            ExecParentChildType parChild_ = createFirstChildExec(current_, loc_, dels_);
            ExecPartType child_ = parChild_.getChild();
            if (child_ != null) {
                out_.append(parChild_.getParentPartType().getBegin());
                parChild_.getParentPartType().appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                if (current_ instanceof ExecParentPartType) {
                    if (!((ExecParentPartType)current_).analyzeTree(_an, dels_)) {
                        return "";
                    }
                }
                ExecPartType next_ = createNextSiblingExec(current_, loc_, dels_);
                ExecParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    out_.append(((ExecBinaryType) par_).getSeparator(current_.getIndex()));
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    if (!par_.analyzeTree(_an, dels_)) {
                        return "";
                    }
                    out_.append(par_.getEnd());
                    stop_ = true;
                    break;
                }
                if (par_ == null) {
                    stop_ = true;
                    break;
                }
                out_.append(par_.getEnd());
                dels_.removeLast();
                current_ = par_;
            }
            if (stop_) {
                break;
            }
        }
        return out_.toString();
    }
    private static ExecParentChildType createFirstChildExec(ExecPartType _parent, AnalyzingType _analyze, CustList<IntTreeMap<String>> _dels) {
        if (!(_parent instanceof ExecParentPartType)) {
            return new ExecParentChildType(null,null);
        }
        ExecParentPartType par_ = (ExecParentPartType) _parent;
        int indexPar_ = 0;
        int off_ = 0;
        ExecPartType g_ = par_;
        for (int i = _dels.size()-1; i >= 0; i--) {
            IntTreeMap< String> befLast_;
            befLast_ = _dels.get(i);
            off_ += befLast_.getKey(indexPar_);
            indexPar_ = g_.getIndex();
            g_ = g_.getParent();
        }
        IntTreeMap< String> last_ = _dels.last();
        String v_ = last_.firstValue();
        AnalyzingType an_ = ParserType.analyzeLocalExec(off_, v_, _analyze.getIndexes());
        ExecPartType p_ = ExecPartType.createPartTypeExec(par_, 0, an_, last_);
        addValues(p_, _dels, an_);
        return new ExecParentChildType(par_,p_);
    }
    private static ExecPartType createQuickFirstChild(ExecPartType _parent, AnalyzingType _analyze, CustList<IntTreeMap<String>> _dels) {
        if (!(_parent instanceof ExecParentPartType)) {
            return null;
        }
        ExecParentPartType par_ = (ExecParentPartType) _parent;
        int indexPar_ = 0;
        int off_ = 0;
        ExecPartType g_ = par_;
        for (int i = _dels.size()-1; i >= 0; i--) {
            IntTreeMap< String> befLast_;
            befLast_ = _dels.get(i);
            off_ += befLast_.getKey(indexPar_);
            indexPar_ = g_.getIndex();
            g_ = g_.getParent();
        }
        IntTreeMap< String> last_ = _dels.last();
        String v_ = last_.firstValue();
        AnalyzingType an_ = ParserType.analyzeQuickLocal(off_, v_, _analyze.getIndexes());
        ExecPartType p_ = ExecPartType.createQuickPartType(par_, 0, an_, last_);
        addValues(p_, _dels, an_);
        return p_;
    }
    private static ExecPartType createQuickNextSibling(ExecPartType _parent, AnalyzingType _analyze, CustList<IntTreeMap<String>> _dels) {
        ExecParentPartType par_ = _parent.getParent();
        if (!(par_ instanceof ExecBinaryType)) {
            return null;
        }
        ExecBinaryType b_ = (ExecBinaryType) par_;
        int indexCur_ = _parent.getIndex();
        int indexNext_ = indexCur_ + 1;
        IntTreeMap< String> last_ = _dels.last();
        if (last_.size() <= indexNext_) {
            return null;
        }
        int indexPar_ = indexNext_;
        int off_ = 0;
        ExecPartType g_ = par_;
        for (int i = _dels.size()-1; i >= 0; i--) {
            IntTreeMap< String> befLast_;
            befLast_ = _dels.get(i);
            off_ += befLast_.getKey(indexPar_);
            indexPar_ = g_.getIndex();
            g_ = g_.getParent();
        }
        String v_ = last_.getValue(indexNext_);
        AnalyzingType an_ = ParserType.analyzeQuickLocal(off_, v_, _analyze.getIndexes());
        ExecPartType p_ = ExecPartType.createQuickPartType(b_,indexNext_, an_, last_);
        p_.setPreviousSibling(_parent);
        addValues(p_, _dels, an_);
        return p_;
    }
    private static ExecPartType createNextSiblingExec(ExecPartType _parent, AnalyzingType _analyze, CustList<IntTreeMap<String>> _dels) {
        ExecParentPartType par_ = _parent.getParent();
        if (!(par_ instanceof ExecBinaryType)) {
            return null;
        }
        ExecBinaryType b_ = (ExecBinaryType) par_;
        int indexCur_ = _parent.getIndex();
        int indexNext_ = indexCur_ + 1;
        IntTreeMap< String> last_ = _dels.last();
        if (last_.size() <= indexNext_) {
            return null;
        }
        int indexPar_ = indexNext_;
        int off_ = 0;
        ExecPartType g_ = par_;
        for (int i = _dels.size()-1; i >= 0; i--) {
            IntTreeMap< String> befLast_;
            befLast_ = _dels.get(i);
            off_ += befLast_.getKey(indexPar_);
            indexPar_ = g_.getIndex();
            g_ = g_.getParent();
        }
        String v_ = last_.getValue(indexNext_);
        AnalyzingType an_ = ParserType.analyzeLocalExec(off_, v_, _analyze.getIndexes());
        ExecPartType p_ = ExecPartType.createPartTypeExec(b_,indexNext_, an_, last_);
        p_.setPreviousSibling(_parent);
        addValues(p_, _dels, an_);
        return p_;
    }
    private static void addValues(ExecPartType _p, CustList<IntTreeMap< String>> _dels, AnalyzingType _an) {
        if (!(_p instanceof ExecParentPartType)) {
            return;
        }
        if (_p instanceof ExecTemplatePartType) {
            IntTreeMap<String> values_;
            values_ = new IntTreeMap< String>();
            values_.putAllMap(_an.getValues());
            values_.removeKey(values_.lastKey());
            _dels.add(values_);
        } else if (_p instanceof ExecInnerPartType) {
            IntTreeMap<String> values_;
            values_ = new IntTreeMap< String>();
            values_.putAllMap(_an.getValues());
            _dels.add(values_);
        } else {
            _dels.add(_an.getValues());
        }
    }
}
