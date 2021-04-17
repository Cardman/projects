package code.expressionlanguage.analyze.opers;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.opers.util.MemberId;
import code.expressionlanguage.analyze.opers.util.OperatorConverter;
import code.expressionlanguage.analyze.opers.util.ClassMethodIdMemberIdTypeFct;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;


public abstract class QuickOperation extends MethodOperation {

    private boolean okNum;
    private final ClassMethodIdMemberIdTypeFct fct = new ClassMethodIdMemberIdTypeFct();
    private final ClassMethodIdMemberIdTypeFct conv = new ClassMethodIdMemberIdTypeFct();
    private AnaTypeFct functionTest;
    private final StringList errFirst = new StringList();
    private final StringList errSecond = new StringList();

    private int opOffset;

    public QuickOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    final void calculateChildren() {
        StrTypes vs_ = getOperations().getValues();
        getChildren().addAllEntries(vs_);
    }

    @Override
    public final void analyze(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode left_ = chidren_.first();
        OperationNode right_ = chidren_.last();
        opOffset = right_.getIndexInEl();
        AnaClassArgumentMatching leftRes_ = left_.getResultClass();
        AnaClassArgumentMatching rightRes_ = right_.getResultClass();
        String oper_ = getOperations().getOperators().firstValue();
        OperatorConverter opConv_ = getBinaryOperatorOrMethod(this, left_, right_, oper_, _page);
        if (opConv_ != null) {
            fct.infos(opConv_,_page);
            okNum = true;
            ClassMethodId test_ = opConv_.getTest();
            if (test_ == null) {
                return;
            }
            functionTest = opConv_.getFunctionTest();
            leftRes_.implicitInfosTest(opConv_);
            Mapping map_ = new Mapping();
            map_.setArg(getResultClass());
            map_.setParam(leftRes_);
            if (!AnaInherits.isCorrectOrNumbers(map_, _page)) {
                ClassMethodIdReturn res_ = tryGetDeclaredImplicitCast(leftRes_.getSingleNameOrEmpty(), getResultClass(), _page);
                if (res_.isFoundMethod()) {
                    conv.infos(res_);
                } else {
                    setRelativeOffsetPossibleAnalyzable(getIndexInEl()+getOperations().getOperators().firstKey(), _page);
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_page.getLocalizer().getCurrentFileName());
                    cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    //oper len
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringUtil.join(getResultClass().getNames(),ExportCst.JOIN_TYPES),
                            StringUtil.join(leftRes_.getNames(),ExportCst.JOIN_TYPES));
                    _page.getLocalizer().addError(cast_);
                    errFirst.add(cast_.getBuiltError());
                    okNum = false;
                }
                setResultClass(AnaClassArgumentMatching.copy(AnaTypeUtil.toPrimitive(leftRes_, _page), _page.getPrimitiveTypes()));
            }
            return;
        }
        okNum = true;
        if (!leftRes_.isBoolType(_page)) {
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+getOperations().getOperators().firstKey(), _page);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            //first operator char or second operator char
            un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                    StringUtil.join(leftRes_.getNames(),ExportCst.JOIN_TYPES));
            _page.getLocalizer().addError(un_);
            errFirst.add(un_.getBuiltError());
            okNum = false;
        }
        if (!rightRes_.isBoolType(_page)) {
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+getOperations().getOperators().firstKey(), _page);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            //first operator char or second operator char
            un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                    StringUtil.join(rightRes_.getNames(), ExportCst.JOIN_TYPES));
            _page.getLocalizer().addError(un_);
            errSecond.add(un_.getBuiltError());
            okNum = false;
        }
        leftRes_.setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
        rightRes_.setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
        setResultClass(AnaClassArgumentMatching.copy(leftRes_, _page.getPrimitiveTypes()));
    }

    public boolean isOkNum() {
        return okNum;
    }

    public StringList getErrFirst() {
        return errFirst;
    }

    public StringList getErrSecond() {
        return errSecond;
    }

    public ClassMethodIdMemberIdTypeFct getConv() {
        return conv;
    }

    public ClassMethodIdMemberIdTypeFct getFct() {
        return fct;
    }
    public AnaTypeFct getFunctionTest() {
        return functionTest;
    }

    public AnaTypeFct getConvert() {
        return conv.getFunction();
    }

    public int getOpOffset() {
        return opOffset;
    }
}
