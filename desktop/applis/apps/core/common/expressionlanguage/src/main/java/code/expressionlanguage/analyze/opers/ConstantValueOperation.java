package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;

public final class ConstantValueOperation extends ConstantOperation {

    private final ConstType constType;

    public ConstantValueOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        constType = _op.getConstType();
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        int relativeOff_ = getOffset();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _page);
        String argClName_;
        if (constType == ConstType.TRUE_CST) {
            argClName_ = _page.getAliasPrimBoolean();
            setSimpleArgument(BooleanStruct.of(true));
            setResultClass(new AnaClassArgumentMatching(argClName_));
            return;
        }
        if (constType == ConstType.FALSE_CST) {
            argClName_ = _page.getAliasPrimBoolean();
            setSimpleArgument(BooleanStruct.of(false));
            setResultClass(new AnaClassArgumentMatching(argClName_));
            return;
        }
        argClName_ = EMPTY_STRING;
        setSimpleArgument(NullStruct.NULL_VALUE);
        setResultClass(new AnaClassArgumentMatching(argClName_));
    }

}
