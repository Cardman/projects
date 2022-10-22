package aiki.main;

import aiki.beans.*;
import aiki.gui.WindowAiki;
import aiki.gui.threads.PreparedRenderedPages;
import aiki.sml.DefLoadingData;
import aiki.sml.LoadingGame;
import aiki.sml.Resources;
import code.formathtml.analyze.blocks.AnaRendBlock;
import code.gui.SoftApplicationCore;
import code.gui.TopLeftFrame;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.pages.aiki.CssInit;
import code.scripts.pages.aiki.MessagesInit;
import code.scripts.pages.aiki.PagesInit;
import code.util.StringList;
import code.util.StringMap;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class CreateMainWindowAiki implements Runnable {

    private final AbstractProgramInfos list;
    private final LoadingGame load;

    private final StringList withParam;

    private final String path;

    private final TopLeftFrame topLeft;

    private final String lg;

    private final AikiFactory aikiFactory;
    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public CreateMainWindowAiki(LoadingGame _load, StringList _withParam, String _path, TopLeftFrame _topLeft, String _lg, AbstractProgramInfos _list, AikiFactory _aikiFactory) {
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
        StringMap<String> builtMessages_ = MessagesInit.ms();
        AnaRendBlock.adjust(builtMessages_);
        StringMap<String> builtOther_ = CssInit.ms();
        PreparedRenderedPages dataWeb_ = new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES, new DataGameInit(), PagesInit.build(), builtMessages_, builtOther_, new PkData());
        PreparedRenderedPages fight_ = new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES, new FightGameInit(), PagesInit.buildFight(), builtMessages_, builtOther_, new PkFight());
        PreparedRenderedPages pk_ = new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES, new DetPkGameInit(), PagesInit.buildInd(), builtMessages_, builtOther_, new PkInd());
        PreparedRenderedPages pkNet_ = new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES, new DetPkGameInit(), PagesInit.buildInd(), builtMessages_, builtOther_, new PkInd());
        PreparedRenderedPages diff_ = new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES, new DiffGameInit(), PagesInit.buildDiff(), builtMessages_, builtOther_, new PkDiff());
        PreparedRenderedPages prog_ = new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES, new ProgGameInit(), PagesInit.buildProg(), builtMessages_, builtOther_, new PkProg());
        WindowAiki window_ = new WindowAiki(lg, list,aikiFactory);
        dataWeb_.run();
        fight_.run();
        pk_.run();
        pkNet_.run();
        diff_.run();
        prog_.run();
//        AbstractThread dataWebThread_ = window_.getThreadFactory().newThread(dataWeb_);
//        dataWebThread_.start();
//        AbstractThread fightThread_ = window_.getThreadFactory().newThread(fight_);
//        fightThread_.start();
//        AbstractThread pkThread_ = window_.getThreadFactory().newThread(pk_);
//        pkThread_.start();
//        AbstractThread pkNetThread_ = window_.getThreadFactory().newThread(pkNet_);
//        pkNetThread_.start();
//        AbstractThread diffThread_ = window_.getThreadFactory().newThread(diff_);
//        diffThread_.start();
//        AbstractThread progThread_ = window_.getThreadFactory().newThread(prog_);
//        progThread_.start();
//        window_.setPreparedDataWebThread(dataWebThread_);
//        window_.setPreparedFightThread(fightThread_);
//        window_.setPreparedPkThread(pkThread_);
//        window_.setPreparedPkNetThread(pkNetThread_);
//        window_.setPreparedDiffThread(diffThread_);
//        window_.setPreparedProgThread(progThread_);
        window_.setPreparedDataWebTask(dataWeb_);
        window_.setPreparedFightTask(fight_);
        window_.setPreparedPkTask(pk_);
        window_.setPreparedPkNetTask(pkNet_);
        window_.setPreparedDiffTask(diff_);
        window_.setPreparedProgTask(prog_);
        SoftApplicationCore.setLocation(window_.getCommonFrame(), topLeft);
        window_.pack();
        window_.setVisible(true);
        if (!withParam.isEmpty()) {
            window_.getThreadFactory().newStartedThread(new CreateMainWindowParam(window_, load, path, withParam, new DefLoadingData(window_.getFacade().getLanguages(),window_.getFacade().getDisplayLanguages(), window_.getFacade().getSexList())));
        } else {
            window_.getThreadFactory().newStartedThread(new CreateMainWindowNoParam(window_, load, path, new DefLoadingData(window_.getFacade().getLanguages(),window_.getFacade().getDisplayLanguages(), window_.getFacade().getSexList())));
        }
    }

}
