package code.formathtml.util;
import code.util.ints.Equallable;

public final class FormInputCoords implements Equallable<FormInputCoords> {

    private long form;

    private long input;

    @Override
    public boolean eq(FormInputCoords _obj) {
        if (_obj.form != form) {
            return false;
        }
        if (_obj.input != input) {
            return false;
        }
        return true;
    }

    public long getForm() {
        return form;
    }

    public void setForm(long _form) {
        form = _form;
    }

    public long getInput() {
        return input;
    }

    public void setInput(long _input) {
        input = _input;
    }
}
