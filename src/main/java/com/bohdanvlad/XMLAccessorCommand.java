package com.bohdanvlad;

public abstract class XMLAccessorCommand implements Command
{
    protected XMLAccessor xmlAccessor;

    public XMLAccessorCommand()
    {
        this.xmlAccessor = new XMLAccessor();
    }
    
}
