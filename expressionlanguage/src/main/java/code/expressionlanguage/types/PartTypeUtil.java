package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.AccessedBlock;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.options.Options;
import code.util.CustList;
import code.util.*;
import code.util.Ints;
import code.util.StringList;

public final class PartTypeUtil {

    private PartTypeUtil() {}
    public static StringList processAnalyzeDepends(String _input, int _index, Analyzable _an, RootBlock _rooted, boolean _exact) {
        Options options_ = _an.getOptions();
        Ints indexes_ = ParserType.getIndexes(_input, _an);
        if (indexes_ == null) {
            _an.getCurrentBadIndexes().add(0);
            return new StringList();
        }
        AnalyzingType loc_ = ParserType.analyzeLocal(0, _input, indexes_, options_);
        CustList<IntTreeMap< String>> dels_;
        dels_ = new CustList<IntTreeMap< String>>();
        boolean rem_ = loc_.isRemovedEmptyFirstChild();
        PartType root_ = PartType.createPartType(_an,null, 0, 0, loc_, loc_.getValues(), rem_, options_);
        addValues(root_, dels_, loc_);
        PartType current_ = root_;
        StringList allDeps_ = new StringList();
        while (true) {
            PartType child_ = createFirstChild(_an,current_, loc_, dels_, options_);
            if (child_ != null) {
                ((ParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                current_.analyzeDepends(_an, _index, dels_, _rooted, _exact);
                StringList deps_ = current_.getTypeNames();
                allDeps_.addAllElts(deps_);
                PartType next_ = createNextSibling(_an,current_, loc_, dels_, options_);
                ParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    par_.analyzeDepends(_an, _index, dels_, _rooted, _exact);
                    deps_ = par_.getTypeNames();
                    allDeps_.addAllElts(deps_);
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
        allDeps_.removeDuplicates();
        return allDeps_;
    }
    public static String processAnalyzeInherits(String _input, int _index, String _globalType, Analyzable _an, RootBlock _rooted, boolean _protectedInc) {
        Options options_ = _an.getOptions();
        Ints indexes_ = ParserType.getIndexes(_input, _an);
        if (indexes_ == null) {
            _an.getCurrentBadIndexes().add(0);
            return "";
        }
        AnalyzingType loc_ = ParserType.analyzeLocal(0, _input, indexes_, options_);
        CustList<IntTreeMap< String>> dels_;
        dels_ = new CustList<IntTreeMap< String>>();
        boolean rem_ = loc_.isRemovedEmptyFirstChild();
        PartType root_ = PartType.createPartType(_an,null, 0, 0, loc_, loc_.getValues(), rem_, options_);
        addValues(root_, dels_, loc_);
        PartType current_ = root_;
        while (true) {
            PartType child_ = createFirstChild(_an,current_, loc_, dels_, options_);
            if (child_ != null) {
                ((ParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                current_.analyzeInherits(_an, _index, dels_, _globalType, _rooted, _protectedInc);
                if (current_.getAnalyzedType().isEmpty()) {
                    return "";
                }
                PartType next_ = createNextSibling(_an,current_, loc_, dels_, options_);
                ParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    par_.analyzeInherits(_an, _index, dels_, _globalType, _rooted, _protectedInc);
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

    public static String processAnalyze(String _input, String _globalType, Analyzable _an, AccessingImportingBlock _rooted) {
        Options options_ = _an.getOptions();
        Ints indexes_ = ParserType.getIndexes(_input, _an);
        if (indexes_ == null) {
            _an.getCurrentBadIndexes().add(0);
            return "";
        }
        AnalyzingType loc_ = ParserType.analyzeLocal(0, _input, indexes_, options_);
        CustList<IntTreeMap< String>> dels_;
        dels_ = new CustList<IntTreeMap< String>>();
        boolean rem_ = loc_.isRemovedEmptyFirstChild();
        PartType root_ = PartType.createPartType(_an,null, 0, 0, loc_, loc_.getValues(), rem_, options_);
        addValues(root_, dels_, loc_);
        PartType current_ = root_;
        while (true) {
            PartType child_ = createFirstChild(_an,current_, loc_, dels_, options_);
            if (child_ != null) {
                ((ParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                current_.analyze(_an, dels_, _globalType, _rooted);
                if (current_.getAnalyzedType().isEmpty()) {
                    return "";
                }
                PartType next_ = createNextSibling(_an,current_, loc_, dels_, options_);
                ParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    par_.analyze(_an, dels_, _globalType, _rooted);
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

    public static boolean processAnalyzeConstraints(String _className, StringMap<StringList> _inherit, Analyzable _context, boolean _exact) {
        if (!_exact && !_className.contains(Templates.TEMPLATE_BEGIN)) {
            return true;
        }
        Ints indexes_ = ParserType.getQuickIndexes(_className);
        AnalyzingType loc_ = ParserType.analyzeQuickLocal(0, _className, indexes_);
        CustList<IntTreeMap< String>> dels_;
        dels_ = new CustList<IntTreeMap< String>>();
        boolean rem_ = loc_.isRemovedEmptyFirstChild();
        PartType root_ = PartType.createQuickPartType(null, 0, 0, loc_, loc_.getValues(), rem_);
        addValues(root_, dels_, loc_);
        PartType current_ = root_;
        while (true) {
            PartType child_ = createQuickFirstChild(current_, loc_, dels_);
            if (child_ != null) {
                ((ParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                current_.analyzeTemplate(_context, dels_, _inherit);
                if (current_.getAnalyzedType().isEmpty()) {
                    return false;
                }
                PartType next_ = createQuickNextSibling(current_, loc_, dels_);
                ParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    par_.analyzeTemplate(_context, dels_, _inherit);
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
    public static String processAnalyzeLine(String _input, String _globalType, Analyzable _an, AccessingImportingBlock _rooted) {
        Options options_ = _an.getOptions();
        Ints indexes_ = ParserType.getIndexes(_input, _an);
        if (indexes_ == null) {
            _an.getCurrentBadIndexes().add(0);
            return "";
        }
        AnalyzingType loc_ = ParserType.analyzeLocal(0, _input, indexes_, options_);
        CustList<IntTreeMap< String>> dels_;
        dels_ = new CustList<IntTreeMap< String>>();
        boolean rem_ = loc_.isRemovedEmptyFirstChild();
        PartType root_ = PartType.createPartType(_an,null, 0, 0, loc_, loc_.getValues(), rem_, options_);
        addValues(root_, dels_, loc_);
        PartType current_ = root_;
        while (true) {
            PartType child_ = createFirstChild(_an,current_, loc_, dels_, options_);
            if (child_ != null) {
                ((ParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                current_.analyzeLine(_an, dels_, _globalType, _rooted);
                if (current_.getAnalyzedType().isEmpty()) {
                    return "";
                }
                PartType next_ = createNextSibling(_an,current_, loc_, dels_, options_);
                ParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    par_.analyzeLine(_an, dels_, _globalType, _rooted);
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
    public static String processAnalyzeAccessibleId(String _input, Analyzable _an, AccessedBlock _rooted) {
        Options options_ = _an.getOptions();
        Ints indexes_ = ParserType.getIndexes(_input, _an);
        if (indexes_ == null) {
            _an.getCurrentBadIndexes().add(0);
            return "";
        }
        AnalyzingType loc_ = ParserType.analyzeLocal(0, _input, indexes_, options_);
        CustList<IntTreeMap< String>> dels_;
        dels_ = new CustList<IntTreeMap< String>>();
        boolean rem_ = loc_.isRemovedEmptyFirstChild();
        PartType root_ = PartType.createPartType(_an, null, 0, 0, loc_, loc_.getValues(), rem_, options_);
        addValues(root_, dels_, loc_);
        PartType current_ = root_;
        while (true) {
            PartType child_ = createFirstChild(_an, current_, loc_, dels_, options_);
            if (child_ != null) {
                ((ParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                current_.analyzeAccessibleId(_an, dels_, _rooted);
                if (current_.getAnalyzedType().isEmpty()) {
                    return "";
                }
                PartType next_ = createNextSibling(_an, current_, loc_, dels_, options_);
                ParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    par_.analyzeAccessibleId(_an, dels_, _rooted);
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
    public static String processPrettyType(String _input) {
        StringBuilder out_ = new StringBuilder();
        Ints indexes_ = ParserType.getIndexesExec(_input);
        AnalyzingType loc_ = ParserType.analyzeLocalExec(0, _input, indexes_);
        CustList<IntTreeMap< String>> dels_;
        dels_ = new CustList<IntTreeMap< String>>();
        boolean rem_ = loc_.isRemovedEmptyFirstChild();
        PartType root_ = PartType.createPartTypeExec(null, 0, 0, loc_, loc_.getValues(), rem_);
        addValues(root_, dels_, loc_);
        PartType current_ = root_;
        while (true) {
            if (current_ instanceof LeafPartType) {
                String t_ = ((LeafPartType)current_).getTypeName();
                out_.append(t_);
            }
            PartType child_ = createFirstChildExec(current_, loc_, dels_);
            if (child_ != null) {
                out_.append(((ParentPartType)current_).getPrettyBegin());
                ((ParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                PartType next_ = createNextSiblingExec(current_, loc_, dels_);
                ParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    out_.append(((BinaryType) par_).getSeparator(current_.getIndex()));
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
    public static String processExec(String _input,ExecutableCode _an) {
        StringBuilder out_ = new StringBuilder();
        Ints indexes_ = ParserType.getIndexesExec(_input);
        if (indexes_ == null) {
            return "";
        }
        AnalyzingType loc_ = ParserType.analyzeLocalExec(0, _input, indexes_);
        CustList<IntTreeMap< String>> dels_;
        dels_ = new CustList<IntTreeMap< String>>();
        boolean rem_ = loc_.isRemovedEmptyFirstChild();
        PartType root_ = PartType.createPartTypeExec(null, 0, 0, loc_, loc_.getValues(), rem_);
        addValues(root_, dels_, loc_);
        PartType current_ = root_;
        while (true) {
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
                if (current_ instanceof ParentPartType) {
                    if (!((ParentPartType)current_).analyzeTree(_an, dels_)) {
                        return "";
                    }
                }
                PartType next_ = createNextSiblingExec(current_, loc_, dels_);
                ParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    out_.append(((BinaryType) par_).getSeparator(current_.getIndex()));
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
    static PartType createFirstChild(Analyzable _an, PartType _parent, AnalyzingType _analyze, CustList<IntTreeMap< String>> _dels, Options _options) {
        if (!(_parent instanceof ParentPartType)) {
            return null;
        }
        ParentPartType par_ = (ParentPartType) _parent;
        int indexPar_ = 0;
        int off_ = 0;
        PartType g_ = par_;
        for (int i = _dels.size()-1; i >= 0; i--) {
            IntTreeMap< String> befLast_;
            befLast_ = _dels.get(i);
            off_ += befLast_.getKey(indexPar_);
            indexPar_ = g_.getIndex();
            g_ = g_.getParent();
        }
        IntTreeMap< String> last_ = _dels.last();
        String v_ = last_.firstValue();
        AnalyzingType an_ = ParserType.analyzeLocal(off_, v_, _analyze.getIndexes(), _options);
        boolean rem_ = an_.isRemovedEmptyFirstChild();
        PartType p_ = PartType.createPartType(_an, par_, 0, off_, an_, last_, rem_, _options);
        addValues(p_, _dels, an_);
        return p_;
    }
    static PartType createFirstChildExec(PartType _parent, AnalyzingType _analyze, CustList<IntTreeMap< String>> _dels) {
        if (!(_parent instanceof ParentPartType)) {
            return null;
        }
        ParentPartType par_ = (ParentPartType) _parent;
        int indexPar_ = 0;
        int off_ = 0;
        PartType g_ = par_;
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
        boolean rem_ = an_.isRemovedEmptyFirstChild();
        PartType p_ = PartType.createPartTypeExec(par_, 0, off_, an_, last_, rem_);
        addValues(p_, _dels, an_);
        return p_;
    }
    static PartType createQuickFirstChild(PartType _parent, AnalyzingType _analyze, CustList<IntTreeMap< String>> _dels) {
        if (!(_parent instanceof ParentPartType)) {
            return null;
        }
        ParentPartType par_ = (ParentPartType) _parent;
        int indexPar_ = 0;
        int off_ = 0;
        PartType g_ = par_;
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
        boolean rem_ = an_.isRemovedEmptyFirstChild();
        PartType p_ = PartType.createQuickPartType(par_, 0, off_, an_, last_, rem_);
        addValues(p_, _dels, an_);
        return p_;
    }
    static PartType createNextSibling(Analyzable _an, PartType _parent, AnalyzingType _analyze, CustList<IntTreeMap< String>> _dels, Options _options) {
        ParentPartType par_ = _parent.getParent();
        if (!(par_ instanceof BinaryType)) {
            return null;
        }
        BinaryType b_ = (BinaryType) par_;
        int indexCur_ = _parent.getIndex();
        int indexNext_ = indexCur_ + 1;
        IntTreeMap< String> last_ = _dels.last();
        if (last_.size() <= indexNext_) {
            return null;
        }
        int indexPar_ = indexNext_;
        int off_ = 0;
        PartType g_ = par_;
        for (int i = _dels.size()-1; i >= 0; i--) {
            IntTreeMap< String> befLast_;
            befLast_ = _dels.get(i);
            off_ += befLast_.getKey(indexPar_);
            indexPar_ = g_.getIndex();
            g_ = g_.getParent();
        }
        String v_ = last_.getValue(indexNext_);
        AnalyzingType an_ = ParserType.analyzeLocal(off_, v_, _analyze.getIndexes(), _options);
        boolean rem_ = an_.isRemovedEmptyFirstChild();
        PartType p_ = PartType.createPartType(_an,b_,indexNext_, off_, an_, last_, rem_, _options);
        p_.setPreviousSibling(_parent);
        addValues(p_, _dels, an_);
        return p_;
    }
    static PartType createQuickNextSibling(PartType _parent, AnalyzingType _analyze, CustList<IntTreeMap< String>> _dels) {
        ParentPartType par_ = _parent.getParent();
        if (!(par_ instanceof BinaryType)) {
            return null;
        }
        BinaryType b_ = (BinaryType) par_;
        int indexCur_ = _parent.getIndex();
        int indexNext_ = indexCur_ + 1;
        IntTreeMap< String> last_ = _dels.last();
        if (last_.size() <= indexNext_) {
            return null;
        }
        int indexPar_ = indexNext_;
        int off_ = 0;
        PartType g_ = par_;
        for (int i = _dels.size()-1; i >= 0; i--) {
            IntTreeMap< String> befLast_;
            befLast_ = _dels.get(i);
            off_ += befLast_.getKey(indexPar_);
            indexPar_ = g_.getIndex();
            g_ = g_.getParent();
        }
        String v_ = last_.getValue(indexNext_);
        AnalyzingType an_ = ParserType.analyzeQuickLocal(off_, v_, _analyze.getIndexes());
        boolean rem_ = an_.isRemovedEmptyFirstChild();
        PartType p_ = PartType.createQuickPartType(b_,indexNext_, off_, an_, last_, rem_);
        p_.setPreviousSibling(_parent);
        addValues(p_, _dels, an_);
        return p_;
    }
    static PartType createNextSiblingExec(PartType _parent, AnalyzingType _analyze, CustList<IntTreeMap< String>> _dels) {
        ParentPartType par_ = _parent.getParent();
        if (!(par_ instanceof BinaryType)) {
            return null;
        }
        BinaryType b_ = (BinaryType) par_;
        int indexCur_ = _parent.getIndex();
        int indexNext_ = indexCur_ + 1;
        IntTreeMap< String> last_ = _dels.last();
        if (last_.size() <= indexNext_) {
            return null;
        }
        int indexPar_ = indexNext_;
        int off_ = 0;
        PartType g_ = par_;
        for (int i = _dels.size()-1; i >= 0; i--) {
            IntTreeMap< String> befLast_;
            befLast_ = _dels.get(i);
            off_ += befLast_.getKey(indexPar_);
            indexPar_ = g_.getIndex();
            g_ = g_.getParent();
        }
        String v_ = last_.getValue(indexNext_);
        AnalyzingType an_ = ParserType.analyzeLocalExec(off_, v_, _analyze.getIndexes());
        boolean rem_ = an_.isRemovedEmptyFirstChild();
        PartType p_ = PartType.createPartTypeExec(b_,indexNext_, off_, an_, last_, rem_);
        p_.setPreviousSibling(_parent);
        addValues(p_, _dels, an_);
        return p_;
    }
    private static void addValues(PartType _p, CustList<IntTreeMap< String>> _dels, AnalyzingType _an) {
        if (!(_p instanceof ParentPartType)) {
            return;
        }
        if (_p instanceof TemplatePartType) {
            IntTreeMap<String> values_;
            values_ = new IntTreeMap< String>();
            values_.putAllMap(_an.getValues());
            values_.removeKey(values_.lastKey());
            _dels.add(values_);
        } else if (_p instanceof InnerPartType) {
            IntTreeMap<String> values_;
            values_ = new IntTreeMap< String>();
            values_.putAllMap(_an.getValues());
            if (values_.firstValue().trim().isEmpty()) {
                values_.removeKey(values_.firstKey());
            }
            _dels.add(values_);
        } else {
            _dels.add(_an.getValues());
        }
    }
}
