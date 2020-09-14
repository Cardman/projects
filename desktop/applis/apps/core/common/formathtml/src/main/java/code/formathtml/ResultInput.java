package code.formathtml;

import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.analyze.opers.util.FieldInfo;
import code.formathtml.exec.*;
import code.sml.Element;
import code.util.CustList;
import code.util.StringList;

public final class ResultInput {

    static final String EMPTY_STRING = "";

    private CustList<RendDynOperationNode> opsRead = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsValue = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsWrite = new CustList<RendDynOperationNode>();
    private String varName = EMPTY_STRING;
    private String id = EMPTY_STRING;
    private String idClass = EMPTY_STRING;
    private String idName = EMPTY_STRING;
    private String className = EMPTY_STRING;
    public void build(Configuration _cont, RendBlock _bl, RendDocumentBlock _doc, Element _read,String _varValue) {
        String name_ = _read.getAttribute(_cont.getRendKeyWords().getAttrName());
        if (!name_.isEmpty()) {
            opsRead = RenderExpUtil.getAnalyzedOperations(name_,_bl.getAttributeDelimiter(_cont.getRendKeyWords().getAttrName()), 0, _cont);
            RendDynOperationNode last_ = opsRead.last();
            RendDynOperationNode res_;
            if (last_ instanceof RendIdOperation) {
                res_ = RendAffectationOperation.getIdOp((RendMethodOperation) last_);
            } else {
                res_ = last_;
            }
            RendSettableElResult settable_ = RendAffectationOperation.castDottedTo(res_);
            if (!(settable_ instanceof RendPossibleIntermediateDotted)) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_cont.getAnalyzingDoc().getFileName());
                badEl_.setIndexFile(_bl.getAttributeDelimiter(_cont.getRendKeyWords().getAttrName()));
                badEl_.buildError(_cont.getRendAnalysisMessages().getBadInputName());
                Configuration.addError(badEl_, _cont.getAnalyzingDoc(), _cont.getContext().getAnalyzing());
            } else {
                className = ((RendDynOperationNode)settable_).getResultClass().getSingleNameOrEmpty();
                if (settable_ instanceof RendSettableFieldOperation) {
                    FieldInfo infoField_ = ((RendSettableFieldOperation) settable_).getFieldMetaInfo();
                    ClassField clField_ = infoField_.getClassField();
                    if (infoField_.isStaticField()) {
                        FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                        badEl_.setFileName(_cont.getAnalyzingDoc().getFileName());
                        badEl_.setIndexFile(_bl.getAttributeDelimiter(_cont.getRendKeyWords().getAttrName()));
                        badEl_.buildError(_cont.getRendAnalysisMessages().getStaticInputName(),
                                clField_.getFieldName());
                        Configuration.addError(badEl_, _cont.getAnalyzingDoc(), _cont.getContext().getAnalyzing());
                    }
                    if (infoField_.isFinalField()) {
                        FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                        badEl_.setFileName(_cont.getAnalyzingDoc().getFileName());
                        badEl_.setIndexFile(_bl.getAttributeDelimiter(_cont.getRendKeyWords().getAttrName()));
                        badEl_.buildError(_cont.getContext().getAnalyzing().getAnalysisMessages().getFinalField(),
                                clField_.getFieldName());
                        Configuration.addError(badEl_, _cont.getAnalyzingDoc(), _cont.getContext().getAnalyzing());
                    }
                    idClass = clField_.getClassName();
                    idName = clField_.getFieldName();
                    id = StringList.concat(idClass,".",idName);
                    String cl_ = ((RendSettableFieldOperation) settable_).getResultClass().getSingleNameOrEmpty();
                    ClassArgumentMatching pr_;
                    if (((RendSettableFieldOperation) settable_).isIntermediateDottedOperation()) {
                        pr_ = ((RendSettableFieldOperation) settable_).getPrevious();
                    } else {
                        pr_ = new ClassArgumentMatching(_cont.getAnalyzing().getGlobalClass());
                    }
                    StringList varNames_ = new StringList();
                    String varPrevLoc_ = RendBlock.lookForVar(_cont, varNames_);
                    varNames_.add(varPrevLoc_);
                    String varLoc_ = RendBlock.lookForVar(_cont, varNames_);
                    varNames_.add(varLoc_);
                    varName = StringList.concat(varPrevLoc_,RendBlock.COMMA,varLoc_);

                    RendAffectationOperation rendAff_ = new RendAffectationOperation(0,pr_,4);
                    ClassArgumentMatching clResField_ = new ClassArgumentMatching(cl_);
                    RendDotOperation rendDot_ = new RendDotOperation(0, clResField_,2);
                    RendStdVariableOperation rendPrevVar_ = new RendStdVariableOperation(0, varPrevLoc_,pr_,0);
                    RendSettableFieldOperation rendField_ = new RendSettableFieldOperation((RendSettableFieldOperation) settable_, 1, clResField_, 1, true);
                    rendPrevVar_.setSiblingSet(rendField_);
                    rendDot_.appendChild(rendPrevVar_);
                    rendDot_.appendChild(rendField_);
                    rendAff_.appendChild(rendDot_);
                    RendStdVariableOperation rendVar_ = new RendStdVariableOperation(0, varLoc_, clResField_,3);
                    rendAff_.appendChild(rendVar_);
                    rendAff_.setup();

                    opsWrite.add(rendPrevVar_);
                    opsWrite.add(rendField_);
                    opsWrite.add(rendDot_);
                    opsWrite.add(rendVar_);
                    opsWrite.add(rendAff_);
                } else {
                    if (settable_ instanceof RendArrOperation) {
                        String cl_ = ((RendArrOperation) settable_).getResultClass().getSingleNameOrEmpty();
                        CustList<RendDynOperationNode> childrenNodes_ = ((RendArrOperation) settable_).getChildrenNodes();
                        StringList varNames_ = new StringList();
                        String varPrevLoc_ = RendBlock.lookForVar(_cont, varNames_);
                        varNames_.add(varPrevLoc_);
                        ClassArgumentMatching pr_ = ((RendArrOperation) settable_).getPrevious();
                        idClass = pr_.getSingleNameOrEmpty();
                        RendAffectationOperation rendAff_ = new RendAffectationOperation(0,pr_,4+childrenNodes_.size());
                        ClassArgumentMatching clResField_ = new ClassArgumentMatching(cl_);
                        RendDotOperation rendDot_ = new RendDotOperation(0, clResField_,2+childrenNodes_.size());
                        RendStdVariableOperation rendPrevVar_ = new RendStdVariableOperation(0, varPrevLoc_,pr_,0);
                        RendArrOperation arr_ = new RendArrOperation((RendArrOperation) settable_, 1, pr_, childrenNodes_.size() + 1, true, null);
                        int i_ = 1;
                        CustList<RendDynOperationNode> list_ = new CustList<RendDynOperationNode>();
                        StringList varParamNames_ = new StringList();
                        StringList typeNames_ = new StringList();
                        for (RendDynOperationNode o: childrenNodes_) {
                            String varParam_ = RendBlock.lookForVar(_cont, varNames_);
                            RendStdVariableOperation rendVar_ = new RendStdVariableOperation(i_-1, varParam_, o.getResultClass(),i_);
                            arr_.appendChild(rendVar_);
                            list_.add(rendVar_);
                            varNames_.add(varParam_);
                            varParamNames_.add(varParam_);
                            typeNames_.add(_cont.getStandards().getAliasPrimInteger());
                            i_++;
                        }
                        idName = StringList.concat("[](", StringList.join(typeNames_,","),")");
                        id = StringList.concat(idClass,".",idName);
                        String varLoc_ = RendBlock.lookForVar(_cont, varNames_);
                        varNames_.add(varLoc_);
                        varName = StringList.concat(varPrevLoc_,RendBlock.COMMA, StringList.join(varParamNames_,RendBlock.COMMA),RendBlock.COMMA,varLoc_);
                        rendPrevVar_.setSiblingSet(arr_);
                        rendDot_.appendChild(rendPrevVar_);
                        rendDot_.appendChild(arr_);
                        rendAff_.appendChild(rendDot_);
                        RendStdVariableOperation rendVar_ = new RendStdVariableOperation(0, varLoc_, clResField_,childrenNodes_.size() + 3);
                        rendAff_.appendChild(rendVar_);
                        rendAff_.setup();
                        opsWrite.add(rendPrevVar_);
                        opsWrite.addAllElts(list_);
                        opsWrite.add(arr_);
                        opsWrite.add(rendDot_);
                        opsWrite.add(rendVar_);
                        opsWrite.add(rendAff_);
                    } else {
                        String cl_ = ((RendCustArrOperation) settable_).getResultClass().getSingleNameOrEmpty();
                        CustList<RendDynOperationNode> childrenNodes_ = ((RendCustArrOperation) settable_).getChildrenNodes();
                        StringList varNames_ = new StringList();
                        String varPrevLoc_ = RendBlock.lookForVar(_cont, varNames_);
                        varNames_.add(varPrevLoc_);
                        ClassArgumentMatching pr_ = ((RendCustArrOperation) settable_).getPrevious();
                        idClass = pr_.getSingleNameOrEmpty();
                        RendAffectationOperation rendAff_ = new RendAffectationOperation(0,pr_,4+childrenNodes_.size());
                        ClassArgumentMatching clResField_ = new ClassArgumentMatching(cl_);
                        RendDotOperation rendDot_ = new RendDotOperation(0, clResField_,2+childrenNodes_.size());
                        RendStdVariableOperation rendPrevVar_ = new RendStdVariableOperation(0, varPrevLoc_,pr_,0);
                        RendCustArrOperation arr_ = new RendCustArrOperation((RendCustArrOperation) settable_, 1, pr_, childrenNodes_.size() + 1, true, null);
                        int i_ = 1;
                        CustList<RendDynOperationNode> list_ = new CustList<RendDynOperationNode>();
                        StringList varParamNames_ = new StringList();
                        for (RendDynOperationNode o: childrenNodes_) {
                            String varParam_ = RendBlock.lookForVar(_cont, varNames_);
                            RendStdVariableOperation rendVar_ = new RendStdVariableOperation(i_-1, varParam_, o.getResultClass(),i_);
                            arr_.appendChild(rendVar_);
                            list_.add(rendVar_);
                            varNames_.add(varParam_);
                            varParamNames_.add(varParam_);
                            i_++;
                        }
                        String sgn_ = ((RendCustArrOperation) settable_).getClassMethodId().getConstraints().getSignature(_cont.getAnalyzing());
                        idName = StringList.concat("[]", sgn_);
                        id = StringList.concat(idClass,".",idName);
                        String varLoc_ = RendBlock.lookForVar(_cont, varNames_);
                        varNames_.add(varLoc_);
                        varName = StringList.concat(varPrevLoc_,RendBlock.COMMA, StringList.join(varParamNames_,RendBlock.COMMA),RendBlock.COMMA,varLoc_);
                        rendPrevVar_.setSiblingSet(arr_);
                        rendDot_.appendChild(rendPrevVar_);
                        rendDot_.appendChild(arr_);
                        rendAff_.appendChild(rendDot_);
                        RendStdVariableOperation rendVar_ = new RendStdVariableOperation(0, varLoc_, clResField_,childrenNodes_.size() + 3);
                        rendAff_.appendChild(rendVar_);
                        rendAff_.setup();
                        opsWrite.add(rendPrevVar_);
                        opsWrite.addAllElts(list_);
                        opsWrite.add(arr_);
                        opsWrite.add(rendDot_);
                        opsWrite.add(rendVar_);
                        opsWrite.add(rendAff_);
                    }
                }
            }
        } else {
            String type_ = _read.getAttribute(_cont.getRendKeyWords().getAttrType());
            if (!StringList.quickEq(type_,_cont.getRendKeyWords().getValueSubmit())) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_cont.getAnalyzingDoc().getFileName());
                badEl_.setIndexFile(_bl.getAttributeDelimiter(_cont.getRendKeyWords().getAttrName()));
                badEl_.buildError(_cont.getRendAnalysisMessages().getBadInputName());
                Configuration.addError(badEl_, _cont.getAnalyzingDoc(), _cont.getContext().getAnalyzing());
            }
        }
        if (_read.hasAttribute(_varValue)) {
            String value_ = _read.getAttribute(_varValue);
            opsValue = RenderExpUtil.getAnalyzedOperations(value_,_bl.getAttributeDelimiter(_varValue), 0, _cont);
        }
    }

    public CustList<RendDynOperationNode> getOpsRead() {
        return opsRead;
    }

    public CustList<RendDynOperationNode> getOpsValue() {
        return opsValue;
    }

    public CustList<RendDynOperationNode> getOpsWrite() {
        return opsWrite;
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
}
