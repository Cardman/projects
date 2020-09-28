package code.formathtml.exec.blocks;

import code.formathtml.Configuration;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.sml.Element;
import code.sml.MutableNode;
import code.util.CustList;
import code.util.StringMap;

public final class RendStdInput extends RendInput {

    public RendStdInput(int _offsetTrim, Element read, StringMap<ExecTextPart> execAttributes, StringMap<ExecTextPart> execAttributesText, CustList<RendDynOperationNode> opsRead, CustList<RendDynOperationNode> opsValue, CustList<RendDynOperationNode> opsWrite, CustList<RendDynOperationNode> opsConverter, CustList<RendDynOperationNode> opsConverterField, String varName, String varNameConverter, String varNameConverterField, String id, String idClass, String idName, String className) {
        super(_offsetTrim, read, execAttributes, execAttributesText, opsRead, opsValue, opsWrite, opsConverter, opsConverterField, varName, varNameConverter, varNameConverterField, id, idClass, idName, className);
    }

    @Override
    protected void processExecAttr(Configuration _cont, MutableNode _nextWrite, Element _read) {
        processIndexes(_cont,_read, (Element) _nextWrite);
    }
}
