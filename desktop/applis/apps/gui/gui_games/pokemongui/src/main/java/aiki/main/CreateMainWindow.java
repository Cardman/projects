package aiki.main;
import aiki.beans.*;
import aiki.gui.threads.PreparedRenderedPages;
import aiki.sml.LoadingGame;
import aiki.gui.MainWindow;
import aiki.sml.Resources;
import code.gui.CustComponent;
import code.gui.SoftApplicationCore;
import code.gui.TopLeftFrame;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.pages.aiki.CssInit;
import code.scripts.pages.aiki.MessagesInit;
import code.scripts.pages.aiki.PagesInit;
import code.sml.Document;
import code.util.StringMap;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class CreateMainWindow implements Runnable {

    private final AbstractProgramInfos list;
    private final LoadingGame load;

    private final StringMap<Object> withParam;

    private final String path;

    private final TopLeftFrame topLeft;

    private final String lg;

    private final AikiFactory aikiFactory;
    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public CreateMainWindow(LoadingGame _load, StringMap<Object> _withParam, String _path, TopLeftFrame _topLeft, String _lg, AbstractProgramInfos _list, AikiFactory _aikiFactory) {
        load = _load;
        withParam = _withParam;
        path = _path;
        topLeft = _topLeft;
        lg = _lg;
        list = _list;
        aikiFactory = _aikiFactory;
    }

    @Override
    public void run() {
        StringMap<Document> built_ = PagesInit.build();
        StringMap<String> builtMessages_ = MessagesInit.ms();
        StringMap<String> builtOther_ = CssInit.ms();
        PreparedRenderedPages dataWeb_ = new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES, new DataGameInit(), built_, builtMessages_, builtOther_);
        PreparedRenderedPages fight_ = new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES, new FightGameInit(), built_, builtMessages_, builtOther_);
        PreparedRenderedPages pk_ = new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES, new DetPkGameInit(), built_, builtMessages_, builtOther_);
        PreparedRenderedPages pkNet_ = new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES, new DetPkGameInit(), built_, builtMessages_, builtOther_);
        PreparedRenderedPages diff_ = new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES, new DiffGameInit(), built_, builtMessages_, builtOther_);
        PreparedRenderedPages prog_ = new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES, new ProgGameInit(), built_, builtMessages_, builtOther_);
        MainWindow window_ = new MainWindow(lg, list,aikiFactory);
        Thread dataWebThread_ = CustComponent.newThread(dataWeb_);
        dataWebThread_.start();
        Thread fightThread_ = CustComponent.newThread(fight_);
        fightThread_.start();
        Thread pkThread_ = CustComponent.newThread(pk_);
        pkThread_.start();
        Thread pkNetThread_ = CustComponent.newThread(pkNet_);
        pkNetThread_.start();
        Thread diffThread_ = CustComponent.newThread(diff_);
        diffThread_.start();
        Thread progThread_ = CustComponent.newThread(prog_);
        progThread_.start();
        window_.setPreparedDataWebThread(dataWebThread_);
        window_.setPreparedFightThread(fightThread_);
        window_.setPreparedPkThread(pkThread_);
        window_.setPreparedPkNetThread(pkNetThread_);
        window_.setPreparedDiffThread(diffThread_);
        window_.setPreparedProgThread(progThread_);
        window_.setPreparedDataWebTask(dataWeb_);
        window_.setPreparedFightTask(fight_);
        window_.setPreparedPkTask(pk_);
        window_.setPreparedPkNetTask(pkNet_);
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
