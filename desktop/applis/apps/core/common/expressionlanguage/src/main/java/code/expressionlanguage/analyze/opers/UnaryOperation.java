package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.ClassMethodIdMemberIdTypeFct;
import code.expressionlanguage.analyze.opers.util.OperatorConverter;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.fwd.opers.AnaOperatorContent;
import code.expressionlanguage.structs.ByteStruct;
import code.expressionlanguage.structs.ShortStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class UnaryOperation extends AbstractUnaryOperation implements SymbolOperation {
    private final ClassMethodIdMemberIdTypeFct fct = new ClassMethodIdMemberIdTypeFct();
    private final AnaOperatorContent operatorContent;
    private boolean okNum;

    public UnaryOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        operatorContent = new AnaOperatorContent();
        operatorContent.setOper(getOperators().firstValue());
        operatorContent.setOpOffset(getOperators().firstKey());
    }

    @Override
    public void analyzeUnary(AnalyzedPageEl _page) {
        okNum = true;
        OperationNode child_ = getFirstChild();
        String oper_ = getOperators().firstValue();
        if (AnaTypeUtil.isPureNumberClass(child_.getResultClass(), _page)) {
            unaryNum(_page);
            return;
        }
        AnaClassArgumentMatching operand_ = child_.getResultClass();
        CustList<OperationNode> single_ = new CustList<OperationNode>(child_);
        OperatorConverter clId_ = operUse(_page, oper_, operand_, single_, groupUnNum(_page));
        if (clId_ != null) {
            fct.infos(clId_,_page);
            return;
        }
        unaryNum(_page);
    }

    private void unaryNum(AnalyzedPageEl _page) {
        OperationNode child_ = getFirstChild();
        AnaClassArgumentMatching clMatch_ = child_.getResultClass();
        AnaClassArgumentMatching cl_ = AnaTypeUtil.toPrimitive(clMatch_, _page);
        if (child_ instanceof ConstantOperation) {
            Argument arg_ = child_.getArgument();
            Struct instance_ = arg_.getStruct();
            if (instance_ instanceof ByteStruct) {
                clMatch_.setUnwrapObject(cl_, _page.getPrimitiveTypes());
                setResultClass(AnaClassArgumentMatching.copy(cl_, _page.getPrimitiveTypes()));
                return;
            }
            if (instance_ instanceof ShortStruct) {
                clMatch_.setUnwrapObject(cl_, _page.getPrimitiveTypes());
                setResultClass(AnaClassArgumentMatching.copy(cl_, _page.getPrimitiveTypes()));
                return;
            }
        }
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+operatorContent.getOpOffset(), _page);
        if (!AnaTypeUtil.isPureNumberClass(clMatch_, _page)) {
            errSymbol(_page);
            setResultClass(new AnaClassArgumentMatching(_page.getAliasNumber()));
            return;
        }
        if (AnaTypeUtil.isIntOrderClass(cl_, _page)) {
            int res_ = AnaTypeUtil.getIntOrderClass(cl_, _page);
            cl_ = NumericOperation.goToAtLeastInt(_page,cl_,res_);
        }
        clMatch_.setUnwrapObject(cl_, _page.getPrimitiveTypes());
        setResultClass(AnaClassArgumentMatching.copy(cl_, _page.getPrimitiveTypes()));
    }

    public ClassMethodIdMemberIdTypeFct getFct() {
        return fct;
    }

    @Override
    public AnaOperatorContent getOperatorContent() {
        return operatorContent;
    }

    public String getOper() {
        return operatorContent.getOper();
    }

    @Override
    public boolean isOkNum() {
        return okNum;
    }

}
