package code.expressionlanguage.analyze.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.exec.blocks.AccessedBlock;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.types.*;
import code.util.*;

public final class AnaPartTypeUtil {
    private AnaPartTypeUtil(){
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

    static boolean isKoForWord(String _type, CustList<String> _excludedWords) {
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

    public static AnaResultPartType processAnalyze(String _input, boolean _rootName, String _globalType, ContextEl _an, AccessedBlock _local, AccessedBlock _rooted, int _loc, CustList<PartOffset> _offs) {
        _an.getAnalyzing().setLocalInType(_loc);
        _an.getAnalyzing().setRefFileName("");
        AnaResultPartType res_ = processAccessAnalyze(_input, _rootName, _globalType, _an, _local, _rooted, _loc, _offs);
        String analyzedType_ = res_.getResult();
        if (analyzedType_.isEmpty()) {
            return res_;
        }
        AnaPartType r_ = res_.getPartType();
        checkAccess(r_,_globalType,_an);
        return new AnaResultPartType(analyzedType_,r_);
    }
    public static AnaResultPartType processAccAnalyze(String _input, boolean _rootName, String _globalType, ContextEl _an, AccessedBlock _local, AccessedBlock _rooted, int _loc, CustList<PartOffset> _offs) {
        _an.getAnalyzing().setLocalInType(_loc);
        _an.getAnalyzing().setRefFileName("");
        AnaResultPartType res_ = processAccessAnalyze(_input, _rootName, _globalType, _an, _local, _rooted, _loc, _offs);
        String analyzedType_ = res_.getResult();
        if (analyzedType_.isEmpty()) {
            return res_;
        }
        AnaPartType r_ = res_.getPartType();
        return new AnaResultPartType(analyzedType_,r_);
    }
    public static AnaResultPartType processAnalyzeHeader(String _input, boolean _rootName, String _globalType, ContextEl _an, AccessedBlock _local, AccessedBlock _rooted, int _loc, CustList<PartOffset> _offs) {
        if (_loc > -1) {
            _an.getAnalyzing().setLocalInType(_loc);
            _an.getAnalyzing().setRefFileName("");
        }
        AnaResultPartType res_ = processAccessAnalyze(_input, _rootName, _globalType, _an, _local, _rooted, _loc, _offs);
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
        Ints indexes_ = ParserType.getIndexes(_input, _an);
        if (indexes_ == null) {
            String err_ = LinkageUtil.transform(FoundErrorInterpret.buildARError(_an.getAnalysisMessages().getUnknownType(), _input));
            String pref_ = "<a title=\""+err_+"\" class=\"e\">";
            _offs.add(new PartOffset(pref_, _loc));
            _offs.add(new PartOffset("</a>", _loc + _input.length()));
            return new AnaResultPartType("",null);
        }
        AnalyzingType loc_ = ParserType.analyzeLocal(0, _input, indexes_);
        CustList<IntTreeMap< String>> dels_;
        dels_ = new CustList<IntTreeMap< String>>();
        AnaPartType root_ = AnaPartType.createPartType(_an, _rootName,null, 0, 0, loc_, loc_.getValues());
        root_.setLength(_input.length());
        addValues(root_, dels_, loc_);
        AnaPartType current_ = root_;
        while (true) {
            AnaPartType child_ = createFirstChild(_an, _rootName,current_, loc_, dels_);
            if (child_ != null) {
                ((AnaParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                current_.analyze(_an, dels_, _globalType, _local, _rooted);
                if (current_.getAnalyzedType().isEmpty()) {
                    processInexist(_input, _an, current_);
                } else {
                    processLeafOffsets(_an, _rooted, current_);
                }
                AnaPartType next_ = createNextSibling(_an, _rootName,current_, loc_, dels_);
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    par_.analyze(_an, dels_, _globalType, _local, _rooted);
                    if (par_.getAnalyzedType().isEmpty()) {
                        processInexist(_input, _an, par_);
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
        if (root_.getAnalyzedType().isEmpty()) {
            appendQuickParts(root_, _offs, _an);
            return new AnaResultPartType("",null);
        }
        return new AnaResultPartType(root_.getAnalyzedType(),root_);
    }

    private static void processInexist(String _input, ContextEl _an, AnaPartType current_) {
        if (current_ instanceof AnaEmptyPartType) {
            AnaParentPartType par_ = current_.getParent();
            if (par_ != null) {
                return;
            }
            current_.processInexistType(_an,_input);
            current_.buildOffsetPartDefault(_an);
            return;
        }
        current_.processInexistType(_an,_input);
        if (current_ instanceof AnaParentPartType) {
            ((AnaParentPartType)current_).buildErrorInexist(_an);
            return;
        }
        current_.buildOffsetPartDefault(_an);
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
        boolean out_ = checkConstrains(root_, _inherit, _context,_parts);
        appendParts(root_,_parts,_context);
        return res_&&out_;
    }

    private static boolean checkParametersCount(AnaPartType _root, ContextEl _context, CustList<PartOffset> _parts) {
        AnaPartType current_ = _root;
        boolean ok_ = true;
        while (true) {
            AnaPartType child_ = current_.getFirstChild();
            if (child_ != null) {
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                if (isNotCorrectParam(_context, current_)) {
                    processErrorParamCount(_context, _parts, current_);
                    ok_ = false;
                }
                AnaPartType next_ = current_.getNextSibling();
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                if (par_ == _root) {
                    if (isNotCorrectParam(_context, par_)) {
                        processErrorParamCount(_context, _parts, par_);
                        ok_ = false;
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
        return ok_;
    }

    private static void processErrorParamCount(ContextEl _context, CustList<PartOffset> _parts, AnaPartType current_) {
        AnaPartType ch_ = current_.getFirstChild();
        if (ch_ != null&&current_ instanceof AnaTemplatePartType) {
            String err_ = FoundErrorInterpret.buildARError(_context.getAnalysisMessages().getBadParamerizedType(), ch_.getAnalyzedType());
            current_.getErrs().add(err_);
            return;
        }
        AnaPartType l_ = ch_;
        while (ch_ != null) {
            l_ = ch_;
            ch_ = ch_.getNextSibling();
        }
        if (l_ != null) {
            //inner
            String err_ = FoundErrorInterpret.buildARError(_context.getAnalysisMessages().getBadParamerizedType(), l_.getAnalyzedType());
            current_.getErrs().add(err_);
            return;
        }
        current_.processBadFormedOffsets(_context);
    }

    private static boolean isNotCorrectParam(ContextEl _context, AnaPartType _current) {
        //AnaInnerPartType,AnaTemplatePartType,AnaNamePartType
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

    private static boolean checkConstrains(AnaPartType _root, StringMap<StringList> _inherit, ContextEl _context, CustList<PartOffset> _parts) {
        AnaPartType current_ = _root;
        boolean ok_ = true;
        while (true) {
            AnaPartType child_ = current_.getFirstChild();
            if (child_ != null) {
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                if (koTemp(_inherit, _context, current_)) {
                    ((AnaTemplatePartType)current_).buildBadConstraintsOffsetList(_context);
                    ok_ = false;
                }
                AnaPartType next_ = current_.getNextSibling();
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                if (par_ == _root) {
                    if (koTemp(_inherit, _context, par_)) {
                        ((AnaTemplatePartType)par_).buildBadConstraintsOffsetList(_context);
                        ok_ = false;
                    }
                    processBadLen(_context, par_);
                    stop_ = true;
                    break;
                }
                if (par_ == null) {
                    stop_ = true;
                    break;
                }
                processBadLen(_context, par_);
                current_ = par_;
            }
            if (stop_) {
                break;
            }
        }
        return ok_;
    }

    private static void processBadLen(ContextEl _context, AnaParentPartType par_) {
        if (par_ instanceof AnaTemplatePartType) {
            ((AnaTemplatePartType)par_).processBadLen(_context);
        }
        if (par_ instanceof AnaInnerPartType) {
            par_.buildErrorInexist(_context);
        }
    }

    private static boolean koTemp(StringMap<StringList> _inherit, ContextEl _context, AnaPartType _current) {
        return _current instanceof AnaTemplatePartType && !((AnaTemplatePartType) _current).okTmp(_context, _inherit);
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
    private static AnaPartType getAnalyzeLine(String _input, ReadyTypes _ready, boolean _rootName, ContextEl _an, AccessedBlock _local, AccessedBlock _rooted, int _loc, CustList<AnaLeafPartType> _leaves, CustList<PartOffset> _offs) {
        Ints indexes_ = ParserType.getIndexes(_input, _an);
        if (indexes_ == null) {
            String err_ = LinkageUtil.transform(FoundErrorInterpret.buildARError(_an.getAnalysisMessages().getUnknownType(), _input));
            String pref_ = "<a title=\""+err_+"\" class=\"e\">";
            _offs.add(new PartOffset(pref_,_loc));
            _offs.add(new PartOffset("</a>",_loc+_input.length()));
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
                    processInexist(_input, _an, current_);
                } else {
                    processLeafOffsets(_an, _rooted, current_);
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
                    if (par_.getAnalyzedType().isEmpty()) {
                        processInexist(_input, _an, par_);
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
        if (root_.getAnalyzedType().isEmpty()) {
            appendQuickParts(root_,_offs,_an);
            return null;
        }
        return root_;
    }
    private static void checkAccess(AnaPartType _root, String _gl, ContextEl _an) {
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
            String err_ = LinkageUtil.transform(FoundErrorInterpret.buildARError(_an.getAnalysisMessages().getUnknownType(), _input));
            String pref_ = "<a title=\""+err_+"\" class=\"e\">";
            _offs.add(new PartOffset(pref_,_loc));
            _offs.add(new PartOffset("</a>",_loc+_input.length()));
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
                    processInexist(_input, _an, current_);
                } else {
                    processLeafOffsets(_an, _rooted, current_);
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
                    if (par_.getAnalyzedType().isEmpty()) {
                        processInexist(_input, _an, par_);
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
        if (root_.getAnalyzedType().isEmpty()) {
            appendQuickParts(root_,_offs,_an);
            return new AnaResultPartType("",null);
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

    private static void appendParts(AnaPartType _root, CustList<PartOffset> _offs, ContextEl _cont) {
        AnaPartType current_ = _root;
        while (true) {
            AnaPartType child_ = current_.getFirstChild();
            if (child_ != null) {
                current_ = child_;
                continue;
            }
            current_.buildOffsetPartDefault(_cont);
            appendIntern(_offs, current_);
            boolean stop_ = false;
            while (true) {
                AnaPartType next_ = current_.getNextSibling();
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    int index_ = current_.getIndex();
                    if (par_ instanceof AnaTemplatePartType) {
                        if (par_.getBeginOps().isValidIndex(index_)) {
                            _offs.add(par_.getBeginOps().get(index_));
                        }
                        if (par_.getEndOps().isValidIndex(index_)) {
                            _offs.add(par_.getEndOps().get(index_));
                        }
                    }
                    if (par_ instanceof AnaInnerPartType) {
                        appendIntern(_offs, par_);
                    }
                    current_ = next_;
                    break;
                }
                if (par_ == _root) {
                    appendEnd(_offs, par_);
                    stop_ = true;
                    break;
                }
                if (par_ == null) {
                    stop_ = true;
                    break;
                }
                appendEnd(_offs, par_);
                current_ = par_;
            }
            if (stop_) {
                break;
            }
        }
    }

    private static void appendEnd(CustList<PartOffset> _offs, AnaParentPartType par_) {
        if (par_ instanceof AnaTemplatePartType) {
            _offs.add(((AnaTemplatePartType)par_).getLastPartBegin());
            _offs.add(((AnaTemplatePartType)par_).getLastPartEnd());
        }
    }

    private static void appendQuickParts(AnaPartType _root, CustList<PartOffset> _offs, ContextEl _cont) {
        AnaPartType current_ = _root;
        while (true) {
            if (current_ instanceof AnaWildCardPartType) {
                appendOffset(_offs, current_);
            }
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
                    if (par_ instanceof AnaTemplatePartType&&current_.getIndex() == 0) {
                        appendOffset(_offs, par_);
                    }
                    if (par_ instanceof AnaInnerPartType&&next_.getNextSibling() == null) {
                        appendOffset(_offs, par_);
                    }
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

    private static void appendOffset(CustList<PartOffset> _offs, AnaPartType _l) {
        boolean hasEmpty_ = false;
        AnaPartType f_ = _l.getFirstChild();
        AnaPartType ch_ = f_;
        while (ch_ != null) {
            if (ch_ instanceof AnaEmptyPartType) {
                hasEmpty_ = true;
                break;
            }
            ch_ = ch_.getNextSibling();
        }
        if (_l instanceof AnaNamePartType) {
            AnaPartType prev_ = ((AnaNamePartType) _l).getPreviousPartType();
            if (prev_ == null || !prev_.getAnalyzedType().isEmpty()) {
                appendIntern(_offs, _l);
            }
            return;
        }
        if (f_ == null || hasEmpty_ || _l.getAnalyzedType().isEmpty() && !_l.isAlreadyError()) {
            appendIntern(_offs, _l);
        }
    }

    private static void appendIntern(CustList<PartOffset> _offs, AnaPartType _l) {
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
