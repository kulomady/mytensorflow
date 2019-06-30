package com.mine.mytensorflow.mobilenet

import android.app.Activity
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.ArrayList
import java.util.Arrays
import java.util.Collections

/**
 * The most of those information can be found in GTSRB_TensorFlow_MobileNet.ipynb
 */
object MobilenetModelConfig {
    var MODEL_FILENAME = "g_model.lite"

    val INPUT_IMG_SIZE_WIDTH = 224
    val INPUT_IMG_SIZE_HEIGHT = 224
    val FLOAT_TYPE_SIZE = 4
    val PIXEL_SIZE = 3
    val MODEL_INPUT_SIZE = FLOAT_TYPE_SIZE * INPUT_IMG_SIZE_WIDTH * INPUT_IMG_SIZE_HEIGHT * PIXEL_SIZE
    val IMAGE_MEAN = 0
    val IMAGE_STD = 255.0f

    //This list can be taken from notebooks/output/labels_readable.txt
    val OUTPUT_LABELS = Collections.unmodifiableList(
        Arrays.asList(
            "20_speed",
            "30_speed",
            "50_speed",
            "60_speed",
            "70_speed",
            "80_speed",
            "80_lifted",
            "100_speed",
            "120_speed",
            "no_overtaking_general",
            "no_overtaking_trucks",
            "right_of_way_crossing",
            "right_of_way_general",
            "give_way",
            "stop",
            "no_way_general",
            "no_way_trucks",
            "no_way_one_way",
            "attention_general",
            "attention_left_turn",
            "attention_right_turn",
            "attention_curvy",
            "attention_bumpers",
            "attention_slippery",
            "attention_bottleneck",
            "attention_construction",
            "attention_traffic_light",
            "attention_pedestrian",
            "attention_children",
            "attention_bikes",
            "attention_snowflake",
            "attention_deer",
            "lifted_general",
            "turn_right",
            "turn_left",
            "turn_straight",
            "turn_straight_right",
            "turn_straight_left",
            "turn_right_down",
            "turn_left_down",
            "turn_circle",
            "lifted_no_overtaking_general",
            "lifted_no_overtaking_trucks"
        )
    )


    @Throws(IOException::class)
    private fun loadLabelList(activity: Activity, path:String): List<String> {
        val labels = ArrayList<String>()
        val reader = BufferedReader(InputStreamReader(activity.assets.open(path)))

        while ( reader.readLine() != null) {
            labels.add(reader.readLine())
        }
        reader.close()
        return labels
    }

    val MAX_CLASSIFICATION_RESULTS = 3
    val CLASSIFICATION_THRESHOLD = 0.1f
}