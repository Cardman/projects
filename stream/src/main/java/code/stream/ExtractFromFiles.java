package code.stream;
import code.util.StringMap;

public final class ExtractFromFiles {
    private ExtractFromFiles() {
    }
    
    public static StringMap<String> getMessagesFromLocaleClass(String _folder, String _loc, String _class) {
        String fileName_ = StreamTextFile.getPropertiesPath(_folder, _loc, _class);
        return StreamTextFile.getMessagesFromLocale(fileName_, _loc);
    }

}
