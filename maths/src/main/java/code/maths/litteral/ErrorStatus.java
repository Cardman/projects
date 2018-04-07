package code.maths.litteral;

public final class ErrorStatus {

    private boolean error;

    private String string = "";

    private int index = -1;

    public boolean isError() {
        return error;
    }

    public void setError(boolean _error) {
        error = _error;
    }

    public String getString() {
        return string;
    }

    public void setString(String _string) {
        string = _string;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int _index) {
        index = _index;
    }
}
