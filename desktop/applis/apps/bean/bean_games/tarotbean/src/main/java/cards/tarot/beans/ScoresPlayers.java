package cards.tarot.beans;
import code.maths.Rate;

public final class ScoresPlayers {

    private String nickname;

    private Rate rate;

    private long score;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String _nickname) {
        nickname = _nickname;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate _rate) {
        rate = _rate;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long _score) {
        score = _score;
    }

}
