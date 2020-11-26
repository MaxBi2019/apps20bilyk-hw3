package ua.edu.ucu.smartarr;

import java.util.Arrays;

// Remove duplicates from SmartArray. Use method equals() to compare objects
public class DistinctDecorator extends SmartArrayDecorator
{
    private SmartArray array;

    public DistinctDecorator(SmartArray smartArray)
    {
        super(smartArray);
        makeUnique(smartArray);
    }


    private void makeUnique(SmartArray arr)
    {
        Object[] objects = arr.toArray();
        Object[] unique  = new Object[objects.length];
        boolean isUnique;
        int index = 0;
        for (int indA = objects.length; indA > 0; --indA)
        {
            isUnique = true;
            for (int indB = indA-1; indB > 0; --indB)
            {
                if (objects[indA-1].equals(objects[indB-1]))
                {
                    isUnique = false;
                    break;
                }
            }
            if (isUnique)
            {
                unique[index++] = objects[indA-1];
            }
            array = new BaseArray(Arrays.copyOf(unique, index));
        }

    }

    @Override
    public Object[] toArray() {
        return array.toArray();
    }

    @Override
    public String operationDescription() {
        return null;
    }

    @Override
    public int size() {
        return array.size();
    }
}
