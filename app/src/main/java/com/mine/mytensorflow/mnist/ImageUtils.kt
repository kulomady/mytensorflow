package com.mine.mytensorflow.mnist

import android.graphics.*

object ImageUtils {

    private val INVERT = ColorMatrix(
        floatArrayOf(-1f, 0f, 0f, 0f, 255f, 0f, -1f, 0f, 0f, 255f, 0f, 0f, -1f, 0f, 255f, 0f, 0f, 0f, 1f, 0f)
    )

    private val BLACKWHITE = ColorMatrix(
        floatArrayOf(0.5f, 0.5f,0.5f, 0f, 0f, 0.5f, 0.5f, 0.5f, 0f, 0f, 0.5f, 0.5f, 0.5f, 0f, 0f, 0f, 0f, 0f, 1f, 0f, -1f, -1f, -1f, 0f, 1f
        )
    )

    /**
     * Make bitmap appropriate size, greyscale and inverted. MNIST model is originally teached on
     * dataset of images 28x28px with white letter written on black background.
     */
    fun prepareImageForClassification(bitmap: Bitmap): Bitmap {
        val colorMatrix = ColorMatrix()
        colorMatrix.setSaturation(0f)
        colorMatrix.postConcat(BLACKWHITE)
        colorMatrix.postConcat(INVERT)
        val f = ColorMatrixColorFilter(colorMatrix)

        val paint = Paint()
        paint.colorFilter = f

        val bmpGrayscale = Bitmap.createScaledBitmap(
            bitmap,
            MnistModelConfig.INPUT_IMG_SIZE_WIDTH,
            MnistModelConfig.INPUT_IMG_SIZE_HEIGHT,
            false
        )
        val canvas = Canvas(bmpGrayscale)
        canvas.drawBitmap(bmpGrayscale, 0f, 0f, paint)
        return bmpGrayscale
    }

}
