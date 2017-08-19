package code.expressionlanguage.methods;
import code.expressionlanguage.opers.util.FctConstraints;
import code.util.ObjectMap;
import code.util.StringList;
import code.xml.RowCol;

public interface RootedBlock extends AccessibleBlock {

    String getFullName();

    String getName();

    String getPackageName();

    StringList getAllSuperClasses();

    StringList getAllSuperTypes();

    StringList getDirectSuperClasses();

    RowCol getRowCol(int _offset, int _tabWidth,String _attribute);

    Block getFirstChild();

    boolean isFinalType();
    boolean isAbstractType();

    StringList getAllInterfaces();

    ObjectMap<FctConstraints, String> getDefaultMethods();

    boolean mustImplement();
}
