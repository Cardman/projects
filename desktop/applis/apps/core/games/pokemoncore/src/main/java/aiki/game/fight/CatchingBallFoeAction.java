package aiki.game.fight;

public final class CatchingBallFoeAction {
    private String catchingBall;
    private String nickname;
    private byte player = Fighter.BACK;
    private boolean caught;

    public String getCatchingBall() {
        return catchingBall;
    }

    public void setCatchingBall(String _c) {
        this.catchingBall = _c;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String _n) {
        this.nickname = _n;
    }

    public byte getPlayer() {
        return player;
    }

    public void setPlayer(byte _p) {
        this.player = _p;
    }

    public boolean isCaught() {
        return caught;
    }

    public void setCaught(boolean _c) {
        this.caught = _c;
    }
}
