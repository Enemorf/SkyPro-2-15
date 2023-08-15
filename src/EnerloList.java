import exceptions.IndexTooBigException;
import exceptions.ItemNotFoundException;

public class EnerloList implements StringList
{
    private String[] mainArray;

    public EnerloList (int length)
    {
        mainArray = new String[length];
    }

    @Override
    public String add(String item)
    {
        //проверка на null string

        for (int i = 0; i < mainArray.length; i++)
        {
            if(mainArray[i].isEmpty())
            {
                mainArray[i] = item;
                return item;
            }
        }

        String[] newArray = new String[mainArray.length + 1];
        for(int i = 0; i < newArray.length;i++)
        {
            if(i > mainArray.length)
            {
                newArray[i] = item;
                mainArray = newArray;
                return item;
            }
            newArray[i] = mainArray[i];
        }
        throw new RuntimeException();
    }

    @Override
    public String add(int index, String item)
    {
        //проверка на null string

        if(index >= mainArray.length)
            throw new IndexTooBigException("Индекс превышает размер массива");
        if(mainArray[index].isEmpty())
        {
            mainArray[index] = item;
            return item;
        }
        else
        {
            String[] newArray = new String[mainArray.length + 1];

            boolean isUseNewItem = false;

            for(int i = 0; i < mainArray.length; i++)
            {
                if(i == index)
                {
                    newArray[i] = item;
                    isUseNewItem = true;
                    continue;
                }
                if(isUseNewItem)
                    newArray[i+1] = mainArray[i];
                else
                    newArray[i] = mainArray[i];
            }
            mainArray = newArray;
            return item;
        }
    }

    @Override
    public String set(int index, String item)
    {
        //проверка на null string

        if(index >= mainArray.length)
            throw new IndexTooBigException("Индекс превышает размер массива");
        mainArray[index] = item;
        return item;
    }

    @Override
    public String remove(String item)
    {
        // проверка на null string

        boolean isRemoveComplete = false;
        String[] newArray = new String[mainArray.length -1];

        for(int i = 0; i < mainArray.length;i++)
        {
            if(mainArray[i].equals(item))
            {
                isRemoveComplete = true;
                continue;
            }
            if(isRemoveComplete)
                newArray[i-1] = mainArray[i];
            else
                newArray[i] = mainArray[i];

        }
        mainArray = newArray;
        return item;
    }

    @Override
    public String remove(int index)
    {
        if(index >= mainArray.length)
            throw new IndexTooBigException("Индекс превышает размер массива");

        if(mainArray[index].isEmpty())
            throw new ItemNotFoundException("По этому индексу нет предмета");

        String res = mainArray[index];
        boolean isRemoveComplete = false;
        String[] newArray = new String[mainArray.length -1];

        for(int i = 0; i < mainArray.length; i++)
        {
            if(i == index)
            {
                isRemoveComplete = true;
                continue;
            }
            if(isRemoveComplete)
                newArray[i-1] = mainArray[i];
            else
                newArray[i] = mainArray[i];
        }
        mainArray = newArray;
        return res;
    }

    @Override
    public boolean contains(String item)
    {
        for (String s : mainArray)
        {
            if (s.equals(item))
                return true;
        }
        return false;
    }

    @Override
    public int indexOf(String item)
    {
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
        for(int i = mainArray.length-1; i > 0; i--)
        {
            if(mainArray[i].equals(item))
                return i;
        }
        return -1;
    }

    @Override
    public String get(int index)
    {
        if(index > mainArray.length-1)
            throw new IndexTooBigException("Индекс превышает размер массива");

        return mainArray[index];
    }

    @Override
    public boolean equals(StringList otherList)
    {
        //сравниваем не только точное наполнение, но и расположение элементов

        if(mainArray.length != otherList.size())
            return false;

        for(int i =0; i < mainArray.length; i++)
        {
            if(!mainArray[i].equals(otherList.get(i)))
                return false;
        }
        return true;
    }

    @Override
    public int size()
    {
        return mainArray.length;
    }

    @Override
    public boolean isEmpty()
    {
        for (String s : mainArray) {
            if (!s.isEmpty())
                return false;
        }
        return true;
    }

    @Override
    public void clear()
    {
        mainArray = new String[mainArray.length];
    }

    @Override
    public String[] toArray()
    {
        return mainArray;
    }
}
