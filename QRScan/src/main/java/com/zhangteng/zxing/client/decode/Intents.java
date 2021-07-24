/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zhangteng.zxing.client.decode;

import android.content.Intent;

/**
 * This class provides the constants to use when sending an Intent to Barcode Scanner.
 * These strings are effectively API and cannot be changed.
 *
 * @author dswitkin@google.com (Daniel Switkin)
 */
public final class Intents {
  private Intents() {
  }

  /**
   * Constants related to the {@link Scan#ACTION} Intent.
   */
  public static final class Scan {
    /**
     * Send this intent to open the Barcodes app in scanning mode, find a barcode, and return
     * the results.
     */
    public static final String ACTION = "com.zhangteng.zxing.client.android.SCAN";

    /**
     * By default, sending this will decode all barcodes that we understand. However it
     * may be useful to limit scanning to certain formats. Use
     * {@link Intent#putExtra(String, String)} with one of the values below.
     *
     * Setting this is effectively shorthand for setting explicit formats with {@link #FORMATS}.
     * It is overridden by that setting.
     */
    public static final String MODE = "SCAN_MODE";

    /**
     * Decode only QR codes.
     */
    public static final String QR_CODE_MODE = "QR_CODE_MODE";

    /**
     * Comma-separated list of formats to scan for. The values must match the names of
     * {@link com.zhangteng.zxing.BarcodeFormat}
     * Example: "EAN_13,EAN_8,QR_CODE". This overrides {@link #MODE}.
     */
    public static final String FORMATS = "SCAN_FORMATS";

    /**
     * Optional parameter to specify the id of the camera from which to recognize barcodes.
     * Overrides the default camera that would otherwise would have been selected.
     * If provided, should be an int.
     */
    public static final String CAMERA_ID = "SCAN_CAMERA_ID";

    /**
     * @see com.zhangteng.zxing.DecodeHintType#CHARACTER_SET
     */
    public static final String CHARACTER_SET = "CHARACTER_SET";

    /**
     * Optional parameters to specify the width and height of the scanning rectangle in pixels.
     * The app will try to honor these, but will clamp them to the size of the preview frame.
     * You should specify both or neither, and pass the size as an int.
     */
    public static final String WIDTH = "SCAN_WIDTH";
    public static final String HEIGHT = "SCAN_HEIGHT";

    /**
     * Desired duration in milliseconds for which to pause after a successful scan before
     * returning to the calling intent. Specified as a long, not an integer!
     * For example: 1000L, not 1000.
     */
    public static final String RESULT_DISPLAY_DURATION_MS = "RESULT_DISPLAY_DURATION_MS";

    /**
     * Prompt to show on-screen when scanning by intent. Specified as a {@link String}.
     */
    public static final String PROMPT_MESSAGE = "PROMPT_MESSAGE";

    /**
     * If a barcode is found, Barcodes returns
     * of the app which requested the scan via
     * {@link android.app.Activity#startActivityForResult(Intent, int)}
     * The barcodes contents can be retrieved with
     * {@link Intent#getStringExtra(String)}.
     * If the user presses Back, the result code will be {@link android.app.Activity#RESULT_CANCELED}.
     */
    public static final String RESULT = "SCAN_RESULT";

    /**
     * Call {@link Intent#getStringExtra(String)} with {@code RESULT_FORMAT}
     * to determine which barcode format was found.
     * See {@link com.zhangteng.zxing.BarcodeFormat} for possible values.
     */
    public static final String RESULT_FORMAT = "SCAN_RESULT_FORMAT";

    /**
     * Call {@link Intent#getByteArrayExtra(String)} with {@code RESULT_BYTES}
     * to get a {@code byte[]} of raw bytes in the barcode, if available.
     */
    public static final String RESULT_BYTES = "SCAN_RESULT_BYTES";

    /**
     * Key for the value of {@link com.zhangteng.zxing.ResultMetadataType#ORIENTATION}, if available.
     * Call {@link Intent#getIntArrayExtra(String)} with {@code RESULT_ORIENTATION}.
     */
    public static final String RESULT_ORIENTATION = "SCAN_RESULT_ORIENTATION";

    /**
     * Key for the value of {@link com.zhangteng.zxing.ResultMetadataType#ERROR_CORRECTION_LEVEL}, if available.
     * Call {@link Intent#getStringExtra(String)} with {@code RESULT_ERROR_CORRECTION_LEVEL}.
     */
    public static final String RESULT_ERROR_CORRECTION_LEVEL = "SCAN_RESULT_ERROR_CORRECTION_LEVEL";

    /**
     * Prefix for keys that map to the values of {@link com.zhangteng.zxing.ResultMetadataType#BYTE_SEGMENTS},
     * if available. The actual values will be set under a series of keys formed by adding 0, 1, 2, ...
     * to this prefix. So the first byte segment is under key "SCAN_RESULT_BYTE_SEGMENTS_0" for example.
     * Call {@link Intent#getByteArrayExtra(String)} with these keys.
     */
    public static final String RESULT_BYTE_SEGMENTS_PREFIX = "SCAN_RESULT_BYTE_SEGMENTS_";

    private Scan() {
    }
  }

  /**
   * Constants related to the {@link Encode#ACTION} Intent.
   */
  public static final class Encode {
    /**
     * Send this intent to encode a piece of data as a QR code and display it full screen, so
     * that another person can scan the barcode from your screen.
     */
    public static final String ACTION = "com.google.zxing.client.android.ENCODE";

    /**
     * The data to encode. Use {@link Intent#putExtra(String, String)} or
     * {@link Intent#putExtra(String, android.os.Bundle)},
     * depending on the type and format specified. Non-QR Code formats should
     * just use a String here. For QR Code, see Contents for details.
     */
    public static final String DATA = "ENCODE_DATA";

    /**
     * The type of data being supplied if the format is QR Code. Use
     * {@link Intent#putExtra(String, String)} with one of {@link Contents.Type}.
     */
    public static final String TYPE = "ENCODE_TYPE";

    /**
     * The barcode format to be displayed. If this isn't specified or is blank,
     * it defaults to QR Code. Use {@link Intent#putExtra(String, String)}, where
     * format is one of {@link com.zhangteng.zxing.BarcodeFormat}.
     */
    public static final String FORMAT = "ENCODE_FORMAT";

    /**
     * Normally the contents of the barcode are displayed to the user in a TextView. Setting this
     * boolean to false will hide that TextView, showing only the encode barcode.
     */
    public static final String SHOW_CONTENTS = "ENCODE_SHOW_CONTENTS";

    private Encode() {
    }
  }
  // Not the best place for this, but, better than a new class
  // Should be FLAG_ACTIVITY_NEW_DOCUMENT in API 21+.
  // Defined once here because the current value is deprecated, so generates just one warning
  public static final int FLAG_NEW_DOC = Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
}
