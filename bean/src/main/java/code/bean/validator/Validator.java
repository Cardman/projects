package code.bean.validator;
import org.w3c.dom.Node;

public interface Validator {

    Message validate(Object _navigation, Node _node, Object _value);
}
