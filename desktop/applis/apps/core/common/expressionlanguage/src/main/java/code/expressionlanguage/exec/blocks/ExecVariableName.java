package code.expressionlanguage.exec.blocks;

public final class ExecVariableName {

    private final String type;
    private final String name;

    private final int offset;

    public ExecVariableName(String _t,String _v, int _o) {
        this.type = _t;
        this.name = _v;
        this.offset = _o;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getOffset() {
        return offset;
    }
}
