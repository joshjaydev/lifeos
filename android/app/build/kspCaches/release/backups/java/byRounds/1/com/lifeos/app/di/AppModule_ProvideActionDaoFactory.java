package com.lifeos.app.di;

import com.lifeos.app.data.local.LifeOSDatabase;
import com.lifeos.app.data.local.dao.ActionDao;
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
public final class AppModule_ProvideActionDaoFactory implements Factory<ActionDao> {
  private final Provider<LifeOSDatabase> databaseProvider;

  public AppModule_ProvideActionDaoFactory(Provider<LifeOSDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ActionDao get() {
    return provideActionDao(databaseProvider.get());
  }

  public static AppModule_ProvideActionDaoFactory create(
      Provider<LifeOSDatabase> databaseProvider) {
    return new AppModule_ProvideActionDaoFactory(databaseProvider);
  }

  public static ActionDao provideActionDao(LifeOSDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideActionDao(database));
  }
}
