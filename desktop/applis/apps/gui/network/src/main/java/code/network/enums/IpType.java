package code.network.enums;
import code.format.Format;
import code.scripts.messages.gui.MessGuiNetGr;
import code.util.core.StringUtil;

public enum IpType {
    HOST_NAME,IP_V4,IP_V6;

    public String toString(String _locale) {
        String fichier_ = MessGuiNetGr.ms().getVal(StringUtil.concat(ResourcesAccess.NOM_DOSSIER, "/", _locale, "/", ResourcesAccess.NOM_FICHIER));
//        String fichier_ = ResourceFiles.ressourceFichier(StringUtil.concat(ResourcesAccess.NOM_DOSSIER,ResourceFiles.SEPARATEUR, _locale,ResourceFiles.SEPARATEUR, ResourcesAccess.NOM_FICHIER));
        return Format.getConstanteLangue(Format.concatParts(ResourcesAccess.IP, name()), fichier_);
    }
}
