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

package com.zhangteng.zxing.client.encode;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import com.zhangteng.zxing.BarcodeFormat;
import com.zhangteng.zxing.EncodeHintType;
import com.zhangteng.zxing.R;
import com.zhangteng.zxing.WriterException;
import com.zhangteng.zxing.client.decode.Contents;
import com.zhangteng.zxing.client.decode.Intents;
import com.zhangteng.zxing.common.BitMatrix;
import com.zhangteng.zxing.qrcode.QRCodeWriter;

import java.util.EnumMap;
import java.util.Map;

/**
 * This class does the work of decoding the user's request and extracting all the data
 * to be encoded in a barcode.
 *
 * @author dswitkin@google.com (Daniel Switkin)
 */
final class QRCodeEncoder {

    private static final String TAG = QRCodeEncoder.class.getSimpleName();

    private static final int WHITE = 0xFFFFFFFF;
    private static final int BLACK = 0xFF000000;

    private final Context activity;
    private String contents;
    private String displayContents;
    private String title;
    private BarcodeFormat format;
    private final int dimension;

    QRCodeEncoder(Context activity, Intent intent, int dimension) throws WriterException {
        this.activity = activity;
        this.dimension = dimension;
        encodeContentsFromZXingIntent(intent);
    }

    String getContents() {
        return contents;
    }

    String getDisplayContents() {
        return displayContents;
    }

    String getTitle() {
        return title;
    }

    // It would be nice if the string encoding lived in the core ZXing library,
    // but we use platform specific code like PhoneNumberUtils, so it can't.
    private void encodeContentsFromZXingIntent(Intent intent) {
        // Default to QR_CODE if no format given.
        String formatString = intent.getStringExtra(Intents.Encode.FORMAT);
        format = null;
        if (formatString != null) {
            try {
                format = BarcodeFormat.valueOf(formatString);
            } catch (IllegalArgumentException iae) {
                // Ignore it then
            }
        }
        String type = intent.getStringExtra(Intents.Encode.TYPE);
        if (format == null && type != null && !type.isEmpty()) {
            this.format = BarcodeFormat.QR_CODE;
            encodeQRCodeContents(intent, type);
        } else {
            this.format = BarcodeFormat.QR_CODE;
            String data = intent.getStringExtra(Intents.Encode.DATA);
            if (data != null && !data.isEmpty()) {
                contents = data;
                displayContents = data;
                title = activity.getString(R.string.contents_text);
            }
        }
    }

    private void encodeQRCodeContents(Intent intent, String type) {
        String textData = intent.getStringExtra(Intents.Encode.DATA);
        switch (type) {
            case Contents.Type.TEXT:
                if (textData != null && !textData.isEmpty()) {
                    contents = textData;
                    displayContents = textData;
                    title = activity.getString(R.string.contents_text);
                }
                break;
            default:
                if (textData != null && !textData.isEmpty()) {
                    contents = textData;
                    displayContents = textData;
                    title = activity.getString(R.string.contents_text);
                }
                break;
        }
    }

    Bitmap encodeAsBitmap() throws WriterException {
        String contentsToEncode = contents;
        if (contentsToEncode == null) {
            return null;
        }
        Map<EncodeHintType, Object> hints = null;
        String encoding = guessAppropriateEncoding(contentsToEncode);
        if (encoding != null) {
            hints = new EnumMap<>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, encoding);
        }
        BitMatrix result;
        try {
            result = new QRCodeWriter().encode(contentsToEncode, format, dimension, dimension, hints);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    private static String guessAppropriateEncoding(CharSequence contents) {
        // Very crude at the moment
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) > 0xFF) {
                return "UTF-8";
            }
        }
        return null;
    }

}
