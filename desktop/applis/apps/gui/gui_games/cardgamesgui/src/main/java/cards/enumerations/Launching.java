package cards.enumerations;
import code.format.Format;
import code.scripts.messages.cards.MessagesCardsAll;
import code.util.core.StringUtil;

public enum Launching {
    WELCOME;

    public static String getConstanteLangue(String _file, String _group, String _nomConstante) {
        String fichier_ = MessagesCardsAll.ms().getVal(_file);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
        return Format.getConstanteLangue(Format.concatParts(_group,_nomConstante), fichier_);
    }

    public String toString(String _locale) {
        return getConstanteLangue(StringUtil.concat(ResourcesAccess.NOM_DOSSIER, "/", _locale, "/", ResourcesAccess.NOM_FICHIER), ResourcesAccess.LAUNCH,name());
    }
}
