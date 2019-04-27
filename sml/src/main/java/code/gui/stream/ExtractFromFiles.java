package code.gui.stream;
import code.resources.ResourceFiles;
import code.sml.DocumentBuilder;
import code.sml.util.ResourcesMessagesUtil;
import code.util.StringMap;
import code.util.opers.MessagesUtil;

public final class ExtractFromFiles {
    private static final String TAB = "\t";
    private ExtractFromFiles() {
    }
    public static StringMap<String> getMessagesFromLocaleClass(String _folder, String _loc, String _class) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(_folder, _loc, _class);
        return getMessagesFromLocale(fileName_, _loc);
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
