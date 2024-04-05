package com.bohdanvlad;

public abstract class XMLAccessorCommand implements Command
{
    protected XMLAccessor xmlAccessor;
    protected Presentation presentation;

    public XMLAccessorCommand(Presentation presentation)
    {
        this.presentation = presentation;
        this.xmlAccessor = new XMLAccessor();
    }
    
}
