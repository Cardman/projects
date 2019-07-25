package code.formathtml;

import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.sml.Element;
import code.sml.MutableNode;
import code.util.StringList;

public final class RendRadio extends RendInput {
    RendRadio(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    @Override
    protected void processAttributes(Configuration _cont, RendDocumentBlock _doc, Element _read, StringList _all, StringList _list) {
//        _list.removeAllString(CHECKED);
        processAnaInput(_cont,_doc,_read);
    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read) {
        Element elt_ = (Element) _nextWrite;
        processIndexes(_cont,_read, elt_);
        String name_ = _read.getAttribute(ATTRIBUTE_NAME);
        if (name_.isEmpty()) {
            return;
        }
        Struct res_ = ElRenderUtil.calculateReuse(getOpsRead(), _cont).getStruct();
        if (res_ == NullStruct.NULL_VALUE) {
            elt_.removeAttribute(CHECKED);
        } else {
            String strObj_ = getStringKey(_cont, res_);
            if (_cont.getContext().getException() != null) {
                return;
            }
            if (StringList.quickEq(elt_.getAttribute(ATTRIBUTE_VALUE),strObj_)) {
                elt_.setAttribute(CHECKED, CHECKED);
            } else {
                elt_.removeAttribute(CHECKED);
            }
        }
    }

    @Override
    public void reduce(Configuration _context) {
        super.reduce(_context);
    }
}
