package code.formathtml.classes;
import code.bean.Bean;
import code.util.CustList;
import code.util.Numbers;

public class BeanThree extends Bean {

    private Numbers<Integer> numbers = new Numbers<Integer>();

    private int index = CustList.INDEX_NOT_FOUND_ELT;

    private Numbers<Integer> numbersTwo = new Numbers<Integer>();

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

    public Numbers<Integer> getNumbers() {
        return numbers;
    }

    public void setIndex(int _index) {
        index = _index;
    }

    public int getIndexTwo() {
        return indexTwo;
    }

    public Numbers<Integer> getNumbersTwo() {
        return numbersTwo;
    }

    public void setIndexTwo(int _indexTwo) {
        indexTwo = _indexTwo;
    }

    public void setNumbers(Numbers<Integer> _numbers) {
        numbers = _numbers;
    }

    public void setNumbersTwo(Numbers<Integer> _numbersTwo) {
        numbersTwo = _numbersTwo;
    }
}
