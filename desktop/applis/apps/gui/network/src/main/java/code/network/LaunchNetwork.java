package code.network;

import aiki.beans.DetPkGameInit;
import aiki.beans.PkInd;
import aiki.gui.threads.PreparedRenderedPages;
import aiki.main.AikiFactory;
import aiki.sml.Resources;
import cards.facade.CardGamesStream;
import cards.facade.IntArtCardGames;
import cards.gui.WindowCards;
import cards.gui.interfaces.ResultCardsServerInteractImpl;
import cards.main.CardFactories;
import code.gui.AbsButton;
import code.gui.EnabledMenu;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.pages.aiki.CssInit;
import code.scripts.pages.aiki.MessagesInit;
import code.scripts.pages.aiki.PagesInit;
import code.sml.NavigationCore;
import code.util.StringList;
import code.util.StringMap;

public final class LaunchNetwork implements Runnable {
    private final AbstractProgramInfos list;

    private final String language;

    private final AbsButton button;
    private final AikiFactory aikiFactory;
    private final CardFactories cardFactories;
    private final EnabledMenu languageMenu;
    public LaunchNetwork(String _language, AbstractProgramInfos _list, AbsButton _b, EnabledMenu _lgMenu, AikiFactory _a, CardFactories _c) {
        language = _language;
        list = _list;
        button = _b;
        languageMenu = _lgMenu;
        aikiFactory = _a;
        cardFactories = _c;
    }
    @Override
    public void run() {
        button.setEnabled(false);
        StringList lgs_ = list.getLanguages();
        StringMap<String> builtMessages_ = MessagesInit.ms();
        NavigationCore.adjust(builtMessages_);
        StringMap<String> builtOther_ = CssInit.ms();
        PreparedRenderedPages pkNet_ = new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES, new DetPkGameInit(), PagesInit.buildInd(), builtMessages_, builtOther_, new PkInd(), lgs_);
        pkNet_.run();
        WindowNetWork window_ = new WindowNetWork(new CardGamesStream(list, WindowCards.getTempFolderSl(list)),language, list, aikiFactory,languageMenu,new IntArtCardGames());
        window_.setPrepare(cardFactories.getTaskNav());
        window_.setButtonClick(button);
        window_.setPreparedPkNetTask(pkNet_);
        window_.setResultCardsServerInteract(new ResultCardsServerInteractImpl());
        window_.pack();
        window_.setVisible(true);
    }
}
