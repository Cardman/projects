package code.network;

import aiki.beans.DetPkGameInit;
import aiki.beans.PkInd;
import aiki.gui.threads.PreparedRenderedPages;
import aiki.main.*;
import aiki.sml.Resources;
import cards.gui.animations.*;
import cards.gui.interfaces.ResultCardsServerInteractImpl;
import cards.main.*;
import code.gui.AbsButton;
import code.gui.initialize.*;
import code.scripts.imgs.cards.*;
import code.scripts.pages.aiki.*;
import code.sml.NavigationCore;
import code.util.*;
import code.util.consts.*;

public final class LaunchNetwork implements Runnable {
    private final AbstractProgramInfos list;

    private final String language;

    private final CardFactories cardFactories;

    private final AikiFactory aikiFactory;
    private final AbsButton button;
    public LaunchNetwork(String _language, AbstractProgramInfos _list, CardFactories _cardFactories, AikiFactory _aikiFactory, AbsButton _b) {
        language = _language;
        list = _list;
        cardFactories = _cardFactories;
        aikiFactory = _aikiFactory;
        button = _b;
    }
    @Override
    public void run() {
        button.setEnabled(false);
        StringList lgs_ = Constants.getAvailableLanguages();
        StringMap<StringMap<PreparedPagesCards>> belote_ = LaunchingGame.generateAnalyzedBelote(lgs_);
        StringMap<StringMap<PreparedPagesCards>> president_ = LaunchingGame.generateAnalyzedPresident(lgs_);
        StringMap<StringMap<PreparedPagesCards>> tarot_ = LaunchingGame.generateAnalyzedTarot(lgs_);
        StringMap<String> builtMessages_ = MessagesInit.ms();
        NavigationCore.adjust(builtMessages_);
        StringMap<String> builtOther_ = CssInit.ms();
        PreparedRenderedPages pkNet_ = new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES, new DetPkGameInit(), PagesInit.buildInd(), builtMessages_, builtOther_, new PkInd());
        pkNet_.run();
        WindowNetWork window_ = new WindowNetWork(language, list, belote_,president_,tarot_,cardFactories,aikiFactory);
        window_.setButtonClick(button);
        window_.setPreparedPkNetTask(pkNet_);
        window_.setResultCardsServerInteract(new ResultCardsServerInteractImpl());
        window_.pack();
        window_.setVisible(true);
        window_.setImages(CardsInit.ms());
    }
}
