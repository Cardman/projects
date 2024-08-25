package cards.facade.enumerations;
import cards.facade.MessagesCardGames;
import code.sml.util.TranslationsLg;
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

    public String toString(TranslationsLg _locale) {
        return StringUtil.nullToEmpty(MessagesCardGames.getAppliTr(_locale).getMapping().getVal(MessagesCardGames.GAMES_NAMES).getMapping().getVal(getNumber()));
    }
}
