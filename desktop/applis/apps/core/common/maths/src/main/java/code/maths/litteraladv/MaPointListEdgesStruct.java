package code.maths.litteraladv;

import code.maths.geo.Edge;
import code.maths.geo.RatePoint;
import code.util.CustList;

public final class MaPointListEdgesStruct extends MaPairPointStruct {
    private final CustList<Edge> edges;
    protected MaPointListEdgesStruct(RatePoint _point, CustList<Edge> _edges) {
        super(_point);
        edges = _edges;
    }

    public CustList<Edge> getEdges() {
        return edges;
    }

    @Override
    protected boolean sameValueMath(MaPairPointStruct _other) {
        if (!(_other instanceof MaPointListEdgesStruct)) {
            return false;
        }
        CustList<Edge> edgesOth_ = ((MaPointListEdgesStruct) _other).edges;
        return Edge.eqEdgesMath(edges,edgesOth_);
    }

    @Override
    protected boolean sameValue(MaPairPointStruct _other) {
        if (!(_other instanceof MaPointListEdgesStruct)) {
            return false;
        }
        CustList<Edge> edgesOth_ = ((MaPointListEdgesStruct) _other).edges;
        return MaListEdgeStruct.eqEdges(edges,edgesOth_);
    }

    @Override
    protected String displayValue() {
        return MaListEdgeStruct.displayRsult(edges);
    }
}
