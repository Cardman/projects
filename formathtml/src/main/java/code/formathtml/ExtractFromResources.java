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
//        link_ = StringList.replace(link_, IMPLICIT_LANGUAGE, SEPARATOR_PATH+Constants.getLanguage()+SEPARATOR_PATH);
        String contents_ = getContentFile(_conf, _files, link_, _resourcesFolder);
        //        contents_ = contents_.replace(PREFIXED_BEGIN, UNPREFIXED_BEGIN);
        //        contents_ = contents_.replace(PREFIXED_END, UNPREFIXED_END);
//        return transformResourceUrl(contents_);
        return contents_;
    }

    static String getRealFilePath(String _link) {
        return StringList.replace(_link, IMPLICIT_LANGUAGE, SEPARATOR_PATH+Constants.getLanguage()+SEPARATOR_PATH);
    }

//    @Deprecated
//    static String formatMessage(Configuration _conf,String _loc, Bean _object, Element _element, Map<String,String> _files, String... _resourcesFolder) {
//        String value_ = _element.getAttribute(ATTRIBUTE_VALUE);
//        if (value_.startsWith(CALL_METHOD)) {
//            String preformatted_ = extractObject(value_.substring(1), _object).toString();
//            List<Object> objects_ = new List<Object>();
//            for (Element n: XmlParser.childrenElements(_element)) {
//                String attribute_ = n.getAttribute(ATTRIBUTE_VALUE);
//                if (attribute_.startsWith(CALL_METHOD)) {
//                    objects_.add(extractObject(attribute_.substring(1), _object));
//                } else {
//                    objects_.add(attribute_);
//                }
//            }
//            return StringList.simpleFormat(preformatted_, objects_.toArray());
//        }
//        StringList elts_ = StringList.splitStrings(value_, COMMA);
//        //        String var_ = value_.split(COMMA)[0];
//        String var_ = elts_.first();
//        String fileName_ = _conf.getProperties().getVal(var_);
//        if (fileName_ == null) {
//            fileName_ = var_;
//        }
//        Map<String,String> messages_ = getInnerMessagesFromLocaleClass(_conf.getMessagesFolder(), _loc, fileName_, _files, _resourcesFolder);
//        //        String preformatted_ = messages_.getVal(value_.split(COMMA)[1]);
//        String preformatted_ = messages_.getVal(elts_.last());
//        preformatted_ = XmlParser.transformSpecialChars(preformatted_);
//        List<Object> objects_ = new List<Object>();
//        for (Element n: XmlParser.childrenElements(_element)) {
//            String attribute_ = n.getAttribute(ATTRIBUTE_VALUE);
//            if (attribute_.startsWith(CALL_METHOD)) {
//                objects_.add(extractObject(attribute_.substring(1), _object));
//            } else {
//                objects_.add(attribute_);
//            }
//        }
//        return StringList.simpleFormat(preformatted_, objects_.toArray());
//    }
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
    //    public static Map<String,String> getInnerMessagesFromLocaleClass(String _folder, String _loc, Class<?> _class, Map<String, String> _files) {
    //        String fileName_ = StreamTextFile.getPropertiesPath(_folder, _loc, _class.getName());
    //        String content_ = _files.getVal(fileName_);
    //        return StreamTextFile.getMessages(content_);
    //    }
    //    public static Map<String,String> getMessagesFromLocaleClass(String _folder, String _loc, String _relative) {
    //        String fileName_ = StreamTextFile.getPropertiesPath(_folder,_loc,_relative);
    //        return StreamTextFile.getMessagesFromLocale(fileName_, _loc);
    //    }

    static String getContentFile(Configuration _conf, StringMap<String> _files, String _fileName, String... _resourcesFolder) {
        //        String content_ = _files.getVal(_fileName);
        //        if (content_ != null) {
        //            return content_;
        //        }
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
