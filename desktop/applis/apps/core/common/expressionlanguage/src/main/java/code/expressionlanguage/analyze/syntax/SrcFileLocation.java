package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.common.DisplayedStrings;

public interface SrcFileLocation {
    String getFileName();
    int getIndex();
    RowSrcLocation build(DisplayedStrings _dis);
}
