package com.lifeos.app.di;

import com.lifeos.domain.repository.ActionRepository;
import com.lifeos.domain.usecase.action.GetActionsByFolderUseCase;
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
public final class AppModule_ProvideGetActionsByFolderUseCaseFactory implements Factory<GetActionsByFolderUseCase> {
  private final Provider<ActionRepository> actionRepositoryProvider;

  public AppModule_ProvideGetActionsByFolderUseCaseFactory(
      Provider<ActionRepository> actionRepositoryProvider) {
    this.actionRepositoryProvider = actionRepositoryProvider;
  }

  @Override
  public GetActionsByFolderUseCase get() {
    return provideGetActionsByFolderUseCase(actionRepositoryProvider.get());
  }

  public static AppModule_ProvideGetActionsByFolderUseCaseFactory create(
      Provider<ActionRepository> actionRepositoryProvider) {
    return new AppModule_ProvideGetActionsByFolderUseCaseFactory(actionRepositoryProvider);
  }

  public static GetActionsByFolderUseCase provideGetActionsByFolderUseCase(
      ActionRepository actionRepository) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideGetActionsByFolderUseCase(actionRepository));
  }
}
