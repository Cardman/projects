package code.maths.litteral;
import code.maths.MathList;
import code.maths.Rate;

public final class MbArgument {

    private boolean boolVal;
    private Rate rateVal = Rate.zero();
    private MathList listVal = new MathList();
    private ErrorStatus err = new ErrorStatus();

    private MathType mathType;

    public static MbArgument numberToArgument(String _nb) {
        MbArgument a_ = new MbArgument();
        a_.rateVal = new Rate(_nb);
        a_.mathType = MathType.RATE;
        return a_;
    }

    public boolean isBoolVal() {
        return boolVal;
    }

    public Rate getRateVal() {
        return rateVal;
    }

    public MathList getListVal() {
        return listVal;
    }

    public ErrorStatus getErr() {
        return err;
    }

    public void setObject(ErrorStatus _object) {
        err = _object;
    }

    public void setObject(Rate _object) {
        rateVal = _object;
    }

    public void setObject(boolean _object) {
        boolVal = _object;
    }

    public void setObject(MathList _object) {
        listVal = _object;
    }

    public MathType getArgClass() {
        return mathType;
    }

    public void setArgClass(MathType _argClass) {
        mathType = _argClass;
    }

}
