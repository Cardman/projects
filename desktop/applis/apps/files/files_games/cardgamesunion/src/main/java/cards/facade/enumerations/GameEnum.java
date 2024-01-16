package cards.facade.enumerations;
import code.format.Format;
import code.scripts.messages.cards.MessagesGamesGames;
import code.util.CustList;
import code.util.core.StringUtil;

/**Jeux de cartes utilisees*/
public enum GameEnum {
    BELOTE("0"),PRESIDENT("1"),TAROT("2"),NONE("_");
    private final String number;

    GameEnum(String _p) {
        this.number = _p;
    }

    public String getNumber() {
        return number;
    }

    public static GameEnum getStatusTypeByName(String _env) {
        for (GameEnum e: all()) {
            if (StringUtil.quickEq(e.getNumber(),_env)) {
                return e;
            }
        }
        return GameEnum.NONE;
    }
    public static CustList<GameEnum> all() {
        CustList<GameEnum> ls_ = allValid();
        ls_.add(NONE);
        return ls_;
    }

    public static CustList<GameEnum> allValid() {
        CustList<GameEnum> ls_ = new CustList<GameEnum>();
        ls_.add(BELOTE);
        ls_.add(PRESIDENT);
        ls_.add(TAROT);
        return ls_;
    }

    public String toString(String _locale) {
        String file_ = StringUtil.nullToEmpty(MessagesGamesGames.ms().getVal(_locale));
        return Format.getConstanteLangue(number, file_);
    }
}
