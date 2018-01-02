package cards.facade.enumerations;
import code.format.Format;
import code.util.consts.Constants;
import code.util.ints.Displayable;

/**Jeux de cartes utilisees*/
public enum GameEnum implements Displayable {
    BELOTE,PRESIDENT,TAROT;

    @Override
    public String display() {
        return toString(Constants.getLanguage());
    }

    public String toString(String _locale) {
        String folderName_ = ResourcesAccess.NOM_DOSSIER;
        String fileName_ = ResourcesAccess.NOM_FICHIER;
        return Format.getConstanteLangue(folderName_,fileName_, _locale, ResourcesAccess.GAMES,name());
    }
}
