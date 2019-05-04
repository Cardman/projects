package aiki.game.fight;

public final class KeyFightRound {
    private int fight;
    private int round;
    public KeyFightRound(int _f,int _r) {
        fight = _f;
        round = _r;
    }
    public KeyFightRound(KeyFightRound _k) {
        fight = _k.fight;
        round = _k.round;
    }
    public int getFight() {
        return fight;
    }

    public void setFight(int _fight) {
        fight = _fight;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int _round) {
        round = _round;
    }

}
