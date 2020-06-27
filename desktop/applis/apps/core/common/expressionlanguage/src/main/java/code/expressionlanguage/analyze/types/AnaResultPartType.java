package code.expressionlanguage.analyze.types;

public final class AnaResultPartType {
    private final String result;
    private final AnaPartType partType;

    public AnaResultPartType(String _result, AnaPartType _partType) {
        result = _result;
        partType = _partType;
    }

    public AnaPartType getPartType() {
        return partType;
    }

    public String getResult() {
        return result;
    }
}
