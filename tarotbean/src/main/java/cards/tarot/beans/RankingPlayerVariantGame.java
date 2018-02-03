package cards.tarot.beans;


final class RankingPlayerVariantGame {

    private String nickname;

    private short positionDiff;

    private short positionOudlers;

    private short positionCharacters;

    private short positionStrengthCharacters;

    private short finalPosition;

    String getNickname() {
        return nickname;
    }

    void setNickname(String _nickname) {
        nickname = _nickname;
    }

    short getPositionDiff() {
        return positionDiff;
    }

    void setPositionDiff(short _positionDiff) {
        positionDiff = _positionDiff;
    }

    short getPositionOudlers() {
        return positionOudlers;
    }

    void setPositionOudlers(short _positionOudlers) {
        positionOudlers = _positionOudlers;
    }

    short getPositionCharacters() {
        return positionCharacters;
    }

    void setPositionCharacters(short _positionCharacters) {
        positionCharacters = _positionCharacters;
    }

    short getPositionStrengthCharacters() {
        return positionStrengthCharacters;
    }

    void setPositionStrengthCharacters(short _positionStrengthCharacters) {
        positionStrengthCharacters = _positionStrengthCharacters;
    }

    short getFinalPosition() {
        return finalPosition;
    }

    void setFinalPosition(short _finalPosition) {
        finalPosition = _finalPosition;
    }

}
