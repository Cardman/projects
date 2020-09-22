package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.opers.util.OperatorConverter;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.stds.LgNames;
import code.util.*;

public final class CompoundAffectationOperation extends MethodOperation {

    private SettableElResult settable;
    private String oper;
    private ClassMethodId classMethodId;
    private int rootNumber = -1;
    private int memberNumber = -1;
    private ClassMethodId converter;
    private int rootNumberConv = -1;
    private int memberNumberConv = -1;
    private ClassMethodId test;

    private int opOffset;
    private boolean rightBool;

    public CompoundAffectationOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        oper = _op.getOperators().firstValue();
        opOffset = _op.getOperators().firstKey();
    }

    @Override
    void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode root_ = chidren_.first();
        OperationNode right_ = chidren_.last();
        SettableElResult elt_ = AffectationOperation.tryGetSettable(this);
        LgNames stds_ = _page.getStandards();
        if (!(elt_ instanceof OperationNode)) {
            setRelativeOffsetPossibleAnalyzable(root_.getIndexInEl(), _page);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //oper len
            un_.buildError(_page.getAnalysisMessages().getUnexpectedAffect(),
                    oper);
            _page.getLocalizer().addError(un_);
            IntTreeMap< String> ops_ = getOperations().getOperators();
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ops_.firstKey(), _page);
            int opLocat_ = _page.getLocalizer().getCurrentLocationIndex();
            CustList<PartOffset> err_ = new CustList<PartOffset>();
            err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(un_.getBuiltError()) +"\" class=\"e\">",opLocat_));
            err_.add(new PartOffset("</a>",opLocat_+oper.length()));
            getPartOffsetsChildren().add(err_);
            setResultClass(new AnaClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        settable = elt_;
        if (settable instanceof SettableAbstractFieldOperation) {
            SettableAbstractFieldOperation cst_ = (SettableAbstractFieldOperation)settable;
            StringMap<Boolean> fieldsAfterLast_ = _page.getDeclaredAssignments();
            if (ElUtil.checkFinalFieldReadOnly(cst_, fieldsAfterLast_, _page)) {
                setRelativeOffsetPossibleAnalyzable(cst_.getIndexInEl(), _page);
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_page.getLocalizer().getCurrentFileName());
                un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //field name len
                un_.buildError(_page.getAnalysisMessages().getFinalField(),
                        cst_.getFieldName());
                _page.getLocalizer().addError(un_);
                getErrs().add(un_.getBuiltError());
            }
        }
        IntTreeMap< String> ops_ = getOperations().getOperators();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ops_.firstKey(), _page);
        int opLocat_ = _page.getLocalizer().getCurrentLocationIndex();
        String op_ = ops_.firstValue();
        op_ = op_.substring(0, op_.length() - 1);
        AnaClassArgumentMatching clMatchLeft_ = elt_.getResultClass();
        OperatorConverter cl_ = getBinaryOperatorOrMethod(this,(OperationNode) elt_,right_, op_, _page);
        if (cl_.getSymbol() != null) {
            ClassMethodId test_ = cl_.getTest();
            if (test_ != null) {
                clMatchLeft_.getImplicitsTest().add(test_);
                clMatchLeft_.setRootNumberTest(cl_.getRootNumberTest());
                clMatchLeft_.setMemberNumberTest(cl_.getMemberNumberTest());
                test = test_;
            }
            if (!AnaTypeUtil.isPrimitive(cl_.getSymbol().getClassName(), _page)) {
                classMethodId = cl_.getSymbol();
                rootNumber = cl_.getRootNumber();
                memberNumber = cl_.getMemberNumber();
            }
            Mapping map_ = new Mapping();
            map_.setArg(getResultClass());
            map_.setParam(elt_.getResultClass());
            if (!AnaTemplates.isCorrectOrNumbers(map_, _page)) {
                ClassMethodIdReturn res_ = tryGetDeclaredImplicitCast(elt_.getResultClass().getSingleNameOrEmpty(), getResultClass(), _page);
                if (res_.isFoundMethod()) {
                    converter = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                    rootNumberConv = res_.getRootNumber();
                    memberNumberConv = res_.getMemberNumber();
                } else {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_page.getLocalizer().getCurrentFileName());
                    cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    //oper len
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(getResultClass().getNames(),"&"),
                            StringList.join(elt_.getResultClass().getNames(),"&"));
                    _page.getLocalizer().addError(cast_);
                    getErrs().add(cast_.getBuiltError());
                }
                setResultClass(AnaClassArgumentMatching.copy(AnaTypeUtil.toPrimitive(clMatchLeft_, _page), _page.getStandards()));
            }
            setBool(right_,_page);
            return;
        }
        setResultClass(AnaClassArgumentMatching.copy(AnaTypeUtil.toPrimitive(clMatchLeft_, _page), _page.getStandards()));
        elt_.setVariable(false);
        String stringType_ = stds_.getAliasString();
        boolean isString_ = clMatchLeft_.matchClass(stringType_);
        if (isString_&&!StringList.quickEq(oper, Block.NULL_EQ)) {
            settable.setCatenizeStrings();
        }
        AnaClassArgumentMatching clMatchRight_ = right_.getResultClass();
        setRelativeOffsetPossibleAnalyzable(root_.getIndexInEl(), _page);

        if (StringList.quickEq(oper, Block.PLUS_EQ)) {
            if (!AnaTypeUtil.isPureNumberClass(clMatchLeft_, _page)) {
                if (!isString_) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_page.getLocalizer().getCurrentFileName());
                    cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    //oper len
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(clMatchRight_.getNames(),"&"),
                            StringList.join(clMatchLeft_.getNames(),"&"));
                    _page.getLocalizer().addError(cast_);
                    CustList<PartOffset> err_ = new CustList<PartOffset>();
                    err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(cast_.getBuiltError()) +"\" class=\"e\">",opLocat_));
                    err_.add(new PartOffset("</a>",opLocat_+oper.length()-1));
                    getPartOffsetsChildren().add(err_);
                    return;
                }
                clMatchRight_.setConvertToString(true);
                return;
            }
            if (!AnaTypeUtil.isPureNumberClass(clMatchRight_, _page)) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(_page.getLocalizer().getCurrentFileName());
                cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //oper len
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringList.join(clMatchRight_.getNames(),"&"),
                        StringList.join(clMatchLeft_.getNames(),"&"));
                _page.getLocalizer().addError(cast_);
                CustList<PartOffset> err_ = new CustList<PartOffset>();
                err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(cast_.getBuiltError()) +"\" class=\"e\">",opLocat_));
                err_.add(new PartOffset("</a>",opLocat_+oper.length()-1));
                getPartOffsetsChildren().add(err_);
                return;
            }
            AnaClassArgumentMatching unwrapped_ = AnaTypeUtil.toPrimitive(clMatchLeft_, _page);
            if (!AnaTypeUtil.isFloatOrderClass(clMatchLeft_,clMatchRight_, _page)
                    && !AnaTypeUtil.isIntOrderClass(clMatchLeft_,clMatchRight_, _page)) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(_page.getLocalizer().getCurrentFileName());
                cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //oper len
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringList.join(clMatchRight_.getNames(),"&"),
                        StringList.join(clMatchLeft_.getNames(),"&"));
                _page.getLocalizer().addError(cast_);
                CustList<PartOffset> err_ = new CustList<PartOffset>();
                err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(cast_.getBuiltError()) +"\" class=\"e\">",opLocat_));
                err_.add(new PartOffset("</a>",opLocat_+oper.length()-1));
                getPartOffsetsChildren().add(err_);
                return;
            }
            elt_.getResultClass().setUnwrapObject(unwrapped_, _page.getStandards());
            right_.getResultClass().setUnwrapObject(unwrapped_, _page.getStandards());
            return;
        }
        if (StringList.quickEq(oper, Block.AND_EQ) || StringList.quickEq(oper, Block.OR_EQ) || StringList.quickEq(oper, Block.XOR_EQ)) {
            boolean okRes_ = false;
            if (clMatchLeft_.isBoolType(_page) && clMatchRight_.isBoolType(_page)) {
                okRes_ = true;
            } else {
                if (AnaTypeUtil.isIntOrderClass(clMatchLeft_,clMatchRight_, _page)) {
                    okRes_ = true;
                }
            }
            if (!okRes_) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(_page.getLocalizer().getCurrentFileName());
                cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //oper len
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringList.join(clMatchRight_.getNames(),"&"),
                        StringList.join(clMatchLeft_.getNames(),"&"));
                _page.getLocalizer().addError(cast_);
                CustList<PartOffset> err_ = new CustList<PartOffset>();
                err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(cast_.getBuiltError()) +"\" class=\"e\">",opLocat_));
                err_.add(new PartOffset("</a>",opLocat_+oper.length()-1));
                getPartOffsetsChildren().add(err_);
                return;
            }
            AnaClassArgumentMatching unwrapped_ = AnaTypeUtil.toPrimitive(clMatchLeft_, _page);
            elt_.getResultClass().setUnwrapObject(unwrapped_, _page.getStandards());
            right_.getResultClass().setUnwrapObject(unwrapped_, _page.getStandards());
            setBool(right_,_page);
            return;
        }
        if (StringList.quickEq(oper, Block.AND_LOG_EQ) || StringList.quickEq(oper, Block.OR_LOG_EQ)) {
            if (!clMatchLeft_.isBoolType(_page) || !clMatchRight_.isBoolType(_page)) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(_page.getLocalizer().getCurrentFileName());
                cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //oper len
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringList.join(clMatchRight_.getNames(),"&"),
                        StringList.join(clMatchLeft_.getNames(),"&"));
                _page.getLocalizer().addError(cast_);
                CustList<PartOffset> err_ = new CustList<PartOffset>();
                err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(cast_.getBuiltError()) +"\" class=\"e\">",opLocat_));
                err_.add(new PartOffset("</a>",opLocat_+oper.length()-1));
                getPartOffsetsChildren().add(err_);
                return;
            }
            AnaClassArgumentMatching unwrapped_ = AnaTypeUtil.toPrimitive(clMatchLeft_, _page);
            elt_.getResultClass().setUnwrapObject(unwrapped_, _page.getStandards());
            right_.getResultClass().setUnwrapObject(unwrapped_, _page.getStandards());
            setBool(right_,_page);
            return;
        }
        if (StringList.quickEq(oper, Block.NULL_EQ)) {
            StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
            Mapping mapping_ = new Mapping();
            mapping_.setMapping(vars_);
            mapping_.setArg(clMatchRight_);
            mapping_.setParam(clMatchLeft_);
            if (!AnaTemplates.isCorrectOrNumbers(mapping_, _page)) {
                ClassMethodIdReturn res_ = tryGetDeclaredImplicitCast(clMatchLeft_.getSingleNameOrEmpty(), clMatchRight_, _page);
                if (res_.isFoundMethod()) {
                    ClassMethodId clImpl_ = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                    clMatchRight_.getImplicits().add(clImpl_);
                    clMatchRight_.setRootNumber(res_.getRootNumber());
                    clMatchRight_.setMemberNumber(res_.getMemberNumber());
                } else {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_page.getLocalizer().getCurrentFileName());
                    cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    //oper
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(clMatchRight_.getNames(),"&"),
                            StringList.join(clMatchLeft_.getNames(),"&"));
                    _page.getLocalizer().addError(cast_);
                    CustList<PartOffset> err_ = new CustList<PartOffset>();
                    err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(cast_.getBuiltError()) +"\" class=\"e\">",opLocat_));
                    err_.add(new PartOffset("</a>",opLocat_+oper.length()-1));
                    getPartOffsetsChildren().add(err_);
                }
            }
            setResultClass(AnaClassArgumentMatching.copy(clMatchLeft_, _page.getStandards()));
            return;
        }
        if (!AnaTypeUtil.isFloatOrderClass(clMatchLeft_,clMatchRight_, _page)
                && !AnaTypeUtil.isIntOrderClass(clMatchLeft_,clMatchRight_, _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_page.getLocalizer().getCurrentFileName());
            cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //oper len
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    StringList.join(clMatchRight_.getNames(),"&"),
                    StringList.join(clMatchLeft_.getNames(),"&"));
            _page.getLocalizer().addError(cast_);
            CustList<PartOffset> err_ = new CustList<PartOffset>();
            err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(cast_.getBuiltError()) +"\" class=\"e\">",opLocat_));
            err_.add(new PartOffset("</a>",opLocat_+oper.length()-1));
            getPartOffsetsChildren().add(err_);
        } else {
            AnaClassArgumentMatching unwrapped_ = AnaTypeUtil.toPrimitive(clMatchLeft_, _page);
            elt_.getResultClass().setUnwrapObject(unwrapped_, _page.getStandards());
            right_.getResultClass().setUnwrapObject(unwrapped_, _page.getStandards());
        }
    }

    private void setBool(OperationNode _right,AnalyzedPageEl _page) {
        rightBool = _right.getResultClass().isBoolType(_page);
    }

    public String getOper() {
        return oper;
    }

    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }

    public int getOpOffset() {
        return opOffset;
    }

    public ClassMethodId getConverter() {
        return converter;
    }

    public ClassMethodId getTest() {
        return test;
    }

    public SettableElResult getSettable() {
        return settable;
    }

    public int getRootNumber() {
        return rootNumber;
    }

    public int getMemberNumber() {
        return memberNumber;
    }

    public int getRootNumberConv() {
        return rootNumberConv;
    }

    public int getMemberNumberConv() {
        return memberNumberConv;
    }

    public boolean isRightBool() {
        return rightBool;
    }
}
