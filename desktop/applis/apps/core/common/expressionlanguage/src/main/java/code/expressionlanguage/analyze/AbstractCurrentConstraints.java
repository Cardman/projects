package code.expressionlanguage.analyze;

import code.util.StringList;
import code.util.StringMap;

public interface AbstractCurrentConstraints {
    StringMap<StringList> getCurrentConstraints();
}
