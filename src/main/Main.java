package main;

import java.util.Random;

public class Main {
    public static void main(String[] args)
    {
        EnerloIntList list1 = new EnerloIntList();
        EnerloIntList list2 = new EnerloIntList();
        EnerloIntList list3 = new EnerloIntList();
        Random rnd = new Random();
        for(int i = 0; i < 10_000; i ++)
        {
            list1.add(rnd.nextInt(25_000));
            list2.add(rnd.nextInt(25_000));
            list3.add(rnd.nextInt(25_000));
        }

        long start = System.currentTimeMillis();
        sortBubble(list1);
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        sortSelection(list2);
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        sortInsertion(list3);
        System.out.println(System.currentTimeMillis() - start);
    }

    private static void sortBubble(EnerloIntList arr) {
        for (int i = 0; i < arr.size() - 1; i++) {
            for (int j = 0; j < arr.size() - 1 - i; j++) {
                if (arr.get(j) > arr.get(j + 1))
                {
                    int tmp = arr.get(j);
                    arr.set(j,arr.get(j+1));
                    arr.set(j+1, tmp);
                }
            }
        }
    }

    private static void sortSelection(EnerloIntList arr)
    {
        for (int i = 0; i < arr.size() - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.size(); j++) {
                if (arr.get(j) < arr.get(minElementIndex))
                {
                    minElementIndex = j;
                }
            }
            int tmp = arr.get(i);
            arr.set(i,arr.get(minElementIndex));
            arr.set(minElementIndex, tmp);
        }
    }
    private static void sortInsertion(EnerloIntList arr) {
        for (int i = 1; i < arr.size(); i++) {
            int temp = arr.get(i);
            int j = i;
            while (j > 0 && arr.get(j - 1) >= temp) {
                arr.set(j,arr.get(j - 1));
                j--;
            }
            arr.set(j,temp);
        }
    }
}