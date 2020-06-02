package code.network.enums;
import code.format.Format;

public enum IpType {
    HOST_NAME,IP_V4,IP_V6;

    public String toString(String _locale) {
        return Format.getConstanteLangue(ResourcesAccess.NOM_DOSSIER,ResourcesAccess.NOM_FICHIER, _locale, ResourcesAccess.IP, name());
    }
}
