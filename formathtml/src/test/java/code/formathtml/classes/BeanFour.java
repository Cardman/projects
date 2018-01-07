package code.formathtml.classes;
import code.bean.Accessible;
import code.bean.Bean;

public class BeanFour extends Bean {

    private int invisibleField;

    @Accessible
    private String hello;

    private Composite composite = new Composite();

    public BeanFour() {
        setClassName("code.formathtml.classes.BeanFour");
    }

    public int getInvisibleField() {
        return invisibleField;
    }

    @Accessible
    private void setInvisibleField(Integer _invisibleField) {
        invisibleField = _invisibleField;
    }

    @Accessible
    private void setInvisibleIntField(int _invisibleField) {
        invisibleField = _invisibleField;
    }

    @Accessible
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
