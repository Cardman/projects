package code.maths.geo;
import code.util.CustList;

public interface HasEdges {

    CustList<CustPoint> getPoints();

    CustList<Edge> getEdges();
}
