package code.expressionlanguage.analyze.opers;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.NamedFunctionBlock;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.opers.util.MemberId;
import code.expressionlanguage.analyze.opers.util.OperatorConverter;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.util.CustList;
import code.util.*;
import code.util.core.StringUtil;

public final class EqOperation extends MethodOperation implements MiddleSymbolOperation {

    private String oper;
    private ClassMethodId classMethodId;
    private AnaTypeFct function;
    private int opOffset;
    private MemberId memberId = new MemberId();
    public EqOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        oper = _op.getOperators().values().first();
        opOffset = _op.getOperators().firstKey();
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+opOffset, _page);
        if (StringUtil.quickEq(oper.trim(), NEG_BOOL)) {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFileName(_page.getLocalizer().getCurrentFileName());
            int index_ = _page.getLocalizer().getCurrentLocationIndex();
            badEl_.setIndexFile(index_);
            //oper len
            badEl_.buildError(_page.getAnalysisMessages().getBadOperatorRef(),
                    oper.trim());
            _page.getLocalizer().addError(badEl_);
            CustList<PartOffset> err_ = new CustList<PartOffset>();
            err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(badEl_.getBuiltError()) +"\" class=\"e\">",index_));
            err_.add(new PartOffset("</a>",index_+1));
            getPartOffsetsChildren().add(err_);
        }
        String custOp_ = oper.trim();
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode l_ = chidren_.first();
        OperationNode r_ = chidren_.last();
        OperatorConverter cl_ = getBinaryOperatorOrMethod(this,l_,r_, custOp_, _page);
        if (cl_.getSymbol() != null) {
            classMethodId = cl_.getSymbol();
            memberId = cl_.getMemberId();
            function = cl_.getFunction();
            return;
        }
        setResultClass(new AnaClassArgumentMatching(_page.getAliasPrimBoolean(),PrimitiveTypes.BOOL_WRAP));
    }

    @Override
    void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }

    public String getOper() {
        return getOp();
    }

    public AnaTypeFct getFunction() {
        return function;
    }

    @Override
    public String getOp() {
        return oper;
    }

    @Override
    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }

    @Override
    public int getOpOffset() {
        return opOffset;
    }

    /**The execution cannot occur if there is only one character as symbol
    Sample: 1!2 leads to error even if there is two operands*/
    @Override
    public boolean isOkNum() {
        return true;
    }

    public MemberId getMemberId() {
        return memberId;
    }

}
