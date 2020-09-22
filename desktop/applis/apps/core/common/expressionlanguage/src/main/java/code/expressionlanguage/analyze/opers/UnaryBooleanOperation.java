package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.OperatorConverter;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.*;

public final class UnaryBooleanOperation extends AbstractUnaryOperation implements SymbolOperation {
    private ClassMethodId classMethodId;
    private int opOffset;
    private boolean okNum;
    private int rootNumber = -1;
    private int memberNumber = -1;

    public UnaryBooleanOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyzeUnary(AnalyzedPageEl _page) {
        okNum = true;
        LgNames stds_ = _page.getStandards();
        String booleanPrimType_ = stds_.getAliasPrimBoolean();
        OperationNode child_ = getFirstChild();
        AnaClassArgumentMatching clMatch_;
        clMatch_ = child_.getResultClass();
        opOffset = getOperations().getOperators().firstKey();
        String oper_ = getOperations().getOperators().firstValue();
        OperatorConverter clId_ = getUnaryOperatorOrMethod(this,child_, oper_, _page);
        if (clId_ != null) {
            classMethodId = clId_.getSymbol();
            rootNumber = clId_.getRootNumber();
            memberNumber = clId_.getMemberNumber();
            return;
        }
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _page);
        if (!clMatch_.isBoolType(_page)) {
            ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_page.getStandards().getAliasPrimBoolean(), clMatch_, _page);
            if (res_.isFoundMethod()) {
                ClassMethodId cl_ = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                clMatch_.getImplicits().add(cl_);
                clMatch_.setRootNumber(res_.getRootNumber());
                clMatch_.setMemberNumber(res_.getMemberNumber());
            } else {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                un_.setFileName(_page.getLocalizer().getCurrentFileName());
                //operator
                un_.buildError(_page.getAnalysisMessages().getUnexpectedOperandTypes(),
                        StringList.join(clMatch_.getNames(),"&"),
                        oper_);
                if (!MethodOperation.isEmptyError(getFirstChild())){
                    getErrs().add(un_.getBuiltError());
                }
                _page.getLocalizer().addError(un_);
            }
        }
        clMatch_.setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
        setResultClass(new AnaClassArgumentMatching(booleanPrimType_,PrimitiveTypes.BOOL_WRAP));
    }


    @Override
    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }
    @Override
    public int getOpOffset() {
        return opOffset;
    }

    @Override
    public boolean isOkNum() {
        return okNum;
    }

    @Override
    public int getRootNumber() {
        return rootNumber;
    }

    @Override
    public int getMemberNumber() {
        return memberNumber;
    }
}
