package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.TextBlockInfo;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.structs.CharStruct;

public final class ConstantCharOperation extends ConstantOperation {

    private final TextBlockInfo strInfo;

    public ConstantCharOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        strInfo = _op.getStrInfo();
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        int relativeOff_ = getOffset();
        String originalStr_ = getValue();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _page);
        String argClName_;
        argClName_ = _page.getAliasPrimChar();
        Argument a_;
        if (strInfo.isKo() || originalStr_.isEmpty()) {
            a_ = new Argument();
            FoundErrorInterpret badFormat_ = new FoundErrorInterpret();
            badFormat_.setFile(_page.getCurrentFile());
            badFormat_.setIndexFile(_page);
            //constant len
            badFormat_.buildError(_page.getAnalysisMessages().getBadCharFormat(),strInfo.getFound());
            _page.getLocalizer().addError(badFormat_);
            addErr(badFormat_.getBuiltError());
        } else {
            a_ = new Argument(new CharStruct(originalStr_.charAt(0)));
        }
        setSimpleArgument(a_);
        setResultClass(new AnaClassArgumentMatching(argClName_));
    }

}
