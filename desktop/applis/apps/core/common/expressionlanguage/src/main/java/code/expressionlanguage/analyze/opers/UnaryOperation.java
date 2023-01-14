package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.SymbolFactoryUtil;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.ClassMethodIdMemberIdTypeFct;
import code.expressionlanguage.analyze.opers.util.OperatorConverter;
import code.expressionlanguage.analyze.opers.util.ResultOperand;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.common.symbol.CommonOperSymbol;
import code.expressionlanguage.fwd.opers.AnaOperatorContent;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.expressionlanguage.structs.ByteStruct;
import code.expressionlanguage.structs.ShortStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.core.StringUtil;

public final class UnaryOperation extends AbstractUnaryOperation implements SymbolOperation {
    private final ClassMethodIdMemberIdTypeFct fct = new ClassMethodIdMemberIdTypeFct();
    private final AnaOperatorContent operatorContent;
    private boolean okNum;
    private boolean pureBoolResult;

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
        ResultOperand resOp_ = SymbolFactoryUtil.generateOperand(oper_, child_.getResultClass(), _page);
        AnaClassArgumentMatching rOp_ = resOp_.getResult();
        OperatorConverter clId_ = null;
        if (rOp_.getSingleNameOrEmpty().isEmpty()) {
            AnaClassArgumentMatching operand_ = child_.getResultClass();
            CustList<OperationNode> single_ = new CustList<OperationNode>(child_);
            clId_ = operUse(_page, oper_, operand_, single_, SymbolFactoryUtil.unaries(oper_,_page));
        }
        pureBoolResult = StringUtil.quickEq("!",oper_);
        fct.setSymbol(resOp_.getSymbol());
        if (clId_ != null) {
            fct.infos(clId_);
            return;
        }
        if (cst(_page)) {
            return;
        }
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+operatorContent.getOpOffset(), _page);
        if (rOp_.getSingleNameOrEmpty().isEmpty()) {
            errSymbol(_page);
            if (StringUtil.quickEq("!",oper_)) {
                setResultClass(new AnaClassArgumentMatching(_page.getAliasPrimBoolean(), PrimitiveTypes.BOOL_WRAP));
            }
            setResultClass(new AnaClassArgumentMatching(_page.getAliasNumber()));
            return;
        }
        setResultClass(AnaClassArgumentMatching.copy(rOp_, _page.getPrimitiveTypes()));
    }
    private boolean cst(AnalyzedPageEl _page) {
        OperationNode child_ = getFirstChild();
        AnaClassArgumentMatching clMatch_ = child_.getResultClass();
        AnaClassArgumentMatching cl_ = AnaTypeUtil.toPrimitive(clMatch_, _page);
        if (child_ instanceof ConstantOperation) {
            Argument arg_ = child_.getArgument();
            Struct instance_ = arg_.getStruct();
            if (instance_ instanceof ByteStruct) {
                clMatch_.setUnwrapObject(cl_, _page.getPrimitiveTypes());
                setResultClass(AnaClassArgumentMatching.copy(cl_, _page.getPrimitiveTypes()));
                return true;
            }
            if (instance_ instanceof ShortStruct) {
                clMatch_.setUnwrapObject(cl_, _page.getPrimitiveTypes());
                setResultClass(AnaClassArgumentMatching.copy(cl_, _page.getPrimitiveTypes()));
                return true;
            }
        }
        return false;
    }

    public boolean isPureBoolResult() {
        return pureBoolResult;
    }

    public CommonOperSymbol getSymbol() {
        return fct.getSymbol();
    }

    public ClassMethodIdMemberIdTypeFct getFct() {
        return fct;
    }

    @Override
    public AnaOperatorContent getOperatorContent() {
        return operatorContent;
    }

    @Override
    public boolean isOkNum() {
        return okNum;
    }

}
