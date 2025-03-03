package com.epam.rd.autocode.iterator;

import java.util.*;

class Iterators {

    public static Iterator<Integer> intArrayTwoTimesIterator(int[] array){
        return new Iterator<Integer>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < array.length * 2;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                int value = array[index / 2];
                index++;
                return value;
            }
        };
    }

    public static Iterator<Integer> intArrayThreeTimesIterator(int[] array) {
        return new Iterator<Integer>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < array.length * 3;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                int value = array[index / 3];
                index++;
                return value;
            }
        };
    }

    public static Iterator<Integer> intArrayFiveTimesIterator(int[] array) {
        return new Iterator<Integer>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < array.length * 5;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                int value = array[index / 5];
                index++;
                return value;
            }
        };
        }

    public static Iterable<String> table(String[] columns, int[] rows){
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < columns.length; i++) {
            for (int j = 0; j < rows.length; j++) {
                list.add(columns[i]+rows[j]);
            }
        }
        return list;
    }
}

