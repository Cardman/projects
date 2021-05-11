package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.util.MappingLocalType;
import code.expressionlanguage.analyze.util.TypeVar;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.stds.StandardType;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class ResolvingTypes {
    private ResolvingTypes() {
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
            String pref_ = ExportCst.anchorErr(err_);
            partOffsets_.add(new PartOffset(pref_,rc_));
            partOffsets_.add(new PartOffset(ExportCst.END_ANCHOR,rc_+1));
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
            String pref_ = ExportCst.anchorErr(err_);
            partOffsets_.add(new PartOffset(pref_,rc_));
            partOffsets_.add(new PartOffset(ExportCst.END_ANCHOR,rc_+void_.length()));
            return _page.getAliasObject();
        }
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
        String tr_ = _in.trim();
        if (StringUtil.quickEq(tr_, void_)) {
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
            resType_ = AnaPartTypeUtil.processAnalyze(tr_, gl_, a_,r_, rc_, _partOffsets, _page);
        } else {
            resType_ = AnaPartTypeUtil.processAnalyzeLine(tr_, gl_, a_,r_, rc_, _partOffsets, _page);
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
            String pref_ = ExportCst.anchorErr(err_);
            partOffsets_.add(new PartOffset(pref_,rc_));
            partOffsets_.add(new PartOffset(ExportCst.END_ANCHOR,rc_+1));
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
            String pref_ = ExportCst.anchorErr(err_);
            partOffsets_.add(new PartOffset(pref_,rc_));
            partOffsets_.add(new PartOffset(ExportCst.END_ANCHOR,rc_+void_.length()));
            return _page.getAliasObject();
        }
        AccessedBlock r_ = _page.getCurrentGlobalBlock().getCurrentGlobalBlock();
        StringMap<StringList> varsCt_ = _page.getCurrentConstraints().getCurrentConstraints();
        _page.getBuildingConstraints().buildCurrentConstraintsFull();
        AccessedBlock a_ = _page.getCurrentGlobalBlock().getCurrentGlobalBlock(r_);
        CustList<PartOffset> partOffsets_ = _page.getCurrentParts();
        partOffsets_.clear();
        String gl_ = _page.getGlobalClass();
        AnaResultPartType resType_;
        _page.getCurrentBadIndexes().clear();
        if (_exact) {
            resType_ = AnaPartTypeUtil.processAnalyze(tr_, gl_, a_,r_, rc_,partOffsets_, _page);
        } else {
            resType_ = AnaPartTypeUtil.processAnalyzeLine(tr_, gl_, a_,r_, rc_,partOffsets_, _page);
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
            String pref_ = ExportCst.anchorErr(err_);
            partOffsets_.add(new PartOffset(pref_,rc_));
            partOffsets_.add(new PartOffset(ExportCst.END_ANCHOR,rc_+1));
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
            String pref_ = ExportCst.anchorErr(err_);
            partOffsets_.add(new PartOffset(pref_,rc_));
            partOffsets_.add(new PartOffset(ExportCst.END_ANCHOR,rc_+void_.length()));
            return _page.getAliasObject();
        }
        AccessedBlock r_ = _page.getCurrentGlobalBlock().getCurrentGlobalBlock();
        StringMap<StringList> varsCt_ = _page.getCurrentConstraints().getCurrentConstraints();
        _page.getBuildingConstraints().buildCurrentConstraintsFull();
        AccessedBlock a_ = _page.getCurrentGlobalBlock().getCurrentGlobalBlock(r_);
        CustList<PartOffset> partOffsets_ = _page.getCurrentParts();
        partOffsets_.clear();
        String gl_ = _page.getGlobalClass();
        AnaResultPartType resType_;
        _page.getCurrentBadIndexes().clear();
        resType_ = AnaPartTypeUtil.processAccAnalyze(tr_, gl_, a_,r_, rc_,partOffsets_, _page);
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
        return resolveAccessibleIdTypeBlock(_loc, _in, _page).getFullName();
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
            return new ResolvedIdType(res_,std_);
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
            String err_ = un_.getBuiltError();
            String pref_ = ExportCst.anchorErr(err_);
            partOffsets_.add(new PartOffset(pref_,rc_+firstOff_+_loc));
            partOffsets_.add(new PartOffset(ExportCst.END_ANCHOR,rc_+firstOff_+_loc+void_.length()));
            return new ResolvedIdType("",null);
        }
        if (tr_.isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            un_.setIndexFile(rc_);
            //_in len
            un_.buildError(_page.getAnalysisMessages().getEmptyType());
            _page.getLocalizer().addError(un_);
            String err_ = un_.getBuiltError();
            String pref_ = ExportCst.anchorErr(err_);
            partOffsets_.add(new PartOffset(pref_,rc_+firstOff_+_loc));
            partOffsets_.add(new PartOffset(ExportCst.END_ANCHOR,rc_+firstOff_+_loc+1));
            return new ResolvedIdType("",null);
        }
        RootBlock b_ = _page.getAnaClassBody(res_);
        AnaGeneType resType_ = b_;
        if (b_ == null) {
            MappingLocalType resolved_ = _page.getMappingLocal().getVal(base_);
            if (resolved_ != null) {
                ContextUtil.appendParts(firstOff_+_loc,firstOff_+_loc + base_.length(),resolved_.getFullName(),partOffsets_, _page);
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
                    String err_ = undef_.getBuiltError();
                    String pref_ = ExportCst.anchorErr(err_);
                    partOffsets_.add(new PartOffset(pref_,rc_+firstOff_+_loc));
                    partOffsets_.add(new PartOffset(ExportCst.END_ANCHOR,rc_+firstOff_+_loc + base_.length()));
                    return new ResolvedIdType("",null);
                }
                ContextUtil.appendParts(firstOff_+_loc,firstOff_+_loc + base_.length(),id_,partOffsets_, _page);
                res_ = id_;
                resType_ = _page.getAnaGeneType(id_);
            }
        } else {
            ContextUtil.appendParts(firstOff_+_loc,firstOff_+_loc + base_.length(),res_,partOffsets_, _page);
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
                String pref_ = ExportCst.anchorErr(err_);
                partOffsets_.add(new PartOffset(pref_,rc_+offset_+delta_));
                partOffsets_.add(new PartOffset(ExportCst.END_ANCHOR,rc_+offset_+delta_ + i_.trim().length()));
                return new ResolvedIdType("",null);
            }
            resType_ = inner_;
            ContextUtil.appendParts(offset_+delta_,offset_+delta_ + i_.trim().length(),resId_,partOffsets_, _page);
            res_ = resId_;
            offset_ += i_.length() + delta_;
        }
        return new ResolvedIdType(res_,resType_);
    }

    public static String resolveCorrectType(String _in, AnalyzedPageEl _page) {
        return resolveCorrectType(0, _in, _page);
    }

    public static String resolveCorrectType(int _loc, String _in, AnalyzedPageEl _page) {
        return resolveCorrectType(_loc, _in, true, _page);
    }
}
