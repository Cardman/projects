package cards.consts;
import code.format.Format;
import code.util.consts.Constants;
public enum MixCardsChoice {
EACH_DEAL,EACH_LAUNCHING,ONCE_ONLY,NEVER;

    @Override
    public String toString() {
        return toString(Constants.getLanguage());
    }
    public String toString(String _locale) {
        String folderName_ = ResourcesAccess.NOM_DOSSIER;
        String fileName_ = ResourcesAccess.NOM_FICHIER;
        return Format.getConstanteLangue(folderName_,fileName_, _locale, ResourcesAccess.MIX,name());
    }
}
