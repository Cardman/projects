package code.sml.stream;
import code.resources.ResourceFiles;
import code.sml.DocumentBuilder;
import code.sml.util.ResourcesMessagesUtil;
import code.util.StringMap;

public final class ExtractFromFiles {
    private ExtractFromFiles() {
    }
    public static StringMap<String> getMessagesFromLocaleClass(String _folder, String _loc, String _class) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(_folder, _loc, _class);
        return getMessagesFromLocale(fileName_);
    }

    public static StringMap<String> getMessagesFromLocale(String _fileName) {
        String loadedResourcesMessages_ = ResourceFiles.ressourceFichier(_fileName);
        return DocumentBuilder.getMessagesFromContent(loadedResourcesMessages_);
    }

}
