package code.expressionlanguage.opers.util;
import code.expressionlanguage.methods.Classes;
import code.util.CustList;
import code.util.ints.Listable;

public final class ArgumentsGroup extends CustList<ClassArgumentMatching> {

//    public ArgumentsGroup() {
//    }

//    public ArgumentsGroup(ClassArgumentMatching _element) {
//        super(_element);
//    }

//    public ArgumentsGroup(ClassArgumentMatching _element, ClassArgumentMatching _elementTwo,
//            ClassArgumentMatching... _elements) {
//        super(_element, _elementTwo, _elements);
//    }

    private Classes classes;

    public ArgumentsGroup(Classes _classes, ClassArgumentMatching[] _elements) {
        super(_elements);
        classes = _classes;
    }

    public ArgumentsGroup(Classes _classes, Listable<? extends ClassArgumentMatching> _c) {
        super(_c);
        classes = _classes;
    }

    public Classes getClasses() {
        return classes;
    }
}
