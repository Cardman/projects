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
            _page.setSumOffset(resultExpressionRead.getSumOffset());
            _page.zeroOffset();
            tryBuildInputResult(_bl, _anaDoc, _page);
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
            _page.setSumOffset(resultExpressionValue.getSumOffset());
            _page.zeroOffset();
            opsValueRoot = RenderAnalysis.getRootAnalyzedOperations(0, _anaDoc, _page,resultExpressionValue);
        }
    }

    public void tryBuildInputResult(AnaRendBlock _bl, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        setOpsReadRoot(RenderAnalysis.getRootAnalyzedOperations(0, _anaDoc, _page,resultExpressionRead));
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
            arr(_page, (ArrOperation) settable_);
        } else if (settable_ instanceof AbsFctOperation){
            call(_bl, _anaDoc, _page, (AbsFctOperation) settable_);
        } else {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFile(_page.getCurrentFile());
            badEl_.setIndexFile(_bl.getAttributeDelimiter(_anaDoc.getRendKeyWords().getAttrName()));
            badEl_.buildError(_anaDoc.getRendAnalysisMessages().getBadInputName());
            AnalyzingDoc.addError(badEl_, _page);
        }
    }

    private void arr(AnalyzedPageEl _page, ArrOperation _set) {
        AnaClassArgumentMatching pr_ = _set.getPreviousResultClass();
        ClassMethodId classMethodId_ = _set.getCallFctContent().getClassMethodId();
        if (classMethodId_ == null) {
            CustList<OperationNode> childrenNodes_ = _set.getChildrenNodes();
            idClass = NumParsers.getSingleNameOrEmpty(pr_.getNames());
            StringList typeNames_ = new StringList();
            int s_ = childrenNodes_.size();
            for (int i = 0; i < s_; i++) {
                String cl_ = NumParsers.getSingleNameOrEmpty(childrenNodes_.get(i).getResultClass().getNames());
                typeNames_.add(cl_);
            }
            idName = StringUtil.concat("[](", StringUtil.join(typeNames_,","),")");
        } else {
            _set.applySet(_page);
            idClass = NumParsers.getSingleNameOrEmpty(pr_.getNames());
            MethodId constraints_ = classMethodId_.getConstraints();
            String sgn_ = constraints_.getSignature(_page.getDisplayedStrings());
            idName = StringUtil.concat("[]", sgn_);
        }
    }

    private void call(AnaRendBlock _bl, AnalyzingDoc _anaDoc, AnalyzedPageEl _page, AbsFctOperation _set) {
        ClassMethodId classMethodId_ = _set.getClassMethodId();
        AnaClassArgumentMatching pr_ = _set.getPreviousResultClass();
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
