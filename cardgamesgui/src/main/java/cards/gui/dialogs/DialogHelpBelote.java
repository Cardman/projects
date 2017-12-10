package cards.gui.dialogs;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import cards.belote.BidBeloteSuit;
import cards.belote.HandBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Order;
import cards.consts.Suit;
import cards.gui.MainWindow;
import code.gui.Dialog;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.EqList;
import code.util.StringList;
import code.util.consts.Constants;

public final class DialogHelpBelote extends Dialog {

    private static final DialogHelpBelote DIALOG = new DialogHelpBelote();
    private static final String EMPTY="";
    private static final char POSSIBLE='P';
    private static final char OWNED='C';
    private static final char PLAYED='J';
    private static final char RETURN_LINE_CHAR='\n';
    private static final char SPACE_CHAR=' ';
    private static final char TAB_CHAR='\t';

    private DialogHelpBelote() {
    }

    private void voir() {
        setResizable(false);
        setVisible(true);
    }
    public static void setTitleDialog(MainWindow _fenetre,String _title) {
        DIALOG.setDialogIcon(_fenetre);
        DIALOG.setLocationRelativeTo(_fenetre);
        DIALOG.setTitle(_title);
    }
    public static void setDialogueBelote(EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,
            EnumMap<Suit,EqList<HandBelote>> _cartesCertaines,
            EnumMap<Suit,HandBelote> _repartitionJouees,
            Suit _couleurDemandee,BidBeloteSuit _bid,
            StringList _pseudos) {
        Container container_=new Container();
        container_.setLayout(new FlowLayout());
        JPanel panneau2_=new JPanel();
        JPanel panneau3_;
        JTextArea zone_;
        panneau2_.setLayout(new BorderLayout());
        HandBelote tout_ = HandBelote.pileBase();
        //une des couleurs domine
        if(_bid.getCouleur() != Suit.UNDEFINED) {
            EnumList<Suit> couleurs_ = new EnumList<Suit>();
            couleurs_.add(_bid.getCouleur());
            if(_bid.getCouleur()!=_couleurDemandee) {
                //si on joue une couleur ordinaire
                couleurs_.add(_couleurDemandee);
            }
            for(Suit couleur_: Suit.couleursOrdinaires()) {
                if(couleurs_.containsObj(couleur_)) {
                    continue;
                }
                couleurs_.add(couleur_);
            }
            tout_.trier(couleurs_, true, _bid.getCouleur());
        } else {
            Order ordre_ = _bid.getEnchere().getOrdre();
            //non null
            tout_.setOrdre(ordre_);
            tout_.trier(Suit.couleursOrdinaires(), true, ordre_);
        }
        EnumList<Suit> suits_ = Suit.couleursOrdinaires();
        panneau3_=new JPanel();
//        Suit couleur_;
        int nbBotPlayers_ = _pseudos.size();
        for(int indicePseudo_=CustList.SECOND_INDEX;indicePseudo_<nbBotPlayers_;indicePseudo_++) {
            zone_=new JTextArea(EMPTY,37,15);
            zone_.setRows(37);
            zone_.setEditable(false);
            zone_.append(_pseudos.get(indicePseudo_)+RETURN_LINE_CHAR);
            for (Suit s: suits_) {
                HandBelote h_ = tout_.couleurs(_bid).getVal(s);
                zone_.append(s.toString(Constants.getLanguage())+RETURN_LINE_CHAR);
                for(CardBelote carte_:h_) {
                    zone_.append(TAB_CHAR+EMPTY);
                    zone_.append(carte_.getSymbol(Constants.getLanguage())+SPACE_CHAR);
                    if(_cartesPossibles.getVal(s).get(indicePseudo_).contient(carte_)) {
                        zone_.append(POSSIBLE+EMPTY);
                    }
                    if(_cartesCertaines.getVal(s).get(indicePseudo_).contient(carte_)) {
                        zone_.append(OWNED+EMPTY);
                    }
                    if(_repartitionJouees.getVal(s).contient(carte_)) {
                        zone_.append(PLAYED+EMPTY);
                    }
                    zone_.append(RETURN_LINE_CHAR+EMPTY);
                }
            }
//            for(CardBelote carte_:tout_) {
//                couleur_=carte_.couleur();
//                zone_.append(TAB_CHAR+EMPTY);
//                zone_.append(carte_.toString().split(SPACE_CHAR+EMPTY)[CustList.FIRST_INDEX]+SPACE_CHAR);
//                if(_cartesPossibles.getVal(couleur_).get(indicePseudo_).contient(carte_)) {
//                    zone_.append(POSSIBLE+EMPTY);
//                }
//                if(_cartesCertaines.getVal(couleur_).get(indicePseudo_).contient(carte_)) {
//                    zone_.append(OWNED+EMPTY);
//                }
//                if(_repartitionJouees.getVal(couleur_).contient(carte_)) {
//                    zone_.append(PLAYED+EMPTY);
//                }
//                zone_.append(RETURN_LINE_CHAR+EMPTY);
//            }
            panneau3_.add(zone_);
        }
        panneau2_.add(panneau3_,BorderLayout.CENTER);
        JScrollPane ascenseur_=new JScrollPane(panneau2_);
        ascenseur_.setPreferredSize(new Dimension(600,600));
        container_.add(ascenseur_);
        DIALOG.setContentPane(container_);
        DIALOG.pack();
        DIALOG.voir();
    }
}
