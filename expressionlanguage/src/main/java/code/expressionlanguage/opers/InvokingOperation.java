package code.expressionlanguage.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.Struct;
import code.util.CustList;

public abstract class InvokingOperation extends MethodOperation {

    public InvokingOperation(int _index,
            ContextEl _importingPage, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _importingPage, _indexChild, _m, _op);
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

    static CustList<Argument> listArguments(CustList<OperationNode> _children, CustList<Argument> _nodes, ContextEl _context,boolean _nativeMethod) {
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
                int len_ = optArgs_.size();
                array_ = InstanceOperation.newClassicArray(_context, g_, g_, new int[]{len_});
                Struct arr_ = new Struct(array_);
                for (int i = 0; i < len_; i++) {
                    Argument chArg_ = optArgs_.get(i);
                    ArrOperation.setCheckedElement(arr_, i, chArg_, _context);
                }
                argRem_.setStruct(arr_);
            } else {
                int len_ = optArgs_.size();
                Struct[] str_ = new Struct[len_];
                for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                    Argument chArg_ = optArgs_.get(i);
                    str_[i] = chArg_.getStruct();
                }
                String clArr_ = PrimitiveTypeUtil.getPrettyArrayType(g_);
                argRem_.setStruct(new Struct(str_,clArr_));
            }
            firstArgs_.add(argRem_);
            return firstArgs_;
        }
        CustList<Argument> firstArgs_ = new CustList<Argument>(_nodes);
        return firstArgs_;
    }

    abstract boolean isCallMethodCtor();
}
