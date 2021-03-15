package code.maths.litteraladv;

import code.maths.geo.Edge;
import code.maths.geo.RatePoint;
import code.util.*;
import code.util.core.StringUtil;

public final class MaMapPointEdgeStruct implements MaStruct {
    private final IdMap<RatePoint,CustList<Edge>> edges;

    public MaMapPointEdgeStruct(IdMap<RatePoint,CustList<Edge>> _edges) {
        this.edges = _edges;
    }

    public CustList<Edge> getEdges(RatePoint _point) {
        CustList<Edge> edges_ = new CustList<Edge>();
        for (EntryCust<RatePoint,CustList<Edge>> e: edges.entryList()) {
            if (_point.eq(e.getKey())) {
                edges_.addAllElts(e.getValue());
            }
        }
        return edges_;
    }

    public IdMap<RatePoint,CustList<Edge>> getEdges() {
        return edges;
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        if (!(_other instanceof MaMapPointEdgeStruct)) {
            return false;
        }
        MaMapPointEdgeStruct oth_ = (MaMapPointEdgeStruct) _other;
        return eqEdges(edges,oth_.edges);
    }

    static boolean eqEdges(IdMap<RatePoint,CustList<Edge>> _this,IdMap<RatePoint,CustList<Edge>> _other) {
        int len_ = _this.size();
        if (_other.size() != len_) {
            return false;
        }
        for (int i = 0; i < len_; i++) {
            if (!_this.getKey(i).eq(_other.getKey(i))) {
                return false;
            }
            if (!MaListEdgeStruct.eqEdges(_this.getValue(i),_other.getValue(i))) {
                return false;
            }
        }
        return true;
    }
    @Override
    public String displayRsult() {
        return displayRsult(edges);
    }

    static String displayRsult(IdMap<RatePoint,CustList<Edge>> _edges) {
        StringList edges_ = new StringList(new CollCapacity(_edges.size()));
        for (EntryCust<RatePoint,CustList<Edge>> e: _edges.entryList()) {
            edges_.add(MaRatePointStruct.displayRsult(e.getKey())+":"+MaListEdgeStruct.displayRsult(e.getValue()));
        }
        return "("+ StringUtil.join(edges_,",")+")";
    }

}
