package code.bean.nat.analyze.blocks;

import code.bean.nat.NatCaller;
import code.bean.nat.analyze.NatResultInput;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.util.InputInfo;
import code.sml.Element;
import code.util.StringList;
import code.util.core.StringUtil;

public final class NatAnaRendInput extends NatAnaRendElement {
    private NatOperationNode rootRead;
    private NatOperationNode rootValue;
    private String varName = AnaRendBlockHelp.EMPTY_STRING;
    private InputInfo varNames = new InputInfo();
    private String idClass = AnaRendBlockHelp.EMPTY_STRING;
    private String idName = AnaRendBlockHelp.EMPTY_STRING;
    private String className = AnaRendBlockHelp.EMPTY_STRING;
    private NatResultInput resultInput;
    private final boolean radio;
    private NatCaller callerGet;

    NatAnaRendInput(Element _elt, int _offset, boolean _radio) {
        super(_elt, _offset);
        radio = _radio;
    }

    @Override
    protected void processAttributes(AnaRendDocumentBlock _doc, Element _read, StringList _list, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        processAnaInput(_read, _anaDoc, _page);
        removeAttrs(_list, _anaDoc);
    }

    private static void removeAttrs(StringList _ls, AnalyzingDoc _anaDoc) {
        _ls.removeAllString(_anaDoc.getRendKeyWords().getAttrChecked());
        _ls.removeAllString(_anaDoc.getRendKeyWords().getAttrValue());
        _ls.removeAllString(_anaDoc.getRendKeyWords().getAttrName());
        _ls.removeAllString(StringUtil.concat(_anaDoc.getPrefix(), _anaDoc.getRendKeyWords().getAttrClassName()));
        _ls.removeAllString(_anaDoc.getRendKeyWords().getAttrNi());
        _ls.removeAllString(StringUtil.concat(_anaDoc.getPrefix(), _anaDoc.getRendKeyWords().getAttrConvertValue()));
        _ls.removeAllString(StringUtil.concat(_anaDoc.getPrefix(), _anaDoc.getRendKeyWords().getAttrConvertFieldValue()));
        _ls.removeAllString(StringUtil.concat(_anaDoc.getPrefix(), _anaDoc.getRendKeyWords().getAttrConvertField()));
        _ls.removeAllString(StringUtil.concat(_anaDoc.getPrefix(), _anaDoc.getRendKeyWords().getAttrVarValue()));
        _ls.removeAllString(StringUtil.concat(_anaDoc.getPrefix(), _anaDoc.getRendKeyWords().getAttrValidator()));
        _ls.removeAllString(_anaDoc.getRendKeyWords().getAttrType());
    }

    void processAnaInput(Element _read, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        NatResultInput r_ = new NatResultInput();
        r_.build(_read, StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrVarValue()), _anaDoc, _page);
        varNames = r_.getVarNamesParams();
        resultInput = r_;
        rootRead = r_.getOpsReadRoot();
        rootValue = r_.getOpsValueRoot();
        varName = r_.getVarName();
        callerGet = r_.getCallerGet();
        idClass = r_.getIdClass();
        idName = r_.getIdName();
        className = r_.getClassNameNat();
    }

    public NatCaller getCallerGet() {
        return callerGet;
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

    public String getIdClass() {
        return idClass;
    }

    public String getIdName() {
        return idName;
    }

    public String getVarName() {
        return varName;
    }

    public NatResultInput getResultInput() {
        return resultInput;
    }

    public boolean isRadio() {
        return radio;
    }

    public InputInfo getVarNames() {
        return varNames;
    }
}
