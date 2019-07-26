package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.opers.util.ClassField;
import code.formathtml.exec.*;
import code.formathtml.util.FieldUpdates;
import code.sml.Element;
import code.util.CustList;
import code.util.StringList;

public abstract class RendInput extends RendElement {
    private CustList<RendDynOperationNode> opsRead = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsValue = new CustList<RendDynOperationNode>();
    private CustList<RendDynOperationNode> opsWrite = new CustList<RendDynOperationNode>();
    private String varName = EMPTY_STRING;
    private ClassField idField;
    RendInput(Element _elt, OffsetsBlock _offset) {
        super(_elt, _offset);
    }

    protected void processAnaInput(Configuration _cont, RendDocumentBlock _doc, Element _read) {
        ResultInput r_ = new ResultInput();
        r_.build(_cont, _doc,_read,StringList.concat(_cont.getPrefix(),ATTRIBUTE_VAR_VALUE));
        opsRead = r_.getOpsRead();
        opsValue = r_.getOpsValue();
        opsWrite = r_.getOpsWrite();
        varName = r_.getVarName();
        idField = r_.getIdField();
    }

    @Override
    public void reduce(Configuration _context) {
        opsRead = reduceList(opsRead);
        opsValue = reduceList(opsValue);
        opsWrite = reduceList(opsWrite);
    }
    protected Argument processIndexes(Configuration _cont, Element _read, Element _write) {
        FieldUpdates f_ = new FieldUpdates();
        f_.setIdField(idField);
        f_.setOpsRead(opsRead);
        f_.setOpsWrite(opsWrite);
        f_.setOpsValue(opsValue);
        f_.setVarName(varName);
        Argument arg_ = fetchName(_cont, _read, _write, f_);
        fetchValue(_cont,_read,_write,opsValue);
        return arg_;
    }

    protected CustList<RendDynOperationNode> getOpsRead() {
        return opsRead;
    }
}
