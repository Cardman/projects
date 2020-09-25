package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AnalyzedBlock;
import code.expressionlanguage.analyze.blocks.ReturnMethod;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.opers.util.NameParametersFilter;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.util.ClassMethodIdAncestor;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.expressionlanguage.fwd.opers.AnaCallFctContent;
import code.util.CustList;
import code.util.StringList;

public final class ExplicitOperatorOperation extends InvokingOperation implements PreAnalyzableOperation,RetrieveMethod {
    private AnaCallFctContent callFctContent;

    private int offsetOper;
    private String from;

    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    private String methodFound = EMPTY_STRING;
    private CustList<CustList<MethodInfo>> methodInfos = new CustList<CustList<MethodInfo>>();

    public ExplicitOperatorOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        callFctContent = new AnaCallFctContent(getOperations().getFctName());
        offsetOper = getOperations().getOperators().firstKey();
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
            int off_ = StringList.getFirstPrintableCharIndex(args_.get(1));
            String fromType_ = StringExpUtil.removeDottedSpaces(args_.get(1));
            from = ResolvingImportTypes.resolveCorrectTypeAccessible(off_+ callFctContent.getMethodName().indexOf(',')+1,fromType_, _page);
            partOffsets.addAllElts(_page.getCurrentParts());
        }
        if (from.isEmpty()) {
            methodFound = op_;
            CustList<MethodInfo> ops_ = getOperators(null, _page);
            methodInfos.add(ops_);
        } else {
            methodFound = op_;
            methodInfos = getDeclaredCustMethodByType(MethodAccessKind.STATIC_CALL, false,false,new StringList(from), op_, false,null, _page);
        }
        int len_ = methodInfos.size();
        for (int i = 0; i < len_; i++) {
            int gr_ = methodInfos.get(i).size();
            CustList<MethodInfo> newList_ = new CustList<MethodInfo>();
            for (int j = 0; j < gr_; j++) {
                MethodInfo methodInfo_ = methodInfos.get(i).get(j);
                if (!StringList.quickEq(methodInfo_.getConstraints().getName(),op_)) {
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
        String typeAff_ = EMPTY_STRING;
        AnalyzedBlock cur_ = _page.getCurrentAnaBlock();
        if (apply_ && cur_ instanceof ReturnMethod) {
            typeAff_ = tryGetRetType(_page);
        }
        filterByReturnType(typeAff_,methodInfos, _page);
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
                    Integer.toString(2),
                    Integer.toString(args_.size())
            );
            _page.getLocalizer().addError(badCall_);
            getErrs().add(badCall_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getStandards().getAliasObject()));
            return;
        }
        ClassMethodId id_ = null;
        if (idMethod_ != null) {
            id_ = idMethod_.getClassMethodId();
            MethodId s_ = id_.getConstraints();
            MethodAccessKind static_ = MethodId.getKind(isStaticAccess(), s_.getKind());
            id_ = new ClassMethodId(from,new MethodId(static_,op_,s_.getParametersTypes(),s_.isVararg()));
        }
        NameParametersFilter name_ = buildFilter(_page);
        if (!name_.isOk()) {
            setResultClass(new AnaClassArgumentMatching(_page.getStandards().getAliasObject()));
            return;
        }
        ClassMethodIdReturn cust_;
        if (from.isEmpty()) {
            cust_ = getOperator(id_,varargOnly_,false,op_, varargParam_, name_, _page);
        } else {
            cust_ = tryGetDeclaredCustMethod(-1, MethodAccessKind.STATIC_CALL,
                    false, new StringList(from), op_, false, false, false, null,
                    varargParam_, name_, _page);
        }
        if (!cust_.isFoundMethod()) {
            FoundErrorInterpret undefined_ = new FoundErrorInterpret();
            undefined_.setFileName(_page.getLocalizer().getCurrentFileName());
            undefined_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //_name len
            StringList classesNames_ = new StringList();
            for (OperationNode c: name_.getAll()) {
                classesNames_.add(StringList.join(c.getResultClass().getNames(), "&"));
            }
            undefined_.buildError(_page.getAnalysisMessages().getUndefinedMethod(),
                    new MethodId(MethodAccessKind.STATIC, cl_, classesNames_).getSignature(_page));
            _page.getLocalizer().addError(undefined_);
            getErrs().add(undefined_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getStandards().getAliasObject()));
            return;
        }
        setResultClass(new AnaClassArgumentMatching(cust_.getReturnType(), _page.getStandards()));
        String foundClass_ = cust_.getRealClass();
        MethodId realId_ = cust_.getRealId();
        if (realId_.getKind() != MethodAccessKind.STATIC_CALL) {
            foundClass_ = StringExpUtil.getIdFromAllTypes(foundClass_);
        }
        callFctContent.setRootNumber(cust_.getRootNumber());
        callFctContent.setMemberNumber(cust_.getMemberNumber());
        callFctContent.setClassMethodId(new ClassMethodId(foundClass_, cust_.getRealId()));
        if (cust_.isVarArgToCall()) {
            StringList paramtTypes_ = cust_.getRealId().getParametersTypes();
            callFctContent.setNaturalVararg(paramtTypes_.size() - 1);
            callFctContent.setLastType(paramtTypes_.last());
        }
        unwrapArgsFct(realId_, callFctContent.getNaturalVararg(), callFctContent.getLastType(), name_.getAll(), _page);
    }


    public int getOffsetOper() {
        return offsetOper;
    }

    public ClassMethodId getClassMethodId() {
        return callFctContent.getClassMethodId();
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

    public int getMemberNumber() {
        return callFctContent.getMemberNumber();
    }

    public int getRootNumber() {
        return callFctContent.getRootNumber();
    }

    public AnaCallFctContent getCallFctContent() {
        return callFctContent;
    }
}
