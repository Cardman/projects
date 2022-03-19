package code.expressionlanguage.analyze.types;

import code.expressionlanguage.common.AnaGeneType;

public final class FoundTypeIdDto {
    private final int indexInType;
    private final String input;
    private final String solved;
    private final AnaGeneType type;

    public FoundTypeIdDto(int _index, String _in, String _out, AnaGeneType _resolved) {
        this.indexInType = _index;
        this.input = _in;
        this.solved = _out;
        this.type = _resolved;
    }

    public AnaGeneType getType() {
        return type;
    }

    public int getIndexInType() {
        return indexInType;
    }

    public String getInput() {
        return input;
    }

    public String getSolved() {
        return solved;
    }
}
