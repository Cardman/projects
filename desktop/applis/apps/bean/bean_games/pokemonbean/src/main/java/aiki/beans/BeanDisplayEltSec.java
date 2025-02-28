package aiki.beans;

public interface BeanDisplayEltSec<T> {
    int displayEltSecHeader(CommonBean _rend, T _info);
    int displayEltSec(CommonBean _rend, T _info);
}
