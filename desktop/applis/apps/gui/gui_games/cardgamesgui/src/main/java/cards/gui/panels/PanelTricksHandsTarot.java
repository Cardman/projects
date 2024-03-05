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
import code.util.core.NumberUtil;

public class PanelTricksHandsTarot extends PanelTricksHandsUniqCard<CardTarot> {

    private final TricksHandsTarot tricksHands;

    private final byte numberPlayers;
    private final DisplayingTarot displayingTarot;
    public PanelTricksHandsTarot(ChangeableTitle _parent,
            TricksHandsTarot _tricksHands,
            byte _numberPlayers,
            StringList _pseudos,
            DisplayingTarot _displayingTarot, WindowCardsInt _window) {
        super(_parent,_pseudos,_window,_numberPlayers,new TarotCardConverter());
        numberPlayers = _numberPlayers;
        displayingTarot = _displayingTarot;
        tricksHands = _tricksHands;
        init(_numberPlayers);
        changeTrick();
        changeCard();
    }

    @Override
    protected int tricksSize() {
        return tricksHands.getTricks().size();
    }

    @Override
    protected CustList<CardTarot> list(byte _i) {
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
    protected CustList<CustList<CardTarot>> tricks(int _nb) {
        return convert(tricksHands.getTricks().left(_nb));
    }

    @Override
    protected CustList<CustList<CardTarot>> tricks() {
        return convert(tricksHands.getTricks());
    }

    private CustList<CustList<CardTarot>> convert(CustList<TrickTarot> _trs) {
        CustList<CustList<CardTarot>> trs_ = new CustList<CustList<CardTarot>>();
        for (TrickTarot t: _trs) {
            CustList<CardTarot> l_ = new CustList<CardTarot>();
            for (CardTarot c: t) {
                l_.add(c);
            }
            trs_.add(l_);
        }
        return trs_;
    }

    @Override
    protected CustList<Integer> tricksStarters() {
        CustList<Integer> trs_ = new CustList<Integer>();
        for (TrickTarot t:tricksHands.getTricks()) {
            trs_.add((int) t.getEntameur());
        }
        return trs_;
    }

    @Override
    protected int restituteFull() {
        byte numeroPli_=(byte)(getTrickNumber().getSelectedIndex() - 1);
        tricksHands.restoreHandsAtSelectedNumberedTrick(displayingTarot, numberPlayers, numeroPli_);
        return numeroPli_;
    }

    @Override
    protected void restitute() {
        byte numeroPli_=(byte)(getTrickNumber().getSelectedIndex() - 1);
        byte numeroCarte_=(byte)NumberUtil.parseInt(getCardNumberTrick().getSelectedItem());
        numeroCarte_--;
        tricksHands.restoreHandsAtSelectedNumberedTrickWithSelectedCard(displayingTarot, numberPlayers, numeroPli_, numeroCarte_);
    }

    public TricksHandsTarot getTricksHands() {
        return tricksHands;
    }
}
