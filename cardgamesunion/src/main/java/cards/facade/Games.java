package cards.facade;
import cards.belote.BidBeloteSuit;
import cards.belote.GameBelote;
import cards.belote.HandBelote;
import cards.belote.RulesBelote;
import cards.belote.enumerations.*;
import cards.belote.sml.DocumentWriterBeloteUtil;
import cards.consts.*;
import cards.president.GamePresident;
import cards.president.HandPresident;
import cards.president.RulesPresident;
import cards.president.enumerations.CardPresident;
import cards.president.enumerations.EqualtyPlaying;
import cards.president.enumerations.Playing;
import cards.president.enumerations.PresidentResoucesAccess;
import cards.president.sml.DocumentWriterPresidentUtil;
import cards.tarot.GameTarot;
import cards.tarot.GameTarotCommon;
import cards.tarot.HandTarot;
import cards.tarot.RulesTarot;
import cards.tarot.enumerations.*;
import cards.tarot.sml.DocumentWriterTarotUtil;
import code.format.Format;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.EnumMap;
import code.util.Numbers;
import code.util.StringList;

public final class Games {
    private static final String FOLDER = "resources_cards/classes";
    private static final String SEPARATOR = " - ";

    private static final String EMPTY = "";

    private static final String SPACE = " ";

    private static final String RETURN_LINE = "\n";
    private static final String GAME_BELOTE = "cards.belote.gamebelote";
    private static final String BELOTE_FILE_NAME = Format.getClassProperties(GAME_BELOTE);
    private static final String BELOTE_OVER_TRUMP_FOE = "overTrumpFoe";

    private static final String BELOTE_OVER_TRUMP_PARTNER = "overTrumpPartner";

    private static final String BELOTE_PLAY_STRONGER_CARD = "playStrongerCard";

    private static final String BELOTE_PLAY_SUIT = "playSuit";

    private static final String BELOTE_TRUMP_FOE = "trumpFoe";

    private static final String BELOTE_UNDER_TRUMP_FOE = "underTrumpFoe";

    private static final String BELOTE_UNDER_TRUMP_PARTNER = "underTrumpPartner";
    private static final String GAME_PRESIDENT = "cards.president.gamepresident";
    private static final String PRESIDENT_FILE_NAME = Format.getClassProperties(GAME_PRESIDENT);
    private static final String PRESIDENT_SKIPPED = "skipped";

    private static final String PRESIDENT_HAVE_PASSED = "havePassed";

    private static final String PRESIDENT_HAS_TO_EQUAL_OR_SKIP = "hasToEqualOrSkip";

    private static final String PRESIDENT_CANNOT_USE_LOWER_OR_EQ = "cannotUseLowerOrEq";

    private static final String PRESIDENT_CANNOT_USE_LOWER = "cannotUseLower";

    private static final String PRESIDENT_HAVE_PLAY_GIVEN_NUMBER_CARDS = "hasPlayGivenNumberCards";

    private static final String PRESIDENT_CANNOT_PASS = "cannotPass";

    private static final String TAROT_HANDFUL_EXCUSE = "handfulExcuse";

    private static final String TAROT_HANDFUL_NOT_ENOUGH_TRUMPS = "handfulNotEnoughTrumps";

    private static final String TAROT_HANDFUL_TOO_MANY_TRUMPS = "handfulTooManyTrumps";

    private static final String TAROT_TOO_MANY_CARDS = "tooManyCards";

    private static final String TAROT_DISCARDED_TRUMP = "discardedTrump";

    private static final String TAROT_NO_DISCARDED_CHARACTER = "noDiscardedCharacter";

    private static final String TAROT_NO_DISCARDED_OUDLER = "noDiscardedOudler";

    private static final String TAROT_NO_DISCARDED_TRUMP = "noDiscardedTrump";

    private static final String TAROT_FIRST_TRICK = "firstTrick";

    private static final String TAROT_OVERTRUMP = "overtrump";

    private static final String TAROT_PLAY_STRONGER_CARD = "playStrongerCard";

    private static final String TAROT_PLAY_SUIT = "playSuit";

    private static final String TAROT_TRUMP = "trump";

    private static final String TAROT_UNDERTRUMP = "undertrump";
    private static final String GAME_TAROT = "cards.tarot.gametarot";
    private static final String TAROT_FILE_NAME = Format.getClassProperties(GAME_TAROT);
    private CustList<GameBelote> partiesBelote = new CustList<GameBelote>();

    private CustList<GameTarot> partiesTarot = new CustList<GameTarot>();

    private CustList<GamePresident> partiesPresident = new CustList<GamePresident>();

    private RulesBelote rulesBelote;

    private RulesTarot rulesTarot;

    private RulesPresident rulesPresident;

    public boolean enCoursDePartie(){
        return enCoursDePartieBelote() || enCoursDePartieTarot() || enCoursDePartiePresident();
    }
    public boolean enCoursDePartieBelote(){
        return !partiesBelote.isEmpty();
    }

    public boolean enCoursDePartieTarot(){
        return !partiesTarot.isEmpty();
    }

    public boolean enCoursDePartiePresident(){
        return !partiesPresident.isEmpty();
    }

    public GameBelote partieBelote(){
        return partiesBelote.first();
    }

    public GameTarot partieTarot(){
        return partiesTarot.first();
    }

    public GamePresident partiePresident() {
        return partiesPresident.first();
    }

    public void finirParties(){
        partiesBelote.clear();
        partiesTarot.clear();
        partiesPresident.clear();
    }
    public void jouerBelote(GameBelote _partieBelote){
        finirParties();
        partiesBelote.add(_partieBelote);
    }
    public void jouerTarot(GameTarot _partieTarot){
        finirParties();
        partiesTarot.add(_partieTarot);
    }
    public void jouerPresident(GamePresident _partiePresident){
        finirParties();
        partiesPresident.add(_partiePresident);
    }

    public boolean isSameTeam(Numbers<Byte> _players) {
        if (enCoursDePartieBelote()) {
            return partieBelote().isSameTeam(_players);
        }
        if (enCoursDePartieTarot()) {
            return partieTarot().getTeamsRelation().isSameTeam(_players);
        }
        if (enCoursDePartiePresident()) {
//            return false;
            return _players.size() == CustList.ONE_ELEMENT;
        }
        return true;
    }
    public void sauvegarderPartieEnCours(String _nomFichier){
        if(enCoursDePartieBelote()){
            GameBelote game_ = partieBelote();
            StreamTextFile.saveTextFile(_nomFichier, DocumentWriterBeloteUtil.setGameBelote(game_));
        }
        if(enCoursDePartieTarot()){
            GameTarot game_ = partieTarot();
            StreamTextFile.saveTextFile(_nomFichier, DocumentWriterTarotUtil.setGameTarot(game_));
        }
        if(enCoursDePartiePresident()){
            GamePresident game_ = partiePresident();
            StreamTextFile.saveTextFile(_nomFichier, DocumentWriterPresidentUtil.setGamePresident(game_));
        }
    }
    public static StringBuilder autoriseMessEcartDe(GameTarot _g,ReasonDiscard _r,CardTarot _c, String _loc) {
        if(_r == ReasonDiscard.TOO_MUCH) {
            return new StringBuilder(Format.formatter(FOLDER, TAROT_FILE_NAME, _loc, TAROT_TOO_MANY_CARDS));
        }
        HandTarot m = _g.getDistribution().main(_g.getPreneur());
        EnumMap<Suit,HandTarot> rep_ = m.couleurs();
        int atoutsExcuse_ = GameTarotCommon.atoutsAvecExcuse(rep_);
        int total_ = atoutsExcuse_;
        int rois_ = 0;
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            HandTarot main_ = rep_.getVal(couleur_);
            if (!main_.estVide()) {
                total_ += main_.total();
                for (CardTarot c: main_) {
                    if (c.getNomFigure() == CardChar.KING) {
                        rois_++;
                    }
                }
            }
        }
        int nbDog_ = _g.getDistribution()
                .derniereMain().total() - (_g.getCardsToBeDiscarded() - 1);
        StringBuilder m_ = new StringBuilder();
        if (total_ - rois_ - atoutsExcuse_ < nbDog_) {
            if(_c.estUnBout()) {
                m_.append(Format.formatter(FOLDER, TAROT_FILE_NAME, _loc, TAROT_NO_DISCARDED_OUDLER, toString(_c,_loc))).append(RETURN_LINE);
            }
            if(_c.couleur() == Suit.TRUMP && !_c.estUnBout()) {
                m_.append(Format.formatter(FOLDER, TAROT_FILE_NAME, _loc, TAROT_DISCARDED_TRUMP, toString(_c,_loc))).append(RETURN_LINE);
            }
            if(_c.getNomFigure() == CardChar.KING) {
                m_.append(Format.formatter(FOLDER, TAROT_FILE_NAME, _loc, TAROT_NO_DISCARDED_CHARACTER, toString(_c,_loc), toString(CardChar.KING,_loc))).append(RETURN_LINE);
            }
            return m_;
        } else {
            if(_c.estUnBout()) {
                m_.append(Format.formatter(FOLDER, TAROT_FILE_NAME, _loc, TAROT_NO_DISCARDED_OUDLER, toString(_c,_loc))).append(RETURN_LINE);
            }
            if(_c.couleur() == Suit.TRUMP) {
                m_.append(Format.formatter(FOLDER, TAROT_FILE_NAME, _loc, TAROT_NO_DISCARDED_TRUMP, toString(_c,_loc))).append(RETURN_LINE);
            }
            if(_c.getNomFigure() == CardChar.KING) {
                m_.append(Format.formatter(FOLDER, TAROT_FILE_NAME, _loc, TAROT_NO_DISCARDED_CHARACTER, toString(_c,_loc), toString(CardChar.KING,_loc))).append(RETURN_LINE);
            }
        }
        return m_;
    }

    public static String isValidHandfulMessage(GameTarot _g,Handfuls _h, HandTarot _hand,HandTarot _excludedCards, String _loc) {
        int nbTrumps_ = _g.getRules().getPoigneesAutorisees().getVal(_h);
        if (_hand.total()==nbTrumps_&&(!_hand.contient(CardTarot.excuse())||_excludedCards.estVide())) {
            return EMPTY;
        }
        if(_hand.total()>nbTrumps_) {
            return Format.formatter(FOLDER, TAROT_FILE_NAME, _loc, TAROT_HANDFUL_TOO_MANY_TRUMPS, Integer.toString(_hand.total()-nbTrumps_));
        }
        if(_hand.total()<nbTrumps_) {
            return Format.formatter(FOLDER, TAROT_FILE_NAME, _loc, TAROT_HANDFUL_NOT_ENOUGH_TRUMPS, Integer.toString(nbTrumps_-_hand.total()));
        }
        return Format.formatter(FOLDER, TAROT_FILE_NAME, _loc, TAROT_HANDFUL_EXCUSE);
    }
    public static String autoriseBelote(GameBelote _g,String _loc) {
        HandBelote main_=_g.getDistribution().main(_g.playerHavingToPlay());
        EnumMap<Suit,HandBelote> e_ = main_.couleurs(_g.getBid());
        byte numero_=_g.playerHavingToPlay();
        HandBelote m= _g.getProgressingTrick().getCartes();
        Suit couleurDemandee_= _g.getProgressingTrick().couleurDemandee();
        Suit couleurAtout_=_g.couleurAtout();
        byte ramasseurVirtuel_= _g.getProgressingTrick().getRamasseurPliEnCours(_g.getNombreDeJoueurs(), _g.getBid());
        CardBelote carteForte_= _g.getProgressingTrick().carteDuJoueur(ramasseurVirtuel_,_g.getNombreDeJoueurs());
        HandBelote leadingSuit_ = GameBelote.main(e_,couleurDemandee_);
        if(_g.getBid().getCouleurDominante()) {
            HandBelote trumps_ = GameBelote.main(e_,couleurAtout_);
            byte valeurForte_=carteForte_.strength(couleurDemandee_, _g.getBid());
            if(couleurAtout_==couleurDemandee_) {
                //Nombre d'atouts dans la main du joueur
                if(trumps_.derniereCarte().strength(couleurDemandee_, _g.getBid())>valeurForte_) {
                    return Format.formatter(FOLDER, BELOTE_FILE_NAME, _loc, BELOTE_PLAY_SUIT, toString(couleurDemandee_,_loc));
                }
                if(trumps_.premiereCarte().strength(couleurDemandee_, _g.getBid())<valeurForte_) {
                    return Format.formatter(FOLDER, BELOTE_FILE_NAME, _loc, BELOTE_PLAY_SUIT, toString(couleurDemandee_,_loc));
                }
                return Format.formatter(FOLDER, BELOTE_FILE_NAME, _loc, BELOTE_PLAY_STRONGER_CARD, toString(carteForte_,_loc));
            }
            if(!leadingSuit_.estVide()) {
                return Format.formatter(FOLDER, BELOTE_FILE_NAME, _loc, BELOTE_PLAY_SUIT, toString(couleurDemandee_,_loc));
            }
            if(_g.memeEquipe(ramasseurVirtuel_, numero_)) {
                /*Le partenaire est maitre temporairement*/
                if(_g.surCoupeObligatoirePartenaire()) {
                    if(_g.sousCoupeObligatoirePartenaire()) {
                        if(trumps_.premiereCarte().strength(couleurDemandee_, _g.getBid())<valeurForte_) {
                            return Format.formatter(FOLDER, BELOTE_FILE_NAME, _loc, BELOTE_UNDER_TRUMP_PARTNER, toString(couleurAtout_,_loc));
                        }
                    }
                    if(trumps_.derniereCarte().strength(couleurDemandee_, _g.getBid())>valeurForte_) {
                        return Format.formatter(FOLDER, BELOTE_FILE_NAME, _loc, BELOTE_OVER_TRUMP_PARTNER, toString(couleurAtout_,_loc));
                    }
                    if(trumps_.premiereCarte().strength(couleurDemandee_, _g.getBid())>valeurForte_) {
                        return Format.formatter(FOLDER, BELOTE_FILE_NAME, _loc, BELOTE_PLAY_STRONGER_CARD, toString(carteForte_,_loc));
                    }
                }
                if(_g.sousCoupeObligatoirePartenaire()) {
                    if(trumps_.premiereCarte().strength(couleurDemandee_, _g.getBid())<valeurForte_) {
                        return Format.formatter(FOLDER, BELOTE_FILE_NAME, _loc, BELOTE_UNDER_TRUMP_PARTNER, toString(couleurAtout_,_loc));
                    }
                }
            }
            HandBelote trumpsTrick_ = GameBelote.main(m.couleurs(_g.getBid()),couleurAtout_);
            if(trumpsTrick_.estVide()) {
                /*PliBelote non coupe*/
                return Format.formatter(FOLDER, BELOTE_FILE_NAME, _loc, BELOTE_TRUMP_FOE, toString(carteForte_,_loc));
            }
            /*PliBelote coupe par un adversaire*/
            if(trumps_.derniereCarte().strength(couleurDemandee_, _g.getBid())>valeurForte_) {
                return Format.formatter(FOLDER, BELOTE_FILE_NAME, _loc, BELOTE_OVER_TRUMP_FOE, toString(couleurAtout_,_loc));
            }
            if(trumps_.premiereCarte().strength(couleurDemandee_, _g.getBid())>valeurForte_) {
                return Format.formatter(FOLDER, BELOTE_FILE_NAME, _loc, BELOTE_PLAY_STRONGER_CARD, toString(carteForte_,_loc));
            }
            return Format.formatter(FOLDER, BELOTE_FILE_NAME, _loc, BELOTE_UNDER_TRUMP_FOE, toString(couleurAtout_,_loc));
        }
        if(_g.getBid().ordreCouleur()) {
            return Format.formatter(FOLDER, BELOTE_FILE_NAME, _loc, BELOTE_PLAY_SUIT, toString(couleurDemandee_,_loc));
        }
        byte valeurForte_=carteForte_.strength(couleurDemandee_, _g.getBid());
        if(leadingSuit_.derniereCarte().strength(couleurDemandee_, _g.getBid())>valeurForte_
                ||leadingSuit_.premiereCarte().strength(couleurDemandee_, _g.getBid())<valeurForte_) {
            return Format.formatter(FOLDER, BELOTE_FILE_NAME, _loc, BELOTE_PLAY_SUIT, toString(couleurDemandee_,_loc));
        }
        return Format.formatter(FOLDER, BELOTE_FILE_NAME, _loc, BELOTE_PLAY_STRONGER_CARD, toString(carteForte_,_loc));
    }
    public static StringBuilder autorisePresident(GamePresident _g,byte _player, CardPresident _card, byte _nb, String _loc) {
        Playing playing_ = _g.getStatus(_player);
        if (playing_ == Playing.PASS) {
            return new StringBuilder(Format.formatter(FOLDER, PRESIDENT_FILE_NAME, _loc, PRESIDENT_HAVE_PASSED));
        }
        if (playing_ == Playing.SKIPPED) {
            return new StringBuilder(Format.formatter(FOLDER, PRESIDENT_FILE_NAME, _loc, PRESIDENT_SKIPPED));
        }
        HandPresident l_ = _g.getProgressingTrick().getBestCards();
        StringBuilder errorPlaying_ = new StringBuilder();
        byte str_ = l_.premiereCarte().strength(_g.isReversed());
        if (playing_ == Playing.HAS_TO_EQUAL) {
            if (_card.strength(_g.isReversed()) != str_) {
                errorPlaying_.append(Format.formatter(FOLDER, PRESIDENT_FILE_NAME, _loc, PRESIDENT_HAS_TO_EQUAL_OR_SKIP, toString(l_.premiereCarte(),_loc))).append(RETURN_LINE);
            }
        } else if (_g.getRules().getEqualty() == EqualtyPlaying.FORBIDDEN) {
            if (_card.strength(_g.isReversed()) <= str_) {
                errorPlaying_.append(Format.formatter(FOLDER, PRESIDENT_FILE_NAME, _loc, PRESIDENT_CANNOT_USE_LOWER_OR_EQ, toString(l_.premiereCarte(),_loc))).append(RETURN_LINE);
            }
        } else {
            if (_card.strength(_g.isReversed()) <= str_) {
                errorPlaying_.append(Format.formatter(FOLDER, PRESIDENT_FILE_NAME, _loc, PRESIDENT_CANNOT_USE_LOWER, toString(l_.premiereCarte(),_loc))).append(RETURN_LINE);
            }
        }
        if (_nb != _g.getProgressingTrick().getNombreDeCartesParJoueur()) {
            errorPlaying_.append(Format.formatter(FOLDER, PRESIDENT_FILE_NAME, _loc, PRESIDENT_HAVE_PLAY_GIVEN_NUMBER_CARDS, Long.toString(_g.getProgressingTrick().getNombreDeCartesParJoueur()))).append(RETURN_LINE);
        }
        return errorPlaying_;
    }

    public static String canPassMess(GamePresident _g, String _loc) {
        HandPresident b_ = _g.getProgressingTrick().getBestCards();
        return Format.formatter(FOLDER, PRESIDENT_FILE_NAME, _loc, PRESIDENT_CANNOT_PASS, toString(b_.premiereCarte(),_loc));
    }
    public static String autoriseTarot(GameTarot _g, String _loc) {
        HandTarot main_ = _g.getDistribution().main(_g.playerHavingToPlay());
        EnumMap<Suit,HandTarot> repartition_ = main_.couleurs();
        Suit couleurDemandee_ = _g.getProgressingTrick().couleurDemandee();
        if (_g.getProgressingTrick().couleurDemandee() == Suit.UNDEFINED) {
            Suit couleurAppele_ = _g.getCalledCards().premiereCarte().couleur();
            return Format.formatter(FOLDER, TAROT_FILE_NAME, _loc, TAROT_FIRST_TRICK, toString(couleurAppele_,_loc), toString(_g.getCalledCards().premiereCarte(),_loc));
        }
        if (Suit.couleursOrdinaires().containsObj(couleurDemandee_)
                && !repartition_.getVal(couleurDemandee_).estVide()) {
            return Format.formatter(FOLDER, TAROT_FILE_NAME, _loc, TAROT_PLAY_SUIT, toString(couleurDemandee_,_loc));
        }
        HandTarot atoutsJoues_ = _g.getProgressingTrick().getCartes().couleurs().getVal(Suit.TRUMP);
        if (atoutsJoues_.estVide()) {
            return Format.formatter(FOLDER, TAROT_FILE_NAME, _loc, TAROT_TRUMP, toString(couleurDemandee_,_loc));
        }
        byte nombreDeJoueurs_ = _g.getNombreDeJoueurs();
        byte ramasseurVirtuel_ = _g.getProgressingTrick().getRamasseur(nombreDeJoueurs_);
        CardTarot carteForte_ = _g.getProgressingTrick().carteDuJoueur(
                ramasseurVirtuel_, nombreDeJoueurs_);
        byte valeurForte_ = carteForte_.strength(couleurDemandee_);
        if (repartition_.getVal(Suit.TRUMP).premiereCarte().strength(couleurDemandee_) < valeurForte_) {
            if (Suit.couleursOrdinaires().containsObj(couleurDemandee_)) {
                return Format.formatter(FOLDER, TAROT_FILE_NAME, _loc, TAROT_UNDERTRUMP, toString(couleurDemandee_,_loc));
            }
            return Format.formatter(FOLDER, TAROT_FILE_NAME, _loc, TAROT_PLAY_SUIT, toString(couleurDemandee_,_loc));
        }
        if (valeurForte_ < repartition_.getVal(Suit.TRUMP).derniereCarte().strength(couleurDemandee_)) {
            if (Suit.couleursOrdinaires().containsObj(couleurDemandee_)) {
                return Format.formatter(FOLDER, TAROT_FILE_NAME, _loc, TAROT_OVERTRUMP, toString(couleurDemandee_,_loc));
            }
            return Format.formatter(FOLDER, TAROT_FILE_NAME, _loc, TAROT_PLAY_SUIT, toString(couleurDemandee_,_loc));
        }
        return Format.formatter(FOLDER, TAROT_FILE_NAME, _loc, TAROT_PLAY_STRONGER_CARD, toString(couleurDemandee_,_loc));
    }

    public static String toString(HandBelote _b,String _lg) {
        StringList retString_= new StringList();
        for (CardBelote c: _b.getCards()) {
            retString_.add(toString(c,_lg));
        }
        return retString_.join(SEPARATOR);
    }
    public static String toString(HandPresident _b, String _lg) {
        StringList retString_= new StringList();
        for (CardPresident c: _b.getCards()) {
            retString_.add(toString(c,_lg));
        }
        return retString_.join(SEPARATOR);
    }
    public static String toString(HandTarot _t, String _lg) {
        StringList retString_= new StringList();
        for (CardTarot c: _t.getCards()) {
            retString_.add(toString(c,_lg));
        }
        return retString_.join(SEPARATOR);
    }
    public static String getSymbol(CardTarot _c,String _loc) {
        if (_c.getNomFigure() != CardChar.UNDEFINED) {
            return getSymbol(_c.getNomFigure(),_loc);
        }
        return String.valueOf(_c.getForce());
    }

    public static String getSymbol(CardBelote _c, String _loc) {
        if (_c.getNomFigure() != CardChar.UNDEFINED) {
            return getSymbol(_c.getNomFigure(),_loc);
        }
        return String.valueOf(_c.valeur());
    }
    public static String getSymbol(CardPresident _c, String _loc) {
        if (_c.getNomFigure() != CardChar.UNDEFINED) {
            return getSymbol(_c.getNomFigure(),_loc);
        }
        return String.valueOf(_c.valeur());
    }
    public static String getSymbol(CardChar _c,String _loc) {
        String folderName_ = CoreResourcesAccess.NOM_DOSSIER;
        String fileName_ = CoreResourcesAccess.SYMBOL_CARDS_TXT;
        return Format.getConstanteLangue(folderName_, fileName_, _loc, CoreResourcesAccess.CHARS, _c.name());
    }
    public static String toString(CardChar _c,String _loc) {
        String folderName_ = CoreResourcesAccess.NOM_DOSSIER;
        String fileName_ = CoreResourcesAccess.NOM_FICHIER;
        return Format.getConstanteLangue(folderName_,fileName_, _loc, CoreResourcesAccess.CHARS, _c.name());
    }
    public static String toString(BeloteTrumpPartner _b, String _locale){
        return Format.getConstanteLangue(BeloteResoucesAccess.NOM_DOSSIER,BeloteResoucesAccess.NOM_FICHIER, _locale, BeloteResoucesAccess.BELOTE_TRUMP_PART,_b.name());
    }
    public static String toString(BidBeloteSuit _b, String _loc) {
        StringBuilder pts_ = new StringBuilder();
        if (_b.getPoints() > 0) {
            pts_.append(SPACE);
            pts_.append(_b.getPoints());
        }
        if (_b.getCouleurDominante()) {
            pts_.insert(0, toString(_b.getSuit(),_loc));
            return pts_.toString();
        }
        pts_.insert(0,toString(_b.getBid(),_loc));
        return pts_.toString();
    }
    public static String toString(BidBelote _b, String _locale){
        return Format.getConstanteLangue(BeloteResoucesAccess.NOM_DOSSIER,BeloteResoucesAccess.NOM_FICHIER, _locale, BeloteResoucesAccess.BELOTE_BID,_b.name());
    }
    public static String toString(DeclaresBelote _b, String _locale){
        return Format.getConstanteLangue(BeloteResoucesAccess.NOM_DOSSIER,BeloteResoucesAccess.NOM_FICHIER, _locale,BeloteResoucesAccess.BELOTE_DECLARES, _b.name());
    }
    public static String toString(CardBelote _b, String _locale){
        return Format.getConstanteLangue(BeloteResoucesAccess.NOM_DOSSIER,BeloteResoucesAccess.NOM_FICHIER, _locale, BeloteResoucesAccess.BELOTE_CARD, _b.name());
    }
    public static String toString(DeclaresBeloteRebelote _b, String _locale){
        return Format.getConstanteLangue(BeloteResoucesAccess.NOM_DOSSIER,BeloteResoucesAccess.NOM_FICHIER, _locale, BeloteResoucesAccess.BELOTE_DECLARES_BEL_REB, _b.name());
    }
    public static String toString(BonusBelote _b, String _locale){
        return Format.getConstanteLangue(BeloteResoucesAccess.NOM_DOSSIER,BeloteResoucesAccess.NOM_FICHIER, _locale, BeloteResoucesAccess.BELOTE_BONUS,_b.name());
    }
    public static String toString(CardPresident _b, String _locale){
        return Format.getConstanteLangue(PresidentResoucesAccess.NOM_DOSSIER,PresidentResoucesAccess.NOM_FICHIER, _locale, PresidentResoucesAccess.PRESIDENT_CARD,_b.name());
    }
    public static String toString(Playing _b, String _locale){
        return Format.getConstanteLangue(PresidentResoucesAccess.NOM_DOSSIER,PresidentResoucesAccess.NOM_FICHIER, _locale, PresidentResoucesAccess.PRESIDENT_PLAY,_b.name());
    }
    public static String toString(EqualtyPlaying _b, String _locale){
        return Format.getConstanteLangue(PresidentResoucesAccess.NOM_DOSSIER,PresidentResoucesAccess.NOM_FICHIER, _locale, PresidentResoucesAccess.PRESIDENT_EQUAL_PLAY,_b.name());
    }
    public static String toString(ModeTarot _b, String _locale){
        return Format.getConstanteLangue(TarotResoucesAccess.NOM_DOSSIER, TarotResoucesAccess.NOM_FICHIER, _locale, TarotResoucesAccess.TAROT_MODE, _b.name());
    }
    public static String toString(ChoiceTarot _b, String _locale){
        return Format.getConstanteLangue(TarotResoucesAccess.NOM_DOSSIER,TarotResoucesAccess.NOM_FICHIER, _locale, TarotResoucesAccess.TAROT_CHOICE, _b.name());
    }
    public static String toString(BidTarot _b, String _locale){
        return Format.getConstanteLangue(TarotResoucesAccess.NOM_DOSSIER,TarotResoucesAccess.NOM_FICHIER, _locale, TarotResoucesAccess.TAROT_BID,_b.name());
    }
    public static String toString(CardTarot _b, String _locale){
        return Format.getConstanteLangue(TarotResoucesAccess.NOM_DOSSIER,TarotResoucesAccess.NOM_FICHIER, _locale, TarotResoucesAccess.TAROT_CARD,_b.name());
    }
    public static String toString(Handfuls _b, String _locale){
        return Format.getConstanteLangue(TarotResoucesAccess.NOM_DOSSIER,TarotResoucesAccess.NOM_FICHIER, _locale, TarotResoucesAccess.TAROT_HANDFULS,_b.name());
    }
    public static String toString(Miseres _b, String _locale){
        return Format.getConstanteLangue(TarotResoucesAccess.NOM_DOSSIER,TarotResoucesAccess.NOM_FICHIER, _locale, TarotResoucesAccess.TAROT_MISERES, _b.name());
    }
    public static String toString(DealingTarot _b, String _locale){
        return Format.getConstanteLangue(TarotResoucesAccess.NOM_DOSSIER, TarotResoucesAccess.NOM_FICHIER, _locale, TarotResoucesAccess.TAROT_DEAL, _b.name());
    }
    public static String toString(EndDealTarot _b, String _locale){
        return Format.getConstanteLangue(TarotResoucesAccess.NOM_DOSSIER, TarotResoucesAccess.NOM_FICHIER, _locale, TarotResoucesAccess.TAROT_END,_b.name());
    }
    public static String toString(BonusTarot _b, String _locale){
        return Format.getConstanteLangue(TarotResoucesAccess.NOM_DOSSIER,TarotResoucesAccess.NOM_FICHIER, _locale, TarotResoucesAccess.TAROT_BONUS,_b.name());
    }
    public static String toString(Suit _b, String _locale) {
        String folderName_ = CoreResourcesAccess.NOM_DOSSIER;
        String fileName_ = CoreResourcesAccess.NOM_FICHIER;
        return Format.getConstanteLangue(folderName_,fileName_, _locale, CoreResourcesAccess.SUIT, _b.name());
    }

    public static String toString(Status _b, String _locale) {
        String folderName_ = CoreResourcesAccess.NOM_DOSSIER;
        String fileName_ = CoreResourcesAccess.NOM_FICHIER;
        return Format.getConstanteLangue(folderName_,fileName_, _locale, CoreResourcesAccess.STATUS,_b.name());
    }
    public static String toString(MixCardsChoice _b, String _locale) {
        String folderName_ = CoreResourcesAccess.NOM_DOSSIER;
        String fileName_ = CoreResourcesAccess.NOM_FICHIER;
        return Format.getConstanteLangue(folderName_,fileName_, _locale, CoreResourcesAccess.MIX,_b.name());
    }
    public RulesBelote getRulesBelote() {
        return rulesBelote;
    }
    public void setRulesBelote(RulesBelote _rulesBelote) {
        rulesBelote = _rulesBelote;
    }
    public RulesTarot getRulesTarot() {
        return rulesTarot;
    }
    public void setRulesTarot(RulesTarot _rulesTarot) {
        rulesTarot = _rulesTarot;
    }
    public RulesPresident getRulesPresident() {
        return rulesPresident;
    }
    public void setRulesPresident(RulesPresident _rulesPresident) {
        rulesPresident = _rulesPresident;
    }
    public void setPartiesBelote(CustList<GameBelote> _listGameBelote) {
        partiesBelote = _listGameBelote;
    }
    public void setPartiesTarot(CustList<GameTarot> _listGameTarot) {
        partiesTarot = _listGameTarot;
    }
    public void setPartiesPresident(CustList<GamePresident> _listGamePresident) {
        partiesPresident = _listGamePresident;
    }
    public CustList<GameBelote> getPartiesBelote() {
        return partiesBelote;
    }
    public CustList<GameTarot> getPartiesTarot() {
        return partiesTarot;
    }
    public CustList<GamePresident> getPartiesPresident() {
        return partiesPresident;
    }
}
