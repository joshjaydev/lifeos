package com.lifeos.app.di;

import com.lifeos.data.local.LocalDatabase;
import com.lifeos.domain.repository.NoteRepository;
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
public final class AppModule_ProvideNoteRepositoryFactory implements Factory<NoteRepository> {
  private final Provider<LocalDatabase> localDatabaseProvider;

  public AppModule_ProvideNoteRepositoryFactory(Provider<LocalDatabase> localDatabaseProvider) {
    this.localDatabaseProvider = localDatabaseProvider;
  }

  @Override
  public NoteRepository get() {
    return provideNoteRepository(localDatabaseProvider.get());
  }

  public static AppModule_ProvideNoteRepositoryFactory create(
      Provider<LocalDatabase> localDatabaseProvider) {
    return new AppModule_ProvideNoteRepositoryFactory(localDatabaseProvider);
  }

  public static NoteRepository provideNoteRepository(LocalDatabase localDatabase) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideNoteRepository(localDatabase));
  }
}
