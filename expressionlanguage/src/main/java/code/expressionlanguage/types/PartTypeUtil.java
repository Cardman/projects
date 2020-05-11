package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.*;
import code.util.CustList;
import code.util.*;
import code.util.Ints;
import code.util.StringList;

public final class PartTypeUtil {

    private PartTypeUtil() {}

    static boolean isCorrectType(String _input) {
        return isCorrectType(_input,new StringList());
    }

    public static boolean isCorrectType(String _input, CustList<String> _excludedWords) {
        Ints indexes_ = ParserType.getIndexes(_input, new StringList());
        if (indexes_ == null) {
            return false;
        }
        AnalyzingType loc_ = ParserType.analyzeLocal(0, _input, indexes_);
        CustList<IntTreeMap< String>> dels_;
        dels_ = new CustList<IntTreeMap< String>>();
        PartType root_ = PartType.createPartType(null, 0, 0, loc_, loc_.getValues());
        addValues(root_, dels_, loc_);
        PartType current_ = root_;
        while (true) {
            PartType child_ = createFirstChild(current_, loc_, dels_);
            if (child_ != null) {
                ((ParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                if (current_ instanceof EmptyPartType) {
                    return false;
                }
                if (current_ instanceof NamePartType) {
                    String typeName_ = ((LeafPartType) current_).getTypeName();
                    if (isKoForWord(typeName_,_excludedWords)) {
                        return false;
                    }
                }
                if (current_ instanceof VariablePartType) {
                    if (current_.getParent() instanceof InnerPartType) {
                        return false;
                    }
                    if (current_.getParent() instanceof TemplatePartType && current_.getIndex() == 0) {
                        return false;
                    }
                    String typeName_ = ((LeafPartType) current_).getTypeName();
                    if (isKoForWord(typeName_,_excludedWords)) {
                        return false;
                    }
                }
                if (current_ instanceof EmptyWildCardPart
                        || current_ instanceof WildCardPartType) {
                    if (!(current_.getParent() instanceof TemplatePartType)) {
                        return false;
                    }
                }
                PartType next_ = createNextSibling(current_, loc_, dels_);
                ParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
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
        if (root_ instanceof WildCardPartType) {
            return root_.getParent() instanceof TemplatePartType;
        }
        return true;
    }

    static boolean isKoForWord(String _type) {
        return isKoForWord(_type, new StringList());
    }
    private static boolean isKoForWord(String _type, CustList<String> _excludedWords) {
        String typeName_ = _type.trim();
        if (typeName_.startsWith("#")) {
            typeName_ = typeName_.substring("#".length()).trim();
        }
        if (!StringExpUtil.isTypeLeafPart(typeName_)) {
            return true;
        }
        if (StringList.contains(_excludedWords,typeName_)) {
            return true;
        }
        return ContextEl.isDigit(typeName_.charAt(0));
    }
    private static void addIfLeaf(PartType _p,CustList<LeafPartType> _l) {
        if (!(_p instanceof LeafPartType)) {
            return;
        }
        _l.add((LeafPartType) _p);
    }

    static String processAnalyze(String _input, String _globalType, Analyzable _an, AccessingImportingBlock _rooted) {
        return processAnalyze(_input, false,_globalType,_an,_rooted,_rooted, 0,new CustList<PartOffset>());
    }
    public static String processAnalyze(String _input, boolean _rootName, String _globalType, Analyzable _an, AccessingImportingBlock _local, AccessingImportingBlock _rooted, int _loc, CustList<PartOffset> _offs) {
        Ints indexes_ = ParserType.getIndexes(_input, _an);
        if (indexes_ == null) {
            return "";
        }
        AnalyzingType loc_ = ParserType.analyzeLocal(0, _input, indexes_);
        CustList<IntTreeMap< String>> dels_;
        dels_ = new CustList<IntTreeMap< String>>();
        PartType root_ = PartType.createPartType(_an,_rootName,null, 0, 0, loc_, loc_.getValues());
        CustList<LeafPartType> l_ = new CustList<LeafPartType>();
        addIfLeaf(root_,l_);
        addValues(root_, dels_, loc_);
        PartType current_ = root_;
        while (true) {
            PartType child_ = createFirstChild(_an,_rootName,current_, loc_, dels_);
            if (child_ != null) {
                addIfLeaf(child_,l_);
                ((ParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                current_.analyze(_an, dels_, _globalType,_local, _rooted);
                if (current_.getAnalyzedType().isEmpty()) {
                    return "";
                }
                PartType next_ = createNextSibling(_an,_rootName,current_, loc_, dels_);
                ParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    addIfLeaf(next_,l_);
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    par_.analyze(_an, dels_, _globalType,_local, _rooted);
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
        if (_loc > -1) {
            addTypeParts(_an, _rooted, "", _loc, _offs, l_);
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
        PartType root_ = PartType.createQuickPartType(null, 0, 0, loc_, loc_.getValues());
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
    static String processAnalyzeLine(String _input, Analyzable _an, AccessingImportingBlock _rooted) {
        return processAnalyzeLine(_input, new AlwaysReadyTypes(),false,"",_an,_rooted,_rooted, 0,new CustList<PartOffset>());
    }
    public static String processAnalyzeLine(String _input, ReadyTypes _ready, boolean _rootName, String _globalType, Analyzable _an, AccessingImportingBlock _local, AccessingImportingBlock _rooted, int _loc, CustList<PartOffset> _offs) {
        Ints indexes_ = ParserType.getIndexes(_input, _an);
        if (indexes_ == null) {
            return "";
        }
        AnalyzingType loc_ = ParserType.analyzeLocal(0, _input, indexes_);
        CustList<IntTreeMap< String>> dels_;
        dels_ = new CustList<IntTreeMap< String>>();
        PartType root_ = PartType.createPartType(_an,_rootName,null, 0, 0, loc_, loc_.getValues());
        CustList<LeafPartType> l_ = new CustList<LeafPartType>();
        addIfLeaf(root_,l_);
        addValues(root_, dels_, loc_);
        PartType current_ = root_;
        while (true) {
            PartType child_ = createFirstChild(_an,_rootName,current_, loc_, dels_);
            if (child_ != null) {
                addIfLeaf(child_,l_);
                ((ParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                current_.analyzeLine(_an, _ready,dels_, _globalType,_local, _rooted);
                if (current_.getAnalyzedType().isEmpty()) {
                    return "";
                }
                PartType next_ = createNextSibling(_an,_rootName,current_, loc_, dels_);
                ParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    addIfLeaf(next_,l_);
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    par_.analyzeLine(_an, _ready,dels_, _globalType,_local, _rooted);
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
        if (_loc > -1) {
            addTypeParts(_an, _rooted, "", _loc, _offs, l_);
        }
        String ana_ = root_.getAnalyzedType();
        if (!_ready.isReady(ana_)) {
            return "";
        }
        return ana_;
    }


    static String processAnalyzeAccessibleId(String _input, Analyzable _an, AccessingImportingBlock _rooted, String _refFileName, int _loc, CustList<PartOffset> _offs) {
        Ints indexes_ = ParserType.getIndexes(_input, _an);
        if (indexes_ == null) {
            return "";
        }
        AnalyzingType loc_ = ParserType.analyzeLocal(0, _input, indexes_);
        CustList<IntTreeMap< String>> dels_;
        dels_ = new CustList<IntTreeMap< String>>();
        PartType root_ = PartType.createPartType(_an,false, null, 0, 0, loc_, loc_.getValues());
        CustList<LeafPartType> l_ = new CustList<LeafPartType>();
        addIfLeaf(root_,l_);
        addValues(root_, dels_, loc_);
        PartType current_ = root_;
        while (true) {
            PartType child_ = createFirstChild(_an,false, current_, loc_, dels_);
            if (child_ != null) {
                addIfLeaf(child_,l_);
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
                PartType next_ = createNextSibling(_an,false, current_, loc_, dels_);
                ParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    addIfLeaf(next_,l_);
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
        addTypeParts(_an, _rooted, _refFileName, _loc, _offs, l_);
        return root_.getAnalyzedType();
    }

    private static void addTypeParts(Analyzable _an, AccessingImportingBlock _rooted,
                                     String _refFileName, int _loc, CustList<PartOffset> _offs, CustList<LeafPartType> _leaves) {
        if (_an.getContextEl().isCovering()) {
            String curr_ = ((Block)_rooted).getFile().getRenderFileName();
            for (LeafPartType l: _leaves){
                if (l instanceof NamePartType) {
                    String type_ = l.getTypeName();
                    String imported_ = l.getAnalyzedType();
                    String idCl_ = Templates.getIdFromAllTypes(imported_);
                    GeneType g_ = _an.getContextEl().getClassBody(idCl_);
                    if (ElUtil.isFromCustFile(g_)) {
                        String ref_ = ((RootBlock) g_).getFile().getRenderFileName();
                        String rel_ = ElUtil.relativize(curr_,ref_);
                        int id_ = ((RootBlock) g_).getIdRowCol();
                        int begin_ = _loc + l.getIndexInType();
                        _offs.add(new PartOffset("<a title=\""+g_.getFullName()+"\" href=\""+rel_+"#m"+id_+"\">", begin_));
                        _offs.add(new PartOffset("</a>", begin_+type_.length()));
                    }
                }
                if (l instanceof VariablePartType) {
                    String type_ = l.getTypeName();
                    String imported_ = l.getAnalyzedType();
                    Integer id_ = _an.getAnalyzing().getAvailableVariables().getVal(imported_.substring(1));
                    String rel_ = "";
                    if (!_refFileName.isEmpty()) {
                        rel_ = ElUtil.relativize(curr_,_refFileName);
                    }
                    int begin_ = _loc + l.getIndexInType();
                    _offs.add(new PartOffset("<a href=\""+rel_+"#m"+id_+"\">", begin_));
                    _offs.add(new PartOffset("</a>", begin_+type_.length()));
                }
            }
        }
    }
    public static String processPrettyType(String _input) {
        StringBuilder out_ = new StringBuilder();
        Ints indexes_ = ParserType.getIndexesExec(_input);
        AnalyzingType loc_ = ParserType.analyzeLocalExec(0, _input, indexes_);
        CustList<IntTreeMap< String>> dels_;
        dels_ = new CustList<IntTreeMap< String>>();
        PartType root_ = PartType.createPartTypeExec(null, 0, 0, loc_, loc_.getValues());
        addValues(root_, dels_, loc_);
        PartType current_ = root_;
        while (true) {
            if (current_ instanceof LeafPartType) {
                String t_ = ((LeafPartType)current_).getTypeName();
                out_.append(t_);
            }
            ParentChildType parChild_ = createFirstChildExec(current_, loc_, dels_);
            PartType child_ = parChild_.getChild();
            if (child_ != null) {
                out_.append(parChild_.getParentPartType().getPrettyBegin());
                parChild_.getParentPartType().appendChild(child_);
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
    public static String processPrettySingleType(String _input) {
        StringBuilder out_ = new StringBuilder();
        Ints indexes_ = ParserType.getIndexesExec(_input);
        AnalyzingType loc_ = ParserType.analyzeLocalExec(0, _input, indexes_);
        CustList<IntTreeMap< String>> dels_;
        dels_ = new CustList<IntTreeMap< String>>();
        PartType root_ = PartType.createPartTypeExec(null, 0, 0, loc_, loc_.getValues());
        addValues(root_, dels_, loc_);
        PartType current_ = root_;
        while (true) {
            if (current_ instanceof LeafPartType) {
                String t_ = ((LeafPartType)current_).getTypeName();
                out_.append(t_);
            }
            ParentChildType parChild_ = createFirstChildExec(current_, loc_, dels_);
            PartType child_ = parChild_.getChild();
            if (child_ != null) {
                out_.append(parChild_.getParentPartType().getPrettyBegin());
                parChild_.getParentPartType().appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                PartType next_ = createNextSiblingExec(current_, loc_, dels_);
                ParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    out_.append(((BinaryType) par_).getSingleSeparator(current_.getIndex()));
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
        PartType root_ = PartType.createPartTypeExec(null, 0, 0, loc_, loc_.getValues());
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
            ParentChildType parChild_ = createFirstChildExec(current_, loc_, dels_);
            PartType child_ = parChild_.getChild();
            if (child_ != null) {
                out_.append(parChild_.getParentPartType().getBegin());
                parChild_.getParentPartType().appendChild(child_);
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
    private static PartType createFirstChild(PartType _parent, AnalyzingType _analyze, CustList<IntTreeMap<String>> _dels) {
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
        AnalyzingType an_ = ParserType.analyzeLocal(off_, v_, _analyze.getIndexes());
        PartType p_ = PartType.createPartType(par_, 0, off_, an_, last_);
        addValues(p_, _dels, an_);
        return p_;
    }
    private static PartType createFirstChild(Analyzable _an, boolean _rootName,PartType _parent, AnalyzingType _analyze, CustList<IntTreeMap<String>> _dels) {
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
        AnalyzingType an_ = ParserType.analyzeLocal(off_, v_, _analyze.getIndexes());
        PartType p_ = PartType.createPartType(_an,_rootName, par_, 0, off_, an_, last_);
        addValues(p_, _dels, an_);
        return p_;
    }
    private static ParentChildType createFirstChildExec(PartType _parent, AnalyzingType _analyze, CustList<IntTreeMap<String>> _dels) {
        if (!(_parent instanceof ParentPartType)) {
            return new ParentChildType(null,null);
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
        PartType p_ = PartType.createPartTypeExec(par_, 0, off_, an_, last_);
        addValues(p_, _dels, an_);
        return new ParentChildType(par_,p_);
    }
    private static PartType createQuickFirstChild(PartType _parent, AnalyzingType _analyze, CustList<IntTreeMap<String>> _dels) {
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
        PartType p_ = PartType.createQuickPartType(par_, 0, off_, an_, last_);
        addValues(p_, _dels, an_);
        return p_;
    }
    private static PartType createNextSibling(PartType _parent, AnalyzingType _analyze, CustList<IntTreeMap<String>> _dels) {
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
        AnalyzingType an_ = ParserType.analyzeLocal(off_, v_, _analyze.getIndexes());
        PartType p_ = PartType.createPartType(b_,indexNext_, off_, an_, last_);
        p_.setPreviousSibling(_parent);
        addValues(p_, _dels, an_);
        return p_;
    }
    private static PartType createNextSibling(Analyzable _an,boolean _rootName, PartType _parent, AnalyzingType _analyze, CustList<IntTreeMap<String>> _dels) {
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
        AnalyzingType an_ = ParserType.analyzeLocal(off_, v_, _analyze.getIndexes());
        PartType p_ = PartType.createPartType(_an,_rootName,b_,indexNext_, off_, an_, last_);
        p_.setPreviousSibling(_parent);
        addValues(p_, _dels, an_);
        return p_;
    }
    private static PartType createQuickNextSibling(PartType _parent, AnalyzingType _analyze, CustList<IntTreeMap<String>> _dels) {
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
        PartType p_ = PartType.createQuickPartType(b_,indexNext_, off_, an_, last_);
        p_.setPreviousSibling(_parent);
        addValues(p_, _dels, an_);
        return p_;
    }
    private static PartType createNextSiblingExec(PartType _parent, AnalyzingType _analyze, CustList<IntTreeMap<String>> _dels) {
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
        PartType p_ = PartType.createPartTypeExec(b_,indexNext_, off_, an_, last_);
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
            _dels.add(values_);
        } else {
            _dels.add(_an.getValues());
        }
    }
}
