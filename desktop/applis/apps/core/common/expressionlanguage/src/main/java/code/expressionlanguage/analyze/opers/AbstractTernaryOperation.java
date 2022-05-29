package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.ResultTernary;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;

public abstract class AbstractTernaryOperation extends AbstractComTernaryOperation {


    protected AbstractTernaryOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public final void analyze(AnalyzedPageEl _page) {
        analyzeTernary(_page);
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode opTwo_ = chidren_.get(IndexConstants.SECOND_INDEX);
        OperationNode opThree_ = chidren_.last();
        AnaClassArgumentMatching clMatchTwo_ = opTwo_.getResultClass();
        AnaClassArgumentMatching clMatchThree_ = opThree_.getResultClass();
        StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
        MethodOperation m_ = retrieveAncestor(this);
        String type_ = EMPTY_STRING;
        if (m_ instanceof CastOperation) {
            CastOperation c_ = (CastOperation) m_;
            type_ = c_.getClassName();
        }
        if (!type_.isEmpty()) {
            if (AnaTypeUtil.isPrimitive(type_, _page)) {
                opTwo_.getResultClass().setUnwrapObject(type_, _page.getPrimitiveTypes());
                opThree_.getResultClass().setUnwrapObject(type_, _page.getPrimitiveTypes());
            }
            setResultClass(new AnaClassArgumentMatching(type_));
            return;
        }
        StringList one_ = clMatchTwo_.getNames();
        StringList two_ = clMatchThree_.getNames();
        ResultTernary res_ = ResultTernary.getResultTernary(one_, null, two_, null, vars_, _page);
        if (res_.isUnwrapFirst()) {
            opTwo_.getResultClass().setUnwrapObjectNb(res_.getCastPrim());
        }
        if (res_.isUnwrapSecond()) {
            opThree_.getResultClass().setUnwrapObjectNb(res_.getCastPrim());
        }
        setResultClass(new AnaClassArgumentMatching(res_.getTypes()));
    }

    static MethodOperation retrieveAncestor(OperationNode _current) {
        OperationNode current_ = _current;
        MethodOperation m_ = _current.getParent();
        while (m_ != null) {
            if (!(m_ instanceof AbstractTernaryOperation) && !(m_ instanceof IdOperation) || m_ instanceof AbstractTernaryOperation && m_.getFirstChild() == current_) {
                break;
            }
            current_ = current_.getParent();
            m_ = m_.getParent();
        }
        return m_;
    }

}
