package code.formathtml;

public final class RendClass extends RendParentBlock {
    private String fullName=EMPTY_STRING;

    public RendClass(int _offsetTrim, String _info) {
        super(_offsetTrim);
        fullName = _info;
    }

    public String getFullName() {
        return fullName;
    }
}
