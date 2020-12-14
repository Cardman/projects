package code.expressionlanguage.analyze.opers;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.util.ClassMethodIdAncestor;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.fwd.opers.AnaArrContent;
import code.expressionlanguage.fwd.opers.AnaCallFctContent;
import code.expressionlanguage.linkage.LinkageUtil;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ArrOperation extends InvokingOperation implements SettableElResult,PreAnalyzableOperation,RetrieveMethod {

    private boolean getAndSet;

    private AnaArrContent arrContent;
    private AnaCallFctContent callFctContent;
    private int anc;

    private boolean staticChoiceMethod;
    private String nbErr = "";
    private String methodFound = EMPTY_STRING;
    private CustList<CustList<MethodInfo>> methodInfos = new CustList<CustList<MethodInfo>>();
    private MemberId memberIdGet = new MemberId();
    private MemberId memberIdSet = new MemberId();
    private AnaTypeFct converter;
    private AnaTypeFct functionGet;
    private AnaTypeFct functionSet;

    public ArrOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        callFctContent = new AnaCallFctContent("");
        arrContent = new AnaArrContent();
    }

    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        ForwardOperation fwd_ = tryGetForward(this);
        boolean accessSuperTypes_ = true;
        boolean accessFromSuper_ = false;
        if (fwd_ != null) {
            accessSuperTypes_ = fwd_.isAccessSuperTypes();
            accessFromSuper_ = fwd_.isAccessFromSuper();
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
        methodInfos = getDeclaredCustMethodByType(isStaticAccess(), bounds_, trimMeth_, false, _page, new ScopeFilter(null, accessFromSuper_, accessSuperTypes_, false, _page.getGlobalClass()));
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
        filterByReturnType(apply_,methodInfos, _page, getParentMatching());
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
        boolean accessFromSuper_ = false;
        if (fwd_ != null) {
            staticChoiceMethod_ = fwd_.isStaticChoiceMethod();
            accessSuperTypes_ = fwd_.isAccessSuperTypes();
            accessFromSuper_ = fwd_.isAccessFromSuper();
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
        if (!name_.isOk()) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        ClassMethodIdReturn clMeth_ = tryGetDeclaredCustMethod(varargOnly_, isStaticAccess(),
                bounds_, trimMeth_, false,
                varargParam_, name_, _page, new ScopeFilter(feed_, accessFromSuper_, accessSuperTypes_, false, _page.getGlobalClass()));
        ClassMethodIdReturn clMethSet_ = tryGetDeclaredCustMethod(varargOnly_, isStaticAccess(),
                bounds_, trimMethSet_, false,
                varargParam_, name_, _page, new ScopeFilter(feedSet_, accessFromSuper_, accessSuperTypes_, false, _page.getGlobalClass()));
        boolean found_ = true;
        if (!clMeth_.isFoundMethod()) {
            found_ = false;
        }
        if (!clMethSet_.isFoundMethod()) {
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
                    abs_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    abs_.setFileName(_page.getLocalizer().getCurrentFileName());
                    //method name len
                    abs_.buildError(
                            _page.getAnalysisMessages().getAbstractMethodRef(),
                            clMeth_.getRealClass(),
                            clMeth_.getRealId().getSignature(_page));
                    _page.getLocalizer().addError(abs_);
                    addErr(abs_.getBuiltError());
                }
            }
            staticChoiceMethod = staticChoiceMethod_;
            anc = clMeth_.getAncestor();
            String foundClass_ = clMeth_.getRealClass();
            if (!staticChoiceMethod_) {
                foundClass_ = StringExpUtil.getIdFromAllTypes(foundClass_);
            }
            MethodId id_ = clMeth_.getRealId();
            callFctContent.setClassMethodId(new ClassMethodId(foundClass_, id_));
            callFctContent.setClassName(foundClass_);
            MethodId realId_ = clMeth_.getRealId();
            if (clMeth_.isVarArgToCall()) {
                StringList paramtTypes_ = clMeth_.getRealId().getParametersTypes();
                callFctContent.setNaturalVararg(paramtTypes_.size() - 1);
                callFctContent.setLastType(paramtTypes_.last());
            }
            unwrapArgsFct(realId_, callFctContent.getNaturalVararg(), callFctContent.getLastType(), name_.getAll(), _page);
            setResultClass(new AnaClassArgumentMatching(clMeth_.getReturnType(), _page.getPrimitiveTypes()));
            return;
        }
        if (chidren_.size() != 1) {
            getPartOffsetsChildren().add(new CustList<PartOffset>());
            IntTreeMap<String> operators_ =  getOperations().getOperators();
            setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _page);
            int i_ = _page.getLocalizer().getCurrentLocationIndex() + operators_.getKey(1);
            FoundErrorInterpret badNb_ = new FoundErrorInterpret();
            badNb_.setFileName(_page.getLocalizer().getCurrentFileName());
            badNb_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //second separator char
            badNb_.buildError(_page.getAnalysisMessages().getOperatorNbDiff(),
                    Long.toString(1),
                    Long.toString(chidren_.size()),
                    "[]"
            );
            _page.getLocalizer().addError(badNb_);
            CustList<PartOffset> list_ = new CustList<PartOffset>();
            list_.add(new PartOffset("<a title=\""+LinkageUtil.transform(badNb_.getBuiltError()) +"\" class=\"e\">",i_));
            list_.add(new PartOffset("</a>",i_+ 1));
            getPartOffsetsChildren().add(list_);
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        OperationNode right_ = chidren_.last();
        if (right_ instanceof WrappOperation || right_ instanceof NamedArgumentOperation
                ||right_ instanceof FirstOptOperation || right_ instanceof IdFctOperation
                ||right_ instanceof VarargOperation) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            //first separator char
            un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                    StringUtil.join(class_.getNames(),"&"));
            _page.getLocalizer().addError(un_);
            addErr(un_.getBuiltError());
            class_ = new AnaClassArgumentMatching(_page.getAliasObject());
            setResultClass(class_);
            return;
        }
        AnaClassArgumentMatching indexClass_ = right_.getResultClass();
        setRelativeOffsetPossibleAnalyzable(right_.getIndexInEl(), _page);
        if (!indexClass_.isNumericInt(_page)) {
            ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_page.getAliasPrimInteger(), indexClass_, _page);
            if (res_.isFoundMethod()) {
                ClassMethodId cl_ = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                indexClass_.getImplicits().add(cl_);
                indexClass_.setMemberId(res_.getMemberId());
                converter = res_.getPair();
            } else {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                un_.setFileName(_page.getLocalizer().getCurrentFileName());
                //first separator char
                un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                        StringUtil.join(indexClass_.getNames(),"&"));
                _page.getLocalizer().addError(un_);
                nbErr = un_.getBuiltError();
            }
        }
        setRelativeOffsetPossibleAnalyzable(chidren_.first().getIndexInEl(), _page);
        if (!class_.isArray()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            //first separator char
            un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                    StringUtil.join(class_.getNames(),"&"));
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
    public void setCatenizeStrings() {
        arrContent.setCatString(true);
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

    public AnaTypeFct getConverter() {
        return converter;
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
}
