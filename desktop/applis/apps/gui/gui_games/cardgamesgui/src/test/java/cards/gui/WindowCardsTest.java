package cards.gui;

import cards.facade.*;
import cards.gui.dialogs.BeanBuilderHelperCards;
import cards.gui.dialogs.EditorCardsNonModalEvent;
import cards.gui.dialogs.help.HelpIndexesTree;
import cards.gui.labels.AbsMetaLabelCard;
import cards.main.CardFactories;
import cards.main.LaunchingGame;
import code.gui.GuiBaseUtil;
import code.gui.LanguagesButtonsPair;
import code.mock.MockBaseExecutorServiceParam;
import code.scripts.pages.cards.AbsHelpCards;
import code.scripts.pages.cards.MessagesHelpCards;
import code.util.*;
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
        MessagesCardGames.appendNickNames(MessagesCardGames.getAppliTr(wc_.getFrames().currentLg()), MessagesCardGames.en());
//        WindowCards.getIcon(wc_.getImageFactory());
//        FacadeCards.install(WindowCards.getTempFolderSl(wc_.getFrames()),wc_.getFrames());
        LaunchingGame lg_ = new LaunchingGame(new StringList(), wc_.getFrames(),new CardFactories(wc_.getFrames(), new MockBaseExecutorServiceParam<HelpIndexesTree>()), new LanguagesButtonsPair(null,null,null), null);
        lg_.run();
        sels();
        defs();
        GuiBaseUtil.tryToReopen(wc_.getApplicationName(),wc_.getFrames());
        BeanBuilderHelperCards cards_ = new BeanBuilderHelperCards(wc_.getFrames());
        cards_.initGrid();
        cards_.setTitledBorder("");
        cards_.colCount(1);
        cards_.formatMessageDirCtsHeader("");
        cards_.addImgCts(new int[1][1],"");
        cards_.addImg(new int[1][1]);
        cards_.paintNb(1);
        cards_.paintIndent();
        cards_.initLine();
        cards_.feedParentsCts();
        wc_.getFrames().getTranslations().getMapping().getVal(EN).getMapping().addEntry(MessagesHelpCards.APP_BEAN,MessagesHelpCards.en());
        wc_.getFrames().getTranslations().getMapping().getVal(FR).getMapping().addEntry(MessagesHelpCards.APP_BEAN,MessagesHelpCards.fr());
        wc_.getFrames().getTranslations().getMapping().getVal(EN).getMaxiCards().addAllEntries(MiniCardsSampleGene.def());
        wc_.getFrames().getTranslations().getMapping().getVal(FR).getMaxiCards().addAllEntries(MiniCardsSampleGene.def());
        StringMap<AbsHelpCards> b_ = MessagesHelpCards.build();
        b_.getValue(0).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(1).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(2).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(3).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(4).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(5).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(6).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(7).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(8).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(9).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(10).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(11).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(12).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(13).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(14).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(15).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(16).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(17).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(18).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(19).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(20).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(21).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(22).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(23).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(24).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(25).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(26).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(27).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(28).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(29).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(30).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(31).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(32).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(33).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(34).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(35).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(36).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(37).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(38).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(39).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(40).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(41).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(42).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(43).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(44).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(45).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(46).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(47).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(48).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(49).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(50).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(51).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(52).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(53).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(54).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(55).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(56).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(57).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(58).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(59).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(60).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(61).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(62).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(63).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(64).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(65).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(66).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(67).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(68).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(69).format(cards_, wc_.getFrames().currentLg());
        b_.getValue(70).format(cards_, wc_.getFrames().currentLg());
//        new CallablePreparedPagesCards(new BeloteStandardsSample(),null,new StringMap<Document>(),new StringMap<String>(),wc_.getFrames().getLanguages()).call();
//        new CallablePreparedPagesCards(new PresidentStandardsSample(),null,new StringMap<Document>(),new StringMap<String>(),wc_.getFrames().getLanguages()).call();
//        new CallablePreparedPagesCards(new TarotStandardsSample(),null,new StringMap<Document>(),new StringMap<String>(),wc_.getFrames().getLanguages()).call();
    }
    public static void defs() {
        StringMap<StringMap<int[][]>> out_ = new StringMap<StringMap<int[][]>>(new CollCapacity(2));
        out_.addEntry(EN, AbsMetaLabelCard.enDef());
        out_.addEntry(FR, AbsMetaLabelCard.frDef());
    }

    public static void sels() {
        StringMap<StringMap<int[][]>> out_ = new StringMap<StringMap<int[][]>>(new CollCapacity(2));
        out_.addEntry(EN, AbsMetaLabelCard.enSel());
        out_.addEntry(FR, AbsMetaLabelCard.frSel());
    }

    private WindowCards loadBeloteOtherDisplay(StringList _ls) {
        WindowCards wc_ = frameDialogSoft("/__/", "/_/");
        wc_.getCommonFrame().setVisible(true);
        wc_.loadGameBegin(_ls);
        return wc_;
    }

}
