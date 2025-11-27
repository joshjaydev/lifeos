package com.lifeos.app.di;

import com.lifeos.app.data.local.LifeOSDatabase;
import com.lifeos.app.data.local.dao.GoalDao;
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
public final class AppModule_ProvideGoalDaoFactory implements Factory<GoalDao> {
  private final Provider<LifeOSDatabase> databaseProvider;

  public AppModule_ProvideGoalDaoFactory(Provider<LifeOSDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public GoalDao get() {
    return provideGoalDao(databaseProvider.get());
  }

  public static AppModule_ProvideGoalDaoFactory create(Provider<LifeOSDatabase> databaseProvider) {
    return new AppModule_ProvideGoalDaoFactory(databaseProvider);
  }

  public static GoalDao provideGoalDao(LifeOSDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideGoalDao(database));
  }
}
