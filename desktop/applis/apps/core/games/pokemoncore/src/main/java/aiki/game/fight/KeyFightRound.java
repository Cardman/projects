package aiki.game.fight;

public final class KeyFightRound {
    private final int fight;
    private final int round;
    public KeyFightRound(int _f,int _r) {
        fight = _f;
        round = _r;
    }
    public KeyFightRound(KeyFightRound _k) {
        fight = _k.fight;
        round = _k.round;
    }
    public KeyFightRound next() {
        return new KeyFightRound(fight,round+1);
    }
    public KeyFightRound nextFight() {
        return new KeyFightRound(fight+1,0);
    }
    public KeyFightRound previous() {
        return new KeyFightRound(fight,round-1);
    }
    public int getFight() {
        return fight;
    }

    public int getRound() {
        return round;
    }

}
