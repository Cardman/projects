package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.ParsedArgument;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.NumberInfos;

public final class ConstantNbOperation extends ConstantOperation {

    private final NumberInfos nbInfos;

    public ConstantNbOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        nbInfos = _op.getNbInfos();
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        int relativeOff_ = getOffset();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _page);
        ParsedArgument parsed_ = parse(_page,nbInfos);
        String argClassName_ = parsed_.getType();
        if (argClassName_.isEmpty()) {
            FoundErrorInterpret badFormat_ = new FoundErrorInterpret();
            badFormat_.setFile(_page.getCurrentFile());
            badFormat_.setIndexFile(_page);
            //constant len
            String originalStr_ = getValue();
            String str_ = originalStr_.trim();
            badFormat_.buildError(_page.getAnalysisMessages().getBadNbFormat(),
                    str_);
            _page.getLocalizer().addError(badFormat_);
            addErr(badFormat_.getBuiltError());
            argClassName_ = _page.getAliasPrimDouble();
        }
        Argument arg_ = new Argument(parsed_.getStruct());
        setSimpleArgument(arg_);
        setResultClass(new AnaClassArgumentMatching(argClassName_));
    }

    private static ParsedArgument parse(AnalyzedPageEl _page, NumberInfos _nbInfos) {
        return ParsedArgument.parse(_nbInfos, _page);
    }

}
