package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.linkage.ExportCst;
import code.maths.litteralcom.StrTypes;
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
        AnaPartType root_ = AnaPartType.createPartType(null, 0, 0, loc_, loc_.getValues().getValue(0));
        addValues(root_, loc_);
        AnaPartType current_ = root_;
        while (true) {
            AnaPartType child_ = createFirstChild(current_, indexes_);
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
                        || current_ instanceof AnaWildCardPartType
                        || current_ instanceof AnaRefPartType) {
                    if (!(current_.getParent() instanceof AnaTemplatePartType)) {
                        return false;
                    }
                }
                AnaPartType next_ = createNextSibling(current_, indexes_);
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
                current_ = par_;
            }
            if (stop_) {
                break;
            }
        }
        return !(root_ instanceof AnaWildCardPartType) && !(root_ instanceof AnaRefPartType);
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

    public static AnaResultPartType processAnalyze(String _input, String _globalType, AccessedBlock _local, AccessedBlock _rooted, int _loc, CustList<PartOffset> _offs, AnalyzedPageEl _page) {
        _page.setLocalInType(_loc);
        _page.setRefFileName("");
        AnaResultPartType res_ = processAccessAnalyze(_input, false, _globalType, _local, _rooted, _loc, _offs, _page);
        String analyzedType_ = res_.getResult();
        if (analyzedType_.isEmpty()) {
            return res_;
        }
        AnaPartType r_ = res_.getPartType();
        checkAccess(r_,_globalType, _page);
        return res_;
    }
    public static AnaResultPartType processAccAnalyze(String _input, String _globalType, AccessedBlock _local, AccessedBlock _rooted, int _loc, CustList<PartOffset> _offs, AnalyzedPageEl _page) {
        _page.setLocalInType(_loc);
        _page.setRefFileName("");
        return processAccessAnalyze(_input, false, _globalType, _local, _rooted, _loc, _offs, _page);
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
        String inputTr_ = _input.trim();
        Ints indexes_ = ParserType.getIndexes(inputTr_, _page);
        if (indexes_ == null) {
            String pref_ = ExportCst.anchorErr(FoundErrorInterpret.buildARError(_page.getAnalysisMessages().getUnknownType(), _input));
            _offs.add(new PartOffset(pref_, _loc));
            _offs.add(new PartOffset(ExportCst.END_ANCHOR, _loc + _input.length()));
            return new AnaResultPartType("",null);
        }
        AnalyzingType loc_ = ParserType.analyzeLocal(0, inputTr_, indexes_);
        AnaPartType root_ = AnaPartType.createPartType(_rootName,null, 0, 0, loc_, _page, loc_.getValues().getValue(0));
        root_.setLength(inputTr_.length());
        addValues(root_, loc_);
        AnaPartType current_ = root_;
        while (true) {
            AnaPartType child_ = createFirstChild(_rootName,current_, _page, indexes_);
            if (child_ != null) {
                ((AnaParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                current_.analyze(_globalType, _local, _rooted, _page);
                processAfterAnalyze(_input, _rooted, _page, current_);
                AnaPartType next_ = createNextSibling(_rootName,current_, _page, indexes_);
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    par_.analyze(_globalType, _local, _rooted, _page);
                    processAfterAnalyze(_input,_rooted,_page,par_);
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
        if (root_.getAnalyzedType().isEmpty()) {
            appendQuickParts(root_, _offs, _page);
            return new AnaResultPartType("",null);
        }
        return new AnaResultPartType(root_.getAnalyzedType(),root_);
    }

    private static void processAfterAnalyze(String _input, AccessedBlock _rooted, AnalyzedPageEl _page, AnaPartType _current) {
        if (_current.getAnalyzedType().isEmpty()) {
            processInexist(_input, _current, _page);
        } else {
            processLeafOffsets(_rooted, _current, _page);
        }
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
        return !skip(_current) && !AnaInherits.correctNbParameters(info_,analyzedType_, _page);
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
        if (_current instanceof AnaRefPartType) {
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

    public static AnaResultPartType processAnalyzeLine(String _input, String _globalType, AccessedBlock _local, AccessedBlock _rooted, int _loc, CustList<PartOffset> _offs, AnalyzedPageEl _page) {
        _page.setLocalInType(_loc);
        _page.setRefFileName("");
        AnaPartType anaType_ = getAnalyzeLine(_input, new AlwaysReadyTypes(), false, _local, _rooted, _loc, _offs, _page);
        if (anaType_ == null) {
            return new AnaResultPartType("",null);
        }
        checkAccess(anaType_,_globalType, _page);
        return new AnaResultPartType(anaType_.getAnalyzedType(),anaType_);
    }
    static AnaResultPartType processAnalyzeLineWithoutErr(String _input, AccessedBlock _local, AccessedBlock _rooted, int _loc, CustList<PartOffset> _offs, AnalyzedPageEl _page) {
        _page.setLocalInType(_loc);
        _page.setRefFileName("");
        AnaPartType anaType_ = getAnalyzeLine(_input, new AlwaysReadyTypes(), false, _local, _rooted, _loc, _offs, _page);
        if (anaType_ == null) {
            return new AnaResultPartType("",null);
        }
        appendParts(anaType_,_offs, _page);
        return new AnaResultPartType(anaType_.getAnalyzedType(),anaType_);
    }
    static String processAnalyzeLineInherits(String _input, ReadyTypes _ready, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page) {
        AnaPartType anaType_ = getAnalyzeLine(_input, _ready, true, _local, _rooted, -1, new CustList<PartOffset>(), _page);
        if (anaType_ == null) {
            return "";
        }
        String ana_ = anaType_.getAnalyzedType();
        if (!_ready.isReady(ana_)) {
            return "";
        }
        return ana_;
    }
    private static AnaPartType getAnalyzeLine(String _input, ReadyTypes _ready, boolean _rootName, AccessedBlock _local, AccessedBlock _rooted, int _loc, CustList<PartOffset> _offs, AnalyzedPageEl _page) {
        String inputTr_ = _input.trim();
        Ints indexes_ = ParserType.getIndexes(inputTr_, _page);
        if (indexes_ == null) {
            String pref_ = ExportCst.anchorErr(FoundErrorInterpret.buildARError(_page.getAnalysisMessages().getUnknownType(), _input));
            _offs.add(new PartOffset(pref_,_loc));
            _offs.add(new PartOffset(ExportCst.END_ANCHOR,_loc+_input.length()));
            return null;
        }
        AnalyzingType loc_ = ParserType.analyzeLocal(0, inputTr_, indexes_);
        AnaPartType root_ = AnaPartType.createPartType(_rootName,null, 0, 0, loc_, _page, loc_.getValues().getValue(0));
        root_.setLength(inputTr_.length());
        addValues(root_, loc_);
        AnaPartType current_ = root_;
        while (true) {
            AnaPartType child_ = createFirstChild(_rootName,current_, _page, indexes_);
            if (child_ != null) {
                ((AnaParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                current_.analyzeLine(_ready, _local, _rooted, _page);
                processAfterAnalyze(_input, _rooted, _page, current_);
                AnaPartType next_ = createNextSibling(_rootName,current_, _page, indexes_);
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    par_.analyzeLine(_ready, _local, _rooted, _page);
                    processAfterAnalyze(_input,_rooted,_page,par_);
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
        _page.setLocalInType(_loc);
        _page.setRefFileName(_refFileName);
        Ints indexes_ = ParserType.getIndexes(_input.trim(), _page);
        if (indexes_ == null) {
            String pref_ = ExportCst.anchorErr(FoundErrorInterpret.buildARError(_page.getAnalysisMessages().getUnknownType(), _input));
            _offs.add(new PartOffset(pref_,_loc));
            _offs.add(new PartOffset(ExportCst.END_ANCHOR,_loc+_input.length()));
            return new AnaResultPartType("",null);
        }
        AnalyzingType loc_ = ParserType.analyzeLocalId(0, _input.trim(), indexes_);
        AnaPartType root_ = AnaPartType.createPartType(false, null, 0, 0, loc_, _page, loc_.getValues().getValue(0));
        root_.setLength(_input.trim().length());
        addValues(root_, loc_);
        AnaPartType current_ = root_;
        while (true) {
            AnaPartType child_ = createFirstChildId(current_, _page, indexes_);
            if (child_ != null) {
                ((AnaParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            boolean stop_ = false;
            while (true) {
                current_.analyzeAccessibleId(_rooted, _page);
                processAfterAnalyze(_input, _rooted, _page, current_);
                AnaPartType next_ = createNextSiblingId(current_, _page, indexes_);
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    par_.analyzeAccessibleId(_rooted, _page);
                    processAfterAnalyze(_input,_rooted,_page,par_);
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
                    if (par_ instanceof AnaInnerPartType && next_.getNextSibling() == null) {
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
            if (current_ instanceof AnaWildCardPartType||current_ instanceof AnaRefPartType) {
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
        if (f_ == null || hasEmpty_ || (_l.getAnalyzedType().isEmpty() && !_l.isAlreadyError())) {
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

    private static AnaPartType createFirstChild(AnaPartType _parent, Ints _indexes) {
        if (!(_parent instanceof AnaParentPartType)) {
            return null;
        }
        AnaParentPartType par_ = (AnaParentPartType) _parent;
        StrTypes last_ = par_.getStrTypes();
        int off_ = par_.getIndexInType() + last_.firstKey();
        String v_ = last_.firstValue();
        off_ += StringUtil.getFirstPrintableCharIndex(v_);
        String trim_ = v_.trim();
        AnalyzingType an_ = ParserType.analyzeLocal(off_, trim_, _indexes);
        AnaPartType p_ = AnaPartType.createPartType(par_, 0, off_, an_, last_.getValue(0));
        p_.setLength(trim_.length());
        addValues(p_, an_);
        return p_;
    }
    private static AnaPartType createFirstChild(boolean _rootName, AnaPartType _parent, AnalyzedPageEl _page, Ints _indexes) {
        if (!(_parent instanceof AnaParentPartType)) {
            return null;
        }
        AnaParentPartType par_ = (AnaParentPartType) _parent;
        StrTypes last_ = par_.getStrTypes();
        int off_ = par_.getIndexInType() + last_.firstKey();
        String v_ = last_.firstValue();
        off_ += StringUtil.getFirstPrintableCharIndex(v_);
        String trim_ = v_.trim();
        AnalyzingType an_ = ParserType.analyzeLocal(off_, trim_, _indexes);
        AnaPartType p_ = AnaPartType.createPartType(_rootName, par_, 0, off_, an_, _page, v_);
        p_.setLength(trim_.length());
        addValues(p_, an_);
        return p_;
    }
    private static AnaPartType createFirstChildId(AnaPartType _parent, AnalyzedPageEl _page, Ints _indexes) {
        if (!(_parent instanceof AnaParentPartType)) {
            return null;
        }
        AnaParentPartType par_ = (AnaParentPartType) _parent;
        StrTypes last_ = par_.getStrTypes();
        int off_ = par_.getIndexInType() + last_.firstKey();
        String v_ = last_.firstValue();
        off_ += StringUtil.getFirstPrintableCharIndex(v_);
        AnalyzingType an_ = ParserType.analyzeLocalId(off_, v_.trim(), _indexes);
        AnaPartType p_ = AnaPartType.createPartType(false, par_, 0, off_, an_, _page, v_);
        p_.setLength(v_.trim().length());
        addValues(p_, an_);
        return p_;
    }

    private static AnaPartType createNextSibling(AnaPartType _parent, Ints _indexes) {
        AnaParentPartType par_ = _parent.getParent();
        if (!(par_ instanceof AnaBinaryType)) {
            return null;
        }
        AnaBinaryType b_ = (AnaBinaryType) par_;
        int indexCur_ = _parent.getIndex();
        int indexNext_ = indexCur_ + 1;
        StrTypes last_ = par_.getStrTypes();
        if (last_.size() <= indexNext_) {
            return null;
        }
        int off_ = par_.getIndexInType() + last_.getKey(indexNext_);
        String v_ = last_.getValue(indexNext_);
        off_ += StringUtil.getFirstPrintableCharIndex(v_);
        String trim_ = v_.trim();
        AnalyzingType an_ = ParserType.analyzeLocal(off_, trim_, _indexes);
        AnaPartType p_ = AnaPartType.createPartType(b_,indexNext_, off_, an_, last_.getValue(indexNext_));
        p_.setPreviousSibling(_parent);
        p_.setLength(trim_.length());
        addValues(p_, an_);
        return p_;
    }
    private static AnaPartType createNextSibling(boolean _rootName, AnaPartType _parent, AnalyzedPageEl _page, Ints _indexes) {
        AnaParentPartType par_ = _parent.getParent();
        if (!(par_ instanceof AnaBinaryType)) {
            return null;
        }
        AnaBinaryType b_ = (AnaBinaryType) par_;
        int indexCur_ = _parent.getIndex();
        int indexNext_ = indexCur_ + 1;
        StrTypes last_ = par_.getStrTypes();
        if (last_.size() <= indexNext_) {
            return null;
        }
        int off_ = par_.getIndexInType() + last_.getKey(indexNext_);
        String v_ = last_.getValue(indexNext_);
        off_ += StringUtil.getFirstPrintableCharIndex(v_);
        String trim_ = v_.trim();
        AnalyzingType an_ = ParserType.analyzeLocal(off_, trim_, _indexes);
        AnaPartType p_ = AnaPartType.createPartType(_rootName,b_,indexNext_, off_, an_, _page, v_);
        p_.setPreviousSibling(_parent);
        p_.setLength(trim_.length());
        addValues(p_, an_);
        return p_;
    }
    private static AnaPartType createNextSiblingId(AnaPartType _parent, AnalyzedPageEl _page, Ints _indexes) {
        AnaParentPartType par_ = _parent.getParent();
        if (!(par_ instanceof AnaBinaryType)) {
            return null;
        }
        AnaBinaryType b_ = (AnaBinaryType) par_;
        int indexCur_ = _parent.getIndex();
        int indexNext_ = indexCur_ + 1;
        StrTypes last_ = par_.getStrTypes();
        if (last_.size() <= indexNext_) {
            return null;
        }
        int off_ = par_.getIndexInType() + last_.getKey(indexNext_);
        String v_ = last_.getValue(indexNext_);
        off_ += StringUtil.getFirstPrintableCharIndex(v_);
        AnalyzingType an_ = ParserType.analyzeLocalId(off_, v_.trim(), _indexes);
        AnaPartType p_ = AnaPartType.createPartType(false,b_,indexNext_, off_, an_, _page, v_);
        p_.setPreviousSibling(_parent);
        p_.setLength(v_.trim().length());
        addValues(p_, an_);
        return p_;
    }

    private static void addValues(AnaPartType _p, AnalyzingType _an) {
        if (!(_p instanceof AnaParentPartType)) {
            return;
        }
        if (_p instanceof AnaTemplatePartType) {
            StrTypes values_;
            values_ = _an.getValues();
            values_.remove(values_.getValues().getLastIndex());
            ((AnaParentPartType)_p).getStrTypes().addAllEntries(values_);
        } else {
            ((AnaParentPartType)_p).getStrTypes().addAllEntries(_an.getValues());
        }
    }
}
