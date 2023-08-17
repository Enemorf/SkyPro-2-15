package test;

import main.EnerloIntList;
import main.EnerloList;
import main.exertions.IndexTooBigException;
import main.exertions.ItemNotFoundException;
import main.exertions.StringIsNullException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import static test.ConstantsTest.*;

public class EnerloIntTest
{
    private final EnerloIntList out = new EnerloIntList();

    @Test
    public void checkAddToItemPositive()
    {
        assertDoesNotThrow(() -> out.add(INT_1));
        assertEquals(INT_1, out.get(0));
    }

    @Test
    public void checkAddToItemAndIndexTestPositive()
    {
        out.add(INT_1);
        out.add(INT_2);
        out.add(INT_3);
        out.add(1, INT_4);

        assertEquals(INT_4, out.get(1));
        assertEquals(4, out.size());
    }
    @Test
    public void checkAddToItemAndIndexTestNegative()
    {
        assertThrows(IndexTooBigException.class, ()-> out.add(5, INT_5));
    }

    @Test
    public void checkSetTestPositive()
    {
        out.add(INT_6);
        assertDoesNotThrow(()-> out.set(0, INT_7));
        assertEquals(INT_7, out.get(0));
    }

    @Test
    public void checkContainsTest()
    {
        out.add(INT_1);
        assertTrue(out.contains(INT_1));
        assertFalse(out.contains(INT_2));
    }

    @Test
    public void checkIndexOf()
    {
        out.add(INT_7);
        out.add(INT_8);

        assertEquals(0, out.indexOf(INT_7));
        assertEquals(1,out.indexOf(INT_8));
    }

    @Test
    public void checkLastIndexOf()
    {
        out.add(INT_7);
        out.add(INT_8);

        assertEquals(0, out.lastIndexOf(INT_7));
        assertEquals(1,out.lastIndexOf(INT_8));
    }

    @Test
    public void checkGet()
    {
        out.add(INT_5);
        assertEquals(INT_5, out.get(0));
    }

    @Test
    public void checkEquals()
    {
        EnerloIntList secondList = new EnerloIntList();
        secondList.add(INT_1);
        secondList.add(INT_3);
        secondList.add(INT_2);
        out.add(INT_1);
        out.add(INT_3);
        out.add(INT_2);

        assertTrue(out.equals(secondList));

        secondList.set(0,INT_4);
        assertFalse(out.equals(secondList));
    }

    @Test
    public void checkSize()
    {
        out.add(INT_4);
        out.add(INT_7);
        assertEquals(2, out.size());
    }

    @Test
    public void checkIsEmpty()
    {
        assertTrue(out.isEmpty());
        out.add(INT_3);
        assertFalse(out.isEmpty());
    }

    @Test
    public void checkClear()
    {
        out.add(INT_5);
        out.add(INT_7);

        out.clear();
        assertTrue(out.isEmpty());
    }

    @Test
    public void checkToArray()
    {
        Integer[] checkArray = new Integer[] {INT_1, INT_2, INT_3};

        out.add(INT_1);
        out.add(INT_2);
        out.add(INT_3);

        assertArrayEquals(checkArray, out.toArray());
    }
}
