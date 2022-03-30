package code.bean.nat.analyze;

import code.bean.nat.analyze.blocks.AnaRendBlockHelp;
import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.bean.nat.analyze.opers.AffectationNatOperation;
import code.bean.nat.analyze.opers.NatOperationNode;
import code.bean.nat.analyze.opers.NatSettableElResult;
import code.bean.nat.analyze.opers.SettableAbstractFieldNatOperation;
import code.bean.nat.fwd.opers.NatAnaSettableOperationContent;
import code.expressionlanguage.common.ClassField;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendBlock;
import code.formathtml.util.InputInfo;
import code.sml.Element;
import code.util.StringList;
import code.util.core.StringUtil;

public final class NatResultInput {

    private static final String EMPTY_STRING = "";

    private NatOperationNode opsReadRoot;
    private NatOperationNode opsValueRoot;
    private String varName = EMPTY_STRING;
    private String result;
    private String previousResult;
    private StringList varNames = new StringList();
    private InputInfo varNamesParams = new InputInfo();
    private String id = EMPTY_STRING;
    private String idClass = EMPTY_STRING;
    private String idName = EMPTY_STRING;
    private String classNameNat = EMPTY_STRING;
    private NatOperationNode settable;

    public void build(Element _read, String _varValue, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        String name_ = _read.getAttribute(_anaDoc.getRendKeyWords().getAttrName());
        if (!name_.isEmpty()) {
            tryBuildInputResult(name_, _anaDoc, _page);
        }
        if (_read.hasAttribute(_varValue)) {
            String value_ = _read.getAttribute(_varValue);
            opsValueRoot = NatRenderAnalysis.getRootAnalyzedOperations(value_, 0, _anaDoc, _page);
        }
    }

    public void tryBuildInputResult(String _name, AnalyzingDoc _anaDoc, NatAnalyzedCode _page) {
        opsReadRoot = NatRenderAnalysis.getRootAnalyzedOperations(_name, 0, _anaDoc, _page);
        NatOperationNode res_ = opsReadRoot;
        NatSettableElResult settable_ = AffectationNatOperation.castDottedTo(res_);
        setClassNameNat(((NatOperationNode) settable_).getNames());
        setSettable((NatOperationNode) settable_);
        NatAnaSettableOperationContent settableFieldContent_ = ((SettableAbstractFieldNatOperation) settable_).getSettableFieldContent();
        ClassField clField_ = settableFieldContent_.getClassField();
        idClass = clField_.getClassName();
        idName = clField_.getFieldName();
        id = StringUtil.concat(idClass,".",idName);
        setResult(((NatOperationNode) settable_).getNames());
        InputInfo info_ = new InputInfo();
        info_.getVarTypes().add(result);
        String pr_;
        if (((SettableAbstractFieldNatOperation) settable_).isIntermediateDottedOperation()) {
            pr_ = ((SettableAbstractFieldNatOperation) settable_).getPreviousResultClass();
        } else {
            pr_ = _page.getGlobalClass();
        }
        setPreviousResult(pr_);
        StringList varNames_ = new StringList();
        String varPrevLoc_ = AnaRendBlockHelp.lookForVar(varNames_);
        varNames_.add(varPrevLoc_);
        String varLoc_ = AnaRendBlockHelp.lookForVar(varNames_);
        varNames_.add(varLoc_);
        setVarNames(varNames_);
        setVarNamesParams(info_);
        setVarName(StringUtil.concat(varPrevLoc_,AnaRendBlock.COMMA,varLoc_));
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String _varName) {
        this.varName = _varName;
    }

    public String getIdClass() {
        return idClass;
    }

    public String getIdName() {
        return idName;
    }

    public String getClassNameNat() {
        return classNameNat;
    }

    public void setClassNameNat(String _className) {
        classNameNat = _className;
    }

    public String getId() {
        return id;
    }

    public NatOperationNode getOpsReadRoot() {
        return opsReadRoot;
    }

    public NatOperationNode getOpsValueRoot() {
        return opsValueRoot;
    }

    public NatOperationNode getSettable() {
        return settable;
    }

    public void setSettable(NatOperationNode _settable) {
        settable = _settable;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String _result) {
        this.result = _result;
    }

    public String getPreviousResult() {
        return previousResult;
    }

    public void setPreviousResult(String _previousResult) {
        previousResult = _previousResult;
    }

    public InputInfo getVarNamesParams() {
        return varNamesParams;
    }

    public void setVarNamesParams(InputInfo _varNamesParams) {
        varNamesParams = _varNamesParams;
    }

    public StringList getVarNames() {
        return varNames;
    }

    public void setVarNames(StringList _varNames) {
        varNames = _varNames;
    }
}
