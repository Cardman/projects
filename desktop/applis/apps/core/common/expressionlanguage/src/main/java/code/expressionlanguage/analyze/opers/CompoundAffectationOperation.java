package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.opers.util.OperatorConverter;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.instr.PartOffset;
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
    public void analyze(ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode root_ = chidren_.first();
        OperationNode right_ = chidren_.last();
        SettableElResult elt_ = AffectationOperation.tryGetSettable(this);
        boolean ok_ = elt_ != null;
        AnalyzedPageEl page_ = _conf.getAnalyzing();
        LgNames stds_ = page_.getStandards();
        if (!ok_) {
            setRelativeOffsetPossibleAnalyzable(root_.getIndexInEl(), _conf);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(page_.getLocalizer().getCurrentFileName());
            un_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
            //oper len
            un_.buildError(_conf.getAnalysisMessages().getUnexpectedAffect(),
                    oper);
            page_.getLocalizer().addError(un_);
            IntTreeMap< String> ops_ = getOperations().getOperators();
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ops_.firstKey(), _conf);
            int opLocat_ = page_.getLocalizer().getCurrentLocationIndex();
            CustList<PartOffset> err_ = new CustList<PartOffset>();
            err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(un_.getBuiltError()) +"\" class=\"e\">",opLocat_));
            err_.add(new PartOffset("</a>",opLocat_+oper.length()));
            getPartOffsetsChildren().add(err_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        settable = elt_;
        if (settable instanceof SettableAbstractFieldOperation) {
            SettableAbstractFieldOperation cst_ = (SettableAbstractFieldOperation)settable;
            StringMap<Boolean> fieldsAfterLast_ = page_.getDeclaredAssignments();
            if (ElUtil.checkFinalFieldReadOnly(_conf, cst_, fieldsAfterLast_)) {
                setRelativeOffsetPossibleAnalyzable(cst_.getIndexInEl(), _conf);
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(page_.getLocalizer().getCurrentFileName());
                un_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
                //field name len
                un_.buildError(_conf.getAnalysisMessages().getFinalField(),
                        cst_.getFieldName());
                page_.getLocalizer().addError(un_);
                getErrs().add(un_.getBuiltError());
            }
        }
        IntTreeMap< String> ops_ = getOperations().getOperators();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ops_.firstKey(), _conf);
        int opLocat_ = page_.getLocalizer().getCurrentLocationIndex();
        String op_ = ops_.firstValue();
        op_ = op_.substring(0, op_.length() - 1);
        ClassArgumentMatching clMatchLeft_ = elt_.getResultClass();
        ClassArgumentMatching second_ = right_.getResultClass();
        OperatorConverter cl_ = getBinaryOperatorOrMethod(this,clMatchLeft_,second_, op_, _conf);
        if (cl_.getSymbol() != null) {
            ClassMethodId test_ = cl_.getTest();
            if (test_ != null) {
                clMatchLeft_.getImplicitsTest().add(test_);
                clMatchLeft_.setRootNumberTest(cl_.getRootNumberTest());
                clMatchLeft_.setMemberNumberTest(cl_.getMemberNumberTest());
                test = test_;
            }
            if (!AnaTypeUtil.isPrimitive(cl_.getSymbol().getClassName(),page_)) {
                classMethodId = cl_.getSymbol();
                rootNumber = cl_.getRootNumber();
                memberNumber = cl_.getMemberNumber();
            }
            Mapping map_ = new Mapping();
            map_.setArg(getResultClass());
            map_.setParam(elt_.getResultClass());
            if (!AnaTemplates.isCorrectOrNumbers(map_, _conf)) {
                ClassMethodIdReturn res_ = tryGetDeclaredImplicitCast(_conf, elt_.getResultClass().getSingleNameOrEmpty(), getResultClass());
                if (res_.isFoundMethod()) {
                    converter = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                    rootNumberConv = res_.getRootNumber();
                    memberNumberConv = res_.getMemberNumber();
                } else {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(page_.getLocalizer().getCurrentFileName());
                    cast_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
                    //oper len
                    cast_.buildError(_conf.getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(getResultClass().getNames(),"&"),
                            StringList.join(elt_.getResultClass().getNames(),"&"));
                    page_.getLocalizer().addError(cast_);
                    getErrs().add(cast_.getBuiltError());
                }
                setResultClass(ClassArgumentMatching.copy(AnaTypeUtil.toPrimitive(clMatchLeft_,page_)));
            }
            return;
        }
        setResultClass(ClassArgumentMatching.copy(AnaTypeUtil.toPrimitive(clMatchLeft_,page_)));
        elt_.setVariable(false);
        String stringType_ = stds_.getAliasString();
        boolean isString_ = clMatchLeft_.matchClass(stringType_);
        if (isString_&&!StringList.quickEq(oper, Block.NULL_EQ)) {
            settable.setCatenizeStrings();
        }
        ClassArgumentMatching clMatchRight_ = right_.getResultClass();
        setRelativeOffsetPossibleAnalyzable(root_.getIndexInEl(), _conf);

        if (StringList.quickEq(oper, Block.PLUS_EQ)) {
            if (!AnaTypeUtil.isPureNumberClass(clMatchLeft_, page_)) {
                if (!isString_) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(page_.getLocalizer().getCurrentFileName());
                    cast_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
                    //oper len
                    cast_.buildError(_conf.getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(clMatchRight_.getNames(),"&"),
                            StringList.join(clMatchLeft_.getNames(),"&"));
                    page_.getLocalizer().addError(cast_);
                    CustList<PartOffset> err_ = new CustList<PartOffset>();
                    err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(cast_.getBuiltError()) +"\" class=\"e\">",opLocat_));
                    err_.add(new PartOffset("</a>",opLocat_+oper.length()-1));
                    getPartOffsetsChildren().add(err_);
                    return;
                }
                clMatchRight_.setConvertToString(true);
                right_.cancelArgumentString();
                return;
            }
            if (!AnaTypeUtil.isPureNumberClass(clMatchRight_, page_)) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(page_.getLocalizer().getCurrentFileName());
                cast_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
                //oper len
                cast_.buildError(_conf.getAnalysisMessages().getBadImplicitCast(),
                        StringList.join(clMatchRight_.getNames(),"&"),
                        StringList.join(clMatchLeft_.getNames(),"&"));
                page_.getLocalizer().addError(cast_);
                CustList<PartOffset> err_ = new CustList<PartOffset>();
                err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(cast_.getBuiltError()) +"\" class=\"e\">",opLocat_));
                err_.add(new PartOffset("</a>",opLocat_+oper.length()-1));
                getPartOffsetsChildren().add(err_);
                return;
            }
            ClassArgumentMatching unwrapped_ = AnaTypeUtil.toPrimitive(clMatchLeft_, page_);
            if (!AnaTypeUtil.isFloatOrderClass(clMatchLeft_,clMatchRight_,_conf)
                    && !AnaTypeUtil.isIntOrderClass(clMatchLeft_,clMatchRight_,_conf)) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(page_.getLocalizer().getCurrentFileName());
                cast_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
                //oper len
                cast_.buildError(_conf.getAnalysisMessages().getBadImplicitCast(),
                        StringList.join(clMatchRight_.getNames(),"&"),
                        StringList.join(clMatchLeft_.getNames(),"&"));
                page_.getLocalizer().addError(cast_);
                CustList<PartOffset> err_ = new CustList<PartOffset>();
                err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(cast_.getBuiltError()) +"\" class=\"e\">",opLocat_));
                err_.add(new PartOffset("</a>",opLocat_+oper.length()-1));
                getPartOffsetsChildren().add(err_);
                return;
            }
            elt_.getResultClass().setUnwrapObject(unwrapped_);
            right_.getResultClass().setUnwrapObject(unwrapped_);
            ((OperationNode) elt_).cancelArgument();
            right_.cancelArgument();
            return;
        }
        if (StringList.quickEq(oper, Block.AND_EQ) || StringList.quickEq(oper, Block.OR_EQ) || StringList.quickEq(oper, Block.XOR_EQ)) {
            boolean okRes_ = false;
            if (clMatchLeft_.isBoolType(page_) && clMatchRight_.isBoolType(page_)) {
                okRes_ = true;
            } else {
                if (AnaTypeUtil.isIntOrderClass(clMatchLeft_,clMatchRight_,_conf)) {
                    okRes_ = true;
                }
            }
            if (!okRes_) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(page_.getLocalizer().getCurrentFileName());
                cast_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
                //oper len
                cast_.buildError(_conf.getAnalysisMessages().getBadImplicitCast(),
                        StringList.join(clMatchRight_.getNames(),"&"),
                        StringList.join(clMatchLeft_.getNames(),"&"));
                page_.getLocalizer().addError(cast_);
                CustList<PartOffset> err_ = new CustList<PartOffset>();
                err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(cast_.getBuiltError()) +"\" class=\"e\">",opLocat_));
                err_.add(new PartOffset("</a>",opLocat_+oper.length()-1));
                getPartOffsetsChildren().add(err_);
                return;
            }
            ClassArgumentMatching unwrapped_ = AnaTypeUtil.toPrimitive(clMatchLeft_, page_);
            elt_.getResultClass().setUnwrapObject(unwrapped_);
            right_.getResultClass().setUnwrapObject(unwrapped_);
            ((OperationNode) elt_).cancelArgument();
            right_.cancelArgument();
            return;
        }
        if (StringList.quickEq(oper, Block.AND_LOG_EQ) || StringList.quickEq(oper, Block.OR_LOG_EQ)) {
            if (!clMatchLeft_.isBoolType(page_) || !clMatchRight_.isBoolType(page_)) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(page_.getLocalizer().getCurrentFileName());
                cast_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
                //oper len
                cast_.buildError(_conf.getAnalysisMessages().getBadImplicitCast(),
                        StringList.join(clMatchRight_.getNames(),"&"),
                        StringList.join(clMatchLeft_.getNames(),"&"));
                page_.getLocalizer().addError(cast_);
                CustList<PartOffset> err_ = new CustList<PartOffset>();
                err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(cast_.getBuiltError()) +"\" class=\"e\">",opLocat_));
                err_.add(new PartOffset("</a>",opLocat_+oper.length()-1));
                getPartOffsetsChildren().add(err_);
                return;
            }
            ClassArgumentMatching unwrapped_ = AnaTypeUtil.toPrimitive(clMatchLeft_, page_);
            elt_.getResultClass().setUnwrapObject(unwrapped_);
            right_.getResultClass().setUnwrapObject(unwrapped_);
            ((OperationNode) elt_).cancelArgument();
            right_.cancelArgument();
            return;
        }
        if (StringList.quickEq(oper, Block.NULL_EQ)) {
            StringMap<StringList> vars_ = page_.getCurrentConstraints().getCurrentConstraints();
            Mapping mapping_ = new Mapping();
            mapping_.setMapping(vars_);
            mapping_.setArg(clMatchRight_);
            mapping_.setParam(clMatchLeft_);
            if (!AnaTemplates.isCorrectOrNumbers(mapping_, _conf)) {
                ClassMethodIdReturn res_ = tryGetDeclaredImplicitCast(_conf, clMatchLeft_.getSingleNameOrEmpty(), clMatchRight_);
                if (res_.isFoundMethod()) {
                    ClassMethodId clImpl_ = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                    clMatchRight_.getImplicits().add(clImpl_);
                    clMatchRight_.setRootNumber(res_.getRootNumber());
                    clMatchRight_.setMemberNumber(res_.getMemberNumber());
                } else {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(page_.getLocalizer().getCurrentFileName());
                    cast_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
                    //oper
                    cast_.buildError(_conf.getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(clMatchRight_.getNames(),"&"),
                            StringList.join(clMatchLeft_.getNames(),"&"));
                    page_.getLocalizer().addError(cast_);
                    CustList<PartOffset> err_ = new CustList<PartOffset>();
                    err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(cast_.getBuiltError()) +"\" class=\"e\">",opLocat_));
                    err_.add(new PartOffset("</a>",opLocat_+oper.length()-1));
                    getPartOffsetsChildren().add(err_);
                }
            }
            setResultClass(ClassArgumentMatching.copy(clMatchLeft_));
            return;
        }
        if (!AnaTypeUtil.isFloatOrderClass(clMatchLeft_,clMatchRight_,_conf)
                && !AnaTypeUtil.isIntOrderClass(clMatchLeft_,clMatchRight_,_conf)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(page_.getLocalizer().getCurrentFileName());
            cast_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
            //oper len
            cast_.buildError(_conf.getAnalysisMessages().getBadImplicitCast(),
                    StringList.join(clMatchRight_.getNames(),"&"),
                    StringList.join(clMatchLeft_.getNames(),"&"));
            page_.getLocalizer().addError(cast_);
            CustList<PartOffset> err_ = new CustList<PartOffset>();
            err_.add(new PartOffset("<a title=\""+LinkageUtil.transform(cast_.getBuiltError()) +"\" class=\"e\">",opLocat_));
            err_.add(new PartOffset("</a>",opLocat_+oper.length()-1));
            getPartOffsetsChildren().add(err_);
        }
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
}
