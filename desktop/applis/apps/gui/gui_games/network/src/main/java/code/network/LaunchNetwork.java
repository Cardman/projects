package code.network;

import aiki.main.AikiFactory;
import cards.facade.CardGamesStream;
import cards.facade.IntArtCardGames;
import cards.gui.WindowCards;
import code.gui.LanguagesButtonsPair;
import code.gui.initialize.AbstractProgramInfos;

public final class LaunchNetwork implements Runnable {
    private final AbstractProgramInfos list;

    private final AikiFactory aikiFactory;
    private final LanguagesButtonsPair pair;
    private WindowNetWork window;

    public LaunchNetwork(AbstractProgramInfos _list, AikiFactory _a, LanguagesButtonsPair _p) {
        list = _list;
        aikiFactory = _a;
        pair = _p;
    }
    @Override
    public void run() {
//        button.setEnabled(false);
//        StringList lgs_ = list.getLanguages();
//        StringMap<String> builtMessages_ = MessagesInit.ms();
//        NavigationCore.adjust(builtMessages_);
//        StringMap<String> builtOther_ = CssInit.ms();
//        PreparedRenderedPages pkNet_ = new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES, new DetPkGameInit(), PagesInit.buildInd(), builtMessages_, builtOther_, new PkInd(), lgs_);
//        pkNet_.run();
        window = new WindowNetWork(new CardGamesStream(list, WindowCards.getTempFolderSl(list)), list, aikiFactory, new IntArtCardGames(), pair);
        WindowNetWork window_ = window;
//        window_.setPrepare(cardFactories.getTaskNav());
//        window_.setButtonClick(button);
//        window_.setResultCardsServerInteract(new ResultCardsServerInteractImpl());
        window_.pack();
        window_.setVisible(true);
    }

    public WindowNetWork getWindow() {
        return window;
    }
}
