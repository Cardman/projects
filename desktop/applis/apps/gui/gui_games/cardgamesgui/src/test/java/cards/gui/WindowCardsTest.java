package cards.gui;

import cards.facade.*;
import cards.gui.dialogs.EditorCardsNonModalEvent;
import cards.gui.dialogs.help.HelpIndexesTree;
import cards.gui.labels.AbsMetaLabelCard;
import cards.main.CardFactories;
import cards.main.CardNatLgNamesNavigation;
import cards.main.LaunchingGame;
import code.gui.GuiBaseUtil;
import code.gui.LanguagesButtonsPair;
import code.mock.MockBaseExecutorServiceParam;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

public final class WindowCardsTest extends EquallableCardsGuiUtil {
    @Test
    public void modal1() {
        WindowCards fr_ = frameDialogSoft("/__/", "/_/");
        assertTrue(new EditorCardsNonModalEvent(fr_).act());
    }
    @Test
    public void modal2() {
        WindowCards fr_ = frameDialogSoft("/__/", "/_/");
        updateBase(fr_.getFrames().currentLg());
        tryClick(fr_.getLoad());
        assertFalse(new EditorCardsNonModalEvent(fr_).act());
    }
    @Test
    public void load1() {
        WindowCards fr_ =loadBeloteOtherDisplay(new StringList(""));
        assertTrue(fr_.getCommonFrame().isVisible());
    }
    @Test
    public void load2() {
        WindowCards fr_ =loadBeloteOtherDisplay(new StringList());
        assertTrue(fr_.getCommonFrame().isVisible());
    }
    @Test
    public void change() {
        WindowCards wc_ = frameDialogSoft("/__/", "/_/");
        wc_.getCommonFrame().setVisible(true);
        tryClick(wc_.getSingleModeButton());
        tryClick(wc_.getBackMenu());
        assertTrue(wc_.getCommonFrame().isVisible());
    }
    @Test
    public void translate() {
        WindowCards wc_ = frameLanguage();
        wc_.getCommonFrame().setVisible(true);
        tryClick(wc_.getCore().getLanguage());
        tryClick(wc_.getLanguageDialogButtons().getContent().getGroupe().get(wc_.getFrames().getTranslations().getMapping().indexOfEntry(EN)));
        tryClick(wc_.getLanguageDialogButtons().getContent().getGroupe().get(wc_.getFrames().getTranslations().getMapping().indexOfEntry(FR)));
        assertTrue(wc_.getCommonFrame().isVisible());
//        wc_.getFrames().getCounts().put(wc_.getApplicationName(),wc_.getFrames().getThreadFactory().newAtomicInteger());
        wc_.quit();
        Games.appendNickNames(Games.getAppliTr(wc_.getFrames().currentLg()),Nicknames.en());
        WindowCards.getIcon(wc_.getImageFactory());
//        FacadeCards.install(WindowCards.getTempFolderSl(wc_.getFrames()),wc_.getFrames());
        LaunchingGame lg_ = new LaunchingGame(new StringList(), wc_.getFrames(),new CardFactories(wc_.getFrames(), new MockBaseExecutorServiceParam<CardNatLgNamesNavigation>(),new MockBaseExecutorServiceParam<HelpIndexesTree>()), new LanguagesButtonsPair(null,null,null));
        lg_.run();
        AbsMetaLabelCard.sels();
        AbsMetaLabelCard.defs();
        GuiBaseUtil.tryToReopen(wc_.getApplicationName(),wc_.getFrames());
//        new CallablePreparedPagesCards(new BeloteStandardsSample(),null,new StringMap<Document>(),new StringMap<String>(),wc_.getFrames().getLanguages()).call();
//        new CallablePreparedPagesCards(new PresidentStandardsSample(),null,new StringMap<Document>(),new StringMap<String>(),wc_.getFrames().getLanguages()).call();
//        new CallablePreparedPagesCards(new TarotStandardsSample(),null,new StringMap<Document>(),new StringMap<String>(),wc_.getFrames().getLanguages()).call();
    }
    private WindowCards loadBeloteOtherDisplay(StringList _ls) {
        WindowCards wc_ = frameDialogSoft("/__/", "/_/");
        wc_.getCommonFrame().setVisible(true);
        wc_.loadGameBegin(_ls);
        return wc_;
    }

}
