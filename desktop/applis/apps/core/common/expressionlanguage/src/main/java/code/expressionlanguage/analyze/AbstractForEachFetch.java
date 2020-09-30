package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.util.IterableAnalysisResult;
import code.util.StringList;

public interface AbstractForEachFetch {
    IterableAnalysisResult getCustomType(StringList _names, String _first);
    IterableAnalysisResult getCustomTableType(StringList _names, String _first, String _second);
}
