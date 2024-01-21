package code.network;

import aiki.beans.DetPkGameInit;
import aiki.beans.PkInd;
import aiki.gui.threads.PreparedRenderedPages;
import aiki.main.AikiFactory;
import aiki.sml.Resources;
import cards.facade.DefNicknamesCrud;
import cards.gui.animations.PreparedPagesCards;
import cards.gui.interfaces.ResultCardsServerInteractImpl;
import cards.main.CardFactories;
import cards.main.LaunchingGame;
import code.gui.AbsButton;
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
    public LaunchNetwork(String _language, AbstractProgramInfos _list, AbsButton _b, AikiFactory _a, CardFactories _c) {
        language = _language;
        list = _list;
        button = _b;
        aikiFactory = _a;
        cardFactories = _c;
    }
    @Override
    public void run() {
        button.setEnabled(false);
        StringList lgs_ = list.getLanguages();
        StringMap<StringMap<PreparedPagesCards>> belote_ = LaunchingGame.generateAnalyzedBelote(lgs_);
        StringMap<StringMap<PreparedPagesCards>> president_ = LaunchingGame.generateAnalyzedPresident(lgs_);
        StringMap<StringMap<PreparedPagesCards>> tarot_ = LaunchingGame.generateAnalyzedTarot(lgs_);
        StringMap<String> builtMessages_ = MessagesInit.ms();
        NavigationCore.adjust(builtMessages_);
        StringMap<String> builtOther_ = CssInit.ms();
        PreparedRenderedPages pkNet_ = new PreparedRenderedPages(Resources.ACCESS_TO_DEFAULT_FILES, new DetPkGameInit(), PagesInit.buildInd(), builtMessages_, builtOther_, new PkInd(), lgs_);
        pkNet_.run();
        WindowNetWork window_ = new WindowNetWork(new DefNicknamesCrud(list),language, list, belote_,president_,tarot_,aikiFactory);
        window_.setButtonClick(button);
        window_.setPreparedPkNetTask(pkNet_);
        window_.setResultCardsServerInteract(new ResultCardsServerInteractImpl());
        window_.pack();
        window_.setVisible(true);
        window_.setTaskLoading(cardFactories.getTaskLoad());
        window_.setTaskLoadingMiniDef(cardFactories.getTaskLoadMiniDef());
        window_.setTaskLoadingMiniSel(cardFactories.getTaskLoadMiniSel());
    }
}
