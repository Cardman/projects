package code.expressionlanguage.opers.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.opers.SymbolOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringList;

public abstract class ExecNumericOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {
    private int opOffset;

    public ExecNumericOperation(SymbolOperation _n) {
        super(_n);
        opOffset = _n.getOpOffset();
    }

    static Argument calculateAffect(Argument _left,ExecutableCode _conf, Argument _right, String _op, boolean _catString, ClassArgumentMatching _arg) {
        ResultErrorStd res_= new ResultErrorStd();
        NumberStruct.calculateOperator(_conf, res_, _arg, _op, _catString, _left.getStruct(), _right.getStruct());
        return new Argument(res_.getResult());
    }
    static Argument calculateIncrDecr(Argument _left,ExecutableCode _conf, String _op, ClassArgumentMatching _arg) {
        Argument o_;
        if (StringList.quickEq(_op, Block.INCR)) {
            o_ = new Argument(ExecAddOperation.addOne((NumberStruct) _left.getStruct(), _conf, _arg));
        } else {
            o_ = new Argument(ExecAddOperation.removeOne((NumberStruct) _left.getStruct(), _conf, _arg));
        }
        return o_;
    }

    public static Argument calculateDivEx(Argument _a, ExecutableCode _cont, Argument _b,ClassArgumentMatching _order) {
        LgNames stds_ = _cont.getStandards();
        String div_;
        div_ = stds_.getAliasDivisionZero();
        Struct res_ = NumberStruct.calculateDiv((NumberStruct)_a.getStruct(),(NumberStruct) _b.getStruct(), _cont, _order);
        if (res_ == NullStruct.NULL_VALUE) {
            _cont.setException(new ErrorStruct(_cont,div_));
        }
        return new Argument(res_);
    }
    static Argument calculateModEx(Argument _a, ExecutableCode _cont, Argument _b,ClassArgumentMatching _order) {
        LgNames stds_ = _cont.getStandards();
        String div_;
        div_ = stds_.getAliasDivisionZero();
        Struct res_ = NumberStruct.calculateMod((NumberStruct)_a.getStruct(),(NumberStruct) _b.getStruct(), _cont, _order);
        if (res_ == NullStruct.NULL_VALUE) {
            _cont.setException(new ErrorStruct(_cont,div_));
        }
        return new Argument(res_);
    }
    public int getOpOffset() {
        return opOffset;
    }
}
