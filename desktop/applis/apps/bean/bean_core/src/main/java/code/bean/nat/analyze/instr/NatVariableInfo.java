package code.bean.nat.analyze.instr;

public final class NatVariableInfo {

    private String name;

    private int firstNatChar;

    private int lastNatChar;

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public int getFirstNatChar() {
        return firstNatChar;
    }

    public void setFirstNatChar(int _firstChar) {
        firstNatChar = _firstChar;
    }

    public int getLastNatChar() {
        return lastNatChar;
    }

    public void setLastNatChar(int _lastChar) {
        lastNatChar = _lastChar;
    }


}
