package com.app.information_handling.composite;

import com.app.information_handling.composite.TextComponent;
import com.app.information_handling.composite.TextComponentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {
    private static final Logger logger = LogManager.getLogger();
    private final TextComponentType type;
    private final List<TextComponent> components = new ArrayList<>();

    public TextComposite(TextComponentType type) {
        this.type = type;
    }

    @Override
    public void add(TextComponent component) {
        logger.debug("Adding component " + component);
        components.add(component);
    }

    @Override
    public void remove(TextComponent component) {
        logger.debug("Component removed " + component);
        components.remove(component);
    }

    @Override
    public List<TextComponent> getComponents() {
        return components;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (TextComponent component : components) {
            builder.append(component.toString());
            if (type == TextComponentType.SENTENCE) builder.append(" ");
            if (type == TextComponentType.PARAGRAPH) builder.append("\n");
        }
        return builder.toString().trim();
    }
}
