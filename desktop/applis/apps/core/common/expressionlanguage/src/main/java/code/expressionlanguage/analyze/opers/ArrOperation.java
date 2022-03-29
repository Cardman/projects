package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.util.ClassMethodIdAncestor;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.opers.AnaArrContent;
import code.expressionlanguage.fwd.opers.AnaCallFctContent;
import code.expressionlanguage.linkage.ExportCst;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ArrOperation extends InvokingOperation implements SettableElResult,PreAnalyzableOperation,RetrieveMethod,AbstractCallLeftOperation {

    private boolean getAndSet;

    private final AnaArrContent arrContent;
    private final AnaCallFctContent callFctContent;
    private int anc;

    private boolean staticChoiceMethod;
    private String nbErr = "";
    private String methodFound = EMPTY_STRING;
    private CustList<CustList<MethodInfo>> methodInfos = new CustList<CustList<MethodInfo>>();
    private MemberId memberIdGet = new MemberId();
    private MemberId memberIdSet = new MemberId();
    private AnaTypeFct functionGet;
    private AnaTypeFct functionSet;
    private final int lastOpOffset;

    public ArrOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        callFctContent = new AnaCallFctContent("");
        arrContent = new AnaArrContent();
        lastOpOffset = _op.getOperators().lastKey();
    }

    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        ForwardOperation fwd_ = tryGetForward(this);
        boolean accessSuperTypes_ = true;
        boolean baseAccess_ = true;
        if (fwd_ != null) {
            accessSuperTypes_ = fwd_.isAccessSuperTypes();
            baseAccess_ = fwd_.isBaseAccess();
        }
        String trimMeth_ = "[]";
        AnaClassArgumentMatching class_ = getPreviousResultClass();
        String classType_ = "";
        if (fwd_ != null) {
            classType_ = fwd_.getClassType();
        }
        StringList l_;
        if (!classType_.isEmpty()) {
            l_ = new StringList(classType_);
        } else {
            l_ = class_.getNames();
        }
        StringList bounds_ = new StringList();
        for (String c: l_) {
            bounds_.addAllElts(getBounds(c, _page));
        }
        methodFound = trimMeth_;
        methodInfos = getDeclaredCustMethodByType(isStaticAccess(), bounds_, trimMeth_, false, _page, new ScopeFilter(null, baseAccess_, accessSuperTypes_, false, _page.getGlobalClass()), getFormattedFilter(_page, this));
        int len_ = methodInfos.size();
        for (int i = 0; i < len_; i++) {
            int gr_ = methodInfos.get(i).size();
            CustList<MethodInfo> newList_ = new CustList<MethodInfo>();
            for (int j = 0; j < gr_; j++) {
                MethodInfo methodInfo_ = methodInfos.get(i).get(j);
                if (!StringUtil.quickEq(methodInfo_.getConstraints().getName(),trimMeth_)) {
                    continue;
                }
                newList_.add(methodInfo_);
            }
            methodInfos.set(i, newList_);
        }
        boolean apply_ = false;
        OperationNode curPar_ = getParent();
        if (curPar_ instanceof AbstractDotOperation) {
            if (curPar_.getParent() == null) {
                apply_ = true;
            }
        }
        filterByReturnType("",apply_,methodInfos, _page, getParentMatching(this));
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        processEmptyErrorChild();
        CustList<OperationNode> chidren_ = getChildrenNodes();
        String varargParam_ = getVarargParam(chidren_);
        int varargOnly_ = lookOnlyForVarArg();
        ClassMethodIdAncestor idMethod_ = lookOnlyForId();
        ForwardOperation fwd_ = tryGetForward(this);
        boolean staticChoiceMethod_ = false;
        boolean accessSuperTypes_ = true;
        boolean baseAccess_ = true;
        if (fwd_ != null) {
            staticChoiceMethod_ = fwd_.isStaticChoiceMethod();
            accessSuperTypes_ = fwd_.isAccessSuperTypes();
            baseAccess_ = fwd_.isBaseAccess();
        }
        ClassMethodIdAncestor feed_ = null;
        ClassMethodIdAncestor feedSet_ = null;
        String trimMeth_ = "[]";
        String trimMethSet_ = "[]=";
        if (idMethod_ != null) {
            ClassMethodId id_ = idMethod_.getClassMethodId();
            String idClass_ = id_.getClassName();
            MethodId mid_ = id_.getConstraints();
            feed_ = new ClassMethodIdAncestor(new ClassMethodId(idClass_, MethodId.to(MethodAccessKind.INSTANCE, trimMeth_, mid_)),idMethod_.getAncestor());
            feedSet_ = new ClassMethodIdAncestor(new ClassMethodId(idClass_, MethodId.to(MethodAccessKind.INSTANCE, trimMethSet_, mid_)),idMethod_.getAncestor());
        }
        AnaClassArgumentMatching class_ = getPreviousResultClass();
        String classType_ = "";
        if (fwd_ != null) {
            classType_ = fwd_.getClassType();
        }
        StringList l_;
        if (!classType_.isEmpty()) {
            l_ = new StringList(classType_);
        } else {
            l_ = class_.getNames();
        }
        StringList bounds_ = new StringList();
        for (String c: l_) {
            bounds_.addAllElts(getBounds(c, _page));
        }
        NameParametersFilter name_ = buildFilter(_page);
        if (!name_.getParameterFilterErr().isEmpty()) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        ClassMethodIdReturn clMeth_ = tryGetDeclaredCustMethod(varargOnly_, isStaticAccess(),
                bounds_, trimMeth_, false,
                varargParam_, name_, _page, new ScopeFilter(feed_, baseAccess_, accessSuperTypes_, false, _page.getGlobalClass()));
        ClassMethodIdReturn clMethSet_ = tryGetDeclaredCustMethod(varargOnly_, isStaticAccess(),
                bounds_, trimMethSet_, false,
                varargParam_, name_, _page, new ScopeFilter(feedSet_, baseAccess_, accessSuperTypes_, false, _page.getGlobalClass()));
        boolean found_ = clMeth_ != null;
        if (clMethSet_ == null) {
            found_ = false;
        }
        if (found_) {
            functionGet = clMeth_.getPair();
            functionSet = clMethSet_.getPair();
            memberIdGet = clMeth_.getMemberId();
            memberIdSet = clMethSet_.getMemberId();
            if (staticChoiceMethod_) {
                if (clMeth_.isAbstractMethod()) {
                    setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _page);
                    FoundErrorInterpret abs_ = new FoundErrorInterpret();
                    abs_.setIndexFile(_page);
                    abs_.setFile(_page.getCurrentFile());
                    //method name len
                    abs_.buildError(
                            _page.getAnalysisMessages().getAbstractMethodRef(),
                            clMeth_.getRealClass(),
                            clMeth_.getRealId().getSignature(_page.getDisplayedStrings()));
                    _page.getLocalizer().addError(abs_);
                    addErr(abs_.getBuiltError());
                }
            }
            staticChoiceMethod = staticChoiceMethod_;
            anc = clMeth_.getAncestor();
            callFctContent.update(clMeth_);
            setResultClass(new AnaClassArgumentMatching(clMeth_.getReturnType(), _page.getPrimitiveTypes()));
            return;
        }
        if (chidren_.size() != 1) {
            getPartOffsetsChildren().add(new InfoErrorDto(""));
            StrTypes operators_ =  getOperations().getOperators();
            setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _page);
            FoundErrorInterpret badNb_ = new FoundErrorInterpret();
            badNb_.setFile(_page.getCurrentFile());
            badNb_.setIndexFile(_page);
            //second separator char
            badNb_.buildError(_page.getAnalysisMessages().getOperatorNbDiff(),
                    Long.toString(1),
                    Long.toString(chidren_.size()),
                    "[]"
            );
            _page.getLocalizer().addError(badNb_);
            getPartOffsetsChildren().add(new InfoErrorDto(badNb_.getBuiltError(),_page,operators_.getKey(1),1));
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        setRelativeOffsetPossibleAnalyzable(chidren_.first().getIndexInEl(), _page);
        OperationNode right_ = chidren_.last();
        if (right_ instanceof WrappOperation || right_ instanceof NamedArgumentOperation
                ||right_ instanceof FirstOptOperation || getDeltaCount(right_) != 0) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_page);
            un_.setFile(_page.getCurrentFile());
            //first separator char
            un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                    StringUtil.join(class_.getNames(),ExportCst.JOIN_TYPES));
            _page.getLocalizer().addError(un_);
            addErr(un_.getBuiltError());
            class_ = new AnaClassArgumentMatching(_page.getAliasObject());
            setResultClass(class_);
            return;
        }
        AnaClassArgumentMatching indexClass_ = right_.getResultClass();
        if (indexClass_.matchClass(_page.getAliasRange())) {
            if (!class_.isArray()) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setIndexFile(_page);
                un_.setFile(_page.getCurrentFile());
                //first separator char
                un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                        StringUtil.join(class_.getNames(),ExportCst.JOIN_TYPES));
                _page.getLocalizer().addError(un_);
                addErr(un_.getBuiltError());
                class_ = new AnaClassArgumentMatching(_page.getAliasObject());
                setResultClass(class_);
                return;
            }
            setResultClass(class_);
            return;
        }
        if (!indexClass_.isNumericInt(_page)) {
            ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_page.getAliasPrimInteger(), indexClass_, _page);
            if (res_ != null) {
                indexClass_.implicitInfos(res_);
            } else {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setIndexFile(_page);
                un_.setFile(_page.getCurrentFile());
                //first separator char
                un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                        StringUtil.join(indexClass_.getNames(),ExportCst.JOIN_TYPES));
                _page.getLocalizer().addError(un_);
                nbErr = un_.getBuiltError();
            }
        }
        if (!class_.isArray()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_page);
            un_.setFile(_page.getCurrentFile());
            //first separator char
            un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                    StringUtil.join(class_.getNames(),ExportCst.JOIN_TYPES));
            _page.getLocalizer().addError(un_);
            addErr(un_.getBuiltError());
            class_ = new AnaClassArgumentMatching(_page.getAliasObject());
            setResultClass(class_);
            return;
        }
        indexClass_.setUnwrapObject(AnaTypeUtil.toPrimitive(indexClass_, _page), _page.getPrimitiveTypes());
        class_ = AnaTypeUtil.getQuickComponentType(class_);
        class_.setUnwrapObject(class_, _page.getPrimitiveTypes());
        setResultClass(class_);
    }

    private static ForwardOperation tryGetForward(MethodOperation _operation) {
        MethodOperation par_ = _operation.getParent();
        if (par_ == null) {
            return null;
        }
        OperationNode firstChild_ = par_.getFirstChild();
        ForwardOperation elt_;
        if (!(firstChild_ instanceof AbstractDotOperation)) {
            elt_ = castTo(firstChild_);
        } else {
            OperationNode beforeLast_ = ((MethodOperation)firstChild_).getChildrenNodes().last();
            elt_ = castTo(beforeLast_);
        }
        return elt_;
    }
    private static ForwardOperation castTo(OperationNode _op) {
        if (_op instanceof ForwardOperation) {
            return (ForwardOperation) _op;
        }
        return null;
    }

    public boolean isGetAndSet() {
        return getAndSet;
    }

    public AnaTypeFct getFunctionGet() {
        return functionGet;
    }

    public AnaTypeFct getFunctionSet() {
        return functionSet;
    }

    @Override
    public void setVariable(boolean _variable) {
        getAndSet = true;
        arrContent.setVariable(_variable);
    }

    @Override
    public boolean isErrLeftValue() {
        return false;
    }

    public AnaArrContent getArrContent() {
        return arrContent;
    }

    public boolean isStaticChoiceMethod() {
        return staticChoiceMethod;
    }

    public int getAnc() {
        return anc;
    }

    public String getNbErr() {
        return nbErr;
    }

    @Override
    public String getMethodFound() {
        return methodFound;
    }

    @Override
    public CustList<CustList<MethodInfo>> getMethodInfos() {
        return methodInfos;
    }

    public MemberId getMemberIdGet() {
        return memberIdGet;
    }

    public MemberId getMemberIdSet() {
        return memberIdSet;
    }

    public AnaCallFctContent getCallFctContent() {
        return callFctContent;
    }

    public int getLastOpOffset() {
        return lastOpOffset;
    }
}
