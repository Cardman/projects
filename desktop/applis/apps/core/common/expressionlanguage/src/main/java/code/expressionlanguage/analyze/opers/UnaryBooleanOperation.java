package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.ClassMethodIdMemberIdTypeFct;
import code.expressionlanguage.analyze.opers.util.OperatorConverter;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.util.core.StringUtil;

public final class UnaryBooleanOperation extends AbstractUnaryOperation implements SymbolOperation {
    private final ClassMethodIdMemberIdTypeFct fct = new ClassMethodIdMemberIdTypeFct();
    private int opOffset;
    private boolean okNum;

    public UnaryBooleanOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyzeUnary(AnalyzedPageEl _page) {
        okNum = true;
        String booleanPrimType_ = _page.getAliasPrimBoolean();
        OperationNode child_ = getFirstChild();
        AnaClassArgumentMatching clMatch_;
        clMatch_ = child_.getResultClass();
        opOffset = getOperators().firstKey();
        String oper_ = getOperators().firstValue();
        OperatorConverter clId_ = getUnaryOperatorOrMethod(this,child_, oper_, _page);
        if (clId_ != null) {
            fct.infos(clId_,_page);
            return;
        }
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+opOffset, _page);
        if (!clMatch_.isBoolType(_page)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_page);
            un_.setFile(_page.getCurrentFile());
            //operator
            un_.buildError(_page.getAnalysisMessages().getUnexpectedOperandTypes(),
                    StringUtil.join(clMatch_.getNames(), ExportCst.JOIN_TYPES),
                    oper_);
            if (!MethodOperation.isEmptyError(getFirstChild())){
                addErr(un_.getBuiltError());
            }
            _page.getLocalizer().addError(un_);
        }
        clMatch_.setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
        setResultClass(new AnaClassArgumentMatching(booleanPrimType_,PrimitiveTypes.BOOL_WRAP));
    }

    public ClassMethodIdMemberIdTypeFct getFct() {
        return fct;
    }

    @Override
    public int getOpOffset() {
        return opOffset;
    }

    @Override
    public boolean isOkNum() {
        return okNum;
    }

}
