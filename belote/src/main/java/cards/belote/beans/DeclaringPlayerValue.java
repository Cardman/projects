package cards.belote.beans;
import code.bean.Accessible;


final class DeclaringPlayerValue{

    @Accessible
    private String declaring;

    @Accessible
    private int value;

    public String getDeclaring() {
        return declaring;
    }

    public void setDeclaring(String _declaring) {
        declaring = _declaring;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int _value) {
        value = _value;
    }

}
