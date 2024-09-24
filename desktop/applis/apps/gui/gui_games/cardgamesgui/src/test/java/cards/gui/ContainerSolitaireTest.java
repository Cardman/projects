package cards.gui;

import cards.facade.*;
import cards.facade.enumerations.*;
import cards.gui.containers.*;
import cards.solitaire.*;
import code.gui.*;
import code.util.*;
import code.util.core.NumberUtil;
import org.junit.Test;

public final class ContainerSolitaireTest extends EquallableCardsGuiUtil {
    @Test
    public void p1() {
        AbsDealSolitaire deal_ = SolitaireSampleFirstDeal.dealSample();
        ContainerSolitaire css_ = editSolitaire(deal_);
        tryPlay(css_, action(0, 0, 8));
        tryPlay(css_, action(0, 0, 9));
        tryPlay(css_, action(0, 0, 10));
        tryPlay(css_, action(0, 0, 11));
        tryPlay(css_, action(0, 0, 8));
        tryPlay(css_, action(0, 0, 9));
        tryPlay(css_, action(0, 0, 10));
        tryPlay(css_, action(0, 0, 11));
        tryPlay(css_, action(0, 0, 8));
        tryPlay(css_, action(0, 0, 9));
        tryPlay(css_, action(0, 0, 10));
        tryPlay(css_, action(0, 0, 11));
        tryPlay(css_, action(0, 0, 8));
        tryPlay(css_, action(0, 0, 9));
        tryPlay(css_, action(0, 0, 11));
        tryPlay(css_, action(0, 0, 8));
        tryPlay(css_, action(0, 0, 11));
        tryPlay(css_, action(0, 0, 11));
        tryPlay(css_, action(0, 2, 11));
        tryPlay(css_, action(0, 3, 11));
        tryPlay(css_, action(0, 3, 11));
        tryPlay(css_, action(1, 0, 8));
        tryPlay(css_, action(2, 1, 9));
        tryPlay(css_, action(2, 0, 9));
        tryPlay(css_, action(3, 2, 10));
        tryPlay(css_, action(3, 1, 10));
        tryPlay(css_, action(3, 0, 10));
        tryPlay(css_, action(0, 0, 8));
        tryPlay(css_, action(0, 0, 10));
        tryPlay(css_, action(0, 0, 8));
        tryPlay(css_, action(7, 6, 9));
        tryPlay(css_, action(6, 5, 9));
        tryPlay(css_, action(6, 4, 9));
        tryPlay(css_, action(7, 5, 10));
        tryPlay(css_, action(7, 4, 10));
        tryPlay(css_, action(5, 4, 8));
        tryPlay(css_, action(4, 3, 11));
        tryPlay(css_, action(5, 3, 8));
        tryPlay(css_, action(6, 3, 9));
        tryPlay(css_, action(7, 3, 10));
        tryPlay(css_, action(4, 2, 10));
        tryPlay(css_, action(5, 2, 11));
        tryPlay(css_, action(6, 2, 8));
        tryPlay(css_, action(7, 2, 9));
        tryPlay(css_, action(4, 1, 9));
        tryPlay(css_, action(5, 1, 10));
        tryPlay(css_, action(6, 1, 11));
        tryPlay(css_, action(7, 1, 8));
        tryPlay(css_, action(4, 0, 8));
        tryPlay(css_, action(5, 0, 9));
        tryPlay(css_, action(6, 0, 10));
        tryPlay(css_, action(7, 0, 11));
        assertTrue(css_.partieSolitaire().finish());
    }
    @Test
    public void p2() {
        AbsDealSolitaire deal_ = SolitaireSampleFirstDeal.dealSample();
        ContainerSolitaire css_ = loadSolitaire(deal_);
        tryPlay(css_, action(0, 0, 8));
        tryPlay(css_, action(0, 0, 9));
        tryPlay(css_, action(0, 0, 10));
        tryPlay(css_, action(0, 0, 11));
        tryPlay(css_, action(0, 0, 8));
        tryPlay(css_, action(0, 0, 9));
        tryPlay(css_, action(0, 0, 10));
        tryPlay(css_, action(0, 0, 11));
        tryPlay(css_, action(0, 0, 8));
        tryPlay(css_, action(0, 0, 9));
        tryPlay(css_, action(0, 0, 10));
        tryPlay(css_, action(0, 0, 11));
        tryPlay(css_, action(0, 0, 8));
        tryPlay(css_, action(0, 0, 9));
        tryPlay(css_, action(0, 0, 11));
        tryPlay(css_, action(0, 0, 8));
        tryPlay(css_, action(0, 0, 11));
        tryPlay(css_, action(0, 0, 11));
        tryPlay(css_, action(0, 2, 11));
        tryPlay(css_, action(0, 3, 11));
        tryPlay(css_, action(0, 3, 11));
        tryPlay(css_, action(1, 0, 8));
        tryPlay(css_, action(2, 1, 9));
        tryPlay(css_, action(2, 0, 9));
        tryPlay(css_, action(3, 2, 10));
        tryPlay(css_, action(3, 1, 10));
        tryPlay(css_, action(3, 0, 10));
        tryPlay(css_, action(0, 0, 8));
        tryPlay(css_, action(0, 0, 10));
        tryPlay(css_, action(0, 0, 8));
        tryPlay(css_, action(7, 6, 9));
        tryPlay(css_, action(6, 5, 9));
        tryPlay(css_, action(6, 4, 9));
        tryPlay(css_, action(7, 5, 10));
        tryPlay(css_, action(7, 4, 10));
        tryPlay(css_, action(5, 4, 8));
        tryPlay(css_, action(4, 3, 11));
        tryPlay(css_, action(5, 3, 8));
        tryPlay(css_, action(6, 3, 9));
        tryPlay(css_, action(7, 3, 10));
        tryPlay(css_, action(4, 2, 10));
        tryPlay(css_, action(5, 2, 11));
        tryPlay(css_, action(6, 2, 8));
        tryPlay(css_, action(7, 2, 9));
        tryPlay(css_, action(4, 1, 9));
        tryPlay(css_, action(5, 1, 10));
        tryPlay(css_, action(6, 1, 11));
        tryPlay(css_, action(7, 1, 8));
        tryPlay(css_, action(4, 0, 8));
        tryPlay(css_, action(5, 0, 9));
        tryPlay(css_, action(6, 0, 10));
        tryPlay(css_, action(7, 0, 11));
        assertTrue(css_.partieSolitaire().finish());
    }
    @Test
    public void p3() {
        ContainerSolitaire css_ = modifySolitaire();
        tryPlay(css_, action(0, 0, 8));
        tryPlay(css_, action(0, 0, 9));
        tryPlay(css_, action(0, 0, 10));
        tryPlay(css_, action(0, 0, 11));
        tryPlay(css_, action(0, 0, 8));
        tryPlay(css_, action(0, 0, 9));
        tryPlay(css_, action(0, 0, 10));
        tryPlay(css_, action(0, 0, 11));
        tryPlay(css_, action(0, 0, 8));
        tryPlay(css_, action(0, 0, 9));
        tryPlay(css_, action(0, 0, 10));
        tryPlay(css_, action(0, 0, 11));
        tryPlay(css_, action(0, 0, 8));
        tryPlay(css_, action(0, 0, 9));
        tryPlay(css_, action(0, 0, 11));
        tryPlay(css_, action(0, 0, 8));
        tryPlay(css_, action(0, 0, 11));
        tryPlay(css_, action(0, 0, 11));
        tryPlay(css_, action(0, 2, 11));
        tryPlay(css_, action(0, 3, 11));
        tryPlay(css_, action(0, 3, 11));
        tryPlay(css_, action(1, 0, 8));
        tryPlay(css_, action(2, 1, 9));
        tryPlay(css_, action(2, 0, 9));
        tryPlay(css_, action(3, 2, 10));
        tryPlay(css_, action(3, 1, 10));
        tryPlay(css_, action(3, 0, 10));
        tryPlay(css_, action(0, 0, 8));
        tryPlay(css_, action(0, 0, 10));
        tryPlay(css_, action(0, 0, 8));
        tryPlay(css_, action(7, 6, 9));
        tryPlay(css_, action(6, 5, 9));
        tryPlay(css_, action(6, 4, 9));
        tryPlay(css_, action(7, 5, 10));
        tryPlay(css_, action(7, 4, 10));
        tryPlay(css_, action(5, 4, 8));
        tryPlay(css_, action(4, 3, 11));
        tryPlay(css_, action(5, 3, 8));
        tryPlay(css_, action(6, 3, 9));
        tryPlay(css_, action(7, 3, 10));
        tryPlay(css_, action(4, 2, 10));
        tryPlay(css_, action(5, 2, 11));
        tryPlay(css_, action(6, 2, 8));
        tryPlay(css_, action(7, 2, 9));
        tryPlay(css_, action(4, 1, 9));
        tryPlay(css_, action(5, 1, 10));
        tryPlay(css_, action(6, 1, 11));
        tryPlay(css_, action(7, 1, 8));
        tryPlay(css_, action(4, 0, 8));
        tryPlay(css_, action(5, 0, 9));
        tryPlay(css_, action(6, 0, 10));
        tryPlay(css_, action(7, 0, 11));
        assertTrue(css_.partieSolitaire().finish());
    }
    @Test
    public void p4() {
        AbsDealSolitaire deal_ = SolitaireSampleFirstDeal.dealSample();
        ContainerSolitaire css_ = editSolitaire(deal_);
        ActionSolitaire ac_ = action(1, 0, 8);
        assertTrue(css_.partieSolitaire().canBeSelected(ac_.getFromIndex(), ac_.getCardIndex()));
        tryClickCard(((AbsPanel)css_.getAll().getComponent(ac_.getFromIndex())).getComponent(ac_.getCardIndex()));
        tryClickCard(((AbsPanel)css_.getAll().getComponent(ac_.getFromIndex())).getComponent(ac_.getCardIndex()));
        assertFalse(css_.partieSolitaire().finish());
    }
    @Test
    public void p5() {
        ContainerSolitaire css_ = modifySolitaire();
        tryPlay(css_, action(0, 0, 8));
        tryPlay(css_, action(0, 0, 9));
        tryPlay(css_, action(0, 0, 10));
        tryPlay(css_, action(0, 0, 11));
        tryPlay(css_, action(0, 0, 8));
        tryPlay(css_, action(0, 0, 9));
        tryPlay(css_, action(0, 0, 10));
        tryPlay(css_, action(0, 0, 11));
        tryPlay(css_, action(0, 0, 8));
        tryPlay(css_, action(0, 0, 9));
        tryPlay(css_, action(0, 0, 10));
        tryPlay(css_, action(0, 0, 11));
        tryPlay(css_, action(0, 0, 8));
        tryPlay(css_, action(0, 0, 9));
        tryPlay(css_, action(0, 0, 11));
        tryPlay(css_, action(0, 0, 8));
        tryPlay(css_, action(0, 0, 11));
        tryPlay(css_, action(0, 0, 11));
        tryPlay(css_, action(0, 2, 11));
        tryPlay(css_, action(0, 3, 11));
        tryPlay(css_, action(0, 3, 11));
        tryPlay(css_, action(1, 0, 8));
        tryPlay(css_, action(2, 1, 9));
        tryPlay(css_, action(2, 0, 9));
        tryPlay(css_, action(3, 2, 10));
        tryPlay(css_, action(3, 1, 10));
        tryPlay(css_, action(3, 0, 10));
        tryPlay(css_, action(0, 0, 8));
        tryPlay(css_, action(0, 0, 10));
        tryPlay(css_, action(0, 0, 8));
        tryPlay(css_, action(7, 6, 9));
        tryPlay(css_, action(6, 5, 9));
        tryPlay(css_, action(6, 4, 9));
        tryPlay(css_, action(7, 5, 10));
        tryPlay(css_, action(7, 4, 10));
        tryPlay(css_, action(5, 4, 8));
        tryPlay(css_, action(4, 3, 11));
        tryPlay(css_, action(5, 3, 8));
        tryPlay(css_, action(6, 3, 9));
        tryPlay(css_, action(7, 3, 10));
        tryPlay(css_, action(4, 2, 10));
        tryPlay(css_, action(5, 2, 11));
        tryPlay(css_, action(6, 2, 8));
        tryPlay(css_, action(7, 2, 9));
        tryPlay(css_, action(4, 1, 9));
        tryPlay(css_, action(5, 1, 10));
        tryPlay(css_, action(6, 1, 11));
        tryPlay(css_, action(7, 1, 8));
        tryPlay(css_, action(4, 0, 8));
        tryPlay(css_, action(5, 0, 9));
        tryPlay(css_, action(6, 0, 10));
        tryPlay(css_, action(7, 0, 11));
        tryClick(css_.getNextDe());
        assertFalse(css_.partieSolitaire().finish());
    }
    @Test
    public void p6() {
        ContainerSolitaire css_ = modifySolitaireDir();
        assertFalse(css_.partieSolitaire().finish());
    }
    public static void tryPlay(ContainerSolitaire _d, ActionSolitaire _ac) {
        assertTrue(_d.partieSolitaire().canBeSelected(_ac.getFromIndex(), _ac.getCardIndex()));
        tryClickCard(((AbsPanel)_d.getAll().getComponent(_ac.getFromIndex())).getComponent(_ac.getCardIndex()));
        assertTrue(_d.partieSolitaire().canBePlayed(_ac.getFromIndex(), _ac.getCardIndex(), _ac.getToIndex()));
        AbsPanel dest_ = (AbsPanel) _d.getAll().getComponent(_ac.getToIndex());
        tryClickCard(dest_.getComponent(NumberUtil.max(0,dest_.getComponentCount()-1)));
    }
    private ContainerSolitaire editSolitaire(AbsDealSolitaire _deal) {
        WindowCards wc_ = frameSingleSolitaire();
        ContainerSolitaire csb_ = new ContainerSolitaire(wc_);
        wc_.getCore().setContainerGame(csb_);
        csb_.editerSolitaire(_deal);
        MenuItemUtils.setEnabledMenu(wc_.getChange(),true);
        return csb_;
    }

    private ContainerSolitaire saveSolitaire(AbsDealSolitaire _deal) {
        WindowCards wc_ = frameSingleSolitaireSave();
        ContainerSolitaire csb_ = new ContainerSolitaire(wc_);
        wc_.getCore().setContainerGame(csb_);
        csb_.editerSolitaire(_deal);
        MenuItemUtils.setEnabledMenu(wc_.getChange(),true);
        return csb_;
    }
    private ContainerSolitaire editSolitaireOtherDisplay(AbsDealSolitaire _deal) {
        WindowCards wc_ = frameSingleSolitaireWithEnd();
        wc_.baseWindow().getFacadeCards().getParametres().setWaitTrickClick(false);
        ContainerSolitaire csb_ = new ContainerSolitaire(wc_);
        wc_.getCore().setContainerGame(csb_);
        csb_.editerSolitaire(_deal);
        MenuItemUtils.setEnabledMenu(wc_.getChange(),true);
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

    private ContainerSolitaire modifySolitaire() {
        return modifySolitaire(0);
    }
    private ContainerSolitaire modifySolitaire(int _i) {
        WindowCards wc_ = frameSingleSolitaireWithEnd(_i);
        wc_.getCore().setFirstDealSolitaire(new SolitaireSampleFirstDeal());
        wc_.baseWindow().getFacadeCards().getParametres().setWaitTrickClick(false);
        tryClick(wc_.getSingleModeButton());
        tryClick(wc_.getSoloGames().getVal(GameEnum.CLASSIC));
        ContainerSolitaire csb_ = (ContainerSolitaire) wc_.getCore().getContainerGame();
        MenuItemUtils.setEnabledMenu(wc_.getChange(),true);
        return csb_;
    }

    private ContainerSolitaire modifySolitaireDir() {
        WindowCards wc_ = frameSingleSolitaireWithEndModif(0);
        wc_.baseWindow().getFacadeCards().getParametres().setWaitTrickClick(false);
        wc_.getCommonFrame().setVisible(true);
        ContainerSolitaire csb_ = (ContainerSolitaire) wc_.getCore().getContainerGame();
        MenuItemUtils.setEnabledMenu(wc_.getChange(),true);
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
