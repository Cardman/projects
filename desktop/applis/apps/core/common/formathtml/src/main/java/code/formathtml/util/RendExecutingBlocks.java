package code.formathtml.util;

import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.blocks.RendDocumentBlock;
import code.util.StringMap;

public final class RendExecutingBlocks {

    private final StringMap<RendDocumentBlock> renders = new StringMap<RendDocumentBlock>();
    private final StringMap<Struct> builtBeans = new StringMap<Struct>();

    private final StringMap<Struct> builtValidators = new StringMap<Struct>();
    private RendDocumentBlock rendDocumentBlock;
    public StringMap<RendDocumentBlock> getRenders() {
        return renders;
    }

    public StringMap<Struct> getBuiltBeans() {
        return builtBeans;
    }

    public StringMap<Struct> getBuiltValidators() {
        return builtValidators;
    }

    public RendDocumentBlock getRendDocumentBlock() {
        return rendDocumentBlock;
    }

    public void setRendDocumentBlock(RendDocumentBlock _val) {
        this.rendDocumentBlock = _val;
    }
}
