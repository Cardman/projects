package code.expressionlanguage.opers;
import java.lang.reflect.Array;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.Struct;
import code.util.CustList;
import code.util.IdMap;
import code.util.Numbers;
import code.util.consts.ConstClasses;

public abstract class InvokingOperation extends MethodOperation {

    public InvokingOperation(String _el, int _index,
            ContextEl _importingPage, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
    }

    static CustList<ClassArgumentMatching> listClasses(CustList<OperationNode> _children) {
        if (!_children.isEmpty() && _children.first().isVararg()) {
            CustList<ClassArgumentMatching> firstArgs_ = new CustList<ClassArgumentMatching>();
            CustList<ClassArgumentMatching> optArgs_ = new CustList<ClassArgumentMatching>();
            boolean opt_ = false;
            for (OperationNode o: _children) {
                if (o.isVararg()) {
                    continue;
                }
                if (o.isFirstOptArg()) {
                    opt_ = true;
                }
                if (opt_) {
                    optArgs_.add(o.getResultClass());
                } else {
                    firstArgs_.add(o.getResultClass());
                }
            }
            String name_ = _children.first().getResultClass().getName();
            name_ = PrimitiveTypeUtil.getPrettyArrayType(name_);
            ClassArgumentMatching clMatch_ = new ClassArgumentMatching(name_);
            firstArgs_.add(clMatch_);
            return firstArgs_;
        }
        CustList<ClassArgumentMatching> firstArgs_ = new CustList<ClassArgumentMatching>();
        for (OperationNode o: _children) {
            firstArgs_.add(o.getResultClass());
        }
        return firstArgs_;
    }

    static CustList<Argument> listArguments(CustList<OperationNode> _children, IdMap<OperationNode,ArgumentsPair> _nodes, boolean _nativeMethod) {
        if (!_children.isEmpty() && _children.first().isVararg()) {
            CustList<Argument> firstArgs_ = new CustList<Argument>();
            CustList<Argument> optArgs_ = new CustList<Argument>();
            boolean opt_ = false;
            for (OperationNode o: _children) {
                if (o.isVararg()) {
                    continue;
                }
                if (o.isFirstOptArg()) {
                    opt_ = true;
                }
                ArgumentsPair a_ = _nodes.getVal(o);
                if (opt_) {
                    optArgs_.add(a_.getArgument());
                } else {
                    firstArgs_.add(a_.getArgument());
                }
            }
            Object array_;
            Argument argRem_ = new Argument();
            String g_ = _children.first().getResultClass().getName();
            if (_nativeMethod) {
                OperationNode o_ = _children.first();
                String className_ = o_.getResultClass().getName();
                if (className_.startsWith(PrimitiveTypeUtil.PRIM)) {
                    className_ = className_.substring(1);
                }
                Class<?> cl_ = ConstClasses.classForNameNotInit(PrimitiveTypeUtil.getArrayClass(className_));
                array_ = Array.newInstance(cl_, optArgs_.size());
                int len_ = optArgs_.size();
                for (int i = 0; i < len_; i++) {
                    Array.set(array_, i, optArgs_.get(i).getObject());
                }
                argRem_.setObject(array_);
            } else {
                Struct str_ = PrimitiveTypeUtil.newCustomArray(g_, new Numbers<Integer>(optArgs_.size()));
                array_ = str_.getInstance();
                int len_ = optArgs_.size();
                for (int i = 0; i < len_; i++) {
                    Array.set(array_, i, optArgs_.get(i).getStruct());
                }
                argRem_.setStruct(str_);
            }
            firstArgs_.add(argRem_);
            return firstArgs_;
        }
        CustList<Argument> firstArgs_ = new CustList<Argument>();
        for (OperationNode o: _children) {
            firstArgs_.add(_nodes.getVal(o).getArgument());
        }
        return firstArgs_;
    }
    static CustList<Argument> listArguments(CustList<OperationNode> _children, CustList<Argument> _nodes, boolean _nativeMethod) {
        if (!_children.isEmpty() && _children.first().isVararg()) {
            CustList<Argument> firstArgs_ = new CustList<Argument>();
            CustList<Argument> optArgs_ = new CustList<Argument>();
            boolean opt_ = false;
            int i_ = CustList.FIRST_INDEX;
            for (OperationNode o: _children) {
                if (o.isVararg()) {
                    i_++;
                    continue;
                }
                if (o.isFirstOptArg()) {
                    opt_ = true;
                }
                Argument a_ = _nodes.get(i_);
                if (opt_) {
                    optArgs_.add(a_);
                } else {
                    firstArgs_.add(a_);
                }
                i_++;
            }
            Object array_;
            Argument argRem_ = new Argument();
            String g_ = _children.first().getResultClass().getName();
            if (_nativeMethod) {
                OperationNode o_ = _children.first();
                String className_ = o_.getResultClass().getName();
                if (className_.startsWith(PrimitiveTypeUtil.PRIM)) {
                    className_ = className_.substring(1);
                }
                Class<?> cl_ = ConstClasses.classForNameNotInit(PrimitiveTypeUtil.getArrayClass(className_));
                array_ = Array.newInstance(cl_, optArgs_.size());
                int len_ = optArgs_.size();
                for (int i = 0; i < len_; i++) {
                    Array.set(array_, i, optArgs_.get(i).getObject());
                }
                argRem_.setObject(array_);
            } else {
                Struct str_ = PrimitiveTypeUtil.newCustomArray(g_, new Numbers<Integer>(optArgs_.size()));
                array_ = str_.getInstance();
                int len_ = optArgs_.size();
                for (int i = 0; i < len_; i++) {
                    Array.set(array_, i, optArgs_.get(i).getStruct());
                }
                argRem_.setStruct(str_);
            }
            firstArgs_.add(argRem_);
            return firstArgs_;
        }
        CustList<Argument> firstArgs_ = new CustList<Argument>(_nodes);
        return firstArgs_;
    }
    static CustList<Argument> listArguments(CustList<OperationNode> _children, boolean _nativeMethod) {
        if (!_children.isEmpty() && _children.first().isVararg()) {
            CustList<Argument> firstArgs_ = new CustList<Argument>();
            CustList<Argument> optArgs_ = new CustList<Argument>();
            boolean opt_ = false;
            for (OperationNode o: _children) {
                if (o.isVararg()) {
                    continue;
                }
                if (o.isFirstOptArg()) {
                    opt_ = true;
                }
                if (opt_) {
                    optArgs_.add(o.getArgument());
                } else {
                    firstArgs_.add(o.getArgument());
                }
            }
            Object array_;
            Argument argRem_ = new Argument();
            String g_ = _children.first().getResultClass().getName();
            if (_nativeMethod) {
                OperationNode op_ = _children.first();
                String className_ = op_.getResultClass().getName();
                if (className_.startsWith(PrimitiveTypeUtil.PRIM)) {
                    className_ = className_.substring(1);
                }
                Class<?> cl_ = ConstClasses.classForNameNotInit(PrimitiveTypeUtil.getArrayClass(className_));
                array_ = Array.newInstance(cl_, optArgs_.size());
                int len_ = optArgs_.size();
                for (int i = 0; i < len_; i++) {
                    Array.set(array_, i, optArgs_.get(i).getObject());
                }
                argRem_.setObject(array_);
            } else {
                Struct str_ = PrimitiveTypeUtil.newCustomArray(g_, new Numbers<Integer>(optArgs_.size()));
                array_ = str_.getInstance();
                int len_ = optArgs_.size();
                for (int i = 0; i < len_; i++) {
                    Array.set(array_, i, optArgs_.get(i).getStruct());
                }
                argRem_.setStruct(str_);
            }
            firstArgs_.add(argRem_);
            return firstArgs_;
        }
        CustList<Argument> firstArgs_ = new CustList<Argument>();
        for (OperationNode o: _children) {
            firstArgs_.add(o.getArgument());
        }
        return firstArgs_;
    }

    abstract boolean isCallMethodCtor();
}
