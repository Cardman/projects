package aiki.main;
import aiki.gui.threads.PreparedRenderedPages;
import aiki.sml.LoadingGame;
import aiki.gui.MainWindow;
import aiki.sml.Resources;
import code.gui.CustComponent;
import code.gui.SoftApplicationCore;
import code.gui.TopLeftFrame;
import code.util.StringMap;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class CreateMainWindow implements Runnable {

    private LoadingGame load;

    private StringMap<Object> withParam;

    private String path;

    private TopLeftFrame topLeft;

    private String lg;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public CreateMainWindow(LoadingGame _load, StringMap<Object> _withParam, String _path, TopLeftFrame _topLeft, String _lg) {
        load = _load;
        withParam = _withParam;
        path = _path;
        topLeft = _topLeft;
        lg = _lg;
    }

    @Override
    public void run() {
        PreparedRenderedPages dataWeb_ = new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES,Resources.ACCESS_TO_DEFAULT_DATA);
        PreparedRenderedPages fight_ = new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES,Resources.ACCESS_TO_DEFAULT_FIGHT);
        PreparedRenderedPages pk_ = new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES,Resources.ACCESS_TO_DEFAULT_PK);
        PreparedRenderedPages diff_ = new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES,Resources.ACCESS_TO_DEFAULT_DIFF);
        PreparedRenderedPages prog_ = new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES,Resources.ACCESS_TO_DEFAULT_PROG);
        MainWindow window_ = new MainWindow(lg);
        Thread dataWebThread_ = CustComponent.newThread(dataWeb_);
        dataWebThread_.start();
        Thread fightThread_ = CustComponent.newThread(fight_);
        fightThread_.start();
        Thread pkThread_ = CustComponent.newThread(pk_);
        pkThread_.start();
        Thread diffThread_ = CustComponent.newThread(diff_);
        diffThread_.start();
        Thread progThread_ = CustComponent.newThread(prog_);
        progThread_.start();
        window_.setPreparedDataWebThread(dataWebThread_);
        window_.setPreparedFightThread(fightThread_);
        window_.setPreparedPkThread(pkThread_);
        window_.setPreparedDiffThread(diffThread_);
        window_.setPreparedProgThread(progThread_);
        window_.setPreparedDataWebTask(dataWeb_);
        window_.setPreparedFightTask(fight_);
        window_.setPreparedPkTask(pk_);
        window_.setPreparedDiffTask(diff_);
        window_.setPreparedProgTask(prog_);
        SoftApplicationCore.setLocation(window_, topLeft);
        window_.pack();
        window_.setVisible(true);
        if (!withParam.isEmpty()) {
            CustComponent.newThread(new CreateMainWindowParam(window_, load, path, withParam)).start();
        } else {
            CustComponent.newThread(new CreateMainWindowNoParam(window_, load, path)).start();
        }
    }
}
