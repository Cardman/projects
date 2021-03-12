package code.maths.geo;
import code.util.CustList;

public interface HasEdges {

    CustList<RatePoint> getPoints();

    CustList<Edge> getEdges();
}
