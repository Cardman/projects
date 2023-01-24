package code.formathtml.util;

public final class IndexButton {
    private final FormInputCoords inputCoords;
    private int count;

    public IndexButton(FormInputCoords _input) {
        this.inputCoords = _input;
    }

    public FormInputCoords getInputCoords() {
        return inputCoords;
    }

    public int getCount() {
        return count;
    }

    public void incr() {
        count++;
    }
}
