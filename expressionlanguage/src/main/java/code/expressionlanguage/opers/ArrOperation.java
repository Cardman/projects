package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadOperandsNumber;
import code.expressionlanguage.methods.util.UnexpectedTypeOperationError;
import code.expressionlanguage.opers.util.ArrayStruct;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.IdMap;
import code.util.NatTreeMap;

public final class ArrOperation extends MethodOperation implements SettableElResult, PossibleIntermediateDotted {

    private ClassArgumentMatching previousResultClass;
    private boolean intermediate;

    private Argument previousArgument;

    private boolean variable;

    private boolean catString;

    public ArrOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public ConstructorId getConstId() {
        return null;
    }

    @Override
    public void analyze(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        if (chidren_.size() != 1) {
            setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
            BadOperandsNumber badNb_ = new BadOperandsNumber();
            badNb_.setFileName(_conf.getCurrentFileName());
            badNb_.setOperandsNumber(chidren_.size());
            badNb_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().addError(badNb_);
            setResultClass(new ClassArgumentMatching(_conf.getStandards().getAliasObject()));
            return;
        }
        ClassArgumentMatching class_ = getPreviousResultClass();
        OperationNode right_ = chidren_.last();
        ClassArgumentMatching indexClass_ = right_.getResultClass();
        setRelativeOffsetPossibleAnalyzable(right_.getIndexInEl(), _conf);
        LgNames stds_ = _conf.getStandards();
        String primInt_ = stds_.getAliasPrimInteger();
        Argument rightArg_ = right_.getArgument();
        boolean convertNumber_ = false;
        if (rightArg_ != null && rightArg_.getObject() instanceof Number) {
            Number value_ = (Number) rightArg_.getObject();
            long valueUnwrapped_ = value_.longValue();
            if (valueUnwrapped_ >= Integer.MIN_VALUE && valueUnwrapped_ <= Integer.MAX_VALUE) {
                right_.getResultClass().setUnwrapObject(primInt_);
                convertNumber_ = true;
            }
        }
        if (!convertNumber_ && !indexClass_.isNumericInt(_conf)) {
            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
            un_.setRc(_conf.getCurrentLocation());
            un_.setFileName(_conf.getCurrentFileName());
            un_.setExpectedResult(_conf.getStandards().getAliasPrimInteger());
            un_.setOperands(indexClass_);
            _conf.getClasses().addError(un_);
            class_ = new ClassArgumentMatching(_conf.getStandards().getAliasObject());
            setResultClass(class_);
            return;
        }
        setRelativeOffsetPossibleAnalyzable(chidren_.first().getIndexInEl(), _conf);
        if (!class_.isArray()) {
            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
            un_.setRc(_conf.getCurrentLocation());
            un_.setFileName(_conf.getCurrentFileName());
            un_.setExpectedResult(PrimitiveTypeUtil.getPrettyArrayType(_conf.getStandards().getAliasObject()));
            un_.setOperands(class_);
            _conf.getClasses().addError(un_);
            class_ = new ClassArgumentMatching(_conf.getStandards().getAliasObject());
            setResultClass(class_);
            return;
        }
        if (!convertNumber_) {
            indexClass_.setUnwrapObject(PrimitiveTypeUtil.toPrimitive(indexClass_, true, _conf));
        }
        class_ = PrimitiveTypeUtil.getQuickComponentType(class_);
        setResultClass(class_);
    }
    @Override
    public void analyzeAssignmentBeforeNextSibling(Analyzable _conf,
            OperationNode _nextSibling, OperationNode _previous) {
        analyzeStdAssignmentBeforeNextSibling(_conf, _nextSibling, _previous);
    }
    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        analyzeStdAssignmentAfter(_conf);
    }
    @Override
    public void quickCalculate(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        if (!_conf.isGearConst()) {
            return;
        }
        if (getPreviousArgument() == null) {
            return;
        }
        Struct array_;
        array_ = getPreviousArgument().getStruct();
        if (!(array_ instanceof ArrayStruct)) {
            return;
        }
        Object o_ = chidren_.last().getArgument().getObject();
        if (!(o_ instanceof Number)) {
            return;
        }
        int index_ = ((Number)o_).intValue();
        if (index_ < 0) {
            return;
        }
        Struct[] str_ = ((ArrayStruct)array_).getInstance();
        if (index_ >= str_.length) {
            return;
        }
        Struct res_ = str_[index_];
        Argument arg_ = Argument.createVoid();
        arg_.setStruct(res_);
        setSimpleArgumentAna(arg_, _conf);
    }
    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl(), _conf);
        int max_ = chidren_.size();
        if (resultCanBeSet()) {
            max_--;
        }
        Argument a_ = getArgument(_nodes, max_, _conf);
        if (resultCanBeSet()) {
            setQuickSimpleArgument(a_, _conf, _nodes);
        } else {
            setSimpleArgument(a_, _conf, _nodes);
        }
        return a_;
    }

    Argument getArgument(IdMap<OperationNode, ArgumentsPair> _nodes,
            int _maxIndexChildren, ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Struct array_;
        array_ = _nodes.getVal(this).getPreviousArgument().getStruct();
        Argument a_ = new Argument();
        for (int i = CustList.FIRST_INDEX; i < _maxIndexChildren; i++) {
            OperationNode op_ = chidren_.get(i);
            Object o_ = _nodes.getVal(op_).getArgument().getObject();
            int indexEl_ = chidren_.get(i).getIndexInEl();
            setRelativeOffsetPossibleLastPage(indexEl_, _conf);
            array_ = InvokingOperation.getElement(array_, o_, _conf);
            if (_conf.getException() != null) {
                return a_;
            }
        }
        a_.setStruct(array_);
        return a_;
    }

    @Override
    public void calculate(ExecutableCode _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl(), _conf);
        int max_ = chidren_.size();
        if (resultCanBeSet()) {
            max_--;
        }
        Argument a_ = getArgument(max_, _conf);
        if (resultCanBeSet()) {
            setQuickSimpleArgument(a_, _conf);
        } else {
            setSimpleArgument(a_, _conf);
        }
    }

    Argument getArgument(int _maxIndexChildren, ExecutableCode _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Struct array_;
        array_ = getPreviousArgument().getStruct();
        Argument a_ = new Argument();
        for (int i = CustList.FIRST_INDEX; i < _maxIndexChildren; i++) {
            Object o_ = chidren_.get(i).getArgument().getObject();
            int indexEl_ = chidren_.get(i).getIndexInEl();
            setRelativeOffsetPossibleLastPage(indexEl_, _conf);
            array_ = InvokingOperation.getElement(array_, o_, _conf);
            if (_conf.getException() != null) {
                return a_;
            }
        }
        a_.setStruct(array_);
        return a_;
    }
    static void setCheckedElement(Struct _array,Object _index, Argument _element, ExecutableCode _conf) {
        InvokingOperation.setElement(_array, _index, _element.getStruct(), _conf, false);
    }
    
    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }

    @Override
    public boolean resultCanBeSet() {
        return variable;
    }

    @Override
    public void setVariable(boolean _variable) {
        variable = _variable;
    }

    @Override
    public void setCatenizeStrings() {
        catString = true;
    }

    @Override
    public final void setIntermediateDotted() {
        intermediate = true;
    }
    @Override
    public final boolean isIntermediateDottedOperation() {
        return intermediate;
    }

    @Override
    public final ClassArgumentMatching getPreviousResultClass() {
        return previousResultClass;
    }

    @Override
    public final void setPreviousResultClass(ClassArgumentMatching _previousResultClass) {
        setPreviousResultClass(_previousResultClass, false);
    }

    @Override
    public final void setPreviousResultClass(ClassArgumentMatching _previousResultClass, boolean _staticAccess) {
        previousResultClass = _previousResultClass;
    }

    @Override
    public final Argument getPreviousArgument() {
        return previousArgument;
    }

    @Override
    public final void setPreviousArgument(Argument _previousArgument) {
        previousArgument = _previousArgument;
    }

    @Override
    public Argument calculateSetting(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            Argument _right, boolean _convert) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument a_ = _nodes.getVal(this).getArgument();
        setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl(), _conf);
        OperationNode lastElement_ = chidren_.last();
        Struct array_;
        array_ = _nodes.getVal(this).getPreviousArgument().getStruct();
        a_.setStruct(affectArray(array_, _nodes.getVal(lastElement_).getArgument(), lastElement_.getIndexInEl(), _right, _conf, _convert));
        if (_conf.getException() != null) {
            return a_;
        }
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }

    @Override
    public void calculateSetting(ExecutableCode _conf, Argument _right, boolean _convert) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument a_ = getArgument();
        OperationNode lastElement_ = chidren_.last();
        Argument last_ = lastElement_.getArgument();
        Struct array_;
        array_ = getPreviousArgument().getStruct();
        a_.setStruct(affectArray(array_, last_, lastElement_.getIndexInEl(), _right, _conf, _convert));
        if (_conf.getException() != null) {
            return;
        }
        setSimpleArgument(a_, _conf);
    }

    @Override
    public Argument calculateCompoundSetting(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, Argument _right) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument a_ = _nodes.getVal(this).getArgument();
        Struct store_;
        store_ = a_.getStruct();
        setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl(), _conf);
        OperationNode lastElement_ = chidren_.last();
        Struct array_;
        array_ = _nodes.getVal(this).getPreviousArgument().getStruct();
        a_.setStruct(compoundAffectArray(array_, store_, _nodes.getVal(lastElement_).getArgument(), lastElement_.getIndexInEl(), _op,_right, _conf));
        if (_conf.getException() != null) {
            return a_;
        }
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }

    @Override
    public void calculateCompoundSetting(ExecutableCode _conf, String _op,
            Argument _right) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument a_ = getArgument();
        Struct store_;
        store_ = a_.getStruct();
        OperationNode lastElement_ = chidren_.last();
        Argument last_ = lastElement_.getArgument();
        Struct array_;
        array_ = getPreviousArgument().getStruct();
        a_.setStruct(compoundAffectArray(array_, store_, last_, lastElement_.getIndexInEl(), _op, _right, _conf));
        if (_conf.getException() != null) {
            return;
        }
        setSimpleArgument(a_, _conf);
    }

    @Override
    public Argument calculateSemiSetting(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, boolean _post) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument a_ = _nodes.getVal(this).getArgument();
        Struct store_;
        store_ = a_.getStruct();
        setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl(), _conf);
        OperationNode lastElement_ = chidren_.last();
        Struct array_;
        array_ = _nodes.getVal(this).getPreviousArgument().getStruct();
        a_.setStruct(semiAffectArray(array_, store_, _nodes.getVal(lastElement_).getArgument(), lastElement_.getIndexInEl(), _op, _post, _conf));
        if (_conf.getException() != null) {
            return a_;
        }
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }

    @Override
    public void calculateSemiSetting(ExecutableCode _conf, String _op,
            boolean _post) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument a_ = getArgument();
        Struct store_;
        store_ = a_.getStruct();
        OperationNode lastElement_ = chidren_.last();
        Argument last_ = lastElement_.getArgument();
        Struct array_;
        array_ = getPreviousArgument().getStruct();
        a_.setStruct(semiAffectArray(array_, store_, last_, lastElement_.getIndexInEl(), _op, _post, _conf));
        if (_conf.getException() != null) {
            return;
        }
        setSimpleArgument(a_, _conf);
    }

    Struct affectArray(Struct _array,Argument _index, int _indexEl, Argument _right, ExecutableCode _conf, boolean _convert) {
        setRelativeOffsetPossibleLastPage(_indexEl, _conf);
        Object o_ = _index.getObject();
        if (_conf.getException() != null) {
            return _right.getStruct();
        }
        InvokingOperation.setElement(_array, o_, _right.getStruct(), _conf, _convert);
        return _right.getStruct();
    }

    Struct compoundAffectArray(Struct _array,Struct _stored,Argument _index, int _indexEl, String _op, Argument _right, ExecutableCode _conf) {
        setRelativeOffsetPossibleLastPage(_indexEl, _conf);
        Object o_ = _index.getObject();
        if (_conf.getException() != null) {
            return _stored;
        }
        Argument left_ = new Argument();
        left_.setStruct(_stored);
        String clForm_ = EMPTY_STRING;
        ClassArgumentMatching clArg_;
        if (!_array.isNull()) {
            LgNames stds_ = _conf.getStandards();
            String strClass_ = stds_.getStructClassName(_array, _conf.getContextEl());
            clForm_ = PrimitiveTypeUtil.getQuickComponentType(strClass_);
            clArg_ = new ClassArgumentMatching(clForm_);
        } else {
            clArg_ = getResultClass();
        }
        Argument res_;
        res_ = NumericOperation.calculateAffect(left_, _conf, _right, _op, catString, clArg_);
        if (_conf.getException() != null) {
            return _stored;
        }
        InvokingOperation.setElement(_array, o_, res_.getStruct(), _conf, false);
        return res_.getStruct();
    }
    Struct semiAffectArray(Struct _array,Struct _stored,Argument _index, int _indexEl, String _op, boolean _post, ExecutableCode _conf) {
        setRelativeOffsetPossibleLastPage(_indexEl, _conf);
        Object o_ = _index.getObject();
        if (_conf.getException() != null) {
            return _stored;
        }
        Argument left_ = new Argument();
        left_.setStruct(_stored);
        String clForm_ = EMPTY_STRING;
        ClassArgumentMatching clArg_;
        if (!_array.isNull()) {
            LgNames stds_ = _conf.getStandards();
            String strClass_ = stds_.getStructClassName(_array, _conf.getContextEl());
            clForm_ = PrimitiveTypeUtil.getQuickComponentType(strClass_);
            clArg_ = new ClassArgumentMatching(clForm_);
        } else {
            clArg_ = getResultClass();
        }
        Argument res_;
        res_ = NumericOperation.calculateIncrDecr(left_, _conf, _op, clArg_);
        if (_conf.getException() != null) {
            return _stored;
        }
        InvokingOperation.setElement(_array, o_, res_.getStruct(), _conf, false);
        if (_post) {
            return left_.getStruct();
        }
        return res_.getStruct();
    }
}
