package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class SuperFctOperation extends InvokingOperation {

    private String methodName;

    private ClassMethodId classMethodId;

    private boolean staticMethod;

    private String lastType = EMPTY_STRING;

    private int naturalVararg = -1;
    private int anc;
    private int delta;
    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();

    public SuperFctOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        methodName = getOperations().getFctName();
    }

    @Override
    public void analyze(ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        String trimMeth_;
        int varargOnly_ = lookOnlyForVarArg();
        ClassMethodIdAncestor idMethod_ = lookOnlyForId();
        LgNames stds_ = _conf.getStandards();
        boolean import_ = false;
        ClassArgumentMatching clCur_;
        if (!isIntermediateDottedOperation()) {
            clCur_ = new ClassArgumentMatching(_conf.getAnalyzing().getGlobalClass());
            import_ = true;
            setStaticAccess(_conf.getAnalyzing().getStaticContext());
        } else {
            clCur_ = getPreviousResultClass();
        }
        String className_ = methodName.substring(0, methodName.lastIndexOf(PAR_RIGHT));
        int lenPref_ = methodName.indexOf(PAR_LEFT) + 1;
        className_ = className_.substring(lenPref_);
        int loc_ = StringList.getFirstPrintableCharIndex(className_)-off_;
        className_ = ResolvingImportTypes.resolveCorrectType(_conf,lenPref_+loc_,className_);
        partOffsets.addAllElts(_conf.getCoverage().getCurrentParts());
        String clCurName_ = className_;
        StringList bounds_ = getBounds(clCurName_, _conf);
        String varargParam_ = getVarargParam(chidren_);
        CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_);
        Mapping map_ = new Mapping();
        map_.setParam(className_);
        map_.setArg(clCur_);
        StringMap<StringList> mapping_ = _conf.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
        map_.setMapping(mapping_);
        if (!AnaTemplates.isCorrectOrNumbers(map_, _conf)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            cast_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            //type len
            cast_.buildError(_conf.getAnalysisMessages().getBadImplicitCast(),
                    StringList.join(clCur_.getNames(),"&"),
                    className_);
            _conf.getAnalyzing().getLocalizer().addError(cast_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);

        delta = methodName.lastIndexOf(PAR_RIGHT)+1;
        String mName_ = methodName.substring(delta);
        delta += StringList.getFirstPrintableCharIndex(mName_);
        trimMeth_ = mName_.trim();
        ClassMethodIdAncestor feed_ = null;
        if (idMethod_ != null) {
            ClassMethodId id_ = idMethod_.getClassMethodId();
            String idClass_ = id_.getClassName();
            MethodId mid_ = id_.getConstraints();
            boolean vararg_ = mid_.isVararg();
            StringList params_ = mid_.getParametersTypes();
            MethodAccessKind static_ = MethodId.getKind(isStaticAccess(), mid_.getKind());
            feed_ = new ClassMethodIdAncestor(new ClassMethodId(idClass_, new MethodId(static_, trimMeth_, params_, vararg_)),idMethod_.getAncestor());
        }
        ClassMethodIdReturn clMeth_ = getDeclaredCustMethod(_conf, varargOnly_, isStaticAccess(), bounds_, trimMeth_, true, false, import_, feed_, varargParam_, ClassArgumentMatching.toArgArray(firstArgs_));
        anc = clMeth_.getAncestor();
        if (!clMeth_.isFoundMethod()) {
            setResultClass(voidToObject(new ClassArgumentMatching(clMeth_.getReturnType()),_conf));
            return;
        }
        if (clMeth_.isAbstractMethod()) {
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
            FoundErrorInterpret abs_ = new FoundErrorInterpret();
            abs_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            abs_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            //method name len
            abs_.buildError(
                    _conf.getAnalysisMessages().getAbstractMethodRef(),
                    clMeth_.getRealClass(),
                    clMeth_.getRealId().getSignature(_conf));
            _conf.getAnalyzing().getLocalizer().addError(abs_);
            setResultClass(voidToObject(new ClassArgumentMatching(clMeth_.getReturnType()),_conf));
            return;
        }
        String foundClass_ = clMeth_.getRealClass();
        MethodId id_ = clMeth_.getRealId();
        if (id_.getKind() != MethodAccessKind.STATIC_CALL) {
            foundClass_ = StringExpUtil.getIdFromAllTypes(foundClass_);
        }
        classMethodId = new ClassMethodId(foundClass_, id_);
        MethodId realId_ = clMeth_.getRealId();
        if (clMeth_.isVarArgToCall()) {
            StringList paramtTypes_ = clMeth_.getRealId().getParametersTypes();
            naturalVararg = paramtTypes_.size() - 1;
            lastType = paramtTypes_.last();
        }
        staticMethod = id_.getKind() != MethodAccessKind.INSTANCE;
        unwrapArgsFct(chidren_, realId_, naturalVararg, lastType, firstArgs_, _conf);
        setResultClass(voidToObject(new ClassArgumentMatching(clMeth_.getReturnType()),_conf));
        if (isIntermediateDottedOperation() && !staticMethod) {
            Argument arg_ = getPreviousArgument();
            checkNull(arg_,_conf);
        }
    }

    public String getMethodName() {
        return methodName;
    }

    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }

    public boolean isStaticMethod() {
        return staticMethod;
    }

    public String getLastType() {
        return lastType;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

    public int getAnc() {
        return anc;
    }

    public int getDelta() {
        return delta;
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }
}
