package cards.network.threads;

import cards.belote.*;
import cards.belote.enumerations.*;
import cards.consts.EnumCardsRetrieverUtil;
import cards.facade.Games;
import cards.facade.IntArtCardGames;
import cards.network.belote.DiscardPhaseBelote;
import cards.network.belote.actions.BiddingBelote;
import cards.network.belote.actions.DiscardedCardBelote;
import cards.network.belote.actions.PlayingCardBelote;
import cards.network.belote.displaying.DealtHandBelote;
import cards.network.belote.displaying.RefreshHandBelote;
import cards.network.belote.unlock.AllowBiddingBelote;
import cards.network.belote.unlock.AllowPlayingBelote;
import cards.network.common.QuitCards;
import cards.network.common.before.*;
import cards.network.president.actions.DiscardedCardsPresident;
import cards.network.president.actions.PlayingCardPresident;
import cards.network.president.displaying.DealtHandPresident;
import cards.network.president.displaying.ReceivedGivenCards;
import cards.network.president.unlock.AllowDiscarding;
import cards.network.president.unlock.AllowPlayingPresident;
import cards.network.tarot.DiscardPhaseTarot;
import cards.network.tarot.actions.BiddingTarot;
import cards.network.tarot.actions.DiscardedCardTarot;
import cards.network.tarot.actions.PlayingCardTarot;
import cards.network.tarot.displaying.DealtHandTarot;
import cards.network.tarot.unlock.AllowBiddingTarot;
import cards.network.tarot.unlock.AllowPlayingTarot;
import cards.network.tarot.unlock.CallableCards;
import cards.president.*;
import cards.president.enumerations.CardPresident;
import cards.president.enumerations.Playing;
import cards.president.enumerations.PresidentCardsExporterUtil;
import cards.president.enumerations.PresidentCardsRetrieverUtil;
import cards.tarot.*;
import cards.tarot.enumerations.*;
import code.gui.initialize.AbstractSocket;
import code.network.NetCommon;
import code.network.WindowNetWork;
import code.threads.AbstractThreadFactory;
import code.util.*;
import code.util.comparators.ComparatorBoolean;
import code.util.core.*;
import code.util.ints.IntSplitPartsFields;

public final class Net {

    public static final int CLIENT_INDEX_ARRIVE = 0;
    public static final int CLIENT_CHOSEN_PLACE = 1;
    public static final int CLIENT_READY = 2;
    public static final int CLIENT_RULES_BELOTE = 3;
    public static final int CLIENT_RULES_PRESIDENT = 4;
    public static final int CLIENT_RULES_TAROT = 5;
    public static final int CLIENT_DEALT_HAND_BELOTE = 6;
    public static final int CLIENT_DEALT_HAND_PRESIDENT = 7;
    public static final int CLIENT_DEALT_HAND_TAROT = 8;
    public static final int CLIENT_DEALT_ALLOW_BIDDING_BELOTE = 9;
    public static final int CLIENT_DEALT_BIDDING_BELOTE = 10;
    public static final int CLIENT_DEALT_DISCARD_PHASE_BELOTE = 11;
    public static final int CLIENT_DEALT_REFRESH_HAND_BELOTE = 12;
    public static final int CLIENT_DEALT_ALLOW_DISCARDING = 13;
    public static final int CLIENT_DEALT_RECEIVED_GIVEN_CARDS = 14;
    public static final int CLIENT_DEALT_ALLOW_BIDDING_TAROT = 15;
    public static final int CLIENT_DEALT_BIDDING_TAROT = 16;
    public static final int CLIENT_DEALT_DISCARD_PHASE_TAROT = 17;
    public static final int CLIENT_DEALT_CALLABLE_CARDS = 18;
    public static final int CLIENT_DEALT_SLAM_TAROT = 19;
    public static final int CLIENT_DEALT_ALLOW_PLAYING_BELOTE = 20;
    public static final int CLIENT_DEALT_PLAYING_BELOTE = 21;
    public static final int CLIENT_DEALT_ALLOW_PLAYING_PRESIDENT = 22;
    public static final int CLIENT_DEALT_PLAYING_PRESIDENT = 23;
    public static final int CLIENT_DEALT_ALLOW_PLAYING_TAROT = 24;
    public static final int CLIENT_DEALT_PLAYING_TAROT = 25;
    public static final int CLIENT_DONE_PAUSE = 26;
    public static final int CLIENT_RES_BELOTE = 27;
    public static final int CLIENT_RES_PRESIDENT = 28;
    public static final int CLIENT_RES_TAROT = 29;
    public static final int CLIENT_TRICKS_HANDS_BELOTE = 30;
    public static final int CLIENT_TRICKS_HANDS_PRESIDENT = 31;
    public static final int CLIENT_TRICKS_HANDS_TAROT = 32;
    public static final int CLIENT_TRICKS_TEAMS = 33;
    public static final int CLIENT_POSSIBLE_QUIT = 34;
    public static final int SERVER_CHOSEN_PLACE = 0;
    public static final int SERVER_READY = 1;
    public static final int SERVER_RULES_BELOTE = 2;
    public static final int SERVER_RULES_PRESIDENT = 3;
    public static final int SERVER_RULES_TAROT = 4;
    public static final int SERVER_PLAY_GAME = 5;
    public static final int SERVER_DEALT = 6;
    public static final int SERVER_BIDDING_BELOTE = 7;
    public static final int SERVER_BIDDING_TAROT = 8;
    public static final int SERVER_DONE_BIDDING = 9;
    public static final int SERVER_DISCARDED_CARD_BELOTE = 10;
    public static final int SERVER_VALIDATE_DISCARD_BELOTE = 11;
    public static final int SERVER_VALIDATE_COMPLETED_HAND_BELOTE = 12;
    public static final int SERVER_VALIDATE_REFRESHED_HAND_PRESIDENT = 13;
    public static final int SERVER_DISCARDED_CARDS_PRESIDENT = 14;
    public static final int SERVER_DISCARDED_CARD_TAROT = 15;
    public static final int SERVER_VALIDATE_DISCARD_TAROT = 16;
    public static final int SERVER_PLAYING_BELOTE = 17;
    public static final int SERVER_PLAYING_PRESIDENT = 18;
    public static final int SERVER_PLAYING_TAROT = 19;
    public static final int SERVER_DONE_PLAYING = 20;
    public static final int SERVER_DONE_PAUSE = 21;
    public static final int SERVER_DONE_END_GAME = 22;
    public static final int SERVER_DONE_TRICKS = 23;
    public static final int SERVER_DONE_TEAMS = 24;
    public static final int SERVER_QUITTING = 25;
    public static final int RULES_BELOTE = 0;
    public static final int RULES_PRESIDENT = 1;
    public static final int RULES_TAROT = 2;
    public static final char VALIDATE_DISCARD_CALL_BEFORE = '0';
    public static final char VALIDATE_DISCARD_SIMPLE_CALL = '1';
    public static final char VALIDATE_DISCARD_SLAM = '2';
    public static final char SEP_0 = ':';
    public static final char SEP_1 = ';';
    public static final char SEP_2 = ',';
    public static final char SEP_3 = '!';
    public static final char SEP_4 = '*';
    public static final char SEP_5 = '$';
    public static final char SEP_6 = '=';
    public static final char SEP_7 = '@';
    public static final char SEP_8 = '&';
    public static final char SEP_9 = '|';
    private static final String CARDS = "CARDS";

    /** A used port for connections*/
    private static final int PORT = 667;

    private int nbPlayers;

    private boolean progressingGame;

    /**A useful facade for games*/

    private Games games = new Games();

//    private IntMap<String> playersLocales = new IntMap<String>();

    private final CustList<Longs> scores = new CustList<Longs>();

    private ByteMap<BoolVal> activePlayers;
    private ByteMap<BoolVal> received;
    private final IntArtCardGames ia;
    private final CustList<IntClientActLoopCards> clientAct = new CustList<IntClientActLoopCards>();
    private final CustList<IntServerActLoopCards> serverActLoopCards = new CustList<IntServerActLoopCards>();
    private final CustList<IntSplitPartsFields> splitInfo = new CustList<IntSplitPartsFields>();

    public Net(IntArtCardGames _i) {
        ia = _i;
        clientAct.add(new ClientActLoopCardsNewPlayer());
        clientAct.add(new ClientActLoopCardsOldPlayer());
        clientAct.add(new ClientActLoopCardsIndexArrive());
        clientAct.add(new ClientActLoopCardsChosenPlace());
        clientAct.add(new ClientActLoopCardsReady());
        clientAct.add(new ClientActLoopCardsRulesBelote());
        clientAct.add(new ClientActLoopCardsRulesPresident());
        clientAct.add(new ClientActLoopCardsRulesTarot());
        clientAct.add(new ClientActLoopCardsDealtHandBelote());
        clientAct.add(new ClientActLoopCardsDealtHandPresident());
        clientAct.add(new ClientActLoopCardsDealtHandTarot());
        clientAct.add(new ClientActLoopCardsAllowBiddingBelote());
        clientAct.add(new ClientActLoopCardsBiddingBelote());
        clientAct.add(new ClientActLoopCardsDiscardPhaseBelote());
        clientAct.add(new ClientActLoopCardsRefreshHandBelote());
        clientAct.add(new ClientActLoopCardsAllowDiscarding());
        clientAct.add(new ClientActLoopCardsReceivedGivenCards());
        clientAct.add(new ClientActLoopCardsAllowBiddingTarot());
        clientAct.add(new ClientActLoopCardsBiddingTarot());
        clientAct.add(new ClientActLoopCardsDiscardPhaseTarot());
        clientAct.add(new ClientActLoopCardsCallableCards());
        clientAct.add(new ClientActLoopCardsSlamTarot());
        clientAct.add(new ClientActLoopCardsAllowPlayingBelote());
        clientAct.add(new ClientActLoopCardsPlayingBelote());
        clientAct.add(new ClientActLoopCardsAllowPlayingPresident());
        clientAct.add(new ClientActLoopCardsPlayingPresident());
        clientAct.add(new ClientActLoopCardsAllowPlayingTarot());
        clientAct.add(new ClientActLoopCardsPlayingTarot());
        clientAct.add(new ClientActLoopCardsDonePause());
        clientAct.add(new ClientActLoopCardsResultsBelote());
        clientAct.add(new ClientActLoopCardsResultsPresident());
        clientAct.add(new ClientActLoopCardsResultsTarot());
        clientAct.add(new ClientActLoopCardsTricksHandsBelote());
        clientAct.add(new ClientActLoopCardsTricksHandsPresident());
        clientAct.add(new ClientActLoopCardsTricksHandsTarot());
        clientAct.add(new ClientActLoopCardsTeams());
        clientAct.add(new ClientActLoopCardsEnabledQuit());
        serverActLoopCards.add(new ServerActLoopCardsNewPlayer());
        serverActLoopCards.add(new ServerActLoopCardsOldPlayer());
        serverActLoopCards.add(new ServerActLoopCardsChosenPlace());
        serverActLoopCards.add(new ServerActLoopCardsReady());
        serverActLoopCards.add(new ServerActLoopCardsRulesBelote());
        serverActLoopCards.add(new ServerActLoopCardsRulesPresident());
        serverActLoopCards.add(new ServerActLoopCardsRulesTarot());
        serverActLoopCards.add(new ServerActLoopCardsPlayGame());
        serverActLoopCards.add(new ServerActLoopCardsDealt());
        serverActLoopCards.add(new ServerActLoopCardsBiddingBelote());
        serverActLoopCards.add(new ServerActLoopCardsBiddingTarot());
        serverActLoopCards.add(new ServerActLoopCardsActedByClientBid());
        serverActLoopCards.add(new ServerActLoopCardsDiscardedCardBelote());
        serverActLoopCards.add(new ServerActLoopCardsValidateDiscardBelote());
        serverActLoopCards.add(new ServerActLoopCardsActedByClientCompletedHandBelote());
        serverActLoopCards.add(new ServerActLoopCardsRefreshedHandPresident());
        serverActLoopCards.add(new ServerActLoopCardsDiscardedCardsPresident());
        serverActLoopCards.add(new ServerActLoopCardsDiscardedCardTarot());
        serverActLoopCards.add(new ServerActLoopCardsValidateDiscardTarot());
        serverActLoopCards.add(new ServerActLoopCardsPlayingBelote());
        serverActLoopCards.add(new ServerActLoopCardsPlayingPresident());
        serverActLoopCards.add(new ServerActLoopCardsPlayingTarot());
        serverActLoopCards.add(new ServerActLoopCardsDonePlaying());
        serverActLoopCards.add(new ServerActLoopCardsDonePause());
        serverActLoopCards.add(new ServerActLoopCardsActedByClientEndGame());
        serverActLoopCards.add(new ServerActLoopCardsActedByClientTricks());
        serverActLoopCards.add(new ServerActLoopCardsActedByClientTeams());
        serverActLoopCards.add(new ServerActLoopCardsQuittingDirect());
        splitInfo.add(new DefSplitPartsFields());
        splitInfo.add(new LimSplitPartsFields(1));
        splitInfo.add(new LimSplitPartsFields(2));
    }

    public IntArtCardGames getIa() {
        return ia;
    }

    public static String exportNewPlayer(int _index, String _pseudo) {
        StringBuilder out_ = new StringBuilder();
        out_.append(SEP_0);
        out_.append(_index);
        out_.append(SEP_0);
        out_.append(_pseudo);
        return out_.toString();
    }
    public static String exportOldPlayer(int _index, int _target, String _pseudo) {
        StringBuilder out_ = new StringBuilder();
        out_.append('-');
        out_.append(SEP_0);
        out_.append(_index);
        out_.append(SEP_0);
        out_.append(_target);
        out_.append(SEP_0);
        out_.append(_pseudo);
        return out_.toString();
    }
    public static String exportPlayGame() {
        StringBuilder out_ = new StringBuilder();
        out_.append(SERVER_PLAY_GAME);
        out_.append(SEP_0);
        return out_.toString();
    }
    public static String exportDealt(int _index) {
        StringBuilder out_ = new StringBuilder();
        out_.append(SERVER_DEALT);
        out_.append(SEP_0);
        out_.append(_index);
        return out_.toString();
    }
    public static String exportDoneBidding(int _index) {
        StringBuilder out_ = new StringBuilder();
        out_.append(SERVER_DONE_BIDDING);
        out_.append(SEP_0);
        out_.append(_index);
        return out_.toString();
    }
    public static String exportCompletedHandBelote(int _index) {
        StringBuilder out_ = new StringBuilder();
        out_.append(SERVER_VALIDATE_COMPLETED_HAND_BELOTE);
        out_.append(SEP_0);
        out_.append(_index);
        return out_.toString();
    }
    public static String exportRefreshedHandPresident(int _index) {
        StringBuilder out_ = new StringBuilder();
        out_.append(SERVER_VALIDATE_REFRESHED_HAND_PRESIDENT);
        out_.append(SEP_0);
        out_.append(_index);
        return out_.toString();
    }
    public static String exportSlamTarot() {
        StringBuilder out_ = new StringBuilder();
        out_.append(CLIENT_DEALT_SLAM_TAROT);
        out_.append(SEP_0);
        return out_.toString();
    }

    public static String exportDonePlaying(int _index) {
        StringBuilder out_ = new StringBuilder();
        out_.append(SERVER_DONE_PLAYING);
        out_.append(SEP_0);
        out_.append(_index);
        return out_.toString();
    }

    public static String exportDonePause(int _index) {
        StringBuilder out_ = new StringBuilder();
        out_.append(SERVER_DONE_PAUSE);
        out_.append(SEP_0);
        out_.append(_index);
        return out_.toString();
    }

    public static String exportDoneEndGame(int _index) {
        StringBuilder out_ = new StringBuilder();
        out_.append(SERVER_DONE_END_GAME);
        out_.append(SEP_0);
        out_.append(_index);
        return out_.toString();
    }

    public static String exportDoneTricks(int _index) {
        StringBuilder out_ = new StringBuilder();
        out_.append(SERVER_DONE_TRICKS);
        out_.append(SEP_0);
        out_.append(_index);
        return out_.toString();
    }

    public static String exportDoneTeams(int _index) {
        StringBuilder out_ = new StringBuilder();
        out_.append(SERVER_DONE_TEAMS);
        out_.append(SEP_0);
        out_.append(_index);
        return out_.toString();
    }

    public static String exportDonePause() {
        StringBuilder out_ = new StringBuilder();
        out_.append(CLIENT_DONE_PAUSE);
        out_.append(SEP_0);
        return out_.toString();
    }
    public static void loopClient(WindowNetWork _window, String _info, AbstractSocket _socket) {
        NetRetrievedInfos ret_ = netRetrievedInfos(_info, _window.getNet());
        loopClient(_window, ret_, _socket);
    }

    public static void loopClient(WindowNetWork _window, NetRetrievedInfos _ret, AbstractSocket _socket) {
        _window.getNet().clientAct.get(_ret.getIndexAct()).loop(_window, _ret.getParts(), _socket);
    }
    public static void loopServer(String _info, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        NetRetrievedInfos ret_ = netRetrievedInfos(_info, _instance);
        if (ret_.getIndexAct() < 0) {
            return;
        }
        loopServer(ret_, _instance, _fct, _common);
    }

    public static void loopServer(NetRetrievedInfos _ret, Net _instance, AbstractThreadFactory _fct, NetCommon _common) {
        _instance.serverActLoopCards.get(_ret.getIndexAct()).loop(_ret.getParts(),_instance,_fct,_common);
    }

    public static NetRetrievedInfos netRetrievedInfos(String _info, Net _instance) {
        return new NetRetrievedInfos(_instance.splitInfo, _info);
    }

    public static String exportIndexArrive(int _index, int _nbPlayers, NetCommon _common, Games _instance) {
        StringBuilder out_ = new StringBuilder();
        out_.append(CLIENT_INDEX_ARRIVE);
        out_.append(SEP_0);
        out_.append(_index);
        out_.append(SEP_0);
        out_.append(_nbPlayers);
        out_.append(SEP_0);
        out_.append(placesPlayers(_common.getPlacesPlayers()));
        out_.append(SEP_0);
        CustList<String> ready_ = new CustList<String>();
        for (EntryCust<Integer, BoolVal> e: _common.getReadyPlayers().entryList()) {
            ready_.add(e.getKey()+ NetCommon.exportBool(e.getValue()));
        }
        out_.append(StringUtil.join(ready_,SEP_1));
        out_.append(SEP_0);
        RulesBelote rulesBelote_ = _instance.getRulesBelote();
        RulesPresident rulesPresident_ = _instance.getRulesPresident();
        RulesTarot rulesTarot_ = _instance.getRulesTarot();
        if (rulesBelote_ != null) {
            out_.append(exportRulesBelote(RULES_BELOTE,rulesBelote_));
        }
        if (rulesPresident_ != null) {
            out_.append(exportRulesPresident(RULES_PRESIDENT,rulesPresident_));
        }
        if (rulesTarot_ != null) {
            out_.append(exportRulesTarot(RULES_TAROT,rulesTarot_));
        }
        return out_.toString();
    }
    public static IndexOfArrivingCards importIndexArrive(CustList<String> _info) {
        IndexOfArrivingCards index_ = new IndexOfArrivingCards();
        index_.setIndex(NumberUtil.parseInt(_info.get(0)));
        index_.setNbPlayers(NumberUtil.parseInt(_info.get(1)));
        index_.setPlacesPlayers(placePlayers(_info.get(2)));
        IntMap<BoolVal> ready_ = new IntMap<BoolVal>();
        for (String p: StringUtil.splitChar(_info.get(3),SEP_1)) {
            String k_ = p.substring(0,p.length()-1);
            ready_.addEntry(NumberUtil.parseInt(k_), toBoolValEndsWith(p));
        }
        index_.setReadyPlayers(ready_);
        char rulesFlag_ = _info.get(4).charAt(0);
        if (rulesFlag_ == '0'+RULES_BELOTE) {
            index_.setRulesBelote(importRulesBelote(_info, 5));
        }
        if (rulesFlag_ == '0'+RULES_PRESIDENT) {
            index_.setRulesPresident(importRulesPresident(_info,5));
        }
        if (rulesFlag_ == '0'+RULES_TAROT) {
            index_.setRulesTarot(importRulesTarot(_info,5));
        }
        return index_;
    }
    public static StringBuilder exportClientRulesBelote(RulesBelote _rules) {
        return exportRulesBelote(CLIENT_RULES_BELOTE,_rules);
    }
    public static StringBuilder exportServerRulesBelote(RulesBelote _rules) {
        return exportRulesBelote(SERVER_RULES_BELOTE,_rules);
    }
    public static StringBuilder exportRulesBelote(int _flag,RulesBelote _rules) {
        StringBuilder out_ = new StringBuilder();
        out_.append(_flag);
        out_.append(SEP_0);
        out_.append(_rules.getDealing().getSt());
        out_.append(SEP_0);
        out_.append(_rules.getCommon().getMixedCards().getMixSt());
        out_.append(SEP_0);
        CustList<String> bids_ = new CustList<String>();
        for (EntryCust<BidBelote,BoolVal> e: _rules.getAllowedBids().entryList()) {
            bids_.add(e.getKey().getSt()+ NetCommon.exportBool(e.getValue()));
        }
        out_.append(StringUtil.join(bids_,SEP_1));
        out_.append(SEP_0);
        CustList<String> decl_ = new CustList<String>();
        for (EntryCust<DeclaresBelote, BoolVal> e: _rules.getAllowedDeclares().entryList()) {
            decl_.add(e.getKey().getSt()+ NetCommon.exportBool(e.getValue()));
        }
        out_.append(StringUtil.join(decl_,SEP_1));
        out_.append(SEP_0);
        out_.append(_rules.getTrumpPartner().getBeloteTrSt());
        out_.append(SEP_0);
        out_.append(NetCommon.exportBool(_rules.isUnderTrumpFoe()));
        out_.append(NetCommon.exportBool(_rules.isClassicCountPoints()));
        return out_;
    }

    public static RulesBelote importRulesBelote(CustList<String> _info, int _offset) {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(BeloteCardsRetrieverUtil.toDealingBelote(_info.get(_offset)));
        rules_.getCommon().setMixedCards(EnumCardsRetrieverUtil.toMixCardsChoice(_info.get(1+_offset)));
        IdMap<BidBelote, BoolVal> bids_ = new IdMap<BidBelote, BoolVal>();
        for (String p: StringUtil.splitChar(_info.get(2+_offset),SEP_1)) {
            if (p.isEmpty()) {
                continue;
            }
            String k_ = p.substring(0,p.length()-1);
            bids_.addEntry(BeloteCardsRetrieverUtil.toBidBelote(k_),toBoolValEndsWith(p));
        }
        rules_.setAllowedBids(bids_);
        IdMap<DeclaresBelote, BoolVal> decl_ = new IdMap<DeclaresBelote, BoolVal>();
        for (String p: StringUtil.splitChar(_info.get(3+_offset),SEP_1)) {
            if (p.isEmpty()) {
                continue;
            }
            String k_ = p.substring(0,p.length()-1);
            decl_.addEntry(BeloteCardsRetrieverUtil.toDeclaresBelote(k_),toBoolValEndsWith(p));
        }
        rules_.setAllowedDeclares(decl_);
        rules_.setTrumpPartner(BeloteCardsRetrieverUtil.toBeloteTrumpPartner(_info.get(4+_offset)));
        String flags_ = _info.get(5+_offset);
        rules_.setUnderTrumpFoe(NetCommon.toBoolEquals(flags_,0));
        rules_.setClassicCountPoints(NetCommon.toBoolEquals(flags_,1));
        return rules_;
    }
    public static StringBuilder exportClientRulesPresident(RulesPresident _rules) {
        return exportRulesPresident(CLIENT_RULES_PRESIDENT,_rules);
    }
    public static StringBuilder exportServerRulesPresident(RulesPresident _rules) {
        return exportRulesPresident(SERVER_RULES_PRESIDENT,_rules);
    }
    public static StringBuilder exportRulesPresident(int _flag,RulesPresident _rules) {
        StringBuilder out_ = new StringBuilder();
        out_.append(_flag);
        out_.append(SEP_0);
        out_.append(_rules.getNbPlayers());
        out_.append(SEP_0);
        out_.append(_rules.getCommon().getMixedCards().getMixSt());
        out_.append(SEP_0);
        out_.append(_rules.getNbStacks());
        out_.append(SEP_0);
        out_.append(_rules.getEqualty().getSt());
        out_.append(SEP_0);
        out_.append(NetCommon.exportBool(_rules.isSwitchCards()));
        out_.append(NetCommon.exportBool(_rules.isLooserStartsFirst()));
        out_.append(NetCommon.exportBool(_rules.isPossibleReversing()));
        out_.append(NetCommon.exportBool(_rules.isHasToPlay()));
        out_.append(NetCommon.exportBool(_rules.isLoosingIfFinishByBestCards()));
        return out_;
    }

    public static RulesPresident importRulesPresident(CustList<String> _info, int _offset) {
        RulesPresident rules_ = new RulesPresident();
        rules_.setNbPlayers(NumberUtil.parseInt(_info.get(_offset)));
        rules_.getCommon().setMixedCards(EnumCardsRetrieverUtil.toMixCardsChoice(_info.get(1+_offset)));
        rules_.setNbStacks(NumberUtil.parseInt(_info.get(2+_offset)));
        rules_.setEqualty(PresidentCardsRetrieverUtil.toEqualtyPlaying(_info.get(3+_offset)));
        String flags_ = _info.get(4+_offset);
        rules_.setSwitchCards(NetCommon.toBoolEquals(flags_,0));
        rules_.setLooserStartsFirst(NetCommon.toBoolEquals(flags_,1));
        rules_.setPossibleReversing(NetCommon.toBoolEquals(flags_,2));
        rules_.setHasToPlay(NetCommon.toBoolEquals(flags_,3));
        rules_.setLoosingIfFinishByBestCards(NetCommon.toBoolEquals(flags_,4));
        return rules_;
    }
    public static StringBuilder exportClientRulesTarot(RulesTarot _rules) {
        return exportRulesTarot(CLIENT_RULES_TAROT,_rules);
    }
    public static StringBuilder exportServerRulesTarot(RulesTarot _rules) {
        return exportRulesTarot(SERVER_RULES_TAROT,_rules);
    }
    public static StringBuilder exportRulesTarot(int _flag,RulesTarot _rules) {
        StringBuilder out_ = new StringBuilder();
        out_.append(_flag);
        out_.append(_rules.getDealing().getSt());
        out_.append(SEP_0);
        out_.append(_rules.getCommon().getMixedCards().getMixSt());
        out_.append(SEP_0);
        out_.append(_rules.getMode().getSt());
        out_.append(NetCommon.exportBool(_rules.isAllowPlayCalledSuit()));
        out_.append(NetCommon.exportBool(_rules.getDiscardAfterCall()));
        out_.append(SEP_0);
        CustList<String> bids_ = new CustList<String>();
        for (EntryCust<BidTarot, BoolVal> e: _rules.getAllowedBids().entryList()) {
            bids_.add(e.getKey().getSt()+ NetCommon.exportBool(e.getValue()));
        }
        out_.append(StringUtil.join(bids_,SEP_1));
        out_.append(SEP_0);
        CustList<String> hand_ = new CustList<String>();
        for (EntryCust<Handfuls, Integer> e: _rules.getAllowedHandfuls().entryList()) {
            hand_.add(e.getKey().getSt()+e.getValue());
        }
        out_.append(StringUtil.join(hand_,SEP_1));
        out_.append(SEP_0);
        CustList<String> mis_ = new CustList<String>();
        for (Miseres e: _rules.getMiseres()) {
            mis_.add(e.getSt());
        }
        out_.append(StringUtil.join(mis_,SEP_1));
        out_.append(SEP_0);
        out_.append(_rules.getEndDealTarot().getSt());
        return out_;
    }

    public static RulesTarot importRulesTarot(CustList<String> _info, int _offset) {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(TarotCardsRetrieverUtil.toDealingTarot(_info.get(_offset)));
        rules_.getCommon().setMixedCards(EnumCardsRetrieverUtil.toMixCardsChoice(_info.get(1+_offset)));
        String flags_ = _info.get(2+_offset);
        rules_.setMode(TarotCardsRetrieverUtil.toModeTarot(flags_.substring(0,1)));
        rules_.setAllowPlayCalledSuit(NetCommon.toBoolEquals(flags_,1));
        rules_.setDiscardAfterCall(NetCommon.toBoolEquals(flags_,2));
        IdMap<BidTarot, BoolVal> bids_ = new IdMap<BidTarot, BoolVal>();
        for (String p: StringUtil.splitChar(_info.get(3+_offset),SEP_1)) {
            if (p.isEmpty()) {
                continue;
            }
            String k_ = p.substring(0,p.length()-1);
            bids_.addEntry(TarotCardsRetrieverUtil.toBidTarot(k_),toBoolValEndsWith(p));
        }
        rules_.setAllowedBids(bids_);
        IdMap<Handfuls, Integer> hand_ = new IdMap<Handfuls, Integer>();
        for (String p: StringUtil.splitChar(_info.get(4+_offset),SEP_1)) {
            if (p.isEmpty()) {
                continue;
            }
            String k_ = p.substring(0,1);
            hand_.addEntry(TarotCardsRetrieverUtil.toHandfuls(k_),NumberUtil.parseInt(p.substring(1)));
        }
        rules_.setAllowedHandfuls(hand_);
        IdList<Miseres> mis_ = new IdList<Miseres>();
        for (String p: StringUtil.splitChar(_info.get(5+_offset),SEP_1)) {
            if (p.isEmpty()) {
                continue;
            }
            mis_.add(TarotCardsRetrieverUtil.toMiseres(p));
        }
        rules_.setMiseres(mis_);
        rules_.setEndDealTarot(TarotCardsRetrieverUtil.toEndDealTarot(_info.get(6+_offset)));
        return rules_;
    }
    public static String exportClientChosenPlace(int _index, int _place, AbsMap<Integer,Byte> _map) {
        return exportChosenPlace(CLIENT_CHOSEN_PLACE,_index,_place,_map);
    }
    public static String exportServerChosenPlace(int _index, int _place, AbsMap<Integer,Byte> _map) {
        return exportChosenPlace(SERVER_CHOSEN_PLACE,_index,_place,_map);
    }
    public static String exportChosenPlace(int _flag,int _index, int _place, AbsMap<Integer,Byte> _map) {
        StringBuilder out_ = new StringBuilder();
        out_.append(_flag);
        out_.append(SEP_0);
        out_.append(_index);
        out_.append(SEP_0);
        out_.append(_place);
        out_.append(SEP_0);
        out_.append(placesPlayers(_map));
        return out_.toString();
    }

    public static ChoosenPlace importChosenPlace(CustList<String> _info) {
        ChoosenPlace chosen_ = new ChoosenPlace();
        chosen_.setIndex(NumberUtil.parseInt(_info.get(0)));
        chosen_.setPlace(NumberUtil.parseInt(_info.get(1)));
        chosen_.setPlacesPlayers(placePlayers(_info.get(2)));
        return chosen_;
    }
    public static String exportClientReady(int _index, boolean _value) {
        return exportReady(CLIENT_READY,_index,_value);
    }
    public static String exportServerReady(int _index, boolean _value) {
        return exportReady(SERVER_READY,_index,_value);
    }
    public static String exportReady(int _flag,int _index, boolean _value) {
        StringBuilder out_ = new StringBuilder();
        out_.append(_flag);
        out_.append(SEP_0);
        out_.append(_index);
        out_.append(SEP_0);
        out_.append(NetCommon.exportBool(_value));
        return out_.toString();
    }

    public static ReadyCards importReady(CustList<String> _info) {
        ReadyCards chosen_ = new ReadyCards();
        chosen_.setIndex(NumberUtil.parseInt(_info.get(0)));
        chosen_.getContent().setReady(NetCommon.toBoolEquals(_info.get(1)));
        return chosen_;
    }
    public static String exportDealtHandBelote(DealtHandBelote _dealt) {
        StringBuilder out_ = new StringBuilder();
        out_.append(CLIENT_DEALT_HAND_BELOTE);
        out_.append(SEP_0);
        out_.append(_dealt.getDealer());
        out_.append(SEP_0);
        out_.append(exportBidBeloteSuitList(_dealt.getAllowedBids(), SEP_1, SEP_2));
        out_.append(SEP_0);
        out_.append(exportHandBelote(_dealt.getCards(), SEP_1));
        out_.append(SEP_0);
        out_.append(exportHandBelote(_dealt.getDeck(), SEP_1));
        return out_.toString();
    }

    public static DealtHandBelote importDealtHandBelote(CustList<String> _info) {
        DealtHandBelote belote_ = new DealtHandBelote();
        belote_.setDealer((byte) NumberUtil.parseInt(_info.get(0)));
        belote_.setAllowedBids(importBidBeloteSuitList(_info.get(1), SEP_1, SEP_2));
        belote_.setCards(importHandBelote(_info.get(2), SEP_1));
        belote_.setDeck(importHandBelote(_info.get(3), SEP_1));
        return belote_;
    }
    public static String exportDealtHandPresident(DealtHandPresident _dealt) {
        StringBuilder out_ = new StringBuilder();
        out_.append(CLIENT_DEALT_HAND_PRESIDENT);
        out_.append(SEP_0);
        out_.append(_dealt.getDealer());
        out_.append(SEP_0);
        out_.append(_dealt.getMaxCards());
        out_.append(SEP_0);
        out_.append(exportPlayingMap(_dealt.getStatus(), SEP_1, SEP_2));
        out_.append(SEP_0);
        out_.append(exportHandPresident(_dealt.getCards(), SEP_1));
        return out_.toString();
    }

    public static DealtHandPresident importDealtHandPresident(CustList<String> _info) {
        DealtHandPresident president_ = new DealtHandPresident();
        president_.setDealer((byte) NumberUtil.parseInt(_info.get(0)));
        president_.setMaxCards(NumberUtil.parseInt(_info.get(1)));
        president_.setStatus(importPlayingMap(_info.get(2), SEP_1, SEP_2));
        president_.setCards(importHandPresident(_info.get(3), SEP_1));
        return president_;
    }
    public static String exportDealtHandTarot(DealtHandTarot _dealt) {
        StringBuilder out_ = new StringBuilder();
        out_.append(CLIENT_DEALT_HAND_TAROT);
        out_.append(SEP_0);
        out_.append(_dealt.getDealer());
        out_.append(SEP_0);
        out_.append(exportBidTarotList(_dealt.getAllowedBids(), SEP_1));
        out_.append(SEP_0);
        out_.append(exportHandTarot(_dealt.getDog(), SEP_1));
        out_.append(SEP_0);
        out_.append(exportHandTarot(_dealt.getCards(), SEP_1));
        return out_.toString();
    }

    public static DealtHandTarot importDealtHandTarot(CustList<String> _info) {
        DealtHandTarot tarot_ = new DealtHandTarot();
        tarot_.setDealer((byte) NumberUtil.parseInt(_info.get(0)));
        tarot_.setAllowedBids(importBidTarotList(_info.get(1), SEP_1));
        tarot_.setDog(importHandTarot(_info.get(2), SEP_1));
        tarot_.setCards(importHandTarot(_info.get(3), SEP_1));
        return tarot_;
    }

    public static String exportAllowBiddingBelote(AllowBiddingBelote _dealt) {
        StringBuilder out_ = new StringBuilder();
        out_.append(CLIENT_DEALT_ALLOW_BIDDING_BELOTE);
        out_.append(SEP_0);
        out_.append(exportBidBeloteSuit(SEP_1, _dealt.getBid()));
        out_.append(SEP_0);
        out_.append(exportBidBeloteSuitList(_dealt.getBids(), SEP_1, SEP_2));
        return out_.toString();
    }

    public static AllowBiddingBelote importAllowBiddingBelote(CustList<String> _info) {
        AllowBiddingBelote belote_ = new AllowBiddingBelote();
        belote_.setBid(importBidBeloteSuit(SEP_1, _info.get(0)));
        belote_.setBids(importBidBeloteSuitList(_info.get(1), SEP_1, SEP_2));
        return belote_;
    }

    public static String exportClientBiddingBelote(BiddingBelote _dealt) {
        return exportBiddingBelote(CLIENT_DEALT_BIDDING_BELOTE, _dealt);
    }

    public static String exportServerBiddingBelote(BiddingBelote _dealt) {
        return exportBiddingBelote(SERVER_BIDDING_BELOTE, _dealt);
    }

    private static String exportBiddingBelote(int _flag, BiddingBelote _dealt) {
        StringBuilder out_ = new StringBuilder();
        out_.append(_flag);
        out_.append(SEP_0);
        out_.append(_dealt.getPlace());
        out_.append(SEP_0);
        out_.append(exportBidBeloteSuit(SEP_1, _dealt.getBidBelote()));
        return out_.toString();
    }

    public static BiddingBelote importBiddingBelote(CustList<String> _info) {
        BiddingBelote belote_ = new BiddingBelote();
        belote_.setPlace((byte) NumberUtil.parseInt(_info.get(0)));
        belote_.setBidBelote(importBidBeloteSuit(SEP_1, _info.get(1)));
        return belote_;
    }

    public static String exportDiscardPhaseBelote(DiscardPhaseBelote _dealt) {
        StringBuilder out_ = new StringBuilder();
        out_.append(CLIENT_DEALT_DISCARD_PHASE_BELOTE);
        out_.append(SEP_0);
        out_.append(_dealt.getDiscardPhase().getTaker());
        out_.append(_dealt.getDiscardPhase().getTakerIndex());
        out_.append(SEP_0);
        out_.append(exportHandBelote(_dealt.getDiscard(), SEP_1));
        return out_.toString();
    }

    public static DiscardPhaseBelote importDiscardPhaseBelote(CustList<String> _info) {
        DiscardPhaseBelote belote_ = new DiscardPhaseBelote();
        String str_ = _info.get(0);
        belote_.getDiscardPhase().setTaker(NumberUtil.parseInt(str_.substring(0,1)));
        belote_.getDiscardPhase().setTakerIndex(NumberUtil.parseInt(str_.substring(1)));
        belote_.setDiscard(importHandBelote(_info.get(1), SEP_1));
        return belote_;
    }

    public static String exportDiscardedCardBelote(DiscardedCardBelote _dealt) {
        StringBuilder out_ = new StringBuilder();
        out_.append(SERVER_DISCARDED_CARD_BELOTE);
        out_.append(SEP_0);
        out_.append(_dealt.getPlace());
        out_.append(SEP_0);
        out_.append(BeloteCardsExporterUtil.fromCardBelote(_dealt.getCard()));
        return out_.toString();
    }

    public static DiscardedCardBelote importDiscardedCardBelote(CustList<String> _info) {
        DiscardedCardBelote belote_ = new DiscardedCardBelote();
        belote_.setPlace((byte) NumberUtil.parseInt(_info.get(0)));
        belote_.setCard(BeloteCardsRetrieverUtil.toCardBelote(_info.get(1)));
        return belote_;
    }

    public static String exportDiscardSimple() {
        StringBuilder out_ = new StringBuilder();
        out_.append(SERVER_VALIDATE_DISCARD_BELOTE);
        out_.append(SEP_0);
        out_.append(VALIDATE_DISCARD_SIMPLE_CALL);
        return out_.toString();
    }

    public static String exportDiscardSlam() {
        StringBuilder out_ = new StringBuilder();
        out_.append(SERVER_VALIDATE_DISCARD_BELOTE);
        out_.append(SEP_0);
        out_.append(VALIDATE_DISCARD_SLAM);
        return out_.toString();
    }

    public static String exportRefreshHandBelote(RefreshHandBelote _r) {
        StringBuilder out_ = new StringBuilder();
        out_.append(CLIENT_DEALT_REFRESH_HAND_BELOTE);
        out_.append(SEP_0);
        out_.append(_r.getPlace());
        out_.append(SEP_0);
        out_.append(_r.getTakerIndex());
        out_.append(SEP_0);
        out_.append(exportHandBelote(_r.getRefreshedHand(),SEP_1));
        return out_.toString();
    }

    public static RefreshHandBelote importRefreshHandBelote(CustList<String> _info) {
        RefreshHandBelote belote_ = new RefreshHandBelote();
        belote_.setPlace((byte) NumberUtil.parseInt(_info.get(0)));
        belote_.setTakerIndex((byte) NumberUtil.parseInt(_info.get(1)));
        belote_.setRefreshedHand(importHandBelote(_info.get(2), SEP_1));
        return belote_;
    }

    public static String exportAllowDiscarding(HandPresident _one) {
        StringBuilder out_ = new StringBuilder();
        out_.append(CLIENT_DEALT_ALLOW_DISCARDING);
        out_.append(SEP_0);
        out_.append(exportHandPresident(_one,SEP_1));
        return out_.toString();
    }

    public static AllowDiscarding importAllowDiscarding(CustList<String> _info) {
        AllowDiscarding president_ = new AllowDiscarding();
        president_.setReceivedCards(importHandPresident(_info.get(0), SEP_1));
        return president_;
    }

    public static String exportReceivedGivenCards(HandPresident _received, HandPresident _given, HandPresident _newHand) {
        StringBuilder president_ = new StringBuilder();
        president_.append(CLIENT_DEALT_RECEIVED_GIVEN_CARDS);
        president_.append(SEP_0);
        president_.append(exportHandPresident(_received,SEP_1));
        president_.append(SEP_0);
        president_.append(exportHandPresident(_given,SEP_1));
        president_.append(SEP_0);
        president_.append(exportHandPresident(_newHand,SEP_1));
        return president_.toString();
    }

    public static ReceivedGivenCards importReceivedGivenCards(CustList<String> _info) {
        ReceivedGivenCards out_ = new ReceivedGivenCards();
        out_.setReceived(importHandPresident(_info.get(0), SEP_1));
        out_.setGiven(importHandPresident(_info.get(1), SEP_1));
        out_.setNewHand(importHandPresident(_info.get(2), SEP_1));
        return out_;
    }

    public static String exportDiscardedCardsPresident(int _place,HandPresident _dis) {
        StringBuilder president_ = new StringBuilder();
        president_.append(SERVER_DISCARDED_CARDS_PRESIDENT);
        president_.append(SEP_0);
        president_.append(_place);
        president_.append(SEP_0);
        president_.append(exportHandPresident(_dis,SEP_1));
        return president_.toString();
    }

    public static DiscardedCardsPresident importDiscardedCardsPresident(CustList<String> _info) {
        DiscardedCardsPresident out_ = new DiscardedCardsPresident();
        out_.setPlace((byte) NumberUtil.parseInt(_info.get(0)));
        out_.setDiscarded(importHandPresident(_info.get(1), SEP_1));
        return out_;
    }

    public static String exportAllowBiddingTarot(AllowBiddingTarot _dealt) {
        StringBuilder out_ = new StringBuilder();
        out_.append(CLIENT_DEALT_ALLOW_BIDDING_TAROT);
        out_.append(SEP_0);
        out_.append(_dealt.getMaxBid().getSt());
        out_.append(SEP_0);
        out_.append(exportBidTarotList(_dealt.getBids(), SEP_1));
        return out_.toString();
    }

    public static AllowBiddingTarot importAllowBiddingTarot(CustList<String> _info) {
        AllowBiddingTarot tarot_ = new AllowBiddingTarot();
        tarot_.setMaxBid(TarotCardsRetrieverUtil.toBidTarot(_info.get(0)));
        tarot_.setBids(importBidTarotList(_info.get(1), SEP_1));
        return tarot_;
    }

    public static String exportClientBiddingTarot(BiddingTarot _dealt) {
        return exportBiddingTarot(CLIENT_DEALT_BIDDING_TAROT, _dealt);
    }

    public static String exportServerBiddingTarot(BiddingTarot _dealt) {
        return exportBiddingTarot(SERVER_BIDDING_TAROT, _dealt);
    }

    private static String exportBiddingTarot(int _flag, BiddingTarot _dealt) {
        StringBuilder out_ = new StringBuilder();
        out_.append(_flag);
        out_.append(SEP_0);
        out_.append(_dealt.getPlace());
        out_.append(SEP_0);
        out_.append(_dealt.getBid().getSt());
        return out_.toString();
    }

    public static BiddingTarot importBiddingTarot(CustList<String> _info) {
        BiddingTarot tarot_ = new BiddingTarot();
        tarot_.setPlace((byte) NumberUtil.parseInt(_info.get(0)));
        tarot_.setBid(TarotCardsRetrieverUtil.toBidTarot(_info.get(1)));
        return tarot_;
    }

    public static String exportDiscardPhaseTarot(DiscardPhaseTarot _dealt) {
        StringBuilder out_ = new StringBuilder();
        out_.append(CLIENT_DEALT_DISCARD_PHASE_TAROT);
        out_.append(SEP_0);
        out_.append(NetCommon.exportBool(_dealt.isCallAfter()));
        out_.append(_dealt.getDiscardPhase().getTaker());
        out_.append(_dealt.getDiscardPhase().getTakerIndex());
        out_.append(SEP_0);
        out_.append(exportHandTarot(_dealt.getDiscardCard(), SEP_1));
        out_.append(SEP_0);
        out_.append(exportHandTarot(_dealt.getCallableCards(), SEP_1));
        return out_.toString();
    }

    public static DiscardPhaseTarot importDiscardPhaseTarot(CustList<String> _info) {
        DiscardPhaseTarot tarot_ = new DiscardPhaseTarot();
        String str_ = _info.get(0);
        tarot_.setCallAfter(NetCommon.toBoolEquals(str_,0));
        tarot_.getDiscardPhase().setTaker(NumberUtil.parseInt(str_.substring(1,2)));
        tarot_.getDiscardPhase().setTakerIndex(NumberUtil.parseInt(str_.substring(2)));
        tarot_.setDiscardCard(importHandTarot(_info.get(1), SEP_1));
        tarot_.setCallableCards(importHandTarot(_info.get(2), SEP_1));
        return tarot_;
    }

    public static String exportDiscardedCardTarot(DiscardedCardTarot _dealt) {
        StringBuilder out_ = new StringBuilder();
        out_.append(SERVER_DISCARDED_CARD_TAROT);
        out_.append(SEP_0);
        out_.append(_dealt.getPlace());
        out_.append(SEP_0);
        out_.append(TarotCardsExporterUtil.fromCardTarot(_dealt.getCard()));
        return out_.toString();
    }

    public static DiscardedCardTarot importDiscardedCardTarot(CustList<String> _info) {
        DiscardedCardTarot tarot_ = new DiscardedCardTarot();
        tarot_.setPlace((byte) NumberUtil.parseInt(_info.get(0)));
        tarot_.setCard(TarotCardsRetrieverUtil.toCardTarot(_info.get(1)));
        return tarot_;
    }

    public static String exportCallableCards(CallableCards _dealt) {
        StringBuilder out_ = new StringBuilder();
        out_.append(CLIENT_DEALT_CALLABLE_CARDS);
        out_.append(SEP_0);
        out_.append(NetCommon.exportBool(_dealt.isDiscarding()));
        out_.append(_dealt.getTakerIndex());
        out_.append(SEP_0);
        out_.append(exportHandTarot(_dealt.getCards(),SEP_1));
        return out_.toString();
    }
    public static CallableCards importCallableCards(CustList<String> _info) {
        CallableCards c_ = new CallableCards();
        String i_ = _info.get(0);
        c_.setDiscarding(NetCommon.toBoolEquals(i_,0));
        c_.setTakerIndex((byte) NumberUtil.parseInt(i_.substring(1)));
        c_.setCards(importHandTarot(_info.get(1),SEP_1));
        return c_;
    }


    public static String exportDiscardCallBefore(HandTarot _call) {
        StringBuilder out_ = new StringBuilder();
        out_.append(SERVER_VALIDATE_DISCARD_TAROT);
        out_.append(SEP_0);
        out_.append(VALIDATE_DISCARD_CALL_BEFORE);
        out_.append(SEP_0);
        out_.append(exportHandTarot(_call,SEP_1));
        return out_.toString();
    }

    public static String exportDiscardSimpleCall(CardTarot _call) {
        StringBuilder out_ = new StringBuilder();
        out_.append(SERVER_VALIDATE_DISCARD_TAROT);
        out_.append(SEP_0);
        out_.append(VALIDATE_DISCARD_SIMPLE_CALL);
        if (_call != CardTarot.WHITE) {
            out_.append(SEP_0);
            out_.append(TarotCardsExporterUtil.fromCardTarot(_call));
        }
        return out_.toString();
    }

    public static String exportDiscardSlam(CardTarot _call) {
        StringBuilder out_ = new StringBuilder();
        out_.append(SERVER_VALIDATE_DISCARD_TAROT);
        out_.append(SEP_0);
        out_.append(VALIDATE_DISCARD_SLAM);
        if (_call != CardTarot.WHITE) {
            out_.append(SEP_0);
            out_.append(TarotCardsExporterUtil.fromCardTarot(_call));
        }
        return out_.toString();
    }

    public static String exportAllowPlayingBelote(AllowPlayingBelote _dealt) {
        StringBuilder out_ = new StringBuilder();
        out_.append(CLIENT_DEALT_ALLOW_PLAYING_BELOTE);
        out_.append(SEP_0);
        out_.append(NetCommon.exportBool(_dealt.isFirstRoundPlaying()));
        out_.append(NetCommon.exportBool(_dealt.isPossibleBeloteRebelote()));
        out_.append(NetCommon.exportBool(_dealt.isAllowedBeloteRebelote()));
        out_.append(_dealt.getTakerIndex());
        out_.append(SEP_0);
        out_.append(exportBidBeloteSuit(SEP_1, _dealt.getCurrentBid()));
        out_.append(SEP_0);
        out_.append(exportHandBelote(_dealt.getBelReb(), SEP_1));
        out_.append(SEP_0);
        out_.append(exportHandBelote(_dealt.getCards(), SEP_1));
        out_.append(SEP_0);
        out_.append(exportDeclareHandBelote(_dealt.getDeclaration(),SEP_0,SEP_1));
        return out_.toString();
    }

    public static AllowPlayingBelote importAllowPlayingBelote(CustList<String> _info) {
        AllowPlayingBelote belote_ = new AllowPlayingBelote();
        String i_ = _info.get(0);
        belote_.setFirstRoundPlaying(NetCommon.toBoolEquals(i_,0));
        belote_.setPossibleBeloteRebelote(NetCommon.toBoolEquals(i_,1));
        belote_.setAllowedBeloteRebelote(NetCommon.toBoolEquals(i_,2));
        belote_.setTakerIndex((byte) NumberUtil.parseInt(i_.substring(3)));
        belote_.setCurrentBid(importBidBeloteSuit(SEP_1, _info.get(1)));
        belote_.setBelReb(importHandBelote(_info.get(2), SEP_1));
        belote_.setCards(importHandBelote(_info.get(3), SEP_1));
        belote_.setDeclaration(importDeclareHandBelote(_info,4, SEP_1));
        return belote_;
    }

    public static String exportClientPlayingBelote(PlayingCardBelote _dealt) {
        return exportPlayingBelote(CLIENT_DEALT_PLAYING_BELOTE, _dealt);
    }

    public static String exportServerPlayingBelote(PlayingCardBelote _dealt) {
        return exportPlayingBelote(SERVER_PLAYING_BELOTE, _dealt);
    }

    private static String exportPlayingBelote(int _flag, PlayingCardBelote _dealt) {
        StringBuilder out_ = new StringBuilder();
        out_.append(_flag);
        out_.append(SEP_0);
        out_.append(_dealt.getPlace());
        out_.append(SEP_0);
        out_.append(NetCommon.exportBool(_dealt.isRefreshing()));
        out_.append(NetCommon.exportBool(_dealt.isDeclaringBeloteRebelote()));
        out_.append(NetCommon.exportBool(_dealt.isDeclaring()));
        out_.append(_dealt.getTakerIndex());
        out_.append(SEP_0);
        out_.append(BeloteCardsExporterUtil.fromCardBelote(_dealt.getPlayedCard()));
        out_.append(SEP_0);
        out_.append(exportDeclareHandBelote(_dealt.getDeclare(),SEP_0,SEP_1));
        return out_.toString();
    }

    public static PlayingCardBelote importPlayingBelote(CustList<String> _info) {
        PlayingCardBelote belote_ = new PlayingCardBelote();
        belote_.setPlace((byte) NumberUtil.parseInt(_info.get(0)));
        String i_ = _info.get(1);
        belote_.setRefreshing(NetCommon.toBoolEquals(i_,0));
        belote_.setDeclaringBeloteRebelote(NetCommon.toBoolEquals(i_,1));
        belote_.setDeclaring(NetCommon.toBoolEquals(i_,2));
        belote_.setTakerIndex((byte) NumberUtil.parseInt(i_.substring(3)));
        belote_.setPlayedCard(BeloteCardsRetrieverUtil.toCardBelote(_info.get(2)));
        belote_.setDeclare(importDeclareHandBelote(_info,3,SEP_1));
        return belote_;
    }

    public static String exportAllowPlayingPresident(AllowPlayingPresident _dealt) {
        StringBuilder out_ = new StringBuilder();
        out_.append(CLIENT_DEALT_ALLOW_PLAYING_PRESIDENT);
        out_.append(SEP_0);
        out_.append(NetCommon.exportBool(_dealt.isReversed()));
        out_.append(NetCommon.exportBool(_dealt.isEnabledPass()));
        out_.append(_dealt.getStatus().getPlay());
        out_.append(SEP_0);
        out_.append(exportHandPresident(_dealt.getCards(), SEP_1));
        return out_.toString();
    }

    public static AllowPlayingPresident importAllowPlayingPresident(CustList<String> _info) {
        AllowPlayingPresident president_ = new AllowPlayingPresident();
        String i_ = _info.get(0);
        president_.setReversed(NetCommon.toBoolEquals(i_,0));
        president_.setEnabledPass(NetCommon.toBoolEquals(i_,1));
        president_.setStatus(Playing.retrieve(i_.substring(2)));
        president_.setCards(importHandPresident(_info.get(1), SEP_1));
        return president_;
    }

    public static String exportClientPlayingPresident(PlayingCardPresident _dealt) {
        return exportPlayingPresident(CLIENT_DEALT_PLAYING_PRESIDENT, _dealt);
    }

    public static String exportServerPlayingPresident(PlayingCardPresident _dealt) {
        return exportPlayingPresident(SERVER_PLAYING_PRESIDENT, _dealt);
    }

    private static String exportPlayingPresident(int _flag, PlayingCardPresident _dealt) {
        StringBuilder out_ = new StringBuilder();
        out_.append(_flag);
        out_.append(SEP_0);
        out_.append(_dealt.getPlace());
        out_.append(SEP_0);
        out_.append(NetCommon.exportBool(_dealt.isRefreshing()));
        out_.append(NetCommon.exportBool(_dealt.isPass()));
        out_.append(NetCommon.exportBool(_dealt.isReversed()));
        out_.append(PresidentCardsExporterUtil.fromCardPresident(_dealt.getPlayedCard()));
        out_.append(SEP_0);
        out_.append(_dealt.getIndex());
        out_.append(SEP_0);
        out_.append(_dealt.getNextPlayer());
        out_.append(SEP_0);
        out_.append(exportHandPresident(_dealt.getPlayedHand(),SEP_1));
        out_.append(SEP_0);
        out_.append(exportPlayingMap(_dealt.getStatus(),SEP_1,SEP_2));
        return out_.toString();
    }

    public static PlayingCardPresident importPlayingPresident(CustList<String> _info) {
        PlayingCardPresident president_ = new PlayingCardPresident();
        president_.setPlace((byte) NumberUtil.parseInt(_info.get(0)));
        String i_ = _info.get(1);
        president_.setRefreshing(NetCommon.toBoolEquals(i_,0));
        president_.setPass(NetCommon.toBoolEquals(i_,1));
        president_.setReversed(NetCommon.toBoolEquals(i_,2));
        president_.setPlayedCard(PresidentCardsRetrieverUtil.toCardPresident(i_.substring(3)));
        president_.setIndex((byte) NumberUtil.parseInt(_info.get(2)));
        president_.setNextPlayer((byte) NumberUtil.parseInt(_info.get(3)));
        president_.setPlayedHand(importHandPresident(_info.get(4),SEP_1));
        president_.setStatus(importPlayingMap(_info.get(5),SEP_1,SEP_2));
        return president_;
    }

    public static String exportAllowPlayingTarot(AllowPlayingTarot _dealt) {
        StringBuilder out_ = new StringBuilder();
        out_.append(CLIENT_DEALT_ALLOW_PLAYING_TAROT);
        out_.append(SEP_0);
        out_.append(NetCommon.exportBool(_dealt.isFirstRoundPlaying()));
        out_.append(_dealt.getTakerIndex());
        out_.append(SEP_0);
        out_.append(_dealt.getCurrentBid().getSt());
        out_.append(SEP_0);
        out_.append(exportHandTarot(_dealt.getCards(), SEP_1));
        out_.append(SEP_0);
        out_.append(exportHandTarot(_dealt.getCalledCards(), SEP_1));
        out_.append(SEP_0);
        out_.append(exportHandTarot(_dealt.getDiscardedTrumps(),SEP_1));
        return out_.toString();
    }

    public static AllowPlayingTarot importAllowPlayingTarot(CustList<String> _info) {
        AllowPlayingTarot tarot_ = new AllowPlayingTarot();
        String i_ = _info.get(0);
        tarot_.setFirstRoundPlaying(NetCommon.toBoolEquals(i_,0));
        tarot_.setTakerIndex((byte) NumberUtil.parseInt(i_.substring(1)));
        tarot_.setCurrentBid(TarotCardsRetrieverUtil.toBidTarot(_info.get(1)));
        tarot_.setCards(importHandTarot(_info.get(2), SEP_1));
        tarot_.setCalledCards(importHandTarot(_info.get(3), SEP_1));
        tarot_.setDiscardedTrumps(importHandTarot(_info.get(4), SEP_1));
        return tarot_;
    }

    public static String exportClientPlayingTarot(PlayingCardTarot _dealt) {
        return exportPlayingTarot(CLIENT_DEALT_PLAYING_TAROT, _dealt);
    }

    public static String exportServerPlayingTarot(PlayingCardTarot _dealt) {
        return exportPlayingTarot(SERVER_PLAYING_TAROT, _dealt);
    }

    private static String exportPlayingTarot(int _flag, PlayingCardTarot _dealt) {
        StringBuilder out_ = new StringBuilder();
        out_.append(_flag);
        out_.append(SEP_0);
        out_.append(_dealt.getPlace());
        out_.append(SEP_0);
        out_.append(NetCommon.exportBool(_dealt.isRefreshing()));
        out_.append(NetCommon.exportBool(_dealt.isCalledCard()));
        out_.append(_dealt.getTakerIndex());
        out_.append(SEP_0);
        out_.append(TarotCardsExporterUtil.fromCardTarot(_dealt.getPlayedCard()));
        out_.append(SEP_0);
        out_.append(TarotCardsExporterUtil.fromHandfuls(_dealt.getChoosenHandful()));
        out_.append(SEP_0);
        out_.append(exportHandTarot(_dealt.getHandful(),SEP_1));
        out_.append(exportHandTarot(_dealt.getExcludedTrumps(),SEP_1));
        out_.append(exportMiseres(_dealt.getMiseres(),SEP_1));
        return out_.toString();
    }

    public static PlayingCardTarot importPlayingTarot(CustList<String> _info) {
        PlayingCardTarot tarot_ = new PlayingCardTarot();
        tarot_.setPlace((byte) NumberUtil.parseInt(_info.get(0)));
        String i_ = _info.get(1);
        tarot_.setRefreshing(NetCommon.toBoolEquals(i_,0));
        tarot_.setCalledCard(NetCommon.toBoolEquals(i_,1));
        tarot_.setTakerIndex((byte) NumberUtil.parseInt(i_.substring(2)));
        tarot_.setPlayedCard(TarotCardsRetrieverUtil.toCardTarot(_info.get(2)));
        tarot_.setChoosenHandful(TarotCardsRetrieverUtil.toHandfuls(_info.get(3)));
        tarot_.setHandful(importHandTarot(_info.get(4),SEP_1));
        tarot_.setExcludedTrumps(importHandTarot(_info.get(5),SEP_1));
        tarot_.setMiseres(importMiseres(_info.get(6),SEP_1));
        return tarot_;
    }

    public static String exportGameBelote(ResultsBelote _dealt) {
        CustList<Longs> scores_ = _dealt.getRes().getScores();
        GameBelote game_ = _dealt.getGame();
        StringBuilder out_ = new StringBuilder();
        out_.append(CLIENT_RES_BELOTE);
        out_.append(SEP_0);
        out_.append(exportLongsList(scores_,SEP_1,SEP_2));
        out_.append(SEP_0);
        out_.append(game_.getType().getGameTypeSt());
        out_.append(SEP_0);
        out_.append(game_.getDeal().getNbDeals());
        out_.append(SEP_0);
        out_.append(game_.getDeal().getDealer());
        out_.append(SEP_0);
        out_.append(exportDealBelote(game_.getDeal(),SEP_1,SEP_2));
        out_.append(SEP_0);
        out_.append(exportDeclareHandBeloteList(game_.getDeclares(),SEP_1,SEP_2,SEP_3));
        out_.append(SEP_0);
        out_.append(exportHandBeloteList(game_.getDeclaresBeloteRebelote(),SEP_1,SEP_2));
        out_.append(SEP_0);
        out_.append(exportBoolList(game_.getWonLastTrick()));
        out_.append(SEP_0);
        out_.append(exportTrickBeloteList(game_.getTricks(),SEP_1,SEP_2));
        out_.append(SEP_0);
        out_.append(exportTrickBelote(game_.getProgressingTrick(),SEP_1));
        out_.append(SEP_0);
        out_.append(exportBidBeloteSuitList(game_.getBids(),SEP_1,SEP_2));
        out_.append(SEP_0);
        out_.append(exportShortList(game_.getScores(),SEP_1));
        out_.append(SEP_0);
        out_.append(game_.getNumber());
        return out_.toString();
    }

    public static ResultsBelote importGameBelote(CustList<String> _info) {
        ResultsBelote game_ = new ResultsBelote();
        game_.getRes().setScores(importLongsList(_info.get(0),SEP_1,SEP_2));
        game_.getGame().setType(EnumCardsRetrieverUtil.toGameType(_info.get(1)));
        DealBelote deal_ = importDealBelote(_info.get(4),SEP_1,SEP_2);
        deal_.setNbDeals(NumberUtil.parseLongZero(_info.get(2)));
        deal_.setDealer((byte) NumberUtil.parseInt(_info.get(3)));
        game_.getGame().setDeal(deal_);
        game_.getGame().setDeclares(importDeclareHandBeloteList(_info.get(5), SEP_1,SEP_2,SEP_3));
        game_.getGame().setDeclaresBeloteRebelote(importHandBeloteList(_info.get(6), SEP_1,SEP_2));
        game_.getGame().setWonLastTrick(importBoolList(_info.get(7)));
        game_.getGame().setTricks(importTrickBeloteList(_info.get(8), SEP_1, SEP_2));
        game_.getGame().setProgressingTrick(importTrickBelote(_info.get(9), SEP_1));
        game_.getGame().setBids(importBidBeloteSuitList(_info.get(10),SEP_1,SEP_2));
        game_.getGame().setScores(importShortList(_info.get(11),SEP_1));
        game_.getGame().setNumber(NumberUtil.parseLongZero(_info.get(12)));
        return game_;
    }

    public static String exportTricksHandsBelote(TricksHandsBelote _dealt) {
        StringBuilder out_ = new StringBuilder();
        out_.append(CLIENT_TRICKS_HANDS_BELOTE);
        out_.append(SEP_0);
        out_.append(_dealt.getPreneur());
        out_.append(SEP_0);
        out_.append(exportBidBeloteSuit(SEP_1,_dealt.getBid()));
        out_.append(SEP_0);
        out_.append(_dealt.getDistribution().getNbDeals());
        out_.append(SEP_0);
        out_.append(_dealt.getDistribution().getDealer());
        out_.append(SEP_0);
        out_.append(exportDealBelote(_dealt.getDistribution(),SEP_1,SEP_2));
        out_.append(SEP_0);
        out_.append(exportTrickBeloteList(_dealt.getTricks(),SEP_1,SEP_2));
        out_.append(SEP_0);
        out_.append(exportHandBeloteList(_dealt.getCardsHandsAtInitialState(),SEP_1,SEP_2));
        return out_.toString();
    }

    public static TricksHandsBelote importTricksHandsBelote(CustList<String> _info) {
        TricksHandsBelote game_ = new TricksHandsBelote();
        game_.setPreneur((byte)NumberUtil.parseInt(_info.get(0)));
        game_.setBid(importBidBeloteSuit(SEP_1, _info.get(1)));
        DealBelote deal_ = importDealBelote(_info.get(4),SEP_1,SEP_2);
        deal_.setNbDeals(NumberUtil.parseLongZero(_info.get(2)));
        deal_.setDealer((byte) NumberUtil.parseInt(_info.get(3)));
        game_.setDistribution(deal_);
        game_.setTricks(importTrickBeloteList(_info.get(5), SEP_1, SEP_2));
        game_.setCardsHandsAtInitialState(importHandBeloteList(_info.get(6), SEP_1, SEP_2));
        return game_;
    }
    public static String exportDeclareHandBeloteList(CustList<DeclareHandBelote> _dealt, char _sep, char _sec, char _th) {
        CustList<String> ls_ = new CustList<String>();
        for (DeclareHandBelote b: _dealt) {
            ls_.add(exportDeclareHandBelote(b,_sec,_th).toString());
        }
        return StringUtil.join(ls_, _sep);
    }

    public static CustList<DeclareHandBelote> importDeclareHandBeloteList(String _info, char _sep, char _sec, char _th) {
        CustList<DeclareHandBelote> h_ = new CustList<DeclareHandBelote>();
        if (_info.isEmpty()) {
            return h_;
        }
        for (String s: StringUtil.splitChar(_info,_sep)) {
            h_.add(importDeclareHandBelote(StringUtil.splitChar(s,_sec),0,_th));
        }
        return h_;
    }
    public static StringBuilder exportDeclareHandBelote(DeclareHandBelote _dealt, char _sep, char _sec) {
        StringBuilder out_ = new StringBuilder();
        out_.append(_dealt.getPlayer());
        out_.append(_sep);
        out_.append(_dealt.getDeclare().getSt());
        out_.append(_sep);
        out_.append(exportHandBelote(_dealt.getHand(),_sec));
        return out_;
    }

    public static DeclareHandBelote importDeclareHandBelote(CustList<String> _info, int _off, char _sep) {
        DeclareHandBelote h_ = new DeclareHandBelote();
        h_.setPlayer((byte) NumberUtil.parseInt(_info.get(_off)));
        h_.setDeclare(BeloteCardsRetrieverUtil.toDeclaresBelote(_info.get(_off+1)));
        h_.setHand(importHandBelote(_info.get(_off+2), _sep));
        return h_;
    }
    public static String exportDealBelote(DealBelote _dealt, char _sep, char _sec) {
        return exportHandBeloteList(_dealt.getDeal(), _sep, _sec);
    }
    public static String exportHandBeloteList(CustList<HandBelote> _dealt, char _sep, char _sec) {
        CustList<String> ls_ = new CustList<String>();
        for (HandBelote b: _dealt) {
            ls_.add(exportHandBelote(b,_sec));
        }
        return StringUtil.join(ls_, _sep);
    }
    public static DealBelote importDealBelote(String _info, char _sep, char _sec) {
        DealBelote db_ = new DealBelote();
        db_.setDeal(importHandBeloteList(_info, _sep, _sec));
        return db_;
    }
    public static CustList<HandBelote> importHandBeloteList(String _info, char _sep, char _sec) {
        CustList<HandBelote> h_ = new CustList<HandBelote>();
        if (_info.isEmpty()) {
            return h_;
        }
        for (String s: StringUtil.splitChar(_info,_sep)) {
            h_.add(importHandBelote(s,_sec));
        }
        return h_;
    }

    public static String exportTrickBeloteList(CustList<TrickBelote> _dealt, char _sep, char _sec) {
        CustList<String> ls_ = new CustList<String>();
        for (TrickBelote b: _dealt) {
            ls_.add(exportTrickBelote(b,_sec));
        }
        return StringUtil.join(ls_, _sep);
    }
    public static CustList<TrickBelote> importTrickBeloteList(String _info, char _sep, char _sec) {
        CustList<TrickBelote> h_ = new CustList<TrickBelote>();
        if (_info.isEmpty()) {
            return h_;
        }
        for (String s: StringUtil.splitChar(_info,_sep)) {
            h_.add(importTrickBelote(s,_sec));
        }
        return h_;
    }

    public static String exportTrickBelote(TrickBelote _dealt, char _sep) {
        CustList<String> ls_ = new CustList<String>();
        for (CardBelote b: _dealt) {
            ls_.add(BeloteCardsExporterUtil.fromCardBelote(b));
        }
        return StringUtil.join(ls_, _sep);
    }
    public static TrickBelote importTrickBelote(String _info, char _sep) {
        TrickBelote h_ = new TrickBelote();
        if (_info.isEmpty()) {
            return h_;
        }
        for (String s: StringUtil.splitChar(_info,_sep)) {
            h_.getCards().ajouter(BeloteCardsRetrieverUtil.toCardBelote(s));
        }
        return h_;
    }

    public static String exportHandBelote(HandBelote _dealt, char _sep) {
        CustList<String> ls_ = new CustList<String>();
        for (CardBelote b: _dealt) {
            ls_.add(BeloteCardsExporterUtil.fromCardBelote(b));
        }
        return StringUtil.join(ls_, _sep);
    }
    public static HandBelote importHandBelote(String _info, char _sep) {
        HandBelote h_ = new HandBelote();
        if (_info.isEmpty()) {
            return h_;
        }
        for (String s: StringUtil.splitChar(_info,_sep)) {
            h_.ajouter(BeloteCardsRetrieverUtil.toCardBelote(s));
        }
        return h_;
    }

    public static String exportBidBeloteSuitList(CustList<BidBeloteSuit> _dealt, char _sep, char _sec) {
        CustList<String> ls_ = new CustList<String>();
        for (BidBeloteSuit b: _dealt) {
            ls_.add(exportBidBeloteSuit(_sec, b));
        }
        return StringUtil.join(ls_, _sep);
    }

    private static String exportBidBeloteSuit(char _sec, BidBeloteSuit _b) {
        return _b.getBid().getSt() + _sec + _b.getSuit().getSuitSt() + _sec + _b.getPoints();
    }

    public static CustList<BidBeloteSuit> importBidBeloteSuitList(String _info, char _sep, char _sec) {
        CustList<BidBeloteSuit> h_ = new CustList<BidBeloteSuit>();
        if (_info.isEmpty()) {
            return h_;
        }
        for (String s: StringUtil.splitChar(_info,_sep)) {
            h_.add(importBidBeloteSuit(_sec, s));
        }
        return h_;
    }

    private static BidBeloteSuit importBidBeloteSuit(char _sec, String _info) {
        StringList bid_ = StringUtil.splitChar(_info, _sec);
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setBid(BeloteCardsRetrieverUtil.toBidBelote(bid_.get(0)));
        b_.setSuit(EnumCardsRetrieverUtil.toSuit(bid_.get(1)));
        b_.setPoints(NumberUtil.parseInt(bid_.get(2)));
        return b_;
    }

    public static String exportGamePresident(ResultsPresident _dealt) {
        CustList<Longs> scores_ = _dealt.getRes().getScores();
        GamePresident game_ = _dealt.getGame();
        StringBuilder out_ = new StringBuilder();
        out_.append(CLIENT_RES_PRESIDENT);
        out_.append(SEP_0);
        out_.append(exportLongsList(scores_,SEP_1,SEP_2));
        out_.append(SEP_0);
        out_.append(game_.getType().getGameTypeSt());
        out_.append(SEP_0);
        out_.append(game_.getDeal().getNbDeals());
        out_.append(SEP_0);
        out_.append(game_.getDeal().getDealer());
        out_.append(SEP_0);
        out_.append(exportDealPresident(game_.getDeal(),SEP_1,SEP_2));
        out_.append(SEP_0);
        out_.append(exportByteList(game_.getRanks(),SEP_1));
        out_.append(SEP_0);
        out_.append(exportTrickPresidentList(game_.getTricks(),SEP_1,SEP_2,SEP_3));
        out_.append(SEP_0);
        out_.append(exportTrickPresident(game_.getProgressingTrick(),SEP_1,SEP_2));
        out_.append(SEP_0);
        out_.append(exportHandPresidentList(game_.getSwitchedCards(),SEP_1,SEP_2));
        out_.append(SEP_0);
        out_.append(exportShortList(game_.getScores(),SEP_1));
        out_.append(SEP_0);
        out_.append(game_.getNumber());
        return out_.toString();
    }

    public static ResultsPresident importGamePresident(CustList<String> _info) {
        ResultsPresident game_ = new ResultsPresident();
        game_.getRes().setScores(importLongsList(_info.get(0),SEP_1,SEP_2));
        game_.getGame().setType(EnumCardsRetrieverUtil.toGameType(_info.get(1)));
        DealPresident deal_ = importDealPresident(_info.get(4),SEP_1,SEP_2);
        deal_.setNbDeals(NumberUtil.parseLongZero(_info.get(2)));
        deal_.setDealer((byte) NumberUtil.parseInt(_info.get(3)));
        game_.getGame().setDeal(deal_);
        game_.getGame().setRanks(importByteList(_info.get(5), SEP_1));
        game_.getGame().setTricks(importTrickPresidentList(_info.get(6), SEP_1, SEP_2,SEP_3));
        game_.getGame().setProgressingTrick(importTrickPresident(_info.get(7), SEP_1,SEP_2));
        game_.getGame().setSwitchedCards(importHandPresidentList(_info.get(8),SEP_1,SEP_2));
        game_.getGame().setScores(importShortList(_info.get(9),SEP_1));
        game_.getGame().setNumber(NumberUtil.parseLongZero(_info.get(10)));
        return game_;
    }

    public static String exportTricksHandsPresident(TricksHandsPresident _dealt) {
        StringBuilder out_ = new StringBuilder();
        out_.append(CLIENT_TRICKS_HANDS_PRESIDENT);
        out_.append(SEP_0);
        out_.append(_dealt.getNumberMaxSwitchedCards());
        out_.append(SEP_0);
        out_.append(NetCommon.exportBool(_dealt.isReversed()));
        out_.append(SEP_0);
        out_.append(_dealt.getDistribution().getNbDeals());
        out_.append(SEP_0);
        out_.append(_dealt.getDistribution().getDealer());
        out_.append(SEP_0);
        out_.append(exportDealPresident(_dealt.getDistribution(),SEP_1,SEP_2));
        out_.append(SEP_0);
        out_.append(exportTrickPresidentList(_dealt.getTricks(),SEP_1,SEP_2,SEP_3));
        out_.append(SEP_0);
        out_.append(exportTrickPresident(_dealt.getProgressingTrick(),SEP_1,SEP_2));
        out_.append(SEP_0);
        out_.append(exportHandPresidentList(_dealt.getCardsHandsAtInitialState(),SEP_1,SEP_2));
        out_.append(SEP_0);
        out_.append(exportHandPresidentList(_dealt.getSwitchedCards(),SEP_1,SEP_2));
        out_.append(SEP_0);
        out_.append(exportByteList(_dealt.getRanks(),SEP_1));
        return out_.toString();
    }

    public static TricksHandsPresident importTricksHandsPresident(CustList<String> _info) {
        TricksHandsPresident game_ = new TricksHandsPresident();
        game_.setNumberMaxSwitchedCards(NumberUtil.parseInt(_info.get(0)));
        game_.setReversed(NetCommon.toBoolEquals(_info.get(1)));
        DealPresident deal_ = importDealPresident(_info.get(4),SEP_1,SEP_2);
        deal_.setNbDeals(NumberUtil.parseLongZero(_info.get(2)));
        deal_.setDealer((byte) NumberUtil.parseInt(_info.get(3)));
        game_.setDistribution(deal_);
        game_.setTricks(importTrickPresidentList(_info.get(5), SEP_1, SEP_2,SEP_3));
        game_.setProgressingTrick(importTrickPresident(_info.get(6), SEP_1, SEP_2));
        game_.setCardsHandsAtInitialState(importHandPresidentList(_info.get(7), SEP_1, SEP_2));
        game_.setSwitchedCards(importHandPresidentList(_info.get(8), SEP_1, SEP_2));
        game_.setRanks(importByteList(_info.get(9), SEP_1));
        return game_;
    }
    public static String exportDealPresident(DealPresident _dealt, char _sep, char _sec) {
        return exportHandPresidentList(_dealt.getDeal(),_sep,_sec);
    }

    public static String exportHandPresidentList(CustList<HandPresident> _dealt, char _sep, char _sec) {
        CustList<String> ls_ = new CustList<String>();
        for (HandPresident b: _dealt) {
            ls_.add(exportHandPresident(b,_sec));
        }
        return StringUtil.join(ls_, _sep);
    }
    public static DealPresident importDealPresident(String _info, char _sep, char _sec) {
        DealPresident h_ = new DealPresident();
        h_.setDeal(importHandPresidentList(_info, _sep, _sec));
        return h_;
    }
    public static CustList<HandPresident> importHandPresidentList(String _info, char _sep, char _sec) {
        CustList<HandPresident> h_ = new CustList<HandPresident>();
        if (_info.isEmpty()) {
            return h_;
        }
        for (String s: StringUtil.splitChar(_info,_sep)) {
            h_.add(importHandPresident(s,_sec));
        }
        return h_;
    }

    public static String exportTrickPresidentList(CustList<TrickPresident> _dealt, char _sep, char _sec, char _th) {
        CustList<String> ls_ = new CustList<String>();
        for (TrickPresident b: _dealt) {
            ls_.add(exportTrickPresident(b,_sec,_th));
        }
        return StringUtil.join(ls_, _sep);
    }
    public static CustList<TrickPresident> importTrickPresidentList(String _info, char _sep, char _sec, char _th) {
        CustList<TrickPresident> h_ = new CustList<TrickPresident>();
        if (_info.isEmpty()) {
            return h_;
        }
        for (String s: StringUtil.splitChar(_info,_sep)) {
            h_.add(importTrickPresident(s,_sec,_th));
        }
        return h_;
    }

    public static String exportTrickPresident(TrickPresident _dealt, char _sep, char _sec) {
        CustList<String> ls_ = new CustList<String>();
        for (HandPresident b: _dealt) {
            ls_.add(exportHandPresident(b,_sec));
        }
        return StringUtil.join(ls_, _sep);
    }
    public static TrickPresident importTrickPresident(String _info, char _sep, char _sec) {
        TrickPresident h_ = new TrickPresident();
        if (_info.isEmpty()) {
            return h_;
        }
        for (String s: StringUtil.splitChar(_info,_sep)) {
            h_.getCards().add(importHandPresident(s,_sec));
        }
        return h_;
    }

    public static String exportHandPresident(HandPresident _dealt, char _sep) {
        CustList<String> ls_ = new CustList<String>();
        for (CardPresident b: _dealt) {
            ls_.add(PresidentCardsExporterUtil.fromCardPresident(b));
        }
        return StringUtil.join(ls_, _sep);
    }
    public static HandPresident importHandPresident(String _info, char _sep) {
        HandPresident h_ = new HandPresident();
        if (_info.isEmpty()) {
            return h_;
        }
        for (String s: StringUtil.splitChar(_info,_sep)) {
            h_.ajouter(PresidentCardsRetrieverUtil.toCardPresident(s));
        }
        return h_;
    }
    public static String exportPlayingMap(ByteMap<Playing> _dealt, char _sep, char _sec) {
        CustList<String> ls_ = new CustList<String>();
        for (EntryCust<Byte, Playing> b: _dealt.entryList()) {
            ls_.add(b.getKey()+ (_sec +b.getValue().getPlay()));
        }
        return StringUtil.join(ls_, _sep);
    }

    public static ByteMap<Playing> importPlayingMap(String _info, char _sep, char _sec) {
        ByteMap<Playing> h_ = new ByteMap<Playing>();
        if (_info.isEmpty()) {
            return h_;
        }
        for (String s: StringUtil.splitChar(_info,_sep)) {
            StringList playing_ = StringUtil.splitChar(s, _sec);
            h_.addEntry((byte)NumberUtil.parseInt(playing_.get(0)),Playing.retrieve(playing_.get(1)));
        }
        return h_;
    }

    public static String exportGameTarot(ResultsTarot _dealt) {
        CustList<Longs> scores_ = _dealt.getRes().getScores();
        GameTarot game_ = _dealt.getGame();
        StringBuilder out_ = new StringBuilder();
        out_.append(CLIENT_RES_TAROT);
        out_.append(SEP_0);
        out_.append(exportLongsList(scores_,SEP_1,SEP_2));
        out_.append(SEP_0);
        out_.append(game_.getType().getGameTypeSt());
        out_.append(SEP_0);
        out_.append(game_.getDeal().getNbDeals());
        out_.append(SEP_0);
        out_.append(game_.getDeal().getDealer());
        out_.append(SEP_0);
        out_.append(exportDealTarot(game_.getDeal(),SEP_1,SEP_2));
        out_.append(SEP_0);
        out_.append(exportBidTarotList(game_.getBids(),SEP_1));
        out_.append(SEP_0);
        out_.append(exportHandTarot(game_.getCalledCards(),SEP_1));
        out_.append(SEP_0);
        out_.append(exportTrickTarotList(game_.getTricks(),SEP_1,SEP_2));
        out_.append(SEP_0);
        out_.append(exportTrickTarot(game_.getProgressingTrick(),SEP_1));
        out_.append(SEP_0);
        out_.append(exportHandTarotList(game_.getHandfuls(),SEP_1,SEP_2));
        out_.append(SEP_0);
        out_.append(exportHandfulsList(game_.getDeclaresHandfuls(),SEP_1,SEP_2));
        out_.append(SEP_0);
        out_.append(exportMiseresList(game_.getDeclaresMiseres(),SEP_1,SEP_2));
        out_.append(SEP_0);
        out_.append(exportBoolList(game_.getSmallBound()));
        out_.append(SEP_0);
        out_.append(exportShortList(game_.getScores(),SEP_1));
        out_.append(SEP_0);
        out_.append(game_.getNumber());
        return out_.toString();
    }

    public static ResultsTarot importGameTarot(CustList<String> _info) {
        ResultsTarot game_ = new ResultsTarot();
        game_.getRes().setScores(importLongsList(_info.get(0),SEP_1,SEP_2));
        game_.getGame().setType(EnumCardsRetrieverUtil.toGameType(_info.get(1)));
        DealTarot deal_ = importDealTarot(_info.get(4),SEP_1,SEP_2);
        deal_.setNbDeals(NumberUtil.parseLongZero(_info.get(2)));
        deal_.setDealer((byte) NumberUtil.parseInt(_info.get(3)));
        game_.getGame().setDeal(deal_);
        game_.getGame().setBids(importBidTarotList(_info.get(5), SEP_1));
        game_.getGame().setCalledCards(importHandTarot(_info.get(6), SEP_1));
        game_.getGame().setTricks(importTrickTarotList(_info.get(7), SEP_1, SEP_2));
        game_.getGame().setProgressingTrick(importTrickTarot(_info.get(8), SEP_1));
        game_.getGame().setHandfuls(importHandTarotList(_info.get(9), SEP_1,SEP_2));
        game_.getGame().setDeclaresHandfuls(importHandfulsList(_info.get(10), SEP_1,SEP_2));
        game_.getGame().setDeclaresMiseres(importMiseresList(_info.get(11), SEP_1,SEP_2));
        game_.getGame().setSmallBound(importBoolList(_info.get(12)));
        game_.getGame().setScores(importShortList(_info.get(13),SEP_1));
        game_.getGame().setNumber(NumberUtil.parseLongZero(_info.get(14)));
        return game_;
    }

    public static String exportTricksHandsTarot(TricksHandsTarot _dealt) {
        StringBuilder out_ = new StringBuilder();
        out_.append(CLIENT_TRICKS_HANDS_TAROT);
        out_.append(SEP_0);
        out_.append(_dealt.getPreneur());
        out_.append(SEP_0);
        out_.append(_dealt.getDistribution().getNbDeals());
        out_.append(SEP_0);
        out_.append(_dealt.getDistribution().getDealer());
        out_.append(SEP_0);
        out_.append(exportDealTarot(_dealt.getDistribution(),SEP_1,SEP_2));
        out_.append(SEP_0);
        out_.append(exportTrickTarotList(_dealt.getTricks(),SEP_1,SEP_2));
        out_.append(SEP_0);
        out_.append(exportHandTarotList(_dealt.getCardsHandsAtInitialState(),SEP_1,SEP_2));
        return out_.toString();
    }

    public static TricksHandsTarot importTricksHandsTarot(CustList<String> _info) {
        TricksHandsTarot game_ = new TricksHandsTarot();
        game_.setPreneur((byte)NumberUtil.parseInt(_info.get(0)));
        DealTarot deal_ = importDealTarot(_info.get(3),SEP_1,SEP_2);
        deal_.setNbDeals(NumberUtil.parseLongZero(_info.get(1)));
        deal_.setDealer((byte) NumberUtil.parseInt(_info.get(2)));
        game_.setDistribution(deal_);
        game_.setTricks(importTrickTarotList(_info.get(4), SEP_1, SEP_2));
        game_.setCardsHandsAtInitialState(importHandTarotList(_info.get(5), SEP_1, SEP_2));
        return game_;
    }
    public static String exportDealTarot(DealTarot _dealt, char _sep, char _sec) {
        return exportHandTarotList(_dealt.getDeal(),_sep,_sec);
    }

    public static String exportHandTarotList(CustList<HandTarot> _dealt, char _sep, char _sec) {
        CustList<String> ls_ = new CustList<String>();
        for (HandTarot b: _dealt) {
            ls_.add(exportHandTarot(b,_sec));
        }
        return StringUtil.join(ls_, _sep);
    }
    public static DealTarot importDealTarot(String _info, char _sep, char _sec) {
        DealTarot h_ = new DealTarot();
        h_.setDeal(importHandTarotList(_info, _sep, _sec));
        return h_;
    }
    public static CustList<HandTarot> importHandTarotList(String _info, char _sep, char _sec) {
        CustList<HandTarot> h_ = new CustList<HandTarot>();
        if (_info.isEmpty()) {
            return h_;
        }
        for (String s: StringUtil.splitChar(_info,_sep)) {
            h_.add(importHandTarot(s,_sec));
        }
        return h_;
    }
    public static String exportTrickTarotList(CustList<TrickTarot> _dealt, char _sep, char _sec) {
        CustList<String> ls_ = new CustList<String>();
        for (TrickTarot b: _dealt) {
            ls_.add(exportTrickTarot(b,_sec));
        }
        return StringUtil.join(ls_, _sep);
    }
    public static CustList<TrickTarot> importTrickTarotList(String _info, char _sep, char _sec) {
        CustList<TrickTarot> h_ = new CustList<TrickTarot>();
        if (_info.isEmpty()) {
            return h_;
        }
        for (String s: StringUtil.splitChar(_info,_sep)) {
            h_.add(importTrickTarot(s,_sec));
        }
        return h_;
    }

    public static String exportTrickTarot(TrickTarot _dealt, char _sep) {
        CustList<String> ls_ = new CustList<String>();
        for (CardTarot b: _dealt) {
            ls_.add(TarotCardsExporterUtil.fromCardTarot(b));
        }
        return StringUtil.join(ls_, _sep);
    }
    public static TrickTarot importTrickTarot(String _info, char _sep) {
        TrickTarot h_ = new TrickTarot();
        if (_info.isEmpty()) {
            return h_;
        }
        for (String s: StringUtil.splitChar(_info,_sep)) {
            h_.getCards().ajouter(TarotCardsRetrieverUtil.toCardTarot(s));
        }
        return h_;
    }

    public static String exportHandTarot(HandTarot _dealt, char _sep) {
        CustList<String> ls_ = new CustList<String>();
        for (CardTarot b: _dealt) {
            ls_.add(TarotCardsExporterUtil.fromCardTarot(b));
        }
        return StringUtil.join(ls_, _sep);
    }

    public static HandTarot importHandTarot(String _info, char _sep) {
        HandTarot h_ = new HandTarot();
        if (_info.isEmpty()) {
            return h_;
        }
        for (String s: StringUtil.splitChar(_info,_sep)) {
            h_.ajouter(TarotCardsRetrieverUtil.toCardTarot(s));
        }
        return h_;
    }

    public static String exportBidTarotList(CustList<BidTarot> _dealt, char _sep) {
        if (_dealt.isEmpty()) {
            return "-";
        }
        CustList<String> ls_ = new CustList<String>();
        for (BidTarot b: _dealt) {
            ls_.add(b.getSt());
        }
        return StringUtil.join(ls_, _sep);
    }
    public static IdList<BidTarot> importBidTarotList(String _info, char _sep) {
        IdList<BidTarot> h_ = new IdList<BidTarot>();
        if (StringUtil.quickEq(_info,"-")) {
            return h_;
        }
        for (String s: StringUtil.splitChar(_info,_sep)) {
            h_.add(TarotCardsRetrieverUtil.toBidTarot(s));
        }
        return h_;
    }

    public static String exportHandfulsList(CustList<IdList<Handfuls>> _dealt, char _sep, char _sec) {
        CustList<String> ls_ = new CustList<String>();
        for (IdList<Handfuls> b: _dealt) {
            ls_.add(exportHandfuls(b,_sec));
        }
        return StringUtil.join(ls_,_sep);
    }
    public static CustList<IdList<Handfuls>> importHandfulsList(String _info, char _sep, char _sec) {
        CustList<IdList<Handfuls>> h_ = new CustList<IdList<Handfuls>>();
        if (_info.isEmpty()) {
            return h_;
        }
        for (String s: StringUtil.splitChar(_info,_sep)) {
            h_.add(importHandfuls(s,_sec));
        }
        return h_;
    }
    public static String exportMiseresList(CustList<IdList<Miseres>> _dealt, char _sep, char _sec) {
        CustList<String> ls_ = new CustList<String>();
        for (IdList<Miseres> b: _dealt) {
            ls_.add(exportMiseres(b,_sec));
        }
        return StringUtil.join(ls_, _sep);
    }

    public static CustList<IdList<Miseres>> importMiseresList(String _info, char _sep, char _sec) {
        CustList<IdList<Miseres>> h_ = new CustList<IdList<Miseres>>();
        if (_info.isEmpty()) {
            return h_;
        }
        for (String s: StringUtil.splitChar(_info,_sep)) {
            h_.add(importMiseres(s,_sec));
        }
        return h_;
    }
    public static String exportHandfuls(CustList<Handfuls> _dealt, char _sep) {
        if (_dealt.isEmpty()) {
            return "-";
        }
        CustList<String> ls_ = new CustList<String>();
        for (Handfuls b: _dealt) {
            ls_.add(TarotCardsExporterUtil.fromHandfuls(b));
        }
        return StringUtil.join(ls_,_sep);
    }
    public static IdList<Handfuls> importHandfuls(String _info, char _sep) {
        IdList<Handfuls> h_ = new IdList<Handfuls>();
        if (StringUtil.quickEq(_info,"-")) {
            return h_;
        }
        for (String s: StringUtil.splitChar(_info,_sep)) {
            h_.add(TarotCardsRetrieverUtil.toHandfuls(s));
        }
        return h_;
    }
    public static String exportMiseres(CustList<Miseres> _dealt, char _sep) {
        CustList<String> ls_ = new CustList<String>();
        for (Miseres b: _dealt) {
            ls_.add(TarotCardsExporterUtil.fromMiseres(b));
        }
        return StringUtil.join(ls_, _sep);
    }

    public static IdList<Miseres> importMiseres(String _info, char _sep) {
        IdList<Miseres> h_ = new IdList<Miseres>();
        if (_info.isEmpty()) {
            return h_;
        }
        for (String s: StringUtil.splitChar(_info,_sep)) {
            h_.add(TarotCardsRetrieverUtil.toMiseres(s));
        }
        return h_;
    }
    private static String placesPlayers(AbsMap<Integer,Byte> _placesPlayers) {
        CustList<String> places_ = new CustList<String>();
        for (EntryCust<Integer, Byte> e: _placesPlayers.entryList()) {
            places_.add(""+e.getKey()+SEP_2+e.getValue());
        }
        return StringUtil.join(places_, SEP_1);
    }
    private static IntTreeMap<Byte> placePlayers(String _info) {
        IntTreeMap<Byte> placesPlayers_ = new IntTreeMap<Byte>();
        if (_info.isEmpty()) {
            return placesPlayers_;
        }
        for (String p: StringUtil.splitChar(_info,SEP_1)) {
            StringList kv_ = StringUtil.splitChar(p, SEP_2);
            placesPlayers_.addEntry(NumberUtil.parseInt(kv_.first()), (byte) NumberUtil.parseInt(kv_.last()));
        }
        return placesPlayers_;
    }

    public static String exportTeams(CustList<Bytes> _dealt) {
        StringBuilder out_ = new StringBuilder();
        out_.append(CLIENT_TRICKS_TEAMS);
        out_.append(SEP_0);
        out_.append(exportByteLists(_dealt,SEP_1,SEP_2));
        return out_.toString();
    }
    public static CustList<Bytes> importTeams(String _info) {
        return importByteLists(_info,SEP_1,SEP_2);
    }

    public static String exportBoolList(CustList<BoolVal> _dealt) {
        StringBuilder ls_ = new StringBuilder();
        for (BoolVal b: _dealt) {
            ls_.append(NetCommon.exportBool(b));
        }
        return ls_.toString();
    }
    public static CustList<BoolVal> importBoolList(String _info) {
        CustList<BoolVal> h_ = new CustList<BoolVal>();
        for (char s: _info.toCharArray()) {
            h_.add(ComparatorBoolean.of(NetCommon.toBoolEquals(s)));
        }
        return h_;
    }

    public static String exportShortList(Shorts _dealt, char _sep) {
        CustList<String> ls_ = new CustList<String>();
        for (short b: _dealt) {
            ls_.add(Integer.toString(b));
        }
        return StringUtil.join(ls_,_sep);
    }
    public static Shorts importShortList(String _info, char _sep) {
        Shorts h_ = new Shorts();
        if (_info.isEmpty()) {
            return h_;
        }
        for (String s: StringUtil.splitChar(_info,_sep)) {
            h_.add((short)NumberUtil.parseInt(s));
        }
        return h_;
    }

    public static String exportByteLists(CustList<Bytes> _dealt, char _sep, char _sec) {
        CustList<String> ls_ = new CustList<String>();
        for (Bytes b: _dealt) {
            ls_.add(exportByteList(b, _sec));
        }
        return StringUtil.join(ls_,_sep);
    }
    public static CustList<Bytes> importByteLists(String _info, char _sep, char _sec) {
        CustList<Bytes> h_ = new CustList<Bytes>();
        if (_info.isEmpty()) {
            return h_;
        }
        for (String s: StringUtil.splitChar(_info,_sep)) {
            h_.add(importByteList(s,_sec));
        }
        return h_;
    }

    public static String exportByteList(Bytes _dealt, char _sep) {
        CustList<String> ls_ = new CustList<String>();
        for (byte b: _dealt) {
            ls_.add(Integer.toString(b));
        }
        return StringUtil.join(ls_,_sep);
    }
    public static Bytes importByteList(String _info, char _sep) {
        Bytes h_ = new Bytes();
        if (_info.isEmpty()) {
            return h_;
        }
        for (String s: StringUtil.splitChar(_info,_sep)) {
            h_.add((byte)NumberUtil.parseInt(s));
        }
        return h_;
    }

    public static String exportLongsList(CustList<Longs> _dealt, char _sep, char _sec) {
        CustList<String> ls_ = new CustList<String>();
        for (Longs b: _dealt) {
            ls_.add(exportLongList(b,_sec));
        }
        return StringUtil.join(ls_,_sep);
    }
    public static CustList<Longs> importLongsList(String _info, char _sep, char _sec) {
        CustList<Longs> h_ = new CustList<Longs>();
        if (_info.isEmpty()) {
            return h_;
        }
        for (String s: StringUtil.splitChar(_info,_sep)) {
            h_.add(importLongList(s,_sec));
        }
        return h_;
    }

    public static String exportLongList(Longs _dealt, char _sep) {
        CustList<String> ls_ = new CustList<String>();
        for (long b: _dealt) {
            ls_.add(Long.toString(b));
        }
        return StringUtil.join(ls_,_sep);
    }
    public static Longs importLongList(String _info, char _sep) {
        Longs h_ = new Longs();
        if (_info.isEmpty()) {
            return h_;
        }
        for (String s: StringUtil.splitChar(_info,_sep)) {
            h_.add(NumberUtil.parseLongZero(s));
        }
        return h_;
    }
//
    private static BoolVal toBoolValEndsWith(String _l) {
        if (_l.endsWith("1")) {
            return BoolVal.TRUE;
        }
        return BoolVal.FALSE;
    }
    public static String exportEnableQuit() {
        StringBuilder out_ = new StringBuilder();
        out_.append(CLIENT_POSSIBLE_QUIT);
        out_.append(SEP_0);
        return out_.toString();
    }
    public static String exportQuitting(QuitCards _index) {
        StringBuilder out_ = new StringBuilder();
        out_.append(SERVER_QUITTING);
        out_.append(SEP_0);
        out_.append(NetCommon.exportBool(_index.getContent().isClosing()));
        out_.append(NetCommon.exportBool(_index.getContent().isServer()));
        out_.append(_index.getPlace());
        return out_.toString();
    }

    public static QuitCards importQuitting(CustList<String> _info) {
        QuitCards q_ = new QuitCards();
        String i_ = _info.get(0);
        q_.getContent().setClosing(NetCommon.toBoolEquals(i_,0));
        q_.getContent().setServer(NetCommon.toBoolEquals(i_,1));
        q_.setPlace((byte)NumberUtil.parseInt(i_.substring(2)));
        return q_;
    }

    public static int getPort() {
        return PORT;
    }

    public static String getCards() {
        return CARDS;
    }

    /**server
    @return true &hArr; the players are correctly placed around the "table"
     * @param _instance
     * @param _common */
    public static boolean distinctPlaces(Net _instance, NetCommon _common) {
        boolean distinct_ = true;
        Bytes places_ = new Bytes();
        for (byte r: activePlayers(_instance, _common)) {
            if (places_.containsObj(r)) {
                distinct_ = false;
                break;
            }
            places_.add(r);
        }
        return distinct_;
    }

    /**server
     * @param _instance
     * @param _common*/
    static void initAllPresent(Net _instance, NetCommon _common) {
        _instance.activePlayers = new ByteMap<BoolVal>();
        for (byte r: _common.getPlacesPlayers().values()) {
            _instance.activePlayers.put(r, BoolVal.TRUE);
        }
    }
    static void initAllReady(NetCommon _common) {
        for (EntryCust<Integer,BoolVal> e: _common.getReadyPlayers().entryList()) {
            e.setValue(BoolVal.FALSE);
        }
    }
    /**server
     * @param _instance
     * @param _common*/
    static void initAllReceived(Net _instance, NetCommon _common) {
        if (_instance.received == null) {
            _instance.received = new ByteMap<BoolVal>();
        }
        for (byte r: _common.getPlacesPlayers().values()) {
            if (_instance.activePlayers.getVal(r) == BoolVal.TRUE) {
                _instance.received.put(r, BoolVal.FALSE);
            } else {
                setReceivedForPlayer(r, _instance);
            }
        }
    }

    //bk: synchronized
    /**server*/
    static void quit(byte _p, Net _instance) {
        if (_instance.activePlayers == null) {
            return;
        }
        _instance.activePlayers.put(_p, BoolVal.FALSE);
        setReceivedForPlayer(_p, _instance);
    }
    //bk: synchronized
    /**server*/
    static void setReceivedForPlayer(byte _p, Net _instance) {
        _instance.received.put(_p,BoolVal.TRUE);
    }

    //bk: synchronized
    /**server*/
    static boolean allReceivedAmong(Bytes _players, Net _instance) {
        boolean allReceived_ = true;
        for (byte p: _instance.received.getKeys()) {
            if (_players.containsObj(p) && _instance.received.getVal(p) != BoolVal.TRUE) {
                allReceived_ = false;
                break;
            }
        }
        return allReceived_;
    }

    static boolean allReceived(Net _instance) {
        boolean allReceived_ = true;
        for (BoolVal r: _instance.received.values()) {
            if (r != BoolVal.TRUE) {
                allReceived_ = false;
                break;
            }
        }
        return allReceived_;
    }
    /**server
    @return true &hArr; the connected players are belonging to a seam team
     * @param _instance
     * @param _common */
    static boolean isSameTeam(Net _instance, NetCommon _common) {
        Bytes players_ = new Bytes(activePlayers(_instance, _common));
        return Net.getGames(_instance).isSameTeam(players_);
    }
    /**server
    bk: synchronized, called from mouse events or server loop
    @return the connected players
     * @param _instance
     * @param _common */
    static Bytes activePlayers(Net _instance, NetCommon _common) {
        if (_instance.activePlayers == null) {
            Bytes activePlayers_ = new Bytes();
            for (byte i: _common.getPlacesPlayers().values()) {
                activePlayers_.add(i);
            }
            return activePlayers_;
        }
        Bytes activePlayers_ = new Bytes();
        for (byte i: _common.getPlacesPlayers().values()) {
            if (_instance.activePlayers.getVal(i) != BoolVal.TRUE) {
                continue;
            }
            activePlayers_.add(i);
        }
        return activePlayers_;
    }

    /**server
     @return true &hArr; if the <i>_place</i> match with a currently connected player
      * @param _place the place of a bot or a player
     * @param _instance
     * @param _common */
    static boolean isHumanPlayer(byte _place, Net _instance, NetCommon _common) {
        return !getPlacesPlayersByValue(_place, _common).isEmpty() && _instance.activePlayers.getVal(_place) == BoolVal.TRUE;
    }
    /**server
     @return the associated socket of the place or null if it is an invalid place for a player
      * @param _place the place of a player around the table
     * @param _common */
    static AbstractSocket getSocketByPlace(byte _place, NetCommon _common) {
        for (int i: _common.getPlacesPlayers().getKeys()) {
            if (_common.getPlacesPlayers().getVal(i) == _place) {
                return _common.getSockets().getVal(i);
            }
        }
        return null;
    }
//    /**server*/
//    static String getLanguageByPlace(byte _place, Net _instance, NetCommon _common) {
//        for (int i: _common.getPlacesPlayers().getKeys()) {
//            if (_common.getPlacesPlayers().getVal(i) == _place) {
//                return getPlayersLocales(_instance).getVal(i);
//            }
//        }
//        return null;
//    }
//
//    /**server*/
//    static boolean delegateServer(Quit _bye, Net _instance) {
//        for (byte p: Net.activePlayers(_instance)) {
//            if (p == _bye.getPlace()) {
//                continue;
//            }
//            DelegateServer d_ = new DelegateServer();
//            d_.setGames(Net.getGames(_instance));
//            d_.setNicknames(new IntMap<String>());
//            Net.sendObject(Net.getSocketByPlace(p, _instance),d_);
//            return true;
//        }
//        ByeCards forcedBye_ = new ByeCards();
//        forcedBye_.setForced(false);
//        forcedBye_.setServer(true);
//        forcedBye_.setClosing(_bye.isClosing());
//        Ints players_ = Net.getPlacesPlayersByValue(_bye.getPlace(), _instance);
//        if (!players_.isEmpty()) {
//            removePlayer(players_.first(), forcedBye_, _instance);
//        }
//        Net.getNicknames(_instance).clear();
//        Net.getGames(_instance).finirParties();
//        Net.getPlacesPlayers(_instance).clear();
//        return false;
//    }

    /**server
     * @param _instance*/
    public static Games getGames(Net _instance) {
        return _instance.games;
    }

    /**server (delegating)*/
    public static void setGames(Games _games, Net _instance) {
        _instance.games = _games;
    }

    /**server
     * @param _instance*/
    public static int getNbPlayers(Net _instance) {
        return _instance.nbPlayers;
    }

    /**server*/
    public static void setNbPlayers(int _nbPlayers, Net _instance) {
        _instance.nbPlayers = _nbPlayers;
    }

    /**server*/
    public static Ints getPlacesPlayersByValue(byte _value, NetCommon _common) {
        Ints l_;
        l_ = new Ints();
        for (EntryCust<Integer, Byte> e: _common.getPlacesPlayers().entryList()) {
            if (e.getValue() != _value) {
                continue;
            }
            l_.add(e.getKey());
        }
        return l_;
    }

    /**server and client (delegating)
     * @param _instance*/
    public static boolean isProgressingGame(Net _instance) {
        return _instance.progressingGame;
    }

    /**server*/
    public static void setProgressingGame(boolean _progressingGame, Net _instance) {
        _instance.progressingGame = _progressingGame;
    }

    /**server
     * @param _instance*/
    public static CustList<Longs> getScores(Net _instance) {
        return _instance.scores;
    }

//    /**server
//     * @param _instance*/
//    public static IntMap<String> getPlayersLocales(Net _instance) {
//        return _instance.playersLocales;
//    }

}
