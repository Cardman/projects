package code.expressionlanguage.analyze.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.ImportedField;
import code.expressionlanguage.analyze.ImportedMethod;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.*;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.analyze.util.TypeVar;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardType;
import code.util.*;

public final class ResolvingImportTypes {
    private ResolvingImportTypes() {

    }
    public static String resolveAccessibleIdTypeWithoutError(ContextEl _analyzable, int _loc, String _in) {
        String void_ = _analyzable.getStandards().getAliasVoid();
        if (StringList.quickEq(_in.trim(), void_)) {
            return "";
        }
        AccessedBlock r_ = _analyzable.getAnalyzing().getCurrentGlobalBlock().getCurrentGlobalBlock();
        int rc_ = _analyzable.getAnalyzing().getLocalizer().getCurrentLocationIndex()+_loc;
        AccessedBlock a_ = _analyzable.getAnalyzing().getCurrentGlobalBlock().getCurrentGlobalBlock(r_);
        CustList<PartOffset> offs_ = _analyzable.getAnalyzing().getCurrentParts();
        return AnaPartTypeUtil.processAnalyzeLineWithoutErr(_in, _analyzable,a_,r_, rc_, offs_).getResult();
    }
    public static String resolveCorrectAccessibleType(ContextEl _analyzable, int _loc,String _in, String _fromType) {
        int rc_ = _analyzable.getAnalyzing().getLocalizer().getCurrentLocationIndex()+_loc;
        String tr_ = _in.trim();
        if (tr_.isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_analyzable.getAnalyzing().getLocalizer().getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(_analyzable.getAnalysisMessages().getEmptyType());
            _analyzable.getAnalyzing().getLocalizer().addError(un_);
            CustList<PartOffset> partOffsets_ = _analyzable.getAnalyzing().getCurrentParts();
            partOffsets_.clear();
            String err_ = un_.getBuiltError();
            String pref_ = "<a title=\""+err_+"\" class=\"e\">";
            partOffsets_.add(new PartOffset(pref_,rc_));
            partOffsets_.add(new PartOffset("</a>",rc_+1));
            return _analyzable.getStandards().getAliasObject();
        }
        String void_ = _analyzable.getStandards().getAliasVoid();
        if (StringList.quickEq(tr_, void_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_analyzable.getAnalyzing().getLocalizer().getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(_analyzable.getAnalysisMessages().getVoidType(),
                    void_);
            _analyzable.getAnalyzing().getLocalizer().addError(un_);
            CustList<PartOffset> partOffsets_ = _analyzable.getAnalyzing().getCurrentParts();
            partOffsets_.clear();
            String err_ = un_.getBuiltError();
            String pref_ = "<a title=\""+err_+"\" class=\"e\">";
            partOffsets_.add(new PartOffset(pref_,rc_));
            partOffsets_.add(new PartOffset("</a>",rc_+void_.length()));
            return _analyzable.getStandards().getAliasObject();
        }
        AccessedBlock r_ = _analyzable.getAnalyzing().getCurrentGlobalBlock().getCurrentGlobalBlock();
        StringMap<StringList> vars_ = new StringMap<StringList>();
        String idFromType_ = StringExpUtil.getIdFromAllTypes(_fromType);
        AnaGeneType from_ = _analyzable.getAnalyzing().getAnaGeneType(_analyzable,idFromType_);
        String ref_ = "";
        if (ContextUtil.isFromCustFile(from_)) {
            ref_ = ((Block)from_).getFile().getRenderFileName();
        }
        _analyzable.getAnalyzing().getAvailableVariables().clear();
        if (from_ instanceof RootBlock) {
            for (TypeVar t: ((RootBlock)from_).getParamTypesMapValues()) {
                _analyzable.getAnalyzing().getAvailableVariables().addEntry(t.getName(),t.getOffset());
                vars_.addEntry(t.getName(), t.getConstraints());
            }
        }
        CustList<PartOffset> partOffsets_ = _analyzable.getAnalyzing().getCurrentParts();
        partOffsets_.clear();
        AnaResultPartType resType_ = AnaPartTypeUtil.processAnalyzeAccessibleId(_in, _analyzable, r_, ref_,rc_,partOffsets_);
        if (resType_.getResult().trim().isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_analyzable.getAnalyzing().getLocalizer().getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(_analyzable.getAnalysisMessages().getUnknownType(),
                    _in);
            _analyzable.getAnalyzing().getLocalizer().addError(un_);
            return _analyzable.getStandards().getAliasObject();
        }
        if (!AnaPartTypeUtil.processAnalyzeConstraints(resType_, vars_, _analyzable, true,partOffsets_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_analyzable.getAnalyzing().getLocalizer().getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(_analyzable.getAnalysisMessages().getBadParamerizedType(),
                    _in);
            _analyzable.getAnalyzing().getLocalizer().addError(un_);
            return _analyzable.getStandards().getAliasObject();
        }
        return resType_.getResult();
    }
    public static String resolveCorrectTypeWithoutErrors(ContextEl _analyzable, int _loc, String _in, boolean _exact, CustList<PartOffset> _partOffsets) {
        String void_ = _analyzable.getStandards().getAliasVoid();
        if (StringList.quickEq(_in.trim(), void_)) {
            return "";
        }
        AccessedBlock r_ = _analyzable.getAnalyzing().getCurrentGlobalBlock().getCurrentGlobalBlock();
        StringMap<StringList> varsCt_ = _analyzable.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
        _analyzable.getAnalyzing().getBuildingConstraints().buildCurrentConstraintsFull();
        AccessedBlock a_ = _analyzable.getAnalyzing().getCurrentGlobalBlock().getCurrentGlobalBlock(r_);
        String gl_ = _analyzable.getAnalyzing().getGlobalClass();
        int rc_ = _analyzable.getAnalyzing().getLocalizer().getCurrentLocationIndex() + _loc;
        _analyzable.getAnalyzing().getCurrentBadIndexes().clear();
        AnaResultPartType resType_;
        if (_exact) {
            resType_ = AnaPartTypeUtil.processAnalyze(_in, false,gl_, _analyzable, a_,r_, rc_, _partOffsets);
        } else {
            resType_ = AnaPartTypeUtil.processAnalyzeLine(_in, false, gl_, _analyzable, a_,r_, rc_, _partOffsets);
        }
        if (resType_.getResult().trim().isEmpty()) {
            return "";
        }
        if (!_analyzable.getAnalyzing().getCurrentBadIndexes().isEmpty()) {
            return "";
        }
        if (!AnaPartTypeUtil.processAnalyzeConstraints(resType_, varsCt_, _analyzable, _exact, _partOffsets)) {
            return "";
        }
        return resType_.getResult();
    }
    public static String resolveCorrectType(ContextEl _analyzable,int _loc,String _in, boolean _exact) {
        int rc_ = _analyzable.getAnalyzing().getLocalizer().getCurrentLocationIndex() + _loc;
        String tr_ = _in.trim();
        if (tr_.isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_analyzable.getAnalyzing().getLocalizer().getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(_analyzable.getAnalysisMessages().getEmptyType());
            _analyzable.getAnalyzing().getLocalizer().addError(un_);
            CustList<PartOffset> partOffsets_ = _analyzable.getAnalyzing().getCurrentParts();
            partOffsets_.clear();
            String err_ = un_.getBuiltError();
            String pref_ = "<a title=\""+err_+"\" class=\"e\">";
            partOffsets_.add(new PartOffset(pref_,rc_));
            partOffsets_.add(new PartOffset("</a>",rc_+1));
            return _analyzable.getStandards().getAliasObject();
        }
        String void_ = _analyzable.getStandards().getAliasVoid();
        if (StringList.quickEq(tr_, void_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_analyzable.getAnalyzing().getLocalizer().getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(_analyzable.getAnalysisMessages().getVoidType(),
                    void_);
            _analyzable.getAnalyzing().getLocalizer().addError(un_);
            CustList<PartOffset> partOffsets_ = _analyzable.getAnalyzing().getCurrentParts();
            partOffsets_.clear();
            String err_ = un_.getBuiltError();
            String pref_ = "<a title=\""+err_+"\" class=\"e\">";
            partOffsets_.add(new PartOffset(pref_,rc_));
            partOffsets_.add(new PartOffset("</a>",rc_+void_.length()));
            return _analyzable.getStandards().getAliasObject();
        }
        AccessedBlock r_ = _analyzable.getAnalyzing().getCurrentGlobalBlock().getCurrentGlobalBlock();
        StringMap<StringList> varsCt_ = _analyzable.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
        _analyzable.getAnalyzing().getBuildingConstraints().buildCurrentConstraintsFull();
        AccessedBlock a_ = _analyzable.getAnalyzing().getCurrentGlobalBlock().getCurrentGlobalBlock(r_);
        CustList<PartOffset> partOffsets_ = _analyzable.getAnalyzing().getCurrentParts();
        String gl_ = _analyzable.getAnalyzing().getGlobalClass();
        AnaResultPartType resType_;
        _analyzable.getAnalyzing().getCurrentBadIndexes().clear();
        if (_exact) {
            resType_ = AnaPartTypeUtil.processAnalyze(tr_, false,gl_, _analyzable, a_,r_, rc_,partOffsets_);
        } else {
            resType_ = AnaPartTypeUtil.processAnalyzeLine(tr_,false, gl_, _analyzable,a_,r_, rc_,partOffsets_);
        }
        for (InaccessibleType i: _analyzable.getAnalyzing().getCurrentBadIndexes()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_analyzable.getAnalyzing().getLocalizer().getCurrentFileName());
            un_.setIndexFile(rc_+i.getIndex());
            //part len
            un_.buildError(_analyzable.getAnalysisMessages().getInaccessibleType(),
                    i.getType(),gl_);
            _analyzable.getAnalyzing().getLocalizer().addError(un_);
        }
        return checkResType(_analyzable, _in, _exact, rc_, varsCt_, resType_,partOffsets_);
    }
    public static String resolveCorrectTypeAccessible(ContextEl _analyzable,int _loc,String _in) {
        int rc_ = _analyzable.getAnalyzing().getLocalizer().getCurrentLocationIndex() + _loc;
        String void_ = _analyzable.getStandards().getAliasVoid();
        String tr_ = _in.trim();
        if (tr_.isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_analyzable.getAnalyzing().getLocalizer().getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(_analyzable.getAnalysisMessages().getEmptyType());
            _analyzable.getAnalyzing().getLocalizer().addError(un_);
            CustList<PartOffset> partOffsets_ = _analyzable.getAnalyzing().getCurrentParts();
            partOffsets_.clear();
            String err_ = un_.getBuiltError();
            String pref_ = "<a title=\""+err_+"\" class=\"e\">";
            partOffsets_.add(new PartOffset(pref_,rc_));
            partOffsets_.add(new PartOffset("</a>",rc_+1));
            return _analyzable.getStandards().getAliasObject();
        }
        if (StringList.quickEq(tr_, void_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_analyzable.getAnalyzing().getLocalizer().getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(_analyzable.getAnalysisMessages().getVoidType(),
                    void_);
            _analyzable.getAnalyzing().getLocalizer().addError(un_);
            CustList<PartOffset> partOffsets_ = _analyzable.getAnalyzing().getCurrentParts();
            partOffsets_.clear();
            String err_ = un_.getBuiltError();
            String pref_ = "<a title=\""+err_+"\" class=\"e\">";
            partOffsets_.add(new PartOffset(pref_,rc_));
            partOffsets_.add(new PartOffset("</a>",rc_+void_.length()));
            return _analyzable.getStandards().getAliasObject();
        }
        AccessedBlock r_ = _analyzable.getAnalyzing().getCurrentGlobalBlock().getCurrentGlobalBlock();
        StringMap<StringList> varsCt_ = _analyzable.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
        _analyzable.getAnalyzing().getBuildingConstraints().buildCurrentConstraintsFull();
        AccessedBlock a_ = _analyzable.getAnalyzing().getCurrentGlobalBlock().getCurrentGlobalBlock(r_);
        CustList<PartOffset> partOffsets_ = _analyzable.getAnalyzing().getCurrentParts();
        String gl_ = _analyzable.getAnalyzing().getGlobalClass();
        AnaResultPartType resType_;
        _analyzable.getAnalyzing().getCurrentBadIndexes().clear();
        resType_ = AnaPartTypeUtil.processAccAnalyze(tr_, false,gl_, _analyzable, a_,r_, rc_,partOffsets_);
        return checkResType(_analyzable, _in, true, rc_, varsCt_, resType_,partOffsets_);
    }
    private static String checkResType(ContextEl _analyzable, String _in, boolean _exact, int rc_, StringMap<StringList> varsCt_, AnaResultPartType resType_, CustList<PartOffset> _parts) {
        if (resType_.getResult().trim().isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_analyzable.getAnalyzing().getLocalizer().getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(_analyzable.getAnalysisMessages().getUnknownType(),
                    _in);
            _analyzable.getAnalyzing().getLocalizer().addError(un_);
            return _analyzable.getStandards().getAliasObject();
        }
        if (!AnaPartTypeUtil.processAnalyzeConstraints(resType_, varsCt_, _analyzable, _exact,_parts)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_analyzable.getAnalyzing().getLocalizer().getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(_analyzable.getAnalysisMessages().getBadParamerizedType(),
                    _in);
            _analyzable.getAnalyzing().getLocalizer().addError(un_);
            return _analyzable.getStandards().getAliasObject();
        }
        return resType_.getResult();
    }

    public static String resolveAccessibleIdType(ContextEl _analyzable,int _loc,String _in) {
        int rc_ = _analyzable.getAnalyzing().getLocalizer().getCurrentLocationIndex();
        String tr_ = _in.trim();
        String void_ = _analyzable.getStandards().getAliasVoid();
        AccessedBlock r_ = _analyzable.getAnalyzing().getCurrentGlobalBlock().getCurrentGlobalBlock();
        StringList inners_ = getParts(_analyzable,_in);
        String firstFull_ = inners_.first();
        int firstOff_ = StringList.getFirstPrintableCharIndex(firstFull_);
        String base_ = firstFull_.trim();
        if (base_.isEmpty()) {
            firstOff_ = 0;
        }
        String res_ = StringExpUtil.removeDottedSpaces(base_);
        if (_analyzable.getStandards().getStandards().contains(res_)) {
            return res_;
        }
        CustList<PartOffset> partOffsets_ = _analyzable.getAnalyzing().getCurrentParts();
        partOffsets_.clear();
        if (StringList.quickEq(tr_, void_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_analyzable.getAnalyzing().getLocalizer().getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(_analyzable.getAnalysisMessages().getVoidType(),
                    void_);
            _analyzable.getAnalyzing().getLocalizer().addError(un_);
            partOffsets_.clear();
            String err_ = un_.getBuiltError();
            String pref_ = "<a title=\""+err_+"\" class=\"e\">";
            partOffsets_.add(new PartOffset(pref_,rc_+firstOff_+_loc));
            partOffsets_.add(new PartOffset("</a>",rc_+firstOff_+_loc+void_.length()));
            return "";
        }
        RootBlock b_ = _analyzable.getAnalyzing().getAnaClassBody(res_);
        if (b_ == null) {
            if (tr_.isEmpty()) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_analyzable.getAnalyzing().getLocalizer().getCurrentFileName());
                un_.setIndexFile(rc_);
                //_in len
                un_.buildError(_analyzable.getAnalysisMessages().getEmptyType());
                _analyzable.getAnalyzing().getLocalizer().addError(un_);
                String err_ = un_.getBuiltError();
                String pref_ = "<a title=\""+err_+"\" class=\"e\">";
                partOffsets_.add(new PartOffset(pref_,rc_+firstOff_+_loc));
                partOffsets_.add(new PartOffset("</a>",rc_+firstOff_+_loc+1));
                return "";
            }
            String resolved_ = _analyzable.getAnalyzing().getMappingLocal().getVal(base_);
            if (resolved_ != null) {
                ContextUtil.appendParts(_analyzable,firstOff_+_loc,firstOff_+_loc + base_.length(),resolved_,partOffsets_);
                res_ = resolved_;
            } else {
                String id_ = lookupImportType(_analyzable,base_,r_, new AlwaysReadyTypes());
                if (id_.isEmpty()) {
                    FoundErrorInterpret undef_;
                    undef_ = new FoundErrorInterpret();
                    undef_.setFileName(_analyzable.getAnalyzing().getLocalizer().getCurrentFileName());
                    undef_.setIndexFile(rc_);
                    //_in len
                    undef_.buildError(_analyzable.getAnalysisMessages().getUnknownType(),
                            _in);
                    _analyzable.getAnalyzing().getLocalizer().addError(undef_);
                    String err_ = undef_.getBuiltError();
                    String pref_ = "<a title=\""+err_+"\" class=\"e\">";
                    partOffsets_.add(new PartOffset(pref_,rc_+firstOff_+_loc));
                    partOffsets_.add(new PartOffset("</a>",rc_+firstOff_+_loc + base_.length()));
                    return "";
                }
                ContextUtil.appendParts(_analyzable,firstOff_+_loc,firstOff_+_loc + base_.length(),id_,partOffsets_);
                res_ = id_;
            }
        } else {
            ContextUtil.appendParts(_analyzable,firstOff_+_loc,firstOff_+_loc + res_.length(),res_,partOffsets_);
        }
        int offset_ = _loc;
        offset_ += inners_.first().length();
        int size_ = inners_.size();
        for (int i = 2; i < size_; i += 2) {
            String i_ = inners_.get(i);
            String resId_;
            int delta_ = 1;
            if (StringList.quickEq(".",inners_.get(i-1))) {
                resId_ = StringList.concat(res_,"..",i_.trim());
            } else {
                delta_ = 2;
                resId_ = StringList.concat(res_,"-",i_.trim());
            }
            RootBlock inner_ = _analyzable.getAnalyzing().getAnaClassBody(resId_);
            if (inner_ == null) {
                //ERROR
                FoundErrorInterpret undef_;
                undef_ = new FoundErrorInterpret();
                undef_.setFileName(_analyzable.getAnalyzing().getLocalizer().getCurrentFileName());
                undef_.setIndexFile(rc_);
                //_in len
                undef_.buildError(_analyzable.getAnalysisMessages().getUnknownType(),
                        _in);
                _analyzable.getAnalyzing().getLocalizer().addError(undef_);
                String err_ = undef_.getBuiltError();
                String pref_ = "<a title=\""+err_+"\" class=\"e\">";
                partOffsets_.add(new PartOffset(pref_,rc_+offset_+delta_));
                partOffsets_.add(new PartOffset("</a>",rc_+offset_+delta_ + i_.trim().length()));
                return "";
            }
            ContextUtil.appendParts(_analyzable,offset_+delta_,offset_+delta_ + i_.trim().length(),resId_,partOffsets_);
            res_ = resId_;
            offset_ += i_.length() + delta_;
        }
        return res_;
    }
    public static String resolveCorrectType(ContextEl _an,String _in) {
        return resolveCorrectType(_an, 0, _in, true);
    }
    public static String resolveCorrectType(ContextEl _an,int _loc, String _in) {
        return resolveCorrectType(_an, _loc, _in, true);
    }

    public static String lookupImportType(ContextEl _an,String _type, AccessedBlock _rooted, ReadyTypes _ready) {
        String prefixedType_;
        prefixedType_ = getRealSinglePrefixedMemberType(_an,_type, _rooted,_ready);
        return prefixedType_;
    }
    private static String getRealSinglePrefixedMemberType(ContextEl _an,String _type, AccessedBlock _rooted, ReadyTypes _ready) {
        String look_ = _type.trim();
        StringList types_ = new StringList();
        CustList<StringList> imports_ = new CustList<StringList>();
        AccessedBlock typeImp_ = _an.getAnalyzing().getCurrentGlobalBlock().getCurrentGlobalBlockImporting();
        fetchImports(typeImp_, imports_);
        for (StringList s: imports_) {
            for (String i: s) {
                String tr_ = i.trim();
                if (!tr_.contains(".")) {
                    continue;
                }
                String end_ = StringExpUtil.removeDottedSpaces(tr_.substring(tr_.lastIndexOf('.')+1));
                if (!StringList.quickEq(end_, look_)) {
                    continue;
                }
                if (stopLookup(_an,types_,tr_,look_,_ready)) {
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
            String type_ = StringExpUtil.removeDottedSpaces(StringList.concat(r_.getPackageName(),".",_type));
            if (_an.getAnalyzing().getAnaClassBody(type_) != null) {
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
                if (!StringList.quickEq(end_, "*")) {
                    continue;
                }
                if (stopLookup(_an,types_,tr_,look_,_ready)) {
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
        String defPkg_ = _an.getStandards().getDefaultPkg();
        String type_ = StringExpUtil.removeDottedSpaces(StringList.concat(defPkg_,".",_type));
        if (_an.getAnalyzing().getAnaGeneType(_an,type_) != null) {
            return type_;
        }
        return "";
    }

    private static boolean stopLookup(ContextEl _an,StringList _types, String _import, String _look, ReadyTypes _ready) {
        String beginImp_ = StringExpUtil.removeDottedSpaces(_import.substring(0, _import.lastIndexOf('.')+1));
        String keyWordStatic_ = _an.getKeyWords().getKeyWordStatic();
        boolean stQualifier_ = false;
        if (StringExpUtil.startsWithKeyWord(beginImp_, keyWordStatic_)) {
            beginImp_ = beginImp_.substring(keyWordStatic_.length()).trim();
            stQualifier_ = true;
        }
        String typeInner_ = StringList.concat(beginImp_, _look);
        StringList allInnerTypes_ = AnaTemplates.getAllInnerTypes(typeInner_, _an);
        String owner_ = allInnerTypes_.first();
        AnaGeneType cl_ = _an.getAnalyzing().getAnaGeneType(_an,owner_);
        String res_ = owner_;
        boolean addImport_ = true;
        if (cl_ != null) {
            int size_ = allInnerTypes_.size();
            for (int i = 2; i < size_; i+=2) {
                String i_ = allInnerTypes_.get(i).trim();
                if (!_ready.isReady(res_)) {
                    return true;
                }
                StringList builtInners_ = AnaTypeUtil.getInners(res_, allInnerTypes_.get(i-1), i_, stQualifier_, _an);
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

    public static CustList<CustList<ImportedMethod>> lookupImportStaticMethods(ContextEl _analyzable, String _glClass, String _method) {
        CustList<CustList<ImportedMethod>> methods_ = new CustList<CustList<ImportedMethod>>();
        AccessedBlock type_ = _analyzable.getAnalyzing().getCurrentGlobalBlock().getCurrentGlobalBlockImporting();
        CustList<StringList> imports_ = new CustList<StringList>();
        fetchImports(type_, imports_);
        String keyWordStatic_ = _analyzable.getKeyWords().getKeyWordStatic();
        int import_ = 1;
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
                String foundCandidate_ = resolveCandidate(_analyzable,typeLoc_);
                AnaGeneType root_ = _analyzable.getAnalyzing().getAnaGeneType(_analyzable,foundCandidate_);
                if (root_ == null) {
                    continue;
                }
                String end_ = StringExpUtil.removeDottedSpaces(st_.substring(st_.lastIndexOf('.')+1));
                if (!StringList.quickEq(end_, _method.trim())) {
                    continue;
                }
                StringList typesLoc_ = new StringList(foundCandidate_);
                typesLoc_.addAllElts(root_.getAllSuperTypes());
                fetchImportStaticMethods(_analyzable,_glClass, _method, m_, foundCandidate_, typesLoc_);
            }
            methods_.add(m_);
            import_++;
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
                if (!StringList.quickEq(end_, "*")) {
                    continue;
                }
                String typeLoc_ = StringExpUtil.removeDottedSpaces(st_.substring(0,st_.lastIndexOf('.')));
                String foundCandidate_ = resolveCandidate(_analyzable,typeLoc_);
                AnaGeneType root_ = _analyzable.getAnalyzing().getAnaGeneType(_analyzable,foundCandidate_);
                if (root_ == null) {
                    continue;
                }
                StringList typesLoc_ = new StringList(foundCandidate_);
                typesLoc_.addAllElts(root_.getAllSuperTypes());
                fetchImportStaticMethods(_analyzable,_glClass, _method, m_, foundCandidate_, typesLoc_);
            }
            methods_.add(m_);
            import_++;
        }
        return methods_;
    }

    private static void fetchImportStaticMethods(ContextEl _analyzable, String _glClass, String _method, CustList<ImportedMethod> _methods, String _typeLoc, StringList _typesLoc) {
        for (String s: _typesLoc) {
            AnaGeneType super_ = _analyzable.getAnalyzing().getAnaGeneType(_analyzable,s);
            String pkg_ = super_.getPackageName();
            String outer_ = "";
            if (super_ instanceof RootBlock) {
                outer_ = ((RootBlock)super_).getOuterFullName();
            }
            for (GeneCustStaticMethod e: ClassesUtil.getMethodBlocks(super_)) {
                if (!e.isStaticMethod()) {
                    continue;
                }
                if (!StringList.quickEq(_method.trim(), e.getId().getName())) {
                    continue;
                }
                if (e instanceof OverridableBlock) {
                    OverridableBlock c = (OverridableBlock) e;
                    Accessed a_ = new Accessed(c.getAccess(), pkg_, s, outer_);
                    if (!ContextUtil.canAccess(_typeLoc, a_, _analyzable)) {
                        continue;
                    }
                    if (!ContextUtil.canAccess(_glClass, a_, _analyzable)) {
                        continue;
                    }
                }
                ClassMethodId clMet_ = new ClassMethodId(s, e.getId());
                ImportedMethod value_ = new ImportedMethod(e.getImportedReturnType(), clMet_);
                if (e instanceof Block) {
                    value_.setFileName(((Block)e).getFile().getFileName());
                }
                if (e instanceof NamedFunctionBlock) {
                    value_.setMemberNumber(((NamedFunctionBlock)e).getNameNumber());
                }
                if (super_ instanceof RootBlock) {
                    value_.setRootNumber(((RootBlock)super_).getNumberAll());
                }
                addImportMethod(_methods, value_);
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

    public static StringMap<ImportedField> lookupImportStaticFields(ContextEl _analyzable, String _glClass, String _method) {
        StringMap<ImportedField> methods_ = new StringMap<ImportedField>();
        int import_ = 1;
        AccessedBlock type_ = _analyzable.getAnalyzing().getCurrentGlobalBlock().getCurrentGlobalBlockImporting();
        CustList<StringList> imports_ = new CustList<StringList>();
        fetchImports(type_, imports_);
        String keyWordStatic_ = _analyzable.getKeyWords().getKeyWordStatic();
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
                String foundCandidate_ = resolveCandidate(_analyzable,typeLoc_);
                AnaGeneType root_ = _analyzable.getAnalyzing().getAnaGeneType(_analyzable,foundCandidate_);
                if (root_ == null) {
                    continue;
                }
                String end_ = StringExpUtil.removeDottedSpaces(st_.substring(st_.lastIndexOf('.')+1));
                if (!StringList.quickEq(end_, _method.trim())) {
                    continue;
                }
                StringList typesLoc_ = new StringList(foundCandidate_);
                typesLoc_.addAllElts(root_.getAllSuperTypes());
                fetchImportStaticFieldsTmp(_analyzable,_glClass, _method, methods_, import_, foundCandidate_, typesLoc_);
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
                if (!StringList.quickEq(end_, "*")) {
                    continue;
                }
                String typeLoc_ = StringExpUtil.removeDottedSpaces(st_.substring(0,st_.lastIndexOf('.')));
                String foundCandidate_ = resolveCandidate(_analyzable,typeLoc_);
                AnaGeneType root_ = _analyzable.getAnalyzing().getAnaGeneType(_analyzable,foundCandidate_);
                if (root_ == null) {
                    continue;
                }
                StringList typesLoc_ = new StringList(foundCandidate_);
                typesLoc_.addAllElts(root_.getAllSuperTypes());
                fetchImportStaticFieldsTmp(_analyzable,_glClass, _method, methods_, import_, foundCandidate_, typesLoc_);
            }
            import_++;
        }
        return methods_;
    }

    public static String resolveCandidate(ContextEl _analyzable,String _c) {
        StringList allInnerTypes_ = getParts(_analyzable,_c);
        String owner_ = allInnerTypes_.first();
        AnaGeneType cl_ = _analyzable.getAnalyzing().getAnaGeneType(_analyzable,owner_);
        String res_ = owner_;
        if (cl_ != null) {
            int size_ = allInnerTypes_.size();
            for (int i = 2; i < size_; i+=2) {
                String i_ = allInnerTypes_.get(i).trim();
                String part_ = allInnerTypes_.get(i-1);
                StringList builtInners_ = AnaTypeUtil.getInners(res_,part_,i_,false,_analyzable);
                if (builtInners_.onlyOneElt()) {
                    res_ = builtInners_.first();
                    continue;
                }
                break;
            }
        }
        return res_;
    }

    public static StringList getParts(ContextEl _analyzable,String _c) {
        StringList allInnerTypes_;
        allInnerTypes_ = AnaTemplates.getAllInnerTypes(_c, _analyzable);
        return allInnerTypes_;
    }

    private static void fetchImportStaticFieldsTmp(ContextEl _analyzable,String _glClass, String _method, StringMap<ImportedField> _methods, int _import, String _typeLoc, StringList _typesLoc) {
        fetchImportStaticFields(_analyzable,_glClass,_method,_methods,_import,_typeLoc,_typesLoc);
    }

    public static void fetchImportStaticFields(ContextEl _analyzable,String _glClass, String _method, StringMap<ImportedField> _methods, int _import, String _typeLoc, StringList _typesLoc) {
        for (String s: _typesLoc) {
            AnaGeneType super_ = _analyzable.getAnalyzing().getAnaGeneType(_analyzable,s);
            if (super_ instanceof StandardType) {
                for (StandardField m: ((StandardType)super_).getFields().values()) {
                    int ind_ = notMatch(_method, m);
                    if (ind_ < 0) {
                        continue;
                    }
                    Accessed a_ = new Accessed(AccessEnum.PUBLIC,"","","");
                    addImport(_methods,s, new ImportedField(_import,a_,m.getImportedClassName(),m.isFinalField(),-1));
                }
            }
            if (super_ instanceof RootBlock){
                RootBlock cust_ = (RootBlock) super_;
                String pkg_ = cust_.getPackageName();
                String outerFullName_ = cust_.getOuterFullName();
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
                    Accessed a_ = new Accessed(e.getAccess(),pkg_,s,outerFullName_);
                    if (!ContextUtil.canAccess(_typeLoc, a_, _analyzable)) {
                        continue;
                    }
                    if (!ContextUtil.canAccess(_glClass, a_, _analyzable)) {
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
            if (StringList.quickEq(e.getKey(), _class)) {
                return;
            }
        }
        _methods.addEntry(_class,_value);
    }
    private static int notMatch(String _method, GeneField _field) {
        if (!_field.isStaticField()) {
            return -1;
        }
        return StringList.indexOf(_field.getFieldName(), _method.trim());
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
