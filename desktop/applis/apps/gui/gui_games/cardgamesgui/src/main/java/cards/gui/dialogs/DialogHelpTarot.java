package cards.gui.dialogs;



import cards.consts.Suit;
import cards.facade.Games;
import cards.gui.containers.ContainerSingleImpl;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import code.gui.*;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.cards.MessagesGuiCards;
import code.sml.util.TranslationsLg;
import code.threads.AbstractAtomicBoolean;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class DialogHelpTarot extends DialogHelpCards {

    private static final String EMPTY="";
    private static final String RETURN_LINE="\n";
    private static final String SPACE=" ";
    private static final String TAB="\t";

    public DialogHelpTarot(AbstractProgramInfos _fact, AbstractAtomicBoolean _modal) {
        super(_fact, _modal);
    }

    /**Cartes possibles et certaines &#224 la belote et au tarot*/
    public void setDialogueTarot(IdMap<Suit, CustList<HandTarot>> _cartesPossibles,
                                        IdMap<Suit, CustList<HandTarot>> _cartesCertaines, IdMap<Suit, HandTarot> _repartitionJouees,
                                        StringList _pseudos, TranslationsLg _lg) {
        StringMap<String> messages_ = ContainerSingleImpl.file(_lg);
        AbsPanel container_= getCompo().newLineBox();
        AbsPanel panneau2_= getCompo().newBorder();
        AbsPanel panneau3_;
        AbsTextArea zone_;
        HandTarot tout_ = HandTarot.pileBase();
        IdList<Suit> suits_ = new IdList<Suit>(Suit.toutesCouleurs());
        panneau3_= getCompo().newLineBox();
//        Suit couleur_;
//        Suit couleurMemo_=null;
        int nbPlayers_ = _pseudos.size();
        nbPlayers_++;
        //Dog hand
        for(int indicePseudo_ = IndexConstants.SECOND_INDEX; indicePseudo_<nbPlayers_; indicePseudo_++) {
            zone_= getCompo().newTextArea(EMPTY,84,15);
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
                    zone_.append(StringUtil.concat(Games.toString(carte_,_lg),SPACE));
                    if(_cartesPossibles.getVal(s).get(indicePseudo_).contient(carte_)) {
                        zone_.append(messages_.getVal(MessagesGuiCards.MAIN_POSSIBLE));
                    }
                    if(_cartesCertaines.getVal(s).get(indicePseudo_).contient(carte_)) {
                        zone_.append(messages_.getVal(MessagesGuiCards.MAIN_OWNED));
                    }
                    if(_repartitionJouees.getVal(s).contient(carte_)) {
                        zone_.append(messages_.getVal(MessagesGuiCards.MAIN_PLAYED));
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
        AbsScrollPane ascenseur_= getCompo().newAbsScrollPane(panneau2_);
        ascenseur_.setPreferredSize(new MetaDimension(600,600));
        container_.add(ascenseur_);
        getAbsDialog().setContentPane(container_);
        getAbsDialog().pack();
        getAbsDialog().setVisible(true);
    }

}
