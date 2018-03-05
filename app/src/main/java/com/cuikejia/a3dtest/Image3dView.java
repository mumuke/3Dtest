package com.cuikejia.a3dtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by cuikejia on 2018/3/1.
 */

public class Image3dView extends View {
    //   源视图，用于生成图片对象
    private View sourceView;

    //   根据传入的源视图生成的图片对象
    private Bitmap sourceBitmap;

    //源视图的宽度
    private float sourceWidth;

    //用于对图片进行的矩阵
    private Matrix matrix = new Matrix();

    //用于对图片进行三维操作
    private Camera camera = new Camera();

    public Image3dView(Context context) {
        super(context);
    }

    public Image3dView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Image3dView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (sourceBitmap == null) {
            getSourceBitmap();
        }
        float degree = 90 - (90 / sourceWidth) * getWidth();
        camera.save();
        camera.rotateY(degree);
        camera.getMatrix(matrix);
        camera.restore();

        matrix.preTranslate(0, -getHeight() / 2);
        matrix.postTranslate(0, getHeight() / 2);
        canvas.drawBitmap(sourceBitmap, matrix, null);
    }

    private void getSourceBitmap() {
        if (sourceView != null) {
            sourceView.setDrawingCacheEnabled(true);
            sourceView.layout(0, 0, sourceView.getWidth(), sourceView.getHeight());
            sourceView.buildDrawingCache();
            sourceBitmap = sourceView.getDrawingCache();
        }
    }

    public void setSourceView(View view) {
        sourceView = view;
        sourceWidth = sourceView.getWidth();
    }

    public void clearSourceBitmap() {
        if (sourceBitmap != null) {
            sourceBitmap = null;
        }
    }


}
