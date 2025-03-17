package cards.gui.panels;






import cards.gui.WindowCardsInt;
import cards.gui.labels.TarotCardConverter;
import cards.tarot.DisplayingTarot;
import cards.tarot.TrickTarot;
import cards.tarot.TricksHandsTarot;
import cards.tarot.enumerations.CardTarot;
import code.gui.*;
import code.util.CustList;
import code.util.StringList;

public final class PanelTricksHandsTarot extends PanelTricksHandsUniqCard<CardTarot> {

    private final TricksHandsTarot tricksHands;

    private final int numberPlayers;
    private final DisplayingTarot displayingTarot;
    public PanelTricksHandsTarot(ChangeableTitle _parent,
            TricksHandsTarot _tricksHands,
                                 int _numberPlayers,
            StringList _pseudos,
            DisplayingTarot _displayingTarot, WindowCardsInt _window) {
        super(_parent,_pseudos,_window,_numberPlayers,new TarotCardConverter());
        numberPlayers = _numberPlayers;
        displayingTarot = _displayingTarot;
        tricksHands = _tricksHands;
        init(_numberPlayers);
        changeTrick();
    }

    @Override
    protected int tricksSize() {
        return tricksHands.getTricks().size();
    }

    @Override
    protected CustList<CardTarot> list(int _i) {
        return tricksHands.getDistribution().hand(_i).getCards();
    }

    @Override
    protected CustList<CardTarot> derniereMain() {
        return tricksHands.getDistribution().derniereMain().getCards();
    }

    @Override
    protected int offset() {
        return 1;
    }

    @Override
    protected int nbPlayers() {
        return numberPlayers;
    }

    @Override
    protected CustList<TrickCardContentDto<CardTarot>> tricks(int _nb) {
        return convert(tricksHands.getTricks().left(_nb));
    }

    @Override
    protected CustList<TrickCardContentDto<CardTarot>> tricks() {
        return convert(tricksHands.getTricks());
    }

    private static CustList<TrickCardContentDto<CardTarot>> convert(CustList<TrickTarot> _trs) {
        CustList<TrickCardContentDto<CardTarot>> trs_ = new CustList<TrickCardContentDto<CardTarot>>();
        for (TrickTarot t: _trs) {
            trs_.add(new TrickCardContentDto<CardTarot>(t.getCartes().getCards(),t.getEntameur()));
        }
        return trs_;
    }

    @Override
    protected int restituteFull() {
        int numeroPli_= getTrickNumber().getSelectedIndex() - 1;
        tricksHands.restoreHandsAtSelectedNumberedTrick(displayingTarot, numberPlayers, numeroPli_);
        return numeroPli_;
    }

    @Override
    protected void restitute() {
        int numeroPli_= getTrickNumber().getSelectedIndex() - 1;
        int numeroCarte_=getCardNumberTrick().getSelectedIndex();
        numeroCarte_--;
        tricksHands.restoreHandsAtSelectedNumberedTrickWithSelectedCard(displayingTarot, numberPlayers, numeroPli_, numeroCarte_);
    }

    public TricksHandsTarot getTricksHands() {
        return tricksHands;
    }
}
