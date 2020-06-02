package code.maths;

public final class IdBezoutNb {
    private LgInt first;
    private LgInt second;
    private LgInt pgcd;
    private LgInt ppcm;

    public IdBezoutNb(LgInt _first, LgInt _second, LgInt _pgcd, LgInt _ppcm) {
        first = _first;
        second = _second;
        pgcd = _pgcd;
        ppcm = _ppcm;
    }

    public LgInt getFirst() {
        return first;
    }

    public LgInt getSecond() {
        return second;
    }

    public LgInt getPgcd() {
        return pgcd;
    }

    public LgInt getPpcm() {
        return ppcm;
    }
}
