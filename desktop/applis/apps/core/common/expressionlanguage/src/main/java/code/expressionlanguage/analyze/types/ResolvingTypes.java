package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.util.MappingLocalType;
import code.expressionlanguage.analyze.util.TypeVar;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.stds.StandardType;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class ResolvingTypes {
    private ResolvingTypes() {
    }

    public static AnaResultPartType resolveAccessibleIdTypeWithoutError(int _loc, String _in, AnalyzedPageEl _page) {
        String void_ = _page.getAliasVoid();
        if (StringUtil.quickEq(_in.trim(), void_)) {
            return new AnaResultPartType(_in,_loc,"",null, null);
        }
        AccessedBlock r_ = _page.getCurrentGlobalBlock().getCurrentGlobalBlock();
        int rc_ = _page.getLocalizer().getCurrentLocationIndex()+_loc;
        AccessedBlock a_ = _page.getCurrentGlobalBlock().getCurrentGlobalBlock(r_);
        return AnaPartTypeUtil.processAnalyzeLineWithoutErr(_in, a_,r_, rc_, _page);
    }

    public static AnaResultPartType resolveCorrectAccessibleType(int _loc, String _in, String _fromType, AnalyzedPageEl _page) {
        int rc_ = _page.getLocalizer().getCurrentLocationIndex()+_loc;
        AccessedBlock r_ = _page.getCurrentGlobalBlock().getCurrentGlobalBlock();
        StringMap<StringList> vars_ = new StringMap<StringList>();
        String idFromType_ = StringExpUtil.getIdFromAllTypes(_fromType);
        AnaGeneType from_ = _page.getAnaGeneType(idFromType_);
        FileBlock ref_ = null;
        if (ContextUtil.isFromCustFile(from_)) {
            ref_ = ((AbsBk)from_).getFile();
        }
        _page.getAvailableVariables().clear();
        if (from_ instanceof RootBlock) {
            for (TypeVar t: ((RootBlock)from_).getParamTypesMapValues()) {
                _page.getAvailableVariables().addEntry(t.getName(),t.getOffset());
                vars_.addEntry(t.getName(), t.getConstraints());
            }
        }
        AnaResultPartType resType_ = AnaPartTypeUtil.processAnalyzeAccessibleId(_in, r_, ref_,rc_, _page);
        boolean ok_ = AnaPartTypeUtil.processAnalyzeConstraintsCore(resType_, vars_, true, _page);
        if (!ok_) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(_page.getAnalysisMessages().getBadParamerizedType(),
                    _in);
            _page.getLocalizer().addError(un_);
        }
        return resType_;
    }

    public static AnaResultPartType resolveCorrectTypeWithoutErrors(int _loc, String _in, AnalyzedPageEl _page) {
        String tr_ = _in.trim();
        AccessedBlock r_ = _page.getCurrentGlobalBlock().getCurrentGlobalBlock();
        StringMap<StringList> varsCt_ = _page.getCurrentConstraints().getCurrentConstraints();
        _page.getBuildingConstraints().buildCurrentConstraintsFull();
        AccessedBlock a_ = _page.getCurrentGlobalBlock().getCurrentGlobalBlock(r_);
        String gl_ = _page.getGlobalClass();
        int rc_ = _page.getLocalizer().getCurrentLocationIndex() + _loc;
        _page.getCurrentBadIndexes().clear();
        AnaResultPartType resType_ = AnaPartTypeUtil.processAnalyzeLine(tr_, gl_, a_, r_, rc_, _page);
        AnaPartTypeUtil.processAnalyzeConstraintsCore(resType_, varsCt_, false, _page);
        inaccess(_page, resType_);
        return resType_;
    }

    public static AnaResultPartType resolveCorrectTypeWithoutErrorsExact(int _loc, String _in, AnalyzedPageEl _page) {
        String tr_ = _in.trim();
        AccessedBlock r_ = _page.getCurrentGlobalBlock().getCurrentGlobalBlock();
        StringMap<StringList> varsCt_ = _page.getCurrentConstraints().getCurrentConstraints();
        _page.getBuildingConstraints().buildCurrentConstraintsFull();
        AccessedBlock a_ = _page.getCurrentGlobalBlock().getCurrentGlobalBlock(r_);
        String gl_ = _page.getGlobalClass();
        int rc_ = _page.getLocalizer().getCurrentLocationIndex() + _loc;
        _page.getCurrentBadIndexes().clear();
        AnaResultPartType resType_ = AnaPartTypeUtil.processAnalyze(tr_, gl_, a_, r_, rc_, _page);
        AnaPartTypeUtil.processAnalyzeConstraintsCore(resType_, varsCt_, true, _page);
        inaccess(_page, resType_);
        return resType_;
    }

    private static void inaccess(AnalyzedPageEl _page, AnaResultPartType _resType) {
        if (!_page.getCurrentBadIndexes().isEmpty()) {
            _resType.setOk(false);
        }
    }

    public static AnaResultPartType resolveCorrectType(int _loc, String _in, boolean _exact, AnalyzedPageEl _page) {
        int rc_ = _page.getLocalizer().getCurrentLocationIndex() + _loc;
        String tr_ = _in.trim();
        AccessedBlock r_ = _page.getCurrentGlobalBlock().getCurrentGlobalBlock();
        StringMap<StringList> varsCt_ = _page.getCurrentConstraints().getCurrentConstraints();
        _page.getBuildingConstraints().buildCurrentConstraintsFull();
        AccessedBlock a_ = _page.getCurrentGlobalBlock().getCurrentGlobalBlock(r_);
        String gl_ = _page.getGlobalClass();
        AnaResultPartType resType_;
        _page.getCurrentBadIndexes().clear();
        if (_exact) {
            resType_ = AnaPartTypeUtil.processAnalyze(tr_, gl_, a_,r_, rc_, _page);
        } else {
            resType_ = AnaPartTypeUtil.processAnalyzeLine(tr_, gl_, a_,r_, rc_, _page);
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
        return checkResType(_in, _exact, rc_, varsCt_, resType_, _page);
    }

    public static AnaResultPartType resolveCorrectTypeAccessible(int _loc, String _in, AnalyzedPageEl _page) {
        int rc_ = _page.getLocalizer().getCurrentLocationIndex() + _loc;
//        String void_ = _page.getAliasVoid();
        String tr_ = _in.trim();
//        if (tr_.isEmpty()) {
//            FoundErrorInterpret un_ = new FoundErrorInterpret();
//            un_.setFileName(_page.getLocalizer().getCurrentFileName());
//            un_.setIndexFile(rc_);
//            //_in len
//            un_.buildError(_page.getAnalysisMessages().getEmptyType());
//            _page.getLocalizer().addError(un_);
//            CustList<PartOffset> partOffsets_ = _page.getCurrentParts();
//            partOffsets_.clear();
//            String err_ = un_.getBuiltError();
//            String pref_ = ExportCst.anchorErr(err_);
//            partOffsets_.add(new PartOffset(pref_,rc_));
//            partOffsets_.add(new PartOffset(ExportCst.END_ANCHOR,rc_+1));
//            return _page.getAliasObject();
//        }
//        if (StringUtil.quickEq(tr_, void_)) {
//            FoundErrorInterpret un_ = new FoundErrorInterpret();
//            un_.setFileName(_page.getLocalizer().getCurrentFileName());
//            un_.setIndexFile(rc_);
//            //_in len
//            un_.buildError(_page.getAnalysisMessages().getVoidType(),
//                    void_);
//            _page.getLocalizer().addError(un_);
//            CustList<PartOffset> partOffsets_ = _page.getCurrentParts();
//            partOffsets_.clear();
//            String err_ = un_.getBuiltError();
//            String pref_ = ExportCst.anchorErr(err_);
//            partOffsets_.add(new PartOffset(pref_,rc_));
//            partOffsets_.add(new PartOffset(ExportCst.END_ANCHOR,rc_+void_.length()));
//            return _page.getAliasObject();
//        }
        AccessedBlock r_ = _page.getCurrentGlobalBlock().getCurrentGlobalBlock();
        StringMap<StringList> varsCt_ = _page.getCurrentConstraints().getCurrentConstraints();
        _page.getBuildingConstraints().buildCurrentConstraintsFull();
        AccessedBlock a_ = _page.getCurrentGlobalBlock().getCurrentGlobalBlock(r_);
        String gl_ = _page.getGlobalClass();
        AnaResultPartType resType_;
        _page.getCurrentBadIndexes().clear();
        resType_ = AnaPartTypeUtil.processAccAnalyze(tr_, gl_, a_,r_, rc_, _page);
        return checkResType(_in, true, rc_, varsCt_, resType_, _page);
    }

    private static AnaResultPartType checkResType(String _in, boolean _exact, int _rc, StringMap<StringList> _varsCt, AnaResultPartType _resType, AnalyzedPageEl _page) {
        boolean ok_ = AnaPartTypeUtil.processAnalyzeConstraintsCore(_resType, _varsCt, _exact, _page);
        if (!ok_) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            un_.setIndexFile(_rc);
            //_in len
            un_.buildError(_page.getAnalysisMessages().getBadParamerizedType(),
                    _in);
            _page.getLocalizer().addError(un_);
        }
        return _resType;
    }

    public static ResolvedIdType resolveAccessibleIdTypeBlock(int _loc, String _in, AnalyzedPageEl _page) {
        int rc_ = _page.getLocalizer().getCurrentLocationIndex();
        String tr_ = _in.trim();
        String void_ = _page.getAliasVoid();
        AccessedBlock r_ = _page.getCurrentGlobalBlock().getCurrentGlobalBlock();
        StringList inners_ = AnaInherits.getAllInnerTypes(_in, _page);
        String firstFull_ = inners_.first();
        int firstOff_ = StringUtil.getFirstPrintableCharIndex(firstFull_);
        String base_ = firstFull_.trim();
        if (base_.isEmpty()) {
            firstOff_ = 0;
        }
        String res_ = StringExpUtil.removeDottedSpaces(base_);
        StandardType std_ = _page.getStandardsTypes().getVal(res_);
        if (std_ != null) {
            return new ResolvedIdType(res_,std_, new CustList<AnaResultPartType>());
        }
        CustList<AnaResultPartType> dels_ = new CustList<AnaResultPartType>();
        if (StringUtil.quickEq(tr_, void_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(_page.getAnalysisMessages().getVoidType(),
                    void_);
            _page.getLocalizer().addError(un_);
            dels_.add(PreLinkagePartTypeUtil.processAccessKoRootAnalyze(un_,void_,r_,rc_+firstOff_+_loc,_page));
            return new ResolvedIdType("",null, dels_);
        }
        if (tr_.isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(_page.getAnalysisMessages().getEmptyType());
            _page.getLocalizer().addError(un_);
            dels_.add(PreLinkagePartTypeUtil.processAccessKoRootAnalyze(un_," ",r_,rc_+firstOff_+_loc,_page));
            return new ResolvedIdType("",null, dels_);
        }
        RootBlock b_ = _page.getAnaClassBody(res_);
        AnaGeneType resType_ = b_;
        if (b_ == null) {
            MappingLocalType resolved_ = _page.getMappingLocal().getVal(base_);
            if (resolved_ != null) {
                dels_.add(PreLinkagePartTypeUtil.processAccessOkRootAnalyze(base_,resolved_.getFullName(),r_,_page.getTraceIndex()+firstOff_+_loc,_page));
                res_ = resolved_.getFullName();
                resType_ = resolved_.getType();
            } else {
                String id_ = ResolvingImportTypes.lookupImportType(base_,r_, new AlwaysReadyTypes(), _page);
                if (id_.isEmpty()) {
                    FoundErrorInterpret undef_;
                    undef_ = new FoundErrorInterpret();
                    undef_.setFileName(_page.getLocalizer().getCurrentFileName());
                    undef_.setIndexFile(rc_);
                    //_in len
                    undef_.buildError(_page.getAnalysisMessages().getUnknownType(),
                            _in);
                    _page.getLocalizer().addError(undef_);
                    dels_.add(PreLinkagePartTypeUtil.processAccessKoRootAnalyze(undef_,base_,r_,rc_+firstOff_+_loc,_page));
                    return new ResolvedIdType("",null, dels_);
                }
                resType_ = _page.getAnaGeneType(id_);
                dels_.add(PreLinkagePartTypeUtil.processAccessOkRootAnalyze(base_,id_,r_,_page.getTraceIndex()+firstOff_+_loc,_page));
                res_ = id_;
            }
        } else {
            dels_.add(PreLinkagePartTypeUtil.processAccessOkRootAnalyze(base_,res_,r_,_page.getTraceIndex()+firstOff_+_loc,_page));
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
                dels_.add(PreLinkagePartTypeUtil.processAccessKoRootAnalyze(undef_,i_.trim(),r_,rc_+offset_+delta_,_page));
                return new ResolvedIdType("",null, dels_);
            }
            resType_ = inner_;
            dels_.add(PreLinkagePartTypeUtil.processAccessOkRootAnalyze(i_.trim(),resId_,r_,_page.getTraceIndex()+offset_+delta_,_page));
            res_ = resId_;
            offset_ += i_.length() + delta_;
        }
        return new ResolvedIdType(res_,resType_, dels_);
    }


    public static AnaResultPartType resolveCorrectType(String _in, AnalyzedPageEl _page) {
        return resolveCorrectType(0, _in, _page);
    }

    public static AnaResultPartType resolveCorrectType(int _loc, String _in, AnalyzedPageEl _page) {
        return resolveCorrectType(_loc, _in, true, _page);
    }
}
