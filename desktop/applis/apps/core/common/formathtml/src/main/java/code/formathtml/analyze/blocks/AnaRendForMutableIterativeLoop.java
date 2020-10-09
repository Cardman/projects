package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.ForLoopPart;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.analyze.opers.AffectationOperation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.analyze.AnalyzingDoc;
import code.util.StringList;

public final class AnaRendForMutableIterativeLoop extends AnaRendParentBlock implements AnaRendLoop {

    private String label;
    private int labelOffset;

    private final String className;
    private int classNameOffset;

    private String importedClassName = EMPTY_STRING;

    private final String classIndexName;
    private String importedClassIndexName;
    private int classIndexNameOffset;

    private final StringList variableNames = new StringList();

    private final String init;
    private int initOffset;

    private final String expression;
    private int expressionOffset;

    private final String step;
    private int stepOffset;


    private OperationNode rootInit;

    private OperationNode rootExp;

    private OperationNode rootStep;
    AnaRendForMutableIterativeLoop(OffsetStringInfo _className,
                                   OffsetStringInfo _from,
                                   OffsetStringInfo _to, OffsetStringInfo _step, OffsetStringInfo _classIndex, OffsetStringInfo _label, OffsetsBlock _offset, PrimitiveTypes _primTypes) {
        super(_offset);
        className = _className.getInfo();
        classNameOffset = _className.getOffset();
        init = _from.getInfo();
        initOffset = _from.getOffset();
        expression = _to.getInfo();
        expressionOffset = _to.getOffset();
        step = _step.getInfo();
        stepOffset = _step.getOffset();
        String classIndex_ = _classIndex.getInfo();
        if (classIndex_.isEmpty()) {
            classIndex_ = _primTypes.getAliasPrimInteger();
        }
        classIndexName = classIndex_;
        classIndexNameOffset = _classIndex.getOffset();
        label = _label.getInfo();
        labelOffset = _label.getOffset();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setGlobalOffset(classIndexNameOffset);
        _page.setOffset(0);
        importedClassIndexName = ResolvingImportTypes.resolveCorrectType(classIndexName, _page);
        if (!AnaTypeUtil.isIntOrderClass(new AnaClassArgumentMatching(importedClassIndexName), _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_anaDoc.getFileName());
            cast_.setIndexFile(classIndexNameOffset);
            cast_.buildError(_page.getAnalysisMessages().getNotPrimitiveWrapper(),
                    importedClassIndexName);
            AnalyzingDoc.addError(cast_, _anaDoc, _page);
        }
        _page.setGlobalOffset(classNameOffset);
        _page.setOffset(0);
        if (!className.isEmpty()) {
            KeyWords keyWords_ = _page.getKeyWords();
            String keyWordVar_ = keyWords_.getKeyWordVar();
            if (StringList.quickEq(className.trim(), keyWordVar_)) {
                importedClassName = keyWordVar_;
            } else {
                importedClassName = ResolvingImportTypes.resolveCorrectType(className, _page);
            }
            _page.setMerged(true);
            _page.setFinalVariable(false);
            _page.setCurrentVarSetting(importedClassName);
        } else {
            _page.setMerged(false);
        }
        _page.getVariablesNames().clear();
        _page.getVariablesNamesToInfer().clear();
        _page.setGlobalOffset(initOffset);
        _page.setOffset(0);
        _anaDoc.setAttribute(_anaDoc.getRendKeyWords().getAttrInit());
        _page.setForLoopPartState(ForLoopPart.INIT);
        _page.setAcceptCommaInstr(true);
        if (!init.trim().isEmpty()) {
            rootInit = RenderAnalysis.getRootAnalyzedOperations(init, 0, _anaDoc, _page);
        }
        if (_page.isMerged()) {
            StringList vars_ = _page.getVariablesNames();
            String t_ = inferOrObject(importedClassName, _page);
            AffectationOperation.processInferLoop(t_, _page);
            getVariableNames().addAllElts(vars_);
        }
        _page.setMerged(false);
        _page.setAcceptCommaInstr(false);
        _page.setGlobalOffset(expressionOffset);
        _page.setOffset(0);
        _anaDoc.setAttribute(_anaDoc.getRendKeyWords().getAttrCondition());
        _page.setForLoopPartState(ForLoopPart.CONDITION);
        if (!expression.trim().isEmpty()) {
            rootExp = RenderAnalysis.getRootAnalyzedOperations(expression, 0, _anaDoc, _page);
        }
        if (rootExp != null) {
            AnaClassArgumentMatching exp_ = rootExp.getResultClass();
            if (!exp_.isBoolType(_page)) {
                ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_page.getAliasPrimBoolean(), exp_, _page);
                if (res_.isFoundMethod()) {
                    ClassMethodId cl_ = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                    exp_.getImplicits().add(cl_);
                    exp_.setRootNumber(res_.getRootNumber());
                    exp_.setMemberNumber(res_.getMemberNumber());
                } else {
                    ClassMethodIdReturn trueOp_ = OperationNode.fetchTrueOperator(exp_, _page);
                    if (trueOp_.isFoundMethod()) {
                        ClassMethodId cl_ = new ClassMethodId(trueOp_.getId().getClassName(),trueOp_.getRealId());
                        exp_.getImplicitsTest().add(cl_);
                        exp_.setRootNumberTest(trueOp_.getRootNumber());
                        exp_.setMemberNumberTest(trueOp_.getMemberNumber());
                    } else {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(_anaDoc.getFileName());
                        un_.setIndexFile(expressionOffset);
                        un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                                StringList.join(exp_.getNames(),AND_ERR));
                        AnalyzingDoc.addError(un_, _anaDoc, _page);
                    }
                }
            }
            exp_.setUnwrapObjectNb(PrimitiveTypes.BOOL_WRAP);
        }
        buildIncrementPart(_anaDoc, _page);
    }

    private void buildIncrementPart(AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setMerged(false);
        _page.setGlobalOffset(stepOffset);
        _page.setOffset(0);
        _page.setForLoopPartState(ForLoopPart.STEP);
        _page.setMerged(true);
        _page.setAcceptCommaInstr(true);
        _anaDoc.setAttribute(_anaDoc.getRendKeyWords().getAttrStep());
        if (!step.trim().isEmpty()) {
            rootStep = RenderAnalysis.getRootAnalyzedOperations(step, 0, _anaDoc, _page);
        }
        _page.setMerged(false);
        _page.setAcceptCommaInstr(false);
    }

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        for (String v: variableNames) {
            _ip.getLoopsVars().removeKey(v);
            _ip.getInfosVars().removeKey(v);
        }
    }

    public String getImportedClassIndexName() {
        return importedClassIndexName;
    }

    public String getImportedClassName() {
        return importedClassName;
    }

    public void setImportedClassName(String _importedClassName) {
        importedClassName = _importedClassName;
    }

    public StringList getVariableNames() {
        return variableNames;
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public int getRealLabelOffset() {
        return labelOffset;
    }

    public int getInitOffset() {
        return initOffset;
    }

    public int getExpressionOffset() {
        return expressionOffset;
    }

    public int getStepOffset() {
        return stepOffset;
    }

    public OperationNode getRootInit() {
        return rootInit;
    }

    public OperationNode getRootExp() {
        return rootExp;
    }

    public OperationNode getRootStep() {
        return rootStep;
    }
}
