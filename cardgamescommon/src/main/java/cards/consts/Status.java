package cards.consts;
import code.format.Format;
import code.util.consts.Constants;
import code.util.ints.Displayable;

/**Statut du joueur*/
public enum Status implements Displayable {
TAKER,CALLED_PLAYER,DEFENDER;

    @Override
    public String display() {
        return toString(Constants.getLanguage());
    }
    public String toString(String _locale) {
        String folderName_ = ResourcesAccess.NOM_DOSSIER;
        String fileName_ = ResourcesAccess.NOM_FICHIER;
        return Format.getConstanteLangue(folderName_,fileName_, _locale, ResourcesAccess.STATUS,name());
    }
}
