package cards.gui.dialogs.help;
import code.util.CustList;
import code.util.core.IndexConstants;
import code.util.ints.Displayable;

public class NodeHelp implements Displayable {
    private CustList<NodeHelp> elements = new CustList<NodeHelp>();
    private ElementHelp elementLocal;

    public NodeHelp(ElementHelp _element) {
        elementLocal = _element;
    }

    public String nom() {
        return getElementLocal().nom();
    }

    public String getFile() {
        return getElementLocal().getFile();
    }

    public void ajouterInfo(NodeHelp _element) {
        elements.add(_element);
    }

    private NodeHelp elementLoc(int _indice) {
        return elements.get(_indice);
    }

    public NodeHelp element(CustList<Integer> _indices) {
        NodeHelp element_ = this;
        int pathLength_ = _indices.size();
        for (int indice_ = 0; indice_ < pathLength_; indice_++) {
            element_ = element_.elementLoc(_indices.get(indice_));
        }
        return element_;
    }

    @Override
    public String display() {
        return elementLocal.nom();
    }

    public ElementHelp getElementLocal() {
        return elementLocal;
    }
}
