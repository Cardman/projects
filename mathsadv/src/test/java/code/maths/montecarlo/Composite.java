package code.maths.montecarlo;
import code.maths.montecarlo.MonteCarloString;
import code.util.CustList;
import code.util.Numbers;
import code.util.annot.RwXml;

@RwXml
public class Composite {

    private MonteCarloString law;

    private int primitiveField;

    private int[] array;

    private Numbers<Integer> numbers;

    private CustList<MonteCarloString> laws;

    public MonteCarloString getLaw() {
        return law;
    }

    public void setLaw(MonteCarloString _law) {
        law = _law;
    }

    public int getPrimitiveField() {
        return primitiveField;
    }

    public void setPrimitiveField(int _primitiveField) {
        primitiveField = _primitiveField;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] _array) {
        array = _array;
    }

    public Numbers<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(Numbers<Integer> _numbers) {
        numbers = _numbers;
    }

    public CustList<MonteCarloString> getLaws() {
        return laws;
    }

    public void setLaws(CustList<MonteCarloString> _laws) {
        laws = _laws;
    }
}
