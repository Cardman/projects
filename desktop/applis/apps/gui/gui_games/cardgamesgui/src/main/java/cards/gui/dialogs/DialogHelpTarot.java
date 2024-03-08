package cards.gui.dialogs;


import cards.consts.Suit;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.util.TranslationsLg;
import code.threads.AbstractAtomicBoolean;
import code.util.*;

public final class DialogHelpTarot extends DialogHelpUniqCard<CardTarot> {

    public DialogHelpTarot(AbstractProgramInfos _fact, AbstractAtomicBoolean _modal) {
        super(_fact, _modal);
    }

    @Override
    protected IdList<Suit> suits() {
        return Suit.toutesCouleurs();
    }

    @Override
    protected IdMap<Suit, IdList<CardTarot>> couleurs() {
        HandTarot tout_ = HandTarot.pileBase();
        IdMap<Suit, HandTarot> map_ = tout_.couleurs();
        return convert(map_);
    }

    private IdMap<Suit, IdList<CardTarot>> convert(IdMap<Suit, HandTarot> _map) {
        IdMap<Suit, IdList<CardTarot>> cartes_ = new IdMap<Suit, IdList<CardTarot>>();
        for (EntryCust<Suit, HandTarot> e: _map.entryList()) {
            cartes_.addEntry(e.getKey(),e.getValue().getCards());
        }
        return cartes_;
    }

    @Override
    protected int id(CardTarot _carte) {
        return _carte.getId().nb();
    }

    /**Cartes possibles et certaines &#224 la belote et au tarot*/
    public void setDialogueTarot(IdMap<Suit, CustList<HandTarot>> _cartesPossibles,
                                        IdMap<Suit, CustList<HandTarot>> _cartesCertaines, IdMap<Suit, HandTarot> _repartitionJouees,
                                        StringList _pseudos, TranslationsLg _lg) {
        setDialogue(convertMap(_cartesPossibles),convertMap(_cartesCertaines),convert(_repartitionJouees),_pseudos,_lg);
//        StringMap<String> messages_ = ContainerSingleImpl.file(_lg);
//        AbsPanel container_= getCompo().newLineBox();
//        AbsPanel panneau2_= getCompo().newBorder();
//        AbsPanel panneau3_;
//        AbsTextArea zone_;
//        IdMap<Suit, IdList<CardTarot>> couleurs_ = couleurs();
//        IdList<Suit> suits_ = new IdList<Suit>(Suit.toutesCouleurs());
//        panneau3_= getCompo().newLineBox();
////        Suit couleur_;
////        Suit couleurMemo_=null;
//        int nbPlayers_ = _pseudos.size();
//        nbPlayers_++;
//        //Dog hand
//        for(int indicePseudo_ = IndexConstants.SECOND_INDEX; indicePseudo_<nbPlayers_; indicePseudo_++) {
//            zone_= getCompo().newTextArea(EMPTY,84,15);
//            zone_.setEditable(false);
//            if(indicePseudo_<_pseudos.size()) {
//                zone_.append(StringUtil.concat(_pseudos.get(indicePseudo_),RETURN_LINE));
//            } else {
//                zone_.append(RETURN_LINE);
//            }
//            for (Suit s: suits_) {
//                IdList<CardTarot> h_ = couleurs_.getVal(s);
//                if(s != Suit.UNDEFINED) {
//                    zone_.append(StringUtil.concat(Games.toString(s,_lg),RETURN_LINE));
//                }
//                for(CardTarot carte_:h_) {
//                    zone_.append(TAB);
//                    zone_.append(StringUtil.concat(Games.toString(carte_,_lg),SPACE));
//                    if(_cartesPossibles.getVal(s).get(indicePseudo_).contient(carte_)) {
//                        zone_.append(messages_.getVal(MessagesGuiCards.MAIN_POSSIBLE));
//                    }
//                    if(_cartesCertaines.getVal(s).get(indicePseudo_).contient(carte_)) {
//                        zone_.append(messages_.getVal(MessagesGuiCards.MAIN_OWNED));
//                    }
//                    if(_repartitionJouees.getVal(s).contient(carte_)) {
//                        zone_.append(messages_.getVal(MessagesGuiCards.MAIN_PLAYED));
//                    }
//                    zone_.append(RETURN_LINE);
//                }
//            }
////            for(CardTarot carte_:tout_) {
////                couleur_=carte_.couleur();
////                if(couleur_!=couleurMemo_&&carte_ != CardTarot.EXCUSE) {
////                    zone_.append(couleur_.toString(Constants.getLanguage())+RETURN_LINE_CHAR);
////                }
////                zone_.append(TAB_CHAR+EMPTY);
////                if(carte_ == CardTarot.EXCUSE) {
////                    zone_.append(carte_.toString(Constants.getLanguage())+SPACE_CHAR);
////                } else {
////                    zone_.append(carte_.getSymbol(Constants.getLanguage())+SPACE_CHAR);
////                }
////                if(_cartesPossibles.getVal(couleur_).get(indicePseudo_).contient(carte_)) {
////                    zone_.append(POSSIBLE+EMPTY);
////                }
////                if(_cartesCertaines.getVal(couleur_).get(indicePseudo_).contient(carte_)) {
////                    zone_.append(OWNED+EMPTY);
////                }
////                if(_repartitionJouees.getVal(couleur_).contient(carte_)) {
////                    zone_.append(PLAYED+EMPTY);
////                }
////                zone_.append(RETURN_LINE_CHAR+EMPTY);
////                couleurMemo_=carte_.couleur();
////            }
//            panneau3_.add(zone_);
//        }
//        panneau2_.add(panneau3_,GuiConstants.BORDER_LAYOUT_CENTER);
//        AbsScrollPane ascenseur_= getCompo().newAbsScrollPane(panneau2_);
//        ascenseur_.setPreferredSize(new MetaDimension(600,600));
//        container_.add(ascenseur_);
//        getAbsDialog().setContentPane(container_);
//        getAbsDialog().pack();
//        getAbsDialog().setVisible(true);
    }

    public IdMap<Suit, CustList<IdList<CardTarot>>> convertMap(IdMap<Suit,CustList<HandTarot>> _list){
        if (_list == null) {
            return new IdMap<Suit, CustList<IdList<CardTarot>>>();
        }
        IdMap<Suit, CustList<IdList<CardTarot>>> map_ = new IdMap<Suit, CustList<IdList<CardTarot>>>();
        for (EntryCust<Suit,CustList<HandTarot>> e: _list.entryList()) {
            CustList<IdList<CardTarot>> ls_ = new CustList<IdList<CardTarot>>();
            for (HandTarot h:e.getValue()) {
                ls_.add(h.getCards());
            }
            map_.addEntry(e.getKey(),ls_);
        }
        return map_;
    }
}
