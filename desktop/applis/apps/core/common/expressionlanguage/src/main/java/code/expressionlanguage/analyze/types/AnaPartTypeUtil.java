package code.expressionlanguage.analyze.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.exec.blocks.AccessedBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.PartOffset;
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
        return processAnalyze(_input, false,_globalType,_an,_rooted,_rooted, 0,new CustList<PartOffset>()).getResult();
    }
    public static AnaResultPartType processAnalyze(String _input, boolean _rootName, String _globalType, ContextEl _an, AccessedBlock _local, AccessedBlock _rooted, int _loc, CustList<PartOffset> _offs) {
        CustList<AnaLeafPartType> l_ = new CustList<AnaLeafPartType>();
        _an.getAnalyzing().setLocalInType(_loc);
        _an.getAnalyzing().setRefFileName("");
        AnaResultPartType res_ = processAccessAnalyze(_input, _rootName, _globalType, _an, _local, _rooted, _loc, _offs, l_);
        String analyzedType_ = res_.getResult();
        if (analyzedType_.isEmpty()) {
            return res_;
        }
        AnaPartType r_ = res_.getPartType();
        checkAccess(r_,_globalType,_an);
        return new AnaResultPartType(analyzedType_,r_);
    }
    public static AnaResultPartType processAccAnalyze(String _input, boolean _rootName, String _globalType, ContextEl _an, AccessedBlock _local, AccessedBlock _rooted, int _loc, CustList<PartOffset> _offs) {
        CustList<AnaLeafPartType> l_ = new CustList<AnaLeafPartType>();
        _an.getAnalyzing().setLocalInType(_loc);
        _an.getAnalyzing().setRefFileName("");
        AnaResultPartType res_ = processAccessAnalyze(_input, _rootName, _globalType, _an, _local, _rooted, _loc, _offs, l_);
        String analyzedType_ = res_.getResult();
        if (analyzedType_.isEmpty()) {
            return res_;
        }
        AnaPartType r_ = res_.getPartType();
        return new AnaResultPartType(analyzedType_,r_);
    }
    public static AnaResultPartType processAnalyzeHeader(String _input, boolean _rootName, String _globalType, ContextEl _an, AccessedBlock _local, AccessedBlock _rooted, int _loc, CustList<PartOffset> _offs) {
        CustList<AnaLeafPartType> l_ = new CustList<AnaLeafPartType>();
        if (_loc > -1) {
            _an.getAnalyzing().setLocalInType(_loc);
            _an.getAnalyzing().setRefFileName("");
        }
        AnaResultPartType res_ = processAccessAnalyze(_input, _rootName, _globalType, _an, _local, _rooted, _loc, _offs, l_);
        String analyzedType_ = res_.getResult();
        if (analyzedType_.isEmpty()) {
            return res_;
        }
        AnaPartType r_ = res_.getPartType();
        checkAccess(r_,_globalType,_an);
        if (_loc > -1) {
            appendParts(r_,_offs,_an);
        }
        return new AnaResultPartType(analyzedType_,r_);
    }
    public static AnaResultPartType processAccessAnalyze(String _input, boolean _rootName, String _globalType, ContextEl _an, AccessedBlock _local, AccessedBlock _rooted, int _loc, CustList<PartOffset> _offs) {
        return processAccessAnalyze(_input,_rootName,_globalType,_an,_local,_rooted,_loc,_offs, new CustList<AnaLeafPartType>());
    }
    public static AnaResultPartType processAccessAnalyze(String _input, boolean _rootName, String _globalType, ContextEl _an, AccessedBlock _local, AccessedBlock _rooted, int _loc, CustList<PartOffset> _offs, CustList<AnaLeafPartType> _l) {
        Ints indexes_ = ParserType.getIndexes(_input, _an);
        if (indexes_ == null) {
            String err_ = FoundErrorInterpret.buildARError(_an.getAnalysisMessages().getUnknownType(), _input);
            String pref_ = "<a title=\""+err_+"\" class=\"e\">";
            _offs.add(new PartOffset(pref_,_loc));
            _offs.add(new PartOffset("</a>",_loc+_input.length()));
            return new AnaResultPartType("",null);
        }
        AnalyzingType loc_ = ParserType.analyzeLocal(0, _input, indexes_);
        CustList<IntTreeMap< String>> dels_;
        dels_ = new CustList<IntTreeMap< String>>();
        AnaPartType root_ = AnaPartType.createPartType(_an,_rootName,null, 0, 0, loc_, loc_.getValues());
        root_.setLength(_input.length());
        addIfLeaf(root_, _l);
        addValues(root_, dels_, loc_);
        AnaPartType current_ = root_;
        while (true) {
            AnaPartType child_ = createFirstChild(_an,_rootName,current_, loc_, dels_);
            if (child_ != null) {
                addIfLeaf(child_, _l);
                ((AnaParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                current_.analyze(_an, dels_, _globalType,_local, _rooted);
                if (current_.getAnalyzedType().isEmpty()) {
                    current_.processInexistType(_an,_input);
                    appendError(current_,_offs,_an);
                    return new AnaResultPartType("",null);
                }
                processLeafOffsets(_an, _rooted, current_);
                AnaPartType next_ = createNextSibling(_an,_rootName,current_, loc_, dels_);
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    addIfLeaf(next_, _l);
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    par_.analyze(_an, dels_, _globalType,_local, _rooted);
                    if (par_.getAnalyzedType().isEmpty()) {
                        par_.processInexistType(_an,_input);
                        appendError(current_,_offs,_an);
                        return new AnaResultPartType("",null);
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
        return new AnaResultPartType(root_.getAnalyzedType(),root_);
    }
    private static void checkAccessGeneral(ContextEl _an, String _gl, AnaPartType _current) {
        if (_current instanceof AnaNamePartType) {
            ((AnaNamePartType) _current).checkAccessGeneral(_an);
            ((AnaNamePartType) _current).processInaccessibleOffsets(_an,_gl);
        }
    }

    public static boolean processAnalyzeConstraints(AnaResultPartType _className, StringMap<StringList> _inherit, ContextEl _context, boolean _exact) {
        return processAnalyzeConstraints(_className,_inherit,_context,_exact,new CustList<PartOffset>());
    }
    public static boolean processAnalyzeConstraints(AnaResultPartType _className, StringMap<StringList> _inherit, ContextEl _context, boolean _exact, CustList<PartOffset> _parts) {
        AnaPartType root_ = _className.getPartType();
        if (root_ == null) {
            return false;
        }
        if (!_exact && !_className.getResult().contains(Templates.TEMPLATE_BEGIN)) {
            appendParts(root_,_parts,_context);
            return true;
        }
        boolean res_ = checkParametersCount(root_, _context,_parts);
        if (!res_) {
            return false;
        }
        boolean out_ = checkConstrains(root_, _inherit, _context);
        if (out_) {
            appendParts(root_,_parts,_context);
        }
        return out_;
    }

    public static boolean checkParametersCount(AnaPartType _root, ContextEl _context, CustList<PartOffset> _parts) {
        AnaPartType current_ = _root;
        while (true) {
            AnaPartType child_ = current_.getFirstChild();
            if (child_ != null) {
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                if (isNotCorrectParam(_context, current_)) {
                    current_.processBadFormedOffsets(_context);
                    appendError(current_,_parts,_context);
                    return false;
                }
                AnaPartType next_ = current_.getNextSibling();
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                if (par_ == _root) {
                    if (isNotCorrectParam(_context, par_)) {
                        par_.processBadFormedOffsets(_context);
                        appendError(par_,_parts,_context);
                        return false;
                    }
                    stop_ = true;
                    break;
                }
                if (par_ == null) {
                    stop_ = true;
                    break;
                }
                current_ = par_;
            }
            if (stop_) {
                break;
            }
        }
        return true;
    }

    private static boolean isNotCorrectParam(ContextEl _context, AnaPartType _current) {
        return !skip(_current) && !Templates.correctNbParameters(_current.getAnalyzedType(), _context);
    }

    private static boolean skip(AnaPartType _current) {
        if (_current.getParent() instanceof AnaInnerPartType) {
            return true;
        }
        if (_current.getParent() instanceof AnaTemplatePartType && _current.getIndex() == 0) {
            return true;
        }
        return skipByClass(_current);
    }

    private static boolean skipByClass(AnaPartType _current) {
        if (_current instanceof AnaVariablePartType) {
            return true;
        }
        if (_current instanceof AnaArraryPartType) {
            return true;
        }
        if (_current instanceof AnaWildCardPartType) {
            return true;
        }
        return _current instanceof AnaEmptyWildCardPart;
    }

    public static boolean checkConstrains(AnaPartType _root, StringMap<StringList> _inherit, ContextEl _context) {
        AnaPartType current_ = _root;
        while (true) {
            AnaPartType child_ = current_.getFirstChild();
            if (child_ != null) {
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                if (koTemp(_inherit, _context, current_)) {
                    ((AnaTemplatePartType)current_).processBadConstraintsOffsets(_context);
                    ((AnaTemplatePartType)current_).buildBadConstraintsOffset();
                    return false;
                }
                AnaPartType next_ = current_.getNextSibling();
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                if (par_ == _root) {
                    if (koTemp(_inherit, _context, par_)) {
                        ((AnaTemplatePartType)par_).processBadConstraintsOffsets(_context);
                        ((AnaTemplatePartType)par_).buildBadConstraintsOffset();
                        return false;
                    }
                    stop_ = true;
                    break;
                }
                if (par_ == null) {
                    stop_ = true;
                    break;
                }
                current_ = par_;
            }
            if (stop_) {
                break;
            }
        }
        return true;
    }

    public static boolean koTemp(StringMap<StringList> _inherit, ContextEl _context, AnaPartType _current) {
        return _current instanceof AnaTemplatePartType && !((AnaTemplatePartType) _current).okTmp(_context, _inherit);
    }

    static String processAnalyzeLine(String _input, ContextEl _an, ExecRootBlock _rooted) {
        _an.getAnalyzing().setImportingTypes(_rooted);
        return processAnalyzeLine(_input,false,"", _an,_rooted,_rooted, 0,new CustList<PartOffset>()).getResult();
    }
    public static AnaResultPartType processAnalyzeLine(String _input, boolean _rootName, String _globalType, ContextEl _an, AccessedBlock _local, AccessedBlock _rooted, int _loc, CustList<PartOffset> _offs) {
        CustList<AnaLeafPartType> ls_ = new CustList<AnaLeafPartType>();
        _an.getAnalyzing().setLocalInType(_loc);
        _an.getAnalyzing().setRefFileName("");
        AnaPartType anaType_ = getAnalyzeLine(_input, new AlwaysReadyTypes(), _rootName, _an, _local, _rooted, _loc, ls_, _offs);
        if (anaType_ == null) {
            return new AnaResultPartType("",null);
        }
        checkAccess(anaType_,_globalType,_an);
        return new AnaResultPartType(anaType_.getAnalyzedType(),anaType_);
    }
    public static AnaResultPartType processAnalyzeLineWithoutErr(String _input, boolean _rootName, ContextEl _an, AccessedBlock _local, AccessedBlock _rooted, int _loc, CustList<PartOffset> _offs) {
        CustList<AnaLeafPartType> ls_ = new CustList<AnaLeafPartType>();
        _an.getAnalyzing().setLocalInType(_loc);
        _an.getAnalyzing().setRefFileName("");
        AnaPartType anaType_ = getAnalyzeLine(_input, new AlwaysReadyTypes(), _rootName, _an, _local, _rooted, _loc, ls_, _offs);
        if (anaType_ == null) {
            return new AnaResultPartType("",null);
        }
        appendParts(anaType_,_offs,_an);
        return new AnaResultPartType(anaType_.getAnalyzedType(),anaType_);
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
        root_.setLength(_input.length());
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
                    current_.processInexistType(_an,_input);
                    appendError(current_,_offs,_an);
                    return null;
                }
                processLeafOffsets(_an, _rooted, current_);
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
                    if (par_.getAnalyzedType().isEmpty()) {
                        par_.processInexistType(_an,_input);
                        appendError(current_,_offs,_an);
                        return null;
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
        return root_;
    }
    static void checkAccess(AnaPartType _root, String _gl, ContextEl _an) {
        AnaPartType current_ = _root;
        while (true) {
            AnaPartType child_ = current_.getFirstChild();
            if (child_ != null) {
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                checkAccessGeneral(_an,_gl, current_);
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

    static AnaResultPartType processAnalyzeAccessibleId(String _input, ContextEl _an, AccessedBlock _rooted, String _refFileName, int _loc, CustList<PartOffset> _offs) {
        Ints indexes_ = ParserType.getIndexes(_input, _an);
        _an.getAnalyzing().setLocalInType(_loc);
        _an.getAnalyzing().setRefFileName(_refFileName);
        if (indexes_ == null) {
            return new AnaResultPartType("",null);
        }
        AnalyzingType loc_ = ParserType.analyzeLocal(0, _input, indexes_);
        CustList<IntTreeMap< String>> dels_;
        dels_ = new CustList<IntTreeMap< String>>();
        AnaPartType root_ = AnaPartType.createPartType(_an,false, null, 0, 0, loc_, loc_.getValues());
        root_.setLength(_input.length());
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
                    current_.processInexistType(_an,_input);
                    appendError(current_,_offs,_an);
                    return new AnaResultPartType("",null);
                }
                processLeafOffsets(_an, _rooted, current_);
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
                    if (par_.getAnalyzedType().isEmpty()) {
                        par_.processInexistType(_an,_input);
                        appendError(par_,_offs,_an);
                        return new AnaResultPartType("",null);
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
        String analyzedType_ = root_.getAnalyzedType();
        return new AnaResultPartType(analyzedType_,root_);
    }

    private static void processLeafOffsets(ContextEl _an, AccessedBlock _rooted, AnaPartType current_) {
        if (current_ instanceof AnaNamePartType) {
            ((AnaNamePartType)current_).processOffsets(_an,_rooted);
        }
        if (current_ instanceof AnaVariablePartType) {
            ((AnaVariablePartType)current_).processOffsets(_an,_rooted);
        }
    }

    public static void appendParts(AnaPartType _root,CustList<PartOffset> _offs,  ContextEl _cont) {
        AnaPartType current_ = _root;
        while (true) {
            AnaPartType child_ = current_.getFirstChild();
            if (child_ != null) {
                current_ = child_;
                continue;
            }
            current_.buildOffsetPartDefault(_cont);
            appendOffset(_offs, current_);
            boolean stop_ = false;
            while (true) {
                AnaPartType next_ = current_.getNextSibling();
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                if (par_ == _root) {
                    stop_ = true;
                    break;
                }
                if (par_ == null) {
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
    private static void appendError(AnaPartType _p, CustList<PartOffset> _offs,  ContextEl _cont) {
        _p.buildOffsetPartDefault(_cont);
        appendOffset(_offs,_p);
    }
    private static void appendOffset(CustList<PartOffset> _offs, AnaPartType _l) {
        if (_l.getBeginOffset() != null) {
            _offs.add(_l.getBeginOffset());
        }
        if (_l.getEndOffset() != null) {
            _offs.add(_l.getEndOffset());
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
        p_.setLength(v_.length());
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
        p_.setLength(v_.length());
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
        p_.setLength(v_.length());
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
        p_.setLength(v_.length());
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
