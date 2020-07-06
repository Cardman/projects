package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.exec.blocks.ExecForIterativeLoop;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetBooleanInfo;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.*;

public final class ForIterativeLoop extends BracedBlock implements ForLoop {

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

    private final StringList nameErrors = new StringList();
    public ForIterativeLoop(ContextEl _importingPage,
                            OffsetStringInfo _className, OffsetStringInfo _variable,
                            OffsetStringInfo _from,
                            OffsetStringInfo _to, OffsetBooleanInfo _eq, OffsetStringInfo _step, OffsetStringInfo _classIndex, OffsetStringInfo _label, OffsetsBlock _offset) {
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
            classIndex_ = _importingPage.getStandards().getAliasPrimInteger();
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

    @Override
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        processVariableNames(_cont);
        MemberCallingsBlock f_ = _cont.getAnalyzing().getCurrentFct();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        String cl_ = importedClassName;
        ClassArgumentMatching elementClass_ = new ClassArgumentMatching(cl_);
        page_.setGlobalOffset(initOffset);
        page_.setOffset(0);
        MethodAccessKind static_ = f_.getStaticContext();
        CustList<ExecOperationNode> init_ = ElUtil.getAnalyzedOperationsReadOnly(init, _cont, Calculation.staticCalculation(static_));
        rootInit = page_.getCurrentRoot();
        ExecOperationNode initEl_ = init_.last();
        checkType(_cont, elementClass_, initEl_, initOffset);
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        CustList<ExecOperationNode> exp_ = ElUtil.getAnalyzedOperationsReadOnly(expression, _cont, Calculation.staticCalculation(static_));
        rootExp = page_.getCurrentRoot();
        ExecOperationNode expressionEl_ = exp_.last();
        checkType(_cont, elementClass_, expressionEl_, expressionOffset);
        page_.setGlobalOffset(stepOffset);
        page_.setOffset(0);
        CustList<ExecOperationNode> step_ = ElUtil.getAnalyzedOperationsReadOnly(step, _cont, Calculation.staticCalculation(static_));
        rootStep = page_.getCurrentRoot();
        ExecOperationNode stepEl_ = step_.last();
        checkType(_cont, elementClass_, stepEl_, stepOffset);
        AnaLoopVariable lv_ = new AnaLoopVariable();
        lv_.setClassName(cl_);
        lv_.setRef(variableNameOffset);
        lv_.setIndexClassName(importedClassIndexName);
        _cont.getAnalyzing().putVar(variableName, lv_);
        _cont.getCoverage().putBlockOperationsLoops(_cont,this);
        ExecForIterativeLoop exec_ = new ExecForIterativeLoop(getOffset(),label, importedClassName,
                importedClassIndexName,variableName,variableNameOffset, initOffset,
                expressionOffset, stepOffset,eq,init_,exp_,step_);
        page_.getBlockToWrite().appendChild(exec_);
        page_.getAnalysisAss().getMappingMembers().put(exec_,this);
        page_.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
        _cont.getCoverage().putBlockOperations(_cont, exec_,this);
    }

    private void checkType(ContextEl _cont, ClassArgumentMatching _elementClass, ExecOperationNode _stepEl, int _offset) {
        Mapping m_ = new Mapping();
        m_.setArg(_stepEl.getResultClass());
        m_.setParam(_elementClass);
        if (!AnaTemplates.isCorrectOrNumbers(m_,_cont)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(_offset);
            //char before expression
            cast_.buildError(_cont.getAnalysisMessages().getBadImplicitCast(),
                    StringList.join(_stepEl.getResultClass().getNames(),"&"),
                    StringList.join(_elementClass.getNames(),"&"));
            _cont.addError(cast_);
            setReachableError(true);
            getErrorsBlock().add(cast_.getBuiltError());
        }
    }

    private void processVariableNames(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(classIndexNameOffset);
        page_.setOffset(0);
        importedClassIndexName = ResolvingImportTypes.resolveCorrectType(_cont,classIndexName);
        if (!PrimitiveTypeUtil.isIntOrderClass(new ClassArgumentMatching(importedClassIndexName), _cont)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(classIndexNameOffset);
            //classIndexName len
            cast_.buildError(_cont.getAnalysisMessages().getNotPrimitiveWrapper(),
                    importedClassIndexName);
            _cont.addError(cast_);
            setReachableError(true);
            getErrorsBlock().add(cast_.getBuiltError());
        }
        page_.setGlobalOffset(classNameOffset);
        page_.setOffset(0);
        importedClassName = ResolvingImportTypes.resolveCorrectType(_cont,className);
        String cl_ = importedClassName;
        ClassArgumentMatching elementClass_ = new ClassArgumentMatching(cl_);
        if (!PrimitiveTypeUtil.isIntOrderClass(elementClass_, _cont)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(classNameOffset);
            //className len
            cast_.buildError(_cont.getAnalysisMessages().getNotPrimitiveWrapper(),
                    importedClassName);
            _cont.addError(cast_);
            setReachableError(true);
            getErrorsBlock().add(cast_.getBuiltError());
        }
        page_.setGlobalOffset(variableNameOffset);
        page_.setOffset(0);
        if (_cont.getAnalyzing().containsVar(variableName)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(getFile().getFileName());
            d_.setIndexFile(variableNameOffset);
            //variable name len
            d_.buildError(_cont.getAnalysisMessages().getBadVariableName(),
                    variableName);
            _cont.addError(d_);
        }
        if (_cont.getAnalyzing().containsMutableLoopVar(variableName)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(getFile().getFileName());
            d_.setIndexFile(variableNameOffset);
            //variable name len
            d_.buildError(_cont.getAnalysisMessages().getBadVariableName(),
                    variableName);
            _cont.addError(d_);
        }
        if (!ContextUtil.isValidSingleToken(_cont,variableName)) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(getFile().getFileName());
            b_.setIndexFile(variableNameOffset);
            //variable name len
            b_.buildError(_cont.getAnalysisMessages().getBadVariableName(),
                    variableName);
            _cont.addError(b_);
            nameErrors.add(b_.getBuiltError());
        }
    }


    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        if (!_anEl.isReachable(this)) {
            _anEl.completeAbruptGroup(this);
        }
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

    public StringList getNameErrors() {
        return nameErrors;
    }
}
