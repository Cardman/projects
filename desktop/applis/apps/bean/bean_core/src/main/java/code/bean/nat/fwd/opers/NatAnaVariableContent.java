package code.bean.nat.fwd.opers;

public final class NatAnaVariableContent {

    private String variableName = "";

    private final int off;

    public NatAnaVariableContent(int _off) {
        off = _off;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String _variableName) {
        this.variableName = _variableName;
    }

    public int getOff() {
        return off;
    }

}
