package code.expressionlanguage.opers;
import java.lang.reflect.Array;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.exceptions.BadIndexException;
import code.expressionlanguage.exceptions.BadIndexTypeException;
import code.expressionlanguage.exceptions.BadNumberValuesException;
import code.expressionlanguage.exceptions.NotArrayException;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.Struct;
import code.util.CustList;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.exceptions.NullObjectException;

public final class ArrOperation extends MethodOperation implements SettableElResult {

    public ArrOperation(String _el, int _index, ContextEl _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
    }

    @Override
    public void analyzeLeft(CustList<OperationNode> _nodes, ContextEl _conf,
            boolean _enumContext, String _op) {
        CustList<OperationNode> chidren_ = getChildrenAmong(_nodes, true);
        if (chidren_.size() < 2) {
            setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
            throw new BadNumberValuesException(_conf.joinPages());
        }
        ClassArgumentMatching class_ = chidren_.first().getResultClass();
        OperationNode lastElement_ = null;
        if (resultCanBeSet()) {
            lastElement_ = chidren_.last();
        }
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
        if (lastElement_ != null) {
            setResultClass(class_);
            setCalculatedLater(true);
            return;
        }
        setResultClass(class_);
    }

    @Override
    public void analyzeRight(CustList<OperationNode> _nodes, ContextEl _conf,
            boolean _enumContext, String _op) {
        CustList<OperationNode> chidren_ = getChildrenAmong(_nodes, true);
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
    public void analyzeSetting(CustList<OperationNode> _nodes, ContextEl _conf,
            boolean _enumContext, String _op) {
        CustList<OperationNode> chidren_ = getChildrenAmong(_nodes, true);
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
        CustList<OperationNode> chidren_ = getChildrenAmong();
        Struct array_;
        array_ = _nodes.getVal(chidren_.first()).getArgument().getStruct();
        setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl(), _conf);
        OperationNode lastElement_ = null;
        if (resultCanBeSet()) {
            lastElement_ = chidren_.last();
            chidren_.removeLast();
        }

        for (int i = CustList.SECOND_INDEX; i < chidren_.size(); i++) {
            OperationNode op_ = chidren_.get(i);
            Object o_ = _nodes.getVal(op_).getArgument().getObject();
            array_ = getElement(array_, o_, _conf, chidren_.get(i).getIndexInEl());
        }
        if (lastElement_ != null) {
            Argument a_ = Argument.createVoid();
            a_.setStruct(array_);
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        Argument a_ = new Argument();
        a_.setArgClassName(getResultClass().getName());
        a_.setStruct(array_);
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }
    @Override
    public Argument calculateRight(IdMap<OperationNode, ArgumentsPair> _nodes,
            ContextEl _conf, String _op) {
        CustList<OperationNode> chidren_ = getChildrenAmong();
        Struct array_;
        array_ = _nodes.getVal(chidren_.first()).getArgument().getStruct();
        setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl(), _conf);
        for (int i = CustList.SECOND_INDEX; i < chidren_.size(); i++) {
            OperationNode op_ = chidren_.get(i);
            Object o_ = _nodes.getVal(op_).getArgument().getObject();
            array_ = getElement(array_, o_, _conf, chidren_.get(i).getIndexInEl());
        }
        Argument a_ = new Argument();
        a_.setArgClassName(getResultClass().getName());
        a_.setStruct(array_);
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }
    @Override
    public Argument calculateSetting(
            IdMap<OperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op) {
        CustList<OperationNode> chidren_ = getChildrenAmong();
        Struct array_;
        String arrayClass_;
        array_ = _nodes.getVal(this).getArgument().getStruct();
        arrayClass_ = _nodes.getVal(this).getArgument().getObjectClassName();
        setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl(), _conf);
        PageEl ip_ = _conf.getLastPage();
        OperationNode lastElement_ = null;
        if (resultCanBeSet()) {
            lastElement_ = chidren_.last();
            chidren_.removeLast();
        }
        if (lastElement_ != null) {
            Object o_ = _nodes.getVal(lastElement_).getArgument().getObject();
            Struct leftObj_ = getElement(array_, o_, _conf, lastElement_.getIndexInEl());
            Argument left_ = new Argument();
            arrayClass_ = PrimitiveTypeUtil.getQuickComponentType(arrayClass_);
            left_.setArgClassName(arrayClass_);
            left_.setStruct(leftObj_);
            Argument right_ = ip_.getRightArgument();
            Argument res_;
            res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op);
            setElement(array_, o_, res_.getStruct(), _conf, lastElement_.getIndexInEl());
            Argument a_ = _nodes.getVal(this).getArgument();
            setSimpleArgument(a_, _conf, _nodes);
            return a_;
        }
        Argument a_ = new Argument();
        a_.setArgClassName(getResultClass().getName());
        a_.setStruct(array_);
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }
    /**@throws BadIndexException
    @throws NullObjectException*/

    @Override
    public void calculateLeft(CustList<OperationNode> _nodes, ContextEl _conf,
            String _op) {
        CustList<OperationNode> chidren_ = getChildrenAmong(_nodes, false);
        Struct array_;
        array_ = chidren_.first().getArgument().getStruct();
        setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl(), _conf);
        OperationNode lastElement_ = null;
        if (resultCanBeSet()) {
            lastElement_ = chidren_.last();
            chidren_.removeLast();
        }
        for (int i = CustList.SECOND_INDEX; i < chidren_.size(); i++) {
            Object o_ = chidren_.get(i).getArgument().getObject();
            array_ = getElement(array_, o_, _conf, chidren_.get(i).getIndexInEl());
        }
        if (lastElement_ != null) {
            Argument a_ = Argument.createVoid();
            a_.setStruct(array_);
            setSimpleArgument(a_, _conf);
            return;
        }
        Argument a_ = new Argument();
        a_.setArgClassName(getResultClass().getName());
        a_.setStruct(array_);
        setSimpleArgument(a_, _conf);
    }

    @Override
    public void calculateRight(CustList<OperationNode> _nodes, ContextEl _conf,
            String _op) {
        CustList<OperationNode> chidren_ = getChildrenAmong(_nodes, false);
        Struct array_;
        array_ = chidren_.first().getArgument().getStruct();
        setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl(), _conf);
        for (int i = CustList.SECOND_INDEX; i < chidren_.size(); i++) {
            Object o_ = chidren_.get(i).getArgument().getObject();
            array_ = getElement(array_, o_, _conf, chidren_.get(i).getIndexInEl());
        }
        Argument a_ = new Argument();
        a_.setArgClassName(getResultClass().getName());
        a_.setStruct(array_);
        setSimpleArgument(a_, _conf);
    }

    @Override
    public void calculateSetting(CustList<OperationNode> _nodes,
            ContextEl _conf, String _op) {
        CustList<OperationNode> chidren_ = getChildrenAmong(_nodes, false);
        Struct array_;
        String arrayClass_;
        array_ = getArgument().getStruct();
        arrayClass_ = getArgument().getObjectClassName();
        setRelativeOffsetPossibleLastPage(chidren_.first().getIndexInEl(), _conf);
        PageEl ip_ = _conf.getLastPage();
        OperationNode lastElement_ = null;
        if (resultCanBeSet()) {
            lastElement_ = chidren_.last();
            chidren_.removeLast();
        }
        if (lastElement_ != null) {
            setRelativeOffsetPossibleLastPage(lastElement_.getIndexInEl(), _conf);
            Object o_ = lastElement_.getArgument().getObject();
            Struct leftObj_ = getElement(array_, o_, _conf, lastElement_.getIndexInEl());
            Argument left_ = new Argument();
            arrayClass_ = PrimitiveTypeUtil.getQuickComponentType(arrayClass_);
            left_.setArgClassName(arrayClass_);
            left_.setStruct(leftObj_);
            Argument right_ = ip_.getRightArgument();
            Argument res_;
            res_ = NumericOperation.calculateAffect(left_, _conf, right_, _op);
            setElement(array_, o_, res_.getStruct(), _conf, lastElement_.getIndexInEl());
            setSimpleArgument(getArgument(), _conf);
            return;
        }
        Argument a_ = new Argument();
        a_.setArgClassName(getResultClass().getName());
        a_.setStruct(array_);
        setSimpleArgument(a_, _conf);
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
        return new Struct(output_, PrimitiveTypeUtil.getAliasArrayClass(output_.getClass()));
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
                Array.set(arrayInst_, index_, null);
            } else {
                Array.set(arrayInst_, index_, new Struct());
            }
            return;
        }
        if (_value.isJavaObject()) {
            if (_struct.isJavaObject()) {
                Array.set(arrayInst_, index_, _value.getInstance());
                return;
            }
        }
        Array.set(arrayInst_, index_, _value);
    }
    @Override
    void calculateChildren() {
        NatTreeMap<Integer, String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }

    @Override
    public boolean resultCanBeSet() {
        return getParent() == null || ElUtil.getDirectChildren(getParent()).last() == this;
    }
}
