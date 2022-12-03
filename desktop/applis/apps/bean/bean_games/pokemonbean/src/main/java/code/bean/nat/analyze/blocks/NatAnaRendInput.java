package code.bean.nat.analyze.blocks;

import code.sml.NatAnalyzingDoc;
import code.bean.nat.analyze.NatResultInput;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.sml.Element;
import code.util.StringList;
import code.util.core.StringUtil;

public final class NatAnaRendInput extends NatAnaRendElementSpec {
    private NatOperationNode rootRead;
    private NatOperationNode rootValue;
    private String className = AnaRendBlockHelp.EMPTY_STRING;
    private NatResultInput resultInput;
    private final boolean radio;

    public NatAnaRendInput(Element _elt, boolean _radio) {
        super(_elt);
        radio = _radio;
    }

    void input(Element _read, StringList _list, NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        processAnaInput(_read, _anaDoc, _page);
        removeAttrs(_list, _anaDoc);
    }

    private static void removeAttrs(StringList _ls, NatAnalyzingDoc _anaDoc) {
        _ls.removeAllString(_anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrChecked());
        _ls.removeAllString(_anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrValue());
        _ls.removeAllString(_anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrName());
        _ls.removeAllString(StringUtil.concat(_anaDoc.getPrefix(), _anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrClassName()));
        _ls.removeAllString(_anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrNi());
        _ls.removeAllString(StringUtil.concat(_anaDoc.getPrefix(), _anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrConvertValue()));
        _ls.removeAllString(StringUtil.concat(_anaDoc.getPrefix(), _anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrConvertFieldValue()));
        _ls.removeAllString(StringUtil.concat(_anaDoc.getPrefix(), _anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrConvertField()));
        _ls.removeAllString(StringUtil.concat(_anaDoc.getPrefix(), _anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrVarValue()));
        _ls.removeAllString(StringUtil.concat(_anaDoc.getPrefix(), _anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrValidator()));
        _ls.removeAllString(_anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrType());
    }

    void processAnaInput(Element _read, NatAnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        NatResultInput r_ = new NatResultInput(_read, StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getKeyWordsAttrs().getAttrVarValue()), _anaDoc, _page);
        resultInput = r_;
        rootRead = r_.getOpsReadRoot();
        rootValue = r_.getOpsValueRoot();
        className = r_.getClassNameNat();
    }

    public NatOperationNode getRootRead() {
        return rootRead;
    }

    public NatOperationNode getRootValue() {
        return rootValue;
    }

    public String getClassName() {
        return className;
    }

    public NatResultInput getResultInput() {
        return resultInput;
    }

    public boolean isRadio() {
        return radio;
    }

}
