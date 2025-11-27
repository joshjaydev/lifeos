package com.lifeos.app.di;

import com.lifeos.data.local.LocalDatabase;
import com.lifeos.domain.repository.PrincipleRepository;
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
public final class AppModule_ProvidePrincipleRepositoryFactory implements Factory<PrincipleRepository> {
  private final Provider<LocalDatabase> localDatabaseProvider;

  public AppModule_ProvidePrincipleRepositoryFactory(
      Provider<LocalDatabase> localDatabaseProvider) {
    this.localDatabaseProvider = localDatabaseProvider;
  }

  @Override
  public PrincipleRepository get() {
    return providePrincipleRepository(localDatabaseProvider.get());
  }

  public static AppModule_ProvidePrincipleRepositoryFactory create(
      Provider<LocalDatabase> localDatabaseProvider) {
    return new AppModule_ProvidePrincipleRepositoryFactory(localDatabaseProvider);
  }

  public static PrincipleRepository providePrincipleRepository(LocalDatabase localDatabase) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.providePrincipleRepository(localDatabase));
  }
}
