/*
 * Copyright (C) 2010 ZXing authors
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
import android.net.Uri;

import com.zhangteng.zxing.BarcodeFormat;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

final class DecodeFormatManager {

  private static final Pattern COMMA_PATTERN = Pattern.compile(",");

  static final Set<BarcodeFormat> QR_CODE_FORMATS = EnumSet.of(BarcodeFormat.QR_CODE);

  private static final Map<String,Set<BarcodeFormat>> FORMATS_FOR_MODE;
  static {
    FORMATS_FOR_MODE = new HashMap<>();
    FORMATS_FOR_MODE.put(Intents.Scan.QR_CODE_MODE, QR_CODE_FORMATS);
  }

  private DecodeFormatManager() {}

  static Set<BarcodeFormat> parseDecodeFormats(Intent intent) {
    Iterable<String> scanFormats = null;
    CharSequence scanFormatsString = intent.getStringExtra(Intents.Scan.FORMATS);
    if (scanFormatsString != null) {
      scanFormats = Arrays.asList(COMMA_PATTERN.split(scanFormatsString));
    }
    return parseDecodeFormats(scanFormats, intent.getStringExtra(Intents.Scan.MODE));
  }

  static Set<BarcodeFormat> parseDecodeFormats(Uri inputUri) {
    List<String> formats = inputUri.getQueryParameters(Intents.Scan.FORMATS);
    if (formats != null && formats.size() == 1 && formats.get(0) != null) {
      formats = Arrays.asList(COMMA_PATTERN.split(formats.get(0)));
    }
    return parseDecodeFormats(formats, inputUri.getQueryParameter(Intents.Scan.MODE));
  }

  private static Set<BarcodeFormat> parseDecodeFormats(Iterable<String> scanFormats, String decodeMode) {
    if (scanFormats != null) {
      Set<BarcodeFormat> formats = EnumSet.noneOf(BarcodeFormat.class);
      try {
        for (String format : scanFormats) {
          formats.add(BarcodeFormat.valueOf(format));
        }
        return formats;
      } catch (IllegalArgumentException iae) {
        // ignore it then
      }
    }
    if (decodeMode != null) {
      return FORMATS_FOR_MODE.get(decodeMode);
    }
    return null;
  }

}
