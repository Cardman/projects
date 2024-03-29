package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.SymbolFactoryUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.opers.util.ClassMethodIdMemberIdTypeFct;
import code.expressionlanguage.analyze.opers.util.OperatorConverter;
import code.expressionlanguage.analyze.opers.util.ResultOperand;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.symbol.CommonOperSymbol;
import code.expressionlanguage.fwd.opers.AnaOperatorContent;
import code.expressionlanguage.linkage.ExportCst;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.core.StringUtil;

public final class SemiAffectationOperation extends AbstractUnaryOperation  {
    private SettableElResult settable;
    private final AnaOperatorContent operatorContent;
    private final boolean post;
    private final ClassMethodIdMemberIdTypeFct fct = new ClassMethodIdMemberIdTypeFct();
    private final ClassMethodIdMemberIdTypeFct convTo = new ClassMethodIdMemberIdTypeFct();

    public SemiAffectationOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op, boolean _post) {
        super(_index, _indexChild, _m, _op);
        post = _post;
        operatorContent = new AnaOperatorContent();
        operatorContent.setOper(_op.getOperators().firstValue());
        operatorContent.setOpOffset(_op.getOperators().firstKey());
    }

    @Override
    public void analyzeUnary(AnalyzedPageEl _page) {
        OperationNode leftEl_ = getFirstChild();
        settable = AffectationOperation.tryGetCastSettable(this);
        if (!isLeftValue(settable)) {
            setRelativeOffsetPossibleAnalyzable(leftEl_.getIndexInEl(), _page);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(_page);
            //operator
            un_.buildError(_page.getAnalysisMessages().getUnexpectedAffect(),
                    operatorContent.getOper());
            _page.getLocalizer().addError(un_);
            addErr(un_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        WrappOperation.procArr(_page, (OperationNode) settable);
        CompoundAffectationOperation.checkFinal(this,_page,settable);
        setResultClass(AnaClassArgumentMatching.copy(getSettableResClass(), _page.getPrimitiveTypes()));
        settable.setVariable(false);
        StrTypes ops_ = getOperators();
        String op_ = ops_.firstValue();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ops_.firstKey(), _page);
        ResultOperand resOp_ = SymbolFactoryUtil.generateOperand(op_, leftEl_.getResultClass(), _page);
        AnaClassArgumentMatching rOp_ = resOp_.getResult();
        OperatorConverter cl_ = null;
        if (rOp_.getSingleNameOrEmpty().isEmpty()) {
            AnaClassArgumentMatching operand_ = leftEl_.getResultClass();
            CustList<OperationNode> single_ = new CustList<OperationNode>(leftEl_);
            cl_ = operUse(_page, op_, operand_, single_, SymbolFactoryUtil.unaries(op_,_page));
        }
        fct.setSymbol(resOp_.getSymbol());
        if (cl_ != null) {
            fct.infos(cl_);
            CompoundAffectationOperation.tryImplicit(this,_page, getSettableResClass(), convTo);
            return;
        }
        if (rOp_.getSingleNameOrEmpty().isEmpty()) {
            AnaClassArgumentMatching clMatchLeft_ = leftEl_.getResultClass();
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFile(_page.getCurrentFile());
            cast_.setIndexFile(_page);
            //operator
            cast_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                    StringUtil.join(clMatchLeft_.getNames(), ExportCst.JOIN_TYPES));
            _page.getLocalizer().addError(cast_);
            addErr(cast_.getBuiltError());
        }
        setResultClass(AnaClassArgumentMatching.copy(rOp_, _page.getPrimitiveTypes()));
    }

    public CommonOperSymbol getSymbol() {
        return fct.getSymbol();
    }

    private AnaClassArgumentMatching getSettableResClass() {
        return ExplicitOperatorOperation.getSettableResClass(settable);
    }

    public boolean isPost() {
        return post;
    }

    public ClassMethodIdMemberIdTypeFct getFct() {
        return fct;
    }

    public ClassMethodIdMemberIdTypeFct getConvTo() {
        return convTo;
    }

    public int getOpOffset() {
        return operatorContent.getOpOffset();
    }

    public SettableElResult getSettable() {
        return settable;
    }

    public AnaTypeFct getFunctionTo() {
        return convTo.getFunction();
    }

    public AnaOperatorContent getOperatorContent() {
        return operatorContent;
    }
}
