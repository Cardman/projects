package cards.gui.dialogs;


import cards.belote.BidBeloteSuit;
import cards.belote.DisplayingBelote;
import cards.belote.HandBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.util.TranslationsLg;
import code.threads.AbstractAtomicBoolean;
import code.util.*;

public final class DialogHelpBelote extends DialogHelpUniqCard<CardBelote> {

    private BidBeloteSuit bid;
    private Suit couleurDemandee;

    public DialogHelpBelote(AbstractProgramInfos _fact, AbstractAtomicBoolean _modal) {
        super(_fact, _modal);
    }

    @Override
    protected IdList<Suit> suits() {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        if(bid.getSuit() != Suit.UNDEFINED) {
            couleurs_.add(bid.getSuit());
            if(bid.getSuit() !=couleurDemandee) {
                //si on joue une couleur ordinaire
                couleurs_.add(couleurDemandee);
            }
            for(Suit couleur_: Suit.couleursOrdinaires()) {
                if(couleurs_.containsObj(couleur_)) {
                    continue;
                }
                couleurs_.add(couleur_);
            }
        } else {
            couleurs_.addAllElts(Suit.couleursOrdinaires());
        }
        return couleurs_;
    }

    @Override
    protected IdMap<Suit, IdList<CardBelote>> couleurs() {
        HandBelote tout_ = HandBelote.pileBase();
        DisplayingBelote dis_ = new DisplayingBelote();
        dis_.getDisplaying().setSuits(suits());
        dis_.getDisplaying().setDecreasing(true);
        tout_.trier(dis_, bid);
        IdMap<Suit, HandBelote> map_ = tout_.couleurs(bid);
        return convert(map_);
    }

    private IdMap<Suit, IdList<CardBelote>> convert(IdMap<Suit, HandBelote> _map) {
        IdMap<Suit, IdList<CardBelote>> cartes_ = new IdMap<Suit, IdList<CardBelote>>();
        for (EntryCust<Suit, HandBelote> e: _map.entryList()) {
            cartes_.addEntry(e.getKey(),e.getValue().getCards());
        }
        return cartes_;
    }

    @Override
    protected int id(CardBelote _carte) {
        return _carte.getId().nb();
    }

    public void setDialogueBelote(IdMap<Suit, CustList<HandBelote>> _cartesPossibles,
                                  IdMap<Suit, CustList<HandBelote>> _cartesCertaines,
                                  IdMap<Suit, HandBelote> _repartitionJouees,
                                  Suit _couleurDemandee, BidBeloteSuit _bid,
                                  StringList _pseudos, TranslationsLg _lg) {
        bid = _bid;
        couleurDemandee = _couleurDemandee;
        setDialogue(convertMap(_cartesPossibles),convertMap(_cartesCertaines),convert(_repartitionJouees),_pseudos,_lg);

//        StringMap<String> messages_ = ContainerSingleImpl.file(_lg);
//        AbsPanel container_= getCompo().newLineBox();
//        AbsPanel panneau2_= getCompo().newBorder();
//        AbsPanel panneau3_;
//        AbsTextArea zone_;
//        //une des couleurs domine
////        if(_bid.getSuit() != Suit.UNDEFINED) {
////            IdList<Suit> couleurs_ = new IdList<Suit>();
////            couleurs_.add(_bid.getSuit());
////            if(_bid.getSuit() !=_couleurDemandee) {
////                //si on joue une couleur ordinaire
////                couleurs_.add(_couleurDemandee);
////            }
////            for(Suit couleur_: Suit.couleursOrdinaires()) {
////                if(couleurs_.containsObj(couleur_)) {
////                    continue;
////                }
////                couleurs_.add(couleur_);
////            }
////            DisplayingBelote dis_ = new DisplayingBelote();
////            dis_.getDisplaying().setSuits(couleurs_);
////            dis_.getDisplaying().setDecreasing(true);
////            tout_.trier(dis_, _bid);
//////            tout_.trier(couleurs_, true, _bid.getSuit());
////        } else {
//////            Order ordre_ = _bid.getBid().getOrdre();
////            //non null
//////            tout_.setOrdre(ordre_);
////            DisplayingBelote dis_ = new DisplayingBelote();
////            dis_.getDisplaying().setSuits(Suit.couleursOrdinaires());
////            dis_.getDisplaying().setDecreasing(true);
////            tout_.trier(dis_, _bid);
//////            tout_.trier(Suit.couleursOrdinaires(), true, ordre_);
////        }
//        IdList<Suit> suits_ = suits();
//        IdMap<Suit, IdList<CardBelote>> map_ = couleurs();
//        panneau3_= getCompo().newLineBox();
////        Suit couleur_;
//        int nbBotPlayers_ = _pseudos.size();
//        for(int indicePseudo_ = IndexConstants.SECOND_INDEX; indicePseudo_<nbBotPlayers_; indicePseudo_++) {
//            zone_= getCompo().newTextArea(EMPTY,37,15);
//            zone_.setEditable(false);
//            zone_.append(StringUtil.concat(_pseudos.get(indicePseudo_),RETURN_LINE));
//            for (Suit s: suits_) {
//                IdList<CardBelote> h_ = map_.getVal(s);
////                HandBelote h_ = tout_.couleurs(_bid).getVal(s);
//                zone_.append(StringUtil.concat(Games.toString(s,_lg),RETURN_LINE));
//                for(CardBelote carte_:h_) {
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
////            for(CardBelote carte_:tout_) {
////                couleur_=carte_.couleur();
////                zone_.append(TAB_CHAR+EMPTY);
////                zone_.append(carte_.toString().split(SPACE_CHAR+EMPTY)[CustList.FIRST_INDEX]+SPACE_CHAR);
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

    public IdMap<Suit, CustList<IdList<CardBelote>>> convertMap(IdMap<Suit,CustList<HandBelote>> _list){
        if (_list == null) {
            return new IdMap<Suit, CustList<IdList<CardBelote>>>();
        }
        IdMap<Suit, CustList<IdList<CardBelote>>> map_ = new IdMap<Suit, CustList<IdList<CardBelote>>>();
        for (EntryCust<Suit,CustList<HandBelote>> e: _list.entryList()) {
            CustList<IdList<CardBelote>> ls_ = new CustList<IdList<CardBelote>>();
            for (HandBelote h:e.getValue()) {
                ls_.add(h.getCards());
            }
            map_.addEntry(e.getKey(),ls_);
        }
        return map_;
    }
}
