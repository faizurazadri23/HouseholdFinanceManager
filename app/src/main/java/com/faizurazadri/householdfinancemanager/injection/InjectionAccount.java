package com.faizurazadri.householdfinancemanager.injection;

import android.content.Context;

import com.faizurazadri.householdfinancemanager.Api.ApiConfig;
import com.faizurazadri.householdfinancemanager.data.source.remote.RemoteAccount;
import com.faizurazadri.householdfinancemanager.data.source.repository.AccountRepository;

public class InjectionAccount {

    public static AccountRepository accountRepository(Context context){
        RemoteAccount remoteAccount = RemoteAccount.getInstance(new ApiConfig(context));
        return AccountRepository.getInstance(remoteAccount);
    }
}
