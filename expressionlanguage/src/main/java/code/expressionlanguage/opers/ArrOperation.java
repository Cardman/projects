package code.expressionlanguage.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.exec.Operable;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.NumberStruct;
import code.util.CustList;
import code.util.StringList;

public final class ArrOperation extends InvokingOperation implements SettableElResult {

    private boolean variable;

    private boolean catString;

    private ClassMethodId classMethodId;

    private String lastType = EMPTY_STRING;

    private int naturalVararg = -1;

    private int anc;

    private boolean staticChoiceMethod;

    public ArrOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyze(ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<ClassArgumentMatching> firstArgs_ = listClasses(chidren_, _conf);
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
        String trimMeth_ = "[]";
        if (idMethod_ != null) {
            ClassMethodId id_ = idMethod_.getClassMethodId();
            String idClass_ = id_.getClassName();
            MethodId mid_ = id_.getConstraints();
            boolean vararg_ = mid_.isVararg();
            StringList params_ = mid_.getParametersTypes();
            feed_ = new ClassMethodIdAncestor(new ClassMethodId(idClass_, new MethodId(MethodAccessKind.INSTANCE, trimMeth_, params_, vararg_)),idMethod_.getAncestor());
        }
        ClassArgumentMatching class_ = getPreviousResultClass();
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
            bounds_.addAllElts(getBounds(c, _conf));
        }
        ClassMethodIdReturn clMeth_ = tryGetDeclaredCustMethod(_conf, varargOnly_, isStaticAccess(),
                bounds_, trimMeth_, accessSuperTypes_, accessFromSuper_, false, feed_,
                ClassArgumentMatching.toArgArray(firstArgs_));
        if (clMeth_.isFoundMethod()) {
            if (staticChoiceMethod_) {
                if (clMeth_.isAbstractMethod()) {
                    setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
                    FoundErrorInterpret abs_ = new FoundErrorInterpret();
                    abs_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                    abs_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                    //method name len
                    abs_.buildError(
                            _conf.getAnalysisMessages().getAbstractMethodRef(),
                            clMeth_.getRealClass(),
                            clMeth_.getRealId().getSignature(_conf));
                    _conf.getAnalyzing().getLocalizer().addError(abs_);
                    setResultClass(new ClassArgumentMatching(clMeth_.getReturnType()));
                    return;
                }
            }
            staticChoiceMethod = staticChoiceMethod_;
            anc = clMeth_.getAncestor();
            String foundClass_ = clMeth_.getRealClass();
            if (!staticChoiceMethod_) {
                foundClass_ = Templates.getIdFromAllTypes(foundClass_);
            }
            MethodId id_ = clMeth_.getRealId();
            classMethodId = new ClassMethodId(foundClass_, id_);
            MethodId realId_ = clMeth_.getRealId();
            if (clMeth_.isVarArgToCall()) {
                StringList paramtTypes_ = clMeth_.getRealId().getParametersTypes();
                naturalVararg = paramtTypes_.size() - 1;
                lastType = paramtTypes_.last();
            }
            unwrapArgsFct(chidren_, realId_, naturalVararg, lastType, firstArgs_, _conf);
            setResultClass(new ClassArgumentMatching(clMeth_.getReturnType()));
            Argument arg_ = getPreviousArgument();
            checkNull(arg_,_conf);
            return;
        }
        if (chidren_.size() != 1) {
            setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
            FoundErrorInterpret badNb_ = new FoundErrorInterpret();
            badNb_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            badNb_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //second separator char
            badNb_.buildError(_conf.getAnalysisMessages().getOperatorNbDiff(),
                    Integer.toString(1),
                    Integer.toString(chidren_.size()),
                    "[]"
            );
            _conf.getAnalyzing().getLocalizer().addError(badNb_);
            setResultClass(new ClassArgumentMatching(_conf.getStandards().getAliasObject()));
            return;
        }
        OperationNode right_ = chidren_.last();
        ClassArgumentMatching indexClass_ = right_.getResultClass();
        setRelativeOffsetPossibleAnalyzable(right_.getIndexInEl(), _conf);
        LgNames stds_ = _conf.getStandards();
        String primInt_ = stds_.getAliasPrimInteger();
        Argument rightArg_ = right_.getArgument();
        boolean convertNumber_ = false;
        if (rightArg_ != null && rightArg_.getStruct() instanceof NumberStruct) {
            long valueUnwrapped_ = ClassArgumentMatching.convertToNumber(rightArg_.getStruct()).longStruct();
            if (valueUnwrapped_ >= Integer.MIN_VALUE && valueUnwrapped_ <= Integer.MAX_VALUE) {
                right_.getResultClass().setUnwrapObject(primInt_);
                convertNumber_ = true;
            }
        }
        if (!convertNumber_ && !indexClass_.isNumericInt(_conf)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            //first separator char
            un_.buildError(_conf.getAnalysisMessages().getUnexpectedType(),
                    StringList.join(indexClass_.getNames(),"&"));
            _conf.getAnalyzing().getLocalizer().addError(un_);
            class_ = new ClassArgumentMatching(_conf.getStandards().getAliasObject());
            setResultClass(class_);
            return;
        }
        setRelativeOffsetPossibleAnalyzable(chidren_.first().getIndexInEl(), _conf);
        if (!class_.isArray()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            //first separator char
            un_.buildError(_conf.getAnalysisMessages().getUnexpectedType(),
                    StringList.join(class_.getNames(),"&"));
            _conf.getAnalyzing().getLocalizer().addError(un_);
            class_ = new ClassArgumentMatching(_conf.getStandards().getAliasObject());
            setResultClass(class_);
            return;
        }
        if (!convertNumber_) {
            indexClass_.setUnwrapObject(PrimitiveTypeUtil.toPrimitive(indexClass_,  _conf));
            right_.cancelArgument();
        }
        class_ = PrimitiveTypeUtil.getQuickComponentType(class_);
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
    private static ForwardOperation castTo(Operable _op) {
        if (_op instanceof ForwardOperation) {
            return (ForwardOperation) _op;
        }
        return null;
    }

    public boolean isVariable() {
        return variable;
    }

    @Override
    public void setVariable(boolean _variable) {
        variable = _variable;
    }

    public boolean isCatString() {
        return catString;
    }

    @Override
    public void setCatenizeStrings() {
        catString = true;
    }

    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }

    public boolean isStaticChoiceMethod() {
        return staticChoiceMethod;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

    public String getLastType() {
        return lastType;
    }

    public int getAnc() {
        return anc;
    }
}
