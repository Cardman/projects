package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.opers.util.MemberId;
import code.expressionlanguage.analyze.opers.util.OperatorConverter;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.fwd.opers.AnaOperatorContent;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.maths.litteral.StrTypes;
import code.util.CustList;
import code.util.*;
import code.util.StringList;
import code.util.core.StringUtil;

public final class CmpOperation extends MethodOperation implements MiddleSymbolOperation {

    private boolean stringCompare;
    private String className="";
    private final AnaOperatorContent operatorContent;
    private boolean okNum;
    private MemberId memberId = new MemberId();
    private AnaTypeFct function;

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
            int in_ = Math.min(getOperations().getOperators().size()-1,1);
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+getOperations().getOperators().getKey(in_), _page);
            FoundErrorInterpret badNb_ = new FoundErrorInterpret();
            badNb_.setFileName(_page.getLocalizer().getCurrentFileName());
            int index_ = _page.getLocalizer().getCurrentLocationIndex();
            badNb_.setIndexFile(index_);
            //first oper
            badNb_.buildError(_page.getAnalysisMessages().getOperatorNbDiff(),
                    Long.toString(2),
                    Long.toString(chidren_.size()),
                    operatorContent.getOper()
            );
            _page.getLocalizer().addError(badNb_);
            for (int i = 0; i < in_;i++) {
                CustList<PartOffset> err_ = new CustList<PartOffset>();
                getPartOffsetsChildren().add(err_);
            }
            CustList<PartOffset> err_ = new CustList<PartOffset>();
            err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(badNb_.getBuiltError()) +"\" class=\"e\">",index_));
            err_.add(new PartOffset("</a>",index_+getOperations().getOperators().getValue(in_).length()));
            getPartOffsetsChildren().add(err_);
            setResultClass(new AnaClassArgumentMatching(_page.getAliasPrimBoolean(),PrimitiveTypes.BOOL_WRAP));
            return;
        }
        operatorContent.setOpOffset(getOperations().getOperators().firstKey());
        OperationNode l_ = chidren_.first();
        AnaClassArgumentMatching first_ = l_.getResultClass();
        OperationNode r_ = chidren_.last();
        AnaClassArgumentMatching second_ = r_.getResultClass();
        String op_ = getOperations().getOperators().firstValue().trim();
        OperatorConverter cl_ = getBinaryOperatorOrMethod(this,l_,r_, op_, _page);
        if (cl_.getSymbol() != null) {
            if (!AnaTypeUtil.isPrimitive(cl_.getSymbol().getClassName(), _page)) {
                className = cl_.getSymbol().getClassName();
                memberId = cl_.getMemberId();
                function = cl_.getFunction();
            }
            return;
        }
        String stringType_ = _page.getAliasString();
        if (first_.matchClass(stringType_) && second_.matchClass(stringType_)) {
            stringCompare = true;
            setResultClass(new AnaClassArgumentMatching(_page.getAliasPrimBoolean(),PrimitiveTypes.BOOL_WRAP));
            first_.setCheckOnlyNullPe(true);
            second_.setCheckOnlyNullPe(true);
            return;
        }
        if (AnaTypeUtil.isFloatOrderClass(first_,second_, _page)) {
            AnaClassArgumentMatching classFirst_ = AnaTypeUtil.toPrimitive(first_, _page);
            AnaClassArgumentMatching classSecond_ = AnaTypeUtil.toPrimitive(second_, _page);
            l_.getResultClass().setUnwrapObject(classFirst_, _page.getPrimitiveTypes());
            r_.getResultClass().setUnwrapObject(classSecond_, _page.getPrimitiveTypes());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasPrimBoolean(),PrimitiveTypes.BOOL_WRAP));
            return;
        }
        if (AnaTypeUtil.isIntOrderClass(first_,second_, _page)) {
            AnaClassArgumentMatching classFirst_ = AnaTypeUtil.toPrimitive(first_, _page);
            AnaClassArgumentMatching classSecond_ = AnaTypeUtil.toPrimitive(second_, _page);
            l_.getResultClass().setUnwrapObject(classFirst_, _page.getPrimitiveTypes());
            r_.getResultClass().setUnwrapObject(classSecond_, _page.getPrimitiveTypes());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasPrimBoolean(),PrimitiveTypes.BOOL_WRAP));
            return;
        }
        okNum = false;
        _page.setOkNumOp(false);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+getOperations().getOperators().getKey(0), _page);
        String res_ = _page.getAliasPrimBoolean();
        FoundErrorInterpret un_ = new FoundErrorInterpret();
        int index_ = _page.getLocalizer().getCurrentLocationIndex();
        un_.setIndexFile(index_);
        un_.setFileName(_page.getLocalizer().getCurrentFileName());
        //oper
        un_.buildError(_page.getAnalysisMessages().getUnexpectedOperandTypes(),
                StringUtil.join(new StringList(
                        StringUtil.join(first_.getNames(),"&"),
                        StringUtil.join(second_.getNames(),"&")
                ),";"),
                getOp());
        _page.getLocalizer().addError(un_);
        CustList<PartOffset> err_ = new CustList<PartOffset>();
        err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(un_.getBuiltError()) +"\" class=\"e\">",index_));
        err_.add(new PartOffset("</a>",index_+ operatorContent.getOper().length()));
        getPartOffsetsChildren().add(err_);
        setResultClass(new AnaClassArgumentMatching(res_, _page.getPrimitiveTypes()));
    }
    public boolean isStringCompare() {
        return stringCompare;
    }

    @Override
    void calculateChildren() {
        StrTypes vs_ = getOperations().getValues();
        getChildren().addAllEntries(vs_);
    }

    public AnaTypeFct getFunction() {
        return function;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public String getOp() {
        return operatorContent.getOper();
    }

    @Override
    public boolean isOkNum() {
        return okNum;
    }

    @Override
    public int getOpOffset() {
        return operatorContent.getOpOffset();
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public AnaOperatorContent getOperatorContent() {
        return operatorContent;
    }
}
