package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.ResultTernary;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class NullSafeOperation extends MethodOperation {

    private int opOffset;
    private int opOff;

    public NullSafeOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _page);
        OperationNode opTwo_ = chidren_.first();
        OperationNode opThree_ = chidren_.last();
        opOffset = opThree_.getIndexInEl();
        opOff = getOperators().firstKey();
        AnaClassArgumentMatching clMatchTwo_ = opTwo_.getResultClass();
        AnaClassArgumentMatching clMatchThree_ = opThree_.getResultClass();
        StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
        MethodOperation m_ = AbstractTernaryOperation.retrieveAncestor(this);
        String type_ = EMPTY_STRING;
        if (m_ instanceof CastOperation) {
            CastOperation c_ = (CastOperation) m_;
            type_ = c_.getClassName();
        }
        if (!type_.isEmpty()) {
            setResultClass(new AnaClassArgumentMatching(type_));
            return;
        }
        StringList one_ = clMatchTwo_.getNames();
        StringList two_ = clMatchThree_.getNames();
        ResultTernary res_ = ResultTernary.getResultTernary(one_, null, two_, null, vars_, _page);
        setResultClass(new AnaClassArgumentMatching(res_.getTypes()));
    }

    public int getOpOffset() {
        return opOffset;
    }

    public int getOpOff() {
        return opOff;
    }
}
