package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.ResultTernary;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.maths.litteralcom.StrTypes;
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
    void calculateChildren() {
        StrTypes vs_ = getOperations().getValues();
        getChildren().addAllEntries(vs_);
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _page);
        OperationNode opTwo_ = chidren_.first();
        OperationNode opThree_ = chidren_.last();
        opOffset = opThree_.getIndexInEl();
        opOff = getOperations().getOperators().firstKey();
        AnaClassArgumentMatching clMatchTwo_ = opTwo_.getResultClass();
        AnaClassArgumentMatching clMatchThree_ = opThree_.getResultClass();
        StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
        OperationNode current_ = this;
        MethodOperation m_ = getParent();
        while (m_ != null) {
            if (!(m_ instanceof AbstractTernaryOperation)) {
                if (m_ instanceof IdOperation) {
                    current_ = current_.getParent();
                    m_ = m_.getParent();
                    continue;
                }
                break;
            }
            if (m_.getFirstChild() == current_) {
                break;
            }
            current_ = current_.getParent();
            m_ = m_.getParent();
        }
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
