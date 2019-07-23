package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.UnexpectedTagName;
import code.expressionlanguage.errors.custom.UnexpectedTypeError;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.util.StringList;

public final class RendClass extends RendParentBlock {
    private String name;
    private String fullName=EMPTY_STRING;
    RendClass(OffsetStringInfo _name, OffsetsBlock _offset) {
        super(_offset);
        name = _name.getInfo();
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc) {
        if (!(getParent() instanceof RendPackage)) {
            UnexpectedTagName un_ = new UnexpectedTagName();
            un_.setFileName(_cont.getCurrentFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            _cont.getClasses().addError(un_);
        } else {
            RendPackage par_ = (RendPackage) getParent();
            fullName = StringList.concat(par_.getName(),".",name);
            fullName = ContextEl.removeDottedSpaces(fullName);
            if (_cont.getClassBody(fullName) == null) {
                UnexpectedTypeError un_ = new UnexpectedTypeError();
                un_.setFileName(_cont.getCurrentFileName());
                un_.setIndexFile(getOffset().getOffsetTrim());
                un_.setType(fullName);
                _cont.getClasses().addError(un_);
            }
        }
    }

    public String getFullName() {
        return fullName;
    }
}
