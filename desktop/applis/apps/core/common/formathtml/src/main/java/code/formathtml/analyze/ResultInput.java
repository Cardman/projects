package code.formathtml.analyze;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.opers.*;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.opers.AnaSettableOperationContent;
import code.formathtml.analyze.blocks.AnaRendBlock;
import code.sml.Element;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ResultInput {

    private static final String EMPTY_STRING = "";
    private final ResultExpression resultExpressionRead = new ResultExpression();
    private final ResultExpression resultExpressionValue = new ResultExpression();
    private OperationNode opsReadRoot;
    private OperationNode opsValueRoot;
    private String idClass = EMPTY_STRING;
    private String idName = EMPTY_STRING;
    private String className = EMPTY_STRING;

    public void build(AnaRendBlock _bl, Element _read, String _varValue, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        int attributeName_ = _bl.getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrName());
        String name_ = _read.getAttribute(_anaDoc.getRendKeyWords().getAttrName());
        if (!name_.isEmpty()) {
            _page.setGlobalOffset(attributeName_);
            _page.zeroOffset();
            tryBuildInputResult(name_, _bl, _anaDoc, _page);
        } else {
            String type_ = _read.getAttribute(_anaDoc.getRendKeyWords().getAttrType());
            if (!StringUtil.quickEq(type_,_anaDoc.getRendKeyWords().getValueSubmit())) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFile(_page.getCurrentFile());
                badEl_.setIndexFile(attributeName_);
                badEl_.buildError(_anaDoc.getRendAnalysisMessages().getBadInputName());
                AnalyzingDoc.addError(badEl_, _page);
            }
        }
        if (_read.hasAttribute(_varValue)) {
            int attributeValue_ = _bl.getAttributeDelimiter(_varValue);
            _page.setGlobalOffset(attributeValue_);
            _page.zeroOffset();
            String value_ = _read.getAttribute(_varValue);
            opsValueRoot = RenderAnalysis.getRootAnalyzedOperations(value_, 0, _anaDoc, _page,resultExpressionValue);
        }
    }

    public void tryBuildInputResult(String _name, AnaRendBlock _bl, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        setOpsReadRoot(RenderAnalysis.getRootAnalyzedOperations(_name, 0, _anaDoc, _page,resultExpressionRead));
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
            AnaSettableOperationContent settableFieldContent_ = ((SettableAbstractFieldOperation) settable_).getSettableFieldContent();
            ClassField clField_ = settableFieldContent_.getClassField();
            if (clField_ != null) {
                if (settableFieldContent_.isFinalField()) {
                    FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                    badEl_.setFile(_page.getCurrentFile());
                    badEl_.setIndexFile(_bl.getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrName()));
                    badEl_.buildError(_page.getAnalysisMessages().getFinalField(),
                            clField_.getFieldName());
                    AnalyzingDoc.addError(badEl_, _page);
                }
                idClass = clField_.getClassName();
                idName = clField_.getFieldName();
            }
        } else if (settable_ instanceof ArrOperation) {
            AnaClassArgumentMatching pr_ = ((ArrOperation) settable_).getPreviousResultClass();
            ClassMethodId classMethodId_ = ((ArrOperation) settable_).getCallFctContent().getClassMethodId();
            if (classMethodId_ == null) {
                CustList<OperationNode> childrenNodes_ = ((ArrOperation) settable_).getChildrenNodes();
                idClass = NumParsers.getSingleNameOrEmpty(pr_.getNames());
                StringList typeNames_ = new StringList();
                int s_ = childrenNodes_.size();
                for (int i = 0; i < s_; i++) {
                    String cl_ = NumParsers.getSingleNameOrEmpty(childrenNodes_.get(i).getResultClass().getNames());
                    typeNames_.add(cl_);
                }
                idName = StringUtil.concat("[](", StringUtil.join(typeNames_,","),")");
            } else {
                ((ArrOperation) settable_).applySet(_page);
                idClass = NumParsers.getSingleNameOrEmpty(pr_.getNames());
                MethodId constraints_ = classMethodId_.getConstraints();
                String sgn_ = constraints_.getSignature(_page.getDisplayedStrings());
                idName = StringUtil.concat("[]", sgn_);
            }
        } else if (settable_ instanceof AbstractCallFctOperation){
            ClassMethodId classMethodId_ = ((AbstractCallFctOperation)settable_).getClassMethodId();
            AnaClassArgumentMatching pr_ = ((InvokingOperation) settable_).getPreviousResultClass();
            if (classMethodId_ == null || !classMethodId_.getConstraints().isRetRef()) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFile(_page.getCurrentFile());
                badEl_.setIndexFile(_bl.getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrName()));
                badEl_.buildError(_anaDoc.getRendAnalysisMessages().getBadInputName());
                AnalyzingDoc.addError(badEl_, _page);
            } else {
                idClass = NumParsers.getSingleNameOrEmpty(pr_.getNames());
                MethodId constraints_ = classMethodId_.getConstraints();
                idName = constraints_.getSignature(_page.getDisplayedStrings());
            }
        } else {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFile(_page.getCurrentFile());
            badEl_.setIndexFile(_bl.getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrName()));
            badEl_.buildError(_anaDoc.getRendAnalysisMessages().getBadInputName());
            AnalyzingDoc.addError(badEl_, _page);
        }
    }

    public ResultExpression getResultExpressionRead() {
        return resultExpressionRead;
    }

    public ResultExpression getResultExpressionValue() {
        return resultExpressionValue;
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

}
