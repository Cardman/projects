package code.expressionlanguage.opers.util;


public class FieldResult {

    private FieldInfo id;

    private SearchingMemberStatus status;

    private int anc;
    private int order;

    public FieldInfo getId() {
        return id;
    }

    public void setId(FieldInfo _id) {
        id = _id;
    }

    public SearchingMemberStatus getStatus() {
        return status;
    }

    public void setStatus(SearchingMemberStatus _status) {
        status = _status;
    }

    public int getAnc() {
        return anc;
    }

    public void setAnc(int _anc) {
        anc = _anc;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int _order) {
        order = _order;
    }
}
