package cards.tarot;
import cards.consts.EndGameState;
import cards.consts.PossibleTrickWinner;
import cards.consts.Role;
import cards.tarot.enumerations.*;
import cards.tarot.tsts.TstsTarot;
import code.maths.Rate;
import code.util.*;
import code.util.core.BoolVal;
import org.junit.Assert;

import cards.consts.Suit;

public abstract class EquallableTarotUtil {

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
        byte nbPl_ = (byte) _r.getDealing().getId().getNombreJoueurs();
        CustList<CustList<BoolVal>> confidence_ = TstsTarot.initConf(nbPl_);
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
        GameTarot.defined(_b,_r,(byte) _taker,confidence_);
        return confidence_;
    }
}
