package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.ClassMethodIdMemberIdTypeFct;
import code.expressionlanguage.analyze.opers.util.OperatorConverter;
import code.expressionlanguage.analyze.opers.util.ResultOperand;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;

public abstract class NumericOperation extends MethodOperation implements MiddleSymbolOperation {
    private final ClassMethodIdMemberIdTypeFct fct = new ClassMethodIdMemberIdTypeFct();
    private final String op;
    private final int opOffset;
    private boolean okNum;

    protected NumericOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        op = _op.getOperators().firstValue();
        opOffset = _op.getOperators().firstKey();
    }

    static AnaClassArgumentMatching getIntResultClass(AnaClassArgumentMatching _a, AnaClassArgumentMatching _b, AnalyzedPageEl _page) {
        int oa_ = AnaTypeUtil.getIntOrderClass(_a, _page);
        int ob_ = AnaTypeUtil.getIntOrderClass(_b, _page);
        int max_ = Math.max(oa_, ob_);
        AnaClassArgumentMatching arg_ = getMaxWrap(_a, oa_, _b, ob_);
        int intOrder_ = AnaTypeUtil.getIntOrderClass(_page.getAliasPrimInteger(), _page);
        if (max_ < intOrder_) {
            arg_ = new AnaClassArgumentMatching(_page.getAliasPrimInteger(),PrimitiveTypes.INT_WRAP);
        }
        return AnaTypeUtil.toPrimitive(arg_, _page);
    }
    static AnaClassArgumentMatching getFloatResultClass(AnaClassArgumentMatching _a, AnaClassArgumentMatching _b, AnalyzedPageEl _page) {
        int oa_ = AnaTypeUtil.getFloatOrderClass(_a, _page);
        int ob_ = AnaTypeUtil.getFloatOrderClass(_b, _page);
        AnaClassArgumentMatching arg_ = getMaxWrap(_a, oa_, _b, ob_);
        return AnaTypeUtil.toPrimitive(arg_, _page);
    }

    private static AnaClassArgumentMatching getMaxWrap(AnaClassArgumentMatching _a, int _oa, AnaClassArgumentMatching _b, int _ob) {
        AnaClassArgumentMatching arg_;
        if (_oa > _ob) {
            arg_ = _a;
        } else {
            arg_ = _b;
        }
        return arg_;
    }

    @Override
    public final void analyze(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode l_ = chidren_.first();
        StrTypes ops_ = getOperators();
        OperationNode r_ = chidren_.last();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ops_.firstKey(), _page);
        okNum = true;
        String oper_ = ops_.firstValue();
        OperatorConverter res_ = null;
        if ( StringExpUtil.isBinNum(oper_) && !binNum(oper_,l_.getResultClass(), r_.getResultClass(), _page)){
            res_ = CompoundAffectationOperation.tryGetStd(_page, oper_, this, groupBinNum(_page));
        } else if (StringExpUtil.isBitwise(oper_)&&!bitwise(l_.getResultClass(), r_.getResultClass(), _page)) {
            res_ = CompoundAffectationOperation.tryGetStd(_page, oper_, this, groupBinBitwise(_page));
        } else if (StringExpUtil.isShiftOper(oper_)&&!AnaTypeUtil.isIntOrderClass(l_.getResultClass(), r_.getResultClass(), _page)) {
            res_ = CompoundAffectationOperation.tryGetStd(_page, oper_, this, groupBinShift(_page));
        }
        if (res_ != null) {
            fct.infos(res_,_page);
            return;
        }
        natOp(_page);
    }

    private void natOp(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode l_ = chidren_.first();
        AnaClassArgumentMatching a_ = l_.getResultClass();
        StrTypes ops_ = getOperators();
        OperationNode r_ = chidren_.last();
        AnaClassArgumentMatching c_ = r_.getResultClass();
        ResultOperand res_ = analyzeOper(a_, ops_.firstValue(), c_, _page);
        okNum = _page.isOkNumOp();
        setResultClass(AnaClassArgumentMatching.copy(res_.getResult(), _page.getPrimitiveTypes()));
    }

    abstract ResultOperand analyzeOper(AnaClassArgumentMatching _a, String _op, AnaClassArgumentMatching _b, AnalyzedPageEl _page);

    public ClassMethodIdMemberIdTypeFct getFct() {
        return fct;
    }

    @Override
    public String getOp() {
        return op;
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
