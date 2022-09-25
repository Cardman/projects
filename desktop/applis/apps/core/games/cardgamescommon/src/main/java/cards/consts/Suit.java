package cards.consts;
import code.util.CollCapacity;
import code.util.IdList;

/**Couleurs existantes dans les jeux de cartes (Atout n'existe que pour le Tarot)*/
public enum Suit {
UNDEFINED,TRUMP,HEART,SPADE,DIAMOND,CLUB;
    public static IdList<Suit> couleursOrdinaires(){
        IdList<Suit> couleurs_ = new IdList<Suit>(new CollCapacity(4));
        ordinaires(couleurs_);
        return couleurs_;
    }
    public static IdList<Suit> toutesCouleurs(){
        IdList<Suit> couleurs_ = new IdList<Suit>(new CollCapacity(6));
        couleurs_.add(UNDEFINED);
        couleurs_.add(TRUMP);
        ordinaires(couleurs_);
        return couleurs_;
    }

    public static IdList<Suit> couleursDefinies(){
        IdList<Suit> couleurs_ = new IdList<Suit>(new CollCapacity(5));
        couleurs_.add(TRUMP);
        ordinaires(couleurs_);
        return couleurs_;
    }

    private static void ordinaires(IdList<Suit> _couleurs) {
        _couleurs.add(HEART);
        _couleurs.add(SPADE);
        _couleurs.add(DIAMOND);
        _couleurs.add(CLUB);
    }

    public static boolean equalsSuits(IdList<Suit> _one, IdList<Suit> _two) {
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
