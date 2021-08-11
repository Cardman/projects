package code.bean.nat.fwd.opers;

public final class NatExecVariableContent {

    private final String variableName;

    private final int off;

    public NatExecVariableContent(NatAnaVariableContent _cont) {
        variableName = _cont.getVariableName();
        off = _cont.getOff();
    }

    public int getOff() {
        return off;
    }

    public String getVariableName() {
        return variableName;
    }
}
