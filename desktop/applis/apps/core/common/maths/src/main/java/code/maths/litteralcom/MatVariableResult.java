package code.maths.litteralcom;

public final class MatVariableResult {
    private final String name;
    private final int index;

    public MatVariableResult(String _name, int _index) {
        this.name = _name;
        this.index = _index;
    }

    public static MatVariableResult build(String _string, int _len, int _from) {
        StringBuilder name_ = new StringBuilder();
        int i_ = _from;
        while (i_ < _len) {
            char last_ = _string.charAt(i_);
            if (!MathExpUtil.isWordChar(last_)) {
                break;
            }
            name_.append(last_);
            i_++;
        }
        return new MatVariableResult(name_.toString(),i_);
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }
}
