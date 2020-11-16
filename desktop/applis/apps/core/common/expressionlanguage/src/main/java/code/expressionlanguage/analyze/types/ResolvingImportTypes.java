package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ImportedField;
import code.expressionlanguage.analyze.ImportedMethod;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.util.MappingLocalType;
import code.expressionlanguage.common.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.analyze.util.TypeVar;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.util.*;
import code.util.core.StringUtil;

public final class ResolvingImportTypes {
    private ResolvingImportTypes() {

    }
    public static String resolveAccessibleIdTypeWithoutError(int _loc, String _in, AnalyzedPageEl _page) {
        String void_ = _page.getAliasVoid();
        if (StringUtil.quickEq(_in.trim(), void_)) {
            return "";
        }
        AccessedBlock r_ = _page.getCurrentGlobalBlock().getCurrentGlobalBlock();
        int rc_ = _page.getLocalizer().getCurrentLocationIndex()+_loc;
        AccessedBlock a_ = _page.getCurrentGlobalBlock().getCurrentGlobalBlock(r_);
        CustList<PartOffset> offs_ = _page.getCurrentParts();
        return AnaPartTypeUtil.processAnalyzeLineWithoutErr(_in, a_,r_, rc_, offs_, _page).getResult();
    }
    public static String resolveCorrectAccessibleType(int _loc, String _in, String _fromType, AnalyzedPageEl _page) {
        int rc_ = _page.getLocalizer().getCurrentLocationIndex()+_loc;
        String tr_ = _in.trim();
        if (tr_.isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(_page.getAnalysisMessages().getEmptyType());
            _page.getLocalizer().addError(un_);
            CustList<PartOffset> partOffsets_ = _page.getCurrentParts();
            partOffsets_.clear();
            String err_ = un_.getBuiltError();
            String pref_ = "<a title=\""+err_+"\" class=\"e\">";
            partOffsets_.add(new PartOffset(pref_,rc_));
            partOffsets_.add(new PartOffset("</a>",rc_+1));
            return _page.getAliasObject();
        }
        String void_ = _page.getAliasVoid();
        if (StringUtil.quickEq(tr_, void_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(_page.getAnalysisMessages().getVoidType(),
                    void_);
            _page.getLocalizer().addError(un_);
            CustList<PartOffset> partOffsets_ = _page.getCurrentParts();
            partOffsets_.clear();
            String err_ = un_.getBuiltError();
            String pref_ = "<a title=\""+err_+"\" class=\"e\">";
            partOffsets_.add(new PartOffset(pref_,rc_));
            partOffsets_.add(new PartOffset("</a>",rc_+void_.length()));
            return _page.getAliasObject();
        }
        AccessedBlock r_ = _page.getCurrentGlobalBlock().getCurrentGlobalBlock();
        StringMap<StringList> vars_ = new StringMap<StringList>();
        String idFromType_ = StringExpUtil.getIdFromAllTypes(_fromType);
        AnaGeneType from_ = _page.getAnaGeneType(idFromType_);
        String ref_ = "";
        if (ContextUtil.isFromCustFile(from_)) {
            ref_ = ((Block)from_).getFile().getRenderFileName();
        }
        _page.getAvailableVariables().clear();
        if (from_ instanceof RootBlock) {
            for (TypeVar t: ((RootBlock)from_).getParamTypesMapValues()) {
                _page.getAvailableVariables().addEntry(t.getName(),t.getOffset());
                vars_.addEntry(t.getName(), t.getConstraints());
            }
        }
        CustList<PartOffset> partOffsets_ = _page.getCurrentParts();
        partOffsets_.clear();
        AnaResultPartType resType_ = AnaPartTypeUtil.processAnalyzeAccessibleId(_in, r_, ref_,rc_,partOffsets_, _page);
        if (resType_.getResult().trim().isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(_page.getAnalysisMessages().getUnknownType(),
                    _in);
            _page.getLocalizer().addError(un_);
            return _page.getAliasObject();
        }
        if (!AnaPartTypeUtil.processAnalyzeConstraints(resType_, vars_, true,partOffsets_, _page)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(_page.getAnalysisMessages().getBadParamerizedType(),
                    _in);
            _page.getLocalizer().addError(un_);
            return _page.getAliasObject();
        }
        return resType_.getResult();
    }
    public static String resolveCorrectTypeWithoutErrors(int _loc, String _in, boolean _exact, CustList<PartOffset> _partOffsets, AnalyzedPageEl _page) {
        String void_ = _page.getAliasVoid();
        if (StringUtil.quickEq(_in.trim(), void_)) {
            return "";
        }
        AccessedBlock r_ = _page.getCurrentGlobalBlock().getCurrentGlobalBlock();
        StringMap<StringList> varsCt_ = _page.getCurrentConstraints().getCurrentConstraints();
        _page.getBuildingConstraints().buildCurrentConstraintsFull();
        AccessedBlock a_ = _page.getCurrentGlobalBlock().getCurrentGlobalBlock(r_);
        String gl_ = _page.getGlobalClass();
        int rc_ = _page.getLocalizer().getCurrentLocationIndex() + _loc;
        _page.getCurrentBadIndexes().clear();
        AnaResultPartType resType_;
        if (_exact) {
            resType_ = AnaPartTypeUtil.processAnalyze(_in, false,gl_, a_,r_, rc_, _partOffsets, _page);
        } else {
            resType_ = AnaPartTypeUtil.processAnalyzeLine(_in, false, gl_, a_,r_, rc_, _partOffsets, _page);
        }
        if (resType_.getResult().trim().isEmpty()) {
            return "";
        }
        if (!_page.getCurrentBadIndexes().isEmpty()) {
            return "";
        }
        if (!AnaPartTypeUtil.processAnalyzeConstraints(resType_, varsCt_, _exact, _partOffsets, _page)) {
            return "";
        }
        return resType_.getResult();
    }
    public static String resolveCorrectType(int _loc, String _in, boolean _exact, AnalyzedPageEl _page) {
        int rc_ = _page.getLocalizer().getCurrentLocationIndex() + _loc;
        String tr_ = _in.trim();
        if (tr_.isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(_page.getAnalysisMessages().getEmptyType());
            _page.getLocalizer().addError(un_);
            CustList<PartOffset> partOffsets_ = _page.getCurrentParts();
            partOffsets_.clear();
            String err_ = un_.getBuiltError();
            String pref_ = "<a title=\""+err_+"\" class=\"e\">";
            partOffsets_.add(new PartOffset(pref_,rc_));
            partOffsets_.add(new PartOffset("</a>",rc_+1));
            return _page.getAliasObject();
        }
        String void_ = _page.getAliasVoid();
        if (StringUtil.quickEq(tr_, void_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(_page.getAnalysisMessages().getVoidType(),
                    void_);
            _page.getLocalizer().addError(un_);
            CustList<PartOffset> partOffsets_ = _page.getCurrentParts();
            partOffsets_.clear();
            String err_ = un_.getBuiltError();
            String pref_ = "<a title=\""+err_+"\" class=\"e\">";
            partOffsets_.add(new PartOffset(pref_,rc_));
            partOffsets_.add(new PartOffset("</a>",rc_+void_.length()));
            return _page.getAliasObject();
        }
        AccessedBlock r_ = _page.getCurrentGlobalBlock().getCurrentGlobalBlock();
        StringMap<StringList> varsCt_ = _page.getCurrentConstraints().getCurrentConstraints();
        _page.getBuildingConstraints().buildCurrentConstraintsFull();
        AccessedBlock a_ = _page.getCurrentGlobalBlock().getCurrentGlobalBlock(r_);
        CustList<PartOffset> partOffsets_ = _page.getCurrentParts();
        String gl_ = _page.getGlobalClass();
        AnaResultPartType resType_;
        _page.getCurrentBadIndexes().clear();
        if (_exact) {
            resType_ = AnaPartTypeUtil.processAnalyze(tr_, false,gl_, a_,r_, rc_,partOffsets_, _page);
        } else {
            resType_ = AnaPartTypeUtil.processAnalyzeLine(tr_,false, gl_, a_,r_, rc_,partOffsets_, _page);
        }
        for (InaccessibleType i: _page.getCurrentBadIndexes()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            un_.setIndexFile(rc_+i.getIndex());
            //part len
            un_.buildError(_page.getAnalysisMessages().getInaccessibleType(),
                    i.getType(),gl_);
            _page.getLocalizer().addError(un_);
        }
        return checkResType(_in, _exact, rc_, varsCt_, resType_,partOffsets_, _page);
    }
    public static String resolveCorrectTypeAccessible(int _loc, String _in, AnalyzedPageEl _page) {
        int rc_ = _page.getLocalizer().getCurrentLocationIndex() + _loc;
        String void_ = _page.getAliasVoid();
        String tr_ = _in.trim();
        if (tr_.isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(_page.getAnalysisMessages().getEmptyType());
            _page.getLocalizer().addError(un_);
            CustList<PartOffset> partOffsets_ = _page.getCurrentParts();
            partOffsets_.clear();
            String err_ = un_.getBuiltError();
            String pref_ = "<a title=\""+err_+"\" class=\"e\">";
            partOffsets_.add(new PartOffset(pref_,rc_));
            partOffsets_.add(new PartOffset("</a>",rc_+1));
            return _page.getAliasObject();
        }
        if (StringUtil.quickEq(tr_, void_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(_page.getAnalysisMessages().getVoidType(),
                    void_);
            _page.getLocalizer().addError(un_);
            CustList<PartOffset> partOffsets_ = _page.getCurrentParts();
            partOffsets_.clear();
            String err_ = un_.getBuiltError();
            String pref_ = "<a title=\""+err_+"\" class=\"e\">";
            partOffsets_.add(new PartOffset(pref_,rc_));
            partOffsets_.add(new PartOffset("</a>",rc_+void_.length()));
            return _page.getAliasObject();
        }
        AccessedBlock r_ = _page.getCurrentGlobalBlock().getCurrentGlobalBlock();
        StringMap<StringList> varsCt_ = _page.getCurrentConstraints().getCurrentConstraints();
        _page.getBuildingConstraints().buildCurrentConstraintsFull();
        AccessedBlock a_ = _page.getCurrentGlobalBlock().getCurrentGlobalBlock(r_);
        CustList<PartOffset> partOffsets_ = _page.getCurrentParts();
        String gl_ = _page.getGlobalClass();
        AnaResultPartType resType_;
        _page.getCurrentBadIndexes().clear();
        resType_ = AnaPartTypeUtil.processAccAnalyze(tr_, false,gl_, a_,r_, rc_,partOffsets_, _page);
        return checkResType(_in, true, rc_, varsCt_, resType_,partOffsets_, _page);
    }
    private static String checkResType(String _in, boolean _exact, int _rc, StringMap<StringList> _varsCt, AnaResultPartType _resType, CustList<PartOffset> _parts, AnalyzedPageEl _page) {
        if (_resType.getResult().trim().isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            un_.setIndexFile(_rc);
            //_in len
            un_.buildError(_page.getAnalysisMessages().getUnknownType(),
                    _in);
            _page.getLocalizer().addError(un_);
            return _page.getAliasObject();
        }
        if (!AnaPartTypeUtil.processAnalyzeConstraints(_resType, _varsCt, _exact,_parts, _page)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            un_.setIndexFile(_rc);
            //_in len
            un_.buildError(_page.getAnalysisMessages().getBadParamerizedType(),
                    _in);
            _page.getLocalizer().addError(un_);
            return _page.getAliasObject();
        }
        return _resType.getResult();
    }

    public static String resolveAccessibleIdType(int _loc, String _in, AnalyzedPageEl _page) {
        int rc_ = _page.getLocalizer().getCurrentLocationIndex();
        String tr_ = _in.trim();
        String void_ = _page.getAliasVoid();
        AccessedBlock r_ = _page.getCurrentGlobalBlock().getCurrentGlobalBlock();
        StringList inners_ = getParts(_in, _page);
        String firstFull_ = inners_.first();
        int firstOff_ = StringUtil.getFirstPrintableCharIndex(firstFull_);
        String base_ = firstFull_.trim();
        if (base_.isEmpty()) {
            firstOff_ = 0;
        }
        String res_ = StringExpUtil.removeDottedSpaces(base_);
        if (_page.getStandardsTypes().contains(res_)) {
            return res_;
        }
        CustList<PartOffset> partOffsets_ = _page.getCurrentParts();
        partOffsets_.clear();
        if (StringUtil.quickEq(tr_, void_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(_page.getAnalysisMessages().getVoidType(),
                    void_);
            _page.getLocalizer().addError(un_);
            partOffsets_.clear();
            String err_ = un_.getBuiltError();
            String pref_ = "<a title=\""+err_+"\" class=\"e\">";
            partOffsets_.add(new PartOffset(pref_,rc_+firstOff_+_loc));
            partOffsets_.add(new PartOffset("</a>",rc_+firstOff_+_loc+void_.length()));
            return "";
        }
        if (tr_.isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(_page.getAnalysisMessages().getEmptyType());
            _page.getLocalizer().addError(un_);
            String err_ = un_.getBuiltError();
            String pref_ = "<a title=\""+err_+"\" class=\"e\">";
            partOffsets_.add(new PartOffset(pref_,rc_+firstOff_+_loc));
            partOffsets_.add(new PartOffset("</a>",rc_+firstOff_+_loc+1));
            return "";
        }
        RootBlock b_ = _page.getAnaClassBody(res_);
        if (b_ == null) {
            MappingLocalType resolved_ = _page.getMappingLocal().getVal(base_);
            if (resolved_ != null) {
                ContextUtil.appendParts(firstOff_+_loc,firstOff_+_loc + base_.length(),resolved_.getFullName(),partOffsets_, _page);
                res_ = resolved_.getFullName();
            } else {
                String id_ = lookupImportType(base_,r_, new AlwaysReadyTypes(), _page);
                if (id_.isEmpty()) {
                    FoundErrorInterpret undef_;
                    undef_ = new FoundErrorInterpret();
                    undef_.setFileName(_page.getLocalizer().getCurrentFileName());
                    undef_.setIndexFile(rc_);
                    //_in len
                    undef_.buildError(_page.getAnalysisMessages().getUnknownType(),
                            _in);
                    _page.getLocalizer().addError(undef_);
                    String err_ = undef_.getBuiltError();
                    String pref_ = "<a title=\""+err_+"\" class=\"e\">";
                    partOffsets_.add(new PartOffset(pref_,rc_+firstOff_+_loc));
                    partOffsets_.add(new PartOffset("</a>",rc_+firstOff_+_loc + base_.length()));
                    return "";
                }
                ContextUtil.appendParts(firstOff_+_loc,firstOff_+_loc + base_.length(),id_,partOffsets_, _page);
                res_ = id_;
            }
        } else {
            ContextUtil.appendParts(firstOff_+_loc,firstOff_+_loc + res_.length(),res_,partOffsets_, _page);
        }
        int offset_ = _loc;
        offset_ += inners_.first().length();
        int size_ = inners_.size();
        for (int i = 2; i < size_; i += 2) {
            String i_ = inners_.get(i);
            String resId_;
            int delta_ = 1;
            if (StringUtil.quickEq(".",inners_.get(i-1))) {
                resId_ = StringUtil.concat(res_,"..",i_.trim());
            } else {
                delta_ = 2;
                resId_ = StringUtil.concat(res_,"-",i_.trim());
            }
            RootBlock inner_ = _page.getAnaClassBody(resId_);
            if (inner_ == null) {
                //ERROR
                FoundErrorInterpret undef_;
                undef_ = new FoundErrorInterpret();
                undef_.setFileName(_page.getLocalizer().getCurrentFileName());
                undef_.setIndexFile(rc_);
                //_in len
                undef_.buildError(_page.getAnalysisMessages().getUnknownType(),
                        _in);
                _page.getLocalizer().addError(undef_);
                String err_ = undef_.getBuiltError();
                String pref_ = "<a title=\""+err_+"\" class=\"e\">";
                partOffsets_.add(new PartOffset(pref_,rc_+offset_+delta_));
                partOffsets_.add(new PartOffset("</a>",rc_+offset_+delta_ + i_.trim().length()));
                return "";
            }
            ContextUtil.appendParts(offset_+delta_,offset_+delta_ + i_.trim().length(),resId_,partOffsets_, _page);
            res_ = resId_;
            offset_ += i_.length() + delta_;
        }
        return res_;
    }
    public static String resolveCorrectType(String _in, AnalyzedPageEl _page) {
        return resolveCorrectType(0, _in, _page);
    }
    public static String resolveCorrectType(int _loc, String _in, AnalyzedPageEl _page) {
        return resolveCorrectType(_loc, _in, true, _page);
    }

    public static String lookupImportType(String _type, AccessedBlock _rooted, ReadyTypes _ready, AnalyzedPageEl _page) {
        String prefixedType_;
        prefixedType_ = getRealSinglePrefixedMemberType(_type, _rooted,_ready, _page);
        return prefixedType_;
    }
    private static String getRealSinglePrefixedMemberType(String _type, AccessedBlock _rooted, ReadyTypes _ready, AnalyzedPageEl _page) {
        String look_ = _type.trim();
        StringList types_ = new StringList();
        CustList<StringList> imports_ = new CustList<StringList>();
        AccessedBlock typeImp_ = _page.getCurrentGlobalBlock().getCurrentGlobalBlockImporting();
        fetchImports(typeImp_, imports_);
        for (StringList s: imports_) {
            for (String i: s) {
                String tr_ = i.trim();
                if (!tr_.contains(".")) {
                    continue;
                }
                String end_ = StringExpUtil.removeDottedSpaces(tr_.substring(tr_.lastIndexOf('.')+1));
                if (!StringUtil.quickEq(end_, look_)) {
                    continue;
                }
                if (stopLookup(types_,tr_,look_,_ready, _page)) {
                    return "";
                }
            }
        }
        if (types_.onlyOneElt()) {
            return types_.first();
        }
        if (!types_.isEmpty()) {
            return "";
        }
        if (_rooted instanceof RootBlock) {
            RootBlock r_ = (RootBlock) _rooted;
            String type_ = StringExpUtil.removeDottedSpaces(StringUtil.concat(r_.getPackageName(),".",_type));
            if (_page.getAnaClassBody(type_) != null) {
                return type_;
            }
        }
        for (StringList s: imports_) {
            for (String i: s) {
                String tr_ = i.trim();
                if (!tr_.contains(".")) {
                    continue;
                }
                String end_ = StringExpUtil.removeDottedSpaces(tr_.substring(tr_.lastIndexOf('.')+1));
                if (!StringUtil.quickEq(end_, "*")) {
                    continue;
                }
                if (stopLookup(types_,tr_,look_,_ready, _page)) {
                    return "";
                }
            }
        }
        if (types_.onlyOneElt()) {
            return types_.first();
        }
        if (!types_.isEmpty()) {
            return "";
        }
        String defPkg_ = _page.getDefaultPkg();
        String type_ = StringExpUtil.removeDottedSpaces(StringUtil.concat(defPkg_,".",_type));
        if (_page.getAnaGeneType(type_) != null) {
            return type_;
        }
        return "";
    }

    private static boolean stopLookup(StringList _types, String _import, String _look, ReadyTypes _ready, AnalyzedPageEl _page) {
        String beginImp_ = StringExpUtil.removeDottedSpaces(_import.substring(0, _import.lastIndexOf('.')+1));
        String keyWordStatic_ = _page.getKeyWords().getKeyWordStatic();
        boolean stQualifier_ = false;
        if (StringExpUtil.startsWithKeyWord(beginImp_, keyWordStatic_)) {
            beginImp_ = beginImp_.substring(keyWordStatic_.length()).trim();
            stQualifier_ = true;
        }
        String typeInner_ = StringUtil.concat(beginImp_, _look);
        StringList allInnerTypes_ = AnaTemplates.getAllInnerTypes(typeInner_, _page);
        String owner_ = allInnerTypes_.first();
        AnaGeneType cl_ = _page.getAnaGeneType(owner_);
        String res_ = owner_;
        boolean addImport_ = true;
        if (cl_ != null) {
            int size_ = allInnerTypes_.size();
            for (int i = 2; i < size_; i+=2) {
                String i_ = allInnerTypes_.get(i).trim();
                if (!_ready.isReady(res_)) {
                    return true;
                }
                StringList builtInners_ = AnaTypeUtil.getInners(res_, allInnerTypes_.get(i-1), i_, stQualifier_, _page);
                if (builtInners_.onlyOneElt()) {
                    res_ = builtInners_.first();
                    continue;
                }
                if (!builtInners_.isEmpty()) {
                    return true;
                }
                addImport_ = false;
                break;
            }
        } else {
            addImport_ = false;
        }
        if (addImport_) {
            _types.add(res_);
        }
        return false;
    }

    public static CustList<CustList<ImportedMethod>> lookupImportStaticMethods(String _glClass, String _method, AnalyzedPageEl _page) {
        CustList<CustList<ImportedMethod>> methods_ = new CustList<CustList<ImportedMethod>>();
        AccessedBlock type_ = _page.getCurrentGlobalBlock().getCurrentGlobalBlockImporting();
        CustList<StringList> imports_ = new CustList<StringList>();
        fetchImports(type_, imports_);
        String keyWordStatic_ = _page.getKeyWords().getKeyWordStatic();
        for (StringList t: imports_) {
            CustList<ImportedMethod> m_ = new CustList<ImportedMethod>();
            for (String i: t) {
                String tr_ = i.trim();
                if (!tr_.contains(".")) {
                    continue;
                }
                if (!StringExpUtil.startsWithKeyWord(tr_, keyWordStatic_)) {
                    continue;
                }
                String st_ = tr_.substring(keyWordStatic_.length()).trim();
                String typeLoc_ = StringExpUtil.removeDottedSpaces(st_.substring(0,st_.lastIndexOf('.')));
                String foundCandidate_ = resolveCandidate(typeLoc_, _page);
                AnaGeneType root_ = _page.getAnaGeneType(foundCandidate_);
                if (root_ == null) {
                    continue;
                }
                String end_ = StringExpUtil.removeDottedSpaces(st_.substring(st_.lastIndexOf('.')+1));
                if (!StringUtil.quickEq(end_, _method.trim())) {
                    continue;
                }
                StringList typesLoc_ = new StringList(foundCandidate_);
                typesLoc_.addAllElts(root_.getAllSuperTypes());
                fetchImportStaticMethods(_glClass, _method, m_, foundCandidate_, typesLoc_, _page);
            }
            methods_.add(m_);
        }
        for (StringList t: imports_) {
            CustList<ImportedMethod> m_ = new CustList<ImportedMethod>();
            for (String i: t) {
                String tr_ = i.trim();
                if (!tr_.contains(".")) {
                    continue;
                }
                if (!StringExpUtil.startsWithKeyWord(tr_, keyWordStatic_)) {
                    continue;
                }
                String st_ = tr_.substring(keyWordStatic_.length()).trim();
                String end_ = StringExpUtil.removeDottedSpaces(st_.substring(st_.lastIndexOf('.')+1));
                if (!StringUtil.quickEq(end_, "*")) {
                    continue;
                }
                String typeLoc_ = StringExpUtil.removeDottedSpaces(st_.substring(0,st_.lastIndexOf('.')));
                String foundCandidate_ = resolveCandidate(typeLoc_, _page);
                AnaGeneType root_ = _page.getAnaGeneType(foundCandidate_);
                if (root_ == null) {
                    continue;
                }
                StringList typesLoc_ = new StringList(foundCandidate_);
                typesLoc_.addAllElts(root_.getAllSuperTypes());
                fetchImportStaticMethods(_glClass, _method, m_, foundCandidate_, typesLoc_, _page);
            }
            methods_.add(m_);
        }
        return methods_;
    }

    private static void fetchImportStaticMethods(String _glClass, String _method, CustList<ImportedMethod> _methods, String _typeLoc, StringList _typesLoc, AnalyzedPageEl _page) {
        for (String s: _typesLoc) {
            AnaGeneType super_ = _page.getAnaGeneType(s);
            if (super_ instanceof RootBlock) {
                RootBlock t_ = (RootBlock) super_;
                for (OverridableBlock e: t_.getOverridableBlocks()) {
                    if (!e.isStaticMethod()) {
                        continue;
                    }
                    if (!StringUtil.quickEq(_method.trim(), e.getId().getName())) {
                        continue;
                    }
                    String pkg_ = super_.getPackageName();
                    Accessed a_ = new Accessed(e.getAccess(), pkg_, t_);
                    if (!ContextUtil.canAccess(_typeLoc, a_, _page)) {
                        continue;
                    }
                    if (!ContextUtil.canAccess(_glClass, a_, _page)) {
                        continue;
                    }
                    ClassMethodId clMet_ = new ClassMethodId(s, e.getId());
                    ImportedMethod value_ = new ImportedMethod(e.getImportedReturnType(), clMet_);
                    value_.setFileName(e.getFile().getFileName());
                    value_.setMemberNumber(e.getNameNumber());
                    value_.setCustMethod(e);
                    value_.setRootNumber(t_.getNumberAll());
                    addImportMethod(_methods, value_);
                }
            }
            if (super_ instanceof StandardType) {
                for (StandardMethod e: ((StandardType) super_).getMethods()) {
                    if (!e.isStaticMethod()) {
                        continue;
                    }
                    if (!StringUtil.quickEq(_method.trim(), e.getId().getName())) {
                        continue;
                    }
                    ClassMethodId clMet_ = new ClassMethodId(s, e.getId());
                    ImportedMethod value_ = new ImportedMethod(e.getImportedReturnType(), clMet_);
                    value_.setStandardMethod(e);
                    addImportMethod(_methods, value_);
                }
            }
        }
    }
    private static void addImportMethod(CustList<ImportedMethod> _methods, ImportedMethod _value) {
        ClassMethodId id_ = _value.getId();
        for (ImportedMethod e: _methods) {
            if (e.getId().eq(id_)) {
                return;
            }
        }
        _methods.add(_value);
    }

    public static StringMap<ImportedField> lookupImportStaticFields(String _glClass, String _method, AnalyzedPageEl _page) {
        StringMap<ImportedField> methods_ = new StringMap<ImportedField>();
        int import_ = 1;
        AccessedBlock type_ = _page.getCurrentGlobalBlock().getCurrentGlobalBlockImporting();
        CustList<StringList> imports_ = new CustList<StringList>();
        fetchImports(type_, imports_);
        String keyWordStatic_ = _page.getKeyWords().getKeyWordStatic();
        for (StringList t: imports_) {
            for (String i: t) {
                String tr_ = i.trim();
                if (!tr_.contains(".")) {
                    continue;
                }
                if (!StringExpUtil.startsWithKeyWord(tr_, keyWordStatic_)) {
                    continue;
                }
                String st_ = tr_.substring(keyWordStatic_.length()).trim();
                String typeLoc_ = StringExpUtil.removeDottedSpaces(st_.substring(0,st_.lastIndexOf('.')));
                String foundCandidate_ = resolveCandidate(typeLoc_, _page);
                AnaGeneType root_ = _page.getAnaGeneType(foundCandidate_);
                if (root_ == null) {
                    continue;
                }
                String end_ = StringExpUtil.removeDottedSpaces(st_.substring(st_.lastIndexOf('.')+1));
                if (!StringUtil.quickEq(end_, _method.trim())) {
                    continue;
                }
                StringList typesLoc_ = new StringList(foundCandidate_);
                typesLoc_.addAllElts(root_.getAllSuperTypes());
                fetchImportStaticFieldsTmp(_glClass, _method, methods_, import_, foundCandidate_, typesLoc_, _page);
            }
            import_++;
        }
        for (StringList t: imports_) {
            for (String i: t) {
                String tr_ = i.trim();
                if (!tr_.contains(".")) {
                    continue;
                }
                if (!StringExpUtil.startsWithKeyWord(tr_, keyWordStatic_)) {
                    continue;
                }
                String st_ = tr_.substring(keyWordStatic_.length()).trim();
                String end_ = StringExpUtil.removeDottedSpaces(st_.substring(st_.lastIndexOf('.')+1));
                if (!StringUtil.quickEq(end_, "*")) {
                    continue;
                }
                String typeLoc_ = StringExpUtil.removeDottedSpaces(st_.substring(0,st_.lastIndexOf('.')));
                String foundCandidate_ = resolveCandidate(typeLoc_, _page);
                AnaGeneType root_ = _page.getAnaGeneType(foundCandidate_);
                if (root_ == null) {
                    continue;
                }
                StringList typesLoc_ = new StringList(foundCandidate_);
                typesLoc_.addAllElts(root_.getAllSuperTypes());
                fetchImportStaticFieldsTmp(_glClass, _method, methods_, import_, foundCandidate_, typesLoc_, _page);
            }
            import_++;
        }
        return methods_;
    }

    public static String resolveCandidate(String _c, AnalyzedPageEl _page) {
        StringList allInnerTypes_ = getParts(_c, _page);
        String owner_ = allInnerTypes_.first();
        AnaGeneType cl_ = _page.getAnaGeneType(owner_);
        String res_ = owner_;
        if (cl_ != null) {
            int size_ = allInnerTypes_.size();
            for (int i = 2; i < size_; i+=2) {
                String i_ = allInnerTypes_.get(i).trim();
                String part_ = allInnerTypes_.get(i-1);
                StringList builtInners_ = AnaTypeUtil.getInners(res_,part_,i_,false, _page);
                if (builtInners_.onlyOneElt()) {
                    res_ = builtInners_.first();
                    continue;
                }
                break;
            }
        }
        return res_;
    }

    public static StringList getParts(String _c, AnalyzedPageEl _page) {
        StringList allInnerTypes_;
        allInnerTypes_ = AnaTemplates.getAllInnerTypes(_c, _page);
        return allInnerTypes_;
    }

    private static void fetchImportStaticFieldsTmp(String _glClass, String _method, StringMap<ImportedField> _methods, int _import, String _typeLoc, StringList _typesLoc, AnalyzedPageEl _page) {
        fetchImportStaticFields(_glClass,_method,_methods,_import,_typeLoc,_typesLoc, _page);
    }

    public static void fetchImportStaticFields(String _glClass, String _method, StringMap<ImportedField> _methods, int _import, String _typeLoc, StringList _typesLoc, AnalyzedPageEl _page) {
        for (String s: _typesLoc) {
            AnaGeneType super_ = _page.getAnaGeneType(s);
            if (super_ instanceof StandardType) {
                for (StandardField m: ((StandardType)super_).getFields()) {
                    int ind_ = notMatch(_method, m);
                    if (ind_ < 0) {
                        continue;
                    }
                    Accessed a_ = new Accessed(AccessEnum.PUBLIC,"", null);
                    addImport(_methods,s, new ImportedField(_import,a_,m.getImportedClassName(),m.isFinalField(),-1));
                }
            }
            if (super_ instanceof RootBlock){
                RootBlock cust_ = (RootBlock) super_;
                String pkg_ = cust_.getPackageName();
                for (InfoBlock e: ClassesUtil.getFieldBlocks(cust_)) {
                    int ind_ = notMatch(_method, e);
                    if (ind_ < 0) {
                        continue;
                    }
                    int v_ = -1;
                    if (e instanceof FieldBlock) {
                        v_ = ((FieldBlock)e).getValuesOffset().get(ind_);
                    }
                    if (e instanceof InnerTypeOrElement) {
                        v_ = e.getFieldNameOffset();
                    }
                    Accessed a_ = new Accessed(e.getAccess(),pkg_, cust_);
                    if (!ContextUtil.canAccess(_typeLoc, a_, _page)) {
                        continue;
                    }
                    if (!ContextUtil.canAccess(_glClass, a_, _page)) {
                        continue;
                    }
                    ImportedField value_ = new ImportedField(_import, a_, e.getImportedClassName(), e.isFinalField(), v_);
                    value_.setFileName(e.getFile().getFileName());
                    value_.setMemberNumber(e.getFieldNumber());
                    value_.setRootNumber(cust_.getNumberAll());
                    addImport(_methods,s, value_);
                }
            }
        }
    }
    private static void addImport(StringMap<ImportedField> _methods, String _class, ImportedField _value) {
        for (EntryCust<String, ImportedField> e: _methods.entryList()) {
            if (StringUtil.quickEq(e.getKey(), _class)) {
                return;
            }
        }
        _methods.addEntry(_class,_value);
    }
    private static int notMatch(String _method, GeneField _field) {
        if (!_field.isStaticField()) {
            return -1;
        }
        return StringUtil.indexOf(_field.getFieldName(), _method.trim());
    }
    private static void fetchImports(AccessedBlock _rooted, CustList<StringList> _imports) {
        if (_rooted instanceof RootBlock) {
            RootBlock r_ = (RootBlock) _rooted;
            _imports.add(r_.getImports());
            for (RootBlock r: r_.getAllParentTypes()) {
                addImports(r,_imports);
            }
        } else {
            addImports(_rooted, _imports);
        }
        if (_rooted != null) {
            _imports.add(_rooted.getFileImports());
        }
    }

    private static void addImports(AccessedBlock _rooted, CustList<StringList> _imports) {
        if (_rooted != null) {
            _imports.add(_rooted.getImports());
        }
    }
}
