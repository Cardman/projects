package code.maths.litteraladv;

import code.maths.geo.Edge;

public final class MaEdgeStruct implements MaStruct {
    private final Edge edge;

    public MaEdgeStruct(Edge _edge) {
        this.edge = _edge;
    }

    public Edge getEdge() {
        return edge;
    }

    @Override
    public boolean sameReference(MaStruct _other) {
        if (!(_other instanceof MaEdgeStruct)) {
            return false;
        }
        MaEdgeStruct oth_ = (MaEdgeStruct) _other;
        if (!edge.getFirst().eq(oth_.edge.getFirst())) {
            return false;
        }
        return edge.getSecond().eq(oth_.edge.getSecond());
    }

    @Override
    public String displayRsult() {
        return "("+MaRatePointStruct.displayRsult(edge.getFirst())+","+MaRatePointStruct.displayRsult(edge.getSecond())+",-)";
    }

}
