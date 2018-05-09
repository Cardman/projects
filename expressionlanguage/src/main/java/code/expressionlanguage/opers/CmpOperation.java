package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadOperandsNumber;
import code.expressionlanguage.methods.util.UnexpectedTypeOperationError;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class CmpOperation extends PrimitiveBoolOperation {

    private static final int EQ_CMP = 0;

    private boolean stringCompare;

    public CmpOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
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
    public void analyze(Analyzable _conf,
            String _fieldName) {
        analyzeCommon(_conf);
    }

    void analyzeCommon(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        LgNames stds_ = _conf.getStandards();
        if (chidren_.size() != 2) {
            setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
            BadOperandsNumber badNb_ = new BadOperandsNumber();
            badNb_.setFileName(_conf.getCurrentFileName());
            badNb_.setOperandsNumber(chidren_.size());
            badNb_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(badNb_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasPrimBoolean()));
            return;
        }
        ClassArgumentMatching first_ = chidren_.first().getResultClass();
        ClassArgumentMatching second_ = chidren_.last().getResultClass();
        String stringType_ = stds_.getAliasString();
        if (first_.matchClass(stringType_) && second_.matchClass(stringType_)) {
            stringCompare = true;
            setResultClass(new ClassArgumentMatching(stds_.getAliasPrimBoolean()));
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
            _conf.getClasses().getErrorsDet().add(un_);
            setResultClass(new ClassArgumentMatching(res_));
            return;
        }
        ClassArgumentMatching classFirst_ = PrimitiveTypeUtil.toPrimitive(first_, true, _conf);
        ClassArgumentMatching classSecond_ = PrimitiveTypeUtil.toPrimitive(second_, true, _conf);
        if (classFirst_.isPrimitive(_conf)) {
            if (classSecond_.isPrimitive(_conf)) {
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
            _conf.getClasses().getErrorsDet().add(un_);
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
            _conf.getClasses().getErrorsDet().add(un_);
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
        _conf.getClasses().getErrorsDet().add(un_);
        setResultClass(new ClassArgumentMatching(res_));
    }
    @Override
    public void analyzeAssignmentBeforeNextSibling(Analyzable _conf,
            OperationNode _nextSibling, OperationNode _previous) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        ObjectMap<ClassField,Assignment> fieldsAfter_;
        CustList<StringMap<Assignment>> variablesAfter_;
        fieldsAfter_ = vars_.getFields().getVal(_previous);
        variablesAfter_ = vars_.getVariables().getVal(_previous);
        ObjectMap<ClassField,AssignmentBefore> fieldsBefore_ = new ObjectMap<ClassField,AssignmentBefore>();
        for (EntryCust<ClassField, Assignment> e: fieldsAfter_.entryList()) {
            Assignment b_ = e.getValue();
            fieldsBefore_.put(e.getKey(), b_.assignBefore());
        }
        vars_.getFieldsBefore().put(_nextSibling, fieldsBefore_);
        CustList<StringMap<AssignmentBefore>> variablesBefore_ = new CustList<StringMap<AssignmentBefore>>();
        for (StringMap<Assignment> s: variablesAfter_) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                Assignment b_ = e.getValue();
                sm_.put(e.getKey(), b_.assignBefore());
            }
            variablesBefore_.add(sm_);
        }
        vars_.getVariablesBefore().put(_nextSibling, variablesBefore_);
    }
    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        Block block_ = _conf.getCurrentBlock();
        AssignedVariables vars_ = _conf.getAssignedVariables().getFinalVariables().getVal(block_);
        CustList<OperationNode> children_ = getChildrenNodes();
        OperationNode last_ = children_.last();
        ObjectMap<ClassField,Assignment> fieldsAfter_ = new ObjectMap<ClassField,Assignment>();
        CustList<StringMap<Assignment>> variablesAfter_ = new CustList<StringMap<Assignment>>();
        ObjectMap<ClassField,Assignment> fieldsAfterLast_ = vars_.getFields().getVal(last_);
        CustList<StringMap<Assignment>> variablesAfterLast_ = vars_.getVariables().getVal(last_);
        for (EntryCust<ClassField, Assignment> e: fieldsAfterLast_.entryList()) {
            Assignment b_ = e.getValue();
            fieldsAfter_.put(e.getKey(), b_.assign(true));
        }
        vars_.getFields().put(this, fieldsAfter_);
        for (StringMap<Assignment> s: variablesAfterLast_) {
            StringMap<Assignment> sm_ = new StringMap<Assignment>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                Assignment b_ = e.getValue();
                sm_.put(e.getKey(), b_.assign(true));
            }
            variablesAfter_.add(sm_);
        }
        vars_.getVariables().put(this, variablesAfter_);
    }
    @Override
    public Argument calculate(IdMap<OperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        return calculateCommon(_nodes, _conf);
    }

    Argument calculateCommon(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode opOne_ = chidren_.first();
        OperationNode opTwo_ = chidren_.last();
        Argument first_ = _nodes.getVal(opOne_).getArgument();
        LgNames stds_ = _conf.getStandards();
        String null_;
        null_ = stds_.getAliasNullPe();
        if (first_.isNull()) {
            setRelativeOffsetPossibleLastPage(opOne_.getIndexInEl(), _conf);
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
            return Argument.createVoid();
        }
        Argument second_ = _nodes.getVal(opTwo_).getArgument();
        if (second_.isNull()) {
            setRelativeOffsetPossibleLastPage(opTwo_.getIndexInEl(), _conf);
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
            return Argument.createVoid();
        }
        boolean complement_ = false;
        String op_ = getOperations().getOperators().values().first().trim();
        String useOp_ = op_;
        if (StringList.quickEq(op_, LOWER_EQ)) {
            complement_ = true;
            useOp_ = GREATER;
        } else if (StringList.quickEq(op_, GREATER_EQ)) {
            complement_ = true;
            useOp_ = LOWER;
        }
        Argument arg_;
        if (StringList.quickEq(useOp_, LOWER)) {
            arg_ = calculateLower(first_, stringCompare, second_);
        } else {
            arg_ = calculateGreater(first_, stringCompare, second_);
        }
        Boolean b_ = (Boolean) arg_.getObject();
        if (complement_) {
            b_ = !b_;
            arg_.setObject(b_);
        }
        setSimpleArgument(arg_, _conf, _nodes);
        return arg_;
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument first_ = chidren_.first().getArgument();
        if (first_.isNull()) {
            setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl(), _conf);
            return;
        }
        Argument second_ = chidren_.last().getArgument();
        if (second_.isNull()) {
            setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
            return;
        }
        boolean complement_ = false;
        String op_ = getOperations().getOperators().values().first().trim();
        String useOp_ = op_;
        if (StringList.quickEq(op_, LOWER_EQ)) {
            complement_ = true;
            useOp_ = GREATER;
        } else if (StringList.quickEq(op_, GREATER_EQ)) {
            complement_ = true;
            useOp_ = LOWER;
        }
        Argument arg_;
        if (StringList.quickEq(useOp_, LOWER)) {
            arg_ = calculateLower(first_, stringCompare, second_);
        } else {
            arg_ = calculateGreater(first_, stringCompare, second_);
        }
        Boolean b_ = (Boolean) arg_.getObject();
        if (complement_) {
            b_ = !b_;
            arg_.setObject(b_);
        }
        setSimpleArgumentAna(arg_, _conf);
    }

    @Override
    public void calculate(ContextEl _conf) {
        calculateCommon(_conf);
    }

    void calculateCommon(ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument first_ = chidren_.first().getArgument();
        LgNames stds_ = _conf.getStandards();
        String null_;
        null_ = stds_.getAliasNullPe();
        if (first_.isNull()) {
            setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl(), _conf);
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
            return;
        }
        Argument second_ = chidren_.last().getArgument();
        if (second_.isNull()) {
            setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
            return;
        }
        boolean complement_ = false;
        String op_ = getOperations().getOperators().values().first().trim();
        String useOp_ = op_;
        if (StringList.quickEq(op_, LOWER_EQ)) {
            complement_ = true;
            useOp_ = GREATER;
        } else if (StringList.quickEq(op_, GREATER_EQ)) {
            complement_ = true;
            useOp_ = LOWER;
        }
        Argument arg_;
        if (StringList.quickEq(useOp_, LOWER)) {
            arg_ = calculateLower(first_, stringCompare, second_);
        } else {
            arg_ = calculateGreater(first_, stringCompare, second_);
        }
        Boolean b_ = (Boolean) arg_.getObject();
        if (complement_) {
            b_ = !b_;
            arg_.setObject(b_);
        }
        setSimpleArgument(arg_, _conf);
    }
    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }

}
