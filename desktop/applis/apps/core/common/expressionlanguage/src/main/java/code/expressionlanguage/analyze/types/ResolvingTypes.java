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
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class ResolvingTypes {
    private ResolvingTypes() {
    }

    public static AnaResultPartType resolveAccessibleIdTypeWithoutError(int _loc, String _in, AnalyzedPageEl _page) {
        AccessedBlock r_ = _page.getImporting();
        int rc_ = _page.getIndex()+_loc;
        return AnaPartTypeUtil.processAnalyzeLineWithoutErr(_in, r_,r_, rc_, _page);
    }

    public static AnaResultPartType resolveCorrectAccessibleType(int _loc, String _in, String _fromType, AnalyzedPageEl _page) {
        int rc_ = _page.getIndex()+_loc;
        AccessedBlock r_ = _page.getImporting();
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
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(_page,_loc);
            //_in len
            un_.buildError(_page.getAnalysisMessages().getBadParamerizedType(),
                    _in);
            _page.getLocalizer().addError(un_);
        }
        return resType_;
    }

    public static AnaResultPartType resolveCorrectTypeWithoutErrors(int _loc, String _in, AnalyzedPageEl _page) {
        String tr_ = _in.trim();
        AccessedBlock r_ = _page.getImporting();
        StringMap<StringList> varsCt_ = _page.getCurrentConstraints().getCurrentConstraints();
        _page.getBuildingConstraints().buildCurrentConstraintsFull();
        String gl_ = _page.getGlobalClass();
        int rc_ = _page.getIndex() + _loc;
        _page.getCurrentBadIndexes().clear();
        AnaResultPartType resType_ = AnaPartTypeUtil.processAnalyzeLine(tr_, gl_, r_, r_, rc_, _page);
        AnaPartTypeUtil.processAnalyzeConstraintsCore(resType_, varsCt_, false, _page);
        inaccess(_page, resType_);
        return resType_;
    }

    public static AnaResultPartType resolveCorrectTypeWithoutErrorsExact(int _loc, String _in, AnalyzedPageEl _page) {
        String tr_ = _in.trim();
        AccessedBlock r_ = _page.getImporting();
        StringMap<StringList> varsCt_ = _page.getCurrentConstraints().getCurrentConstraints();
        _page.getBuildingConstraints().buildCurrentConstraintsFull();
        String gl_ = _page.getGlobalClass();
        int rc_ = _page.getIndex() + _loc;
        _page.getCurrentBadIndexes().clear();
        AnaResultPartType resType_ = AnaPartTypeUtil.processAnalyze(tr_, gl_, r_, r_, rc_, _page);
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
        int rc_ = _page.getIndex() + _loc;
        String tr_ = _in.trim();
        AccessedBlock r_ = _page.getImporting();
        StringMap<StringList> varsCt_ = _page.getCurrentConstraints().getCurrentConstraints();
        _page.getBuildingConstraints().buildCurrentConstraintsFull();
        String gl_ = _page.getGlobalClass();
        AnaResultPartType resType_;
        _page.getCurrentBadIndexes().clear();
        if (_exact) {
            resType_ = AnaPartTypeUtil.processAnalyze(tr_, gl_, r_,r_, rc_, _page);
        } else {
            resType_ = AnaPartTypeUtil.processAnalyzeLine(tr_, gl_, r_,r_, rc_, _page);
        }
        for (InaccessibleType i: _page.getCurrentBadIndexes()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(_page,_loc+i.getIndex());
            //part len
            un_.buildError(_page.getAnalysisMessages().getInaccessibleType(),
                    i.getType(),gl_);
            _page.getLocalizer().addError(un_);
        }
        return checkResType(_in, _exact, _loc, varsCt_, resType_, _page);
    }

    public static AnaResultPartType resolveCorrectTypeAccessible(int _loc, String _in, AnalyzedPageEl _page) {
        int rc_ = _page.getIndex() + _loc;
//        String void_ = _page.getAliasVoid();
        String tr_ = _in.trim();
//        if (tr_.isEmpty()) {
//            FoundErrorInterpret un_ = new FoundErrorInterpret();
//            un_.setFile(_page.getCurrentFile());
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
//            un_.setFile(_page.getCurrentFile());
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
        AccessedBlock r_ = _page.getImporting();
        StringMap<StringList> varsCt_ = _page.getCurrentConstraints().getCurrentConstraints();
        _page.getBuildingConstraints().buildCurrentConstraintsFull();
        String gl_ = _page.getGlobalClass();
        AnaResultPartType resType_;
        _page.getCurrentBadIndexes().clear();
        resType_ = AnaPartTypeUtil.processAccAnalyze(tr_, gl_, r_,r_, rc_, _page);
        return checkResType(_in, true, _loc, varsCt_, resType_, _page);
    }

    private static AnaResultPartType checkResType(String _in, boolean _exact, int _rc, StringMap<StringList> _varsCt, AnaResultPartType _resType, AnalyzedPageEl _page) {
        boolean ok_ = AnaPartTypeUtil.processAnalyzeConstraintsCore(_resType, _varsCt, _exact, _page);
        if (!ok_) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(_page,_rc);
            //_in len
            un_.buildError(_page.getAnalysisMessages().getBadParamerizedType(),
                    _in);
            _page.getLocalizer().addError(un_);
        }
        return _resType;
    }

    public static ResolvedIdType resolveAccessibleIdTypeBlock(int _loc, String _in, AnalyzedPageEl _page) {
        int rc_ = _page.getIndex();
        AccessedBlock r_ = _page.getImporting();
        int rootLoc_ = rc_ + _loc;
        StrTypes operators_ = new StrTypes();
        CustList<FoundTypeIdDto> found_ = new CustList<FoundTypeIdDto>();
        CustList<FoundTypeErrorDto> err_ = new CustList<FoundTypeErrorDto>();
        ResolvedIdTypeContent resolvedIdType_ = resolveAccessibleIdTypeBlockWithoutErr(_in, _page, operators_, found_, err_);
        CustList<AnaResultPartType> all_ = new CustList<AnaResultPartType>();
        for (FoundTypeIdDto f: found_) {
            all_.add(PreLinkagePartTypeUtil.processAccessOkRootAnalyze(f.getInput(),f.getType(),f.getSolved(),r_,rootLoc_,f.getIndexInType(),_page));
        }
        for (FoundTypeErrorDto f: err_) {
            FoundErrorInterpret undef_;
            undef_ = new FoundErrorInterpret();
            undef_.setFile(_page.getCurrentFile());
            undef_.setIndexFile(_page,_loc);
            undef_.setBuiltError(f.getSolved());
            all_.add(PreLinkagePartTypeUtil.processAccessKoRootAnalyze(f.isVoidType(),undef_,f.getInput(), rootLoc_,f.getIndexInType(),_page));
            _page.getLocalizer().addError(undef_);
        }
        AnaResultPartType result_ = PreLinkagePartTypeUtil.processAccessInnerRootAnalyze(_in, all_, operators_, r_, rootLoc_, _page);
        return new ResolvedIdType(resolvedIdType_.getFullName(),resolvedIdType_.getGeneType(), result_);
    }

    public static ResolvedIdTypeContent resolveAccessibleIdTypeBlockWithoutErr(String _in, AnalyzedPageEl _page, StrTypes _operators, CustList<FoundTypeIdDto> _found, CustList<FoundTypeErrorDto> _err) {
        String tr_ = _in.trim();
        String void_ = _page.getAliasVoid();
        AccessedBlock r_ = _page.getImporting();
        StringList inners_ = AnaInherits.getAllInnerTypes(_in, _page);
        String firstFull_ = inners_.first();
        int firstOff_;
        String base_ = firstFull_.trim();
        if (base_.isEmpty()) {
            firstOff_ = 0;
        } else {
            firstOff_ = StringUtil.getFirstPrintableCharIndex(firstFull_);
        }
        String res_ = StringExpUtil.removeDottedSpaces(base_);
        StandardType std_ = _page.getStandardsTypes().getVal(res_);
        if (std_ != null) {
            return new ResolvedIdTypeContent(res_,std_);
        }
        if (StringUtil.quickEq(tr_, void_)) {
            _err.add(new FoundTypeErrorDto(firstOff_,void_,FoundErrorInterpret.buildARError(_page.getAnalysisMessages().getVoidType(),
                    void_),true));
            return new ResolvedIdTypeContent("",null);
        }
        if (tr_.isEmpty()) {
            _err.add(new FoundTypeErrorDto(firstOff_," ",FoundErrorInterpret.buildARError(_page.getAnalysisMessages().getEmptyType())));
            return new ResolvedIdTypeContent("",null);
        }
        RootBlock b_ = _page.getAnaClassBody(res_);
        AnaGeneType resType_ = b_;
        if (b_ == null) {
            MappingLocalType resolved_ = _page.getMappingLocal().getVal(base_);
            if (resolved_ != null) {
                _found.add(new FoundTypeIdDto(firstOff_,base_,resolved_.getFullName(),resolved_.getType()));
                res_ = resolved_.getFullName();
                resType_ = resolved_.getType();
            } else {
                ResolvedIdTypeContent id_ = ResolvingImportTypes.lookupImportType(base_,r_, new AlwaysReadyTypes(), _page);
                if (id_ == null) {
                    _err.add(new FoundTypeErrorDto(firstOff_,base_,FoundErrorInterpret.buildARError(_page.getAnalysisMessages().getUnknownType(),
                            _in)));
                    return new ResolvedIdTypeContent("",null);
                }
                resType_ = id_.getGeneType();
                _found.add(new FoundTypeIdDto(firstOff_,base_,id_.getFullName(),resType_));
                res_ = id_.getFullName();
            }
        } else {
            _found.add(new FoundTypeIdDto(firstOff_,base_,res_,b_));
        }
        int offsetType_ = firstFull_.length();
        int size_ = inners_.size();
        for (int i = 2; i < size_; i += 2) {
            String i_ = inners_.get(i);
            String resId_;
            String oper_ = inners_.get(i - 1);
            _operators.addEntry(offsetType_,oper_);
            int delta_ = oper_.length();
            if (StringUtil.quickEq(".", oper_)) {
                resId_ = StringUtil.concat(res_,"..",i_.trim());
            } else {
                resId_ = StringUtil.concat(res_,"-",i_.trim());
            }
            RootBlock inner_ = _page.getAnaClassBody(resId_);
            if (inner_ == null) {
                _err.add(new FoundTypeErrorDto(offsetType_+delta_,i_.trim(),FoundErrorInterpret.buildARError(_page.getAnalysisMessages().getUnknownType(),
                        _in)));
                return new ResolvedIdTypeContent("",null);
            }
            resType_ = inner_;
            _found.add(new FoundTypeIdDto(offsetType_+delta_,i_.trim(),resId_,inner_));
            res_ = resId_;
            offsetType_ += i_.length() + delta_;
        }
        return new ResolvedIdTypeContent(res_,resType_);
    }

    public static AnaResultPartType resolveCorrectType(String _in, AnalyzedPageEl _page) {
        return resolveCorrectType(0, _in, _page);
    }

    public static AnaResultPartType resolveCorrectType(int _loc, String _in, AnalyzedPageEl _page) {
        return resolveCorrectType(_loc, _in, true, _page);
    }
}
