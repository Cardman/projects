package aiki.gui.components.editor;

public interface AbsSubscribeBuilderUtilFactory<E,F> {
    AbsSubscribeBuilderUtil<E,F> build(SubscribedTranslationMessagesFactoryCore<E> _message);
}
