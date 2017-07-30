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
import code.util.consts.ConstClasses;

public abstract class InvokingOperation extends MethodOperation {

    public InvokingOperation(String _el, int _index,
            ContextEl _importingPage, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
    }

    static CustList<ClassArgumentMatching> listClasses(CustList<OperationNode> _children) {
        if (!_children.isEmpty() && _children.first().isVararg()) {
//            chidren_.first().
//            Argument[] args_ = new Argument[chidren_.size()];
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
//            Class<?> cl_ = _children.first().getArgument().getArgClass();
            String name_ = _children.first().getResultClass().getName();
            name_ = PrimitiveTypeUtil.getPrettyArrayType(name_);
//            name_ = PrimitiveTypeUtil.getArrayType(name_);
//            Class<?> cl_ = _children.first().getResultClass().getClazz();
//            Object array_ = Array.newInstance(cl_, 0);
//            ClassArgumentMatching clMatch_ = new ClassArgumentMatching(array_.getClass().getName());
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
//            chidren_.first().
//            Argument[] args_ = new Argument[chidren_.size()];
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
            if (_nativeMethod) {
                OperationNode o_ = _children.first();
                String className_ = o_.getResultClass().getName();
                if (className_.startsWith(PrimitiveTypeUtil.PRIM)) {
                    className_ = className_.substring(1);
                }
//                ArgumentsPair op_ = _nodes.getVal(o_);
//                Argument varArg_ = op_.getArgument();
                Class<?> cl_ = ConstClasses.classAliasForNameNotInit(PrimitiveTypeUtil.getArrayClass(className_));
//                Class<?> cl_ = varArg_.getArgClass();
                array_ = Array.newInstance(cl_, optArgs_.size());
                int len_ = optArgs_.size();
                for (int i = 0; i < len_; i++) {
                    Array.set(array_, i, optArgs_.get(i).getObject());
                }
            } else {
                array_ = Array.newInstance(Struct.class, optArgs_.size());
                int len_ = optArgs_.size();
                for (int i = 0; i < len_; i++) {
                    Array.set(array_, i, optArgs_.get(i).getStruct());
                }
            }
            Argument argRem_ = new Argument();
//            String g_ = _children.first().getArgument().getArgClassName();
            String g_ = _children.first().getResultClass().getName();
//          argRem_.setArgClassName(array_.getClass().getName());
            argRem_.setArgClassName(PrimitiveTypeUtil.getPrettyArrayType(g_));
//            argRem_.setArgClassName(array_.getClass().getName());
            argRem_.setObject(array_);
            firstArgs_.add(argRem_);
            return firstArgs_;
        }
        CustList<Argument> firstArgs_ = new CustList<Argument>();
        for (OperationNode o: _children) {
            firstArgs_.add(_nodes.getVal(o).getArgument());
        }
        return firstArgs_;
    }
    static CustList<Argument> listArguments(CustList<OperationNode> _children, boolean _nativeMethod) {
        if (!_children.isEmpty() && _children.first().isVararg()) {
//            chidren_.first().
//            Argument[] args_ = new Argument[chidren_.size()];
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
            if (_nativeMethod) {
                OperationNode op_ = _children.first();
                String className_ = op_.getResultClass().getName();
                if (className_.startsWith(PrimitiveTypeUtil.PRIM)) {
                    className_ = className_.substring(1);
                }
//                Argument varArg_ = op_.getArgument();
//                Class<?> cl_ = varArg_.getArgClass();
                Class<?> cl_ = ConstClasses.classAliasForNameNotInit(PrimitiveTypeUtil.getArrayClass(className_));
                array_ = Array.newInstance(cl_, optArgs_.size());
                int len_ = optArgs_.size();
                for (int i = 0; i < len_; i++) {
                    Array.set(array_, i, optArgs_.get(i).getObject());
                }
            } else {
                array_ = Array.newInstance(Struct.class, optArgs_.size());
                int len_ = optArgs_.size();
                for (int i = 0; i < len_; i++) {
                    Array.set(array_, i, optArgs_.get(i).getStruct());
                }
            }
            Argument argRem_ = new Argument();
//            String g_ = _children.first().getArgument().getArgClassName();
            String g_ = _children.first().getResultClass().getName();
//            argRem_.setArgClassName(array_.getClass().getName());
            argRem_.setArgClassName(PrimitiveTypeUtil.getPrettyArrayType(g_));
            argRem_.setObject(array_);
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
//    List<Arguem>
}
