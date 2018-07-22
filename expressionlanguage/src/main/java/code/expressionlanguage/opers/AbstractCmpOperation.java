package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.util.BadOperandsNumber;
import code.expressionlanguage.methods.util.StaticAccessError;
import code.expressionlanguage.methods.util.UnexpectedTypeOperationError;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.Numbers;
import code.util.StringList;

public abstract class AbstractCmpOperation extends PrimitiveBoolOperation {

    private static final int EQ_CMP = 0;

    private boolean stringCompare;

    public AbstractCmpOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    static Argument quickCalculateLower(Argument _a, boolean _strCmp, Argument _b) {
        if (_strCmp) {
            Argument a_ = new Argument();
            String first_ = (String)_a.getObject();
            String second_ = (String)_b.getObject();
            a_.setObject(first_.compareTo(second_) < EQ_CMP);
            return a_;
        }
        Object nbOne_ = _a.getObject();
        Number a_;
        if (nbOne_ instanceof Number) {
            a_ = (Number)nbOne_;
        } else {
            a_ = (long)((Character)nbOne_).charValue();
        }
        Object nbTwo_ = _b.getObject();
        Number b_;
        if (nbTwo_ instanceof Number) {
            b_ = (Number)nbTwo_;
        } else {
            b_ = (long)((Character)nbTwo_).charValue();
        }
        Argument arg_ = new Argument();
        arg_.setObject(Numbers.lt(a_, b_));
        return arg_;
    }

    static Argument quickCalculateGreater(Argument _a, boolean _strCmp, Argument _b) {
        if (_strCmp) {
            Argument a_ = new Argument();
            String first_ = (String)_a.getObject();
            String second_ = (String)_b.getObject();
            a_.setObject(first_.compareTo(second_) < EQ_CMP);
            return a_;
        }
        Object nbOne_ = _a.getObject();
        Number a_;
        if (nbOne_ instanceof Number) {
            a_ = (Number)nbOne_;
        } else {
            a_ = (long)((Character)nbOne_).charValue();
        }
        Object nbTwo_ = _b.getObject();
        Number b_;
        if (nbTwo_ instanceof Number) {
            b_ = (Number)nbTwo_;
        } else {
            b_ = (long)((Character)nbTwo_).charValue();
        }
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
        String stringType_ = stds_.getAliasString();
        if (first_.matchClass(stringType_) && second_.matchClass(stringType_)) {
            stringCompare = true;
            setResultClass(new ClassArgumentMatching(stds_.getAliasPrimBoolean()));
            Argument arg_ = chidren_.first().getArgument();
            if (Argument.isNullValue(arg_)) {
                StaticAccessError static_ = new StaticAccessError();
                static_.setFileName(_conf.getCurrentFileName());
                static_.setRc(_conf.getCurrentLocation());
                _conf.getClasses().addError(static_);
            }
            arg_ = chidren_.last().getArgument();
            if (Argument.isNullValue(arg_)) {
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
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+getOperations().getOperators().getKey(0), _conf);
            String res_ = stds_.getAliasPrimBoolean();
            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
            un_.setRc(_conf.getCurrentLocation());
            un_.setFileName(_conf.getCurrentFileName());
            un_.setExpectedResult(stds_.getAliasString());
            un_.setOperands(new StringList(first_.getName(),second_.getName()));
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
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+getOperations().getOperators().getKey(0), _conf);
            String res_ = stds_.getAliasPrimBoolean();
            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
            un_.setRc(_conf.getCurrentLocation());
            un_.setFileName(_conf.getCurrentFileName());
            un_.setExpectedResult(stds_.getAliasPrimDouble());
            un_.setOperands(new StringList(classFirst_.getName(),classSecond_.getName()));
            _conf.getClasses().addError(un_);
            setResultClass(new ClassArgumentMatching(res_));
            return;
        }
        if (classSecond_.isPrimitive(_conf)) {
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+getOperations().getOperators().getKey(0), _conf);
            String res_ = stds_.getAliasPrimBoolean();
            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
            un_.setRc(_conf.getCurrentLocation());
            un_.setFileName(_conf.getCurrentFileName());
            un_.setExpectedResult(stds_.getAliasPrimDouble());
            un_.setOperands(new StringList(classFirst_.getName(),classSecond_.getName()));
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
        un_.setOperands(new StringList(classFirst_.getName(),classSecond_.getName()));
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
    final void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }

}
