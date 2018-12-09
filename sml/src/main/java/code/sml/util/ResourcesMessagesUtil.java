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

    private ResourcesMessagesUtil() {
    }
    public static String getPropertiesPath(String _folder, String _language, String _file) {
        String name_ = StringList.replace(_file, DOT, SEPARATEUR);
        return StringList.simpleStringsFormat(PROPERTIES_PATTERN, _folder, _language, name_);
    }

    public static StringMap<String> getMessagesFromLocale(String _fileName, String _loc) {
        String loadedResourcesMessages_ = ResourceFiles.ressourceFichier(_fileName);
        if (loadedResourcesMessages_.isEmpty()) {
            return new StringMap<String>();
        }
        StringMap<String> messages_ = MessagesUtil.getMessages(loadedResourcesMessages_);
        for (String k: messages_.getKeys()) {
            if (k.startsWith(TAB)) {
                continue;
            }
            String value_ = messages_.getVal(k);
            messages_.put(k, DocumentBuilder.transformSpecialChars(value_));
        }
        return messages_;
    }
}
