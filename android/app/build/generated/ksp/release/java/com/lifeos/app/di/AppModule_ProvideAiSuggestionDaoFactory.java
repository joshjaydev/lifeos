package com.lifeos.app.di;

import com.lifeos.app.data.local.LifeOSDatabase;
import com.lifeos.app.data.local.dao.AiSuggestionDao;
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
public final class AppModule_ProvideAiSuggestionDaoFactory implements Factory<AiSuggestionDao> {
  private final Provider<LifeOSDatabase> databaseProvider;

  public AppModule_ProvideAiSuggestionDaoFactory(Provider<LifeOSDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public AiSuggestionDao get() {
    return provideAiSuggestionDao(databaseProvider.get());
  }

  public static AppModule_ProvideAiSuggestionDaoFactory create(
      Provider<LifeOSDatabase> databaseProvider) {
    return new AppModule_ProvideAiSuggestionDaoFactory(databaseProvider);
  }

  public static AiSuggestionDao provideAiSuggestionDao(LifeOSDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideAiSuggestionDao(database));
  }
}
