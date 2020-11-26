package ua.edu.ucu.smartarr;

import ua.edu.ucu.functions.MyComparator;

import java.util.Arrays;

// Sorts elements using MyComparator to compare them
public class SortDecorator extends SmartArrayDecorator
{
    private SmartArray array;

    public SortDecorator(SmartArray array,
                         MyComparator comparator)
    {
        super(array);
        sort(array, comparator);

    }

    private void sort(SmartArray array, MyComparator comparator)
    {
        Object[] objects = array.toArray();
        this.array = new BaseArray(Arrays.copyOf(objects, objects.length));
        Arrays.sort(this.array.toArray(), comparator);
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
