package cards.tarot.beans;
import cards.tarot.enumerations.Miseres;
import code.util.StringMap;
import code.util.TreeMap;

final class SumDeclaringPlayer {

    private String nickname;

    private String status;

    private StringMap<Short> handfuls;

    private StringMap<Short> miseres;

    private int sum;

    String getNickname() {
        return nickname;
    }

    void setNickname(String _nickname) {
        nickname = _nickname;
    }

    String getStatus() {
        return status;
    }

    void setStatus(String _statut) {
        status = _statut;
    }

    StringMap<Short> getHandfuls() {
        return handfuls;
    }

    void setHandfuls(StringMap<Short> _handfuls) {
        handfuls = _handfuls;
    }

    StringMap<Short> getMiseres() {
        return miseres;
    }

    void setMiseres(StringMap<Short> _miseres) {
        miseres = _miseres;
    }

    int getSum() {
        return sum;
    }

    void setSum(int _sum) {
        sum = _sum;
    }

}
