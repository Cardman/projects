package cards.tarot.beans;
import code.bean.Accessible;


public final class RankingPlayerVariantGame {

    @Accessible
    private String nickname;

    @Accessible
    private short positionDiff;

    @Accessible
    private short positionOudlers;

    @Accessible
    private short positionCharacters;

    @Accessible
    private short positionStrengthCharacters;

    @Accessible
    private short finalPosition;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String _nickname) {
        nickname = _nickname;
    }

    public short getPositionDiff() {
        return positionDiff;
    }

    public void setPositionDiff(short _positionDiff) {
        positionDiff = _positionDiff;
    }

    public short getPositionOudlers() {
        return positionOudlers;
    }

    public void setPositionOudlers(short _positionOudlers) {
        positionOudlers = _positionOudlers;
    }

    public short getPositionCharacters() {
        return positionCharacters;
    }

    public void setPositionCharacters(short _positionCharacters) {
        positionCharacters = _positionCharacters;
    }

    public short getPositionStrengthCharacters() {
        return positionStrengthCharacters;
    }

    public void setPositionStrengthCharacters(short _positionStrengthCharacters) {
        positionStrengthCharacters = _positionStrengthCharacters;
    }

    public short getFinalPosition() {
        return finalPosition;
    }

    public void setFinalPosition(short _finalPosition) {
        finalPosition = _finalPosition;
    }

}
