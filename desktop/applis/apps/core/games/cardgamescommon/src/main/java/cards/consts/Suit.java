package cards.consts;
import code.util.EnumList;

/**Couleurs existantes dans les jeux de cartes (Atout n'existe que pour le Tarot)*/
public enum Suit {
UNDEFINED,TRUMP,HEART,SPADE,DIAMOND,CLUB;
    public static EnumList<Suit> couleursOrdinaires(){
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for(Suit suit_:values()){
            if (notNormal(suit_)) {
                continue;
            }
            couleurs_.add(suit_);
        }
        return couleurs_;
    }

    private static boolean notNormal(Suit _suit) {
        return _suit == Suit.UNDEFINED || _suit == Suit.TRUMP;
    }

    public static boolean equalsSuits(EnumList<Suit> _one, EnumList<Suit> _two) {
        if (_one.size() != _two.size()) {
            return false;
        }
        for (Suit e: _one) {
            if (_two.indexesOfObj(e).size() != _one.indexesOfObj(e).size()) {
                return false;
            }
        }
        return true;
    }

}
