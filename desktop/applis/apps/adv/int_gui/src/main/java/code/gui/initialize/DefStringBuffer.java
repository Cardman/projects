package code.gui.initialize;

public final class DefStringBuffer implements AbsStringBuffer {
    private String buffer = "";
    @Override
    public String paste() {
        return buffer;
    }

    @Override
    public void copy(String _c) {
        buffer = _c;
    }
}
