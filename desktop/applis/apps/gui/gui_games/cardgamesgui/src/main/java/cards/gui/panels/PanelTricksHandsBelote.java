package cards.gui.panels;






import cards.belote.*;
import cards.belote.enumerations.CardBelote;
import cards.gui.WindowCardsInt;
import cards.gui.labels.BeloteCardConverter;
import code.gui.*;
import code.util.CustList;
import code.util.StringList;

public final class PanelTricksHandsBelote extends PanelTricksHandsUniqCard<CardBelote> {

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
    protected CustList<TrickCardContentDto<CardBelote>> tricks(int _nb) {
        return convert(tricksHands.getTricks().left(_nb));
    }

    @Override
    protected CustList<TrickCardContentDto<CardBelote>> tricks() {
        return convert(tricksHands.getTricks());
    }

    private static CustList<TrickCardContentDto<CardBelote>> convert(CustList<TrickBelote> _trs) {
        CustList<TrickCardContentDto<CardBelote>> trs_ = new CustList<TrickCardContentDto<CardBelote>>();
        for (TrickBelote t: _trs) {
            trs_.add(new TrickCardContentDto<CardBelote>(t.getCartes().getCards(),t.getEntameur()));
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
        byte numeroCarte_=(byte)getCardNumberTrick().getSelectedIndex();
        numeroCarte_--;
        tricksHands.restituerMains(displayingBelote, numeroPli_,numeroCarte_, rules);
    }

    public TricksHandsBelote getTricksHands() {
        return tricksHands;
    }
}
