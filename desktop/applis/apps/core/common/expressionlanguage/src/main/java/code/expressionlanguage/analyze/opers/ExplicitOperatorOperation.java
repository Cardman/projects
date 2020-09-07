package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.AnalyzedBlock;
import code.expressionlanguage.analyze.blocks.ReturnMethod;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.opers.util.NameParametersFilter;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.expressionlanguage.options.KeyWords;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class ExplicitOperatorOperation extends InvokingOperation implements PreAnalyzableOperation,RetrieveMethod {
    private ClassMethodId classMethodId;
    private int rootNumber = -1;
    private int memberNumber = -1;
    private String methodName;
    private String lastType = EMPTY_STRING;

    private int naturalVararg = -1;

    private int offsetOper;
    private String from;

    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    private String methodFound = EMPTY_STRING;
    private CustList<CustList<MethodInfo>> methodInfos = new CustList<CustList<MethodInfo>>();

    public ExplicitOperatorOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        methodName = getOperations().getFctName();
        offsetOper = getOperations().getOperators().firstKey();
    }

    @Override
    public void preAnalyze(ContextEl _conf) {
        String cl_ = methodName;
        setStaticAccess(_conf.getAnalyzing().getStaticContext());
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
        cl_ = cl_.substring(cl_.indexOf(PAR_LEFT)+1, cl_.lastIndexOf(PAR_RIGHT));
        StringList args_ = StringExpUtil.getAllSepCommaTypes(cl_);
        from = "";
        String op_ = args_.first();
        if (args_.size() > 1) {
            int off_ = StringList.getFirstPrintableCharIndex(args_.get(1));
            String fromType_ = StringExpUtil.removeDottedSpaces(args_.get(1));
            from = ResolvingImportTypes.resolveCorrectTypeAccessible(_conf,off_+methodName.indexOf(',')+1,fromType_);
            partOffsets.addAllElts(_conf.getAnalyzing().getCurrentParts());
        }
        if (from.isEmpty()) {
            methodFound = op_;
            CustList<MethodInfo> ops_ = getOperators(_conf, null);
            methodInfos.add(ops_);
        } else {
            methodFound = op_;
            methodInfos = getDeclaredCustMethodByType(_conf,MethodAccessKind.STATIC_CALL, false,false,new StringList(from), op_, false,null);
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
        AnalyzedBlock cur_ = _conf.getAnalyzing().getCurrentAnaBlock();
        if (apply_ && cur_ instanceof ReturnMethod) {
            typeAff_ = tryGetRetType(_conf);
        }
        filterByReturnType(_conf,typeAff_,methodInfos);
    }

    @Override
    public void analyze(ContextEl _conf) {
        String cl_ = methodName;
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
        cl_ = cl_.substring(cl_.indexOf(PAR_LEFT)+1, cl_.lastIndexOf(PAR_RIGHT));
        StringList args_ = StringExpUtil.getAllSepCommaTypes(cl_);
        String op_ = args_.first();
        int varargOnly_ = lookOnlyForVarArg();
        ClassMethodIdAncestor idMethod_ = lookOnlyForId();
        CustList<OperationNode> chidren_ = getChildrenNodes();
        String varargParam_ = getVarargParam(chidren_);
        if (args_.size() > 2) {
            FoundErrorInterpret badCall_ = new FoundErrorInterpret();
            badCall_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            badCall_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //key word len
            badCall_.buildError(_conf.getAnalysisMessages().getSplitComaLow(),
                    Integer.toString(2),
                    Integer.toString(args_.size())
            );
            _conf.getAnalyzing().getLocalizer().addError(badCall_);
            getErrs().add(badCall_.getBuiltError());
            setResultClass(new ClassArgumentMatching(_conf.getStandards().getAliasObject()));
            return;
        }
        ClassMethodId id_ = null;
        if (idMethod_ != null) {
            id_ = idMethod_.getClassMethodId();
            MethodId s_ = id_.getConstraints();
            MethodAccessKind static_ = MethodId.getKind(isStaticAccess(), s_.getKind());
            id_ = new ClassMethodId(from,new MethodId(static_,op_,s_.getParametersTypes(),s_.isVararg()));
        }
        NameParametersFilter name_ = buildFilter(_conf);
        if (!name_.isOk()) {
            setResultClass(new ClassArgumentMatching(_conf.getStandards().getAliasObject()));
            return;
        }
        ClassMethodIdReturn cust_;
        if (from.isEmpty()) {
            cust_ = getOperator(_conf, id_,varargOnly_,false,op_, varargParam_, name_);
        } else {
            cust_ = tryGetDeclaredCustMethod(_conf, -1, MethodAccessKind.STATIC_CALL,
                    false, new StringList(from), op_, false, false, false, null,
                    varargParam_, name_);
        }
        if (!cust_.isFoundMethod()) {
            FoundErrorInterpret undefined_ = new FoundErrorInterpret();
            undefined_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            undefined_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //_name len
            StringList classesNames_ = new StringList();
            for (ClassArgumentMatching c: name_.getAll()) {
                classesNames_.add(StringList.join(c.getNames(), "&"));
            }
            undefined_.buildError(_conf.getAnalysisMessages().getUndefinedMethod(),
                    new MethodId(MethodAccessKind.STATIC, cl_, classesNames_).getSignature(_conf));
            _conf.getAnalyzing().getLocalizer().addError(undefined_);
            getErrs().add(undefined_.getBuiltError());
            setResultClass(new ClassArgumentMatching(_conf.getStandards().getAliasObject()));
            return;
        }
        setResultClass(new ClassArgumentMatching(cust_.getReturnType()));
        String foundClass_ = cust_.getRealClass();
        MethodId realId_ = cust_.getRealId();
        if (realId_.getKind() != MethodAccessKind.STATIC_CALL) {
            foundClass_ = StringExpUtil.getIdFromAllTypes(foundClass_);
        }
        rootNumber = cust_.getRootNumber();
        memberNumber = cust_.getMemberNumber();
        classMethodId = new ClassMethodId(foundClass_, cust_.getRealId());
        if (cust_.isVarArgToCall()) {
            StringList paramtTypes_ = cust_.getRealId().getParametersTypes();
            naturalVararg = paramtTypes_.size() - 1;
            lastType = paramtTypes_.last();
        }
        unwrapArgsFct(chidren_, realId_, naturalVararg, lastType, name_.getAll(), _conf);
    }


    public int getOffsetOper() {
        return offsetOper;
    }
    public int getNaturalVararg() {
        return naturalVararg;
    }

    public String getLastType() {
        return lastType;
    }

    public ClassMethodId getClassMethodId() {
        return classMethodId;
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
        return memberNumber;
    }

    public int getRootNumber() {
        return rootNumber;
    }
}
