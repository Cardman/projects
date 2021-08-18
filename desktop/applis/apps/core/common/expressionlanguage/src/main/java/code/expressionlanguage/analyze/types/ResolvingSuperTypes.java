package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.accessing.TypeAccessor;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.util.TypeVar;
import code.util.StringList;
import code.util.StringMap;

public final class ResolvingSuperTypes {
    private ResolvingSuperTypes() {
    }


    private static void initVariables(RootBlock _ana, AnalyzedPageEl _page) {
        StringMap<Integer> variables_ = new StringMap<Integer>();
        for (RootBlock r : _ana.getSelfAndParentTypes()) {
            for (TypeVar t : r.getParamTypes()) {
                variables_.addEntry(t.getName(), t.getOffset());
            }
        }
        //No need to call Templates.isCorrect
        _page.getAvailableVariables().clear();
        _page.getAvailableVariables().putAllMap(variables_);
    }

    public static void loopWildCards(RootBlock _ana, int _location, AnalyzedPageEl _page, AnaResultPartType _resType) {
        for (String p:StringExpUtil.getWildCards(_resType.getResult())){
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_ana.getFile().getFileName());
            call_.setIndexFile(_location);
            //_in len
            call_.buildError(_page.getAnalysisMessages().getIllegalGenericSuperTypeBound(),
                    p,
                    _resType.getResult());
            _page.addLocError(call_);
            _ana.addNameErrors(call_);
        }
    }

    public static AnaResultPartType processAnalyzeHeader(String _in, RootBlock _ana, int _location, AnalyzedPageEl _page, boolean _rootName) {
        String tr_ = _in.trim();
        if (tr_.isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_ana.getFile().getFileName());
            un_.setIndexFile(_location);
            //_in len
            un_.buildError(_page.getAnalysisMessages().getEmptyType());
            _page.addLocError(un_);
            _ana.addNameErrors(un_);
            return new AnaResultPartType(_in,_location, _page.getAnalysisMessages(), _ana);
        }
        initVariables(_ana, _page);
        String gl_ = _ana.getGenericString();
        AccessedBlock scope_ = _ana.getParentType();
        if (scope_ == null) {
            scope_ = _ana.getOperator();
        }
        _page.getCurrentBadIndexes().clear();
        _page.setImportingAcces(new TypeAccessor(_ana.getFullName()));
        _page.setImportingTypes(_ana);
        AnaResultPartType resType_ = AnaPartTypeUtil.processAnalyzeHeader(_in, _rootName, gl_, scope_, _ana, _location, _page);
        for (InaccessibleType i : _page.getCurrentBadIndexes()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_ana.getFile().getFileName());
            un_.setIndexFile(_location + i.getIndex());
            //part len
            un_.buildError(_page.getAnalysisMessages().getInaccessibleType(),
                    i.getType(), gl_);
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
//            return null;
        }
        return resType_;
    }

    public static String resolveBaseInherits(String _idSup, RootBlock _ana, StringList _readyTypes, AnalyzedPageEl _page) {
        String id_ = StringExpUtil.getIdFromAllTypes(_idSup);
        AccessedBlock scope_ = _ana.getParentType();
        if (scope_ == null) {
            scope_= _ana.getOperator();
        }
        InheritReadyTypes inh_ = new InheritReadyTypes(_readyTypes);
        _page.setImportingTypes(_ana);
        _page.getMappingLocal().clear();
        _page.getMappingLocal().putAllMap(_ana.getMappings());
        return AnaPartTypeUtil.processAnalyzeLineInherits(id_, inh_, scope_,_ana, _page);
    }
}
