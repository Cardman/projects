package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.common.DisplayedStrings;

public interface SrcFileLocation {
    String getFileName();
    int getIndex();
    FileBlock getFile();
    RowSrcLocation build(DisplayedStrings _dis);
}
