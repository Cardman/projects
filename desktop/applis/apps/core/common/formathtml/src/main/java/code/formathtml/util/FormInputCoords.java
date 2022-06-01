package code.formathtml.util;

public final class FormInputCoords {

    private long form;

    private long input;

    public boolean eq(FormInputCoords _obj) {
        if (_obj.form != form) {
            return false;
        }
        return _obj.input == input;
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
