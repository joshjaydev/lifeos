package com.lifeos.app.di;

import com.lifeos.domain.repository.ActionRepository;
import com.lifeos.domain.usecase.action.UpdateActionUseCase;
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
public final class AppModule_ProvideUpdateActionUseCaseFactory implements Factory<UpdateActionUseCase> {
  private final Provider<ActionRepository> actionRepositoryProvider;

  public AppModule_ProvideUpdateActionUseCaseFactory(
      Provider<ActionRepository> actionRepositoryProvider) {
    this.actionRepositoryProvider = actionRepositoryProvider;
  }

  @Override
  public UpdateActionUseCase get() {
    return provideUpdateActionUseCase(actionRepositoryProvider.get());
  }

  public static AppModule_ProvideUpdateActionUseCaseFactory create(
      Provider<ActionRepository> actionRepositoryProvider) {
    return new AppModule_ProvideUpdateActionUseCaseFactory(actionRepositoryProvider);
  }

  public static UpdateActionUseCase provideUpdateActionUseCase(ActionRepository actionRepository) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideUpdateActionUseCase(actionRepository));
  }
}
