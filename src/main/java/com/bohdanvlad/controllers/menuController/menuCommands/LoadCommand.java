package com.bohdanvlad.controllers.menuController.menuCommands;

import com.bohdanvlad.presentationComponents.Presentation;
import java.io.IOException;

public class LoadCommand extends XMLAccessorCommand
{
    private static final int SLIDESETTER = 0;
    public LoadCommand(Presentation presentation)
    {
        super(presentation);
    }

    @Override
    public void execute(Object name)
    {
        try {
            this.presentation.clear();
            this.xmlAccessor.loadFile(this.presentation, (String) name);
            this.presentation.setSlideNumber(SLIDESETTER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
