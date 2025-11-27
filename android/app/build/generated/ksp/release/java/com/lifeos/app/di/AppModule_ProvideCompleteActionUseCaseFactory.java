package com.lifeos.app.di;

import com.lifeos.domain.repository.ActionRepository;
import com.lifeos.domain.usecase.action.CompleteActionUseCase;
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
public final class AppModule_ProvideCompleteActionUseCaseFactory implements Factory<CompleteActionUseCase> {
  private final Provider<ActionRepository> actionRepositoryProvider;

  public AppModule_ProvideCompleteActionUseCaseFactory(
      Provider<ActionRepository> actionRepositoryProvider) {
    this.actionRepositoryProvider = actionRepositoryProvider;
  }

  @Override
  public CompleteActionUseCase get() {
    return provideCompleteActionUseCase(actionRepositoryProvider.get());
  }

  public static AppModule_ProvideCompleteActionUseCaseFactory create(
      Provider<ActionRepository> actionRepositoryProvider) {
    return new AppModule_ProvideCompleteActionUseCaseFactory(actionRepositoryProvider);
  }

  public static CompleteActionUseCase provideCompleteActionUseCase(
      ActionRepository actionRepository) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideCompleteActionUseCase(actionRepository));
  }
}
