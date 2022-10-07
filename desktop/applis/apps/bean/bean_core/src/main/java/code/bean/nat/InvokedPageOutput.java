package code.bean.nat;

public final class InvokedPageOutput {
    private final String dest;
    private final String res;

    public InvokedPageOutput(String _d, String _r) {
        dest = _d;
        res = _r;
    }

    public String getDest() {
        return dest;
    }

    public String getRes() {
        return res;
    }

}
