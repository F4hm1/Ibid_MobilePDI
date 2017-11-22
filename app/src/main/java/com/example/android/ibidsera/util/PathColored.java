package com.example.android.ibidsera.util;

import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by Randi Dwi Nandra on 22/11/2017.
 * randi.dwinandra@gmail.com
 */

public class PathColored {
    private Paint paint;
    private Path path;

    public PathColored(Paint paint, Path path) {
        this.paint = paint;
        this.path = path;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }
}
