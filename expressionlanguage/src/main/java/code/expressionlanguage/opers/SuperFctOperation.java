package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.AbstractMethod;
import code.expressionlanguage.errors.custom.BadImplicitCast;
import code.expressionlanguage.errors.custom.StaticAccessError;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassMethodIdReturn;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;

public final class SuperFctOperation extends ReflectableInvokingOperation {

    private String methodName;

    private ClassMethodId classMethodId;

    private boolean staticMethod;

    private String lastType = EMPTY_STRING;

    private int naturalVararg = -1;
    private int anc;
    public SuperFctOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        methodName = getOperations().getFctName();
    }

    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }

    @Override
    public void analyze(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        String trimMeth_;
        int varargOnly_ = lookOnlyForVarArg();
        ClassMethodId idMethod_ = lookOnlyForId();
        LgNames stds_ = _conf.getStandards();
        boolean import_ = false;
        ClassArgumentMatching clCur_;
        if (!isIntermediateDottedOperation()) {
            clCur_ = new ClassArgumentMatching(_conf.getGlobalClass());
            import_ = true;
            setStaticAccess(_conf.isStaticContext());
        } else {
            clCur_ = getPreviousResultClass();
        }
        String className_ = methodName.substring(0, methodName.lastIndexOf(PAR_RIGHT));
        int lenPref_ = methodName.indexOf(PAR_LEFT) + 1;
        className_ = className_.substring(lenPref_);
        className_ = _conf.resolveCorrectType(className_);
        Mapping map_ = new Mapping();
        map_.setParam(className_);
        map_.setArg(clCur_);
        StringMap<StringList> mapping_ = _conf.getCurrentConstraints();
        map_.setMapping(mapping_);
        if (!Templates.isCorrectOrNumbers(map_, _conf)) {
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(map_);
            cast_.setIndexFile(_conf.getCurrentLocationIndex());
            cast_.setFileName(_conf.getCurrentFileName());
            _conf.getClasses().addError(cast_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        String clCurName_ = className_;
        if (hasVoidPrevious(clCurName_, _conf)) {
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        StringList bounds_ = getBounds(clCurName_, _conf);
        CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_, _conf);
        if (hasVoidArguments(chidren_, firstArgs_, off_, _conf)) {
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);

        trimMeth_ = methodName.substring(methodName.lastIndexOf(PAR_RIGHT)+1).trim();
        ClassMethodId feed_ = null;
        if (idMethod_ != null) {
            String idClass_ = idMethod_.getClassName();
            MethodId mid_ = idMethod_.getConstraints();
            boolean vararg_ = mid_.isVararg();
            StringList params_ = mid_.getParametersTypes();
            boolean static_ = isStaticAccess() || mid_.isStaticMethod();
            feed_ = new ClassMethodId(idClass_, new MethodId(static_, trimMeth_, params_, vararg_));
        }
        ClassMethodIdReturn clMeth_ = getDeclaredCustMethod(_conf, varargOnly_, isStaticAccess(), bounds_, trimMeth_, true, false, import_, feed_, ClassArgumentMatching.toArgArray(firstArgs_));
        anc = clMeth_.getAncestor();
        if (!clMeth_.isFoundMethod()) {
            setResultClass(new ClassArgumentMatching(clMeth_.getReturnType()));
            return;
        }
        if (clMeth_.isAbstractMethod()) {
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
            AbstractMethod abs_ = new AbstractMethod();
            abs_.setClassName(clMeth_.getRealClass());
            abs_.setSgn(clMeth_.getRealId().getSignature());
            abs_.setIndexFile(_conf.getCurrentLocationIndex());
            abs_.setFileName(_conf.getCurrentFileName());
            _conf.getClasses().addError(abs_);
            setResultClass(new ClassArgumentMatching(clMeth_.getReturnType()));
            return;
        }
        String foundClass_ = clMeth_.getRealClass();
        foundClass_ = Templates.getIdFromAllTypes(foundClass_);
        MethodId id_ = clMeth_.getRealId();
        classMethodId = new ClassMethodId(foundClass_, id_);
        MethodId realId_ = clMeth_.getRealId();
        if (clMeth_.isVarArgToCall()) {
            StringList paramtTypes_ = clMeth_.getRealId().getParametersTypes();
            naturalVararg = paramtTypes_.size() - 1;
            lastType = paramtTypes_.last();
        }
        staticMethod = clMeth_.isStaticMethod();
        unwrapArgsFct(chidren_, realId_, naturalVararg, lastType, firstArgs_, _conf);
        setResultClass(new ClassArgumentMatching(clMeth_.getReturnType()));
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

}
