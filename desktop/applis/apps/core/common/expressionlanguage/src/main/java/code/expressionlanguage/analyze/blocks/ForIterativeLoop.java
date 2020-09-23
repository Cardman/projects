package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetBooleanInfo;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.opers.Calculation;
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
    public ForIterativeLoop(OffsetStringInfo _className, OffsetStringInfo _variable,
                            OffsetStringInfo _from,
                            OffsetStringInfo _to, OffsetBooleanInfo _eq, OffsetStringInfo _step, OffsetStringInfo _classIndex, OffsetStringInfo _label, OffsetsBlock _offset, AnalyzedPageEl _page) {
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
            classIndex_ = _page.getStandards().getAliasPrimInteger();
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
        _page.setOffset(0);
        MethodAccessKind static_ = f_.getStaticContext();
        rootInit = ElUtil.getRootAnalyzedOperationsReadOnly(init, Calculation.staticCalculation(static_), _page);
//        rootInit = _page.getCurrentRoot();
//        ExecOperationNode initEl_ = init_.last();
        checkType(cl_, initOffset, rootInit, _page);
        _page.setGlobalOffset(expressionOffset);
        _page.setOffset(0);
        rootExp = ElUtil.getRootAnalyzedOperationsReadOnly(expression, Calculation.staticCalculation(static_), _page);
//        rootExp = _page.getCurrentRoot();
//        ExecOperationNode expressionEl_ = exp_.last();
        checkType(cl_, expressionOffset, rootExp, _page);
        _page.setGlobalOffset(stepOffset);
        _page.setOffset(0);
        rootStep = ElUtil.getRootAnalyzedOperationsReadOnly(step, Calculation.staticCalculation(static_), _page);
//        rootStep = _page.getCurrentRoot();
//        ExecOperationNode stepEl_ = step_.last();
        checkType(cl_, stepOffset, rootStep, _page);
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
        if (!AnaTemplates.isCorrectOrNumbers(m_, _page)) {
            ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(_elementClass, arg_, _page);
            if (res_.isFoundMethod()) {
                ClassMethodId cl_ = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                arg_.getImplicits().add(cl_);
                arg_.setRootNumber(res_.getRootNumber());
                arg_.setMemberNumber(res_.getMemberNumber());
            } else {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(getFile().getFileName());
                cast_.setIndexFile(_offset);
                //char before expression
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringList.join(arg_.getNames(),"&"),
                        _elementClass);
                _page.addLocError(cast_);
                setReachableError(true);
                getErrorsBlock().add(cast_.getBuiltError());
            }
        }
//        ElUtil.setImplicits(_stepEl, _page, _root);
    }

    private boolean processVariableNames(AnalyzedPageEl _page) {
        _page.setGlobalOffset(classIndexNameOffset);
        _page.setOffset(0);
        importedClassIndexName = ResolvingImportTypes.resolveCorrectType(classIndexName, _page);
        if (!AnaTypeUtil.isIntOrderClass(new AnaClassArgumentMatching(importedClassIndexName), _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(classIndexNameOffset);
            //classIndexName len
            cast_.buildError(_page.getAnalysisMessages().getNotPrimitiveWrapper(),
                    importedClassIndexName);
            _page.addLocError(cast_);
            setReachableError(true);
            getErrorsBlock().add(cast_.getBuiltError());
        }
        _page.setGlobalOffset(classNameOffset);
        _page.setOffset(0);
        importedClassName = ResolvingImportTypes.resolveCorrectType(className, _page);
        String cl_ = importedClassName;
        AnaClassArgumentMatching elementClass_ = new AnaClassArgumentMatching(cl_);
        if (!AnaTypeUtil.isIntOrderClass(elementClass_, _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(classNameOffset);
            //className len
            cast_.buildError(_page.getAnalysisMessages().getNotPrimitiveWrapper(),
                    importedClassName);
            _page.addLocError(cast_);
            setReachableError(true);
            getErrorsBlock().add(cast_.getBuiltError());
        }
        _page.setGlobalOffset(variableNameOffset);
        _page.setOffset(0);
        TokenErrorMessage res_ = ManageTokens.partVar(_page).checkTokenVar(variableName, _page);
        if (res_.isError()) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(getFile().getFileName());
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
