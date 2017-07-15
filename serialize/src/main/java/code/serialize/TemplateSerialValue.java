package code.serialize;

final class TemplateSerialValue<V> {

    private final TemplateSerial template;

    private final V value;

    public TemplateSerialValue(TemplateSerial _template, V _value) {
        template = _template;
        value = _value;
    }

    public TemplateSerial getTemplate() {
        return template;
    }

    public V getValue() {
        return value;
    }
}
