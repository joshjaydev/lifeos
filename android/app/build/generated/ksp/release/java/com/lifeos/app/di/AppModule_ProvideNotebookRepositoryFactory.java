package com.lifeos.app.di;

import com.lifeos.data.local.LocalDatabase;
import com.lifeos.domain.repository.NotebookRepository;
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
public final class AppModule_ProvideNotebookRepositoryFactory implements Factory<NotebookRepository> {
  private final Provider<LocalDatabase> localDatabaseProvider;

  public AppModule_ProvideNotebookRepositoryFactory(Provider<LocalDatabase> localDatabaseProvider) {
    this.localDatabaseProvider = localDatabaseProvider;
  }

  @Override
  public NotebookRepository get() {
    return provideNotebookRepository(localDatabaseProvider.get());
  }

  public static AppModule_ProvideNotebookRepositoryFactory create(
      Provider<LocalDatabase> localDatabaseProvider) {
    return new AppModule_ProvideNotebookRepositoryFactory(localDatabaseProvider);
  }

  public static NotebookRepository provideNotebookRepository(LocalDatabase localDatabase) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideNotebookRepository(localDatabase));
  }
}
