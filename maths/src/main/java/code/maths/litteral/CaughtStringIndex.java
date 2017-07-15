package code.maths.litteral;

public class CaughtStringIndex {

    private final String token;

    private final int index;

    private final boolean valid;

    public CaughtStringIndex(String _token, int _index, boolean _valid) {
        token = _token;
        index = _index;
        valid = _valid;
    }

    public String getToken() {
        return token;
    }

    public int getIndex() {
        return index;
    }

    public boolean isValid() {
        return valid;
    }
}
