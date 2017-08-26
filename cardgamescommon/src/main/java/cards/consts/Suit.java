package cards.consts;
import code.format.Format;
import code.util.EnumList;
import code.util.consts.Constants;

/**Couleurs existantes dans les jeux de cartes (Atout n'existe que pour le Tarot)*/
public enum Suit {
UNDEFINED,TRUMP,HEART,SPADE,DIAMOND,CLUB;
    public static EnumList<Suit> couleursOrdinaires(){
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for(Suit suit_:values()){
            if(suit_ == Suit.UNDEFINED){
                continue;
            }
            if(suit_ == Suit.TRUMP){
                continue;
            }
            couleurs_.add(suit_);
        }
        return couleurs_;
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
    @Override
    public String toString() {
        return toString(Constants.getLanguage());
    }

    public String toString(String _locale) {
        String folderName_ = ResourcesAccess.NOM_DOSSIER;
        String fileName_ = ResourcesAccess.NOM_FICHIER;
        return Format.getConstanteLangue(folderName_,fileName_, _locale, ResourcesAccess.SUIT, name());
    }
}
