/*
 * Created by Daniel Campos
 * Last modified 18/01/21 10:10 PM
 * Copyright (C) 2021 GiusNiyyel Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.giusniyyel.imc.common.utils;

import android.content.Context;
import android.widget.EditText;

import com.giusniyyel.imc.R;

import java.text.DecimalFormat;

public class CommonUtils {

    public static boolean validateField(Context context, EditText etField) {
        boolean isValid = true;

        if (toText(etField).isEmpty()) {
            etField.setError(context.getString(R.string.common_validate_field_required));
            etField.requestFocus();
            isValid = false;
        }

        return isValid;
    }

    public static String toText(EditText et) {
        return et.getText().toString().trim();
    }

    public static float toFloat(EditText et) {
        return Float.parseFloat(toText(et));
    }

    public static float roundTwoDecimals(float d){
        DecimalFormat twoForm = new DecimalFormat("#.##");
        return Float.parseFloat(twoForm.format(d));
    }
}
