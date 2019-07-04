package cards.gui.dialogs;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JTextArea;

import cards.consts.Suit;
import cards.facade.Games;
import cards.gui.MainWindow;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import code.gui.Dialog;
import code.gui.Panel;
import code.gui.ScrollPane;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.EqList;
import code.util.StringList;

public final class DialogHelpTarot extends Dialog {

    private static final DialogHelpTarot DIALOG = new DialogHelpTarot();
    private static final String EMPTY="";
    private static final String POSSIBLE="P";
    private static final String OWNED="C";
    private static final String PLAYED="J";
    private static final String RETURN_LINE="\n";
    private static final String SPACE=" ";
    private static final String TAB="\t";
    private DialogHelpTarot() {
    }
    public static void setTitleDialog(MainWindow _fenetre,String _title) {
        DIALOG.setDialogIcon(_fenetre);
        DIALOG.setLocationRelativeTo(_fenetre);
        DIALOG.setTitle(_title);
    }
    /**Cartes possibles et certaines &#224 la belote et au tarot*/
    public static void setDialogueTarot(EnumMap<Suit,CustList<HandTarot>> _cartesPossibles,EnumMap<Suit,CustList<HandTarot>> _cartesCertaines,EnumMap<Suit,HandTarot> _repartitionJouees,StringList _pseudos, String _lg) {
        Panel container_=new Panel();
        container_.setLayout(new FlowLayout());
        Panel panneau2_=new Panel();
        Panel panneau3_;
        JTextArea zone_;
        panneau2_.setLayout(new BorderLayout());
        HandTarot tout_ = HandTarot.pileBase();
        EnumList<Suit> suits_ = new EnumList<Suit>(Suit.values());
        panneau3_=new Panel();
//        Suit couleur_;
//        Suit couleurMemo_=null;
        int nbPlayers_ = _pseudos.size();
        nbPlayers_++;
        //Dog hand
        for(int indicePseudo_=CustList.SECOND_INDEX;indicePseudo_<nbPlayers_;indicePseudo_++) {
            zone_=new JTextArea(EMPTY,84,15);
            zone_.setRows(84);
            zone_.setEditable(false);
            if(indicePseudo_<_pseudos.size()) {
                zone_.append(StringList.concat(_pseudos.get(indicePseudo_),RETURN_LINE));
            } else {
                zone_.append(RETURN_LINE);
            }
            for (Suit s: suits_) {
                HandTarot h_ = tout_.couleur(s);
                if(s != Suit.UNDEFINED) {
                    zone_.append(StringList.concat(Games.toString(s,_lg),RETURN_LINE));
                }
                for(CardTarot carte_:h_) {
                    zone_.append(TAB);
                    if(carte_ == CardTarot.EXCUSE) {
                        zone_.append(StringList.concat(Games.toString(carte_,_lg),SPACE));
                    } else {
                        zone_.append(StringList.concat(Games.getSymbol(carte_,_lg),SPACE));
                    }
                    if(_cartesPossibles.getVal(s).get(indicePseudo_).contient(carte_)) {
                        zone_.append(POSSIBLE);
                    }
                    if(_cartesCertaines.getVal(s).get(indicePseudo_).contient(carte_)) {
                        zone_.append(OWNED);
                    }
                    if(_repartitionJouees.getVal(s).contient(carte_)) {
                        zone_.append(PLAYED);
                    }
                    zone_.append(RETURN_LINE);
                }
            }
//            for(CardTarot carte_:tout_) {
//                couleur_=carte_.couleur();
//                if(couleur_!=couleurMemo_&&carte_ != CardTarot.EXCUSE) {
//                    zone_.append(couleur_.toString(Constants.getLanguage())+RETURN_LINE_CHAR);
//                }
//                zone_.append(TAB_CHAR+EMPTY);
//                if(carte_ == CardTarot.EXCUSE) {
//                    zone_.append(carte_.toString(Constants.getLanguage())+SPACE_CHAR);
//                } else {
//                    zone_.append(carte_.getSymbol(Constants.getLanguage())+SPACE_CHAR);
//                }
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
//                couleurMemo_=carte_.couleur();
//            }
            panneau3_.add(zone_);
        }
        panneau2_.add(panneau3_,BorderLayout.CENTER);
        ScrollPane ascenseur_=new ScrollPane(panneau2_);
        ascenseur_.setPreferredSize(new Dimension(600,600));
        container_.add(ascenseur_);
        DIALOG.setContentPane(container_);
        DIALOG.pack();
        DIALOG.voir();
    }
    private void voir() {
        setResizable(false);
        setVisible(true);
    }
}
