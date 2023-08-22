package main;

import main.exertions.IndexTooBigException;

public class EnerloIntList implements IntegerList
{
    private Integer[] mainArray;

    public EnerloIntList ()
    {
        mainArray = new Integer[8];
    }

    private void checkIndex(int index)
    {
        if(index >= mainArray.length)
            throw new IndexTooBigException("Слишком большое значение индекса");
    }

    @Override
    public int add(int item)
    {

        if(mainArray[mainArray.length-1] != null)
            mainArray = grow(mainArray);

        for(int i =0; i < mainArray.length; i++)
        {
            if(mainArray[i] == null)
            {
                mainArray[i] = item;
                return item;
            }
        }
        return Integer.MIN_VALUE;
    }

    @Override
    public int add(int index, int item)
    {
        checkIndex(index);
        int tempRes;

        if(mainArray[mainArray.length-1] != null)
            mainArray = grow(mainArray);


        for(int i = 0; i < mainArray.length; i++)
        {
            if(i == index)
            {
                tempRes = mainArray[i];
                mainArray[i] = item;
                for(int j = mainArray.length; j >=i;j--)
                {
                    if(i == j)
                    {
                        mainArray[j+1] = tempRes;
                        break;
                    }

                    if(mainArray[j] != null)
                        mainArray[j+1] = mainArray[j];
                }
                break;
            }
        }
        return item;
    }
    private Integer[] grow(Integer[] arr)
    {
        float multi = 1.5f;
        int newSize = (int) (arr.length * multi);
        Integer[] newArr = new Integer[newSize];

        for(int i = 0; i < newArr.length; i++)
        {
            if(i > arr.length)
                break;
            newArr[i] = arr[i];
        }
        return newArr;
    }

    @Override
    public int set(int index, int item)
    {
        checkIndex(index);

        mainArray[index] = item;
        return item;
    }


    @Override
    public int remove(int index)
    {
        checkIndex(index);
        return remove(mainArray[index]);
    }

    @Override
    public boolean contains (int item)
    {
        //sortSelection(mainArray);

        int min = 0;
        int max = mainArray.length - 1;
        quickSort(mainArray, min, max);

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == mainArray[mid]) {
                return true;
            }

            if (item < mainArray[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }
    private void sortSelection(Integer[] arr)
    {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex])
                {
                    minElementIndex = j;
                }
            }
            int tmp = arr[i];
            arr[i] = arr[minElementIndex];
            arr[minElementIndex] = tmp;
        }
    }

    public static void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private static void swapElements(Integer[] arr, int begin, int end)
    {
        int tmp = arr[begin];
        arr[begin] = arr[end];
        arr[end] = tmp;
    }

    @Override
    public int indexOf (int item)
    {
        for(int i = 0; i < mainArray.length; i++)
        {
            if(mainArray[i] == item)
                return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf (int item)
    {
        for(int i = mainArray.length-1;i >=0;i--)
        {
            if(mainArray[i] == item)
                return i;
        }
        return -1;
    }

    @Override
    public int get(int index)
    {
        checkIndex(index);
        return mainArray[index];
    }

    @Override
    public boolean equals(IntegerList otherList)
    {
        if(mainArray.length != otherList.size())
            return false;
        for(int i = 0; i < mainArray.length; i++)
        {
            if(!(mainArray[i] == otherList.get(i)))
                return false;
        }
        return true;
    }

    @Override
    public int size() {
        return mainArray.length;
    }

    @Override
    public boolean isEmpty()
    {
        return mainArray.length == 0;
    }

    @Override
    public void clear()
    {
        mainArray = new Integer[0];
    }

    @Override
    public Integer[] toArray() {
        return mainArray;
    }
}
