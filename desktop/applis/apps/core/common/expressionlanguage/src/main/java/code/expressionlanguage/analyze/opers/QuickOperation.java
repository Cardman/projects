package code.expressionlanguage.analyze.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.util.OperatorConverter;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringList;


public abstract class QuickOperation extends MethodOperation {

    private boolean okNum;
    private ClassMethodId classMethodId;
    private int rootNumber = -1;
    private int memberNumber = -1;
    private ClassMethodId test;
    private ClassMethodId converter;
    private int rootNumberConv = -1;
    private int memberNumberConv = -1;
    private CustList<PartOffset> errFirst = new CustList<PartOffset>();
    private CustList<PartOffset> errSecond = new CustList<PartOffset>();

    public QuickOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    final void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }

    static void tryGetResult(MethodOperation _to, boolean _abs, boolean _okNum, AnalyzedPageEl _page) {
        if (!_okNum) {
            return;
        }
        CustList<OperationNode> children_ = _to.getChildrenNodes();
        Argument f_ = children_.first().getArgument();
        Argument s_ = children_.last().getArgument();
        if (f_ == null) {
            return;
        }
        Struct v_ = f_.getStruct();
        if (_abs) {
            if (BooleanStruct.isTrue(v_)) {
                _to.setSimpleArgumentAna(f_, _page);
                return;
            }
        } else {
            if (BooleanStruct.isFalse(v_)) {
                _to.setSimpleArgumentAna(f_, _page);
                return;
            }
        }
        if (s_ == null) {
            return;
        }
        _to.setSimpleArgumentAna(s_, _page);
    }
    @Override
    public final void analyze(ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        AnalyzedPageEl page_ = _conf.getAnalyzing();
        LgNames stds_ = page_.getStandards();
        String booleanPrimType_ = stds_.getAliasPrimBoolean();
        OperationNode left_ = chidren_.first();
        OperationNode right_ = chidren_.last();
        AnaClassArgumentMatching leftRes_ = left_.getResultClass();
        AnaClassArgumentMatching rightRes_ = right_.getResultClass();
        String oper_ = getOperations().getOperators().firstValue();
        OperatorConverter opConv_ = getBinaryOperatorOrMethod(this, left_, right_, oper_, _conf);
        if (opConv_.getSymbol() != null) {
            if (!AnaTypeUtil.isPrimitive(opConv_.getSymbol().getClassName(),page_)) {
                classMethodId = opConv_.getSymbol();
                rootNumber = opConv_.getRootNumber();
                memberNumber = opConv_.getMemberNumber();
            }
            okNum = true;
            ClassMethodId test_ = opConv_.getTest();
            if (test_ == null) {
                return;
            }
            test = test_;
            leftRes_.getImplicitsTest().add(test_);
            leftRes_.setRootNumberTest(opConv_.getRootNumberTest());
            leftRes_.setMemberNumberTest(opConv_.getMemberNumberTest());
            Mapping map_ = new Mapping();
            map_.setArg(getResultClass());
            map_.setParam(leftRes_);
            if (!AnaTemplates.isCorrectOrNumbers(map_, _conf)) {
                ClassMethodIdReturn res_ = tryGetDeclaredImplicitCast(_conf, leftRes_.getSingleNameOrEmpty(), getResultClass());
                if (res_.isFoundMethod()) {
                    converter = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                    rootNumberConv = res_.getRootNumber();
                    memberNumberConv = res_.getMemberNumber();
                } else {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(page_.getLocalizer().getCurrentFileName());
                    cast_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
                    //oper len
                    cast_.buildError(_conf.getAnalyzing().getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(getResultClass().getNames(),"&"),
                            StringList.join(leftRes_.getNames(),"&"));
                    page_.getLocalizer().addError(cast_);
                    setRelativeOffsetPossibleAnalyzable(getIndexInEl()+getOperations().getOperators().firstKey(), _conf);
                    int index_ = page_.getLocalizer().getCurrentLocationIndex();
                    errFirst.add(new PartOffset("<a title=\""+LinkageUtil.transform(cast_.getBuiltError()) +"\" class=\"e\">",index_));
                    errFirst.add(new PartOffset("</a>",index_+1));
                    okNum = false;
                }
                setResultClass(AnaClassArgumentMatching.copy(AnaTypeUtil.toPrimitive(leftRes_,page_),page_.getStandards()));
            }
            return;
        }
        okNum = true;
        if (!leftRes_.isBoolType(page_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
            un_.setFileName(page_.getLocalizer().getCurrentFileName());
            //first operator char or second operator char
            un_.buildError(_conf.getAnalyzing().getAnalysisMessages().getUnexpectedType(),
                    StringList.join(leftRes_.getNames(),"&"));
            page_.getLocalizer().addError(un_);
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+getOperations().getOperators().firstKey(), _conf);
            int index_ = page_.getLocalizer().getCurrentLocationIndex();
            errFirst.add(new PartOffset("<a title=\""+LinkageUtil.transform(un_.getBuiltError()) +"\" class=\"e\">",index_));
            errFirst.add(new PartOffset("</a>",index_+1));
            okNum = false;
        }
        if (!rightRes_.isBoolType(page_)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
            un_.setFileName(page_.getLocalizer().getCurrentFileName());
            //first operator char or second operator char
            un_.buildError(_conf.getAnalyzing().getAnalysisMessages().getUnexpectedType(),
                    StringList.join(rightRes_.getNames(),"&"));
            page_.getLocalizer().addError(un_);
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+getOperations().getOperators().firstKey(), _conf);
            int index_ = page_.getLocalizer().getCurrentLocationIndex()+1;
            errSecond.add(new PartOffset("<a title=\""+LinkageUtil.transform(un_.getBuiltError()) +"\" class=\"e\">",index_));
            errSecond.add(new PartOffset("</a>",index_+1));
            okNum = false;
        }
        leftRes_.setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
        rightRes_.setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
        left_.quickCancel();
        right_.quickCancel();
        setResultClass(AnaClassArgumentMatching.copy(leftRes_,page_.getStandards()));
    }

    public boolean isOkNum() {
        return okNum;
    }

    public CustList<PartOffset> getErrFirst() {
        return errFirst;
    }

    public CustList<PartOffset> getErrSecond() {
        return errSecond;
    }

    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }

    public ClassMethodId getTest() {
        return test;
    }

    public ClassMethodId getConverter() {
        return converter;
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
