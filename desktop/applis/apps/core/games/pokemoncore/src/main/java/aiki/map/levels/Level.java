package aiki.map.levels;

import aiki.db.DataBase;
import aiki.map.buildings.Building;
import aiki.map.characters.DualFight;
import aiki.map.characters.Person;
import aiki.map.characters.TempTrainer;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.Cave;
import aiki.map.places.City;
import aiki.map.places.InitializedPlace;
import aiki.map.places.League;
import aiki.map.places.Place;
import aiki.map.pokemon.WildPk;
import aiki.map.tree.LevelArea;
import aiki.map.util.Limits;
import aiki.map.util.ScreenCoords;
import aiki.util.*;
import code.images.BaseSixtyFourUtil;
import code.util.CustList;

import code.util.core.IndexConstants;
import code.util.core.NumberUtil;


public abstract class Level {

    /**
     * Left top for key, rectangle block for value that is an obstacle or an
     * area of wild pokemon.
     */
    private Points<Block> blocks;

    /**
     * @param _data
     */
    public void validate(DataBase _data, LevelArea _level) {
        long sum_ = 0;
        PointEqList used_ = new PointEqList();
        for (CommonParam<Point,Block> e : blocks.entryList()) {
            if (!e.getValue().isValid()) {
                _data.setError(true);
                continue;
            }
            Point id_ = e.getKey();
            Block block_ = e.getValue();
            int x_ = id_.getx();
            int y_ = id_.gety();
            int w_ = block_.getWidth();
            int h_ = block_.getHeight();
            int xMax_ = x_ + w_;
            int yMax_ = y_ + h_;
            for (int x = x_; x < xMax_; x++) {
                for (int y = y_; y < yMax_; y++) {
                    Point pt_ = new Point((short) x, (short) y);
                    if (used_.containsObj(pt_)) {
                        _data.setError(true);
                    }
                    used_.add(pt_);
                }
            }
            sum_ += h_ * w_;
        }
        if (sum_ != _level.size()) {
            _data.setError(true);
        }
    }


    public boolean hasValidImage(DataBase _data) {
        for (CommonParam<Point,Block> e : blocks.entryList()) {
            if (!e.getValue().hasValidImage(_data)) {
                return false;
            }
        }
        return true;
    }

    public static Points< int[][]> getLevelBackgroundImage(
            DataBase _data, Coords _coords) {
        int sideLen_ = _data.getMap().getSideLength();
        Place pl_ = _data.getMap().getPlace(_coords.getNumberPlace());
        Level lev_ = pl_.getLevelByCoords(_coords);
        Points< int[][]> tiles_ = new PointsArr();
        for (Point p : lev_.getBlocks().getKeys()) {
            Block bl_ = lev_.getBlocks().getVal(p);
            int[][] image_ = _data.getImage(bl_.getTileFileName());
            short w_ = bl_.getWidth();
            short h_ = bl_.getHeight();
            for (short x = IndexConstants.FIRST_INDEX; x < w_; x++) {
                for (short y = IndexConstants.FIRST_INDEX; y < h_; y++) {
                    int[][] img_ = BaseSixtyFourUtil.clipSixtyFour(image_, x * sideLen_, y
                            * sideLen_, sideLen_, sideLen_);
                    tiles_.put(
                            new Point((short) (x + p.getx()), (short) (y + p
                                    .gety())), img_);
                }
            }
        }
        return tiles_;
    }

    public static Points< int[][]> getLevelForegroundImage(
            DataBase _data, Coords _coords) {
        Place pl_ = _data.getMap().getPlace(_coords.getNumberPlace());
        Level lev_ = pl_.getLevelByCoords(_coords);
        Points< int[][]> frontTiles_ = new PointsArr();
        feedLeague(_data, _coords, frontTiles_);
        if (lev_ instanceof LevelLeague) {
            LevelLeague lv_ = (LevelLeague) lev_;
            Person person_ = lv_.getTrainer();
            frontTiles_.put(new Point(lv_.getTrainerCoords()),
                    _data.getPerson(person_.getImageMiniFileName()));
            frontTiles_.put(new Point(lv_.getAccessPoint()),
                    _data.getLink(lv_.getFileName()));
        }
        feedLevelWithWildPokemon(_data, lev_, frontTiles_);
        feedCity(_data, _coords, pl_, lev_, frontTiles_);
        feedCave(_data, _coords, pl_, frontTiles_);
        if (pl_ instanceof InitializedPlace) {
            InitializedPlace init_ = (InitializedPlace) pl_;
            for (Point p : init_.getLinksWithCaves().getKeys()) {
                Link link_ = init_.getLinksWithCaves().getVal(p);
                frontTiles_.put(new Point(p),
                        _data.getLink(link_.getFileName()));
            }
        }
        return frontTiles_;
    }

    private static void feedCity(DataBase _data, Coords _coords, Place _pl, Level _lev, Points<int[][]> _frontTiles) {
        if (_pl instanceof City&& _coords.isInside()) {
            City city_ = (City) _pl;
            Building building_ = city_.getBuildings().getVal(
                    _coords.getInsideBuilding());
            _frontTiles.put(new Point(building_.getExitCity()),
                    _data.getLink(building_.getImageFileName()));
        }
        if (_lev instanceof LevelIndoorPokemonCenter) {
            LevelIndoorPokemonCenter lv_ = (LevelIndoorPokemonCenter) _lev;
            for (Point p : lv_.getGerants().getKeys()) {
                Person person_ = lv_.getGerants().getVal(p);
                _frontTiles.put(new Point(p),
                        _data.getPerson(person_.getImageMiniFileName()));
            }
            _frontTiles.put(new Point(lv_.getStorageCoords()),
                    _data.getStorage());
        }
        if (_lev instanceof LevelIndoorGym) {
            LevelIndoorGym lv_ = (LevelIndoorGym) _lev;
            for (Point p : lv_.getGymTrainers().getKeys()) {
                Person person_ = lv_.getGymTrainers().getVal(p);
                _frontTiles.put(new Point(p),
                        _data.getPerson(person_.getImageMiniFileName()));
            }
            Person person_ = lv_.getGymLeader();
            _frontTiles.put(new Point(lv_.getGymLeaderCoords()),
                    _data.getPerson(person_.getImageMiniFileName()));
        }
    }

    private static void feedCave(DataBase _data, Coords _coords, Place _pl, Points<int[][]> _frontTiles) {
        if (_pl instanceof Cave) {
            Cave cave_ = (Cave) _pl;
            for (LevelPoint lp_ : cave_.getLinksWithOtherPlaces().getKeys()) {
                if (!NumberUtil.eq(lp_.getLevelIndex(), _coords.getLevel()
                        .getLevelIndex())) {
                    continue;
                }
                Link link_ = cave_.getLinksWithOtherPlaces().getVal(lp_);
                _frontTiles.put(new Point(lp_.getPoint()),
                        _data.getLink(link_.getFileName()));
            }
            LevelCave lv_ = cave_.getLevelCave(_coords.getLevel());
            for (Point p : lv_.getLinksOtherLevels().getKeys()) {
                Link link_ = lv_.getLinksOtherLevels().getVal(p);
                _frontTiles.put(new Point(p),
                        _data.getLink(link_.getFileName()));
            }
        }
    }

    private static void feedLeague(DataBase _data, Coords _coords, Points<int[][]> _frontTiles) {
        Coords curCoords_ = new Coords(_coords);
        curCoords_.getLevel().setPoint(new Point((short)0,(short)0));
        for (Place p : _data.getMap().getPlaces()) {
            if (!(p instanceof League)) {
                continue;
            }
            Coords access_ = ((League) p).getAccessCoords();
            Coords accessCoords_ = new Coords(access_);
            accessCoords_.getLevel().setPoint(new Point((short)0,(short)0));
            if (Coords.eq(accessCoords_, curCoords_)) {
                Point pt_ = access_.getLevel().getPoint();
                _frontTiles.put(new Point(pt_),
                        _data.getLink(((League) p).getFileName()));
            }
        }
    }

    private static void feedLevelWithWildPokemon(DataBase _data, Level _lev, Points<int[][]> _frontTiles) {
        if (_lev instanceof LevelWithWildPokemon) {
            LevelWithWildPokemon lv_ = (LevelWithWildPokemon) _lev;
            for (Point p : lv_.getCharacters().getKeys()) {
                Person person_ = lv_.getPerson(p);
                _frontTiles.put(new Point(p),
                        _data.getPerson(person_.getImageMiniFileName()));
            }
            for (Point p : lv_.getLegendaryPks().getKeys()) {
                WildPk pk_ = lv_.getPokemon(p);
                _frontTiles.put(new Point(p),
                        _data.getMiniPk().getVal(pk_.getName()));
            }
            for (Point p : lv_.getItems().getKeys()) {
                _frontTiles.put(new Point(p),
                        _data.getMiniItems().getVal(lv_.getItems().getVal(p)));
            }
            for (Point p : lv_.getTm().getKeys()) {
                _frontTiles.put(new Point(p), _data.getImageTmHm());
            }
            for (Point p : lv_.getHm().getKeys()) {
                _frontTiles.put(new Point(p), _data.getImageTmHm());
            }
            for (Point p : lv_.getDualFights().getKeys()) {
                DualFight dual_ = lv_.getDualFights().getVal(p);
                TempTrainer tmp_ = dual_.getFoeTrainer();
                _frontTiles.put(new Point(p),
                        _data.getPerson(tmp_.getImageMiniFileName()));
            }
            for (DualFight d : lv_.getDualFights().values()) {
                TempTrainer tmp_ = d.getFoeTrainer();
                _frontTiles.put(new Point(d.getPt()), _data.getPerson(tmp_
                        .getImageMiniSecondTrainerFileName()));
            }
        }
    }

    public static void translateShortLineData(Points< Short> _data,
            short _y, short _dir) {
        CustList<Point> links_ = _data.getKeys();
        Points< Point> deplLinks_ = new PointsPoint();
        for (Point c : links_) {
            if (c.gety() < _y) {
                deplLinks_.put(c, c);
                continue;
            }
            deplLinks_.put(c, new Point(c.getx(), (short) (c.gety() + _dir)));
        }
        translate(_data, links_, deplLinks_);
    }

    public static void translateShortColumnData(Points< Short> _data,
            short _x, short _dir) {
        CustList<Point> links_ = _data.getKeys();
        Points< Point> deplLinks_ = new PointsPoint();
        for (Point c : links_) {
            if (c.getx() < _x) {
                deplLinks_.put(c, c);
                continue;
            }
            deplLinks_.put(c, new Point((short) (c.getx() + _dir), c.gety()));
        }
        translate(_data, links_, deplLinks_);
    }

    private static void translate(Points<Short> _data, CustList<Point> _links, Points<Point> _deplLinks) {
        CustList<Point> links_ = _links;
        while (!links_.isEmpty()) {
            for (Point c : links_) {
                Point dest_ = _deplLinks.getVal(c);
                if (!containsPt(links_,dest_)) {
                    Short movedBlock_ = _data.getVal(c);
                    _data.removeKey(c);
                    _data.put(dest_, movedBlock_);
                    _deplLinks.removeKey(c);
                }
            }
            for (Point c : links_) {
                if (!_deplLinks.contains(c)) {
                    continue;
                }
                Point dest_ = _deplLinks.getVal(c);
                if (Point.eq(c, dest_)) {
                    _deplLinks.removeKey(c);
                }
            }
            links_ = _deplLinks.getKeys();
        }
    }

    public abstract boolean isEmptyForAdding(Point _point);

    public abstract boolean isEmpty(Point _point);

    public ScreenCoords getScreenCoordsByPoint(Point _point) {
        ScreenCoords out_ = new ScreenCoords();
        for (Point i : blocks.getKeys()) {
            Block block_ = blocks.getVal(i);
            if (inRangeBlock(_point, i, block_)) {
                out_.setXcoords(_point.getx() - i.getx());
                out_.setYcoords(_point.gety() - i.gety());
                return out_;
            }
        }
        out_.setXcoords(-1);
        out_.setYcoords(-1);
        return out_;
    }

    public Block getEnvBlockByPoint(Point _point) {
        Block bl_ = getBlockByPoint(_point);
        if (bl_.getType() == EnvironmentType.NOTHING) {
            return new Block();
        }
        return bl_;
    }

    public Block getBlockByPoint(Point _point) {
        for (Point i : blocks.getKeys()) {
            Block block_ = blocks.getVal(i);
            if (inRangeBlock(_point, i, block_)) {
                return block_;
            }
        }
        return new Block();
    }

    private boolean inRangeBlock(Point _point, Point _i, Block _block) {
        short w_ = _block.getWidth();
        short h_ = _block.getHeight();
        short xi_ = _i.getx();
        short yi_ = _i.gety();
        int xr_ = xi_ + w_;
        int yb_ = yi_ + h_;
        short xp_ = _point.getx();
        short yp_ = _point.gety();
        return inRange(xi_, xr_, xp_) && inRange(yi_, yb_, yp_);
    }

    public static boolean inRange(short _min, int _max, short _xp) {
        return _xp >= _min && _xp < _max;
    }
    public Limits limits() {
        Point leftTopPoint_ = new Point((short) 0, (short) 0);
        Point rightBottomPoint_ = new Point((short) 0, (short) 0);
        CustList<Point> cles_ = blocks.getKeys();
        if (!cles_.isEmpty()) {
            Point p_ = cles_.first();
            leftTopPoint_.affect(p_);
            rightBottomPoint_.affect(p_);
        }
        for (Point p : cles_) {
            if (p.getx() < leftTopPoint_.getx()) {
                leftTopPoint_.setx(p.getx());
            }
            if (p.gety() < leftTopPoint_.gety()) {
                leftTopPoint_.sety(p.gety());
            }
            Block block_ = blocks.getVal(p);
            short tmp_ = (short) (p.getx() + block_.getWidth() - 1);
            if (tmp_ > rightBottomPoint_.getx()) {
                rightBottomPoint_.setx(tmp_);
            }
            tmp_ = (short) (p.gety() + block_.getHeight() - 1);
            if (tmp_ > rightBottomPoint_.gety()) {
                rightBottomPoint_.sety(tmp_);
            }
        }
        return new Limits(leftTopPoint_, rightBottomPoint_);
    }

    public Points<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(Points<Block> _blocks) {
        blocks = _blocks;
    }

    private static boolean containsPt(CustList<Point> _l, Point _pt) {
        for (Point p: _l) {
            if (_pt.eq(p)) {
                return true;
            }
        }
        return false;
    }
}
