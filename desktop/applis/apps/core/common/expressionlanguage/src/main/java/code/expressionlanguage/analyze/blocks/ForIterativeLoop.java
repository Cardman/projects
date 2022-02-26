package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetBooleanInfo;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.linkage.ExportCst;
import code.util.*;
import code.util.core.StringUtil;

public final class ForIterativeLoop extends AbstractForLoop implements Loop {

    private final String label;
    private final int labelOffset;

    private final String className;
    private String importedClassName;
    private final int classNameOffset;

    private final String classIndexName;
    private String importedClassIndexName;
    private final int classIndexNameOffset;

    private final String variableName;
    private final int variableNameOffset;

    private final String init;
    private final int initOffset;

    private final String expression;
    private final int expressionOffset;

    private final String step;
    private final int stepOffset;

    private final boolean eq;
    private final int eqOffset;

    private final ResultExpression resInit = new ResultExpression();
    private final ResultExpression resExp = new ResultExpression();
    private final ResultExpression resStep = new ResultExpression();

    private final StringList nameErrors = new StringList();
    public ForIterativeLoop(OffsetStringInfo _className, OffsetStringInfo _variable,
                            OffsetStringInfo _from,
                            OffsetStringInfo _to, OffsetBooleanInfo _eq, OffsetStringInfo _step, OffsetStringInfo _classIndex, OffsetStringInfo _label, int _offset, AnalyzedPageEl _page) {
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
            classIndex_ = _page.getAliasPrimInteger();
        }
        classIndexName = classIndex_;
        classIndexNameOffset = _classIndex.getOffset();
        label = _label.getInfo();
        labelOffset = _label.getOffset();
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public int getRealLabelOffset() {
        return getLabelOffset();
    }
    public int getLabelOffset() {
        return labelOffset;
    }

    public int getClassNameOffset() {
        return classNameOffset;
    }

    public int getClassIndexNameOffset() {
        return classIndexNameOffset;
    }

    public int getVariableNameOffset() {
        return variableNameOffset;
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

    public int getEqOffset() {
        return eqOffset;
    }

    public String getClassIndexName() {
        return classIndexName;
    }

    public String getClassName() {
        return className;
    }

    public String getVariableName() {
        return variableName;
    }

    public String getInit() {
        return init;
    }

    public String getExpression() {
        return expression;
    }

    public String getStep() {
        return step;
    }

    public boolean isEq() {
        return eq;
    }

    public String getImportedClassName() {
        return importedClassName;
    }

    public String getImportedClassIndexName() {
        return importedClassIndexName;
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        boolean res_ = processVariableNames(_page);
        MemberCallingsBlock f_ = _page.getCurrentFct();
        String cl_ = importedClassName;
        _page.setGlobalOffset(initOffset);
        _page.zeroOffset();
        MethodAccessKind static_ = f_.getStaticContext();
        resInit.setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(resInit, init, Calculation.staticCalculation(static_), _page));
//        rootInit = _page.getCurrentRoot();
//        ExecOperationNode initEl_ = init_.last();
        checkType(cl_, initOffset, resInit.getRoot(), _page);
        _page.setGlobalOffset(expressionOffset);
        _page.zeroOffset();
        resExp.setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(resExp, expression, Calculation.staticCalculation(static_), _page));
//        rootExp = _page.getCurrentRoot();
//        ExecOperationNode expressionEl_ = exp_.last();
        checkType(cl_, expressionOffset, resExp.getRoot(), _page);
        _page.setGlobalOffset(stepOffset);
        _page.zeroOffset();
        resStep.setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(resStep, step, Calculation.staticCalculation(static_), _page));
//        rootStep = _page.getCurrentRoot();
//        ExecOperationNode stepEl_ = step_.last();
        checkType(cl_, stepOffset, resStep.getRoot(), _page);
        if (res_) {
            AnaLoopVariable lv_ = new AnaLoopVariable();
            lv_.setRef(variableNameOffset);
            lv_.setIndexClassName(importedClassIndexName);
            _page.getLoopsVars().put(variableName, lv_);
            AnaLocalVariable lInfo_ = new AnaLocalVariable();
            lInfo_.setClassName(cl_);
            lInfo_.setRef(variableNameOffset);
            lInfo_.setConstType(ConstType.FIX_VAR);
            _page.getInfosVars().put(variableName, lInfo_);
        }
//        _page.getCoverage().putBlockOperationsLoops(this);
//        ExecForIterativeLoop exec_ = new ExecForIterativeLoop(getOffset(),label, importedClassName,
//                importedClassIndexName,variableName,variableNameOffset, initOffset,
//                expressionOffset, stepOffset,eq,init_,exp_,step_);
//        exec_.setFile(_page.getBlockToWrite().getFile());
//        _page.getBlockToWrite().appendChild(exec_);
//        _page.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
//        _page.getCoverage().putBlockOperations(exec_,this);
    }

    private void checkType(String _elementClass, int _offset, OperationNode _root, AnalyzedPageEl _page) {
        Mapping m_ = new Mapping();
        AnaClassArgumentMatching arg_ = _root.getResultClass();
        m_.setArg(arg_);
        m_.setParam(_elementClass);
        if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
            ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_elementClass, arg_, _page);
            if (res_ != null) {
                arg_.implicitInfos(res_);
            } else {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFile(getFile());
                cast_.setIndexFile(_offset);
                //char before expression
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringUtil.join(arg_.getNames(), ExportCst.JOIN_TYPES),
                        _elementClass);
                _page.addLocError(cast_);
                addErrorBlock(cast_.getBuiltError());
            }
        }
//        ElUtil.setImplicits(_stepEl, _page, _root);
    }

    private boolean processVariableNames(AnalyzedPageEl _page) {
        _page.setGlobalOffset(classIndexNameOffset);
        _page.zeroOffset();
        importedClassIndexName = ResolvingTypes.resolveCorrectType(classIndexName, _page).getResult(_page);
        if (!AnaTypeUtil.isIntOrderClass(new AnaClassArgumentMatching(importedClassIndexName), _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFile(getFile());
            cast_.setIndexFile(classIndexNameOffset);
            //classIndexName len
            cast_.buildError(_page.getAnalysisMessages().getNotPrimitiveWrapper(),
                    importedClassIndexName);
            _page.addLocError(cast_);
            addErrorBlock(cast_.getBuiltError());
        }
        _page.setGlobalOffset(classNameOffset);
        _page.zeroOffset();
        importedClassName = ResolvingTypes.resolveCorrectType(className, _page).getResult(_page);
        String cl_ = importedClassName;
        AnaClassArgumentMatching elementClass_ = new AnaClassArgumentMatching(cl_);
        if (!AnaTypeUtil.isIntOrderClass(elementClass_, _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFile(getFile());
            cast_.setIndexFile(classNameOffset);
            //className len
            cast_.buildError(_page.getAnalysisMessages().getNotPrimitiveWrapper(),
                    importedClassName);
            _page.addLocError(cast_);
            addErrorBlock(cast_.getBuiltError());
        }
        _page.setGlobalOffset(variableNameOffset);
        _page.zeroOffset();
        TokenErrorMessage res_ = ManageTokens.partVar(_page).checkTokenVar(variableName, _page);
        if (res_.isError()) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFile(getFile());
            b_.setIndexFile(variableNameOffset);
            //variable name len
            b_.setBuiltError(res_.getMessage());
            _page.addLocError(b_);
            nameErrors.add(b_.getBuiltError());
            return false;
        }
        return true;
    }


    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        _ip.getLoopsVars().removeKey(variableName);
        _ip.getInfosVars().removeKey(variableName);
    }

    public ResultExpression getResInit() {
        return resInit;
    }

    public OperationNode getRootInit() {
        return resInit.getRoot();
    }

    public ResultExpression getResExp() {
        return resExp;
    }

    public OperationNode getRootExp() {
        return resExp.getRoot();
    }

    public ResultExpression getResStep() {
        return resStep;
    }

    public OperationNode getRootStep() {
        return resStep.getRoot();
    }

    public StringList getNameErrors() {
        return nameErrors;
    }
}
