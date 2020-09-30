package code.expressionlanguage;

import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.coverage.Coverage;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;

public final class SingleContextEl extends ContextEl {

    public SingleContextEl(int _stackOverFlow, DefaultLockingClass _lock,
                           Initializer _init, Options _options, LgNames _stds, int _tabWidth, ClassesCommon _com) {
        super(new CommonExecutionInfos(_tabWidth, _stackOverFlow, _stds, new Classes(_com), new Coverage(_options.isCovering()), _lock, _init), InitPhase.READ_ONLY_OTHERS);
        setFullStack(new DefaultFullStack(this));
    }

}
