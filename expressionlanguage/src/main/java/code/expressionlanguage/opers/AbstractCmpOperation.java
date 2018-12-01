package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.calls.util.CustomFoundMethod;
import code.expressionlanguage.errors.custom.BadOperandsNumber;
import code.expressionlanguage.errors.custom.StaticAccessError;
import code.expressionlanguage.errors.custom.UnexpectedTypeOperationError;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassMethodIdReturn;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.StringList;

public abstract class AbstractCmpOperation extends PrimitiveBoolOperation {

    private static final int EQ_CMP = 0;

    private boolean stringCompare;
    private ClassMethodId classMethodId;

    public AbstractCmpOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    public static Argument quickCalculateLower(Argument _a, boolean _strCmp, Argument _b) {
        if (_strCmp) {
            Argument a_ = new Argument();
            String first_ = (String)_a.getObject();
            String second_ = (String)_b.getObject();
            a_.setObject(first_.compareTo(second_) < EQ_CMP);
            return a_;
        }
        Number a_ = _a.getNumber();
        Number b_ = _b.getNumber();
        Argument arg_ = new Argument();
        arg_.setObject(Numbers.lt(a_, b_));
        return arg_;
    }

    public static Argument quickCalculateGreater(Argument _a, boolean _strCmp, Argument _b) {
        if (_strCmp) {
            Argument a_ = new Argument();
            String first_ = (String)_a.getObject();
            String second_ = (String)_b.getObject();
            a_.setObject(first_.compareTo(second_) < EQ_CMP);
            return a_;
        }
        Number a_ = _a.getNumber();
        Number b_ = _b.getNumber();
        Argument arg_ = new Argument();
        arg_.setObject(Numbers.gt(a_, b_));
        return arg_;
    }
    static Argument calculateLower(Argument _a, boolean _strCmp, Argument _b) {
        if (_strCmp) {
            Argument a_ = new Argument();
            String first_ = (String)_a.getObject();
            String second_ = (String)_b.getObject();
            a_.setObject(first_.compareTo(second_) < EQ_CMP);
            return a_;
        }
        Object o_ = _a.getObject();
        Double aOne_ = null;
        Float aTwo_ = null;
        Long aThree_ = null;
        Integer aFour_ = null;
        Short aFive_ = null;
        Byte aSix_ = null;
        Character aSeven_ = null;
        if (o_ instanceof Double) {
            aOne_ = (Double) o_;
        } else if (o_ instanceof Float) {
            aTwo_ = (Float) o_;
        } else if (o_ instanceof Long) {
            aThree_ = (Long) o_;
        } else if (o_ instanceof Integer) {
            aFour_ = (Integer) o_;
        } else if (o_ instanceof Short) {
            aFive_ = (Short) o_;
        } else if (o_ instanceof Byte) {
            aSix_ = (Byte) o_;
        } else if (o_ instanceof Character) {
            aSeven_ = (Character) o_;
        }
        Object p_ = _b.getObject();
        Double bOne_ = null;
        Float bTwo_ = null;
        Long bThree_ = null;
        Integer bFour_ = null;
        Short bFive_ = null;
        Byte bSix_ = null;
        Character bSeven_ = null;
        if (p_ instanceof Double) {
            bOne_ = (Double) p_;
        } else if (p_ instanceof Float) {
            bTwo_ = (Float) p_;
        } else if (p_ instanceof Long) {
            bThree_ = (Long) p_;
        } else if (p_ instanceof Integer) {
            bFour_ = (Integer) p_;
        } else if (p_ instanceof Short) {
            bFive_ = (Short) p_;
        } else if (p_ instanceof Byte) {
            bSix_ = (Byte) p_;
        } else if (p_ instanceof Character) {
            bSeven_ = (Character) p_;
        }
        boolean nb_;
        if (aOne_ != null) {
            if (bOne_ != null) {
                nb_ = aOne_< bOne_;
            } else if (bTwo_ != null) {
                nb_ = aOne_< bTwo_;
            } else if (bThree_ != null) {
                nb_ = aOne_< bThree_;
            } else if (bFour_ != null) {
                nb_ = aOne_< bFour_;
            } else if (bFive_ != null) {
                nb_ = aOne_< bFive_;
            } else if (bSix_ != null) {
                nb_ = aOne_< bSix_;
            } else {
                nb_ = aOne_ < bSeven_;
            }
        } else if (aTwo_ != null) {
            if (bOne_ != null) {
                nb_ = aTwo_< bOne_;
            } else if (bTwo_ != null) {
                nb_ = aTwo_< bTwo_;
            } else if (bThree_ != null) {
                nb_ = aTwo_< bThree_;
            } else if (bFour_ != null) {
                nb_ = aTwo_< bFour_;
            } else if (bFive_ != null) {
                nb_ = aTwo_< bFive_;
            } else if (bSix_ != null) {
                nb_ = aTwo_< bSix_;
            } else {
                nb_ = aTwo_ < bSeven_;
            }
        } else if (aThree_ != null) {
            if (bOne_ != null) {
                nb_ = aThree_< bOne_;
            } else if (bTwo_ != null) {
                nb_ = aThree_< bTwo_;
            } else if (bThree_ != null) {
                nb_ = aThree_< bThree_;
            } else if (bFour_ != null) {
                nb_ = aThree_< bFour_;
            } else if (bFive_ != null) {
                nb_ = aThree_< bFive_;
            } else if (bSix_ != null) {
                nb_ = aThree_< bSix_;
            } else {
                nb_ = aThree_ < bSeven_;
            }
        } else if (aFour_ != null) {
            if (bOne_ != null) {
                nb_ = aFour_< bOne_;
            } else if (bTwo_ != null) {
                nb_ = aFour_< bTwo_;
            } else if (bThree_ != null) {
                nb_ = aFour_< bThree_;
            } else if (bFour_ != null) {
                nb_ = aFour_< bFour_;
            } else if (bFive_ != null) {
                nb_ = aFour_< bFive_;
            } else if (bSix_ != null) {
                nb_ = aFour_< bSix_;
            } else {
                nb_ = aFour_ < bSeven_;
            }
        } else if (aFive_ != null) {
            if (bOne_ != null) {
                nb_ = aFive_< bOne_;
            } else if (bTwo_ != null) {
                nb_ = aFive_< bTwo_;
            } else if (bThree_ != null) {
                nb_ = aFive_< bThree_;
            } else if (bFour_ != null) {
                nb_ = aFive_< bFour_;
            } else if (bFive_ != null) {
                nb_ = aFive_< bFive_;
            } else if (bSix_ != null) {
                nb_ = aFive_< bSix_;
            } else {
                nb_ = aFive_ < bSeven_;
            }
        } else if (aSix_ != null) {
            if (bOne_ != null) {
                nb_ = aSix_< bOne_;
            } else if (bTwo_ != null) {
                nb_ = aSix_< bTwo_;
            } else if (bThree_ != null) {
                nb_ = aSix_< bThree_;
            } else if (bFour_ != null) {
                nb_ = aSix_< bFour_;
            } else if (bFive_ != null) {
                nb_ = aSix_< bFive_;
            } else if (bSix_ != null) {
                nb_ = aSix_< bSix_;
            } else {
                nb_ = aSix_ < bSeven_;
            }
        } else {
            if (bOne_ != null) {
                nb_ = aSeven_ < bOne_;
            } else if (bTwo_ != null) {
                nb_ = aSeven_< bTwo_;
            } else if (bThree_ != null) {
                nb_ = aSeven_< bThree_;
            } else if (bFour_ != null) {
                nb_ = aSeven_< bFour_;
            } else if (bFive_ != null) {
                nb_ = aSeven_ < bFive_;
            } else if (bSix_ != null) {
                nb_ = aSeven_ < bSix_;
            } else {
                nb_ = aSeven_ < bSeven_;
            }
        }
        Argument a_ = new Argument();
        a_.setObject(nb_);
        return a_;
    }

    static Argument calculateGreater(Argument _a, boolean _strCmp, Argument _b) {
        if (_strCmp) {
            Argument a_ = new Argument();
            String first_ = (String)_a.getObject();
            String second_ = (String)_b.getObject();
            a_.setObject(first_.compareTo(second_) > EQ_CMP);
            return a_;
        }
        Object o_ = _a.getObject();
        Double aOne_ = null;
        Float aTwo_ = null;
        Long aThree_ = null;
        Integer aFour_ = null;
        Short aFive_ = null;
        Byte aSix_ = null;
        Character aSeven_ = null;
        if (o_ instanceof Double) {
            aOne_ = (Double) o_;
        } else if (o_ instanceof Float) {
            aTwo_ = (Float) o_;
        } else if (o_ instanceof Long) {
            aThree_ = (Long) o_;
        } else if (o_ instanceof Integer) {
            aFour_ = (Integer) o_;
        } else if (o_ instanceof Short) {
            aFive_ = (Short) o_;
        } else if (o_ instanceof Byte) {
            aSix_ = (Byte) o_;
        } else if (o_ instanceof Character) {
            aSeven_ = (Character) o_;
        }
        Object p_ = _b.getObject();
        Double bOne_ = null;
        Float bTwo_ = null;
        Long bThree_ = null;
        Integer bFour_ = null;
        Short bFive_ = null;
        Byte bSix_ = null;
        Character bSeven_ = null;
        if (p_ instanceof Double) {
            bOne_ = (Double) p_;
        } else if (p_ instanceof Float) {
            bTwo_ = (Float) p_;
        } else if (p_ instanceof Long) {
            bThree_ = (Long) p_;
        } else if (p_ instanceof Integer) {
            bFour_ = (Integer) p_;
        } else if (p_ instanceof Short) {
            bFive_ = (Short) p_;
        } else if (p_ instanceof Byte) {
            bSix_ = (Byte) p_;
        } else if (p_ instanceof Character) {
            bSeven_ = (Character) p_;
        }
        boolean nb_;
        if (aOne_ != null) {
            if (bOne_ != null) {
                nb_ = aOne_> bOne_;
            } else if (bTwo_ != null) {
                nb_ = aOne_> bTwo_;
            } else if (bThree_ != null) {
                nb_ = aOne_> bThree_;
            } else if (bFour_ != null) {
                nb_ = aOne_> bFour_;
            } else if (bFive_ != null) {
                nb_ = aOne_> bFive_;
            } else if (bSix_ != null) {
                nb_ = aOne_> bSix_;
            } else {
                nb_ = aOne_ > bSeven_;
            }
        } else if (aTwo_ != null) {
            if (bOne_ != null) {
                nb_ = aTwo_> bOne_;
            } else if (bTwo_ != null) {
                nb_ = aTwo_> bTwo_;
            } else if (bThree_ != null) {
                nb_ = aTwo_> bThree_;
            } else if (bFour_ != null) {
                nb_ = aTwo_> bFour_;
            } else if (bFive_ != null) {
                nb_ = aTwo_> bFive_;
            } else if (bSix_ != null) {
                nb_ = aTwo_> bSix_;
            } else {
                nb_ = aTwo_ > bSeven_;
            }
        } else if (aThree_ != null) {
            if (bOne_ != null) {
                nb_ = aThree_> bOne_;
            } else if (bTwo_ != null) {
                nb_ = aThree_> bTwo_;
            } else if (bThree_ != null) {
                nb_ = aThree_> bThree_;
            } else if (bFour_ != null) {
                nb_ = aThree_> bFour_;
            } else if (bFive_ != null) {
                nb_ = aThree_> bFive_;
            } else if (bSix_ != null) {
                nb_ = aThree_> bSix_;
            } else {
                nb_ = aThree_ > bSeven_;
            }
        } else if (aFour_ != null) {
            if (bOne_ != null) {
                nb_ = aFour_> bOne_;
            } else if (bTwo_ != null) {
                nb_ = aFour_> bTwo_;
            } else if (bThree_ != null) {
                nb_ = aFour_> bThree_;
            } else if (bFour_ != null) {
                nb_ = aFour_> bFour_;
            } else if (bFive_ != null) {
                nb_ = aFour_> bFive_;
            } else if (bSix_ != null) {
                nb_ = aFour_> bSix_;
            } else {
                nb_ = aFour_ > bSeven_;
            }
        } else if (aFive_ != null) {
            if (bOne_ != null) {
                nb_ = aFive_> bOne_;
            } else if (bTwo_ != null) {
                nb_ = aFive_> bTwo_;
            } else if (bThree_ != null) {
                nb_ = aFive_> bThree_;
            } else if (bFour_ != null) {
                nb_ = aFive_> bFour_;
            } else if (bFive_ != null) {
                nb_ = aFive_> bFive_;
            } else if (bSix_ != null) {
                nb_ = aFive_> bSix_;
            } else {
                nb_ = aFive_ > bSeven_;
            }
        } else if (aSix_ != null) {
            if (bOne_ != null) {
                nb_ = aSix_> bOne_;
            } else if (bTwo_ != null) {
                nb_ = aSix_> bTwo_;
            } else if (bThree_ != null) {
                nb_ = aSix_> bThree_;
            } else if (bFour_ != null) {
                nb_ = aSix_> bFour_;
            } else if (bFive_ != null) {
                nb_ = aSix_> bFive_;
            } else if (bSix_ != null) {
                nb_ = aSix_> bSix_;
            } else {
                nb_ = aSix_ > bSeven_;
            }
        } else {
            if (bOne_ != null) {
                nb_ = aSeven_ > bOne_;
            } else if (bTwo_ != null) {
                nb_ = aSeven_> bTwo_;
            } else if (bThree_ != null) {
                nb_ = aSeven_> bThree_;
            } else if (bFour_ != null) {
                nb_ = aSeven_> bFour_;
            } else if (bFive_ != null) {
                nb_ = aSeven_ > bFive_;
            } else if (bSix_ != null) {
                nb_ = aSeven_ > bSix_;
            } else {
                nb_ = aSeven_ > bSeven_;
            }
        }
        Argument a_ = new Argument();
        a_.setObject(nb_);
        return a_;
    }

    @Override
    public final void analyze(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        LgNames stds_ = _conf.getStandards();
        if (chidren_.size() != 2) {
            _conf.setOkNumOp(false);
            setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
            BadOperandsNumber badNb_ = new BadOperandsNumber();
            badNb_.setFileName(_conf.getCurrentFileName());
            badNb_.setOperandsNumber(chidren_.size());
            badNb_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(badNb_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasPrimBoolean()));
            return;
        }
        ClassArgumentMatching first_ = chidren_.first().getResultClass();
        ClassArgumentMatching second_ = chidren_.last().getResultClass();
        String op_ = getOperations().getOperators().values().first().trim();
        ClassMethodIdReturn cust_ = getOperator(_conf, op_, first_, second_);
        if (cust_.isFoundMethod()) {
            setResultClass(new ClassArgumentMatching(cust_.getReturnType()));
            String foundClass_ = cust_.getRealClass();
            foundClass_ = Templates.getIdFromAllTypes(foundClass_);
            MethodId id_ = cust_.getRealId();
            classMethodId = new ClassMethodId(foundClass_, id_);
            MethodId realId_ = cust_.getRealId();
            CustList<ClassArgumentMatching> firstArgs_ = new CustList<ClassArgumentMatching>();
            for (OperationNode o: chidren_) {
                firstArgs_.add(o.getResultClass());
            }
            InvokingOperation.unwrapArgsFct(chidren_, realId_, -1, EMPTY_STRING, firstArgs_, _conf);
            return;
        }
        String stringType_ = stds_.getAliasString();
        if (first_.matchClass(stringType_) && second_.matchClass(stringType_)) {
            stringCompare = true;
            setResultClass(new ClassArgumentMatching(stds_.getAliasPrimBoolean()));
            Argument arg_ = chidren_.first().getArgument();
            if (Argument.isNullValue(arg_)) {
                _conf.setOkNumOp(false);
                StaticAccessError static_ = new StaticAccessError();
                static_.setFileName(_conf.getCurrentFileName());
                static_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().addError(static_);
            }
            arg_ = chidren_.last().getArgument();
            if (Argument.isNullValue(arg_)) {
                _conf.setOkNumOp(false);
                StaticAccessError static_ = new StaticAccessError();
                static_.setFileName(_conf.getCurrentFileName());
                static_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().addError(static_);
            }
            first_.setCheckOnlyNullPe(true);
            second_.setCheckOnlyNullPe(true);
            return;
        }
        if (first_.matchClass(stringType_) || second_.matchClass(stringType_)) {
            _conf.setOkNumOp(false);
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+getOperations().getOperators().getKey(0), _conf);
            String res_ = stds_.getAliasPrimBoolean();
            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
            un_.setRc(_conf.getCurrentLocation());
            un_.setFileName(_conf.getCurrentFileName());
            un_.setExpectedResult(stds_.getAliasString());
            un_.setOperands(first_,second_);
            _conf.getClasses().addError(un_);
            setResultClass(new ClassArgumentMatching(res_));
            return;
        }
        ClassArgumentMatching classFirst_ = PrimitiveTypeUtil.toPrimitive(first_, true, _conf);
        ClassArgumentMatching classSecond_ = PrimitiveTypeUtil.toPrimitive(second_, true, _conf);
        if (classFirst_.isPrimitive(_conf)) {
            if (classSecond_.isPrimitive(_conf)) {
                chidren_.first().getResultClass().setUnwrapObject(classFirst_);
                chidren_.last().getResultClass().setUnwrapObject(classSecond_);
                setResultClass(new ClassArgumentMatching(stds_.getAliasPrimBoolean()));
                return;
            }
            _conf.setOkNumOp(false);
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+getOperations().getOperators().getKey(0), _conf);
            String res_ = stds_.getAliasPrimBoolean();
            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
            un_.setRc(_conf.getCurrentLocation());
            un_.setFileName(_conf.getCurrentFileName());
            un_.setExpectedResult(stds_.getAliasPrimDouble());
            un_.setOperands(classFirst_,classSecond_);
            _conf.getClasses().addError(un_);
            setResultClass(new ClassArgumentMatching(res_));
            return;
        }
        _conf.setOkNumOp(false);
        if (classSecond_.isPrimitive(_conf)) {
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+getOperations().getOperators().getKey(0), _conf);
            String res_ = stds_.getAliasPrimBoolean();
            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
            un_.setRc(_conf.getCurrentLocation());
            un_.setFileName(_conf.getCurrentFileName());
            un_.setExpectedResult(stds_.getAliasPrimDouble());
            un_.setOperands(classFirst_,classSecond_);
            _conf.getClasses().addError(un_);
            setResultClass(new ClassArgumentMatching(res_));
            return;
        }
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+getOperations().getOperators().getKey(0), _conf);
        StringList expectedTypes_ = new StringList();
        expectedTypes_.add(stds_.getAliasPrimDouble());
        expectedTypes_.add(stds_.getAliasString());
        String res_ = _conf.getStandards().getAliasPrimBoolean();
        UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
        un_.setRc(_conf.getCurrentLocation());
        un_.setFileName(_conf.getCurrentFileName());
        un_.setExpectedResult(expectedTypes_.join(";"));
        un_.setOperands(classFirst_,classSecond_);
        _conf.getClasses().addError(un_);
        setResultClass(new ClassArgumentMatching(res_));
    }
    public final boolean isStringCompare() {
        return stringCompare;
    }
    @Override
    public final void analyzeAssignmentBeforeNextSibling(Analyzable _conf,
            OperationNode _nextSibling, OperationNode _previous) {
        analyzeStdAssignmentBeforeNextSibling(_conf, _nextSibling, _previous);
    }
    @Override
    public final void analyzeAssignmentAfter(Analyzable _conf) {
        analyzeStdAssignmentAfter(_conf);
    }

    @Override
    public final void quickCalculate(Analyzable _conf) {
        if (classMethodId != null || !_conf.isOkNumOp()) {
            return;
        }
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument first_ = chidren_.first().getArgument();
        if (first_.isNull()) {
            return;
        }
        Argument second_ = chidren_.last().getArgument();
        if (second_.isNull()) {
            return;
        }
        quickCalculateNotNull(_conf);
    }

    abstract void quickCalculateNotNull(Analyzable _conf);

    @Override
    public void calculate(ExecutableCode _conf) {
        if (classMethodId != null) {
            CustList<OperationNode> chidren_ = getChildrenNodes();
            CustList<Argument> arguments_ = new CustList<Argument>();
            for (OperationNode o: chidren_) {
                arguments_.add(o.getArgument());
            }
            CustList<Argument> firstArgs_ = InvokingOperation.listArguments(chidren_, -1, EMPTY_STRING, arguments_, _conf);
            String classNameFound_ = classMethodId.getClassName();
            MethodId id_ = classMethodId.getConstraints();
            Argument res_;
            res_ = ProcessMethod.calculateArgument(Argument.createVoid(), classNameFound_, id_, firstArgs_, _conf.getContextEl());
            setSimpleArgument(res_, _conf);
            return;
        }
        calculateCmp(_conf);
    }
    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        if (classMethodId != null) {
            CustList<OperationNode> chidren_ = getChildrenNodes();
            CustList<Argument> arguments_ = new CustList<Argument>();
            for (OperationNode o: chidren_) {
                arguments_.add(_nodes.getVal(o).getArgument());
            }
            CustList<Argument> firstArgs_ = InvokingOperation.listArguments(chidren_, -1, EMPTY_STRING, arguments_, _conf);
            String classNameFound_ = classMethodId.getClassName();
            MethodId id_ = classMethodId.getConstraints();
            _conf.getContextEl().setCallMethod(new CustomFoundMethod(Argument.createVoid(), classNameFound_, id_, firstArgs_));
            return Argument.createVoid();
        }
        return calculateCmp(_nodes, _conf);
    }
    abstract Argument calculateCmp(IdMap<OperationNode,ArgumentsPair> _nodes, ContextEl _conf);
    abstract void calculateCmp(ExecutableCode _conf);
    @Override
    final void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }

}
