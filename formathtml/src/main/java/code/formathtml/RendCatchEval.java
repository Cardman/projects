package code.formathtml;

import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.errors.custom.BadVariableName;
import code.expressionlanguage.errors.custom.DuplicateVariable;
import code.expressionlanguage.errors.custom.UnexpectedTagName;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.variables.LocalVariable;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.stacks.RendTryBlockStack;
import code.util.StringMap;

public final class RendCatchEval extends RendAbstractCatchEval {

    private final String className;

    private String importedClassName;

    private int classNameOffset;

    private final String variableName;

    private int variableNameOffset;
    RendCatchEval(OffsetStringInfo _className, OffsetStringInfo _variable, OffsetsBlock _offset) {
        super(_offset);
        className = _className.getInfo();
        classNameOffset = _className.getOffset();
        variableName = _variable.getInfo();
        variableNameOffset = _variable.getOffset();
    }

    public String getImportedClassName() {
        return importedClassName;
    }
    public String getVariableName() {
        return variableName;
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont,RendDocumentBlock _doc) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(variableNameOffset);
        page_.setOffset(0);
        if (_cont.getAnalyzing().containsCatchVar(variableName)) {
            DuplicateVariable d_ = new DuplicateVariable();
            d_.setId(variableName);
            d_.setFileName(_cont.getCurrentFileName());
            d_.setIndexFile(variableNameOffset);
            _cont.addError(d_);
            return;
        }
        if (!_cont.isValidSingleToken(variableName)) {
            BadVariableName b_ = new BadVariableName();
            b_.setFileName(_cont.getCurrentFileName());
            b_.setIndexFile(variableNameOffset);
            b_.setVarName(variableName);
            _cont.addError(b_);
        }
        page_.setGlobalOffset(classNameOffset);
        importedClassName = _cont.resolveCorrectType(className);
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(importedClassName);
        _cont.getAnalyzing().putCatchVar(variableName, lv_);
        RendBlock pBlock_ = getPreviousSibling();
        if (!(pBlock_ instanceof RendAbstractCatchEval)) {
            if (!(pBlock_ instanceof RendTryEval)) {
                if (!(pBlock_ instanceof RendPossibleEmpty)) {
                    UnexpectedTagName un_ = new UnexpectedTagName();
                    un_.setFileName(_cont.getCurrentFileName());
                    un_.setIndexFile(getOffset().getOffsetTrim());
                    _cont.addError(un_);
                } else if (!(pBlock_.getPreviousSibling() instanceof RendAbstractCatchEval)) {
                    if (!(pBlock_.getPreviousSibling() instanceof RendTryEval)) {
                        UnexpectedTagName un_ = new UnexpectedTagName();
                        un_.setFileName(_cont.getCurrentFileName());
                        un_.setIndexFile(getOffset().getOffsetTrim());
                        _cont.addError(un_);
                    }
                }
            }
        }
    }

    @Override
    public void removeAllVars(ImportingPage _ip) {
        super.removeAllVars(_ip);
        String var_ = getVariableName();
        _ip.getCatchVars().removeKey(var_);
    }

}
