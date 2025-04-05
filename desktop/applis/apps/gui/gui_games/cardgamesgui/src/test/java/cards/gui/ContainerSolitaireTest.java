package cards.gui;

import cards.facade.*;
import cards.facade.enumerations.*;
import cards.gui.containers.*;
import cards.solitaire.*;
import code.gui.*;
import code.mock.MockPanel;
import code.util.*;
import code.util.core.NumberUtil;
import org.junit.Test;

public final class ContainerSolitaireTest extends EquallableCardsGuiUtil {
    @Test
    public void p1() {
        AbsDealSolitaire deal_ = SolitaireSampleFirstDeal.dealClassic();
        ContainerSolitaire css_ = editSolitaire(deal_);
        dealClassic(css_);
        assertTrue(css_.partieSolitaire().finish());
    }
    @Test
    public void p2() {
        AbsDealSolitaire deal_ = SolitaireSampleFirstDeal.dealClassic();
        ContainerSolitaire css_ = loadSolitaire(deal_);
        dealClassic(css_);
        assertTrue(css_.partieSolitaire().finish());
    }
    @Test
    public void p3() {
        ContainerSolitaire css_ = modifySolitaire(GameEnum.CLASSIC);
        dealClassic(css_);
        assertTrue(css_.partieSolitaire().finish());
    }
    @Test
    public void p4() {
        AbsDealSolitaire deal_ = SolitaireSampleFirstDeal.dealClassic();
        ContainerSolitaire css_ = editSolitaire(deal_);
        ActionSolitaire ac_ = action(1, 0, 8);
        assertTrue(css_.partieSolitaire().canBeSelected(ac_.getFromIndex(), ac_.getCardIndex()));
        tryClickCard(compo((AbsPanel) compo(css_.getAll(), ac_.getFromIndex()), ac_.getCardIndex()));
        tryClickCard(compo((AbsPanel) compo(css_.getAll(), ac_.getFromIndex()), ac_.getCardIndex()));
        assertFalse(css_.partieSolitaire().finish());
    }
    @Test
    public void p5() {
        ContainerSolitaire css_ = modifySolitaire(GameEnum.CLASSIC);
        dealClassic(css_);
        tryClick(css_.getNextDe());
        assertFalse(css_.partieSolitaire().finish());
    }
    @Test
    public void p6() {
        ContainerSolitaire css_ = modifySolitaireDir(GameEnum.CLASSIC);
        assertFalse(css_.partieSolitaire().finish());
    }
    @Test
    public void p7() {
        AbsDealSolitaire deal_ = SolitaireSampleFirstDeal.dealFreeCell();
        ContainerSolitaire css_ = editSolitaire(deal_);
        dealFreeCell(css_);
        assertTrue(css_.partieSolitaire().finish());
    }
    @Test
    public void p8() {
        AbsDealSolitaire deal_ = SolitaireSampleFirstDeal.dealFreeCell();
        ContainerSolitaire css_ = loadSolitaire(deal_);
        dealFreeCell(css_);
        assertTrue(css_.partieSolitaire().finish());
    }
    @Test
    public void p9() {
        ContainerSolitaire css_ = modifySolitaire(GameEnum.FREECELL);
        dealFreeCell(css_);
        assertTrue(css_.partieSolitaire().finish());
    }
    @Test
    public void p10() {
        AbsDealSolitaire deal_ = SolitaireSampleFirstDeal.dealFreeCell();
        ContainerSolitaire css_ = editSolitaire(deal_);
        ActionSolitaire ac_ = action(4, 5, 0);
        assertTrue(css_.partieSolitaire().canBeSelected(ac_.getFromIndex(), ac_.getCardIndex()));
        tryClickCard(compo((AbsPanel) compo(css_.getAll(), ac_.getFromIndex()), ac_.getCardIndex()));
        tryClickCard(compo((AbsPanel) compo(css_.getAll(), ac_.getFromIndex()), ac_.getCardIndex()));
        assertFalse(css_.partieSolitaire().finish());
    }
    @Test
    public void p11() {
        ContainerSolitaire css_ = modifySolitaire(GameEnum.FREECELL);
        dealFreeCell(css_);
        tryClick(css_.getNextDe());
        assertFalse(css_.partieSolitaire().finish());
    }
    @Test
    public void p12() {
        ContainerSolitaire css_ = modifySolitaireDir(GameEnum.FREECELL);
        assertFalse(css_.partieSolitaire().finish());
    }
    @Test
    public void p13() {
        AbsDealSolitaire deal_ = SolitaireSampleFirstDeal.dealSpider();
        ContainerSolitaire css_ = editSolitaire(deal_);
        dealSpider(css_);
        assertTrue(css_.partieSolitaire().finish());
    }
    @Test
    public void p14() {
        AbsDealSolitaire deal_ = SolitaireSampleFirstDeal.dealSpider();
        ContainerSolitaire css_ = loadSolitaire(deal_);
        dealSpider(css_);
        assertTrue(css_.partieSolitaire().finish());
    }
    @Test
    public void p15() {
        ContainerSolitaire css_ = modifySolitaire(GameEnum.SPIDER);
        dealSpider(css_);
        assertTrue(css_.partieSolitaire().finish());
    }
    @Test
    public void p16() {
        AbsDealSolitaire deal_ = SolitaireSampleFirstDeal.dealSpider();
        ContainerSolitaire css_ = editSolitaire(deal_);
        ActionSolitaire ac_ = action(1, 0, 11);
        assertTrue(css_.partieSolitaire().canBeSelected(ac_.getFromIndex(), ac_.getCardIndex()));
        tryClickCard(compo((AbsPanel) compo(css_.getAll(), ac_.getFromIndex()), ac_.getCardIndex()));
        tryClickCard(compo((AbsPanel) compo(css_.getAll(), ac_.getFromIndex()), ac_.getCardIndex()));
        assertFalse(css_.partieSolitaire().finish());
    }
    @Test
    public void p17() {
        ContainerSolitaire css_ = modifySolitaire(GameEnum.SPIDER);
        dealSpider(css_);
        tryClick(css_.getNextDe());
        assertFalse(css_.partieSolitaire().finish());
    }
    @Test
    public void p18() {
        ContainerSolitaire css_ = modifySolitaireDir(GameEnum.SPIDER);
        assertFalse(css_.partieSolitaire().finish());
    }
    private void dealClassic(ContainerSolitaire _css) {
        tryPlay(_css, action(0, 0, 8));
        tryPlay(_css, action(0, 0, 9));
        tryPlay(_css, action(0, 0, 10));
        tryPlay(_css, action(0, 0, 11));
        tryPlay(_css, action(0, 0, 8));
        tryPlay(_css, action(0, 0, 9));
        tryPlay(_css, action(0, 0, 10));
        tryPlay(_css, action(0, 0, 11));
        tryPlay(_css, action(0, 0, 8));
        tryPlay(_css, action(0, 0, 9));
        tryPlay(_css, action(0, 0, 10));
        tryPlay(_css, action(0, 0, 11));
        tryPlay(_css, action(0, 0, 8));
        tryPlay(_css, action(0, 0, 9));
        tryPlay(_css, action(0, 0, 11));
        tryPlay(_css, action(0, 0, 8));
        tryPlay(_css, action(0, 0, 11));
        tryPlay(_css, action(0, 0, 11));
        tryPlay(_css, action(0, 2, 11));
        tryPlay(_css, action(0, 3, 11));
        tryPlay(_css, action(0, 3, 11));
        tryPlay(_css, action(1, 0, 8));
        tryPlay(_css, action(2, 1, 9));
        tryPlay(_css, action(2, 0, 9));
        tryPlay(_css, action(3, 2, 10));
        tryPlay(_css, action(3, 1, 10));
        tryPlay(_css, action(3, 0, 10));
        tryPlay(_css, action(0, 0, 8));
        tryPlay(_css, action(0, 0, 10));
        tryPlay(_css, action(0, 0, 8));
        tryPlay(_css, action(7, 6, 9));
        tryPlay(_css, action(6, 5, 9));
        tryPlay(_css, action(6, 4, 9));
        tryPlay(_css, action(7, 5, 10));
        tryPlay(_css, action(7, 4, 10));
        tryPlay(_css, action(5, 4, 8));
        tryPlay(_css, action(4, 3, 11));
        tryPlay(_css, action(5, 3, 8));
        tryPlay(_css, action(6, 3, 9));
        tryPlay(_css, action(7, 3, 10));
        tryPlay(_css, action(4, 2, 10));
        tryPlay(_css, action(5, 2, 11));
        tryPlay(_css, action(6, 2, 8));
        tryPlay(_css, action(7, 2, 9));
        tryPlay(_css, action(4, 1, 9));
        tryPlay(_css, action(5, 1, 10));
        tryPlay(_css, action(6, 1, 11));
        tryPlay(_css, action(7, 1, 8));
        tryPlay(_css, action(4, 0, 8));
        tryPlay(_css, action(5, 0, 9));
        tryPlay(_css, action(6, 0, 10));
        tryPlay(_css, action(7, 0, 11));
    }
    private void dealFreeCell(ContainerSolitaire _css) {
        tryPlay(_css,action(4,5,1));
        tryPlay(_css,action(5,5,0));
        tryPlay(_css,action(6,5,3));
        tryPlay(_css,action(7,5,2));
        tryPlay(_css,action(4,4,2));
        tryPlay(_css,action(5,4,1));
        tryPlay(_css,action(6,4,0));
        tryPlay(_css,action(7,4,3));
        tryPlay(_css,action(4,3,3));
        tryPlay(_css,action(5,3,2));
        tryPlay(_css,action(6,3,1));
        tryPlay(_css,action(7,3,0));
        tryPlay(_css,action(4,2,0));
        tryPlay(_css,action(5,2,3));
        tryPlay(_css,action(6,2,2));
        tryPlay(_css,action(7,2,1));
        tryPlay(_css,action(4,1,1));
        tryPlay(_css,action(5,1,0));
        tryPlay(_css,action(6,1,3));
        tryPlay(_css,action(7,1,2));
        tryPlay(_css,action(4,0,12));
        tryPlay(_css,action(5,0,13));
        tryPlay(_css,action(6,0,14));
        tryPlay(_css,action(7,0,15));
        tryPlay(_css,action(0,11,13));
        tryPlay(_css,action(1,11,12));
        tryPlay(_css,action(2,11,15));
        tryPlay(_css,action(3,11,14));
        tryPlay(_css,action(0,10,12));
        tryPlay(_css,action(1,10,15));
        tryPlay(_css,action(2,10,14));
        tryPlay(_css,action(3,10,13));
        tryPlay(_css,action(0,9,15));
        tryPlay(_css,action(1,9,14));
        tryPlay(_css,action(2,9,13));
        tryPlay(_css,action(3,9,12));
        tryPlay(_css,action(0,8,14));
        tryPlay(_css,action(1,8,13));
        tryPlay(_css,action(2,8,12));
        tryPlay(_css,action(3,8,15));
        tryPlay(_css,action(0,7,13));
        tryPlay(_css,action(1,7,12));
        tryPlay(_css,action(2,7,15));
        tryPlay(_css,action(3,7,14));
        tryPlay(_css,action(0,6,12));
        tryPlay(_css,action(1,6,13));
        tryPlay(_css,action(2,6,14));
        tryPlay(_css,action(3,6,15));
        tryPlay(_css,action(0,5,12));
        tryPlay(_css,action(1,5,13));
        tryPlay(_css,action(2,5,14));
        tryPlay(_css,action(3,5,15));
        tryPlay(_css,action(0,4,12));
        tryPlay(_css,action(1,4,13));
        tryPlay(_css,action(2,4,14));
        tryPlay(_css,action(3,4,15));
        tryPlay(_css,action(0,3,12));
        tryPlay(_css,action(1,3,13));
        tryPlay(_css,action(2,3,14));
        tryPlay(_css,action(3,3,15));
        tryPlay(_css,action(0,2,12));
        tryPlay(_css,action(1,2,13));
        tryPlay(_css,action(2,2,14));
        tryPlay(_css,action(3,2,15));
        tryPlay(_css,action(0,1,12));
        tryPlay(_css,action(1,1,13));
        tryPlay(_css,action(2,1,14));
        tryPlay(_css,action(3,1,15));
        tryPlay(_css,action(0,0,12));
        tryPlay(_css,action(1,0,13));
        tryPlay(_css,action(2,0,14));
        tryPlay(_css,action(3, 0, 15));
    }
    private void dealSpider(ContainerSolitaire _css) {
        tryPlay(_css, action(0, 0, 1));
        tryPlay(_css, action(5, 0, 1));
        tryPlay(_css, action(1, 0, 11));
        tryPlay(_css, action(6, 0, 2));
        tryPlay(_css, action(2, 0, 11));
        tryPlay(_css, action(7, 0, 3));
        tryPlay(_css, action(3, 0, 11));
        tryPlay(_css, action(8, 0, 4));
        tryPlay(_css, action(4, 0, 11));
        tryPlay(_css, action(9, 5, 1));
        tryPlay(_css, action(10, 5, 2));
        tryPlay(_css, action(9, 2, 1));
        tryPlay(_css, action(10, 2, 2));
        tryPlay(_css, action(9, 1, 1));
        tryPlay(_css, action(10, 1, 2));
        tryPlay(_css, action(9, 0, 1));
        tryPlay(_css, action(10, 0, 2));
        tryPlay(_css, action(0, 0, 1));
        tryPlay(_css, action(4, 0, 5));
        tryPlay(_css, action(3, 0, 5));
        tryPlay(_css, action(2, 6, 5));
        tryPlay(_css, action(1, 6, 5));
        tryPlay(_css, action(9, 0, 10));
        tryPlay(_css, action(8, 0, 10));
        tryPlay(_css, action(7, 0, 10));
        tryPlay(_css, action(6, 0, 10));
        tryPlay(_css, action(5, 0, 1));
        tryPlay(_css, action(10, 0, 2));
        tryPlay(_css, action(0, 0, 1));
        tryPlay(_css, action(4, 0, 5));
        tryPlay(_css, action(3, 0, 5));
        tryPlay(_css, action(2, 11, 5));
        tryPlay(_css, action(1, 11, 5));
        tryPlay(_css, action(9, 0, 10));
        tryPlay(_css, action(8, 0, 10));
        tryPlay(_css, action(7, 0, 10));
        tryPlay(_css, action(6, 0, 10));
        tryPlay(_css, action(0, 0, 1));
        tryPlay(_css, action(4, 0, 5));
        tryPlay(_css, action(3, 0, 5));
        tryPlay(_css, action(2, 11, 5));
        tryPlay(_css, action(1, 11, 5));
        tryPlay(_css, action(9, 0, 10));
        tryPlay(_css, action(8, 0, 10));
        tryPlay(_css, action(7, 0, 10));
        tryPlay(_css, action(6, 0, 10));
        tryPlay(_css, action(5, 5, 3));
        tryPlay(_css, action(5, 0, 3));
        tryPlay(_css, action(10, 5, 4));
        tryPlay(_css, action(10, 0, 4));
        tryPlay(_css, action(0, 0, 1));
        tryPlay(_css, action(1, 11, 7));
        tryPlay(_css, action(2, 11, 9));
        tryPlay(_css, action(3, 10, 4));
        tryPlay(_css, action(1, 0, 4));
        tryPlay(_css, action(4, 10, 11));
        tryPlay(_css, action(5, 0, 6));
        tryPlay(_css, action(2, 0, 6));
        tryPlay(_css, action(6, 0, 11));
        tryPlay(_css, action(7, 0, 8));
        tryPlay(_css, action(3, 0, 8));
        tryPlay(_css, action(8, 0, 11));
        tryPlay(_css, action(9, 0, 10));
        tryPlay(_css, action(4, 0, 10));
        tryPlay(_css, action(10, 0, 11));
    }
    public static void tryPlay(ContainerSolitaire _d, ActionSolitaire _ac) {
        assertTrue(_d.partieSolitaire().canBeSelected(_ac.getFromIndex(), _ac.getCardIndex()));
        tryClickCard(compo((AbsPanel) compo(_d.getAll(), _ac.getFromIndex()), _ac.getCardIndex()));
        assertTrue(_d.partieSolitaire().canBePlayed(_ac.getFromIndex(), _ac.getCardIndex(), _ac.getToIndex()));
        AbsPanel dest_ = (AbsPanel) compo(_d.getAll(), _ac.getToIndex());
        tryClickCard(compo(dest_, NumberUtil.max(0, dest_.getComponentCount() - 1)));
    }

    private static AbsCustComponent compo(AbsPanel _pan, int _index) {
        return ((MockPanel)_pan).getComponent(_index);
    }

    private ContainerSolitaire editSolitaire(AbsDealSolitaire _deal) {
        WindowCards wc_ = frameSingleSolitaire();
        ContainerSolitaire csb_ = new ContainerSolitaire(wc_);
        wc_.getCore().setContainerGame(csb_);
        csb_.editerSolitaire(_deal);
        wc_.getChange().setEnabled(true);
        return csb_;
    }

    private ContainerSolitaire saveSolitaire(AbsDealSolitaire _deal) {
        WindowCards wc_ = frameSingleSolitaireSave();
        ContainerSolitaire csb_ = new ContainerSolitaire(wc_);
        wc_.getCore().setContainerGame(csb_);
        csb_.editerSolitaire(_deal);
        wc_.getChange().setEnabled(true);
        return csb_;
    }
    private ContainerSolitaire editSolitaireOtherDisplay(AbsDealSolitaire _deal) {
        WindowCards wc_ = frameSingleSolitaireWithEnd();
        wc_.baseWindow().getFacadeCards().getParametres().setWaitTrickClick(false);
        ContainerSolitaire csb_ = new ContainerSolitaire(wc_);
        wc_.getCore().setContainerGame(csb_);
        csb_.editerSolitaire(_deal);
        wc_.getChange().setEnabled(true);
        return csb_;
    }

    private ContainerSolitaire loadSolitaire(AbsDealSolitaire _game) {
        WindowCards wc_ = frameSingleSolitaire();
        return containerGame(_game, wc_);
    }

    private ContainerSolitaire loadSolitaireOtherDisplay(AbsDealSolitaire _game) {
        return loadSolitaireOtherDisplay(_game, 0);
    }

    private ContainerSolitaire loadSolitaireOtherDisplay(AbsDealSolitaire _game, int _i) {
        WindowCards wc_ = frameSingleSolitaireWithEnd(_i);
        wc_.baseWindow().getFacadeCards().getParametres().setWaitTrickClick(false);
        return containerGame(_game, wc_);
    }

    private ContainerSolitaire containerGame(AbsDealSolitaire _game, WindowCards _wc) {
        assertTrue(_game.valid(mesCheck()));
        Games games_ = new Games();
        games_.jouerSolitaire(_game);
        _wc.tryToLoadDeal("_",games_);
        return (ContainerSolitaire) _wc.getCore().getContainerGame();
    }

    private ContainerSolitaire modifySolitaire(GameEnum _solo) {
        return modifySolitaire(0, _solo);
    }
    private ContainerSolitaire modifySolitaire(int _i, GameEnum _solo) {
        WindowCards wc_ = frameSingleSolitaireWithEnd(_i);
        wc_.getCore().setFirstDealSolitaire(new SolitaireSampleFirstDeal());
        wc_.baseWindow().getFacadeCards().getParametres().setWaitTrickClick(false);
        tryClick(wc_.getSingleModeButton());
        tryClick(wc_.getSoloGames().getVal(_solo));
        ContainerSolitaire csb_ = (ContainerSolitaire) wc_.getCore().getContainerGame();
        wc_.getChange().setEnabled(true);
        return csb_;
    }

    private ContainerSolitaire modifySolitaireDir(GameEnum _solo) {
        WindowCards wc_ = frameSingleSolitaireWithEndModif(0, _solo);
        wc_.baseWindow().getFacadeCards().getParametres().setWaitTrickClick(false);
        wc_.getCommonFrame().setVisible(true);
        ContainerSolitaire csb_ = (ContainerSolitaire) wc_.getCore().getContainerGame();
        wc_.getChange().setEnabled(true);
        return csb_;
    }

    private static StringMap<String> mesCheck(){
        StringMap<String> m_ = new StringMap<String>();
        m_.addEntry(AbsDealSolitaire.NOT_PLAYABLE,"_");
        m_.addEntry(AbsDealSolitaire.BAD_HAND_COUNT,":");
        m_.addEntry(AbsDealSolitaire.BAD_CARD_HAND_COUNT,":");
        m_.addEntry(AbsDealSolitaire.BAD_CARD_UNIT_COUNT,":");
        m_.addEntry(AbsDealSolitaire.CANNOT_BE_SELECTED,"_");
        m_.addEntry(AbsDealSolitaire.CANNOT_BE_PLAYED,"_");
        return m_;
    }

    public static ActionSolitaire action(int _from, int _card, int _to) {
        ActionSolitaire ac_ = new ActionSolitaire();
        ac_.setFromIndex(_from);
        ac_.setCardIndex(_card);
        ac_.setToIndex(_to);
        return ac_;
    }

}
