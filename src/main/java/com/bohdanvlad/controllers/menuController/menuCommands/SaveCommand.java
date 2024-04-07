package com.bohdanvlad.controllers.menuController.menuCommands;

import com.bohdanvlad.presentationComponents.Presentation;

import java.io.IOException;

public class SaveCommand extends XMLAccessorCommand
{
    public SaveCommand(Presentation presentation)
    {
        super(presentation);
    }

    @Override
    public void execute(Object name)
    {
        try {
            this.xmlAccessor.saveFile(this.presentation, (String) name);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
