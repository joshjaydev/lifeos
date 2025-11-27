package com.lifeos.app.di;

import com.lifeos.domain.repository.FolderRepository;
import com.lifeos.domain.usecase.folder.DeleteFolderUseCase;
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
public final class AppModule_ProvideDeleteFolderUseCaseFactory implements Factory<DeleteFolderUseCase> {
  private final Provider<FolderRepository> folderRepositoryProvider;

  public AppModule_ProvideDeleteFolderUseCaseFactory(
      Provider<FolderRepository> folderRepositoryProvider) {
    this.folderRepositoryProvider = folderRepositoryProvider;
  }

  @Override
  public DeleteFolderUseCase get() {
    return provideDeleteFolderUseCase(folderRepositoryProvider.get());
  }

  public static AppModule_ProvideDeleteFolderUseCaseFactory create(
      Provider<FolderRepository> folderRepositoryProvider) {
    return new AppModule_ProvideDeleteFolderUseCaseFactory(folderRepositoryProvider);
  }

  public static DeleteFolderUseCase provideDeleteFolderUseCase(FolderRepository folderRepository) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideDeleteFolderUseCase(folderRepository));
  }
}
