package code.expressionlanguage.classes;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultLockingClass;
import code.expressionlanguage.Initializer;
import code.expressionlanguage.errors.AnalysisMessages;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;

public final class Ctx extends ContextEl {

    private Initializer init;

    public Ctx(int _stackOverFlow, DefaultLockingClass _lock,
                           Initializer _init, Options _options, AnalysisMessages _mess, KeyWords _keyWords, LgNames _stds, int _tabWidth) {
        super(false,_stackOverFlow, _lock, _options, _mess, _keyWords, _stds, _tabWidth);
        init = _init;
    }
    @Override
    public void initError() {
        setMemoryError(new ErrorStruct(this, getStandards().getAliasError()));
    }
    @Override
    public Initializer getInit() {
        return init;
    }

    @Override
    public String getCurrentFileName() {
        return "";
    }

    @Override
    public void addError(FoundErrorInterpret _error) {
        //
    }

}
