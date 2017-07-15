package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.MethodId;
import code.util.EqList;
import code.util.StringList;
import code.xml.RowCol;

public interface RootedBlock {

    String getFullName();

    String getName();

    String getSuperClass();

    String getPackageName();

    StringList getAllSuperClasses();

    StringList getDirectSuperClasses();

    RowCol getRowCol(int _offset, int _tabWidth,String _attribute);

    EqList<MethodId> getNormalMethods();

    Block getFirstChild();

    void validateConstructors(ContextEl _cont);
}
