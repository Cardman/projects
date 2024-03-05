package cards.gui.panels;






import cards.belote.*;
import cards.belote.enumerations.CardBelote;
import cards.gui.WindowCardsInt;
import cards.gui.labels.BeloteCardConverter;
import code.gui.*;
import code.util.CustList;
import code.util.StringList;
import code.util.core.NumberUtil;

public class PanelTricksHandsBelote extends PanelTricksHandsUniqCard<CardBelote> {

    private final TricksHandsBelote tricksHands;
    private final RulesBelote rules;
    private final DisplayingBelote displayingBelote;

    public PanelTricksHandsBelote(ChangeableTitle _parent,
                                  TricksHandsBelote _tricksHands,
                                  RulesBelote _r, StringList _pseudos,
                                  DisplayingBelote _displayingBelote,
                                  WindowCardsInt _window) {
        super(_parent,_pseudos,_window,_r.getDealing().getId().getNombreJoueurs(),new BeloteCardConverter());
        rules = _r;
        displayingBelote = _displayingBelote;
        tricksHands = _tricksHands;
        init(_r.getDealing().getId().getNombreJoueurs());
        changeTrick();
        changeCard();
    }

    @Override
    protected int tricksSize() {
        return tricksHands.getTricks().size();
    }

    @Override
    protected CustList<CardBelote> list(byte _i) {
        return tricksHands.getDistribution().hand(_i).getCards();
    }

    @Override
    protected CustList<CardBelote> derniereMain() {
        return tricksHands.getDistribution().derniereMain().getCards();
    }

    @Override
    protected int offset() {
        return RulesBelote.offset(rules);
    }

    @Override
    protected int nbPlayers() {
        return rules.getDealing().getId().getNombreJoueurs();
    }

    @Override
    protected CustList<CustList<CardBelote>> tricks(int _nb) {
        CustList<CustList<CardBelote>> trs_ = new CustList<CustList<CardBelote>>();
        for (TrickBelote t:tricksHands.getTricks().left(_nb)) {
            CustList<CardBelote> l_ = new CustList<CardBelote>();
            for (CardBelote c: t) {
                l_.add(c);
            }
            trs_.add(l_);
        }
        return trs_;
    }

    @Override
    protected CustList<CustList<CardBelote>> tricks() {
        CustList<CustList<CardBelote>> trs_ = new CustList<CustList<CardBelote>>();
        for (TrickBelote t:tricksHands.getTricks()) {
            CustList<CardBelote> l_ = new CustList<CardBelote>();
            for (CardBelote c: t) {
                l_.add(c);
            }
            trs_.add(l_);
        }
        return trs_;
    }

    @Override
    protected CustList<Integer> tricksStarters() {
        CustList<Integer> trs_ = new CustList<Integer>();
        for (TrickBelote t:tricksHands.getTricks()) {
            trs_.add((int) t.getEntameur());
        }
        return trs_;
    }

    @Override
    protected int restituteFull() {
        byte numeroPli_=(byte)(getTrickNumber().getSelectedIndex() - 1);
        tricksHands.restituerMains(displayingBelote, numeroPli_, rules);
        return numeroPli_;
    }

    @Override
    protected void restitute() {
        byte numeroPli_=(byte)(getTrickNumber().getSelectedIndex() - 1);
        byte numeroCarte_=(byte)NumberUtil.parseInt(getCardNumberTrick().getSelectedItem());
        numeroCarte_--;
        tricksHands.restituerMains(displayingBelote, numeroPli_,numeroCarte_, rules);
    }

    public TricksHandsBelote getTricksHands() {
        return tricksHands;
    }
}
