package cards.tarot;

import cards.consts.GameType;
import cards.consts.Hypothesis;
import cards.consts.Suit;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CallingCard;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.Miseres;
import code.util.*;
import org.junit.Test;

import static org.junit.Assert.fail;

public final class GameTarotTrickHypothesisTest {
    @Test
    public void hypothesesRepartitionsJoueurs1Test() {
        HandTarot last_ = new HandTarot();
        last_.ajouter(CardTarot.HEART_KING);
        last_.ajouter(CardTarot.HEART_8);
        last_.ajouter(CardTarot.DIAMOND_KNIGHT);
        RulesTarot r_ = new RulesTarot();
        CustList<TrickTarot> trs_ = new CustList<TrickTarot>();
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.TAKE);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        TrickTarot t_ = new TrickTarot((byte) getTaker(r_,2,bids_),false);
        t_.ajouter(CardTarot.HEART_QUEEN);
        t_.ajouter(CardTarot.HEART_KNIGHT);
        t_.ajouter(CardTarot.HEART_10);
        trs_.add(t_);
        t_ = new TrickTarot(r_.getDealing().getNextPlayer(2),true);
        t_.ajouter(CardTarot.TRUMP_14);
        t_.ajouter(CardTarot.TRUMP_1);
        t_.ajouter(CardTarot.TRUMP_21);
        t_.ajouter(CardTarot.TRUMP_3);
        t_.ajouter(CardTarot.TRUMP_4);
        trs_.add(t_);
        t_ = new TrickTarot(t_.getRamasseur(),true);
        t_.ajouter(CardTarot.TRUMP_20);
        t_.ajouter(CardTarot.TRUMP_5);
        t_.ajouter(CardTarot.TRUMP_6);
        t_.ajouter(CardTarot.TRUMP_7);
        t_.ajouter(CardTarot.TRUMP_8);
        trs_.add(t_);
        TrickTarot pr_ = new TrickTarot(t_.getRamasseur(),true);
        HandTarot calledCards_ = new HandTarot();
        calledCards_.ajouter(CardTarot.CLUB_KING);
        GameTarot g_ = newGameTarotWithourDecl(r_, trs_, pr_, 2, bids_, calledCards_, 4, last_);
        GameTarotTrickInfo info_ = newGameTarotTrickInfo(g_);
        HandTarot curHand_ = new HandTarot();
        curHand_.ajouter(CardTarot.HEART_8);
        curHand_.ajouter(CardTarot.TRUMP_18);
        curHand_.ajouter(CardTarot.TRUMP_16);
        curHand_.ajouter(CardTarot.TRUMP_10);
        curHand_.ajouter(CardTarot.TRUMP_2);
        curHand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        curHand_.ajouter(CardTarot.DIAMOND_10);
        curHand_.ajouter(CardTarot.DIAMOND_JACK);
        curHand_.ajouter(CardTarot.DIAMOND_9);
        curHand_.ajouter(CardTarot.CLUB_KNIGHT);
        curHand_.ajouter(CardTarot.CLUB_10);
        curHand_.ajouter(CardTarot.HEART_7);
        curHand_.ajouter(CardTarot.HEART_KING);
        GameTarotTeamsRelation team_ = g_.getTeamsRelation();
        EnumMap<Suit, EqList<HandTarot>> poss_ = info_.cartesPossibles(team_, curHand_, (byte) 0, last_);
        EnumMap<Hypothesis, EnumMap<Suit, EqList<HandTarot>>> hypos_ = info_.cartesCertaines(team_, poss_);
        poss_ = hypos_.getVal(Hypothesis.POSSIBLE);
        EnumMap<Suit, EqList<HandTarot>> sure_ = hypos_.getVal(Hypothesis.SURE);
        CustList<TrickTarot> tricks_ = g_.getTricks();
        tricks_.add(pr_);
        GameTarotTrickHypothesis.hypothesesRepartitionsJoueurs(team_,g_.getCalledCards(),g_.getContrat(), tricks_, (byte) 0,poss_,sure_);
    }
    @Test
    public void hypothesesRepartitionsJoueurs2Test() {
        HandTarot last_ = new HandTarot();
        last_.ajouter(CardTarot.HEART_KING);
        last_.ajouter(CardTarot.HEART_8);
        last_.ajouter(CardTarot.DIAMOND_KNIGHT);
        RulesTarot r_ = new RulesTarot();
        CustList<TrickTarot> trs_ = new CustList<TrickTarot>();
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.TAKE);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        TrickTarot t_ = new TrickTarot((byte) getTaker(r_,2,bids_),false);
        t_.ajouter(CardTarot.HEART_QUEEN);
        t_.ajouter(CardTarot.HEART_KNIGHT);
        t_.ajouter(CardTarot.HEART_10);
        trs_.add(t_);
        t_ = new TrickTarot(r_.getDealing().getNextPlayer(2),true);
        t_.ajouter(CardTarot.TRUMP_14);
        t_.ajouter(CardTarot.TRUMP_3);
        t_.ajouter(CardTarot.TRUMP_21);
        t_.ajouter(CardTarot.TRUMP_1);
        t_.ajouter(CardTarot.TRUMP_4);
        trs_.add(t_);
        t_ = new TrickTarot(t_.getRamasseur(),true);
        t_.ajouter(CardTarot.TRUMP_20);
        t_.ajouter(CardTarot.TRUMP_5);
        t_.ajouter(CardTarot.TRUMP_6);
        t_.ajouter(CardTarot.TRUMP_7);
        t_.ajouter(CardTarot.TRUMP_8);
        trs_.add(t_);
        TrickTarot pr_ = new TrickTarot(t_.getRamasseur(),true);
        HandTarot calledCards_ = new HandTarot();
        calledCards_.ajouter(CardTarot.CLUB_KING);
        GameTarot g_ = newGameTarotWithourDecl(r_, trs_, pr_, 2, bids_, calledCards_, 1, last_);
        GameTarotTrickInfo info_ = newGameTarotTrickInfo(g_);
        HandTarot curHand_ = new HandTarot();
        curHand_.ajouter(CardTarot.HEART_8);
        curHand_.ajouter(CardTarot.TRUMP_18);
        curHand_.ajouter(CardTarot.TRUMP_16);
        curHand_.ajouter(CardTarot.TRUMP_10);
        curHand_.ajouter(CardTarot.TRUMP_2);
        curHand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        curHand_.ajouter(CardTarot.DIAMOND_10);
        curHand_.ajouter(CardTarot.DIAMOND_JACK);
        curHand_.ajouter(CardTarot.DIAMOND_9);
        curHand_.ajouter(CardTarot.CLUB_KNIGHT);
        curHand_.ajouter(CardTarot.CLUB_10);
        curHand_.ajouter(CardTarot.HEART_7);
        curHand_.ajouter(CardTarot.HEART_KING);
        GameTarotTeamsRelation team_ = g_.getTeamsRelation();
        EnumMap<Suit, EqList<HandTarot>> poss_ = info_.cartesPossibles(team_, curHand_, (byte) 0, last_);
        EnumMap<Hypothesis, EnumMap<Suit, EqList<HandTarot>>> hypos_ = info_.cartesCertaines(team_, poss_);
        poss_ = hypos_.getVal(Hypothesis.POSSIBLE);
        EnumMap<Suit, EqList<HandTarot>> sure_ = hypos_.getVal(Hypothesis.SURE);
        CustList<TrickTarot> tricks_ = g_.getTricks();
        tricks_.add(pr_);
        GameTarotTrickHypothesis.hypothesesRepartitionsJoueurs(team_,g_.getCalledCards(),g_.getContrat(),tricks_, (byte) 0,poss_,sure_);
    }
    @Test
    public void hypothesesRepartitionsJoueurs3Test() {
        HandTarot last_ = new HandTarot();
        last_.ajouter(CardTarot.HEART_KING);
        last_.ajouter(CardTarot.HEART_8);
        last_.ajouter(CardTarot.DIAMOND_KNIGHT);
        RulesTarot r_ = new RulesTarot();
        CustList<TrickTarot> trs_ = new CustList<TrickTarot>();
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.TAKE);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        TrickTarot t_ = new TrickTarot((byte) getTaker(r_,2,bids_),false);
        t_.ajouter(CardTarot.HEART_QUEEN);
        t_.ajouter(CardTarot.HEART_KNIGHT);
        t_.ajouter(CardTarot.HEART_10);
        trs_.add(t_);
        t_ = new TrickTarot(r_.getDealing().getNextPlayer(2),true);
        t_.ajouter(CardTarot.TRUMP_1);
        t_.ajouter(CardTarot.TRUMP_3);
        t_.ajouter(CardTarot.TRUMP_21);
        t_.ajouter(CardTarot.TRUMP_14);
        t_.ajouter(CardTarot.TRUMP_4);
        trs_.add(t_);
        TrickTarot pr_ = new TrickTarot(t_.getRamasseur(),true);
        HandTarot calledCards_ = new HandTarot();
        calledCards_.ajouter(CardTarot.CLUB_KING);
        GameTarot g_ = newGameTarotWithourDecl(r_, trs_, pr_, 2, bids_, calledCards_, 3, last_);
        GameTarotTrickInfo info_ = newGameTarotTrickInfo(g_);
        HandTarot curHand_ = new HandTarot();
        curHand_.ajouter(CardTarot.HEART_8);
        curHand_.ajouter(CardTarot.TRUMP_20);
        curHand_.ajouter(CardTarot.TRUMP_18);
        curHand_.ajouter(CardTarot.TRUMP_16);
        curHand_.ajouter(CardTarot.TRUMP_10);
        curHand_.ajouter(CardTarot.TRUMP_2);
        curHand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        curHand_.ajouter(CardTarot.DIAMOND_10);
        curHand_.ajouter(CardTarot.DIAMOND_JACK);
        curHand_.ajouter(CardTarot.DIAMOND_9);
        curHand_.ajouter(CardTarot.CLUB_KNIGHT);
        curHand_.ajouter(CardTarot.CLUB_10);
        curHand_.ajouter(CardTarot.HEART_7);
        curHand_.ajouter(CardTarot.HEART_KING);
        GameTarotTeamsRelation team_ = g_.getTeamsRelation();
        EnumMap<Suit, EqList<HandTarot>> poss_ = info_.cartesPossibles(team_, curHand_, (byte) 0, last_);
        EnumMap<Hypothesis, EnumMap<Suit, EqList<HandTarot>>> hypos_ = info_.cartesCertaines(team_, poss_);
        poss_ = hypos_.getVal(Hypothesis.POSSIBLE);
        EnumMap<Suit, EqList<HandTarot>> sure_ = hypos_.getVal(Hypothesis.SURE);
        CustList<TrickTarot> tricks_ = g_.getTricks();
        tricks_.add(pr_);
        GameTarotTrickHypothesis.hypothesesRepartitionsJoueurs(team_,g_.getCalledCards(),g_.getContrat(),tricks_, (byte) 0,poss_,sure_);
    }
    @Test
    public void hypothesesRepartitionsJoueurs4Test() {
        HandTarot last_ = new HandTarot();
        last_.ajouter(CardTarot.HEART_KING);
        last_.ajouter(CardTarot.HEART_8);
        last_.ajouter(CardTarot.DIAMOND_KNIGHT);
        RulesTarot r_ = new RulesTarot();
        CustList<TrickTarot> trs_ = new CustList<TrickTarot>();
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.TAKE);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        TrickTarot t_ = new TrickTarot((byte) getTaker(r_,2,bids_),false);
        t_.ajouter(CardTarot.HEART_QUEEN);
        t_.ajouter(CardTarot.HEART_KNIGHT);
        t_.ajouter(CardTarot.HEART_10);
        trs_.add(t_);
        t_ = new TrickTarot(r_.getDealing().getNextPlayer(2),true);
        t_.ajouter(CardTarot.TRUMP_14);
        t_.ajouter(CardTarot.TRUMP_3);
        t_.ajouter(CardTarot.TRUMP_21);
        t_.ajouter(CardTarot.TRUMP_1);
        t_.ajouter(CardTarot.TRUMP_4);
        trs_.add(t_);
        TrickTarot pr_ = new TrickTarot(t_.getRamasseur(),true);
        HandTarot calledCards_ = new HandTarot();
        calledCards_.ajouter(CardTarot.CLUB_KING);
        GameTarot g_ = newGameTarotWithourDecl(r_, trs_, pr_, 2, bids_, calledCards_, 1, last_);
        GameTarotTrickInfo info_ = newGameTarotTrickInfo(g_);
        HandTarot curHand_ = new HandTarot();
        curHand_.ajouter(CardTarot.HEART_8);
        curHand_.ajouter(CardTarot.TRUMP_18);
        curHand_.ajouter(CardTarot.TRUMP_16);
        curHand_.ajouter(CardTarot.TRUMP_10);
        curHand_.ajouter(CardTarot.TRUMP_20);
        curHand_.ajouter(CardTarot.TRUMP_2);
        curHand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        curHand_.ajouter(CardTarot.DIAMOND_10);
        curHand_.ajouter(CardTarot.DIAMOND_JACK);
        curHand_.ajouter(CardTarot.DIAMOND_9);
        curHand_.ajouter(CardTarot.CLUB_KNIGHT);
        curHand_.ajouter(CardTarot.CLUB_10);
        curHand_.ajouter(CardTarot.HEART_7);
        curHand_.ajouter(CardTarot.HEART_KING);
        GameTarotTeamsRelation team_ = g_.getTeamsRelation();
        EnumMap<Suit, EqList<HandTarot>> poss_ = info_.cartesPossibles(team_, curHand_, (byte) 0, last_);
        EnumMap<Hypothesis, EnumMap<Suit, EqList<HandTarot>>> hypos_ = info_.cartesCertaines(team_, poss_);
        poss_ = hypos_.getVal(Hypothesis.POSSIBLE);
        EnumMap<Suit, EqList<HandTarot>> sure_ = hypos_.getVal(Hypothesis.SURE);
        CustList<TrickTarot> tricks_ = g_.getTricks();
        tricks_.add(pr_);
        GameTarotTrickHypothesis.hypothesesRepartitionsJoueurs(team_,g_.getCalledCards(),g_.getContrat(),tricks_, (byte) 0,poss_,sure_);
    }
    @Test
    public void hypothesesRepartitionsJoueurs5Test() {
        HandTarot last_ = new HandTarot();
        last_.ajouter(CardTarot.HEART_KING);
        last_.ajouter(CardTarot.HEART_8);
        last_.ajouter(CardTarot.DIAMOND_KNIGHT);
        RulesTarot r_ = new RulesTarot();
        CustList<TrickTarot> trs_ = new CustList<TrickTarot>();
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.TAKE);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        TrickTarot t_ = new TrickTarot((byte) getTaker(r_,2,bids_),false);
        t_.ajouter(CardTarot.HEART_QUEEN);
        t_.ajouter(CardTarot.HEART_KNIGHT);
        t_.ajouter(CardTarot.HEART_10);
        trs_.add(t_);
        t_ = new TrickTarot(r_.getDealing().getNextPlayer(2),true);
        t_.ajouter(CardTarot.TRUMP_14);
        t_.ajouter(CardTarot.TRUMP_1);
        t_.ajouter(CardTarot.TRUMP_21);
        t_.ajouter(CardTarot.TRUMP_3);
        t_.ajouter(CardTarot.TRUMP_4);
        trs_.add(t_);
        TrickTarot pr_ = new TrickTarot(t_.getRamasseur(),true);
        HandTarot calledCards_ = new HandTarot();
        calledCards_.ajouter(CardTarot.CLUB_KING);
        GameTarot g_ = newGameTarotWithourDecl(r_, trs_, pr_, 2, bids_, calledCards_, 4, last_);
        GameTarotTrickInfo info_ = newGameTarotTrickInfo(g_);
        HandTarot curHand_ = new HandTarot();
        curHand_.ajouter(CardTarot.HEART_8);
        curHand_.ajouter(CardTarot.TRUMP_18);
        curHand_.ajouter(CardTarot.TRUMP_16);
        curHand_.ajouter(CardTarot.TRUMP_10);
        curHand_.ajouter(CardTarot.TRUMP_20);
        curHand_.ajouter(CardTarot.TRUMP_2);
        curHand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        curHand_.ajouter(CardTarot.DIAMOND_10);
        curHand_.ajouter(CardTarot.DIAMOND_JACK);
        curHand_.ajouter(CardTarot.DIAMOND_9);
        curHand_.ajouter(CardTarot.CLUB_KNIGHT);
        curHand_.ajouter(CardTarot.CLUB_10);
        curHand_.ajouter(CardTarot.HEART_7);
        curHand_.ajouter(CardTarot.HEART_KING);
        GameTarotTeamsRelation team_ = g_.getTeamsRelation();
        EnumMap<Suit, EqList<HandTarot>> poss_ = info_.cartesPossibles(team_, curHand_, (byte) 0, last_);
        EnumMap<Hypothesis, EnumMap<Suit, EqList<HandTarot>>> hypos_ = info_.cartesCertaines(team_, poss_);
        poss_ = hypos_.getVal(Hypothesis.POSSIBLE);
        EnumMap<Suit, EqList<HandTarot>> sure_ = hypos_.getVal(Hypothesis.SURE);
        CustList<TrickTarot> tricks_ = g_.getTricks();
        tricks_.add(pr_);
        GameTarotTrickHypothesis.hypothesesRepartitionsJoueurs(team_,g_.getCalledCards(),g_.getContrat(),tricks_, (byte) 0,poss_,sure_);
    }
    @Test
    public void hypothesesRepartitionsJoueurs6Test() {
        HandTarot last_ = new HandTarot();
        last_.ajouter(CardTarot.HEART_KING);
        last_.ajouter(CardTarot.HEART_8);
        last_.ajouter(CardTarot.DIAMOND_KNIGHT);
        RulesTarot r_ = new RulesTarot();
        CustList<TrickTarot> trs_ = new CustList<TrickTarot>();
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.TAKE);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        TrickTarot t_ = new TrickTarot((byte) getTaker(r_,2,bids_),false);
        t_.ajouter(CardTarot.HEART_QUEEN);
        t_.ajouter(CardTarot.HEART_KNIGHT);
        t_.ajouter(CardTarot.HEART_10);
        trs_.add(t_);
        t_ = new TrickTarot(r_.getDealing().getNextPlayer(2),true);
        t_.ajouter(CardTarot.TRUMP_14);
        t_.ajouter(CardTarot.TRUMP_3);
        t_.ajouter(CardTarot.TRUMP_21);
        t_.ajouter(CardTarot.TRUMP_11);
        t_.ajouter(CardTarot.TRUMP_4);
        trs_.add(t_);
        t_ = new TrickTarot(t_.getRamasseur(),true);
        t_.ajouter(CardTarot.TRUMP_20);
        t_.ajouter(CardTarot.TRUMP_1);
        t_.ajouter(CardTarot.TRUMP_6);
        t_.ajouter(CardTarot.TRUMP_7);
        t_.ajouter(CardTarot.TRUMP_8);
        trs_.add(t_);
        t_ = new TrickTarot(t_.getRamasseur(),true);
        t_.ajouter(CardTarot.TRUMP_19);
        t_.ajouter(CardTarot.CLUB_2);
        t_.ajouter(CardTarot.TRUMP_9);
        t_.ajouter(CardTarot.TRUMP_10);
        t_.ajouter(CardTarot.TRUMP_12);
        trs_.add(t_);
        TrickTarot pr_ = new TrickTarot(t_.getRamasseur(),true);
        pr_.ajouter(CardTarot.TRUMP_18);
        pr_.ajouter(CardTarot.CLUB_3);
        pr_.ajouter(CardTarot.TRUMP_13);
        HandTarot calledCards_ = new HandTarot();
        calledCards_.ajouter(CardTarot.CLUB_KING);
        GameTarot g_ = newGameTarotWithourDecl(r_, trs_, pr_, 2, bids_, calledCards_, 2, last_);
        GameTarotTrickInfo info_ = newGameTarotTrickInfo(g_);
        HandTarot curHand_ = new HandTarot();
        curHand_.ajouter(CardTarot.HEART_6);
        curHand_.ajouter(CardTarot.HEART_9);
        curHand_.ajouter(CardTarot.TRUMP_16);
        curHand_.ajouter(CardTarot.TRUMP_2);
        curHand_.ajouter(CardTarot.DIAMOND_2);
        curHand_.ajouter(CardTarot.DIAMOND_10);
        curHand_.ajouter(CardTarot.DIAMOND_JACK);
        curHand_.ajouter(CardTarot.DIAMOND_9);
        curHand_.ajouter(CardTarot.CLUB_KNIGHT);
        curHand_.ajouter(CardTarot.CLUB_10);
        curHand_.ajouter(CardTarot.HEART_7);
        curHand_.ajouter(CardTarot.HEART_JACK);
        GameTarotTeamsRelation team_ = g_.getTeamsRelation();
        EnumMap<Suit, EqList<HandTarot>> poss_ = info_.cartesPossibles(team_, curHand_, (byte) 3, last_);
        EnumMap<Hypothesis, EnumMap<Suit, EqList<HandTarot>>> hypos_ = info_.cartesCertaines(team_, poss_);
        poss_ = hypos_.getVal(Hypothesis.POSSIBLE);
        EnumMap<Suit, EqList<HandTarot>> sure_ = hypos_.getVal(Hypothesis.SURE);
        CustList<TrickTarot> tricks_ = g_.getTricks();
        tricks_.add(pr_);
        GameTarotTrickHypothesis.hypothesesRepartitionsJoueurs(team_,g_.getCalledCards(),g_.getContrat(),tricks_, (byte) 3,poss_,sure_);
    }
    @Test
    public void hypothesesRepartitionsJoueurs7Test() {
        HandTarot last_ = new HandTarot();
        last_.ajouter(CardTarot.HEART_KING);
        last_.ajouter(CardTarot.HEART_8);
        last_.ajouter(CardTarot.DIAMOND_KNIGHT);
        RulesTarot r_ = new RulesTarot();
        CustList<TrickTarot> trs_ = new CustList<TrickTarot>();
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.TAKE);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        TrickTarot t_ = new TrickTarot((byte) getTaker(r_,2,bids_),false);
        t_.ajouter(CardTarot.HEART_QUEEN);
        t_.ajouter(CardTarot.HEART_KNIGHT);
        t_.ajouter(CardTarot.HEART_10);
        trs_.add(t_);
        t_ = new TrickTarot(r_.getDealing().getNextPlayer(2),true);
        t_.ajouter(CardTarot.TRUMP_14);
        t_.ajouter(CardTarot.TRUMP_3);
        t_.ajouter(CardTarot.TRUMP_21);
        t_.ajouter(CardTarot.TRUMP_11);
        t_.ajouter(CardTarot.TRUMP_4);
        trs_.add(t_);
        t_ = new TrickTarot(t_.getRamasseur(),true);
        t_.ajouter(CardTarot.TRUMP_20);
        t_.ajouter(CardTarot.TRUMP_1);
        t_.ajouter(CardTarot.TRUMP_6);
        t_.ajouter(CardTarot.TRUMP_7);
        t_.ajouter(CardTarot.TRUMP_8);
        trs_.add(t_);
        TrickTarot pr_ = new TrickTarot(t_.getRamasseur(),true);
        pr_.ajouter(CardTarot.TRUMP_19);
        pr_.ajouter(CardTarot.TRUMP_12);
        pr_.ajouter(CardTarot.TRUMP_9);
        HandTarot calledCards_ = new HandTarot();
        calledCards_.ajouter(CardTarot.CLUB_KING);
        GameTarot g_ = newGameTarotWithourDecl(r_, trs_, pr_, 2, bids_, calledCards_, 2, last_);
        GameTarotTrickInfo info_ = newGameTarotTrickInfo(g_);
        HandTarot curHand_ = new HandTarot();
        curHand_.ajouter(CardTarot.HEART_6);
        curHand_.ajouter(CardTarot.HEART_9);
        curHand_.ajouter(CardTarot.TRUMP_16);
        curHand_.ajouter(CardTarot.TRUMP_10);
        curHand_.ajouter(CardTarot.TRUMP_2);
        curHand_.ajouter(CardTarot.DIAMOND_2);
        curHand_.ajouter(CardTarot.DIAMOND_10);
        curHand_.ajouter(CardTarot.DIAMOND_JACK);
        curHand_.ajouter(CardTarot.DIAMOND_9);
        curHand_.ajouter(CardTarot.CLUB_KNIGHT);
        curHand_.ajouter(CardTarot.CLUB_10);
        curHand_.ajouter(CardTarot.HEART_7);
        curHand_.ajouter(CardTarot.HEART_JACK);
        GameTarotTeamsRelation team_ = g_.getTeamsRelation();
        EnumMap<Suit, EqList<HandTarot>> poss_ = info_.cartesPossibles(team_, curHand_, (byte) 3, last_);
        EnumMap<Hypothesis, EnumMap<Suit, EqList<HandTarot>>> hypos_ = info_.cartesCertaines(team_, poss_);
        poss_ = hypos_.getVal(Hypothesis.POSSIBLE);
        EnumMap<Suit, EqList<HandTarot>> sure_ = hypos_.getVal(Hypothesis.SURE);
        CustList<TrickTarot> tricks_ = g_.getTricks();
        tricks_.add(pr_);
        GameTarotTrickHypothesis.hypothesesRepartitionsJoueurs(team_,g_.getCalledCards(),g_.getContrat(),tricks_, (byte) 3,poss_,sure_);
    }
    @Test
    public void hypothesesRepartitionsJoueurs8Test() {
        HandTarot last_ = new HandTarot();
        last_.ajouter(CardTarot.HEART_KING);
        last_.ajouter(CardTarot.HEART_8);
        last_.ajouter(CardTarot.DIAMOND_KNIGHT);
        RulesTarot r_ = new RulesTarot();
        CustList<TrickTarot> trs_ = new CustList<TrickTarot>();
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.TAKE);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        TrickTarot t_ = new TrickTarot((byte) getTaker(r_,2,bids_),false);
        t_.ajouter(CardTarot.HEART_QUEEN);
        t_.ajouter(CardTarot.HEART_KNIGHT);
        t_.ajouter(CardTarot.HEART_10);
        trs_.add(t_);
        t_ = new TrickTarot(r_.getDealing().getNextPlayer(2),true);
        t_.ajouter(CardTarot.TRUMP_14);
        t_.ajouter(CardTarot.TRUMP_3);
        t_.ajouter(CardTarot.TRUMP_21);
        t_.ajouter(CardTarot.TRUMP_11);
        t_.ajouter(CardTarot.TRUMP_4);
        trs_.add(t_);
        t_ = new TrickTarot(t_.getRamasseur(),true);
        t_.ajouter(CardTarot.TRUMP_20);
        t_.ajouter(CardTarot.TRUMP_1);
        t_.ajouter(CardTarot.TRUMP_6);
        t_.ajouter(CardTarot.TRUMP_7);
        t_.ajouter(CardTarot.TRUMP_8);
        trs_.add(t_);
        t_ = new TrickTarot(t_.getRamasseur(),true);
        t_.ajouter(CardTarot.TRUMP_19);
        t_.ajouter(CardTarot.TRUMP_15);
        t_.ajouter(CardTarot.TRUMP_9);
        t_.ajouter(CardTarot.TRUMP_10);
        t_.ajouter(CardTarot.TRUMP_12);
        trs_.add(t_);
        TrickTarot pr_ = new TrickTarot(t_.getRamasseur(),true);
        pr_.ajouter(CardTarot.TRUMP_18);
        pr_.ajouter(CardTarot.TRUMP_16);
        pr_.ajouter(CardTarot.TRUMP_13);
        HandTarot calledCards_ = new HandTarot();
        calledCards_.ajouter(CardTarot.CLUB_KING);
        GameTarot g_ = newGameTarotWithourDecl(r_, trs_, pr_, 2, bids_, calledCards_, 2, last_);
        GameTarotTrickInfo info_ = newGameTarotTrickInfo(g_);
        HandTarot curHand_ = new HandTarot();
        curHand_.ajouter(CardTarot.HEART_6);
        curHand_.ajouter(CardTarot.HEART_9);
        curHand_.ajouter(CardTarot.TRUMP_17);
        curHand_.ajouter(CardTarot.TRUMP_2);
        curHand_.ajouter(CardTarot.DIAMOND_2);
        curHand_.ajouter(CardTarot.DIAMOND_10);
        curHand_.ajouter(CardTarot.DIAMOND_JACK);
        curHand_.ajouter(CardTarot.DIAMOND_9);
        curHand_.ajouter(CardTarot.CLUB_KNIGHT);
        curHand_.ajouter(CardTarot.CLUB_10);
        curHand_.ajouter(CardTarot.HEART_7);
        curHand_.ajouter(CardTarot.HEART_JACK);
        GameTarotTeamsRelation team_ = g_.getTeamsRelation();
        EnumMap<Suit, EqList<HandTarot>> poss_ = info_.cartesPossibles(team_, curHand_, (byte) 3, last_);
        EnumMap<Hypothesis, EnumMap<Suit, EqList<HandTarot>>> hypos_ = info_.cartesCertaines(team_, poss_);
        poss_ = hypos_.getVal(Hypothesis.POSSIBLE);
        EnumMap<Suit, EqList<HandTarot>> sure_ = hypos_.getVal(Hypothesis.SURE);
        CustList<TrickTarot> tricks_ = g_.getTricks();
        tricks_.add(pr_);
        GameTarotTrickHypothesis.hypothesesRepartitionsJoueurs(team_,g_.getCalledCards(),g_.getContrat(),tricks_, (byte) 3,poss_,sure_);
    }
    @Test
    public void hypothesesRepartitionsJoueurs9Test() {
        HandTarot last_ = new HandTarot();
        last_.ajouter(CardTarot.HEART_KING);
        last_.ajouter(CardTarot.HEART_8);
        last_.ajouter(CardTarot.DIAMOND_KNIGHT);
        RulesTarot r_ = new RulesTarot();
        CustList<TrickTarot> trs_ = new CustList<TrickTarot>();
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.TAKE);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        TrickTarot t_ = new TrickTarot((byte) getTaker(r_,2,bids_),false);
        t_.ajouter(CardTarot.HEART_QUEEN);
        t_.ajouter(CardTarot.HEART_KNIGHT);
        t_.ajouter(CardTarot.HEART_10);
        trs_.add(t_);
        t_ = new TrickTarot(r_.getDealing().getNextPlayer(2),true);
        t_.ajouter(CardTarot.TRUMP_14);
        t_.ajouter(CardTarot.TRUMP_3);
        t_.ajouter(CardTarot.TRUMP_21);
        t_.ajouter(CardTarot.TRUMP_11);
        t_.ajouter(CardTarot.TRUMP_4);
        trs_.add(t_);
        t_ = new TrickTarot(t_.getRamasseur(),true);
        t_.ajouter(CardTarot.TRUMP_20);
        t_.ajouter(CardTarot.TRUMP_1);
        t_.ajouter(CardTarot.TRUMP_6);
        t_.ajouter(CardTarot.TRUMP_7);
        t_.ajouter(CardTarot.TRUMP_8);
        trs_.add(t_);
        t_ = new TrickTarot(t_.getRamasseur(),true);
        t_.ajouter(CardTarot.TRUMP_19);
        t_.ajouter(CardTarot.TRUMP_15);
        t_.ajouter(CardTarot.TRUMP_9);
        t_.ajouter(CardTarot.TRUMP_10);
        t_.ajouter(CardTarot.TRUMP_12);
        trs_.add(t_);
        TrickTarot pr_ = new TrickTarot(t_.getRamasseur(),true);
        HandTarot calledCards_ = new HandTarot();
        calledCards_.ajouter(CardTarot.CLUB_KING);
        GameTarot g_ = newGameTarotWithourDecl(r_, trs_, pr_, 2, bids_, calledCards_, 2, last_);
        GameTarotTrickInfo info_ = newGameTarotTrickInfo(g_);
        HandTarot curHand_ = new HandTarot();
        curHand_.ajouter(CardTarot.HEART_9);
        curHand_.ajouter(CardTarot.TRUMP_18);
        curHand_.ajouter(CardTarot.TRUMP_17);
        curHand_.ajouter(CardTarot.TRUMP_2);
        curHand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        curHand_.ajouter(CardTarot.DIAMOND_10);
        curHand_.ajouter(CardTarot.DIAMOND_JACK);
        curHand_.ajouter(CardTarot.DIAMOND_9);
        curHand_.ajouter(CardTarot.CLUB_KNIGHT);
        curHand_.ajouter(CardTarot.CLUB_10);
        curHand_.ajouter(CardTarot.HEART_8);
        curHand_.ajouter(CardTarot.HEART_KING);
        GameTarotTeamsRelation team_ = g_.getTeamsRelation();
        EnumMap<Suit, EqList<HandTarot>> poss_ = info_.cartesPossibles(team_, curHand_, (byte) 0, last_);
        EnumMap<Hypothesis, EnumMap<Suit, EqList<HandTarot>>> hypos_ = info_.cartesCertaines(team_, poss_);
        poss_ = hypos_.getVal(Hypothesis.POSSIBLE);
        EnumMap<Suit, EqList<HandTarot>> sure_ = hypos_.getVal(Hypothesis.SURE);
        CustList<TrickTarot> tricks_ = g_.getTricks();
        tricks_.add(pr_);
        GameTarotTrickHypothesis.hypothesesRepartitionsJoueurs(team_,g_.getCalledCards(),g_.getContrat(),tricks_, (byte) 0,poss_,sure_);
    }
    @Test
    public void hypothesesRepartitionsJoueurs10Test() {
        HandTarot last_ = new HandTarot();
        last_.ajouter(CardTarot.HEART_KING);
        last_.ajouter(CardTarot.HEART_8);
        last_.ajouter(CardTarot.DIAMOND_KNIGHT);
        RulesTarot r_ = new RulesTarot();
        CustList<TrickTarot> trs_ = new CustList<TrickTarot>();
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.TAKE);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        TrickTarot t_ = new TrickTarot((byte) getTaker(r_,2,bids_),false);
        t_.ajouter(CardTarot.HEART_QUEEN);
        t_.ajouter(CardTarot.HEART_KNIGHT);
        t_.ajouter(CardTarot.HEART_10);
        trs_.add(t_);
        t_ = new TrickTarot(r_.getDealing().getNextPlayer(2),true);
        t_.ajouter(CardTarot.TRUMP_14);
        t_.ajouter(CardTarot.TRUMP_3);
        t_.ajouter(CardTarot.TRUMP_21);
        t_.ajouter(CardTarot.TRUMP_11);
        t_.ajouter(CardTarot.TRUMP_4);
        trs_.add(t_);
        t_ = new TrickTarot(t_.getRamasseur(),true);
        t_.ajouter(CardTarot.TRUMP_20);
        t_.ajouter(CardTarot.TRUMP_1);
        t_.ajouter(CardTarot.TRUMP_6);
        t_.ajouter(CardTarot.TRUMP_7);
        t_.ajouter(CardTarot.TRUMP_8);
        trs_.add(t_);
        t_ = new TrickTarot(t_.getRamasseur(),true);
        t_.ajouter(CardTarot.TRUMP_19);
        t_.ajouter(CardTarot.CLUB_2);
        t_.ajouter(CardTarot.TRUMP_9);
        t_.ajouter(CardTarot.TRUMP_10);
        t_.ajouter(CardTarot.TRUMP_12);
        trs_.add(t_);
        TrickTarot pr_ = new TrickTarot(t_.getRamasseur(),true);
        HandTarot calledCards_ = new HandTarot();
        calledCards_.ajouter(CardTarot.CLUB_KING);
        GameTarot g_ = newGameTarotWithourDecl(r_, trs_, pr_, 2, bids_, calledCards_, 2, last_);
        GameTarotTrickInfo info_ = newGameTarotTrickInfo(g_);
        HandTarot curHand_ = new HandTarot();
        curHand_.ajouter(CardTarot.HEART_9);
        curHand_.ajouter(CardTarot.TRUMP_18);
        curHand_.ajouter(CardTarot.TRUMP_17);
        curHand_.ajouter(CardTarot.TRUMP_2);
        curHand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        curHand_.ajouter(CardTarot.DIAMOND_10);
        curHand_.ajouter(CardTarot.DIAMOND_JACK);
        curHand_.ajouter(CardTarot.DIAMOND_9);
        curHand_.ajouter(CardTarot.CLUB_KNIGHT);
        curHand_.ajouter(CardTarot.CLUB_10);
        curHand_.ajouter(CardTarot.HEART_8);
        curHand_.ajouter(CardTarot.HEART_KING);
        GameTarotTeamsRelation team_ = g_.getTeamsRelation();
        EnumMap<Suit, EqList<HandTarot>> poss_ = info_.cartesPossibles(team_, curHand_, (byte) 0, last_);
        EnumMap<Hypothesis, EnumMap<Suit, EqList<HandTarot>>> hypos_ = info_.cartesCertaines(team_, poss_);
        poss_ = hypos_.getVal(Hypothesis.POSSIBLE);
        EnumMap<Suit, EqList<HandTarot>> sure_ = hypos_.getVal(Hypothesis.SURE);
        CustList<TrickTarot> tricks_ = g_.getTricks();
        tricks_.add(pr_);
        GameTarotTrickHypothesis.hypothesesRepartitionsJoueurs(team_,g_.getCalledCards(),g_.getContrat(),tricks_, (byte) 0,poss_,sure_);
    }
    @Test
    public void hypothesesRepartitionsJoueurs11Test() {
        HandTarot last_ = new HandTarot();
        last_.ajouter(CardTarot.HEART_KING);
        last_.ajouter(CardTarot.HEART_8);
        last_.ajouter(CardTarot.DIAMOND_KNIGHT);
        RulesTarot r_ = new RulesTarot();
        CustList<TrickTarot> trs_ = new CustList<TrickTarot>();
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.TAKE);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        TrickTarot t_ = new TrickTarot((byte) getTaker(r_,2,bids_),false);
        t_.ajouter(CardTarot.SPADE_10);
        t_.ajouter(CardTarot.SPADE_4);
        t_.ajouter(CardTarot.SPADE_1);
        trs_.add(t_);
        t_ = new TrickTarot(r_.getDealing().getNextPlayer(2),true);
        t_.ajouter(CardTarot.HEART_1);
        t_.ajouter(CardTarot.HEART_2);
        t_.ajouter(CardTarot.HEART_KING);
        t_.ajouter(CardTarot.HEART_3);
        t_.ajouter(CardTarot.HEART_JACK);
        trs_.add(t_);
        t_ = new TrickTarot(t_.getRamasseur(),true);
        t_.ajouter(CardTarot.HEART_QUEEN);
        t_.ajouter(CardTarot.HEART_KNIGHT);
        t_.ajouter(CardTarot.HEART_10);
        t_.ajouter(CardTarot.HEART_4);
        t_.ajouter(CardTarot.HEART_5);
        trs_.add(t_);
        TrickTarot pr_ = new TrickTarot(t_.getRamasseur(),true);
        HandTarot calledCards_ = new HandTarot();
        calledCards_.ajouter(CardTarot.CLUB_KING);
        GameTarot g_ = newGameTarotWithourDecl(r_, trs_, pr_, 2, bids_, calledCards_, 2, last_);
        GameTarotTrickInfo info_ = newGameTarotTrickInfo(g_);
        HandTarot curHand_ = new HandTarot();
        curHand_.ajouter(CardTarot.HEART_6);
        curHand_.ajouter(CardTarot.HEART_9);
        curHand_.ajouter(CardTarot.TRUMP_18);
        curHand_.ajouter(CardTarot.TRUMP_17);
        curHand_.ajouter(CardTarot.TRUMP_2);
        curHand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        curHand_.ajouter(CardTarot.DIAMOND_10);
        curHand_.ajouter(CardTarot.DIAMOND_JACK);
        curHand_.ajouter(CardTarot.DIAMOND_9);
        curHand_.ajouter(CardTarot.CLUB_KNIGHT);
        curHand_.ajouter(CardTarot.CLUB_10);
        curHand_.ajouter(CardTarot.HEART_8);
        curHand_.ajouter(CardTarot.SPADE_2);
        GameTarotTeamsRelation team_ = g_.getTeamsRelation();
        EnumMap<Suit, EqList<HandTarot>> poss_ = info_.cartesPossibles(team_, curHand_, (byte) 0, last_);
        EnumMap<Hypothesis, EnumMap<Suit, EqList<HandTarot>>> hypos_ = info_.cartesCertaines(team_, poss_);
        poss_ = hypos_.getVal(Hypothesis.POSSIBLE);
        EnumMap<Suit, EqList<HandTarot>> sure_ = hypos_.getVal(Hypothesis.SURE);
        CustList<TrickTarot> tricks_ = g_.getTricks();
        tricks_.add(pr_);
        GameTarotTrickHypothesis.hypothesesRepartitionsJoueurs(team_,g_.getCalledCards(),g_.getContrat(),tricks_, (byte) 0,poss_,sure_);
    }
    @Test
    public void hypothesesRepartitionsJoueurs12Test() {
        HandTarot last_ = new HandTarot();
        last_.ajouter(CardTarot.HEART_KING);
        last_.ajouter(CardTarot.HEART_8);
        last_.ajouter(CardTarot.DIAMOND_KNIGHT);
        RulesTarot r_ = new RulesTarot();
        CustList<TrickTarot> trs_ = new CustList<TrickTarot>();
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.TAKE);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        TrickTarot t_ = new TrickTarot((byte) getTaker(r_,2,bids_),false);
        t_.ajouter(CardTarot.SPADE_10);
        t_.ajouter(CardTarot.SPADE_4);
        t_.ajouter(CardTarot.SPADE_1);
        trs_.add(t_);
        t_ = new TrickTarot(r_.getDealing().getNextPlayer(2),true);
        t_.ajouter(CardTarot.HEART_1);
        t_.ajouter(CardTarot.HEART_2);
        t_.ajouter(CardTarot.HEART_KING);
        t_.ajouter(CardTarot.HEART_JACK);
        t_.ajouter(CardTarot.HEART_3);
        trs_.add(t_);
        t_ = new TrickTarot(t_.getRamasseur(),true);
        t_.ajouter(CardTarot.HEART_QUEEN);
        t_.ajouter(CardTarot.HEART_KNIGHT);
        t_.ajouter(CardTarot.HEART_10);
        t_.ajouter(CardTarot.HEART_4);
        t_.ajouter(CardTarot.HEART_5);
        trs_.add(t_);
        TrickTarot pr_ = new TrickTarot(t_.getRamasseur(),true);
        HandTarot calledCards_ = new HandTarot();
        calledCards_.ajouter(CardTarot.CLUB_KING);
        GameTarot g_ = newGameTarotWithourDecl(r_, trs_, pr_, 2, bids_, calledCards_, 2, last_);
        GameTarotTrickInfo info_ = newGameTarotTrickInfo(g_);
        HandTarot curHand_ = new HandTarot();
        curHand_.ajouter(CardTarot.HEART_6);
        curHand_.ajouter(CardTarot.HEART_9);
        curHand_.ajouter(CardTarot.TRUMP_18);
        curHand_.ajouter(CardTarot.TRUMP_17);
        curHand_.ajouter(CardTarot.TRUMP_2);
        curHand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        curHand_.ajouter(CardTarot.DIAMOND_10);
        curHand_.ajouter(CardTarot.DIAMOND_JACK);
        curHand_.ajouter(CardTarot.DIAMOND_9);
        curHand_.ajouter(CardTarot.CLUB_KNIGHT);
        curHand_.ajouter(CardTarot.CLUB_10);
        curHand_.ajouter(CardTarot.HEART_8);
        curHand_.ajouter(CardTarot.SPADE_2);
        GameTarotTeamsRelation team_ = g_.getTeamsRelation();
        EnumMap<Suit, EqList<HandTarot>> poss_ = info_.cartesPossibles(team_, curHand_, (byte) 0, last_);
        EnumMap<Hypothesis, EnumMap<Suit, EqList<HandTarot>>> hypos_ = info_.cartesCertaines(team_, poss_);
        poss_ = hypos_.getVal(Hypothesis.POSSIBLE);
        EnumMap<Suit, EqList<HandTarot>> sure_ = hypos_.getVal(Hypothesis.SURE);
        CustList<TrickTarot> tricks_ = g_.getTricks();
        tricks_.add(pr_);
        GameTarotTrickHypothesis.hypothesesRepartitionsJoueurs(team_,g_.getCalledCards(),g_.getContrat(),tricks_, (byte) 0,poss_,sure_);
    }
    @Test
    public void hypothesesRepartitionsJoueurs13Test() {
        HandTarot last_ = new HandTarot();
        last_.ajouter(CardTarot.HEART_KING);
        last_.ajouter(CardTarot.HEART_8);
        last_.ajouter(CardTarot.DIAMOND_KNIGHT);
        RulesTarot r_ = new RulesTarot();
        CustList<TrickTarot> trs_ = new CustList<TrickTarot>();
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.TAKE);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        TrickTarot t_ = new TrickTarot((byte) getTaker(r_,2,bids_),false);
        t_.ajouter(CardTarot.SPADE_10);
        t_.ajouter(CardTarot.SPADE_4);
        t_.ajouter(CardTarot.SPADE_1);
        trs_.add(t_);
        t_ = new TrickTarot(r_.getDealing().getNextPlayer(2),true);
        t_.ajouter(CardTarot.HEART_1);
        t_.ajouter(CardTarot.HEART_2);
        t_.ajouter(CardTarot.HEART_KING);
        t_.ajouter(CardTarot.HEART_3);
        t_.ajouter(CardTarot.HEART_KNIGHT);
        trs_.add(t_);
        t_ = new TrickTarot(t_.getRamasseur(),true);
        t_.ajouter(CardTarot.HEART_QUEEN);
        t_.ajouter(CardTarot.HEART_10);
        t_.ajouter(CardTarot.HEART_JACK);
        t_.ajouter(CardTarot.HEART_4);
        t_.ajouter(CardTarot.HEART_5);
        trs_.add(t_);
        TrickTarot pr_ = new TrickTarot(t_.getRamasseur(),true);
        HandTarot calledCards_ = new HandTarot();
        calledCards_.ajouter(CardTarot.CLUB_KING);
        GameTarot g_ = newGameTarotWithourDecl(r_, trs_, pr_, 2, bids_, calledCards_, 2, last_);
        GameTarotTrickInfo info_ = newGameTarotTrickInfo(g_);
        HandTarot curHand_ = new HandTarot();
        curHand_.ajouter(CardTarot.HEART_6);
        curHand_.ajouter(CardTarot.HEART_9);
        curHand_.ajouter(CardTarot.TRUMP_18);
        curHand_.ajouter(CardTarot.TRUMP_17);
        curHand_.ajouter(CardTarot.TRUMP_2);
        curHand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        curHand_.ajouter(CardTarot.DIAMOND_10);
        curHand_.ajouter(CardTarot.DIAMOND_JACK);
        curHand_.ajouter(CardTarot.DIAMOND_9);
        curHand_.ajouter(CardTarot.CLUB_KNIGHT);
        curHand_.ajouter(CardTarot.CLUB_10);
        curHand_.ajouter(CardTarot.HEART_8);
        curHand_.ajouter(CardTarot.SPADE_2);
        GameTarotTeamsRelation team_ = g_.getTeamsRelation();
        EnumMap<Suit, EqList<HandTarot>> poss_ = info_.cartesPossibles(team_, curHand_, (byte) 0, last_);
        EnumMap<Hypothesis, EnumMap<Suit, EqList<HandTarot>>> hypos_ = info_.cartesCertaines(team_, poss_);
        poss_ = hypos_.getVal(Hypothesis.POSSIBLE);
        EnumMap<Suit, EqList<HandTarot>> sure_ = hypos_.getVal(Hypothesis.SURE);
        CustList<TrickTarot> tricks_ = g_.getTricks();
        tricks_.add(pr_);
        GameTarotTrickHypothesis.hypothesesRepartitionsJoueurs(team_,g_.getCalledCards(),g_.getContrat(),tricks_, (byte) 0,poss_,sure_);
    }
    @Test
    public void hypothesesRepartitionsJoueurs14Test() {
        HandTarot last_ = new HandTarot();
        last_.ajouter(CardTarot.HEART_KING);
        last_.ajouter(CardTarot.HEART_8);
        last_.ajouter(CardTarot.DIAMOND_KNIGHT);
        RulesTarot r_ = new RulesTarot();
        CustList<TrickTarot> trs_ = new CustList<TrickTarot>();
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.TAKE);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        TrickTarot t_ = new TrickTarot((byte) getTaker(r_,2,bids_),false);
        t_.ajouter(CardTarot.SPADE_10);
        t_.ajouter(CardTarot.SPADE_4);
        t_.ajouter(CardTarot.SPADE_1);
        trs_.add(t_);
        t_ = new TrickTarot(r_.getDealing().getNextPlayer(2),true);
        t_.ajouter(CardTarot.TRUMP_2);
        t_.ajouter(CardTarot.TRUMP_3);
        t_.ajouter(CardTarot.TRUMP_21);
        t_.ajouter(CardTarot.TRUMP_4);
        t_.ajouter(CardTarot.HEART_KNIGHT);
        trs_.add(t_);
        t_ = new TrickTarot(t_.getRamasseur(),true);
        t_.ajouter(CardTarot.TRUMP_20);
        t_.ajouter(CardTarot.TRUMP_10);
        t_.ajouter(CardTarot.HEART_JACK);
        t_.ajouter(CardTarot.TRUMP_8);
        t_.ajouter(CardTarot.TRUMP_9);
        trs_.add(t_);
        TrickTarot pr_ = new TrickTarot(t_.getRamasseur(),true);
        HandTarot calledCards_ = new HandTarot();
        calledCards_.ajouter(CardTarot.CLUB_KING);
        GameTarot g_ = newGameTarotWithourDecl(r_, trs_, pr_, 2, bids_, calledCards_, 2, last_);
        GameTarotTrickInfo info_ = newGameTarotTrickInfo(g_);
        HandTarot curHand_ = new HandTarot();
        curHand_.ajouter(CardTarot.HEART_6);
        curHand_.ajouter(CardTarot.HEART_9);
        curHand_.ajouter(CardTarot.TRUMP_18);
        curHand_.ajouter(CardTarot.TRUMP_17);
        curHand_.ajouter(CardTarot.TRUMP_11);
        curHand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        curHand_.ajouter(CardTarot.DIAMOND_10);
        curHand_.ajouter(CardTarot.DIAMOND_JACK);
        curHand_.ajouter(CardTarot.DIAMOND_9);
        curHand_.ajouter(CardTarot.CLUB_KNIGHT);
        curHand_.ajouter(CardTarot.CLUB_10);
        curHand_.ajouter(CardTarot.HEART_8);
        curHand_.ajouter(CardTarot.SPADE_2);
        GameTarotTeamsRelation team_ = g_.getTeamsRelation();
        EnumMap<Suit, EqList<HandTarot>> poss_ = info_.cartesPossibles(team_, curHand_, (byte) 0, last_);
        EnumMap<Hypothesis, EnumMap<Suit, EqList<HandTarot>>> hypos_ = info_.cartesCertaines(team_, poss_);
        poss_ = hypos_.getVal(Hypothesis.POSSIBLE);
        EnumMap<Suit, EqList<HandTarot>> sure_ = hypos_.getVal(Hypothesis.SURE);
        CustList<TrickTarot> tricks_ = g_.getTricks();
        tricks_.add(pr_);
        GameTarotTrickHypothesis.hypothesesRepartitionsJoueurs(team_,g_.getCalledCards(),g_.getContrat(),tricks_, (byte) 0,poss_,sure_);
    }
    @Test
    public void hypothesesRepartitionsJoueurs15Test() {
        HandTarot last_ = new HandTarot();
        last_.ajouter(CardTarot.HEART_KING);
        last_.ajouter(CardTarot.HEART_8);
        last_.ajouter(CardTarot.DIAMOND_KNIGHT);
        RulesTarot r_ = new RulesTarot();
        CustList<TrickTarot> trs_ = new CustList<TrickTarot>();
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.TAKE);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        TrickTarot t_ = new TrickTarot((byte) getTaker(r_,2,bids_),false);
        t_.ajouter(CardTarot.SPADE_10);
        t_.ajouter(CardTarot.SPADE_4);
        t_.ajouter(CardTarot.SPADE_1);
        trs_.add(t_);
        t_ = new TrickTarot(r_.getDealing().getNextPlayer(2),true);
        t_.ajouter(CardTarot.TRUMP_2);
        t_.ajouter(CardTarot.TRUMP_3);
        t_.ajouter(CardTarot.TRUMP_21);
        t_.ajouter(CardTarot.TRUMP_4);
        t_.ajouter(CardTarot.HEART_KNIGHT);
        trs_.add(t_);
        t_ = new TrickTarot(t_.getRamasseur(),true);
        t_.ajouter(CardTarot.TRUMP_20);
        t_.ajouter(CardTarot.TRUMP_10);
        t_.ajouter(CardTarot.HEART_10);
        t_.ajouter(CardTarot.TRUMP_8);
        t_.ajouter(CardTarot.TRUMP_9);
        trs_.add(t_);
        TrickTarot pr_ = new TrickTarot(t_.getRamasseur(),true);
        HandTarot calledCards_ = new HandTarot();
        calledCards_.ajouter(CardTarot.CLUB_KING);
        GameTarot g_ = newGameTarotWithourDecl(r_, trs_, pr_, 2, bids_, calledCards_, 2, last_);
        GameTarotTrickInfo info_ = newGameTarotTrickInfo(g_);
        HandTarot curHand_ = new HandTarot();
        curHand_.ajouter(CardTarot.HEART_6);
        curHand_.ajouter(CardTarot.HEART_9);
        curHand_.ajouter(CardTarot.TRUMP_18);
        curHand_.ajouter(CardTarot.TRUMP_17);
        curHand_.ajouter(CardTarot.TRUMP_11);
        curHand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        curHand_.ajouter(CardTarot.DIAMOND_10);
        curHand_.ajouter(CardTarot.DIAMOND_JACK);
        curHand_.ajouter(CardTarot.DIAMOND_9);
        curHand_.ajouter(CardTarot.CLUB_KNIGHT);
        curHand_.ajouter(CardTarot.CLUB_10);
        curHand_.ajouter(CardTarot.HEART_8);
        curHand_.ajouter(CardTarot.SPADE_2);
        GameTarotTeamsRelation team_ = g_.getTeamsRelation();
        EnumMap<Suit, EqList<HandTarot>> poss_ = info_.cartesPossibles(team_, curHand_, (byte) 0, last_);
        EnumMap<Hypothesis, EnumMap<Suit, EqList<HandTarot>>> hypos_ = info_.cartesCertaines(team_, poss_);
        poss_ = hypos_.getVal(Hypothesis.POSSIBLE);
        EnumMap<Suit, EqList<HandTarot>> sure_ = hypos_.getVal(Hypothesis.SURE);
        CustList<TrickTarot> tricks_ = g_.getTricks();
        tricks_.add(pr_);
        GameTarotTrickHypothesis.hypothesesRepartitionsJoueurs(team_,g_.getCalledCards(),g_.getContrat(),tricks_, (byte) 0,poss_,sure_);
    }
    private GameTarot newGameTarotWithourDecl(RulesTarot _r, CustList<TrickTarot> _trs, TrickTarot _prog,
                                              int _dealer,
                                              EnumList<BidTarot> _bids, HandTarot _calledCards, int _call, HandTarot _lastHand) {
        int nbPl_ = _r.getRepartition().getNombreJoueurs();
        EqList<EnumList<Miseres>> m_ = new EqList<EnumList<Miseres>>();
        EqList<HandTarot> h_ = new EqList<HandTarot>();
        for (int i = 0; i < nbPl_; i++) {
            m_.add(new EnumList<Miseres>());
            h_.add(new HandTarot());
        }
        return newGameTarot(_r,_trs,_prog,m_,h_,_dealer,_bids,_calledCards,_call,_lastHand);
    }
    private GameTarot newGameTarot(RulesTarot _r, CustList<TrickTarot> _trs,TrickTarot _prog,
                                   EqList<EnumList<Miseres>> _m, EqList<HandTarot> _h, int _dealer,
                                   EnumList<BidTarot> _bids, HandTarot _calledCards, int _call, HandTarot _lastHand) {
        EqList<HandTarot> deal_ = new EqList<HandTarot>();
        deal_.add(_lastHand);
        GameTarot g_ = new GameTarot(GameType.RANDOM,new DealTarot(deal_, (byte) _dealer),_r);
        g_.setProgressingTrick(_prog);
        g_.setTricks(_trs);
        g_.setHandfuls(_h);
        g_.setDeclaresMiseres(_m);
        g_.setBids(_bids);
        g_.setCalledCards(_calledCards);
        if (_call > -1) {
            g_.getAppele().add((byte) _call);
        }
        byte player_ = g_.playerAfter((byte) _dealer);
        int taker_ = getTaker(_r,_dealer,_bids);
        BidTarot bid_ = BidTarot.FOLD;
        for (BidTarot b: _bids) {
            if (b.strongerThan(bid_)) {
                bid_ = b;
            }
            player_ = g_.playerAfter(player_);
        }
        g_.setPreneur((byte) taker_);
        g_.setContrat(bid_);
        if (!g_.avecContrat() || !bid_.isJouerDonne()) {
            g_.initEquipeDetermineeSansPreneur();
        } else if (_r.getRepartition().getAppel() == CallingCard.DEFINED) {
            g_.initEquipeDeterminee();
        } else if (_r.getRepartition().getAppel() == CallingCard.WITHOUT) {
            g_.initDefense();
        }
        CustList<TrickTarot> tricks_ = g_.unionPlis(false);
        byte starter_;
        byte trickWinner_;
        for (TrickTarot t: tricks_) {
            if (t.getVuParToutJoueur()) {
                if (t.total() != g_.getNombreDeJoueurs()) {
                    fail("bad count");
                }
            } else {
                if (t.total() != g_.getRegles().getRepartition().getNombreCartesChien()) {
                    fail("bad count");
                }
            }
        }
        if (g_.getProgressingTrick().total() >= g_.getNombreDeJoueurs()) {
            fail("bad count");
        }
        if (!tricks_.isEmpty()) {
            starter_ = _prog.getEntameur();
            trickWinner_ = _prog.getEntameur();
            for (TrickTarot t: tricks_) {
                if (!t.getVuParToutJoueur()) {
                    continue;
                }
                g_.retrieveCalledPlayers(t);
            }
            g_.retrieveCalledPlayers(_prog);
        } else if (_prog.getVuParToutJoueur()) {
            starter_ = _prog.getEntameur();
            trickWinner_ = _prog.getEntameur();
            g_.retrieveCalledPlayers(_prog);
        } else if (!g_.avecContrat()) {
            starter_ = g_.playerAfter((byte) _dealer);
            trickWinner_ = g_.getEntameur();
        } else if (g_.contrats() < g_.getNombreDeJoueurs()) {
            starter_ = g_.playerAfter((byte) _dealer);
            trickWinner_ = g_.getEntameur();
        } else if (g_.pasJeuApresPasse()) {
            //if existePreneur()
            starter_ = g_.getPreneur();
            trickWinner_ = g_.getPreneur();
        } else {
            starter_ = g_.playerAfter((byte) _dealer);
            trickWinner_ = g_.getEntameur();
        }
        g_.getAppele().removeDuplicates();
        if (g_.getAppele().size() > 1) {
            fail("Bad callee player");
        }
        for (int i: g_.getAppele()) {
            g_.getConfidence().get(i).set(g_.getPreneur(),true);
        }
        g_.setStarter(starter_);
        g_.setTrickWinner(trickWinner_);
        return g_;
    }

    private int getTaker(RulesTarot _g, int _dealer, CustList<BidTarot> _bids) {
        byte player_ = _g.getRepartition().getNextPlayer(_dealer);
        int taker_ = CustList.INDEX_NOT_FOUND_ELT;
        BidTarot bid_ = BidTarot.FOLD;
        for (BidTarot b: _bids) {
            if (b.strongerThan(bid_)) {
                taker_ = player_;
                bid_ = b;
            }
            player_ = _g.getRepartition().getNextPlayer(player_);
        }
        return taker_;
    }
    private GameTarotTrickInfo newGameTarotTrickInfo(GameTarot _g) {
        Numbers<Integer> handLengths_ = new Numbers<Integer>();
        int nombreCartesParJoueur_ = _g.getRegles().getRepartition().getNombreCartesParJoueur();
        int nbPl_ = _g.getRegles().getRepartition().getNombreJoueurs();
        for (int i = 0; i < nbPl_; i++) {
            handLengths_.add(nombreCartesParJoueur_);
        }
        handLengths_.add(_g.getRegles().getRepartition().getNombreCartesChien());
        int nbTr_ = _g.getTricks().size() - 1;
        for (int i = 0; i < nbPl_; i++) {
            handLengths_.set(i,handLengths_.get(i)-nbTr_);
        }
        for (int i: _g.getProgressingTrick().joueursAyantJoue((byte) nbPl_)) {
            handLengths_.set(i, handLengths_.get(i)-1);
        }
        return new GameTarotTrickInfo(_g.getProgressingTrick(), _g.getTricks(),
                _g.getDeclaresMiseres(),
                _g.getHandfuls(), _g.getContrat(), _g.getCalledCards(),
                handLengths_);
    }
}
