package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.methods.util.UnexpectedTypeOperationError;
import code.expressionlanguage.opers.util.CharStruct;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.NumberStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.StringList;

public final class ArrOperation extends MethodOperation implements SettableElResult {

    private boolean variable;

    private boolean catString;

    public ArrOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyze(Analyzable _conf,
            String _fieldName) {
        analyzeCommon(_conf);
    }

    @Override
    public boolean isOtherConstructorClass() {
        return false;
    }

    @Override
    public ConstructorId getConstId() {
        return null;
    }

    @Override
    public boolean isPossibleInitClass() {
        return false;
    }
    @Override
    public boolean isSuperConstructorCall() {
        return false;
    }

    void analyzeCommon(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        ClassArgumentMatching class_ = chidren_.first().getResultClass();
        ClassArgumentMatching indexClass_ = chidren_.last().getResultClass();
        setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
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
        setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl(), _conf);
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
        class_ = new ClassArgumentMatching(PrimitiveTypeUtil.getQuickComponentType(class_.getName()));
        LgNames stds_ = _conf.getStandards();
        String stringType_;
        stringType_ = stds_.getAliasString();
        if (resultCanBeSet()) {
            if (StringList.quickEq(class_.getName(), stringType_)) {
                catString = true;
            }
        }
        setResultClass(class_);
    }

    @Override
    public Argument calculate(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf, String _op) {
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
            String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument a_ = _nodes.getVal(this).getArgument();
        Struct array_;
        array_ = a_.getStruct();
        setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl(), _conf);
        OperationNode lastElement_ = chidren_.last();
        Struct arg_;
        arg_ = _nodes.getVal(chidren_.first()).getArgument().getStruct();
        affectArray(arg_, array_, _nodes.getVal(lastElement_).getArgument(), lastElement_.getIndexInEl(), _op, _conf);
        if (_conf.getException() != null) {
            return a_;
        }
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }

    @Override
    public void calculate(CustList<OperationNode> _nodes, ContextEl _conf,
            String _op) {
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
    public void calculateSetting(CustList<OperationNode> _nodes,
            ContextEl _conf, String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Argument a_ = getArgument();
        Struct array_;
        array_ = a_.getStruct();
        OperationNode lastElement_ = chidren_.last();
        Argument last_ = lastElement_.getArgument();
        Struct arg_;
        arg_ = chidren_.first().getArgument().getStruct();
        affectArray(arg_, array_, last_, lastElement_.getIndexInEl(), _op, _conf);
        if (_conf.getException() != null) {
            return;
        }
        setSimpleArgument(a_, _conf);
    }

    void affectArray(Struct _array,Struct _stored,Argument _index, int _indexEl, String _op, ContextEl _conf) {
        setRelativeOffsetPossibleLastPage(_indexEl, _conf);
        LgNames stds_ = _conf.getStandards();
        String null_;
        null_ = stds_.getAliasNullPe();
        Object o_ = _index.getObject();
        PageEl ip_ = _conf.getLastPage();
        Struct leftObj_;
        if (_stored.isArray()) {
            leftObj_ = getElement(_stored, o_, _conf, _indexEl);
        } else {
            leftObj_ = _stored;
        }
        if (_conf.getException() != null) {
            return;
        }
        Argument left_ = new Argument();
        left_.setStruct(leftObj_);
        Argument right_ = ip_.getRightArgument();
        String base_ = PrimitiveTypeUtil.getQuickComponentType(stds_.getStructClassName(_array, _conf));
        if (getParent() instanceof AffectationOperation || _conf.isCheckAffectation()) {
            if (PrimitiveTypeUtil.primitiveTypeNullObject(base_, right_.getStruct(), _conf)) {
                _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
                return;
            }
        } else {
            if (PrimitiveTypeUtil.primitiveTypeNullObject(base_, leftObj_, _conf)) {
                _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
                return;
            }
        }
        Argument res_;
        res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op, catString);
        if (_conf.getException() != null) {
            return;
        }
        setElement(_array, o_, res_.getStruct(), _conf);
    }

    Argument getArgument(int _maxIndexChildren, ContextEl _conf) {
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

    Struct getElement(Struct _struct, Object _index, ContextEl _conf, int _indexEl) {
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
        if (_index == null) {
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
        return LgNames.getElement(array_, index_, _conf);
    }
    static void setCheckedElement(Struct _array,Object _index, Argument _element, ContextEl _conf) {
        LgNames stds_ = _conf.getStandards();
        String null_;
        null_ = stds_.getAliasNullPe();
        String base_ = PrimitiveTypeUtil.getQuickComponentType(stds_.getStructClassName(_array, _conf));
        if (PrimitiveTypeUtil.primitiveTypeNullObject(base_, _element.getStruct(), _conf)) {
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
            return;
        }
        setElement(_array, _index, _element.getStruct(), _conf);
    }
    static void setElement(Struct _struct, Object _index, Struct _value, ContextEl _conf) {
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
        if (_index == null) {
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
            return;
        }
        String strClass_ = stds_.getStructClassName(_struct, _conf);
        String valClass_ = stds_.getStructClassName(_value, _conf);
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
        LgNames.setElement(instance_, index_, value_, _conf);
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
