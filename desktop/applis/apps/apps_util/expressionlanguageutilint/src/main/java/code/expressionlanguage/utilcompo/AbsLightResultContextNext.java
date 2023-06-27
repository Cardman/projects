package code.expressionlanguage.utilcompo;

import code.expressionlanguage.exec.AbsStackStopper;
import code.expressionlanguage.options.ResultContext;
import code.util.StringMap;

public interface AbsLightResultContextNext {

    ResultContext next(ResultContext _r, StringMap<String> _files, AbsStackStopper _s);
}
