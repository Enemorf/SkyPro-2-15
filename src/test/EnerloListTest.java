package test;

import main.EnerloList;
import main.StringList;
import main.exertions.IndexTooBigException;
import main.exertions.ItemNotFoundException;
import main.exertions.StringIsNullException;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static test.ConstantsTest.*;

public class EnerloListTest
{
    private final EnerloList out = new EnerloList();

    @Test
    public void checkAddToItemPositive()
    {
        assertDoesNotThrow(() -> out.add(STR_1));
        assertEquals(STR_1, out.get(0));
    }

    @Test
    public void checkAddToItemNegative()
    {
        assertThrows(StringIsNullException.class, ()-> out.add(null));
    }

    @Test
    public void checkAddToItemAndIndexTestPositive()
    {
        out.add(STR_1);
        out.add(STR_2);
        out.add(STR_3);
        out.add(1, STR_4);

        assertEquals(STR_4, out.get(1));
        assertEquals(4, out.size());
    }
    @Test
    public void checkAddToItemAndIndexTestNegative()
    {
        assertThrows(IndexTooBigException.class, ()-> out.add(5, STR_5));
        out.add(STR_1);
        assertThrows(StringIsNullException.class, ()-> out.add(0, null));
    }

    @Test
    public void checkSetTestPositive()
    {
        out.add(STR_6);
        assertDoesNotThrow(()-> out.set(0, STR_7));
        assertEquals(STR_7, out.get(0));
    }

    @Test
    public void checkRemoveTest()
    {
        out.add(STR_1);
        out.add(STR_2);
        out.add(STR_3);

        int index = out.size();

        assertDoesNotThrow(()-> out.remove(1));
        assertEquals(index-1, out.size());

        index = out.size();
        assertThrows(ItemNotFoundException.class, () -> out.remove(STR_2));
        assertDoesNotThrow(()-> out.remove(STR_1));
        assertEquals(index-1, out.size());
    }

    @Test
    public void checkContainsTest()
    {
        out.add(STR_1);
        assertTrue(out.contains(STR_1));
        assertFalse(out.contains(STR_2));
    }

    @Test
    public void checkIndexOf()
    {
        out.add(STR_7);
        out.add(STR_8);

        assertEquals(0, out.indexOf(STR_7));
        assertEquals(1,out.indexOf(STR_8));
    }

    @Test
    public void checkLastIndexOf()
    {
        out.add(STR_7);
        out.add(STR_8);

        assertEquals(0, out.lastIndexOf(STR_7));
        assertEquals(1,out.lastIndexOf(STR_8));
    }

    @Test
    public void checkGet()
    {
        out.add(STR_5);
        assertEquals(STR_5, out.get(0));
    }

    @Test
    public void checkEquals()
    {
        EnerloList secondList = new EnerloList();
        secondList.add(STR_1);
        secondList.add(STR_3);
        secondList.add(STR_2);
        out.add(STR_1);
        out.add(STR_3);
        out.add(STR_2);

        assertTrue(out.equals(secondList));

        secondList.set(0,STR_4);
        assertFalse(out.equals(secondList));

        secondList.remove(0);
        assertFalse(out.equals(secondList));
    }

    @Test
    public void checkSize()
    {
        out.add(STR_4);
        out.add(STR_5);
        assertEquals(2, out.size());
    }

    @Test
    public void checkIsEmpty()
    {
        assertTrue(out.isEmpty());
        out.add(STR_3);
        assertFalse(out.isEmpty());
    }

    @Test
    public void checkClear()
    {
        out.add(STR_5);
        out.add(STR_7);

        out.clear();
        assertTrue(out.isEmpty());
    }

    @Test
    public void checkToArray()
    {
        String[] checkArray = new String[] {STR_1, STR_2, STR_3};

        out.add(STR_1);
        out.add(STR_2);
        out.add(STR_3);

        assertArrayEquals(checkArray, out.toArray());
    }
}
