package code.bean.help;

import code.bean.nat.FixCharacterCaseConverter;
import code.formathtml.render.MetaDocument;
import code.sml.*;
import code.bean.nat.analyze.blocks.AnaRendBlockHelp;
import code.sml.Document;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsFile;
import code.util.*;
import code.util.core.StringUtil;

public final class HelpCaller {
    private final StringMap<String> properties;

    private HelpCaller(StringMap<String> _props){
        properties = _props;
    }

    public static MetaDocument text(Document _uniq, TranslationsAppli _ms, StringMap<int[][]> _imgs, StringMap<String> _props) {
        HelpCaller ins_ = new HelpCaller(_props);
        StringMap<TranslationsFile> files_ = files(_ms, _props);
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
            write_ = complete(write_, child_, ins_.proc(current_, dest_, files_, rend_));
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
        return MetaDocument.newInstance(dest_, rend_,"",new FixCharacterCaseConverter(),new HelpMetaSimpleImageBuilder(_imgs));
    }
    private Node proc(Node _current, Document _doc, StringMap<TranslationsFile> _files, RendKeyWordsGroup _rend) {
        if (_current instanceof Element) {
            String tagName_ = ((Element)_current).getTagName();
            if (StringUtil.quickEq(tagName_, _rend.getKeyWordsTags().getKeyWordMessage())) {
                String value_ = ((Element)_current).getAttribute(_rend.getKeyWordsAttrs().getAttrValue());
                return _doc.createTextNode(getPre(value_, properties, _files));
            }
            Element elt_ = _doc.createElement(tagName_);
            for (Attr a: _current.getAttributes()) {
                elt_.setAttribute(a.getName(),a.getValue());
            }
            return elt_;
        }
        return _doc.createTextNode(_current.getTextContent());
    }
    public static StringMap<TranslationsFile> files(TranslationsAppli _otherMessage, StringMap<String> _props){
        StringMap<TranslationsFile> files_ = new StringMap<TranslationsFile>();
        for (String a : _props.values()) {
            tryPut(files_,a,_otherMessage.getMapping().getVal(a));
        }
        return files_;
    }

    private static void tryPut(StringMap<TranslationsFile> _files, String _key, TranslationsFile _val) {
        _files.put(_key, _val);
    }
    public static String getPre(String _value, StringMap<String> _props, StringMap<TranslationsFile> _files) {
        StringList elts_ = StringUtil.splitStrings(_value, AnaRendBlockHelp.COMMA);
        String var_ = elts_.first();
        String fileName_ = _props.getVal(var_);
        TranslationsFile content_ = _files.getVal(fileName_);
        StringMap<String> messages_ = content_.getMapping();
        String key_ = elts_.last();
        return StringUtil.nullToEmpty(messages_.getVal(key_));
    }

    private static Element complete(Element _blockToWrite, Node _n, Node _toWrite) {
        _blockToWrite.appendChild(_toWrite);
        if (_toWrite instanceof Element && _n != null) {
            return (Element) _toWrite;
        }
        return _blockToWrite;
    }
}
