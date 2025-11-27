package com.lifeos.app.ui.screens.notes;

import com.lifeos.domain.repository.NoteRepository;
import com.lifeos.domain.repository.NotebookRepository;
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
public final class NotesViewModel_Factory implements Factory<NotesViewModel> {
  private final Provider<NoteRepository> noteRepositoryProvider;

  private final Provider<NotebookRepository> notebookRepositoryProvider;

  public NotesViewModel_Factory(Provider<NoteRepository> noteRepositoryProvider,
      Provider<NotebookRepository> notebookRepositoryProvider) {
    this.noteRepositoryProvider = noteRepositoryProvider;
    this.notebookRepositoryProvider = notebookRepositoryProvider;
  }

  @Override
  public NotesViewModel get() {
    return newInstance(noteRepositoryProvider.get(), notebookRepositoryProvider.get());
  }

  public static NotesViewModel_Factory create(Provider<NoteRepository> noteRepositoryProvider,
      Provider<NotebookRepository> notebookRepositoryProvider) {
    return new NotesViewModel_Factory(noteRepositoryProvider, notebookRepositoryProvider);
  }

  public static NotesViewModel newInstance(NoteRepository noteRepository,
      NotebookRepository notebookRepository) {
    return new NotesViewModel(noteRepository, notebookRepository);
  }
}
