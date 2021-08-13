package code.bean.nat.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.bean.nat.analyze.NatResultInput;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.util.InputInfo;
import code.sml.Element;
import code.util.core.StringUtil;

public abstract class NatAnaRendInput extends NatAnaRendElement {
    private NatOperationNode rootRead;
    private NatOperationNode rootValue;
    private String varName = AnaRendBlockHelp.EMPTY_STRING;
    private InputInfo varNames = new InputInfo();
    private String id = AnaRendBlockHelp.EMPTY_STRING;
    private String idClass = AnaRendBlockHelp.EMPTY_STRING;
    private String idName = AnaRendBlockHelp.EMPTY_STRING;
    private String className = AnaRendBlockHelp.EMPTY_STRING;
    private NatResultInput resultInput;
    NatAnaRendInput(Element _elt, int _offset) {
        super(_elt, _offset);
    }

    protected void processAnaInput(Element _read, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        NatResultInput r_ = new NatResultInput();
        r_.build(_read, StringUtil.concat(_anaDoc.getPrefix(),_anaDoc.getRendKeyWords().getAttrVarValue()), _anaDoc, _page);
        varNames = r_.getVarNamesParams();
        resultInput = r_;
        rootRead = r_.getOpsReadRoot();
        rootValue = r_.getOpsValueRoot();
        varName = r_.getVarName();
        id = r_.getId();
        idClass = r_.getIdClass();
        idName = r_.getIdName();
        className = r_.getClassName();
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

    public String getId() {
        return id;
    }

    public String getVarName() {
        return varName;
    }

    public NatResultInput getResultInput() {
        return resultInput;
    }

    public InputInfo getVarNames() {
        return varNames;
    }
}
