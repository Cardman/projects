package code.expressionlanguage.gui.unit;

import code.expressionlanguage.ProgressingTests;
import code.expressionlanguage.RunnableContextEl;
import code.expressionlanguage.structs.Struct;

public final class ProgressingTestsImpl implements ProgressingTests {
    private MainWindow mainWindow;

    public ProgressingTestsImpl(MainWindow _mainWindow) {
        mainWindow = _mainWindow;
    }

    @Override
    public void updateInfos(RunnableContextEl _ctx, Struct _infos, Struct _doneTests, Struct _method) {
        mainWindow.showProgress(_ctx,_infos,_doneTests,_method);
    }
}
