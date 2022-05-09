package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.TextBlockInfo;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.structs.StringStruct;

public final class ConstantStrOperation extends ConstantOperation {

    private final TextBlockInfo strInfo;

    public ConstantStrOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        strInfo = _op.getStrInfo();
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        int relativeOff_ = getOffset();
        String originalStr_ = getValue();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _page);
        String stringType_ = _page.getAliasString();
        Argument a_ = new Argument(new StringStruct(originalStr_));
        setSimpleArgument(a_);
        setResultClass(new AnaClassArgumentMatching(stringType_));
        if (strInfo.isKo()) {
            FoundErrorInterpret badFormat_ = new FoundErrorInterpret();
            badFormat_.setFile(_page.getCurrentFile());
            badFormat_.setIndexFile(_page);
            //constant len
            badFormat_.buildError(_page.getAnalysisMessages().getBadCharFormat(),strInfo.getFound());
            _page.getLocalizer().addError(badFormat_);
            addErr(badFormat_.getBuiltError());
        }
    }

}
