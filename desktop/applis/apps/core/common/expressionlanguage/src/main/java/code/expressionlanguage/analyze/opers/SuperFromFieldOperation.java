package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.linkage.ExportCst;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class SuperFromFieldOperation extends AbsChoiceFieldOperation {

    public SuperFromFieldOperation(OperationsSequence _op) {
        super(_op);
    }

    @Override
    public AnaClassArgumentMatching getFrom(AnalyzedPageEl _page,SettableAbstractFieldOperation _settable) {
        String className_ = className(_page);
        AnaClassArgumentMatching clCur_;
        if (!_settable.isIntermediateDottedOperation()) {
            clCur_ = new AnaClassArgumentMatching(_page.getGlobalClass());
        } else {
            clCur_ = _settable.getPreviousResultClass();
        }
        Mapping map_ = new Mapping();
        map_.setParam(className_);
        map_.setArg(clCur_);
        StringMap<StringList> mapping_ = _page.getCurrentConstraints().getCurrentConstraints();
        map_.setMapping(mapping_);
        if (!AnaInherits.isCorrectOrNumbers(map_, _page)) {
            error(_page, _settable, className_, clCur_);
            _settable.setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return null;
        }
        return new AnaClassArgumentMatching(className_);
    }

    private void error(AnalyzedPageEl _page, SettableAbstractFieldOperation _settable, String _clName, AnaClassArgumentMatching _result) {
        FoundErrorInterpret cast_ = new FoundErrorInterpret();
        cast_.setIndexFile(_page);
        cast_.setFile(_page.getCurrentFile());
        //type len
        cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                StringUtil.join(_result.getNames(), ExportCst.JOIN_TYPES),
                _clName);
        _page.getLocalizer().addError(cast_);
        _settable.addErr(cast_.getBuiltError());
    }

    @Override
    public boolean isSuperAccess() {
        return true;
    }

}
