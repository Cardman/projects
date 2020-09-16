package code.expressionlanguage;

import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.DefaultLockingClass;
import code.expressionlanguage.exec.Initializer;
import code.expressionlanguage.exec.coverage.Coverage;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;

public final class SingleContextEl extends ContextEl {

    public SingleContextEl(int _stackOverFlow, DefaultLockingClass _lock,
                           Initializer _init, Options _options, KeyWords _keyWords, LgNames _stds, int _tabWidth) {
        super(new CommonExecutionInfos(_tabWidth, _stackOverFlow, _stds, new Classes(), new Coverage(_options.isCovering()), _lock, _init));
        setFullStack(new DefaultFullStack(this));
    }

}
