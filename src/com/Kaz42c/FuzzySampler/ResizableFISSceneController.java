package com.Kaz42c.FuzzySampler;

public abstract class ResizableFISSceneController {
    protected static FIS model = new FIS();

    public FIS getModel() {
        return model;
    }

    public void reSize() {
        Main.stage.sizeToScene();
    }
}
