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

import android.provider.ContactsContract;

/**
 * The set of constants to use when sending Barcode Scanner an Intent which requests a barcode
 * to be encoded.
 *
 * @author dswitkin@google.com (Daniel Switkin)
 */
public final class Contents {
  private Contents() {
  }

  /**
   * Contains type constants used when sending Intents.
   */
  public static final class Type {
    /**
     * Plain text. Use Intent.putExtra(DATA, string). This can be used for URLs too, but string
     * must include "http://" or "https://".
     */
    public static final String TEXT = "TEXT_TYPE";

    /**
     * A contact. Send a request to encode it as follows:
     * {@code
     * import android.provider.Contacts;
     *
     * Intent intent = new Intent(Intents.Encode.ACTION);
     * intent.putExtra(Intents.Encode.TYPE, CONTACT);
     * Bundle bundle = new Bundle();
     * bundle.putString(ContactsContract.Intents.Insert.NAME, "Jenny");
     * bundle.putString(ContactsContract.Intents.Insert.PHONE, "8675309");
     * bundle.putString(ContactsContract.Intents.Insert.EMAIL, "jenny@the80s.com");
     * bundle.putString(ContactsContract.Intents.Insert.POSTAL, "123 Fake St. San Francisco, CA 94102");
     * intent.putExtra(Intents.Encode.DATA, bundle);
     * }
     */
    public static final String CONTACT = "CONTACT_TYPE";

    private Type() {
    }
  }

  public static final String KEY_DECODE_QR = "preferences_decode_QR";

  public static final String KEY_PLAY_BEEP = "preferences_play_beep";
  public static final String KEY_VIBRATE = "preferences_vibrate";
  public static final String KEY_FRONT_LIGHT_MODE = "preferences_front_light_mode";
  public static final String KEY_AUTO_FOCUS = "preferences_auto_focus";
  public static final String KEY_INVERT_SCAN = "preferences_invert_scan";

  public static final String KEY_DISABLE_CONTINUOUS_FOCUS = "preferences_disable_continuous_focus";
  public static final String KEY_DISABLE_EXPOSURE = "preferences_disable_exposure";
  public static final String KEY_DISABLE_METERING = "preferences_disable_metering";
  public static final String KEY_DISABLE_BARCODE_SCENE_MODE = "preferences_disable_barcode_scene_mode";
  public static final String KEY_DISABLE_AUTO_ORIENTATION = "preferences_orientation";
}
