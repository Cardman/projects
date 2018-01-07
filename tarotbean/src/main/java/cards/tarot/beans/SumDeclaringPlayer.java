package cards.tarot.beans;
import code.bean.Accessible;
import code.util.NatTreeMap;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;

public final class SumDeclaringPlayer {

    @Accessible
    private String nickname;

    @Accessible
    private String status;

    @Accessible
    private NatTreeMap<Handfuls,Short> handfuls;

    @Accessible
    private NatTreeMap<Miseres,Short> miseres;

    @Accessible
    private int sum;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String _nickname) {
        nickname = _nickname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String _statut) {
        status = _statut;
    }

    public NatTreeMap<Handfuls,Short> getHandfuls() {
        return handfuls;
    }

    public void setHandfuls(NatTreeMap<Handfuls,Short> _handfuls) {
        handfuls = _handfuls;
    }

    public NatTreeMap<Miseres,Short> getMiseres() {
        return miseres;
    }

    public void setMiseres(NatTreeMap<Miseres,Short> _miseres) {
        miseres = _miseres;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int _sum) {
        sum = _sum;
    }

}
