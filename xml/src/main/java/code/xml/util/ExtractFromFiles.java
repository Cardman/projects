package code.xml.util;
import code.util.StringMap;

public final class ExtractFromFiles {
    private ExtractFromFiles() {
    }
    public static StringMap<String> getMessagesFromLocaleClass(String _folder, String _loc, String _class) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(_folder, _loc, _class);
        return ResourcesMessagesUtil.getMessagesFromLocale(fileName_, _loc);
    }

}
