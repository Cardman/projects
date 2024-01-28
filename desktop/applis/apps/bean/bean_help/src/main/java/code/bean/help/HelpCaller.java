package code.bean.help;

import code.bean.nat.FixCharacterCaseConverter;
import code.formathtml.render.MetaDocument;
import code.sml.*;
import code.bean.nat.analyze.blocks.AnaRendBlockHelp;
import code.sml.Document;
import code.util.*;
import code.util.core.StringUtil;

public final class HelpCaller {
    private static final String PROPERTIES_PATTERN = "{0}/{1}/{2}";
    private final StringMap<String> properties;
    private final String messageFolder;
    private HelpCaller(StringMap<String> _props, String _messFolder){
        properties = _props;
        messageFolder = _messFolder;
    }

    public static MetaDocument text(Document _uniq, StringMap<String> _ms, String _language, StringMap<int[][]> _imgs, StringMap<String> _props, String _messFolder, String _pref) {
        HelpCaller ins_ = new HelpCaller(_props,_messFolder);
        StringMap<String> files_ = files(_ms,_language, _props, _messFolder);
//        for (String a : _contextConf.getAddedFiles()) {
//            files_.put(a, _ms.getVal(a));
//        }
//        for (String l : _navigation.getLanguages()) {
//            for (String a : _contextConf.getProperties().values()) {
//                String folder_ = _contextConf.getMessagesFolder();
//                String fileName_ = ResourcesMessagesUtil.getPropertiesPath(folder_, l, a);
//                files_.put(fileName_, _ms.getVal(fileName_));
//            }
//        }
        RendKeyWordsGroup rend_ = new RendKeyWordsGroup();
        Node root_ = _uniq.getDocumentElement().getFirstChild();
        Document dest_ = DocumentBuilder.newXmlDocument();
        dest_.appendChild(dest_.createElement(_uniq.getDocumentElement().getTagName()));
        Element write_ = dest_.getDocumentElement();
        Node current_ = root_;
        while (current_ != null) {
            Node child_ = current_.getFirstChild();
            write_ = complete(write_, child_, ins_.proc(current_, _pref, _language, dest_, files_, rend_));
            if (child_ != null) {
                current_ = child_;
                continue;
            }
            while (current_ != null) {
                Node next_ = current_.getNextSibling();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                Element par_ = current_.getParentNode();
                write_ = write_.getParentNode();
                if (par_ == root_) {
                    current_ = null;
                } else {
                    current_ = par_;
                }
            }
        }
        return MetaDocument.newInstance(dest_, rend_,"ABCDEF",new FixCharacterCaseConverter(),new HelpMetaSimpleImageBuilder(_imgs));
    }
    private Node proc(Node _current, String _prefix, String _lg, Document _doc, StringMap<String> _files, RendKeyWordsGroup _rend) {
        if (_current instanceof Element) {
            String tagName_ = ((Element)_current).getTagName();
            if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rend.getKeyWordsTags().getKeyWordMessage()))) {
                StringList objects_ = new StringList();
                String value_ = ((Element)_current).getAttribute(_rend.getKeyWordsAttrs().getAttrValue());
                String preRend_ = StringUtil.simpleStringsFormat(getPre(value_, _lg, properties, _files, messageFolder), objects_);
                return _doc.createTextNode(preRend_);
            }
            Element elt_ = _doc.createElement(tagName_);
            for (Attr a: _current.getAttributes()) {
                elt_.setAttribute(a.getName(),a.getValue());
            }
            return elt_;
        }
        return _doc.createTextNode(StringUtil.simpleStringsFormat(_current.getTextContent(),new StringList()));
    }
    public static StringMap<String> files(StringMap<String> _otherMessage, String _language, StringMap<String> _props, String _messFolder){
        StringMap<String> files_ = new StringMap<String>();
        for (String a : _props.values()) {
            String fileName_ = getPropertiesPath(_messFolder, _language, a);
            tryPut(files_,fileName_,_otherMessage.getVal(fileName_));
        }
        return files_;
    }

    private static void tryPut(StringMap<String> _files, String _key, String _val) {
        _files.put(_key, StringUtil.nullToEmpty(_val));
    }
    public static String getPre(String _value, String _lg, StringMap<String> _props, StringMap<String> _files, String _mess) {
        StringList elts_ = StringUtil.splitStrings(_value, AnaRendBlockHelp.COMMA);
        String var_ = elts_.first();
        String fileName_ = _props.getVal(var_);
        String content_ = _files.getVal(getPropertiesPath(_mess, _lg, fileName_));
        StringMap<String> messages_ = NavigationCore.getMessages(content_);
        String key_ = elts_.last();
        return StringUtil.nullToEmpty(messages_.getVal(key_));
    }
    public static String getPropertiesPath(String _folder, String _language, String _file) {
        return StringUtil.simpleStringsFormat(PROPERTIES_PATTERN, _folder, _language, _file);
    }
    private static Element complete(Element _blockToWrite, Node _n, Node _toWrite) {
        _blockToWrite.appendChild(_toWrite);
        if (_toWrite instanceof Element && _n != null) {
            return (Element) _toWrite;
        }
        return _blockToWrite;
    }
}
