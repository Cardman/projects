package code.formathtml.classes;
import code.bean.Bean;
import code.util.CustList;
import code.util.Ints;

public class BeanThree extends Bean {

    private Ints numbers = new Ints();

    private int index = CustList.INDEX_NOT_FOUND_ELT;

    private Ints numbersTwo = new Ints();

    private int indexTwo = CustList.INDEX_NOT_FOUND_ELT;

    public BeanThree() {
        setClassName("code.formathtml.classes.BeanThree");
    }

    @Override
    public void beforeDisplaying() {
        if (!numbers.isEmpty()) {
            return;
        }
        if (!numbersTwo.isEmpty()) {
            return;
        }
        numbers.add(2);
        numbers.add(4);
        numbers.add(6);
        numbersTwo.add(2);
        numbersTwo.add(4);
        numbersTwo.add(6);
    }

    public int getIndex() {
        return index;
    }

    public Ints getNumbers() {
        return numbers;
    }

    public void setIndex(int _index) {
        index = _index;
    }

    public int getIndexTwo() {
        return indexTwo;
    }

    public Ints getNumbersTwo() {
        return numbersTwo;
    }

    public void setIndexTwo(int _indexTwo) {
        indexTwo = _indexTwo;
    }

    public void setNumbers(Ints _numbers) {
        numbers = _numbers;
    }

    public void setNumbersTwo(Ints _numbersTwo) {
        numbersTwo = _numbersTwo;
    }
}
