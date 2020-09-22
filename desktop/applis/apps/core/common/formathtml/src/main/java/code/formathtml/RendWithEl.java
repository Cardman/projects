package code.formathtml;

import code.expressionlanguage.analyze.files.OffsetsBlock;

public interface RendWithEl {

    void processEl(Configuration _cont);

    OffsetsBlock getOffset();
}
