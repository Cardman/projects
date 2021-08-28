package cards.gui.dialogs;



import cards.consts.Suit;
import cards.facade.Games;
import cards.gui.WindowCards;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import code.gui.*;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class DialogHelpTarot {

    private static final String EMPTY="";
    private static final String POSSIBLE="P";
    private static final String OWNED="C";
    private static final String PLAYED="J";
    private static final String RETURN_LINE="\n";
    private static final String SPACE=" ";
    private static final String TAB="\t";
    private final AbsDialog absDialog;
    private final AbsCompoFactory compo;

    public DialogHelpTarot(AbstractProgramInfos _fact) {
        absDialog = _fact.getFrameFactory().newDialog();
        compo = _fact.getCompoFactory();
    }

    public static void setTitleDialog(WindowCards _fenetre, String _title) {
        _fenetre.getDialogHelpTarot().absDialog.setDialogIcon(_fenetre.getImageFactory(),_fenetre);
        _fenetre.getDialogHelpTarot().absDialog.setLocationRelativeTo(_fenetre);
        _fenetre.getDialogHelpTarot().absDialog.setTitle(_title);
    }
    /**Cartes possibles et certaines &#224 la belote et au tarot*/
    public void setDialogueTarot(EnumMap<Suit, CustList<HandTarot>> _cartesPossibles,
                                        EnumMap<Suit, CustList<HandTarot>> _cartesCertaines, EnumMap<Suit, HandTarot> _repartitionJouees,
                                        StringList _pseudos, String _lg) {
        AbsPanel container_=compo.newLineBox();
        AbsPanel panneau2_=compo.newBorder();
        AbsPanel panneau3_;
        AbsTextArea zone_;
        HandTarot tout_ = HandTarot.pileBase();
        EnumList<Suit> suits_ = new EnumList<Suit>(Suit.values());
        panneau3_=compo.newLineBox();
//        Suit couleur_;
//        Suit couleurMemo_=null;
        int nbPlayers_ = _pseudos.size();
        nbPlayers_++;
        //Dog hand
        for(int indicePseudo_ = IndexConstants.SECOND_INDEX; indicePseudo_<nbPlayers_; indicePseudo_++) {
            zone_=compo.newTextArea(EMPTY,84,15);
            zone_.setEditable(false);
            if(indicePseudo_<_pseudos.size()) {
                zone_.append(StringUtil.concat(_pseudos.get(indicePseudo_),RETURN_LINE));
            } else {
                zone_.append(RETURN_LINE);
            }
            for (Suit s: suits_) {
                HandTarot h_ = tout_.couleur(s);
                if(s != Suit.UNDEFINED) {
                    zone_.append(StringUtil.concat(Games.toString(s,_lg),RETURN_LINE));
                }
                for(CardTarot carte_:h_) {
                    zone_.append(TAB);
                    if(carte_ == CardTarot.EXCUSE) {
                        zone_.append(StringUtil.concat(Games.toString(carte_,_lg),SPACE));
                    } else {
                        zone_.append(StringUtil.concat(Games.getSymbol(carte_,_lg),SPACE));
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
        panneau2_.add(panneau3_,GuiConstants.BORDER_LAYOUT_CENTER);
        AbsScrollPane ascenseur_=compo.newAbsScrollPane(panneau2_);
        ascenseur_.setPreferredSize(new MetaDimension(600,600));
        container_.add(ascenseur_);
        absDialog.setContentPane(container_);
        absDialog.pack();
        voir();
    }
    private void voir() {
        absDialog.setResizable(false);
        absDialog.setVisible(true);
    }

}
