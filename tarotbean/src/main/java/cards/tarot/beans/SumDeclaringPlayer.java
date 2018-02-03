package cards.tarot.beans;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.util.NatTreeMap;

final class SumDeclaringPlayer {

    private String nickname;

    private String status;

    private NatTreeMap<Handfuls,Short> handfuls;

    private NatTreeMap<Miseres,Short> miseres;

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

    NatTreeMap<Handfuls,Short> getHandfuls() {
        return handfuls;
    }

    void setHandfuls(NatTreeMap<Handfuls,Short> _handfuls) {
        handfuls = _handfuls;
    }

    NatTreeMap<Miseres,Short> getMiseres() {
        return miseres;
    }

    void setMiseres(NatTreeMap<Miseres,Short> _miseres) {
        miseres = _miseres;
    }

    int getSum() {
        return sum;
    }

    void setSum(int _sum) {
        sum = _sum;
    }

}
