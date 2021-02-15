package com.tusset.xbox.util;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.HashMap;

/**
 *
 * @author mtusset
 */
public class Util {

    public static HashMap<String, Double> getDimensionScreen() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        HashMap<String, Double> hash = new HashMap<>();
        hash.put("width", size.getWidth());
        hash.put("height", size.getHeight());
        return hash;
    }
}
