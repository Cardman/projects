package cards.tarot.beans;
import code.maths.Rate;

public final class PointsPlayerVariantGame {

    private String nickname;

    private Rate pointsTricks;

    private short minimumPoints;

    private Rate differenceScore;

    private short rate;

    private short score;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String _nickname) {
        nickname = _nickname;
    }

    public Rate getPointsTricks() {
        return pointsTricks;
    }

    public void setPointsTricks(Rate _pointsTricks) {
        pointsTricks = _pointsTricks;
    }

    public short getMinimumPoints() {
        return minimumPoints;
    }

    public void setMinimumPoints(short _minimumPoints) {
        minimumPoints = _minimumPoints;
    }

    public Rate getDifferenceScore() {
        return differenceScore;
    }

    public void setDifferenceScore(Rate _differenceScore) {
        differenceScore = _differenceScore;
    }

    public short getRate() {
        return rate;
    }

    public void setRate(short _rate) {
        rate = _rate;
    }

    public short getScore() {
        return score;
    }

    public void setScore(short _score) {
        score = _score;
    }

}
