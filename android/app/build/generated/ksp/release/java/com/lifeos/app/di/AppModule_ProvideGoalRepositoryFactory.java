package com.lifeos.app.di;

import com.lifeos.data.local.LocalDatabase;
import com.lifeos.domain.repository.GoalRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AppModule_ProvideGoalRepositoryFactory implements Factory<GoalRepository> {
  private final Provider<LocalDatabase> localDatabaseProvider;

  public AppModule_ProvideGoalRepositoryFactory(Provider<LocalDatabase> localDatabaseProvider) {
    this.localDatabaseProvider = localDatabaseProvider;
  }

  @Override
  public GoalRepository get() {
    return provideGoalRepository(localDatabaseProvider.get());
  }

  public static AppModule_ProvideGoalRepositoryFactory create(
      Provider<LocalDatabase> localDatabaseProvider) {
    return new AppModule_ProvideGoalRepositoryFactory(localDatabaseProvider);
  }

  public static GoalRepository provideGoalRepository(LocalDatabase localDatabase) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideGoalRepository(localDatabase));
  }
}
