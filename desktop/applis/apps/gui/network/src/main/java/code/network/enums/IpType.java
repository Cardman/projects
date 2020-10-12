package code.network.enums;
import code.format.Format;
import code.resources.ResourceFiles;
import code.util.core.StringUtil;

public enum IpType {
    HOST_NAME,IP_V4,IP_V6;

    public String toString(String _locale) {
        String fichier_ = ResourceFiles.ressourceFichier(StringUtil.concat(ResourcesAccess.NOM_DOSSIER,ResourceFiles.SEPARATEUR, _locale,ResourceFiles.SEPARATEUR, ResourcesAccess.NOM_FICHIER));
        return Format.getConstanteLangue(Format.concatParts(ResourcesAccess.IP, name()), fichier_);
    }
}
