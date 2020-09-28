package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetBooleanInfo;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.stds.LgNames;
import code.formathtml.Configuration;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.util.AnalyzingDoc;
import code.util.StringList;

public final class AnaRendForIterativeLoop extends AnaRendParentBlock implements AnaRendLoop {

    private String label;
    private int labelOffset;

    private final String className;
    private String importedClassName;
    private int classNameOffset;

    private final String classIndexName;
    private String importedClassIndexName;
    private int classIndexNameOffset;

    private final String variableName;
    private int variableNameOffset;

    private final String init;
    private int initOffset;

    private final String expression;
    private int expressionOffset;

    private final String step;
    private int stepOffset;

    private final boolean eq;
    private int eqOffset;

    private OperationNode rootInit;

    private OperationNode rootExp;

    private OperationNode rootStep;
    AnaRendForIterativeLoop(OffsetStringInfo _className, OffsetStringInfo _variable,
                            OffsetStringInfo _from,
                            OffsetStringInfo _to, OffsetBooleanInfo _eq, OffsetStringInfo _step, OffsetStringInfo _classIndex, OffsetStringInfo _label, OffsetsBlock _offset, LgNames _stds) {
        super(_offset);
        className = _className.getInfo();
        classNameOffset = _className.getOffset();
        variableName = _variable.getInfo();
        variableNameOffset = _variable.getOffset();
        init = _from.getInfo();
        initOffset = _from.getOffset();
        expression = _to.getInfo();
        expressionOffset = _to.getOffset();
        step = _step.getInfo();
        stepOffset = _step.getOffset();
        eq = _eq.isInfo();
        eqOffset = _eq.getOffset();
        String classIndex_ = _classIndex.getInfo();
        if (classIndex_.isEmpty()) {
            classIndex_ = _stds.getAliasPrimInteger();
        }
        classIndexName = classIndex_;
        classIndexNameOffset = _classIndex.getOffset();
        label = _label.getInfo();
        labelOffset = _label.getOffset();
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setGlobalOffset(classIndexNameOffset);
        _page.setOffset(0);
        importedClassIndexName = ResolvingImportTypes.resolveCorrectType(classIndexName, _page);
        if (!AnaTypeUtil.isIntOrderClass(new AnaClassArgumentMatching(importedClassIndexName), _page)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(importedClassIndexName);
            mapping_.setParam(_page.getStandards().getAliasLong());
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_anaDoc.getFileName());
            cast_.setIndexFile(classIndexNameOffset);
            cast_.buildError(_page.getAnalysisMessages().getNotPrimitiveWrapper(),
                    importedClassIndexName);
            Configuration.addError(cast_, _anaDoc, _page);
        }
        _page.setGlobalOffset(classNameOffset);
        _page.setOffset(0);
        importedClassName = ResolvingImportTypes.resolveCorrectType(className, _page);
        String cl_ = importedClassName;
        AnaClassArgumentMatching elementClass_ = new AnaClassArgumentMatching(cl_);
        if (!AnaTypeUtil.isIntOrderClass(elementClass_, _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_anaDoc.getFileName());
            cast_.setIndexFile(classNameOffset);
            cast_.buildError(_page.getAnalysisMessages().getNotPrimitiveWrapper(),
                    importedClassName);
            Configuration.addError(cast_, _anaDoc, _page);
        }
        _page.setGlobalOffset(variableNameOffset);
        _page.setOffset(0);
        TokenErrorMessage res_ = ManageTokens.partVar(_page).checkTokenVar(variableName, _page);
        if (res_.isError()) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(_anaDoc.getFileName());
            b_.setIndexFile(variableNameOffset);
            b_.setBuiltError(res_.getMessage());
            Configuration.addError(b_, _anaDoc, _page);
        }
        _page.setGlobalOffset(initOffset);
        _page.setOffset(0);
        _anaDoc.setAttribute(_cont.getRendKeyWords().getAttrFrom());
        rootInit = RenderAnalysis.getRootAnalyzedOperations(init, 0, _anaDoc, _page);
        checkResult(_anaDoc, _page, cl_, initOffset, rootInit);
        _page.setGlobalOffset(expressionOffset);
        _page.setOffset(0);
        _anaDoc.setAttribute(_cont.getRendKeyWords().getAttrTo());
        rootExp = RenderAnalysis.getRootAnalyzedOperations(expression, 0, _anaDoc, _page);
        checkResult(_anaDoc, _page, cl_, expressionOffset, rootExp);
        _page.setGlobalOffset(stepOffset);
        _page.setOffset(0);
        _anaDoc.setAttribute(_cont.getRendKeyWords().getAttrStep());
        rootStep = RenderAnalysis.getRootAnalyzedOperations(step, 0, _anaDoc, _page);
        checkResult(_anaDoc, _page, cl_, stepOffset, rootStep);
        if (!res_.isError()) {
            AnaLoopVariable lv_ = new AnaLoopVariable();
            lv_.setIndexClassName(importedClassIndexName);
            _page.getLoopsVars().put(variableName, lv_);
            AnaLocalVariable lInfo_ = new AnaLocalVariable();
            lInfo_.setClassName(cl_);
            lInfo_.setConstType(ConstType.FIX_VAR);
            _page.getInfosVars().put(variableName, lInfo_);
        }
    }

    private static void checkResult(AnalyzingDoc _anaDoc, AnalyzedPageEl _page, String _result, int _offset, OperationNode _currentRoot) {
        Mapping m_ = new Mapping();
        AnaClassArgumentMatching resCl_ = _currentRoot.getResultClass();
        m_.setArg(resCl_);
        m_.setParam(_result);
        if (!AnaTemplates.isCorrectOrNumbers(m_, _page)) {
            ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_result, resCl_, _page);
            if (res_.isFoundMethod()) {
                ClassMethodId cl_ = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                resCl_.getImplicits().add(cl_);
                resCl_.setRootNumber(res_.getRootNumber());
                resCl_.setMemberNumber(res_.getMemberNumber());
            } else {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(_anaDoc.getFileName());
                cast_.setIndexFile(_offset);
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        _result,
                        StringList.join(_currentRoot.getResultClass().getNames(),AND_ERR));
                Configuration.addError(cast_, _anaDoc, _page);
            }
        }
    }

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        String var_ = getVariableName();
        _ip.getLoopsVars().removeKey(var_);
        _ip.getInfosVars().removeKey(var_);
    }
    public String getVariableName() {
        return variableName;
    }

    public boolean isEq() {
        return eq;
    }
    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public int getRealLabelOffset() {
        return labelOffset;
    }

    public String getImportedClassName() {
        return importedClassName;
    }

    public String getImportedClassIndexName() {
        return importedClassIndexName;
    }

    public int getInitOffset() {
        return initOffset;
    }

    public int getStepOffset() {
        return stepOffset;
    }

    public int getExpressionOffset() {
        return expressionOffset;
    }

    public OperationNode getRootExp() {
        return rootExp;
    }

    public OperationNode getRootInit() {
        return rootInit;
    }

    public OperationNode getRootStep() {
        return rootStep;
    }
}
