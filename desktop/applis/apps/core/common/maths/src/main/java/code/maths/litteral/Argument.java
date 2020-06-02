package code.maths.litteral;
import code.maths.Rate;

public final class Argument {

    private Object object;

    private MathType mathType;

    public static Argument numberToArgument(String _nb) {
        Argument a_ = new Argument();
        a_.object = new Rate(_nb);
        a_.mathType = MathType.RATE;
        return a_;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object _object) {
        object = _object;
    }

    public MathType getArgClass() {
        return mathType;
    }

    public void setArgClass(MathType _argClass) {
        mathType = _argClass;
    }

}
