package code.maths.matrix;

public final class QuotModPolynom {

    private Polynom quot;
    private Polynom mod;

    public QuotModPolynom(Polynom _quot, Polynom _mod) {
        quot = _quot;
        mod = _mod;
    }

    public Polynom getQuot() {
        return quot;
    }
    public Polynom getMod() {
        return mod;
    }
}
