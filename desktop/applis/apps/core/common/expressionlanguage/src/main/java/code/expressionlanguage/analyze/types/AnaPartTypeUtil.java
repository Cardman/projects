package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.linkage.ExportCst;
import code.maths.litteralcom.StrTypes;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.analyze.instr.PartOffset;
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
        while (current_ != null) {
            AnaPartType child_ = createFirstChild(current_, indexes_);
            if (child_ != null) {
                ((AnaParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            while (current_ != null) {
                if (isKo(current_,_excludedWords)) {
                    return false;
                }
                AnaPartType next_ = createNextSibling(current_, indexes_);
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                current_ = goUp(par_,root_);
            }
        }
        return !(root_ instanceof AnaWildCardPartType) && !(root_ instanceof AnaRefPartType);
    }
    static AnaPartType goUp(AnaPartType _par, AnaPartType _root) {
        if (_par == null||_par == _root) {
            return null;
        }
        return _par;
    }

    static boolean isKo(AnaPartType _current, CustList<String> _excludedWords) {
        if (_current instanceof AnaEmptyPartType) {
            return true;
        }
        if (_current instanceof AnaNamePartType) {
            String typeName_ = ((AnaLeafPartType) _current).getTypeName();
            if (isKoForWord(typeName_,_excludedWords)) {
                return true;
            }
        }
        return (_current instanceof AnaEmptyWildCardPart
                || _current instanceof AnaWildCardPartType
                || _current instanceof AnaRefPartType) && !(_current.getParent() instanceof AnaTemplatePartType);
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

    public static AnaResultPartType processAnalyze(String _input, String _globalType, AccessedBlock _local, AccessedBlock _rooted, int _loc, AnalyzedPageEl _page) {
        _page.setRefFileName(null);
        AnaResultPartType res_ = processAccessAnalyze(_input, false, _globalType, _local, _rooted, _loc, _page);
        String analyzedType_ = res_.getResult();
        if (analyzedType_.isEmpty()) {
            return res_;
        }
        AnaPartType r_ = res_.getPartType();
        checkAccess(r_,_globalType, _page);
        return res_;
    }
    public static AnaResultPartType processAccAnalyze(String _input, String _globalType, AccessedBlock _local, AccessedBlock _rooted, int _loc, AnalyzedPageEl _page) {
        _page.setRefFileName(null);
        return processAccessAnalyze(_input, false, _globalType, _local, _rooted, _loc, _page);
    }
    public static AnaResultPartType processAnalyzeHeader(String _input, boolean _rootName, String _globalType, AccessedBlock _local, AccessedBlock _rooted, int _loc, AnalyzedPageEl _page) {
        _page.setRefFileName(null);
        AnaResultPartType res_ = processAccessAnalyze(_input, _rootName, _globalType, _local, _rooted, _loc, _page);
        String analyzedType_ = res_.getResult();
        if (analyzedType_.isEmpty()) {
            return res_;
        }
        AnaPartType r_ = res_.getPartType();
        checkAccess(r_,_globalType, _page);
        return res_;
    }
    public static AnaResultPartType processAccessAnalyze(String _input, boolean _rootName, String _globalType, AccessedBlock _local, AccessedBlock _rooted, int _loc, AnalyzedPageEl _page) {
        String inputTr_ = _input.trim();
        Ints indexes_ = ParserType.getIndexes(inputTr_, _page);
        if (indexes_ == null) {
            return new AnaResultPartType(_input,_loc,"",null, _rooted);
        }
        AnaPartType root_ = root(_rootName, _page, inputTr_, indexes_);
        AnaPartType current_ = root_;
        while (current_ != null) {
            AnaPartType child_ = createFirstChild(_rootName,current_, _page, indexes_);
            if (child_ != null) {
                ((AnaParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            while (current_ != null) {
                current_.analyze(_globalType, _local, _rooted, _page, _loc);
                AnaPartType next_ = createNextSibling(_rootName,current_, _page, indexes_);
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    par_.analyze(_globalType, _local, _rooted, _page, _loc);
                    current_ = null;
                } else {
                    current_ = par_;
                }
            }
        }
        AnaResultPartType out_ = new AnaResultPartType(_input, _loc, "", root_, _rooted);
        lookForTypes(out_, _page);
        return out_;
    }

    private static void processAfterAnalyzeLoop(String _input, AccessedBlock _rooted, AnaPartType _root, AnalysisMessages _analysisMessages) {
        AnaPartType current_ = _root;
        while (current_ != null) {
            AnaPartType child_ = current_.getFirstChild();
            if (child_ != null) {
                current_ = child_;
                continue;
            }
            processInaccessibleOffsets(current_, _analysisMessages);
            while (current_ != null) {
                processAfterAnalyze(_input, _rooted, current_, _analysisMessages);
                AnaPartType next_ = current_.getNextSibling();
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                if (par_ == _root) {
                    processAfterAnalyze(_input,_rooted, par_, _analysisMessages);
                    current_ = null;
                } else {
                    current_ = par_;
                }
            }
        }
    }
    private static void processAfterAnalyze(String _input, AccessedBlock _rooted, AnaPartType _current, AnalysisMessages _analysisMessages) {
        if (_current.getAnalyzedType().isEmpty()) {
            processInexist(_input, _current, _analysisMessages);
        } else {
            processLeafOffsets(_rooted, _current);
            checkIndivCt(_current, _analysisMessages);
            processErrorParamCount(_current, _analysisMessages);
        }
    }

    private static void processInexist(String _input, AnaPartType _current, AnalysisMessages _analysisMessages) {
        if (_current instanceof AnaEmptyPartType) {
            AnaParentPartType par_ = _current.getParent();
            if (par_ != null) {
                return;
            }
        }
        _current.processInexistType(_input, _analysisMessages);
    }

    private static String buildOffsetPartDefault(AnaPartType _current) {
        if (_current instanceof AnaNamePartType) {
            AnaNamePartType current_ = (AnaNamePartType) _current;
            if (!current_.isBuildRef()) {
                return _current.buildOffsetPart("", "");
            }
            FileBlock currentFile_ = current_.getCurrentFile();
            FileBlock refFileName_ = current_.getRefFileName();
            int value_ = current_.getValue();
            return _current.buildOffsetPart(ExportCst.href(currentFile_,refFileName_,value_), current_.getTitleRef());
        }
        if (_current instanceof AnaVariablePartType) {
            AnaVariablePartType current_ = (AnaVariablePartType) _current;
            FileBlock refFileName_ = current_.getRefFileName();
            int value_ = current_.getValue();
            if (refFileName_ != null) {
                return _current.buildOffsetPartDefault(ExportCst.href(current_.getCurrentFile(), refFileName_,value_));
            } else {
                return _current.buildOffsetPartDefault(ExportCst.href(value_));
            }
        }
        return _current.buildOffsetPart();
    }

    private static void checkAccessGeneral(String _gl, AnaPartType _current, AnalyzedPageEl _page) {
        if (_current instanceof AnaNamePartType) {
            ((AnaNamePartType) _current).checkAccessGeneral(_gl,_page);
        }
    }

    public static boolean processAnalyzeConstraints(AnaResultPartType _className, StringMap<StringList> _inherit, boolean _exact, CustList<PartOffset> _parts, AnalyzedPageEl _page) {
        boolean ok_ = processAnalyzeConstraintsCore(_className, _inherit, _exact, _page);
        processAnalyzeConstraintsRep(_className, _parts, _page);
        return ok_;
    }
    public static boolean processAnalyzeConstraintsCore(AnaResultPartType _className, StringMap<StringList> _inherit, boolean _exact, AnalyzedPageEl _page) {
        AnaPartType root_ = _className.getPartType();
        if (root_ == null) {
            return false;
        }
        if (root_.getAnalyzedType().isEmpty()) {
            return false;
        }
        if (!_exact && !_className.getResult().contains(StringExpUtil.TEMPLATE_BEGIN)) {
            return true;
        }
        boolean res_ = checkParametersCount(root_, _page);
        boolean out_ = checkConstrains(root_, _inherit, _page);
        return res_&&out_;
    }
    public static void processAnalyzeConstraintsRep(AnaResultPartType _className, CustList<PartOffset> _parts, AnalyzedPageEl _page) {
        if (!_page.isGettingParts()) {
            return;
        }
        AnalysisMessages analysisMessages_ = _page.getAnalysisMessages();
        processAnalyzeConstraintsRep(_className, _parts, analysisMessages_);
    }

    public static void processAnalyzeConstraintsRep(AnaResultPartType _className, CustList<PartOffset> _parts, AnalysisMessages _analysisMessages) {
        AnaPartType root_ = _className.getPartType();
        if (root_ == null) {
            int loc_ = _className.getLoc();
            String input_ = _className.getInput();
            if (input_.isEmpty()) {
                return;
            }
            String pref_ = ExportCst.anchorErr(FoundErrorInterpret.buildARError(_analysisMessages.getUnknownType(), input_));
            _parts.add(new PartOffset(pref_, loc_));
            _parts.add(new PartOffset(ExportCst.END_ANCHOR, loc_ + input_.length()));
            return;
        }
        int loc_ = _className.getLoc();
        String input_ = _className.getInput();
        if (input_.isEmpty()) {
            String pref_ = ExportCst.anchorErr(FoundErrorInterpret.buildARError(_analysisMessages.getEmptyType()));
            _parts.add(new PartOffset(pref_, loc_));
            _parts.add(new PartOffset(ExportCst.END_ANCHOR, loc_ + 1));
            return;
        }
        processAfterAnalyzeLoop(_className.getInput(), _className.getRooted(), root_, _analysisMessages);
        appendParts(root_, _parts);
    }

    static void lookForTypes(AnaResultPartType _className, AnalyzedPageEl _page){
        if (!_page.isGettingParts()) {
            return;
        }
        AnaPartType root_ = _className.getPartType();
        AnaPartType current_ = root_;
        while (current_ != null) {
            AnaPartType child_ = current_.getFirstChild();
            if (child_ != null) {
                current_ = child_;
                continue;
            }
            while (current_ != null) {
                lookForTypes(_page,current_);
                AnaPartType next_ = current_.getNextSibling();
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    lookForTypes(_page,root_);
                    current_ = null;
                } else {
                    current_ = par_;
                }
            }
        }
    }

    private static void lookForTypes(AnalyzedPageEl _page, AnaPartType _current) {
        if (!(_current instanceof AnaNamePartType)) {
            return;
        }
        ((AnaNamePartType)_current).processFound(_page);
    }

    static boolean checkParametersCount(AnaPartType _root, AnalyzedPageEl _page){
        AnaPartType current_ = _root;
        boolean ok_ = true;
        while (current_ != null) {
            AnaPartType child_ = current_.getFirstChild();
            if (child_ != null) {
                current_ = child_;
                continue;
            }
            while (current_ != null) {
                ok_ = chCount(_page, current_, ok_);
                AnaPartType next_ = current_.getNextSibling();
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                if (par_ == _root) {
                    ok_ = chCount(_page, par_, ok_);
                    current_ = null;
                } else {
                    current_ = par_;
                }
            }
        }
        return ok_;
    }

    private static boolean chCount(AnalyzedPageEl _page, AnaPartType _current, boolean _ok) {
        if (isNotCorrectParam(_current, _page)) {
            return false;
        }
        return _ok;
    }

    private static void processErrorParamCount(AnaPartType _current, AnalysisMessages _analysisMessages) {
        if (!_current.isErrorNbParam()) {
            return;
        }
        AnaPartType ch_ = _current.getFirstChild();
        if (ch_ != null&&_current instanceof AnaTemplatePartType) {
            String err_ = FoundErrorInterpret.buildARError(_analysisMessages.getBadParamerizedType(), ch_.getAnalyzedType());
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
            String err_ = FoundErrorInterpret.buildARError(_analysisMessages.getBadParamerizedType(), l_.getAnalyzedType());
            _current.getErrs().add(err_);
            return;
        }
        _current.processBadFormedOffsets(_analysisMessages);
    }

    private static boolean isNotCorrectParam(AnaPartType _current, AnalyzedPageEl _page) {
        //AnaInnerPartType,AnaTemplatePartType,AnaNamePartType
        String analyzedType_ = _current.getAnalyzedType();
        String idCl_ = StringExpUtil.getIdFromAllTypes(analyzedType_);
        String compo_ = StringExpUtil.getQuickComponentBaseType(idCl_).getComponent();
        AnaGeneType info_ = _page.getAnaGeneType(compo_);
        boolean ko_ = !skip(_current) && !AnaInherits.correctNbParameters(info_, analyzedType_, _page);
        if (ko_) {
            _current.setErrorNbParam();
        }
        return ko_;
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
        while (current_ != null) {
            AnaPartType child_ = current_.getFirstChild();
            if (child_ != null) {
                current_ = child_;
                continue;
            }
            while (current_ != null) {
                ok_ = checkNb(_inherit, _page, current_, ok_);
                AnaPartType next_ = current_.getNextSibling();
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                if (par_ == _root) {
                    ok_ = checkNb(_inherit, _page, par_, ok_);
                    current_ = null;
                } else {
                    current_ = par_;
                }
            }
        }
        return ok_;
    }

    private static boolean checkNb(StringMap<StringList> _inherit, AnalyzedPageEl _page, AnaPartType _current, boolean _ok) {
        if (koTemp(_inherit, _current, _page)) {
            ((AnaTemplatePartType) _current).setKoConstraints();
            return false;
        }
        return _ok;
    }

    private static void checkIndivCt(AnaPartType _current, AnalysisMessages _analysisMessages) {
        if (koTempErr(_current)) {
            ((AnaTemplatePartType) _current).buildBadConstraintsOffsetList(_analysisMessages);
        }
    }

    private static boolean koTemp(StringMap<StringList> _inherit, AnaPartType _current, AnalyzedPageEl _page) {
        return _current instanceof AnaTemplatePartType && !((AnaTemplatePartType) _current).okTmp(_inherit, _page);
    }

    private static boolean koTempErr(AnaPartType _current) {
        return _current instanceof AnaTemplatePartType && ((AnaTemplatePartType) _current).isKoConstraints();
    }

    public static AnaResultPartType processAnalyzeLine(String _input, String _globalType, AccessedBlock _local, AccessedBlock _rooted, int _loc, AnalyzedPageEl _page) {
        _page.setRefFileName(null);
        AnaResultPartType anaType_ = getAnalyzeLine(_input, new AlwaysReadyTypes(), false, _local, _rooted, _loc, _page);
        AnaPartType root_ = anaType_.getPartType();
        if (root_ == null) {
//            String pref_ = ExportCst.anchorErr(FoundErrorInterpret.buildARError(_page.getAnalysisMessages().getUnknownType(), _input));
//            _offs.add(new PartOffset(pref_,_loc));
//            _offs.add(new PartOffset(ExportCst.END_ANCHOR,_loc+_input.length()));
            return new AnaResultPartType(_input,_loc,"",null, _rooted);
        }
        checkAccess(root_,_globalType, _page);
        return anaType_;
    }
    static AnaResultPartType processAnalyzeLineWithoutErr(String _input, AccessedBlock _local, AccessedBlock _rooted, int _loc, AnalyzedPageEl _page) {
        _page.setRefFileName(null);
        AnaResultPartType anaType_ = getAnalyzeLine(_input, new AlwaysReadyTypes(), false, _local, _rooted, _loc, _page);
        if (anaType_.getResult().isEmpty()) {
            return new AnaResultPartType(_input,_loc,"",null, _rooted);
        }
//        processAfterAnalyzeLoop(_input,_rooted,_page,root_);
//        appendParts(root_,_offs, _page);
        return anaType_;
    }
    static String processAnalyzeLineInherits(String _input, ReadyTypes _ready, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page) {
        AnaPartType anaType_ = getAnalyzeLine(_input, _ready, true, _local, _rooted, -1, _page).getPartType();
        if (anaType_ == null) {
            return "";
        }
        String ana_ = anaType_.getAnalyzedType();
        if (!_ready.isReady(ana_)) {
            return "";
        }
        return ana_;
    }
    private static AnaResultPartType getAnalyzeLine(String _input, ReadyTypes _ready, boolean _rootName, AccessedBlock _local, AccessedBlock _rooted, int _loc, AnalyzedPageEl _page) {
        String inputTr_ = _input.trim();
        Ints indexes_ = ParserType.getIndexes(inputTr_, _page);
        if (indexes_ == null) {
            return new AnaResultPartType(_input,_loc,"",null, _rooted);
        }
        AnaPartType root_ = root(_rootName, _page, inputTr_, indexes_);
        AnaPartType current_ = root_;
        while (current_ != null) {
            AnaPartType child_ = createFirstChild(_rootName,current_, _page, indexes_);
            if (child_ != null) {
                ((AnaParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            while (current_ != null) {
                current_.analyzeLine(_ready, _local, _rooted, _page, _loc);
                AnaPartType next_ = createNextSibling(_rootName,current_, _page, indexes_);
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    par_.analyzeLine(_ready, _local, _rooted, _page, _loc);
                    current_ = null;
                } else {
                    current_ = par_;
                }
            }
        }
        AnaResultPartType out_ = new AnaResultPartType(_input, _loc, "", root_, _rooted);
        lookForTypes(out_, _page);
        return out_;
    }

    private static AnaPartType root(boolean _rootName, AnalyzedPageEl _page, String _inputTr, Ints _indexes) {
        AnalyzingType loc_ = ParserType.analyzeLocal(0, _inputTr, _indexes);
        return commonRoot(_rootName, _page, _inputTr, loc_);
    }

    private static AnaPartType commonRoot(boolean _rootName, AnalyzedPageEl _page, String _inputTr, AnalyzingType _loc) {
        AnaPartType root_ = AnaPartType.createPartType(_rootName, null, 0, 0, _loc, _page, _inputTr);
        root_.setLength(_inputTr.length());
        addValues(root_, _loc);
        return root_;
    }

    private static void checkAccess(AnaPartType _root, String _gl, AnalyzedPageEl _page) {
        AnaPartType current_ = _root;
        while (current_ != null) {
            AnaPartType child_ = current_.getFirstChild();
            if (child_ != null) {
                current_ = child_;
                continue;
            }
            while (current_ != null) {
                checkAccessGeneral(_gl, current_, _page);
                AnaPartType next_ = current_.getNextSibling();
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                current_ = goUp(par_,_root);
            }
        }
    }

    static AnaResultPartType processAnalyzeAccessibleId(String _input, AccessedBlock _rooted, FileBlock _refFileName, int _loc, AnalyzedPageEl _page) {
        _page.setRefFileName(_refFileName);
        Ints indexes_ = ParserType.getIndexes(_input.trim(), _page);
        if (indexes_ == null) {
//            String pref_ = ExportCst.anchorErr(FoundErrorInterpret.buildARError(_page.getAnalysisMessages().getUnknownType(), _input));
//            _offs.add(new PartOffset(pref_,_loc));
//            _offs.add(new PartOffset(ExportCst.END_ANCHOR,_loc+_input.length()));
            return new AnaResultPartType(_input,_loc,"",null, _rooted);
        }
        AnaPartType root_ = rootId(_page, indexes_, _input.trim());
        AnaPartType current_ = root_;
        while (current_ != null) {
            AnaPartType child_ = createFirstChildId(current_, _page, indexes_);
            if (child_ != null) {
                ((AnaParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            while (current_ != null) {
                current_.analyzeAccessibleId(_rooted, _page,_loc);
                AnaPartType next_ = createNextSiblingId(current_, _page, indexes_);
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    par_.appendChild(next_);
                    current_ = next_;
                    break;
                }
                if (par_ == root_) {
                    par_.analyzeAccessibleId(_rooted, _page,_loc);
                    current_ = null;
                } else {
                    current_ = par_;
                }
            }
        }
//        processAfterAnalyzeLoop(_input,_rooted,_page,root_);
//        if (root_.getAnalyzedType().isEmpty()) {
//            appendQuickParts(root_,_offs, _page);
//            return new AnaResultPartType("",null);
//        }
        AnaResultPartType out_ = new AnaResultPartType(_input, _loc, "", root_, _rooted);
        lookForTypes(out_, _page);
        return out_;
    }

    private static AnaPartType rootId(AnalyzedPageEl _page, Ints _indexes, String _trimInput) {
        AnalyzingType loc_ = ParserType.analyzeLocalId(0, _trimInput, _indexes);
        return commonRoot(false, _page, _trimInput, loc_);
    }

    private static void processLeafOffsets(AccessedBlock _rooted, AnaPartType _current) {
        if (_current instanceof AnaNamePartType) {
            ((AnaNamePartType)_current).processOffsets(_rooted);
        }
        if (_current instanceof AnaVariablePartType) {
            ((AnaVariablePartType)_current).processOffsets(_rooted);
        }
    }

    private static void appendParts(AnaPartType _root, CustList<PartOffset> _offs) {
        AnaPartType current_ = _root;
        while (current_ != null) {
            appendOffsetBegin(_offs, current_);
            AnaPartType child_ = current_.getFirstChild();
            if (child_ != null) {
                current_ = child_;
                continue;
            }
            String candidate_ = buildOffsetPartDefault(current_);
            appendOffset(candidate_,_offs, current_,current_.getFull(),current_.getLength());
            while (current_ != null) {
                AnaPartType next_ = current_.getNextSibling();
                AnaParentPartType par_ = current_.getParent();
                if (next_ != null) {
                    appendInternNext(_offs, current_, next_, par_);
                    current_ = next_;
                    break;
                }
                appendEnd(_offs, par_);
                current_ = goUp(par_,_root);
            }
        }
    }

    private static void processInaccessibleOffsets(AnaPartType _current, AnalysisMessages _analysisMessages) {
        if (_current instanceof AnaNamePartType) {
            ((AnaNamePartType) _current).processInaccessibleOffsets(_analysisMessages);
        }
    }

    private static void appendInternNext(CustList<PartOffset> _offs, AnaPartType _current, AnaPartType _next, AnaParentPartType _par) {
        if (error(_par)) {
            appendOffsetNext(_offs, _current, _next, _par);
            return;
        }
        int index_ = _current.getIndex();
        if (_par instanceof AnaTemplatePartType && _par.getErrsList().isValidIndex(index_)) {
            int begin_ = _par.getFullBegin(index_);
            _offs.add(new PartOffset(ExportCst.anchorErr(_par.getErrsList().get(index_)), begin_));
            int len_ = _par.getOpLen(index_);
            _offs.add(new PartOffset(ExportCst.END_ANCHOR, begin_ + len_));
        }
        if (_par instanceof AnaInnerPartType && _next.getNextSibling() == null) {
            String cand_ = _par.buildOffsetPart();
            appendIntern(cand_, _offs, _par.buildErrorInexistBegin(), _par.buildErrorInexistEnd());
        }
    }

    private static void appendEnd(CustList<PartOffset> _offs, AnaParentPartType _par) {
        if (error(_par)) {
            appendOffsetEnd(_offs, _par);
            return;
        }
        if (_par instanceof AnaTemplatePartType) {
            StringList errLen_ = _par.getErrs();
            if (!errLen_.isEmpty()) {
                int begin_ = _par.getFullBegin();
                int len_ = _par.getOpLen();
                _offs.add(new PartOffset(ExportCst.anchorErr(StringUtil.join(errLen_,ExportCst.JOIN_ERR)),begin_));
                _offs.add(new PartOffset(ExportCst.END_ANCHOR,begin_+len_));
            }
        }
    }

    private static boolean error(AnaPartType _par) {
        return _par != null && _par.getAnalyzedType().isEmpty();
    }

    private static void appendOffsetEnd(CustList<PartOffset> _offs, AnaParentPartType _par) {
        if (_par instanceof AnaArraryPartType) {
            appendOffset(_offs, _par);
        }
    }

    private static void appendOffsetBegin(CustList<PartOffset> _offs, AnaPartType _current) {
        if (_current instanceof AnaWildCardPartType|| _current instanceof AnaRefPartType) {
            appendOffset(_offs, (AnaParentPartType) _current);
        }
    }

    private static void appendOffsetNext(CustList<PartOffset> _offs, AnaPartType _current, AnaPartType _next, AnaParentPartType _par) {
        if (_par instanceof AnaTemplatePartType&& _current.getIndex() == 0) {
            appendOffset(_offs, _par);
        }
        if (_par instanceof AnaInnerPartType&& _next.getNextSibling() == null) {
            appendOffset(_offs, _par);
        }
    }

    private static void appendOffset(CustList<PartOffset> _offs, AnaParentPartType _par) {
        String cand_ = _par.buildOffsetPart();
        appendOffset(cand_,_offs, _par,_par.buildErrorInexistBegin(),_par.buildErrorInexistEnd());
    }

    private static void appendOffset(String _candidate,CustList<PartOffset> _offs, AnaPartType _l, int _begin, int _len) {
        boolean hasEmpty_ = false;
        boolean alreadyError_ = false;
        AnaPartType f_ = _l.getFirstChild();
        AnaPartType ch_ = f_;
        while (ch_ != null) {
            if (ch_ instanceof AnaEmptyPartType) {
                hasEmpty_ = true;
            }
            if (!ch_.getErrs().isEmpty()) {
                alreadyError_ = true;
            }
            ch_ = ch_.getNextSibling();
        }
        if (_l instanceof AnaNamePartType) {
            AnaPartType prev_ = ((AnaNamePartType) _l).getPreviousPartType();
            if (prev_ == null || prev_.getErrs().isEmpty()) {
                appendIntern(_candidate,_offs, _begin,_len);
            }
            return;
        }
        if (f_ == null || hasEmpty_ || !alreadyError_) {
            appendIntern(_candidate,_offs, _begin,_len);
        }
    }

    private static void appendIntern(String _candidate, CustList<PartOffset> _offs, int _begin, int _len) {
        if (_candidate.isEmpty()) {
            return;
        }
        _offs.add(new PartOffset(_candidate,_begin));
        _offs.add(new PartOffset(ExportCst.END_ANCHOR,_begin+_len));
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
