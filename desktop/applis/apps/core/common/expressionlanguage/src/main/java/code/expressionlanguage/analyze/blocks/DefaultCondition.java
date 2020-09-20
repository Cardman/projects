package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.exec.blocks.ExecDefaultCondition;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.exec.blocks.ExecInstanceDefaultCondition;
import code.expressionlanguage.files.OffsetsBlock;
import code.util.StringList;

public final class DefaultCondition extends SwitchPartBlock {
    private String variableName = "";

    private final StringList nameErrors = new StringList();
    private int variableOffset;
    private String instanceTest;

    public DefaultCondition(OffsetsBlock _offset) {
        super(_offset);
    }

    public DefaultCondition(OffsetsBlock _offset, String _variableName, int _variableOffset) {
        super(_offset);
        variableName = _variableName;
        variableOffset = _variableOffset;
    }
    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        if (checkDefault(_page)){
            ExecDefaultCondition exec_ = new ExecDefaultCondition(getOffset());
            exec_.setFile(_page.getBlockToWrite().getFile());
            _page.getBlockToWrite().appendChild(exec_);
            _page.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
            _page.getCoverage().putBlockOperations(exec_,this);
        }
    }

    private boolean checkDefault(AnalyzedPageEl _page) {
        BracedBlock b_ = getParent();
        if (!(b_ instanceof SwitchBlock)) {
            _page.setGlobalOffset(getOffset().getOffsetTrim());
            _page.setOffset(0);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            //key word len
            un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDef(),
                    _page.getKeyWords().getKeyWordDefault(),
                    "",
                    _page.getKeyWords().getKeyWordSwitch());
            _page.addLocError(un_);
            setReachableError(true);
            getErrorsBlock().add(un_.getBuiltError());
            return true;
        } else {
            _page.getCoverage().putBlockOperationsSwitchs(b_,this);
            SwitchBlock s_ = (SwitchBlock) b_;
            String instanceTest_ = s_.getInstanceTest();
            if (instanceTest_.isEmpty()) {
                Block first_ = b_.getFirstChild();
                while (first_ != this) {
                    if (first_ instanceof DefaultCondition) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(getFile().getFileName());
                        un_.setIndexFile(getOffset().getOffsetTrim());
                        //key word len
                        un_.buildError(_page.getAnalysisMessages().getUnexpectedDefDup(),
                                _page.getKeyWords().getKeyWordDefault(),
                                _page.getKeyWords().getKeyWordSwitch());
                        _page.addLocError(un_);
                        setReachableError(true);
                        getErrorsBlock().add(un_.getBuiltError());
                        break;
                    }
                    first_ = first_.getNextSibling();
                }
                return true;
            }
            instanceTest = instanceTest_;
            if (getNextSibling() != null){
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(getFile().getFileName());
                un_.setIndexFile(getOffset().getOffsetTrim());
                //key word len
                un_.buildError(_page.getAnalysisMessages().getUnexpectedDefDup(),
                        _page.getKeyWords().getKeyWordDefault(),
                        _page.getKeyWords().getKeyWordSwitch());
                _page.addLocError(un_);
                setReachableError(true);
                getErrorsBlock().add(un_.getBuiltError());
            }
            TokenErrorMessage res_ = ManageTokens.partVar(_page).checkTokenVar(variableName, _page);
            if (res_.isError()) {
                FoundErrorInterpret d_ = new FoundErrorInterpret();
                d_.setFileName(getFile().getFileName());
                d_.setIndexFile(variableOffset);
                //variable name
                d_.setBuiltError(res_.getMessage());
                _page.addLocError(d_);
                nameErrors.add(d_.getBuiltError());
                if (variableName.trim().isEmpty()) {
                    setReachableError(true);
                    getErrorsBlock().add(d_.getBuiltError());
                }
                return true;
            }
            ExecInstanceDefaultCondition exec_ = new ExecInstanceDefaultCondition(getOffset(),variableName, instanceTest_,variableOffset);
            exec_.setFile(_page.getBlockToWrite().getFile());
            _page.getBlockToWrite().appendChild(exec_);
            _page.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
            _page.getCoverage().putBlockOperations(exec_,this);
            AnaLocalVariable lv_ = new AnaLocalVariable();
            lv_.setClassName(instanceTest_);
            lv_.setRef(variableOffset);
            lv_.setConstType(ConstType.FIX_VAR);
            _page.getInfosVars().put(variableName, lv_);
            return false;
        }
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
