package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.util.ClassMethodIdAncestor;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.fwd.opers.AnaArrContent;
import code.expressionlanguage.fwd.opers.AnaCallFctContent;
import code.expressionlanguage.linkage.ExportCst;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ExplicitOperatorOperation extends InvokingOperation implements PreAnalyzableOperation,RetrieveMethod,AbstractCallLeftOperation,SettableElResult {
    private final AnaCallFctContent callFctContent;
    private final AnaArrContent arrContent;

    private final int offsetOper;
    private String from;

    private final CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    private String methodFound = EMPTY_STRING;
    private CustList<CustList<MethodInfo>> methodInfos = new CustList<CustList<MethodInfo>>();

    public ExplicitOperatorOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        callFctContent = new AnaCallFctContent(getOperations().getFctName());
        offsetOper = getOperations().getOperators().firstKey();
        arrContent = new AnaArrContent();
    }

    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        String cl_ = callFctContent.getMethodName();
        setStaticAccess(_page.getStaticContext());
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _page);
        cl_ = cl_.substring(cl_.indexOf(PAR_LEFT)+1, cl_.lastIndexOf(PAR_RIGHT));
        StringList args_ = StringExpUtil.getAllSepCommaTypes(cl_);
        from = "";
        String op_ = args_.first();
        if (args_.size() > 1) {
            int off_ = StringUtil.getFirstPrintableCharIndex(args_.get(1));
            String fromType_ = args_.get(1).trim();
            from = ResolvingTypes.resolveCorrectTypeAccessible(off_+ callFctContent.getMethodName().indexOf(',')+1,fromType_, _page);
            partOffsets.addAllElts(_page.getCurrentParts());
        }
        if (from.isEmpty()) {
            methodFound = op_;
            CustList<MethodInfo> ops_ = getOperators(isLvalue(), null, _page);
            methodInfos.add(ops_);
        } else {
            methodFound = op_;
            methodInfos = getDeclaredCustMethodByType(MethodAccessKind.STATIC_CALL, new StringList(from), op_, false, _page, new ScopeFilter(null, true, false, isLvalue(), _page.getGlobalClass()), getFormattedFilter(_page, this));
        }
        int len_ = methodInfos.size();
        for (int i = 0; i < len_; i++) {
            int gr_ = methodInfos.get(i).size();
            CustList<MethodInfo> newList_ = new CustList<MethodInfo>();
            for (int j = 0; j < gr_; j++) {
                MethodInfo methodInfo_ = methodInfos.get(i).get(j);
                if (!StringUtil.quickEq(methodInfo_.getConstraints().getName(),op_)) {
                    continue;
                }
                newList_.add(methodInfo_);
            }
            methodInfos.set(i, newList_);
        }
        boolean apply_ = false;
        OperationNode curPar_ = getParent();
        if (curPar_ == null){
            apply_ = true;
        }
        filterByReturnType("",apply_,methodInfos, _page, getParentMatching(this));
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        String cl_ = callFctContent.getMethodName();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _page);
        cl_ = cl_.substring(cl_.indexOf(PAR_LEFT)+1, cl_.lastIndexOf(PAR_RIGHT));
        StringList args_ = StringExpUtil.getAllSepCommaTypes(cl_);
        String op_ = args_.first();
        int varargOnly_ = lookOnlyForVarArg();
        ClassMethodIdAncestor idMethod_ = lookOnlyForId();
        CustList<OperationNode> chidren_ = getChildrenNodes();
        String varargParam_ = getVarargParam(chidren_);
        if (args_.size() > 2) {
            FoundErrorInterpret badCall_ = new FoundErrorInterpret();
            badCall_.setFileName(_page.getLocalizer().getCurrentFileName());
            badCall_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len
            badCall_.buildError(_page.getAnalysisMessages().getSplitComaLow(),
                    Long.toString(2),
                    Long.toString(args_.size())
            );
            _page.getLocalizer().addError(badCall_);
            addErr(badCall_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        ClassMethodId id_ = null;
        if (idMethod_ != null) {
            id_ = idMethod_.getClassMethodId();
            MethodId s_ = id_.getConstraints();
            MethodAccessKind static_ = MethodId.getKind(isStaticAccess(), s_.getKind());
            id_ = new ClassMethodId(from,MethodId.to(static_,op_,s_));
        }
        NameParametersFilter name_ = buildFilter(_page);
        if (!name_.getParameterFilterErr().isEmpty()) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        if (StringExpUtil.isWildCard(from)) {
            FoundErrorInterpret badAccess_ = new FoundErrorInterpret();
            badAccess_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            badAccess_.setFileName(_page.getLocalizer().getCurrentFileName());
            //type len
            badAccess_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                    from);
            _page.getLocalizer().addError(badAccess_);
            addErr(badAccess_.getBuiltError());
        }
        ClassMethodIdReturn cust_;
        if (from.isEmpty()) {
            cust_ = getOperator(isLvalue(), id_,varargOnly_, op_, varargParam_, name_, _page);
        } else {
            cust_ = tryGetDeclaredCustMethod(-1, MethodAccessKind.STATIC_CALL,
                    new StringList(from), op_, false,
                    varargParam_, name_, _page, new ScopeFilter(null, true, false, isLvalue(), _page.getGlobalClass()));
        }
        if (cust_ == null) {
            FoundErrorInterpret undefined_ = new FoundErrorInterpret();
            undefined_.setFileName(_page.getLocalizer().getCurrentFileName());
            undefined_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //_name len
            StringList classesNames_ = new StringList();
            for (OperationNode c: name_.getAll()) {
                classesNames_.add(StringUtil.join(c.getResultClass().getNames(), ExportCst.JOIN_TYPES));
            }
            undefined_.buildError(_page.getAnalysisMessages().getUndefinedMethod(),
                    new MethodId(MethodAccessKind.STATIC, cl_, classesNames_).getSignature(_page));
            _page.getLocalizer().addError(undefined_);
            addErr(undefined_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        setResultClass(new AnaClassArgumentMatching(cust_.getReturnType(), _page.getPrimitiveTypes()));
        callFctContent.update(cust_);
        MethodId realId_ = cust_.getRealId();
        unwrapArgsFct(realId_, callFctContent.getNaturalVararg(), callFctContent.getLastType(), name_.getAll(), _page);
    }

    public int getOffsetOper() {
        return offsetOper;
    }

    public String getFrom() {
        return from;
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }

    public String getMethodFound() {
        return methodFound;
    }

    public CustList<CustList<MethodInfo>> getMethodInfos() {
        return methodInfos;
    }

    public AnaCallFctContent getCallFctContent() {
        return callFctContent;
    }

    @Override
    public boolean isErrLeftValue() {
        return false;
    }

    @Override
    public AnaArrContent getArrContent() {
        return arrContent;
    }

    @Override
    public void setVariable(boolean _variable) {
        arrContent.setVariable(_variable);
    }

}
