package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.SymbolFactoryUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.ClassMethodIdMemberIdTypeFct;
import code.expressionlanguage.analyze.opers.util.OperatorConverter;
import code.expressionlanguage.analyze.opers.util.ResultOperand;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.symbol.CommonOperSymbol;
import code.expressionlanguage.fwd.opers.AnaOperatorContent;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.util.CustList;
import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class CmpOperation extends MethodOperation implements MiddleSymbolOperation {

    private final ClassMethodIdMemberIdTypeFct fct = new ClassMethodIdMemberIdTypeFct();
    private final AnaOperatorContent operatorContent;
    private boolean okNum;

    public CmpOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        operatorContent = new AnaOperatorContent();
        operatorContent.setOper(_op.getOperators().firstValue().trim());
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        okNum = true;
        if (chidren_.size() != 2) {
            okNum = false;
            _page.setOkNumOp(false);
            int in_ = NumberUtil.min(getOperators().size()-1,1);
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ getOperators().getKey(in_), _page);
            FoundErrorInterpret badNb_ = new FoundErrorInterpret();
            badNb_.setFile(_page.getCurrentFile());
            badNb_.setIndexFile(_page);
            //first oper
            badNb_.buildError(_page.getAnalysisMessages().getOperatorNbDiff(),
                    Long.toString(2),
                    Long.toString(chidren_.size()),
                    operatorContent.getOper()
            );
            _page.getLocalizer().addError(badNb_);
            for (int i = 0; i < in_;i++) {
                getPartOffsetsChildren().add(new InfoErrorDto(""));
            }
            getPartOffsetsChildren().add(new InfoErrorDto(badNb_,_page, getOperators().getValue(in_).length()));
            setResultClass(new AnaClassArgumentMatching(_page.getAliasPrimBoolean(),PrimitiveTypes.BOOL_WRAP));
            return;
        }
        operatorContent.setOpOffset(getOperators().firstKey());
        OperationNode l_ = chidren_.first();
        AnaClassArgumentMatching first_ = l_.getResultClass();
        OperationNode r_ = chidren_.last();
        AnaClassArgumentMatching second_ = r_.getResultClass();
        String op_ = getOperators().firstValue().trim();
        OperatorConverter cl_ = null;
        ResultOperand resOp_ = SymbolFactoryUtil.generateOperand(op_, l_.getResultClass(), r_.getResultClass(), _page);
        AnaClassArgumentMatching rOp_ = resOp_.getResult();
        if (rOp_.getSingleNameOrEmpty().isEmpty()) {
            cl_ = CompoundAffectationOperation.tryGetStd(_page, op_, this, SymbolFactoryUtil.binaries(op_, _page));
        }
        fct.setSymbol(resOp_.getSymbol());
        if (cl_ != null) {
            fct.infos(cl_);
            return;
        }
        setResultClass(AnaClassArgumentMatching.copy(rOp_, _page.getPrimitiveTypes()));
        if (rOp_.getSingleNameOrEmpty().isEmpty()) {
            okNum = false;
            _page.setOkNumOp(false);
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ getOperators().getKey(0), _page);
            String res_ = _page.getAliasPrimBoolean();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_page);
            un_.setFile(_page.getCurrentFile());
            //oper
            un_.buildError(_page.getAnalysisMessages().getUnexpectedOperandTypes(),
                    StringUtil.join(new StringList(
                            StringUtil.join(first_.getNames(),ExportCst.JOIN_TYPES),
                            StringUtil.join(second_.getNames(),ExportCst.JOIN_TYPES)
                    ),ExportCst.JOIN_OPERANDS),
                    getOp());
            _page.getLocalizer().addError(un_);
            getPartOffsetsChildren().add(new InfoErrorDto(un_, _page, operatorContent.getOper().length()));
            setResultClass(new AnaClassArgumentMatching(res_, _page.getPrimitiveTypes()));
        }
        okNum = _page.isOkNumOp();
    }

    public CommonOperSymbol getSymbol() {
        return fct.getSymbol();
    }

    public ClassMethodIdMemberIdTypeFct getFct() {
        return fct;
    }

    @Override
    public String getOp() {
        return operatorContent.getOper();
    }

    @Override
    public boolean isOkNum() {
        return okNum;
    }

    public AnaOperatorContent getOperatorContent() {
        return operatorContent;
    }
}
