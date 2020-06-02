package cards.tarot.beans;
import code.maths.Rate;

final class ScoresPlayers {

    private String nickname;

    private Rate rate;

    private short score;

    String getNickname() {
        return nickname;
    }

    void setNickname(String _nickname) {
        nickname = _nickname;
    }

    Rate getRate() {
        return rate;
    }

    void setRate(Rate _rate) {
        rate = _rate;
    }

    short getScore() {
        return score;
    }

    void setScore(short _score) {
        score = _score;
    }

}
