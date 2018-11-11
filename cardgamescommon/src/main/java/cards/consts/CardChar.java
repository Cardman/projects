package cards.consts;
import code.format.Format;

/**Figures existantes dans les jeux de cartes (L'Excuse n'existe qu'au tarot)*/
public enum CardChar {
    EXCUSE,KING,QUEEN,KNIGHT,JACK,UNDEFINED;

    public String getSymbol(String _loc) {
        String folderName_ = ResourcesAccess.NOM_DOSSIER;
        String fileName_ = ResourcesAccess.SYMBOL_CARDS_TXT;
        return Format.getConstanteLangue(folderName_, fileName_, _loc, ResourcesAccess.CHARS, name());
    }

    public String toString(String _locale) {
        String folderName_ = ResourcesAccess.NOM_DOSSIER;
        String fileName_ = ResourcesAccess.NOM_FICHIER;
        return Format.getConstanteLangue(folderName_,fileName_, _locale, ResourcesAccess.CHARS, name());
    }
}
