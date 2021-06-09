package code.formathtml.analyze;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.opers.AnaSettableOperationContent;
import code.formathtml.analyze.blocks.AnaRendBlock;
import code.formathtml.util.InputInfo;
import code.sml.Element;
import code.util.BooleanList;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ResultInput {

    private static final String EMPTY_STRING = "";

    private OperationNode opsReadRoot;
    private OperationNode opsValueRoot;
    private String varName = EMPTY_STRING;
    private AnaClassArgumentMatching result;
    private AnaClassArgumentMatching previousResult;
    private StringList varNames = new StringList();
    private InputInfo varNamesParams = new InputInfo();
    private String id = EMPTY_STRING;
    private String idClass = EMPTY_STRING;
    private String idName = EMPTY_STRING;
    private String className = EMPTY_STRING;
    private OperationNode settable;

    public void build(AnaRendBlock _bl, Element _read, String _varValue, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        String name_ = _read.getAttribute(_anaDoc.getRendKeyWords().getAttrName());
        if (!name_.isEmpty()) {
            _anaDoc.getInputBuilder().tryBuildInputResult(name_, this,_bl, _anaDoc, _page);
        } else {
            String type_ = _read.getAttribute(_anaDoc.getRendKeyWords().getAttrType());
            if (!StringUtil.quickEq(type_,_anaDoc.getRendKeyWords().getValueSubmit())) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_anaDoc.getFileName());
                badEl_.setIndexFile(_bl.getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrName()));
                badEl_.buildError(_anaDoc.getRendAnalysisMessages().getBadInputName());
                AnalyzingDoc.addError(badEl_, _anaDoc, _page);
            }
        }
        if (_read.hasAttribute(_varValue)) {
            String value_ = _read.getAttribute(_varValue);
            opsValueRoot = RenderAnalysis.getRootAnalyzedOperations(value_, 0, _anaDoc, _page);
        }
    }

    public void tryBuildInputResult(String _name, AnaRendBlock _bl, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        setOpsReadRoot(RenderAnalysis.getRootAnalyzedOperations(_name, 0, _anaDoc, _page));
        OperationNode res_;
        if (opsReadRoot instanceof IdOperation) {
            res_ = AffectationOperation.getFirstToBeAnalyzed((MethodOperation) opsReadRoot);
        } else {
            res_ = opsReadRoot;
        }
        SettableElResult settable_ = AffectationOperation.castDottedTo(res_);
        if (settable_ != null) {
            setClassName(NumParsers.getSingleNameOrEmpty(settable_.getResultClass().getNames()));
        }
        if (settable_ instanceof SettableAbstractFieldOperation) {
            setSettable((OperationNode) settable_);
            AnaSettableOperationContent settableFieldContent_ = ((SettableAbstractFieldOperation) settable_).getSettableFieldContent();
            ClassField clField_ = settableFieldContent_.getClassField();
            if (clField_ != null) {
                if (settableFieldContent_.isStaticField()) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_anaDoc.getFileName());
                    badEl_.setIndexFile(_bl.getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrName()));
                    badEl_.buildError(_anaDoc.getRendAnalysisMessages().getStaticInputName(),
                            clField_.getFieldName());
                    AnalyzingDoc.addError(badEl_, _anaDoc, _page);
                }
                if (settableFieldContent_.isFinalField()) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_anaDoc.getFileName());
                    badEl_.setIndexFile(_bl.getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrName()));
                    badEl_.buildError(_page.getAnalysisMessages().getFinalField(),
                            clField_.getFieldName());
                    AnalyzingDoc.addError(badEl_, _anaDoc, _page);
                }
                idClass = clField_.getClassName();
                idName = clField_.getFieldName();
            }
            id = StringUtil.concat(idClass,".",idName);
            setResult(settable_.getResultClass());
            InputInfo info_ = new InputInfo();
            info_.getVarTypes().add(NumParsers.getSingleNameOrEmpty(result.getNames()));
            AnaClassArgumentMatching pr_;
            if (((SettableAbstractFieldOperation) settable_).isIntermediateDottedOperation()) {
                pr_ = ((SettableAbstractFieldOperation) settable_).getPreviousResultClass();
            } else {
                pr_ = new AnaClassArgumentMatching(_page.getGlobalClass());
            }
            setPreviousResult(pr_);
            StringList varNames_ = new StringList();
            String varPrevLoc_ = AnaRendBlock.lookForVar(varNames_, _page);
            varNames_.add(varPrevLoc_);
            String varLoc_ = AnaRendBlock.lookForVar(varNames_, _page);
            varNames_.add(varLoc_);
            setVarNames(varNames_);
            setVarNamesParams(info_);
            setVarName(StringUtil.concat(varPrevLoc_,AnaRendBlock.COMMA,varLoc_));
        } else if (settable_ instanceof ArrOperation) {
            setSettable((OperationNode) settable_);
            result = settable_.getResultClass();
            ClassMethodId classMethodId_ = ((ArrOperation) settable_).getCallFctContent().getClassMethodId();
            AnaClassArgumentMatching pr_ = ((ArrOperation) settable_).getPreviousResultClass();
            setPreviousResult(pr_);
            if (classMethodId_ == null) {
                CustList<OperationNode> childrenNodes_ = ((ArrOperation) settable_).getChildrenNodes();
                StringList varNames_ = new StringList();
                String varPrevLoc_ = AnaRendBlock.lookForVar(varNames_, _page);
                varNames_.add(varPrevLoc_);
                idClass = NumParsers.getSingleNameOrEmpty(pr_.getNames());
                StringList varParamNames_ = new StringList();
                StringList typeNames_ = new StringList();
                InputInfo info_ = new InputInfo();
                info_.getVarTypes().add(NumParsers.getSingleNameOrEmpty(result.getNames()));
                int s_ = childrenNodes_.size();
                for (int i = 0; i < s_; i++) {
                    String varParam_ = AnaRendBlock.lookForVar(varNames_, _page);
                    varNames_.add(varParam_);
                    varParamNames_.add(varParam_);
                    typeNames_.add(_page.getAliasPrimInteger());
                    info_.getVarTypes().add(NumParsers.getSingleNameOrEmpty(childrenNodes_.get(i).getResultClass().getNames()));
                }
                idName = StringUtil.concat("[](", StringUtil.join(typeNames_,","),")");
                id = StringUtil.concat(idClass,".",idName);
                String varLoc_ = AnaRendBlock.lookForVar(varNames_, _page);
                varNames_.add(varLoc_);
                setVarNames(varNames_);
                info_.getVarNames().addAllElts(varParamNames_);
                setVarNamesParams(info_);
                setVarName(StringUtil.concat(varPrevLoc_,AnaRendBlock.COMMA, StringUtil.join(varParamNames_,AnaRendBlock.COMMA),AnaRendBlock.COMMA,varLoc_));
            } else {
                StringList varNames_ = new StringList();
                String varPrevLoc_ = AnaRendBlock.lookForVar(varNames_, _page);
                varNames_.add(varPrevLoc_);
                idClass = NumParsers.getSingleNameOrEmpty(pr_.getNames());
                StringList varParamNames_ = new StringList();
                MethodId constraints_ = classMethodId_.getConstraints();
                int nbParam_ = constraints_.getParametersTypesLength();
                InputInfo info_ = new InputInfo();
                info_.getVarTypes().add(NumParsers.getSingleNameOrEmpty(result.getNames()));
                for (int i = 0; i < nbParam_; i++) {
                    String varParam_ = AnaRendBlock.lookForVar(varNames_, _page);
                    varNames_.add(varParam_);
                    varParamNames_.add(varParam_);
                    info_.getVarTypes().add(constraints_.getParametersType(i));
                    info_.getRefs().add(constraints_.getParametersRef(i));
                }
                String sgn_ = constraints_.getSignature(_page);
                idName = StringUtil.concat("[]", sgn_);
                id = StringUtil.concat(idClass,".",idName);
                String varLoc_ = AnaRendBlock.lookForVar(varNames_, _page);
                varNames_.add(varLoc_);
                setVarNames(varNames_);
                info_.getVarNames().addAllElts(varParamNames_);
                setVarNamesParams(info_);
                setVarName(StringUtil.concat(varPrevLoc_,AnaRendBlock.COMMA, StringUtil.join(varParamNames_,AnaRendBlock.COMMA),AnaRendBlock.COMMA,varLoc_));
            }
        } else {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFileName(_anaDoc.getFileName());
            badEl_.setIndexFile(_bl.getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrName()));
            badEl_.buildError(_anaDoc.getRendAnalysisMessages().getBadInputName());
            AnalyzingDoc.addError(badEl_, _anaDoc, _page);
        }
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String _varName) {
        this.varName = _varName;
    }

    public String getId() {
        return id;
    }

    public String getIdClass() {
        return idClass;
    }

    public String getIdName() {
        return idName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String _className) {
        className = _className;
    }

    public OperationNode getOpsReadRoot() {
        return opsReadRoot;
    }

    public void setOpsReadRoot(OperationNode _opsReadRoot) {
        opsReadRoot = _opsReadRoot;
    }

    public OperationNode getOpsValueRoot() {
        return opsValueRoot;
    }

    public OperationNode getSettable() {
        return settable;
    }

    public void setSettable(OperationNode _settable) {
        settable = _settable;
    }

    public AnaClassArgumentMatching getResult() {
        return result;
    }

    public void setResult(AnaClassArgumentMatching _result) {
        this.result = _result;
    }

    public AnaClassArgumentMatching getPreviousResult() {
        return previousResult;
    }

    public void setPreviousResult(AnaClassArgumentMatching _previousResult) {
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
