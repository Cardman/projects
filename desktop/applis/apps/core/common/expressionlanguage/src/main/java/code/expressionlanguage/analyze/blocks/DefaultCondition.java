package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.util.StringList;

public final class DefaultCondition extends SwitchPartBlock {
    private String variableName = "";

    private final StringList nameErrors = new StringList();
    private int variableOffset;
    private boolean tole;
    private String instanceTest = "";

    public DefaultCondition(int _offset) {
        super(_offset);
    }

    public DefaultCondition(int _offset, OffsetStringInfo _variable, boolean _tolerate) {
        super(_offset);
        variableName = _variable.getInfo();
        variableOffset = _variable.getOffset();
        tole = _tolerate;
    }
    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        checkDefault(_page);
    }

    private void checkDefault(AnalyzedPageEl _page) {
        BracedBlock b_ = getParent();
        if (!(b_ instanceof SwitchBlock)&&!(b_ instanceof SwitchMethodBlock)) {
            _page.setSumOffset(getOffset());
            _page.zeroOffset();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(getFile());
            un_.setIndexFile(getOffset());
            //key word len
            un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDef(),
                    _page.getKeyWords().getKeyWordDefault(),
                    "",
                    _page.getKeyWords().getKeyWordSwitch());
            _page.addLocError(un_);
            addErrorBlock(un_.getBuiltError());
            return;
        }
        boolean instance_;
        boolean forceInstance_;
        String instanceTest_;
        if (b_ instanceof SwitchBlock) {
            SwitchBlock s_ = (SwitchBlock) b_;
            setSwitchParent(s_);
            instanceTest_ = s_.getInstanceTest();
            instance_ = s_.isInstance();
            forceInstance_=s_.isForceInstance();
        } else {
            SwitchMethodBlock s_ = (SwitchMethodBlock) b_;
            setSwitchMethod(s_);
            instanceTest_ = s_.getInstanceTest();
            instance_ = s_.isInstance();
            forceInstance_=s_.isForceInstance();
        }
        if (!instance_) {
            AbsBk first_ = b_.getFirstChild();
            while (first_ != this) {
                if (first_ instanceof DefaultCondition) {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFile(getFile());
                    un_.setIndexFile(getOffset());
                    //key word len
                    un_.buildError(_page.getAnalysisMessages().getUnexpectedDefDup(),
                            _page.getKeyWords().getKeyWordDefault(),
                            _page.getKeyWords().getKeyWordSwitch());
                    _page.addLocError(un_);
                    addErrorBlock(un_.getBuiltError());
                    break;
                }
                first_ = first_.getNextSibling();
            }
            return;
        }
        instanceTest = instanceTest_;
        if (getNextSibling() != null){
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(getFile());
            un_.setIndexFile(getOffset());
            //key word len
            un_.buildError(_page.getAnalysisMessages().getUnexpectedDefDup(),
                    _page.getKeyWords().getKeyWordDefault(),
                    _page.getKeyWords().getKeyWordSwitch());
            _page.addLocError(un_);
            addErrorBlock(un_.getBuiltError());
        }
        if (acceptEmpty(forceInstance_)) {
            return;
        }
        TokenErrorMessage res_ = ManageTokens.partVar(_page).checkTokenVar(variableName, _page);
        if (res_.isError()) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFile(getFile());
            d_.setIndexFile(variableOffset);
            //variable name
            d_.setBuiltError(res_.getMessage());
            _page.addLocError(d_);
            nameErrors.add(d_.getBuiltError());
            if (variableName.trim().isEmpty()) {
                addErrorBlock(d_.getBuiltError());
            }
            return;
        }
        AnaLocalVariable lv_ = new AnaLocalVariable();
        lv_.setClassName(instanceTest_);
        lv_.setRef(variableOffset);
        lv_.setConstType(ConstType.FIX_VAR);
        lv_.setFinalVariable(true);
        _page.getInfosVars().put(variableName, lv_);
    }

    private boolean acceptEmpty(boolean _force) {
        return variableName.trim().isEmpty() && (_force||tole);
    }

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        if (!variableName.isEmpty()) {
            _ip.getInfosVars().removeKey(variableName);
        }
    }

    public int getVariableOffset() {
        return variableOffset;
    }

    public String getVariableName() {
        return variableName;
    }

    public String getInstanceTest() {
        return instanceTest;
    }

    public StringList getNameErrors() {
        return nameErrors;
    }
}
