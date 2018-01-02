package code.network.enums;
import java.net.Inet4Address;
import java.net.Inet6Address;

import code.format.Format;
import code.util.consts.Constants;
import code.util.ints.Displayable;

public enum IpType implements Displayable {
    HOST_NAME,IP_V4(Inet4Address.class),IP_V6(Inet6Address.class);
    private final Class<?> classIp;

    private IpType(){
        classIp = null;
    }
    private IpType(Class<?> _classIp) {
        classIp = _classIp;
    }
    public Class<?> getClassIp() {
        return classIp;
    }
    @Override
    public String display() {
        return toString(Constants.getLanguage());
    }

    public String toString(String _locale) {
        return Format.getConstanteLangue(ResourcesAccess.NOM_DOSSIER,ResourcesAccess.NOM_FICHIER, _locale, ResourcesAccess.IP, name());
    }
}
