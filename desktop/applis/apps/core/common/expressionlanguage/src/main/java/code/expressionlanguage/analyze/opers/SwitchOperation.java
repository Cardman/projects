package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.analyze.opers.util.ParentInferring;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.analyze.variables.AnaLoopVariable;
import code.expressionlanguage.analyze.variables.AnaNamedLocalVariable;
import code.expressionlanguage.analyze.variables.AnaNamedLoopVariable;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.fwd.opers.AnaArrContent;
import code.expressionlanguage.options.KeyWords;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IntTreeMap;
import code.util.core.StringUtil;

public final class SwitchOperation extends AbstractUnaryOperation implements PreAnalyzableOperation,SettableElResult {
    private final SwitchMethodBlock switchMethod;
    private final String methodName;
    private final CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    private String retType = EMPTY_STRING;
    private final AnaArrContent arrContent;
    private int rootNumber = -1;
    private int operatorNumber = -1;
    private final int delta;
    public SwitchOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op, SwitchMethodBlock _switchMethod, int _delta) {
        super(_index, _indexChild, _m, _op);
        switchMethod = _switchMethod;
        methodName = getOperations().getFctName();
        arrContent = new AnaArrContent();
        delta = _delta;
    }
    @Override
    void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }
    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        _page.getAllSwitchMethods().add(this);
        switchMethod.setResult(new AnaClassArgumentMatching(""));
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _page);
        for (EntryCust<String, AnaLocalVariable> e: _page.getInfosVars().entryList()) {
            switchMethod.getCache().getLocalVariables().add(new AnaNamedLocalVariable(e.getKey(), e.getValue()));
        }
        for (AnaNamedLocalVariable e: _page.getCache().getLocalVariables()) {
            switchMethod.getCache().getLocalVariables().add(new AnaNamedLocalVariable(e.getName(), e.getLocalVariable()));
        }
        for (EntryCust<String, AnaLoopVariable> e: _page.getLoopsVars().entryList()) {
            switchMethod.getCache().getLoopVariables().add(new AnaNamedLoopVariable(e.getKey(),e.getValue()));
        }
        for (AnaNamedLoopVariable e: _page.getCache().getLoopVariables()) {
            switchMethod.getCache().getLoopVariables().add(new AnaNamedLoopVariable(e.getName(),e.getLocalVariable()));
        }
        ParentInferring par_ = ParentInferring.getParentInferring(this);
        OperationNode m_ = par_.getOperation();
        Block cur_ = _page.getCurrentBlock();
        if (m_ == null && cur_ instanceof ReturnMethod) {
            retType = InvokingOperation.tryGetRetType(_page);
        } else {
            retType = InvokingOperation.tryGetTypeAff(m_, par_.getOperationChild().getIndexChild());
        }
        KeyWords keyWords_ = _page.getKeyWords();
        String switchWord_ = keyWords_.getKeyWordSwitch();
        String afterSwitch_ = methodName.trim().substring(switchWord_.length());
        String suppType_ = "";
        int offDelta_ = StringUtil.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offDelta_, _page);
        if (afterSwitch_.trim().startsWith("[")) {
            int delta_ = StringExpUtil.getOffset(afterSwitch_) + 1;
            int start_ = afterSwitch_.indexOf('[') + 1;
            int k_ = start_;
            int len_ = afterSwitch_.length();
            int count_ = 1;
            while (k_ < len_) {
                char ch_ = afterSwitch_.charAt(k_);
                if (ch_ == '[') {
                    count_++;
                }
                if (ch_ == ']') {
                    count_--;
                    if (count_ == 0) {
                        suppType_ = afterSwitch_.substring(start_,k_);
                        delta_ += StringExpUtil.getOffset(suppType_);
                        k_ = len_;
                        continue;
                    }
                }
                k_++;
            }
            String res_ = ResolvingTypes.resolveCorrectType(switchWord_.length()+delta_,suppType_.trim(), _page);
            partOffsets.addAllElts(_page.getCurrentParts());
            retType = res_;
        }
        if (retType.isEmpty()) {
            retType = _page.getAliasObject();
        }
        switchMethod.setRetType(retType);
        MethodOperation parent_ = getParent();
        int indCh_ = getIndexChild();
        while (atMostOne(parent_)) {
            indCh_ = parent_.getIndexChild();
            parent_ = parent_.getParent();
        }
        if (isParentSetter(parent_)&&indCh_==0) {
            switchMethod.setRetRef(true);
        }
        RootBlock globalType_ = switchMethod.getParentType();
        OperatorBlock operator_ = switchMethod.getOperator();
        if (globalType_ != null) {
            rootNumber = globalType_.getNumberAll();
            switchMethod.getAllReservedInners().addAllElts(globalType_.getAllReservedInners());
        }
        if (operator_ != null) {
            operatorNumber = operator_.getNameNumber();
            switchMethod.getAllReservedInners().addAllElts(operator_.getAllReservedInners());
        }
        MemberCallingsBlock currentFct_ = _page.getCurrentFct();
        if (currentFct_ != null) {
            currentFct_.getSwitchMethods().add(switchMethod);
            switchMethod.getMappings().putAllMap(currentFct_.getMappings());
            switchMethod.getAllReservedInners().addAllElts(currentFct_.getMappings().getKeys());
        } else {
            if (globalType_ != null) {
                switchMethod.getMappings().putAllMap(globalType_.getMappings());
            }
            if (operator_ != null) {
                switchMethod.getMappings().putAllMap(operator_.getMappings());
            }
        }
        Block currentBlock_ = _page.getCurrentBlock();
        if (currentBlock_ instanceof InfoBlock) {
            ((InfoBlock)currentBlock_).getSwitchMethods().add(switchMethod);
        } else if (currentBlock_ instanceof MemberCallingsBlock) {
            ((MemberCallingsBlock)currentBlock_).getSwitchMethods().add(switchMethod);
        } else if (currentBlock_ instanceof RootBlock) {
            ((RootBlock)currentBlock_).getSwitchMethods().add(switchMethod);
        }
    }

    @Override
    public void analyzeUnary(AnalyzedPageEl _page) {
        String type_ = getFirstChild().getResultClass().getSingleNameOrEmpty();
        if (type_.isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(switchMethod.getFile().getFileName());
            un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //one char => change to first left par
            un_.buildError(_page.getAnalysisMessages().getUnknownType(),
                    type_);
            _page.addLocError(un_);
            addErr(un_.getBuiltError());
        } else {
            String id_ = StringExpUtil.getIdFromAllTypes(type_);
            AnaGeneType classBody_ = _page.getAnaGeneType(id_);
            boolean final_ = true;
            if (classBody_ != null) {
                final_ = ContextUtil.isHyperAbstract(classBody_);
            } else if (type_.startsWith("#")||type_.startsWith("[")) {
                final_ = false;
            }
            if (!AnaTypeUtil.isPrimitiveOrWrapper(id_, _page)) {
                if (!StringUtil.quickEq(id_, _page.getAliasString())) {
                    if (!(classBody_ instanceof EnumBlock)) {
                        if (!final_) {
                            switchMethod.setInstanceTest(type_);
                        } else {
                            FoundErrorInterpret un_ = new FoundErrorInterpret();
                            un_.setFileName(switchMethod.getFile().getFileName());
                            un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                            //one char => change to first left par
                            un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                                    id_);
                            _page.addLocError(un_);
                            addErr(un_.getBuiltError());
                        }
                    } else {
                        switchMethod.setEnumTest(true);
                    }
                }
            }
        }
        switchMethod.setResult(AnaClassArgumentMatching.copy(getFirstChild().getResultClass(), _page.getPrimitiveTypes()));
        setResultClass(new AnaClassArgumentMatching(retType));
        switchMethod.processAfterEl(_page);
    }

    public SwitchMethodBlock getSwitchMethod() {
        return switchMethod;
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }

    @Override
    public void setVariable(boolean _variable) {
        arrContent.setVariable(_variable);
    }

    @Override
    public void setCatenizeStrings() {
        arrContent.setCatString(true);
    }

    public AnaArrContent getArrContent() {
        return arrContent;
    }

    public int getRootNumber() {
        return rootNumber;
    }

    public int getOperatorNumber() {
        return operatorNumber;
    }

    public int getDelta() {
        return delta;
    }
}
