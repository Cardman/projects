package cards.tarot.beans;
import code.bean.Accessible;


public final class BonusesPlayers {

    @Accessible
    private String nickname;

    @Accessible
    private short bonus;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String _nickname) {
        nickname = _nickname;
    }

    public short getBonus() {
        return bonus;
    }

    public void setBonus(short _bonus) {
        bonus = _bonus;
    }

}
