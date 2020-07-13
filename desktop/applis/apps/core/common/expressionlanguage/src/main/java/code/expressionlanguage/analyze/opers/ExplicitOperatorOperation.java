package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.CustList;
import code.util.StringList;

public final class ExplicitOperatorOperation extends InvokingOperation implements PreAnalyzableOperation {
    private ClassMethodId classMethodId;
    private String methodName;
    private String lastType = EMPTY_STRING;

    private int naturalVararg = -1;

    private int offsetOper;
    private String from;

    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    public ExplicitOperatorOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        methodName = getOperations().getFctName();
        offsetOper = getOperations().getOperators().firstKey();
    }

    @Override
    public void preAnalyze(ContextEl _conf) {
        String cl_ = methodName;
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
        cl_ = cl_.substring(cl_.indexOf(PAR_LEFT)+1, cl_.lastIndexOf(PAR_RIGHT));
        StringList args_ = StringExpUtil.getAllSepCommaTypes(cl_);
        from = "";
        if (args_.size() > 1) {
            int off_ = StringList.getFirstPrintableCharIndex(args_.get(1));
            String fromType_ = StringExpUtil.removeDottedSpaces(args_.get(1));
            from = ResolvingImportTypes.resolveCorrectTypeAccessible(_conf,off_+methodName.indexOf(',')+1,fromType_);
            partOffsets.addAllElts(_conf.getAnalyzing().getCurrentParts());
        }
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
        CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_);
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
        ClassMethodIdReturn cust_;
        if (from.isEmpty()) {
            cust_ = getOperator(_conf, id_,varargOnly_,false,op_, varargParam_,ClassArgumentMatching.toArgArray(firstArgs_));
        } else {
            cust_ = tryGetDeclaredCustMethod(_conf, -1, MethodAccessKind.STATIC_CALL,
                    false, new StringList(from), op_, false, false, false, null,
                    varargParam_,ClassArgumentMatching.toArgArray(firstArgs_));
        }
        if (!cust_.isFoundMethod()) {
            FoundErrorInterpret undefined_ = new FoundErrorInterpret();
            undefined_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            undefined_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //_name len
            StringList classesNames_ = new StringList();
            for (ClassArgumentMatching c: firstArgs_) {
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
        classMethodId = new ClassMethodId(foundClass_, cust_.getRealId());
        if (cust_.isVarArgToCall()) {
            StringList paramtTypes_ = cust_.getRealId().getParametersTypes();
            naturalVararg = paramtTypes_.size() - 1;
            lastType = paramtTypes_.last();
        }
        unwrapArgsFct(chidren_, realId_, naturalVararg, lastType, firstArgs_, _conf);
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
}
