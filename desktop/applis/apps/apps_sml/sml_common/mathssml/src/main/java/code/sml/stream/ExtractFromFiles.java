package code.sml.stream;
import code.resources.ResourceFiles;
import code.sml.util.ResourcesMessagesUtil;
import code.util.StringMap;

public final class ExtractFromFiles {
    private ExtractFromFiles() {
    }
    public static StringMap<String> getMessagesFromLocaleClass(String _folder, String _loc, String _class) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(_folder, _loc, _class);
        String loadedResourcesMessages_ = ResourceFiles.ressourceFichier(fileName_);
        return ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
    }

}
