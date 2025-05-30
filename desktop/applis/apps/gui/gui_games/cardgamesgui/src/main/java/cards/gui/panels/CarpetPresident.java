package cards.gui.panels;





import cards.facade.Games;
import cards.gui.labels.AbsMetaLabelCard;
import cards.gui.labels.GraphicCard;
import cards.gui.labels.PresidentCardConverter;
import cards.president.HandPresident;
import cards.president.enumerations.CardPresident;
import cards.president.enumerations.Playing;
import code.gui.AbsPanel;
import code.gui.AbsPlainLabel;
import code.gui.GuiConstants;
import code.gui.files.MessagesGuiFct;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.sml.util.TranslationsLg;
import code.util.CustList;
import code.util.*;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public class CarpetPresident {

    private static final String SEPARATOR = ":";
//    private static final String EMPTY="";
//    private static final char RETURN_LINE_CHAR='\n';

    private final CustList<AbsPlainLabel> labels = new CustList<AbsPlainLabel>();

    private final CustList<GraphicCard<CardPresident>> listCards = new CustList<GraphicCard<CardPresident>>();

    private IntMap<Playing> cards = new IntMap<Playing>();
    private StringList pseudos = new StringList();

//    private byte nextPlayer;

    /**sens de distribution des cartes*/
//    private Boolean horaire=false;
    /**max number of cards*/
    private AbsPanel container;

    public void initTapisPresident(TranslationsLg _lg, StringList _pseudos, IntMap<Playing> _status, int _nombre, AbsCompoFactory _compoFactory) {
        container = _compoFactory.newBorder();
        pseudos = _pseudos;
        cards = new IntMap<Playing>(_status);
        AbsPanel centerDeck_ = _compoFactory.newLineBox();
        centerDeck_.setPreferredSize(Carpet.getDimensionForSeveralCards(_nombre));
        listCards.clear();
        boolean entered_ = false;
        for (int c = IndexConstants.FIRST_INDEX; c < _nombre; c++) {
            GraphicCard<CardPresident> cg_=new GraphicCard<CardPresident>(new PresidentCardConverter(), !entered_, _compoFactory, _lg);
            cg_.setPreferredSize(Carpet.getDimension(entered_));
            cg_.setVisible(false);
            centerDeck_.add(cg_.getPaintableLabel());
            listCards.add(cg_);
            entered_ = true;
        }
        centerDeck_.setBackground(GuiConstants.newColor(0, 125, 0));
        container.add(centerDeck_, MessagesGuiFct.BORDER_LAYOUT_CENTER);
        AbsPanel playersPanel_ = _compoFactory.newPageBox();
        for (String n: pseudos) {
            AbsPlainLabel l_ = _compoFactory.newPlainLabel(n);
            l_.setOpaque(true);
            labels.add(l_);
            playersPanel_.add(l_);
        }
        container.add(playersPanel_, MessagesGuiFct.BORDER_LAYOUT_WEST);
    }

//    public void retirerCartes() {
//        centerDeck.removeAll();
//        centerDeck.repaint();
//    }

    public void setTalonPresident(AbstractImageFactory _fact) {
//        centerDeck.removeAll();
        for (AbsMetaLabelCard g: listCards) {
            g.setVisible(false);
        }
//        boolean entered_ = false;
//        for (int c = CustList.FIRST_INDEX; c < number; c++) {
//            GraphicPresidentCard cg_=new GraphicPresidentCard(SwingConstants.RIGHT,!entered_);
//            cg_.setJeu();
//            cg_.setPreferredSize(GraphicPresidentCard.getMaxDimension());
//            centerDeck.add(cg_);
//            entered_ = true;
//        }
        repaintValidate(_fact);
    }

    public void repaintValidate(AbstractImageFactory _fact) {
        for (AbsMetaLabelCard c: listCards) {
            AbsMetaLabelCard.paintCard(_fact,c);
        }
    }

    public void setTalonPresident(AbstractImageFactory _fact, TranslationsLg _lg, HandPresident _m) {
        if (_m.estVide()) {
            return;
        }
        int len_ = NumberUtil.min(_m.total(),listCards.size());
//        if (len_ > listCards.size()) {
//            return;
//        }
        for (int i = IndexConstants.FIRST_INDEX; i <len_; i++) {
            listCards.get(i).setVisible(true);
            CardPresident card_ = _m.carte(i);
            listCards.get(i).setCarteEnJeu(_fact,_lg, card_);
//            listCards.get(i).repaint();
        }
        int c_ = listCards.size();
        for (int i = len_; i < c_; i++) {
            listCards.get(i).setVisible(false);
        }
        repaintValidate(_fact);
//        centerDeck.removeAll();
//        boolean entered_ = false;
//        for (CardPresident c: _m) {
//            GraphicPresidentCard cg_=new GraphicPresidentCard(c,SwingConstants.RIGHT,!entered_);
//            cg_.setPreferredSize(GraphicPresidentCard.getMaxDimension());
//            centerDeck.add(cg_);
//            entered_ = true;
//        }
//        centerDeck.repaint();
//        centerDeck.validate();
    }

    public void setStatus(AbstractImageFactory _fact, TranslationsLg _lg, IntMap<Playing> _status, int _nextPlayer) {
        cards.putAllMap(_status);
        for (int p: cards.getKeys()) {
            AbsPlainLabel l_ = labels.get(p);
            if (p == _nextPlayer) {
                l_.setBackground(GuiConstants.YELLOW);
            } else {
                l_.setBackground(GuiConstants.WHITE);
            }
            l_.setText(StringUtil.concat(pseudos.get(p),SEPARATOR, Games.toString(cards.getVal(p),_lg)));
        }
        repaintValidate(_fact);
    }

    public AbsPanel getContainer() {
        return container;
    }
}
