package code.util.classestest;
import code.util.ints.Equallable;


public final class KeyExample implements Equallable<KeyExample> {

    private int firstField;

    private int secondFiled;

    public KeyExample() {
    }

    public KeyExample(int _first,int _second) {
        firstField = _first;
        secondFiled = _second;
    }

    public int getFirstField() {
        return firstField;
    }

    public void setFirstField(int _firstField) {
        firstField = _firstField;
    }

    public int getSecondFiled() {
        return secondFiled;
    }

    public void setSecondFiled(int _secondFiled) {
        secondFiled = _secondFiled;
    }

    @Override
    public boolean eq(KeyExample _g) {
        if (firstField != _g.firstField) {
            return false;
        }
        if (secondFiled != _g.secondFiled) {
            return false;
        }
        return true;
    }
}
