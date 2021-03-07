package code.maths.litteraladv;

final class Res {
    private final boolean calculated;
    private final String message;

    Res(boolean _calculated, String _message) {
        this.calculated = _calculated;
        this.message = _message;
    }

    boolean isCalculated() {
        return calculated;
    }

    String getMessage() {
        return message;
    }
}
