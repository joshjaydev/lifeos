package com.lifeos.app.ui.screens.actions;

import com.lifeos.domain.usecase.action.CompleteActionUseCase;
import com.lifeos.domain.usecase.action.CreateActionUseCase;
import com.lifeos.domain.usecase.action.DeleteActionUseCase;
import com.lifeos.domain.usecase.action.GetActionsByFolderUseCase;
import com.lifeos.domain.usecase.action.GetActionsUseCase;
import com.lifeos.domain.usecase.action.UpdateActionUseCase;
import com.lifeos.domain.usecase.folder.CreateFolderUseCase;
import com.lifeos.domain.usecase.folder.DeleteFolderUseCase;
import com.lifeos.domain.usecase.folder.GetFoldersUseCase;
import com.lifeos.domain.usecase.folder.UpdateFolderUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class ActionsViewModel_Factory implements Factory<ActionsViewModel> {
  private final Provider<GetActionsUseCase> getActionsUseCaseProvider;

  private final Provider<GetActionsByFolderUseCase> getActionsByFolderUseCaseProvider;

  private final Provider<CreateActionUseCase> createActionUseCaseProvider;

  private final Provider<UpdateActionUseCase> updateActionUseCaseProvider;

  private final Provider<DeleteActionUseCase> deleteActionUseCaseProvider;

  private final Provider<CompleteActionUseCase> completeActionUseCaseProvider;

  private final Provider<GetFoldersUseCase> getFoldersUseCaseProvider;

  private final Provider<CreateFolderUseCase> createFolderUseCaseProvider;

  private final Provider<UpdateFolderUseCase> updateFolderUseCaseProvider;

  private final Provider<DeleteFolderUseCase> deleteFolderUseCaseProvider;

  public ActionsViewModel_Factory(Provider<GetActionsUseCase> getActionsUseCaseProvider,
      Provider<GetActionsByFolderUseCase> getActionsByFolderUseCaseProvider,
      Provider<CreateActionUseCase> createActionUseCaseProvider,
      Provider<UpdateActionUseCase> updateActionUseCaseProvider,
      Provider<DeleteActionUseCase> deleteActionUseCaseProvider,
      Provider<CompleteActionUseCase> completeActionUseCaseProvider,
      Provider<GetFoldersUseCase> getFoldersUseCaseProvider,
      Provider<CreateFolderUseCase> createFolderUseCaseProvider,
      Provider<UpdateFolderUseCase> updateFolderUseCaseProvider,
      Provider<DeleteFolderUseCase> deleteFolderUseCaseProvider) {
    this.getActionsUseCaseProvider = getActionsUseCaseProvider;
    this.getActionsByFolderUseCaseProvider = getActionsByFolderUseCaseProvider;
    this.createActionUseCaseProvider = createActionUseCaseProvider;
    this.updateActionUseCaseProvider = updateActionUseCaseProvider;
    this.deleteActionUseCaseProvider = deleteActionUseCaseProvider;
    this.completeActionUseCaseProvider = completeActionUseCaseProvider;
    this.getFoldersUseCaseProvider = getFoldersUseCaseProvider;
    this.createFolderUseCaseProvider = createFolderUseCaseProvider;
    this.updateFolderUseCaseProvider = updateFolderUseCaseProvider;
    this.deleteFolderUseCaseProvider = deleteFolderUseCaseProvider;
  }

  @Override
  public ActionsViewModel get() {
    return newInstance(getActionsUseCaseProvider.get(), getActionsByFolderUseCaseProvider.get(), createActionUseCaseProvider.get(), updateActionUseCaseProvider.get(), deleteActionUseCaseProvider.get(), completeActionUseCaseProvider.get(), getFoldersUseCaseProvider.get(), createFolderUseCaseProvider.get(), updateFolderUseCaseProvider.get(), deleteFolderUseCaseProvider.get());
  }

  public static ActionsViewModel_Factory create(
      Provider<GetActionsUseCase> getActionsUseCaseProvider,
      Provider<GetActionsByFolderUseCase> getActionsByFolderUseCaseProvider,
      Provider<CreateActionUseCase> createActionUseCaseProvider,
      Provider<UpdateActionUseCase> updateActionUseCaseProvider,
      Provider<DeleteActionUseCase> deleteActionUseCaseProvider,
      Provider<CompleteActionUseCase> completeActionUseCaseProvider,
      Provider<GetFoldersUseCase> getFoldersUseCaseProvider,
      Provider<CreateFolderUseCase> createFolderUseCaseProvider,
      Provider<UpdateFolderUseCase> updateFolderUseCaseProvider,
      Provider<DeleteFolderUseCase> deleteFolderUseCaseProvider) {
    return new ActionsViewModel_Factory(getActionsUseCaseProvider, getActionsByFolderUseCaseProvider, createActionUseCaseProvider, updateActionUseCaseProvider, deleteActionUseCaseProvider, completeActionUseCaseProvider, getFoldersUseCaseProvider, createFolderUseCaseProvider, updateFolderUseCaseProvider, deleteFolderUseCaseProvider);
  }

  public static ActionsViewModel newInstance(GetActionsUseCase getActionsUseCase,
      GetActionsByFolderUseCase getActionsByFolderUseCase, CreateActionUseCase createActionUseCase,
      UpdateActionUseCase updateActionUseCase, DeleteActionUseCase deleteActionUseCase,
      CompleteActionUseCase completeActionUseCase, GetFoldersUseCase getFoldersUseCase,
      CreateFolderUseCase createFolderUseCase, UpdateFolderUseCase updateFolderUseCase,
      DeleteFolderUseCase deleteFolderUseCase) {
    return new ActionsViewModel(getActionsUseCase, getActionsByFolderUseCase, createActionUseCase, updateActionUseCase, deleteActionUseCase, completeActionUseCase, getFoldersUseCase, createFolderUseCase, updateFolderUseCase, deleteFolderUseCase);
  }
}
