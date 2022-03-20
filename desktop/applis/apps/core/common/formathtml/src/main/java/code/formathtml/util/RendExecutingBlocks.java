package code.formathtml.util;

import code.formathtml.exec.blocks.RendDocumentBlock;
import code.util.StringMap;

public final class RendExecutingBlocks {

    private final StringMap<RendDocumentBlock> renders = new StringMap<RendDocumentBlock>();

    public StringMap<RendDocumentBlock> getRenders() {
        return renders;
    }


}
