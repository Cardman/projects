package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.util.UnknownClassName;
import code.sml.RowCol;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.Numbers;

public final class PartTypeUtil {

    public static String processTypeHeaders(String _input,Analyzable _an, RootBlock _rooted,RowCol _location) {
        StringBuilder out_ = new StringBuilder();
        Numbers<Integer> indexes_ = ParserType.getIndexes(_input);
        if (indexes_ == null) {
            UnknownClassName un_ = new UnknownClassName();
            un_.setClassName("");
            un_.setFileName(_rooted.getFile().getFileName());
            un_.setRc(_location);
            _an.getClasses().addError(un_);
            out_.append(_an.getStandards().getAliasObject());
            return out_.toString();
        }
        AnalyzingType loc_ = ParserType.analyzeLocal(0, _input, indexes_);
        CustList<NatTreeMap<Integer, String>> dels_;
        dels_ = new CustList<NatTreeMap<Integer, String>>();
        boolean rem_ = loc_.isRemovedEmptyFirstChild();
        PartType root_ = PartType.createPartType(null, 0, 0, loc_, loc_.getValues(), rem_);
        addValues(root_, dels_, loc_);
        PartType current_ = root_;
        while (true) {
            if (current_ == null) {
                break;
            }
            if (current_ instanceof LeafPartType) {
                out_.append(((LeafPartType)current_).getTypeName());
            }
            PartType child_ = createFirstChild(current_, loc_, dels_);
            if (child_ != null) {
                out_.append(((ParentPartType)current_).getBegin());
                ((ParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                PartType next_ = createNextSibling(current_, loc_, dels_);
                ParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    out_.append(par_.getSeparator(current_.getIndex()));
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
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
    public static String process(String _input,Analyzable _an, RootBlock _rooted,RowCol _location) {
        StringBuilder out_ = new StringBuilder();
        Numbers<Integer> indexes_ = ParserType.getIndexes(_input);
        if (indexes_ == null) {
            UnknownClassName un_ = new UnknownClassName();
            un_.setClassName("");
            un_.setFileName(_rooted.getFile().getFileName());
            un_.setRc(_location);
            _an.getClasses().addError(un_);
            out_.append(_an.getStandards().getAliasObject());
            return out_.toString();
        }
        AnalyzingType loc_ = ParserType.analyzeLocal(0, _input, indexes_);
        CustList<NatTreeMap<Integer, String>> dels_;
        dels_ = new CustList<NatTreeMap<Integer, String>>();
        boolean rem_ = loc_.isRemovedEmptyFirstChild();
        PartType root_ = PartType.createPartType(null, 0, 0, loc_, loc_.getValues(), rem_);
        addValues(root_, dels_, loc_);
        PartType current_ = root_;
        while (true) {
            if (current_ == null) {
                break;
            }
            if (current_ instanceof LeafPartType) {
                ((LeafPartType)current_).checkExistence(_an, _rooted, _location);
                out_.append(((LeafPartType)current_).exportHeader());
            }
            PartType child_ = createFirstChild(current_, loc_, dels_);
            if (child_ != null) {
                out_.append(((ParentPartType)current_).getBegin());
                ((ParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                PartType next_ = createNextSibling(current_, loc_, dels_);
                ParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    out_.append(par_.getSeparator(current_.getIndex()));
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
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

    public static String processExec(String _input,ExecutableCode _an) {
        StringBuilder out_ = new StringBuilder();
        Numbers<Integer> indexes_ = ParserType.getIndexes(_input);
        if (indexes_ == null) {
            return "";
        }
        AnalyzingType loc_ = ParserType.analyzeLocal(0, _input, indexes_);
        CustList<NatTreeMap<Integer, String>> dels_;
        dels_ = new CustList<NatTreeMap<Integer, String>>();
        boolean rem_ = loc_.isRemovedEmptyFirstChild();
        PartType root_ = PartType.createPartType(null, 0, 0, loc_, loc_.getValues(), rem_);
        addValues(root_, dels_, loc_);
        PartType current_ = root_;
        while (true) {
            if (current_ == null) {
                break;
            }
            if (current_ instanceof LeafPartType) {
                ((LeafPartType)current_).checkDynExistence(_an);
                String t_ = ((LeafPartType)current_).exportHeader();
                if (t_.trim().isEmpty()) {
                    return "";
                }
                out_.append(t_);
            }
            PartType child_ = createFirstChild(current_, loc_, dels_);
            if (child_ != null) {
                out_.append(((ParentPartType)current_).getBegin());
                ((ParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                PartType next_ = createNextSibling(current_, loc_, dels_);
                ParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    out_.append(par_.getSeparator(current_.getIndex()));
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
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
    static PartType createFirstChild(PartType _parent, AnalyzingType _analyze, CustList<NatTreeMap<Integer, String>> _dels) {
        if (!(_parent instanceof ParentPartType)) {
            return null;
        }
        ParentPartType par_ = (ParentPartType) _parent;
        int indexPar_ = par_.getIndex();
        int off_ = 0;
        if (_dels.size() >= 2) {
            NatTreeMap<Integer, String> befLast_;
            befLast_ = _dels.get(_dels.size()-2);
            off_ = befLast_.getKey(indexPar_);
        }
        NatTreeMap<Integer, String> last_ = _dels.last();
        if (last_.isEmpty()) {
            return null;
        }
        int k_ = last_.firstKey();
        off_ += k_;
        String v_ = last_.firstValue();
        AnalyzingType an_ = ParserType.analyzeLocal(off_, v_, _analyze.getIndexes());
        boolean rem_ = an_.isRemovedEmptyFirstChild();
        PartType p_ = PartType.createPartType(par_, 0, off_, an_, last_, rem_);
        addValues(p_, _dels, an_);
        return p_;
    }
    static PartType createNextSibling(PartType _parent, AnalyzingType _analyze, CustList<NatTreeMap<Integer, String>> _dels) {
        ParentPartType par_ = _parent.getParent();
        if (par_ == null) {
            return null;
        }
        int indexCur_ = _parent.getIndex();
        int indexNext_ = indexCur_ + 1;
        NatTreeMap<Integer, String> last_ = _dels.last();
        if (last_.size() <= indexNext_) {
            return null;
        }
        int indexPar_ = par_.getIndex();
        int off_ = 0;
        if (_dels.size() >= 2) {
            NatTreeMap<Integer, String> befLast_;
            befLast_ = _dels.get(_dels.size()-2);
            off_ = befLast_.getKey(indexPar_);
        }
        int k_ = last_.getKey(indexNext_);
        off_ += k_;
        String v_ = last_.getValue(indexNext_);
        AnalyzingType an_ = ParserType.analyzeLocal(off_, v_, _analyze.getIndexes());
        boolean rem_ = an_.isRemovedEmptyFirstChild();
        PartType p_ = PartType.createPartType(par_,indexNext_, off_, an_, last_, rem_);
        addValues(p_, _dels, an_);
        return p_;
    }
    private static void addValues(PartType _p, CustList<NatTreeMap<Integer, String>> _dels, AnalyzingType _an) {
        if (!(_p instanceof ParentPartType)) {
            return;
        }
        if (_p instanceof TemplatePartType) {
            NatTreeMap<Integer,String> values_;
            values_ = new NatTreeMap<Integer, String>(_an.getValues());
            values_.removeKey(values_.lastKey());
            _dels.add(values_);
        } else if (_p instanceof InnerPartType) {
            NatTreeMap<Integer,String> values_;
            values_ = new NatTreeMap<Integer, String>(_an.getValues());
            if (values_.firstValue().isEmpty()) {
                values_.removeKey(values_.firstKey());
            }
            _dels.add(values_);
        } else {
            _dels.add(_an.getValues());
        }
    }
}
