package cards.main;

import cards.facade.CardGamesStream;
import cards.facade.IntArtCardGames;
import cards.gui.WindowCards;
import code.gui.TopLeftFrame;
import code.gui.files.FileDialog;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringList;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class LaunchingGame implements Runnable {

    private final AbstractProgramInfos list;
    private final StringList args;

    private final String language;

    private final TopLeftFrame topLeft;
    private final CardFactories taskLoadImgs;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public LaunchingGame(StringList _args, String _language, TopLeftFrame _topLeft, AbstractProgramInfos _list, CardFactories _imgs) {
        args = _args;
        language = _language;
        topLeft = _topLeft;
        list = _list;
        taskLoadImgs = _imgs;
    }

    @Override
    public void run() {
        WindowCards window_ = new WindowCards(new CardGamesStream(list,WindowCards.getTempFolderSl(list)),language, list,taskLoadImgs.getGeneralHelp(),new IntArtCardGames());
        window_.setPrepare(taskLoadImgs.getTaskNav());
//        window_.setResultCardsServerInteract(new ResultCardsServerInteractImpl());
        FileDialog.setLocation(window_.getCommonFrame(), topLeft);
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
