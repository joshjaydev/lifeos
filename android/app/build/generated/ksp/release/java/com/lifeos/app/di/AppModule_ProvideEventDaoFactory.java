package com.lifeos.app.di;

import com.lifeos.app.data.local.LifeOSDatabase;
import com.lifeos.app.data.local.dao.EventDao;
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
public final class AppModule_ProvideEventDaoFactory implements Factory<EventDao> {
  private final Provider<LifeOSDatabase> databaseProvider;

  public AppModule_ProvideEventDaoFactory(Provider<LifeOSDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public EventDao get() {
    return provideEventDao(databaseProvider.get());
  }

  public static AppModule_ProvideEventDaoFactory create(Provider<LifeOSDatabase> databaseProvider) {
    return new AppModule_ProvideEventDaoFactory(databaseProvider);
  }

  public static EventDao provideEventDao(LifeOSDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideEventDao(database));
  }
}
