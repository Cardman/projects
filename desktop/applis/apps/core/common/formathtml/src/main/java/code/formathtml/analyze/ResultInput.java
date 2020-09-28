package code.formathtml.analyze;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.analyze.opers.util.FieldInfo;
import code.expressionlanguage.functionid.ClassMethodId;
import code.formathtml.Configuration;
import code.formathtml.analyze.blocks.AnaRendBlock;
import code.formathtml.util.AnalyzingDoc;
import code.sml.Element;
import code.util.CustList;
import code.util.StringList;

public final class ResultInput {

    private static final String EMPTY_STRING = "";

    private OperationNode opsReadRoot;
    private OperationNode opsValueRoot;
    private String varName = EMPTY_STRING;
    private AnaClassArgumentMatching result;
    private AnaClassArgumentMatching previousResult;
    private StringList varNames = new StringList();
    private String id = EMPTY_STRING;
    private String idClass = EMPTY_STRING;
    private String idName = EMPTY_STRING;
    private String className = EMPTY_STRING;
    private SettableElResult settable;

    public void build(Configuration _cont, AnaRendBlock _bl, Element _read, String _varValue, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        String name_ = _read.getAttribute(_cont.getRendKeyWords().getAttrName());
        if (!name_.isEmpty()) {
            opsReadRoot = RenderAnalysis.getRootAnalyzedOperations(name_, 0, _anaDoc, _page);
            OperationNode res_;
            if (opsReadRoot instanceof IdOperation) {
                res_ = AffectationOperation.getFirstToBeAnalyzed((MethodOperation) opsReadRoot);
            } else {
                res_ = opsReadRoot;
            }
            SettableElResult settable_ = AffectationOperation.castDottedTo(res_);
            settable = settable_;
            if (settable_ != null) {
                className = NumParsers.getSingleNameOrEmpty(settable_.getResultClass().getNames());
            }
            if (settable_ instanceof SettableAbstractFieldOperation) {
                FieldInfo infoField_ = ((SettableAbstractFieldOperation) settable_).getFieldMetaInfo();
                if (infoField_ != null) {
                    ClassField clField_ = infoField_.getClassField();
                    if (infoField_.isStaticField()) {
                        FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                        badEl_.setFileName(_anaDoc.getFileName());
                        badEl_.setIndexFile(_bl.getAttributeDelimiter(_cont.getRendKeyWords().getAttrName()));
                        badEl_.buildError(_anaDoc.getRendAnalysisMessages().getStaticInputName(),
                                clField_.getFieldName());
                        Configuration.addError(badEl_, _anaDoc, _page);
                    }
                    if (infoField_.isFinalField()) {
                        FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                        badEl_.setFileName(_anaDoc.getFileName());
                        badEl_.setIndexFile(_bl.getAttributeDelimiter(_cont.getRendKeyWords().getAttrName()));
                        badEl_.buildError(_page.getAnalysisMessages().getFinalField(),
                                clField_.getFieldName());
                        Configuration.addError(badEl_, _anaDoc, _page);
                    }
                    idClass = clField_.getClassName();
                    idName = clField_.getFieldName();
                }
                id = StringList.concat(idClass,".",idName);
                result = settable_.getResultClass();
                AnaClassArgumentMatching pr_;
                if (((SettableAbstractFieldOperation) settable_).isIntermediateDottedOperation()) {
                    pr_ = ((SettableAbstractFieldOperation) settable_).getPreviousResultClass();
                } else {
                    pr_ = new AnaClassArgumentMatching(_page.getGlobalClass());
                }
                previousResult = pr_;
                StringList varNames_ = new StringList();
                String varPrevLoc_ = AnaRendBlock.lookForVar(varNames_, _page);
                varNames_.add(varPrevLoc_);
                String varLoc_ = AnaRendBlock.lookForVar(varNames_, _page);
                varNames_.add(varLoc_);
                varNames = varNames_;
                varName = StringList.concat(varPrevLoc_,AnaRendBlock.COMMA,varLoc_);
            } else if (settable_ instanceof ArrOperation) {
                result = settable_.getResultClass();
                ClassMethodId classMethodId_ = ((ArrOperation) settable_).getCallFctContent().getClassMethodId();
                AnaClassArgumentMatching pr_ = ((ArrOperation) settable_).getPreviousResultClass();
                previousResult = pr_;
                if (classMethodId_ == null) {
                    CustList<OperationNode> childrenNodes_ = ((ArrOperation) settable_).getChildrenNodes();
                    StringList varNames_ = new StringList();
                    String varPrevLoc_ = AnaRendBlock.lookForVar(varNames_, _page);
                    varNames_.add(varPrevLoc_);
                    idClass = NumParsers.getSingleNameOrEmpty(pr_.getNames());
                    StringList varParamNames_ = new StringList();
                    StringList typeNames_ = new StringList();
                    int s_ = childrenNodes_.size();
                    for (int i = 0; i < s_; i++) {
                        String varParam_ = AnaRendBlock.lookForVar(varNames_, _page);
                        varNames_.add(varParam_);
                        varParamNames_.add(varParam_);
                        typeNames_.add(_page.getStandards().getAliasPrimInteger());
                    }
                    idName = StringList.concat("[](", StringList.join(typeNames_,","),")");
                    id = StringList.concat(idClass,".",idName);
                    String varLoc_ = AnaRendBlock.lookForVar(varNames_, _page);
                    varNames_.add(varLoc_);
                    varNames = varNames_;
                    varName = StringList.concat(varPrevLoc_,AnaRendBlock.COMMA, StringList.join(varParamNames_,AnaRendBlock.COMMA),AnaRendBlock.COMMA,varLoc_);
                } else {
                    CustList<OperationNode> childrenNodes_ = ((ArrOperation) settable_).getChildrenNodes();
                    StringList varNames_ = new StringList();
                    String varPrevLoc_ = AnaRendBlock.lookForVar(varNames_, _page);
                    varNames_.add(varPrevLoc_);
                    idClass = NumParsers.getSingleNameOrEmpty(pr_.getNames());
                    StringList varParamNames_ = new StringList();
                    int s_ = childrenNodes_.size();
                    for (int i = 0; i < s_; i++) {
                        String varParam_ = AnaRendBlock.lookForVar(varNames_, _page);
                        varNames_.add(varParam_);
                        varParamNames_.add(varParam_);
                    }
                    String sgn_ = classMethodId_.getConstraints().getSignature(_page);
                    idName = StringList.concat("[]", sgn_);
                    id = StringList.concat(idClass,".",idName);
                    String varLoc_ = AnaRendBlock.lookForVar(varNames_, _page);
                    varNames_.add(varLoc_);
                    varNames = varNames_;
                    varName = StringList.concat(varPrevLoc_,AnaRendBlock.COMMA, StringList.join(varParamNames_,AnaRendBlock.COMMA),AnaRendBlock.COMMA,varLoc_);
                }
            } else {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_anaDoc.getFileName());
                badEl_.setIndexFile(_bl.getAttributeDelimiter(_cont.getRendKeyWords().getAttrName()));
                badEl_.buildError(_anaDoc.getRendAnalysisMessages().getBadInputName());
                Configuration.addError(badEl_, _anaDoc, _page);
            }
        } else {
            String type_ = _read.getAttribute(_cont.getRendKeyWords().getAttrType());
            if (!StringList.quickEq(type_,_cont.getRendKeyWords().getValueSubmit())) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_anaDoc.getFileName());
                badEl_.setIndexFile(_bl.getAttributeDelimiter(_cont.getRendKeyWords().getAttrName()));
                badEl_.buildError(_anaDoc.getRendAnalysisMessages().getBadInputName());
                Configuration.addError(badEl_, _anaDoc, _page);
            }
        }
        if (_read.hasAttribute(_varValue)) {
            String value_ = _read.getAttribute(_varValue);
            opsValueRoot = RenderAnalysis.getRootAnalyzedOperations(value_, 0, _anaDoc, _page);
        }
    }

    public String getVarName() {
        return varName;
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

    public OperationNode getOpsReadRoot() {
        return opsReadRoot;
    }

    public OperationNode getOpsValueRoot() {
        return opsValueRoot;
    }

    public SettableElResult getSettable() {
        return settable;
    }

    public AnaClassArgumentMatching getResult() {
        return result;
    }

    public AnaClassArgumentMatching getPreviousResult() {
        return previousResult;
    }

    public StringList getVarNames() {
        return varNames;
    }
}
