package ua.edu.ucu.smartarr;

import ua.edu.ucu.functions.MyFunction;

import java.util.Arrays;

// Map every element to another object using MyFunction
public class MapDecorator extends SmartArrayDecorator
{
    private SmartArray array;

    public MapDecorator(SmartArray array,
                        MyFunction function)
    {
        super(array);
        apply(array, function);
    }

    private void apply(SmartArray array, MyFunction function)
    {
        Object[] objects = array.toArray();
        this.array = new BaseArray(Arrays.copyOf(objects, objects.length));
        objects = this.array.toArray();
        for (int ind = objects.length; ind > 0; --ind)
        {
            objects[ind-1] = function.apply(objects[ind-1]);
        }
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
