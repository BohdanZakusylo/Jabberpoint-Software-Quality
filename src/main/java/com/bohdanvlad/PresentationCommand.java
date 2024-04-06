package com.bohdanvlad;

public abstract class PresentationCommand
{
    protected Presentation presentation;

    public PresentationCommand(Presentation presentation)
    {
        this.presentation = presentation;
    }

}
