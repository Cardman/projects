package code.formathtml.exec.blocks;

public final class RendClass extends RendParentBlock {
    private String fullName=EMPTY_STRING;

    public RendClass(String _info) {
        fullName = _info;
    }

    public String getFullName() {
        return fullName;
    }
}
