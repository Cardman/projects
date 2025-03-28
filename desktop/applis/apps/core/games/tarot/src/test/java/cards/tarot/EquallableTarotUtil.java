package cards.tarot;
import cards.consts.EndGameState;
import cards.consts.PossibleTrickWinner;
import cards.consts.Role;
import cards.tarot.enumerations.*;
import code.maths.Rate;
import code.maths.montecarlo.DefaultGenerator;
import code.util.*;
import code.util.core.BoolVal;
import org.junit.Assert;

import cards.consts.Suit;

public abstract class EquallableTarotUtil {


    public static void gererChienInconnuDirect(GameTarot _gt) {
        _gt.gererChienInconnu();
        _gt.firstLead();
    }

    public static void gererChienInconnuChelemDirect(GameTarot _gt) {
        _gt.gererChienInconnu();
        _gt.ajouterChelemUtilisateur();
        _gt.firstLead();
    }

    public static void intelligenceArtificielleAppel(GameTarot _gt) {
        _gt.intelligenceArtificielleAppel(new DefGameTarot());
    }
    protected static HandTarot create(CardTarot... _cards) {
        return HandTarot.create(_cards);
    }

    public static void initDonne(DealTarot _deal,RulesTarot _rules, HandTarot _ppile) {
        _deal.initDonne(_rules, DefaultGenerator.oneElt(), _ppile);
    }
    public static void assertNotNull(IdList<Handfuls> _value) {
        Assert.assertNotNull(_value);
    }

    public static void assertNotNull(IntMap<Integer> _value) {
        Assert.assertNotNull(_value);
    }

    public static void assertNotNull(String _value) {
        Assert.assertNotNull(_value);
    }

    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }
    public static void assertTrue(String _mess,boolean _value) {
        Assert.assertTrue(_mess,_value);
    }
    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }
    public static void assertSame(BoolVal _expected, BoolVal _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(ReasonDiscard _expected, ReasonDiscard _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(EndGameState _expected, EndGameState _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(PossibleTrickWinner _expected, PossibleTrickWinner _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(BidTarot _expected, BidTarot _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(Role _expected, Role _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(Suit _expected, Suit _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(CardTarot _expected, CardTarot _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(GameTarot _expected, GameTarot _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertNotSame(RulesTarot _expected, RulesTarot _result) {
        Assert.assertNotSame(_expected, _result);
    }

    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }
    public static void assertEq(long _expected, int _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(HandTarot _expected, HandTarot _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected.getCards().getList(),_result.getCards().getList());
    }
    public static void assertEq(Rate _expected, Rate _result) {
        Assert.assertNotNull(_result);
        Assert.assertTrue(_expected.eq(_result));
    }
    public static void assertEq(String _expected, String _result) {
        Assert.assertEquals(_expected,_result);
    }

    public static void assertEq(Suit _expected, Suit _result) {
        Assert.assertSame(_expected, _result);
    }

    public static void assertEq(CardTarot _expected, CardTarot _result) {
        Assert.assertSame(_expected, _result);
    }
    
    public static void assertEq(DealingTarot _expected, DealingTarot _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(EndDealTarot _expected, EndDealTarot _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(Miseres _expected, Miseres _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(Handfuls _expected, Handfuls _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(ModeTarot _expected, ModeTarot _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(BidTarot _expected, BidTarot _result) {
        Assert.assertSame(_expected, _result);
    }
    protected static CustList<CustList<BoolVal>> getConfi(BidTarot _b, RulesTarot _r, int _taker){
//        ModeTarot mode_ = _r.getMode();
//        boolean b_ = GameTarot.avContrat(_r);
//        if (mode_ == ModeTarot.NORMAL) {
//            b_ = true;
//        } else if (mode_ == ModeTarot.NORMAL_WITH_ONE_FOR_ONE) {
//            b_ = true;
//        } else if (mode_ == ModeTarot.NORMAL_WITH_MISERE) {
//            b_ = true;
//        }
        int nbPl_ = _r.getDealing().getId().getNombreJoueurs();
        CustList<CustList<BoolVal>> confidence_ = GameTarot.confidence(nbPl_);
//        if (!b_ || !_b.isJouerDonne()) {
//            GameTarot.confSansPreneur(_r,confidence_);
////            for (byte i = IndexConstants.FIRST_INDEX; i < nbPl_; i++) {
////                for (byte p: _r.getDealing().getAppelesDetermines(i)) {
////                    confidence_.get(i).set(p,BoolVal.TRUE);
////                }
////                confidence_.get(i).set(i,BoolVal.TRUE);
////            }
//        } else if (_r.getDealing().getAppel() == CallingCard.DEFINED) {
//            GameTarot.confDeterminee(_r,(byte)_taker,confidence_);
////            Bytes attaquants_= _r.getDealing().getAppelesDetermines((byte) _taker);
////            attaquants_.add((byte) _taker);
////            Bytes defenseurs_=GameTarotTeamsRelation.autresJoueurs(attaquants_, nbPl_);
////            for(byte j1_:attaquants_) {
////                for(byte j2_:attaquants_) {
////                    if(j1_==j2_) {
////                        continue;
////                    }
////                    confidence_.get(j1_).set(j2_,BoolVal.TRUE);
////                }
////            }
////            for(byte j1_:defenseurs_) {
////                for(byte j2_:defenseurs_) {
////                    if(j1_==j2_) {
////                        continue;
////                    }
////                    confidence_.get(j1_).set(j2_,BoolVal.TRUE);
////                }
////            }
//        } else if (_r.getDealing().getAppel() == CallingCard.WITHOUT) {
//            GameTarot.confDef(_r,(byte)_taker,confidence_);
////            Bytes defenseurs_=new Bytes();
////            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nbPl_; joueur_++) {
////                if(joueur_==_taker) {
////                    continue;
////                }
////                defenseurs_.add(joueur_);
////            }
////            for(byte j1_:defenseurs_) {
////                for(byte j2_:defenseurs_) {
////                    if(j1_==j2_) {
////                        continue;
////                    }
////                    confidence_.get(j1_).set(j2_,BoolVal.TRUE);
////                }
////            }
//        }
        GameTarot.defined(_b,_r,_taker,confidence_);
        return confidence_;
    }
    public static Ints handLengths4(GameTarot _g) {
        Ints handLengths_ = new Ints();
        int nombreCartesParJoueur_ = _g.getRegles().getDealing().getNombreCartesParJoueur();
        int nbPl_ = _g.getRegles().getDealing().getId().getNombreJoueurs();
        CustList<HandTarot> hands_ = _g.getProgressingTrick().completeCurrent(nbPl_, true);
        int nbTr_ = _g.getTricks().size() - 1;
        handLengths_.add(nombreCartesParJoueur_-nbTr_-hands_.get(0).total());
        handLengths_.add(nombreCartesParJoueur_-nbTr_-hands_.get(1).total());
        handLengths_.add(nombreCartesParJoueur_-nbTr_-hands_.get(2).total());
        handLengths_.add(nombreCartesParJoueur_-nbTr_-hands_.get(3).total());
        handLengths_.add(_g.getRegles().getDealing().getNombreCartesChien());
        return handLengths_;
    }
    public static Ints handLengths5(GameTarot _g) {
        Ints handLengths_ = new Ints();
        int nombreCartesParJoueur_ = _g.getRegles().getDealing().getNombreCartesParJoueur();
        int nbPl_ = _g.getRegles().getDealing().getId().getNombreJoueurs();
        CustList<HandTarot> hands_ = _g.getProgressingTrick().completeCurrent(nbPl_, true);
        int nbTr_ = _g.getTricks().size() - 1;
        handLengths_.add(nombreCartesParJoueur_-nbTr_-hands_.get(0).total());
        handLengths_.add(nombreCartesParJoueur_-nbTr_-hands_.get(1).total());
        handLengths_.add(nombreCartesParJoueur_-nbTr_-hands_.get(2).total());
        handLengths_.add(nombreCartesParJoueur_-nbTr_-hands_.get(3).total());
        handLengths_.add(nombreCartesParJoueur_-nbTr_-hands_.get(4).total());
        handLengths_.add(_g.getRegles().getDealing().getNombreCartesChien());
        return handLengths_;
    }
    public static Ints handLengths6(GameTarot _g) {
        Ints handLengths_ = new Ints();
        int nombreCartesParJoueur_ = _g.getRegles().getDealing().getNombreCartesParJoueur();
        int nbPl_ = _g.getRegles().getDealing().getId().getNombreJoueurs();
        CustList<HandTarot> hands_ = _g.getProgressingTrick().completeCurrent(nbPl_, true);
        int nbTr_ = _g.getTricks().size() - 1;
        handLengths_.add(nombreCartesParJoueur_-nbTr_-hands_.get(0).total());
        handLengths_.add(nombreCartesParJoueur_-nbTr_-hands_.get(1).total());
        handLengths_.add(nombreCartesParJoueur_-nbTr_-hands_.get(2).total());
        handLengths_.add(nombreCartesParJoueur_-nbTr_-hands_.get(3).total());
        handLengths_.add(nombreCartesParJoueur_-nbTr_-hands_.get(4).total());
        handLengths_.add(nombreCartesParJoueur_-nbTr_-hands_.get(5).total());
        handLengths_.add(_g.getRegles().getDealing().getNombreCartesChien());
        return handLengths_;
    }
    public static IdMap<Suit,CustList<HandTarot>> generate() {
        IdMap<Suit,CustList<HandTarot>> g_ = new IdMap<Suit,CustList<HandTarot>>();
        CustList<HandTarot> e_ = new CustList<HandTarot>();
        e_.add(new HandTarot());
        e_.add(new HandTarot());
        e_.add(new HandTarot());
        e_.add(new HandTarot());
        e_.add(new HandTarot());
        e_.add(new HandTarot());
        g_.addEntry(Suit.UNDEFINED,e_);
        CustList<HandTarot> t_ = new CustList<HandTarot>();
        t_.add(new HandTarot());
        t_.add(new HandTarot());
        t_.add(new HandTarot());
        t_.add(new HandTarot());
        t_.add(new HandTarot());
        t_.add(new HandTarot());
        g_.addEntry(Suit.TRUMP,t_);
        CustList<HandTarot> h_ = new CustList<HandTarot>();
        h_.add(new HandTarot());
        h_.add(new HandTarot());
        h_.add(new HandTarot());
        h_.add(new HandTarot());
        h_.add(new HandTarot());
        h_.add(new HandTarot());
        g_.addEntry(Suit.HEART,h_);
        CustList<HandTarot> s_ = new CustList<HandTarot>();
        s_.add(new HandTarot());
        s_.add(new HandTarot());
        s_.add(new HandTarot());
        s_.add(new HandTarot());
        s_.add(new HandTarot());
        s_.add(new HandTarot());
        g_.addEntry(Suit.SPADE,s_);
        CustList<HandTarot> d_ = new CustList<HandTarot>();
        d_.add(new HandTarot());
        d_.add(new HandTarot());
        d_.add(new HandTarot());
        d_.add(new HandTarot());
        d_.add(new HandTarot());
        d_.add(new HandTarot());
        g_.addEntry(Suit.DIAMOND,d_);
        CustList<HandTarot> c_ = new CustList<HandTarot>();
        c_.add(new HandTarot());
        c_.add(new HandTarot());
        c_.add(new HandTarot());
        c_.add(new HandTarot());
        c_.add(new HandTarot());
        c_.add(new HandTarot());
        g_.addEntry(Suit.CLUB,c_);
        return g_;
    }
}
