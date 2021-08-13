package code.bean.help.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.analyze.blocks.AnaRendParentBlock;
import code.sml.Element;
import code.sml.util.ResourcesMessagesUtil;
import code.util.*;
import code.util.core.StringUtil;

public final class HelpAnaRendMessage extends AnaRendParentBlock {

    private final Element elt;

    private StringMap<String> preformatted;
    private StringList varNames = new StringList();

    HelpAnaRendMessage(Element _elt, int _offset) {
        super(_offset);
        elt = _elt;
    }

    static StringMap<String> getPre(String _value, AnalyzingDoc _analyzingDoc) {
        StringList elts_ = StringUtil.splitStrings(_value, COMMA);
        String var_ = elts_.first();
        String fileName_ = _analyzingDoc.getProperties().getVal(var_);
        StringMap<String> pres_ = new StringMap<String>();
        for (String l: _analyzingDoc.getLanguages()) {
            StringMap<String> files_ = _analyzingDoc.getFiles();
            String content_ = tryGetContent(l, fileName_, files_, _analyzingDoc);
            StringMap<String> messages_ = getMessages(content_);
            String key_ = elts_.last();
            String format_ = getQuickFormat(messages_, key_);
            pres_.addEntry(l,format_);
        }
        return pres_;
    }

    public static String tryGetContent(String _loc, String _relative, StringMap<String> _files, AnalyzingDoc _anaDoc) {
        String folder_ = _anaDoc.getMessagesFolder();
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(folder_,_loc,_relative);
        return getContentFile(_files, fileName_);
    }

    private static String getContentFile(StringMap<String> _files, String _fileName) {
        return _files.getVal(_fileName);
    }

    public static String getQuickFormat(StringMap<String> _messages, String _key) {
        return _messages.getVal(_key);
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        String value_ = elt.getAttribute(_anaDoc.getRendKeyWords().getAttrValue());
        preformatted = getPre(value_, _anaDoc);
//        for (Element n: elt.getChildElements()) {
//            String attribute_ = n.getAttribute(_anaDoc.getRendKeyWords().getAttrValue());
//            args.add(AnaRendBlockHelp.EMPTY_STRING);
//            roots.add(NatRenderAnalysis.getRootAnalyzedOperations(attribute_, 0, _anaDoc, _page));
//        }
        //if (!element_.getAttribute(ATTRIBUTE_ESCAPED).isEmpty()) {
        varNames = new StringList();

    }

    public StringList getVarNames() {
        return varNames;
    }

    public StringMap<String> getPreformatted() {
        return preformatted;
    }

}
