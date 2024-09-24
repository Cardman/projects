package cards.facade.enumerations;
import cards.facade.MessagesCardGames;
import cards.solitaire.SolitaireType;
import code.sml.util.TranslationsLg;
import code.util.CustList;
import code.util.core.StringUtil;

/**Jeux de cartes utilisees*/
public enum GameEnum {
    BELOTE("0"),PRESIDENT("1"),TAROT("2"),CLASSIC("3",SolitaireType.CLASSIC),FREECELL("4",SolitaireType.FREECELL), SPIDER("5",SolitaireType.SPIDER),NONE("_");
    private final String number;
    private final SolitaireType solitaireType;

    GameEnum(String _p) {
        this(_p,null);
    }

    GameEnum(String _p, SolitaireType _s) {
        this.number = _p;
        this.solitaireType = _s;
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
        CustList<GameEnum> ls_ = allValidPlusSolo();
        ls_.add(NONE);
        return ls_;
    }

    public SolitaireType getSolitaireType() {
        return solitaireType;
    }

    public static CustList<GameEnum> allValidPlusSolo() {
        CustList<GameEnum> ls_ = allValid();
        ls_.add(GameEnum.CLASSIC);
        ls_.add(GameEnum.FREECELL);
        ls_.add(GameEnum.SPIDER);
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
