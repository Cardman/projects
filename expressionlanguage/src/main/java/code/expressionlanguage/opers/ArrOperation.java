package code.expressionlanguage.opers;
import java.lang.reflect.Array;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.exceptions.BadIndexException;
import code.expressionlanguage.exceptions.BadIndexTypeException;
import code.expressionlanguage.exceptions.BadNumberValuesException;
import code.expressionlanguage.exceptions.DynamicArrayStoreException;
import code.expressionlanguage.exceptions.NotArrayException;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.Struct;
import code.util.CustList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.exceptions.NullObjectException;

public final class ArrOperation extends MethodOperation implements SettableElResult {

    private boolean variable;

    public ArrOperation(String _el, int _index, ContextEl _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
    }

    @Override
    public void analyze(CustList<OperationNode> _nodes, ContextEl _conf,
            boolean _enumContext, String _op) {
        analyzeCommon(_conf);
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
            if (!indexClass_.isNumericInt()) {
                throw new BadIndexTypeException(indexClass_+RETURN_LINE+_conf.joinPages());
            }
            setRelativeOffsetPossibleLastPage(chidren_.get(i-1).getIndexInEl(), _conf);
            if (!class_.isArray()) {
                throw new NotArrayException(class_+RETURN_LINE+_conf.joinPages());
            }
            class_ = new ClassArgumentMatching(PrimitiveTypeUtil.getQuickComponentType(class_.getName()));
        }
        setResultClass(class_);
    }

    @Override
    public Argument calculateLeft(IdMap<OperationNode, ArgumentsPair> _nodes,
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
    @Override
    public Argument calculateRight(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf, String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl(), _conf);
        Argument a_ = getArgument(_nodes, chidren_.size(), _conf);
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
        Struct array_;
        array_ = _nodes.getVal(this).getArgument().getStruct();
        setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl(), _conf);
        OperationNode lastElement_ = null;
        if (resultCanBeSet()) {
            lastElement_ = chidren_.last();
        }
        if (lastElement_ != null) {
            affectArray(array_, _nodes.getVal(lastElement_).getArgument(), lastElement_.getIndexInEl(), _op, _conf);
            Argument a_ = _nodes.getVal(this).getArgument();
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        Argument a_ = new Argument();
        a_.setStruct(array_);
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }
    /**@throws BadIndexException
    @throws NullObjectException*/

    @Override
    public void calculateLeft(CustList<OperationNode> _nodes, ContextEl _conf,
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
    public void calculateRight(CustList<OperationNode> _nodes, ContextEl _conf,
            String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl(), _conf);
        Argument a_ = getArgument(chidren_.size(), _conf);
        setSimpleArgument(a_, _conf);
    }

    @Override
    public void calculateSetting(CustList<OperationNode> _nodes,
            ContextEl _conf, String _op) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        Struct array_;
        array_ = getArgument().getStruct();
        OperationNode lastElement_ = null;
        if (resultCanBeSet()) {
            lastElement_ = chidren_.last();
        }
        if (lastElement_ != null) {
            affectArray(array_, lastElement_.getArgument(), lastElement_.getIndexInEl(), _op, _conf);
            setSimpleArgument(getArgument(), _conf);
            return;
        }
        Argument a_ = new Argument();
        a_.setStruct(array_);
        setSimpleArgument(a_, _conf);
    }

    void affectArray(Struct _array,Argument _index, int _indexEl, String _op, ContextEl _conf) {
        setRelativeOffsetPossibleLastPage(_indexEl, _conf);
        Object o_ = _index.getObject();
        PageEl ip_ = _conf.getLastPage();
        Struct leftObj_ = getElement(_array, o_, _conf, _indexEl);
        Argument left_ = new Argument();
        left_.setStruct(leftObj_);
        Argument right_ = ip_.getRightArgument();
        Argument res_;
        res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op);
        setElement(_array, o_, res_.getStruct(), _conf, _indexEl);
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
        if (_struct.isNull()) {
            throw new NullObjectException(_conf.joinPages());
        }
        if (_index == null) {
            throw new NullObjectException(_conf.joinPages());
        }
        Object arrayInst_ = _struct.getInstance();
        int len_ = Array.getLength(arrayInst_);
        int index_ = ((Number)_index).intValue();
        if (index_ < 0 || index_ >= len_) {
            throw new BadIndexException(String.valueOf(index_)+RETURN_LINE+_conf.joinPages());
        }
        Object output_ = Array.get(arrayInst_, index_);
        if (output_ == null) {
            return new Struct();
        }
        if (output_ instanceof Struct) {
            return (Struct)output_;
        }
        return new Struct(output_);
    }
    void setElement(Struct _struct, Object _index, Struct _value, ContextEl _conf, int _indexEl) {
        setRelativeOffsetPossibleLastPage(_indexEl, _conf);
        if (_struct.isNull()) {
            throw new NullObjectException(_conf.joinPages());
        }
        if (_index == null) {
            throw new NullObjectException(_conf.joinPages());
        }
        Object arrayInst_ = _struct.getInstance();
        int len_ = Array.getLength(arrayInst_);
        int index_ = ((Number)_index).intValue();
        if (index_ < 0 || index_ >= len_) {
            throw new BadIndexException(String.valueOf(index_)+RETURN_LINE+_conf.joinPages());
        }
        if (_value.isNull()) {
            if (_struct.isJavaObject()) {
                if (arrayInst_.getClass().getComponentType().isPrimitive()) {
                    throw new NullObjectException(_conf.joinPages());
                }
                Array.set(arrayInst_, index_, null);
            } else {
                Array.set(arrayInst_, index_, new Struct());
            }
            return;
        }
        String componentType_ = PrimitiveTypeUtil.getQuickComponentType(_struct.getClassName());
        String elementType_ = _value.getClassName();
        if (!PrimitiveTypeUtil.canBeUseAsArgument(componentType_, elementType_, _conf.getClasses())) {
            throw new DynamicArrayStoreException(componentType_, elementType_, _conf.joinPages());
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
