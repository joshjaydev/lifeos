package com.lifeos.app.di;

import com.lifeos.domain.repository.FolderRepository;
import com.lifeos.domain.usecase.folder.UpdateFolderUseCase;
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
public final class AppModule_ProvideUpdateFolderUseCaseFactory implements Factory<UpdateFolderUseCase> {
  private final Provider<FolderRepository> folderRepositoryProvider;

  public AppModule_ProvideUpdateFolderUseCaseFactory(
      Provider<FolderRepository> folderRepositoryProvider) {
    this.folderRepositoryProvider = folderRepositoryProvider;
  }

  @Override
  public UpdateFolderUseCase get() {
    return provideUpdateFolderUseCase(folderRepositoryProvider.get());
  }

  public static AppModule_ProvideUpdateFolderUseCaseFactory create(
      Provider<FolderRepository> folderRepositoryProvider) {
    return new AppModule_ProvideUpdateFolderUseCaseFactory(folderRepositoryProvider);
  }

  public static UpdateFolderUseCase provideUpdateFolderUseCase(FolderRepository folderRepository) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideUpdateFolderUseCase(folderRepository));
  }
}
