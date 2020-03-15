package code.formathtml;

import code.expressionlanguage.errors.custom.UnexpectedTagName;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;

public final class RendImportForm extends RendParentBlock {
    private String name;
    RendImportForm(OffsetStringInfo _name, OffsetsBlock _offset) {
        super(_offset);
        name = _name.getInfo();
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc) {
        if (!(getParent() instanceof RendImport)) {
            UnexpectedTagName un_ = new UnexpectedTagName();
            un_.setFileName(_cont.getCurrentFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            _cont.addError(un_);
        }
    }

    public String getName() {
        return name;
    }
}
