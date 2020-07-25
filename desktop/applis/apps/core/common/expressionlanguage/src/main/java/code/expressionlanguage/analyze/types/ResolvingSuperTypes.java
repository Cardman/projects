package code.expressionlanguage.analyze.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.accessing.TypeAccessor;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
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
    public static AnaResultPartType resolveTypeMapping(ContextEl _context, String _in, RootBlock _ana, ExecRootBlock _currentBlock,
                                                       int _location) {
        String tr_ = _in.trim();
        if (tr_.isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_currentBlock.getFile().getFileName());
            un_.setIndexFile(_location);
            //_in len
            un_.buildError(_context.getAnalysisMessages().getEmptyType());
            _context.addError(un_);
            _ana.addNameErrors(un_);
            return new AnaResultPartType(_context.getStandards().getAliasObject(),null);
        }
        String void_ = _context.getStandards().getAliasVoid();
        if (StringList.quickEq(tr_, void_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_currentBlock.getFile().getFileName());
            un_.setIndexFile(_location);
            //_in len
            un_.buildError(_context.getAnalysisMessages().getVoidType(),
                    void_);
            _context.addError(un_);
            _ana.addNameErrors(un_);
            return new AnaResultPartType(_context.getStandards().getAliasObject(),null);
        }
        StringMap<Integer> variables_ = new StringMap<Integer>();
        for (RootBlock r: _ana.getSelfAndParentTypes()) {
            for (TypeVar t: r.getParamTypes()) {
                variables_.addEntry(t.getName(),t.getOffset());
            }
        }
        //No need to call Templates.isCorrect
        _context.getAnalyzing().getAvailableVariables().clear();
        _context.getAnalyzing().getAvailableVariables().putAllMap(variables_);
        String gl_ = _currentBlock.getGenericString();
        CustList<PartOffset> partOffsets_ = _context.getAnalyzing().getCurrentParts();
        _context.getAnalyzing().getCurrentBadIndexes().clear();
        _context.getAnalyzing().setImportingAcces(new TypeAccessor(_currentBlock.getFullName()));
        _context.getAnalyzing().setImportingTypes(_ana);
        AnaResultPartType resType_ = AnaPartTypeUtil.processAnalyzeHeader(_in, false,gl_, _context, _ana,_ana, _location,partOffsets_);
        for (InaccessibleType i: _context.getAnalyzing().getCurrentBadIndexes()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_currentBlock.getFile().getFileName());
            un_.setIndexFile(_location+i.getIndex());
            //part len
            un_.buildError(_context.getAnalysisMessages().getInaccessibleType(),
                    i.getType(),gl_);
            _context.addError(un_);
        }
        if (resType_.getResult().trim().isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_currentBlock.getFile().getFileName());
            un_.setIndexFile(_location);
            //_in len
            un_.buildError(_context.getAnalysisMessages().getUnknownType(),
                    _in);
            _context.addError(un_);
            return new AnaResultPartType(_context.getStandards().getAliasObject(),null);
        }
        return resType_;
    }
    /**Used at building mapping constraints*/
    public static AnaResultPartType resolveTypeInherits(ContextEl _context, String _in, RootBlock _ana, ExecRootBlock _currentBlock,
                                                        int _location, CustList<PartOffset> _partOffsets) {
        String tr_ = _in.trim();
        if (tr_.isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_currentBlock.getFile().getFileName());
            un_.setIndexFile(_location);
            //_in len
            un_.buildError(_context.getAnalysisMessages().getEmptyType());
            _context.addError(un_);
            _ana.addNameErrors(un_);
            return new AnaResultPartType(_context.getStandards().getAliasObject(),null);
        }
        String void_ = _context.getStandards().getAliasVoid();
        if (StringList.quickEq(tr_, void_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_currentBlock.getFile().getFileName());
            un_.setIndexFile(_location);
            //_in len
            un_.buildError(_context.getAnalysisMessages().getVoidType(),
                    void_);
            _context.addError(un_);
            _ana.addNameErrors(un_);
            return new AnaResultPartType(_context.getStandards().getAliasObject(),null);
        }
        StringMap<Integer> variables_ = new StringMap<Integer>();
        for (RootBlock r: _ana.getSelfAndParentTypes()) {
            for (TypeVar t: r.getParamTypes()) {
                variables_.addEntry(t.getName(),t.getOffset());
            }
        }
        //No need to call Templates.isCorrect
        _context.getAnalyzing().getAvailableVariables().clear();
        _context.getAnalyzing().getAvailableVariables().putAllMap(variables_);
        String gl_ = _currentBlock.getGenericString();
        RootBlock scope_ = _ana.getParentType();
        _context.getAnalyzing().getCurrentBadIndexes().clear();
        _context.getAnalyzing().setImportingAcces(new TypeAccessor(_ana.getFullName()));
        _context.getAnalyzing().setImportingTypes(_ana);
        AnaResultPartType resType_ = AnaPartTypeUtil.processAnalyzeHeader(_in,true,gl_,_context,scope_,_ana, _location, _partOffsets);
        for (InaccessibleType i: _context.getAnalyzing().getCurrentBadIndexes()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_currentBlock.getFile().getFileName());
            un_.setIndexFile(_location+i.getIndex());
            //part len
            un_.buildError(_context.getAnalysisMessages().getInaccessibleType(),
                    i.getType(),gl_);
            _context.addError(un_);
        }
        if (resType_.getResult().trim().isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_currentBlock.getFile().getFileName());
            un_.setIndexFile(_location);
            //_in len
            un_.buildError(_context.getAnalysisMessages().getUnknownType(),
                    _in);
            _context.addError(un_);
            return new AnaResultPartType(_context.getStandards().getAliasObject(),null);
        }
        for (String p:StringExpUtil.getAllTypes(resType_.getResult()).mid(1)){
            if (p.startsWith(Templates.SUB_TYPE)) {
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                call_.setFileName(_currentBlock.getFile().getFileName());
                call_.setIndexFile(_location);
                //_in len
                call_.buildError(_context.getAnalysisMessages().getIllegalGenericSuperTypeBound(),
                        p,
                        resType_.getResult());
                _context.addError(call_);
                _ana.addNameErrors(call_);
            }
            if (p.startsWith(Templates.SUP_TYPE)) {
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                call_.setFileName(_currentBlock.getFile().getFileName());
                call_.setIndexFile(_location);
                //_in len
                call_.buildError(_context.getAnalysisMessages().getIllegalGenericSuperTypeBound(),
                        p,
                        resType_.getResult());
                _context.addError(call_);
                _ana.addNameErrors(call_);
            }
        }
        return resType_;
    }

    public static String resolveBaseInherits(ContextEl _context, String _idSup, RootBlock _ana, StringList _readyTypes) {
        String id_ = StringExpUtil.getIdFromAllTypes(_idSup);
        CustList<PartOffset> partOffsets_ = new CustList<PartOffset>();
        RootBlock scope_ = _ana.getParentType();
        InheritReadyTypes inh_ = new InheritReadyTypes(_readyTypes);
        _context.getAnalyzing().setImportingTypes(_ana);
        return AnaPartTypeUtil.processAnalyzeLineInherits(id_, inh_, _context,scope_,_ana, partOffsets_);
    }
}
