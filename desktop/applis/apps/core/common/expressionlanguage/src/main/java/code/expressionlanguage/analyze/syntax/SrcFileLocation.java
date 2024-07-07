package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.common.DisplayedStrings;

public interface SrcFileLocation {
    FileBlockCursor cursor();

    boolean match(SrcFileLocation _o);

    RowSrcLocation build(DisplayedStrings _dis);
}
