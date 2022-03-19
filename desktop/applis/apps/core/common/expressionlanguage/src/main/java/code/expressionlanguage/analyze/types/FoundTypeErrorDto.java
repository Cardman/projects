package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;

public class FoundTypeErrorDto {
    private final int indexInType;
    private final String input;
    private final FoundErrorInterpret solved;
    private final boolean voidType;

    public FoundTypeErrorDto(int _index, String _in, FoundErrorInterpret _out) {
        this(_index,_in,_out,false);
    }

    public FoundTypeErrorDto(int _index, String _in, FoundErrorInterpret _out, boolean _voidTy) {
        this.indexInType = _index;
        this.input = _in;
        this.solved = _out;
        this.voidType = _voidTy;
    }

    public int getIndexInType() {
        return indexInType;
    }

    public String getInput() {
        return input;
    }

    public FoundErrorInterpret getSolved() {
        return solved;
    }

    public boolean isVoidType() {
        return voidType;
    }
}
