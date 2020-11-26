package ua.edu.ucu.smartarr;
import ua.edu.ucu.functions.MyFunction;


// Map every element to another object using MyFunction
public class MapDecorator extends SmartArrayDecorator
{
    private SmartArray array;

    public MapDecorator(SmartArray smartArray,
                        MyFunction function)
    {
        super(smartArray);
        apply(smartArray, function);
    }

    private void apply(SmartArray arr, MyFunction function)
    {
        Object[] objects = arr.toArray();
        for (int ind = objects.length; ind > 0; --ind)
        {
            objects[ind-1] = function.apply(objects[ind-1]);
        }
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
