package com.bohdanvlad.controllers.menuController.menuCommands;

import com.bohdanvlad.presentationComponents.Presentation;
import com.bohdanvlad.fileAccessors.XMLAccessor;
import com.bohdanvlad.controllers.Command;

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
