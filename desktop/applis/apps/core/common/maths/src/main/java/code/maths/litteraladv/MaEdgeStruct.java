package code.maths.litteraladv;

import code.maths.geo.Edge;

public final class MaEdgeStruct implements MaAddonStruct {
    private final Edge edge;

    public MaEdgeStruct(Edge _edge) {
        this.edge = _edge;
    }

    public Edge getEdge() {
        return edge;
    }

    @Override
    public boolean sameReferenceMath(MaStruct _other) {
        if (!(_other instanceof MaEdgeStruct)) {
            return false;
        }
        MaEdgeStruct oth_ = (MaEdgeStruct) _other;
        return edge.isEqual(oth_.edge);
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        if (!(_other instanceof MaEdgeStruct)) {
            return false;
        }
        MaEdgeStruct oth_ = (MaEdgeStruct) _other;
        return eqEdge(edge,oth_.edge);
    }

    static boolean eqEdge(Edge _this,Edge _oth) {
        if (!_this.getFirst().eq(_oth.getFirst())) {
            return false;
        }
        return _this.getSecond().eq(_oth.getSecond());
    }
    @Override
    public String displayRsult() {
        return displayRsult(edge);
    }

    static String displayRsult(Edge _edge) {
        return "(" + MaRatePointStruct.displayRsult(_edge.getFirst()) + "," + MaRatePointStruct.displayRsult(_edge.getSecond()) +","+MaOperationNode.EDGE+")";
    }

}
