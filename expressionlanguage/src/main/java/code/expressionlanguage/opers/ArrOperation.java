package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.BadOperandsNumber;
import code.expressionlanguage.methods.util.UnexpectedTypeOperationError;
import code.expressionlanguage.opers.util.ArrayStruct;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.CharStruct;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.NumberStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class ArrOperation extends MethodOperation implements SettableElResult {

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
    public void analyze(Analyzable _conf,
            String _fieldName) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        if (chidren_.size() != 2) {
            setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
            BadOperandsNumber badNb_ = new BadOperandsNumber();
            badNb_.setFileName(_conf.getCurrentFileName());
            badNb_.setOperandsNumber(chidren_.size());
            badNb_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(badNb_);
            setResultClass(new ClassArgumentMatching(_conf.getStandards().getAliasObject()));
            return;
        }
        ClassArgumentMatching class_ = chidren_.first().getResultClass();
        ClassArgumentMatching indexClass_ = chidren_.last().getResultClass();
        setRelativeOffsetPossibleAnalyzable(chidren_.last().getIndexInEl(), _conf);
        if (!indexClass_.isNumericInt(_conf)) {
            UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
            un_.setRc(_conf.getCurrentLocation());
            un_.setFileName(_conf.getCurrentFileName());
            un_.setExpectedResult(_conf.getStandards().getAliasPrimInteger());
            un_.setOperands(new StringList(indexClass_.getName()));
            _conf.getClasses().getErrorsDet().add(un_);
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
            un_.setOperands(new StringList(class_.getName()));
            _conf.getClasses().getErrorsDet().add(un_);
            class_ = new ClassArgumentMatching(_conf.getStandards().getAliasObject());
            setResultClass(class_);
            return;
        }
        indexClass_.setUnwrapObject(PrimitiveTypeUtil.toPrimitive(indexClass_, true, _conf).getName());
        class_ = new ClassArgumentMatching(PrimitiveTypeUtil.getQuickComponentType(class_.getName()));
        setResultClass(class_);
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
        LgNames lgNames_ = _conf.getStandards();
        String aliasBoolean_ = lgNames_.getAliasBoolean();
        boolean isBool_;
        isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(aliasBoolean_, getResultClass().getName(), _conf);
        for (EntryCust<ClassField, Assignment> e: fieldsAfterLast_.entryList()) {
            Assignment b_ = e.getValue();
            fieldsAfter_.put(e.getKey(), b_.assign(isBool_));
        }
        for (StringMap<Assignment> s: variablesAfterLast_) {
            StringMap<Assignment> sm_ = new StringMap<Assignment>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                Assignment b_ = e.getValue();
                sm_.put(e.getKey(), b_.assign(isBool_));
            }
            variablesAfter_.add(sm_);
        }
        vars_.getFields().put(this, fieldsAfter_);
        vars_.getVariables().put(this, variablesAfter_);
    }
    @Override
    public void quickCalculate(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        if (!_conf.isGearConst()) {
            return;
        }
        Struct array_;
        array_ = chidren_.first().getArgument().getStruct();
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
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }

    Argument getArgument(IdMap<OperationNode, ArgumentsPair> _nodes,
            int _maxIndexChildren, ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Struct array_;
        array_ = _nodes.getVal(chidren_.first()).getArgument().getStruct();
        Argument a_ = new Argument();
        for (int i = CustList.SECOND_INDEX; i < _maxIndexChildren; i++) {
            OperationNode op_ = chidren_.get(i);
            Object o_ = _nodes.getVal(op_).getArgument().getObject();
            array_ = getElement(array_, o_, _conf, chidren_.get(i).getIndexInEl());
            if (_conf.getException() != null) {
                return a_;
            }
        }
        a_.setStruct(array_);
        return a_;
    }

    @Override
    public Argument calculateSetting(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, boolean _post) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument a_ = _nodes.getVal(this).getArgument();
        Struct store_;
        store_ = a_.getStruct();
        setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl(), _conf);
        OperationNode lastElement_ = chidren_.last();
        Struct array_;
        array_ = _nodes.getVal(chidren_.first()).getArgument().getStruct();
        a_.setStruct(affectArray(array_, store_, _nodes.getVal(lastElement_).getArgument(), lastElement_.getIndexInEl(), _op, _post, _conf));
        if (_conf.getException() != null) {
            return a_;
        }
        setSimpleArgument(a_, _conf, _nodes);
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
        setSimpleArgument(a_, _conf);
    }

    @Override
    public void calculateSetting(
            ExecutableCode _conf, String _op, boolean _post) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument a_ = getArgument();
        Struct store_;
        store_ = a_.getStruct();
        OperationNode lastElement_ = chidren_.last();
        Argument last_ = lastElement_.getArgument();
        Struct array_;
        array_ = chidren_.first().getArgument().getStruct();
        a_.setStruct(affectArray(array_, store_, last_, lastElement_.getIndexInEl(), _op, _post, _conf));
        if (_conf.getException() != null) {
            return;
        }
        setSimpleArgument(a_, _conf);
    }

    Struct affectArray(Struct _array,Struct _stored,Argument _index, int _indexEl, String _op, boolean _post, ExecutableCode _conf) {
        setRelativeOffsetPossibleLastPage(_indexEl, _conf);
        Object o_ = _index.getObject();
        PageEl ip_ = _conf.getOperationPageEl();
        Struct leftObj_;
        if (resultCanBeSet()) {
            leftObj_ = getElement(_stored, o_, _conf, _indexEl);
        } else {
            leftObj_ = _stored;
        }
        if (_conf.getException() != null) {
            return _stored;
        }
        Argument left_ = new Argument();
        left_.setStruct(leftObj_);
        Argument right_ = ip_.getRightArgument();
        Argument res_;
        res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op, catString);
        if (_conf.getException() != null) {
            return _stored;
        }
        setElement(_array, o_, res_.getStruct(), _conf);
        if (_post) {
            return left_.getStruct();
        }
        return res_.getStruct();
    }

    Argument getArgument(int _maxIndexChildren, ExecutableCode _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Struct array_;
        array_ = chidren_.first().getArgument().getStruct();
        Argument a_ = new Argument();
        for (int i = CustList.SECOND_INDEX; i < _maxIndexChildren; i++) {
            Object o_ = chidren_.get(i).getArgument().getObject();
            array_ = getElement(array_, o_, _conf, chidren_.get(i).getIndexInEl());
            if (_conf.getException() != null) {
                return a_;
            }
        }
        a_.setStruct(array_);
        return a_;
    }

    Struct getElement(Struct _struct, Object _index, ExecutableCode _conf, int _indexEl) {
        setRelativeOffsetPossibleLastPage(_indexEl, _conf);
        LgNames stds_ = _conf.getStandards();
        String null_;
        String badIndex_;
        null_ = stds_.getAliasNullPe();
        badIndex_ = stds_.getAliasBadIndex();
        if (_struct.isNull()) {
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
            return NullStruct.NULL_VALUE;
        }
        Object array_ = _struct.getInstance();
        int len_ = LgNames.getLength(array_);
        int index_ = ((Number)_index).intValue();
        if (index_ < 0 || index_ >= len_) {
            _conf.setException(new StdStruct(new CustomError(StringList.concat(String.valueOf(index_),RETURN_LINE,_conf.joinPages())),badIndex_));
            return NullStruct.NULL_VALUE;
        }
        return LgNames.getElement(array_, index_, _conf.getContextEl());
    }
    static void setCheckedElement(Struct _array,Object _index, Argument _element, ExecutableCode _conf) {
        setElement(_array, _index, _element.getStruct(), _conf);
    }
    static void setElement(Struct _struct, Object _index, Struct _value, ExecutableCode _conf) {
        LgNames stds_ = _conf.getStandards();
        String null_;
        String badIndex_;
        String store_;
        null_ = stds_.getAliasNullPe();
        badIndex_ = stds_.getAliasBadIndex();
        store_ = stds_.getAliasStore();
        if (_struct.isNull()) {
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
            return;
        }
        String strClass_ = stds_.getStructClassName(_struct, _conf.getContextEl());
        String valClass_ = stds_.getStructClassName(_value, _conf.getContextEl());
        Object instance_ = _struct.getInstance();
        int len_ = LgNames.getLength(instance_);
        int index_ = ((Number)_index).intValue();
        if (index_ < 0 || index_ >= len_) {
            _conf.setException(new StdStruct(new CustomError(StringList.concat(String.valueOf(index_),RETURN_LINE,_conf.joinPages())),badIndex_));
            return;
        }
        if (!_value.isNull()) {
            String componentType_ = PrimitiveTypeUtil.getQuickComponentType(strClass_);
            String elementType_ = valClass_;
            Mapping mapping_ = new Mapping();
            mapping_.setArg(elementType_);
            mapping_.setParam(componentType_);
            if (!Templates.isCorrect(mapping_, _conf)) {
                _conf.setException(new StdStruct(new CustomError(StringList.concat(componentType_,elementType_,_conf.joinPages())),store_));
                return;
            }
        }
        Struct value_;
        if (_value instanceof NumberStruct || _value instanceof CharStruct) {
            String componentType_ = PrimitiveTypeUtil.getQuickComponentType(strClass_);
            ClassArgumentMatching cl_ = new ClassArgumentMatching(componentType_);
            value_ = PrimitiveTypeUtil.convertObject(cl_, _value, _conf);
        } else {
            value_ = _value;
        }
        LgNames.setElement(instance_, index_, value_, _conf.getContextEl());
    }
    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
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
}
