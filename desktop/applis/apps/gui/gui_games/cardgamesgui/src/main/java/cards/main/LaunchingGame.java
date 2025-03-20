package cards.main;

import cards.facade.CardGamesStream;
import cards.facade.IntArtCardGames;
import cards.facade.MessagesCardGames;
import cards.gui.WindowCards;
import code.gui.LanguagesButtonsPair;
import code.gui.files.FileDialog;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringList;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class LaunchingGame implements Runnable {

    private final AbstractProgramInfos list;
    private final StringList args;

    private final CardFactories taskLoadImgs;
    private final LanguagesButtonsPair pair;
    private final AbstractImage image;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public LaunchingGame(StringList _args, AbstractProgramInfos _list, CardFactories _imgs, LanguagesButtonsPair _p, AbstractImage _icon) {
        args = _args;
        list = _list;
        taskLoadImgs = _imgs;
        pair = _p;
        image = _icon;
    }

    @Override
    public void run() {
        WindowCards window_ = new WindowCards(new CardGamesStream(list,WindowCards.getTempFolderSl(list)), list,taskLoadImgs.getGeneralHelp(), new IntArtCardGames(), pair);
//        window_.setPrepare(taskLoadImgs.getTaskNav());
//        window_.setResultCardsServerInteract(new ResultCardsServerInteractImpl());
        FileDialog.setLocation(window_.getCommonFrame(), FileDialog.loadCoords(WindowCards.getTempFolder(list), MessagesCardGames.getAppliFilesTr(list.getTranslations()).val().getMapping().getVal(MessagesCardGames.COORDS), list.getStreams()), list);
        window_.setImageIconFrame(image);
        window_.pack();
        window_.setVisible(true);
//        HelpInitializer helpInitializerTask_ = new HelpInitializer(window_.getGeneralHelp(),list);
//        AbstractThread helpInitializerThread_ = window_.getThreadFactory().newThread(helpInitializerTask_);
//        helpInitializerThread_.start();
        window_.setHelpInitializerTask(taskLoadImgs.getHelpTask());
        window_.loadGameBegin(args);
    }

}
