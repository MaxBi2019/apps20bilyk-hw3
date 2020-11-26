package ua.edu.ucu.smartarr;

import java.util.Arrays;

// Base array for decorators
public class BaseArray implements SmartArray
{
    private Object[] objects;

    public BaseArray(Object[] objects)
    {
        this.objects = objects;
    }

    @Override
    public Object[] toArray()
    {
        return objects;
    }

    @Override
    public String operationDescription()
    {
        return "Basic operation";
    }

    @Override
    public int size() {
        return objects.length;
    }
}
