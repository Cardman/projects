package code.expressionlanguage.opers;
import java.lang.reflect.Array;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.exceptions.BadIndexTypeException;
import code.expressionlanguage.exceptions.BadNumberValuesException;
import code.expressionlanguage.exceptions.InvokeException;
import code.expressionlanguage.exceptions.NotArrayException;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.CustStruct;
import code.expressionlanguage.opers.util.NullStruct;
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
    private boolean catChars;

    public ArrOperation(int _index, ContextEl _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _importingPage, _indexChild, _m, _op);
    }

    @Override
    public void analyze(CustList<OperationNode> _nodes, ContextEl _conf,
            String _fieldName, String _op) {
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

    void analyzeCommon(ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        if (chidren_.size() < 2) {
            setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
            throw new BadNumberValuesException(_conf.joinPages());
        }
        ClassArgumentMatching class_ = chidren_.first().getResultClass();
        for (int i = CustList.SECOND_INDEX; i < chidren_.size(); i++) {
            ClassArgumentMatching indexClass_ = chidren_.get(i).getResultClass();
            setRelativeOffsetPossibleLastPage(chidren_.get(i).getIndexInEl(), _conf);
            if (!indexClass_.isNumericInt(_conf)) {
                throw new BadIndexTypeException(indexClass_+RETURN_LINE+_conf.joinPages());
            }
            setRelativeOffsetPossibleLastPage(chidren_.get(i-1).getIndexInEl(), _conf);
            if (!class_.isArray()) {
                throw new NotArrayException(class_+RETURN_LINE+_conf.joinPages());
            }
            class_ = new ClassArgumentMatching(PrimitiveTypeUtil.getQuickComponentType(class_.getName()));
        }
        LgNames stds_ = _conf.getStandards();
        String stringType_;
        String charType_;
        stringType_ = stds_.getAliasString();
        charType_ = stds_.getAliasCharacter();
        if (resultCanBeSet()) {
            if (StringList.quickEq(class_.getName(), stringType_)) {
                catString = true;
            }
            if (StringList.quickEq(class_.getName(), charType_)) {
                catChars = true;
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
        for (int i = CustList.SECOND_INDEX; i < _maxIndexChildren; i++) {
            OperationNode op_ = chidren_.get(i);
            Object o_ = _nodes.getVal(op_).getArgument().getObject();
            array_ = getElement(array_, o_, _conf, chidren_.get(i).getIndexInEl());
        }
        Argument a_ = new Argument();
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
        affectArray(array_, _nodes.getVal(lastElement_).getArgument(), lastElement_.getIndexInEl(), _op, _conf);
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }
    /**@throws BadIndexException
    @throws NullObjectException*/

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
        affectArray(array_, lastElement_.getArgument(), lastElement_.getIndexInEl(), _op, _conf);
        setSimpleArgument(a_, _conf);
    }

    void affectArray(Struct _array,Argument _index, int _indexEl, String _op, ContextEl _conf) {
        setRelativeOffsetPossibleLastPage(_indexEl, _conf);
        LgNames stds_ = _conf.getStandards();
        String null_;
        null_ = stds_.getAliasNullPe();
        Object o_ = _index.getObject();
        PageEl ip_ = _conf.getLastPage();
        Struct leftObj_ = getElement(_array, o_, _conf, _indexEl);
        Argument left_ = new Argument();
        left_.setStruct(leftObj_);
        Argument right_ = ip_.getRightArgument();
        String base_ = PrimitiveTypeUtil.getQuickComponentType(_array.getClassName(_conf));
        if (PrimitiveTypeUtil.primitiveTypeNullObject(base_, right_.getStruct(), _conf)) {
            throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
        }
        Argument res_;
        res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op, catChars, catString);
        setElement(_array, o_, res_.getStruct(), _conf);
    }

    Argument getArgument(int _maxIndexChildren, ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Struct array_;
        array_ = chidren_.first().getArgument().getStruct();
        for (int i = CustList.SECOND_INDEX; i < _maxIndexChildren; i++) {
            Object o_ = chidren_.get(i).getArgument().getObject();
            array_ = getElement(array_, o_, _conf, chidren_.get(i).getIndexInEl());
        }
        Argument a_ = new Argument();
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
            throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
        }
        if (_index == null) {
            throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
        }
        if (_conf.getClasses() != null) {
            Struct[] arrayInst_ = (Struct[]) _struct.getInstance();
            int len_ = arrayInst_.length;
            int index_ = ((Number)_index).intValue();
            if (index_ < 0 || index_ >= len_) {
                throw new InvokeException(new StdStruct(new CustomError(String.valueOf(index_)+RETURN_LINE+_conf.joinPages()),badIndex_));
            }
            return arrayInst_[index_];
        }
        Object arrayInst_ = _struct.getInstance();
        int len_ = Array.getLength(arrayInst_);
        int index_ = ((Number)_index).intValue();
        if (index_ < 0 || index_ >= len_) {
            throw new InvokeException(new StdStruct(new CustomError(String.valueOf(index_)+RETURN_LINE+_conf.joinPages()),badIndex_));
        }
        Object output_ = Array.get(arrayInst_, index_);
        if (output_ == null) {
            return NullStruct.NULL_VALUE;
        }
        return CustStruct.wrapOrId(output_);
    }
    static void setCheckedElement(Struct _array,Object _index, Argument _element, ContextEl _conf) {
        LgNames stds_ = _conf.getStandards();
        String null_;
        null_ = stds_.getAliasNullPe();
        String base_ = PrimitiveTypeUtil.getQuickComponentType(_array.getClassName(_conf));
        if (PrimitiveTypeUtil.primitiveTypeNullObject(base_, _element.getStruct(), _conf)) {
            throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
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
            throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
        }
        if (_index == null) {
            throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
        }
        if (_conf.getClasses() != null) {
            Struct[] arrayInst_ = (Struct[]) _struct.getInstance();
            int len_ = arrayInst_.length;
            int index_ = ((Number)_index).intValue();
            if (index_ < 0 || index_ >= len_) {
                throw new InvokeException(new StdStruct(new CustomError(String.valueOf(index_)+RETURN_LINE+_conf.joinPages()),badIndex_));
            }
            if (!_value.isNull()) {
                String componentType_ = PrimitiveTypeUtil.getQuickComponentType(_struct.getClassName(_conf));
                String elementType_ = _value.getClassName(_conf);
                Mapping mapping_ = new Mapping();
                mapping_.setArg(elementType_);
                mapping_.setParam(componentType_);
                if (!Templates.isCorrect(mapping_, _conf)) {
                    throw new InvokeException(new StdStruct(new CustomError(componentType_+elementType_+_conf.joinPages()),store_));
                }
            }
            arrayInst_[index_] = _value;
            return;
        }
        Object arrayInst_ = _struct.getInstance();
        int len_ = Array.getLength(arrayInst_);
        int index_ = ((Number)_index).intValue();
        if (index_ < 0 || index_ >= len_) {
            throw new InvokeException(new StdStruct(new CustomError(String.valueOf(index_)+RETURN_LINE+_conf.joinPages()),badIndex_));
        }
        if (_value.isNull()) {
            if (_struct.isJavaObject()) {
                Array.set(arrayInst_, index_, null);
            } else {
                Array.set(arrayInst_, index_, NullStruct.NULL_VALUE);
            }
            return;
        }
        String componentType_ = PrimitiveTypeUtil.getQuickComponentType(_struct.getClassName(_conf));
        String elementType_ = _value.getClassName(_conf);
        Mapping mapping_ = new Mapping();
        mapping_.setArg(elementType_);
        mapping_.setParam(componentType_);
        if (!Templates.isCorrect(mapping_, _conf)) {
            throw new InvokeException(new StdStruct(new CustomError(componentType_+elementType_+_conf.joinPages()),store_));
        }
        if (!_value.isJavaObject()) {
            Array.set(arrayInst_, index_, _value);
            return;
        }
        Array.set(arrayInst_, index_, _value.getInstance());
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
    public void setVariable() {
        variable = true;
    }
}
