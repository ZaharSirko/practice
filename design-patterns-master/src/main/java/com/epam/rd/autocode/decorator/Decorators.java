package com.epam.rd.autocode.decorator;

import java.util.ArrayList;
import java.util.List;

public class Decorators {
    public static List<String> evenIndexElementsSubList(List<String> sourceList) {
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < sourceList.size();  i += 2) {
            result.add(sourceList.get(i));
        }
        return result;
    }
}
