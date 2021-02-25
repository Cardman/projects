package code.sml.util;

import code.sml.DocumentBuilder;
import code.util.StringMap;
import code.util.core.StringUtil;
import code.util.opers.MessagesUtil;

public final class ResourcesMessagesUtil {

    private static final String SEPARATEUR = "/";
    private static final String PROPERTIES_PATTERN = "{0}/{1}/{2}.properties";
    private static final String DOT = ".";

    private ResourcesMessagesUtil() {
    }
    public static String getPropertiesPath(String _folder, String _language, String _file) {
        String name_ = StringUtil.replace(_file, DOT, SEPARATEUR);
        return StringUtil.simpleStringsFormat(PROPERTIES_PATTERN, _folder, _language, name_);
    }

    public static StringMap<String> getMessagesFromContent(String _loadedResourcesMessages) {
        if (_loadedResourcesMessages.isEmpty()) {
            return new StringMap<String>();
        }
        StringMap<String> messages_ = MessagesUtil.getMessages(_loadedResourcesMessages);
        for (String k: messages_.getKeys()) {
            String value_ = messages_.getVal(k);
            messages_.put(k, DocumentBuilder.transformSpecialChars(value_));
        }
        return messages_;
    }
}
