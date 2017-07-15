package code.maths.litteral;
import code.util.CustList;
import code.util.EnumList;
import code.util.StringMap;

public abstract class InvokingOperation extends MethodOperation {

    public InvokingOperation(String _el, int _index,
            StringMap<String> _importingPage, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_el, _index, _importingPage, _indexChild, _m, _op);
    }

    static EnumList<MathType> listClasses(CustList<OperationNode> _children) {
//        if (!_children.isEmpty() && _children.first().isVararg()) {
////            chidren_.first().
////            Argument[] args_ = new Argument[chidren_.size()];
//            List<ClassMatching> firstArgs_ = new List<ClassMatching>();
//            List<ClassMatching> optArgs_ = new List<ClassMatching>();
//            boolean opt_ = false;
//            for (OperationNode o: _children) {
//                if (o.isVararg()) {
//                    continue;
//                }
//                if (o.isFirstOptArg()) {
//                    opt_ = true;
//                }
//                if (opt_) {
//                    optArgs_.add(o.getResultClass());
//                } else {
//                    firstArgs_.add(o.getResultClass());
//                }
//            }
//            Class<?> cl_ = _children.first().getArgument().getArgClass();
//            Object array_ = Array.newInstance(cl_, 0);
//            ClassMatching clMatch_ = new ClassMatching(array_.getClass());
//            firstArgs_.add(clMatch_);
//            return firstArgs_;
//        }
        EnumList<MathType> firstArgs_ = new EnumList<MathType>();
        for (OperationNode o: _children) {
            firstArgs_.add(o.getResultClass());
        }
        return firstArgs_;
    }

    static CustList<Argument> listArguments(CustList<OperationNode> _children) {
//        if (!_children.isEmpty() && _children.first().isVararg()) {
////            chidren_.first().
////            Argument[] args_ = new Argument[chidren_.size()];
//            List<Argument> firstArgs_ = new List<Argument>();
//            List<Argument> optArgs_ = new List<Argument>();
//            boolean opt_ = false;
//            for (OperationNode o: _children) {
//                if (o.isVararg()) {
//                    continue;
//                }
//                if (o.isFirstOptArg()) {
//                    opt_ = true;
//                }
//                if (opt_) {
//                    optArgs_.add(o.getArgument());
//                } else {
//                    firstArgs_.add(o.getArgument());
//                }
//            }
//            Class<?> cl_ = _children.first().getArgument().getArgClass();
//            Object array_ = Array.newInstance(cl_, optArgs_.size());
//            int len_ = optArgs_.size();
//            for (int i = 0; i < len_; i++) {
//                Array.set(array_, i, optArgs_.get(i).getObject());
//            }
//            Argument argRem_ = new Argument();
//            argRem_.setArgClass(array_.getClass());
//            argRem_.setObject(array_);
//            firstArgs_.add(argRem_);
//            return firstArgs_;
//        }
        CustList<Argument> firstArgs_ = new CustList<Argument>();
        for (OperationNode o: _children) {
            firstArgs_.add(o.getArgument());
        }
        return firstArgs_;
    }
//    List<Arguem>
}
