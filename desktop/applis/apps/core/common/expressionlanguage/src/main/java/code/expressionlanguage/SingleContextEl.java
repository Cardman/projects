package code.expressionlanguage;

import code.expressionlanguage.exec.DefaultLockingClass;
import code.expressionlanguage.exec.Initializer;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;

public final class SingleContextEl extends ContextEl {

    private Initializer init;

    public SingleContextEl(int _stackOverFlow, DefaultLockingClass _lock,
                           Initializer _init, Options _options, KeyWords _keyWords, LgNames _stds, int _tabWidth) {
        super(_stackOverFlow, _lock, _options, _stds, _tabWidth);
        init = _init;
    }

    @Override
    public Initializer getInit() {
        return init;
    }
}
