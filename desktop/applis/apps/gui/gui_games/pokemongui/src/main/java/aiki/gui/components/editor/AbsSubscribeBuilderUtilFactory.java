package aiki.gui.components.editor;

public interface AbsSubscribeBuilderUtilFactory<E,F> {
    AbsSubscribeBuilderUtil<E,F> build(SubscribedTranslationMessagesFactoryCoreMessages<E> _message);
}
