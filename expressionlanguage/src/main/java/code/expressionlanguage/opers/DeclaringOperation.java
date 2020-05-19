package code.expressionlanguage.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.exec.DeclaringOperable;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.*;

public final class DeclaringOperation extends MethodOperation implements DeclaringOperable {

    public DeclaringOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyzeAssignmentBeforeNextSibling(ContextEl _conf,
            OperationNode _nextSibling, OperationNode _previous) {
        analyzeStdAssignmentBeforeNextSibling(_conf, _nextSibling, _previous);

    }

    @Override
    void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }

    @Override
    public void analyze(ContextEl _conf) {
        setResultClass(new ClassArgumentMatching(EMPTY_STRING));
    }

    @Override
    public void analyzeAssignmentAfter(ContextEl _conf) {
        analyzeStdAssignmentAfter(_conf);
    }

}
