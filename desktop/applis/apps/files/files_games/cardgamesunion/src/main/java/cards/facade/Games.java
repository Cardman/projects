package cards.facade;
import cards.belote.*;
import cards.belote.enumerations.*;
import cards.belote.sml.*;
import cards.consts.*;
import cards.president.*;
import cards.president.enumerations.*;
import cards.president.sml.*;
import cards.tarot.*;
import cards.tarot.enumerations.*;
import cards.tarot.sml.*;
import code.format.Format;
import code.scripts.messages.cards.*;
import code.sml.util.*;
import code.stream.StreamTextFile;
import code.stream.core.TechStreams;
import code.util.*;
import code.util.core.*;

public final class Games {
    public static final String CARDS = "cards";
    public static final String EDITOR_CARDS = "editor";
    private static final String FOLDER = "resources_cards/classes";
    private static final String SEPARATOR = " - ";

    private static final String EMPTY = "";

    private static final String RETURN_LINE = "\n";
    private static final String PROPERTIES = "properties";
    private static final String BELOTE_FILE_NAME = "cards/belote/gamebelote."+PROPERTIES;
    private static final String BELOTE_OVER_TRUMP_FOE = "overTrumpFoe";

    private static final String BELOTE_OVER_TRUMP_PARTNER = "overTrumpPartner";

    private static final String BELOTE_PLAY_STRONGER_CARD = "playStrongerCard";

    private static final String BELOTE_PLAY_SUIT = "playSuit";

    private static final String BELOTE_TRUMP_FOE = "trumpFoe";

    private static final String BELOTE_UNDER_TRUMP_FOE = "underTrumpFoe";

    private static final String BELOTE_UNDER_TRUMP_PARTNER = "underTrumpPartner";
    private static final String PRESIDENT_FILE_NAME = "cards/president/gamepresident."+PROPERTIES;
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

//    private static final String TAROT_DISCARDED_TRUMP = "discardedTrump";

    private static final String TAROT_NO_DISCARDED_CHARACTER = "noDiscardedCharacter";

    private static final String TAROT_NO_DISCARDED_OUDLER = "noDiscardedOudler";

    private static final String TAROT_NO_DISCARDED_TRUMP = "noDiscardedTrump";

    private static final String TAROT_OVERTRUMP = "overtrump";

    private static final String TAROT_PLAY_FIRST_TRICK="firstTrick";

    private static final String TAROT_PLAY_STRONGER_CARD = "playStrongerCard";

    private static final String TAROT_PLAY_SUIT = "playSuit";

    private static final String TAROT_TRUMP = "trump";

    private static final String TAROT_UNDERTRUMP = "undertrump";
    private static final String TAROT_FILE_NAME = "cards/tarot/gametarot."+PROPERTIES;
    private static final String RESULTS_BELOTE = "cards.belote.resultsbelote";
    private static final String RESULTS_PRESIDENT = "cards.president.resultspresident";
    private static final String RESULTS_TAROT = "cards.tarot.resultstarot";
    private static final String RESULTS_PAGE = "resultsPage";
    private static final String DETAIL_RESULTS_PAGE = "detailResultsPage";
    private static final String RESOURCES_CLASS_PATH = "resources_cards/classes";
    private CustList<GameBelote> partiesBelote = new CustList<GameBelote>();

    private CustList<GameTarot> partiesTarot = new CustList<GameTarot>();

    private CustList<GamePresident> partiesPresident = new CustList<GamePresident>();

    private RulesBelote rulesBelote;

    private RulesTarot rulesTarot;

    private RulesPresident rulesPresident;

    public static TranslationsAppli initAppliTr(TranslationsLg _lgs) {
        TranslationsAppli a_ = new TranslationsAppli();
        _lgs.getMapping().addEntry(CARDS, a_);
        return a_;
    }

    public static TranslationsAppli getAppliTr(TranslationsLg _lgs) {
        return _lgs.getMapping().getVal(CARDS);
    }
    public static void enTr(TranslationsAppli _lgs) {
        _lgs.getMapping().addEntry(EDITOR_CARDS,MessagesEditorCards.en());
    }
    public static void frTr(TranslationsAppli _lgs) {
        _lgs.getMapping().addEntry(EDITOR_CARDS,MessagesEditorCards.fr());
    }
    public static String getConstanteLangue(String _file, String _group, String _nomConstante) {
        String fichier_ = MessagesCardsAll.ms().getVal(_file);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
        return Format.getConstanteLangue(Format.concatParts(_group,_nomConstante), fichier_);
    }

    /**@see getConstante
     @return la chaine de caracteres retournee par la methode getConstante qui est formatte avec _variables
      * @param _fichier
     * @param _nomConstante nom de la constante en rapport avec la chaine de caracteres
     * @param _variables liste de parametres remplacant les expressions comme {0} {1}...    */
    private static String formatter(String _fichier, String _nomConstante, String... _variables){
        String constante_ = Format.getConstanteLangue(_nomConstante, _fichier);
        return StringUtil.simpleStringsFormat(constante_, _variables);
    }

    public static void setMessages(ResultsBelote _r, String _loc) {
        _r.getRes().setLoc(_loc);
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(RESOURCES_CLASS_PATH, _loc, RESULTS_BELOTE);
        String loadedResourcesMessages_ = MessBeloteGr.ms().getVal(fileName_);
        StringMap<String> messages_ = ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
//        StringMap<String> messages_ = ExtractFromFiles.getMessagesFromLocaleClass(RESOURCES_CLASS_PATH, _loc, RESULTS_BELOTE);
        _r.getRes().setGlobalResultsPageTitle(messages_.getVal(RESULTS_PAGE));
        _r.getRes().setDetailResultsTitle(messages_.getVal(DETAIL_RESULTS_PAGE));
    }

    public static void setMessages(ResultsPresident _r, String _loc) {
        _r.getRes().setLoc(_loc);
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(RESOURCES_CLASS_PATH, _loc, RESULTS_PRESIDENT);
        String loadedResourcesMessages_ = MessPresidentGr.ms().getVal(fileName_);
        StringMap<String> messages_ = ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
//        StringMap<String> messages_ = ExtractFromFiles.getMessagesFromLocaleClass(RESOURCES_CLASS_PATH, _loc, RESULTS_PRESIDENT);
        _r.getRes().setGlobalResultsPageTitle(messages_.getVal(RESULTS_PAGE));
        _r.getRes().setDetailResultsTitle(messages_.getVal(DETAIL_RESULTS_PAGE));
    }

    public static void setMessages(ResultsTarot _r, String _loc) {
        _r.getRes().setLoc(_loc);
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(RESOURCES_CLASS_PATH, _loc, RESULTS_TAROT);
        String loadedResourcesMessages_ = MessTarotGr.ms().getVal(fileName_);
        StringMap<String> messages_ = ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
//        StringMap<String> messages_ = ExtractFromFiles.getMessagesFromLocaleClass(RESOURCES_CLASS_PATH, _loc, RESULTS_TAROT);
        _r.getRes().setGlobalResultsPageTitle(messages_.getVal(RESULTS_PAGE));
        _r.getRes().setDetailResultsTitle(messages_.getVal(DETAIL_RESULTS_PAGE));
    }

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

    public boolean isSameTeam(Bytes _players) {
        if (enCoursDePartieBelote()) {
            return partieBelote().isSameTeam(_players);
        }
        if (enCoursDePartieTarot()) {
            return partieTarot().getTeamsRelation().isSameTeam(_players);
        }
        if (enCoursDePartiePresident()) {
//            return false;
            return _players.size() == IndexConstants.ONE_ELEMENT;
        }
        return true;
    }
    public void sauvegarderPartieEnCours(String _nomFichier, TechStreams _str){
        if(enCoursDePartieBelote()){
            GameBelote game_ = partieBelote();
            StreamTextFile.saveTextFile(_nomFichier, DocumentWriterBeloteUtil.setGameBelote(game_),_str);
        }
        if(enCoursDePartieTarot()){
            GameTarot game_ = partieTarot();
            StreamTextFile.saveTextFile(_nomFichier, DocumentWriterTarotUtil.setGameTarot(game_),_str);
        }
        if(enCoursDePartiePresident()){
            GamePresident game_ = partiePresident();
            StreamTextFile.saveTextFile(_nomFichier, DocumentWriterPresidentUtil.setGamePresident(game_),_str);
        }
    }
    public static StringBuilder autoriseMessEcartDe(ReasonDiscard _r, CardTarot _c, String _loc) {
        StringMap<String> ms_ = MessTarotGr.ms();
        if(_r == ReasonDiscard.TOO_MUCH) {
            return new StringBuilder(formatter(ms_.getVal(tarotFileName(_loc)), TAROT_TOO_MANY_CARDS));
        }
        return build(_r, _c, _loc, ms_.getVal(tarotFileName(_loc)));
    }

    private static StringBuilder build(ReasonDiscard _r, CardTarot _c, String _loc, String _file) {
        StringBuilder m_ = new StringBuilder();
        if (_r == ReasonDiscard.KING) {
            m_.append(formatter(_file, TAROT_NO_DISCARDED_CHARACTER, toString(_c, _loc), toString(CardChar.KING, _loc))).append(RETURN_LINE);
            return m_;
        }
        if (_r == ReasonDiscard.TRUMP_CARD_OULDER) {
            m_.append(formatter(_file, TAROT_NO_DISCARDED_OUDLER, toString(_c, _loc))).append(RETURN_LINE);
            m_.append(formatter(_file, TAROT_NO_DISCARDED_TRUMP, toString(_c, _loc))).append(RETURN_LINE);
            return m_;
        }
        if (_r == ReasonDiscard.TRUMP_CARD) {
            m_.append(formatter(_file, TAROT_NO_DISCARDED_TRUMP, toString(_c, _loc))).append(RETURN_LINE);
            return m_;
        }
        m_.append(formatter(_file, TAROT_NO_DISCARDED_OUDLER, toString(_c, _loc))).append(RETURN_LINE);
        return m_;
    }

    public static String isValidHandfulMessage(GameTarot _g,Handfuls _h, HandTarot _hand,HandTarot _excludedCards, String _loc) {
        StringMap<String> ms_ = MessTarotGr.ms();
        int nbTrumps_ = _g.getRules().getAllowedHandfuls().getVal(_h);
        if (_g.isValidHandful(_h, _hand, _excludedCards)) {
            return EMPTY;
        }
        if(_hand.total()>nbTrumps_) {
            return formatter(ms_.getVal(tarotFileName(_loc)), TAROT_HANDFUL_TOO_MANY_TRUMPS, Long.toString((long)_hand.total()-nbTrumps_));
        }
        if(_hand.total()<nbTrumps_) {
            return formatter(ms_.getVal(tarotFileName(_loc)), TAROT_HANDFUL_NOT_ENOUGH_TRUMPS, Long.toString((long)nbTrumps_-_hand.total()));
        }
        return formatter(ms_.getVal(tarotFileName(_loc)), TAROT_HANDFUL_EXCUSE);
    }

    private static String tarotFileName(String _loc) {
        return StringUtil.concat(FOLDER, "/", _loc, "/", TAROT_FILE_NAME);
    }

    public static String autoriseBelote(GameBelote _g,String _loc) {
        Suit couleurDemandee_= _g.getProgressingTrick().couleurDemandee();
        Suit couleurAtout_=_g.couleurAtout();
        byte ramasseurVirtuel_= _g.getProgressingTrick().getRamasseurPliEnCours(_g.getNombreDeJoueurs(), _g.getBid());
        CardBelote carteForte_= _g.getProgressingTrick().carteDuJoueur(ramasseurVirtuel_,_g.getNombreDeJoueurs());
        StringMap<String> ms_ = MessBeloteGr.ms();
        String file_ = ms_.getVal(beloteFileName(_loc));
        ReasonPlayBelote r_ = _g.getReason();
        if (r_ == ReasonPlayBelote.FOLLOW_SUIT) {
            return formatter(file_, BELOTE_PLAY_SUIT, toString(couleurDemandee_, _loc));
        }
        if (r_ == ReasonPlayBelote.FOLLOW_TR_GREATER) {
            return formatter(file_, BELOTE_PLAY_STRONGER_CARD, toString(carteForte_, _loc));
        }
        if (r_ == ReasonPlayBelote.TR_TRICK) {
            return formatter(file_, BELOTE_TRUMP_FOE, toString(carteForte_, _loc));
        }
        if (r_ == ReasonPlayBelote.UNDER_TR_TRICK) {
            return formatter(file_, BELOTE_UNDER_TRUMP_FOE, toString(couleurAtout_, _loc));
        }
        if (r_ == ReasonPlayBelote.OVER_TR_TRICK) {
            return formatter(file_, BELOTE_OVER_TRUMP_FOE, toString(carteForte_, _loc));
        }
        if (r_ == ReasonPlayBelote.UNDER_PART) {
            return formatter(file_, BELOTE_UNDER_TRUMP_PARTNER, toString(couleurAtout_, _loc));
        }
        return formatter(file_, BELOTE_OVER_TRUMP_PARTNER, toString(couleurAtout_, _loc));
    }

    private static String beloteFileName(String _loc) {
        return StringUtil.concat(FOLDER, "/", _loc, "/", BELOTE_FILE_NAME);
    }

    public static StringBuilder autorisePresident(GamePresident _g,byte _player, CardPresident _card, byte _nb, String _loc) {
        Playing playing_ = _g.getStatus(_player);
        StringMap<String> ms_ = MessPresidentGr.ms();
        if (playing_ == Playing.PASS) {
            return new StringBuilder(formatter(ms_.getVal(presidentFileName(_loc)), PRESIDENT_HAVE_PASSED));
        }
        if (playing_ == Playing.SKIPPED) {
            return new StringBuilder(formatter(ms_.getVal(presidentFileName(_loc)), PRESIDENT_SKIPPED));
        }
        HandPresident l_ = _g.getProgressingTrick().getBestCards();
        StringBuilder errorPlaying_ = new StringBuilder();
        byte str_ = l_.premiereCarte().strength(_g.isReversed());
        if (playing_ == Playing.HAS_TO_EQUAL) {
            if (_card.strength(_g.isReversed()) != str_) {
                errorPlaying_.append(formatter(ms_.getVal(presidentFileName(_loc)), PRESIDENT_HAS_TO_EQUAL_OR_SKIP, toString(l_.premiereCarte(),_loc))).append(RETURN_LINE);
            }
        } else if (_g.getRules().getEqualty() == EqualtyPlaying.FORBIDDEN) {
            if (_card.strength(_g.isReversed()) <= str_) {
                errorPlaying_.append(formatter(ms_.getVal(presidentFileName(_loc)), PRESIDENT_CANNOT_USE_LOWER_OR_EQ, toString(l_.premiereCarte(),_loc))).append(RETURN_LINE);
            }
        } else {
            if (_card.strength(_g.isReversed()) <= str_) {
                errorPlaying_.append(formatter(
                        ms_.getVal(presidentFileName(_loc)), PRESIDENT_CANNOT_USE_LOWER, toString(l_.premiereCarte(),_loc))).append(RETURN_LINE);
            }
        }
        if (_nb != _g.getProgressingTrick().getNombreDeCartesParJoueur()) {
            errorPlaying_.append(formatter(
                    ms_.getVal(presidentFileName(_loc)), PRESIDENT_HAVE_PLAY_GIVEN_NUMBER_CARDS,
                    Long.toString(_g.getProgressingTrick().getNombreDeCartesParJoueur()))).append(RETURN_LINE);
        }
        return errorPlaying_;
    }

    public static String canPassMess(GamePresident _g, String _loc) {
        StringMap<String> ms_ = MessPresidentGr.ms();
        HandPresident b_ = _g.getProgressingTrick().getBestCards();
        return formatter(ms_.getVal(presidentFileName(_loc)), PRESIDENT_CANNOT_PASS,
                toString(b_.premiereCarte(),_loc));
    }

    private static String presidentFileName(String _loc) {
        return StringUtil.concat(FOLDER, "/", _loc, "/", PRESIDENT_FILE_NAME);
    }

    public static String autoriseTarot(GameTarot _g, String _loc) {
        StringMap<String> ms_ = MessTarotGr.ms();
        Suit couleurDemandee_ = _g.getProgressingTrick().couleurDemandee();
        if (_g.getReason() == ReasonPlayTarot.FOLLOW_SUIT) {
            return formatter(ms_.getVal(tarotFileName(_loc)), TAROT_PLAY_SUIT, toString(couleurDemandee_,_loc));
        }
        if (_g.getReason() == ReasonPlayTarot.TR_TRICK) {
            return formatter(ms_.getVal(tarotFileName(_loc)), TAROT_TRUMP, toString(couleurDemandee_, _loc));
        }
        if (_g.getReason() == ReasonPlayTarot.UNDER_TR_TRICK) {
            return formatter(ms_.getVal(tarotFileName(_loc)), TAROT_UNDERTRUMP, toString(couleurDemandee_,_loc));
        }
        if (_g.getReason() == ReasonPlayTarot.OVER_TR_TRICK) {
            return formatter(ms_.getVal(tarotFileName(_loc)), TAROT_OVERTRUMP, toString(couleurDemandee_,_loc));
        }
        if (!_g.getCarteAppelee().estVide() && _g.getReason() == ReasonPlayTarot.NO_CALLED_SUIT) {
            return formatter(ms_.getVal(tarotFileName(_loc)), TAROT_PLAY_FIRST_TRICK, toString(_g.getCarteAppelee().carte(0).getId().getCouleur(), _loc), toString(_g.getCarteAppelee().carte(0),_loc));
        }
        return formatter(ms_.getVal(tarotFileName(_loc)), TAROT_PLAY_STRONGER_CARD, toString(couleurDemandee_,_loc));
    }

    public static String toString(HandBelote _b,String _lg) {
        StringList retString_= new StringList();
        for (CardBelote c: _b.getCards()) {
            retString_.add(toString(c,_lg));
        }
        return StringUtil.join(retString_, SEPARATOR);
    }
    public static String toString(HandPresident _b, String _lg) {
        StringList retString_= new StringList();
        for (CardPresident c: _b.getCards()) {
            retString_.add(toString(c,_lg));
        }
        return StringUtil.join(retString_, SEPARATOR);
    }
    public static String toString(HandTarot _t, String _lg) {
        StringList retString_= new StringList();
        for (CardTarot c: _t.getCards()) {
            retString_.add(toString(c,_lg));
        }
        return StringUtil.join(retString_, SEPARATOR);
    }
    public static String getSymbol(CardTarot _c,String _loc) {
        if (_c.getId().getNomFigure() != CardChar.UNDEFINED) {
            return getSymbol(_c.getId().getNomFigure(),_loc);
        }
        return Long.toString(_c.getForce());
    }

    public static String getSymbol(CardBelote _c, String _loc) {
        if (_c.getId().getNomFigure() != CardChar.UNDEFINED) {
            return getSymbol(_c.getId().getNomFigure(),_loc);
        }
        return Long.toString(_c.getId().getValeur());
    }
    public static String getSymbol(CardPresident _c, String _loc) {
        if (_c.getId().getNomFigure() != CardChar.UNDEFINED) {
            return getSymbol(_c.getId().getNomFigure(),_loc);
        }
        return Long.toString(_c.getId().getValeur());
    }
    public static String getSymbol(CardChar _c,String _loc) {
        return getConstanteLangue(symbolChars(_loc), CoreResourcesAccess.CHARS, _c.name());
    }

    private static String symbolChars(String _loc) {
        return StringUtil.concat(CoreResourcesAccess.NOM_DOSSIER, "/", _loc, "/", CoreResourcesAccess.SYMBOL_CARDS_TXT);
    }

    public static String toString(CardChar _c,String _loc) {
        return getConstanteLangue(coreFileName(_loc), CoreResourcesAccess.CHARS, _c.name());
    }
    public static String toString(BeloteTrumpPartner _b, String _locale){
        String fichier_ = beloteCoreFileNameContent(_locale);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
        return Format.getConstanteLangue(BeloteResoucesAccess.key(_b), fichier_);
    }

    private static String beloteCoreFileName(String _locale) {
        return StringUtil.concat(BeloteResoucesAccess.NOM_DOSSIER, "/", _locale, "/", BeloteResoucesAccess.NOM_FICHIER);
    }

    public static String toString(BidBeloteSuit _b, String _loc) {
        String general_ = coreFileNameContent(_loc);
        String specific_ =beloteCoreFileNameContent(_loc);
        return BidBeloteSuit.toString(_b,general_,specific_);
//        StringBuilder pts_ = new StringBuilder();
//        if (_b.getPoints() > 0) {
//            pts_.append(SPACE);
//            pts_.append(_b.getPoints());
//        }
//        if (_b.getCouleurDominante()) {
////        String fichier_ = ResourceFiles.ressourceFichier(_file);
//            pts_.insert(0, Format.getConstanteLangue(CoreResourcesAccess.key(_b.getSuit()), general_));
//            return pts_.toString();
//        }
////        String fichier_ = ResourceFiles.ressourceFichier(_file);
//        pts_.insert(0, Format.getConstanteLangue(BeloteResoucesAccess.key(_b.getBid()), specific_));
//        return pts_.toString();
    }
    public static String toString(BidBelote _b, String _locale){
        String fichier_ = beloteCoreFileNameContent(_locale);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
        return Format.getConstanteLangue(BeloteResoucesAccess.key(_b), fichier_);
    }
    public static String toString(DeclaresBelote _b, String _locale){
        String fichier_ = beloteCoreFileNameContent(_locale);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
        return Format.getConstanteLangue(BeloteResoucesAccess.key(_b), fichier_);
    }
    public static String toString(CardBelote _b, String _locale){
        String fichier_ = beloteCoreFileNameContent(_locale);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
        return Format.getConstanteLangue(BeloteResoucesAccess.key(_b), fichier_);
    }
    public static String toStringBeloteReb(String _locale){
        return getConstanteLangue(beloteCoreFileName(_locale), BeloteResoucesAccess.BELOTE_DECLARES_BEL_REB, BeloteResoucesAccess.BELOTE_REBELOTE);
    }
    public static String toStringBonusBelote(String _locale){
        return getConstanteLangue(beloteCoreFileName(_locale), BeloteResoucesAccess.BELOTE_BONUS, BeloteResoucesAccess.LAST_TRICK);
    }
    public static String toString(CardPresident _b, String _locale){
        String fichier_ = presidentCoreFileContent(_locale);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
        return Format.getConstanteLangue(PresidentResoucesAccess.key(_b), fichier_);
    }

    public static String toString(Playing _b, String _locale){
        return getConstanteLangue(presidentCoreFileName(_locale), PresidentResoucesAccess.PRESIDENT_PLAY,_b.getPlay());
    }
    public static String toString(EqualtyPlaying _b, String _locale){
        String fichier_ = presidentCoreFileContent(_locale);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
        return Format.getConstanteLangue(PresidentResoucesAccess.key(_b), fichier_);
    }

    private static String presidentCoreFileContent(String _locale) {
        return MessagesCardsAll.ms().getVal(presidentCoreFileName(_locale));
    }

    private static String presidentCoreFileName(String _locale) {
        return StringUtil.concat(PresidentResoucesAccess.NOM_DOSSIER, "/", _locale, "/", PresidentResoucesAccess.NOM_FICHIER);
    }

    public static String toString(ModeTarot _b, String _locale){
        String fichier_ = tarotCoreFileContent(_locale);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
        return Format.getConstanteLangue(TarotResoucesAccess.key(_b), fichier_);
    }
    public static String toString(ChoiceTarot _b, String _locale){
        return getConstanteLangue(tarotCoreFileName(_locale), TarotResoucesAccess.TAROT_CHOICE, _b.name());
    }
    public static String toString(BidTarot _b, String _locale){
        String fichier_ = tarotCoreFileContent(_locale);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
        return Format.getConstanteLangue(TarotResoucesAccess.key(_b), fichier_);
    }

    private static String tarotCoreFileContent(String _locale) {
        return MessagesCardsAll.ms().getVal(tarotCoreFileName(_locale));
    }

    public static String toString(CardTarot _b, String _locale){
        String fichier_ = tarotCoreFileContent(_locale);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
        return Format.getConstanteLangue(TarotResoucesAccess.key(_b), fichier_);
    }
    public static String toString(Handfuls _b, String _locale){
        String fichier_ = tarotCoreFileContent(_locale);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
        return Format.getConstanteLangue(TarotResoucesAccess.key(_b), fichier_);
    }
    public static String toString(Miseres _b, String _locale){
        String fichier_ = tarotCoreFileContent(_locale);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
        return Format.getConstanteLangue(TarotResoucesAccess.key(_b), fichier_);
    }
    public static String toString(DealingTarot _b, String _locale){
        String fichier_ = tarotCoreFileContent(_locale);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
        return Format.getConstanteLangue(TarotResoucesAccess.key(_b), fichier_);
    }
    public static String toString(EndDealTarot _b, String _locale){
        String fichier_ = tarotCoreFileContent(_locale);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
        return Format.getConstanteLangue(TarotResoucesAccess.key(_b), fichier_);
    }
    public static String toString(BonusTarot _b, String _locale){
        return getConstanteLangue(tarotCoreFileName(_locale), TarotResoucesAccess.TAROT_BONUS,_b.name());
    }

    private static String tarotCoreFileName(String _locale) {
        return StringUtil.concat(TarotResoucesAccess.NOM_DOSSIER, "/", _locale, "/", TarotResoucesAccess.NOM_FICHIER);
    }

    public static String toString(Suit _b, String _locale) {
        String fichier_ = coreFileNameContent(_locale);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
        return Format.getConstanteLangue(CoreResourcesAccess.key(_b), fichier_);
    }

    public static String toString(Role _b, String _locale) {
        String fichier_ = coreFileNameContent(_locale);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
        return Format.getConstanteLangue(CoreResourcesAccess.key(_b), fichier_);
    }
    public static String toString(MixCardsChoice _b, String _locale) {
        String fichier_ = coreFileNameContent(_locale);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
        return Format.getConstanteLangue(CoreResourcesAccess.key(_b), fichier_);
    }

    private static String coreFileNameContent(String _locale) {
        return MessagesCardsAll.ms().getVal(coreFileName(_locale));
    }

    private static String beloteCoreFileNameContent(String _locale) {
        return MessagesCardsAll.ms().getVal(beloteCoreFileName(_locale));
    }


    private static String coreFileName(String _locale) {
        return StringUtil.concat(CoreResourcesAccess.NOM_DOSSIER, "/", _locale, "/", CoreResourcesAccess.NOM_FICHIER);
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
