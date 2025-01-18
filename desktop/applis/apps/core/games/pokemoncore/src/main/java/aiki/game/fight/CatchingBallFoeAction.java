package aiki.game.fight;

public final class CatchingBallFoeAction {
    private String catchingBall;
    private String nickname;
    private int player = Fighter.BACK;
    private boolean caught;
    private boolean team = true;

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

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int _p) {
        this.player = _p;
    }

    public boolean isCaught() {
        return caught;
    }

    public void setCaught(boolean _c) {
        this.caught = _c;
    }

    public boolean isTeam() {
        return team;
    }

    public void setTeam(boolean _t) {
        this.team = _t;
    }
}
