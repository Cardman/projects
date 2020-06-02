package cards.belote.beans;


final class DeclaringPlayerValue{

    private String declaring;

    private int value;

    String getDeclaring() {
        return declaring;
    }

    void setDeclaring(String _declaring) {
        declaring = _declaring;
    }

    int getValue() {
        return value;
    }

    void setValue(int _value) {
        value = _value;
    }

}
