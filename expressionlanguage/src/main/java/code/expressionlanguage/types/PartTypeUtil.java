package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.Options;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.util.UnknownClassName;
import code.sml.RowCol;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.StringList;

public final class PartTypeUtil {

    public static StringList getAllTypes(String _input, Options _options) {
        StringList list_ = new StringList();
        StringBuilder out_ = new StringBuilder();
        StringBuilder id_ = new StringBuilder();
        int count_ = 0;
        Numbers<Integer> indexes_ = ParserType.getIndexes(_input, _options);
        AnalyzingType loc_ = ParserType.analyzeLocal(0, _input, indexes_, _options);
        CustList<NatTreeMap<Integer, String>> dels_;
        dels_ = new CustList<NatTreeMap<Integer, String>>();
        boolean rem_ = loc_.isRemovedEmptyFirstChild();
        PartType root_ = PartType.createPartType(null, 0, 0, loc_, loc_.getValues(), rem_, _options);
        addValues(root_, dels_, loc_);
        PartType current_ = root_;
        while (true) {
            if (current_ == null) {
                break;
            }
            if (current_ instanceof LeafPartType) {
                if (count_ == 0) {
                    id_.append(((LeafPartType)current_).getTypeName());
                } else {
                    out_.append(((LeafPartType)current_).getTypeName());
                }
            }
            PartType child_ = createFirstChild(current_, loc_, dels_, _options);
            if (child_ != null) {
                if (count_ == 0) {
                    id_.append(((ParentPartType)current_).getBegin());
                } else {
                    out_.append(((ParentPartType)current_).getBegin());
                }
                ((ParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                PartType next_ = createNextSibling(current_, loc_, dels_, _options);
                ParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    if (par_ instanceof TemplatePartType) {
                        if (current_.getIndex() == 0) {
                            count_++;
                        }
                    }
                    if (count_ == 0) {
                        id_.append(par_.getSeparator(current_.getIndex()));
                    } else if (count_ > 1 || !(par_ instanceof TemplatePartType)) {
                        out_.append(par_.getSeparator(current_.getIndex()));
                    } else if (current_.getIndex() > 0) {
                        // par_ instanceof TemplatePartType
                        // end first argument
                        list_.add(out_.toString());
                        out_.delete(0, out_.length());
                    }
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    if (par_ instanceof TemplatePartType) {
                        list_.add(out_.toString());
                    }
                    stop_ = true;
                    break;
                }
                if (par_ == null) {
                    stop_ = true;
                    break;
                }
                if (par_ instanceof TemplatePartType) {
                    count_--;
                }
                if (count_ > 0) {
                    out_.append(par_.getEnd());
                } else if (par_ instanceof TemplatePartType) {
                    list_.add(out_.toString());
                    out_.delete(0, out_.length());
                }
                dels_.removeLast();
                current_ = par_;
            }
            if (stop_) {
                break;
            }
        }
        list_.add(0, id_.toString());
        return list_;
    }
    public static String processTypeHeaders(String _input,Analyzable _an, RootBlock _rooted,RowCol _location) {
        StringBuilder out_ = new StringBuilder();
        Options options_ = _an.getOptions();
        Numbers<Integer> indexes_ = ParserType.getIndexes(_input, options_);
        if (indexes_ == null) {
            UnknownClassName un_ = new UnknownClassName();
            un_.setClassName("");
            un_.setFileName(_rooted.getFile().getFileName());
            un_.setRc(_location);
            _an.getClasses().addError(un_);
            out_.append(_an.getStandards().getAliasObject());
            return out_.toString();
        }
        AnalyzingType loc_ = ParserType.analyzeLocal(0, _input, indexes_, options_);
        CustList<NatTreeMap<Integer, String>> dels_;
        dels_ = new CustList<NatTreeMap<Integer, String>>();
        boolean rem_ = loc_.isRemovedEmptyFirstChild();
        PartType root_ = PartType.createPartType(null, 0, 0, loc_, loc_.getValues(), rem_, options_);
        addValues(root_, dels_, loc_);
        PartType current_ = root_;
        while (true) {
            if (current_ == null) {
                break;
            }
            if (current_ instanceof LeafPartType) {
                out_.append(((LeafPartType)current_).getTypeName());
            }
            PartType child_ = createFirstChild(current_, loc_, dels_, options_);
            if (child_ != null) {
                out_.append(((ParentPartType)current_).getBegin());
                ((ParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                PartType next_ = createNextSibling(current_, loc_, dels_, options_);
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
    public static String processMapping(String _input,Analyzable _an, AccessingImportingBlock _rooted,RowCol _location) {
        StringBuilder out_ = new StringBuilder();
        Options options_ = _an.getOptions();
        Numbers<Integer> indexes_ = ParserType.getIndexes(_input, options_);
        if (indexes_ == null) {
            UnknownClassName un_ = new UnknownClassName();
            un_.setClassName("");
            un_.setFileName(_rooted.getFile().getFileName());
            un_.setRc(_location);
            _an.getClasses().addError(un_);
            out_.append(_an.getStandards().getAliasObject());
            return out_.toString();
        }
        AnalyzingType loc_ = ParserType.analyzeLocal(0, _input, indexes_, options_);
        CustList<NatTreeMap<Integer, String>> dels_;
        dels_ = new CustList<NatTreeMap<Integer, String>>();
        boolean rem_ = loc_.isRemovedEmptyFirstChild();
        PartType root_ = PartType.createPartType(null, 0, 0, loc_, loc_.getValues(), rem_, options_);
        addValues(root_, dels_, loc_);
        PartType current_ = root_;
        while (true) {
            if (current_ == null) {
                break;
            }
            if (current_ instanceof LeafPartType) {
                ((LeafPartType)current_).checkDirectExistence(_an, dels_, _rooted, _location);
                out_.append(((LeafPartType)current_).exportHeader());
            }
            PartType child_ = createFirstChild(current_, loc_, dels_, options_);
            if (child_ != null) {
                out_.append(((ParentPartType)current_).getBegin());
                ((ParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                PartType next_ = createNextSibling(current_, loc_, dels_, options_);
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
    public static String process(String _input,Analyzable _an, AccessingImportingBlock _rooted,RowCol _location) {
        StringBuilder out_ = new StringBuilder();
        Options options_ = _an.getOptions();
        Numbers<Integer> indexes_ = ParserType.getIndexes(_input, options_);
        if (indexes_ == null) {
            UnknownClassName un_ = new UnknownClassName();
            un_.setClassName("");
            un_.setFileName(_rooted.getFile().getFileName());
            un_.setRc(_location);
            _an.getClasses().addError(un_);
            out_.append(_an.getStandards().getAliasObject());
            return out_.toString();
        }
        AnalyzingType loc_ = ParserType.analyzeLocal(0, _input, indexes_, options_);
        CustList<NatTreeMap<Integer, String>> dels_;
        dels_ = new CustList<NatTreeMap<Integer, String>>();
        boolean rem_ = loc_.isRemovedEmptyFirstChild();
        PartType root_ = PartType.createPartType(null, 0, 0, loc_, loc_.getValues(), rem_, options_);
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
            PartType child_ = createFirstChild(current_, loc_, dels_, options_);
            if (child_ != null) {
                out_.append(((ParentPartType)current_).getBegin());
                ((ParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                PartType next_ = createNextSibling(current_, loc_, dels_, options_);
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
    static String processAnalyze(String _input, String _globalType, Analyzable _an, AccessingImportingBlock _rooted,RowCol _location) {
        return processAnalyze(_input, _globalType, _an, _rooted, true, _location);
    }
    public static String processAnalyze(String _input, String _globalType, Analyzable _an, AccessingImportingBlock _rooted, boolean _exact, RowCol _location) {
        Options options_ = _an.getOptions();
        Numbers<Integer> indexes_ = ParserType.getIndexes(_input, options_);
        if (indexes_ == null) {
            UnknownClassName un_ = new UnknownClassName();
            un_.setClassName("");
            un_.setFileName(_rooted.getFile().getFileName());
            un_.setRc(_location);
            _an.getClasses().addError(un_);
            return _an.getStandards().getAliasObject();
        }
        AnalyzingType loc_ = ParserType.analyzeLocal(0, _input, indexes_, options_);
        CustList<NatTreeMap<Integer, String>> dels_;
        dels_ = new CustList<NatTreeMap<Integer, String>>();
        boolean rem_ = loc_.isRemovedEmptyFirstChild();
        PartType root_ = PartType.createPartType(null, 0, 0, loc_, loc_.getValues(), rem_, options_);
        addValues(root_, dels_, loc_);
        PartType current_ = root_;
        while (true) {
            if (current_ == null) {
                break;
            }
            PartType child_ = createFirstChild(current_, loc_, dels_, options_);
            if (child_ != null) {
                ((ParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                current_.analyze(_an, dels_, _globalType, _rooted, _exact, _location);
                PartType next_ = createNextSibling(current_, loc_, dels_, options_);
                ParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    par_.analyze(_an, dels_, _globalType, _rooted, _exact, _location);
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
        return root_.getAnalyzedType();
    }

    public static String processAnalyze(String _input, String _globalType, Analyzable _an, AccessingImportingBlock _rooted, boolean _exact) {
        Options options_ = _an.getOptions();
        Numbers<Integer> indexes_ = ParserType.getIndexes(_input, options_);
        if (indexes_ == null) {
            return "";
        }
        AnalyzingType loc_ = ParserType.analyzeLocal(0, _input, indexes_, options_);
        CustList<NatTreeMap<Integer, String>> dels_;
        dels_ = new CustList<NatTreeMap<Integer, String>>();
        boolean rem_ = loc_.isRemovedEmptyFirstChild();
        PartType root_ = PartType.createPartType(null, 0, 0, loc_, loc_.getValues(), rem_, options_);
        addValues(root_, dels_, loc_);
        PartType current_ = root_;
        while (true) {
            if (current_ == null) {
                break;
            }
            PartType child_ = createFirstChild(current_, loc_, dels_, options_);
            if (child_ != null) {
                ((ParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                current_.analyze(_an, dels_, _globalType, _rooted, _exact);
                if (current_.getAnalyzedType().isEmpty()) {
                    return "";
                }
                PartType next_ = createNextSibling(current_, loc_, dels_, options_);
                ParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    par_.analyze(_an, dels_, _globalType, _rooted, _exact);
                    if (par_.getAnalyzedType().isEmpty()) {
                        return "";
                    }
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
        return root_.getAnalyzedType();
    }
    public static String processExec(String _input,ExecutableCode _an) {
        StringBuilder out_ = new StringBuilder();
        Numbers<Integer> indexes_ = ParserType.getIndexesExec(_input);
        if (indexes_ == null) {
            return "";
        }
        AnalyzingType loc_ = ParserType.analyzeLocalExec(0, _input, indexes_);
        CustList<NatTreeMap<Integer, String>> dels_;
        dels_ = new CustList<NatTreeMap<Integer, String>>();
        boolean rem_ = loc_.isRemovedEmptyFirstChild();
        PartType root_ = PartType.createPartTypeExec(null, 0, 0, loc_, loc_.getValues(), rem_);
        addValues(root_, dels_, loc_);
        PartType current_ = root_;
        while (true) {
            if (current_ == null) {
                break;
            }
            if (current_ instanceof LeafPartType) {
                ((LeafPartType)current_).checkDynExistence(_an, dels_);
                String t_ = ((LeafPartType)current_).exportHeader();
                if (t_.trim().isEmpty()) {
                    return "";
                }
                out_.append(t_);
            }
            PartType child_ = createFirstChildExec(current_, loc_, dels_);
            if (child_ != null) {
                out_.append(((ParentPartType)current_).getBegin());
                ((ParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                PartType next_ = createNextSiblingExec(current_, loc_, dels_);
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
    static PartType createFirstChild(PartType _parent, AnalyzingType _analyze, CustList<NatTreeMap<Integer, String>> _dels, Options _options) {
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
        AnalyzingType an_ = ParserType.analyzeLocal(off_, v_, _analyze.getIndexes(), _options);
        boolean rem_ = an_.isRemovedEmptyFirstChild();
        PartType p_ = PartType.createPartType(par_, 0, off_, an_, last_, rem_, _options);
        addValues(p_, _dels, an_);
        return p_;
    }
    static PartType createFirstChildExec(PartType _parent, AnalyzingType _analyze, CustList<NatTreeMap<Integer, String>> _dels) {
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
        AnalyzingType an_ = ParserType.analyzeLocalExec(off_, v_, _analyze.getIndexes());
        boolean rem_ = an_.isRemovedEmptyFirstChild();
        PartType p_ = PartType.createPartTypeExec(par_, 0, off_, an_, last_, rem_);
        addValues(p_, _dels, an_);
        return p_;
    }
    static PartType createNextSibling(PartType _parent, AnalyzingType _analyze, CustList<NatTreeMap<Integer, String>> _dels, Options _options) {
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
        AnalyzingType an_ = ParserType.analyzeLocal(off_, v_, _analyze.getIndexes(), _options);
        boolean rem_ = an_.isRemovedEmptyFirstChild();
        PartType p_ = PartType.createPartType(par_,indexNext_, off_, an_, last_, rem_, _options);
        p_.setPreviousSibling(_parent);
        addValues(p_, _dels, an_);
        return p_;
    }
    static PartType createNextSiblingExec(PartType _parent, AnalyzingType _analyze, CustList<NatTreeMap<Integer, String>> _dels) {
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
        AnalyzingType an_ = ParserType.analyzeLocalExec(off_, v_, _analyze.getIndexes());
        boolean rem_ = an_.isRemovedEmptyFirstChild();
        PartType p_ = PartType.createPartTypeExec(par_,indexNext_, off_, an_, last_, rem_);
        p_.setPreviousSibling(_parent);
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
