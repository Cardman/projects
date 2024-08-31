package aiki.main;

import aiki.gui.WindowAiki;
import aiki.sml.LoadingGame;
import aiki.sml.MessagesPkGame;
import code.gui.LanguagesButtonsPair;
import code.gui.files.FileDialog;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.StreamFolderFile;
import code.util.StringList;
import code.util.StringMap;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class CreateMainWindowAiki implements Runnable {

    private final AbstractProgramInfos list;
    private final LoadingGame load;

    private final StringList withParam;

    private final AikiFactory aikiFactory;
    private final LanguagesButtonsPair pair;
    private final StringMap<String> videoBase;
    private final AbstractImage image;
    private WindowAiki window;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public CreateMainWindowAiki(LoadingGame _load, StringList _withParam, AbstractProgramInfos _list, AikiFactory _fact, LanguagesButtonsPair _p, StringMap<String> _mess, AbstractImage _icon) {
        load = _load;
        withParam = _withParam;
        list = _list;
        aikiFactory = _fact;
        pair = _p;
        videoBase = _mess;
        image = _icon;
    }

    @Override
    public void run() {
//        StringMap<String> builtMessages_ = MessagesInit.ms();
//        NavigationCore.adjust(builtMessages_);
//        StringMap<String> builtOther_ = CssInit.ms();
//        PreparedRenderedPages dataWeb_ = new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES, new DataGameInit(), PagesInit.build(), builtMessages_, builtOther_, new PkData(), list.getLanguages());
//        PreparedRenderedPages fight_ = new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES, new FightGameInit(), PagesInit.buildFight(), builtMessages_, builtOther_, new PkFight(), list.getLanguages());
//        PreparedRenderedPages pk_ = new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES, new DetPkGameInit(), PagesInit.buildInd(), builtMessages_, builtOther_, new PkInd(), list.getLanguages());
//        PreparedRenderedPages pkNet_ = new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES, new DetPkGameInit(), PagesInit.buildInd(), builtMessages_, builtOther_, new PkInd(), list.getLanguages());
//        PreparedRenderedPages diff_ = new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES, new DiffGameInit(), PagesInit.buildDiff(), builtMessages_, builtOther_, new PkDiff(), list.getLanguages());
//        PreparedRenderedPages prog_ = new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES, new ProgGameInit(), PagesInit.buildProg(), builtMessages_, builtOther_, new PkProg(), list.getLanguages());
        WindowAiki window_ = new WindowAiki(list,aikiFactory, pair, image);
        window_.getVideoBase().addAllEntries(videoBase);
//        window_.getDataWeb().setEnabled(false);
        StringMap<String> mes_ = MessagesPkGame.getAppliFilesTr(list.getTranslations()).val().getMapping();
        FileDialog.setLocation(window_.getCommonFrame(), FileDialog.loadCoords(WindowAiki.getTempFolder(list), mes_.getVal(MessagesPkGame.COORDS), list.getFileCoreStream(), list.getStreams()), list);
//        fight_.run();
//        pk_.run();
//        pkNet_.run();
//        diff_.run();
//        prog_.run();
        window_.pack();
        window_.setVisible(true);
        window_.setPreparedDataWebTask(aikiFactory.getTaskNavData());
        window_.setPreparedFightTask(aikiFactory.getTaskNavFight());
        window_.setPreparedPkTask(aikiFactory.getTaskNavPkTask());
        window_.setPreparedPkNetTask(aikiFactory.getTaskNavPkNetTask());
        window_.setPreparedDiffTask(aikiFactory.getTaskNavDiff());
        window_.setPreparedProgTask(aikiFactory.getTaskNavProg());
        if (!withParam.isEmpty()) {
            window_.getThreadFactory().newStartedThread(new CreateMainWindowParam(window_, load, StreamFolderFile.getCurrentPath(list.getFileCoreStream()), withParam));
        } else {
            window_.getThreadFactory().newStartedThread(new CreateMainWindowNoParam(window_, load, StreamFolderFile.getCurrentPath(list.getFileCoreStream())));
        }
        window = window_;
//        AbstractBaseExecutorService es_ = list.getThreadFactory().newExecutorService();
//        es_.submit(new DataWebInit(window_));
//        es_.shutdown();
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
    }

    public WindowAiki getWindow() {
        return window;
    }
}
