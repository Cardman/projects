package cards.facade;
import cards.belote.*;
import cards.belote.enumerations.*;
import cards.consts.*;
import cards.president.*;
import cards.president.enumerations.*;
import cards.tarot.*;
import cards.tarot.enumerations.*;
import code.scripts.messages.cards.*;
import code.sml.util.*;
import code.util.*;
import code.util.core.*;

public final class Games {
    private static final String SEPARATOR = " - ";

    private static final String EMPTY = "";

    private static final String RETURN_LINE = "\n";

    private CustList<GameBelote> partiesBelote = new CustList<GameBelote>();

    private CustList<GameTarot> partiesTarot = new CustList<GameTarot>();

    private CustList<GamePresident> partiesPresident = new CustList<GamePresident>();

    private RulesBelote rulesBelote;

    private RulesTarot rulesTarot;

    private RulesPresident rulesPresident;

    private String errorFile;

    public static String toCardString(int _b, TranslationsLg _locale){
        return MessagesCardGames.getCommonCardsTr(MessagesCardGames.getAppliTr(_locale)).getMapping().getVal(Integer.toString(_b));
//        String fichier_ = tarotCoreFileContent(_locale);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
//        return Format.getConstanteLangue(TarotResoucesAccess.key(_b), fichier_);
    }

    /**@see getConstante
     @return la chaine de caracteres retournee par la methode getConstante qui est formatte avec _variables
      * @param _fichier
     * @param _nomConstante nom de la constante en rapport avec la chaine de caracteres
     * @param _variables liste de parametres remplacant les expressions comme {0} {1}...    */
    private static String formatter(StringMap<String> _fichier,String _nomConstante, String... _variables){
        return StringUtil.simpleStringsFormat(StringUtil.nullToEmpty(_fichier.getVal(_nomConstante)), _variables);
    }

    public static void setMessages(ResultsGame _r, TranslationsLg _loc) {
//        _r.setLoc(_loc.getKey());
        StringMap<String> mapping_ = MessagesCardGames.getCommonFileTr(MessagesCardGames.getAppliTr(_loc)).getMapping();
//        StringMap<String> messages_ = ExtractFromFiles.getMessagesFromLocaleClass(RESOURCES_CLASS_PATH, _loc, RESULTS_TAROT);
        _r.setGlobalResultsPageTitle(mapping_.getVal(MessagesCommonFile.RESULT_PAGE));
        _r.setDetailResultsTitle(mapping_.getVal(MessagesCommonFile.DET_RESULT_PAGE));
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
    public void sauvegarderPartieEnCours(CardGamesStream _cs, String _nomFichier){
        AbsCardGamesCrud cr_ = _cs.getCardGamesCrud();
        if(enCoursDePartieBelote()){
            GameBelote game_ = partieBelote();
            cr_.belote(_nomFichier,game_);
        }
        if(enCoursDePartieTarot()){
            GameTarot game_ = partieTarot();
            cr_.tarot(_nomFichier,game_);
        }
        if(enCoursDePartiePresident()){
            GamePresident game_ = partiePresident();
            cr_.president(_nomFichier,game_);
        }
    }
    public static StringBuilder autoriseMessEcartDe(ReasonDiscard _r, CardTarot _c, TranslationsLg _tr) {
        StringMap<String> ms_ = MessagesCardGames.getAppliTr(_tr).getMapping().getVal(MessagesCardGames.GAME_TAROT).getMapping();
        if(_r == ReasonDiscard.TOO_MUCH) {
            return new StringBuilder(formatter(ms_, MessagesTarot.TAROT_TOO_MANY_CARDS));
        }
        return build(_r, _c, _tr, ms_);
    }

    private static StringBuilder build(ReasonDiscard _r, CardTarot _c, TranslationsLg _loc, StringMap<String> _file) {
        StringBuilder m_ = new StringBuilder();
        if (_r == ReasonDiscard.KING) {
            m_.append(formatter(_file, MessagesTarot.TAROT_NO_DISCARDED_CHARACTER, toString(_c, _loc))).append(RETURN_LINE);
            return m_;
        }
        if (_r == ReasonDiscard.TRUMP_CARD_OULDER) {
            m_.append(formatter(_file, MessagesTarot.TAROT_NO_DISCARDED_OUDLER, toString(_c, _loc))).append(RETURN_LINE);
            m_.append(formatter(_file, MessagesTarot.TAROT_NO_DISCARDED_TRUMP, toString(_c, _loc))).append(RETURN_LINE);
            return m_;
        }
        if (_r == ReasonDiscard.TRUMP_CARD) {
            m_.append(formatter(_file, MessagesTarot.TAROT_NO_DISCARDED_TRUMP, toString(_c, _loc))).append(RETURN_LINE);
            return m_;
        }
        m_.append(formatter(_file, MessagesTarot.TAROT_NO_DISCARDED_OUDLER, toString(_c, _loc))).append(RETURN_LINE);
        return m_;
    }

    public static String isValidHandfulMessage(RulesTarot _g,Handfuls _h, HandTarot _hand,HandTarot _excludedCards, TranslationsLg _loc) {
        StringMap<String> ms_ = MessagesCardGames.getAppliTr(_loc).getMapping().getVal(MessagesCardGames.GAME_TAROT).getMapping();
        int nbTrumps_ = _g.getAllowedHandfuls().getVal(_h);
        if (GameTarot.isValidHandful(_g, _h, _hand, _excludedCards)) {
            return EMPTY;
        }
        if(_hand.total()>nbTrumps_) {
            return formatter(ms_, MessagesTarot.TAROT_HANDFUL_TOO_MANY_TRUMPS, Long.toString((long)_hand.total()-nbTrumps_));
        }
        if(_hand.total()<nbTrumps_) {
            return formatter(ms_, MessagesTarot.TAROT_HANDFUL_NOT_ENOUGH_TRUMPS, Long.toString((long)nbTrumps_-_hand.total()));
        }
        return formatter(ms_, MessagesTarot.TAROT_HANDFUL_EXCUSE);
    }

    public static String autoriseBelote(GameBelote _g, TranslationsLg _tr) {
        Suit couleurDemandee_= _g.getProgressingTrick().couleurDemandee();
        Suit couleurAtout_=_g.couleurAtout();
        byte ramasseurVirtuel_= _g.getProgressingTrick().getRamasseurPliEnCours(_g.getNombreDeJoueurs(), _g.getBid());
        CardBelote carteForte_= _g.getProgressingTrick().carteDuJoueur(ramasseurVirtuel_,_g.getNombreDeJoueurs());
        StringMap<String> ms_ = MessagesCardGames.getAppliTr(_tr).getMapping().getVal(MessagesCardGames.GAME_BELOTE).getMapping();
//        String file_ = ms_.getVal(beloteFileName(_loc));
        ReasonPlayBelote r_ = _g.getReason();
        if (r_ == ReasonPlayBelote.FOLLOW_SUIT) {
            return formatter(ms_, MessagesBelote.BELOTE_PLAY_SUIT, toString(couleurDemandee_, _tr));
        }
        if (r_ == ReasonPlayBelote.FOLLOW_TR_GREATER) {
            return formatter(ms_, MessagesBelote.BELOTE_PLAY_STRONGER_CARD, toString(carteForte_, _tr));
        }
        if (r_ == ReasonPlayBelote.TR_TRICK) {
            return formatter(ms_, MessagesBelote.BELOTE_TRUMP_FOE, toString(carteForte_, _tr));
        }
        if (r_ == ReasonPlayBelote.UNDER_TR_TRICK) {
            return formatter(ms_, MessagesBelote.BELOTE_UNDER_TRUMP_FOE, toString(couleurAtout_, _tr));
        }
        if (r_ == ReasonPlayBelote.OVER_TR_TRICK) {
            return formatter(ms_, MessagesBelote.BELOTE_OVER_TRUMP_FOE, toString(carteForte_, _tr));
        }
        if (r_ == ReasonPlayBelote.UNDER_PART) {
            return formatter(ms_, MessagesBelote.BELOTE_UNDER_TRUMP_PARTNER, toString(couleurAtout_, _tr));
        }
        return formatter(ms_, MessagesBelote.BELOTE_OVER_TRUMP_PARTNER, toString(couleurAtout_, _tr));
    }

    public static StringBuilder autorisePresident(GamePresident _g, CardPresident _card, byte _nb, TranslationsLg _tr) {
        Playing playing_ = _g.getStatus();
        StringMap<String> ms_ = MessagesCardGames.getAppliTr(_tr).getMapping().getVal(MessagesCardGames.GAME_PRESIDENT).getMapping();
        if (playing_ == Playing.PASS) {
            return new StringBuilder(formatter(ms_, MessagesPresident.PRESIDENT_HAVE_PASSED));
        }
        if (playing_ == Playing.SKIPPED) {
            return new StringBuilder(formatter(ms_, MessagesPresident.PRESIDENT_SKIPPED));
        }
        HandPresident l_ = _g.getProgressingTrick().getBestCards();
        StringBuilder errorPlaying_ = new StringBuilder();
        byte str_ = l_.premiereCarte().strength(_g.isReversed());
        if (playing_ == Playing.HAS_TO_EQUAL) {
            if (_card.strength(_g.isReversed()) != str_) {
                errorPlaying_.append(formatter(ms_, MessagesPresident.PRESIDENT_HAS_TO_EQUAL_OR_SKIP, toString(l_.premiereCarte(),_tr))).append(RETURN_LINE);
            }
        } else if (_g.getRules().getEqualty() == EqualtyPlaying.FORBIDDEN) {
            if (_card.strength(_g.isReversed()) <= str_) {
                errorPlaying_.append(formatter(ms_, MessagesPresident.PRESIDENT_CANNOT_USE_LOWER_OR_EQ, toString(l_.premiereCarte(),_tr))).append(RETURN_LINE);
            }
        } else {
            if (_card.strength(_g.isReversed()) <= str_) {
                errorPlaying_.append(formatter(
                        ms_, MessagesPresident.PRESIDENT_CANNOT_USE_LOWER, toString(l_.premiereCarte(),_tr))).append(RETURN_LINE);
            }
        }
        if (_nb != _g.getProgressingTrick().getNombreDeCartesParJoueur()) {
            errorPlaying_.append(formatter(
                    ms_, MessagesPresident.PRESIDENT_HAVE_PLAY_GIVEN_NUMBER_CARDS,
                    Long.toString(_g.getProgressingTrick().getNombreDeCartesParJoueur()))).append(RETURN_LINE);
        }
        return errorPlaying_;
    }

    public static String canPassMess(GamePresident _g, TranslationsLg _tr) {
        StringMap<String> ms_ = MessagesCardGames.getAppliTr(_tr).getMapping().getVal(MessagesCardGames.GAME_PRESIDENT).getMapping();
        HandPresident b_ = _g.getProgressingTrick().getBestCards();
        return formatter(ms_, MessagesPresident.PRESIDENT_CANNOT_PASS,
                toString(b_.premiereCarte(),_tr));
    }

    public static String autoriseTarot(GameTarot _g, TranslationsLg _tr) {
        StringMap<String> ms_ = MessagesCardGames.getAppliTr(_tr).getMapping().getVal(MessagesCardGames.GAME_TAROT).getMapping();
        Suit couleurDemandee_ = _g.getProgressingTrick().couleurDemandee();
        if (_g.getReason() == ReasonPlayTarot.FOLLOW_SUIT) {
            return formatter(ms_,MessagesTarot.TAROT_PLAY_SUIT, toString(couleurDemandee_,_tr));
        }
        if (_g.getReason() == ReasonPlayTarot.TR_TRICK) {
            return formatter(ms_, MessagesTarot.TAROT_TRUMP, toString(couleurDemandee_, _tr));
        }
        if (_g.getReason() == ReasonPlayTarot.UNDER_TR_TRICK) {
            return formatter(ms_, MessagesTarot.TAROT_UNDERTRUMP, toString(couleurDemandee_,_tr));
        }
        if (_g.getReason() == ReasonPlayTarot.OVER_TR_TRICK) {
            return formatter(ms_, MessagesTarot.TAROT_OVERTRUMP, toString(couleurDemandee_,_tr));
        }
        if (!_g.getCarteAppelee().estVide() && _g.getReason() == ReasonPlayTarot.NO_CALLED_SUIT) {
            return formatter(ms_, MessagesTarot.TAROT_PLAY_FIRST_TRICK, toString(_g.getCarteAppelee().carte(0).getId().getCouleur(), _tr), toString(_g.getCarteAppelee().carte(0),_tr));
        }
        return formatter(ms_, MessagesTarot.TAROT_PLAY_STRONGER_CARD, toString(couleurDemandee_,_tr));
    }

    public static String toString(HandBelote _b,TranslationsLg _lg) {
        StringList retString_= new StringList();
        for (CardBelote c: _b.getCards()) {
            retString_.add(toString(c,_lg));
        }
        return StringUtil.join(retString_, SEPARATOR);
    }
    public static String toString(HandPresident _b, TranslationsLg _lg) {
        StringList retString_= new StringList();
        for (CardPresident c: _b.getCards()) {
            retString_.add(toString(c,_lg));
        }
        return StringUtil.join(retString_, SEPARATOR);
    }
    public static String toString(HandTarot _t, TranslationsLg _lg) {
        StringList retString_= new StringList();
        for (CardTarot c: _t.getCards()) {
            retString_.add(toString(c,_lg));
        }
        return StringUtil.join(retString_, SEPARATOR);
    }
//    public static String getSymbol(CardTarot _c,String _loc) {
//        if (_c.getId().getNomFigure() != CardChar.UNDEFINED) {
//            return getSymbol(_c.getId().getNomFigure(),_loc);
//        }
//        return Long.toString(_c.getForce());
//    }
//
//    public static String getSymbol(CardBelote _c, String _loc) {
//        if (_c.getId().getNomFigure() != CardChar.UNDEFINED) {
//            return getSymbol(_c.getId().getNomFigure(),_loc);
//        }
//        return Long.toString(_c.getId().getValeur());
//    }
//    public static String getSymbol(CardPresident _c, String _loc) {
//        if (_c.getId().getNomFigure() != CardChar.UNDEFINED) {
//            return getSymbol(_c.getId().getNomFigure(),_loc);
//        }
//        return Long.toString(_c.getId().getValeur());
//    }
//    public static String getSymbol(CardChar _c,String _loc) {
//        return getConstanteLangue(symbolChars(_loc), CoreResourcesAccess.CHARS, _c.name());
//    }

//    private static String symbolChars(String _loc) {
//        return StringUtil.concat(CoreResourcesAccess.NOM_DOSSIER, "/", _loc, "/", CoreResourcesAccess.SYMBOL_CARDS_TXT);
//    }

    public static String toString(BeloteTrumpPartner _b, TranslationsLg _locale){
        return MessagesCardGames.getCommonBeloteTr(MessagesCardGames.getAppliTr(_locale)).getMapping().getVal(BeloteCardsExporterUtil.TRUMPING+BeloteCardsExporterUtil.fromBeloteTrumpPartner(_b));
//        String fichier_ = beloteCoreFileNameContent(_locale);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
//        return Format.getConstanteLangue(BeloteResoucesAccess.key(_b), fichier_);
    }

    public static String toString(BidBeloteSuit _b, TranslationsLg _tr) {
//        String specific_ =beloteCoreFileNameContent(_loc);
        StringMap<String> core_ = MessagesCardGames.getCommonFileTr(MessagesCardGames.getAppliTr(_tr)).getMapping();
        StringMap<String> belote_ = MessagesCardGames.getCommonBeloteTr(MessagesCardGames.getAppliTr(_tr)).getMapping();
        return BidBeloteSuit.toString(_b, EnumCardsExporterUtil.SUITS, core_,BeloteCardsExporterUtil.BID,belote_);
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

    public static String toString(BidBelote _b, TranslationsLg _locale){
        return MessagesCardGames.getCommonBeloteTr(MessagesCardGames.getAppliTr(_locale)).getMapping().getVal(BeloteCardsExporterUtil.BID+BeloteCardsExporterUtil.fromBidBelote(_b));
//        String fichier_ = beloteCoreFileNameContent(_locale);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
//        return Format.getConstanteLangue(BeloteResoucesAccess.key(_b), fichier_);
    }

    public static String toString(DeclaresBelote _b, TranslationsLg _locale){
        return MessagesCardGames.getCommonBeloteTr(MessagesCardGames.getAppliTr(_locale)).getMapping().getVal(BeloteCardsExporterUtil.DECLARE+BeloteCardsExporterUtil.fromDeclaresBelote(_b));
//        String fichier_ = beloteCoreFileNameContent(_locale);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
//        return Format.getConstanteLangue(BeloteResoucesAccess.key(_b), fichier_);
    }
    public static String toString(CardBelote _b, TranslationsLg _locale){
        return MessagesCardGames.getCommonCardsTr(MessagesCardGames.getAppliTr(_locale)).getMapping().getVal(BeloteCardsExporterUtil.fromCardBelote(_b));
//        String fichier_ = beloteCoreFileNameContent(_locale);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
//        return Format.getConstanteLangue(BeloteResoucesAccess.key(_b), fichier_);
    }

    public static String toString(DealingBelote _b, TranslationsLg _locale){
        return MessagesCardGames.getCommonBeloteTr(MessagesCardGames.getAppliTr(_locale)).getMapping().getVal(BeloteCardsExporterUtil.DEAL+BeloteCardsExporterUtil.fromDealingBelote(_b));
//        String fichier_ = tarotCoreFileContent(_locale);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
//        return Format.getConstanteLangue(TarotResoucesAccess.key(_b), fichier_);
    }
    public static String toStringBeloteReb(TranslationsLg _locale){
        return MessagesCardGames.getCommonBeloteTr(MessagesCardGames.getAppliTr(_locale)).getMapping().getVal(BeloteCardsExporterUtil.DECLARE_PAIR);
    }
    public static String toStringBonusBelote(TranslationsLg _locale){
        return MessagesCardGames.getCommonBeloteTr(MessagesCardGames.getAppliTr(_locale)).getMapping().getVal(BeloteCardsExporterUtil.LAST_TRICK);
    }
    public static String toString(CardPresident _b, TranslationsLg _locale){
        return MessagesCardGames.getCommonCardsTr(MessagesCardGames.getAppliTr(_locale)).getMapping().getVal(PresidentCardsExporterUtil.fromCardPresident(_b));
//        String fichier_ = presidentCoreFileContent(_locale);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
//        return Format.getConstanteLangue(PresidentResoucesAccess.key(_b), fichier_);
    }

    public static String toString(Playing _b, TranslationsLg _locale){
        return MessagesCardGames.getCommonPresidentTr(MessagesCardGames.getAppliTr(_locale)).getMapping().getVal(PresidentCardsExporterUtil.PLAYING+_b.getPlay());
//        return getConstanteLangue(presidentCoreFileName(_locale), PresidentResoucesAccess.PRESIDENT_PLAY,_b.getPlay());
    }
    public static String toString(EqualtyPlaying _b, TranslationsLg _locale){
        return MessagesCardGames.getCommonPresidentTr(MessagesCardGames.getAppliTr(_locale)).getMapping().getVal(PresidentCardsExporterUtil.EQUALTY+PresidentCardsExporterUtil.fromEqualtyPlaying(_b));
//        String fichier_ = presidentCoreFileContent(_locale);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
//        return Format.getConstanteLangue(PresidentResoucesAccess.key(_b), fichier_);
    }

    public static String toString(ModeTarot _b, TranslationsLg _locale){
        return MessagesCardGames.getCommonTarotTr(MessagesCardGames.getAppliTr(_locale)).getMapping().getVal(TarotCardsExporterUtil.MODE+TarotCardsExporterUtil.fromModeTarot(_b));
//        String fichier_ = tarotCoreFileContent(_locale);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
//        return Format.getConstanteLangue(TarotResoucesAccess.key(_b), fichier_);
    }
    public static String toString(ChoiceTarot _b, TranslationsLg _locale){
        StringMap<String> mapping_ = MessagesCardGames.getAppliTr(_locale).getMapping().getVal(MessagesCardGames.CHOICE_TAROT).getMapping();
        if (_b == ChoiceTarot.HUNT_SMALL) {
            return mapping_.getVal(TarotCardsExporterUtil.HUNT_SMALL);
        }
        if (_b == ChoiceTarot.SAVE_SMALL) {
            return mapping_.getVal(TarotCardsExporterUtil.SAVE_SMALL);
        }
        return mapping_.getVal(TarotCardsExporterUtil.LEAD_SMALL_BOUND);
    }

    public static String toString(BidTarot _b, TranslationsLg _locale){
        return MessagesCardGames.getCommonTarotTr(MessagesCardGames.getAppliTr(_locale)).getMapping().getVal(TarotCardsExporterUtil.BID+TarotCardsExporterUtil.fromBidTarot(BidTarot.toFirstBid(_b)));
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
//        return Format.getConstanteLangue(TarotResoucesAccess.key(_b), fichier_);
    }

    public static String toString(CardTarot _b, TranslationsLg _locale){
        return MessagesCardGames.getCommonCardsTr(MessagesCardGames.getAppliTr(_locale)).getMapping().getVal(TarotCardsExporterUtil.fromCardTarot(_b));
//        String fichier_ = tarotCoreFileContent(_locale);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
//        return Format.getConstanteLangue(TarotResoucesAccess.key(_b), fichier_);
    }
    public static String toString(Handfuls _b, TranslationsLg _locale){
        return MessagesCardGames.getAppliTr(_locale).getMapping().getVal(MessagesCardGames.COMMON_TAROT).getMapping().getVal(TarotCardsExporterUtil.HANDFUL+TarotCardsExporterUtil.fromHandfuls(_b));
//        String fichier_ = tarotCoreFileContent(_locale);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
//        return Format.getConstanteLangue(TarotResoucesAccess.key(_b), fichier_);
    }
    public static String toString(Miseres _b, TranslationsLg _locale){
        return MessagesCardGames.getCommonTarotTr(MessagesCardGames.getAppliTr(_locale)).getMapping().getVal(TarotCardsExporterUtil.MISERES+TarotCardsExporterUtil.fromMiseres(_b));
//        String fichier_ = tarotCoreFileContent(_locale);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
//        return Format.getConstanteLangue(TarotResoucesAccess.key(_b), fichier_);
    }

    public static String toString(DealingTarot _b, TranslationsLg _locale){
        return MessagesCardGames.getCommonTarotTr(MessagesCardGames.getAppliTr(_locale)).getMapping().getVal(TarotCardsExporterUtil.DEAL+TarotCardsExporterUtil.fromDealingTarot(_b));
//        String fichier_ = tarotCoreFileContent(_locale);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
//        return Format.getConstanteLangue(TarotResoucesAccess.key(_b), fichier_);
    }
//    public static String toString(EndDealTarot _b, String _locale){
//        String fichier_ = tarotCoreFileContent(_locale);
////        String fichier_ = ResourceFiles.ressourceFichier(_file);
//        return Format.getConstanteLangue(TarotResoucesAccess.key(_b), fichier_);
//    }
    public static String toString(EndDealTarot _b, TranslationsLg _locale){
        return MessagesCardGames.getCommonTarotTr(MessagesCardGames.getAppliTr(_locale)).getMapping().getVal(TarotCardsExporterUtil.END_DEAL+TarotCardsExporterUtil.fromEndDealTarot(_b));
    }
    public static String toString(BonusTarot _b, TranslationsLg _locale){
        StringMap<String> mapping_ = MessagesCardGames.getCommonTarotTr(MessagesCardGames.getAppliTr(_locale)).getMapping();
        if (_b == BonusTarot.SLAM) {
            return mapping_.getVal(TarotCardsExporterUtil.SLAM);
        }
        return mapping_.getVal(TarotCardsExporterUtil.SMALL_BOUND);
    }

    public static String toString(Suit _b, TranslationsLg _locale) {
        return MessagesCardGames.getCommonFileTr(MessagesCardGames.getAppliTr(_locale)).getMapping().getVal(EnumCardsExporterUtil.SUITS+EnumCardsExporterUtil.fromSuit(_b));
//        String fichier_ = coreFileNameContent(_locale);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
//        return Format.getConstanteLangue(CoreResourcesAccess.key(_b), fichier_);
    }

    public static String toString(Role _b, TranslationsLg _locale) {
        return MessagesCardGames.getCommonFileTr(MessagesCardGames.getAppliTr(_locale)).getMapping().getVal(EnumCardsExporterUtil.ROLE+EnumCardsExporterUtil.fromRole(_b));
//        String fichier_ = coreFileNameContent(_locale);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
//        return Format.getConstanteLangue(CoreResourcesAccess.key(_b), fichier_);
    }
    public static String toString(MixCardsChoice _b, TranslationsLg _locale) {
        return MessagesCardGames.getCommonMixTr(MessagesCardGames.getAppliTr(_locale)).getMapping().getVal(EnumCardsExporterUtil.fromMixCardsChoice(_b));
//        String fichier_ = coreFileNameContent(_locale);
//        String fichier_ = ResourceFiles.ressourceFichier(_file);
//        return Format.getConstanteLangue(CoreResourcesAccess.key(_b), fichier_);
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

    public String getErrorFile() {
        return errorFile;
    }

    public void setErrorFile(String _e) {
        this.errorFile = _e;
    }
}
