package code.expressionlanguage.analyze.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.AccessedBlock;
import code.expressionlanguage.exec.blocks.ExecAccessingImportingBlock;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.types.*;
import code.util.*;

public final class AnaPartTypeUtil {
    private AnaPartTypeUtil(){
    }

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
        AnaPartType root_ = AnaPartType.createPartType(null, 0, 0, loc_, loc_.getValues());
        addValues(root_, dels_, loc_);
        AnaPartType current_ = root_;
        while (true) {
            AnaPartType child_ = createFirstChild(current_, loc_, dels_);
            if (child_ != null) {
                ((AnaParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                if (current_ instanceof AnaEmptyPartType) {
                    return false;
                }
                if (current_ instanceof AnaNamePartType) {
                    String typeName_ = ((AnaLeafPartType) current_).getTypeName();
                    if (isKoForWord(typeName_,_excludedWords)) {
                        return false;
                    }
                }
                if (current_ instanceof AnaVariablePartType) {
                    if (current_.getParent() instanceof AnaInnerPartType) {
                        return false;
                    }
                    if (current_.getParent() instanceof AnaTemplatePartType && current_.getIndex() == 0) {
                        return false;
                    }
                    String typeName_ = ((AnaLeafPartType) current_).getTypeName();
                    if (isKoForWord(typeName_,_excludedWords)) {
                        return false;
                    }
                }
                if (current_ instanceof AnaEmptyWildCardPart
                        || current_ instanceof AnaWildCardPartType) {
                    if (!(current_.getParent() instanceof AnaTemplatePartType)) {
                        return false;
                    }
                }
                AnaPartType next_ = createNextSibling(current_, loc_, dels_);
                AnaParentPartType par_ = current_.getParent();
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
        if (root_ instanceof AnaWildCardPartType) {
            return root_.getParent() instanceof AnaTemplatePartType;
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
        return StringExpUtil.isDigit(typeName_.charAt(0));
    }
    private static void addIfLeaf(AnaPartType _p,CustList<AnaLeafPartType> _l) {
        if (!(_p instanceof AnaLeafPartType)) {
            return;
        }
        _l.add((AnaLeafPartType) _p);
    }

    static String processAnalyze(String _input, String _globalType, ContextEl _an, ExecRootBlock _rooted) {
        _an.getAnalyzing().setImportingTypes(_rooted);
        return processAnalyze(_input, false,_globalType,_an,_rooted,_rooted, 0,new CustList<PartOffset>());
    }
    public static String processAnalyze(String _input, boolean _rootName, String _globalType, ContextEl _an, AccessedBlock _local, AccessedBlock _rooted, int _loc, CustList<PartOffset> _offs) {
        Ints indexes_ = ParserType.getIndexes(_input, _an);
        if (indexes_ == null) {
            return "";
        }
        AnalyzingType loc_ = ParserType.analyzeLocal(0, _input, indexes_);
        CustList<IntTreeMap< String>> dels_;
        dels_ = new CustList<IntTreeMap< String>>();
        AnaPartType root_ = AnaPartType.createPartType(_an,_rootName,null, 0, 0, loc_, loc_.getValues());
        CustList<AnaLeafPartType> l_ = new CustList<AnaLeafPartType>();
        addIfLeaf(root_,l_);
        addValues(root_, dels_, loc_);
        AnaPartType current_ = root_;
        while (true) {
            AnaPartType child_ = createFirstChild(_an,_rootName,current_, loc_, dels_);
            if (child_ != null) {
                addIfLeaf(child_,l_);
                ((AnaParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                current_.analyze(_an, dels_, _globalType,_local, _rooted);
                if (current_.getAnalyzedType().isEmpty()) {
                    return "";
                }
                AnaPartType next_ = createNextSibling(_an,_rootName,current_, loc_, dels_);
                AnaParentPartType par_ = current_.getParent();
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
        String analyzedType_ = root_.getAnalyzedType();
        if (analyzedType_.isEmpty()) {
            return analyzedType_;
        }
        checkAccess(root_,_an);
        if (_loc > -1) {
            addTypeParts(_an, _rooted, "", _loc, _offs, l_);
        }
        return analyzedType_;
    }

    private static void checkAccessGeneral(ContextEl _an, AnaPartType _current) {
        if (_current instanceof AnaNamePartType) {
            ((AnaNamePartType) _current).checkAccessGeneral(_an);
        }
    }


    public static boolean processAnalyzeConstraints(String _className, StringMap<StringList> _inherit, ContextEl _context, boolean _exact) {
        if (!_exact && !_className.contains(Templates.TEMPLATE_BEGIN)) {
            return true;
        }
        boolean res_ = PartTypeUtil.checkParametersCount(_className, _inherit, _context);
        if (!res_) {
            return false;
        }
        Ints indexes_ = ParserType.getQuickIndexes(_className);
        AnalyzingType loc_ = ParserType.analyzeQuickLocal(0, _className, indexes_);
        CustList<IntTreeMap< String>> dels_;
        dels_ = new CustList<IntTreeMap< String>>();
        AnaPartType root_ = AnaPartType.createQuickPartType(null, 0, 0, loc_, loc_.getValues());
        addValues(root_, dels_, loc_);
        AnaPartType current_ = root_;
        while (true) {
            AnaPartType child_ = createQuickFirstChild(current_, loc_, dels_);
            if (child_ != null) {
                ((AnaParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                current_.analyzeTemplate(_context, dels_, _inherit);
                if (current_.getAnalyzedType().isEmpty()) {
                    return false;
                }
                AnaPartType next_ = createQuickNextSibling(current_, loc_, dels_);
                AnaParentPartType par_ = current_.getParent();
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

    static String processAnalyzeLine(String _input, ContextEl _an, ExecRootBlock _rooted) {
        _an.getAnalyzing().setImportingTypes(_rooted);
        return processAnalyzeLine(_input,false, _an,_rooted,_rooted, 0,new CustList<PartOffset>());
    }
    public static String processAnalyzeLine(String _input, boolean _rootName, ContextEl _an, AccessedBlock _local, AccessedBlock _rooted, int _loc, CustList<PartOffset> _offs) {
        CustList<AnaLeafPartType> ls_ = new CustList<AnaLeafPartType>();
        AnaPartType anaType_ = getAnalyzeLine(_input, new AlwaysReadyTypes(), _rootName, _an, _local, _rooted, _loc, ls_, _offs);
        if (anaType_ == null) {
            return "";
        }
        checkAccess(anaType_,_an);
        addTypeParts(_an, _rooted, "", _loc, _offs, ls_);
        return anaType_.getAnalyzedType();
    }
    public static String processAnalyzeLineInherits(String _input, ReadyTypes _ready, boolean _rootName, ContextEl _an, AccessedBlock _local, AccessedBlock _rooted, int _loc, CustList<PartOffset> _offs) {
        CustList<AnaLeafPartType> ls_ = new CustList<AnaLeafPartType>();
        AnaPartType anaType_ = getAnalyzeLine(_input, _ready, _rootName, _an, _local, _rooted, _loc, ls_, _offs);
        if (anaType_ == null) {
            return "";
        }
        String ana_ = anaType_.getAnalyzedType();
        if (!_ready.isReady(ana_)) {
            return "";
        }
        return ana_;
    }
    static AnaPartType getAnalyzeLine(String _input, ReadyTypes _ready, boolean _rootName, ContextEl _an, AccessedBlock _local, AccessedBlock _rooted, int _loc, CustList<AnaLeafPartType> _leaves, CustList<PartOffset> _offs) {
        Ints indexes_ = ParserType.getIndexes(_input, _an);
        if (indexes_ == null) {
            return null;
        }
        AnalyzingType loc_ = ParserType.analyzeLocal(0, _input, indexes_);
        CustList<IntTreeMap< String>> dels_;
        dels_ = new CustList<IntTreeMap< String>>();
        AnaPartType root_ = AnaPartType.createPartType(_an,_rootName,null, 0, 0, loc_, loc_.getValues());
        addIfLeaf(root_, _leaves);
        addValues(root_, dels_, loc_);
        AnaPartType current_ = root_;
        while (true) {
            AnaPartType child_ = createFirstChild(_an,_rootName,current_, loc_, dels_);
            if (child_ != null) {
                addIfLeaf(child_, _leaves);
                ((AnaParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                current_.analyzeLine(_an, _ready,dels_,_local, _rooted);
                if (current_.getAnalyzedType().isEmpty()) {
                    return null;
                }
                AnaPartType next_ = createNextSibling(_an,_rootName,current_, loc_, dels_);
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    addIfLeaf(next_, _leaves);
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    par_.analyzeLine(_an, _ready,dels_,_local, _rooted);
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
        return root_;
    }
    static void checkAccess(AnaPartType _root, ContextEl _an) {
        AnaPartType current_ = _root;
        while (true) {
            AnaPartType child_ = current_.getFirstChild();
            if (child_ != null) {
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                checkAccessGeneral(_an, current_);
                AnaPartType next_ = current_.getNextSibling();
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                if (par_ == _root || par_ == null) {
                    stop_ = true;
                    break;
                }
                current_ = par_;
            }
            if (stop_) {
                break;
            }
        }
    }

    static String processAnalyzeAccessibleId(String _input, ContextEl _an, AccessedBlock _rooted, String _refFileName, int _loc, CustList<PartOffset> _offs) {
        Ints indexes_ = ParserType.getIndexes(_input, _an);
        if (indexes_ == null) {
            return "";
        }
        AnalyzingType loc_ = ParserType.analyzeLocal(0, _input, indexes_);
        CustList<IntTreeMap< String>> dels_;
        dels_ = new CustList<IntTreeMap< String>>();
        AnaPartType root_ = AnaPartType.createPartType(_an,false, null, 0, 0, loc_, loc_.getValues());
        CustList<AnaLeafPartType> l_ = new CustList<AnaLeafPartType>();
        addIfLeaf(root_,l_);
        addValues(root_, dels_, loc_);
        AnaPartType current_ = root_;
        while (true) {
            AnaPartType child_ = createFirstChild(_an,false, current_, loc_, dels_);
            if (child_ != null) {
                addIfLeaf(child_,l_);
                ((AnaParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                current_.analyzeAccessibleId(_an, dels_, _rooted);
                if (current_.getAnalyzedType().isEmpty()) {
                    return "";
                }
                AnaPartType next_ = createNextSibling(_an,false, current_, loc_, dels_);
                AnaParentPartType par_ = current_.getParent();
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

    private static void addTypeParts(ContextEl _an, AccessedBlock _rooted,
                                     String _refFileName, int _loc, CustList<PartOffset> _offs, CustList<AnaLeafPartType> _leaves) {
        if (_an.isGettingParts()) {
            String curr_ = ((ExecBlock)_rooted).getFile().getRenderFileName();
            for (AnaLeafPartType l: _leaves){
                if (l instanceof AnaNamePartType) {
                    String type_ = l.getTypeName();
                    String imported_ = l.getAnalyzedType();
                    String idCl_ = StringExpUtil.getIdFromAllTypes(imported_);
                    GeneType g_ = _an.getClassBody(idCl_);
                    if (LinkageUtil.isFromCustFile(g_)) {
                        String ref_ = ((ExecRootBlock) g_).getFile().getRenderFileName();
                        String rel_ = LinkageUtil.relativize(curr_,ref_);
                        int id_ = ((ExecRootBlock) g_).getIdRowCol();
                        int begin_ = _loc + l.getIndexInType();
                        _offs.add(new PartOffset("<a title=\""+g_.getFullName()+"\" href=\""+rel_+"#m"+id_+"\">", begin_));
                        _offs.add(new PartOffset("</a>", begin_+type_.length()));
                    }
                }
                if (l instanceof AnaVariablePartType) {
                    String type_ = l.getTypeName();
                    String imported_ = l.getAnalyzedType();
                    Integer id_ = _an.getAnalyzing().getAvailableVariables().getVal(imported_.substring(1));
                    String rel_ = "";
                    if (!_refFileName.isEmpty()) {
                        rel_ = LinkageUtil.relativize(curr_,_refFileName);
                    }
                    int begin_ = _loc + l.getIndexInType();
                    _offs.add(new PartOffset("<a href=\""+rel_+"#m"+id_+"\">", begin_));
                    _offs.add(new PartOffset("</a>", begin_+type_.length()));
                }
            }
        }
    }

    private static AnaPartType createFirstChild(AnaPartType _parent, AnalyzingType _analyze, CustList<IntTreeMap<String>> _dels) {
        if (!(_parent instanceof AnaParentPartType)) {
            return null;
        }
        AnaParentPartType par_ = (AnaParentPartType) _parent;
        int indexPar_ = 0;
        int off_ = 0;
        AnaPartType g_ = par_;
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
        AnaPartType p_ = AnaPartType.createPartType(par_, 0, off_, an_, last_);
        addValues(p_, _dels, an_);
        return p_;
    }
    private static AnaPartType createFirstChild(ContextEl _an, boolean _rootName,AnaPartType _parent, AnalyzingType _analyze, CustList<IntTreeMap<String>> _dels) {
        if (!(_parent instanceof AnaParentPartType)) {
            return null;
        }
        AnaParentPartType par_ = (AnaParentPartType) _parent;
        int indexPar_ = 0;
        int off_ = 0;
        AnaPartType g_ = par_;
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
        AnaPartType p_ = AnaPartType.createPartType(_an,_rootName, par_, 0, off_, an_, last_);
        addValues(p_, _dels, an_);
        return p_;
    }

    private static AnaPartType createQuickFirstChild(AnaPartType _parent, AnalyzingType _analyze, CustList<IntTreeMap<String>> _dels) {
        if (!(_parent instanceof AnaParentPartType)) {
            return null;
        }
        AnaParentPartType par_ = (AnaParentPartType) _parent;
        int indexPar_ = 0;
        int off_ = 0;
        AnaPartType g_ = par_;
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
        AnaPartType p_ = AnaPartType.createQuickPartType(par_, 0, off_, an_, last_);
        addValues(p_, _dels, an_);
        return p_;
    }
    private static AnaPartType createNextSibling(AnaPartType _parent, AnalyzingType _analyze, CustList<IntTreeMap<String>> _dels) {
        AnaParentPartType par_ = _parent.getParent();
        if (!(par_ instanceof AnaBinaryType)) {
            return null;
        }
        AnaBinaryType b_ = (AnaBinaryType) par_;
        int indexCur_ = _parent.getIndex();
        int indexNext_ = indexCur_ + 1;
        IntTreeMap< String> last_ = _dels.last();
        if (last_.size() <= indexNext_) {
            return null;
        }
        int indexPar_ = indexNext_;
        int off_ = 0;
        AnaPartType g_ = par_;
        for (int i = _dels.size()-1; i >= 0; i--) {
            IntTreeMap< String> befLast_;
            befLast_ = _dels.get(i);
            off_ += befLast_.getKey(indexPar_);
            indexPar_ = g_.getIndex();
            g_ = g_.getParent();
        }
        String v_ = last_.getValue(indexNext_);
        AnalyzingType an_ = ParserType.analyzeLocal(off_, v_, _analyze.getIndexes());
        AnaPartType p_ = AnaPartType.createPartType(b_,indexNext_, off_, an_, last_);
        p_.setPreviousSibling(_parent);
        addValues(p_, _dels, an_);
        return p_;
    }
    private static AnaPartType createNextSibling(ContextEl _an,boolean _rootName, AnaPartType _parent, AnalyzingType _analyze, CustList<IntTreeMap<String>> _dels) {
        AnaParentPartType par_ = _parent.getParent();
        if (!(par_ instanceof AnaBinaryType)) {
            return null;
        }
        AnaBinaryType b_ = (AnaBinaryType) par_;
        int indexCur_ = _parent.getIndex();
        int indexNext_ = indexCur_ + 1;
        IntTreeMap< String> last_ = _dels.last();
        if (last_.size() <= indexNext_) {
            return null;
        }
        int indexPar_ = indexNext_;
        int off_ = 0;
        AnaPartType g_ = par_;
        for (int i = _dels.size()-1; i >= 0; i--) {
            IntTreeMap< String> befLast_;
            befLast_ = _dels.get(i);
            off_ += befLast_.getKey(indexPar_);
            indexPar_ = g_.getIndex();
            g_ = g_.getParent();
        }
        String v_ = last_.getValue(indexNext_);
        AnalyzingType an_ = ParserType.analyzeLocal(off_, v_, _analyze.getIndexes());
        AnaPartType p_ = AnaPartType.createPartType(_an,_rootName,b_,indexNext_, off_, an_, last_);
        p_.setPreviousSibling(_parent);
        addValues(p_, _dels, an_);
        return p_;
    }
    private static AnaPartType createQuickNextSibling(AnaPartType _parent, AnalyzingType _analyze, CustList<IntTreeMap<String>> _dels) {
        AnaParentPartType par_ = _parent.getParent();
        if (!(par_ instanceof AnaBinaryType)) {
            return null;
        }
        AnaBinaryType b_ = (AnaBinaryType) par_;
        int indexCur_ = _parent.getIndex();
        int indexNext_ = indexCur_ + 1;
        IntTreeMap< String> last_ = _dels.last();
        if (last_.size() <= indexNext_) {
            return null;
        }
        int indexPar_ = indexNext_;
        int off_ = 0;
        AnaPartType g_ = par_;
        for (int i = _dels.size()-1; i >= 0; i--) {
            IntTreeMap< String> befLast_;
            befLast_ = _dels.get(i);
            off_ += befLast_.getKey(indexPar_);
            indexPar_ = g_.getIndex();
            g_ = g_.getParent();
        }
        String v_ = last_.getValue(indexNext_);
        AnalyzingType an_ = ParserType.analyzeQuickLocal(off_, v_, _analyze.getIndexes());
        AnaPartType p_ = AnaPartType.createQuickPartType(b_,indexNext_, off_, an_, last_);
        p_.setPreviousSibling(_parent);
        addValues(p_, _dels, an_);
        return p_;
    }

    private static void addValues(AnaPartType _p, CustList<IntTreeMap< String>> _dels, AnalyzingType _an) {
        if (!(_p instanceof AnaParentPartType)) {
            return;
        }
        if (_p instanceof AnaTemplatePartType) {
            IntTreeMap<String> values_;
            values_ = new IntTreeMap< String>();
            values_.putAllMap(_an.getValues());
            values_.removeKey(values_.lastKey());
            _dels.add(values_);
        } else if (_p instanceof AnaInnerPartType) {
            IntTreeMap<String> values_;
            values_ = new IntTreeMap< String>();
            values_.putAllMap(_an.getValues());
            _dels.add(values_);
        } else {
            _dels.add(_an.getValues());
        }
    }
}
