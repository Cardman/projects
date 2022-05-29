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
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.IdentifiableUtil;
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
    private final AnaCallFctContent callFctContentSet;
    private int anc;
    private int ancSet;

    private boolean staticChoiceMethod;
    private String nbErr = "";
    private String methodFound = EMPTY_STRING;
    private CustList<CustList<MethodInfo>> methodInfos = new CustList<CustList<MethodInfo>>();
    private MemberId memberIdGet = new MemberId();
    private MemberId memberIdSet = new MemberId();
    private AnaTypeFct functionGet;
    private AnaTypeFct functionSet;
    private final int lastOpOffset;
    private boolean fromArray;
    private ClassMethodIdReturn resMemoGet;
    private ClassMethodIdReturn resMemoSet;
    private String returnSet = "";
    private ClassMethodIdReturn resErrSet;

    public ArrOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        callFctContent = new AnaCallFctContent("");
        callFctContentSet = new AnaCallFctContent("");
        arrContent = new AnaArrContent();
        lastOpOffset = _op.getOperators().lastKey();
    }

    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        ForwardOperation fwd_ = tryGetForward(this);
        boolean staticChoiceMethod_ = false;
        boolean accessSuperTypes_ = true;
        boolean baseAccess_ = true;
        if (fwd_ != null) {
            staticChoiceMethod_ = fwd_.isStaticChoiceMethod();
            accessSuperTypes_ = fwd_.isAccessSuperTypes();
            baseAccess_ = fwd_.isBaseAccess();
        }
        boolean excAbs_ = staticChoiceMethod_;
        String trimMeth_ = "[]";
        if (parSet() instanceof AffectationOperation) {
            trimMeth_ = "[]=";
        }
        AnaClassArgumentMatching class_ = getPreviousResultClass();
        fromArray = class_.isArray();
        StringList bounds_ = bounds(_page, fwd_, class_);
        methodFound = trimMeth_;
        methodInfos = getDeclaredCustMethodByType(isStaticAccess(), bounds_, trimMeth_, false, _page, new ScopeFilter(null, baseAccess_, accessSuperTypes_, false,excAbs_, _page.getGlobalClass()), getFormattedFilter(_page, this));
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
        if (curPar_ instanceof AbstractDotOperation && curPar_.getParent() == null) {
            apply_ = true;
        }
        filterByReturnType("",apply_,methodInfos, _page, getParentMatching(this));
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        processEmptyErrorChild();
        if (!fromArray) {
            indexers(_page);
            return;
        }

        CustList<OperationNode> chidren_ = getChildrenNodes();
        if (chidren_.size() != 1) {
            getPartOffsetsChildren().add(new InfoErrorDto(""));
            StrTypes operators_ =  getOperators();
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
            getPartOffsetsChildren().add(new InfoErrorDto(badNb_,_page,operators_.getKey(1),1));
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        setRelativeOffsetPossibleAnalyzable(chidren_.first().getIndexInEl(), _page);
        AnaClassArgumentMatching class_ = getPreviousResultClass();
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
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        AnaClassArgumentMatching indexClass_ = right_.getResultClass();
        if (indexClass_.matchClass(_page.getAliasRange())) {
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
        indexClass_.setUnwrapObject(AnaTypeUtil.toPrimitive(indexClass_, _page), _page.getPrimitiveTypes());
        AnaClassArgumentMatching classComp_ = AnaTypeUtil.getQuickComponentType(class_);
        classComp_.setUnwrapObject(classComp_, _page.getPrimitiveTypes());
        setResultClass(classComp_);
    }

    private void indexers(AnalyzedPageEl _page) {
        AnaClassArgumentMatching class_ = getPreviousResultClass();
        CustList<OperationNode> chidren_ = getChildrenNodes();
        String varargParam_ = getVarargParam(chidren_);
        int varargOnly_ = lookOnlyForVarArg();
        ForwardOperation fwd_ = tryGetForward(this);
        boolean staticChoiceMethod_ = false;
        boolean accessSuperTypes_ = true;
        boolean baseAccess_ = true;
        if (fwd_ != null) {
            staticChoiceMethod_ = fwd_.isStaticChoiceMethod();
            accessSuperTypes_ = fwd_.isAccessSuperTypes();
            baseAccess_ = fwd_.isBaseAccess();
        }
        staticChoiceMethod = staticChoiceMethod_;
        String trimMeth_ = "[]";
        String trimMethSet_ = "[]=";
        StringList bounds_ = bounds(_page, fwd_, class_);
        NameParametersFilter name_ = buildFilter(_page);
        if (!name_.getParameterFilterErr().isEmpty()) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        if (parSet() instanceof AffectationOperation) {
            ClassMethodIdAncestor feedSet_ = getId(trimMethSet_);
            ClassMethodIdReturn clMethSet_ = tryGet(varargOnly_, trimMethSet_, varargParam_, name_, _page, getDeclaredCustMethodByType(isStaticAccess(), bounds_, trimMethSet_, false, name_, _page, new ScopeFilter(feedSet_, baseAccess_, accessSuperTypes_, false, staticChoiceMethod_, _page.getGlobalClass())));
            if (clMethSet_ != null) {
                functionSet = clMethSet_.getParametrableContent().getPair();
                returnSet = MethodInfo.retIndexSet(clMethSet_,_page);
                memberIdSet = clMethSet_.getParametrableContent().getMemberId();
                ancSet = clMethSet_.getAncestor();
                callFctContentSet.update(clMethSet_);
                setResultClass(new AnaClassArgumentMatching(returnSet, _page.getPrimitiveTypes()));
                return;
            }
            resErrSet = tryGet(varargOnly_, trimMethSet_, varargParam_, name_, _page, getDeclaredCustMethodByType(isStaticAccess(), bounds_, trimMethSet_, false, name_, _page, new ScopeFilter(feedSet_, baseAccess_, accessSuperTypes_, false, _page.getGlobalClass())));
            errIndexer(_page, resErrSet);
            return;
        }
        ClassMethodIdAncestor feed_ = getId(trimMeth_);
        ClassMethodIdReturn clMeth_ = tryGet(varargOnly_, trimMeth_, varargParam_, name_, _page, getDeclaredCustMethodByType(isStaticAccess(), bounds_, trimMeth_, false, name_, _page, new ScopeFilter(feed_, baseAccess_, accessSuperTypes_, false, staticChoiceMethod_, _page.getGlobalClass())));
        if (clMeth_ != null) {
            resMemoGet = clMeth_;
            MethodId formattedId_ = clMeth_.getId().getConstraints();
            StringList clsFormatted_ = new StringList();
            IdentifiableUtil.appendLeftPart(0,clsFormatted_,formattedId_);
            ClassMethodIdAncestor feedGetSet_ = getSetId(trimMethSet_);
            ClassMethodIdReturn clSet_ = tryGetDeclaredCustMethodSetIndexer(isStaticAccess(), new StringList(clMeth_.getFormattedType().getFormatted()), trimMethSet_, clsFormatted_, _page, new ScopeFilter(feedGetSet_, baseAccess_, accessSuperTypes_, false, staticChoiceMethod_, _page.getGlobalClass()));
            if (clSet_ != null) {
                resMemoSet = clSet_;
                returnSet = MethodInfo.retIndexSet(clSet_,_page);
            }
            resErrSet = tryGetDeclaredCustMethodSetIndexer(isStaticAccess(), new StringList(clMeth_.getFormattedType().getFormatted()), trimMethSet_, clsFormatted_, _page, new ScopeFilter(feedGetSet_, baseAccess_, accessSuperTypes_, false, _page.getGlobalClass()));
            anc = clMeth_.getAncestor();
            functionGet = clMeth_.getParametrableContent().getPair();
            memberIdGet = clMeth_.getParametrableContent().getMemberId();
            callFctContent.update(clMeth_);
            setResultClass(new AnaClassArgumentMatching(clMeth_.getReturnType(), _page.getPrimitiveTypes()));
            return;
        }
        ClassMethodIdReturn clMeth2_ = tryGet(varargOnly_, trimMeth_, varargParam_, name_, _page, getDeclaredCustMethodByType(isStaticAccess(), bounds_, trimMeth_, false, name_, _page, new ScopeFilter(feed_, baseAccess_, accessSuperTypes_, false, _page.getGlobalClass())));
        if (clMeth2_ != null) {
            functionGet = clMeth2_.getParametrableContent().getPair();
            memberIdGet = clMeth2_.getParametrableContent().getMemberId();
        }
        errIndexer(_page, clMeth2_);
    }

    private StringList bounds(AnalyzedPageEl _page, ForwardOperation _fwd, AnaClassArgumentMatching _cl) {
        String classType_ = "";
        if (_fwd != null) {
            classType_ = _fwd.getClassType();
        }
        StringList l_;
        if (!classType_.isEmpty()) {
            l_ = new StringList(classType_);
        } else {
            l_ = _cl.getNames();
        }
        StringList bounds_ = new StringList();
        for (String c: l_) {
            bounds_.addAllElts(getBounds(c, _page));
        }
        return bounds_;
    }

    private ClassMethodIdAncestor getId(String _tr) {
        ClassMethodIdAncestor idMethod_ = lookOnlyForId();
        return getId(_tr, idMethod_);
    }

    private ClassMethodIdAncestor getSetId(String _tr) {
        ClassMethodIdAncestor idMethodSet_ = lookOnlyForIdSet();
        return getId(_tr, idMethodSet_);
    }

    private static ClassMethodIdAncestor getId(String _tr, ClassMethodIdAncestor _idMeth) {
        ClassMethodIdAncestor feed_ = null;
        if (_idMeth != null) {
            ClassMethodId id_ = _idMeth.getClassMethodId();
            String idClass_ = id_.getClassName();
            AnaGeneType gene_ = _idMeth.getGt();
            MethodId mid_ = id_.getConstraints();
            feed_ = new ClassMethodIdAncestor(gene_,new ClassMethodId(idClass_, MethodId.to(MethodAccessKind.INSTANCE, _tr, mid_)), _idMeth.getAncestor());
        }
        return feed_;
    }

    public void applySet(AnalyzedPageEl _page) {
        if (resMemoSet == null) {
            errIndexer(_page, resErrSet);
            return;
        }
        functionSet = resMemoSet.getParametrableContent().getPair();
        memberIdSet = resMemoSet.getParametrableContent().getMemberId();
        callFctContentSet.update(resMemoSet);
        feedNamedParamsMethod(resMemoGet.getIndexesParams(),functionSet.getFunction(),resMemoGet.getFilter());
    }

    private void errIndexer(AnalyzedPageEl _page, ClassMethodIdReturn _resEr) {
        if (_resEr != null) {
            setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _page);
            FoundErrorInterpret abs_ = new FoundErrorInterpret();
            abs_.setIndexFile(_page);
            abs_.setFile(_page.getCurrentFile());
            //method name len
            abs_.buildError(
                    _page.getAnalysisMessages().getAbstractMethodRef(),
                    _resEr.getRealClass(),
                    _resEr.getRealId().getSignature(_page.getDisplayedStrings()));
            _page.getLocalizer().addError(abs_);
            addErr(abs_.getBuiltError());
            setResultClass(voidToObject(new AnaClassArgumentMatching(_resEr.getReturnType()), _page));
        } else {
            AnaClassArgumentMatching class_ = getPreviousResultClass();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_page);
            un_.setFile(_page.getCurrentFile());//first separator char
            un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                    StringUtil.join(class_.getNames(), ExportCst.JOIN_TYPES));
            _page.getLocalizer().addError(un_);
            addErr(un_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
        }
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

    public boolean isFromArray() {
        return fromArray;
    }

    public String getReturnSet() {
        return returnSet;
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

    public int getAncSet() {
        return ancSet;
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

    public AnaCallFctContent getCallFctContentSet() {
        return callFctContentSet;
    }

    public int getLastOpOffset() {
        return lastOpOffset;
    }
}
