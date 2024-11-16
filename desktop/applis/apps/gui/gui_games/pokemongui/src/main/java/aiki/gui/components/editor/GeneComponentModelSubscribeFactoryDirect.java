package aiki.gui.components.editor;

public final class GeneComponentModelSubscribeFactoryDirect<T> implements AbsGeneComponentModelSubscribeFactory<T> {

    private final AbsGeneComponentModelSubscribe<T> crud;

    public GeneComponentModelSubscribeFactoryDirect(AbsGeneComponentModelSubscribe<T> _c) {
        crud = _c;
    }

    @Override
    public AbsGeneComponentModelSubscribe<T> build() {
        return crud;
    }
}
