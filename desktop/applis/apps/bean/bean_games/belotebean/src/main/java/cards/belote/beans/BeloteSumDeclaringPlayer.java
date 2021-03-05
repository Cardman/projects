package cards.belote.beans;
import code.util.CustList;


final class BeloteSumDeclaringPlayer {

    private String nickname;

    private String statut;

    private CustList<DeclaringPlayerValue> declaring;

    private int sum;

    String getNickname() {
        return nickname;
    }

    void setNickname(String _nickname) {
        nickname = _nickname;
    }

    String getStatut() {
        return statut;
    }

    void setStatut(String _statut) {
        statut = _statut;
    }

    CustList<DeclaringPlayerValue> getDeclaring() {
        return declaring;
    }

    void setDeclaring(CustList<DeclaringPlayerValue> _declaring) {
        declaring = _declaring;
    }

    int getSum() {
        return sum;
    }

    void setSum(int _sum) {
        sum = _sum;
    }

}
