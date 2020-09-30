package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.accessing.TypeAccessor;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.analyze.util.TypeVar;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class ResolvingSuperTypes {
    private ResolvingSuperTypes() {
    }


    /**Used at building mapping constraints*/
    public static AnaResultPartType resolveTypeMapping(String _in, RootBlock _ana,
                                                       int _location, AnalyzedPageEl _page) {
        String tr_ = _in.trim();
        if (tr_.isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_ana.getFile().getFileName());
            un_.setIndexFile(_location);
            //_in len
            un_.buildError(_page.getAnalysisMessages().getEmptyType());
            _page.addLocError(un_);
            _ana.addNameErrors(un_);
            return new AnaResultPartType(_page.getAliasObject(),null);
        }
        String void_ = _page.getAliasVoid();
        if (StringList.quickEq(tr_, void_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_ana.getFile().getFileName());
            un_.setIndexFile(_location);
            //_in len
            un_.buildError(_page.getAnalysisMessages().getVoidType(),
                    void_);
            _page.addLocError(un_);
            _ana.addNameErrors(un_);
            return new AnaResultPartType(_page.getAliasObject(),null);
        }
        StringMap<Integer> variables_ = new StringMap<Integer>();
        for (RootBlock r: _ana.getSelfAndParentTypes()) {
            for (TypeVar t: r.getParamTypes()) {
                variables_.addEntry(t.getName(),t.getOffset());
            }
        }
        //No need to call Templates.isCorrect
        _page.getAvailableVariables().clear();
        _page.getAvailableVariables().putAllMap(variables_);
        String gl_ = _ana.getGenericString();
        CustList<PartOffset> partOffsets_ = _page.getCurrentParts();
        _page.getCurrentBadIndexes().clear();
        _page.setImportingAcces(new TypeAccessor(_ana.getFullName()));
        _page.setImportingTypes(_ana);
        AnaResultPartType resType_ = AnaPartTypeUtil.processAnalyzeHeader(_in, false,gl_, _ana,_ana, _location,partOffsets_, _page);
        for (InaccessibleType i: _page.getCurrentBadIndexes()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_ana.getFile().getFileName());
            un_.setIndexFile(_location+i.getIndex());
            //part len
            un_.buildError(_page.getAnalysisMessages().getInaccessibleType(),
                    i.getType(),gl_);
            _page.addLocError(un_);
        }
        if (resType_.getResult().trim().isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_ana.getFile().getFileName());
            un_.setIndexFile(_location);
            //_in len
            un_.buildError(_page.getAnalysisMessages().getUnknownType(),
                    _in);
            _page.addLocError(un_);
            return new AnaResultPartType(_page.getAliasObject(),null);
        }
        return resType_;
    }
    /**Used at building mapping constraints*/
    public static AnaResultPartType resolveTypeInherits(String _in, RootBlock _ana,
                                                        int _location, CustList<PartOffset> _partOffsets, AnalyzedPageEl _page) {
        AnaResultPartType resType_ = typeArguments(_in, _ana, _location, _partOffsets, _page);
        if (resType_ == null) {
            return new AnaResultPartType(_page.getAliasObject(),null);
        }
        for (String p:StringExpUtil.getAllTypes(resType_.getResult()).mid(1)){
            if (p.startsWith(Templates.SUB_TYPE)) {
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                call_.setFileName(_ana.getFile().getFileName());
                call_.setIndexFile(_location);
                //_in len
                call_.buildError(_page.getAnalysisMessages().getIllegalGenericSuperTypeBound(),
                        p,
                        resType_.getResult());
                _page.addLocError(call_);
                _ana.addNameErrors(call_);
            }
            if (p.startsWith(Templates.SUP_TYPE)) {
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                call_.setFileName(_ana.getFile().getFileName());
                call_.setIndexFile(_location);
                //_in len
                call_.buildError(_page.getAnalysisMessages().getIllegalGenericSuperTypeBound(),
                        p,
                        resType_.getResult());
                _page.addLocError(call_);
                _ana.addNameErrors(call_);
            }
        }
        return resType_;
    }
    public static AnaResultPartType typeArguments(String _in, RootBlock _ana,
                                                  int _location, CustList<PartOffset> _partOffsets, AnalyzedPageEl _page) {
        String tr_ = _in.trim();
        if (tr_.isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_ana.getFile().getFileName());
            un_.setIndexFile(_location);
            //_in len
            un_.buildError(_page.getAnalysisMessages().getEmptyType());
            _page.addLocError(un_);
            _ana.addNameErrors(un_);
            return null;
        }
        String void_ = _page.getAliasVoid();
        if (StringList.quickEq(tr_, void_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_ana.getFile().getFileName());
            un_.setIndexFile(_location);
            //_in len
            un_.buildError(_page.getAnalysisMessages().getVoidType(),
                    void_);
            _page.addLocError(un_);
            _ana.addNameErrors(un_);
            return null;
        }
        StringMap<Integer> variables_ = new StringMap<Integer>();
        for (RootBlock r: _ana.getSelfAndParentTypes()) {
            for (TypeVar t: r.getParamTypes()) {
                variables_.addEntry(t.getName(),t.getOffset());
            }
        }
        //No need to call Templates.isCorrect
        _page.getAvailableVariables().clear();
        _page.getAvailableVariables().putAllMap(variables_);
        String gl_ = _ana.getGenericString();
        RootBlock scope_ = _ana.getParentType();
        _page.getCurrentBadIndexes().clear();
        _page.setImportingAcces(new TypeAccessor(_ana.getFullName()));
        _page.setImportingTypes(_ana);
        AnaResultPartType resType_ = AnaPartTypeUtil.processAnalyzeHeader(_in,true,gl_, scope_,_ana, _location, _partOffsets, _page);
        for (InaccessibleType i: _page.getCurrentBadIndexes()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_ana.getFile().getFileName());
            un_.setIndexFile(_location+i.getIndex());
            //part len
            un_.buildError(_page.getAnalysisMessages().getInaccessibleType(),
                    i.getType(),gl_);
            _page.addLocError(un_);
        }
        if (resType_.getResult().trim().isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_ana.getFile().getFileName());
            un_.setIndexFile(_location);
            //_in len
            un_.buildError(_page.getAnalysisMessages().getUnknownType(),
                    _in);
            _page.addLocError(un_);
            return null;
        }
        return resType_;
    }

    public static String resolveBaseInherits(String _idSup, RootBlock _ana, StringList _readyTypes, AnalyzedPageEl _page) {
        String id_ = StringExpUtil.getIdFromAllTypes(_idSup);
        CustList<PartOffset> partOffsets_ = new CustList<PartOffset>();
        RootBlock scope_ = _ana.getParentType();
        InheritReadyTypes inh_ = new InheritReadyTypes(_readyTypes);
        _page.setImportingTypes(_ana);
        _page.getMappingLocal().clear();
        _page.getMappingLocal().putAllMap(_ana.getMappings());
        return AnaPartTypeUtil.processAnalyzeLineInherits(id_, inh_, scope_,_ana, partOffsets_, _page);
    }
}
