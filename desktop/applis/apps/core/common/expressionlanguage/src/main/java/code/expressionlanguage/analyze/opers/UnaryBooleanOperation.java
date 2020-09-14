package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.OperatorConverter;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.stds.LgNames;
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
    public void analyzeUnary(ContextEl _conf) {
        okNum = true;
        AnalyzedPageEl page_ = _conf.getAnalyzing();
        LgNames stds_ = page_.getStandards();
        String booleanPrimType_ = stds_.getAliasPrimBoolean();
        OperationNode child_ = getFirstChild();
        ClassArgumentMatching clMatch_;
        clMatch_ = child_.getResultClass();
        opOffset = getOperations().getOperators().firstKey();
        String oper_ = getOperations().getOperators().firstValue();
        OperatorConverter clId_ = getUnaryOperatorOrMethod(this,clMatch_, oper_, _conf);
        if (clId_ != null) {
            classMethodId = clId_.getSymbol();
            rootNumber = clId_.getRootNumber();
            memberNumber = clId_.getMemberNumber();
            return;
        }
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
        if (!clMatch_.isBoolType(page_)) {
            ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_conf, page_.getStandards().getAliasPrimBoolean(), clMatch_);
            if (res_.isFoundMethod()) {
                ClassMethodId cl_ = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                clMatch_.getImplicits().add(cl_);
                clMatch_.setRootNumber(res_.getRootNumber());
                clMatch_.setMemberNumber(res_.getMemberNumber());
            } else {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
                un_.setFileName(page_.getLocalizer().getCurrentFileName());
                //operator
                un_.buildError(_conf.getAnalyzing().getAnalysisMessages().getUnexpectedOperandTypes(),
                        StringList.join(clMatch_.getNames(),"&"),
                        oper_);
                if (!MethodOperation.isEmptyError(getFirstChild())){
                    getErrs().add(un_.getBuiltError());
                }
                page_.getLocalizer().addError(un_);
            }
        }
        clMatch_.setUnwrapObject(booleanPrimType_);
        child_.cancelArgument();
        setResultClass(new ClassArgumentMatching(booleanPrimType_));
    }

    @Override
    public void quickCalculate(ContextEl _conf) {
        tryGetArg(this,_conf);
    }

    public static void tryGetArg(MethodOperation _par, ContextEl _conf) {
        CustList<OperationNode> chidren_ = _par.getChildrenNodes();
        Argument arg_ = chidren_.first().getArgument();
        Struct value_ = arg_.getStruct();
        if (!(value_ instanceof BooleanStruct)) {
            return;
        }
        BooleanStruct o_ = (BooleanStruct) value_;
        Argument a_ = new Argument(o_.neg());
        _par.setSimpleArgumentAna(a_, _conf);
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
