package com.task.utils;

import java.awt.*;

public class SwingUtils {

    public static void deleteAll(Container container) {
        int d = getDepth(container, 0);
        for (int i = d - 1; i > 0; i--) {
            removeComponentsLevel(container, 0, i);
        }
        container.removeAll();
    }

    public static int getDepth(Component component, int curLevel) {
        int maxDeep = curLevel;
        if (component instanceof Container) {
            Container container = (Container) component;
            for (Component c : container.getComponents()) {
                int curDeep = getDepth(c, curLevel + 1);
                if (curDeep > maxDeep) {
                    maxDeep = curDeep;
                }
            }
        }
        return maxDeep;
    }

    public static void removeComponentsLevel(Component root, int rootLevel, int deleteLevel) {
        if (root instanceof Container) {
            Container rootContainer = (Container) root;
            for (Component c : rootContainer.getComponents()) {
                if (rootLevel == deleteLevel) {
                    rootContainer.remove(c);
                }
                removeComponentsLevel(c, rootLevel + 1, deleteLevel);
            }
        }
    }
}
