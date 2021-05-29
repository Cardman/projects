package code.expressionlanguage.analyze.instr;


import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.common.ConstType;

public final class VariableInfo {

    private String name;

    private ConstType kind;

    private int firstChar;

    private int lastChar;

    private RootBlock declaring;

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public ConstType getKind() {
        return kind;
    }

    public void setKind(ConstType _kind) {
        kind = _kind;
    }

    public int getFirstChar() {
        return firstChar;
    }

    public void setFirstChar(int _firstChar) {
        firstChar = _firstChar;
    }

    public int getLastChar() {
        return lastChar;
    }

    public void setLastChar(int _lastChar) {
        lastChar = _lastChar;
    }

    public RootBlock getDeclaring() {
        return declaring;
    }

    public void setDeclaring(RootBlock _declaring) {
        this.declaring = _declaring;
    }
}
