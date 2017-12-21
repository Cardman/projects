package code.expressionlanguage.classes;

public class BeanFour {

    private int invisibleField;


    private String hello;

    private Composite composite = new Composite();

    public int getInvisibleField() {
        return invisibleField;
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
