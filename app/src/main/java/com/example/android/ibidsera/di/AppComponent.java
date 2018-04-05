package com.example.android.ibidsera.di;

import com.example.android.ibidsera.base.RxLazyFragment;
import com.example.android.ibidsera.view.activity.MainActivity;
import com.example.android.ibidsera.view.fragment.migrate.PersiapanFrag;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Fahmi Hakim on 13/03/2018.
 * for SERA
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(PersiapanFrag persiapanFrag);

}
