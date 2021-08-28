package cards.gui.panels;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.SwingConstants;

import cards.facade.Games;
import cards.gui.labels.GraphicPresidentCard;
import cards.president.HandPresident;
import cards.president.enumerations.Playing;
import code.gui.AbsPanel;
import code.gui.AbsPlainLabel;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;
import code.util.*;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public class CarpetPresident {

    private static final String SEPARATOR = ":";
//    private static final String EMPTY="";
//    private static final char RETURN_LINE_CHAR='\n';

    private AbsPanel playersPanel;
    private final CustList<AbsPlainLabel> labels = new CustList<AbsPlainLabel>();

    private AbsPanel centerDeck;
    private final CustList<GraphicPresidentCard> listCards = new CustList<GraphicPresidentCard>();

    private ByteMap<Playing> cards = new ByteMap<Playing>();
    private StringList pseudos = new StringList();

//    private byte nextPlayer;

    /**sens de distribution des cartes*/
//    private Boolean horaire=false;
    /**max number of cards*/
    private int number;
    private AbsPanel container;

    public void initTapisPresident(String _lg, StringList _pseudos, ByteMap<Playing> _status, int _nombre, AbsCompoFactory _compoFactory) {
        container = _compoFactory.newBorder();
        number = _nombre;
        pseudos = _pseudos;
        cards = new ByteMap<Playing>(_status);
        centerDeck= _compoFactory.newLineBox();
        centerDeck.setPreferredSize(GraphicPresidentCard.getDimensionForSeveralCards(_nombre));
        listCards.clear();
        boolean entered_ = false;
        for (int c = IndexConstants.FIRST_INDEX; c < number; c++) {
            GraphicPresidentCard cg_=new GraphicPresidentCard(_lg,SwingConstants.RIGHT,!entered_, _compoFactory);
            cg_.setPreferredSize(GraphicPresidentCard.getDimension(entered_));
            cg_.setVisible(false);
            centerDeck.add(cg_);
            listCards.add(cg_);
            entered_ = true;
        }
        centerDeck.setBackground(new Color(0, 125, 0));
        container.add(centerDeck, BorderLayout.CENTER);
        playersPanel = _compoFactory.newPageBox();
        for (String n: pseudos) {
            AbsPlainLabel l_ = _compoFactory.newPlainLabel(n);
            l_.setOpaque(true);
            labels.add(l_);
            playersPanel.add(l_);
        }
        container.add(playersPanel, BorderLayout.WEST);
    }

//    public void retirerCartes() {
//        centerDeck.removeAll();
//        centerDeck.repaint();
//    }

    public void setTalonPresident(AbstractImageFactory _fact) {
//        centerDeck.removeAll();
        for (GraphicPresidentCard g: listCards) {
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
        centerDeck.repaintChildren(_fact);
    }

    public void setTalonPresident(AbstractImageFactory _fact,String _lg, HandPresident _m) {
        if (_m.estVide()) {
            return;
        }
        int len_ = _m.total();
        if (len_ > listCards.size()) {
            return;
        }
        for (int i = IndexConstants.FIRST_INDEX; i <len_; i++) {
            listCards.get(i).setVisible(true);
            listCards.get(i).setCarteEnJeu(_fact,_lg,_m.carte(i));
//            listCards.get(i).repaint();
        }
        for (int i = len_; i < number; i++) {
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

    public void setStatus(AbstractImageFactory _fact,String _lg, ByteMap<Playing> _status, byte _nextPlayer) {
        cards.putAllMap(_status);
        for (byte p: cards.getKeys()) {
            AbsPlainLabel l_ = labels.get(p);
            if (p == _nextPlayer) {
                l_.setBackground(Color.YELLOW);
            } else {
                l_.setBackground(Color.WHITE);
            }
            l_.setText(StringUtil.concat(pseudos.get(p),SEPARATOR, Games.toString(cards.getVal(p),_lg)));
        }
        repaintValidate(_fact);
    }

    public AbsPanel getContainer() {
        return container;
    }
}
