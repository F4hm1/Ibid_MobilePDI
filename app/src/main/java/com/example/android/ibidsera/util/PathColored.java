package com.example.android.ibidsera.util;

import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.widget.TextView;

/**
 * Created by Randi Dwi Nandra on 22/11/2017.
 * randi.dwinandra@gmail.com
 */

public class PathColored {
    private Paint paint;
    private Path path;
    private float xNum;
    private float yNum;
    private Rect rect;
    private String textNumber;
    private boolean isPrint;

    public boolean isPrint() {
        return isPrint;
    }

    public void setPrint(boolean print) {
        isPrint = print;
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public String getTextNumber() {
        return textNumber;
    }

    public void setTextNumber(String textNumber) {
        this.textNumber = textNumber;
    }

    public PathColored(Path path) {
        this.path = path;
    }


    public PathColored(Paint paint, Path path, float xNum, float yNum, String number) {
        this.paint = paint;
        this.path = path;
        this.xNum = xNum;
        this.yNum = yNum;
        this.textNumber = number;
    }

    public PathColored(Paint paint, Path path) {
        this.paint = paint;
        this.path = path;
    }


    public float getxNum() {
        return xNum;
    }

    public void setxNum(float xNum) {
        this.xNum = xNum;
    }

    public float getyNum() {
        return yNum;
    }

    public void setyNum(float yNum) {
        this.yNum = yNum;
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
