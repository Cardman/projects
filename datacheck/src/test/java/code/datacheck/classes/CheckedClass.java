package code.datacheck.classes;
import code.serialize.CheckedData;

@CheckedData
public class CheckedClass {

    private transient String pi;

    private String ok;

    private int correct;

    public String getPi() {
        return pi;
    }

    public void setPi(String _pi) {
        pi = _pi;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String _ok) {
        ok = _ok;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int _correct) {
        correct = _correct;
    }
}
