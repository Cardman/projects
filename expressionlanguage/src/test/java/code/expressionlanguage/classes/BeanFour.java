package code.expressionlanguage.classes;
import code.expressionlanguage.AccEl;

public class BeanFour {

    private int invisibleField;

    @AccEl
    private String hello;

    private Composite composite = new Composite();

    public int getInvisibleField() {
        return invisibleField;
    }

    @AccEl
    private void setInvisibleField(Integer _invisibleField) {
        invisibleField = _invisibleField;
    }

    @AccEl
    private void setInvisibleIntField(int _invisibleField) {
        invisibleField = _invisibleField;
    }

    @AccEl
    private void setter(String _invisibleField) {
        hello = _invisibleField.substring(-1);
    }

    public Composite getComposite() {
        return composite;
    }

    public void setComposite(Composite _composite) {
        composite = _composite;
    }

    public void setHello(String _hello) {
        hello = _hello;
    }

    public String getHello() {
        return hello;
    }
}
