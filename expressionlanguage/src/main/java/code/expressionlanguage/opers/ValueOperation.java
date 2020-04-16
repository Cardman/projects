package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.*;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.CustList;
import code.util.StringList;

public final class ValueOperation extends LeafOperation {
    private int off;
    private String className;
    public ValueOperation(int _indexInEl, int _indexChild, MethodOperation _m, OperationsSequence _op, String _className) {
        super(_indexInEl, _indexChild, _m, _op);
        int relativeOff_ = _op.getOffset();
        String originalStr_ = _op.getValues().getValue(CustList.FIRST_INDEX);
        off = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
        className = _className;
    }

    @Override
    public void analyze(Analyzable _conf) {
        setResultClass(new ClassArgumentMatching(className));
    }

    public int getOff() {
        return off;
    }
}
