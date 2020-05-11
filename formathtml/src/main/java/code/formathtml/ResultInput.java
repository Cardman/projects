package code.formathtml;

import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.FieldInfo;
import code.expressionlanguage.variables.LocalVariable;
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
    private ClassField idField;
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
            if (!(settable_ instanceof RendSettableFieldOperation)) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_cont.getCurrentFileName());
                badEl_.setIndexFile(_bl.getAttributeDelimiter(_cont.getRendKeyWords().getAttrName()));
                badEl_.buildError(_cont.getRendAnalysisMessages().getBadInputName());
                _cont.addError(badEl_);
            } else {
                FieldInfo infoField_ = ((RendSettableFieldOperation) settable_).getFieldMetaInfo();
                if (infoField_.isStaticField()) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_cont.getCurrentFileName());
                    badEl_.setIndexFile(_bl.getAttributeDelimiter(_cont.getRendKeyWords().getAttrName()));
                    badEl_.buildError(_cont.getRendAnalysisMessages().getStaticInputName(),
                            infoField_.getClassField().getFieldName());
                    _cont.addError(badEl_);
                }
                if (infoField_.isFinalField()) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFileName(_cont.getCurrentFileName());
                    badEl_.setIndexFile(_bl.getAttributeDelimiter(_cont.getRendKeyWords().getAttrName()));
                    badEl_.buildError(_cont.getContext().getAnalysisMessages().getFinalField(),
                            infoField_.getClassField().getFieldName());
                    _cont.addError(badEl_);
                }
                idField = infoField_.getClassField();
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
                LocalVariable lv_ = new LocalVariable();
                String clPrev_ = pr_.getSingleNameOrEmpty();
                lv_.setClassName(clPrev_);
                _cont.getLocalVarsAna().last().addEntry(varPrevLoc_,lv_);
                lv_ = new LocalVariable();
                lv_.setClassName(cl_);
                _cont.getLocalVarsAna().last().addEntry(varLoc_,lv_);

                RendAffectationOperation rendAff_ = new RendAffectationOperation(0,pr_,4);
                ClassArgumentMatching clResField_ = new ClassArgumentMatching(cl_);
                RendDotOperation rendDot_ = new RendDotOperation(0, clResField_,2);
                RendVariableOperation rendPrevVar_ = new RendVariableOperation(0, varPrevLoc_,pr_,0);
                RendSettableFieldOperation rendField_ = new RendSettableFieldOperation((RendSettableFieldOperation) settable_, 1, clResField_, 1, true);
                rendPrevVar_.setSiblingSet(rendField_);
                rendDot_.appendChild(rendPrevVar_);
                rendDot_.appendChild(rendField_);
                rendAff_.appendChild(rendDot_);
                RendVariableOperation rendVar_ = new RendVariableOperation(0, varLoc_, clResField_,3);
                rendAff_.appendChild(rendVar_);
                rendAff_.setup();

                opsWrite.add(rendPrevVar_);
                opsWrite.add(rendField_);
                opsWrite.add(rendDot_);
                opsWrite.add(rendVar_);
                opsWrite.add(rendAff_);

                for (String v:varNames_) {
                    _cont.getLocalVarsAna().last().removeKey(v);
                }
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

    public ClassField getIdField() {
        return idField;
    }
}
