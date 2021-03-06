package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.util.core.IndexConstants;

public final class AnaOperationContent {

    private static final String EMPTY_STRING = "";
    private final int indexInEl;
    private final int indexChild;
    private AnaClassArgumentMatching resultClass;
    private final int indexBegin;
    private Argument argument;
    private int order = IndexConstants.INDEX_NOT_FOUND_ELT;

    public AnaOperationContent(int _indexInEl, int _indexChild, OperationsSequence _op) {
        indexInEl = _indexInEl;
        indexBegin = _op.getDelimiter().getIndexBegin();
        indexChild = _indexChild;
        resultClass = new AnaClassArgumentMatching(EMPTY_STRING);
    }

    public int getIndexInEl() {
        return indexInEl;
    }

    public int getIndexChild() {
        return indexChild;
    }

    public AnaClassArgumentMatching getResultClass() {
        return resultClass;
    }

    public void setResultClass(AnaClassArgumentMatching _resultClass) {
        this.resultClass = _resultClass;
    }

    public int getIndexBegin() {
        return indexBegin;
    }

    public Argument getArgument() {
        return argument;
    }

    public void setArgument(Argument _argument) {
        this.argument = _argument;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int _order) {
        this.order = _order;
    }
}
