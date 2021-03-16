package code.maths.litteraladv;

import code.maths.geo.Edge;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class MaListEdgeStruct implements MaAddonStruct {
    private final CustList<Edge> edges;

    public MaListEdgeStruct(CustList<Edge> _edges) {
        this.edges = _edges;
    }

    public CustList<Edge> getEdges() {
        return edges;
    }

    @Override
    public boolean sameReferenceMath(MaStruct _other) {
        if (!(_other instanceof MaListEdgeStruct)) {
            return false;
        }
        MaListEdgeStruct oth_ = (MaListEdgeStruct) _other;
        return Edge.eqEdgesMath(edges,oth_.edges);
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        if (!(_other instanceof MaListEdgeStruct)) {
            return false;
        }
        MaListEdgeStruct oth_ = (MaListEdgeStruct) _other;
        return eqEdges(edges,oth_.edges);
    }

    static boolean eqEdges(CustList<Edge> _this,CustList<Edge> _other) {
        int len_ = _this.size();
        if (_other.size() != len_) {
            return false;
        }
        for (int i = 0; i < len_; i++) {
            if (!MaEdgeStruct.eqEdge(_this.get(i),_other.get(i))) {
                return false;
            }
        }
        return true;
    }
    @Override
    public String displayRsult() {
        return displayRsult(edges);
    }

    static String displayRsult(CustList<Edge> _edges) {
        StringList edges_ = new StringList(new CollCapacity(_edges.size()));
        for (Edge e: _edges) {
            edges_.add(MaEdgeStruct.displayRsult(e));
        }
        return "("+ StringUtil.join(edges_,",")+")";
    }

}
