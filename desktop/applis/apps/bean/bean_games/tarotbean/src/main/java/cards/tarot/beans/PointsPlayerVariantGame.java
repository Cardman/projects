package cards.tarot.beans;
import code.maths.Rate;

final class PointsPlayerVariantGame {

    private String nickname;

    private Rate pointsTricks;

    private short minimumPoints;

    private Rate differenceScore;

    private short rate;

    private short score;

    String getNickname() {
        return nickname;
    }

    void setNickname(String _nickname) {
        nickname = _nickname;
    }

    Rate getPointsTricks() {
        return pointsTricks;
    }

    void setPointsTricks(Rate _pointsTricks) {
        pointsTricks = _pointsTricks;
    }

    short getMinimumPoints() {
        return minimumPoints;
    }

    void setMinimumPoints(short _minimumPoints) {
        minimumPoints = _minimumPoints;
    }

    Rate getDifferenceScore() {
        return differenceScore;
    }

    void setDifferenceScore(Rate _differenceScore) {
        differenceScore = _differenceScore;
    }

    short getRate() {
        return rate;
    }

    void setRate(short _rate) {
        rate = _rate;
    }

    short getScore() {
        return score;
    }

    void setScore(short _score) {
        score = _score;
    }

}
