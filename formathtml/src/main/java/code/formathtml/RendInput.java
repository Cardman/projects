package code.formathtml;

import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.opers.util.ClassField;
import code.formathtml.exec.*;
import code.sml.Element;
import code.util.CustList;

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
        r_.build(_cont, _doc,_read);
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
    private static CustList<RendDynOperationNode> reduceList(CustList<RendDynOperationNode> _list) {
        if (_list.isEmpty()) {
            return _list;
        }
        return ElRenderUtil.getReducedNodes(_list.last());
    }
    protected void processIndexes(Configuration _cont, Element _read, Element _write) {
        fetchName(_cont, _read, _write, opsRead, idField, varName, opsWrite);
        fetchValue(_cont,_read,_write,opsValue);
    }

    protected CustList<RendDynOperationNode> getOpsRead() {
        return opsRead;
    }
}
