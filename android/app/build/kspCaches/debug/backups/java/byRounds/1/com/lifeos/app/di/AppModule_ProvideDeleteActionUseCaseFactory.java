package com.lifeos.app.di;

import com.lifeos.domain.repository.ActionRepository;
import com.lifeos.domain.usecase.action.DeleteActionUseCase;
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
public final class AppModule_ProvideDeleteActionUseCaseFactory implements Factory<DeleteActionUseCase> {
  private final Provider<ActionRepository> actionRepositoryProvider;

  public AppModule_ProvideDeleteActionUseCaseFactory(
      Provider<ActionRepository> actionRepositoryProvider) {
    this.actionRepositoryProvider = actionRepositoryProvider;
  }

  @Override
  public DeleteActionUseCase get() {
    return provideDeleteActionUseCase(actionRepositoryProvider.get());
  }

  public static AppModule_ProvideDeleteActionUseCaseFactory create(
      Provider<ActionRepository> actionRepositoryProvider) {
    return new AppModule_ProvideDeleteActionUseCaseFactory(actionRepositoryProvider);
  }

  public static DeleteActionUseCase provideDeleteActionUseCase(ActionRepository actionRepository) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideDeleteActionUseCase(actionRepository));
  }
}
