package cards.tarot.beans;
import code.bean.Accessible;
import code.maths.Rate;

public final class ScoresPlayers {

    @Accessible
    private String nickname;

    @Accessible
    private Rate rate;

    @Accessible
    private short score;

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

    public short getScore() {
        return score;
    }

    public void setScore(short _score) {
        score = _score;
    }

}
