package code.expressionlanguage.inherits;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.analyze.util.TypeVar;
import code.expressionlanguage.types.InheritReadyTypes;
import code.expressionlanguage.types.PartTypeUtil;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class ResolvingSuperTypes {
    private ResolvingSuperTypes() {
    }


    /**Used at building mapping constraints*/
    public static String resolveTypeMapping(ContextEl _context,String _in, RootBlock _currentBlock,
                                     int _location) {
        String void_ = _context.getStandards().getAliasVoid();
        if (StringList.quickEq(_in.trim(), void_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_currentBlock.getFile().getFileName());
            un_.setIndexFile(_location);
            //_in len
            un_.buildError(_context.getAnalysisMessages().getVoidType(),
                    void_);
            _context.addError(un_);
            return _context.getStandards().getAliasObject();
        }
        StringMap<Integer> variables_ = new StringMap<Integer>();
        for (RootBlock r: _currentBlock.getSelfAndParentTypes()) {
            for (TypeVar t: r.getParamTypes()) {
                variables_.addEntry(t.getName(),t.getOffset());
            }
        }
        //No need to call Templates.isCorrect
        _context.getAnalyzing().getAvailableVariables().clear();
        _context.getAnalyzing().getAvailableVariables().putAllMap(variables_);
        String gl_ = _currentBlock.getGenericString();
        CustList<PartOffset> partOffsets_ = _context.getCoverage().getCurrentParts();
        _context.getAnalyzing().getCurrentBadIndexes().clear();
        String resType_ = PartTypeUtil.processAnalyze(_in, false,gl_, _context, _currentBlock,_currentBlock, _location,partOffsets_);
        for (int i: _context.getAnalyzing().getCurrentBadIndexes()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_currentBlock.getFile().getFileName());
            un_.setIndexFile(_location+i);
            //part len
            un_.buildError(_context.getAnalysisMessages().getInaccessibleType(),
                    resType_,gl_);
            _context.addError(un_);
        }
        if (resType_.trim().isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_currentBlock.getFile().getFileName());
            un_.setIndexFile(_location);
            //_in len
            un_.buildError(_context.getAnalysisMessages().getUnknownType(),
                    _in);
            _context.addError(un_);
            return _context.getStandards().getAliasObject();
        }
        return resType_;
    }
    /**Used at building mapping constraints*/
    public static String resolveTypeInherits(ContextEl _context, String _in, RootBlock _currentBlock,
                                      int _location) {
        StringMap<Integer> variables_ = new StringMap<Integer>();
        for (RootBlock r: _currentBlock.getSelfAndParentTypes()) {
            for (TypeVar t: r.getParamTypes()) {
                variables_.addEntry(t.getName(),t.getOffset());
            }
        }
        //No need to call Templates.isCorrect
        _context.getAnalyzing().getAvailableVariables().clear();
        _context.getAnalyzing().getAvailableVariables().putAllMap(variables_);
        String gl_ = _currentBlock.getGenericString();
        CustList<PartOffset> partOffsets_ = _currentBlock.getSuperTypesParts();
        RootBlock scope_ = _currentBlock.getParentType();
        _context.getAnalyzing().getCurrentBadIndexes().clear();
        String resType_ = PartTypeUtil.processAnalyze(_in,true,gl_,_context,scope_,_currentBlock, _location,partOffsets_);
        for (int i: _context.getAnalyzing().getCurrentBadIndexes()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_currentBlock.getFile().getFileName());
            un_.setIndexFile(_location+i);
            //part len
            un_.buildError(_context.getAnalysisMessages().getInaccessibleType(),
                    resType_,gl_);
            _context.addError(un_);
        }
        if (resType_.trim().isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_currentBlock.getFile().getFileName());
            un_.setIndexFile(_location);
            //_in len
            un_.buildError(_context.getAnalysisMessages().getUnknownType(),
                    _in);
            _context.addError(un_);
            return _context.getStandards().getAliasObject();
        }
        for (String p:Templates.getAllTypes(resType_).mid(1)){
            if (p.startsWith(Templates.SUB_TYPE)) {
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                call_.setFileName(_currentBlock.getFile().getFileName());
                call_.setIndexFile(_location);
                //_in len
                call_.buildError(_context.getAnalysisMessages().getIllegalGenericSuperTypeBound(),
                        p,
                        resType_);
                _context.addError(call_);
            }
            if (p.startsWith(Templates.SUP_TYPE)) {
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                call_.setFileName(_currentBlock.getFile().getFileName());
                call_.setIndexFile(_location);
                //_in len
                call_.buildError(_context.getAnalysisMessages().getIllegalGenericSuperTypeBound(),
                        p,
                        resType_);
                _context.addError(call_);
            }
        }
        return resType_;
    }

    public static String resolveBaseInherits(ContextEl _context, String _idSup, RootBlock _root, StringList _readyTypes) {
        String id_ = Templates.getIdFromAllTypes(_idSup);
        CustList<PartOffset> partOffsets_ = new CustList<PartOffset>();
        RootBlock scope_ = _root.getParentType();
        InheritReadyTypes inh_ = new InheritReadyTypes(_readyTypes);
        return PartTypeUtil.processAnalyzeLineInherits(id_, inh_,true, _context,scope_,_root, -1,partOffsets_);
    }
}
