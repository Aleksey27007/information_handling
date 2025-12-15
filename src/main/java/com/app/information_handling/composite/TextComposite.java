package com.app.information_handling.composite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent{
    private static final Logger logger = LogManager.getLogger();

    private final TextComponentType type;
    private final List<TextComponent> components = new ArrayList<>();

    public TextComposite(TextComponentType type) {
        this.type = type;
    }

    @Override
    public void add(TextComponent textComponent) {
        logger.debug("Adding component " + textComponent);
        components.add(textComponent);
    }

    @Override
    public void remove(TextComponent textComponent) {
        logger.debug("Component removed " + textComponent);
        components.remove(textComponent);
    }

    @Override
    public List<TextComponent> getComponents() {
        return components;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (TextComponent component : components) {
            sb.append(component.toString());
            if (type == TextComponentType.SENTENCE) sb.append(" ");
            if (type == TextComponentType.PARAGRAPH) sb.append("\n");
        }
        return sb.toString().trim();
    }
}
