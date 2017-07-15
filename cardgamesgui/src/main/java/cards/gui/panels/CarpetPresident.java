package cards.gui.panels;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import code.util.CustList;
import code.util.NumberMap;
import code.util.StringList;
import code.util.consts.Constants;
import cards.gui.labels.GraphicPresidentCard;
import cards.president.HandPresident;
import cards.president.enumerations.Playing;

public class CarpetPresident extends JPanel {

    private static final String SEPARATOR = ":";
//    private static final String EMPTY="";
//    private static final char RETURN_LINE_CHAR='\n';

    private JPanel playersPanel;
    private CustList<JLabel> labels = new CustList<JLabel>();

    private JPanel centerDeck;
    private CustList<GraphicPresidentCard> listCards = new CustList<GraphicPresidentCard>();

    private NumberMap<Byte,Playing> cards = new NumberMap<Byte,Playing>();
    private StringList pseudos = new StringList();

//    private byte nextPlayer;

    /**sens de distribution des cartes*/
//    private Boolean horaire=false;
    /**max number of cards*/
    private int number;
    public CarpetPresident() {}

    public void initTapisPresident(StringList _pseudos, NumberMap<Byte,Playing> _status,int _nombre) {
        setLayout(new BorderLayout());
        number = _nombre;
        pseudos = _pseudos;
        cards = new NumberMap<Byte,Playing>(_status);
        centerDeck = new JPanel();
        centerDeck.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        centerDeck.setPreferredSize(GraphicPresidentCard.getDimensionForSeveralCards(_nombre));
        listCards.clear();
        boolean entered_ = false;
        for (int c = CustList.FIRST_INDEX; c < number; c++) {
            GraphicPresidentCard cg_=new GraphicPresidentCard(SwingConstants.RIGHT,!entered_);
            cg_.setPreferredSize(GraphicPresidentCard.getDimension(entered_));
            cg_.setVisible(false);
            centerDeck.add(cg_);
            listCards.add(cg_);
            entered_ = true;
        }
        centerDeck.setBackground(new Color(0, 125, 0));
        add(centerDeck, BorderLayout.CENTER);
        playersPanel = new JPanel();
        playersPanel.setLayout(new BoxLayout(playersPanel, BoxLayout.PAGE_AXIS));
        for (String n: pseudos) {
            JLabel l_ = new JLabel(n);
            l_.setOpaque(true);
            labels.add(l_);
            playersPanel.add(l_);
        }
        add(playersPanel, BorderLayout.WEST);
    }

//    public void retirerCartes() {
//        centerDeck.removeAll();
//        centerDeck.repaint();
//    }

    public void setTalonPresident() {
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
        repaintValidate();
    }

    public void repaintValidate() {
        centerDeck.repaint();
        centerDeck.validate();
    }

    public void setTalonPresident(HandPresident _m) {
        if (_m.estVide()) {
            return;
        }
        int len_ = _m.total();
        if (len_ > listCards.size()) {
            return;
        }
        for (int i = CustList.FIRST_INDEX; i <len_; i++) {
            listCards.get(i).setVisible(true);
            listCards.get(i).setCarteEnJeu(_m.carte(i));
//            listCards.get(i).repaint();
        }
        for (int i = len_; i < number; i++) {
            listCards.get(i).setVisible(false);
        }
        repaintValidate();
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

    public void setStatus(NumberMap<Byte,Playing> _status, byte _nextPlayer) {
        cards.putAllMap(_status);
        for (byte p: cards.getKeys()) {
            JLabel l_ = labels.get(p);
            if (p == _nextPlayer) {
                l_.setBackground(Color.YELLOW);
            } else {
                l_.setBackground(Color.WHITE);
            }
            l_.setText(pseudos.get(p)+SEPARATOR+cards.getVal(p).toString(Constants.getLanguage()));
        }
        repaintValidate();
    }
}
