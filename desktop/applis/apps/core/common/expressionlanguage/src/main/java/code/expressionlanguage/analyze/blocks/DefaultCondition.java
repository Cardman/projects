package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.exec.blocks.ExecDefaultCondition;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.exec.blocks.ExecInstanceCaseCondition;
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
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        if (checkDefault(_cont)){
            ExecDefaultCondition exec_ = new ExecDefaultCondition(getOffset());
            AnalyzedPageEl page_ = _cont.getAnalyzing();
            page_.getBlockToWrite().appendChild(exec_);
            page_.getAnalysisAss().getMappingMembers().put(exec_,this);
            page_.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
            _cont.getCoverage().putBlockOperations(_cont, exec_,this);
        }
    }

    private boolean checkDefault(ContextEl _cont) {
        BracedBlock b_ = getParent();
        if (!(b_ instanceof SwitchBlock)) {
            AnalyzedPageEl page_ = _cont.getAnalyzing();
            page_.setGlobalOffset(getOffset().getOffsetTrim());
            page_.setOffset(0);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            //key word len
            un_.buildError(_cont.getAnalysisMessages().getUnexpectedCaseDef(),
                    _cont.getKeyWords().getKeyWordDefault(),
                    "",
                    _cont.getKeyWords().getKeyWordSwitch());
            _cont.addError(un_);
            setReachableError(true);
            getErrorsBlock().add(un_.getBuiltError());
            return true;
        } else {
            _cont.getCoverage().putBlockOperationsSwitchs(_cont,b_,this);
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
                        un_.buildError(_cont.getAnalysisMessages().getUnexpectedDefDup(),
                                _cont.getKeyWords().getKeyWordDefault(),
                                _cont.getKeyWords().getKeyWordSwitch());
                        _cont.addError(un_);
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
                un_.buildError(_cont.getAnalysisMessages().getUnexpectedDefDup(),
                        _cont.getKeyWords().getKeyWordDefault(),
                        _cont.getKeyWords().getKeyWordSwitch());
                _cont.addError(un_);
                setReachableError(true);
                getErrorsBlock().add(un_.getBuiltError());
            }
            AnalyzedPageEl page_ = _cont.getAnalyzing();
            TokenErrorMessage res_ = ManageTokens.partVar(_cont).checkStdTokenVar(_cont,variableName);
            if (res_.isError()) {
                FoundErrorInterpret d_ = new FoundErrorInterpret();
                d_.setFileName(getFile().getFileName());
                d_.setIndexFile(variableOffset);
                //variable name
                d_.setBuiltError(res_.getMessage());
                _cont.addError(d_);
                nameErrors.add(d_.getBuiltError());
                if (variableName.trim().isEmpty()) {
                    setReachableError(true);
                    getErrorsBlock().add(d_.getBuiltError());
                }
                return true;
            }
            ExecInstanceDefaultCondition exec_ = new ExecInstanceDefaultCondition(getOffset(),variableName, instanceTest_,variableOffset);
            page_.getBlockToWrite().appendChild(exec_);
            page_.getAnalysisAss().getMappingMembers().put(exec_,this);
            page_.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
            _cont.getCoverage().putBlockOperations(_cont, exec_,this);
            AnaLocalVariable lv_ = new AnaLocalVariable();
            lv_.setClassName(instanceTest_);
            lv_.setRef(variableOffset);
            lv_.setConstType(ConstType.FIX_VAR);
            _cont.getAnalyzing().getInfosVars().put(variableName, lv_);
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
