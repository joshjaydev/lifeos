package com.lifeos.data.local;

import com.lifeos.app.data.local.LifeOSDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class AndroidLocalDatabase_Factory implements Factory<AndroidLocalDatabase> {
  private final Provider<LifeOSDatabase> databaseProvider;

  public AndroidLocalDatabase_Factory(Provider<LifeOSDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public AndroidLocalDatabase get() {
    return newInstance(databaseProvider.get());
  }

  public static AndroidLocalDatabase_Factory create(Provider<LifeOSDatabase> databaseProvider) {
    return new AndroidLocalDatabase_Factory(databaseProvider);
  }

  public static AndroidLocalDatabase newInstance(LifeOSDatabase database) {
    return new AndroidLocalDatabase(database);
  }
}
