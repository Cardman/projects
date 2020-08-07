package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.stacks.RendSwitchBlockStack;

public final class RendDefaultCondition extends RendSwitchPartCondition implements RendBuildableElMethod {

    private final int variableOffset;
    RendDefaultCondition(OffsetStringInfo _variableName, OffsetsBlock _offset) {
        super(_offset);
        setVariableName(_variableName.getInfo());
        variableOffset = _variableName.getOffset();
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont,RendDocumentBlock _doc) {
        RendParentBlock b_ = getParent();
        if (!(b_ instanceof RendSwitchBlock)) {
            AnalyzedPageEl page_ = _cont.getAnalyzing();
            page_.setGlobalOffset(getOffset().getOffsetTrim());
            page_.setOffset(0);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_cont.getCurrentFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            un_.buildError(_cont.getContext().getAnalysisMessages().getUnexpectedCaseDef(),
                    _cont.getKeyWords().getKeyWordDefault(),
                    EMPTY_STRING,
                    _cont.getKeyWords().getKeyWordSwitch());
            _cont.addError(un_);
        } else {
            RendSwitchBlock s_ = (RendSwitchBlock) b_;
            String instanceTest_ = s_.getInstanceTest();
            if (instanceTest_.isEmpty()) {
                RendBlock first_ = b_.getFirstChild();
                while (first_ != this) {
                    if (first_ instanceof RendDefaultCondition) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(_cont.getCurrentFileName());
                        un_.setIndexFile(getOffset().getOffsetTrim());
                        //key word len
                        un_.buildError(_cont.getContext().getAnalysisMessages().getUnexpectedDefDup(),
                                _cont.getKeyWords().getKeyWordDefault(),
                                _cont.getKeyWords().getKeyWordSwitch());
                        _cont.addError(un_);
                        break;
                    }
                    first_ = first_.getNextSibling();
                }
                return;
            }
            setImportedClassName(instanceTest_);
            if (getNextSibling() != null){
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_cont.getCurrentFileName());
                un_.setIndexFile(getOffset().getOffsetTrim());
                //key word len
                un_.buildError(_cont.getContext().getAnalysisMessages().getUnexpectedDefDup(),
                        _cont.getKeyWords().getKeyWordDefault(),
                        _cont.getKeyWords().getKeyWordSwitch());
                _cont.addError(un_);
            }
            TokenErrorMessage res_ = ManageTokens.partVar(_cont.getContext()).checkTokenVar(_cont.getContext(), getVariableName());
            if (res_.isError()) {
                FoundErrorInterpret d_ = new FoundErrorInterpret();
                d_.setFileName(_cont.getCurrentFileName());
                d_.setIndexFile(variableOffset);
                //variable name
                d_.setBuiltError(res_.getMessage());
                _cont.addError(d_);
                return;
            }
            AnaLocalVariable lv_ = new AnaLocalVariable();
            lv_.setClassName(instanceTest_);
            lv_.setConstType(ConstType.FIX_VAR);
            _cont.getAnalyzing().getInfosVars().put(getVariableName(), lv_);
        }
    }

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        if (!getVariableName().isEmpty()) {
            _ip.getInfosVars().removeKey(getVariableName());
        }
    }

    @Override
    public void removeAllVars(ImportingPage _ip) {
        super.removeAllVars(_ip);
        if (!getVariableName().isEmpty()) {
            _ip.getValueVars().removeKey(getVariableName());
        }
    }
    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        rw_.setRead(getFirstChild());
        ip_.getRendLastStack().setCurrentVisitedBlock(this);
    }

    @Override
    public void exitStack(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendSwitchBlockStack if_ = (RendSwitchBlockStack) ip_.getRendLastStack();
        if (if_.getLastVisitedBlock() == this) {
            rw_.setRead(if_.getBlock());
        } else {
            rw_.setRead(getNextSibling());
        }
    }
}
