package code.expressionlanguage.analyze.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.accessing.TypeAccessor;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.analyze.util.TypeVar;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class ResolvingSuperTypes {
    private ResolvingSuperTypes() {
    }


    /**Used at building mapping constraints*/
    public static AnaResultPartType resolveTypeMapping(ContextEl _context, String _in, RootBlock _ana,
                                                       int _location) {
        String tr_ = _in.trim();
        AnalyzedPageEl page_ = _context.getAnalyzing();
        if (tr_.isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_ana.getFile().getFileName());
            un_.setIndexFile(_location);
            //_in len
            un_.buildError(_context.getAnalyzing().getAnalysisMessages().getEmptyType());
            _context.getAnalyzing().addLocError(un_);
            _ana.addNameErrors(un_);
            return new AnaResultPartType(page_.getStandards().getAliasObject(),null);
        }
        String void_ = page_.getStandards().getAliasVoid();
        if (StringList.quickEq(tr_, void_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_ana.getFile().getFileName());
            un_.setIndexFile(_location);
            //_in len
            un_.buildError(_context.getAnalyzing().getAnalysisMessages().getVoidType(),
                    void_);
            _context.getAnalyzing().addLocError(un_);
            _ana.addNameErrors(un_);
            return new AnaResultPartType(page_.getStandards().getAliasObject(),null);
        }
        StringMap<Integer> variables_ = new StringMap<Integer>();
        for (RootBlock r: _ana.getSelfAndParentTypes()) {
            for (TypeVar t: r.getParamTypes()) {
                variables_.addEntry(t.getName(),t.getOffset());
            }
        }
        //No need to call Templates.isCorrect
        page_.getAvailableVariables().clear();
        page_.getAvailableVariables().putAllMap(variables_);
        String gl_ = _ana.getGenericString();
        CustList<PartOffset> partOffsets_ = page_.getCurrentParts();
        page_.getCurrentBadIndexes().clear();
        page_.setImportingAcces(new TypeAccessor(_ana.getFullName()));
        page_.setImportingTypes(_ana);
        AnaResultPartType resType_ = AnaPartTypeUtil.processAnalyzeHeader(_in, false,gl_, _context, _ana,_ana, _location,partOffsets_);
        for (InaccessibleType i: page_.getCurrentBadIndexes()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_ana.getFile().getFileName());
            un_.setIndexFile(_location+i.getIndex());
            //part len
            un_.buildError(_context.getAnalyzing().getAnalysisMessages().getInaccessibleType(),
                    i.getType(),gl_);
            _context.getAnalyzing().addLocError(un_);
        }
        if (resType_.getResult().trim().isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_ana.getFile().getFileName());
            un_.setIndexFile(_location);
            //_in len
            un_.buildError(_context.getAnalyzing().getAnalysisMessages().getUnknownType(),
                    _in);
            _context.getAnalyzing().addLocError(un_);
            return new AnaResultPartType(page_.getStandards().getAliasObject(),null);
        }
        return resType_;
    }
    /**Used at building mapping constraints*/
    public static AnaResultPartType resolveTypeInherits(ContextEl _context, String _in, RootBlock _ana,
                                                        int _location, CustList<PartOffset> _partOffsets) {
        AnaResultPartType resType_ = typeArguments(_context, _in, _ana, _location, _partOffsets);
        if (resType_ == null) {
            return new AnaResultPartType(_context.getAnalyzing().getStandards().getAliasObject(),null);
        }
        for (String p:StringExpUtil.getAllTypes(resType_.getResult()).mid(1)){
            if (p.startsWith(Templates.SUB_TYPE)) {
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                call_.setFileName(_ana.getFile().getFileName());
                call_.setIndexFile(_location);
                //_in len
                call_.buildError(_context.getAnalyzing().getAnalysisMessages().getIllegalGenericSuperTypeBound(),
                        p,
                        resType_.getResult());
                _context.getAnalyzing().addLocError(call_);
                _ana.addNameErrors(call_);
            }
            if (p.startsWith(Templates.SUP_TYPE)) {
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                call_.setFileName(_ana.getFile().getFileName());
                call_.setIndexFile(_location);
                //_in len
                call_.buildError(_context.getAnalyzing().getAnalysisMessages().getIllegalGenericSuperTypeBound(),
                        p,
                        resType_.getResult());
                _context.getAnalyzing().addLocError(call_);
                _ana.addNameErrors(call_);
            }
        }
        return resType_;
    }
    public static AnaResultPartType typeArguments(ContextEl _context, String _in, RootBlock _ana,
                                                   int _location, CustList<PartOffset> _partOffsets) {
        AnalyzedPageEl page_ = _context.getAnalyzing();
        String tr_ = _in.trim();
        if (tr_.isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_ana.getFile().getFileName());
            un_.setIndexFile(_location);
            //_in len
            un_.buildError(_context.getAnalyzing().getAnalysisMessages().getEmptyType());
            _context.getAnalyzing().addLocError(un_);
            _ana.addNameErrors(un_);
            return null;
        }
        String void_ = page_.getStandards().getAliasVoid();
        if (StringList.quickEq(tr_, void_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_ana.getFile().getFileName());
            un_.setIndexFile(_location);
            //_in len
            un_.buildError(_context.getAnalyzing().getAnalysisMessages().getVoidType(),
                    void_);
            _context.getAnalyzing().addLocError(un_);
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
        page_.getAvailableVariables().clear();
        page_.getAvailableVariables().putAllMap(variables_);
        String gl_ = _ana.getGenericString();
        RootBlock scope_ = _ana.getParentType();
        page_.getCurrentBadIndexes().clear();
        page_.setImportingAcces(new TypeAccessor(_ana.getFullName()));
        page_.setImportingTypes(_ana);
        AnaResultPartType resType_ = AnaPartTypeUtil.processAnalyzeHeader(_in,true,gl_,_context,scope_,_ana, _location, _partOffsets);
        for (InaccessibleType i: page_.getCurrentBadIndexes()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_ana.getFile().getFileName());
            un_.setIndexFile(_location+i.getIndex());
            //part len
            un_.buildError(_context.getAnalyzing().getAnalysisMessages().getInaccessibleType(),
                    i.getType(),gl_);
            _context.getAnalyzing().addLocError(un_);
        }
        if (resType_.getResult().trim().isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_ana.getFile().getFileName());
            un_.setIndexFile(_location);
            //_in len
            un_.buildError(_context.getAnalyzing().getAnalysisMessages().getUnknownType(),
                    _in);
            _context.getAnalyzing().addLocError(un_);
            return null;
        }
        return resType_;
    }

    public static String resolveBaseInherits(ContextEl _context, String _idSup, RootBlock _ana, StringList _readyTypes) {
        String id_ = StringExpUtil.getIdFromAllTypes(_idSup);
        CustList<PartOffset> partOffsets_ = new CustList<PartOffset>();
        RootBlock scope_ = _ana.getParentType();
        InheritReadyTypes inh_ = new InheritReadyTypes(_readyTypes);
        _context.getAnalyzing().setImportingTypes(_ana);
        _context.getAnalyzing().getMappingLocal().clear();
        _context.getAnalyzing().getMappingLocal().putAllMap(_ana.getMappings());
        return AnaPartTypeUtil.processAnalyzeLineInherits(id_, inh_, _context,scope_,_ana, partOffsets_);
    }
}
