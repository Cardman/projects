package code.expressionlanguage.structs;

import code.expressionlanguage.analyze.AnalyzedPageEl;

public interface AnaDisplayableStruct extends Struct {

    StringStruct getDisplayedString(AnalyzedPageEl _an);
}
