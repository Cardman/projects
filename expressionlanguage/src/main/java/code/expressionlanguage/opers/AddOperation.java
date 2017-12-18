package code.expressionlanguage.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.exceptions.InvokeRedinedMethException;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.StringList;
import code.util.exceptions.NullObjectException;



public final class AddOperation extends NumericOperation {

    public AddOperation(int _index, ContextEl _importingPage,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _importingPage, _indexChild, _m, _op);
    }

    static Argument addOne(Argument _arg, ContextEl _cont) {
        byte b_ = 1;
        Argument a_ = new Argument();
        a_.setObject(b_);
        return calculateSum(_arg, _cont, a_);
    }

    static Argument removeOne(Argument _arg, ContextEl _cont) {
        byte b_ = 1;
        Argument a_ = new Argument();
        a_.setObject(b_);
        return calculateDiff(_arg, _cont, a_);
    }

    /**@throws InvokeRedinedMethException
    @throws NullObjectException*/
    @Override
    Argument calculateOper(Argument _a, String _op, Argument _b, ContextEl _cont) {
        if (StringList.quickEq(_op.trim(), PLUS)) {
            return calculateSum(_a, _cont, _b);
        }
        return calculateDiff(_a, _cont, _b);
    }

    @Override
    ClassArgumentMatching analyzeOper(ClassArgumentMatching _a, String _op, ClassArgumentMatching _b, ContextEl _cont) {
        if (StringList.quickEq(_op.trim(), PLUS)) {
            if (_a.matchClass(String.class) || _b.matchClass(String.class)) {
                return new ClassArgumentMatching(String.class.getName());
            }
            if (PrimitiveTypeUtil.toPrimitive(_a, true, _cont).matchClass(PrimitiveTypeUtil.PRIM_CHAR)) {
                if (PrimitiveTypeUtil.toPrimitive(_b, true, _cont).matchClass(PrimitiveTypeUtil.PRIM_CHAR)) {
                    return new ClassArgumentMatching(String.class.getName());
                }
            }
        }
        return getResultClass(_a, _cont, _b);
    }

}
