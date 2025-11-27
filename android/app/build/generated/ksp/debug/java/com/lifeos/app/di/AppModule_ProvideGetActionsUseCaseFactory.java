package com.lifeos.app.di;

import com.lifeos.domain.repository.ActionRepository;
import com.lifeos.domain.usecase.action.GetActionsUseCase;
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
public final class AppModule_ProvideGetActionsUseCaseFactory implements Factory<GetActionsUseCase> {
  private final Provider<ActionRepository> actionRepositoryProvider;

  public AppModule_ProvideGetActionsUseCaseFactory(
      Provider<ActionRepository> actionRepositoryProvider) {
    this.actionRepositoryProvider = actionRepositoryProvider;
  }

  @Override
  public GetActionsUseCase get() {
    return provideGetActionsUseCase(actionRepositoryProvider.get());
  }

  public static AppModule_ProvideGetActionsUseCaseFactory create(
      Provider<ActionRepository> actionRepositoryProvider) {
    return new AppModule_ProvideGetActionsUseCaseFactory(actionRepositoryProvider);
  }

  public static GetActionsUseCase provideGetActionsUseCase(ActionRepository actionRepository) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideGetActionsUseCase(actionRepository));
  }
}
