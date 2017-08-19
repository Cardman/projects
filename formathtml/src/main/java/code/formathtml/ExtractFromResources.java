package code.formathtml;
import code.formathtml.exceptions.BadFilePropertiesException;
import code.formathtml.exceptions.MessageKeyNotFoundException;
import code.formathtml.exceptions.NoSuchResourceException;
import code.resources.ResourceFiles;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;
import code.util.exceptions.BadFilePropertiesContentException;
import code.util.opers.MessagesUtil;
import code.xml.util.ResourcesMessagesUtil;

final class ExtractFromResources {
    static final String RETURN_LINE = "\n";
    private static final String SEPARATOR_PATH = "/";
    private static final String IMPLICIT_LANGUAGE = SEPARATOR_PATH+SEPARATOR_PATH;
    private ExtractFromResources() {
    }

    static String loadPage(Configuration _conf, StringMap<String> _files, String _link, String... _resourcesFolder) {
        String link_ = getRealFilePath(_link);
        String contents_ = getContentFile(_conf, _files, link_, _resourcesFolder);
        return contents_;
    }

    static String getRealFilePath(String _link) {
        return StringList.replace(_link, IMPLICIT_LANGUAGE, SEPARATOR_PATH+Constants.getLanguage()+SEPARATOR_PATH);
    }

    static String getFormat(StringMap<String> _messages, String _key, Configuration _conf, String _loc, String _fileName) {
        String fileNamePath_ = ResourcesMessagesUtil.getPropertiesPath(ExtractObject.getMessageFolder(_conf),_loc,_fileName);
        String value_ = _messages.getVal(_key);
        if (value_ == null) {
            //check if key_ is in messages_ from fileNamePath_
            throw new MessageKeyNotFoundException(_key, fileNamePath_, _conf.joinPages());
        }
        _conf.setResourceUrl(fileNamePath_);
        return value_;
    }
    static StringMap<String> getInnerMessagesFromLocaleClass(Configuration _conf, String _loc, String _relative, StringMap<String> _files, String... _resourcesFolder) {
        String folder_ = ExtractObject.getMessageFolder(_conf);
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(folder_,_loc,_relative);
        String content_ = getContentFile(_conf, _files, fileName_, _resourcesFolder);
        try {
            return MessagesUtil.getMessages(content_);
        } catch (BadFilePropertiesContentException _0) {
            throw new BadFilePropertiesException(fileName_+RETURN_LINE+_0.getLine()+RETURN_LINE+_conf.joinPages());
        }
    }

    static String getContentFile(Configuration _conf, StringMap<String> _files, String _fileName, String... _resourcesFolder) {
        String content_ = null;
        for (EntryCust<String, String> e: _files.entryList()) {
            if (e.getKey().equalsIgnoreCase(_fileName)) {
                content_ = e.getValue();
                break;
            }
        }
        if (content_ == null) {
            content_ = ResourceFiles.ressourceFichierUrls(_fileName, _resourcesFolder);
        }
        if (content_ == null) {
            if (_conf.noPages()) {
                throw new NoSuchResourceException(_fileName);
            }
            throw new NoSuchResourceException(_fileName, _conf.joinPages());
        }
        return content_;
    }

}
