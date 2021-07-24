/*
 * Copyright (C) 2011 ZXing authors
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

/**
 * Implementations encode according to some scheme for encoding contact information, like VCard or
 * MECARD.
 *
 * @author Sean Owen
 */
abstract class ContactEncoder {

    /**
     * @return null if s is null or empty, or result of s.trim() otherwise
     */
    static String trim(String s) {
        if (s == null) {
            return null;
        }
        String result = s.trim();
        return result.isEmpty() ? null : result;
    }
}
