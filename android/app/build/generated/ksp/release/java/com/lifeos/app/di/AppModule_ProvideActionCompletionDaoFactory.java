package com.lifeos.app.di;

import com.lifeos.app.data.local.LifeOSDatabase;
import com.lifeos.app.data.local.dao.ActionCompletionDao;
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
public final class AppModule_ProvideActionCompletionDaoFactory implements Factory<ActionCompletionDao> {
  private final Provider<LifeOSDatabase> databaseProvider;

  public AppModule_ProvideActionCompletionDaoFactory(Provider<LifeOSDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ActionCompletionDao get() {
    return provideActionCompletionDao(databaseProvider.get());
  }

  public static AppModule_ProvideActionCompletionDaoFactory create(
      Provider<LifeOSDatabase> databaseProvider) {
    return new AppModule_ProvideActionCompletionDaoFactory(databaseProvider);
  }

  public static ActionCompletionDao provideActionCompletionDao(LifeOSDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideActionCompletionDao(database));
  }
}
