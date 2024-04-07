package com.bohdanvlad.controllers.keyController.keyCommands;

import com.bohdanvlad.presentationComponents.Presentation;

public abstract class PresentationCommand
{
    protected Presentation presentation;

    public PresentationCommand(Presentation presentation)
    {
        this.presentation = presentation;
    }

}
