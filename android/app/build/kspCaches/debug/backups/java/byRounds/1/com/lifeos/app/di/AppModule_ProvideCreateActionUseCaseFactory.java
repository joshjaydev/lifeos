package com.lifeos.app.di;

import com.lifeos.domain.repository.ActionRepository;
import com.lifeos.domain.usecase.action.CreateActionUseCase;
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
public final class AppModule_ProvideCreateActionUseCaseFactory implements Factory<CreateActionUseCase> {
  private final Provider<ActionRepository> actionRepositoryProvider;

  public AppModule_ProvideCreateActionUseCaseFactory(
      Provider<ActionRepository> actionRepositoryProvider) {
    this.actionRepositoryProvider = actionRepositoryProvider;
  }

  @Override
  public CreateActionUseCase get() {
    return provideCreateActionUseCase(actionRepositoryProvider.get());
  }

  public static AppModule_ProvideCreateActionUseCaseFactory create(
      Provider<ActionRepository> actionRepositoryProvider) {
    return new AppModule_ProvideCreateActionUseCaseFactory(actionRepositoryProvider);
  }

  public static CreateActionUseCase provideCreateActionUseCase(ActionRepository actionRepository) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideCreateActionUseCase(actionRepository));
  }
}
