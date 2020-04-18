package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.util.*;
import code.util.CustList;
import code.util.StringList;

public final class ExplicitOperatorOperation extends InvokingOperation {
    private ClassMethodId classMethodId;
    private String methodName;
    private String lastType = EMPTY_STRING;

    private int naturalVararg = -1;

    private int offsetOper;
    public ExplicitOperatorOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        methodName = getOperations().getFctName();
        offsetOper = getOperations().getOperators().firstKey();
    }

    @Override
    public void analyze(Analyzable _conf) {
        String cl_ = methodName;
        cl_ = cl_.substring(cl_.indexOf(PAR_LEFT)+1, cl_.lastIndexOf(PAR_RIGHT));
        int varargOnly_ = lookOnlyForVarArg();
        ClassMethodIdAncestor idMethod_ = lookOnlyForId();
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_, _conf);
        ClassMethodId id_ = null;
        if (idMethod_ != null) {
            id_ = idMethod_.getClassMethodId();
            MethodId s_ = id_.getConstraints();
            id_ = new ClassMethodId("",new MethodId(MethodAccessKind.STATIC,cl_,s_.getParametersTypes(),s_.isVararg()));
        }
        ClassMethodIdReturn cust_ = getOperator(_conf, id_,varargOnly_,false,cl_, ClassArgumentMatching.toArgArray(firstArgs_));
        if (!cust_.isFoundMethod()) {
            FoundErrorInterpret undefined_ = new FoundErrorInterpret();
            undefined_.setFileName(_conf.getCurrentFileName());
            undefined_.setIndexFile(_conf.getCurrentLocationIndex());
            //_name len
            StringList classesNames_ = new StringList();
            for (ClassArgumentMatching c: firstArgs_) {
                classesNames_.add(StringList.join(c.getNames(), "&"));
            }
            undefined_.buildError(_conf.getContextEl().getAnalysisMessages().getUndefinedMethod(),
                    new MethodId(MethodAccessKind.STATIC, cl_, classesNames_).getSignature(_conf));
            _conf.addError(undefined_);
            setResultClass(new ClassArgumentMatching(_conf.getStandards().getAliasObject()));
            return;
        }
        setResultClass(new ClassArgumentMatching(cust_.getReturnType()));
        String foundClass_ = cust_.getRealClass();
        foundClass_ = Templates.getIdFromAllTypes(foundClass_);
        classMethodId = new ClassMethodId(foundClass_, cust_.getRealId());
        MethodId realId_ = cust_.getRealId();
        if (cust_.isVarArgToCall()) {
            StringList paramtTypes_ = cust_.getRealId().getParametersTypes();
            naturalVararg = paramtTypes_.size() - 1;
            lastType = paramtTypes_.last();
        }
        InvokingOperation.unwrapArgsFct(chidren_, realId_, naturalVararg, lastType, firstArgs_, _conf);
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
}
