package com.bohdanvlad;

import java.io.IOException;

public class SaveCommand extends XMLAccessorCommand
{
    @Override
    public void execute(Object pres, Object name)
    {
        try {
            this.xmlAccessor.saveFile((Presentation) pres, (String) name);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
