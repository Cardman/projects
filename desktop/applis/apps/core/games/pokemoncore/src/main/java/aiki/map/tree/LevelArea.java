package aiki.map.tree;

import aiki.fight.pokemon.GenderName;
import aiki.map.levels.*;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.WildPk;
import aiki.map.tree.util.Dims;
import aiki.map.util.Limits;
import aiki.util.*;
import code.util.*;
import code.util.core.IndexConstants;

public class LevelArea {

    private Point leftTop;
    private PointEqList inacessiblePoints;
    private IdMap<Point, Dims> dimsBlocks;
    private IdMap<Point, Integer> indexes;
    private CustList<CustList<GenderName>> pokemon;
    private int height;
    private int width;

    public void initialize(Level _level) {
        Limits limits_ = _level.limits();
        leftTop = limits_.getTopLeft();
        height = limits_.getBottomRight().gety() - leftTop.gety() + 1;
        width = limits_.getBottomRight().getx() - leftTop.getx() + 1;
        inacessiblePoints = new PointEqList();
        indexes = new IdMap<Point, Integer>();
        dimsBlocks = new IdMap<Point, Dims>();
        for (EntryCust<Point,Block> e : _level.getBlocks().entryList()) {
            Block block_ = e.getValue();
            Point id_ = e.getKey();
            if (block_.getType() != EnvironmentType.NOTHING) {
                if (block_.getIndexApparition() != IndexConstants.INDEX_NOT_FOUND_ELT) {
                    indexes.addEntry(id_, block_.getIndexApparition());
                }
                dimsBlocks.addEntry(id_,
                        new Dims(block_.getWidth(), block_.getHeight()));
                continue;
            }
            int x_ = id_.getx();
            int y_ = id_.gety();
            int w_ = block_.getWidth();
            int h_ = block_.getHeight();
            int xMax_ = w_ + x_;
            int yMax_ = h_ + y_;
            for (int x = x_; x < xMax_; x++) {
                for (int y = y_; y < yMax_; y++) {
                    inacessiblePoints.add(new Point(x, y));
                }
            }
        }
        listPk(_level);
    }

    private void listPk(Level _level) {
        pokemon = new CustList<CustList<GenderName>>();
        if (_level instanceof LevelWithWildPokemon) {
            for (AbsAreaApparition a : ((LevelWithWildPokemon) _level)
                    .getWildPokemonAreas()) {
                CustList<GenderName> list_ = new CustList<GenderName>();
                for (WildPk p : a.getWildPokemon()) {
                    list_.add(new GenderName(p.getGender(), p.getName()));
                }
                for (WildPk p : a.getWildPokemonFishing()) {
                    list_.add(new GenderName(p.getGender(), p.getName()));
                }
                pokemon.add(list_);
            }
        }
    }

    public boolean isValid(Point _pt, boolean _accessible) {
        if (_accessible && inacessiblePoints.containsObj(_pt)) {
            return false;
        }
        if (_pt.getx() < leftTop.getx()) {
            return false;
        }
        if (_pt.gety() < leftTop.gety()) {
            return false;
        }
        if (_pt.getx() >= leftTop.getx() + width) {
            return false;
        }
        return _pt.gety() < leftTop.gety() + height;
    }

    public boolean isAccessible(Point _pt) {
        return !inacessiblePoints.containsObj(_pt);
    }

    public boolean allAccessible() {
        return inacessiblePoints.isEmpty();
    }

    public long size() {
        return (long)height * width;
    }

    public CustList<GenderName> getPokemon(Point _pt) {
        int index_ = getIndex(_pt);
        if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
            return new CustList<GenderName>();
        }
        return pokemon.get(index_);
    }

    public int getIndex(Point _pt) {
        for (EntryCust<Point, Dims> k : dimsBlocks.entryList()) {
            if (_pt.getx() >= k.getKey().getx() && _pt.gety() >= k.getKey().gety() && _pt.getx() < k.getKey().getx() + k.getValue().getWidth() && _pt.gety() < k.getKey().gety() + k.getValue().getHeight()) {
                int ind_ = indexOfEntry(k.getKey());
                if (indexes.isValidIndex(ind_)) {
                    return indexes.getValue(ind_);
                }
            }
            // _pt.getx() >= k.getx()
            // _pt.gety() >= k.gety()
            // _pt.getx() < k.getx() + width
            // _pt.gety() < k.gety() + height
        }
        return IndexConstants.INDEX_NOT_FOUND_ELT;
    }
    public int indexOfEntry(Point _pt) {
        int size_ = indexes.size();
        for (int i = 0; i < size_; i++) {
            if (_pt.eq(indexes.getKey(i))) {
                return i;
            }
        }
        return -1;
    }

    public Point getLeftTop() {
        return leftTop;
    }

    public PointEqList getInacessiblePoints() {
        return inacessiblePoints;
    }

    public IdMap<Point, Dims> getDimsBlocks() {
        return dimsBlocks;
    }

    public IdMap<Point, Integer> getIndexes() {
        return indexes;
    }

    public CustList<CustList<GenderName>> getPokemon() {
        return pokemon;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
