package code.network.enums;
import code.format.Format;
import code.util.consts.Constants;
import code.util.ints.Displayable;

public enum IpType implements Displayable {
    HOST_NAME,IP_V4,IP_V6;

    @Override
    public String display() {
        return toString(Constants.getLanguage());
    }

    public String toString(String _locale) {
        return Format.getConstanteLangue(ResourcesAccess.NOM_DOSSIER,ResourcesAccess.NOM_FICHIER, _locale, ResourcesAccess.IP, name());
    }
}
