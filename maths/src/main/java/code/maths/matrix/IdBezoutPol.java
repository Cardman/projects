package code.maths.matrix;

public final class IdBezoutPol {
    private Polynom first;
    private Polynom second;
    private Polynom pgcd;
    private Polynom ppcm;

    public IdBezoutPol(Polynom _first, Polynom _second, Polynom _pgcd, Polynom _ppcm) {
        first = _first;
        second = _second;
        pgcd = _pgcd;
        ppcm = _ppcm;
    }

    public Polynom getFirst() {
        return first;
    }

    public Polynom getSecond() {
        return second;
    }

    public Polynom getPgcd() {
        return pgcd;
    }

    public Polynom getPpcm() {
        return ppcm;
    }
}
