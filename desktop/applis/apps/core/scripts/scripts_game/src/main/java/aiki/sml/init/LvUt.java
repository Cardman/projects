package aiki.sml.init;
import aiki.util.*;
import aiki.map.levels.*;
public final class LvUt extends CstIgame{
private LvUt(){}
static Link newLink(String _lk, Coords _c){
return new Link(_lk,_c);
}
static Coords newCoords(int _p,int _l,Point _in, Point _pt){
 Coords m540c_ = new Coords();
 m540c_.setNumberPlace((short)_p);
 LevelPoint m540lp_ = new LevelPoint();
 m540lp_.setLevelIndex((byte)_l);
 m540c_.setInsideBuilding(_in);
 m540lp_.setPoint(_pt);
 m540c_.setLevel(m540lp_);
 return m540c_;
}
static Coords newCoords(int _p,int _l,Point _pt){
 Coords m541c_ = new Coords();
 m541c_.setNumberPlace((short)_p);
 LevelPoint m541lp_ = new LevelPoint();
 m541lp_.setLevelIndex((byte)_l);
 m541lp_.setPoint(_pt);
 m541c_.setLevel(m541lp_);
 return m541c_;
}
static LevelPoint newLevelPoint(int _l,Point _pt){
 LevelPoint m542lp_ = new LevelPoint();
 m542lp_.setLevelIndex((byte)_l);
 m542lp_.setPoint(_pt);
 return m542lp_;
}
}
