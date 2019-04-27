package code.sml.util;

import code.util.StringList;

public final class ResourcesMessagesUtil {

    private static final String SEPARATEUR = "/";
    private static final String PROPERTIES_PATTERN = "{0}/{1}/{2}.properties";
    private static final String DOT = ".";

    private ResourcesMessagesUtil() {
    }
    public static String getPropertiesPath(String _folder, String _language, String _file) {
        String name_ = StringList.replace(_file, DOT, SEPARATEUR);
        return StringList.simpleStringsFormat(PROPERTIES_PATTERN, _folder, _language, name_);
    }

}
