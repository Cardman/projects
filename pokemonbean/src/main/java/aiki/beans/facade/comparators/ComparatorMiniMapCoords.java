package aiki.beans.facade.comparators;
import java.util.Comparator;

import aiki.map.util.MiniMapCoords;
import code.util.Numbers;

public final class ComparatorMiniMapCoords implements Comparator<MiniMapCoords> {

    @Override
    public int compare(MiniMapCoords _o1, MiniMapCoords _o2) {
        int res_ = Numbers.compare(_o1.getYcoords(), _o2.getYcoords());
        if (res_ != 0) {
            return res_;
        }
        return Numbers.compare(_o1.getXcoords(), _o2.getXcoords());
    }

}