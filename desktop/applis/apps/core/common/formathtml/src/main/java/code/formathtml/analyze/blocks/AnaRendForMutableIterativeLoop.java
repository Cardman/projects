package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AbsLoopDeclarator;
import code.expressionlanguage.analyze.blocks.ForLoopPart;
import code.expressionlanguage.analyze.blocks.ForMutableIterativeLoop;
import code.expressionlanguage.analyze.blocks.ManyLoopExpressionsContent;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.opers.AffectationOperation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.analyze.AnalyzingDoc;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AnaRendForMutableIterativeLoop extends AnaRendParentBlock implements AnaRendLoop, AbsLoopDeclarator {

    private final String label;
    private final int labelOffset;

    private String importedClassName = EMPTY_STRING;

    private final ManyLoopExpressionsContent manyLoopExpressionsContent;

    AnaRendForMutableIterativeLoop(ManyLoopExpressionsContent _cont, OffsetStringInfo _classIndex, int _offset) {
        super(_offset);
        manyLoopExpressionsContent = _cont;
        label = _classIndex.getInfo();
        labelOffset = _classIndex.getOffset();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setSumOffset(manyLoopExpressionsContent.getClassIndexNameOffset());
        _page.zeroOffset();
        manyLoopExpressionsContent.setImportedClassIndexName(ResolvingTypes.resolveCorrectType(manyLoopExpressionsContent.getClassIndexName(), _page).getResult(_page));
        if (!AnaTypeUtil.isIntOrderClass(new AnaClassArgumentMatching(manyLoopExpressionsContent.getImportedClassIndexName()), _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFile(_page.getCurrentFile());
            cast_.setIndexFile(manyLoopExpressionsContent.getClassIndexNameOffset());
            cast_.buildError(_page.getAnalysisMessages().getNotPrimitiveWrapper(),
                    manyLoopExpressionsContent.getImportedClassIndexName());
            AnalyzingDoc.addError(cast_, _page);
        }
        _page.setSumOffset(manyLoopExpressionsContent.getClassNameOffset());
        _page.zeroOffset();
        if (!manyLoopExpressionsContent.getClassName().isEmpty()) {
            _page.setLineDeclarator(this);
            KeyWords keyWords_ = _page.getKeyWords();
            String keyWordVar_ = keyWords_.getKeyWordVar();
            if (StringUtil.quickEq(manyLoopExpressionsContent.getClassName().trim(), keyWordVar_)) {
                importedClassName = keyWordVar_;
            } else {
                importedClassName = ResolvingTypes.resolveCorrectType(manyLoopExpressionsContent.getClassName(), _page).getResult(_page);
            }
            _page.setCurrentVarSetting(importedClassName);
        }
        _page.getVariablesNames().clear();
        _page.getVariablesNamesToInfer().clear();
        _page.setSumOffset(getResInit().getSumOffset());
        _page.zeroOffset();
        _page.setForLoopPartState(ForLoopPart.INIT);
        _page.setAcceptCommaInstr(true);
        if (!manyLoopExpressionsContent.getInit().trim().isEmpty()) {
            manyLoopExpressionsContent.getResInit().setRoot(RenderAnalysis.getRootAnalyzedOperations(0, _anaDoc, _page,getResInit()));
        }
        if (_page.getLineDeclarator() != null) {
            StringList vars_ = _page.getVariablesNames();
            String t_ = inferOrObject(importedClassName, _page);
            AffectationOperation.processInfer(t_, _page);
            getVariableNames().addAllElts(vars_);
            if (manyLoopExpressionsContent.isRefVariable()) {
                ForMutableIterativeLoop.checkOpers(getRootInit(), _page);
            }
        }
        _page.setLineDeclarator(null);
        _page.setAcceptCommaInstr(false);
        _page.setSumOffset(getResExp().getSumOffset());
        _page.zeroOffset();
        _page.setForLoopPartState(ForLoopPart.CONDITION);
        if (!manyLoopExpressionsContent.getExpression().trim().isEmpty()) {
            manyLoopExpressionsContent.getResExp().setRoot(RenderAnalysis.getRootAnalyzedOperations(0, _anaDoc, _page,getResExp()));
        }
        implicit(_page);
        buildIncrementPart(_anaDoc, _page);
    }

    private void implicit(AnalyzedPageEl _page) {
        if (getRootExp() != null) {
            AnaClassArgumentMatching exp_ = getRootExp().getResultClass();
            if (!exp_.isBoolType(_page)) {
                ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_page.getAliasPrimBoolean(), exp_, _page);
                if (res_ != null) {
                    exp_.implicitInfosCore(res_);
                } else {
                    ClassMethodIdReturn trueOp_ = OperationNode.fetchTrueOperator(exp_, _page);
                    if (trueOp_ != null) {
                        exp_.implicitInfosTest(trueOp_);
                    } else {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFile(_page.getCurrentFile());
                        un_.setIndexFile(manyLoopExpressionsContent.getExpressionOffset());
                        un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                                StringUtil.join(exp_.getNames(),AND_ERR));
                        AnalyzingDoc.addError(un_, _page);
                    }
                }
            }
            exp_.setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
        }
    }

    @Override
    public boolean isRefVariable() {
        return manyLoopExpressionsContent.isRefVariable();
    }

    @Override
    public boolean isFinalVariable() {
        return false;
    }

    private void buildIncrementPart(AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setSumOffset(getResStep().getSumOffset());
        _page.zeroOffset();
        _page.setForLoopPartState(ForLoopPart.STEP);
        _page.setAcceptCommaInstr(true);
        if (!manyLoopExpressionsContent.getStep().trim().isEmpty()) {
            manyLoopExpressionsContent.getResStep().setRoot(RenderAnalysis.getRootAnalyzedOperations(0, _anaDoc, _page,getResStep()));
        }
        _page.setAcceptCommaInstr(false);
    }

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        for (String v: manyLoopExpressionsContent.getVariableNames()) {
            _ip.getLoopsVars().removeKey(v);
            _ip.getInfosVars().removeKey(v);
        }
    }

    public String getImportedClassIndexName() {
        return manyLoopExpressionsContent.getImportedClassIndexName();
    }

    public String getImportedClassName() {
        return importedClassName;
    }

    public void setImportedClassName(String _importedClassName) {
        importedClassName = _importedClassName;
    }

    public StringList getVariableNames() {
        return manyLoopExpressionsContent.getVariableNames();
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public int getRealLabelOffset() {
        return labelOffset;
    }

    public ResultExpression getResInit() {
        return manyLoopExpressionsContent.getResInit();
    }

    public ResultExpression getResExp() {
        return manyLoopExpressionsContent.getResExp();
    }

    public ResultExpression getResStep() {
        return manyLoopExpressionsContent.getResStep();
    }

    public String getInit() {
        return manyLoopExpressionsContent.getInit();
    }

    public String getExpression() {
        return manyLoopExpressionsContent.getExpression();
    }

    public String getStep() {
        return manyLoopExpressionsContent.getStep();
    }

    public int getInitOffset() {
        return manyLoopExpressionsContent.getInitOffset();
    }

    public int getExpressionOffset() {
        return manyLoopExpressionsContent.getExpressionOffset();
    }

    public int getStepOffset() {
        return manyLoopExpressionsContent.getStepOffset();
    }

    public OperationNode getRootInit() {
        return manyLoopExpressionsContent.getResInit().getRoot();
    }

    public OperationNode getRootExp() {
        return manyLoopExpressionsContent.getResExp().getRoot();
    }

    public OperationNode getRootStep() {
        return manyLoopExpressionsContent.getResStep().getRoot();
    }

}
