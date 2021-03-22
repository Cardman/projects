package cards.tarot.beans;
import cards.tarot.enumerations.Miseres;
import code.util.StringMap;
import code.util.TreeMap;

public final class TarotSumDeclaringPlayer {

    private String nickname;

    private String status;

    private StringMap<Short> handfuls;

    private StringMap<Short> miseres;

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

    public StringMap<Short> getHandfuls() {
        return handfuls;
    }

    public void setHandfuls(StringMap<Short> _handfuls) {
        handfuls = _handfuls;
    }

    public StringMap<Short> getMiseres() {
        return miseres;
    }

    public void setMiseres(StringMap<Short> _miseres) {
        miseres = _miseres;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int _sum) {
        sum = _sum;
    }

}
