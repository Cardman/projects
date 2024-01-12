package code.bean.help;

import code.bean.nat.FixCharacterCaseConverter;
import code.bean.nat.NatDualConfigurationContext;
import code.bean.nat.NatNavigation;
import code.formathtml.render.MetaDocument;
import code.sml.*;
import code.bean.nat.analyze.NatConfigurationCore;
import code.bean.nat.analyze.blocks.AnaRendBlockHelp;
import code.sml.Document;
import code.sml.util.*;
import code.util.*;
import code.util.core.StringUtil;

public final class HelpCaller {
    private HelpCaller(){

    }

    public static MetaDocument text(NatDualConfigurationContext _contextConf, NatNavigation _navigation, String _realFilePath, Document _uniq, StringMap<String> _ms, String _language, StringMap<int[][]> _imgs) {
        StringMap<String> files_ = NatDualConfigurationContext.files(_navigation,_contextConf,_ms,_ms,"");
        NatConfigurationCore session_ = _navigation.getSession();
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
        session_.setFirstUrl(_realFilePath);
        _navigation.setFiles(files_);
        NatConfigurationCore conf_ = _navigation.getSession();
        NatAnalyzingDoc analyzingDoc_ = new NatAnalyzingDoc();
        StringMap<String> beansInfos_ = new StringMap<String>();
        conf_.getBeansInfos().addAllEntries(beansInfos_);
        analyzingDoc_.setLanguages(_navigation.getLanguages());
        _navigation.getSession().setCurrentLanguage(_language);

        _navigation.getSession().setFiles(_navigation.getFiles());
        NatConfigurationCore c_ = _navigation.getSession();
        analyzingDoc_.setRendKeyWords(c_.getRendKeyWords());
        analyzingDoc_.setupCommon(c_.getNat(), _contextConf.getProperties(), _contextConf.getMessagesFolder());
        Node root_ = _uniq.getDocumentElement().getFirstChild();
        Document dest_ = DocumentBuilder.newXmlDocument();
        dest_.appendChild(dest_.createElement(_uniq.getDocumentElement().getTagName()));
        Element write_ = dest_.getDocumentElement();
        Node current_ = root_;
        while (current_ != null) {
            Node child_ = current_.getFirstChild();
            write_ = complete(write_, child_, proc(current_, c_.getNat().getPrefix(),analyzingDoc_,c_.getRendKeyWords(),_language, dest_));
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
        return MetaDocument.newInstance(dest_, _navigation.getSession().getRendKeyWords(),"ABCDEF",new FixCharacterCaseConverter(),new HelpMetaSimpleImageBuilder(_imgs));
    }
    private static Node proc(Node _current, String _prefix, NatAnalyzingDoc _anaDoc, RendKeyWordsGroup _rendKeyWords, String _lg, Document _doc) {
        if (_current instanceof Element) {
            String tagName_ = ((Element)_current).getTagName();
            if (StringUtil.quickEq(tagName_, StringUtil.concat(_prefix, _rendKeyWords.getKeyWordsTags().getKeyWordMessage()))) {
                StringList objects_ = new StringList();
                String value_ = ((Element)_current).getAttribute(_rendKeyWords.getKeyWordsAttrs().getAttrValue());
                String preRend_ = StringUtil.simpleStringsFormat(getPre(value_, _lg, _anaDoc.getProperties(), _anaDoc.getFiles(), _anaDoc.getMessagesFolder()), objects_);
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

    public static String getPre(String _value, String _lg, StringMap<String> _props, StringMap<String> _files, String _mess) {
        StringList elts_ = StringUtil.splitStrings(_value, AnaRendBlockHelp.COMMA);
        String var_ = elts_.first();
        String fileName_ = _props.getVal(var_);
        String content_ = _files.getVal(ResourcesMessagesUtil.getPropertiesPath(_mess, _lg, fileName_));
        StringMap<String> messages_ = NavigationCore.getMessages(content_);
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
