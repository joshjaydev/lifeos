package com.lifeos.app.di;

import com.lifeos.domain.repository.FolderRepository;
import com.lifeos.domain.usecase.folder.CreateFolderUseCase;
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
public final class AppModule_ProvideCreateFolderUseCaseFactory implements Factory<CreateFolderUseCase> {
  private final Provider<FolderRepository> folderRepositoryProvider;

  public AppModule_ProvideCreateFolderUseCaseFactory(
      Provider<FolderRepository> folderRepositoryProvider) {
    this.folderRepositoryProvider = folderRepositoryProvider;
  }

  @Override
  public CreateFolderUseCase get() {
    return provideCreateFolderUseCase(folderRepositoryProvider.get());
  }

  public static AppModule_ProvideCreateFolderUseCaseFactory create(
      Provider<FolderRepository> folderRepositoryProvider) {
    return new AppModule_ProvideCreateFolderUseCaseFactory(folderRepositoryProvider);
  }

  public static CreateFolderUseCase provideCreateFolderUseCase(FolderRepository folderRepository) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideCreateFolderUseCase(folderRepository));
  }
}
