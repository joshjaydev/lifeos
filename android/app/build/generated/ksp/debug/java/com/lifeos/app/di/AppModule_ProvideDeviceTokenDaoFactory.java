package com.lifeos.app.di;

import com.lifeos.app.data.local.LifeOSDatabase;
import com.lifeos.app.data.local.dao.DeviceTokenDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class AppModule_ProvideDeviceTokenDaoFactory implements Factory<DeviceTokenDao> {
  private final Provider<LifeOSDatabase> databaseProvider;

  public AppModule_ProvideDeviceTokenDaoFactory(Provider<LifeOSDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public DeviceTokenDao get() {
    return provideDeviceTokenDao(databaseProvider.get());
  }

  public static AppModule_ProvideDeviceTokenDaoFactory create(
      Provider<LifeOSDatabase> databaseProvider) {
    return new AppModule_ProvideDeviceTokenDaoFactory(databaseProvider);
  }

  public static DeviceTokenDao provideDeviceTokenDao(LifeOSDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideDeviceTokenDao(database));
  }
}
