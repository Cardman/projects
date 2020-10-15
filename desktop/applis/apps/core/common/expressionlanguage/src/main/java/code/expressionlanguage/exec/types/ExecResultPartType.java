package code.expressionlanguage.exec.types;

public final class ExecResultPartType {
    private final String result;
    private final ExecPartType partType;

    public ExecResultPartType(String _result, ExecPartType _partType) {
        this.result = _result;
        this.partType = _partType;
    }

    public String getResult() {
        return result;
    }

    public ExecPartType getPartType() {
        return partType;
    }
}
