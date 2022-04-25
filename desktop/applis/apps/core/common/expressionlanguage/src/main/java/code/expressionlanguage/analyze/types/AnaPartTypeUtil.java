package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.common.AnaGeneType;
import code.maths.litteralcom.StrTypes;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
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
        AnaPartType root_ = AnaPartType.createPartType(null, 0, 0, loc_, _input);
        AnaPartType current_ = root_;
        while (current_ != null) {
            AnaPartType child_ = create(null, indexes_, 0, current_);
            if (child_ != null) {
                ((AnaParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            while (current_ != null) {
                if (isKo(current_,_excludedWords)) {
                    return false;
                }
                AnaPartType next_ = create(current_, indexes_, current_.getIndex() + 1, current_.getParent());
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
        return processAccessAnalyzeCommon(_input, false, _globalType, _local, _rooted, _loc, _page);
    }
    public static AnaResultPartType processAccAnalyze(String _input, String _globalType, AccessedBlock _local, AccessedBlock _rooted, int _loc, AnalyzedPageEl _page) {
        _page.setRefFileName(null);
        return processAccessAnalyze(_input, false, _globalType, _local, _rooted, _loc, _page);
    }
    public static AnaResultPartType processAnalyzeHeader(String _input, boolean _rootName, String _globalType, AccessedBlock _local, AccessedBlock _rooted, int _loc, AnalyzedPageEl _page) {
        return processAccessAnalyzeCommon(_input, _rootName, _globalType, _local, _rooted, _loc, _page);
    }

    private static AnaResultPartType processAccessAnalyzeCommon(String _input, boolean _rootName, String _globalType, AccessedBlock _local, AccessedBlock _rooted, int _loc, AnalyzedPageEl _page) {
        _page.setRefFileName(null);
        AnaResultPartType res_ = processAccessAnalyze(_input, _rootName, _globalType, _local, _rooted, _loc, _page);
        AnaPartType r_ = res_.getPartType();
        checkAccess(r_, _globalType, _page);
        return res_;
    }

    public static AnaResultPartType processAccessAnalyze(String _input, boolean _rootName, String _globalType, AccessedBlock _local, AccessedBlock _rooted, int _loc, AnalyzedPageEl _page) {
        String inputTr_ = _input.trim();
        Ints indexes_ = ParserType.getIndexes(inputTr_, _page);
        if (indexes_ == null) {
            return new AnaResultPartType(_input,_loc, _page.getAnalysisMessages(), _rooted);
        }
        AnaPartType root_ = root(_rootName, _page, inputTr_, indexes_);
        AnaPartType current_ = root_;
        while (current_ != null) {
            AnaPartType child_ = create(_rootName, null, _page, indexes_, 0, current_);
            if (child_ != null) {
                ((AnaParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            while (current_ != null) {
                current_.analyze(_globalType, _local, _rooted, _page, _loc);
                AnaPartType next_ = create(_rootName, current_, _page, indexes_, current_.getIndex() + 1, current_.getParent());
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
        return new AnaResultPartType(_input, _loc, root_, _rooted);
    }

    private static void checkAccessGeneral(String _gl, AnaPartType _current, AnalyzedPageEl _page) {
        if (_current instanceof AnaNamePartType) {
            ((AnaNamePartType) _current).checkAccessGeneral(_gl,_page);
        }
    }

    public static boolean processAnalyzeConstraintsCore(AnaResultPartType _className, StringMap<StringList> _inherit, boolean _exact, AnalyzedPageEl _page) {
        AnaPartType root_ = _className.getPartType();
        if (root_.getAnalyzedType().isEmpty()) {
            checkParametersCount(root_, _page);
            checkConstrains(root_, _inherit, _page);
            _className.setOk(false);
            return false;
        }
        if (!_exact && !_className.getResult().contains(StringExpUtil.TEMPLATE_BEGIN)) {
            _className.setOk(true);
            return true;
        }
        boolean res_ = checkParametersCount(root_, _page);
        boolean out_ = checkConstrains(root_, _inherit, _page);
        boolean ok_ = res_ && out_;
        _className.setOk(ok_);
        return ok_;
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

    private static boolean koTemp(StringMap<StringList> _inherit, AnaPartType _current, AnalyzedPageEl _page) {
        return _current instanceof AnaTemplatePartType && !((AnaTemplatePartType) _current).okTmp(_inherit, _page);
    }

    public static AnaResultPartType processAnalyzeLine(String _input, String _globalType, AccessedBlock _local, AccessedBlock _rooted, int _loc, AnalyzedPageEl _page) {
        _page.setRefFileName(null);
        AnaResultPartType anaType_ = getAnalyzeLine(_input, new AlwaysReadyTypes(), false, _local, _rooted, _loc, _page);
        AnaPartType root_ = anaType_.getPartType();
//        if (root_ == null) {
////            String pref_ = ExportCst.anchorErr(FoundErrorInterpret.buildARError(_page.getAnalysisMessages().getUnknownType(), _input));
////            _offs.add(new PartOffset(pref_,_loc));
////            _offs.add(new PartOffset(ExportCst.END_ANCHOR,_loc+_input.length()));
//            return new AnaResultPartType(_input,_loc,"",_page.getAnalysisMessages(), _rooted);
//        }
        checkAccess(root_,_globalType, _page);
        return anaType_;
    }
    static AnaResultPartType processAnalyzeLineWithoutErr(String _input, AccessedBlock _local, AccessedBlock _rooted, int _loc, AnalyzedPageEl _page) {
        _page.setRefFileName(null);
         return getAnalyzeLine(_input, new AlwaysReadyTypes(), false, _local, _rooted, _loc, _page);
    }
    static String processAnalyzeLineInherits(String _input, ReadyTypes _ready, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page) {
        AnaPartType anaType_ = getAnalyzeLine(_input, _ready, true, _local, _rooted, -1, _page).getPartType();
//        if (anaType_ == null) {
//            return "";
//        }
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
            return new AnaResultPartType(_input,_loc, _page.getAnalysisMessages(), _rooted);
        }
        AnaPartType root_ = root(_rootName, _page, inputTr_, indexes_);
        AnaPartType current_ = root_;
        while (current_ != null) {
            AnaPartType child_ = create(_rootName, null, _page, indexes_, 0, current_);
            if (child_ != null) {
                ((AnaParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            while (current_ != null) {
                current_.analyzeLine(_ready, _local, _rooted, _page, _loc);
                AnaPartType next_ = create(_rootName, current_, _page, indexes_, current_.getIndex() + 1, current_.getParent());
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
        return new AnaResultPartType(_input, _loc, root_, _rooted);
    }

    private static AnaPartType root(boolean _rootName, AnalyzedPageEl _page, String _inputTr, Ints _indexes) {
        AnalyzingType loc_ = ParserType.analyzeLocal(0, _inputTr, _indexes);
        return commonRoot(_rootName, _page, _inputTr, loc_);
    }

    private static AnaPartType commonRoot(boolean _rootName, AnalyzedPageEl _page, String _inputTr, AnalyzingType _loc) {
        AnaPartType root_ = AnaPartType.createPartType(_rootName, null, 0, 0, _loc, _page, _inputTr);
        root_.setLength(_inputTr.length());
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
            return new AnaResultPartType(_input,_loc, _page.getAnalysisMessages(), _rooted);
        }
        AnaPartType root_ = rootId(_page, indexes_, _input.trim());
        AnaPartType current_ = root_;
        while (current_ != null) {
            AnaPartType child_ = createId(null, _page, indexes_, current_, 0);
            if (child_ != null) {
                ((AnaParentPartType)current_).appendChild(child_);
                current_ = child_;
                continue;
            }
            while (current_ != null) {
                current_.analyzeAccessibleId(_rooted, _page,_loc);
                AnaPartType next_ = createId(current_, _page, indexes_, current_.getParent(), current_.getIndex() + 1);
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
        return new AnaResultPartType(_input, _loc, root_, _rooted);
    }

    private static AnaPartType rootId(AnalyzedPageEl _page, Ints _indexes, String _trimInput) {
        AnalyzingType loc_ = ParserType.analyzeLocalId(0, _trimInput, _indexes);
        return commonRoot(false, _page, _trimInput, loc_);
    }

    private static AnaPartType create(AnaPartType _prev, Ints _indexes, int _next, AnaPartType _par) {
        if (!(_par instanceof AnaParentPartType)) {
            return null;
        }
        AnaParentPartType b_ = (AnaParentPartType)_par;
        StrTypes last_ = b_.getStrTypes();
        if (last_.size() <= _next) {
            return null;
        }
        String v_ = last_.getValue(_next);
        int off_ = off(_next, b_, last_, v_);
        String trim_ = v_.trim();
        AnalyzingType an_ = ParserType.analyzeLocal(off_, trim_, _indexes);
        AnaPartType p_ = AnaPartType.createPartType(b_,_next, off_, an_, last_.getValue(_next));
        return postCreate(_prev, trim_, p_);
    }

    private static AnaPartType create(boolean _rootName, AnaPartType _prev, AnalyzedPageEl _page, Ints _indexes, int _next, AnaPartType _pa) {
        if (!(_pa instanceof AnaParentPartType)) {
            return null;
        }
        AnaParentPartType b_ = (AnaParentPartType) _pa;
        StrTypes last_ = b_.getStrTypes();
        if (last_.size() <= _next) {
            return null;
        }
        String v_ = last_.getValue(_next);
        int off_ = off(_next, b_, last_, v_);
        String trim_ = v_.trim();
        AnalyzingType an_ = ParserType.analyzeLocal(off_, trim_, _indexes);
        AnaPartType p_ = AnaPartType.createPartType(_rootName,b_,_next, off_, an_, _page, v_);
        return postCreate(_prev, trim_, p_);
    }

    private static AnaPartType postCreate(AnaPartType _prev, String _trim, AnaPartType _p) {
        _p.setPreviousSibling(_prev);
        _p.setLength(_trim.length());
        return _p;
    }

    private static AnaPartType createId(AnaPartType _prev, AnalyzedPageEl _page, Ints _indexes, AnaPartType _pa, int _next) {
        if (!(_pa instanceof AnaParentPartType)) {
            return null;
        }
        AnaParentPartType b_ = (AnaParentPartType) _pa;
        StrTypes last_ = b_.getStrTypes();
        if (last_.size() <= _next) {
            return null;
        }
        String v_ = last_.getValue(_next);
        int off_ = off(_next, b_, last_, v_);
        String trim_ = v_.trim();
        AnalyzingType an_ = ParserType.analyzeLocalId(off_, trim_, _indexes);
        AnaPartType p_ = AnaPartType.createPartType(false,b_,_next, off_, an_, _page, v_);
        return postCreate(_prev, trim_, p_);
    }

    private static int off(int _next, AnaParentPartType _b, StrTypes _last, String _v) {
        int off_ = _b.getIndexInType() + _last.getKey(_next);
        off_ += StringUtil.getFirstPrintableCharIndex(_v);
        return off_;
    }

}
