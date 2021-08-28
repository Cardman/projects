package cards.gui.dialogs;

import java.awt.Dimension;

import cards.belote.BidBeloteSuit;
import cards.belote.HandBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Order;
import cards.consts.Suit;
import cards.facade.Games;
import cards.gui.WindowCards;
import code.gui.*;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class DialogHelpBelote {

    private static final String EMPTY="";
    private static final String POSSIBLE="P";
    private static final String OWNED="C";
    private static final String PLAYED="J";
    private static final String RETURN_LINE="\n";
    private static final String SPACE=" ";
    private static final String TAB="\t";
    private final AbsDialog absDialog;
    private final AbsCompoFactory compo;

    public DialogHelpBelote(AbstractProgramInfos _fact) {
        absDialog = _fact.getFrameFactory().newDialog();
        compo = _fact.getCompoFactory();
    }

    private void voir() {
        absDialog.setResizable(false);
        absDialog.setVisible(true);
    }
    public static void setTitleDialog(WindowCards _fenetre, String _title) {
        _fenetre.getDialogHelpBelote().absDialog.setDialogIcon(_fenetre.getImageFactory(),_fenetre);
        _fenetre.getDialogHelpBelote().absDialog.setLocationRelativeTo(_fenetre);
        _fenetre.getDialogHelpBelote().absDialog.setTitle(_title);
    }
    public void setDialogueBelote(EnumMap<Suit, CustList<HandBelote>> _cartesPossibles,
                                         EnumMap<Suit, CustList<HandBelote>> _cartesCertaines,
                                         EnumMap<Suit, HandBelote> _repartitionJouees,
                                         Suit _couleurDemandee, BidBeloteSuit _bid,
                                         StringList _pseudos, String _lg) {
        AbsPanel container_=compo.newLineBox();
        AbsPanel panneau2_=compo.newBorder();
        AbsPanel panneau3_;
        AbsTextArea zone_;
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
        panneau3_=compo.newLineBox();
//        Suit couleur_;
        int nbBotPlayers_ = _pseudos.size();
        for(int indicePseudo_ = IndexConstants.SECOND_INDEX; indicePseudo_<nbBotPlayers_; indicePseudo_++) {
            zone_=compo.newTextArea(EMPTY,37,15);
            zone_.setEditable(false);
            zone_.append(StringUtil.concat(_pseudos.get(indicePseudo_),RETURN_LINE));
            for (Suit s: suits_) {
                HandBelote h_ = tout_.couleurs(_bid).getVal(s);
                zone_.append(StringUtil.concat(Games.toString(s,_lg),RETURN_LINE));
                for(CardBelote carte_:h_) {
                    zone_.append(TAB);
                    zone_.append(StringUtil.concat(Games.getSymbol(carte_,_lg),SPACE));
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
        panneau2_.add(panneau3_,GuiConstants.BORDER_LAYOUT_CENTER);
        AbsScrollPane ascenseur_= compo.newAbsScrollPane(panneau2_);
        ascenseur_.setPreferredSize(new Dimension(600,600));
        container_.add(ascenseur_);
        absDialog.setContentPane(container_);
        absDialog.pack();
        voir();
    }

}
