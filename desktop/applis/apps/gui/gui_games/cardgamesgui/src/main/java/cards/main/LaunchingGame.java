package cards.main;

import cards.facade.CardGamesStream;
import cards.facade.IntArtCardGames;
import cards.gui.WindowCards;
import cards.gui.dialogs.FrameGeneralHelp;
import code.gui.LanguagesButtonsPair;
import code.gui.files.FileDialog;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringList;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class LaunchingGame implements Runnable {

    private final AbstractProgramInfos list;
    private final StringList args;

    private final CardFactories taskLoadImgs;
    private final LanguagesButtonsPair pair;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public LaunchingGame(StringList _args, AbstractProgramInfos _list, CardFactories _imgs, LanguagesButtonsPair _p) {
        args = _args;
        list = _list;
        taskLoadImgs = _imgs;
        pair = _p;
    }

    @Override
    public void run() {
        WindowCards window_ = new WindowCards(new CardGamesStream(list,WindowCards.getTempFolderSl(list)), list,taskLoadImgs.getGeneralHelp(), new IntArtCardGames(), pair);
        window_.setPrepare(taskLoadImgs.getTaskNav());
//        window_.setResultCardsServerInteract(new ResultCardsServerInteractImpl());
        FileDialog.setLocation(window_.getCommonFrame(), FileDialog.loadCoords(WindowCards.getTempFolder(list), FrameGeneralHelp.COORDS, list.getFileCoreStream(), list.getStreams()));
        window_.setImageIconFrame(WindowCards.getIcon(window_.getImageFactory()));
        window_.pack();
        window_.setVisible(true);
//        HelpInitializer helpInitializerTask_ = new HelpInitializer(window_.getGeneralHelp(),list);
//        AbstractThread helpInitializerThread_ = window_.getThreadFactory().newThread(helpInitializerTask_);
//        helpInitializerThread_.start();
        window_.setHelpInitializerTask(taskLoadImgs.getHelpTask());
        window_.loadGameBegin(args);
    }

}
