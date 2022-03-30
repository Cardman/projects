package code.bean.nat.fwd.opers;

public final class NatExecVariableContent {

    private final String variableName;

    public NatExecVariableContent(NatAnaVariableContent _cont) {
        variableName = _cont.getVariableName();
    }

    public String getVariableName() {
        return variableName;
    }
}
