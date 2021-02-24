package aiki.sml.init;
import aiki.instances.*;
import aiki.fight.util.*;
import aiki.util.*;
import aiki.map.enums.*;
import aiki.fight.*;
import aiki.map.levels.*;
public final class LvUt{
private LvUt(){}
static Link newLink(String _lk,Coords _c,Direction _dir){
 Link lk_ = new Link(_lk,_c);
 lk_.setDir(_dir);
 return lk_;
}
static Coords newCoords(int _p,int _l,Point _in, Point _pt){
 Coords c_ = new Coords();
 c_.setNumberPlace((short)_p);
 LevelPoint lp_ = new LevelPoint();
 lp_.setLevelIndex((byte)_l);
 c_.setInsideBuilding(_in);
 lp_.setPoint(_pt);
 c_.setLevel(lp_);
 return c_;
}
static Coords newCoords(int _p,int _l,Point _pt){
 Coords c_ = new Coords();
 c_.setNumberPlace((short)_p);
 LevelPoint lp_ = new LevelPoint();
 lp_.setLevelIndex((byte)_l);
 lp_.setPoint(_pt);
 c_.setLevel(lp_);
 return c_;
}
static LevelPoint newLevelPoint(int _l,Point _pt){
 LevelPoint lp_ = new LevelPoint();
 lp_.setLevelIndex((byte)_l);
 lp_.setPoint(_pt);
 return lp_;
}
}
