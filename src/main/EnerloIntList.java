package main;

import main.exertions.IndexTooBigException;

public class EnerloIntList implements IntegerList
{
    private Integer[] mainArray;

    public EnerloIntList ()
    {
        mainArray = new Integer[0];
    }

    private void checkIndex(int index)
    {
        if(index >= mainArray.length)
            throw new IndexTooBigException("Слишком большое значение индекса");
    }

    @Override
    public int add(int item)
    {
        Integer[] newArray = new Integer[mainArray.length +1];
        for(int i =0; i < newArray.length; i++)
        {
            if(i == mainArray.length)
            {
                newArray[i] = item;
                mainArray = newArray;
                return item;
            }
            newArray[i] = mainArray[i];
        }
        return Integer.MIN_VALUE;
    }

    @Override
    public int add(int index, int item)
    {
        checkIndex(index);

        Integer[] newArray = new Integer[mainArray.length+1];
        boolean checkNewElement = false;
        for(int i = 0; i < newArray.length; i++)
        {
            if(i == index)
            {
                newArray[i] = item;
                checkNewElement = true;
                continue;
            }
            if(checkNewElement)
                newArray[i] = mainArray[i-1];
            else
                newArray[i] = mainArray[i];
        }
        mainArray = newArray;
        return item;
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
        sortSelection(mainArray);

        int min = 0;
        int max = mainArray.length - 1;

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
