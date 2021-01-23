/*
 * Created by Daniel Campos
 * Last modified 18/01/21 10:20 PM
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

package com.giusniyyel.imc;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.giusniyyel.imc.common.Constants;
import com.giusniyyel.imc.common.utils.CommonUtils;
import com.giusniyyel.imc.databinding.DialogImcBinding;
import com.google.android.material.button.MaterialButton;

public class IMCDialog extends DialogFragment implements DialogInterface.OnShowListener {
    private DialogImcBinding mBinding;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setPositiveButton(R.string.common_dialog_button_ok, null);
        mBinding = DialogImcBinding.inflate(LayoutInflater.from(getActivity()));
        builder.setView(mBinding.getRoot());

        configData();

        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(this);
        return dialog;
    }

    private void configData() {
        Bundle args = getArguments();
        if (args != null) {
            float weight = args.getFloat(Constants.WEIGHT);
            float height = args.getFloat(Constants.HEIGHT);
            checkIMC(weight, height);
        }
    }

    @SuppressLint("SetTextI18n")
    private void checkIMC(float weight, float height){
        float imc = weight/(height*height);
        mBinding.dialogImc.setText(Float.toString(CommonUtils.roundTwoDecimals(imc)));
        if (imc < 16)
            mBinding.dialogImcClasification.setText(R.string.imc_clasification1);
        else if (imc >= 16 && imc < 17)
            mBinding.dialogImcClasification.setText(R.string.imc_clasification2);
        else if (imc >= 17 && imc < 18.50)
            mBinding.dialogImcClasification.setText(R.string.imc_clasification3);
        else if (imc >= 18.50 && imc < 25)
            mBinding.dialogImcClasification.setText(R.string.imc_clasification4);
        else if (imc >= 25 && imc < 30)
            mBinding.dialogImcClasification.setText(R.string.imc_clasification5);
        else if (imc >= 30 && imc < 35)
            mBinding.dialogImcClasification.setText(R.string.imc_clasification6);
        else if (imc >= 35 && imc <= 40)
            mBinding.dialogImcClasification.setText(R.string.imc_clasification7);
        else if (imc > 40)
            mBinding.dialogImcClasification.setText(R.string.imc_clasification8);
    }

    @Override
    public void onShow(DialogInterface dialogInterface) {
        final AlertDialog dialog = (AlertDialog) getDialog();
        if (dialog != null) {
            MaterialButton positiveButton = (MaterialButton) dialog.getButton(DialogInterface.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(view -> dismiss());
        }
    }
}
