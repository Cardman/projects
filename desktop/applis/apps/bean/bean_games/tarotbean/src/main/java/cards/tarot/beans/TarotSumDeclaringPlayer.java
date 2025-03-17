package cards.tarot.beans;

import code.util.StringMap;

public final class TarotSumDeclaringPlayer {

    private String nickname;

    private String status;

    private StringMap<Long> handfuls;

    private StringMap<Long> miseres;

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

    public StringMap<Long> getHandfuls() {
        return handfuls;
    }

    public void setHandfuls(StringMap<Long> _handfuls) {
        handfuls = _handfuls;
    }

    public StringMap<Long> getMiseres() {
        return miseres;
    }

    public void setMiseres(StringMap<Long> _miseres) {
        miseres = _miseres;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int _sum) {
        sum = _sum;
    }

}
