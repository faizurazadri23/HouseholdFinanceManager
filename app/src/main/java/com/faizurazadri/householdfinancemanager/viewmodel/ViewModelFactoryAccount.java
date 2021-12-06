package com.faizurazadri.householdfinancemanager.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.faizurazadri.householdfinancemanager.data.source.repository.AccountRepository;
import com.faizurazadri.householdfinancemanager.injection.InjectionAccount;
import com.faizurazadri.householdfinancemanager.ui.viewmodel.AccountViewModel;

public class ViewModelFactoryAccount extends ViewModelProvider.NewInstanceFactory {

    private static volatile ViewModelFactoryAccount INSTANCE;

    private final AccountRepository accountRepository;

    public ViewModelFactoryAccount(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public static ViewModelFactoryAccount getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactoryAccount.class) {
                INSTANCE = new ViewModelFactoryAccount(InjectionAccount.accountRepository(context));
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AccountViewModel.class)) {
            return (T) new AccountViewModel(accountRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
