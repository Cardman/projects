package code.expressionlanguage.gui.unit;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ProgressingTests;
import code.expressionlanguage.RunnableContextEl;
import code.expressionlanguage.structs.Struct;

public final class ProgressingTestsImpl implements ProgressingTests {
    private MainWindow mainWindow;

    public ProgressingTestsImpl(MainWindow _mainWindow) {
        mainWindow = _mainWindow;
    }

    @Override
    public void updateInfos(RunnableContextEl _ctx, Struct _infos, Struct _doneTests, Struct _method, Struct _count) {
        mainWindow.showProgress(_ctx,_infos,_doneTests,_method,_count);
    }

    @Override
    public void finish(RunnableContextEl _ctx, Struct _infos) {
        mainWindow.finish(_ctx,_infos);
    }

    @Override
    public void setResults(RunnableContextEl _ctx, Argument _res) {
        mainWindow.setResults(_ctx,_res);
    }
}
