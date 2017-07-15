package cards.belote;
import static junitparams.JUnitParamsRunner.$;
import cards.consts.Suit;

public class CommonBeloteGame {

    GameBelote game;

    public static Object[] suits(){
        Object[] suits_ = new Object[Suit.couleursOrdinaires().size()];
        int i_ = 0;
        for (Suit c:Suit.couleursOrdinaires()) {
            suits_[i_] = $(c);
            i_++;
        }
        return suits_;
    }
}
