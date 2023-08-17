package main;

import main.exertions.IndexTooBigException;
import main.exertions.ItemNotFoundException;
import main.exertions.StringIsNullException;

import java.util.Arrays;

public class EnerloList implements StringList
{
    private String[] mainArray;

    public EnerloList ()
    {
        mainArray = new String[0];
    }

    private void checkNullItem(String item)
    {
        if(item == null)
            throw new StringIsNullException("Передано пустое значение строки");
    }

    private void checkIndex(int index)
    {
        if(index >= mainArray.length)
            throw new IndexTooBigException("Слишком большое значение индекса");
    }

    @Override
    public String add(String item)
    {
        checkNullItem(item);

        String[] newArray = new String[mainArray.length +1];
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
        return null;
    }

    @Override
    public String add(int index, String item)
    {
        checkNullItem(item);
        checkIndex(index);

        String[] newArray = new String[mainArray.length+1];
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
    public String set(int index, String item)
    {
        checkNullItem(item);
        checkIndex(index);

        mainArray[index] = item;
        return item;
    }

    @Override
    public String remove(String item)
    {
        checkNullItem(item);
        String res = null;

        if(!contains(item))
            throw new ItemNotFoundException("Нет такого объекта");

        String[] newArray = new String[mainArray.length - 1];
        boolean checkNewElement = false;
        for (int i = 0; i < newArray.length; i++)
        {
            if(mainArray[i].equals(item))
            {
                checkNewElement = true;
                res = mainArray[i];
            }
            if(checkNewElement)
                newArray[i] = mainArray[i+1];
            else
                newArray[i] = mainArray[i];
        }
        mainArray = newArray;
        return res;
    }

    @Override
    public String remove(int index)
    {
        checkIndex(index);
        return remove(mainArray[index]);
    }

    @Override
    public boolean contains(String item)
    {
        checkNullItem(item);

        for (String s : mainArray) {
            if (s.equals(item))
                return true;
        }
        return false;
    }

    @Override
    public int indexOf(String item)
    {
        checkNullItem(item);

        for(int i = 0; i < mainArray.length; i++)
        {
            if(mainArray[i].equals(item))
                return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item)
    {
        checkNullItem(item);

        for(int i = mainArray.length-1;i >=0;i--)
        {
            if(mainArray[i].equals(item))
                return i;
        }
        return -1;
    }

    @Override
    public String get(int index)
    {
        checkIndex(index);
        return mainArray[index];
    }

    @Override
    public boolean equals(StringList otherList)
    {
        if(mainArray.length != otherList.size())
            return false;
        for(int i = 0; i < mainArray.length; i++)
        {
            if(!mainArray[i].equals(otherList.get(i)))
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
        mainArray = new String[0];
    }

    @Override
    public String[] toArray() {
        return mainArray;
    }
}
