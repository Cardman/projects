package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.util.*;
import code.util.core.StringUtil;

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
        if (!StringExpUtil.isTypeLeafPart(typeName_)) {
            return true;
        }
        if (StringUtil.contains(_excludedWords,typeName_)) {
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

    public static AnaResultPartType processAnalyze(String _input, boolean _rootName, String _globalType, AccessedBlock _local, AccessedBlock _rooted, int _loc, CustList<PartOffset> _offs, AnalyzedPageEl _page) {
        _page.setLocalInType(_loc);
        _page.setRefFileName("");
        AnaResultPartType res_ = processAccessAnalyze(_input, _rootName, _globalType, _local, _rooted, _loc, _offs, _page);
        String analyzedType_ = res_.getResult();
        if (analyzedType_.isEmpty()) {
            return res_;
        }
        AnaPartType r_ = res_.getPartType();
        checkAccess(r_,_globalType, _page);
        return res_;
    }
    public static AnaResultPartType processAccAnalyze(String _input, boolean _rootName, String _globalType, AccessedBlock _local, AccessedBlock _rooted, int _loc, CustList<PartOffset> _offs, AnalyzedPageEl _page) {
        _page.setLocalInType(_loc);
        _page.setRefFileName("");
        return processAccessAnalyze(_input, _rootName, _globalType, _local, _rooted, _loc, _offs, _page);
    }
    public static AnaResultPartType processAnalyzeHeader(String _input, boolean _rootName, String _globalType, AccessedBlock _local, AccessedBlock _rooted, int _loc, CustList<PartOffset> _offs, AnalyzedPageEl _page) {
        _page.setLocalInType(_loc);
        _page.setRefFileName("");
        AnaResultPartType res_ = processAccessAnalyze(_input, _rootName, _globalType, _local, _rooted, _loc, _offs, _page);
        String analyzedType_ = res_.getResult();
        if (analyzedType_.isEmpty()) {
            return res_;
        }
        AnaPartType r_ = res_.getPartType();
        checkAccess(r_,_globalType, _page);
        appendParts(r_,_offs, _page);
        return res_;
    }
    public static AnaResultPartType processAccessAnalyze(String _input, boolean _rootName, String _globalType, AccessedBlock _local, AccessedBlock _rooted, int _loc, CustList<PartOffset> _offs, AnalyzedPageEl _page) {
        Ints indexes_ = ParserType.getIndexes(_input.trim(), _page);
        if (indexes_ == null) {
            String err_ = LinkageUtil.transform(FoundErrorInterpret.buildARError(_page.getAnalysisMessages().getUnknownType(), _input));
            String pref_ = "<a title=\""+err_+"\" class=\"e\">";
            _offs.add(new PartOffset(pref_, _loc));
            _offs.add(new PartOffset("</a>", _loc + _input.length()));
            return new AnaResultPartType("",null);
        }
        AnalyzingType loc_ = ParserType.analyzeLocal(0, _input.trim(), indexes_);
        CustList<IntTreeMap< String>> dels_;
        dels_ = new CustList<IntTreeMap< String>>();
        AnaPartType root_ = AnaPartType.createPartType(_rootName,null, 0, 0, loc_, loc_.getValues(), _page);
        root_.setLength(_input.trim().length());
        addValues(root_, dels_, loc_);
        AnaPartType current_ = root_;
        while (true) {
            AnaPartType child_ = createFirstChild(_rootName,current_, loc_, dels_, _page);
            if (child_ != null) {
                ((AnaParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                current_.analyze(dels_, _globalType, _local, _rooted, _page);
                if (current_.getAnalyzedType().isEmpty()) {
                    processInexist(_input, current_, _page);
                } else {
                    processLeafOffsets(_rooted, current_, _page);
                }
                AnaPartType next_ = createNextSibling(_rootName,current_, loc_, dels_, _page);
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    par_.analyze(dels_, _globalType, _local, _rooted, _page);
                    if (par_.getAnalyzedType().isEmpty()) {
                        processInexist(_input, par_, _page);
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
            appendQuickParts(root_, _offs, _page);
            return new AnaResultPartType("",null);
        }
        return new AnaResultPartType(root_.getAnalyzedType(),root_);
    }

    private static void processInexist(String _input, AnaPartType _current, AnalyzedPageEl _page) {
        if (_current instanceof AnaEmptyPartType) {
            AnaParentPartType par_ = _current.getParent();
            if (par_ != null) {
                return;
            }
            _current.processInexistType(_input, _page);
            _current.buildOffsetPartDefault(_page);
            return;
        }
        _current.processInexistType(_input, _page);
        if (_current instanceof AnaParentPartType) {
            ((AnaParentPartType)_current).buildErrorInexist(_page);
            return;
        }
        _current.buildOffsetPartDefault(_page);
    }

    private static void checkAccessGeneral(String _gl, AnaPartType _current, AnalyzedPageEl _page) {
        if (_current instanceof AnaNamePartType) {
            ((AnaNamePartType) _current).checkAccessGeneral(_page);
            ((AnaNamePartType) _current).processInaccessibleOffsets(_gl, _page);
        }
    }

    public static boolean processAnalyzeConstraints(AnaResultPartType _className, StringMap<StringList> _inherit, boolean _exact, AnalyzedPageEl _page) {
        return processAnalyzeConstraints(_className,_inherit, _exact,new CustList<PartOffset>(), _page);
    }
    public static boolean processAnalyzeConstraints(AnaResultPartType _className, StringMap<StringList> _inherit, boolean _exact, CustList<PartOffset> _parts, AnalyzedPageEl _page) {
        AnaPartType root_ = _className.getPartType();
        if (root_ == null) {
            return false;
        }
        if (!_exact && !_className.getResult().contains(Templates.TEMPLATE_BEGIN)) {
            appendParts(root_,_parts, _page);
            return true;
        }
        boolean res_ = checkParametersCount(root_, _page);
        boolean out_ = checkConstrains(root_, _inherit, _page);
        appendParts(root_,_parts, _page);
        return res_&&out_;
    }

    static boolean checkParametersCount(AnaPartType _root, AnalyzedPageEl _page){
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
                if (isNotCorrectParam(current_, _page)) {
                    processErrorParamCount(current_, _page);
                    ok_ = false;
                }
                AnaPartType next_ = current_.getNextSibling();
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                if (par_ == _root) {
                    if (isNotCorrectParam(par_, _page)) {
                        processErrorParamCount(par_, _page);
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

    private static void processErrorParamCount(AnaPartType _current, AnalyzedPageEl _page) {
        AnaPartType ch_ = _current.getFirstChild();
        if (ch_ != null&&_current instanceof AnaTemplatePartType) {
            String err_ = FoundErrorInterpret.buildARError(_page.getAnalysisMessages().getBadParamerizedType(), ch_.getAnalyzedType());
            _current.getErrs().add(err_);
            return;
        }
        AnaPartType l_ = ch_;
        while (ch_ != null) {
            l_ = ch_;
            ch_ = ch_.getNextSibling();
        }
        if (l_ != null) {
            //inner
            String err_ = FoundErrorInterpret.buildARError(_page.getAnalysisMessages().getBadParamerizedType(), l_.getAnalyzedType());
            _current.getErrs().add(err_);
            return;
        }
        _current.processBadFormedOffsets(_page);
    }

    private static boolean isNotCorrectParam(AnaPartType _current, AnalyzedPageEl _page) {
        //AnaInnerPartType,AnaTemplatePartType,AnaNamePartType
        String analyzedType_ = _current.getAnalyzedType();
        String idCl_ = StringExpUtil.getIdFromAllTypes(analyzedType_);
        String compo_ = StringExpUtil.getQuickComponentBaseType(idCl_).getComponent();
        AnaGeneType info_ = _page.getAnaGeneType(compo_);
        return !skip(_current) && !AnaTemplates.correctNbParameters(info_,analyzedType_, _page);
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

    private static boolean checkConstrains(AnaPartType _root, StringMap<StringList> _inherit, AnalyzedPageEl _page) {
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
                if (koTemp(_inherit, current_, _page)) {
                    ((AnaTemplatePartType)current_).buildBadConstraintsOffsetList(_page);
                    ok_ = false;
                }
                AnaPartType next_ = current_.getNextSibling();
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                if (par_ == _root) {
                    if (koTemp(_inherit, par_, _page)) {
                        ((AnaTemplatePartType)par_).buildBadConstraintsOffsetList(_page);
                        ok_ = false;
                    }
                    processBadLen(par_, _page);
                    stop_ = true;
                    break;
                }
                if (par_ == null) {
                    stop_ = true;
                    break;
                }
                processBadLen(par_, _page);
                current_ = par_;
            }
            if (stop_) {
                break;
            }
        }
        return ok_;
    }

    private static void processBadLen(AnaParentPartType _par, AnalyzedPageEl _page) {
        if (_par instanceof AnaTemplatePartType) {
            ((AnaTemplatePartType)_par).processBadLen(_page);
        }
        if (_par instanceof AnaInnerPartType) {
            _par.buildErrorInexist(_page);
        }
    }

    private static boolean koTemp(StringMap<StringList> _inherit, AnaPartType _current, AnalyzedPageEl _page) {
        return _current instanceof AnaTemplatePartType && !((AnaTemplatePartType) _current).okTmp(_inherit, _page);
    }

    public static AnaResultPartType processAnalyzeLine(String _input, boolean _rootName, String _globalType, AccessedBlock _local, AccessedBlock _rooted, int _loc, CustList<PartOffset> _offs, AnalyzedPageEl _page) {
        CustList<AnaLeafPartType> ls_ = new CustList<AnaLeafPartType>();
        _page.setLocalInType(_loc);
        _page.setRefFileName("");
        AnaPartType anaType_ = getAnalyzeLine(_input, new AlwaysReadyTypes(), _rootName, _local, _rooted, _loc, ls_, _offs, _page);
        if (anaType_ == null) {
            return new AnaResultPartType("",null);
        }
        checkAccess(anaType_,_globalType, _page);
        return new AnaResultPartType(anaType_.getAnalyzedType(),anaType_);
    }
    static AnaResultPartType processAnalyzeLineWithoutErr(String _input, AccessedBlock _local, AccessedBlock _rooted, int _loc, CustList<PartOffset> _offs, AnalyzedPageEl _page) {
        CustList<AnaLeafPartType> ls_ = new CustList<AnaLeafPartType>();
        _page.setLocalInType(_loc);
        _page.setRefFileName("");
        AnaPartType anaType_ = getAnalyzeLine(_input, new AlwaysReadyTypes(), false, _local, _rooted, _loc, ls_, _offs, _page);
        if (anaType_ == null) {
            return new AnaResultPartType("",null);
        }
        appendParts(anaType_,_offs, _page);
        return new AnaResultPartType(anaType_.getAnalyzedType(),anaType_);
    }
    static String processAnalyzeLineInherits(String _input, ReadyTypes _ready, AccessedBlock _local, AccessedBlock _rooted, CustList<PartOffset> _offs, AnalyzedPageEl _page) {
        CustList<AnaLeafPartType> ls_ = new CustList<AnaLeafPartType>();
        AnaPartType anaType_ = getAnalyzeLine(_input, _ready, true, _local, _rooted, -1, ls_, _offs, _page);
        if (anaType_ == null) {
            return "";
        }
        String ana_ = anaType_.getAnalyzedType();
        if (!_ready.isReady(ana_)) {
            return "";
        }
        return ana_;
    }
    private static AnaPartType getAnalyzeLine(String _input, ReadyTypes _ready, boolean _rootName, AccessedBlock _local, AccessedBlock _rooted, int _loc, CustList<AnaLeafPartType> _leaves, CustList<PartOffset> _offs, AnalyzedPageEl _page) {
        Ints indexes_ = ParserType.getIndexes(_input.trim(), _page);
        if (indexes_ == null) {
            String err_ = LinkageUtil.transform(FoundErrorInterpret.buildARError(_page.getAnalysisMessages().getUnknownType(), _input));
            String pref_ = "<a title=\""+err_+"\" class=\"e\">";
            _offs.add(new PartOffset(pref_,_loc));
            _offs.add(new PartOffset("</a>",_loc+_input.length()));
            return null;
        }
        AnalyzingType loc_ = ParserType.analyzeLocal(0, _input.trim(), indexes_);
        CustList<IntTreeMap< String>> dels_;
        dels_ = new CustList<IntTreeMap< String>>();
        AnaPartType root_ = AnaPartType.createPartType(_rootName,null, 0, 0, loc_, loc_.getValues(), _page);
        root_.setLength(_input.trim().length());
        addIfLeaf(root_, _leaves);
        addValues(root_, dels_, loc_);
        AnaPartType current_ = root_;
        while (true) {
            AnaPartType child_ = createFirstChild(_rootName,current_, loc_, dels_, _page);
            if (child_ != null) {
                addIfLeaf(child_, _leaves);
                ((AnaParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                current_.analyzeLine(_ready,dels_,_local, _rooted, _page);
                if (current_.getAnalyzedType().isEmpty()) {
                    processInexist(_input, current_, _page);
                } else {
                    processLeafOffsets(_rooted, current_, _page);
                }
                AnaPartType next_ = createNextSibling(_rootName,current_, loc_, dels_, _page);
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    addIfLeaf(next_, _leaves);
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    par_.analyzeLine(_ready,dels_,_local, _rooted, _page);
                    if (par_.getAnalyzedType().isEmpty()) {
                        processInexist(_input, par_, _page);
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
            appendQuickParts(root_,_offs, _page);
            return null;
        }
        return root_;
    }
    private static void checkAccess(AnaPartType _root, String _gl, AnalyzedPageEl _page) {
        AnaPartType current_ = _root;
        while (true) {
            AnaPartType child_ = current_.getFirstChild();
            if (child_ != null) {
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                checkAccessGeneral(_gl, current_, _page);
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

    static AnaResultPartType processAnalyzeAccessibleId(String _input, AccessedBlock _rooted, String _refFileName, int _loc, CustList<PartOffset> _offs, AnalyzedPageEl _page) {
        Ints indexes_ = ParserType.getIndexes(_input.trim(), _page);
        _page.setLocalInType(_loc);
        _page.setRefFileName(_refFileName);
        if (indexes_ == null) {
            String err_ = LinkageUtil.transform(FoundErrorInterpret.buildARError(_page.getAnalysisMessages().getUnknownType(), _input));
            String pref_ = "<a title=\""+err_+"\" class=\"e\">";
            _offs.add(new PartOffset(pref_,_loc));
            _offs.add(new PartOffset("</a>",_loc+_input.length()));
            return new AnaResultPartType("",null);
        }
        AnalyzingType loc_ = ParserType.analyzeLocalId(0, _input.trim(), indexes_);
        CustList<IntTreeMap< String>> dels_;
        dels_ = new CustList<IntTreeMap< String>>();
        AnaPartType root_ = AnaPartType.createPartType(false, null, 0, 0, loc_, loc_.getValues(), _page);
        root_.setLength(_input.trim().length());
        CustList<AnaLeafPartType> l_ = new CustList<AnaLeafPartType>();
        addIfLeaf(root_,l_);
        addValues(root_, dels_, loc_);
        AnaPartType current_ = root_;
        while (true) {
            AnaPartType child_ = createFirstChildId(false, current_, loc_, dels_, _page);
            if (child_ != null) {
                addIfLeaf(child_,l_);
                ((AnaParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                current_.analyzeAccessibleId(dels_, _rooted, _page);
                if (current_.getAnalyzedType().isEmpty()) {
                    processInexist(_input, current_, _page);
                } else {
                    processLeafOffsets(_rooted, current_, _page);
                }
                AnaPartType next_ = createNextSiblingId(false, current_, loc_, dels_, _page);
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    addIfLeaf(next_,l_);
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    par_.analyzeAccessibleId(dels_, _rooted, _page);
                    if (par_.getAnalyzedType().isEmpty()) {
                        processInexist(_input, par_, _page);
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
            appendQuickParts(root_,_offs, _page);
            return new AnaResultPartType("",null);
        }
        String analyzedType_ = root_.getAnalyzedType();
        return new AnaResultPartType(analyzedType_,root_);
    }

    private static void processLeafOffsets(AccessedBlock _rooted, AnaPartType _current, AnalyzedPageEl _page) {
        if (_current instanceof AnaNamePartType) {
            ((AnaNamePartType)_current).processOffsets(_rooted, _page);
        }
        if (_current instanceof AnaVariablePartType) {
            ((AnaVariablePartType)_current).processOffsets(_rooted, _page);
        }
    }

    private static void appendParts(AnaPartType _root, CustList<PartOffset> _offs, AnalyzedPageEl _page) {
        AnaPartType current_ = _root;
        while (true) {
            AnaPartType child_ = current_.getFirstChild();
            if (child_ != null) {
                current_ = child_;
                continue;
            }
            current_.buildOffsetPartDefault(_page);
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

    private static void appendEnd(CustList<PartOffset> _offs, AnaParentPartType _par) {
        if (_par instanceof AnaTemplatePartType) {
            _offs.add(((AnaTemplatePartType)_par).getLastPartBegin());
            _offs.add(((AnaTemplatePartType)_par).getLastPartEnd());
        }
    }

    private static void appendQuickParts(AnaPartType _root, CustList<PartOffset> _offs, AnalyzedPageEl _page) {
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
            current_.buildOffsetPartDefault(_page);
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
    private static AnaPartType createFirstChild(boolean _rootName, AnaPartType _parent, AnalyzingType _analyze, CustList<IntTreeMap<String>> _dels, AnalyzedPageEl _page) {
        if (!(_parent instanceof AnaParentPartType)) {
            return null;
        }
        AnaParentPartType par_ = (AnaParentPartType) _parent;
        IntTreeMap< String> last_ = _dels.last();
        int off_ = par_.getIndexInType() + last_.firstKey();
        String v_ = last_.firstValue();
        off_ += StringUtil.getFirstPrintableCharIndex(v_);
        AnalyzingType an_ = ParserType.analyzeLocal(off_, v_.trim(), _analyze.getIndexes());
        AnaPartType p_ = AnaPartType.createPartType(_rootName, par_, 0, off_, an_, last_, _page);
        p_.setLength(v_.trim().length());
        addValues(p_, _dels, an_);
        return p_;
    }
    private static AnaPartType createFirstChildId(boolean _rootName, AnaPartType _parent, AnalyzingType _analyze, CustList<IntTreeMap<String>> _dels, AnalyzedPageEl _page) {
        if (!(_parent instanceof AnaParentPartType)) {
            return null;
        }
        AnaParentPartType par_ = (AnaParentPartType) _parent;
        IntTreeMap< String> last_ = _dels.last();
        int off_ = par_.getIndexInType() + last_.firstKey();
        String v_ = last_.firstValue();
        off_ += StringUtil.getFirstPrintableCharIndex(v_);
        AnalyzingType an_ = ParserType.analyzeLocalId(off_, v_.trim(), _analyze.getIndexes());
        AnaPartType p_ = AnaPartType.createPartType(_rootName, par_, 0, off_, an_, last_, _page);
        p_.setLength(v_.trim().length());
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
    private static AnaPartType createNextSibling(boolean _rootName, AnaPartType _parent, AnalyzingType _analyze, CustList<IntTreeMap<String>> _dels, AnalyzedPageEl _page) {
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
        int off_ = par_.getIndexInType() + last_.getKey(indexNext_);
        String v_ = last_.getValue(indexNext_);
        off_ += StringUtil.getFirstPrintableCharIndex(v_);
        AnalyzingType an_ = ParserType.analyzeLocal(off_, v_.trim(), _analyze.getIndexes());
        AnaPartType p_ = AnaPartType.createPartType(_rootName,b_,indexNext_, off_, an_, last_, _page);
        p_.setPreviousSibling(_parent);
        p_.setLength(v_.trim().length());
        addValues(p_, _dels, an_);
        return p_;
    }
    private static AnaPartType createNextSiblingId(boolean _rootName, AnaPartType _parent, AnalyzingType _analyze, CustList<IntTreeMap<String>> _dels, AnalyzedPageEl _page) {
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
        int off_ = par_.getIndexInType() + last_.getKey(indexNext_);
        String v_ = last_.getValue(indexNext_);
        off_ += StringUtil.getFirstPrintableCharIndex(v_);
        AnalyzingType an_ = ParserType.analyzeLocalId(off_, v_.trim(), _analyze.getIndexes());
        AnaPartType p_ = AnaPartType.createPartType(_rootName,b_,indexNext_, off_, an_, last_, _page);
        p_.setPreviousSibling(_parent);
        p_.setLength(v_.trim().length());
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
            values_.addAllEntries(_an.getValues());
            values_.removeKey(values_.lastKey());
            _dels.add(values_);
        } else if (_p instanceof AnaInnerPartType) {
            IntTreeMap<String> values_;
            values_ = new IntTreeMap< String>();
            values_.addAllEntries(_an.getValues());
            _dels.add(values_);
        } else {
            _dels.add(_an.getValues());
        }
    }
}
