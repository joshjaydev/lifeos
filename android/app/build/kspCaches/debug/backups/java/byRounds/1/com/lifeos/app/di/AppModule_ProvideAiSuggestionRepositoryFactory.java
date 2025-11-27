package com.lifeos.app.di;

import com.lifeos.data.local.LocalDatabase;
import com.lifeos.domain.repository.AiSuggestionRepository;
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
public final class AppModule_ProvideAiSuggestionRepositoryFactory implements Factory<AiSuggestionRepository> {
  private final Provider<LocalDatabase> localDatabaseProvider;

  public AppModule_ProvideAiSuggestionRepositoryFactory(
      Provider<LocalDatabase> localDatabaseProvider) {
    this.localDatabaseProvider = localDatabaseProvider;
  }

  @Override
  public AiSuggestionRepository get() {
    return provideAiSuggestionRepository(localDatabaseProvider.get());
  }

  public static AppModule_ProvideAiSuggestionRepositoryFactory create(
      Provider<LocalDatabase> localDatabaseProvider) {
    return new AppModule_ProvideAiSuggestionRepositoryFactory(localDatabaseProvider);
  }

  public static AiSuggestionRepository provideAiSuggestionRepository(LocalDatabase localDatabase) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideAiSuggestionRepository(localDatabase));
  }
}
