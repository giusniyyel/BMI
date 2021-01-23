/*
 * Created by Daniel Campos
 * Last modified 18/01/21 10:06 PM
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.giusniyyel.imc.common.Constants;
import com.giusniyyel.imc.common.utils.CommonUtils;
import com.giusniyyel.imc.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        configBtn();
    }

    private void configBtn() {
        mBinding.btnOk.setOnClickListener(view -> {
            if (CommonUtils.validateField(this, mBinding.etWeight) && CommonUtils.validateField(this, mBinding.etHeight)){
                Bundle args = new Bundle();
                args.putFloat(Constants.WEIGHT, CommonUtils.toFloat(mBinding.etWeight));
                args.putFloat(Constants.HEIGHT, CommonUtils.toFloat(mBinding.etHeight));
                FragmentManager fm = getSupportFragmentManager();
                IMCDialog imcDialog = new IMCDialog();
                imcDialog.setArguments(args);
                imcDialog.show(fm, getString(R.string.dialog_imc_title));
            }
        });
    }
}