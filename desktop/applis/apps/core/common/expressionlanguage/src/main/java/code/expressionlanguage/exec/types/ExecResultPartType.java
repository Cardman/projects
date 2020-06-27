package code.expressionlanguage.exec.types;

public final class ExecResultPartType {
    private final String result;
    private final ExecPartType partType;

    public ExecResultPartType(String result, ExecPartType partType) {
        this.result = result;
        this.partType = partType;
    }

    public String getResult() {
        return result;
    }

    public ExecPartType getPartType() {
        return partType;
    }
}
