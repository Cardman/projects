package cards.belote.beans;
import code.util.CustList;


final class SumDeclaringPlayer{

    private String nickname;

    private String statut;

    private CustList<DeclaringPlayerValue> declaring;

    private int sum;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String _nickname) {
        nickname = _nickname;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String _statut) {
        statut = _statut;
    }

    public CustList<DeclaringPlayerValue> getDeclaring() {
        return declaring;
    }

    public void setDeclaring(CustList<DeclaringPlayerValue> _declaring) {
        declaring = _declaring;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int _sum) {
        sum = _sum;
    }

}
