package code.serialize.exceptions;
import code.resources.ResourceFiles;
import code.serialize.SerializeXmlObject;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;
import code.util.opers.MessagesUtil;

final class InitializerMessages {
    private static final String SEPARATEUR = "/";
    private static final String PROPERTIES_PATTERN = "{0}/{1}/{2}.properties";
    private static final String DOT = ".";

    private InitializerMessages() {
    }

    static StringMap<StringMap<String>> getMessages(String _class) {
        StringMap<StringMap<String>> allMessages_ = new StringMap<StringMap<String>>();
        for (String l: Constants.getAvailableLanguages()) {
            String path_ = getPropertiesPath(SerializeXmlObject.RESOURCES_FOLDER, l, _class);
            try {
                String content_ = ResourceFiles.ressourceFichier(path_);
                StringMap<String> messages_ = MessagesUtil.getMessages(content_);
                allMessages_.put(l, messages_);
            } catch (RuntimeException _0) {
            }
        }
        return allMessages_;
    }

    public static String getPropertiesPath(String _folder, String _language, String _file) {
        return StringList.simpleFormat(PROPERTIES_PATTERN, _folder, _language, StringList.replace(_file, DOT, SEPARATEUR).toLowerCase());
    }

}
