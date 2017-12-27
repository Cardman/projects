package code.sml.util;

import code.resources.ResourceFiles;
import code.sml.DocumentBuilder;
import code.util.StringList;
import code.util.StringMap;
import code.util.opers.MessagesUtil;

public final class ResourcesMessagesUtil {

    private static final String SEPARATEUR = "/";
    private static final String PROPERTIES_PATTERN = "{0}/{1}/{2}.properties";
    private static final String TAB = "\t";
    private static final String DOT = ".";
    private static final StringMap<StringMap<StringMap<String>>> LOCALES_MESSAGES = new StringMap<StringMap<StringMap<String>>>();

    private ResourcesMessagesUtil() {
    }
    public static String getPropertiesPath(String _folder, String _language, String _file) {
    	String name_ = StringList.toLowerCase(StringList.replace(_file, DOT, SEPARATEUR));
        return StringList.simpleStringsFormat(PROPERTIES_PATTERN, _folder, _language, name_);
    }

    public static StringMap<String> getMessagesFromLocale(String _fileName, String _loc) {
        if (!LOCALES_MESSAGES.contains(_loc)) {
            LOCALES_MESSAGES.put(_loc, new StringMap<StringMap<String>>());
        }
        StringMap<StringMap<String>> map_ = LOCALES_MESSAGES.getVal(_loc);
        if (!map_.contains(_fileName)) {
            String loadedResourcesMessages_ = ResourceFiles.ressourceFichier(_fileName);
            if (loadedResourcesMessages_.isEmpty()) {
                map_.put(_fileName, new StringMap<String>());
                return map_.getVal(_fileName);
            }
            StringMap<String> messages_ = MessagesUtil.getMessages(loadedResourcesMessages_);
            for (String k: messages_.getKeys()) {
                if (k.startsWith(TAB)) {
                    continue;
                }
                String value_ = messages_.getVal(k);
                messages_.put(k, DocumentBuilder.transformSpecialChars(value_));
            }
            map_.put(_fileName, messages_);
        }
        return map_.getVal(_fileName);
    }
}
