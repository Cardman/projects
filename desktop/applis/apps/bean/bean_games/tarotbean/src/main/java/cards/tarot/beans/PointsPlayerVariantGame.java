package cards.tarot.beans;
import code.maths.Rate;

public final class PointsPlayerVariantGame {

    private String nickname;

    private Rate pointsTricks;

    private long minimumPoints;

    private Rate differenceScore;

    private long rate;

    private long score;

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

    public long getMinimumPoints() {
        return minimumPoints;
    }

    public void setMinimumPoints(long _minimumPoints) {
        minimumPoints = _minimumPoints;
    }

    public Rate getDifferenceScore() {
        return differenceScore;
    }

    public void setDifferenceScore(Rate _differenceScore) {
        differenceScore = _differenceScore;
    }

    public long getRate() {
        return rate;
    }

    public void setRate(long _rate) {
        rate = _rate;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long _score) {
        score = _score;
    }

}
