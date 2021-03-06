package ua.edu.ucu.smartarr;

import ua.edu.ucu.functions.MyComparator;

import java.util.Arrays;

// Sorts elements using MyComparator to compare them
public class SortDecorator extends SmartArrayDecorator
{
    private SmartArray array;

    public SortDecorator(SmartArray smartArray,
                         MyComparator comparator)
    {
        super(smartArray);
        sort(smartArray, comparator);

    }

    private void sort(SmartArray arr, MyComparator comparator)
    {
        Object[] objects = arr.toArray();
        Arrays.sort(objects, comparator);
        array = new BaseArray(objects);
    }

    @Override
    public Object[] toArray()
    {
        return array.toArray();
    }

    @Override
    public String operationDescription()
    {
        return null;
    }

    @Override
    public int size()
    {
        return array.size();
    }
}
