package com.lifeos.app.di;

import com.lifeos.domain.repository.FolderRepository;
import com.lifeos.domain.usecase.folder.GetFoldersUseCase;
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
public final class AppModule_ProvideGetFoldersUseCaseFactory implements Factory<GetFoldersUseCase> {
  private final Provider<FolderRepository> folderRepositoryProvider;

  public AppModule_ProvideGetFoldersUseCaseFactory(
      Provider<FolderRepository> folderRepositoryProvider) {
    this.folderRepositoryProvider = folderRepositoryProvider;
  }

  @Override
  public GetFoldersUseCase get() {
    return provideGetFoldersUseCase(folderRepositoryProvider.get());
  }

  public static AppModule_ProvideGetFoldersUseCaseFactory create(
      Provider<FolderRepository> folderRepositoryProvider) {
    return new AppModule_ProvideGetFoldersUseCaseFactory(folderRepositoryProvider);
  }

  public static GetFoldersUseCase provideGetFoldersUseCase(FolderRepository folderRepository) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideGetFoldersUseCase(folderRepository));
  }
}
